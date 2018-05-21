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

import es.upm.dit.isst.VenACenarConmigo.dao.AsistenciaConviteDAOImplementation;
import es.upm.dit.isst.VenACenarConmigo.dao.ConviteDAOImplementation;
import es.upm.dit.isst.VenACenarConmigo.dao.UsuarioDAOImplementation;
import es.upm.dit.isst.VenACenarConmigo.dao.model.AsistenciaConvite;
import es.upm.dit.isst.VenACenarConmigo.dao.model.Convite;
import es.upm.dit.isst.VenACenarConmigo.dao.model.Usuario;

@WebServlet("/MuestraConviteInicioServlet")
public class MuestraConviteInicioServlet extends HttpServlet {
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		int mesesAMostrar = 0;
		String mesesAMostrarString = "";
		// Esta variable representa el intervalo de meses que se van a mostrar en el
		// incio
		if (req.getSession().getAttribute("meses_a_mostrar") == null) {
			mesesAMostrar = 1;
		} else if (req.getSession().getAttribute("meses_a_mostrar") != null) {
			if (req.getParameter("meses_a_mostrar") == null) {
				mesesAMostrarString = "" + 1;
			} else {
				mesesAMostrarString = req.getParameter("meses_a_mostrar");
			}
			log("numero de meses introducido " + mesesAMostrarString);
			mesesAMostrar = Integer.parseInt(mesesAMostrarString);
		}

		String email = (String) req.getSession().getAttribute("email");
		Usuario usuario = UsuarioDAOImplementation.getInstance().readUsuario(email);

		List<Convite> convites = ConviteDAOImplementation.getInstance().readAllConvite();
		List<AsistenciaConvite> asistencias = AsistenciaConviteDAOImplementation.getInstance()
				.readAllAsistenciaConvite();
		String ciudadUsuario = usuario.getCiudad();
		List<Convite> convitesCercanosPorUbicacion = new ArrayList<>();
		for (Convite convite : convites) {
			if (convite.getEmailAnfitrion().equals(usuario.getEmail())) {
				convitesCercanosPorUbicacion.add(convite);
				log("convite Anfitrion" + convite.getNombre());
			}
			for (AsistenciaConvite asistencia : asistencias) {
				if (convite.getIdConvite() == asistencia.getIdConvite()
						&& asistencia.getEmailUsuarioAsistente().equals(usuario.getEmail())) {
					convitesCercanosPorUbicacion.add(convite);
					log("convite Invitado" + convite.getNombre());
				}
			}

		}
		int fechaActual = Calendar.getInstance().get(Calendar.DAY_OF_MONTH);

		List<Convite> convitesCercanosEnTiempoYUbicacion = isConviteNearInTime(convitesCercanosPorUbicacion,
				mesesAMostrar);
		convitesCercanosEnTiempoYUbicacion = ordenarPorFecha(convitesCercanosEnTiempoYUbicacion);

		req.getSession().setAttribute("lista_convites", convitesCercanosEnTiempoYUbicacion);

		req.getSession().setAttribute("meses_a_mostrar", mesesAMostrar);
		req.getSession().setAttribute("ciudad_Usuario", ciudadUsuario);

		resp.sendRedirect(req.getContextPath() + "/Inicio.jsp");
	}

	private List<Convite> isConviteNearInTime(List<Convite> convites, int mesesAMostrar) {
		DateFormat formatoFecha = new SimpleDateFormat("dd/MM/yyyy");
		Date fechaActual = new Date();
		int year = Calendar.getInstance().get(Calendar.YEAR);
		log("año " + year);
		int month = Calendar.getInstance().get(Calendar.MONTH) + 1;
		log("mes " + month);
		int day = Calendar.getInstance().get(Calendar.DAY_OF_MONTH);

		boolean saltoDeAno = false;
		int mesAComparar = month + mesesAMostrar;
		if (mesAComparar > 12) {
			year++;
			mesAComparar = mesAComparar - 12;
			saltoDeAno = true;
		}

		List<Convite> proximosConvites = new ArrayList<>();
		for (Convite convite : convites) {
			try {
				int mesConvite = Integer.parseInt(convite.getFecha().substring(3, 5));
				if (mesConvite < 10) {
					int mesConviteSinCero = Integer.parseInt(convite.getFecha().substring(4, 5));
					mesConvite = mesConviteSinCero;
					log("mes convite :" + mesConvite);
				}
				int anoConvite = Integer.parseInt(convite.getFecha().substring(6, 10));

				boolean entraEnElIntervaloDeTiempo = false;

				if (mesConvite <= mesAComparar && !saltoDeAno && anoConvite == year) {
					entraEnElIntervaloDeTiempo = true;
				} else if (saltoDeAno) {
					if (anoConvite < year) {
						entraEnElIntervaloDeTiempo = true;
					} else if (mesConvite <= mesAComparar && anoConvite == year) {
						entraEnElIntervaloDeTiempo = true;
					}
				}
				// Date().compareTo(fecha) = -1 si es una fecha que no ha ocurrido todavía
				if (fechaActual.compareTo(formatoFecha.parse(convite.getFecha())) < 0 && entraEnElIntervaloDeTiempo) {
					proximosConvites.add(convite);
				}
			} catch (java.text.ParseException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return proximosConvites;
	}
	private List<Convite> ordenarPorFecha (List<Convite> lista) {
		Convite temp;
		for (int i=0; i<lista.size(); i++) {
			for (int j=1; j < (lista.size()-i); j++) {
				if (!masRecientePrimero(lista.get(j-1), lista.get(j))) {
					temp = lista.get(j-1);
					lista.set(j-1, lista.get(j));
					lista.set(j, temp);
				}
			}
		}
		return lista;
	}
	
	//Formato fecha => dd/mm/aaaa  Formato hora => hh:mm
	private boolean conviteAnteriorAFecha (Convite conv, String fecha, String hora) {
		String fecha_conv = conv.getFecha();
		String hora_conv = conv.getHoraComienzo();
		int año = Integer.parseInt(fecha.substring(6));
		int año_conv = Integer.parseInt(fecha_conv.substring(6));
		
		if (año_conv > año) {
			return false;
		} else if (año_conv < año) {
			return true;
		} else {
			int mes = Integer.parseInt(fecha.substring(3,5));
			int mes_conv = Integer.parseInt(fecha_conv.substring(3,5));
			
			if (mes_conv > mes) {
				return false;
			} else if (mes_conv < mes) {
				return true;
			} else {
				int dia = Integer.parseInt(fecha.substring(0,2));
				int dia_conv = Integer.parseInt(fecha_conv.substring(0,2));
				
				if (dia_conv > dia) {
					return false;
				} else if (dia_conv < dia) {
					return true;
				}
				else {
					int hh = Integer.parseInt(hora.substring(0,2));
					int hh_conv = Integer.parseInt(hora_conv.substring(0,2));
					
					if (hh_conv > hh) {
						return false;
					} else if (hh_conv < hh) {
						return true;
					}
					else {
						int mm = Integer.parseInt(hora.substring(3,5));
						int mm_conv = Integer.parseInt(hora_conv.substring(3));
						
						if (mm_conv > mm) {
							return false;
						} else if (mm_conv < mm) {
							return true;
						}
					}
				}
			}
		}
		return true;
	}
	
	private String getNumeroMes (String mes) {
		String num_mes;
		switch(mes) {
		case "Jan":
			num_mes = "01";
			break;
		case "Feb":
			num_mes = "02";
			break;
		case "Mar":
			num_mes = "03";
			break;
		case "Apr":
			num_mes = "04";
			break;
		case "May":
			num_mes = "05";
			break;
		case "Jun":
			num_mes = "06";
			break;
		case "Jul":
			num_mes = "07";
			break;
		case "Aug":
			num_mes = "08";
			break;
		case "Sep":
			num_mes = "09";
			break;
		case "Oct":
			num_mes = "10";
			break;
		case "Nov":
			num_mes = "11";
			break;
		case "Dec":
			num_mes = "12";
			break;
		default:
			num_mes = "00";
			break;
		}
		return num_mes;
	}
	
	private boolean masRecientePrimero (Convite conv1, Convite conv2) {
		String fecha1 = conv1.getFecha();
		String fecha2 = conv2.getFecha();
		
		int año1 = Integer.parseInt(fecha1.substring(6));
		int año2 = Integer.parseInt(fecha2.substring(6));
		
		if (año1 > año2) {
			return false;
		} else if (año1 < año2) {
			return true;
		} else {
			int mes1 = Integer.parseInt(fecha1.substring(3,5));
			int mes2 = Integer.parseInt(fecha2.substring(3,5));
			
			if (mes1 > mes2) {
				return false;
			} else if (mes1 < mes2) {
				return true;
			} else {
				int dia1 = Integer.parseInt(fecha1.substring(0,2));
				int dia2 = Integer.parseInt(fecha2.substring(0,2));
				
				if (dia1 > dia2) {
					return false;
				} else if (dia1 < dia2) {
					return true;
				}
			}
		}
		return true;
	}

	
}