package es.upm.dit.isst.VenACenarConmigo.dao;

import java.util.List;
import es.upm.dit.isst.VenACenarConmigo.dao.model.AccionUsuario;

public interface AccionUsuarioDAO {
	public List <AccionUsuario> readAllAccionUsuario();
	public void createAccionUsuario(AccionUsuario accionUsuario);
	public AccionUsuario readAccionUsuario (int id);
	public void updateAccionUsuario(AccionUsuario accionUsuario);
	public void deleteAccionUsuario(AccionUsuario accionUsuario);
	public AccionUsuario buscarBloqueo(String usuarioEmisor, String usuarioReceptor);
	public AccionUsuario buscarSeguimiento(String usuarioEmisor, String usuarioReceptor);

}
