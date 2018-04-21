package es.upm.dit.isst.VenACenarConmigo.servlets;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import es.upm.dit.isst.VenACenarConmigo.dao.UsuarioDAO;
import es.upm.dit.isst.VenACenarConmigo.dao.UsuarioDAOImplementation;
import es.upm.dit.isst.VenACenarConmigo.dao.model.Usuario;


@WebServlet("/CambiosPerfilServlet")
public class CambiosPerfilServlet extends HttpServlet{
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String emailUsuario = req.getParameter("emailUsuario");
		UsuarioDAO dao = UsuarioDAOImplementation.getInstance();
		
		Usuario usuario = dao.readUsuario(emailUsuario);
		String nombre = req.getParameter("nombre");
		String apellidos = req.getParameter("apellidos");
		String nacimiento = req.getParameter("nacimiento");
		String telefono = req.getParameter("telefono");
		String ciudad = req.getParameter("ciudad");
		String codigoPostal = req.getParameter("codigoPostal");
		String email = req.getParameter("email");
		String profesion = req.getParameter("profesion");
		String descripcion = req.getParameter("descripcion");
		
		usuario.setNombre(nombre);
		usuario.setApellidos(apellidos);
		usuario.setNacimiento(nacimiento);
		usuario.setTelefono(telefono);
		usuario.setCiudad(ciudad);
		usuario.setCodigoPostal(codigoPostal);
		usuario.setEmail(email);
		usuario.setProfesion(profesion);
		usuario.setDescripcionPersonal(descripcion);
		dao.updateUsuario(usuario);
		resp.sendRedirect(req.getContextPath() + "/Perfil.jsp");
	}

}
