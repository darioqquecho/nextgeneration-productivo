package net.royal.spring.alertas.dominio.dto;

import java.math.BigDecimal;
import java.util.Date;

public class LogAlertaDto {
	
	private BigDecimal idLogAlerta;
	private BigDecimal idReglaNegocio;
	private BigDecimal idFuenteAlerta;
	private String nombre;
	private Date creacionFecha;
	private String flgEnviado;
	private Integer cantidadRegistros;
	
	
	//Log Alerta detalle
	private BigDecimal nroRegistro;
	private String campoNombre;
	private String campoValor;
	private String estado;
	
	
	public String getEstado() {
		return estado;
	}
	public void setEstado(String estado) {
		this.estado = estado;
	}
	private Integer	ROWNUM_ ;

	
	public Integer getROWNUM_() {
		return ROWNUM_;
	}
	public void setROWNUM_(Integer rOWNUM_) {
		ROWNUM_ = rOWNUM_;
	}
	public BigDecimal getNroRegistro() {
		return nroRegistro;
	}
	public void setNroRegistro(BigDecimal nroRegistro) {
		this.nroRegistro = nroRegistro;
	}
	public String getCampoNombre() {
		return campoNombre;
	}
	public void setCampoNombre(String campoNombre) {
		this.campoNombre = campoNombre;
	}
	public String getCampoValor() {
		return campoValor;
	}
	public void setCampoValor(String campoValor) {
		this.campoValor = campoValor;
	}
	public BigDecimal getIdLogAlerta() {
		return idLogAlerta;
	}
	public void setIdLogAlerta(BigDecimal idLogAlerta) {
		this.idLogAlerta = idLogAlerta;
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
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	public Date getCreacionFecha() {
		return creacionFecha;
	}
	public void setCreacionFecha(Date creacionFecha) {
		this.creacionFecha = creacionFecha;
	}
	public String getFlgEnviado() {
		return flgEnviado;
	}
	public void setFlgEnviado(String flgEnviado) {
		this.flgEnviado = flgEnviado;
	}
	public Integer getCantidadRegistros() {
		return cantidadRegistros;
	}
	public void setCantidadRegistros(Integer cantidadRegistros) {
		this.cantidadRegistros = cantidadRegistros;
	}
	
	
	
	
}
