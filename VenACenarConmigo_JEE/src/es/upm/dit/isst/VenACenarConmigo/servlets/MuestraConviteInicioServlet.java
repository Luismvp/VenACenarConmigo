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
import es.upm.dit.isst.VenACenarConmigo.dao.model.AsistenciaConvite;
import es.upm.dit.isst.VenACenarConmigo.dao.model.ComentarioConvite;
import es.upm.dit.isst.VenACenarConmigo.dao.model.Convite;
import es.upm.dit.isst.VenACenarConmigo.dao.model.Usuario;

@WebServlet("/MuestraConviteInicioServlet")
public class MuestraConviteInicioServlet extends HttpServlet{
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		String email = (String) req.getSession().getAttribute("email");
		Usuario usuario = UsuarioDAOImplementation.getInstance().readUsuario(email);

		List<Convite> convites = ConviteDAOImplementation.getInstance().readAllConvite();
		String ciudadUsuario = usuario.getCiudad();
		List<Convite> convitesCercanos= new ArrayList<>();
		for (Convite convite : convites) {
			if(convite.getCiudad()== ciudadUsuario) {
				convitesCercanos.add(convite);
			}
		}
		
		req.getSession().setAttribute("lista_convites", convitesCercanos);
		resp.sendRedirect(req.getContextPath() + "/Inicio.jsp");
	}
}
