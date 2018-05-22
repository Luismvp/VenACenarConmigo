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
		String emailUsuario = (String) req.getSession().getAttribute("email");
		Usuario usuario = UsuarioDAOImplementation.getInstance().readUsuario(emailUsuario);
		int privacidad1 = usuario.getPrivacidad1();
		int privacidad2 = usuario.getPrivacidad2();
		int privacidad3 = usuario.getPrivacidad3();
		
		String p_perfil = req.getParameter("perfil");
		String p_convites = req.getParameter("convites");
		String p_publicaciones = req.getParameter("publicaciones");
		
		if (null != p_perfil && p_perfil.equals("cualquiera1")) {
			privacidad1 = 1;
		} else if(null != p_perfil && p_perfil.equals("mesigue1")) {
			privacidad1 = 2;
		} else if(null != p_perfil && p_perfil.equals("sigo1")) {
			privacidad1 = 3;
		}
		
		if (null != p_convites && p_convites.equals("cualquiera2")) {
			privacidad2 = 1;
		} else if(null != p_convites && p_convites.equals("mesigue2")) {
			privacidad2 = 2;
		} else if(null != p_convites && p_convites.equals("sigo2")) {
			privacidad2 = 3;
		}
		
		if (null != p_publicaciones && p_publicaciones.equals("mesigue3")) {
			privacidad3 = 1;
		} else if(null != p_publicaciones && p_publicaciones.equals("sigo3")) {
			privacidad3 = 2;
		} 
		
		usuario.setPrivacidad1(privacidad1);
		usuario.setPrivacidad2(privacidad2);
		usuario.setPrivacidad3(privacidad3);
		UsuarioDAOImplementation.getInstance().updateUsuario(usuario);
		resp.sendRedirect(req.getContextPath() + "/Perfil.jsp");
	}
}
