package es.upm.dit.isst.VenACenarConmigo.dao.model;

import java.io.Serializable;
import java.util.List;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToMany;

@Entity
public class AsistenciaConvite implements Serializable {
	@Id
	private int iDAsistencia;
	private Usuario usuarioAsistente;
	private Convite convite;
	private Usuario anfitrion;
	private String invitacionInscripcion;
	private Boolean confirmado;
	/**
	 * @return the iDAsistencia
	 */
	public int getiDAsistencia() {
		return iDAsistencia;
	}
	/**
	 * @param iDAsistencia the iDAsistencia to set
	 */
	public void setiDAsistencia(int iDAsistencia) {
		this.iDAsistencia = iDAsistencia;
	}
	/**
	 * @return the usuarioAsistente
	 */
	public Usuario getUsuarioAsistente() {
		return usuarioAsistente;
	}
	/**
	 * @param usuarioAsistente the usuarioAsistente to set
	 */
	public void setUsuarioAsistente(Usuario usuarioAsistente) {
		this.usuarioAsistente = usuarioAsistente;
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
	 * @return the anfitrion
	 */
	public Usuario getAnfitrion() {
		return anfitrion;
	}
	/**
	 * @param anfitrion the anfitrion to set
	 */
	public void setAnfitrion(Usuario anfitrion) {
		this.anfitrion = anfitrion;
	}
	/**
	 * @return the invitacionInscripcion
	 */
	public String getInvitacionInscripcion() {
		return invitacionInscripcion;
	}
	/**
	 * @param invitacionInscripcion the invitacionInscripcion to set
	 */
	public void setInvitacionInscripcion(String invitacionInscripcion) {
		this.invitacionInscripcion = invitacionInscripcion;
	}
	/**
	 * @return the confirmado
	 */
	public Boolean getConfirmado() {
		return confirmado;
	}
	/**
	 * @param confirmado the confirmado to set
	 */
	public void setConfirmado(Boolean confirmado) {
		this.confirmado = confirmado;
	}
	
}
