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

@WebServlet("/NuevaContrasenaServlet")
public class NuevaContrasenaServlet extends HttpServlet {
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String emailUsuario = (String) req.getSession().getAttribute("email");
		UsuarioDAO dao = UsuarioDAOImplementation.getInstance();
		
		Usuario usuario = dao.readUsuario(emailUsuario);
		String password = req.getParameter("password");
		String repPassword = req.getParameter("repPassword");
		if (repPassword.equals(password)) {
			usuario.setPassword(repPassword);
			dao.updateUsuario(usuario);
			resp.sendRedirect(req.getContextPath() + "/Login.jsp");
		} else {
			resp.sendRedirect(req.getContextPath() + "/NuevaContraseña.jsp");
		}
	}

}
