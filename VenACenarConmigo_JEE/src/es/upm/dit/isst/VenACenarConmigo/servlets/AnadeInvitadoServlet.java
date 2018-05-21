package es.upm.dit.isst.VenACenarConmigo.servlets;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import es.upm.dit.isst.VenACenarConmigo.dao.AccionUsuarioDAOImplementation;
import es.upm.dit.isst.VenACenarConmigo.dao.AsistenciaConviteDAOImplementation;
import es.upm.dit.isst.VenACenarConmigo.dao.ConviteDAOImplementation;
import es.upm.dit.isst.VenACenarConmigo.dao.UsuarioDAOImplementation;
import es.upm.dit.isst.VenACenarConmigo.dao.model.AccionUsuario;
import es.upm.dit.isst.VenACenarConmigo.dao.model.AsistenciaConvite;
import es.upm.dit.isst.VenACenarConmigo.dao.model.Convite;
import es.upm.dit.isst.VenACenarConmigo.dao.model.Usuario;

@WebServlet("/AnadeInvitadoServlet")

public class AnadeInvitadoServlet extends HttpServlet {
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		int idConvite=Integer.parseInt(req.getParameter("idConvite"));
		String email = (String) req.getSession().getAttribute("email");
		String emailInv= req.getParameter("inv1");
		List<AsistenciaConvite> asistente = AsistenciaConviteDAOImplementation.getInstance().readAllAsistenciaConvite();
		List<AsistenciaConvite> asistente2 = new ArrayList<>();
		for(AsistenciaConvite i:asistente) {
			if(i.getIdConvite()==idConvite) {
				asistente2.add(i);
			}
		}
		Convite convite = ConviteDAOImplementation.getInstance().readConvite(idConvite);
		AsistenciaConvite invitado = new AsistenciaConvite();
		invitado.setInvitacionInscripcion(1);
		invitado.setIdConvite(idConvite);
		invitado.setEmailAnfitrion(convite.getEmailAnfitrion());
		invitado.setConfirmado(false);
		invitado.setEmailUsuarioAsistente(emailInv);
		invitado.setIdAsistente(asistente.size()+1);
		invitado.setNumeroInvitado(asistente2.size()+1);
		
		AsistenciaConviteDAOImplementation.getInstance().createAsistenciaConvite(invitado);
		asistente = AsistenciaConviteDAOImplementation.getInstance().readAllAsistenciaConvite();
		asistente2.clear();
		for(AsistenciaConvite i:asistente) {
			if(i.getIdConvite()==idConvite) {
				asistente2.add(i);
			}
		}
		List<Integer> indexList = new ArrayList<>();
		for (int i = 0; i < asistente2.size(); i++) {
			indexList.add(i);
		}
		List<Integer> privacidades = (List<Integer>) req.getSession().getAttribute("privacidades");
		List<Integer> relaciones = (List<Integer>) req.getSession().getAttribute("relaciones");
		List<Integer> botones = (List<Integer>) req.getSession().getAttribute("botones");
		privacidades.add(UsuarioDAOImplementation.getInstance().readUsuario(emailInv).getPrivacidad1());
		List<AccionUsuario> accionesUsuarios = AccionUsuarioDAOImplementation.getInstance().readAllAccionUsuario();
		boolean added = false;
		int new_index = asistente2.size()-1;
		for (int j = 0; j < accionesUsuarios.size(); j++) {
			if (accionesUsuarios.get(j).getUsuarioEmisor().equals(emailInv) &&
					accionesUsuarios.get(j).getUsuarioReceptor().equals(email) &&
					accionesUsuarios.get(j).getSeguimientoBloqueoDenuncia() == 2) {
				if (added) {
					relaciones.set(new_index, 5);
				} else {
					relaciones.add(5);
					added = true;
				}
				break;
			} else if (accionesUsuarios.get(j).getUsuarioEmisor().equals(email) &&
					accionesUsuarios.get(j).getUsuarioReceptor().equals(emailInv) &&
					accionesUsuarios.get(j).getSeguimientoBloqueoDenuncia() == 1) {
				// Si también me sigue
				if (added && relaciones.get(new_index) == 4) {
					relaciones.set(new_index, 3);
				} else {
					relaciones.add(2);
					added = true;
				}
			} else if (accionesUsuarios.get(j).getUsuarioEmisor().equals(emailInv) &&
					accionesUsuarios.get(j).getUsuarioReceptor().equals(email) &&
					accionesUsuarios.get(j).getSeguimientoBloqueoDenuncia() == 1) {
				if (added && relaciones.get(new_index) == 2) {
					relaciones.set(new_index, 3);
				} else {
					relaciones.add(4);
					added = true;
				}
			} 
		}
		
		if (relaciones.get(new_index) == 0 || relaciones.get(new_index) == 5) {
		   	botones.add(0);
		} else if (privacidades.get(new_index) == 3 && relaciones.get(new_index) == 2) {
		   	botones.add(3);
		} else if (privacidades.get(new_index) == 3 && (relaciones.get(new_index) < 2 || relaciones.get(new_index) == 4)){
		  	botones.add(2);
		} else if (privacidades.get(new_index) == 2 && (relaciones.get(new_index) < 2 || relaciones.get(new_index) == 4)) {
			botones.add(2);
		} else {
			botones.add(1);
		}
		
		req.getSession().setAttribute("indexList", indexList);
		req.getSession().setAttribute("privacidades", privacidades);
		req.getSession().setAttribute("relaciones", relaciones);
		req.getSession().setAttribute("botones", botones);
		req.getSession().setAttribute("ultimoInvitado", asistente2.size());
		req.getSession().setAttribute("lista_invitados", asistente2);
		resp.sendRedirect(req.getContextPath()+"/Convite.jsp");
	}
}
