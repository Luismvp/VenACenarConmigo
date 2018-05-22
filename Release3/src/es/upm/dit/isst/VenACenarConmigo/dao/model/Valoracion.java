package es.upm.dit.isst.VenACenarConmigo.dao.model;

import java.io.Serializable;
import java.util.List;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
@Entity
public class Valoracion implements Serializable{
	@Id
	private int idValoracion;
	public int getIdValoracion() {
		return idValoracion;
	}
	public void setIdValoracion(int idValoracion) {
		this.idValoracion = idValoracion;
	}
	private String usuarioValorador;
	private String usuarioValorado;
	private int convite;
	private int puntuacion;
	private String comentario;
	
	public String getUsuarioValorador() {
		return usuarioValorador;
	}
	public void setUsuarioValorador(String usuarioValorador) {
		this.usuarioValorador = usuarioValorador;
	}
	public String getUsuarioValorado() {
		return usuarioValorado;
	}
	public void setUsuarioValorado(String usuarioValorado) {
		this.usuarioValorado = usuarioValorado;
	}
	/**
	 * @return 
	 */
	public int getConvite() {
		return convite;
	}
	/**
	 * @param 
	 */
	public void setConvite(int convite) {
		this.convite = convite;
	}
	/**
	 * @return 
	 */
	public int getPuntuacion() {
		return puntuacion;
	}
	/**
	 * @param 
	 */
	public void setPuntuacion(int puntuacion) {
		this.puntuacion = puntuacion;
	}

	public String getComentario() {
		return comentario;
	}
	/**
	 * @param 
	 */
	public void setComentario(String comentario) {
		this.comentario = comentario;
	}
}
