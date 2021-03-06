package es.upm.dit.isst.VenACenarConmigo.servlets;

import java.io.IOException;
import java.util.Calendar;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import es.upm.dit.isst.VenACenarConmigo.dao.ConviteDAOImplementation;
import es.upm.dit.isst.VenACenarConmigo.dao.NotificacionDAOImplementation;
import es.upm.dit.isst.VenACenarConmigo.dao.model.Convite;
import es.upm.dit.isst.VenACenarConmigo.dao.model.Notificacion;

@WebServlet("/NuevoConviteServlet")
public class NuevoConviteServlet extends HttpServlet {
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String nombre = req.getParameter("nombre");
		String fecha = req.getParameter("fecha");
		String[] anoMesDia = new String[3];
		String[] horaMinComienzo = new String[2];
		String[] horaMinFin = new String[2];
		if (!fecha.isEmpty()) {
			anoMesDia = fecha.split("/");
		}
		String horaComienzo = req.getParameter("horaComienzo");
		if (!horaComienzo.isEmpty()) {
			horaMinComienzo = horaComienzo.split(":");
		}
		String horaFin = req.getParameter("horaFin");
		if (!horaFin.isEmpty()) {
			horaMinFin = horaFin.split(":");
		}
		String restaurante = "No aplica";
		if (!req.getParameter("restaurante").isEmpty()) {
			restaurante = req.getParameter("restaurante");
		}
		String menu = req.getParameter("menu");
		String numInvitados = req.getParameter("numInvitados");
		String precioInvitado = req.getParameter("precioInvitado");
		String ciudad = req.getParameter("ciudad");
		String area = req.getParameter("area");
		String descripcion = req.getParameter("descripcion");
		String emailAnfitrion = (String) req.getSession().getAttribute("email");

		int numInvitados2 = 1;
		for (int i = 1; i < 16; i++) {
			String j = Integer.toString(i);
			if (numInvitados.equals(j)) {
				numInvitados2 = Integer.parseInt(j);
			}
		}
		Calendar fechaYHoraComienzo = Calendar.getInstance();
		if (null != anoMesDia[0]) {
			fechaYHoraComienzo.set(Integer.parseInt(anoMesDia[2]), (Integer.parseInt(anoMesDia[1]) - 1),
					Integer.parseInt(anoMesDia[0]), Integer.parseInt(horaMinComienzo[0]),
					Integer.parseInt(horaMinComienzo[1]));
		}
		Calendar fechaYHoraFin = Calendar.getInstance();
		if (null != anoMesDia[0]) {
			if (Integer.parseInt(horaMinFin[0]) >= Integer.parseInt(horaMinComienzo[0])) {
				fechaYHoraFin.set(Integer.parseInt(anoMesDia[2]), (Integer.parseInt(anoMesDia[1]) - 1),
						Integer.parseInt(anoMesDia[0]), Integer.parseInt(horaMinFin[0]),
						Integer.parseInt(horaMinFin[1]));
			}else {
				fechaYHoraFin.set(Integer.parseInt(anoMesDia[2]), (Integer.parseInt(anoMesDia[1]) - 1),
						Integer.parseInt(anoMesDia[0])+1, Integer.parseInt(horaMinFin[0]),
						Integer.parseInt(horaMinFin[1]));
			}
		}
		int IdConvite = ConviteDAOImplementation.getInstance().readAllConvite().size() + 1;
		Convite convite = new Convite();
		convite.setIdConvite(IdConvite);
		convite.setNombre(nombre);
		convite.setFechaYHoraComienzo(fechaYHoraComienzo);
		convite.setFechaYHoraFin(fechaYHoraFin);
		convite.setFecha(fecha);
		convite.setHoraComienzo(horaComienzo);
		convite.setHoraFin(horaFin);
		convite.setRestaurante(restaurante);
		convite.setMenu(menu);
		convite.setMaxInvitados(numInvitados2);
		convite.setPrecioInvitado(precioInvitado);
		convite.setCiudad(ciudad);
		convite.setArea(area);
		convite.setEmailAnfitrion(emailAnfitrion);
		convite.setDescripcion(descripcion);
		
		
		ConviteDAOImplementation.getInstance().createConvite(convite);
		Notificacion notificacion = new Notificacion();
		notificacion.setIdNotificacion(NotificacionDAOImplementation.getInstance().readAllNotificacion().size()+1);
		notificacion.setConvite(convite);
		notificacion.setAsistencia(null);
		notificacion.setChecked(false);
		notificacion.setHasFinished(false);
		notificacion.setHasStarted(false);
		NotificacionDAOImplementation.getInstance().createNotificacion(notificacion);
		req.getSession().setAttribute("notificacion", notificacion);
		req.getSession().setAttribute("convite", convite);
		req.getSession().setAttribute("idConvite", IdConvite);
		req.getSession().setAttribute("emailAnfitrion", emailAnfitrion);
		resp.sendRedirect(req.getContextPath() + "/Invitados.jsp");
	}

}
