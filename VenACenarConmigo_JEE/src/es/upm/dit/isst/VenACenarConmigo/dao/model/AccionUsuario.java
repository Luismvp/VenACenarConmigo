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
	private int iDAccion;
	private Usuario usuarioEmisor;
	private Usuario usuarioReceptor;
	private String seguimientoBloqueoDenuncia;
	private String comentario;
	/**
	 * @return the iDAccion
	 */
	public int getiDAccion() {
		return iDAccion;
	}
	/**
	 * @param iDAccion the iDAccion to set
	 */
	public void setiDAccion(int iDAccion) {
		this.iDAccion = iDAccion;
	}
	/**
	 * @return the usuarioEmisor
	 */
	public Usuario getUsuarioEmisor() {
		return usuarioEmisor;
	}
	/**
	 * @param usuarioEmisor the usuarioEmisor to set
	 */
	public void setUsuarioEmisor(Usuario usuarioEmisor) {
		this.usuarioEmisor = usuarioEmisor;
	}
	/**
	 * @return the usuarioReceptor
	 */
	public Usuario getUsuarioReceptor() {
		return usuarioReceptor;
	}
	/**
	 * @param usuarioReceptor the usuarioReceptor to set
	 */
	public void setUsuarioReceptor(Usuario usuarioReceptor) {
		this.usuarioReceptor = usuarioReceptor;
	}
	/**
	 * @return the seguimientoBloqueoDenuncia
	 */
	public String getSeguimientoBloqueoDenuncia() {
		return seguimientoBloqueoDenuncia;
	}
	/**
	 * @param seguimientoBloqueoDenuncia the seguimientoBloqueoDenuncia to set
	 */
	public void setSeguimientoBloqueoDenuncia(String seguimientoBloqueoDenuncia) {
		this.seguimientoBloqueoDenuncia = seguimientoBloqueoDenuncia;
	}
	/**
	 * @return the comentario
	 */
	public String getComentario() {
		return comentario;
	}
	/**
	 * @param comentario the comentario to set
	 */
	public void setComentario(String comentario) {
		this.comentario = comentario;
	}
}
