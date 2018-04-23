package es.upm.dit.isst.VenACenarConmigo.dao.model;

import java.io.Serializable;
import java.util.List;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToMany;

@Entity
public class AsistenciaConvite implements Serializable {
	@Id
	private Integer idAsistencia;
	private String emailAnfitrion;
	private Integer idConvite;
	private String emailUsuarioAsistente;
	private Integer invitacionInscripcion;
	private Boolean confirmado;
	private Integer numeroInvitado;
	
	
	public Integer getIdAsistencia() {
		return idAsistencia;
	}
	public void setIdAsistencia(Integer idAsistencia) {
		this.idAsistencia = idAsistencia;
	}
	public Integer getIdConvite() {
		return idConvite;
	}
	public void setIdConvite(Integer idConvite) {
		this.idConvite = idConvite;
	}
	public Integer getNumeroInvitado() {
		return numeroInvitado;
	}
	public void setNumeroInvitado(Integer numeroInvitado) {
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
	public Integer getInvitacionInscripcion() {
		return invitacionInscripcion;
	}
	public void setInvitacionInscripcion(Integer invitacionInscripcion) {
		this.invitacionInscripcion = invitacionInscripcion;
	}
	public Boolean getConfirmado() {
		return confirmado;
	}
	public void setConfirmado(Boolean confirmado) {
		this.confirmado = confirmado;
	}
}

