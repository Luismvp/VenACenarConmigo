package es.upm.dit.isst.VenACenarConmigo.servlets;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import es.upm.dit.isst.VenACenarConmigo.dao.AsistenciaConviteDAOImplementation;
import es.upm.dit.isst.VenACenarConmigo.dao.ConviteDAOImplementation;
import es.upm.dit.isst.VenACenarConmigo.dao.UsuarioDAOImplementation;
import es.upm.dit.isst.VenACenarConmigo.dao.model.AsistenciaConvite;
import es.upm.dit.isst.VenACenarConmigo.dao.model.Convite;
import es.upm.dit.isst.VenACenarConmigo.dao.model.Usuario;

@WebServlet("/InicioServlet")
public class InicioServlet extends HttpServlet {
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String email = (String) req.getSession().getAttribute("email");
		Usuario usuario = UsuarioDAOImplementation.getInstance().readUsuario(email);
		// Primero buscamos los convites que tienen al usuario como anfitrión
		List<Convite> convites = ConviteDAOImplementation.getInstance().readAllConvite();
		List<Convite> convitesUsuario = new ArrayList<>();
		for (Convite a : convites) {
			if (a.getEmailAnfitrion().equals(usuario.getEmail())) {
				convitesUsuario.add(a);
			}
		}
		// Después buscamos los convites a los que el usuario ha sido invitado
		// y que este ha confirmado, asi como los que se ha inscrito y su inscripción
		// ha sido aceptada.
		List<AsistenciaConvite> asistencias = AsistenciaConviteDAOImplementation.getInstance()
				.readAllAsistenciaConvite();
		for(AsistenciaConvite a : asistencias) {
			if(a.getConfirmado()==true && a.getEmailUsuarioAsistente().equals(email)) {
				convitesUsuario.add(ConviteDAOImplementation.getInstance().readConvite(a.getIdConvite()));
			}
		}
		// Una vez tenemos esto, incluimos en la sesión al usuario, la lista de convites
		// La lista de publicaciones y la lista de seguimientos recomendados.
		req.getSession().setAttribute("convitesFuturos", convitesUsuario);
		req.getSession().setAttribute("usuario", usuario);
	}
}
