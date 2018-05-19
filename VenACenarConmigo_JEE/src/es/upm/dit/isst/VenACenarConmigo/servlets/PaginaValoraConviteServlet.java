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
import es.upm.dit.isst.VenACenarConmigo.dao.model.AsistenciaConvite;
import es.upm.dit.isst.VenACenarConmigo.dao.model.Convite;

@WebServlet("/PaginaValoraConviteServlet")

public class PaginaValoraConviteServlet extends HttpServlet {

	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		int idConviteAValorar = Integer.parseInt(req.getParameter("idConvite"));
		Convite convite = ConviteDAOImplementation.getInstance().readConvite(idConviteAValorar);
		List<AsistenciaConvite> asistente = AsistenciaConviteDAOImplementation.getInstance().readAllAsistenciaConvite();
		List<AsistenciaConvite> invitados = new ArrayList<>();
		for (int i = 0; i < asistente.size(); i++) {
			if (asistente.get(i).getIdConvite() == idConviteAValorar
					&& !asistente.get(i).getEmailUsuarioAsistente().equals(req.getSession().getAttribute("email"))) {
				invitados.add(asistente.get(i));
				log(asistente.get(i).getEmailUsuarioAsistente());
			}
		}
		if (convite.getEmailAnfitrion().equals(req.getSession().getAttribute("email"))) {
			req.getSession().setAttribute("anfitrion", true);
		} else {
			req.getSession().setAttribute("anfitrion", false);
		}
		req.getSession().setAttribute("invitadosAValorar", invitados);
		req.getSession().setAttribute("conviteAValorar", convite);
		resp.sendRedirect(req.getContextPath() + "/Valoracion.jsp");
	}
}
