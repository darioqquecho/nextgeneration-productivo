package net.royal.spring.alertas.dominio.dto;

import java.math.BigDecimal;

public class SelectorGenericoDto {
	
	private String codigocadena;
	private BigDecimal codigo;
	private String nombre;
	private String busqueda;
	private String documento;
	private String correoelectronico;
	private String correointerno;
	private String estado;
	private BigDecimal ROWNUM_;
	
	
	
	public String getEstado() {
		return estado;
	}
	public void setEstado(String estado) {
		this.estado = estado;
	}
	public BigDecimal getROWNUM_() {
		return ROWNUM_;
	}
	public void setROWNUM_(BigDecimal rOWNUM_) {
		ROWNUM_ = rOWNUM_;
	}
	public String getCodigocadena() {
		return codigocadena;
	}
	public void setCodigocadena(String codigocadena) {
		this.codigocadena = codigocadena;
	}
	public BigDecimal getCodigo() {
		return codigo;
	}
	public void setCodigo(BigDecimal codigo) {
		this.codigo = codigo;
	}
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	public String getBusqueda() {
		return busqueda;
	}
	public void setBusqueda(String busqueda) {
		this.busqueda = busqueda;
	}
	public String getDocumento() {
		return documento;
	}
	public void setDocumento(String documento) {
		this.documento = documento;
	}
	public String getCorreoelectronico() {
		return correoelectronico;
	}
	public void setCorreoelectronico(String correoelectronico) {
		this.correoelectronico = correoelectronico;
	}
	public String getCorreointerno() {
		return correointerno;
	}
	public void setCorreointerno(String correointerno) {
		this.correointerno = correointerno;
	}
	
	
}
