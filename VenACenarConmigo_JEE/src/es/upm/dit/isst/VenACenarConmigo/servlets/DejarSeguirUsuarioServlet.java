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

@WebServlet("/DejarSeguirUsuarioServlet")
public class DejarSeguirUsuarioServlet extends HttpServlet {
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String usuarioEmisor = (String) req.getSession().getAttribute("email");
		String usuarioReceptor = (String) req.getParameter("email");
		log(usuarioReceptor);
		AccionUsuario accion = null;
		List<AccionUsuario> seguimientos = AccionUsuarioDAOImplementation.getInstance().readAllAccionUsuario();
		for (AccionUsuario a : seguimientos) {
			if (a.getUsuarioEmisor().equals(usuarioEmisor) && a.getSeguimientoBloqueoDenuncia() == 1) {
				accion = a;
				accion.setSeguimientoBloqueoDenuncia(0);
				AccionUsuarioDAOImplementation.getInstance().updateAccionUsuario(accion);
			}
		}
		int relacion = 1;
		req.getSession().setAttribute("relacion", relacion);
		resp.sendRedirect(req.getContextPath() + "/VistaPerfil.jsp");
	}
}