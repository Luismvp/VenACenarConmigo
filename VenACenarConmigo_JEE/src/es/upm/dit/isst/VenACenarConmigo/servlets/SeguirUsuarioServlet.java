package es.upm.dit.isst.VenACenarConmigo.servlets;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import es.upm.dit.isst.VenACenarConmigo.dao.UsuarioDAOImplementation;
import es.upm.dit.isst.VenACenarConmigo.dao.model.Usuario;
import es.upm.dit.isst.VenACenarConmigo.dao.AccionUsuarioDAOImplementation;
import es.upm.dit.isst.VenACenarConmigo.dao.model.AccionUsuario;

@WebServlet("/SeguirUsuarioServlet")
public class SeguirUsuarioServlet extends HttpServlet {
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String usuarioEmisor = (String) req.getSession().getAttribute("email");
		String usuarioReceptor = (String) req.getParameter("email");
		log(usuarioReceptor);
		int seguimientoBloqueoDenuncia = 1;
		AccionUsuario accion = null;
		List<AccionUsuario> seguimientos = AccionUsuarioDAOImplementation.getInstance().readAllAccionUsuario();
		for (AccionUsuario a : seguimientos) {
			if (a.getUsuarioEmisor().equals(usuarioEmisor)
					&& (a.getSeguimientoBloqueoDenuncia() == 2 || a.getSeguimientoBloqueoDenuncia() == 0)) {
				accion = a;
				accion.setSeguimientoBloqueoDenuncia(1);
				AccionUsuarioDAOImplementation.getInstance().updateAccionUsuario(accion);
			}
		}
		if (null == accion) {
			accion = new AccionUsuario();
			if (AccionUsuarioDAOImplementation.getInstance().readAllAccionUsuario().isEmpty()) {
				accion.setIdAccion(1);
			} else {
				accion.setIdAccion(AccionUsuarioDAOImplementation.getInstance().readAllAccionUsuario().size() + 1);
			}
			accion.setUsuarioEmisor(usuarioEmisor);
			accion.setUsuarioReceptor(usuarioReceptor);
			accion.setSeguimientoBloqueoDenuncia(seguimientoBloqueoDenuncia);
			AccionUsuarioDAOImplementation.getInstance().createAccionUsuario(accion);
		}
		
		Boolean enBusqueda = Boolean.parseBoolean(req.getParameter("enBusqueda"));
		Boolean enConvite = Boolean.parseBoolean(req.getParameter("enConvite"));
		if (enBusqueda || enConvite) {
			int index = Integer.parseInt(req.getParameter("index"));
			List<Integer> botones = (List<Integer>) req.getSession().getAttribute("botones");
			List<Integer> privacidades = (List<Integer>) req.getSession().getAttribute("privacidades");
			List<Integer> relaciones = (List<Integer>) req.getSession().getAttribute("relaciones");
			if (relaciones.get(index) == 4) {
				relaciones.set(index, 3);
			} else {
				relaciones.set(index, 2);
			}
			if (privacidades.get(index) == 3 && relaciones.get(index) == 2) {
		    	botones.set(index, 3);
			} else {
				botones.set(index, 1);
			}
			
			req.getSession().setAttribute("relaciones", relaciones);
			req.getSession().setAttribute("botones", botones);
			if (enBusqueda) {
				resp.sendRedirect(req.getContextPath() + "/BuscarUsuario.jsp");
			} else if (enConvite) {
				resp.sendRedirect(req.getContextPath() + "/Convite.jsp");
			}
			
		} else {
			int relacion = 2;
			req.getSession().setAttribute("relacion", relacion);
			resp.sendRedirect(req.getContextPath() + "/VistaPerfil.jsp");
		}
	}
}