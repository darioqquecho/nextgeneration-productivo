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
 * 
 * 
 * @tabla SGALERTASSYS.FUENTE_HISTORIA
*/
@Entity
@Table(name = "FUENTE_HISTORIA",schema = "SGALERTASSYS")
public class FuenteHistoria implements java.io.Serializable{
	private static final long serialVersionUID = 1L;

	@EmbeddedId
	private FuenteHistoriaPk pk;

	@Column(name = "ANIO", nullable = true)
	private Integer anio;

	@Column(name = "CLAVE_ENTERA", nullable = true)
	private Integer claveEntera;

	@Size(min = 0, max = 200)
	@Column(name = "CLAVE_CADENA", length = 200, nullable = true)
	private String claveCadena;

	@Size(min = 0, max = 3)
	@Column(name = "ESTADO", length = 3, nullable = true)
	private String estado;

	@Size(min = 0, max = 50)
	@Column(name = "CREACION_USUARIO", length = 50, nullable = true)
	private String creacionUsuario;

	@JsonSerialize(using = USerializers.DateSerializer.class)
	@JsonDeserialize(using = UDeserializers.DateDeserializer.class)
	@Column(name = "CREACION_FECHA", nullable = true)
	private java.util.Date creacionFecha;

	@Size(min = 0, max = 50)
	@Column(name = "CREACION_TERMINAL", length = 50, nullable = true)
	private String creacionTerminal;

	@Size(min = 0, max = 50)
	@Column(name = "MODIFICACION_USUARIO", length = 50, nullable = true)
	private String modificacionUsuario;

	@JsonSerialize(using = USerializers.DateSerializer.class)
	@JsonDeserialize(using = UDeserializers.DateDeserializer.class)
	@Column(name = "MODIFICACION_FECHA", nullable = true)
	private java.util.Date modificacionFecha;

	@Size(min = 0, max = 50)
	@Column(name = "MODIFICACION_TERMINAL", length = 50, nullable = true)
	private String modificacionTerminal;

	@Column(name = "ID_FUENTE_HISTORIA", nullable = true)
	private Integer idFuenteHistoria;

	@Transient
	private Boolean auxFlgPreparado=Boolean.FALSE;


	public FuenteHistoria() {
		pk = new FuenteHistoriaPk();
	}


	public FuenteHistoria(FuenteHistoriaPk pk) {
		this.pk = pk;
	}

	public FuenteHistoriaPk getPk() {
		return pk;
	}

	public void setPk(FuenteHistoriaPk pk) {
		this.pk = pk;
	}
	/**
	 *  
	 * 
	 * @campo ANIO
	*/
	public Integer getAnio() {
		return anio;
	}

	/**
	 *  
	 * 
	 * @campo ANIO
	*/
	public void setAnio(Integer anio) {
		this.anio = anio;
	}
	/**
	 *  
	 * 
	 * @campo CLAVE_ENTERA
	*/
	public Integer getClaveEntera() {
		return claveEntera;
	}

	/**
	 *  
	 * 
	 * @campo CLAVE_ENTERA
	*/
	public void setClaveEntera(Integer claveEntera) {
		this.claveEntera = claveEntera;
	}
	/**
	 *  
	 * 
	 * @campo CLAVE_CADENA
	*/
	public String getClaveCadena() {
		return claveCadena;
	}

	/**
	 *  
	 * 
	 * @campo CLAVE_CADENA
	*/
	public void setClaveCadena(String claveCadena) {
		this.claveCadena = claveCadena;
	}
	/**
	 *  
	 * 
	 * @campo ESTADO
	*/
	public String getEstado() {
		return estado;
	}

	/**
	 *  
	 * 
	 * @campo ESTADO
	*/
	public void setEstado(String estado) {
		this.estado = estado;
	}
	/**
	 *  
	 * 
	 * @campo CREACION_USUARIO
	*/
	public String getCreacionUsuario() {
		return creacionUsuario;
	}

	/**
	 *  
	 * 
	 * @campo CREACION_USUARIO
	*/
	public void setCreacionUsuario(String creacionUsuario) {
		this.creacionUsuario = creacionUsuario;
	}
	/**
	 *  
	 * 
	 * @campo CREACION_FECHA
	*/
	public java.util.Date getCreacionFecha() {
		return creacionFecha;
	}

	/**
	 *  
	 * 
	 * @campo CREACION_FECHA
	*/
	public void setCreacionFecha(java.util.Date creacionFecha) {
		this.creacionFecha = creacionFecha;
	}
	/**
	 *  
	 * 
	 * @campo CREACION_TERMINAL
	*/
	public String getCreacionTerminal() {
		return creacionTerminal;
	}

	/**
	 *  
	 * 
	 * @campo CREACION_TERMINAL
	*/
	public void setCreacionTerminal(String creacionTerminal) {
		this.creacionTerminal = creacionTerminal;
	}
	/**
	 *  
	 * 
	 * @campo MODIFICACION_USUARIO
	*/
	public String getModificacionUsuario() {
		return modificacionUsuario;
	}

	/**
	 *  
	 * 
	 * @campo MODIFICACION_USUARIO
	*/
	public void setModificacionUsuario(String modificacionUsuario) {
		this.modificacionUsuario = modificacionUsuario;
	}
	/**
	 *  
	 * 
	 * @campo MODIFICACION_FECHA
	*/
	public java.util.Date getModificacionFecha() {
		return modificacionFecha;
	}

	/**
	 *  
	 * 
	 * @campo MODIFICACION_FECHA
	*/
	public void setModificacionFecha(java.util.Date modificacionFecha) {
		this.modificacionFecha = modificacionFecha;
	}
	/**
	 *  
	 * 
	 * @campo MODIFICACION_TERMINAL
	*/
	public String getModificacionTerminal() {
		return modificacionTerminal;
	}

	/**
	 *  
	 * 
	 * @campo MODIFICACION_TERMINAL
	*/
	public void setModificacionTerminal(String modificacionTerminal) {
		this.modificacionTerminal = modificacionTerminal;
	}
	/**
	 *  
	 * 
	 * @campo ID_FUENTE_HISTORIA
	*/
	public Integer getIdFuenteHistoria() {
		return idFuenteHistoria;
	}

	/**
	 *  
	 * 
	 * @campo ID_FUENTE_HISTORIA
	*/
	public void setIdFuenteHistoria(Integer idFuenteHistoria) {
		this.idFuenteHistoria = idFuenteHistoria;
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
