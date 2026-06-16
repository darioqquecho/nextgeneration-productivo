package net.royal.spring.alertas.dominio.dto;

import java.math.BigDecimal;

public class ReglaNegocioAdicionalDto {
	 
	private BigDecimal idReglaNegocio;
	private BigDecimal idAdicional;
	private BigDecimal ordenColumna;
	private String nombreCampo;
	private String descripcionCampo;
	private BigDecimal longitudCampo;
	private String estado;
	private String creacionUsuario;
	private java.util.Date creacionFecha;
	private String creacionTerminal;
	private String modificacionUsuario;
	private java.util.Date modificacionFecha;
	private String modificacionTerminal;
	private Boolean auxFlgPreparado=Boolean.FALSE;
	private String flgActualizar;
	private BigDecimal ROWNUM_ ;
	private String estadoaux;
	
	
	public String getEstadoaux() {
		return estadoaux;
	}
	public void setEstadoaux(String estadoaux) {
		this.estadoaux = estadoaux;
	}
	public String getFlgActualizar() {
		return flgActualizar;
	}
	public void setFlgActualizar(String flgActualizar) {
		this.flgActualizar = flgActualizar;
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
	public BigDecimal getIdAdicional() {
		return idAdicional;
	}
	public void setIdAdicional(BigDecimal idAdicional) {
		this.idAdicional = idAdicional;
	}
	public BigDecimal getOrdenColumna() {
		return ordenColumna;
	}
	public void setOrdenColumna(BigDecimal ordenColumna) {
		this.ordenColumna = ordenColumna;
	}
	public String getNombreCampo() {
		return nombreCampo;
	}
	public void setNombreCampo(String nombreCampo) {
		this.nombreCampo = nombreCampo;
	}
	public String getDescripcionCampo() {
		return descripcionCampo;
	}
	public void setDescripcionCampo(String descripcionCampo) {
		this.descripcionCampo = descripcionCampo;
	}
	public BigDecimal getLongitudCampo() {
		return longitudCampo;
	}
	public void setLongitudCampo(BigDecimal longitudCampo) {
		this.longitudCampo = longitudCampo;
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
	public Boolean getAuxFlgPreparado() {
		return auxFlgPreparado;
	}
	public void setAuxFlgPreparado(Boolean auxFlgPreparado) {
		this.auxFlgPreparado = auxFlgPreparado;
	}
	
	
	
	
}
