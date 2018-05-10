package es.upm.dit.isst.VenACenarConmigo.servlets;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import es.upm.dit.isst.VenACenarConmigo.dao.AsistenciaConviteDAOImplementation;
import es.upm.dit.isst.VenACenarConmigo.dao.ConviteDAOImplementation;
import es.upm.dit.isst.VenACenarConmigo.dao.UsuarioDAOImplementation;
import es.upm.dit.isst.VenACenarConmigo.dao.model.AsistenciaConvite;
import es.upm.dit.isst.VenACenarConmigo.dao.model.Usuario;


@WebServlet("/LoginServlet")

public class LoginServlet extends HttpServlet {
	private final String ADMIN_EMAIL = "root";
	private final String ADMIN_PASSWORD = "root";

	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String email = req.getParameter("email");
		log(email);
		String password = req.getParameter("password");
		Usuario usuario = UsuarioDAOImplementation.getInstance().loginUsuario(email, password);
		List<AsistenciaConvite> asistenciaConvite = new ArrayList<>();
		if (null==req.getAttribute("asistenciaConvite")) {
			asistenciaConvite = AsistenciaConviteDAOImplementation.getInstance().readAllAsistenciaConvite();
			req.setAttribute("asistenciaConvite", asistenciaConvite);
		} else {
			asistenciaConvite = (List<AsistenciaConvite>) req.getAttribute("asistenciaConvite");
		}
		List<AsistenciaConvite> asistenciaConvite2 = new ArrayList<>();
		for (int i = 0; i < asistenciaConvite.size(); i++) {
			if (asistenciaConvite.get(i).getInvitacionInscripcion() == 1 && asistenciaConvite.get(i).getConfirmado() == false
					&& asistenciaConvite.get(i).getEmailUsuarioAsistente().equals(email)) {
				asistenciaConvite2.add(asistenciaConvite.get(i));
			}
		}
		int numeroNotificaciones=asistenciaConvite2.size();
		log(Integer.toString(numeroNotificaciones));
		if (ADMIN_EMAIL.equals(email) && ADMIN_PASSWORD.equals(password) && req.getSession().getAttribute("usuario")==null ) {
			req.getSession().setAttribute("adminLogged", true);
			req.getSession().setAttribute("usuario_list", UsuarioDAOImplementation.getInstance().readAllUsuarios());
			req.getSession().setAttribute("convite_list", ConviteDAOImplementation.getInstance().readAllConvite());
			req.getSession().setAttribute("asistente_list", AsistenciaConviteDAOImplementation.getInstance().readAllAsistenciaConvite());
			resp.sendRedirect(req.getContextPath() + "/ListaUsuarios.jsp");
		} else if (null != usuario && req.getSession().getAttribute("usuario")==null) {
			log("estoy aqui");
			log(email);
			log(Integer.toString(numeroNotificaciones));
			req.getSession().setAttribute("usuario", usuario);
			req.getSession().setAttribute("email", email);
			req.getSession().setAttribute("numero_notificaciones",numeroNotificaciones);
			resp.sendRedirect(req.getContextPath() + "/Perfil.jsp");
		} else if(req.getSession().getAttribute("usuario")!= null && usuario != null){
			log("ahora estoy aqui");
			log(Integer.toString(numeroNotificaciones));
			req.getSession().setAttribute("usuario", null);
			req.getSession().setAttribute("usuario", usuario);
			req.getSession().setAttribute("email", null);
			req.getSession().setAttribute("email", email);

			req.getSession().setAttribute("numero_notificaciones", null);
			req.getSession().setAttribute("numero_notificaciones", numeroNotificaciones);
			resp.sendRedirect(req.getContextPath() + "/Perfil.jsp");
		} else{
			resp.sendRedirect(req.getContextPath() + "/Login.jsp");
		}
	}
}