package es.upm.dit.isst.VenACenarConmigo.dao;
import java.util.List;
import es.upm.dit.isst.VenACenarConmigo.dao.model.ComentarioConvite;
public interface ComentarioConviteDAO {
	public List <ComentarioConvite> readAllComentarioConvite();
	public void createComentarioConvite (ComentarioConvite comentarioConvite);
	public ComentarioConvite readComentarioConvite (String idConvite);
	public void updateComentarioConvite(ComentarioConvite comentarioConvite);
	public void deleteComentarioConvite(ComentarioConvite comentarioConvite);
}
