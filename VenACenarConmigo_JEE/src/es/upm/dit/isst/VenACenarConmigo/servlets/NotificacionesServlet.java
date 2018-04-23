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


@WebServlet("/NotificacionesServlet")

public class NotificacionesServlet extends HttpServlet {
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String email = (String) req.getSession().getAttribute("email");
		List<AsistenciaConvite> notificaciones = AsistenciaConviteDAOImplementation.getInstance().readNotificacionesAsistenciaConvite(email);
		req.getSession().setAttribute("lista_notificaciones", notificaciones);
		List<Convite> convites = new ArrayList();
		for (int i=0;i<notificaciones.size();i++) {
		
			convites.add((Convite)ConviteDAOImplementation.getInstance().readConvite(notificaciones.get(i).getIdConvite()));
		}
		req.getSession().setAttribute("numero_notificaciones", AsistenciaConviteDAOImplementation.getInstance().readNotificacionesAsistenciaConvite(email).size());
		req.getSession().setAttribute("Lista_convites", convites);
		resp.sendRedirect(req.getContextPath() + "/Notificaciones.jsp");
		
		
	}
}
