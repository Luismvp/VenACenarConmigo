package es.upm.dit.isst.VenACenarConmigo.servlets;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import es.upm.dit.isst.VenACenarConmigo.dao.AccionUsuarioDAOImplementation;
import es.upm.dit.isst.VenACenarConmigo.dao.AficionDAOImplementation;
import es.upm.dit.isst.VenACenarConmigo.dao.AsistenciaConviteDAOImplementation;
import es.upm.dit.isst.VenACenarConmigo.dao.ComentarioConviteDAOImplementation;
import es.upm.dit.isst.VenACenarConmigo.dao.ConviteDAOImplementation;
import es.upm.dit.isst.VenACenarConmigo.dao.PublicacionesDAOImplementation;
import es.upm.dit.isst.VenACenarConmigo.dao.UsuarioDAOImplementation;
import es.upm.dit.isst.VenACenarConmigo.dao.ValoracionDAOImplementation;
import es.upm.dit.isst.VenACenarConmigo.dao.model.AccionUsuario;
import es.upm.dit.isst.VenACenarConmigo.dao.model.Aficion;
import es.upm.dit.isst.VenACenarConmigo.dao.model.AsistenciaConvite;
import es.upm.dit.isst.VenACenarConmigo.dao.model.ComentarioConvite;
import es.upm.dit.isst.VenACenarConmigo.dao.model.Convite;
import es.upm.dit.isst.VenACenarConmigo.dao.model.Publicaciones;
import es.upm.dit.isst.VenACenarConmigo.dao.model.Usuario;
import es.upm.dit.isst.VenACenarConmigo.dao.model.Valoracion;

@WebServlet("/EliminaTodaBBDDServlet")
public class EliminaTodaBBDDServlet extends HttpServlet {


	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		List<AsistenciaConvite> asistencias = AsistenciaConviteDAOImplementation.getInstance().readAllAsistenciaConvite();
		for (AsistenciaConvite asistencia: asistencias) {
			AsistenciaConviteDAOImplementation.getInstance().deleteAsistenciaConvite(asistencia);
		}
		List<Valoracion> valoraciones = ValoracionDAOImplementation.getInstance().readAllValoracion();
		for (Valoracion valoracion: valoraciones) {
			ValoracionDAOImplementation.getInstance().deleteValoracion(valoracion);
		}
		List<ComentarioConvite> comentarios = ComentarioConviteDAOImplementation.getInstance().readAllComentarioConvite();
		for (ComentarioConvite comentario: comentarios) {
			ComentarioConviteDAOImplementation.getInstance().deleteComentarioConvite(comentario);
		}
		List<Convite> convites = ConviteDAOImplementation.getInstance().readAllConvite();
		for (Convite convite: convites) {
			ConviteDAOImplementation.getInstance().deleteConvite(convite);
		}
		List<AccionUsuario> acciones = AccionUsuarioDAOImplementation.getInstance().readAllAccionUsuario();
		for (AccionUsuario accion: acciones) {
			AccionUsuarioDAOImplementation.getInstance().deleteAccionUsuario(accion);
		}
		List<Aficion> aficiones = AficionDAOImplementation.getInstance().readAllAficion();
		for (Aficion aficion: aficiones) {
			AficionDAOImplementation.getInstance().deleteAficion(aficion);
		}
		List<Publicaciones> publicaciones = PublicacionesDAOImplementation.getInstance().readAllPublicaciones();
		for (Publicaciones publicacion: publicaciones) {
			PublicacionesDAOImplementation.getInstance().deletePublicaciones(publicacion);
		}
		List<Usuario> usuarios = UsuarioDAOImplementation.getInstance().readAllUsuarios();
		for (Usuario usuario: usuarios) {
			UsuarioDAOImplementation.getInstance().deleteUsuario(usuario);
		}
		resp.sendRedirect(req.getContextPath()+"/Login.jsp");
	}
}
