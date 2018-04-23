package es.upm.dit.isst.VenACenarConmigo.dao;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Session;
import es.upm.dit.isst.VenACenarConmigo.dao.model.AsistenciaConvite;

public class AsistenciaConviteDAOImplementation implements AsistenciaConviteDAO{
	
	private static AsistenciaConviteDAOImplementation instance = null;
	private AsistenciaConviteDAOImplementation() {}
	public static AsistenciaConviteDAOImplementation getInstance() {
		if(null==instance) {
			instance = new AsistenciaConviteDAOImplementation();
		}
		return instance;
	}
	//Metodo que permite obtener una lista con todas las filas de la tabla AsistenciaConvite
	@Override
	public List<AsistenciaConvite> readAllAsistenciaConvite() {
		// TODO Auto-generated method stub
		List<AsistenciaConvite> asistenciaConvite= new ArrayList<>();
		Session session = SessionFactoryService.get().openSession();
		try {
			session.beginTransaction();
			asistenciaConvite.addAll(
					session.createQuery("from AsistenciaConvite").list()
			);
			session.getTransaction().commit();
		} catch (Exception e) {
			
		} finally {
			session.close();
		}
		return asistenciaConvite;
	}

	@Override
	public List<AsistenciaConvite> readNotificacionesAsistenciaConvite(String email) {
		// TODO Auto-generated method stub
		List<AsistenciaConvite> asistenciaConvite= new ArrayList<>();
		Session session = SessionFactoryService.get().openSession();
		try {
			int invitacionInscripcion=1;
			boolean confirmado=false;
			session.beginTransaction();
			asistenciaConvite.addAll(
					session.createQuery("select a from AsistenciaConvite a "
							+ "where a.emailUsuarioAsistente= :email and a.invitacionInscripcion= :invitacionInscripcion and a.confirmado= :confirmado").list());
			session.getTransaction().commit();
		} catch (Exception e) {
			
		} finally {
			session.close();
		}
		return asistenciaConvite;
	}

	//Crear fila en la tabla AsistenciaConvite
	@Override
	public void createAsistenciaConvite(AsistenciaConvite asistenciaConvite) {
		// TODO Auto-generated method stub
		Session session = SessionFactoryService.get().openSession();
		try {
			session.beginTransaction();
			session.save(asistenciaConvite);
			session.getTransaction().commit();
		} catch (Exception e) {
			
		} finally {
			session.close();
		}
	}
	//Buscar fila en la tabla AsistenciaConvite a traves del email de usuario
	@Override
	public AsistenciaConvite readAsistenciaConvite(String email) {
		// TODO Auto-generated method stub
		AsistenciaConvite asistenciaConvite = null;
		Session session = SessionFactoryService.get().openSession();
		try {
			session.beginTransaction();
			asistenciaConvite = session.get(AsistenciaConvite.class, email);
			session.getTransaction().commit();
		} catch (Exception e) {
			
		} finally {
			session.close();
		}
		return asistenciaConvite;
	}

	public AsistenciaConvite readAsistenciaConvite(int id) {
		// TODO Auto-generated method stub
		AsistenciaConvite asistenciaConvite = null;
		Session session = SessionFactoryService.get().openSession();
		try {
			session.beginTransaction();
			asistenciaConvite = session.get(AsistenciaConvite.class, id);
			session.getTransaction().commit();
		} catch (Exception e) {
			
		} finally {
			session.close();
		}
		return asistenciaConvite;
	}

	//Metodo que actualiza una fila de la tabla AsistenciaConvite
	@Override
	public void updateAsistenciaConvite(AsistenciaConvite asistenciaConvite) {
		// TODO Auto-generated method stub
		Session session = SessionFactoryService.get().openSession();
		try {
			session.beginTransaction();
			session.saveOrUpdate(asistenciaConvite);
			session.getTransaction().commit();
		} catch (Exception e) {
			
		} finally {
			session.close();
		}
	}
	//Metodo que elimina una fila de la tabla AsistenciaConvite
	@Override
	public void deleteAsistenciaConvite(AsistenciaConvite asistenciaConvite) {	
		// TODO Auto-generated method stub
		Session session = SessionFactoryService.get().openSession();
		try {
			session.beginTransaction();

			session.delete(asistenciaConvite);

			session.getTransaction().commit();
		} catch (Exception e) {
			
		} finally {
			session.close();
		}
	}
}
