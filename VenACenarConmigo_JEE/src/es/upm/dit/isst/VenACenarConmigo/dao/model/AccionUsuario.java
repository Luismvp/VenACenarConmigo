package es.upm.dit.isst.VenACenarConmigo.dao.model;

import java.io.Serializable;
import java.util.List;
import javax.persistence.Entity;
import javax.persistence.ForeignKey;
import javax.persistence.Id;
import javax.persistence.ManyToMany;

@Entity
public class AccionUsuario implements Serializable{
	//ToDo:Revisar relaciones ¿foreingnkey?
	@Id
	private String usuarioEmisor;
	private String usuarioReceptor;
	private int seguimientoBloqueoDenuncia;
	private String comentario;
	public String getUsuarioEmisor() {
		return usuarioEmisor;
	}
	public void setUsuarioEmisor(String usuarioEmisor) {
		this.usuarioEmisor = usuarioEmisor;
	}
	public String getUsuarioReceptor() {
		return usuarioReceptor;
	}
	public void setUsuarioReceptor(String usuarioReceptor) {
		this.usuarioReceptor = usuarioReceptor;
	}
	public int getSeguimientoBloqueoDenuncia() {
		return seguimientoBloqueoDenuncia;
	}
	public void setSeguimientoBloqueoDenuncia(int seguimientoBloqueoDenuncia) {
		this.seguimientoBloqueoDenuncia = seguimientoBloqueoDenuncia;
	}
	public String getComentario() {
		return comentario;
	}
	public void setComentario(String comentario) {
		this.comentario = comentario;
	}
}	