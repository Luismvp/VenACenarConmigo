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

@WebServlet("/FiltrarConvitesServlet")
public class FiltrarConvitesServlet extends HttpServlet {
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String email = (String) req.getSession().getAttribute("email");
		List<Convite> convites = ConviteDAOImplementation.getInstance().readAllConvite();
		List<Convite> convites_filtrados = convites;
		int selectedOrder = 0;
		int selectedFilter = 0;
		
		String filtro = (String) req.getParameter("filtro");
		
		String orden = req.getParameter("selectFiltro");
		if (orden.equals("nombre")) {
			convites_filtrados = filtrarPorNombre(convites, filtro);
			selectedFilter = 1;
		} else if (orden.equals("ciudad")) {
			convites_filtrados = filtrarPorCiudad(convites, filtro);
			selectedFilter = 2;
		} else if (orden.equals("area")) {
			convites_filtrados = filtrarPorArea(convites, filtro);
			selectedFilter = 3;
		} 
		
		req.getSession().setAttribute("selectedFilter", selectedFilter);
		req.getSession().setAttribute("selectedOrder", selectedOrder);
		req.getSession().setAttribute("convite_list", convites_filtrados);
		
		resp.sendRedirect(req.getContextPath() + "/BuscarConvite.jsp");
	}

	private List<Convite> filtrarPorNombre (List<Convite> lista, String nombre) {
		if (nombre.equals("")) {
			return lista;
		}
		for (int i=0; i<lista.size(); i++) {
			if(!lista.get(i).getNombre().contains(nombre)) {
				lista.remove(i);
				i--;
			}
		}
		return lista;
	}
	
	private List<Convite> filtrarPorCiudad (List<Convite> lista, String ciudad) {
		if (ciudad.equals("")) {
			return lista;
		}
		for (int i=0; i<lista.size(); i++) {
			if(!lista.get(i).getCiudad().contains(ciudad)) {
				lista.remove(i);
				i--;
			}
		}
		return lista;
	}
	
	private List<Convite> filtrarPorArea (List<Convite> lista, String area) {
		if (area.equals("")) {
			return lista;
		}
		for (int i=0; i<lista.size(); i++) {
			if(!lista.get(i).getArea().contains(area)) {
				lista.remove(i);
				i--;
			}
		}
		return lista;
	}
	
}
