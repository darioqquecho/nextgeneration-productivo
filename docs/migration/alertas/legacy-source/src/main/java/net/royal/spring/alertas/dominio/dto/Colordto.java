package net.royal.spring.alertas.dominio.dto;

import java.math.BigDecimal;
import java.util.Date;

public class Colordto {
	
	private String color;
	private String descripcionCorta;
    private String 	descripcionExtranjera;
    private String estado;
    private Date ultimafechamodif;
    private String ultimoUsuario;
    private String colorrgb;
    private String nombrehojaestilos;
    private String hexadecimal;
    
	private BigDecimal	ROWNUM_ ;

	
	
	public BigDecimal getROWNUM_() {
		return ROWNUM_;
	}
	public void setROWNUM_(BigDecimal rOWNUM_) {
		ROWNUM_ = rOWNUM_;
	}
	public String getColor() {
		return color;
	}
	public void setColor(String color) {
		this.color = color;
	}
 
	public String getDescripcionCorta() {
		return descripcionCorta;
	}
	public void setDescripcionCorta(String descripcionCorta) {
		this.descripcionCorta = descripcionCorta;
	}  
	public String getDescripcionExtranjera() {
		return descripcionExtranjera;
	}
	public void setDescripcionExtranjera(String descripcionExtranjera) {
		this.descripcionExtranjera = descripcionExtranjera;
	}
	public String getEstado() {
		return estado;
	}
	public void setEstado(String estado) {
		this.estado = estado;
	}
	public Date getUltimafechamodif() {
		return ultimafechamodif;
	}
	public void setUltimafechamodif(Date ultimafechamodif) {
		this.ultimafechamodif = ultimafechamodif;
	}
	public String getUltimoUsuario() {
		return ultimoUsuario;
	}
	public void setUltimoUsuario(String ultimoUsuario) {
		this.ultimoUsuario = ultimoUsuario;
	}
	public String getColorrgb() {
		return colorrgb;
	}
	public void setColorrgb(String colorrgb) {
		this.colorrgb = colorrgb;
	}
	public String getNombrehojaestilos() {
		return nombrehojaestilos;
	}
	public void setNombrehojaestilos(String nombrehojaestilos) {
		this.nombrehojaestilos = nombrehojaestilos;
	}
	public String getHexadecimal() {
		return hexadecimal;
	}
	public void setHexadecimal(String hexadecimal) {
		this.hexadecimal = hexadecimal;
	}
    
    
    
	

}
