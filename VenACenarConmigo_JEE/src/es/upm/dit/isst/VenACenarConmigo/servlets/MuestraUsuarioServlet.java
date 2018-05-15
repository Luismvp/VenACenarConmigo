package es.upm.dit.isst.VenACenarConmigo.servlets;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import es.upm.dit.isst.VenACenarConmigo.dao.AccionUsuarioDAOImplementation;
import es.upm.dit.isst.VenACenarConmigo.dao.ConviteDAOImplementation;
import es.upm.dit.isst.VenACenarConmigo.dao.PublicacionesDAOImplementation;
import es.upm.dit.isst.VenACenarConmigo.dao.UsuarioDAOImplementation;
import es.upm.dit.isst.VenACenarConmigo.dao.model.AccionUsuario;
import es.upm.dit.isst.VenACenarConmigo.dao.model.Convite;
import es.upm.dit.isst.VenACenarConmigo.dao.model.Publicaciones;
import es.upm.dit.isst.VenACenarConmigo.dao.model.Usuario;

@WebServlet("/MuestraUsuarioServlet")

public class MuestraUsuarioServlet extends HttpServlet {

	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String emailUsuario = (String) req.getParameter("email");
		Usuario usuarioVisitado = UsuarioDAOImplementation.getInstance().readUsuario(emailUsuario);

		String emailUsuario2 = (String) req.getSession().getAttribute("email");
		Usuario usuarioVisitante = UsuarioDAOImplementation.getInstance().readUsuario(emailUsuario2);
		AccionUsuario seguimiento = null;
		List<AccionUsuario> seguimientos = AccionUsuarioDAOImplementation.getInstance().readAllAccionUsuario();
		for (AccionUsuario a : seguimientos) {
			if (a.getUsuarioEmisor().equals(emailUsuario2) && a.getUsuarioReceptor().equals(emailUsuario)
					&& a.getSeguimientoBloqueoDenuncia() == 1) {
				seguimiento=a;
			}
		}

		List<Convite> convites = ConviteDAOImplementation.getInstance().readAllConvite();
		List<Publicaciones> publicaciones = PublicacionesDAOImplementation.getInstance().readAllPublicaciones();
		List<Publicaciones> publicacionesUsuario = new ArrayList<>();
		for (int i = publicaciones.size() - 1; i >= 0; i--) {
			if (publicaciones.get(i).getUsuario().getEmail().equals(emailUsuario)) {
				publicacionesUsuario.add(publicaciones.get(i));
			}
		}
		List<Convite> convitesUsuario = new ArrayList<>();

		for (int i = 0; i < convites.size(); i++) {
			if (convites.get(i).getEmailAnfitrion().equals(emailUsuario)) {
				convitesUsuario.add(convites.get(i));
			}
		}

		if (null != seguimiento) {
			req.getSession().setAttribute("seguimiento", seguimiento);
		}
		
		if(emailUsuario.equals(emailUsuario2)) {
			resp.sendRedirect(req.getContextPath() + "/Perfil.jsp");
		} else {
			req.getSession().setAttribute("convite_list", convitesUsuario);
			req.getSession().setAttribute("usuario_visitado", usuarioVisitado);
			req.getSession().setAttribute("publicaciones", publicacionesUsuario);
			resp.sendRedirect(req.getContextPath() + "/VistaPerfil.jsp");
		}
	}
}
