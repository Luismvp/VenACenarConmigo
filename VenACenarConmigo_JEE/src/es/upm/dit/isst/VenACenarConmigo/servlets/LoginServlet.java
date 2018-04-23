package es.upm.dit.isst.VenACenarConmigo.servlets;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import es.upm.dit.isst.VenACenarConmigo.dao.AsistenciaConviteDAOImplementation;
import es.upm.dit.isst.VenACenarConmigo.dao.ConviteDAOImplementation;
import es.upm.dit.isst.VenACenarConmigo.dao.UsuarioDAOImplementation;
import es.upm.dit.isst.VenACenarConmigo.dao.model.Usuario;


@WebServlet("/LoginServlet")

public class LoginServlet extends HttpServlet {
	private final String ADMIN_EMAIL = "root";
	private final String ADMIN_PASSWORD = "root";

	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String email = req.getParameter("email");
		String password = req.getParameter("password");
		Usuario usuario = UsuarioDAOImplementation.getInstance().loginUsuario(email, password);
		if (ADMIN_EMAIL.equals(email) && ADMIN_PASSWORD.equals(password) ) {
			req.getSession().setAttribute("adminLogged", true);
			req.getSession().setAttribute("usuario_list", UsuarioDAOImplementation.getInstance().readAllUsuarios());
			req.getSession().setAttribute("convite_list", ConviteDAOImplementation.getInstance().readAllConvite());
			req.getSession().setAttribute("asistente_list", AsistenciaConviteDAOImplementation.getInstance().readAllAsistenciaConvite());
			resp.sendRedirect(req.getContextPath() + "/ListaUsuarios.jsp");
		} else if (null != usuario) {
			req.getSession().setAttribute("usuario", usuario);
			req.getSession().setAttribute("email", email);
			req.getSession().setAttribute("numero_notificaciones", AsistenciaConviteDAOImplementation.getInstance().readNotificacionesAsistenciaConvite(email).size());
			resp.sendRedirect(req.getContextPath() + "/Perfil.jsp");
		} else {
			resp.sendRedirect(req.getContextPath() + "/Login.jsp");
		}
	}
}
