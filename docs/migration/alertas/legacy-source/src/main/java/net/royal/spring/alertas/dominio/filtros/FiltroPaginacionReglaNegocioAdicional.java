package net.royal.spring.alertas.dominio.filtros;

import java.math.BigDecimal;

import net.royal.spring.framework.modelo.generico.DominioPaginacion;

public class FiltroPaginacionReglaNegocioAdicional {
	
	private BigDecimal idReglaNegocio;
	private DominioPaginacion paginacion;
	

	public DominioPaginacion getPaginacion() {
		return paginacion;
	}

	public void setPaginacion(DominioPaginacion paginacion) {
		this.paginacion = paginacion;
	}

	public BigDecimal getIdReglaNegocio() {
		return idReglaNegocio;
	}

	public void setIdReglaNegocio(BigDecimal idReglaNegocio) {
		this.idReglaNegocio = idReglaNegocio;
	}
	
	
}
