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


@WebServlet("/PaginaPrivacidadServlet")
public class PaginaPrivacidadServlet extends HttpServlet{
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String emailUsuario = (String) req.getSession().getAttribute("email");
		Usuario usuario = UsuarioDAOImplementation.getInstance().readUsuario(emailUsuario);
		int privacidad1 = usuario.getPrivacidad1();
		int privacidad2 = usuario.getPrivacidad2();
		int privacidad3 = usuario.getPrivacidad3();
		
		req.getSession().setAttribute("privacidad1", privacidad1);
		req.getSession().setAttribute("privacidad2", privacidad2);
		req.getSession().setAttribute("privacidad3", privacidad3);
		resp.sendRedirect(req.getContextPath() + "/Privacidad.jsp");
	}

}
