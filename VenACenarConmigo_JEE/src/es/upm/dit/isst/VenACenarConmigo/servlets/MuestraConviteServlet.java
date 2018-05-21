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

import es.upm.dit.isst.VenACenarConmigo.dao.AsistenciaConviteDAOImplementation;
import es.upm.dit.isst.VenACenarConmigo.dao.ComentarioConviteDAOImplementation;
import es.upm.dit.isst.VenACenarConmigo.dao.ConviteDAOImplementation;
import es.upm.dit.isst.VenACenarConmigo.dao.UsuarioDAOImplementation;
import es.upm.dit.isst.VenACenarConmigo.dao.ValoracionDAOImplementation;
import es.upm.dit.isst.VenACenarConmigo.dao.model.AsistenciaConvite;
import es.upm.dit.isst.VenACenarConmigo.dao.model.ComentarioConvite;
import es.upm.dit.isst.VenACenarConmigo.dao.model.Convite;
import es.upm.dit.isst.VenACenarConmigo.dao.model.Usuario;
import es.upm.dit.isst.VenACenarConmigo.dao.model.Valoracion;

@WebServlet("/MuestraConviteServlet")

public class MuestraConviteServlet extends HttpServlet {

	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		int idConvite = Integer.parseInt(req.getParameter("idConvite"));
		String email = (String) req.getSession().getAttribute("email");
		Usuario usuario = UsuarioDAOImplementation.getInstance().readUsuario(email);
		
		log("" + idConvite);
		Convite convite = ConviteDAOImplementation.getInstance().readConvite(idConvite);
		List<AsistenciaConvite> asistentes = AsistenciaConviteDAOImplementation.getInstance()
				.readAllAsistenciaConvite();
		List<AsistenciaConvite> asistentes2 = new ArrayList<>();
		int conviteFin = 0;
		int ultimoInvitado=0;
		for (int i = 0; i < asistentes.size(); i++) {
			if (asistentes.get(i).getIdConvite() == idConvite) {
				asistentes2.add(asistentes.get(i));
				ultimoInvitado=asistentes.get(i).getNumeroInvitado();
			}
		}
		

		List<ComentarioConvite> comentarios = ComentarioConviteDAOImplementation.getInstance()
				.readAllComentarioConvite();
		List<ComentarioConvite> comentariosConvite = new ArrayList<>();
		for (int i = comentarios.size() - 1; i >= 0; i--) {
			// GUardar los comentarios que sean de cada convite en comentariosConvite
			int idConviteComentarioi = comentarios.get(i).getConvite().getIdConvite();
			if (idConviteComentarioi != -1 && idConviteComentarioi == convite.getIdConvite()) {
				comentariosConvite.add(comentarios.get(i));
			}
		}
		if(comentariosConvite.size() ==0) {
			ComentarioConvite comentarioInicial = new ComentarioConvite();
			comentarioInicial.setComentario("¡Nadie ha comentado todavía, se el primero en decir algo!");
			comentarioInicial.setiDComentario(1);
			comentarioInicial.setNombre("Admin");
			comentarioInicial.setiDComentario(0);
			Convite conviteInicial = new Convite();
			conviteInicial.setIdConvite(-1);
			comentariosConvite.add(comentarioInicial);
		}
		
		int numRestante = convite.getMaxInvitados() - asistentes2.size();
		req.getSession().setAttribute("lista_comentarios_convite", comentariosConvite);
		req.getSession().setAttribute("numeroRestante", numRestante);
		req.getSession().setAttribute("convite", convite);
		req.getSession().setAttribute("lista_invitados", asistentes2);
		req.getSession().setAttribute("numero_invitados", asistentes2.size());
		req.getSession().setAttribute("ultimoInvitado", ultimoInvitado);
		Calendar ahora = Calendar.getInstance();
		log("" + ahora);
		
		boolean esAnfitrion = false;
		boolean esAsistenteConfirmado = false;
		boolean esInvitadoPendiente = false;
		boolean esInscritoPendiente = false;
		if (convite.getEmailAnfitrion().equals(email)) {
			esAnfitrion = true;
		} else {
			for (int i = 0; i < asistentes2.size(); i++) {
				if (asistentes2.get(i).getEmailUsuarioAsistente().equals(email)) {
					log("" + asistentes2.get(i).getConfirmado());
					if (asistentes2.get(i).getConfirmado()) {
				
						esAsistenteConfirmado = true;
						break;
					} else {
						if (asistentes2.get(i).getInvitacionInscripcion() == 1) {
							esInvitadoPendiente = true;
							break;
						} else {
							esInscritoPendiente = true;
							break;
						}
					}
				}
			}
		}
		boolean haValorado = false;
		List<Valoracion> valoraciones = ValoracionDAOImplementation.getInstance().readAllValoracion();
		for(Valoracion v:valoraciones) {
			if(v.getUsuarioValorador().equals(email) && v.getConvite() == idConvite) {
				haValorado = true;
			}
		}
		Usuario anfitrion = UsuarioDAOImplementation.getInstance().readUsuario(convite.getEmailAnfitrion());
		String nombre_anfitrion = anfitrion.getNombre();
		nombre_anfitrion += " "+anfitrion.getApellidos();
		
		req.getSession().setAttribute("nombre_anfitrion", nombre_anfitrion);
		req.getSession().setAttribute("haValorado", haValorado);
		req.getSession().setAttribute("esAnfitrion", esAnfitrion);
		req.getSession().setAttribute("esAsistenteConfirmado", esAsistenteConfirmado);
		req.getSession().setAttribute("esInvitadoPendiente", esInvitadoPendiente);
		req.getSession().setAttribute("esInscritoPendiente", esInscritoPendiente);
		if (null != convite.getFechaYHoraFin() && convite.getFechaYHoraFin().compareTo(ahora) < 0) {
			log("estoy aqui");
			conviteFin = 1;
			req.getSession().setAttribute("conviteFin", conviteFin);
		} else {
			conviteFin = 0;
			req.getSession().setAttribute("conviteFin", conviteFin);
			log("no ha funcionado");
		}
		resp.sendRedirect(req.getContextPath() + "/Convite.jsp");
	}
}
