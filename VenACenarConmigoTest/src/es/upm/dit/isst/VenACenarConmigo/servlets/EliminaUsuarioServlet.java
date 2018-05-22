package es.upm.dit.isst.VenACenarConmigo.servlets;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import es.upm.dit.isst.VenACenarConmigo.dao.AsistenciaConviteDAOImplementation;
import es.upm.dit.isst.VenACenarConmigo.dao.ComentarioConviteDAOImplementation;
import es.upm.dit.isst.VenACenarConmigo.dao.ConviteDAOImplementation;
import es.upm.dit.isst.VenACenarConmigo.dao.UsuarioDAOImplementation;
import es.upm.dit.isst.VenACenarConmigo.dao.model.AsistenciaConvite;
import es.upm.dit.isst.VenACenarConmigo.dao.model.ComentarioConvite;
import es.upm.dit.isst.VenACenarConmigo.dao.model.Convite;
import es.upm.dit.isst.VenACenarConmigo.dao.model.Usuario;

@WebServlet("/EliminaUsuarioServlet")
public class EliminaUsuarioServlet extends HttpServlet {


	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String email = req.getParameter("email");
		Usuario usuario = UsuarioDAOImplementation.getInstance().readUsuario(email);
		Usuario usuarioVacion = UsuarioDAOImplementation.getInstance().readUsuario("");
		List<ComentarioConvite> coments = ComentarioConviteDAOImplementation.getInstance().readAllComentarioConvite();
		for (ComentarioConvite c:coments) {
			if(c.getNombre().equals(email)) {
				ComentarioConviteDAOImplementation.getInstance().deleteComentarioConvite(c);
			}
		}
		List<Convite> convites = ConviteDAOImplementation.getInstance().readAllConvite();
		for (Convite c:convites) {
			if(c.getEmailAnfitrion().equals(email)) {
				ConviteDAOImplementation.getInstance().deleteConvite(c);
			}
		}
		List<AsistenciaConvite> asist = AsistenciaConviteDAOImplementation.getInstance().readAllAsistenciaConvite();
		for (AsistenciaConvite a:asist) {
			if(a.getEmailAnfitrion().equals(email) || a.getEmailUsuarioAsistente().equals(email)) {
				AsistenciaConviteDAOImplementation.getInstance().deleteAsistenciaConvite(a);
			}
		}
		UsuarioDAOImplementation.getInstance().deleteUsuario(usuario);
		UsuarioDAOImplementation.getInstance().deleteUsuario(usuarioVacion);
		resp.sendRedirect(req.getContextPath()+"/Login.jsp");
	}
}
