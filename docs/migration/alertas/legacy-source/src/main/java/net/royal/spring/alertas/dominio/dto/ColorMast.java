package net.royal.spring.alertas.dominio.dto;

import java.io.Serializable;

public class ColorMast implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private ColorMastPk pk;
	private String descripcioncorta;
	private String descripcionextranjera;
	private String estado;
	private String colorRgb;
	private String nombreHojaEstilos;
	private String hexadecimal;

	public ColorMast() {
		pk = new ColorMastPk();
	}

	public ColorMastPk getPk() {
		return pk;
	}

	public void setPk(ColorMastPk pk) {
		this.pk = pk;
	}

	public String getDescripcioncorta() {
		return descripcioncorta;
	}

	public void setDescripcioncorta(String descripcioncorta) {
		this.descripcioncorta = descripcioncorta;
	}

	public String getDescripcionextranjera() {
		return descripcionextranjera;
	}

	public void setDescripcionextranjera(String descripcionextranjera) {
		this.descripcionextranjera = descripcionextranjera;
	}

	public String getEstado() {
		return estado;
	}

	public void setEstado(String estado) {
		this.estado = estado;
	}

	public String getColorRgb() {
		return colorRgb;
	}

	public void setColorRgb(String colorRgb) {
		this.colorRgb = colorRgb;
	}
	
	public String getNombreHojaEstilos() {
		return nombreHojaEstilos;
	}

	public void setNombreHojaEstilos(String nombreHojaEstilos) {
		this.nombreHojaEstilos = nombreHojaEstilos;
	}
	
	public String getHexadecimal() {
		return hexadecimal;
	}

	public void setHexadecimal(String hexadecimal) {
		this.hexadecimal = hexadecimal;
	}


}
