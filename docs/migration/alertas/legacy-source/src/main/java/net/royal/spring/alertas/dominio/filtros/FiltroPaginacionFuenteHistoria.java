package net.royal.spring.alertas.dominio.filtros;

import java.math.BigDecimal;

import net.royal.spring.framework.modelo.generico.DominioPaginacion;

public class FiltroPaginacionFuenteHistoria {

	private BigDecimal id_rn;
	private String fecha1;
	private String fecha2;
	private BigDecimal claveentera;
	private String clavecadena;
	
	private DominioPaginacion paginacion;

	public BigDecimal getId_rn() {
		return id_rn;
	}

	public void setId_rn(BigDecimal id_rn) {
		this.id_rn = id_rn;
	}

	public String getFecha1() {
		return fecha1;
	}

	public void setFecha1(String fecha1) {
		this.fecha1 = fecha1;
	}

	public String getFecha2() {
		return fecha2;
	}

	public void setFecha2(String fecha2) {
		this.fecha2 = fecha2;
	}

	public BigDecimal getClaveentera() {
		return claveentera;
	}

	public void setClaveentera(BigDecimal claveentera) {
		this.claveentera = claveentera;
	}

	public String getClavecadena() {
		return clavecadena;
	}

	public void setClavecadena(String clavecadena) {
		this.clavecadena = clavecadena;
	}

	public DominioPaginacion getPaginacion() {
		return paginacion;
	}

	public void setPaginacion(DominioPaginacion paginacion) {
		this.paginacion = paginacion;
	}

	
}
