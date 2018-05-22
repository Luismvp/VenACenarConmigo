package es.upm.dit.isst.VenACenarConmigo.servlets;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import es.upm.dit.isst.VenACenarConmigo.dao.ComentarioConviteDAOImplementation;
import es.upm.dit.isst.VenACenarConmigo.dao.ConviteDAOImplementation;
import es.upm.dit.isst.VenACenarConmigo.dao.NotificacionDAOImplementation;
import es.upm.dit.isst.VenACenarConmigo.dao.UsuarioDAOImplementation;
import es.upm.dit.isst.VenACenarConmigo.dao.model.ComentarioConvite;
import es.upm.dit.isst.VenACenarConmigo.dao.model.Convite;
import es.upm.dit.isst.VenACenarConmigo.dao.model.Notificacion;
import es.upm.dit.isst.VenACenarConmigo.dao.model.Usuario;

@WebServlet("/AnadirComentarioEnConviteServlet")
public class AnadirComentarioEnConviteServlet extends HttpServlet {
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String email = (String) req.getSession().getAttribute("email");
		Usuario usuario = UsuarioDAOImplementation.getInstance().readUsuario(email);
		String textComentario = req.getParameter("comentario");

		int idConvite = Integer.parseInt(req.getParameter("idConvite"));

		Convite convite = ConviteDAOImplementation.getInstance().readConvite(idConvite);
		List<ComentarioConvite> comentarios = ComentarioConviteDAOImplementation.getInstance()
				.readAllComentarioConvite();
		ComentarioConvite comentario = new ComentarioConvite();
		comentario.setComentario(textComentario);
		comentario.setNombre(usuario.getNombre());
		comentario.setConvite(convite);
		Calendar fecha = Calendar.getInstance();
		comentario.setFecha(fecha);
		int indexComentario = 1;
		if (comentarios.size() != 0) {
			indexComentario = comentarios.size() + 1;
		}
		comentario.setiDComentario(indexComentario);
		
		ComentarioConviteDAOImplementation.getInstance().createComentarioConvite(comentario);
		List<ComentarioConvite> comentariosConvite = new ArrayList<>();
		comentariosConvite.add(comentario);
		if (comentarios.size() != 0) {
			for (int i = comentarios.size()-1; i >= 0; i--) {
				// GUardar los comentarios que sean de cada convite en comentariosConvite
				int idConviteComentarioi = comentarios.get(i).getConvite().getIdConvite();
				if (idConviteComentarioi != -1 && idConviteComentarioi == convite.getIdConvite()) {
					comentariosConvite.add(comentarios.get(i));
				}
			}
		}

		req.getSession().setAttribute("lista_comentarios_convite", comentariosConvite);
		resp.sendRedirect(req.getContextPath() + "/Convite.jsp");
	}

}
