package es.upm.dit.isst.VenACenarConmigo.servlets;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import es.upm.dit.isst.VenACenarConmigo.dao.ConviteDAOImplementation;
import es.upm.dit.isst.VenACenarConmigo.dao.model.Convite;

@WebServlet("/NuevoConviteServlet")
public class NuevoConviteServlet extends HttpServlet{
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String nombre = req.getParameter("nombre");
		String fecha = req.getParameter("fecha");
		String horaComienzo = req.getParameter("horaComienzo");
		String horaFin = req.getParameter("horaFin");
		String restaurante = "No aplica";
		if (req.getParameter("restaurante") != null) {
			restaurante = req.getParameter("restaurante");
		} 	
		String menu = req.getParameter("menu");
		String temas = req.getParameter("temas");
		String numInvitados = (String) req.getParameter("numInvitados");
		String precioInvitado = (String)req.getParameter("precioInvitado");
		String ciudad = req.getParameter("ciudad");
		String area = req.getParameter("area");	
		String descripcion = req.getParameter("descripcion");	
		String emailAnfitrion = (String) req.getSession().getAttribute("email");
	    int numInvitados2=1;
		for(int i=1;i<=15;i++){
	    	String j = i+"";
	    	if(numInvitados.equals(j)) {
	    		numInvitados2= Integer.parseInt(j);
	    	}
	    }
		int IdConvite = ConviteDAOImplementation.getInstance().readAllConvite().size()+1;
	    Convite convite = new Convite();
	    convite.setIdConvite(IdConvite);
	    convite.setNombre(nombre);
	    convite.setFecha(fecha);
	    convite.setHoraComienzo(horaComienzo);
	    convite.setHoraFin(horaFin);
	    convite.setRestaurante(restaurante);
	    convite.setMenu(menu);
	    convite.setTemasConversacion(temas);
	    convite.setMaxInvitados(numInvitados2);
	    convite.setPrecioInvitado(precioInvitado);
	    convite.setCiudad(ciudad);
	    convite.setArea(area);
	    convite.setDescripcion(descripcion);
	    convite.setEmailAnfitrion(emailAnfitrion);
	    
	    ConviteDAOImplementation.getInstance().createConvite(convite);
	    req.getSession().setAttribute("convite", convite);
	    req.getSession().setAttribute("idConvite", IdConvite);
	    req.getSession().setAttribute("emailAnfitrion", emailAnfitrion);
	    resp.sendRedirect(req.getContextPath() + "/Invitados.jsp");
	}
	

}
