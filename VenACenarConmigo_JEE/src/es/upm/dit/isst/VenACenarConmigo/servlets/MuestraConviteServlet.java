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


@WebServlet("/MuestraConviteServlet")

public class MuestraConviteServlet extends HttpServlet {
	
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
	 int idConvite = Integer.parseInt(req.getParameter("idConvite"));
	 Convite convite = ConviteDAOImplementation.getInstance().readConvite(idConvite);
	 List<AsistenciaConvite> asistentes = AsistenciaConviteDAOImplementation.getInstance().readAllAsistenciaConvite();
	 List<AsistenciaConvite> asistentes2 = new ArrayList();
	 for(int i=0;i<asistentes.size();i++) {
		 if(asistentes.get(i).getIdConvite()==idConvite) {
			 asistentes2.add(asistentes.get(i));
		 }
	 }
	 int numRestante = convite.getMaxInvitados() - asistentes2.size();
	 req.getSession().setAttribute("numeroRestante", numRestante);
	 req.getSession().setAttribute("convite", convite);
	 req.getSession().setAttribute("lista_invitados", asistentes2);
	 req.getSession().setAttribute("numero_invitados", asistentes2.size());
	 resp.sendRedirect(req.getContextPath() + "/Convite.jsp");
	}
}
