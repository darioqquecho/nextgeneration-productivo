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
 * @tabla SGALERTASSYS.LOG_ALERTA
*/
@Entity
@Table(name = "LOG_ALERTA" , schema ="SGALERTASSYS")
public class LogAlerta implements java.io.Serializable{
	private static final long serialVersionUID = 1L;

	@EmbeddedId
	private LogAlertaPk pk;

	@Column(name = "ID_REGLA_NEGOCIO", nullable = true)
	private Integer idReglaNegocio;

	@Column(name = "ID_FUENTE_ALERTA", nullable = true)
	private Integer idFuenteAlerta;

	@Size(min = 0, max = 200)
	@Column(name = "NOMBRE_SISTEMA", length = 200, nullable = true)
	private String nombreSistema;

	@Size(min = 0, max = 100)
	@Column(name = "NOMBRE_COMPUTADORA", length = 100, nullable = true)
	private String nombreComputadora;

	@Size(min = 0, max = 100)
	@Column(name = "NOMBRE_USUARIO_RED", length = 100, nullable = true)
	private String nombreUsuarioRed;

	@Size(min = 0, max = 100)
	@Column(name = "NOMBRE_USUARIO_BD", length = 100, nullable = true)
	private String nombreUsuarioBd;

	@Size(min = 0, max = 1)
	@Column(name = "FLG_ALERTA_GENERADA", length = 1, nullable = true)
	private String flgAlertaGenerada;

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

	@Transient
	private Boolean auxFlgPreparado=Boolean.FALSE;


	public LogAlerta() {
		pk = new LogAlertaPk();
	}


	public LogAlerta(LogAlertaPk pk) {
		this.pk = pk;
	}

	public LogAlertaPk getPk() {
		return pk;
	}

	public void setPk(LogAlertaPk pk) {
		this.pk = pk;
	}
	/**
	 *  
	 * 
	 * @campo ID_REGLA_NEGOCIO
	*/
	public Integer getIdReglaNegocio() {
		return idReglaNegocio;
	}

	/**
	 *  
	 * 
	 * @campo ID_REGLA_NEGOCIO
	*/
	public void setIdReglaNegocio(Integer idReglaNegocio) {
		this.idReglaNegocio = idReglaNegocio;
	}
	/**
	 *  
	 * 
	 * @campo ID_FUENTE_ALERTA
	*/
	public Integer getIdFuenteAlerta() {
		return idFuenteAlerta;
	}

	/**
	 *  
	 * 
	 * @campo ID_FUENTE_ALERTA
	*/
	public void setIdFuenteAlerta(Integer idFuenteAlerta) {
		this.idFuenteAlerta = idFuenteAlerta;
	}
	/**
	 *  
	 * 
	 * @campo NOMBRE_SISTEMA
	*/
	public String getNombreSistema() {
		return nombreSistema;
	}

	/**
	 *  
	 * 
	 * @campo NOMBRE_SISTEMA
	*/
	public void setNombreSistema(String nombreSistema) {
		this.nombreSistema = nombreSistema;
	}
	/**
	 *  
	 * 
	 * @campo NOMBRE_COMPUTADORA
	*/
	public String getNombreComputadora() {
		return nombreComputadora;
	}

	/**
	 *  
	 * 
	 * @campo NOMBRE_COMPUTADORA
	*/
	public void setNombreComputadora(String nombreComputadora) {
		this.nombreComputadora = nombreComputadora;
	}
	/**
	 *  
	 * 
	 * @campo NOMBRE_USUARIO_RED
	*/
	public String getNombreUsuarioRed() {
		return nombreUsuarioRed;
	}

	/**
	 *  
	 * 
	 * @campo NOMBRE_USUARIO_RED
	*/
	public void setNombreUsuarioRed(String nombreUsuarioRed) {
		this.nombreUsuarioRed = nombreUsuarioRed;
	}
	/**
	 *  
	 * 
	 * @campo NOMBRE_USUARIO_BD
	*/
	public String getNombreUsuarioBd() {
		return nombreUsuarioBd;
	}

	/**
	 *  
	 * 
	 * @campo NOMBRE_USUARIO_BD
	*/
	public void setNombreUsuarioBd(String nombreUsuarioBd) {
		this.nombreUsuarioBd = nombreUsuarioBd;
	}
	/**
	 *  
	 * 
	 * @campo FLG_ALERTA_GENERADA
	*/
	public String getFlgAlertaGenerada() {
		return flgAlertaGenerada;
	}

	/**
	 *  
	 * 
	 * @campo FLG_ALERTA_GENERADA
	*/
	public void setFlgAlertaGenerada(String flgAlertaGenerada) {
		this.flgAlertaGenerada = flgAlertaGenerada;
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
