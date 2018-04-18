package es.upm.dit.isst.VenACenarConmigo.dao;

import java.util.List;

import es.upm.dit.isst.VenACenarConmigo.dao.model.Usuario;

public interface UsuarioDAO {
	public Usuario loginUsuario (String email, String password);
	public List <Usuario> readAllUsuarios();
	public void createUsuario(Usuario usuario);
	public Usuario readUsuario(String email);
	public Usuario readUsuarioPorNombre(String nombre);
	public Usuario updateUsuario (Usuario usuario);
	public Usuario deleteUsuario (Usuario usuario);
	
}
