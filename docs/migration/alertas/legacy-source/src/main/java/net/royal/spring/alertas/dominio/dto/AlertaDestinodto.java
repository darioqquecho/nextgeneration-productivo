package net.royal.spring.alertas.dominio.dto;

import java.math.BigDecimal;

public class AlertaDestinodto {

	private BigDecimal idDestino;
	private String correoDestino;
	private String idTipoDestino;
	private String nombrePersona;
	private BigDecimal idPersona;
	private String estado;
	private BigDecimal	ROWNUM_ ;

	public BigDecimal getROWNUM_() {
		return ROWNUM_;
	}
	public void setROWNUM_(BigDecimal rOWNUM_) {
		ROWNUM_ = rOWNUM_;
	}
	public BigDecimal getIdDestino() {
		return idDestino;
	}
	public void setIdDestino(BigDecimal idDestino) {
		this.idDestino = idDestino;
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
	
	
}
