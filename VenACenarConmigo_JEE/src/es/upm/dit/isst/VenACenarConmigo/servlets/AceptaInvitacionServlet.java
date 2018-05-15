package es.upm.dit.isst.VenACenarConmigo.servlets;

import java.io.IOException;
import java.util.List;
import javax.servlet.RequestDispatcher;
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

@WebServlet("/AceptaInvitacionServlet")

public class AceptaInvitacionServlet extends HttpServlet {

	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		int idConvite = Integer.parseInt(req.getParameter("idConvite"));
		String email = (String) req.getParameter("email");
		log("hay idConvite y email");
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
			req.getSession().setAttribute("numero_notificaciones", null);
			req.getSession().setAttribute("numero_notificaciones", asistenciaConvite2.size());
		} else {
			req.getSession().removeAttribute("Lista_convites");
			req.getSession().setAttribute("Lista_convites", asistenciaConvite2);
			req.getSession().setAttribute("numero_notificaciones", null);
			req.getSession().setAttribute("numero_notificaciones", asistenciaConvite2.size());
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
		}else {
			req.getSession().setAttribute("Lista_convites", null);
		}
		if (!convitesConfirmados.isEmpty()) {
			req.getSession().setAttribute("convitesConfirmados", convitesConfirmados);
		}
		boolean enNotificaciones = Boolean.getBoolean(req.getParameter("enNotificaciones"));
		if (enNotificaciones) {
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
}
