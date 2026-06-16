package net.royal.spring.alertas.dominio;

import jakarta.persistence.Column;
import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import jakarta.persistence.Transient;
import jakarta.validation.constraints.Size;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;

import net.royal.spring.framework.web.rest.UDeserializers;
import net.royal.spring.framework.web.rest.USerializers;

/**
 * Maestro de colores
 * 
 * @tabla SGCORESYS.COLORMAST
*/
@Entity
@Table(name = "COLORMAST" , schema="SGALERTASSYS")
public class Colormast implements java.io.Serializable{
	private static final long serialVersionUID = 1L;

	@EmbeddedId
	private ColormastPk pk;

	@Size(min = 0, max = 30)
	@Column(name = "DESCRIPCIONCORTA", length = 30, nullable = true)
	private String descripcioncorta;

	@Size(min = 0, max = 40)
	@Column(name = "DESCRIPCIONEXTRANJERA", length = 40, nullable = true)
	private String descripcionextranjera;

	@Size(min = 0, max = 1)
	@Column(name = "ESTADO", length = 1, nullable = true)
	private String estado;

	@JsonSerialize(using = USerializers.DateSerializer.class)
	@JsonDeserialize(using = UDeserializers.DateDeserializer.class)
	@Column(name = "ULTIMAFECHAMODIF", nullable = true)
	private java.util.Date ultimafechamodif;

	@Size(min = 0, max = 20)
	@Column(name = "ULTIMOUSUARIO", length = 20, nullable = true)
	private String ultimousuario;

	@Size(min = 0, max = 200)
	@Column(name = "COLORRGB", length = 200, nullable = true)
	private String colorrgb;

	@Size(min = 0, max = 200)
	@Column(name = "NOMBREHOJAESTILOS", length = 200, nullable = true)
	private String nombrehojaestilos;

	@Size(min = 0, max = 200)
	@Column(name = "HEXADECIMAL", length = 200, nullable = true)
	private String hexadecimal;

	@Transient
	private Boolean auxFlgPreparado=Boolean.FALSE;


	public Colormast() {
		pk = new ColormastPk();
	}


	public Colormast(ColormastPk pk) {
		this.pk = pk;
	}

	public ColormastPk getPk() {
		return pk;
	}

	public void setPk(ColormastPk pk) {
		this.pk = pk;
	}
	/**
	 * Descripcion Corta
	 * 
	 * @campo DESCRIPCIONCORTA
	*/
	public String getDescripcioncorta() {
		return descripcioncorta;
	}

	/**
	 * Descripcion Corta
	 * 
	 * @campo DESCRIPCIONCORTA
	*/
	public void setDescripcioncorta(String descripcioncorta) {
		this.descripcioncorta = descripcioncorta;
	}
	/**
	 * Descripcion Extranjera
	 * 
	 * @campo DESCRIPCIONEXTRANJERA
	*/
	public String getDescripcionextranjera() {
		return descripcionextranjera;
	}

	/**
	 * Descripcion Extranjera
	 * 
	 * @campo DESCRIPCIONEXTRANJERA
	*/
	public void setDescripcionextranjera(String descripcionextranjera) {
		this.descripcionextranjera = descripcionextranjera;
	}
	/**
	 * A = Activo , I = Inactivo
	 * 
	 * @campo ESTADO
	*/
	public String getEstado() {
		return estado;
	}

	/**
	 * A = Activo , I = Inactivo
	 * 
	 * @campo ESTADO
	*/
	public void setEstado(String estado) {
		this.estado = estado;
	}
	/**
	 * Ultima Fecha de Modificacion
	 * 
	 * @campo ULTIMAFECHAMODIF
	*/
	public java.util.Date getUltimafechamodif() {
		return ultimafechamodif;
	}

	/**
	 * Ultima Fecha de Modificacion
	 * 
	 * @campo ULTIMAFECHAMODIF
	*/
	public void setUltimafechamodif(java.util.Date ultimafechamodif) {
		this.ultimafechamodif = ultimafechamodif;
	}
	/**
	 * Ultimo Usuario de Modificacion
	 * 
	 * @campo ULTIMOUSUARIO
	*/
	public String getUltimousuario() {
		return ultimousuario;
	}

	/**
	 * Ultimo Usuario de Modificacion
	 * 
	 * @campo ULTIMOUSUARIO
	*/
	public void setUltimousuario(String ultimousuario) {
		this.ultimousuario = ultimousuario;
	}
	/**
	 * COLOR RGB
	 * 
	 * @campo COLORRGB
	*/
	public String getColorrgb() {
		return colorrgb;
	}

	/**
	 * COLOR RGB
	 * 
	 * @campo COLORRGB
	*/
	public void setColorrgb(String colorrgb) {
		this.colorrgb = colorrgb;
	}
	/**
	 * NOMBRE DE HOJA DE ESTILOS
	 * 
	 * @campo NOMBREHOJAESTILOS
	*/
	public String getNombrehojaestilos() {
		return nombrehojaestilos;
	}

	/**
	 * NOMBRE DE HOJA DE ESTILOS
	 * 
	 * @campo NOMBREHOJAESTILOS
	*/
	public void setNombrehojaestilos(String nombrehojaestilos) {
		this.nombrehojaestilos = nombrehojaestilos;
	}
	/**
	 * HEXADECIMAL
	 * 
	 * @campo HEXADECIMAL
	*/
	public String getHexadecimal() {
		return hexadecimal;
	}

	/**
	 * HEXADECIMAL
	 * 
	 * @campo HEXADECIMAL
	*/
	public void setHexadecimal(String hexadecimal) {
		this.hexadecimal = hexadecimal;
	}
	public Boolean getAuxFlgPreparado() {
		if (auxFlgPreparado==null)
			return Boolean.FALSE;
		return auxFlgPreparado;
	}

	public void setAuxFlgPreparado(Boolean auxFlgPreparado) {
		this.auxFlgPreparado = auxFlgPreparado;
	}

}
