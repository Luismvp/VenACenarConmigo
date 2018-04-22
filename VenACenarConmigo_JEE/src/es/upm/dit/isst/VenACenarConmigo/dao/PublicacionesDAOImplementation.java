package es.upm.dit.isst.VenACenarConmigo.dao;

import org.hibernate.Session;
import java.util.ArrayList;
import java.util.List;
import es.upm.dit.isst.VenACenarConmigo.dao.model.Publicaciones;

public class PublicacionesDAOImplementation implements PublicacionesDAO{
	private static PublicacionesDAOImplementation instance = null;
	private PublicacionesDAOImplementation() {}
	public static PublicacionesDAOImplementation getInstance() {
		if(null==instance) {
			instance = new PublicacionesDAOImplementation();
		}
		return instance;
	}
	//Metodo que permite obtener una lista con todas las filas de la tabla Publicaciones
	@Override
	public List<Publicaciones> readAllPublicaciones() {
		// TODO Auto-generated method stub
		List<Publicaciones> publicaciones= new ArrayList<>();
		Session session = SessionFactoryService.get().openSession();
		try {
			session.beginTransaction();
			publicaciones.addAll(
					session.createQuery("from Publicaciones").list()
			);
			session.getTransaction().commit();
		} catch (Exception e) {
			
		} finally {
			session.close();
		}
		return publicaciones;
	}
	//Crear fila en la tabla Publicaciones
	@Override
	public void createPublicaciones(Publicaciones publicaciones) {
		// TODO Auto-generated method stub
		Session session = SessionFactoryService.get().openSession();
		try {
			session.beginTransaction();
			session.save(publicaciones);
			session.getTransaction().commit();
		} catch (Exception e) {
			
		} finally {
			session.close();
		}
	}
	//Buscar fila en la tabla Publicaciones a traves del email de usuario
	@Override
	public Publicaciones readPublicaciones(String email) {
		// TODO Auto-generated method stub
		Publicaciones publicaciones = null;
		Session session = SessionFactoryService.get().openSession();
		try {
			session.beginTransaction();
			publicaciones = session.get(Publicaciones.class, email);
			session.getTransaction().commit();
		} catch (Exception e) {
			
		} finally {
			session.close();
		}
		return publicaciones;
	}
	//Metodo que actualiza una fila de la tabla Publicaciones
	@Override
	public void updateAficion(Publicaciones publicaciones) {
		// TODO Auto-generated method stub
		Session session = SessionFactoryService.get().openSession();
		try {
			session.beginTransaction();
			session.saveOrUpdate(publicaciones);
			session.getTransaction().commit();
		} catch (Exception e) {
			
		} finally {
			session.close();
		}
	}
	//Metodo que elimina una fila de la tabla Publicaciones
	@Override
	public void deleteAficion(Publicaciones publicaciones) {
		// TODO Auto-generated method stub
		Session session = SessionFactoryService.get().openSession();
		try {
			session.beginTransaction();
			session.delete(publicaciones);
			session.getTransaction().commit();
		} catch (Exception e) {
			
		} finally {
			session.close();
		}
	}

}
