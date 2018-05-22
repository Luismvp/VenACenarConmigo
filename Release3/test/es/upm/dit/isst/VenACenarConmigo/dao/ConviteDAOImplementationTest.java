package es.upm.dit.isst.VenACenarConmigo.dao;

import static org.junit.Assert.*;

import java.util.Calendar;
import java.util.List;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import es.upm.dit.isst.VenACenarConmigo.dao.model.AsistenciaConvite;
import es.upm.dit.isst.VenACenarConmigo.dao.model.Convite;

public class ConviteDAOImplementationTest {

	@Before
	public void setUp() throws Exception {
		Convite convite1 = new Convite();
		convite1.setArea("Embajadores");
		convite1.setCiudad("Madrid");
		convite1.setDescripcion("Cena de amigos");
		convite1.setEmailAnfitrion("anfitrion1@anfitrion1");
		convite1.setFecha("01/06/2018");
		convite1.setHoraComienzo("21:00");
		convite1.setHoraFin("01:00");
		convite1.setIdConvite(1);
		Calendar fechaYHoraComienzo = Calendar.getInstance();
		fechaYHoraComienzo.set(1, 6, 2018, 21, 0);
		Calendar fechaYHoraFin = Calendar.getInstance();
		fechaYHoraFin.set(1, 6, 2018, 1, 0);
		convite1.setFechaYHoraComienzo(fechaYHoraComienzo);
		convite1.setFechaYHoraFin(fechaYHoraFin);
		convite1.setMaxInvitados(2);
		convite1.setMenu("Ensalada");
		convite1.setNombre("convite1");
		convite1.setPrecioInvitado("20€");
		convite1.setRestaurante("Restaurante");
		ConviteDAOImplementation.getInstance().createConvite(convite1);
		
		Convite convite2 = new Convite();
		convite2.setArea("Moncloa");
		convite2.setCiudad("Madrid");
		convite2.setDescripcion("Comida de amigos");
		convite2.setEmailAnfitrion("anfitrion2@anfitrion2");
		convite2.setFecha("04/06/2018");
		convite2.setHoraComienzo("14:00");
		convite2.setHoraFin("17:00");
		convite2.setIdConvite(2);
		fechaYHoraComienzo.set(4, 6, 2018, 14, 0);
		fechaYHoraFin.set(4, 6, 2018, 17, 0);
		convite2.setFechaYHoraComienzo(fechaYHoraComienzo);
		convite2.setFechaYHoraFin(fechaYHoraFin);
		convite2.setMaxInvitados(2);
		convite2.setMenu("Carnes");
		convite2.setNombre("convite2");
		convite2.setPrecioInvitado("30€");
		ConviteDAOImplementation.getInstance().createConvite(convite2);
	}

	@After
	public void tearDown() throws Exception {
		Convite convite1 = ConviteDAOImplementation.getInstance().readConvite(1);
		ConviteDAOImplementation.getInstance().deleteConvite(convite1);
		Convite convite2 = ConviteDAOImplementation.getInstance().readConvite(2);
		ConviteDAOImplementation.getInstance().deleteConvite(convite2);
		convite1 = null;
		convite2 = null;
	}

	@Test
	public void testReadAllConvite() {
		ConviteDAO dao = ConviteDAOImplementation.getInstance();
		List<Convite> listaConvites = dao.readAllConvite();
		
		Convite convite11 = dao.readConvite(1);
		Convite convite12 = listaConvites.get(0);
		assertEquals(convite11.getArea(), convite12.getArea());
		assertEquals(convite11.getCiudad(),convite12.getCiudad());
		assertEquals(convite11.getClass(), convite12.getClass());
		assertEquals(convite11.getDescripcion(), convite12.getDescripcion());
		assertEquals(convite11.getEmailAnfitrion(), convite12.getEmailAnfitrion());
		assertEquals(convite11.getFecha(), convite12.getFecha());
		assertEquals(convite11.getFechaYHoraComienzo(), convite12.getFechaYHoraComienzo());
		assertEquals(convite11.getFechaYHoraFin(), convite12.getFechaYHoraFin());
		assertEquals(convite11.getHoraComienzo(), convite12.getHoraComienzo());
		assertEquals(convite11.getIdConvite(), convite12.getIdConvite());
		assertEquals(convite11.getMenu(), convite12.getMenu());
		assertEquals(convite11.getNombre(), convite12.getNombre());
		assertEquals(convite11.getPrecioInvitado(), convite12.getPrecioInvitado());
		assertEquals(convite11.getRestaurante(), convite12.getRestaurante());
		
		Convite convite21 = dao.readConvite(2);
		Convite convite22 = listaConvites.get(1);
		assertEquals(convite21.getArea(), convite22.getArea());
		assertEquals(convite21.getCiudad(),convite22.getCiudad());
		assertEquals(convite21.getClass(), convite22.getClass());
		assertEquals(convite21.getDescripcion(), convite22.getDescripcion());
		assertEquals(convite21.getEmailAnfitrion(), convite22.getEmailAnfitrion());
		assertEquals(convite21.getFecha(), convite22.getFecha());
		assertEquals(convite21.getFechaYHoraComienzo(), convite22.getFechaYHoraComienzo());
		assertEquals(convite21.getFechaYHoraFin(), convite22.getFechaYHoraFin());
		assertEquals(convite21.getHoraComienzo(), convite22.getHoraComienzo());
		assertEquals(convite21.getIdConvite(), convite22.getMaxInvitados());
		assertEquals(convite21.getMenu(), convite22.getMenu());
		assertEquals(convite21.getNombre(), convite22.getNombre());
		assertEquals(convite21.getPrecioInvitado(), convite22.getPrecioInvitado());
		assertEquals(convite21.getRestaurante(), convite22.getRestaurante());
	}

	@Test
	public void testCreateConvite() {
		Convite convite3 = new Convite();
		convite3.setArea("Embajadores");
		convite3.setCiudad("Madrid");
		convite3.setDescripcion("Cena de amigos");
		convite3.setEmailAnfitrion("anfitrion1@anfitrion1");
		convite3.setFecha("01/06/2018");
		convite3.setHoraComienzo("21:00");
		convite3.setHoraFin("01:00");
		convite3.setIdConvite(3);
		Calendar fechaYHoraComienzo = Calendar.getInstance();
		fechaYHoraComienzo.set(1, 6, 2018, 21, 0);
		Calendar fechaYHoraFin = Calendar.getInstance();
		fechaYHoraFin.set(1, 6, 2018, 1, 0);
		convite3.setFechaYHoraComienzo(fechaYHoraComienzo);
		convite3.setFechaYHoraFin(fechaYHoraFin);
		convite3.setMaxInvitados(2);
		convite3.setMenu("Ensalada");
		convite3.setNombre("convite3");
		convite3.setPrecioInvitado("20€");
		convite3.setRestaurante("Restaurante");
		ConviteDAOImplementation.getInstance().createConvite(convite3);
		
		Convite convite = ConviteDAOImplementation.getInstance().readConvite(3);
		assertEquals(convite3.getArea(), convite.getArea());
		assertEquals(convite3.getCiudad(),convite.getCiudad());
		assertEquals(convite3.getClass(), convite.getClass());
		assertEquals(convite3.getDescripcion(), convite.getDescripcion());
		assertEquals(convite3.getEmailAnfitrion(), convite.getEmailAnfitrion());
		assertEquals(convite3.getFecha(), convite.getFecha());
		assertEquals(convite3.getFechaYHoraComienzo(), convite.getFechaYHoraComienzo());
		assertEquals(convite3.getFechaYHoraFin(), convite.getFechaYHoraFin());
		assertEquals(convite3.getHoraComienzo(), convite.getHoraComienzo());
		assertEquals(convite3.getIdConvite(), convite.getIdConvite());
		assertEquals(convite3.getMenu(), convite.getMenu());
		assertEquals(convite3.getNombre(), convite.getNombre());
		assertEquals(convite3.getPrecioInvitado(), convite.getPrecioInvitado());
		assertEquals(convite3.getRestaurante(), convite.getRestaurante());
		
	}

	@Test
	public void testReadConviteString() {
		Convite convite1 = ConviteDAOImplementation.getInstance().readConvite(1);
		Convite convite2 = ConviteDAOImplementation.getInstance().readConvite("anfitrion1@anfitrion1");
		
		assertEquals(convite1.getArea(), convite2.getArea());
		assertEquals(convite1.getCiudad(),convite2.getCiudad());
		assertEquals(convite1.getClass(), convite2.getClass());
		assertEquals(convite1.getDescripcion(), convite2.getDescripcion());
		assertEquals(convite1.getEmailAnfitrion(), convite2.getEmailAnfitrion());
		assertEquals(convite1.getFecha(), convite2.getFecha());
		assertEquals(convite1.getFechaYHoraComienzo(), convite2.getFechaYHoraComienzo());
		assertEquals(convite1.getFechaYHoraFin(), convite2.getFechaYHoraFin());
		assertEquals(convite1.getHoraComienzo(), convite2.getHoraFin());
		assertEquals(convite1.getIdConvite(), convite2.getMaxInvitados());
		assertEquals(convite1.getMenu(), convite2.getMenu());
		assertEquals(convite1.getNombre(), convite2.getNombre());
		assertEquals(convite1.getPrecioInvitado(), convite2.getPrecioInvitado());
		assertEquals(convite1.getRestaurante(), convite2.getRestaurante());
	}
	
	@Test
	public void testReadConviteInt() {
		Convite convite = ConviteDAOImplementation.getInstance().readConvite(0);
		assertNull(convite);
	}

	@Test
	public void testUpdateConvite() {
		ConviteDAO dao = ConviteDAOImplementation.getInstance();
		Convite convite1 = dao.readConvite(1);
		convite1.setHoraComienzo("22:00");
		
		dao.updateConvite(convite1);
		Convite convite2 =dao.readConvite(1);
		
		assertEquals(convite1.getHoraComienzo(),convite2.getHoraComienzo());
	}

	@Test
	public void testDeleteConvite() {
		ConviteDAO dao = ConviteDAOImplementation.getInstance();
		Convite convite1 = dao.readConvite(3);
		
		dao.deleteConvite(convite1);
		convite1 = null;
		
		Convite convite2 = dao.readConvite(3);
		assertNull(convite2);
	}

}
