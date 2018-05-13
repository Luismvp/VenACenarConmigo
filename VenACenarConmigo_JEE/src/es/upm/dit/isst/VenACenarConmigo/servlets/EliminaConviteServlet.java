package es.upm.dit.isst.VenACenarConmigo.servlets;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import es.upm.dit.isst.VenACenarConmigo.dao.ConviteDAOImplementation;
import es.upm.dit.isst.VenACenarConmigo.dao.model.Convite;

@WebServlet("/EliminaConviteServlet")
public class EliminaConviteServlet extends HttpServlet {


	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String id = req.getParameter("id");
		int numId = Integer.parseInt(id);
		Convite convite = ConviteDAOImplementation.getInstance().readConvite(numId);
		ConviteDAOImplementation.getInstance().deleteConvite(convite);
		resp.sendRedirect(req.getContextPath()+"/Login.jsp");
	}
}