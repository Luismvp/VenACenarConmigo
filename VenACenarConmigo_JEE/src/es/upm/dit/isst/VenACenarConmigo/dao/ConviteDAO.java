package es.upm.dit.isst.VenACenarConmigo.dao;

import java.util.List;

import es.upm.dit.isst.VenACenarConmigo.dao.model.Convite;

public interface ConviteDAO {
	public List <Convite> readAllConvite();
	public void createConvite (Convite convite);
	public Convite readConvite (String email);
	public void updateConvite (Convite convite);
	public void deleteConvite (Convite convite);

}
