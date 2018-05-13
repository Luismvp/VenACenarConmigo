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


@WebServlet("/RecuperarContrasenaServlet")

public class RecuperarContrasenaServlet extends HttpServlet {
	
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String email = req.getParameter("email");
		Usuario usuario = UsuarioDAOImplementation.getInstance().readUsuario(email);
		EmailHandler emailHandler = EmailHandler.getInstance();
		emailHandler.sendEmail("Ven A Cenar Conmigo", email, "Recuperar contraseña", "Aquí tienes tu contraseña, ten cuidado y no vuelvas a perderla! " + usuario.getPassword());
		resp.sendRedirect(req.getContextPath() + "/Validacion.jsp");
		
	}
}
