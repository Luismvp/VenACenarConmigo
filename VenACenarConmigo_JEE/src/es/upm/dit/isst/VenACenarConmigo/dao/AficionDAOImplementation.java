package es.upm.dit.isst.VenACenarConmigo.dao;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Session;

import es.upm.dit.isst.VenACenarConmigo.dao.model.Aficion;
import es.upm.dit.isst.VenACenarConmigo.dao.SessionFactoryService;


public class AficionDAOImplementation implements AficionDAO{
	
	private static AficionDAOImplementation instance = null;
	private AficionDAOImplementation() {}
	public static AficionDAOImplementation getInstance() {
		if(null==instance) {
			instance = new AficionDAOImplementation();
		}
		return instance;
	}
	//Metodo que permite obtener una lista con todas las filas de la tabla Aficion
	@Override
	public List<Aficion> readAllAficion() {
		// TODO Auto-generated method stub
		List<Aficion> aficiones= new ArrayList<>();
		Session session = SessionFactoryService.get().openSession();
		try {
			session.beginTransaction();
			aficiones.addAll(
					session.createQuery("from Aficion").list()
			);
			session.getTransaction().commit();
		} catch (Exception e) {
			
		} finally {
			session.close();
		}
		return aficiones;
	}
	//Crear fila en la tabla Aficiones
	@Override
	public void createAficion(Aficion aficion) {
		// TODO Auto-generated method stub
		Session session = SessionFactoryService.get().openSession();
		try {
			session.beginTransaction();
			session.save(aficion);
			session.getTransaction().commit();
		} catch (Exception e) {
			
		} finally {
			session.close();
		}
	}
	//Buscar fila en la tabla aficiones a traves del email de usuario
	@Override
	public Aficion readAficion(String email) {
		// TODO Auto-generated method stub
		Aficion aficion = null;
		Session session = SessionFactoryService.get().openSession();
		try {
			session.beginTransaction();
			aficion = session.get(Aficion.class, email);
			session.getTransaction().commit();
		} catch (Exception e) {
			
		} finally {
			session.close();
		}
		return aficion;
	}
	//Metodo que actualiza una fila de la tabla Aficion
	@Override
	public void updateAficion(Aficion aficion) {
		// TODO Auto-generated method stub
		Session session = SessionFactoryService.get().openSession();
		try {
			session.beginTransaction();
			session.saveOrUpdate(aficion);
			session.getTransaction().commit();
		} catch (Exception e) {
			
		} finally {
			session.close();
		}
	}
	//Metodo que elimina una fila de la tabla Aficion
	@Override
	public void deleteAficion(Aficion aficion) {
		// TODO Auto-generated method stub
		Session session = SessionFactoryService.get().openSession();
		try {
			session.beginTransaction();
			session.delete(aficion);
			session.getTransaction().commit();
		} catch (Exception e) {
			
		} finally {
			session.close();
		}
	}

}
