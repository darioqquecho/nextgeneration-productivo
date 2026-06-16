package net.royal.spring.alertas.dominio.filtros;

import java.math.BigDecimal;
import java.util.Date;

import net.royal.spring.framework.modelo.generico.DominioPaginacion;

public class FiltroPaginacionFuenteAlerta {
	private DominioPaginacion paginacion;

	private BigDecimal idFuenteAlerta;
	private Date fechaPreparacionInicio;
	private Date fechaPreparacionFin;
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
 
	 
	
	
}
