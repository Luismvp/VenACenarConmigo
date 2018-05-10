package es.upm.dit.isst.VenACenarConmigo.dao;

import java.util.List;
import es.upm.dit.isst.VenACenarConmigo.dao.model.Publicaciones;

public interface PublicacionesDAO {
	public List <Publicaciones> readAllPublicaciones();
	public void createPublicaciones (Publicaciones publicaciones);
	public Publicaciones readPublicaciones (String email);
	public void updatePublicaciones(Publicaciones publicaciones);
	public void deletePublicaciones(Publicaciones publicaciones);
}
