package es.upm.dit.isst.VenACenarConmigo.dao;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Session;

import es.upm.dit.isst.VenACenarConmigo.dao.model.Notificacion;

public class NotificacionDAOImplementation implements NotificacionDAO{
	private static NotificacionDAOImplementation instance = null;
	private NotificacionDAOImplementation() {}
	public static NotificacionDAOImplementation getInstance() {
		if(null==instance) {
			instance = new NotificacionDAOImplementation();
		}
		return instance;
	}
	@Override
	public List<Notificacion> readAllNotificacion() {
		List<Notificacion> notificaciones= new ArrayList<>();
		Session session = SessionFactoryService.get().openSession();
		try {
			session.beginTransaction();
			notificaciones.addAll(
					session.createQuery("from Notificacion").list()
			);
			session.getTransaction().commit();
		} catch (Exception e) {
			
		} finally {
			session.close();
		}
		return notificaciones;
	}

	@Override
	public void createNotificacion(Notificacion notificacion) {
		// TODO Auto-generated method stub
		Session session = SessionFactoryService.get().openSession();
		try {
			session.beginTransaction();
			session.save(notificacion);
			session.getTransaction().commit();
		} catch (Exception e) {
			
		} finally {
			session.close();
		}
	}

	@Override
	public Notificacion readNotificacion(int id) {
		// TODO Auto-generated method stub
		Notificacion notificacion = null;
		Session session = SessionFactoryService.get().openSession();
		try {
			session.beginTransaction();
			notificacion = session.get(Notificacion.class, id);
			session.getTransaction().commit();
		} catch (Exception e) {
			
		} finally {
			session.close();
		}
		return notificacion;
	}

	@Override
	public void updateNotificacion(Notificacion notificacion) {
		// TODO Auto-generated method stub
		Session session = SessionFactoryService.get().openSession();
		try {
			session.beginTransaction();
			session.saveOrUpdate(notificacion);
			session.getTransaction().commit();
		} catch (Exception e) {
			
		} finally {
			session.close();
		}
	}

	@Override
	public void deleteNotificacion(Notificacion notificacion) {
		// TODO Auto-generated method stub
		Session session = SessionFactoryService.get().openSession();
		try {
			session.beginTransaction();
			session.delete(notificacion);
			session.getTransaction().commit();
		} catch (Exception e) {
			
		} finally {
			session.close();
		}
	}

}
