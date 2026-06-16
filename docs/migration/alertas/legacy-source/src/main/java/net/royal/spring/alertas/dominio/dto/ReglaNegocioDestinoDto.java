package net.royal.spring.alertas.dominio.dto;

import java.math.BigDecimal;

public class ReglaNegocioDestinoDto {


	private BigDecimal idReglaNegocio;
	private String correoDestino;
	private String idTipoDestino;
	private String nombrePersona;
	private BigDecimal idPersona;
	private String estado;
	private String creacionUsuario;
	private java.util.Date creacionFecha;
	private String creacionTerminal;
	private String modificacionUsuario;
	private java.util.Date modificacionFecha;
	private String modificacionTerminal;
	private String grupoCorreo;
	private BigDecimal ROWNUM_;
	private String estadoaux;
	private Boolean auxFlgPreparado=Boolean.FALSE;
	private String flgActualizar;
	
	
	
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
	public String getEstadoaux() {
		return estadoaux;
	}
	public void setEstadoaux(String estadoaux) {
		this.estadoaux = estadoaux;
	}
	public BigDecimal getIdReglaNegocio() {
		return idReglaNegocio;
	}
	public void setIdReglaNegocio(BigDecimal idReglaNegocio) {
		this.idReglaNegocio = idReglaNegocio;
	}
	public String getCorreoDestino() {
		return correoDestino;
	}
	public void setCorreoDestino(String correoDestino) {
		this.correoDestino = correoDestino;
	}
	public String getIdTipoDestino() {
		return idTipoDestino;
	}
	public void setIdTipoDestino(String idTipoDestino) {
		this.idTipoDestino = idTipoDestino;
	}
	public String getNombrePersona() {
		return nombrePersona;
	}
	public void setNombrePersona(String nombrePersona) {
		this.nombrePersona = nombrePersona;
	}
	public BigDecimal getIdPersona() {
		return idPersona;
	}
	public void setIdPersona(BigDecimal idPersona) {
		this.idPersona = idPersona;
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
	public String getGrupoCorreo() {
		return grupoCorreo;
	}
	public void setGrupoCorreo(String grupoCorreo) {
		this.grupoCorreo = grupoCorreo;
	}
	public Boolean getAuxFlgPreparado() {
		return auxFlgPreparado;
	}
	public void setAuxFlgPreparado(Boolean auxFlgPreparado) {
		this.auxFlgPreparado = auxFlgPreparado;
	}
	
	
}
