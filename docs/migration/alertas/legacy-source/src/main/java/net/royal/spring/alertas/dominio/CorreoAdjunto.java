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
 * @tabla SGALERTASSYS.CORREO_ADJUNTO
*/
@Entity
@Table(name = "CORREO_ADJUNTO",schema="SGALERTASSYS")
public class CorreoAdjunto implements java.io.Serializable{
	private static final long serialVersionUID = 1L;

	@EmbeddedId
	private CorreoAdjuntoPk pk;

	@Size(min = 0, max = 250)
	@Column(name = "NOMBRE_ARCHIVO", length = 250, nullable = true)
	private String nombreArchivo;

	@Size(min = 0, max = 4000)
	@Column(name = "RUTA_COMPLETA", length = 4000, nullable = true)
	private String rutaCompleta;

	@Column(name = "CUERPO_ADJUNTO")
	private byte[] cuerpoAdjunto;

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


	public CorreoAdjunto() {
		pk = new CorreoAdjuntoPk();
	}


	public CorreoAdjunto(CorreoAdjuntoPk pk) {
		this.pk = pk;
	}

	public CorreoAdjuntoPk getPk() {
		return pk;
	}

	public void setPk(CorreoAdjuntoPk pk) {
		this.pk = pk;
	}
	/**
	 *  
	 * 
	 * @campo NOMBRE_ARCHIVO
	*/
	public String getNombreArchivo() {
		return nombreArchivo;
	}

	/**
	 *  
	 * 
	 * @campo NOMBRE_ARCHIVO
	*/
	public void setNombreArchivo(String nombreArchivo) {
		this.nombreArchivo = nombreArchivo;
	}
	/**
	 *  
	 * 
	 * @campo RUTA_COMPLETA
	*/
	public String getRutaCompleta() {
		return rutaCompleta;
	}

	/**
	 *  
	 * 
	 * @campo RUTA_COMPLETA
	*/
	public void setRutaCompleta(String rutaCompleta) {
		this.rutaCompleta = rutaCompleta;
	}
	/**
	 *  
	 * 
	 * @campo CUERPO_ADJUNTO
	*/
	public byte[] getCuerpoAdjunto() {
		return cuerpoAdjunto;
	}

	/**
	 *  
	 * 
	 * @campo CUERPO_ADJUNTO
	*/
	public void setCuerpoAdjunto(byte[] cuerpoAdjunto) {
		this.cuerpoAdjunto = cuerpoAdjunto;
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
