package es.upm.dit.isst.VenACenarConmigo.dao;

import java.util.List;

import es.upm.dit.isst.VenACenarConmigo.dao.model.Aficion;


public interface AficionDAO {
	public List <Aficion> readAllAficion();
	public void createAficion (Aficion aficion);
	public Aficion readAficion (String email);
	public void updateAficion (Aficion aficion);
	public void deleteAficion (Aficion aficion);
}
