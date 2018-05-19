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
import es.upm.dit.isst.VenACenarConmigo.dao.ConviteDAOImplementation;
import es.upm.dit.isst.VenACenarConmigo.dao.UsuarioDAOImplementation;
import es.upm.dit.isst.VenACenarConmigo.dao.model.AccionUsuario;
import es.upm.dit.isst.VenACenarConmigo.dao.model.Convite;
import es.upm.dit.isst.VenACenarConmigo.dao.model.Usuario;

@WebServlet("/BuscarConviteServlet")
public class BuscarConviteServlet extends HttpServlet{
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String email = (String) req.getSession().getAttribute("email");
		Calendar now = Calendar.getInstance();
		String mes = getNumeroMes(now.getTime().toString().substring(4,7));
		String fecha_actual = now.getTime().toString().substring(8,10)+"/"+mes+"/"+now.getTime().toString().substring(25);
		String hora_actual = now.getTime().toString().substring(11,17);
		List<Convite> convites = ConviteDAOImplementation.getInstance().readAllConvite();
		// Quitamos los convites que ya se han celebrado (o ya han empezado)
		for (int i = 0; i < convites.size(); i++) {
			if (conviteAnteriorAFecha(convites.get(i), fecha_actual, hora_actual)) {
				convites.remove(i);
			}
		}
		List<Convite> convites_ordenados = convites;
		convites_ordenados = ordenarPorFecha(convites);
		int selectedFilter = 0;
		int selectedOrder = 1;
		List<AccionUsuario> accionesUsuarios = AccionUsuarioDAOImplementation.getInstance().readAllAccionUsuario();
		// 1=ninguna, 2=sigoAlAnfitrion 3=nosSeguimos (4=meSigue)
		List<Integer> relaciones = new ArrayList<>();
		for (int i = 0; i < convites.size(); i++) {
			relaciones.add(1);
		}
		List<Integer> privacidades = new ArrayList<>();
		for (int i = 0; i < convites.size(); i++) {
			privacidades.add(1);
		}
		
		for (int i = 0; i < convites.size(); i++) {
			String emailAnfitrion = convites.get(i).getEmailAnfitrion();
			Usuario anfitrion = UsuarioDAOImplementation.getInstance().readUsuario(emailAnfitrion);
			if(email.equals(emailAnfitrion)) {
				privacidades.set(i, 1);
				continue;
			} else {
				privacidades.set(i, anfitrion.getPrivacidad2());
			}
			
			// Compruebo la relacion del usuario con el anfitrion (1, 2 o 3)
			for (int j = 0; j < accionesUsuarios.size(); j++) {
				// Si le sigo
				if (accionesUsuarios.get(j).getUsuarioEmisor().equals(email) &&
					accionesUsuarios.get(j).getUsuarioReceptor().equals(emailAnfitrion) &&
					accionesUsuarios.get(j).getSeguimientoBloqueoDenuncia() == 1) {
					// Si también me sigue
					if (relaciones.get(i) == 4) {
						relaciones.set(i, 3);
						break;
					} else {
						relaciones.set(i, 2);
					}
				} else if (accionesUsuarios.get(j).getUsuarioEmisor().equals(emailAnfitrion) &&
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
		
		for (int i = 0; i < convites.size(); i++) {
			if (privacidades.get(i) == 3 && relaciones.get(i) < 3) {
				convites.remove(i);
				privacidades.remove(i);
				relaciones.remove(i);
				i--;
			} else if (privacidades.get(i) == 2 && relaciones.get(i) < 2) {
				convites.remove(i);
				privacidades.remove(i);
				relaciones.remove(i);
				i--;
			}
		}
		
		
		req.getSession().setAttribute("selectedOrder", selectedOrder);
		req.getSession().setAttribute("selectedFilter", selectedFilter);
		req.getSession().setAttribute("convite_list", convites_ordenados);
		resp.sendRedirect(req.getContextPath() + "/BuscarConvite.jsp");
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
