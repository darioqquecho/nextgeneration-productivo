package net.royal.spring.alertas.dominio;

import java.util.ArrayList;
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
 * @tabla SGALERTASSYS.CORREO
*/
@Entity
@Table(name = "CORREO",schema="SGALERTASSYS")public class Correo implements java.io.Serializable{

	private static final long serialVersionUID = 1L;
	@EmbeddedId
	private CorreoPk pk;

	@Column(name = "ID_REGLA_NEGOCIO", nullable = true)
	private Integer idReglaNegocio;

	@Size(min = 0, max = 500)
	@Column(name = "ASUNTO", length = 500, nullable = true)
	private String asunto;

	@JsonSerialize(using = USerializers.DateSerializer.class)
	@JsonDeserialize(using = UDeserializers.DateDeserializer.class)
	@Column(name = "FECHA_HORA_ENVIO", nullable = true)
	private java.util.Date fechaHoraEnvio;

	@Size(min = 0, max = 500)
	@Column(name = "RESUMEN_LOG_ALERTA", length = 500, nullable = true)
	private String resumenLogAlerta;

	@Column(name = "ID_ALERTA", nullable = true)
	private Integer idAlerta;

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

	@Column(name = "REFERENCIA_ID")
	private String referenciaId;
	
	@Column(name = "REFERENCIA_PADRE_ID")
	private String referenciaPadreId;
	
	@Column(name = "REFERENCIA_PRINCIPAL_ID")
	private String referenciaPrincipalId;
	
	@Column(name = "PROCESO_ID")
	private String procesoId;
	
	@Column(name = "TRANSACCION_ID")
	private Integer transaccionId;
	
	@Column(name = "SEGUIMIENTO_ID")
	private Integer seguimientoId;
	
	@Column(name = "ACCION_ID")
	private String accionId;
	
	@Column(name = "SUB_ACCION_ID")
	private String subAccionId;
	
	@Column(name = "PERFIL_CORREO_ID",nullable = true)
	private String perfilCorreoId;
	
	@Transient
	private Boolean auxFlgPreparado=Boolean.FALSE;

	@Transient
	private CorreoCuerpo correoCuerpo;
	
	@Transient
	private List<CorreoDestino> listaCorreoDestino;
	
	@Transient
	private List<CorreoAdjunto> listaCorreoAdjuntos;
	
	@Transient
	private String auxTipoTransaccion;
	
	public CorreoCuerpo getCorreoCuerpo() {
		return correoCuerpo;
	}


	public List<CorreoDestino> getListaCorreoDestino() {
		if (listaCorreoDestino==null)
			listaCorreoDestino=new ArrayList<CorreoDestino>();
		return listaCorreoDestino;
	}


	public void setListaCorreoDestino(List<CorreoDestino> listaCorreoDestino) {
		this.listaCorreoDestino = listaCorreoDestino;
	}


	public void setCorreoCuerpo(CorreoCuerpo correoCuerpo) {
		this.correoCuerpo = correoCuerpo;
	}


	public Correo() {
		pk = new CorreoPk();
	}


	public Correo(CorreoPk pk) {
		this.pk = pk;
	}

	public CorreoPk getPk() {
		return pk;
	}

	public void setPk(CorreoPk pk) {
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
	 * @campo FECHA_HORA_ENVIO
	*/
	public java.util.Date getFechaHoraEnvio() {
		return fechaHoraEnvio;
	}

	/**
	 *  
	 * 
	 * @campo FECHA_HORA_ENVIO
	*/
	public void setFechaHoraEnvio(java.util.Date fechaHoraEnvio) {
		this.fechaHoraEnvio = fechaHoraEnvio;
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
	 * @campo ID_ALERTA
	*/
	public Integer getIdAlerta() {
		return idAlerta;
	}

	/**
	 *  
	 * 
	 * @campo ID_ALERTA
	*/
	public void setIdAlerta(Integer idAlerta) {
		this.idAlerta = idAlerta;
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


	public String getReferenciaId() {
		return referenciaId;
	}


	public void setReferenciaId(String referenciaId) {
		this.referenciaId = referenciaId;
	}


	public String getReferenciaPadreId() {
		return referenciaPadreId;
	}


	public void setReferenciaPadreId(String referenciaPadreId) {
		this.referenciaPadreId = referenciaPadreId;
	}


	public String getProcesoId() {
		return procesoId;
	}


	public void setProcesoId(String procesoId) {
		this.procesoId = procesoId;
	}


	public Integer getTransaccionId() {
		return transaccionId;
	}


	public void setTransaccionId(Integer transaccionId) {
		this.transaccionId = transaccionId;
	}


	public Integer getSeguimientoId() {
		return seguimientoId;
	}


	public void setSeguimientoId(Integer seguimientoId) {
		this.seguimientoId = seguimientoId;
	}


	public String getAccionId() {
		return accionId;
	}


	public void setAccionId(String accionId) {
		this.accionId = accionId;
	}


	public String getSubAccionId() {
		return subAccionId;
	}

	public void setSubAccionId(String subAccionId) {
		this.subAccionId = subAccionId;
	}


	public List<CorreoAdjunto> getListaCorreoAdjuntos() {
		if (listaCorreoAdjuntos==null)
			listaCorreoAdjuntos=new ArrayList<CorreoAdjunto>();
		return listaCorreoAdjuntos;
	}


	public void setListaCorreoAdjuntos(List<CorreoAdjunto> listaCorreoAdjuntos) {
		this.listaCorreoAdjuntos = listaCorreoAdjuntos;
	}


	public String getAuxTipoTransaccion() {
		return auxTipoTransaccion;
	}


	public void setAuxTipoTransaccion(String auxTipoTransaccion) {
		this.auxTipoTransaccion = auxTipoTransaccion;
	}


	public String getPerfilCorreoId() {
		return perfilCorreoId;
	}


	public void setPerfilCorreoId(String perfilCorreoId) {
		this.perfilCorreoId = perfilCorreoId;
	}


	public String getReferenciaPrincipalId() {
		return referenciaPrincipalId;
	}


	public void setReferenciaPrincipalId(String referenciaPrincipalId) {
		this.referenciaPrincipalId = referenciaPrincipalId;
	}

	
}
