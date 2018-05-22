package es.upm.dit.isst.VenACenarConmigo.servlets;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import es.upm.dit.isst.VenACenarConmigo.dao.ConviteDAOImplementation;
import es.upm.dit.isst.VenACenarConmigo.dao.model.Convite;

@WebServlet("/OrdenarConvitesServlet")
public class OrdenarConvitesServlet extends HttpServlet {
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String email = (String) req.getSession().getAttribute("email");
		List<Convite> convites = (List<Convite>) req.getSession().getAttribute("convite_list");
		List<Convite> convites_ordenados = convites;
		int selectedFilter = (int) req.getSession().getAttribute("selectedFilter");
		int selectedOrder = 0;
		
		String orden = req.getParameter("selectOrden");
		if (orden.equals("fecha")) {
			convites_ordenados = ordenarPorFecha(convites);
			selectedOrder = 1;
		} else if (orden.equals("precio_asc")) {
			convites_ordenados = ordenarPorPrecioAsc(convites);			
			selectedOrder = 2;
		} else if (orden.equals("precio_desc")) {
			convites_ordenados = ordenarPorPrecioDesc(convites);		
			selectedOrder = 3;
		} 
		
		req.getSession().setAttribute("selectedFilter", selectedFilter);
		req.getSession().setAttribute("selectedOrder", selectedOrder);
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
	
	private List<Convite> ordenarPorPrecioAsc (List<Convite> lista) {
		Convite temp;
		for (int i=0; i<lista.size(); i++) {
			for (int j=1; j < (lista.size()-i); j++) {
				if (masCaroPrimero(lista.get(j-1), lista.get(j))) {
					temp = lista.get(j-1);
					lista.set(j-1, lista.get(j));
					lista.set(j, temp);
				}
			}
		}
		return lista;
	}
	
	private List<Convite> ordenarPorPrecioDesc (List<Convite> lista) {
		Convite temp;
		for (int i=0; i<lista.size(); i++) {
			for (int j=1; j < (lista.size()-i); j++) {
				if (!masCaroPrimero(lista.get(j-1), lista.get(j))) {
					temp = lista.get(j-1);
					lista.set(j-1, lista.get(j));
					lista.set(j, temp);
				}
			}
		}
		return lista;
	}
	
	private boolean masCaroPrimero (Convite conv1, Convite conv2) {
		float precio1 = -1;
		if (!conv1.getPrecioInvitado().equals("")) {
			precio1 = Float.parseFloat(conv1.getPrecioInvitado());
		}
		float precio2 = -1;
		if (!conv2.getPrecioInvitado().equals("")) {
			precio2 = Float.parseFloat(conv2.getPrecioInvitado());
		}
		if (precio1 < precio2) {
			return false;
		}
		return true;
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
