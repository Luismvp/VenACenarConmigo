package es.upm.dit.isst.VenACenarConmigo.servlets;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import es.upm.dit.isst.VenACenarConmigo.dao.UsuarioDAOImplementation;
import es.upm.dit.isst.VenACenarConmigo.dao.model.Usuario;


@WebServlet("/ValidacionServlet")

public class ValidacionServlet extends HttpServlet {
	
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		int idConvite = Integer.parseInt(req.getParameter("idConvite"));
		String email = (String) req.getSession().getAttribute("email");
		String[] inv = new String[15];
		String emailAnfitrion;
		for(int i = 1;i <= 15;i++ ) {
			if(req.getParameter("caras"+i)!=null) {
				inv[i-1]=req.getParameter("caras"+i);
			}else {
				inv[i-1]="0";
			}
		}
		if(req.getParameter("carasAnfi")!=null) {
			
		}
		
	}	
}
