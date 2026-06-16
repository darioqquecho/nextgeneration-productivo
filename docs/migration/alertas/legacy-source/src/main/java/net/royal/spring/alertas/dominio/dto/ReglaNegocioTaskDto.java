package net.royal.spring.alertas.dominio.dto;

import java.math.BigDecimal;

public class ReglaNegocioTaskDto {
	private BigDecimal idReglaNegocio;
	private BigDecimal cantidadCorreosEnviar;	
	private Integer correosPendientes;
	private Integer erroresCantidad;
	private String flgCorreoPrueba;
	private BigDecimal cantidadErroresEnvio;
	
	public BigDecimal getIdReglaNegocio() {
		return idReglaNegocio;
	}
	public void setIdReglaNegocio(BigDecimal idReglaNegocio) {
		this.idReglaNegocio = idReglaNegocio;
	}
	public BigDecimal getCantidadCorreosEnviar() {
		return cantidadCorreosEnviar;
	}
	public void setCantidadCorreosEnviar(BigDecimal cantidadCorreosEnviar) {
		this.cantidadCorreosEnviar = cantidadCorreosEnviar;
	}
	public Integer getCorreosPendientes() {
		return correosPendientes;
	}
	public void setCorreosPendientes(Integer correosPendientes) {
		this.correosPendientes = correosPendientes;
	}
	public Integer getErroresCantidad() {
		return erroresCantidad;
	}
	public void setErroresCantidad(Integer erroresCantidad) {
		this.erroresCantidad = erroresCantidad;
	}
	public String getFlgCorreoPrueba() {
		return flgCorreoPrueba;
	}
	public void setFlgCorreoPrueba(String flgCorreoPrueba) {
		this.flgCorreoPrueba = flgCorreoPrueba;
	}
	public BigDecimal getCantidadErroresEnvio() {
		return cantidadErroresEnvio;
	}
	public void setCantidadErroresEnvio(BigDecimal cantidadErroresEnvio) {
		this.cantidadErroresEnvio = cantidadErroresEnvio;
	}	
	
}
