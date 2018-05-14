package es.upm.dit.isst.VenACenarConmigo.servlets;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import es.upm.dit.isst.VenACenarConmigo.dao.UsuarioDAOImplementation;
import es.upm.dit.isst.VenACenarConmigo.dao.model.Usuario;

@WebServlet("/BuscarUsuarioServlet")
public class BuscarUsuarioServlet extends HttpServlet {
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		req.setCharacterEncoding("UTF-8");
		String usuario = (String) req.getParameter("usuario");
		String[] nombreApellidos = usuario.split("/");
		log(nombreApellidos[0]);
		if(nombreApellidos.length>1) {
			log(nombreApellidos[1]);
		}
		List<Usuario> usuarios = UsuarioDAOImplementation.getInstance().readAllUsuarios();
		List<Usuario> resBusqueda = new ArrayList<>();
		if (nombreApellidos.length == 1) {
			for (Usuario u : usuarios) {
				if (u.getEmail().equals(nombreApellidos[0])) {
					resBusqueda.add(u);
				}
			}
		} else {
			for (Usuario u : usuarios) {
				if (u.getNombre().equals(nombreApellidos[0]) && u.getApellidos().equals(nombreApellidos[1])) {
					resBusqueda.add(u);
				}
			}
		}

		req.getSession().setAttribute("usuario_list", resBusqueda);
		resp.sendRedirect(req.getContextPath() + "/BuscarUsuario.jsp");
	}
}