package es.upm.dit.isst.VenACenarConmigo.servlets;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import es.upm.dit.isst.VenACenarConmigo.dao.ValoracionDAOImplementation;
import es.upm.dit.isst.VenACenarConmigo.dao.model.Valoracion;

@WebServlet("/ValoraConviteServlet")

public class ValoraConviteServlet extends HttpServlet {

	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		int idConvite = Integer.parseInt(req.getParameter("idConvite"));
		String  email = (String) req.getSession().getAttribute("email");
		String[] invitados = new String[15];
		int[] valoracion = new int[15];
		String[] comentarios = new String[15];
		int idValoracion=0;
		Valoracion valoracionFinal = new Valoracion();
		for(int i=1;i<=15;i++) {
			if(req.getParameter("email"+i)!=null) {
				invitados[i-1]=req.getParameter("email"+1);
			}else {
				invitados[i-1]="0";
			}
			log("invitados "+invitados[i-1]);
			if(req.getParameter("caras"+i)!=null) {
				valoracion[i-1]=Integer.parseInt(req.getParameter("caras"+i));
			}else {
				valoracion[i-1]=0;
			}
			log("valoraciones "+valoracion[i-1]);
			if(req.getParameter("comentario"+i)!=null) {
				comentarios[i-1]= req.getParameter("comentario"+i);
			}else {
				comentarios[i-1]="0";
			}
			log("comentarios "+comentarios[i-1]);
		}
		if(ValoracionDAOImplementation.getInstance().readAllValoracion().size()==0) {
			idValoracion=1;
		}else {
			idValoracion= ValoracionDAOImplementation.getInstance().readAllValoracion().size()+1;
		}
		String emailA= "";
		String comentA="";
		int valorA=0;
		if(req.getParameter("emailAnfitrion")!=null) {
			emailA= (String)req.getParameter("emailAnfitrion");
			comentA= (String) req.getParameter("comentarioAnfitrion");
			valorA = Integer.parseInt(req.getParameter("carasAnfi"));
			valoracionFinal.setComentario(comentA);
			valoracionFinal.setConvite(idConvite);
			valoracionFinal.setIdValoracion(idValoracion);
			valoracionFinal.setPuntuacion(valorA);
			valoracionFinal.setUsuarioValorado(emailA);
			valoracionFinal.setUsuarioValorador(email);
			idValoracion++;
		}
		for(int i=0;i<invitados.length;i++) {
			if(!invitados[i].equals("0") && valoracion[i]!=0 && !comentarios[i].equals("0")) {
				valoracionFinal.setComentario(comentarios[i]);
				valoracionFinal.setConvite(idConvite);
				valoracionFinal.setIdValoracion(idValoracion);
				valoracionFinal.setPuntuacion(valoracion[i]);
				valoracionFinal.setUsuarioValorado(invitados[i]);
				valoracionFinal.setUsuarioValorador(email);
				ValoracionDAOImplementation.getInstance().createValoracion(valoracionFinal);
				idValoracion++;
			}
		}
		resp.sendRedirect(req.getContextPath()+"/Convite.jsp");
	}
}
