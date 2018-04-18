package es.upm.dit.isst.VenACenarConmigo.dao.model;

import java.io.Serializable;
import java.sql.Date;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

@Entity
public class Usuario implements Serializable {
	@Id
	private String email;
	private String nombre;
	private String apellidos;
	private Date nacimiento;
	private int telefono;
	private int codigoPostal;
	private String password;
	private String profesionEstudios;
	private String descripcionPersonal;
	@OneToMany(mappedBy="usuario", fetch = FetchType.EAGER)
	private List<Aficion> aficiones;
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	public String getApellidos() {
		return apellidos;
	}
	public void setApellidos(String apellidos) {
		this.apellidos = apellidos;
	}
	public Date getNacimiento() {
		return nacimiento;
	}
	public void setNacimiento(Date nacimiento) {
		this.nacimiento = nacimiento;
	}
	public int getTelefono() {
		return telefono;
	}
	public void setTelefono(int telefono) {
		this.telefono = telefono;
	}
	public int getCodigoPostal() {
		return codigoPostal;
	}
	public void setCodigoPostal(int codigoPostal) {
		this.codigoPostal = codigoPostal;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getProfesionEstudios() {
		return profesionEstudios;
	}
	public void setProfesionEstudios(String profesionEstudios) {
		this.profesionEstudios = profesionEstudios;
	}
	public String getDescripcionPersonal() {
		return descripcionPersonal;
	}
	public void setDescripcionPersonal(String descripcionPersonal) {
		this.descripcionPersonal = descripcionPersonal;
	}
	public List<Aficion> getAficiones() {
		return aficiones;
	}
	public void setAficiones(List<Aficion> aficiones) {
		this.aficiones = aficiones;
	}
	

}
