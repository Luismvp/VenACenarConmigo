package es.upm.dit.isst.VenACenarConmigo.servlets;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import es.upm.dit.isst.VenACenarConmigo.dao.ConviteDAOImplementation;
import es.upm.dit.isst.VenACenarConmigo.dao.model.Convite;

@WebServlet("/BuscarConviteServlet")
public class BuscarConviteServlet extends HttpServlet{
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String email = (String) req.getSession().getAttribute("email");
		List<Convite> convites = ConviteDAOImplementation.getInstance().readAllConvite();
		// 1=ninguna, 2=sigoAlAnfitrion 3=nosSeguimos
		int[] relaciones = null;
		int[] privacidades = null;
		
		/*for (int i = 0; i < convites.size(); i++) {
			String emailAnfitrion = convites.get(i).getEmailAnfitrion();
			// Compruebo la relacion del usuario con el anfitrion (1, 2 o 3)
			// relaciones[i] = ...
			Usuario anfitrion = UsuarioDAOImplementation.getInstance().readUsuario(emailAnfitrion);
			privacidades[i] = anfitrion.getPrivacidad2();
		}
		
		for (int i = 0; i < convites.size(); i++) {
			if (privacidades[i] == 3 && relaciones[i] < 3) {
				convites.remove(i);
				i--;
			} else if (privacidades[i] == 2 && relaciones[i] < 2) {
				convites.remove(i);
				i--;
			}
		}*/
		int selectedFilter = 0;
		int selectedOrder = 1;
		List<Convite> convites_ordenados = convites;
		convites_ordenados = ordenarPorFecha(convites);
		
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
