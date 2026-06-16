package net.royal.spring.alertas.dominio.filtros;

import java.math.BigDecimal;

import net.royal.spring.framework.modelo.generico.DominioPaginacion;

public class FiltroPaginacionReglaNegocio {
	private BigDecimal idReglaNegocio;
	private BigDecimal idAreaNegocio;
	private String idCompania;
	private String estado;
	private DominioPaginacion paginacion;
	public BigDecimal getIdReglaNegocio() {
		return idReglaNegocio;
	}
	public void setIdReglaNegocio(BigDecimal idReglaNegocio) {
		this.idReglaNegocio = idReglaNegocio;
	}
	public BigDecimal getIdAreaNegocio() {
		return idAreaNegocio;
	}
	public void setIdAreaNegocio(BigDecimal idAreaNegocio) {
		this.idAreaNegocio = idAreaNegocio;
	}
	public String getIdCompania() {
		return idCompania;
	}
	public void setIdCompania(String idCompania) {
		this.idCompania = idCompania;
	}
	public String getEstado() {
		return estado;
	}
	public void setEstado(String estado) {
		this.estado = estado;
	}
	public DominioPaginacion getPaginacion() {
		return paginacion;
	}
	public void setPaginacion(DominioPaginacion paginacion) {
		this.paginacion = paginacion;
	}
	
	
}
