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
import es.upm.dit.isst.VenACenarConmigo.dao.ConviteDAOImplementation;
import es.upm.dit.isst.VenACenarConmigo.dao.NotificacionDAOImplementation;
import es.upm.dit.isst.VenACenarConmigo.dao.model.AsistenciaConvite;
import es.upm.dit.isst.VenACenarConmigo.dao.model.Convite;
import es.upm.dit.isst.VenACenarConmigo.dao.model.Notificacion;

@WebServlet("/AceptaInvitacionServlet")

public class AceptaInvitacionServlet extends HttpServlet {

	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		int idConvite = Integer.parseInt(req.getParameter("idConvite"));
		String email = (String) req.getSession().getAttribute("email");
		List<AsistenciaConvite> asistente = AsistenciaConviteDAOImplementation.getInstance().readAllAsistenciaConvite();
		AsistenciaConvite asistenteConfirma = new AsistenciaConvite();
		for (int i = 0; i < asistente.size(); i++) {
			if (asistente.get(i).getIdConvite() == idConvite
					&& asistente.get(i).getEmailUsuarioAsistente().equals(email)) {
				asistenteConfirma = asistente.get(i);
			}
		}

		asistenteConfirma.setConfirmado(true);

		AsistenciaConviteDAOImplementation.getInstance().updateAsistenciaConvite(asistenteConfirma);
		List<Notificacion> notificaciones = NotificacionDAOImplementation.getInstance().readAllNotificacion();
		for (Notificacion n : notificaciones) {
			if (n.getAsistencia() != null) {
				if (n.getAsistencia().equals(asistenteConfirma) && n.getConvite().getIdConvite() == idConvite) {
					AsistenciaConvite asistencia = n.getAsistencia();
					asistencia.setConfirmado(true);
					n.setAsistencia(asistencia);
					NotificacionDAOImplementation.getInstance().updateNotificacion(n);
				}
			}
		}
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
		List<Notificacion> notificacionesUsuario = new ArrayList<>();
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
		List<AsistenciaConvite> asistenciaConvite = AsistenciaConviteDAOImplementation.getInstance()
				.readAllAsistenciaConvite();
		List<AsistenciaConvite> asistenciaConvite2 = new ArrayList<>();
		List<AsistenciaConvite> asistenciaConvite3 = new ArrayList<>();
		for (int i = 0; i < asistenciaConvite.size(); i++) {
			if (asistenciaConvite.get(i).getInvitacionInscripcion() == 1
					&& asistenciaConvite.get(i).getConfirmado() == false
					&& asistenciaConvite.get(i).getEmailUsuarioAsistente().equals(email)) {
				asistenciaConvite2.add(asistenciaConvite.get(i));
				log("estoy encontrando un false");
			}
		}
		if (null != req.getSession().getAttribute("Lista_convites")) {
			req.getSession().setAttribute("Lista_convites", asistenciaConvite2);
			req.getSession().setAttribute("numero_notificaciones",
					(Integer) req.getSession().getAttribute("numero_notificaciones") - 1);
		} else {
			req.getSession().removeAttribute("Lista_convites");
			req.getSession().setAttribute("Lista_convites", asistenciaConvite2);
			req.getSession().setAttribute("numero_notificaciones",
					(Integer) req.getSession().getAttribute("numero_notificaciones") - 1);
		}
		List<Convite> convites = new ArrayList<>();
		List<Convite> convitesConfirmados = new ArrayList<>();
		if (!asistenciaConvite2.isEmpty()) {
			for (int i = 0; i < asistenciaConvite2.size(); i++) {
				convites.add((Convite) ConviteDAOImplementation.getInstance()
						.readConvite(asistenciaConvite2.get(i).getIdConvite()));
			}
		}
		for (int i = 0; i < asistenciaConvite.size(); i++) {
			if (asistenciaConvite.get(i).getInvitacionInscripcion() == 1
					&& asistenciaConvite.get(i).getConfirmado() == true
					&& asistenciaConvite.get(i).getEmailUsuarioAsistente().equals(email)) {
				asistenciaConvite3.add(asistenciaConvite.get(i));
			}
		}
		if (!asistenciaConvite3.isEmpty()) {
			for (int i = 0; i < asistenciaConvite3.size(); i++) {
				convitesConfirmados.add((Convite) ConviteDAOImplementation.getInstance()
						.readConvite(asistenciaConvite3.get(i).getIdConvite()));
			}
		}
		if (!convites.isEmpty()) {
			req.getSession().setAttribute("Lista_convites", convites);
		} else {
			req.getSession().setAttribute("Lista_convites", null);
		}
		if (!convitesConfirmados.isEmpty()) {
			req.getSession().setAttribute("convitesConfirmados", convitesConfirmados);
		}
		boolean enNotificaciones = Boolean.parseBoolean(req.getParameter("enNotificaciones"));
		if (enNotificaciones) {
			req.getSession().setAttribute("lista_notificaciones", notificacionesUsuario);
			resp.sendRedirect(req.getContextPath() + "/Notificaciones.jsp");
		} else {
			List<AsistenciaConvite> asistentes2 = new ArrayList<>();
			for (int i = 0; i < asistenciaConvite.size(); i++) {
				if (asistenciaConvite.get(i).getIdConvite() == idConvite) {
					asistentes2.add(asistenciaConvite.get(i));
				}
			}
			boolean esInvitadoPendiente = false;
			boolean esAsistenteConfirmado = true;
			req.getSession().setAttribute("convite", ConviteDAOImplementation.getInstance().readConvite(idConvite));
			req.getSession().setAttribute("lista_invitados", asistentes2);
			req.getSession().setAttribute("conviteFin", 0);
			req.getSession().setAttribute("esInvitadoPendiente", esInvitadoPendiente);
			req.getSession().setAttribute("esAsistenteConfirmado", esAsistenteConfirmado);
			resp.sendRedirect(req.getContextPath() + "/Convite.jsp");
		}

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
