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
import es.upm.dit.isst.VenACenarConmigo.dao.ConviteDAOImplementation;
import es.upm.dit.isst.VenACenarConmigo.dao.model.AsistenciaConvite;
import es.upm.dit.isst.VenACenarConmigo.dao.model.Convite;

@WebServlet("/AnadeInvitadoServlet")

public class AnadeInvitadoServlet extends HttpServlet {
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		int idConvite=Integer.parseInt(req.getParameter("idConvite"));
		String emailInv= req.getParameter("inv1");
		List<AsistenciaConvite> asistente = AsistenciaConviteDAOImplementation.getInstance().readAllAsistenciaConvite();
		List<AsistenciaConvite> asistente2 = new ArrayList<>();
		for(AsistenciaConvite i:asistente) {
			if(i.getIdConvite()==idConvite) {
				asistente2.add(i);
			}
		}
		Convite convite = ConviteDAOImplementation.getInstance().readConvite(idConvite);
		AsistenciaConvite invitado = new AsistenciaConvite();
		invitado.setInvitacionInscripcion(1);
		invitado.setIdConvite(idConvite);
		invitado.setEmailAnfitrion(convite.getEmailAnfitrion());
		invitado.setConfirmado(false);
		invitado.setEmailUsuarioAsistente(emailInv);
		invitado.setIdAsistente(asistente.size()+1);
		invitado.setNumeroInvitado(asistente2.size()+1);
		
		AsistenciaConviteDAOImplementation.getInstance().createAsistenciaConvite(invitado);
		List<AsistenciaConvite> asistente_now = AsistenciaConviteDAOImplementation.getInstance().readAllAsistenciaConvite();
		List<AsistenciaConvite> asistente3 = new ArrayList<>();
		for(AsistenciaConvite i:asistente_now) {
			if(i.getIdConvite()==idConvite) {
				asistente3.add(i);
			}
		}
		req.getSession().setAttribute("ultimoInvitado", asistente3.size());
		req.getSession().setAttribute("lista_invitados", asistente3);
		resp.sendRedirect(req.getContextPath()+"/Convite.jsp");
	}
}
