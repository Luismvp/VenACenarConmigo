package es.upm.dit.isst.VenACenarConmigo.servlets;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import es.upm.dit.isst.VenACenarConmigo.dao.UsuarioDAOImplementation;
import es.upm.dit.isst.VenACenarConmigo.dao.model.Usuario;

@WebServlet("/NuevoConviteServlet")
public class NuevoConviteServlet extends HttpServlet{
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String nombre = req.getParameter("nombre");
		String fecha = req.getParameter("fecha");
		String horaComienzo = req.getParameter("horaComienzo");
		String horaFin = req.getParameter("horaFin");
		if (req.getParameter("restaurante") != null) {
			String restaurante = req.getParameter("restaurante");
		} else {
			String restaurante = "";
		}
		String menu = req.getParameter("menu");
		String temas = req.getParameter("temas");
		String numInvitados = req.getParameter("numInvitados");
		String precioInvitado = req.getParameter("precioInvitado");
		String descripcion = req.getParameter("descripcion");
		String ciudad = req.getParameter("ciudad");
		String distrito = req.getParameter("distrito");
		
		String emailAnfitrion = req.getParameter("emailAnfitrion");
		Usuario anfitrion = UsuarioDAOImplementation.getInstance().readUsuario(emailAnfitrion);
	    /*
		Convite convite = new Convite();
	    convite.setNombre(nombre);
	    convite.setFecha(fecha);
	    convite.setHoraInicio(horaInicio);
	    convite.setHoraFin(horaFin);
	    convite.setRestaurante(restaurante);
	    convite.setMenu(menu);
	    convite.setTemas(temas);
	    convite.setNumInvitados(numInvitados);
	    convite.setPrecioInvitados(precioInvitado);
	    convite.setDescripcion(descripcion);
	    convite.setCiudad(ciudad);
	    convite.setDistrito(distrito);
	    convite.setAnfitrion(anfitrion);
	    
	    ConviteDAOImplementation.getInstance().createConvite(convite);
	    
	    resp.sendRedirect(req.getContextPath() + "/Perfil.jsp"); */
	}
	

}
