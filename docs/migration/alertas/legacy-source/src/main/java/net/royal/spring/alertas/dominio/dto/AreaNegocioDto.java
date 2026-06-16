package net.royal.spring.alertas.dominio.dto;

import java.math.BigDecimal;
import java.util.Date;

public class AreaNegocioDto {

	private BigDecimal idAreaNegocio;
	private String nombre;
	private String descripcion;
	private String estadoaux;
	private String estado;
	private String creacionUsuario;
	private Date creacionFecha;
	private Date modiffecha;
	private String modifUsuario;
	private BigDecimal	ROWNUM_ ;

	public BigDecimal getROWNUM_() {
		return ROWNUM_;
	}
	public void setROWNUM_(BigDecimal rOWNUM_) {
		ROWNUM_ = rOWNUM_;
	}
	public BigDecimal getIdAreaNegocio() {
		return idAreaNegocio;
	}
	public void setIdAreaNegocio(BigDecimal idAreaNegocio) {
		this.idAreaNegocio = idAreaNegocio;
	}
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
 
	public String getEstado() {
		return estado;
	}
	public void setEstado(String estado) {
		this.estado = estado;
	}
	public String getDescripcion() {
		return descripcion;
	}
	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}
	public String getEstadoaux() {
		return estadoaux;
	}
	public void setEstadoaux(String estadoaux) {
		this.estadoaux = estadoaux;
	}
	public String getCreacionUsuario() {
		return creacionUsuario;
	}
	public void setCreacionUsuario(String creacionUsuario) {
		this.creacionUsuario = creacionUsuario;
	}
 
 
	public String getModifUsuario() {
		return modifUsuario;
	}
	public void setModifUsuario(String modifUsuario) {
		this.modifUsuario = modifUsuario;
	}
	public Date getCreacionFecha() {
		return creacionFecha;
	}
	public void setCreacionFecha(Date creacionFecha) {
		this.creacionFecha = creacionFecha;
	}
	public Date getModiffecha() {
		return modiffecha;
	}
	public void setModiffecha(Date modiffecha) {
		this.modiffecha = modiffecha;
	}
	
}
