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


@WebServlet("/AjustesPrivacidadServlet")

public class AjustesPrivacidadServlet extends HttpServlet {
	
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		int privacidad1 = 1;
		int privacidad2 = 1;
		int privacidad3 = 1;
		if(null != req.getParameter("cualquiera1")) {
			privacidad1 = 1;
		}else if(null != req.getParameter("mesigue1")) {
			privacidad1 = 2;
		}else if(null != req.getParameter("sigo1")) {
			privacidad1 = 3;
		}else {
			privacidad1 = 1;
		}
		if(null != req.getParameter("cualquiera2")) {
			privacidad2 = 1;
		}else if(null != req.getParameter("mesigue2")) {
			privacidad2 = 2;
		}else if(null != req.getParameter("sigo2")) {
			privacidad2 = 3;
		}else {
			privacidad2 = 1;
		}
		
		if(null != req.getParameter("mesigue3")) {
			privacidad3 = 1;
		}else if(null != req.getParameter("sigo3")) {
			privacidad3 = 2;
		}else {
			privacidad3 = 1;
		}
		String emailUsuario = (String) req.getSession().getAttribute("email");
		UsuarioDAO dao = UsuarioDAOImplementation.getInstance();
		
		Usuario usuario = dao.readUsuario(emailUsuario);
		usuario.setPrivacidad1(privacidad1);
		usuario.setPrivacidad2(privacidad2);
		usuario.setPrivacidad3(privacidad3);
		dao.updateUsuario(usuario);
		resp.sendRedirect(req.getContextPath() + "/Perfil.jsp");
	}
}
