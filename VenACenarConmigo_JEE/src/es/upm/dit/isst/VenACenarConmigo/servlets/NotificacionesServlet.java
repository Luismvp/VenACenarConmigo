package es.upm.dit.isst.VenACenarConmigo.servlets;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import es.upm.dit.isst.VenACenarConmigo.dao.AsistenciaConviteDAOImplementation;
import es.upm.dit.isst.VenACenarConmigo.dao.ComentarioConviteDAOImplementation;
import es.upm.dit.isst.VenACenarConmigo.dao.ConviteDAOImplementation;
import es.upm.dit.isst.VenACenarConmigo.dao.NotificacionDAOImplementation;
import es.upm.dit.isst.VenACenarConmigo.dao.model.AsistenciaConvite;
import es.upm.dit.isst.VenACenarConmigo.dao.model.ComentarioConvite;
import es.upm.dit.isst.VenACenarConmigo.dao.model.Convite;
import es.upm.dit.isst.VenACenarConmigo.dao.model.Notificacion;

@WebServlet("/NotificacionesServlet")

public class NotificacionesServlet extends HttpServlet {
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String email = (String) req.getSession().getAttribute("email");
		List<Notificacion> notificaciones = NotificacionDAOImplementation.getInstance().readAllNotificacion();
		List<Notificacion> notificacionesUsuario = new ArrayList<>();
		Calendar ahora = Calendar.getInstance();

		for (Notificacion n : notificaciones) {
			if (null != n.getConvite().getFechaYHoraComienzo()
					&& n.getConvite().getFechaYHoraComienzo().compareTo(ahora) < 0) {
				n.setHasStarted(true);
				NotificacionDAOImplementation.getInstance().updateNotificacion(n);
			}
			if (null != n.getConvite().getFechaYHoraFin() && n.getConvite().getFechaYHoraFin().compareTo(ahora) < 0) {
				n.setHasStarted(true);
				n.setHasFinished(true);
				NotificacionDAOImplementation.getInstance().updateNotificacion(n);
			}
		}
		for (int i = notificaciones.size() - 1; i >= 0; i--) {
			if (notificaciones.get(i).getAsistencia() != null) {
				// Recogemos las invitaciones del usuario si las tiene
				if (notificaciones.get(i).getAsistencia().getEmailUsuarioAsistente().equals(email)
						&& notificaciones.get(i).getAsistencia().getConfirmado() == false
						&& notificaciones.get(i).getAsistencia().getInvitacionInscripcion() == 1
						&& !notificaciones.get(i).isHasStarted() && !notificaciones.get(i).isHasFinished()) {
					notificacionesUsuario.add(notificaciones.get(i));
				}
				// Si el usuario ha confirmado que irá pero aun no ha terminado el convite
				if (notificaciones.get(i).getAsistencia().getEmailUsuarioAsistente().equals(email)
						&& notificaciones.get(i).getAsistencia().getConfirmado()
						&& !notificaciones.get(i).isHasFinished()) {
					notificacionesUsuario.add(notificaciones.get(i));
				}
				// Si el usuario asiste al convite como anfitrion o como invitado y ha empezado
				if ((notificaciones.get(i).getAsistencia().getEmailUsuarioAsistente().equals(email)
						|| notificaciones.get(i).getConvite().getEmailAnfitrion().equals(email))
						&& (notificaciones.get(i).isHasStarted() && !notificaciones.get(i).isHasFinished())) {
					if (notificacionesUsuario.isEmpty()) {
						notificacionesUsuario.add(notificaciones.get(i));
						if (notificacionesUsuario.isEmpty()) {
							notificacionesUsuario.add(notificaciones.get(i));
						} else {
							List<Integer> convites = new ArrayList<>();
							for (Notificacion o : notificacionesUsuario) {
								convites.add(o.getConvite().getIdConvite());
							}
							if (!contiene(convites, notificaciones.get(i).getConvite().getIdConvite())) {
								notificacionesUsuario.add(notificaciones.get(i));
							}
						}
					}
				}
				// Si el usuario asiste al convite como anfitrion o invitado y ha terminado
				if ((notificaciones.get(i).getAsistencia().getEmailUsuarioAsistente().equals(email)
						|| notificaciones.get(i).getConvite().getEmailAnfitrion().equals(email))
						&& notificaciones.get(i).isHasFinished()) {
					if (notificacionesUsuario.isEmpty()) {
						notificacionesUsuario.add(notificaciones.get(i));
					} else {
						List<Integer> convites = new ArrayList<>();
						for (Notificacion o : notificacionesUsuario) {
							convites.add(o.getConvite().getIdConvite());
						}
						if (!contiene(convites, notificaciones.get(i).getConvite().getIdConvite())) {
							notificacionesUsuario.add(notificaciones.get(i));
						}
					}
				}
			}
		}

		int checked = 0;
		for (Notificacion n : notificacionesUsuario) {
			if (n.getAsistencia() != null && n.getAsistencia().getConfirmado() && !n.isChecked()) {
				n.setChecked(true);
				NotificacionDAOImplementation.getInstance().updateNotificacion(n);
				checked++;
			}
			if(n.isHasFinished()) {
				checked++;
			}
		}
		req.getSession().setAttribute("lista_notificaciones", notificacionesUsuario);
		req.getSession().setAttribute("numero_notificaciones",
				(Integer) req.getSession().getAttribute("numero_notificaciones") - checked);
		resp.sendRedirect(req.getContextPath() + "/Notificaciones.jsp");
	}

	private boolean contiene(List<Integer> convites, Integer j) {
		boolean resultado = false;
		for (Integer i : convites) {
			if (j == i) {
				resultado = true;
			}
		}
		return resultado;
	}

}
