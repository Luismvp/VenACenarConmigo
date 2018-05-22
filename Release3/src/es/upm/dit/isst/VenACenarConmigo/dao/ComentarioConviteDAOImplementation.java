package es.upm.dit.isst.VenACenarConmigo.dao;
import org.hibernate.Session;
import java.util.ArrayList;
import java.util.List;
import es.upm.dit.isst.VenACenarConmigo.dao.model.ComentarioConvite;

public class ComentarioConviteDAOImplementation implements ComentarioConviteDAO {
	private static ComentarioConviteDAOImplementation instance = null;
	private ComentarioConviteDAOImplementation() {}
	public static ComentarioConviteDAOImplementation getInstance() {
		if(null==instance) {
			instance = new ComentarioConviteDAOImplementation();
		}
		return instance;
	}
	//Metodo que permite obtener una lista con todas las filas de la tabla ComentarioConvite
	@Override
	public List<ComentarioConvite> readAllComentarioConvite() {
		// TODO Auto-generated method stub
		List<ComentarioConvite> comentarioConvite= new ArrayList<>();
		Session session = SessionFactoryService.get().openSession();
		try {
			session.beginTransaction();
			comentarioConvite.addAll(
					session.createQuery("from ComentarioConvite").list()
			);
			session.getTransaction().commit();
		} catch (Exception e) {
			
		} finally {
			session.close();
		}
		return comentarioConvite;
	}
	//Crear fila en la tabla ComentarioConvite
	@Override
	public void createComentarioConvite(ComentarioConvite comentarioConvite) {
		// TODO Auto-generated method stub
		Session session = SessionFactoryService.get().openSession();
		try {
			session.beginTransaction();
			session.save(comentarioConvite);
			session.getTransaction().commit();
		} catch (Exception e) {
			
		} finally {
			session.close();
		}
	}
	//Buscar fila en la tabla ComentarioConvite a traves del id del convite
	@Override
	public ComentarioConvite readComentarioConvite(String idConvite) {
		// TODO Auto-generated method stub
		ComentarioConvite comentarioConvite = null;
		Session session = SessionFactoryService.get().openSession();
		try {
			session.beginTransaction();
			comentarioConvite = session.get(ComentarioConvite.class, idConvite);
			session.getTransaction().commit();
		} catch (Exception e) {
			
		} finally {
			session.close();
		}
		return comentarioConvite;
	}
	//Metodo que actualiza una fila de la tabla ComentarioConvite
	@Override
	public void updateComentarioConvite(ComentarioConvite comentarioConvite) {
		// TODO Auto-generated method stub
		Session session = SessionFactoryService.get().openSession();
		try {
			session.beginTransaction();
			session.saveOrUpdate(comentarioConvite);
			session.getTransaction().commit();
		} catch (Exception e) {
			
		} finally {
			session.close();
		}
	}
	//Metodo que elimina una fila de la tabla ComentarioConvite
	@Override
	public void deleteComentarioConvite(ComentarioConvite comentarioConvite) {
		// TODO Auto-generated method stub
		Session session = SessionFactoryService.get().openSession();
		try {
			session.beginTransaction();
			session.delete(comentarioConvite);
			session.getTransaction().commit();
		} catch (Exception e) {
			
		} finally {
			session.close();
		}
	}
}
