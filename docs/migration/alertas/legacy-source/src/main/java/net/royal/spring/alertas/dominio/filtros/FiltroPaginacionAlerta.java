package net.royal.spring.alertas.dominio.filtros;

import java.math.BigDecimal;
import java.util.Date;

import net.royal.spring.framework.modelo.generico.DominioPaginacion;

public class FiltroPaginacionAlerta {
	private DominioPaginacion paginacion;

	private BigDecimal id_alerta;
   private BigDecimal id_regla_negocio;
   private Date  fechaPreparacionInicio;
   private Date   fechaPreparacionFin;
   private String  p_resumenlogalerta;
   
   
   
public BigDecimal getId_alerta() {
	return id_alerta;
}
public void setId_alerta(BigDecimal id_alerta) {
	this.id_alerta = id_alerta;
}
public DominioPaginacion getPaginacion() {
	return paginacion;
}
public void setPaginacion(DominioPaginacion paginacion) {
	this.paginacion = paginacion;
}
public BigDecimal getId_regla_negocio() {
	return id_regla_negocio;
}
public void setId_regla_negocio(BigDecimal id_regla_negocio) {
	this.id_regla_negocio = id_regla_negocio;
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
public String getP_resumenlogalerta() {
	return p_resumenlogalerta;
}
public void setP_resumenlogalerta(String p_resumenlogalerta) {
	this.p_resumenlogalerta = p_resumenlogalerta;
}
	
}
