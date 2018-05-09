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

import es.upm.dit.isst.VenACenarConmigo.dao.ConviteDAOImplementation;
import es.upm.dit.isst.VenACenarConmigo.dao.model.Convite;

@WebServlet("/GestionaConvitesServlet")

public class GestionaConvitesServlet extends HttpServlet {
	
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
	 String email = (String)req.getSession().getAttribute("email");
	 List<Convite> convites = ConviteDAOImplementation.getInstance().readAllConvite();
	 List<Convite> convites2 = new ArrayList();
	 for(int i=0;i<convites.size();i++) {
		 if(convites.get(i).getEmailAnfitrion().equals(email)) {
			 convites2.add(convites.get(i));
		 }
	 }
	 req.getSession().setAttribute("convites_anfitrion", convites2);
	 resp.sendRedirect(req.getContextPath() + "/GestionaConvites.jsp");
	}
}
