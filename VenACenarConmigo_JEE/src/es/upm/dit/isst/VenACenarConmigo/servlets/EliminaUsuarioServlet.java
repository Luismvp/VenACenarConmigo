package es.upm.dit.isst.VenACenarConmigo.servlets;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import es.upm.dit.isst.VenACenarConmigo.dao.UsuarioDAOImplementation;
import es.upm.dit.isst.VenACenarConmigo.dao.model.Usuario;

@WebServlet("/EliminaUsuarioServlet")
public class EliminaUsuarioServlet extends HttpServlet {

	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String email = req.getParameter("email");
		Usuario usuario = UsuarioDAOImplementation.getInstance().readUsuario(email);
		Usuario usuarioVacio = UsuarioDAOImplementation.getInstance().readUsuario("");
		UsuarioDAOImplementation.getInstance().deleteUsuario(usuario);
		UsuarioDAOImplementation.getInstance().deleteUsuario(usuarioVacio);
		resp.sendRedirect(req.getContextPath()+"/borrarUsuarios.jsp");
	}
}
