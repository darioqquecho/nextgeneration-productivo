package net.royal.spring.alertas.dominio.dto;

import net.royal.spring.alertas.dominio.Colormast;

/**
 * Maestro de colores
 * 
 * @tabla SGCORESYS.COLORMAST
*/
public class DtoColormast implements java.io.Serializable{


	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String color;
	private String descripcioncorta;
	private String descripcionextranjera;
	private String estado;
	private java.util.Date ultimafechamodif;
	private String ultimousuario;
	private String colorrgb;
	private String nombrehojaestilos;
	private String hexadecimal;

	public String getColor() {
		return color;
	}

	public void setColor(String color) {
		this.color = color;
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
	public java.util.Date getUltimafechamodif() {
		return ultimafechamodif;
	}

	public void setUltimafechamodif(java.util.Date ultimafechamodif) {
		this.ultimafechamodif = ultimafechamodif;
	}
	public String getUltimousuario() {
		return ultimousuario;
	}

	public void setUltimousuario(String ultimousuario) {
		this.ultimousuario = ultimousuario;
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
	public Colormast obtenerBean() {
		Colormast bean = new Colormast();
		return obtenerBean(bean);
	}

	public Colormast obtenerBean(Colormast bean) {
		if (bean == null)
			bean = new Colormast();
		bean.getPk().setColor(color);
		bean.setDescripcioncorta(descripcioncorta);
		bean.setDescripcionextranjera(descripcionextranjera);
		bean.setEstado(estado);
		bean.setUltimafechamodif(ultimafechamodif);
		bean.setUltimousuario(ultimousuario);
		bean.setColorrgb(colorrgb);
		bean.setNombrehojaestilos(nombrehojaestilos);
		bean.setHexadecimal(hexadecimal);


		return bean;
	}

}
