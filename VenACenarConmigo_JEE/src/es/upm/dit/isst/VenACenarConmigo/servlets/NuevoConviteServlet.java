package es.upm.dit.isst.VenACenarConmigo.servlets;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import es.upm.dit.isst.VenACenarConmigo.dao.ConviteDAOImplementation;
import es.upm.dit.isst.VenACenarConmigo.dao.UsuarioDAOImplementation;
import es.upm.dit.isst.VenACenarConmigo.dao.model.Convite;
import es.upm.dit.isst.VenACenarConmigo.dao.model.Usuario;

@WebServlet("/NuevoConviteServlet")
public class NuevoConviteServlet extends HttpServlet{
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		Integer id = 1;
		String nombre = req.getParameter("nombre");
		String fecha = req.getParameter("fecha");
		String horaInicio = req.getParameter("horaInicio");
		String horaFin = req.getParameter("horaFin");
		String restaurante = "";
		if (null != req.getParameter("rest")) {
			restaurante = req.getParameter("rest");
		}
		String menu = req.getParameter("menu");
		String temas = req.getParameter("temas");
		Integer maxInvitados = Integer.parseInt(req.getParameter("invitados"));
		Integer precioInvitado = Integer.parseInt(req.getParameter("precio"));
		String descripcion = req.getParameter("descripcion");
		String ciudad = req.getParameter("ciudad");
		String area = req.getParameter("distrito");
		
		String emailAnfitrion = req.getParameter("emailAnfitrion");
		Usuario anfitrion = UsuarioDAOImplementation.getInstance().readUsuario(emailAnfitrion);
	    
		Convite convite = new Convite();
		convite.setIDConvite(id);
	    convite.setNombre(nombre);
	  //  convite.setAnfitrion(anfitrion);
	    convite.setFecha(fecha);
	    convite.setHoraComienzo(horaInicio);
	    convite.setHoraFin(horaFin);
	    convite.setRestaurante(restaurante);
	    convite.setMenu(menu);
	    convite.setTemasConversacion(temas);
	    convite.setMaxInvitados(maxInvitados);
	    convite.setPrecioInvitado(precioInvitado);
	    convite.setDescripcion(descripcion);
	    convite.setCiudad(ciudad);
	    convite.setArea(area);
	    convite.setAnfitrion(anfitrion);
	    
	    ConviteDAOImplementation.getInstance().createConvite(convite);
	    
	    resp.sendRedirect(req.getContextPath() + "/Perfil.jsp"); 
	}
	

}
