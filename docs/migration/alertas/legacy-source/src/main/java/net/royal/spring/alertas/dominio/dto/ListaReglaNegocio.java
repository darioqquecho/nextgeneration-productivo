package net.royal.spring.alertas.dominio.dto;

import java.math.BigDecimal;

public class ListaReglaNegocio {

	private BigDecimal id_Regla_negocio ;
	
	private String estado;
	private String nombre;
	
	private BigDecimal	ROWNUM_ ;

	public BigDecimal getROWNUM_() {
		return ROWNUM_;
	}
	public void setROWNUM_(BigDecimal rOWNUM_) {
		ROWNUM_ = rOWNUM_;
	}
	public BigDecimal getId_Regla_negocio() {
		return id_Regla_negocio;
	}
	public void setId_Regla_negocio(BigDecimal id_Regla_negocio) {
		this.id_Regla_negocio = id_Regla_negocio;
	}
	public String getEstado() {
		return estado;
	}
	public void setEstado(String estado) {
		this.estado = estado;
	}
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	
	
	
}
