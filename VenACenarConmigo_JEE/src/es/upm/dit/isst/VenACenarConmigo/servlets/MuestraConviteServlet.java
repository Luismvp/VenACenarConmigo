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
import es.upm.dit.isst.VenACenarConmigo.dao.model.AsistenciaConvite;
import es.upm.dit.isst.VenACenarConmigo.dao.model.Convite;

@WebServlet("/MuestraConviteServlet")

public class MuestraConviteServlet extends HttpServlet {

	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		int idConvite = Integer.parseInt(req.getParameter("idConvite"));
		log("" + idConvite);
		Convite convite = ConviteDAOImplementation.getInstance().readConvite(idConvite);
		List<AsistenciaConvite> asistentes = AsistenciaConviteDAOImplementation.getInstance()
				.readAllAsistenciaConvite();
		List<AsistenciaConvite> asistentes2 = new ArrayList();
		int conviteFin = 0;
		int ultimoInvitado=0;
		for (int i = 0; i < asistentes.size(); i++) {
			if (asistentes.get(i).getIdConvite() == idConvite) {
				asistentes2.add(asistentes.get(i));
				ultimoInvitado=asistentes.get(i).getNumeroInvitado();
			}
		}
		int numRestante = convite.getMaxInvitados() - asistentes2.size();

		req.getSession().setAttribute("numeroRestante", numRestante);
		req.getSession().setAttribute("convite", convite);
		req.getSession().setAttribute("lista_invitados", asistentes2);
		req.getSession().setAttribute("numero_invitados", asistentes2.size());
		req.getSession().setAttribute("ultimoInvitado", ultimoInvitado);
		Calendar ahora = Calendar.getInstance();
		log("" + ahora);
		if (null != convite.getFechaYHoraFin() && convite.getFechaYHoraFin().compareTo(ahora) < 0) {
			log("estoy aqui");
			conviteFin = 1;
			req.getSession().setAttribute("conviteFin", conviteFin);
		} else {
			conviteFin = 0;
			req.getSession().setAttribute("conviteFin", conviteFin);
			log("no ha funcionado");
		}
		
		String email = (String)req.getSession().getAttribute("email");
		boolean esAnfitrion = false;
		boolean esAsistenteConfirmado = false;
		boolean esInvitadoPendiente = false;
		boolean esInscritoPendiente = false;
		if (convite.getEmailAnfitrion().equals(email)) {
			esAnfitrion = true;
		} else {
			for (int i = 0; i < asistentes2.size(); i++) {
				if (asistentes2.get(i).getEmailUsuarioAsistente().equals(email)) {
					if (asistentes2.get(i).getConfirmado()) {
						esAsistenteConfirmado = true;
						break;
					} else {
						if (asistentes2.get(i).getInvitacionInscripcion() == 1) {
							esInvitadoPendiente = true;
							break;
						} else {
							esInscritoPendiente = true;
							break;
						}
					}
				}
			}
		}
		
		req.getSession().setAttribute("esAnfitrion", esAnfitrion);
		req.getSession().setAttribute("esAsistenteConfirmado", esAsistenteConfirmado);
		req.getSession().setAttribute("esInvitadoPendiente", esInvitadoPendiente);
		req.getSession().setAttribute("esInscritoPendiente", esInscritoPendiente);
		resp.sendRedirect(req.getContextPath() + "/Convite.jsp");
	}
}
