package net.royal.spring.alertas.dominio.dto;

import java.math.BigDecimal;

public class DtoTabla {
	private BigDecimal codigo;
	private String idcadena;
	private String descripcion;

	
	public String getIdcadena() {
		return idcadena;
	}

	public void setIdcadena(String idcadena) {
		this.idcadena = idcadena;
	}

	public BigDecimal getCodigo() {
		return codigo;
	}

	public void setCodigo(BigDecimal codigo) {
		this.codigo = codigo;
	}

	public String getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

}
