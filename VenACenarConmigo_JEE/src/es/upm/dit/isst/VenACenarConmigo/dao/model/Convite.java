package es.upm.dit.isst.VenACenarConmigo.dao.model;

import java.io.Serializable;
import java.util.List;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.ManyToMany;
@Entity
public class Convite implements Serializable{
	@Id
	private Integer iDConvite;
	@ManyToOne
	private Usuario anfitrion;
	@ManyToMany
	private List<Usuario> invitados;
	private String nombre;
	private String fecha;
	private String horaComienzo;
	private String horaFin;
	private String restaurante;
	private String menu;
	private String temasConversacion;
	private Integer maxInvitados;
	private Integer precioInvitado;
	private String descripcion;
	private String ciudad;
	private String area;
	/**
	 * @return the iDConvite
	 */
	public int getIDConvite() {
		return iDConvite;
	}
	/**
	 * @param iDConvite the iDConvite to set
	 */
	public void setIDConvite(int iDConvite) {
		this.iDConvite = iDConvite;
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
	 * @return the invitados
	 */
	public List<Usuario> getInvitados() {
		return invitados;
	}
	/**
	 * @param invitados the invitados to set
	 */
	public void setInvitados(List<Usuario> invitados) {
		this.invitados = invitados;
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
	/**
	 * @return the fecha
	 */
	public String getFecha() {
		return fecha;
	}
	/**
	 * @param fecha the fecha to set
	 */
	public void setFecha(String fecha) {
		this.fecha = fecha;
	}
	/**
	 * @return the horaCominezo
	 */
	public String getHoraComienzo() {
		return horaComienzo;
	}
	/**
	 * @param horaCominezo the horaCominezo to set
	 */
	public void setHoraComienzo(String horaComienzo) {
		this.horaComienzo = horaComienzo;
	}
	/**
	 * @return the horaFin
	 */
	public String getHoraFin() {
		return horaFin;
	}
	/**
	 * @param horaFin the horaFin to set
	 */
	public void setHoraFin(String horaFin) {
		this.horaFin = horaFin;
	}
	/**
	 * @return the restaurante
	 */
	public String getRestaurante() {
		return restaurante;
	}
	/**
	 * @param restaurante the restaurante to set
	 */
	public void setRestaurante(String restaurante) {
		this.restaurante = restaurante;
	}
	/**
	 * @return the menu
	 */
	public String getMenu() {
		return menu;
	}
	/**
	 * @param menu the menu to set
	 */
	public void setMenu(String menu) {
		this.menu = menu;
	}
	/**
	 * @return the temasConversacion
	 */
	public String getTemasConversacion() {
		return temasConversacion;
	}
	/**
	 * @param temasConversacion the temasConversacion to set
	 */
	public void setTemasConversacion(String temasConversacion) {
		this.temasConversacion = temasConversacion;
	}
	/**
	 * @return the maxInvitados
	 */
	public Integer getMaxInvitados() {
		return maxInvitados;
	}
	/**
	 * @param maxInvitados the maxInvitados to set
	 */
	public void setMaxInvitados(Integer maxInvitados) {
		this.maxInvitados = maxInvitados;
	}
	/**
	 * @return the precioInvitado
	 */
	public Integer getPrecioInvitado() {
		return precioInvitado;
	}
	/**
	 * @param precioInvitado the precioInvitado to set
	 */
	public void setPrecioInvitado(Integer precioInvitado) {
		this.precioInvitado = precioInvitado;
	}
	/**
	 * @return the descripcion
	 */
	public String getDescripcion() {
		return descripcion;
	}
	/**
	 * @param descripcion the descripcion to set
	 */
	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}
	/**
	 * @return the ciudad
	 */
	public String getCiudad() {
		return ciudad;
	}
	/**
	 * @param ciudad the ciudad to set
	 */
	public void setCiudad(String ciudad) {
		this.ciudad = ciudad;
	}
	/**
	 * @return the area
	 */
	public String getArea() {
		return area;
	}
	/**
	 * @param area the area to set
	 */
	public void setArea(String area) {
		this.area = area;
	}
}
