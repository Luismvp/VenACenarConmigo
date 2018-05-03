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

@WebServlet("/DenunciarUsuarioServlet")
public class DenunciarUsuarioServlet extends HttpServlet {
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String usuarioEmisor = (String) req.getSession().getAttribute("email");
		String usuarioReceptor = (String) req.getParameter("email");
		int seguimientoBloqueoDenuncia = 3;
		String comentario = req.getParameter("comentario");
		AccionUsuario accion = new AccionUsuario();
		if (AccionUsuarioDAOImplementation.getInstance().readAllAccionUsuario().isEmpty()) {
			accion.setIdAccion(1);
		} else {
			accion.setIdAccion(AccionUsuarioDAOImplementation.getInstance().readAllAccionUsuario().size() + 1);
		}
		accion.setUsuarioEmisor(usuarioEmisor);
		accion.setUsuarioReceptor(usuarioReceptor);
		accion.setSeguimientoBloqueoDenuncia(seguimientoBloqueoDenuncia);
		accion.setComentario(comentario);
		AccionUsuarioDAOImplementation.getInstance().createAccionUsuario(accion);
		
		resp.sendRedirect(req.getContextPath() + "/VistaPerfil.jsp");
	}
}