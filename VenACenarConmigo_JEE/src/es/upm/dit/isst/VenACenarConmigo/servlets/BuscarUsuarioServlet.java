package es.upm.dit.isst.VenACenarConmigo.servlets;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import es.upm.dit.isst.VenACenarConmigo.dao.AccionUsuarioDAOImplementation;
import es.upm.dit.isst.VenACenarConmigo.dao.UsuarioDAOImplementation;
import es.upm.dit.isst.VenACenarConmigo.dao.model.AccionUsuario;
import es.upm.dit.isst.VenACenarConmigo.dao.model.Usuario;

@WebServlet("/BuscarUsuarioServlet")
public class BuscarUsuarioServlet extends HttpServlet {
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		req.setCharacterEncoding("UTF-8");
		String email = (String) req.getSession().getAttribute("email");
		String email_usuario = (String) req.getParameter("email_usuario");
		String nombre_usuario = (String) req.getParameter("nombre_usuario");
		String apellidos_usuario = (String) req.getParameter("apellidos_usuario");
		List<Usuario> usuarios = UsuarioDAOImplementation.getInstance().readAllUsuarios();
		List<Usuario> resBusqueda = new ArrayList<>();
		if (null != email_usuario && !email_usuario.isEmpty()) {
			for (Usuario u : usuarios) {
				if (u.getEmail().equals(email_usuario)) {
					resBusqueda.add(u);
				}
			}
		} else {
			for (Usuario u : usuarios) {
				if (u.getNombre().equals(nombre_usuario) && u.getApellidos().equals(apellidos_usuario)) {
					resBusqueda.add(u);
				}
			}
		}
		
		List<AccionUsuario> accionesUsuarios = AccionUsuarioDAOImplementation.getInstance().readAllAccionUsuario();
		// 1=ninguna, 2=sigoAlAnfitrion 3=nosSeguimos (4=meSigue) 5=meBloqueó
		List<Integer> relaciones = new ArrayList<>();
		for (int i = 0; i < resBusqueda.size(); i++) {
			relaciones.add(1);
		}
		List<Integer> privacidades = new ArrayList<>();
		for (int i = 0; i < resBusqueda.size(); i++) {
			privacidades.add(1);
		}
		// 0=Ninguno 1=Ver perfil, 2=Seguir, 3=Dejar de seguir
		List<Integer> botones = new ArrayList<>();
		for (int i = 0; i < resBusqueda.size(); i++) {
			botones.add(1);
		}
		for (int i = 0; i < resBusqueda.size(); i++) {
			String emailAjeno = resBusqueda.get(i).getEmail();
			if(email.equals(emailAjeno)) {
				privacidades.set(i, 1);
				continue;
			} else {
				privacidades.set(i, resBusqueda.get(i).getPrivacidad1());
			}
			
			// Compruebo la relacion del usuario con el usuario que busca (1, 2 o 3)
			for (int j = 0; j < accionesUsuarios.size(); j++) {
			    if (accionesUsuarios.get(j).getUsuarioEmisor().equals(emailAjeno) &&
					accionesUsuarios.get(j).getUsuarioReceptor().equals(email) &&
					accionesUsuarios.get(j).getSeguimientoBloqueoDenuncia() == 2) {
			    	relaciones.set(i, 5);
					break;
			    } else if (accionesUsuarios.get(j).getUsuarioEmisor().equals(email) &&
					accionesUsuarios.get(j).getUsuarioReceptor().equals(emailAjeno) &&
					accionesUsuarios.get(j).getSeguimientoBloqueoDenuncia() == 1) {
					// Si también me sigue
					if (relaciones.get(i) == 4) {
						relaciones.set(i, 3);
						break;
					} else {
						relaciones.set(i, 2);
					}
				} else if (accionesUsuarios.get(j).getUsuarioEmisor().equals(emailAjeno) &&
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
		
		for (int i = 0; i < resBusqueda.size(); i++) {
		    if (relaciones.get(i) == 5) {
		    	botones.set(i, 0);
		    } else if (privacidades.get(i) == 3 && relaciones.get(i) == 2) {
		    	botones.set(i, 3);
		    } else if (privacidades.get(i) == 3 && (relaciones.get(i) < 2 || relaciones.get(i) == 4)){
		    	botones.set(i, 2);
			} else if (privacidades.get(i) == 2 && (relaciones.get(i) < 2 || relaciones.get(i) == 4)) {
				botones.set(i, 2);
			}
		}
		
		List<Integer> indexList = new ArrayList<>();
		for (int i = 0; i < resBusqueda.size(); i++) {
			indexList.add(i);
		}
		
		req.getSession().setAttribute("privacidades", privacidades);
		req.getSession().setAttribute("relaciones", relaciones);
		req.getSession().setAttribute("botones", botones);
		req.getSession().setAttribute("usuario_list", resBusqueda);
		req.getSession().setAttribute("index_list", indexList);
		resp.sendRedirect(req.getContextPath() + "/BuscarUsuario.jsp");
	}
}