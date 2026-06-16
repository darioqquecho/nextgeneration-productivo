package net.royal.spring.alertas.dominio.dto;

import java.math.BigDecimal;

public class FuenteHistoriaDto {

	private BigDecimal idReglaNegocio;
 	private BigDecimal idFuenteAlerta;
	private BigDecimal nroRegistro;
	private BigDecimal anio;
	private BigDecimal claveEntera;
	private String claveCadena;
	private String estado;
	private String creacionUsuario;
	private java.util.Date creacionFecha;
	private String creacionTerminal;
	private String modificacionUsuario;
	private java.util.Date modificacionFecha;
	private String modificacionTerminal;
	private BigDecimal idFuenteHistoria;
	private Boolean flgInactivar;
	private BigDecimal ROWNUM_ ;
	
	private BigDecimal contador;
	
	
	
	public BigDecimal getContador() {
		return contador;
	}
	public void setContador(BigDecimal contador) {
		this.contador = contador;
	}
	public Boolean getFlgInactivar() {
		if(flgInactivar == null) 
			return false;
		return flgInactivar;
	}
	public void setFlgInactivar(Boolean flgInactivar) {
		this.flgInactivar = flgInactivar;
	}
	public BigDecimal getROWNUM_() {
		return ROWNUM_;
	}
	public void setROWNUM_(BigDecimal rOWNUM_) {
		ROWNUM_ = rOWNUM_;
	}
	public BigDecimal getIdReglaNegocio() {
		return idReglaNegocio;
	}
	public void setIdReglaNegocio(BigDecimal idReglaNegocio) {
		this.idReglaNegocio = idReglaNegocio;
	}
	public BigDecimal getIdFuenteAlerta() {
		return idFuenteAlerta;
	}
	public void setIdFuenteAlerta(BigDecimal idFuenteAlerta) {
		this.idFuenteAlerta = idFuenteAlerta;
	}
	public BigDecimal getNroRegistro() {
		return nroRegistro;
	}
	public void setNroRegistro(BigDecimal nroRegistro) {
		this.nroRegistro = nroRegistro;
	}
	public BigDecimal getAnio() {
		return anio;
	}
	public void setAnio(BigDecimal anio) {
		this.anio = anio;
	}
	public BigDecimal getClaveEntera() {
		return claveEntera;
	}
	public void setClaveEntera(BigDecimal claveEntera) {
		this.claveEntera = claveEntera;
	}
	public String getClaveCadena() {
		return claveCadena;
	}
	public void setClaveCadena(String claveCadena) {
		this.claveCadena = claveCadena;
	}
	public String getEstado() {
		return estado;
	}
	public void setEstado(String estado) {
		this.estado = estado;
	}
	public String getCreacionUsuario() {
		return creacionUsuario;
	}
	public void setCreacionUsuario(String creacionUsuario) {
		this.creacionUsuario = creacionUsuario;
	}
	public java.util.Date getCreacionFecha() {
		return creacionFecha;
	}
	public void setCreacionFecha(java.util.Date creacionFecha) {
		this.creacionFecha = creacionFecha;
	}
	public String getCreacionTerminal() {
		return creacionTerminal;
	}
	public void setCreacionTerminal(String creacionTerminal) {
		this.creacionTerminal = creacionTerminal;
	}
	public String getModificacionUsuario() {
		return modificacionUsuario;
	}
	public void setModificacionUsuario(String modificacionUsuario) {
		this.modificacionUsuario = modificacionUsuario;
	}
	public java.util.Date getModificacionFecha() {
		return modificacionFecha;
	}
	public void setModificacionFecha(java.util.Date modificacionFecha) {
		this.modificacionFecha = modificacionFecha;
	}
	public String getModificacionTerminal() {
		return modificacionTerminal;
	}
	public void setModificacionTerminal(String modificacionTerminal) {
		this.modificacionTerminal = modificacionTerminal;
	}
	public BigDecimal getIdFuenteHistoria() {
		return idFuenteHistoria;
	}
	public void setIdFuenteHistoria(BigDecimal idFuenteHistoria) {
		this.idFuenteHistoria = idFuenteHistoria;
	}
	
	
	
}
