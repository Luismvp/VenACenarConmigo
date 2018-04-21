package es.upm.dit.isst.VenACenarConmigo.servlets;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import es.upm.dit.isst.VenACenarConmigo.dao.UsuarioDAOImplementation;
import es.upm.dit.isst.VenACenarConmigo.dao.model.Usuario;
import es.upm.dit.isst.VenACenarConmigo.util.EmailHandler;

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
		int privacidad1 = 1;

		if (repPassword.equals(password)) {
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
			usuario.setDescripcionPersonal(descripcion);
			usuario.setValidado(false);
			usuario.setPrivacidad1(privacidad1);
			usuario.setPrivacidad2(privacidad1);
			usuario.setPrivacidad3(privacidad1);
			UsuarioDAOImplementation.getInstance().createUsuario(usuario);
			EmailHandler emailHandler = EmailHandler.getInstance();
			emailHandler.sendEmail("Ven A Cenar Conmigo", email, "Validación", "Aquí tienes el enlace"
					+ "con el que podrás validar tu cuenta en la red social, Bienvenido a la familia!"
					+ "localhost:8080/VenACenarConmigo/ValidarCuenta.jsp");
			resp.sendRedirect(req.getContextPath() + "/Validacion.jsp");
		}else {
			resp.sendRedirect(req.getContextPath() + "/Registro.jsp");
		}
		
	}
}
