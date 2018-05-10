package es.upm.dit.isst.VenACenarConmigo.dao.model;

import java.io.Serializable;
import java.util.Calendar;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

@Entity
public class  Publicaciones implements Serializable{
	@Id
	private int iDPublicacion;
	@ManyToOne
	private Usuario usuario;
	private String texto;
	private byte[] adjunto;
	private Calendar fecha;
	/**
	 * @return the iDPublicacion
	 */
	public int getiDPublicacion() {
		return iDPublicacion;
	}
	/**
	 * @param iDPublicacion the iDPublicacion to set
	 */
	public void setiDPublicacion(int iDPublicacion) {
		this.iDPublicacion = iDPublicacion;
	}
	/**
	 * @return the usuario
	 */
	public Usuario getUsuario() {
		return usuario;
	}
	/**
	 * @param usuario the usuario to set
	 */
	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}
	/**
	 * @return the texto
	 */
	public String getTexto() {
		return texto;
	}
	/**
	 * @param texto the texto to set
	 */
	public void setTexto(String texto) {
		this.texto = texto;
	}
	/**
	 * @return the adjunto
	 */
	public byte[] getAdjunto() {
		return adjunto;
	}
	/**
	 * @param adjunto the adjunto to set
	 */
	public void setAdjunto(byte[] adjunto) {
		this.adjunto = adjunto;
	}
	/**
	 * @return the fecha
	 */
	public Calendar getFecha() {
		return fecha;
	}
	/**
	 * @param fecha the fecha to set
	 */
	public void setFecha(Calendar fecha) {
		this.fecha = fecha;
	}

}
