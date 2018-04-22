package es.upm.dit.isst.VenACenarConmigo.dao;

import java.util.ArrayList;
import java.util.List;
import org.hibernate.Session;
import es.upm.dit.isst.VenACenarConmigo.dao.model.AccionUsuario;

public class AccionUsuarioDAOImplementation implements AccionUsuarioDAO{
	private static AccionUsuarioDAOImplementation instance = null;
	private AccionUsuarioDAOImplementation() {}
	public static AccionUsuarioDAOImplementation getInstance() {
		if(null==instance) {
			instance = new AccionUsuarioDAOImplementation();
		}
		return instance;
	}
	//Metodo que permite obtener una lista con todas las filas de la tabla AccionUsuario
	@Override
	public List<AccionUsuario> readAllAccionUsuario() {
		// TODO Auto-generated method stub
		List<AccionUsuario> accionUsuario= new ArrayList<>();
		Session session = SessionFactoryService.get().openSession();
		try {
			session.beginTransaction();
			accionUsuario.addAll(
					session.createQuery("from AccionUsuario").list()
			);
			session.getTransaction().commit();
		} catch (Exception e) {
			
		} finally {
			session.close();
		}
		return accionUsuario;
	}
	//Crear fila en la tabla AccionUsuario
	@Override
	public void createAccionUsuario(AccionUsuario accionUsuario) {
		// TODO Auto-generated method stub
		Session session = SessionFactoryService.get().openSession();
		try {
			session.beginTransaction();
			session.save(accionUsuario);
			session.getTransaction().commit();
		} catch (Exception e) {
			
		} finally {
			session.close();
		}
	}
	//Buscar fila en la tabla AccionUsuario a traves del email de usuario
	@Override
	public AccionUsuario readAccionUsuario(String email) {
		// TODO Auto-generated method stub
		AccionUsuario accionUsuario = null;
		Session session = SessionFactoryService.get().openSession();
		try {
			session.beginTransaction();
			accionUsuario = session.get(AccionUsuario.class, email);
			session.getTransaction().commit();
		} catch (Exception e) {
			
		} finally {
			session.close();
		}
		return accionUsuario; 
	}
	//Metodo que actualiza una fila de la tabla AccionUsuario
	@Override
	public void updateAccionUsuario(AccionUsuario accionUsuario) {
		// TODO Auto-generated method stub
		Session session = SessionFactoryService.get().openSession();
		try {
			session.beginTransaction();
			session.saveOrUpdate(accionUsuario);
			session.getTransaction().commit();
		} catch (Exception e) {
			
		} finally {
			session.close();
		}
	}
	//Metodo que elimina una fila de la tabla AccionUsuario
	@Override
	public void deleteAccionUsuario(AccionUsuario accionUsuario) {
		// TODO Auto-generated method stub
		Session session = SessionFactoryService.get().openSession();
		try {
			session.beginTransaction();
			session.delete(accionUsuario);
			session.getTransaction().commit();
		} catch (Exception e) {
			
		} finally {
			session.close();
		}
	}

}
