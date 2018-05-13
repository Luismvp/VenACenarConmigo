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

@WebServlet("/AceptaInscripcionServlet")
public class AceptaInscripcionServlet extends HttpServlet {
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		int idConvite = Integer.parseInt(req.getParameter("idConvite"));
		String emailAsistente = (String) req.getParameter("emailAsistente");
		List<AsistenciaConvite> asistentes = AsistenciaConviteDAOImplementation.getInstance().readAllAsistenciaConvite();
		AsistenciaConvite asistenteConfirmado = new AsistenciaConvite();
		for (int i = 0; i < asistentes.size(); i++) {
			if (asistentes.get(i).getIdConvite() == idConvite
					&& asistentes.get(i).getEmailUsuarioAsistente().equals(emailAsistente)) {
				asistenteConfirmado = asistentes.get(i);
			}
		}
		asistenteConfirmado.setConfirmado(true);
		AsistenciaConviteDAOImplementation.getInstance().updateAsistenciaConvite(asistenteConfirmado);
		
		List<AsistenciaConvite> asistentes2 = AsistenciaConviteDAOImplementation.getInstance()
				.readAllAsistenciaConvite();
		List<AsistenciaConvite> asistentes3 = new ArrayList();
		for (int i = 0; i < asistentes2.size(); i++) {
			if (asistentes2.get(i).getIdConvite() == idConvite) {
				asistentes3.add(asistentes2.get(i));
			}
		}
		
		req.getSession().setAttribute("lista_invitados", asistentes3);
		resp.sendRedirect(req.getContextPath() + "/Convite.jsp");
	}
}
