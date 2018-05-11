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

@WebServlet("/InscribirseAConviteServlet")
public class InscribirseAConviteServlet extends HttpServlet {
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String email = (String) req.getSession().getAttribute("email");
		int idConvite = Integer.parseInt(req.getParameter("idConvite"));
		Convite convite = ConviteDAOImplementation.getInstance().readConvite(idConvite);
		String emailAnfitrion = convite.getEmailAnfitrion();
		
		List<AsistenciaConvite> lista_asistentes = AsistenciaConviteDAOImplementation.getInstance().readAllAsistenciaConvite();
		int idAsistente = lista_asistentes.get(lista_asistentes.size()-1).getIdAsistente() + 1;
		int numInvitado = 1;
		AsistenciaConvite asistente = new AsistenciaConvite();
		AsistenciaConviteDAOImplementation dao = AsistenciaConviteDAOImplementation.getInstance();
		
		for (int i = 0; i < lista_asistentes.size(); i++) {
			if (lista_asistentes.get(i).getIdConvite() == idConvite) {
				numInvitado++;
			}
		}
		asistente.setEmailAnfitrion(emailAnfitrion);
		asistente.setEmailUsuarioAsistente(email);
		asistente.setInvitacionInscripcion(2);
		asistente.setNumeroInvitado(numInvitado);
		asistente.setIdConvite(idConvite);
		asistente.setIdAsistente(idAsistente);
		asistente.setConfirmado(false);
		dao.createAsistenciaConvite(asistente);
		
		List<AsistenciaConvite> asistentes = AsistenciaConviteDAOImplementation.getInstance()
				.readAllAsistenciaConvite();
		List<AsistenciaConvite> asistentes2 = new ArrayList();
		for (int i = 0; i < asistentes.size(); i++) {
			if (asistentes.get(i).getIdConvite() == idConvite) {
				asistentes2.add(asistentes.get(i));
			}
		}
		
		boolean esInscritoPendiente = true;
		
		req.getSession().setAttribute("lista_invitados", asistentes2);
		req.getSession().setAttribute("esInscritoPendiente", esInscritoPendiente);
		resp.sendRedirect(req.getContextPath() + "/Convite.jsp");
	}
}
