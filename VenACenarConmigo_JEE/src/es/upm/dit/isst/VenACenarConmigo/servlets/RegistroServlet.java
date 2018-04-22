package es.upm.dit.isst.VenACenarConmigo.servlets;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import es.upm.dit.isst.VenACenarConmigo.dao.UsuarioDAOImplementation;

import es.upm.dit.isst.VenACenarConmigo.dao.model.Usuario;

@WebServlet("/RegistroServlet")
public class RegistroServlet extends HttpServlet {

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String nombre = req.getParameter("nombre");
		String apellidos = req.getParameter("apellidos");
		String nacimiento = req.getParameter("nacimiento");
		String telefono = req.getParameter("telefono");
		String ciudad = req.getParameter("ciudad");
		String codigoPostal = req.getParameter("codigoPostal");
		String email = req.getParameter("email");
		String password = req.getParameter("password");
		String repPassword = req.getParameter("repPassword");
		String profesion = req.getParameter("profesion");
		String descripcion = req.getParameter("descripcion");
		Integer privacidad = 1;
		
		if (nombre.isEmpty() || apellidos.isEmpty() || nacimiento.isEmpty() || telefono.isEmpty() 
			|| ciudad.isEmpty() || codigoPostal.isEmpty() || email.isEmpty() || password.isEmpty()
			|| repPassword.isEmpty()) {
			
			resp.sendRedirect(req.getContextPath() + "/Registro.jsp");
		} else if (repPassword.equals(password)) {
			Usuario usuario = new Usuario();
			usuario.setNombre(nombre);
			usuario.setApellidos(apellidos);
			usuario.setNacimiento(nacimiento);
			usuario.setTelefono(telefono);
			usuario.setCiudad(ciudad);
			usuario.setCodigoPostal(codigoPostal);
			usuario.setEmail(email);
			usuario.setPassword(password);
			usuario.setProfesion(profesion);
			usuario.setDescripcion(descripcion);
			usuario.setValidado(false);
			usuario.setPrivacidad1(privacidad);
			usuario.setPrivacidad2(privacidad);
			usuario.setPrivacidad3(privacidad);
			UsuarioDAOImplementation.getInstance().createUsuario(usuario);
			resp.sendRedirect(req.getContextPath() + "/ValidarCuenta.jsp");
		} else {
			resp.sendRedirect(req.getContextPath() + "/Registro.jsp");
		}
		
	}
}
