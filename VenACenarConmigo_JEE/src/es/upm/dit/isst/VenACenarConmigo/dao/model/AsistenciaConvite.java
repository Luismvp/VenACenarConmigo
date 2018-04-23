package es.upm.dit.isst.VenACenarConmigo.dao.model;

import java.io.Serializable;
import java.util.List;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToMany;

@Entity
public class AsistenciaConvite implements Serializable {
	//ToDo:Revisar relaciones
	@Id
	private int idAsistente;
	private String emailAnfitrion;
	private int idConvite;
	private String emailUsuarioAsistente;
	private int invitacionInscripcion;
	private Boolean confirmado;
	private int numeroInvitado;
	
	
	public int getIdAsistente() {
		return idAsistente;
	}
	public void setIdAsistente(int idAsistente) {
		this.idAsistente = idAsistente;
	}
	public int getIdConvite() {
		return idConvite;
	}
	public void setIdConvite(int idConvite) {
		this.idConvite = idConvite;
	}
	public int getNumeroInvitado() {
		return numeroInvitado;
	}
	public void setNumeroInvitado(int numeroInvitado) {
		this.numeroInvitado = numeroInvitado;
	}
	public String getEmailAnfitrion() {
		return emailAnfitrion;
	}
	public void setEmailAnfitrion(String emailAnfitrion) {
		this.emailAnfitrion = emailAnfitrion;
	}
	public String getEmailUsuarioAsistente() {
		return emailUsuarioAsistente;
	}
	public void setEmailUsuarioAsistente(String emailUsuarioAsistente) {
		this.emailUsuarioAsistente = emailUsuarioAsistente;
	}
	public int getInvitacionInscripcion() {
		return invitacionInscripcion;
	}
	public void setInvitacionInscripcion(int invitacionInscripcion) {
		this.invitacionInscripcion = invitacionInscripcion;
	}
	public Boolean getConfirmado() {
		return confirmado;
	}
	public void setConfirmado(Boolean confirmado) {
		this.confirmado = confirmado;
	}
}