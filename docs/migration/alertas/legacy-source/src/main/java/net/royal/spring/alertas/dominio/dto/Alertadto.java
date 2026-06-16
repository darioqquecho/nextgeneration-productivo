package net.royal.spring.alertas.dominio.dto;

import java.math.BigDecimal;
import java.util.Date;

public class Alertadto {
	private Integer idAlerta;
	
	private String resumenLogAlerta;
	private String nombre;
	private Date creacionFecha;
	private String flgCorreoGenerado;
	private Integer cantidadRegistros;
	
	private BigDecimal	ROWNUM_ ;

	public BigDecimal getROWNUM_() {
		return ROWNUM_;
	}
	public void setROWNUM_(BigDecimal rOWNUM_) {
		ROWNUM_ = rOWNUM_;
	}
	public Integer getIdAlerta() {
		return idAlerta;
	}
	public void setIdAlerta(Integer idAlerta) {
		this.idAlerta = idAlerta;
	}
	public String getResumenLogAlerta() {
		return resumenLogAlerta;
	}
	public void setResumenLogAlerta(String resumenLogAlerta) {
		this.resumenLogAlerta = resumenLogAlerta;
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
	public String getFlgCorreoGenerado() {
		return flgCorreoGenerado;
	}
	public void setFlgCorreoGenerado(String flgCorreoGenerado) {
		this.flgCorreoGenerado = flgCorreoGenerado;
	}
	public Integer getCantidadRegistros() {
		return cantidadRegistros;
	}
	public void setCantidadRegistros(Integer cantidadRegistros) {
		this.cantidadRegistros = cantidadRegistros;
	}
	
	

}
