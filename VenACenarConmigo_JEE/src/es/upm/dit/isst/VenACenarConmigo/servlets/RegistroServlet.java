package es.upm.dit.isst.VenACenarConmigo.servlets;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import es.upm.dit.isst.VenACenarConmigo.dao.UsuarioDAOImplementation;
import es.upm.dit.isst.VenACenarConmigo.dao.model.Aficion;
import es.upm.dit.isst.VenACenarConmigo.dao.model.Usuario;

@WebServlet("RegistroServlet")
public class RegistroServlet extends HttpServlet{
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String email = req.getParameter("email");
		String nombre = req.getParameter("nombre");
		String password = req.getParameter("password");
		String apellidos = req.getParameter("apellidos");
		String nacimiento = req.getParameter("nacimiento");
		String telefono =  req.getParameter("telefono");
		String codigoPostal = req.getParameter("codigoPostal");
		
		String profesion = req.getParameter("profesion");
		String descripcion = req.getParameter("descripcion");
		List<Aficion> aficiones = new ArrayList<>();
		
		Usuario usuario = new Usuario();
		usuario.setPassword(password);
		usuario.setProfesionEstudios(profesion);
		usuario.setTelefono(telefono);
		usuario.setDescripcionPersonal(descripcion);
		usuario.setEmail(email);
		usuario.setNacimiento(nacimiento);
		usuario.setNombre(nombre);
		usuario.setCodigoPostal(codigoPostal);
		usuario.setAficiones(aficiones);
		usuario.setApellidos(apellidos);
		usuario.setValidado(false);
		UsuarioDAOImplementation.getInstance().createUsuario(usuario);
		
		resp.sendRedirect(req.getContextPath() + "/Validacion.jsp");
	}
}
