package net.royal.spring.alertas.dominio.filtros;

import java.math.BigDecimal;
import java.util.Date;

import net.royal.spring.framework.modelo.generico.DominioPaginacion;

public class FiltroEjecucionlog {
	private DominioPaginacion paginacion;

	private BigDecimal idreglanegocio;
	private Date fechaPreparacionInicio;
	private Date fechaPreparacionFin;
	private String p_estado;

	public DominioPaginacion getPaginacion() {
		return paginacion;
	}

	public void setPaginacion(DominioPaginacion paginacion) {
		this.paginacion = paginacion;
	}

	public BigDecimal getIdreglanegocio() {
		return idreglanegocio;
	}

	public void setIdreglanegocio(BigDecimal idreglanegocio) {
		this.idreglanegocio = idreglanegocio;
	}

	public Date getFechaPreparacionInicio() {
		return fechaPreparacionInicio;
	}

	public void setFechaPreparacionInicio(Date fechaPreparacionInicio) {
		this.fechaPreparacionInicio = fechaPreparacionInicio;
	}

	public Date getFechaPreparacionFin() {
		return fechaPreparacionFin;
	}

	public void setFechaPreparacionFin(Date fechaPreparacionFin) {
		this.fechaPreparacionFin = fechaPreparacionFin;
	}

	public String getP_estado() {
		return p_estado;
	}

	public void setP_estado(String p_estado) {
		this.p_estado = p_estado;
	}

}
