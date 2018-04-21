package es.upm.dit.isst.VenACenarConmigo.servlets;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import es.upm.dit.isst.VenACenarConmigo.dao.UsuarioDAOImplementation;
import es.upm.dit.isst.VenACenarConmigo.dao.model.Usuario;


@WebServlet("/ValidacionServlet")

public class ValidacionServlet extends HttpServlet {
	
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String email = req.getParameter("email");
		String password = req.getParameter("password");
		Usuario usuario = UsuarioDAOImplementation.getInstance().readUsuario(email);
		if(usuario.getPassword().equals(password)) {
			usuario.setValidado(true);
			UsuarioDAOImplementation.getInstance().updateUsuario(usuario);
			resp.sendRedirect(req.getContextPath() + "/Login.jsp");
		}else {
			req.getSession().setAttribute("error", "La contraseña introducida no coincide con la del email.");
			resp.sendRedirect(req.getContextPath() + "/ValidarCuenta.jsp");
		}
	}	
}
