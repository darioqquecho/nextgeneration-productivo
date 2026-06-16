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
 * @tabla SGALERTASSYS.TIPO_DATO
*/
@Entity
@Table(name = "TIPO_DATO")
public class TipoDato implements java.io.Serializable{
	private static final long serialVersionUID = 1L;

	@EmbeddedId
	private TipoDatoPk pk;

	@Size(min = 0, max = 200)
	@Column(name = "NOMBRE", length = 200, nullable = true)
	private String nombre;

	@Size(min = 0, max = 500)
	@Column(name = "DESCRIPCION", length = 500, nullable = true)
	private String descripcion;

	@Size(min = 0, max = 1)
	@Column(name = "ESTADO", length = 1, nullable = true)
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

	@Transient
	private Boolean auxFlgPreparado=Boolean.FALSE;


	public TipoDato() {
		pk = new TipoDatoPk();
	}


	public TipoDato(TipoDatoPk pk) {
		this.pk = pk;
	}

	public TipoDatoPk getPk() {
		return pk;
	}

	public void setPk(TipoDatoPk pk) {
		this.pk = pk;
	}
	/**
	 *  
	 * 
	 * @campo NOMBRE
	*/
	public String getNombre() {
		return nombre;
	}

	/**
	 *  
	 * 
	 * @campo NOMBRE
	*/
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	/**
	 *  
	 * 
	 * @campo DESCRIPCION
	*/
	public String getDescripcion() {
		return descripcion;
	}

	/**
	 *  
	 * 
	 * @campo DESCRIPCION
	*/
	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
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
	public Boolean getAuxFlgPreparado() {
		if (auxFlgPreparado==null)
			return Boolean.FALSE;
		return auxFlgPreparado;
	}

	public void setAuxFlgPreparado(Boolean auxFlgPreparado) {
		this.auxFlgPreparado = auxFlgPreparado;
	}

}
