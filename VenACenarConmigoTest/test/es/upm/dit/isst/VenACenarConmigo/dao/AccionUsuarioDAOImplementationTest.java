package es.upm.dit.isst.VenACenarConmigo.dao;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.util.List;
import es.upm.dit.isst.VenACenarConmigo.dao.model.AccionUsuario;

public class AccionUsuarioDAOImplementationTest {
	
	@Before
	public void setUp() throws Exception {
		AccionUsuarioDAO dao = AccionUsuarioDAOImplementation.getInstance();
		AccionUsuario seguimiento = new AccionUsuario();
		seguimiento.setIdAccion(1);
		seguimiento.setSeguimientoBloqueoDenuncia(1);
		seguimiento.setUsuarioEmisor("usuario11");
		seguimiento.setUsuarioReceptor("usuario12");
		dao.createAccionUsuario(seguimiento);
		
		AccionUsuario bloqueo = new AccionUsuario();
		bloqueo.setIdAccion(2);
		bloqueo.setSeguimientoBloqueoDenuncia(2);
		bloqueo.setUsuarioEmisor("usuario21");
		bloqueo.setUsuarioReceptor("usuario22");
		dao.createAccionUsuario(bloqueo);
		
		AccionUsuario denuncia = new AccionUsuario();
		denuncia.setIdAccion(3);
		denuncia.setSeguimientoBloqueoDenuncia(3);
		denuncia.setUsuarioEmisor("usuario31");
		denuncia.setUsuarioReceptor("usuario32");
		denuncia.setComentario("comentario");
		dao.createAccionUsuario(denuncia);
		
		AccionUsuario accion0 = new AccionUsuario();
		accion0.setIdAccion(4);
		accion0.setSeguimientoBloqueoDenuncia(0);
		accion0.setUsuarioEmisor("usuario01");
		accion0.setUsuarioReceptor("usuario02");
		dao.createAccionUsuario(accion0);
	}

	@Test
	public void testBuscarSeguimiento() {
		AccionUsuarioDAO dao = AccionUsuarioDAOImplementation.getInstance();
		AccionUsuario seguimiento1 = dao.readAccionUsuario(1);
		AccionUsuario seguimiento2 = dao.buscarSeguimiento("usuario11","usuario12");
		
		assertEquals(seguimiento1.getIdAccion(), seguimiento2.getIdAccion());
		assertEquals(seguimiento1.getSeguimientoBloqueoDenuncia(), seguimiento2.getSeguimientoBloqueoDenuncia());
		assertEquals(seguimiento1.getUsuarioEmisor(), seguimiento2.getUsuarioEmisor());
		assertEquals(seguimiento1.getUsuarioReceptor(), seguimiento2.getUsuarioReceptor());
		
		AccionUsuario seguimiento3 = dao.buscarSeguimiento("usuario21", "usuario22");
		assertNull(seguimiento3);
	}

	@Test
	public void testBuscarBloqueo() {
		AccionUsuarioDAO dao = AccionUsuarioDAOImplementation.getInstance();
		AccionUsuario bloqueo1 = dao.readAccionUsuario(2);
		AccionUsuario bloqueo2 = dao.buscarBloqueo("usuario21", "usuario22");
		
		assertEquals(bloqueo1.getIdAccion(), bloqueo2.getIdAccion());
		assertEquals(bloqueo1.getSeguimientoBloqueoDenuncia(), bloqueo2.getSeguimientoBloqueoDenuncia());
		assertEquals(bloqueo1.getUsuarioEmisor(), bloqueo2.getUsuarioEmisor());
		assertEquals(bloqueo1.getUsuarioReceptor(), bloqueo2.getUsuarioReceptor());
		
		AccionUsuario bloqueo3 = dao.buscarBloqueo("usuario11", "usuario12");
		assertNull(bloqueo3);
	}

	@Test
	public void testReadAllAccionUsuario() {
		AccionUsuarioDAO dao = AccionUsuarioDAOImplementation.getInstance();
		List<AccionUsuario> listaAcciones = dao.readAllAccionUsuario();
		
		AccionUsuario seguimiento = dao.readAccionUsuario(1);
		AccionUsuario accion1 = listaAcciones.get(0);
		assertEquals(seguimiento.getIdAccion(), accion1.getIdAccion());
		assertEquals(seguimiento.getSeguimientoBloqueoDenuncia(), accion1.getSeguimientoBloqueoDenuncia());
		assertEquals(seguimiento.getUsuarioEmisor(), accion1.getUsuarioEmisor());
		assertEquals(seguimiento.getUsuarioReceptor(), accion1.getUsuarioReceptor());
		
		AccionUsuario bloqueo = dao.readAccionUsuario(2);
		AccionUsuario accion2 = listaAcciones.get(1);
		assertEquals(bloqueo.getIdAccion(), accion2.getIdAccion());
		assertEquals(bloqueo.getSeguimientoBloqueoDenuncia(), accion2.getSeguimientoBloqueoDenuncia());
		assertEquals(bloqueo.getUsuarioEmisor(), accion2.getUsuarioEmisor());
		assertEquals(bloqueo.getUsuarioReceptor(), accion2.getUsuarioReceptor());
		
		AccionUsuario denuncia = dao.readAccionUsuario(3);
		AccionUsuario accion3 = listaAcciones.get(2);
		assertEquals(denuncia.getIdAccion(), accion3.getIdAccion());
		assertEquals(denuncia.getSeguimientoBloqueoDenuncia(), accion3.getSeguimientoBloqueoDenuncia());
		assertEquals(denuncia.getUsuarioEmisor(), accion3.getUsuarioEmisor());
		assertEquals(denuncia.getUsuarioReceptor(), accion3.getUsuarioReceptor());
		
		AccionUsuario accion0 = dao.readAccionUsuario(4);
		AccionUsuario accion4 = listaAcciones.get(3);
		assertEquals(accion0.getIdAccion(), accion4.getIdAccion());
		assertEquals(accion0.getSeguimientoBloqueoDenuncia(), accion4.getSeguimientoBloqueoDenuncia());
		assertEquals(accion0.getUsuarioEmisor(), accion4.getUsuarioEmisor());
		assertEquals(accion0.getUsuarioReceptor(), accion4.getUsuarioReceptor());
	}

	@Test
	public void testCreateAccionUsuario() {
		AccionUsuarioDAO dao = AccionUsuarioDAOImplementation.getInstance();
		AccionUsuario accion1 = new AccionUsuario();
		accion1.setIdAccion(5);
		accion1.setSeguimientoBloqueoDenuncia(3);
		accion1.setUsuarioEmisor("usuario51");
		accion1.setUsuarioReceptor("usuario52");
		accion1.setComentario("comentario2");
		dao.createAccionUsuario(accion1);
		
		AccionUsuario accion2 = dao.readAccionUsuario(5);
		assertEquals(accion1.getIdAccion(), accion2.getIdAccion());
		assertEquals(accion1.getSeguimientoBloqueoDenuncia(), accion2.getSeguimientoBloqueoDenuncia());
		assertEquals(accion1.getUsuarioEmisor(), accion2.getUsuarioEmisor());
		assertEquals(accion1.getUsuarioReceptor(), accion2.getUsuarioReceptor());
		assertEquals(accion1.getComentario(), accion2.getComentario());
	}

	@Test
	public void testReadAccionUsuario() {
		AccionUsuario accion = AccionUsuarioDAOImplementation.getInstance().readAccionUsuario(0);
		assertNull(accion);
	}

	@Test
	public void testUpdateAccionUsuario() {
		AccionUsuarioDAO dao = AccionUsuarioDAOImplementation.getInstance();
		AccionUsuario seguimiento = dao.readAccionUsuario(1);
		seguimiento.setSeguimientoBloqueoDenuncia(0);
		
		dao.updateAccionUsuario(seguimiento);
		AccionUsuario accion =dao.readAccionUsuario(1);
		
		assertEquals(seguimiento.getSeguimientoBloqueoDenuncia(),accion.getSeguimientoBloqueoDenuncia());
	}

	@Test
	public void testDeleteAccionUsuario() {
		AccionUsuarioDAO dao = AccionUsuarioDAOImplementation.getInstance();
		AccionUsuario accion1 = dao.readAccionUsuario(5);
		
		dao.deleteAccionUsuario(accion1);
		accion1 = null;
		
		AccionUsuario accion2 = dao.readAccionUsuario(5);
		assertNull(accion2);
	}
	
	@After
	public void tearDown() throws Exception {
		AccionUsuario seguimiento = AccionUsuarioDAOImplementation.getInstance().readAccionUsuario(1);
		AccionUsuario bloqueo = AccionUsuarioDAOImplementation.getInstance().readAccionUsuario(2);
		AccionUsuario denuncia = AccionUsuarioDAOImplementation.getInstance().readAccionUsuario(3);
		AccionUsuario accion0 = AccionUsuarioDAOImplementation.getInstance().readAccionUsuario(0);
		
		AccionUsuarioDAOImplementation.getInstance().deleteAccionUsuario(seguimiento);
		AccionUsuarioDAOImplementation.getInstance().deleteAccionUsuario(bloqueo);
		AccionUsuarioDAOImplementation.getInstance().deleteAccionUsuario(denuncia);
		AccionUsuarioDAOImplementation.getInstance().deleteAccionUsuario(accion0);
		
		seguimiento = null;
		bloqueo = null;
		denuncia = null;
		accion0 = null;
	}

}