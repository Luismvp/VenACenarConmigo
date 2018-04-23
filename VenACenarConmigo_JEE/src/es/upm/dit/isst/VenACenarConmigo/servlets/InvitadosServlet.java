package es.upm.dit.isst.VenACenarConmigo.servlets;
import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import es.upm.dit.isst.VenACenarConmigo.dao.AsistenciaConviteDAOImplementation;
import es.upm.dit.isst.VenACenarConmigo.dao.model.AsistenciaConvite;

@WebServlet("/InvitadosServlet")
public class InvitadosServlet extends HttpServlet{
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String emailInv1= req.getParameter("inv1");
		String emailInv2= req.getParameter("inv2");
		String emailInv3= req.getParameter("inv3");
		String emailInv4= req.getParameter("inv4");
		String emailInv5= req.getParameter("inv5");
		String emailInv6= req.getParameter("inv6");
		String emailInv7= req.getParameter("inv7");
		String emailInv8= req.getParameter("inv8");
		String emailInv9= req.getParameter("inv9");
		String emailInv10= req.getParameter("inv10");
		String emailInv11= req.getParameter("inv11");
		String emailInv12= req.getParameter("inv12");
		String emailInv13= req.getParameter("inv13");
		String emailInv14= req.getParameter("inv14");
		String emailInv15= req.getParameter("inv15");
		String[] emailInvitados = {emailInv1, emailInv2, emailInv3,emailInv4,emailInv5,emailInv6,emailInv7,emailInv8,emailInv9,emailInv10,emailInv11,emailInv12,emailInv13,emailInv14,emailInv15};
		for(int i=0;i<emailInvitados.length;i++) {
			if(null==emailInvitados[i]) {
				emailInvitados[i]="";
			}
		}
		String emailAnfitrion = (String) req.getSession().getAttribute("emailAnfitrion");
		int idConvite = (int)req.getSession().getAttribute("idConvite");
		int idAsistente = AsistenciaConviteDAOImplementation.getInstance().readAllAsistenciaConvite().size() + 1;
		AsistenciaConvite asistente = new AsistenciaConvite();
		AsistenciaConviteDAOImplementation dao = AsistenciaConviteDAOImplementation.getInstance();
		if( !emailInvitados[0].isEmpty()) {
			asistente.setEmailAnfitrion(emailAnfitrion);
			asistente.setEmailUsuarioAsistente(emailInv1);
			asistente.setInvitacionInscripcion(1);
			asistente.setNumeroInvitado(1);
			asistente.setIdConvite(idConvite);
			asistente.setIdAsistencia(idAsistente);
			asistente.setConfirmado(false);
			dao.createAsistenciaConvite(asistente);
		}
		if(!emailInvitados[1].isEmpty()) {
			idAsistente++;
			asistente.setEmailAnfitrion(emailAnfitrion);
			asistente.setEmailUsuarioAsistente(emailInv2);
			asistente.setInvitacionInscripcion(1);
			asistente.setNumeroInvitado(2);
			asistente.setIdAsistencia(idAsistente);
			asistente.setIdConvite(idConvite);
			asistente.setConfirmado(false);
			dao.createAsistenciaConvite(asistente);
		}
		if(!emailInvitados[2].isEmpty()) {
			idAsistente++;
			asistente.setIdConvite(idConvite);
			asistente.setIdAsistencia(idAsistente);
			asistente.setEmailAnfitrion(emailAnfitrion);
			asistente.setEmailUsuarioAsistente(emailInv3);
			asistente.setInvitacionInscripcion(1);
			asistente.setNumeroInvitado(3);
			asistente.setConfirmado(false);
			dao.createAsistenciaConvite(asistente);
		}
		if(!emailInvitados[3].isEmpty()) {
			idAsistente = + 1;
			asistente.setIdConvite(idConvite);
			asistente.setIdAsistencia(idAsistente);
			asistente.setEmailAnfitrion(emailAnfitrion);
			asistente.setEmailUsuarioAsistente(emailInv4);
			asistente.setInvitacionInscripcion(1);
			asistente.setIdConvite(idConvite);
			asistente.setNumeroInvitado(4);
			asistente.setConfirmado(false);
			dao.createAsistenciaConvite(asistente);
		}
		if(!emailInvitados[4].isEmpty()) {
			idAsistente++;
			asistente.setIdConvite(idConvite);
			asistente.setIdAsistencia(idAsistente);
			asistente.setEmailAnfitrion(emailAnfitrion);
			asistente.setEmailUsuarioAsistente(emailInv5);
			asistente.setInvitacionInscripcion(1);
			asistente.setNumeroInvitado(5);
			asistente.setConfirmado(false);
			dao.createAsistenciaConvite(asistente);
		}
		if(!emailInvitados[5].isEmpty()) {
			idAsistente++;
			asistente.setIdConvite(idConvite);
			asistente.setIdAsistencia(idAsistente);
			asistente.setEmailAnfitrion(emailAnfitrion);
			asistente.setEmailUsuarioAsistente(emailInv6);
			asistente.setInvitacionInscripcion(1);
			asistente.setNumeroInvitado(6);
			asistente.setConfirmado(false);
			dao.createAsistenciaConvite(asistente);
		}
		if(!emailInvitados[6].isEmpty()) {
			idAsistente++;
			asistente.setIdConvite(idConvite);
			asistente.setIdAsistencia(idAsistente);
			asistente.setEmailAnfitrion(emailAnfitrion);
			asistente.setEmailUsuarioAsistente(emailInv7);
			asistente.setInvitacionInscripcion(1);
			asistente.setNumeroInvitado(7);
			asistente.setConfirmado(false);
			dao.createAsistenciaConvite(asistente);
		}
		if(!emailInvitados[7].isEmpty()) {
			idAsistente++;
			asistente.setIdConvite(idConvite);
			asistente.setIdAsistencia(idAsistente);
			asistente.setEmailAnfitrion(emailAnfitrion);
			asistente.setEmailUsuarioAsistente(emailInv8);
			asistente.setInvitacionInscripcion(1);
			asistente.setNumeroInvitado(8);
			asistente.setConfirmado(false);
			dao.createAsistenciaConvite(asistente);
		}
		if(!emailInvitados[8].isEmpty()) {
			idAsistente++;
			asistente.setIdConvite(idConvite);
			asistente.setIdAsistencia(idAsistente);
			asistente.setEmailAnfitrion(emailAnfitrion);
			asistente.setEmailUsuarioAsistente(emailInv9);
			asistente.setInvitacionInscripcion(1);
			asistente.setNumeroInvitado(9);
			asistente.setConfirmado(false);
			dao.createAsistenciaConvite(asistente);
		}
		if(!emailInvitados[9].isEmpty()) {
			idAsistente++;
			asistente.setIdConvite(idConvite);
			asistente.setIdAsistencia(idAsistente);
			asistente.setEmailAnfitrion(emailAnfitrion);
			asistente.setEmailUsuarioAsistente(emailInv10);
			asistente.setInvitacionInscripcion(1);
			asistente.setNumeroInvitado(10);
			asistente.setConfirmado(false);
			dao.createAsistenciaConvite(asistente);
		}
		if(!emailInvitados[10].isEmpty()) {
			idAsistente++;
			asistente.setIdConvite(idConvite);
			asistente.setIdAsistencia(idAsistente);
			asistente.setEmailAnfitrion(emailAnfitrion);
			asistente.setEmailUsuarioAsistente(emailInv11);
			asistente.setInvitacionInscripcion(1);
			asistente.setNumeroInvitado(11);
			asistente.setConfirmado(false);
			dao.createAsistenciaConvite(asistente);
		}
		if(!emailInvitados[11].isEmpty()) {
			idAsistente++;
			asistente.setIdConvite(idConvite);
			asistente.setIdAsistencia(idAsistente);
			asistente.setEmailAnfitrion(emailAnfitrion);
			asistente.setEmailUsuarioAsistente(emailInv12);
			asistente.setInvitacionInscripcion(1);
			asistente.setNumeroInvitado(12);
			asistente.setConfirmado(false);
			dao.createAsistenciaConvite(asistente);
		}
		if(!emailInvitados[12].isEmpty()) {
			idAsistente++;
			asistente.setIdConvite(idConvite);
			asistente.setIdAsistencia(idAsistente);
			asistente.setEmailAnfitrion(emailAnfitrion);
			asistente.setEmailUsuarioAsistente(emailInv13);
			asistente.setInvitacionInscripcion(1);
			asistente.setNumeroInvitado(13);
			asistente.setConfirmado(false);
			dao.createAsistenciaConvite(asistente);
		}
		if(!emailInvitados[13].isEmpty()) {
			idAsistente++;
			asistente.setIdConvite(idConvite);
			asistente.setIdAsistencia(idAsistente);
			asistente.setEmailAnfitrion(emailAnfitrion);
			asistente.setEmailUsuarioAsistente(emailInv14);
			asistente.setInvitacionInscripcion(1);
			asistente.setNumeroInvitado(14);
			asistente.setConfirmado(false);
			dao.createAsistenciaConvite(asistente);
		}
		if(!emailInvitados[14].isEmpty()) {
			idAsistente++;
			asistente.setIdConvite(idConvite);
			asistente.setIdAsistencia(idAsistente);
			asistente.setEmailAnfitrion(emailAnfitrion);
			asistente.setEmailUsuarioAsistente(emailInv15);
			asistente.setInvitacionInscripcion(1);
			asistente.setNumeroInvitado(15);
			asistente.setConfirmado(false);
			dao.createAsistenciaConvite(asistente);
		}
		resp.sendRedirect(req.getContextPath() + "/Perfil.jsp");
	}
}
