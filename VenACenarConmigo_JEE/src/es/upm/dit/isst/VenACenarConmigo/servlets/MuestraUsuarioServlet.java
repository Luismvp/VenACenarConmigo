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
import es.upm.dit.isst.VenACenarConmigo.dao.ValoracionDAOImplementation;
import es.upm.dit.isst.VenACenarConmigo.dao.model.AccionUsuario;
import es.upm.dit.isst.VenACenarConmigo.dao.model.Convite;
import es.upm.dit.isst.VenACenarConmigo.dao.model.Publicaciones;
import es.upm.dit.isst.VenACenarConmigo.dao.model.Usuario;
import es.upm.dit.isst.VenACenarConmigo.dao.model.Valoracion;

@WebServlet("/MuestraUsuarioServlet")

public class MuestraUsuarioServlet extends HttpServlet {

	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String emailUsuario = (String) req.getParameter("email");
		Usuario usuarioVisitado = UsuarioDAOImplementation.getInstance().readUsuario(emailUsuario);

		String emailUsuario2 = (String) req.getSession().getAttribute("email");
		//1=si me sigue 2=si nos seguimos
		int privacidad = usuarioVisitado.getPrivacidad3();
		// 1=ninguna, 2=sigoAlAnfitrion 3=nosSeguimos (4=meSigue)
		int relacion = 1;
		List<AccionUsuario> seguimientos = AccionUsuarioDAOImplementation.getInstance().readAllAccionUsuario();
		for (AccionUsuario a : seguimientos) {
			if (a.getUsuarioEmisor().equals(emailUsuario) && a.getUsuarioReceptor().equals(emailUsuario2)
					&& a.getSeguimientoBloqueoDenuncia() == 1) {
				relacion=4;
			}
		}
		for (AccionUsuario a : seguimientos) {
			if (a.getUsuarioEmisor().equals(emailUsuario2) && a.getUsuarioReceptor().equals(emailUsuario)
					&& a.getSeguimientoBloqueoDenuncia() == 1) {
				if (relacion == 4) {
					relacion=3;
				} else {
					relacion=2;
				}
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
		double valoracion_media = 0.00;
		int num_valoraciones = 0;
		List<Valoracion> valoraciones = ValoracionDAOImplementation.getInstance().readAllValoracion();
		for (Valoracion valoracion:valoraciones) {
			if (valoracion.getUsuarioValorado().equals(emailUsuario)) {
				valoracion_media+= (valoracion.getPuntuacion()*2);
				num_valoraciones++;
			}
		}
		valoracion_media = valoracion_media / num_valoraciones;
		if(num_valoraciones > 0) {
			req.getSession().setAttribute("valoracion_media_buscada", valoracion_media);
		}else {
			req.getSession().setAttribute("valoracion_media_buscada", "No ha sido valorado todavia");
		}
		if(emailUsuario.equals(emailUsuario2)) {
			resp.sendRedirect(req.getContextPath() + "/Perfil.jsp");
		} else {
			req.getSession().setAttribute("relacion", relacion);
			req.getSession().setAttribute("privacidad", privacidad);
			req.getSession().setAttribute("convite_list", convitesUsuario);
			req.getSession().setAttribute("usuario_visitado", usuarioVisitado);
			req.getSession().setAttribute("publicaciones", publicacionesUsuario);
			resp.sendRedirect(req.getContextPath() + "/VistaPerfil.jsp");
		}
	}
}
