package es.upm.dit.isst.VenACenarConmigo.dao;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Session;

import es.upm.dit.isst.VenACenarConmigo.dao.model.Aficion;
import es.upm.dit.isst.VenACenarConmigo.dao.model.Convite;
import es.upm.dit.isst.VenACenarConmigo.dao.SessionFactoryService;

public class ConviteDAOImplementation implements ConviteDAO {
	private static ConviteDAOImplementation instance = null;
	private ConviteDAOImplementation() {}
	public static ConviteDAOImplementation getInstance() {
		if(null==instance) {
			instance = new ConviteDAOImplementation();
		}
		return instance;
	}
	//Metodo que permite obtener una lista con todas las filas de la tabla Convite
	@Override
	public List<Convite> readAllConvite() {
		// TODO Auto-generated method stub
		List<Convite> convites= new ArrayList<>();
		Session session = SessionFactoryService.get().openSession();
		try {
			session.beginTransaction();
			convites.addAll(
					session.createQuery("from Convite").list()
			);
			session.getTransaction().commit();
		} catch (Exception e) {
			
		} finally {
			session.close();
		}
		return convites;
	}
	//Crear fila en la tabla Convite
	@Override
	public void createConvite(Convite convite) {
		// TODO Auto-generated method stub
		Session session = SessionFactoryService.get().openSession();
		try {
			session.beginTransaction();
			session.save(convite);
			session.getTransaction().commit();
		} catch (Exception e) {
			
		} finally {
			session.close();
		}
	}
	//Buscar fila en la tabla convites a traves del email de usuario
	@Override
	public Convite readConvite(String email) {
		// TODO Auto-generated method stub
		Convite convite = null;
		Session session = SessionFactoryService.get().openSession();
		try {
			session.beginTransaction();
			convite = session.get(Convite.class, email);
			session.getTransaction().commit();
		} catch (Exception e) {
			
		} finally {
			session.close();
		}
		return convite;
	}
	//Metodo que actualiza una fila de la tabla Convite
	@Override
	public void updateConvite(Convite convite) {
		// TODO Auto-generated method stub
		Session session = SessionFactoryService.get().openSession();
		try {
			session.beginTransaction();
			session.saveOrUpdate(convite);
			session.getTransaction().commit();
		} catch (Exception e) {
			
		} finally {
			session.close();
		}
	}
	//Metodo que elimina una fila de la tabla Aficion
	@Override
	public void deleteConvite(Convite convite) {
		// TODO Auto-generated method stub
		Session session = SessionFactoryService.get().openSession();
		try {
			session.beginTransaction();
			session.delete(convite);
			session.getTransaction().commit();
		} catch (Exception e) {
			
		} finally {
			session.close();
		}
		
	}

}
