package es.upm.dit.isst.VenACenarConmigo.dao;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Session;
import es.upm.dit.isst.VenACenarConmigo.dao.model.AsistenciaConvite;

public class AsistenciaConviteDAOImplementation implements AsistenciaConviteDAO {

	private static AsistenciaConviteDAOImplementation instance = null;

	private AsistenciaConviteDAOImplementation() {
	}

	public static AsistenciaConviteDAOImplementation getInstance() {
		if (null == instance) {
			instance = new AsistenciaConviteDAOImplementation();
		}
		return instance;
	}

	// Metodo que permite obtener una lista con todas las filas de la tabla
	// AsistenciaConvite
	@Override
	public List<AsistenciaConvite> readAllAsistenciaConvite() {
		// TODO Auto-generated method stub
		List<AsistenciaConvite> asistenciaConvite = new ArrayList<>();
		Session session = SessionFactoryService.get().openSession();
		try {
			session.beginTransaction();
			asistenciaConvite.addAll(session.createQuery("from AsistenciaConvite").list());
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
		List<AsistenciaConvite> asistenciaConvite = new ArrayList<>();
		List<AsistenciaConvite> asistenciaConvite2 = new ArrayList<>();
		Session session = SessionFactoryService.get().openSession();
		try {
			session.beginTransaction();
			asistenciaConvite.addAll(session.createQuery("from AsistenciaConvite").list());
			for (int i = 0; i < asistenciaConvite.size(); i++) {
				if (asistenciaConvite.get(i).getInvitacionInscripcion() == 1 && asistenciaConvite.get(i).getConfirmado() == false
						&& asistenciaConvite.get(i).getEmailUsuarioAsistente() == email) {
					asistenciaConvite2.add(asistenciaConvite.get(i));
				}
			}
			session.getTransaction().commit();
		} catch (Exception e) {
			System.out.println(e);
		} finally {
			session.close();
		}
		return asistenciaConvite2;
	}

	// Crear fila en la tabla AsistenciaConvite
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

	// Buscar fila en la tabla AsistenciaConvite a traves del email de usuario
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

	// Metodo que actualiza una fila de la tabla AsistenciaConvite
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

	// Metodo que elimina una fila de la tabla AsistenciaConvite
	@Override
	public void deleteAsistenciaConvite(AsistenciaConvite asistente) {
		// TODO Auto-generated method stub
		Session session = SessionFactoryService.get().openSession();
		try {
			session.beginTransaction();
			session.delete(asistente);
			session.getTransaction().commit();
		} catch (Exception e) {

		} finally {
			session.close();
		}
	}
}
