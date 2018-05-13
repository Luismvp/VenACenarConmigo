package es.upm.dit.isst.VenACenarConmigo.servlets;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import es.upm.dit.isst.VenACenarConmigo.dao.AsistenciaConviteDAOImplementation;
import es.upm.dit.isst.VenACenarConmigo.dao.ConviteDAOImplementation;
import es.upm.dit.isst.VenACenarConmigo.dao.model.AsistenciaConvite;

@WebServlet("/EliminaInvitadoServlet")
public class EliminaInvitadoServlet extends HttpServlet {


	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		int id = Integer.parseInt(req.getParameter("id"));
		AsistenciaConvite asistente = AsistenciaConviteDAOImplementation.getInstance().readAsistenciaConvite(id);
		AsistenciaConviteDAOImplementation.getInstance().deleteAsistenciaConvite(asistente);
		resp.sendRedirect(req.getContextPath()+"/Login.jsp");
	}
}
