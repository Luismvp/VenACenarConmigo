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

import es.upm.dit.isst.VenACenarConmigo.dao.AccionUsuarioDAOImplementation;
import es.upm.dit.isst.VenACenarConmigo.dao.AsistenciaConviteDAOImplementation;
import es.upm.dit.isst.VenACenarConmigo.dao.ComentarioConviteDAOImplementation;
import es.upm.dit.isst.VenACenarConmigo.dao.ConviteDAOImplementation;
import es.upm.dit.isst.VenACenarConmigo.dao.UsuarioDAOImplementation;
import es.upm.dit.isst.VenACenarConmigo.dao.ValoracionDAOImplementation;
import es.upm.dit.isst.VenACenarConmigo.dao.model.AccionUsuario;
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
		
		List<AccionUsuario> accionesUsuarios = AccionUsuarioDAOImplementation.getInstance().readAllAccionUsuario();
		// 0=soyYo 1=ninguna, 2=sigoAlAnfitrion 3=nosSeguimos (4=meSigue) 5=meBloqueó
		List<Integer> relaciones = new ArrayList<>();
		for (int i = 0; i < asistentes2.size(); i++) {
			relaciones.add(1);
		}
		List<Integer> privacidades = new ArrayList<>();
		for (int i = 0; i < asistentes2.size(); i++) {
			privacidades.add(1);
		}
		// 0=Ninguno 1=Ver perfil, 2=Seguir, 3=Dejar de seguir
		List<Integer> botones = new ArrayList<>();
		for (int i = 0; i < asistentes2.size(); i++) {
			botones.add(1);
		}
		for (int i = 0; i < asistentes2.size(); i++) {
			String emailAjeno = asistentes2.get(i).getEmailUsuarioAsistente();
			if(email.equals(emailAjeno)) {
				privacidades.set(i, 1);
				relaciones.set(i, 0);
				continue;
			} else {
				Usuario usuarioAjeno = UsuarioDAOImplementation.getInstance().readUsuario(emailAjeno);
				privacidades.set(i, usuarioAjeno.getPrivacidad1());
			}
			
			// Compruebo la relacion del usuario con el usuario que busca (1, 2 o 3)
			for (int j = 0; j < accionesUsuarios.size(); j++) {
			    if (accionesUsuarios.get(j).getUsuarioEmisor().equals(emailAjeno) &&
					accionesUsuarios.get(j).getUsuarioReceptor().equals(email) &&
					accionesUsuarios.get(j).getSeguimientoBloqueoDenuncia() == 2) {
			    	relaciones.set(i, 5);
					break;
			    } else if (accionesUsuarios.get(j).getUsuarioEmisor().equals(email) &&
					accionesUsuarios.get(j).getUsuarioReceptor().equals(emailAjeno) &&
					accionesUsuarios.get(j).getSeguimientoBloqueoDenuncia() == 1) {
					// Si también me sigue
					if (relaciones.get(i) == 4) {
						relaciones.set(i, 3);
						break;
					} else {
						relaciones.set(i, 2);
					}
				} else if (accionesUsuarios.get(j).getUsuarioEmisor().equals(emailAjeno) &&
						accionesUsuarios.get(j).getUsuarioReceptor().equals(email) &&
						accionesUsuarios.get(j).getSeguimientoBloqueoDenuncia() == 1) {
					if (relaciones.get(i) == 2) {
						relaciones.set(i, 3);
						break;
					} else {
						relaciones.set(i, 4);
					}
				} 
			}
		}
		
		for (int i = 0; i < asistentes2.size(); i++) {
		    if (relaciones.get(i) == 0 || relaciones.get(i) == 5) {
		    	botones.set(i, 0);
		    } else if (privacidades.get(i) == 3 && relaciones.get(i) == 2) {
		    	botones.set(i, 3);
		    } else if (privacidades.get(i) == 3 && (relaciones.get(i) < 2 || relaciones.get(i) == 4)){
		    	botones.set(i, 2);
			} else if (privacidades.get(i) == 2 && (relaciones.get(i) < 2 || relaciones.get(i) == 4)) {
				botones.set(i, 2);
			}
		}
		
		List<Integer> indexList = new ArrayList<>();
		for (int i = 0; i < asistentes2.size(); i++) {
			indexList.add(i);
		}
		
		req.getSession().setAttribute("privacidades", privacidades);
		req.getSession().setAttribute("relaciones", relaciones);
		req.getSession().setAttribute("botones", botones);
		req.getSession().setAttribute("indexList", indexList);
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
