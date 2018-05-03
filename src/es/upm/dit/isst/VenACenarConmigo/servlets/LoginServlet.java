package es.upm.dit.isst.VenACenarConmigo.servlets;

import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.InputStream;
import javax.imageio.ImageIO;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import es.upm.dit.isst.VenACenarConmigo.dao.AccionUsuarioDAOImplementation;
import es.upm.dit.isst.VenACenarConmigo.dao.AsistenciaConviteDAOImplementation;
import es.upm.dit.isst.VenACenarConmigo.dao.ComentarioConviteDAOImplementation;
import es.upm.dit.isst.VenACenarConmigo.dao.ConviteDAOImplementation;
import es.upm.dit.isst.VenACenarConmigo.dao.PublicacionesDAOImplementation;
import es.upm.dit.isst.VenACenarConmigo.dao.UsuarioDAOImplementation;
import es.upm.dit.isst.VenACenarConmigo.dao.ValoracionDAOImplementation;
import es.upm.dit.isst.VenACenarConmigo.dao.model.Publicaciones;
import es.upm.dit.isst.VenACenarConmigo.dao.model.AsistenciaConvite;
import es.upm.dit.isst.VenACenarConmigo.dao.model.Usuario;
import es.upm.dit.isst.VenACenarConmigo.dao.model.Valoracion;

@WebServlet("/LoginServlet")

public class LoginServlet extends HttpServlet {
	private final String ADMIN_EMAIL = "root";
	private final String ADMIN_PASSWORD = "root";

	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String email = req.getParameter("email");
		log(email);
		String password = req.getParameter("password");
		Usuario usuario = UsuarioDAOImplementation.getInstance().loginUsuario(email, password);
		List<Publicaciones> publicaciones = PublicacionesDAOImplementation.getInstance().readAllPublicaciones();
        List<Publicaciones> publicacionesUsuario = new ArrayList<>();
        if (null != publicaciones && !publicaciones.isEmpty()) {
        	for(int i = publicaciones.size()-1; i>= 0; i--) {
            	if (publicaciones.get(i).getUsuario().getEmail().equals(email)) {
            		publicacionesUsuario.add(publicaciones.get(i));
            	} 	
            }
        }
		BufferedImage fotoPerfil = null;
		List<AsistenciaConvite> asistenciaConvite = new ArrayList<>();
		if (null == req.getAttribute("asistenciaConvite")) {
			asistenciaConvite = AsistenciaConviteDAOImplementation.getInstance().readAllAsistenciaConvite();
			req.setAttribute("asistenciaConvite", asistenciaConvite);
		} else {
			asistenciaConvite = (List<AsistenciaConvite>) req.getAttribute("asistenciaConvite");
		}
		List<AsistenciaConvite> asistenciaConvite2 = new ArrayList<>();
		for (int i = 0; i < asistenciaConvite.size(); i++) {
			if (asistenciaConvite.get(i).getInvitacionInscripcion() == 1
					&& asistenciaConvite.get(i).getConfirmado() == false
					&& asistenciaConvite.get(i).getEmailUsuarioAsistente().equals(email)) {
				asistenciaConvite2.add(asistenciaConvite.get(i));
			}
		}
		int numeroNotificaciones = asistenciaConvite2.size();
		log(Integer.toString(numeroNotificaciones));
		
		double valoracion_media = 0.00;
		int num_valoraciones = 0;
		List<Valoracion> valoraciones = ValoracionDAOImplementation.getInstance().readAllValoracion();
		for (Valoracion valoracion:valoraciones) {
			if (valoracion.getUsuarioValorado().equals(email)) {
				valoracion_media+= (valoracion.getPuntuacion()*2);
				num_valoraciones++;
			}
		}
		valoracion_media = valoracion_media / num_valoraciones;
		valoracion_media = round(valoracion_media, 2);
		
		if (ADMIN_EMAIL.equals(email) && ADMIN_PASSWORD.equals(password)
				&& req.getSession().getAttribute("usuario") == null) {
			req.getSession().setAttribute("adminLogged", true);
			req.getSession().setAttribute("usuario_list", UsuarioDAOImplementation.getInstance().readAllUsuarios());
			req.getSession().setAttribute("convite_list", ConviteDAOImplementation.getInstance().readAllConvite());
			req.getSession().setAttribute("lista_publicaciones_usuario", publicacionesUsuario);
			req.getSession().setAttribute("accion_list", AccionUsuarioDAOImplementation.getInstance().readAllAccionUsuario());
			req.getSession().setAttribute("comentario_list", ComentarioConviteDAOImplementation.getInstance().readAllComentarioConvite());
			req.getSession().setAttribute("asistente_list",
					AsistenciaConviteDAOImplementation.getInstance().readAllAsistenciaConvite());
			req.getSession().setAttribute("valoracion_list", valoraciones);
			resp.sendRedirect(req.getContextPath() + "/ListaUsuarios.jsp");
		} else if (null != usuario && req.getSession().getAttribute("usuario") == null) {
			log("estoy aqui");
			log(email);
			log(Integer.toString(numeroNotificaciones));
			if(num_valoraciones > 0) {
				req.getSession().setAttribute("valoracion_media", valoracion_media);
			}
			req.getSession().setAttribute("usuario", usuario);
			req.getSession().setAttribute("email", email);
			req.getSession().setAttribute("lista_publicaciones_usuario", publicacionesUsuario);
			req.getSession().setAttribute("numero_notificaciones", numeroNotificaciones);
			req.getSession().setAttribute("fotoPerfil", fotoPerfil);
			resp.sendRedirect(req.getContextPath() + "/Perfil.jsp");
		} else if (req.getSession().getAttribute("usuario") != null && usuario != null) {
			log("ahora estoy aqui");
			log(Integer.toString(numeroNotificaciones));
			if(num_valoraciones > 0) {
				req.getSession().setAttribute("valoracion_media", valoracion_media);
			}
			req.getSession().setAttribute("usuario", null);
			req.getSession().setAttribute("usuario", usuario);
			req.getSession().setAttribute("email", null);
			req.getSession().setAttribute("email", email);

			req.getSession().setAttribute("numero_notificaciones", null);
			req.getSession().setAttribute("numero_notificaciones", numeroNotificaciones);
			resp.sendRedirect(req.getContextPath() + "/Perfil.jsp");
		} else {
			resp.sendRedirect(req.getContextPath() + "/Login.jsp");
		}
	}
	private double round(double num, int decimals) {
		int factor = (int) Math.pow(10, decimals);
		double d_aux = num*factor;
		long i_aux = Math.round(d_aux);
		double rounded_num;
		rounded_num = (double)(i_aux) / (double) factor;
		return rounded_num;
	}
}
