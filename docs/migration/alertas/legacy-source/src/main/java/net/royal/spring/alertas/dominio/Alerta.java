package net.royal.spring.alertas.dominio;

import java.util.List;

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
 * @tabla SGALERTASSYS.ALERTA
 */
@Entity
@Table(name = "ALERTA", schema = "SGALERTASSYS")
public class Alerta implements java.io.Serializable {

	private static final long serialVersionUID = 1L;

	@EmbeddedId
	private AlertaPk pk;

	@Column(name = "ID_REGLA_NEGOCIO", nullable = true)
	private Integer idReglaNegocio;

	@Size(min = 0, max = 3)
	@Column(name = "ID_COLOR", length = 3, nullable = true)
	private String idColor;

	@Size(min = 0, max = 1)
	@Column(name = "FLG_CORREO_GENERADO", length = 1, nullable = true)
	private String flgCorreoGenerado;

	@Size(min = 0, max = 200)
	@Column(name = "NOMBRE_SISTEMA", length = 200, nullable = true)
	private String nombreSistema;

	@Size(min = 0, max = 500)
	@Column(name = "TITULO", length = 500, nullable = true)
	private String titulo;

	@Size(min = 0, max = 500)
	@Column(name = "ASUNTO", length = 500, nullable = true)
	private String asunto;

	@Size(min = 0, max = 4000)
	@Column(name = "RESUMEN_LOG_ALERTA", length = 4000, nullable = true)
	private String resumenLogAlerta;

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

	@JsonSerialize(using = USerializers.DateSerializer.class)
	@JsonDeserialize(using = UDeserializers.DateDeserializer.class)
	@Column(name = "FECHA_LOG_ALERTA", nullable = true)
	private java.util.Date fechaLogAlerta;

	@Transient
	private Boolean auxFlgPreparado = Boolean.FALSE;

	@Transient
	private List<AlertaAdicional> listaAdicional;
	@Transient
	private List<AlertaDestino> listaDestino;
	@Transient
	private List<AlertaDetalle> listaDetalle;
	@Transient
	private List<AlertaPlan> listaPlan;
	@Transient
	private List<ReglaNegocioDetalle> listaReglaNegocioDetalle;

	public List<AlertaAdicional> getListaAdicional() {
		return listaAdicional;
	}

	public void setListaAdicional(List<AlertaAdicional> listaAdicional) {
		this.listaAdicional = listaAdicional;
	}

	public List<AlertaDestino> getListaDestino() {
		return listaDestino;
	}

	public void setListaDestino(List<AlertaDestino> listaDestino) {
		this.listaDestino = listaDestino;
	}

	public List<AlertaDetalle> getListaDetalle() {
		return listaDetalle;
	}

	public void setListaDetalle(List<AlertaDetalle> listaDetalle) {
		this.listaDetalle = listaDetalle;
	}

	public List<AlertaPlan> getListaPlan() {
		return listaPlan;
	}

	public void setListaPlan(List<AlertaPlan> listaPlan) {
		this.listaPlan = listaPlan;
	}

	public List<ReglaNegocioDetalle> getListaReglaNegocioDetalle() {
		return listaReglaNegocioDetalle;
	}

	public void setListaReglaNegocioDetalle(List<ReglaNegocioDetalle> listaReglaNegocioDetalle) {
		this.listaReglaNegocioDetalle = listaReglaNegocioDetalle;
	}

	public Alerta() {
		pk = new AlertaPk();
	}

	public Alerta(AlertaPk pk) {
		this.pk = pk;
	}

	public AlertaPk getPk() {
		return pk;
	}

	public void setPk(AlertaPk pk) {
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
	 * @campo ID_COLOR
	 */
	public String getIdColor() {
		return idColor;
	}

	/**
	 * 
	 * 
	 * @campo ID_COLOR
	 */
	public void setIdColor(String idColor) {
		this.idColor = idColor;
	}

	/**
	 * 
	 * 
	 * @campo FLG_CORREO_GENERADO
	 */
	public String getFlgCorreoGenerado() {
		return flgCorreoGenerado;
	}

	/**
	 * 
	 * 
	 * @campo FLG_CORREO_GENERADO
	 */
	public void setFlgCorreoGenerado(String flgCorreoGenerado) {
		this.flgCorreoGenerado = flgCorreoGenerado;
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
	 * @campo TITULO
	 */
	public String getTitulo() {
		return titulo;
	}

	/**
	 * 
	 * 
	 * @campo TITULO
	 */
	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}

	/**
	 * 
	 * 
	 * @campo ASUNTO
	 */
	public String getAsunto() {
		return asunto;
	}

	/**
	 * 
	 * 
	 * @campo ASUNTO
	 */
	public void setAsunto(String asunto) {
		this.asunto = asunto;
	}

	/**
	 * 
	 * 
	 * @campo RESUMEN_LOG_ALERTA
	 */
	public String getResumenLogAlerta() {
		return resumenLogAlerta;
	}

	/**
	 * 
	 * 
	 * @campo RESUMEN_LOG_ALERTA
	 */
	public void setResumenLogAlerta(String resumenLogAlerta) {
		this.resumenLogAlerta = resumenLogAlerta;
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
	 * @campo FECHA_LOG_ALERTA
	 */
	public java.util.Date getFechaLogAlerta() {
		return fechaLogAlerta;
	}

	/**
	 * 
	 * 
	 * @campo FECHA_LOG_ALERTA
	 */
	public void setFechaLogAlerta(java.util.Date fechaLogAlerta) {
		this.fechaLogAlerta = fechaLogAlerta;
	}

	public Boolean getAuxFlgPreparado() {
		if (auxFlgPreparado == null)
			return Boolean.FALSE;
		return auxFlgPreparado;
	}

	public void setAuxFlgPreparado(Boolean auxFlgPreparado) {
		this.auxFlgPreparado = auxFlgPreparado;
	}

}
