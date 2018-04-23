package es.upm.dit.isst.VenACenarConmigo.servlets;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import es.upm.dit.isst.VenACenarConmigo.dao.ConviteDAOImplementation;
import es.upm.dit.isst.VenACenarConmigo.dao.UsuarioDAO;
import es.upm.dit.isst.VenACenarConmigo.dao.UsuarioDAOImplementation;
import es.upm.dit.isst.VenACenarConmigo.dao.model.Convite;
import es.upm.dit.isst.VenACenarConmigo.dao.model.Usuario;


@WebServlet("/NuevoConviteServlet")
public class NuevoConviteServlet extends HttpServlet{
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String nombre = req.getParameter("nombre");
		String fecha = req.getParameter("fecha");
		String horaInicio = req.getParameter("horaInicio");
		String horaFin = req.getParameter("horaFin");
		String restaurante = "No aplica";
		if (null != req.getParameter("rest")) {
			restaurante = req.getParameter("rest");
		}
		String menu = req.getParameter("menu");
		String temas = req.getParameter("temas");
		String numInvitados = Integer.parseInt(req.getParameter("numInvitados"));
		String precioInvitado = Integer.parseInt(req.getParameter("precioInvitado"));
		String descripcion = req.getParameter("descripcion");
		String ciudad = req.getParameter("ciudad");
		String area = req.getParameter("area");
		
		String emailAnfitrion = req.getParameter("email");
		UsuarioDAO dao = UsuarioDAOImplementation.getInstance();
		Usuario anfitrion = dao.readUsuario(emailAnfitrion);

		String emailAnfitrion = (String) req.getSession().getAttribute("email");
	        int numInvitados2=1;
		for(int i=1;i<=15;i++){
	    	  String j = i+"";
	    	  if(numInvitados.equals(j)) {
	    	    numInvitados2 = Integer.parseInt(j);
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
