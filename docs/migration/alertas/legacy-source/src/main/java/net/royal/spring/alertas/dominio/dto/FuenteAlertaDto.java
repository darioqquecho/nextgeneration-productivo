package net.royal.spring.alertas.dominio.dto;

import java.math.BigDecimal;
import java.util.Date;

public class FuenteAlertaDto {
	
	private Integer idFuenteAlertaPk;
	private String nombre;
	private Date creacionFecha;
	private Integer cantidadRegistros;
	private Integer	ROWNUM_ ;

	public Integer getROWNUM_() {
		return ROWNUM_;
	}
	public void setROWNUM_(Integer rOWNUM_) {
		ROWNUM_ = rOWNUM_;
	}
	public Integer getIdFuenteAlertaPk() {
		return idFuenteAlertaPk;
	}
	public void setIdFuenteAlertaPk(Integer idFuenteAlertaPk) {
		this.idFuenteAlertaPk = idFuenteAlertaPk;
	}
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	public Date getCreacionFecha() {
		return creacionFecha;
	}
	public void setCreacionFecha(Date creacionFecha) {
		this.creacionFecha = creacionFecha;
	}
	public Integer getCantidadRegistros() {
		return cantidadRegistros;
	}
	public void setCantidadRegistros(Integer cantidadRegistros) {
		this.cantidadRegistros = cantidadRegistros;
	}
	
	

}
