package net.royal.spring.alertas.dominio.dto;

import java.math.BigDecimal;
import java.util.Date;

public class ConfiguracionServicioDto {
	private BigDecimal id_configuracion_servicio;
	private String flg_correo_prueba;
	private String correo_prueba;
	private String flg_iniciar_servicios;
	private Date ultima_fecha_inicio_servicios;
	private String flg_iniciar_servicios_control;
	private String estado;
	private String creacion_usuario;
	private Date creacion_fecha;
	private String creacion_terminal;
	private String modificacion_usuario;
	private Date modificacion_fecha;
	private String modificacion_terminal;
	private String flg_forzar_reinicio;
	private String correo_tipo_configuracion;
	private String flg_iniciar_envio_correo;
	private String email_cuenta;
	private String email_clave;
	private String email_perfil;
	private String flg_ejecutando_evaluar_regla;
	private Date si_ejecutando_evaluar_regla;
	private Date no_ejecutando_evaluar_regla;
	private String flg_ejecutando_extraer_data;
	private Date si_ejecutando_extraer_data;
	private Date no_ejecutando_extraer_data;
	private String flg_ejecutando_crear_alerta;
	private Date si_ejecutando_crear_alerta;
	private Date no_ejecutando_crear_alerta;
	private String flg_ejecutando_crear_correo;
	private Date si_ejecutando_crear_correo;
	private Date no_ejecutando_crear_correo;
	private String flg_ejecutando_envio_correo;
	private Date si_ejecutando_envio_correo;
	private Date no_ejecutando_envio_correo;
	private String flg_enviar_correo;
	private String flg_crear_correo;
	private String flg_crear_alerta;
	private String flg_extraer_data;
	private String flg_evaluar_regla;
	private String flgLogBaseDatos;
	private String flgactualizar;

	private String flgEnviarCorreoGenerico;
	
	public ConfiguracionServicioDto() {
		this.flgactualizar = "ACTUALIZAR";
	}

	public String getFlgactualizar() {
		return flgactualizar;
	}

	public void setFlgactualizar(String flgactualizar) {
		this.flgactualizar = flgactualizar;
	}

	public BigDecimal getId_configuracion_servicio() {
		return id_configuracion_servicio;
	}

	public void setId_configuracion_servicio(BigDecimal id_configuracion_servicio) {
		this.id_configuracion_servicio = id_configuracion_servicio;
	}

	public String getFlg_correo_prueba() {
		return flg_correo_prueba;
	}

	public void setFlg_correo_prueba(String flg_correo_prueba) {
		this.flg_correo_prueba = flg_correo_prueba;
	}

	public String getCorreo_prueba() {
		return correo_prueba;
	}

	public void setCorreo_prueba(String correo_prueba) {
		this.correo_prueba = correo_prueba;
	}

	public String getFlg_iniciar_servicios() {
		return flg_iniciar_servicios;
	}

	public void setFlg_iniciar_servicios(String flg_iniciar_servicios) {
		this.flg_iniciar_servicios = flg_iniciar_servicios;
	}

	public Date getUltima_fecha_inicio_servicios() {
		return ultima_fecha_inicio_servicios;
	}

	public void setUltima_fecha_inicio_servicios(Date ultima_fecha_inicio_servicios) {
		this.ultima_fecha_inicio_servicios = ultima_fecha_inicio_servicios;
	}

	public String getFlg_iniciar_servicios_control() {
		return flg_iniciar_servicios_control;
	}

	public void setFlg_iniciar_servicios_control(String flg_iniciar_servicios_control) {
		this.flg_iniciar_servicios_control = flg_iniciar_servicios_control;
	}

	public String getEstado() {
		return estado;
	}

	public void setEstado(String estado) {
		this.estado = estado;
	}

	public String getCreacion_usuario() {
		return creacion_usuario;
	}

	public void setCreacion_usuario(String creacion_usuario) {
		this.creacion_usuario = creacion_usuario;
	}

	public Date getCreacion_fecha() {
		return creacion_fecha;
	}

	public void setCreacion_fecha(Date creacion_fecha) {
		this.creacion_fecha = creacion_fecha;
	}

	public String getCreacion_terminal() {
		return creacion_terminal;
	}

	public void setCreacion_terminal(String creacion_terminal) {
		this.creacion_terminal = creacion_terminal;
	}

	public String getModificacion_usuario() {
		return modificacion_usuario;
	}

	public void setModificacion_usuario(String modificacion_usuario) {
		this.modificacion_usuario = modificacion_usuario;
	}

	public Date getModificacion_fecha() {
		return modificacion_fecha;
	}

	public void setModificacion_fecha(Date modificacion_fecha) {
		this.modificacion_fecha = modificacion_fecha;
	}

	public String getModificacion_terminal() {
		return modificacion_terminal;
	}

	public void setModificacion_terminal(String modificacion_terminal) {
		this.modificacion_terminal = modificacion_terminal;
	}

	public String getFlg_forzar_reinicio() {
		return flg_forzar_reinicio;
	}

	public void setFlg_forzar_reinicio(String flg_forzar_reinicio) {
		this.flg_forzar_reinicio = flg_forzar_reinicio;
	}

	public String getCorreo_tipo_configuracion() {
		return correo_tipo_configuracion;
	}

	public void setCorreo_tipo_configuracion(String correo_tipo_configuracion) {
		this.correo_tipo_configuracion = correo_tipo_configuracion;
	}

	public String getFlg_iniciar_envio_correo() {
		return flg_iniciar_envio_correo;
	}

	public void setFlg_iniciar_envio_correo(String flg_iniciar_envio_correo) {
		this.flg_iniciar_envio_correo = flg_iniciar_envio_correo;
	}

	public String getEmail_cuenta() {
		return email_cuenta;
	}

	public void setEmail_cuenta(String email_cuenta) {
		this.email_cuenta = email_cuenta;
	}

	public String getEmail_clave() {
		return email_clave;
	}

	public void setEmail_clave(String email_clave) {
		this.email_clave = email_clave;
	}

	public String getEmail_perfil() {
		return email_perfil;
	}

	public void setEmail_perfil(String email_perfil) {
		this.email_perfil = email_perfil;
	}

	public String getFlg_ejecutando_evaluar_regla() {
		return flg_ejecutando_evaluar_regla;
	}

	public void setFlg_ejecutando_evaluar_regla(String flg_ejecutando_evaluar_regla) {
		this.flg_ejecutando_evaluar_regla = flg_ejecutando_evaluar_regla;
	}

	public Date getSi_ejecutando_evaluar_regla() {
		return si_ejecutando_evaluar_regla;
	}

	public void setSi_ejecutando_evaluar_regla(Date si_ejecutando_evaluar_regla) {
		this.si_ejecutando_evaluar_regla = si_ejecutando_evaluar_regla;
	}

	public Date getNo_ejecutando_evaluar_regla() {
		return no_ejecutando_evaluar_regla;
	}

	public void setNo_ejecutando_evaluar_regla(Date no_ejecutando_evaluar_regla) {
		this.no_ejecutando_evaluar_regla = no_ejecutando_evaluar_regla;
	}

	public String getFlg_ejecutando_extraer_data() {
		return flg_ejecutando_extraer_data;
	}

	public void setFlg_ejecutando_extraer_data(String flg_ejecutando_extraer_data) {
		this.flg_ejecutando_extraer_data = flg_ejecutando_extraer_data;
	}

	public Date getSi_ejecutando_extraer_data() {
		return si_ejecutando_extraer_data;
	}

	public void setSi_ejecutando_extraer_data(Date si_ejecutando_extraer_data) {
		this.si_ejecutando_extraer_data = si_ejecutando_extraer_data;
	}

	public Date getNo_ejecutando_extraer_data() {
		return no_ejecutando_extraer_data;
	}

	public void setNo_ejecutando_extraer_data(Date no_ejecutando_extraer_data) {
		this.no_ejecutando_extraer_data = no_ejecutando_extraer_data;
	}

	public String getFlg_ejecutando_crear_alerta() {
		return flg_ejecutando_crear_alerta;
	}

	public void setFlg_ejecutando_crear_alerta(String flg_ejecutando_crear_alerta) {
		this.flg_ejecutando_crear_alerta = flg_ejecutando_crear_alerta;
	}

	public Date getSi_ejecutando_crear_alerta() {
		return si_ejecutando_crear_alerta;
	}

	public void setSi_ejecutando_crear_alerta(Date si_ejecutando_crear_alerta) {
		this.si_ejecutando_crear_alerta = si_ejecutando_crear_alerta;
	}

	public Date getNo_ejecutando_crear_alerta() {
		return no_ejecutando_crear_alerta;
	}

	public void setNo_ejecutando_crear_alerta(Date no_ejecutando_crear_alerta) {
		this.no_ejecutando_crear_alerta = no_ejecutando_crear_alerta;
	}

	public String getFlg_ejecutando_crear_correo() {
		return flg_ejecutando_crear_correo;
	}

	public void setFlg_ejecutando_crear_correo(String flg_ejecutando_crear_correo) {
		this.flg_ejecutando_crear_correo = flg_ejecutando_crear_correo;
	}

	public Date getSi_ejecutando_crear_correo() {
		return si_ejecutando_crear_correo;
	}

	public void setSi_ejecutando_crear_correo(Date si_ejecutando_crear_correo) {
		this.si_ejecutando_crear_correo = si_ejecutando_crear_correo;
	}

	public Date getNo_ejecutando_crear_correo() {
		return no_ejecutando_crear_correo;
	}

	public void setNo_ejecutando_crear_correo(Date no_ejecutando_crear_correo) {
		this.no_ejecutando_crear_correo = no_ejecutando_crear_correo;
	}

	public String getFlg_ejecutando_envio_correo() {
		return flg_ejecutando_envio_correo;
	}

	public void setFlg_ejecutando_envio_correo(String flg_ejecutando_envio_correo) {
		this.flg_ejecutando_envio_correo = flg_ejecutando_envio_correo;
	}

	public Date getSi_ejecutando_envio_correo() {
		return si_ejecutando_envio_correo;
	}

	public void setSi_ejecutando_envio_correo(Date si_ejecutando_envio_correo) {
		this.si_ejecutando_envio_correo = si_ejecutando_envio_correo;
	}

	public Date getNo_ejecutando_envio_correo() {
		return no_ejecutando_envio_correo;
	}

	public void setNo_ejecutando_envio_correo(Date no_ejecutando_envio_correo) {
		this.no_ejecutando_envio_correo = no_ejecutando_envio_correo;
	}

	public String getFlg_enviar_correo() {
		return flg_enviar_correo;
	}

	public void setFlg_enviar_correo(String flg_enviar_correo) {
		this.flg_enviar_correo = flg_enviar_correo;
	}

	public String getFlg_crear_correo() {
		return flg_crear_correo;
	}

	public void setFlg_crear_correo(String flg_crear_correo) {
		this.flg_crear_correo = flg_crear_correo;
	}

	public String getFlg_crear_alerta() {
		return flg_crear_alerta;
	}

	public void setFlg_crear_alerta(String flg_crear_alerta) {
		this.flg_crear_alerta = flg_crear_alerta;
	}

	public String getFlg_extraer_data() {
		return flg_extraer_data;
	}

	public void setFlg_extraer_data(String flg_extraer_data) {
		this.flg_extraer_data = flg_extraer_data;
	}

	public String getFlg_evaluar_regla() {
		return flg_evaluar_regla;
	}

	public void setFlg_evaluar_regla(String flg_evaluar_regla) {
		this.flg_evaluar_regla = flg_evaluar_regla;
	}

	public String getFlgLogBaseDatos() {
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
