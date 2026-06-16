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
 * @tabla SGALERTASSYS.ALERTA_DETALLE
*/
@Entity
@Table(name = "ALERTA_DETALLE",schema="SGALERTASSYS")
public class AlertaDetalle implements java.io.Serializable{
	private static final long serialVersionUID = 1L;

	@EmbeddedId
	private AlertaDetallePk pk;

	@Column(name = "ID_LOG_ALERTA", nullable = true)
	private Integer idLogAlerta;

	@Column(name = "NRO_REGISTRO", nullable = true)
	private Integer nroRegistro;

	@Size(min = 0, max = 100)
	@Column(name = "NOMBRE_CAMPO", length = 100, nullable = true)
	private String nombreCampo;

	@Size(min = 0, max = 2000)
	@Column(name = "VALOR", length = 2000, nullable = true)
	private String valor;

	@Column(name = "ORDEN_COLUMNA", nullable = true)
	private Integer ordenColumna;

	@Size(min = 0, max = 3)
	@Column(name = "ID_ALINEACION_COLUMNA", length = 3, nullable = true)
	private String idAlineacionColumna;

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


	public AlertaDetalle() {
		pk = new AlertaDetallePk();
	}


	public AlertaDetalle(AlertaDetallePk pk) {
		this.pk = pk;
	}

	public AlertaDetallePk getPk() {
		return pk;
	}

	public void setPk(AlertaDetallePk pk) {
		this.pk = pk;
	}
	/**
	 *  
	 * 
	 * @campo ID_LOG_ALERTA
	*/
	public Integer getIdLogAlerta() {
		return idLogAlerta;
	}

	/**
	 *  
	 * 
	 * @campo ID_LOG_ALERTA
	*/
	public void setIdLogAlerta(Integer idLogAlerta) {
		this.idLogAlerta = idLogAlerta;
	}
	/**
	 *  
	 * 
	 * @campo NRO_REGISTRO
	*/
	public Integer getNroRegistro() {
		return nroRegistro;
	}

	/**
	 *  
	 * 
	 * @campo NRO_REGISTRO
	*/
	public void setNroRegistro(Integer nroRegistro) {
		this.nroRegistro = nroRegistro;
	}
	/**
	 *  
	 * 
	 * @campo NOMBRE_CAMPO
	*/
	public String getNombreCampo() {
		return nombreCampo;
	}

	/**
	 *  
	 * 
	 * @campo NOMBRE_CAMPO
	*/
	public void setNombreCampo(String nombreCampo) {
		this.nombreCampo = nombreCampo;
	}
	/**
	 *  
	 * 
	 * @campo VALOR
	*/
	public String getValor() {
		return valor;
	}

	/**
	 *  
	 * 
	 * @campo VALOR
	*/
	public void setValor(String valor) {
		this.valor = valor;
	}
	/**
	 *  
	 * 
	 * @campo ORDEN_COLUMNA
	*/
	public Integer getOrdenColumna() {
		return ordenColumna;
	}

	/**
	 *  
	 * 
	 * @campo ORDEN_COLUMNA
	*/
	public void setOrdenColumna(Integer ordenColumna) {
		this.ordenColumna = ordenColumna;
	}
	/**
	 *  
	 * 
	 * @campo ID_ALINEACION_COLUMNA
	*/
	public String getIdAlineacionColumna() {
		return idAlineacionColumna;
	}

	/**
	 *  
	 * 
	 * @campo ID_ALINEACION_COLUMNA
	*/
	public void setIdAlineacionColumna(String idAlineacionColumna) {
		this.idAlineacionColumna = idAlineacionColumna;
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
