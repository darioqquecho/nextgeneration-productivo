package net.royal.spring.alertas.dominio.dto;

import java.math.BigDecimal;

import com.fasterxml.jackson.annotation.JsonIgnore;

import net.royal.spring.framework.modelo.generico.DominioTransaccion;

public class DtoBdCampo extends DominioTransaccion{
	private String esquema;
	private String objeto;
		
	private String columna;	
	private String tipoDato;
	private String nulo;
	
	private BigDecimal longitud;
	private BigDecimal precision;
	private BigDecimal escala;
	private BigDecimal columnaId;
	
	private String comparacion=DtoBdObject.COMPARAR_IGUAL;
	
	public DtoBdCampo() {}
	public DtoBdCampo(String esquema,String objeto,String columna) {
		this.esquema=esquema;
		this.objeto=objeto;
		this.columna=columna;
	}
	public DtoBdCampo(String esquema,String objeto,String columna,String tipoDato,BigDecimal longitud) {
		this.esquema=esquema;
		this.objeto=objeto;
		this.columna=columna;
		this.tipoDato=tipoDato;
		this.longitud=longitud;
	}
	
	public BigDecimal getColumnaId() {
		return columnaId;
	}
	public void setColumnaId(BigDecimal columnaId) {
		this.columnaId = columnaId;
	}
	public String getColumna() {
		return columna;
	}
	public void setColumna(String columna) {
		this.columna = columna;
	}
	public String getTipoDato() {
		return tipoDato;
	}
	public void setTipoDato(String tipoDato) {
		this.tipoDato = tipoDato;
	}
	public String getNulo() {
		return nulo;
	}
	public void setNulo(String nulo) {
		this.nulo = nulo;
	}
	public BigDecimal getLongitud() {
		if (longitud==null)
			longitud=new BigDecimal(0);
		return longitud;
	}
	public void setLongitud(BigDecimal longitud) {
		this.longitud = longitud;
	}
	public BigDecimal getPrecision() {
		return precision;
	}
	public void setPrecision(BigDecimal precision) {
		this.precision = precision;
	}
	public BigDecimal getEscala() {
		return escala;
	}
	public void setEscala(BigDecimal escala) {
		this.escala = escala;
	}
	public String getEsquema() {
		return esquema;
	}
	public void setEsquema(String esquema) {
		this.esquema = esquema;
	}
	public String getObjeto() {
		return objeto;
	}
	public void setObjeto(String objeto) {
		this.objeto = objeto;
	}
	public String getComparacion() {
		return comparacion;
	}
	public void setComparacion(String comparacion) {
		this.comparacion = comparacion;
	}		
}
