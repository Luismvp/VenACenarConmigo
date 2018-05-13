package es.upm.dit.isst.VenACenarConmigo.servlets;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import es.upm.dit.isst.VenACenarConmigo.dao.AsistenciaConviteDAOImplementation;
import es.upm.dit.isst.VenACenarConmigo.dao.ConviteDAOImplementation;
import es.upm.dit.isst.VenACenarConmigo.dao.model.AsistenciaConvite;
import es.upm.dit.isst.VenACenarConmigo.dao.model.Convite;

@WebServlet("/NotificacionesServlet")

public class NotificacionesServlet extends HttpServlet {
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String email = (String) req.getSession().getAttribute("email");
		List<AsistenciaConvite> asistenciaConvite = AsistenciaConviteDAOImplementation.getInstance()
				.readAllAsistenciaConvite();
		List<AsistenciaConvite> asistenciaConvite2 = new ArrayList<>();
		List<AsistenciaConvite> asistenciaConvite3 = new ArrayList<>();
		List<AsistenciaConvite> asistenciaConvite4 = new ArrayList<>();
		for (int i = 0; i < asistenciaConvite.size(); i++) {
			if (asistenciaConvite.get(i).getInvitacionInscripcion() == 1
					&& asistenciaConvite.get(i).getConfirmado() == false
					&& asistenciaConvite.get(i).getEmailUsuarioAsistente().equals(email)) {
				asistenciaConvite2.add(asistenciaConvite.get(i));
			}
		}
		if (null != req.getSession().getAttribute("lista_notificaciones")) {
			req.getSession().setAttribute("lista_notificaciones", asistenciaConvite2);
		} else {
			req.getSession().removeAttribute("lista_notificaciones");
			req.getSession().setAttribute("lista_notificaciones", asistenciaConvite2);
		}
		List<Convite> convites = new ArrayList<>();
		List<Convite> convitesConfirmados = new ArrayList<>();
		List<Convite> inscripcionesAceptadas = new ArrayList<>();
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
		for (int i = 0; i < asistenciaConvite.size(); i++) {
			if (asistenciaConvite.get(i).getInvitacionInscripcion() == 2
					&& asistenciaConvite.get(i).getConfirmado() == true
					&& asistenciaConvite.get(i).getEmailUsuarioAsistente().equals(email)) {
				asistenciaConvite4.add(asistenciaConvite.get(i));
			}
		}
		if (!asistenciaConvite3.isEmpty()) {
			for (int i = 0; i < asistenciaConvite3.size(); i++) {
				convitesConfirmados.add((Convite) ConviteDAOImplementation.getInstance()
						.readConvite(asistenciaConvite3.get(i).getIdConvite()));
			}
		}
		if (!asistenciaConvite4.isEmpty()) {
			for (int i = 0; i < asistenciaConvite4.size(); i++) {
				inscripcionesAceptadas.add((Convite) ConviteDAOImplementation.getInstance()
						.readConvite(asistenciaConvite4.get(i).getIdConvite()));
				
			}
		}
		if (!convites.isEmpty()) {
			req.getSession().setAttribute("Lista_convites", convites);
		}
		if (!convitesConfirmados.isEmpty()) {
			req.getSession().setAttribute("convitesConfirmados", convitesConfirmados);
		}
		if (!inscripcionesAceptadas.isEmpty()) {
			req.getSession().setAttribute("inscripcionesAceptadas", inscripcionesAceptadas);
		}
		resp.sendRedirect(req.getContextPath() + "/Notificaciones.jsp");
	}
}
