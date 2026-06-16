package net.royal.spring.alertas.dominio.dto;

import java.math.BigDecimal;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;

import net.royal.spring.framework.modelo.generico.DominioTransaccion;

public class DtoBdComparar extends DominioTransaccion{
	private String comparacion;
	
	private String origenCnnUrl="";
	private String origenCnnUsuario="";
	private String origenCnnClave="";
	private String origenSqlCabecera="";
	private String origenSqlColumnas="";
	
	private String destinoSqlCabecera="";
	private String destinoSqlColumnas="";
	
	private List<DtoBdObject> listaResultado;
	
	public String getOrigenCnnUrl() {
		return origenCnnUrl;
	}
	public void setOrigenCnnUrl(String origenCnnUrl) {
		this.origenCnnUrl = origenCnnUrl;
	}
	public String getOrigenCnnUsuario() {
		return origenCnnUsuario;
	}
	public void setOrigenCnnUsuario(String origenCnnUsuario) {
		this.origenCnnUsuario = origenCnnUsuario;
	}
	public String getOrigenCnnClave() {
		return origenCnnClave;
	}
	public void setOrigenCnnClave(String origenCnnClave) {
		this.origenCnnClave = origenCnnClave;
	}
	public String getOrigenSqlCabecera() {
		return origenSqlCabecera;
	}
	public void setOrigenSqlCabecera(String origenSqlCabecera) {
		this.origenSqlCabecera = origenSqlCabecera;
	}
	public String getOrigenSqlColumnas() {
		return origenSqlColumnas;
	}
	public void setOrigenSqlColumnas(String origenSqlColumnas) {
		this.origenSqlColumnas = origenSqlColumnas;
	}
	public String getDestinoSqlCabecera() {
		return destinoSqlCabecera;
	}
	public void setDestinoSqlCabecera(String destinoSqlCabecera) {
		this.destinoSqlCabecera = destinoSqlCabecera;
	}
	public String getDestinoSqlColumnas() {
		return destinoSqlColumnas;
	}
	public void setDestinoSqlColumnas(String destinoSqlColumnas) {
		this.destinoSqlColumnas = destinoSqlColumnas;
	}
	public List<DtoBdObject> getListaResultado() {
		return listaResultado;
	}
	public void setListaResultado(List<DtoBdObject> listaResultado) {
		this.listaResultado = listaResultado;
	}
	public String getComparacion() {
		return comparacion;
	}
	public void setComparacion(String comparacion) {
		this.comparacion = comparacion;
	}	
	
}
