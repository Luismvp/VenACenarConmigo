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

@WebServlet("/BloquearUsuarioServlet")
public class BloquearUsuarioServlet extends HttpServlet {
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String usuarioEmisor = (String) req.getSession().getAttribute("email");
		String usuarioReceptor = (String) req.getParameter("email");
		int seguimientoBloqueoDenuncia = 2;
		AccionUsuario accion = null;
		List<AccionUsuario> bloqueos = AccionUsuarioDAOImplementation.getInstance().readAllAccionUsuario();
		for (AccionUsuario a : bloqueos) {
			if (a.getUsuarioEmisor().equals(usuarioEmisor) 
					&& (a.getSeguimientoBloqueoDenuncia() == 1 || a.getSeguimientoBloqueoDenuncia() == 0)) {
				accion = a;
				accion.setSeguimientoBloqueoDenuncia(2);
				AccionUsuarioDAOImplementation.getInstance().updateAccionUsuario(accion);
			}
		}
		if (null==accion) {
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
		int relacion = 5;
		req.getSession().setAttribute("relacion", relacion);
		resp.sendRedirect(req.getContextPath() + "/VistaPerfil.jsp");
	}
}