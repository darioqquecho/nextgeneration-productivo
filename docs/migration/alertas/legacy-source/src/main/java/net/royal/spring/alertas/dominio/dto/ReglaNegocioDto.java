package net.royal.spring.alertas.dominio.dto;

import java.math.BigDecimal;

import net.royal.spring.framework.modelo.generico.DominioTransaccion;

public class ReglaNegocioDto extends DominioTransaccion{
	private BigDecimal idReglaNegocio;
	private String nombre;
	private String descripcion;
	private String objectBd;
	private String idTipoEjecucion;
	private String idTipoEnvio;
	private String idCompania;
	private BigDecimal idAreaNegocio;
	private String nombreAreaNegocio;
	private String idColor;
	private BigDecimal idConexionBd;
	private java.util.Date fechaUltimaEjecucion;
	private java.util.Date fechaUltimoEnvio;
	private String flgObtenerResponsable;
	private String flgEjecutandoActualmente;
	private String estado;
	
	
	
	 
	private String creacionUsuario;
	private java.util.Date creacionFecha;
	private String creacionTerminal;
	private String modificacionUsuario;
	private java.util.Date modificacionFecha;
	private String modificacionTerminal;
	private String correoContenido;
	private String correoPie;
	private String flgCorreoPrueba;
	private String flgMostrarFechahoraEnvio;
	private String flgMostrarFechahoraSegui;
	private String correoHojaEstilo;
	private Integer cantidadCorreosEnviar;
	private Integer cantidadErroresEnvio;
	private String flgLogGenerarAlerta;
	private String correoTipoConfiguracion;
	private Boolean auxFlgPreparado=Boolean.FALSE;
	
	private BigDecimal ROWNUM_ ;
	private String estadoaux;
	
	private java.util.Date fechaLog;
	private String javaLog;
	private String spLog;
	private String resumenLog;
	
	private Integer cantidadExito;
	private Integer cantidadError;
	
	private Integer ejecucionCantidad;
	private String ejecucionMensaje;
	private String perfilCorreoId;
	private String auxProgramacionTexto;
	
	
	public Integer getEjecucionCantidad() {
		return ejecucionCantidad;
	}
	public void setEjecucionCantidad(Integer ejecucionCantidad) {
		this.ejecucionCantidad = ejecucionCantidad;
	}
	public String getEjecucionMensaje() {
		return ejecucionMensaje;
	}
	public void setEjecucionMensaje(String ejecucionMensaje) {
		this.ejecucionMensaje = ejecucionMensaje;
	}
	public String getAuxProgramacionTexto() {
		return auxProgramacionTexto;
	}
	public void setAuxProgramacionTexto(String auxProgramacionTexto) {
		this.auxProgramacionTexto = auxProgramacionTexto;
	}
	public String getEstadoaux() {
		return estadoaux;
	}
	public void setEstadoaux(String estadoaux) {
		this.estadoaux = estadoaux;
	}
	public String getCreacionUsuario() {
		return creacionUsuario;
	}
	public void setCreacionUsuario(String creacionUsuario) {
		this.creacionUsuario = creacionUsuario;
	}
	public java.util.Date getCreacionFecha() {
		return creacionFecha;
	}
	public void setCreacionFecha(java.util.Date creacionFecha) {
		this.creacionFecha = creacionFecha;
	}
	public String getCreacionTerminal() {
		return creacionTerminal;
	}
	public void setCreacionTerminal(String creacionTerminal) {
		this.creacionTerminal = creacionTerminal;
	}
	public String getModificacionUsuario() {
		return modificacionUsuario;
	}
	public void setModificacionUsuario(String modificacionUsuario) {
		this.modificacionUsuario = modificacionUsuario;
	}
	public java.util.Date getModificacionFecha() {
		return modificacionFecha;
	}
	public void setModificacionFecha(java.util.Date modificacionFecha) {
		this.modificacionFecha = modificacionFecha;
	}
	public String getModificacionTerminal() {
		return modificacionTerminal;
	}
	public void setModificacionTerminal(String modificacionTerminal) {
		this.modificacionTerminal = modificacionTerminal;
	}
	public String getCorreoContenido() {
		return correoContenido;
	}
	public void setCorreoContenido(String correoContenido) {
		this.correoContenido = correoContenido;
	}
	public String getCorreoPie() {
		return correoPie;
	}
	public void setCorreoPie(String correoPie) {
		this.correoPie = correoPie;
	}
	public String getFlgCorreoPrueba() {
		return flgCorreoPrueba;
	}
	public void setFlgCorreoPrueba(String flgCorreoPrueba) {
		this.flgCorreoPrueba = flgCorreoPrueba;
	}
	public String getFlgMostrarFechahoraEnvio() {
		return flgMostrarFechahoraEnvio;
	}
	public void setFlgMostrarFechahoraEnvio(String flgMostrarFechahoraEnvio) {
		this.flgMostrarFechahoraEnvio = flgMostrarFechahoraEnvio;
	}
	public String getFlgMostrarFechahoraSegui() {
		return flgMostrarFechahoraSegui;
	}
	public void setFlgMostrarFechahoraSegui(String flgMostrarFechahoraSegui) {
		this.flgMostrarFechahoraSegui = flgMostrarFechahoraSegui;
	}
	public String getCorreoHojaEstilo() {
		return correoHojaEstilo;
	}
	public void setCorreoHojaEstilo(String correoHojaEstilo) {
		this.correoHojaEstilo = correoHojaEstilo;
	}
	public Integer getCantidadCorreosEnviar() {
		return cantidadCorreosEnviar;
	}
	public void setCantidadCorreosEnviar(Integer cantidadCorreosEnviar) {
		this.cantidadCorreosEnviar = cantidadCorreosEnviar;
	}
	public Integer getCantidadErroresEnvio() {
		return cantidadErroresEnvio;
	}
	public void setCantidadErroresEnvio(Integer cantidadErroresEnvio) {
		this.cantidadErroresEnvio = cantidadErroresEnvio;
	}
	public String getFlgLogGenerarAlerta() {
		return flgLogGenerarAlerta;
	}
	public void setFlgLogGenerarAlerta(String flgLogGenerarAlerta) {
		this.flgLogGenerarAlerta = flgLogGenerarAlerta;
	}
	public String getCorreoTipoConfiguracion() {
		return correoTipoConfiguracion;
	}
	public void setCorreoTipoConfiguracion(String correoTipoConfiguracion) {
		this.correoTipoConfiguracion = correoTipoConfiguracion;
	}
//	public Boolean getAuxFlgPreparado() {
//		return auxFlgPreparado;
//	}
//	public void setAuxFlgPreparado(Boolean auxFlgPreparado) {
//		this.auxFlgPreparado = auxFlgPreparado;
//	}
	public BigDecimal getROWNUM_() {
		return ROWNUM_;
	}
	public void setROWNUM_(BigDecimal rOWNUM_) {
		ROWNUM_ = rOWNUM_;
	}
	public BigDecimal getIdReglaNegocio() {
		return idReglaNegocio;
	}
	public void setIdReglaNegocio(BigDecimal idReglaNegocio) {
		this.idReglaNegocio = idReglaNegocio;
	}
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	public String getDescripcion() {
		return descripcion;
	}
	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}
	public String getObjectBd() {
		return objectBd;
	}
	public void setObjectBd(String objectBd) {
		this.objectBd = objectBd;
	}
	public String getIdTipoEjecucion() {
		return idTipoEjecucion;
	}
	public void setIdTipoEjecucion(String idTipoEjecucion) {
		this.idTipoEjecucion = idTipoEjecucion;
	}
	public String getIdTipoEnvio() {
		return idTipoEnvio;
	}
	public void setIdTipoEnvio(String idTipoEnvio) {
		this.idTipoEnvio = idTipoEnvio;
	}
	public String getIdCompania() {
		return idCompania;
	}
	public void setIdCompania(String idCompania) {
		this.idCompania = idCompania;
	}
	public BigDecimal getIdAreaNegocio() {
		return idAreaNegocio;
	}
	public void setIdAreaNegocio(BigDecimal idAreaNegocio) {
		this.idAreaNegocio = idAreaNegocio;
	}
	public String getNombreAreaNegocio() {
		return nombreAreaNegocio;
	}
	public void setNombreAreaNegocio(String nombreAreaNegocio) {
		this.nombreAreaNegocio = nombreAreaNegocio;
	}
	public String getIdColor() {
		return idColor;
	}
	public void setIdColor(String idColor) {
		this.idColor = idColor;
	}
	public BigDecimal getIdConexionBd() {
		return idConexionBd;
	}
	public void setIdConexionBd(BigDecimal idConexionBd) {
		this.idConexionBd = idConexionBd;
	}
	public java.util.Date getFechaUltimaEjecucion() {
		return fechaUltimaEjecucion;
	}
	public void setFechaUltimaEjecucion(java.util.Date fechaUltimaEjecucion) {
		this.fechaUltimaEjecucion = fechaUltimaEjecucion;
	}
	public java.util.Date getFechaUltimoEnvio() {
		return fechaUltimoEnvio;
	}
	public void setFechaUltimoEnvio(java.util.Date fechaUltimoEnvio) {
		this.fechaUltimoEnvio = fechaUltimoEnvio;
	}
	public String getFlgObtenerResponsable() {
		return flgObtenerResponsable;
	}
	public void setFlgObtenerResponsable(String flgObtenerResponsable) {
		this.flgObtenerResponsable = flgObtenerResponsable;
	}
	public String getFlgEjecutandoActualmente() {
		return flgEjecutandoActualmente;
	}
	public void setFlgEjecutandoActualmente(String flgEjecutandoActualmente) {
		this.flgEjecutandoActualmente = flgEjecutandoActualmente;
	}
	public String getEstado() {
		return estado;
	}
	public void setEstado(String estado) {
		this.estado = estado;
	}
	public java.util.Date getFechaLog() {
		return fechaLog;
	}
	public void setFechaLog(java.util.Date fechaLog) {
		this.fechaLog = fechaLog;
	}
	public String getJavaLog() {
		return javaLog;
	}
	public void setJavaLog(String javaLog) {
		this.javaLog = javaLog;
	}
	public String getSpLog() {
		return spLog;
	}
	public void setSpLog(String spLog) {
		this.spLog = spLog;
	}
	public String getResumenLog() {
		return resumenLog;
	}
	public void setResumenLog(String resumenLog) {
		this.resumenLog = resumenLog;
	}
	public Integer getCantidadExito() {
		return cantidadExito;
	}
	public void setCantidadExito(Integer cantidadExito) {
		this.cantidadExito = cantidadExito;
	}
	public Integer getCantidadError() {
		return cantidadError;
	}
	public void setCantidadError(Integer cantidadError) {
		this.cantidadError = cantidadError;
	}
	public String getPerfilCorreoId() {
		return perfilCorreoId;
	}
	public void setPerfilCorreoId(String perfilCorreoId) {
		this.perfilCorreoId = perfilCorreoId;
	}
	
	
	
}
