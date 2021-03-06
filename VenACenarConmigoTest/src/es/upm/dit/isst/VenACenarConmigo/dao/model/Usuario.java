package es.upm.dit.isst.VenACenarConmigo.dao.model;

import java.io.Serializable;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.OneToMany;

@Entity
public class Usuario implements Serializable {
	@Id
	private String email;
	private String nombre;
	private String apellidos;
	private String nacimiento;
	private String telefono;
	private String ciudad;
	private String codigoPostal;
	private String password;
	private String profesion;
	private String descripcion;
	private Boolean validado;
	private int privacidad1;
	private int privacidad2;
	private int privacidad3;
	
	private String nombreFoto;
	
	public String getNombreFoto() {
		return nombreFoto;
	}
	public void setNombreFoto(String foto) {
		this.nombreFoto = foto;
	}
	public int getPrivacidad1() {
		return privacidad1;
	}
	public void setPrivacidad1(int privacidad1) {
		this.privacidad1 = privacidad1;
	}
	public int getPrivacidad2() {
		return privacidad2;
	}
	public void setPrivacidad2(int privacidad2) {
		this.privacidad2 = privacidad2;
	}
	public int getPrivacidad3() {
		return privacidad3;
	}
	public void setPrivacidad3(int privacidad3) {
		this.privacidad3 = privacidad3;
	}
	public Boolean getValidado() {
		return validado;
	}
	public void setValidado(Boolean validado) {
		this.validado = validado;
	}
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
	public String getNacimiento() {
		return nacimiento;
	}
	public void setNacimiento(String nacimiento) {
		this.nacimiento = nacimiento;
	}
	public String getTelefono() {
		return telefono;
	}
	public void setTelefono(String telefono) {
		this.telefono = telefono;
	}
	public String getCodigoPostal() {
		return codigoPostal;
	}
	public void setCodigoPostal(String codigoPostal) {
		this.codigoPostal = codigoPostal;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getProfesion() {
		return profesion;
	}
	public void setProfesion(String profesionEstudios) {
		this.profesion = profesionEstudios;
	}
	public String getDescripcion() {
		return descripcion;
	}
	public void setDescripcion(String descripcionPersonal) {
		this.descripcion = descripcionPersonal;
	}
	public List<Aficion> getAficiones() {
		return aficiones;
	}
	public void setAficiones(List<Aficion> aficiones) {
		this.aficiones = aficiones;
	}
	public String getCiudad() {
		return ciudad;
	}
	public void setCiudad(String ciudad) {
		this.ciudad = ciudad;
	}
	

}
