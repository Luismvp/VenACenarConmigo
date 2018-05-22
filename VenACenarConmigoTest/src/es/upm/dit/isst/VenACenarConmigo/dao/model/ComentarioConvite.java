package es.upm.dit.isst.VenACenarConmigo.dao.model;
import java.io.Serializable;
import java.util.Calendar;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

@Entity
public class ComentarioConvite implements Serializable{
	@Id
	private int iDComentario;
	@ManyToOne
	private Convite convite;
	private String nombre;
	private String comentario;
	private Calendar fecha;
	/**
	 * @return the iDPublicacion
	 */
	public int getiDComentario() {
		return iDComentario;
	}
	/**
	 * @param iDPublicacion the iDPublicacion to set
	 */
	public void setiDComentario(int iDComentario) {
		this.iDComentario = iDComentario;
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
	/**
	 * @return the convite
	 */
	public Convite getConvite() {
		return convite;
	}
	/**
	 * @param convite the convite to set
	 */
	public void setConvite(Convite convite) {
		this.convite = convite;
	}
	/**
	 * @return the nombre
	 */
	public String getNombre() {
		return nombre;
	}
	/**
	 * @param nombre the nombre to set
	 */
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	
	
}
