package es.upm.dit.isst.VenACenarConmigo.servlets;

import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.sun.org.apache.xerces.internal.impl.xpath.regex.ParseException;

import es.upm.dit.isst.VenACenarConmigo.dao.AsistenciaConviteDAOImplementation;
import es.upm.dit.isst.VenACenarConmigo.dao.ComentarioConviteDAOImplementation;
import es.upm.dit.isst.VenACenarConmigo.dao.ConviteDAOImplementation;
import es.upm.dit.isst.VenACenarConmigo.dao.UsuarioDAOImplementation;
import es.upm.dit.isst.VenACenarConmigo.dao.model.AsistenciaConvite;
import es.upm.dit.isst.VenACenarConmigo.dao.model.ComentarioConvite;
import es.upm.dit.isst.VenACenarConmigo.dao.model.Convite;
import es.upm.dit.isst.VenACenarConmigo.dao.model.Usuario;

@WebServlet("/MuestraConviteInicioServlet")
public class MuestraConviteInicioServlet extends HttpServlet {
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		int mesesAMostrar =0;
		//Esta variable representa el intervalo de meses que se van a mostrar en el incio
		if(req.getSession().getAttribute("meses_a_mostrar")== null) {
			mesesAMostrar=4;
		}else if (req.getSession().getAttribute("meses_a_mostrar")!= null){
			String mesesAMostrarString = req.getParameter("meses_a_mostrar"); 
			log("numero de meses introducido"+ mesesAMostrar);
			mesesAMostrar = Integer.parseInt(mesesAMostrarString);
		}
		
		String email = (String) req.getSession().getAttribute("email");
		Usuario usuario = UsuarioDAOImplementation.getInstance().readUsuario(email);

		List<Convite> convites = ConviteDAOImplementation.getInstance().readAllConvite();
		String ciudadUsuario = usuario.getCiudad();
		List<Convite> convitesCercanosPorUbicacion = new ArrayList<>();
		for (Convite convite : convites) {
			if (convite.getCiudad() == ciudadUsuario) {
				convitesCercanosPorUbicacion.add(convite);
			}
		}
		int fechaActual = Calendar.getInstance().get(Calendar.DAY_OF_MONTH);
		
		List<Convite>convitesCercanosEnTiempoYUbicacion= isConviteNearInTime(convitesCercanosPorUbicacion,mesesAMostrar);

		req.getSession().setAttribute("lista_convites", convitesCercanosEnTiempoYUbicacion);
		
		req.getSession().setAttribute("meses_a_mostrar", mesesAMostrar);
		req.getSession().setAttribute("ciudad_Usuario", ciudadUsuario);
		
		resp.sendRedirect(req.getContextPath() + "/Inicio.jsp");
	}

	private List<Convite> isConviteNearInTime(List<Convite> convites, int mesesAMostrar) {
		DateFormat formatoFecha = new SimpleDateFormat("dd/MM/yyyy");
		Date fechaActual = new Date();
		int year = Calendar.getInstance().get(Calendar.YEAR);
		int month=Calendar.getInstance().get(Calendar.MONTH);
		int day=Calendar.getInstance().get(Calendar.DAY_OF_MONTH);
		
		boolean saltoDeAno = false;
		int mesAComparar = month + mesesAMostrar;
		if(mesAComparar > 12) {
			year++;
			mesAComparar = mesAComparar -12;
			saltoDeAno=true;
		}
		
		List<Convite> proximosConvites= new ArrayList<>();
		for (Convite convite : convites) {
			try {
				int mesConvite = Integer.parseInt(convite.getFecha().substring(3,5));
				if(mesConvite < 10) {
					int mesConviteSinCero = Integer.parseInt(convite.getFecha().substring(4,5));
					mesConvite = mesConviteSinCero;
				}
				int anoConvite = Integer.parseInt(convite.getFecha().substring(6,10));
				
				boolean entraEnElIntervaloDeTiempo = false;
				
				if(mesConvite <= mesAComparar && !saltoDeAno && anoConvite==year) {
					entraEnElIntervaloDeTiempo = true;
				}else if (saltoDeAno){
					if(anoConvite<year) {
						entraEnElIntervaloDeTiempo = true;
					}else if(mesConvite <= mesAComparar && anoConvite==year) {
						entraEnElIntervaloDeTiempo = true;	
					}
				}
				//Date().compareTo(fecha) = -1 si es una fecha que no ha ocurrido todavía
				if(fechaActual.compareTo(formatoFecha.parse(convite.getFecha()))<0 && entraEnElIntervaloDeTiempo ) {
					proximosConvites.add(convite);
				}
			} catch (java.text.ParseException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return proximosConvites;
	}
}