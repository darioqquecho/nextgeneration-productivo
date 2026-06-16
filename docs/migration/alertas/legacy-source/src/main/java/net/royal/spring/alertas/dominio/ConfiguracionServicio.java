package net.royal.spring.alertas.dominio;

import jakarta.persistence.Column;
import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import jakarta.persistence.Transient;
import jakarta.validation.constraints.Size;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;

import net.royal.spring.alertas.dominio.dto.ConfiguracionCorreo;
import net.royal.spring.framework.util.UString;
import net.royal.spring.framework.web.rest.UDeserializers;
import net.royal.spring.framework.web.rest.USerializers;

/**
 * 
 * 
 * @tabla SGALERTASSYS.CONFIGURACION_SERVICIO
*/
@Entity
@Table(name = "CONFIGURACION_SERVICIO" , schema = "SGALERTASSYS")
public class ConfiguracionServicio implements java.io.Serializable{
	private static final long serialVersionUID = 1L;

	@EmbeddedId
	private ConfiguracionServicioPk pk;

	@Size(min = 0, max = 1)
	@Column(name = "FLG_CORREO_PRUEBA", length = 1, nullable = true)
	private String flgCorreoPrueba;

	@Size(min = 0, max = 200)
	@Column(name = "CORREO_PRUEBA", length = 200, nullable = true)
	private String correoPrueba;

	@Size(min = 0, max = 1)
	@Column(name = "FLG_INICIAR_SERVICIOS", length = 1, nullable = true)
	private String flgIniciarServicios;

	@JsonSerialize(using = USerializers.DateSerializer.class)
	@JsonDeserialize(using = UDeserializers.DateDeserializer.class)
	@Column(name = "ULTIMA_FECHA_INICIO_SERVICIOS", nullable = true)
	private java.util.Date ultimaFechaInicioServicios;

	@Size(min = 0, max = 1)
	@Column(name = "FLG_INICIAR_SERVICIOS_CONTROL", length = 1, nullable = true)
	private String flgIniciarServiciosControl;

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

	@Size(min = 0, max = 1)
	@Column(name = "FLG_FORZAR_REINICIO", length = 1, nullable = true)
	private String flgForzarReinicio;

	@Size(min = 0, max = 100)
	@Column(name = "CORREO_TIPO_CONFIGURACION", length = 100, nullable = true)
	private String correoTipoConfiguracion;

	@Size(min = 0, max = 1)
	@Column(name = "FLG_INICIAR_ENVIO_CORREO", length = 1, nullable = true)
	private String flgIniciarEnvioCorreo;

	@Size(min = 0, max = 200)
	@Column(name = "EMAIL_CUENTA", length = 200, nullable = true)
	private String emailCuenta;

	@Size(min = 0, max = 200)
	@Column(name = "EMAIL_CLAVE", length = 200, nullable = true)
	private String emailClave;

	@Size(min = 0, max = 1000)
	@Column(name = "EMAIL_PERFIL", length = 1000, nullable = true)
	private String emailPerfil;

	@Size(min = 0, max = 1)
	@Column(name = "FLG_EJECUTANDO_EVALUAR_REGLA", length = 1, nullable = true)
	private String flgEjecutandoEvaluarRegla;

	@JsonSerialize(using = USerializers.DateSerializer.class)
	@JsonDeserialize(using = UDeserializers.DateDeserializer.class)
	@Column(name = "SI_EJECUTANDO_EVALUAR_REGLA", nullable = true)
	private java.util.Date siEjecutandoEvaluarRegla;

	@JsonSerialize(using = USerializers.DateSerializer.class)
	@JsonDeserialize(using = UDeserializers.DateDeserializer.class)
	@Column(name = "NO_EJECUTANDO_EVALUAR_REGLA", nullable = true)
	private java.util.Date noEjecutandoEvaluarRegla;

	@Size(min = 0, max = 1)
	@Column(name = "FLG_EJECUTANDO_EXTRAER_DATA", length = 1, nullable = true)
	private String flgEjecutandoExtraerData;

	@JsonSerialize(using = USerializers.DateSerializer.class)
	@JsonDeserialize(using = UDeserializers.DateDeserializer.class)
	@Column(name = "SI_EJECUTANDO_EXTRAER_DATA", nullable = true)
	private java.util.Date siEjecutandoExtraerData;

	@JsonSerialize(using = USerializers.DateSerializer.class)
	@JsonDeserialize(using = UDeserializers.DateDeserializer.class)
	@Column(name = "NO_EJECUTANDO_EXTRAER_DATA", nullable = true)
	private java.util.Date noEjecutandoExtraerData;

	@Size(min = 0, max = 1)
	@Column(name = "FLG_EJECUTANDO_CREAR_ALERTA", length = 1, nullable = true)
	private String flgEjecutandoCrearAlerta;

	@JsonSerialize(using = USerializers.DateSerializer.class)
	@JsonDeserialize(using = UDeserializers.DateDeserializer.class)
	@Column(name = "SI_EJECUTANDO_CREAR_ALERTA", nullable = true)
	private java.util.Date siEjecutandoCrearAlerta;

	@JsonSerialize(using = USerializers.DateSerializer.class)
	@JsonDeserialize(using = UDeserializers.DateDeserializer.class)
	@Column(name = "NO_EJECUTANDO_CREAR_ALERTA", nullable = true)
	private java.util.Date noEjecutandoCrearAlerta;

	@Size(min = 0, max = 1)
	@Column(name = "FLG_EJECUTANDO_CREAR_CORREO", length = 1, nullable = true)
	private String flgEjecutandoCrearCorreo;

	@JsonSerialize(using = USerializers.DateSerializer.class)
	@JsonDeserialize(using = UDeserializers.DateDeserializer.class)
	@Column(name = "SI_EJECUTANDO_CREAR_CORREO", nullable = true)
	private java.util.Date siEjecutandoCrearCorreo;

	@JsonSerialize(using = USerializers.DateSerializer.class)
	@JsonDeserialize(using = UDeserializers.DateDeserializer.class)
	@Column(name = "NO_EJECUTANDO_CREAR_CORREO", nullable = true)
	private java.util.Date noEjecutandoCrearCorreo;

	@Size(min = 0, max = 1)
	@Column(name = "FLG_EJECUTANDO_ENVIO_CORREO", length = 1, nullable = true)
	private String flgEjecutandoEnvioCorreo;

	@JsonSerialize(using = USerializers.DateSerializer.class)
	@JsonDeserialize(using = UDeserializers.DateDeserializer.class)
	@Column(name = "SI_EJECUTANDO_ENVIO_CORREO", nullable = true)
	private java.util.Date siEjecutandoEnvioCorreo;

	@JsonSerialize(using = USerializers.DateSerializer.class)
	@JsonDeserialize(using = UDeserializers.DateDeserializer.class)
	@Column(name = "NO_EJECUTANDO_ENVIO_CORREO", nullable = true)
	private java.util.Date noEjecutandoEnvioCorreo;

	@Size(min = 0, max = 1)
	@Column(name = "FLG_ENVIAR_CORREO", length = 1, nullable = true)
	private String flgEnviarCorreo;

	@Size(min = 0, max = 1)
	@Column(name = "FLG_ENVIAR_CORREO_GENERICO", length = 1, nullable = true)
	private String flgEnviarCorreoGenerico;
		
	@Size(min = 0, max = 1)
	@Column(name = "FLG_CREAR_CORREO", length = 1, nullable = true)
	private String flgCrearCorreo;

	@Size(min = 0, max = 1)
	@Column(name = "FLG_CREAR_ALERTA", length = 1, nullable = true)
	private String flgCrearAlerta;

	@Size(min = 0, max = 1)
	@Column(name = "FLG_EXTRAER_DATA", length = 1, nullable = true)
	private String flgExtraerData;

	@Size(min = 0, max = 1)
	@Column(name = "FLG_EVALUAR_REGLA", length = 1, nullable = true)
	private String flgEvaluarRegla;

	/**
	 * desactiva los logger_debug logger_error de todas las apis y rest
	 */
	@Size(min = 0, max = 1)
	@Column(name = "FLG_LOG_BASE_DATOS", length = 1, nullable = true)
	private String flgLogBaseDatos;
	
	
	@Transient
	private Boolean auxFlgPreparado=Boolean.FALSE;

	@Transient
	private String auxFlgIniciarServicios;

	@Transient
	private String auxFlgLogBaseDatos;
	
	public ConfiguracionServicio() {
		pk = new ConfiguracionServicioPk();
	}


	public ConfiguracionServicio(ConfiguracionServicioPk pk) {
		this.pk = pk;
	}

	public ConfiguracionServicioPk getPk() {
		return pk;
	}

	public void setPk(ConfiguracionServicioPk pk) {
		this.pk = pk;
	}
	/**
	 *  
	 * 
	 * @campo FLG_CORREO_PRUEBA
	*/
	public String getFlgCorreoPrueba() {
		if (UString.estaVacio(flgCorreoPrueba))
			flgCorreoPrueba="N";
		return flgCorreoPrueba;
	}


	/**
	 *  
	 * 
	 * @campo FLG_CORREO_PRUEBA
	*/
	public void setFlgCorreoPrueba(String flgCorreoPrueba) {
		this.flgCorreoPrueba = flgCorreoPrueba;
	}
	/**
	 *  
	 * 
	 * @campo CORREO_PRUEBA
	*/
	public String getCorreoPrueba() {
		return correoPrueba;
	}

	/**
	 *  
	 * 
	 * @campo CORREO_PRUEBA
	*/
	public void setCorreoPrueba(String correoPrueba) {
		this.correoPrueba = correoPrueba;
	}
	/**
	 *  
	 * 
	 * @campo FLG_INICIAR_SERVICIOS
	*/
	public String getFlgIniciarServicios() {
		if (UString.estaVacio(flgIniciarServicios))
			return "N";
		return flgIniciarServicios;
	}

	/**
	 *  
	 * 
	 * @campo FLG_INICIAR_SERVICIOS
	*/
	public void setFlgIniciarServicios(String flgIniciarServicios) {
		this.flgIniciarServicios = flgIniciarServicios;
	}
	
	/**
	 *  
	 * 
	 * @campo ULTIMA_FECHA_INICIO_SERVICIOS
	*/
	public java.util.Date getUltimaFechaInicioServicios() {
		return ultimaFechaInicioServicios;
	}

	/**
	 *  
	 * 
	 * @campo ULTIMA_FECHA_INICIO_SERVICIOS
	*/
	public void setUltimaFechaInicioServicios(java.util.Date ultimaFechaInicioServicios) {
		this.ultimaFechaInicioServicios = ultimaFechaInicioServicios;
	}
	/**
	 *  
	 * 
	 * @campo FLG_INICIAR_SERVICIOS_CONTROL
	*/
	public String getFlgIniciarServiciosControl() {
		return flgIniciarServiciosControl;
	}

	/**
	 *  
	 * 
	 * @campo FLG_INICIAR_SERVICIOS_CONTROL
	*/
	public void setFlgIniciarServiciosControl(String flgIniciarServiciosControl) {
		this.flgIniciarServiciosControl = flgIniciarServiciosControl;
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
	 * @campo FLG_FORZAR_REINICIO
	*/
	public String getFlgForzarReinicio() {
		return flgForzarReinicio;
	}

	/**
	 *  
	 * 
	 * @campo FLG_FORZAR_REINICIO
	*/
	public void setFlgForzarReinicio(String flgForzarReinicio) {
		this.flgForzarReinicio = flgForzarReinicio;
	}
	/**
	 *  
	 * 
	 * @campo CORREO_TIPO_CONFIGURACION
	*/
	public String getCorreoTipoConfiguracion() {
		return correoTipoConfiguracion;
	}

	/**
	 *  
	 * 
	 * @campo CORREO_TIPO_CONFIGURACION
	*/
	public void setCorreoTipoConfiguracion(String correoTipoConfiguracion) {
		this.correoTipoConfiguracion = correoTipoConfiguracion;
	}
	/**
	 *  
	 * 
	 * @campo FLG_INICIAR_ENVIO_CORREO
	*/
	public String getFlgIniciarEnvioCorreo() {
		return flgIniciarEnvioCorreo;
	}

	/**
	 *  
	 * 
	 * @campo FLG_INICIAR_ENVIO_CORREO
	*/
	public void setFlgIniciarEnvioCorreo(String flgIniciarEnvioCorreo) {
		this.flgIniciarEnvioCorreo = flgIniciarEnvioCorreo;
	}
	/**
	 *  
	 * 
	 * @campo EMAIL_CUENTA
	*/
	public String getEmailCuenta() {
		return emailCuenta;
	}

	/**
	 *  
	 * 
	 * @campo EMAIL_CUENTA
	*/
	public void setEmailCuenta(String emailCuenta) {
		this.emailCuenta = emailCuenta;
	}
	/**
	 *  
	 * 
	 * @campo EMAIL_CLAVE
	*/
	public String getEmailClave() {
		return emailClave;
	}

	/**
	 *  
	 * 
	 * @campo EMAIL_CLAVE
	*/
	public void setEmailClave(String emailClave) {
		this.emailClave = emailClave;
	}
	/**
	 *  
	 * 
	 * @campo EMAIL_PERFIL
	*/
	public String getEmailPerfil() {
		return emailPerfil;
	}

	/**
	 *  
	 * 
	 * @campo EMAIL_PERFIL
	*/
	public void setEmailPerfil(String emailPerfil) {
		this.emailPerfil = emailPerfil;
	}
	/**
	 *  
	 * 
	 * @campo FLG_EJECUTANDO_EVALUAR_REGLA
	*/
	public String getFlgEjecutandoEvaluarRegla() {
		return flgEjecutandoEvaluarRegla;
	}

	/**
	 *  
	 * 
	 * @campo FLG_EJECUTANDO_EVALUAR_REGLA
	*/
	public void setFlgEjecutandoEvaluarRegla(String flgEjecutandoEvaluarRegla) {
		this.flgEjecutandoEvaluarRegla = flgEjecutandoEvaluarRegla;
	}
	/**
	 *  
	 * 
	 * @campo SI_EJECUTANDO_EVALUAR_REGLA
	*/
	public java.util.Date getSiEjecutandoEvaluarRegla() {
		return siEjecutandoEvaluarRegla;
	}

	/**
	 *  
	 * 
	 * @campo SI_EJECUTANDO_EVALUAR_REGLA
	*/
	public void setSiEjecutandoEvaluarRegla(java.util.Date siEjecutandoEvaluarRegla) {
		this.siEjecutandoEvaluarRegla = siEjecutandoEvaluarRegla;
	}
	/**
	 *  
	 * 
	 * @campo NO_EJECUTANDO_EVALUAR_REGLA
	*/
	public java.util.Date getNoEjecutandoEvaluarRegla() {
		return noEjecutandoEvaluarRegla;
	}

	/**
	 *  
	 * 
	 * @campo NO_EJECUTANDO_EVALUAR_REGLA
	*/
	public void setNoEjecutandoEvaluarRegla(java.util.Date noEjecutandoEvaluarRegla) {
		this.noEjecutandoEvaluarRegla = noEjecutandoEvaluarRegla;
	}
	/**
	 *  
	 * 
	 * @campo FLG_EJECUTANDO_EXTRAER_DATA
	*/
	public String getFlgEjecutandoExtraerData() {
		return flgEjecutandoExtraerData;
	}

	/**
	 *  
	 * 
	 * @campo FLG_EJECUTANDO_EXTRAER_DATA
	*/
	public void setFlgEjecutandoExtraerData(String flgEjecutandoExtraerData) {
		this.flgEjecutandoExtraerData = flgEjecutandoExtraerData;
	}
	/**
	 *  
	 * 
	 * @campo SI_EJECUTANDO_EXTRAER_DATA
	*/
	public java.util.Date getSiEjecutandoExtraerData() {
		return siEjecutandoExtraerData;
	}

	/**
	 *  
	 * 
	 * @campo SI_EJECUTANDO_EXTRAER_DATA
	*/
	public void setSiEjecutandoExtraerData(java.util.Date siEjecutandoExtraerData) {
		this.siEjecutandoExtraerData = siEjecutandoExtraerData;
	}
	/**
	 *  
	 * 
	 * @campo NO_EJECUTANDO_EXTRAER_DATA
	*/
	public java.util.Date getNoEjecutandoExtraerData() {
		return noEjecutandoExtraerData;
	}

	/**
	 *  
	 * 
	 * @campo NO_EJECUTANDO_EXTRAER_DATA
	*/
	public void setNoEjecutandoExtraerData(java.util.Date noEjecutandoExtraerData) {
		this.noEjecutandoExtraerData = noEjecutandoExtraerData;
	}
	/**
	 *  
	 * 
	 * @campo FLG_EJECUTANDO_CREAR_ALERTA
	*/
	public String getFlgEjecutandoCrearAlerta() {
		return flgEjecutandoCrearAlerta;
	}

	/**
	 *  
	 * 
	 * @campo FLG_EJECUTANDO_CREAR_ALERTA
	*/
	public void setFlgEjecutandoCrearAlerta(String flgEjecutandoCrearAlerta) {
		this.flgEjecutandoCrearAlerta = flgEjecutandoCrearAlerta;
	}
	/**
	 *  
	 * 
	 * @campo SI_EJECUTANDO_CREAR_ALERTA
	*/
	public java.util.Date getSiEjecutandoCrearAlerta() {
		return siEjecutandoCrearAlerta;
	}

	/**
	 *  
	 * 
	 * @campo SI_EJECUTANDO_CREAR_ALERTA
	*/
	public void setSiEjecutandoCrearAlerta(java.util.Date siEjecutandoCrearAlerta) {
		this.siEjecutandoCrearAlerta = siEjecutandoCrearAlerta;
	}
	/**
	 *  
	 * 
	 * @campo NO_EJECUTANDO_CREAR_ALERTA
	*/
	public java.util.Date getNoEjecutandoCrearAlerta() {
		return noEjecutandoCrearAlerta;
	}

	/**
	 *  
	 * 
	 * @campo NO_EJECUTANDO_CREAR_ALERTA
	*/
	public void setNoEjecutandoCrearAlerta(java.util.Date noEjecutandoCrearAlerta) {
		this.noEjecutandoCrearAlerta = noEjecutandoCrearAlerta;
	}
	/**
	 *  
	 * 
	 * @campo FLG_EJECUTANDO_CREAR_CORREO
	*/
	public String getFlgEjecutandoCrearCorreo() {
		return flgEjecutandoCrearCorreo;
	}

	/**
	 *  
	 * 
	 * @campo FLG_EJECUTANDO_CREAR_CORREO
	*/
	public void setFlgEjecutandoCrearCorreo(String flgEjecutandoCrearCorreo) {
		this.flgEjecutandoCrearCorreo = flgEjecutandoCrearCorreo;
	}
	/**
	 *  
	 * 
	 * @campo SI_EJECUTANDO_CREAR_CORREO
	*/
	public java.util.Date getSiEjecutandoCrearCorreo() {
		return siEjecutandoCrearCorreo;
	}

	/**
	 *  
	 * 
	 * @campo SI_EJECUTANDO_CREAR_CORREO
	*/
	public void setSiEjecutandoCrearCorreo(java.util.Date siEjecutandoCrearCorreo) {
		this.siEjecutandoCrearCorreo = siEjecutandoCrearCorreo;
	}
	/**
	 *  
	 * 
	 * @campo NO_EJECUTANDO_CREAR_CORREO
	*/
	public java.util.Date getNoEjecutandoCrearCorreo() {
		return noEjecutandoCrearCorreo;
	}

	/**
	 *  
	 * 
	 * @campo NO_EJECUTANDO_CREAR_CORREO
	*/
	public void setNoEjecutandoCrearCorreo(java.util.Date noEjecutandoCrearCorreo) {
		this.noEjecutandoCrearCorreo = noEjecutandoCrearCorreo;
	}
	/**
	 *  
	 * 
	 * @campo FLG_EJECUTANDO_ENVIO_CORREO
	*/
	public String getFlgEjecutandoEnvioCorreo() {
		return flgEjecutandoEnvioCorreo;
	}

	/**
	 *  
	 * 
	 * @campo FLG_EJECUTANDO_ENVIO_CORREO
	*/
	public void setFlgEjecutandoEnvioCorreo(String flgEjecutandoEnvioCorreo) {
		this.flgEjecutandoEnvioCorreo = flgEjecutandoEnvioCorreo;
	}
	/**
	 *  
	 * 
	 * @campo SI_EJECUTANDO_ENVIO_CORREO
	*/
	public java.util.Date getSiEjecutandoEnvioCorreo() {
		return siEjecutandoEnvioCorreo;
	}

	/**
	 *  
	 * 
	 * @campo SI_EJECUTANDO_ENVIO_CORREO
	*/
	public void setSiEjecutandoEnvioCorreo(java.util.Date siEjecutandoEnvioCorreo) {
		this.siEjecutandoEnvioCorreo = siEjecutandoEnvioCorreo;
	}
	/**
	 *  
	 * 
	 * @campo NO_EJECUTANDO_ENVIO_CORREO
	*/
	public java.util.Date getNoEjecutandoEnvioCorreo() {
		return noEjecutandoEnvioCorreo;
	}

	/**
	 *  
	 * 
	 * @campo NO_EJECUTANDO_ENVIO_CORREO
	*/
	public void setNoEjecutandoEnvioCorreo(java.util.Date noEjecutandoEnvioCorreo) {
		this.noEjecutandoEnvioCorreo = noEjecutandoEnvioCorreo;
	}
	/**
	 *  
	 * 
	 * @campo FLG_ENVIAR_CORREO
	*/
	public String getFlgEnviarCorreo() {
		if (UString.estaVacio(flgEnviarCorreo))
			return "N";
		return flgEnviarCorreo;
	}

	/**
	 *  
	 * 
	 * @campo FLG_ENVIAR_CORREO
	*/
	public void setFlgEnviarCorreo(String flgEnviarCorreo) {
		this.flgEnviarCorreo = flgEnviarCorreo;
	}
	/**
	 *  
	 * 
	 * @campo FLG_CREAR_CORREO
	*/
	public String getFlgCrearCorreo() {
		if (UString.estaVacio(flgCrearCorreo))
			return "N";
		return flgCrearCorreo;
	}

	/**
	 *  
	 * 
	 * @campo FLG_CREAR_CORREO
	*/
	public void setFlgCrearCorreo(String flgCrearCorreo) {
		this.flgCrearCorreo = flgCrearCorreo;
	}
	/**
	 *  
	 * 
	 * @campo FLG_CREAR_ALERTA
	*/
	public String getFlgCrearAlerta() {
		if (UString.estaVacio(flgCrearAlerta))
			return "N";
		return flgCrearAlerta;
	}

	/**
	 *  
	 * 
	 * @campo FLG_CREAR_ALERTA
	*/
	public void setFlgCrearAlerta(String flgCrearAlerta) {
		this.flgCrearAlerta = flgCrearAlerta;
	}
	/**
	 *  
	 * 
	 * @campo FLG_EXTRAER_DATA
	*/
	public String getFlgExtraerData() {
		if (UString.esNuloVacio(flgExtraerData))
			return "N";
		return flgExtraerData;
	}

	/**
	 *  
	 * 
	 * @campo FLG_EXTRAER_DATA
	*/
	public void setFlgExtraerData(String flgExtraerData) {
		this.flgExtraerData = flgExtraerData;
	}
	/**
	 *  
	 * 
	 * @campo FLG_EVALUAR_REGLA
	*/
	public String getFlgEvaluarRegla() {
		if (UString.estaVacio(flgEvaluarRegla))
			return "N";
		return flgEvaluarRegla;
	}

	/**
	 *  
	 * 
	 * @campo FLG_EVALUAR_REGLA
	*/
	public void setFlgEvaluarRegla(String flgEvaluarRegla) {
		this.flgEvaluarRegla = flgEvaluarRegla;
	}
	public Boolean getAuxFlgPreparado() {
		if (auxFlgPreparado==null)
			return Boolean.FALSE;
		return auxFlgPreparado;
	}

	public void setAuxFlgPreparado(Boolean auxFlgPreparado) {
		this.auxFlgPreparado = auxFlgPreparado;
	}
	
	
	public ConfiguracionCorreo getConfiguracionCorreoFramework() {
		ConfiguracionCorreo retorno = new ConfiguracionCorreo();
		retorno.setCorreoPrueba(correoPrueba);
		retorno.setFlgCorreoPrueba(flgCorreoPrueba);
		retorno.setCorreoTipoConfiguracion(correoTipoConfiguracion);
		retorno.setEmailClave(emailClave);
		retorno.setEmailCuenta(emailCuenta);
		retorno.setEmailPerfil(emailPerfil);
		return retorno;
	}


	public String getAuxFlgIniciarServicios() {
		if (UString.estaVacio(auxFlgIniciarServicios))
			return "N";
		return auxFlgIniciarServicios;
	}


	public void setAuxFlgIniciarServicios(String auxFlgIniciarServicios) {
		this.auxFlgIniciarServicios = auxFlgIniciarServicios;
	}


	public String getAuxFlgLogBaseDatos() {
		if (UString.estaVacio(auxFlgLogBaseDatos))
			return "N";
		return auxFlgLogBaseDatos;
	}


	public void setAuxFlgLogBaseDatos(String auxFlgLogBaseDatos) {
		this.auxFlgLogBaseDatos = auxFlgLogBaseDatos;
	}


	public String getFlgLogBaseDatos() {
		if (UString.estaVacio(flgLogBaseDatos))
			return "N";
		return flgLogBaseDatos;
	}


	public void setFlgLogBaseDatos(String flgLogBaseDatos) {
		this.flgLogBaseDatos = flgLogBaseDatos;
	}


	public String getFlgEnviarCorreoGenerico() {
		return flgEnviarCorreoGenerico;
	}


	public void setFlgEnviarCorreoGenerico(String flgEnviarCorreoGenerico) {
		this.flgEnviarCorreoGenerico = flgEnviarCorreoGenerico;
	}
	
}
