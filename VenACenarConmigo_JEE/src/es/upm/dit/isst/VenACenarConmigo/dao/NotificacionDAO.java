package es.upm.dit.isst.VenACenarConmigo.dao;

import java.util.List;

import es.upm.dit.isst.VenACenarConmigo.dao.model.Notificacion;

public interface NotificacionDAO {
	public List<Notificacion> readAllNotificacion();

	public void createNotificacion(Notificacion notificacion);

	public Notificacion readNotificacion(int id);

	public void updateNotificacion(Notificacion notificacion);

	public void deleteNotificacion(Notificacion notificacion);

}
