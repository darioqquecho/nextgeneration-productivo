package net.royal.spring.alertas.dominio.dto;

import java.math.BigDecimal;
import java.util.Date;

public class DtoEjecucionlog {
	private Integer ejecucionlogId;
	private Integer reglaNegocioId;
	private String javalog;
	private String splog;
	private String resumenlog;
	private Date creacionFecha;
	
	private Integer registrosExito;
	private Integer registrosError;
	
	public Integer getEjecucionlogId() {
		return ejecucionlogId;
	}
	public void setEjecucionlogId(Integer ejecucionlogId) {
		this.ejecucionlogId = ejecucionlogId;
	}
	public Integer getReglaNegocioId() {
		return reglaNegocioId;
	}
	public void setReglaNegocioId(Integer reglaNegocioId) {
		this.reglaNegocioId = reglaNegocioId;
	}
	public String getJavalog() {
		return javalog;
	}
	public void setJavalog(String javalog) {
		this.javalog = javalog;
	}
	public String getSplog() {
		return splog;
	}
	public void setSplog(String splog) {
		this.splog = splog;
	}
	public String getResumenlog() {
		return resumenlog;
	}
	public void setResumenlog(String resumenlog) {
		this.resumenlog = resumenlog;
	}
	public Date getCreacionFecha() {
		return creacionFecha;
	}
	public void setCreacionFecha(Date creacionFecha) {
		this.creacionFecha = creacionFecha;
	}
	public Integer getRegistrosExito() {
		return registrosExito;
	}
	public void setRegistrosExito(Integer registrosExito) {
		this.registrosExito = registrosExito;
	}
	public Integer getRegistrosError() {
		return registrosError;
	}
	public void setRegistrosError(Integer registrosError) {
		this.registrosError = registrosError;
	}
	
	
}
