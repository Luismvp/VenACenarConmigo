package es.upm.dit.isst.VenACenarConmigo.servlets;

import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.InputStream;
import javax.imageio.ImageIO;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import es.upm.dit.isst.VenACenarConmigo.dao.AsistenciaConviteDAOImplementation;
import es.upm.dit.isst.VenACenarConmigo.dao.ConviteDAOImplementation;
import es.upm.dit.isst.VenACenarConmigo.dao.NotificacionDAOImplementation;
import es.upm.dit.isst.VenACenarConmigo.dao.PublicacionesDAOImplementation;
import es.upm.dit.isst.VenACenarConmigo.dao.UsuarioDAOImplementation;
import es.upm.dit.isst.VenACenarConmigo.dao.ValoracionDAOImplementation;
import es.upm.dit.isst.VenACenarConmigo.dao.model.Publicaciones;
import es.upm.dit.isst.VenACenarConmigo.dao.model.AsistenciaConvite;
import es.upm.dit.isst.VenACenarConmigo.dao.model.Convite;
import es.upm.dit.isst.VenACenarConmigo.dao.model.Notificacion;
import es.upm.dit.isst.VenACenarConmigo.dao.model.Usuario;
import es.upm.dit.isst.VenACenarConmigo.dao.model.Valoracion;

@WebServlet("/LoginServlet")

public class LoginServlet extends HttpServlet {
	private final String ADMIN_EMAIL = "root";
	private final String ADMIN_PASSWORD = "root";

	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String email = req.getParameter("email");
		log(email);
		String password = req.getParameter("password");
		Usuario usuario = UsuarioDAOImplementation.getInstance().loginUsuario(email, password);
		List<Publicaciones> publicaciones = PublicacionesDAOImplementation.getInstance().readAllPublicaciones();
		List<Publicaciones> publicacionesUsuario = new ArrayList<>();
		if (null != publicaciones && !publicaciones.isEmpty()) {
			for (int i = publicaciones.size() - 1; i >= 0; i--) {
				if (publicaciones.get(i).getUsuario().getEmail().equals(email)) {
					publicacionesUsuario.add(publicaciones.get(i));
				}
			}
		}
		BufferedImage fotoPerfil = null;
		List<AsistenciaConvite> asistenciaConvite = new ArrayList<>();
		if (null == req.getAttribute("asistenciaConvite")) {
			asistenciaConvite = AsistenciaConviteDAOImplementation.getInstance().readAllAsistenciaConvite();
			req.setAttribute("asistenciaConvite", asistenciaConvite);
		} else {
			asistenciaConvite = (List<AsistenciaConvite>) req.getAttribute("asistenciaConvite");
		}
		List<AsistenciaConvite> asistenciaConvite2 = new ArrayList<>();
		for (int i = 0; i < asistenciaConvite.size(); i++) {
			if (asistenciaConvite.get(i).getInvitacionInscripcion() == 1
					&& asistenciaConvite.get(i).getConfirmado() == false
					&& asistenciaConvite.get(i).getEmailUsuarioAsistente().equals(email)) {
				asistenciaConvite2.add(asistenciaConvite.get(i));
			}
		}
		int numeroNotificaciones = asistenciaConvite2.size();
		log(Integer.toString(numeroNotificaciones));

		double valoracion_media = 0.00;
		int num_valoraciones = 0;
		List<Valoracion> valoraciones = ValoracionDAOImplementation.getInstance().readAllValoracion();
		for (Valoracion valoracion : valoraciones) {
			if (valoracion.getUsuarioValorado().equals(email)) {
				valoracion_media += (valoracion.getPuntuacion() * 2);
				num_valoraciones++;
			}
		}
		valoracion_media = valoracion_media / num_valoraciones;

		if (ADMIN_EMAIL.equals(email) && ADMIN_PASSWORD.equals(password)
				&& req.getSession().getAttribute("usuario") == null) {
			req.getSession().setAttribute("adminLogged", true);
			req.getSession().setAttribute("usuario_list", UsuarioDAOImplementation.getInstance().readAllUsuarios());
			req.getSession().setAttribute("convite_list", ConviteDAOImplementation.getInstance().readAllConvite());
			req.getSession().setAttribute("lista_publicaciones_usuario", publicacionesUsuario);
			req.getSession().setAttribute("asistente_list",
					AsistenciaConviteDAOImplementation.getInstance().readAllAsistenciaConvite());
			req.getSession().setAttribute("valoracion_list", valoraciones);
			resp.sendRedirect(req.getContextPath() + "/ListaUsuarios.jsp");
		} else if (null != usuario && req.getSession().getAttribute("usuario") == null) {
			log("estoy aqui");
			log(email);
			log(Integer.toString(numeroNotificaciones));
			if (num_valoraciones > 0) {
				req.getSession().setAttribute("valoracion_media", valoracion_media);
			}
			List<Notificacion> notificaciones = NotificacionDAOImplementation.getInstance().readAllNotificacion();
			List<Notificacion> notificacionesUsuario = new ArrayList<>();
			Calendar ahora = Calendar.getInstance();
			for (Notificacion n : notificaciones) {
				if (null != n.getConvite().getFechaYHoraComienzo()
						&& n.getConvite().getFechaYHoraComienzo().compareTo(ahora) < 0) {
					n.setHasStarted(true);
					n.setChecked(false);
					NotificacionDAOImplementation.getInstance().updateNotificacion(n);
				}
				if (null != n.getConvite().getFechaYHoraFin()
						&& n.getConvite().getFechaYHoraFin().compareTo(ahora) < 0) {
					n.setHasStarted(true);
					n.setHasFinished(true);
					n.setChecked(false);
					NotificacionDAOImplementation.getInstance().updateNotificacion(n);
				}
			}
			for (int i = notificaciones.size() - 1; i >= 0; i--) {
				if (notificaciones.get(i).getAsistencia() != null) {
					if (notificaciones.get(i).getAsistencia().getEmailUsuarioAsistente().equals(email)
							&& notificaciones.get(i).getAsistencia().getConfirmado() == false
							&& notificaciones.get(i).getAsistencia().getInvitacionInscripcion() == 1
							&& !notificaciones.get(i).isHasStarted() && !notificaciones.get(i).isHasFinished()) {
						notificacionesUsuario.add(notificaciones.get(i));
					}
					if (notificaciones.get(i).getAsistencia().getEmailUsuarioAsistente().equals(email)
							&& notificaciones.get(i).getAsistencia().getConfirmado()
							&& notificaciones.get(i).getAsistencia().getInvitacionInscripcion() == 2
							&& !notificaciones.get(i).isHasFinished() && !notificaciones.get(i).isChecked()) {
						notificacionesUsuario.add(notificaciones.get(i));

					}
					if ((notificaciones.get(i).getAsistencia().getEmailUsuarioAsistente().equals(email)
							|| notificaciones.get(i).getConvite().getEmailAnfitrion().equals(email))
							&& (notificaciones.get(i).isHasStarted()) && !notificaciones.get(i).isChecked()) {
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
					if ((notificaciones.get(i).getAsistencia().getEmailUsuarioAsistente().equals(email)
							|| notificaciones.get(i).getConvite().getEmailAnfitrion().equals(email))
							&& (notificaciones.get(i).isHasStarted()) && (notificaciones.get(i).isHasFinished())
							&& !notificaciones.get(i).isChecked()) {
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
				if (n.getAsistencia() != null && !n.getAsistencia().getConfirmado() && !n.isChecked()) {
					NotificacionDAOImplementation.getInstance().updateNotificacion(n);
					checked++;
				}
			}

			req.getSession().setAttribute("usuario", usuario);
			req.getSession().setAttribute("email", email);
			req.getSession().setAttribute("lista_publicaciones_usuario", publicacionesUsuario);
			req.getSession().setAttribute("numero_notificaciones", checked);
			req.getSession().setAttribute("fotoPerfil", fotoPerfil);
			resp.sendRedirect(req.getContextPath() + "/Perfil.jsp");
		} else if (req.getSession().getAttribute("usuario") != null && usuario != null) {
			log("ahora estoy aqui");
			log(Integer.toString(numeroNotificaciones));
			if (num_valoraciones > 0) {
				req.getSession().setAttribute("valoracion_media", valoracion_media);
			}
			List<Notificacion> notificaciones = NotificacionDAOImplementation.getInstance().readAllNotificacion();
			List<Notificacion> notificacionesUsuario = new ArrayList<>();
			for (int i = notificaciones.size() - 1; i >= 0; i--) {
				if (notificaciones.get(i).getAsistencia() != null) {
					if (notificaciones.get(i).getAsistencia().getEmailUsuarioAsistente().equals(email)
							&& notificaciones.get(i).getAsistencia().getConfirmado() == false
							&& notificaciones.get(i).getAsistencia().getInvitacionInscripcion() == 1
							&& !notificaciones.get(i).isHasStarted() && !notificaciones.get(i).isHasFinished()) {
						notificacionesUsuario.add(notificaciones.get(i));
					}
					if (notificaciones.get(i).getAsistencia().getEmailUsuarioAsistente().equals(email)
							&& notificaciones.get(i).getAsistencia().getConfirmado()
							&& !notificaciones.get(i).isHasFinished()) {
						notificacionesUsuario.add(notificaciones.get(i));

					}
					if ((notificaciones.get(i).getAsistencia().getEmailUsuarioAsistente().equals(email)
							|| notificaciones.get(i).getConvite().getEmailAnfitrion().equals(email))
							&& (notificaciones.get(i).isHasStarted())) {
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
					if ((notificaciones.get(i).getAsistencia().getEmailUsuarioAsistente().equals(email)
							|| notificaciones.get(i).getConvite().getEmailAnfitrion().equals(email))
							&& (notificaciones.get(i).isHasStarted()) && (notificaciones.get(i).isHasFinished())) {
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
				if (n.getAsistencia() != null && !n.getAsistencia().getConfirmado() && !n.isChecked()) {
					NotificacionDAOImplementation.getInstance().updateNotificacion(n);
					checked++;
				}
			}
			req.getSession().setAttribute("usuario", null);
			req.getSession().setAttribute("usuario", usuario);
			req.getSession().setAttribute("email", null);
			req.getSession().setAttribute("email", email);

			req.getSession().setAttribute("numero_notificaciones", null);
			req.getSession().setAttribute("numero_notificaciones", checked);
			resp.sendRedirect(req.getContextPath() + "/Perfil.jsp");
		} else {
			resp.sendRedirect(req.getContextPath() + "/Login.jsp");
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
