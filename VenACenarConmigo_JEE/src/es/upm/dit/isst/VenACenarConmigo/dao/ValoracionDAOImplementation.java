package es.upm.dit.isst.VenACenarConmigo.dao;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Session;

import es.upm.dit.isst.VenACenarConmigo.dao.model.Valoracion;

public class ValoracionDAOImplementation implements ValoracionDAO {
	private static ValoracionDAOImplementation instance = null;
	private ValoracionDAOImplementation() {}
	public static ValoracionDAOImplementation getInstance() {
		if(null==instance) {
			instance = new ValoracionDAOImplementation();
		}
		return instance;
	}
	//Metodo que permite obtener una lista con todas las filas de la tabla Convite
	@Override
	public List <Valoracion> readAllValoracion() {
		// TODO Auto-generated method stub
		List<Valoracion> valoracion = new ArrayList<>();
		Session session = SessionFactoryService.get().openSession();
		try {
			session.beginTransaction();
			valoracion.addAll(
					session.createQuery("from Valoraciones").list()
			);
			session.getTransaction().commit();
		} catch (Exception e) {
			
		} finally {
			session.close();
		}
		return valoracion;
	}
	//Crear fila en la tabla Convite
	@Override
	public void createValoracion(Valoracion valoracion) {
		// TODO Auto-generated method stub
		Session session = SessionFactoryService.get().openSession();
		try {
			session.beginTransaction();
			session.save(valoracion);
			session.getTransaction().commit();
		} catch (Exception e) {
			
		} finally {
			session.close();
		}
	}
	//Buscar fila en la tabla convites a traves del email de usuario
	@Override
	public Valoracion readValoracion(String email) {
		// TODO Auto-generated method stub
		Valoracion valoracion = null;
		Session session = SessionFactoryService.get().openSession();
		try {
			session.beginTransaction();
			valoracion = session.get(Valoracion.class, email);
			session.getTransaction().commit();
		} catch (Exception e) {
			
		} finally {
			session.close();
		}
		return valoracion;
	}
	public Valoracion readValoracion(int id) {
		// TODO Auto-generated method stub
		Valoracion valoracion = null;
		Session session = SessionFactoryService.get().openSession();
		try {
			session.beginTransaction();
			valoracion = session.get(Valoracion.class, id);
			session.getTransaction().commit();
		} catch (Exception e) {
			
		} finally {
			session.close();
		}
		return valoracion;
	}
	//Metodo que actualiza una fila de la tabla Convite
	@Override
	public void updateValoracion(Valoracion valoracion) {
		// TODO Auto-generated method stub
		Session session = SessionFactoryService.get().openSession();
		try {
			session.beginTransaction();
			session.saveOrUpdate(valoracion);
			session.getTransaction().commit();
		} catch (Exception e) {
			
		} finally {
			session.close();
		}
	}
	//Metodo que elimina una fila de la tabla Convite
	@Override
	public void deleteValoracion(Valoracion valoracion) {
		// TODO Auto-generated method stub
		Session session = SessionFactoryService.get().openSession();
		try {
			session.beginTransaction();
			session.delete(valoracion);
			session.getTransaction().commit();
		} catch (Exception e) {
			
		} finally {
			session.close();
		}
		
	}

}
