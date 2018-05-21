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
import es.upm.dit.isst.VenACenarConmigo.dao.model.AsistenciaConvite;

@WebServlet("/RechazaInscripcionServlet")
public class RechazaInscripcionServlet extends HttpServlet {
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		int idConvite = Integer.parseInt(req.getParameter("idConvite"));
		String emailAsistente = (String) req.getParameter("emailAsistente");
		List<AsistenciaConvite> asistentes = AsistenciaConviteDAOImplementation.getInstance().readAllAsistenciaConvite();
		AsistenciaConvite asistenteRechazado = null;
		for (int i = 0; i < asistentes.size(); i++) {
			if (asistentes.get(i).getIdConvite() == idConvite
					&& asistentes.get(i).getEmailUsuarioAsistente().equals(emailAsistente)) {
				asistenteRechazado = asistentes.get(i);
			}
		}
		AsistenciaConviteDAOImplementation.getInstance().deleteAsistenciaConvite(asistenteRechazado);
		asistentes = null;
		asistentes = AsistenciaConviteDAOImplementation.getInstance().readAllAsistenciaConvite();
		for (int i = 1; i < asistentes.size(); i++) {
			log("" + asistentes.get(i).getIdAsistente());
			if (asistentes.get(i).getIdConvite() == idConvite
					&& asistentes.get(i).getIdAsistente() - asistentes.get(i - 1).getIdAsistente() == 2) {
				log("" + asistentes.get(i).getIdAsistente());
				AsistenciaConviteDAOImplementation.getInstance().deleteAsistenciaConvite(asistentes.get(i));
				asistentes.get(i).setIdAsistente(asistentes.get(i).getIdAsistente() - 1);
				asistentes.get(i).setNumeroInvitado(asistentes.get(i).getNumeroInvitado() - 1);
				AsistenciaConviteDAOImplementation.getInstance().createAsistenciaConvite(asistentes.get(i));
				asistentes.clear();
				asistentes = AsistenciaConviteDAOImplementation.getInstance().readAllAsistenciaConvite();
			}
		}
		for (int i = 1; i < asistentes.size(); i++) {
			log("" + asistentes.get(i).getIdAsistente());
			if (asistentes.get(i).getIdAsistente() - asistentes.get(i - 1).getIdAsistente() == 2) {
				log("" + asistentes.get(i).getIdAsistente());
				AsistenciaConviteDAOImplementation.getInstance().deleteAsistenciaConvite(asistentes.get(i));
				asistentes.get(i).setIdAsistente(asistentes.get(i).getIdAsistente() - 1);
				AsistenciaConviteDAOImplementation.getInstance().createAsistenciaConvite(asistentes.get(i));
				asistentes.clear();
				asistentes = AsistenciaConviteDAOImplementation.getInstance().readAllAsistenciaConvite();
			}
		}
		
		List<AsistenciaConvite> asistentes2 = new ArrayList();
		for (int i = 0; i < asistentes.size(); i++) {
			if (asistentes.get(i).getIdConvite() == idConvite) {
				asistentes2.add(asistentes.get(i));
			}
		}
		
		List<Integer> indexList = new ArrayList<>();
		for (int i = 0; i < asistentes2.size(); i++) {
			indexList.add(i);
		}
		int index = Integer.parseInt(req.getParameter("index"));
		List<Integer> privacidades = (List<Integer>) req.getSession().getAttribute("privacidades");
		List<Integer> relaciones = (List<Integer>) req.getSession().getAttribute("relaciones");
		List<Integer> botones = (List<Integer>) req.getSession().getAttribute("botones");
		privacidades.remove(index);
		relaciones.remove(index);
		botones.remove(index);
		
		req.getSession().setAttribute("indexList", indexList);
		req.getSession().setAttribute("privacidades", privacidades);
		req.getSession().setAttribute("relaciones", relaciones);
		req.getSession().setAttribute("botones", botones);
		req.getSession().setAttribute("lista_invitados", asistentes2);
		resp.sendRedirect(req.getContextPath() + "/Convite.jsp");
	}
}
