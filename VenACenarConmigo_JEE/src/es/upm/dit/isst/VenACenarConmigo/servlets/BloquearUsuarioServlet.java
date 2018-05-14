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
		AccionUsuario seguimiento = null;
		List<AccionUsuario> seguimientos = AccionUsuarioDAOImplementation.getInstance().readAllAccionUsuario();
		for (AccionUsuario a : seguimientos) {
			if (a.getUsuarioEmisor().equals(usuarioEmisor) && a.getSeguimientoBloqueoDenuncia() == 1) {
				seguimiento = a;
				seguimiento.setSeguimientoBloqueoDenuncia(2);
				AccionUsuarioDAOImplementation.getInstance().updateAccionUsuario(seguimiento);
			}
		}
		if (null==seguimiento) {
			seguimiento = new AccionUsuario();
			if (AccionUsuarioDAOImplementation.getInstance().readAllAccionUsuario().isEmpty()) {
				seguimiento.setIdAccion(1);
			} else {
				seguimiento.setIdAccion(AccionUsuarioDAOImplementation.getInstance().readAllAccionUsuario().size() + 1);
			}
			seguimiento.setUsuarioEmisor(usuarioEmisor);
			seguimiento.setUsuarioReceptor(usuarioReceptor);
			seguimiento.setSeguimientoBloqueoDenuncia(seguimientoBloqueoDenuncia);
		}
		resp.sendRedirect(req.getContextPath() + "/VistaPerfil.jsp");
	}
}