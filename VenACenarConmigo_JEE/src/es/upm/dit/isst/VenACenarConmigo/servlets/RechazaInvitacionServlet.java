package es.upm.dit.isst.VenACenarConmigo.servlets;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import es.upm.dit.isst.VenACenarConmigo.dao.AsistenciaConviteDAOImplementation;
import es.upm.dit.isst.VenACenarConmigo.dao.model.AsistenciaConvite;
import es.upm.dit.isst.VenACenarConmigo.dao.ConviteDAOImplementation;
import es.upm.dit.isst.VenACenarConmigo.dao.model.AsistenciaConvite;
import es.upm.dit.isst.VenACenarConmigo.dao.model.Convite;

@WebServlet("/RechazaInvitacionServlet")

public class RechazaInvitacionServlet extends HttpServlet {

	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		int idConvite = Integer.parseInt(req.getParameter("idConvite"));
		String email = (String) req.getSession().getAttribute("email");
		List<AsistenciaConvite> asistente = AsistenciaConviteDAOImplementation.getInstance().readAllAsistenciaConvite();
		AsistenciaConvite asistenteRechaza = null;
		if (null == req.getParameter("idAsistente")) {
			for (int i = 0; i < asistente.size(); i++) {
				if (asistente.get(i).getIdConvite() == idConvite
						&& asistente.get(i).getEmailUsuarioAsistente().equals(email)) {
					asistenteRechaza = asistente.get(i);
				}
			}
			AsistenciaConviteDAOImplementation.getInstance().deleteAsistenciaConvite(asistenteRechaza);
			asistente = null;
			asistente = AsistenciaConviteDAOImplementation.getInstance().readAllAsistenciaConvite();
			for (int i = 1; i < asistente.size(); i++) {
				log("" + asistente.get(i).getIdAsistente());
				if (asistente.get(i).getIdConvite() == idConvite
						&& asistente.get(i).getIdAsistente() - asistente.get(i - 1).getIdAsistente() == 2) {
					log("" + asistente.get(i).getIdAsistente());
					if (asistente.get(i).getIdConvite() == idConvite
							&& asistente.get(i).getIdAsistente() - asistente.get(i - 1).getIdAsistente() == 2) {
						log("" + asistente.get(i).getIdAsistente());
						AsistenciaConviteDAOImplementation.getInstance().deleteAsistenciaConvite(asistente.get(i));
						asistente.get(i).setIdAsistente(asistente.get(i).getIdAsistente() - 1);
						asistente.get(i).setNumeroInvitado(asistente.get(i).getNumeroInvitado() - 1);
						AsistenciaConviteDAOImplementation.getInstance().createAsistenciaConvite(asistente.get(i));
						asistente.clear();
						asistente = AsistenciaConviteDAOImplementation.getInstance().readAllAsistenciaConvite();
					}
				}
			}
			for (int i = 1; i < asistente.size(); i++) {
				log("" + asistente.get(i).getIdAsistente());
				if (asistente.get(i).getIdAsistente() - asistente.get(i - 1).getIdAsistente() == 2) {
					log("" + asistente.get(i).getIdAsistente());
					AsistenciaConviteDAOImplementation.getInstance().deleteAsistenciaConvite(asistente.get(i));
					asistente.get(i).setIdAsistente(asistente.get(i).getIdAsistente() - 1);
					AsistenciaConviteDAOImplementation.getInstance().createAsistenciaConvite(asistente.get(i));
					asistente.clear();
					asistente = AsistenciaConviteDAOImplementation.getInstance().readAllAsistenciaConvite();
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
			if (null != req.getSession().getAttribute("lista_notificaciones")) {
				req.getSession().setAttribute("lista_notificaciones", asistenciaConvite2);
				req.getSession().setAttribute("numero_notificaciones", null);
				req.getSession().setAttribute("numero_notificaciones", asistenciaConvite2.size());
			} else {
				req.getSession().removeAttribute("lista_notificaciones");
				req.getSession().setAttribute("lista_notificaciones", asistenciaConvite2);
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
			} else {
				req.getSession().setAttribute("Lista_convites", null);
			}
			if (!convitesConfirmados.isEmpty()) {
				req.getSession().setAttribute("convitesConfirmados", convitesConfirmados);
			}

		} else {
			int idAsistente = Integer.parseInt((String) req.getParameter("idAsistente"));
			AsistenciaConvite retirarAsistente = AsistenciaConviteDAOImplementation.getInstance()
					.readAsistenciaConvite(idAsistente);
			AsistenciaConviteDAOImplementation.getInstance().deleteAsistenciaConvite(retirarAsistente);
			asistente = null;
			asistente = AsistenciaConviteDAOImplementation.getInstance().readAllAsistenciaConvite();
			for (int i = 0; i < asistente.size(); i++) {
				log("" + asistente.get(i).getIdAsistente());
				
				if (i != 0) {
					if (asistente.get(i).getIdConvite() == idConvite
							&& asistente.get(i).getIdAsistente() - asistente.get(i - 1).getIdAsistente() == 2) {
						log("" + asistente.get(i).getIdAsistente());
						AsistenciaConviteDAOImplementation.getInstance().deleteAsistenciaConvite(asistente.get(i));
						asistente.get(i).setIdAsistente(asistente.get(i).getIdAsistente() - 1);
						asistente.get(i).setNumeroInvitado(asistente.get(i).getNumeroInvitado() - 1);
						AsistenciaConviteDAOImplementation.getInstance().createAsistenciaConvite(asistente.get(i));
						asistente.clear();
						asistente = AsistenciaConviteDAOImplementation.getInstance().readAllAsistenciaConvite();
					}
				}
			}
			for (int i = 1; i < asistente.size(); i++) {
				log("" + asistente.get(i).getIdAsistente());
				if (asistente.get(i).getIdAsistente() - asistente.get(i - 1).getIdAsistente() == 2) {
					log("" + asistente.get(i).getIdAsistente());
					AsistenciaConviteDAOImplementation.getInstance().deleteAsistenciaConvite(asistente.get(i));
					asistente.get(i).setIdAsistente(asistente.get(i).getIdAsistente() - 1);
					AsistenciaConviteDAOImplementation.getInstance().createAsistenciaConvite(asistente.get(i));
					asistente.clear();
					asistente = AsistenciaConviteDAOImplementation.getInstance().readAllAsistenciaConvite();
				}
			}
			int ultimoAsistente = 0;
			List<AsistenciaConvite> asistente2 = new ArrayList<>();
			for (int i = 0; i < asistente.size(); i++) {
				if (idConvite == asistente.get(i).getIdConvite()) {
					asistente2.add(asistente.get(i));
					ultimoAsistente = asistente.get(i).getNumeroInvitado();
				}
			}
			req.getSession().setAttribute("ultimoInvitado", ultimoAsistente);
			req.getSession().setAttribute("lista_invitados", null);
			req.getSession().setAttribute("lista_invitados", asistente2);
		}
		boolean enNotificaciones = Boolean.parseBoolean(req.getParameter("enNotificaciones"));
		List<AsistenciaConvite> asistenciaConvite = AsistenciaConviteDAOImplementation.getInstance()
				.readAllAsistenciaConvite();
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
			
			req.getSession().setAttribute("lista_invitados", asistentes2);
			req.getSession().setAttribute("esInvitadoPendiente", esInvitadoPendiente);
			req.getSession().setAttribute("esInscritoPendiente", false);
			req.getSession().setAttribute("esAsistenteConfirmado", false);
			req.getSession().setAttribute("conviteFin", 0);
			resp.sendRedirect(req.getContextPath() + "/Convite.jsp");
		}
	}
}
