package net.royal.spring.alertas.dominio.filtros;

import java.math.BigDecimal;
import java.util.Date;

import net.royal.spring.framework.modelo.generico.DominioPaginacion;

public class FiltroPaginacionLogAlerta {
	private DominioPaginacion paginacion;
	
	private BigDecimal id_log_alerta;
	private BigDecimal idFuenteAlerta;
	private Date fechaInicio;
	private Date fechaFin;
	private BigDecimal idreglanegocio;
	
	
	public BigDecimal getIdreglanegocio() {
		return idreglanegocio;
	}
	public void setIdreglanegocio(BigDecimal idreglanegocio) {
		this.idreglanegocio = idreglanegocio;
	}
	public BigDecimal getId_log_alerta() {
		return id_log_alerta;
	}
	public void setId_log_alerta(BigDecimal id_log_alerta) {
		this.id_log_alerta = id_log_alerta;
	}
	private String   fechaDesde;
	private String   fechaHasta;
	public DominioPaginacion getPaginacion() {
		return paginacion;
	}
	public void setPaginacion(DominioPaginacion paginacion) {
		this.paginacion = paginacion;
	}
	public BigDecimal getIdFuenteAlerta() {
		return idFuenteAlerta;
	}
	public void setIdFuenteAlerta(BigDecimal idFuenteAlerta) {
		this.idFuenteAlerta = idFuenteAlerta;
	}
	public Date getFechaInicio() {
		return fechaInicio;
	}
	public void setFechaInicio(Date fechaInicio) {
		this.fechaInicio = fechaInicio;
	}
	public Date getFechaFin() {
		return fechaFin;
	}
	public void setFechaFin(Date fechaFin) {
		this.fechaFin = fechaFin;
	}
	public String getFechaDesde() {
		return fechaDesde;
	}
	public void setFechaDesde(String fechaDesde) {
		this.fechaDesde = fechaDesde;
	}
	public String getFechaHasta() {
		return fechaHasta;
	}
	public void setFechaHasta(String fechaHasta) {
		this.fechaHasta = fechaHasta;
	}
	
	
	
}
