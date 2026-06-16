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
 * @tabla SGALERTASSYS.CORREO_DESTINO
*/
@Entity
@Table(name = "CORREO_DESTINO",schema="SGALERTASSYS")
public class CorreoDestino implements java.io.Serializable{
	private static final long serialVersionUID = 1L;

	@EmbeddedId
	private CorreoDestinoPk pk;

	@Size(min = 0, max = 3)
	@Column(name = "ID_TIPO_DESTINO", length = 3, nullable = true)
	private String idTipoDestino;

	@Size(min = 0, max = 200)
	@Column(name = "NOMBRE_PERSONA", length = 200, nullable = true)
	private String nombrePersona;

	@Column(name = "ID_PERSONA", nullable = true)
	private Integer idPersona;

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


	public CorreoDestino() {
		pk = new CorreoDestinoPk();
	}


	public CorreoDestino(CorreoDestinoPk pk) {
		this.pk = pk;
	}

	public CorreoDestinoPk getPk() {
		return pk;
	}

	public void setPk(CorreoDestinoPk pk) {
		this.pk = pk;
	}
	/**
	 *  
	 * 
	 * @campo ID_TIPO_DESTINO
	*/
	public String getIdTipoDestino() {
		return idTipoDestino;
	}

	/**
	 *  
	 * 
	 * @campo ID_TIPO_DESTINO
	*/
	public void setIdTipoDestino(String idTipoDestino) {
		this.idTipoDestino = idTipoDestino;
	}
	/**
	 *  
	 * 
	 * @campo NOMBRE_PERSONA
	*/
	public String getNombrePersona() {
		return nombrePersona;
	}

	/**
	 *  
	 * 
	 * @campo NOMBRE_PERSONA
	*/
	public void setNombrePersona(String nombrePersona) {
		this.nombrePersona = nombrePersona;
	}
	/**
	 *  
	 * 
	 * @campo ID_PERSONA
	*/
	public Integer getIdPersona() {
		return idPersona;
	}

	/**
	 *  
	 * 
	 * @campo ID_PERSONA
	*/
	public void setIdPersona(Integer idPersona) {
		this.idPersona = idPersona;
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
