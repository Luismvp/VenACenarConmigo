package es.upm.dit.isst.VenACenarConmigo.servlets;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import es.upm.dit.isst.VenACenarConmigo.dao.ConviteDAOImplementation;
import es.upm.dit.isst.VenACenarConmigo.dao.model.Convite;

@WebServlet("/BuscarConviteServlet")
public class BuscarConviteServlet extends HttpServlet{
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String email = (String) req.getSession().getAttribute("email");
		List<Convite> convites = ConviteDAOImplementation.getInstance().readAllConvite();
		int selectedOrder = 0;
		int selectedFilter = 0;
		req.getSession().setAttribute("selectedOrder", selectedOrder);
		req.getSession().setAttribute("selectedFilter", selectedFilter);
		req.getSession().setAttribute("convite_list", convites);
		resp.sendRedirect(req.getContextPath() + "/BuscarConvite.jsp");
	}
}
