package es.upm.dit.isst.VenACenarConmigo.dao.model;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
@Entity
public class Aficion implements Serializable {
	@Id
	private String aficion;
	private String descripcion;
	@ManyToOne
	private Usuario usuario;
	public String getAficion() {
		return aficion;
	}
	public void setAficion(String aficion) {
		this.aficion = aficion;
	}
	public String getDescripcion() {
		return descripcion;
	}
	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}
	public Usuario getUsuario() {
		return usuario;
	}
	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}

}
