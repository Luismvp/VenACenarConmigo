package es.upm.dit.isst.VenACenarConmigo.dao;

import java.util.List;
import es.upm.dit.isst.VenACenarConmigo.dao.model.AsistenciaConvite;

public interface AsistenciaConviteDAO {
	public List <AsistenciaConvite> readAllAsistenciaConvite();
	public void createAsistenciaConvite (AsistenciaConvite asistenciaConvite);
	public AsistenciaConvite readAsistenciaConvite (String email);
	public AsistenciaConvite readAsistenciaConvite (int id);
	public void updateAsistenciaConvite (AsistenciaConvite asistenciaConvite);
	public void deleteAsistenciaConvite (AsistenciaConvite asistente);
}
