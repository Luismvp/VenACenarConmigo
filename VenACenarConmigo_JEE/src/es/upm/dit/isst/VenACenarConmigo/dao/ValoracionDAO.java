package es.upm.dit.isst.VenACenarConmigo.dao;

import java.util.List;

import es.upm.dit.isst.VenACenarConmigo.dao.model.Convite;
import es.upm.dit.isst.VenACenarConmigo.dao.model.Valoracion;

public interface ValoracionDAO {
	public List <Valoracion> readAllValoracion();
	public void createValoracion (Valoracion valoracion);
	public Valoracion readValoracion (String email);
	public Valoracion readValoracion (int id);
	public void updateValoracion (Valoracion valoracion);
	public void deleteValoracion (Valoracion valoracion);
}