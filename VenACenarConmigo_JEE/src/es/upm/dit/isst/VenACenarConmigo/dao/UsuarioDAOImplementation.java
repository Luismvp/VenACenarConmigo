package es.upm.dit.isst.VenACenarConmigo.dao;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Session;

import es.upm.dit.isst.VenACenarConmigo.dao.model.Usuario;
import es.upm.dit.isst.VenACenarConmigo.dao.SessionFactoryService;

public class UsuarioDAOImplementation implements UsuarioDAO{
	
	private static UsuarioDAOImplementation instance;
	private UsuarioDAOImplementation() {}
	public static UsuarioDAOImplementation getInstance() {
		if ( null== instance ) {
			instance = new UsuarioDAOImplementation();
		}
		return instance;
	}
	/*Metodo de Login de cualquier usuario.
	/ Crea una sesión de usuario en base a un email y una contraseña.
	*/
	@Override
	public Usuario loginUsuario(String email, String password) {
		// TODO Auto-generated method stub
		Usuario usuario =null;
		Session session =  SessionFactoryService.get().openSession();	
		try {
			session.beginTransaction();
			usuario = (Usuario) session.createQuery("select n from Usuario n where n.email= :email and n.password= :password")
					.setParameter("email", email)
					.setParameter("password", password)
					.uniqueResult();
			session.getTransaction().commit();
		}catch(Exception e){
			
		}finally {
			session.close();
		}
		return usuario;
	}
	//Metodo que devuelve todas las filas de la tabla.
	@Override
	public List<Usuario> readAllUsuarios() {
		// TODO Auto-generated method stub
		List<Usuario> usuarios= new ArrayList<>();
		Session session = SessionFactoryService.get().openSession();
		try {
			session.beginTransaction();
			usuarios.addAll(
					session.createQuery("from Professor").list()
			);
			session.getTransaction().commit();
		} catch (Exception e) {
			
		} finally {
			session.close();
		}
		return usuarios;
	}
	/*
	 * Metodo que crea una fila en la tabla de usuarios
	 */
	@Override
	public void createUsuario(Usuario usuario) {
		// TODO Auto-generated method stub
		Session session = SessionFactoryService.get().openSession();
		try {
			session.beginTransaction();
			session.save(usuario);
			session.getTransaction().commit();
		} catch (Exception e) {
			
		} finally {
			session.close();
		}
		
	}
/*
 * Buscar usuario por email
 */
	@Override
	public Usuario readUsuario(String email) {
		// TODO Auto-generated method stub
		Usuario usuario = null;
		Session session = SessionFactoryService.get().openSession();
		try {
			session.beginTransaction();
			usuario = session.get(Usuario.class, email);
			session.getTransaction().commit();
		} catch (Exception e) {
			
		} finally {
			session.close();
		}
		return usuario;
	}
	/*
	 * Buscar usuario por nombre
	 */
	@Override
	public Usuario readUsuarioPorNombre(String nombre) {
		// TODO Auto-generated method stub
		Usuario usuario = null;
		Session session = SessionFactoryService.get().openSession();
		try {
			session.beginTransaction();
			usuario = session.get(Usuario.class, nombre);
			session.getTransaction().commit();
		} catch (Exception e) {
			
		} finally {
			session.close();
		}
		return usuario;
	}
	/*
	 * Sustituir información de usuario
	 */
	@Override
	public Usuario updateUsuario(Usuario usuario) {
		// TODO Auto-generated method stub
		Session session = SessionFactoryService.get().openSession();
		try {
			session.beginTransaction();
			session.saveOrUpdate(usuario);
			session.getTransaction().commit();
		} catch (Exception e) {
			
		} finally {
			session.close();
		}
		return usuario;
	}
	/*
	 * Sustituir información de usuario
	 */
	@Override
	public Usuario deleteUsuario(Usuario usuario) {
		// TODO Auto-generated method stub
		Session session = SessionFactoryService.get().openSession();
		try {
			session.beginTransaction();
			session.delete(usuario);
			session.getTransaction().commit();
		} catch (Exception e) {
			
		} finally {
			session.close();
		}
		return usuario;
	}

}
