package es.upm.dit.isst.VenACenarConmigo.servlets;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import es.upm.dit.isst.VenACenarConmigo.dao.AsistenciaConviteDAOImplementation;
import es.upm.dit.isst.VenACenarConmigo.dao.model.AsistenciaConvite;


@WebServlet("/AceptaInvitacionServlet")

public class AceptaInvitacionServlet extends HttpServlet {
	
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
	 int idConvite = Integer.parseInt(req.getParameter("idConvite"));
	 String email = (String)req.getSession().getAttribute("email");
	 List<AsistenciaConvite> asistente = AsistenciaConviteDAOImplementation.getInstance().readAllAsistenciaConvite();
	 AsistenciaConvite asistenteConfirma = null;
	 for(int i=0;i<asistente.size();i++) {
		 if(asistente.get(i).getIdConvite() == idConvite && asistente.get(i).getEmailUsuarioAsistente() == email) {
			 asistenteConfirma = asistente.get(i);
		 }
	 }
	 asistenteConfirma.setConfirmado(true);
	 AsistenciaConviteDAOImplementation.getInstance().updateAsistenciaConvite(asistenteConfirma);
	 RequestDispatcher rd = req.getRequestDispatcher("NotificacionesServlet");
	 rd.forward(req,resp);
	 
	}
}
