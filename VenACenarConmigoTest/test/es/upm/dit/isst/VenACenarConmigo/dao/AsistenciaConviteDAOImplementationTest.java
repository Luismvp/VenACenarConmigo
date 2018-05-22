package es.upm.dit.isst.VenACenarConmigo.dao;

import static org.junit.Assert.*;

import java.util.Calendar;
import java.util.List;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import es.upm.dit.isst.VenACenarConmigo.dao.model.AccionUsuario;
import es.upm.dit.isst.VenACenarConmigo.dao.model.AsistenciaConvite;
import es.upm.dit.isst.VenACenarConmigo.dao.model.Convite;

public class AsistenciaConviteDAOImplementationTest {

	@Before
	public void setUp() throws Exception {
		AsistenciaConviteDAO dao = AsistenciaConviteDAOImplementation.getInstance();
		
		Convite convite = new Convite();
		convite.setArea("Embajadores");
		convite.setCiudad("Madrid");
		convite.setDescripcion("Cena de amigos");
		convite.setEmailAnfitrion("anfitrion@anfitrion");
		convite.setFecha("01/06/2018");
		convite.setHoraComienzo("21:00");
		convite.setHoraFin("01:00");
		convite.setIdConvite(1);
		Calendar fechaYHoraComienzo = Calendar.getInstance();
		fechaYHoraComienzo.set(1, 6, 2018, 21, 0);
		Calendar fechaYHoraFin = Calendar.getInstance();
		fechaYHoraFin.set(1, 6, 2018, 1, 0);
		convite.setFechaYHoraComienzo(fechaYHoraComienzo);
		convite.setFechaYHoraFin(fechaYHoraFin);
		convite.setMaxInvitados(2);
		convite.setMenu("Ensalada");
		convite.setNombre("convite1");
		convite.setPrecioInvitado("20€");
		convite.setRestaurante("Restaurante");
		ConviteDAOImplementation.getInstance().createConvite(convite);
		
		AsistenciaConvite invitacion = new AsistenciaConvite();
		invitacion.setIdAsistente(1);
		invitacion.setInvitacionInscripcion(1);
		invitacion.setIdConvite(1);
		invitacion.setEmailUsuarioAsistente("asistente1@asistente1");
		invitacion.setConfirmado(false);
		invitacion.setNumeroInvitado(1);
		dao.createAsistenciaConvite(invitacion);
		
		AsistenciaConvite inscripcion = new AsistenciaConvite();
		inscripcion.setIdAsistente(2);
		inscripcion.setInvitacionInscripcion(2);
		inscripcion.setIdConvite(1);
		inscripcion.setEmailUsuarioAsistente("asistente2@asistente2");
		invitacion.setConfirmado(false);
		invitacion.setNumeroInvitado(2);
		dao.createAsistenciaConvite(inscripcion);
	}

	@After
	public void tearDown() throws Exception {
		AsistenciaConviteDAO dao = AsistenciaConviteDAOImplementation.getInstance();
		
		AsistenciaConvite invitacion = dao.readAsistenciaConvite(1);
		AsistenciaConvite inscripcion = dao.readAsistenciaConvite(2);
		dao.deleteAsistenciaConvite(invitacion);
		dao.deleteAsistenciaConvite(inscripcion);
		
		Convite convite = ConviteDAOImplementation.getInstance().readConvite(1);
		ConviteDAOImplementation.getInstance().deleteConvite(convite);
		
		invitacion = null;
		inscripcion = null;
		convite = null;
	}

	@Test
	public void testReadAllAsistenciaConvite() {
		AsistenciaConviteDAO dao = AsistenciaConviteDAOImplementation.getInstance();
		List<AsistenciaConvite> listaAsistencias = dao.readAllAsistenciaConvite();
		
		AsistenciaConvite invitacion = dao.readAsistenciaConvite(1);
		AsistenciaConvite asistencia1 = listaAsistencias.get(0);
		assertEquals(invitacion.getConfirmado(), asistencia1.getConfirmado());
		assertEquals(invitacion.getEmailAnfitrion(), asistencia1.getEmailAnfitrion());
		assertEquals(invitacion.getEmailUsuarioAsistente(), asistencia1.getEmailUsuarioAsistente());
		assertEquals(invitacion.getIdAsistente(), asistencia1.getIdAsistente());
		assertEquals(invitacion.getIdConvite(), asistencia1.getIdConvite());
		assertEquals(invitacion.getInvitacionInscripcion(), asistencia1.getInvitacionInscripcion());
		assertEquals(invitacion.getNumeroInvitado(), asistencia1.getNumeroInvitado());
		
		AsistenciaConvite inscripcion = dao.readAsistenciaConvite(2);
		AsistenciaConvite asistencia2 = listaAsistencias.get(1);
		assertEquals(inscripcion.getConfirmado(), asistencia2.getConfirmado());
		assertEquals(inscripcion.getEmailAnfitrion(), asistencia2.getEmailAnfitrion());
		assertEquals(inscripcion.getEmailUsuarioAsistente(), asistencia2.getEmailUsuarioAsistente());
		assertEquals(inscripcion.getIdAsistente(), asistencia2.getIdAsistente());
		assertEquals(inscripcion.getIdConvite(), asistencia2.getIdConvite());
		assertEquals(inscripcion.getInvitacionInscripcion(), asistencia2.getInvitacionInscripcion());
		assertEquals(inscripcion.getNumeroInvitado(), asistencia2.getNumeroInvitado());
	}

	@Test
	public void testCreateAsistenciaConvite() {
		AsistenciaConviteDAO dao = AsistenciaConviteDAOImplementation.getInstance();
		AsistenciaConvite asistencia1 = new AsistenciaConvite();
		asistencia1.setIdAsistente(3);
		asistencia1.setInvitacionInscripcion(1);
		asistencia1.setIdConvite(1);
		asistencia1.setEmailUsuarioAsistente("asistente3@asistente3");
		asistencia1.setConfirmado(false);
		asistencia1.setNumeroInvitado(3);
		dao.createAsistenciaConvite(asistencia1);
		
		AsistenciaConvite asistencia2 = dao.readAsistenciaConvite(3);
		assertEquals(asistencia1.getConfirmado(), asistencia2.getConfirmado());
		assertEquals(asistencia1.getEmailAnfitrion(), asistencia2.getEmailAnfitrion());
		assertEquals(asistencia1.getEmailUsuarioAsistente(), asistencia2.getEmailUsuarioAsistente());
		assertEquals(asistencia1.getIdAsistente(), asistencia2.getIdAsistente());
		assertEquals(asistencia1.getIdConvite(), asistencia2.getIdConvite());
		assertEquals(asistencia1.getInvitacionInscripcion(), asistencia2.getInvitacionInscripcion());
		assertEquals(asistencia1.getNumeroInvitado(), asistencia2.getNumeroInvitado());
	}

	@Test
	public void testReadAsistenciaConviteString() {
		AsistenciaConvite asistencia1 = AsistenciaConviteDAOImplementation.getInstance().readAsistenciaConvite(1);
		AsistenciaConvite asistencia2 = AsistenciaConviteDAOImplementation.getInstance().readAsistenciaConvite("asistente1@asistente1");
		
		assertEquals(asistencia1.getConfirmado(), asistencia2.getConfirmado());
		assertEquals(asistencia1.getEmailAnfitrion(), asistencia2.getEmailAnfitrion());
		assertEquals(asistencia1.getEmailUsuarioAsistente(), asistencia2.getEmailUsuarioAsistente());
		assertEquals(asistencia1.getIdAsistente(), asistencia2.getIdAsistente());
		assertEquals(asistencia1.getIdConvite(), asistencia2.getIdConvite());
		assertEquals(asistencia1.getInvitacionInscripcion(), asistencia2.getInvitacionInscripcion());
		assertEquals(asistencia1.getNumeroInvitado(), asistencia2.getNumeroInvitado());
	}

	@Test
	public void testReadAsistenciaConviteInt() {
		AsistenciaConvite asistencia = AsistenciaConviteDAOImplementation.getInstance().readAsistenciaConvite(0);
		assertNull(asistencia);
	}

	@Test
	public void testUpdateAsistenciaConvite() {
		AsistenciaConviteDAO dao = AsistenciaConviteDAOImplementation.getInstance();
		AsistenciaConvite invitacion = dao.readAsistenciaConvite(1);
		invitacion.setConfirmado(true);
		
		dao.updateAsistenciaConvite(invitacion);
		AsistenciaConvite asistencia =dao.readAsistenciaConvite(1);
		
		assertEquals(invitacion.getConfirmado(),asistencia.getConfirmado());
	}

	@Test
	public void testDeleteAsistenciaConvite() {
		AsistenciaConviteDAO dao = AsistenciaConviteDAOImplementation.getInstance();
		AsistenciaConvite asistencia1 = dao.readAsistenciaConvite(3);
		
		dao.deleteAsistenciaConvite(asistencia1);
		asistencia1 = null;
		
		AsistenciaConvite asistencia2 = dao.readAsistenciaConvite(3);
		assertNull(asistencia2);
	}

}
