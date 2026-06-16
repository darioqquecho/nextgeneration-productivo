package net.royal.spring.alertas.dominio.dto;

import java.math.BigDecimal;
import java.util.Date;

public class CorreoDto {

	private BigDecimal idCorreo;
	private String resumenLogAlerta;
	private BigDecimal idReglaNegocio;
	private String nombre;
	private Date creacionFecha;
	private String estado;
	private Date fechaHoraEnvio;

	private String asunto;
	private String proceso;
	private String referencia;
	private String referenciaPadre;
	private Integer transaccion;
	private String perfilCorreoId;
	private String referenciaPrincipalId;
	
	private BigDecimal ROWNUM_;

	public BigDecimal getIdCorreo() {
		return idCorreo;
	}

	public void setIdCorreo(BigDecimal idCorreo) {
		this.idCorreo = idCorreo;
	}

	public String getResumenLogAlerta() {
		return resumenLogAlerta;
	}

	public void setResumenLogAlerta(String resumenLogAlerta) {
		this.resumenLogAlerta = resumenLogAlerta;
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

	public Date getCreacionFecha() {
		return creacionFecha;
	}

	public void setCreacionFecha(Date creacionFecha) {
		this.creacionFecha = creacionFecha;
	}

	public String getEstado() {
		return estado;
	}

	public void setEstado(String estado) {
		this.estado = estado;
	}

	public Date getFechaHoraEnvio() {
		return fechaHoraEnvio;
	}

	public void setFechaHoraEnvio(Date fechaHoraEnvio) {
		this.fechaHoraEnvio = fechaHoraEnvio;
	}

	public BigDecimal getROWNUM_() {
		return ROWNUM_;
	}

	public void setROWNUM_(BigDecimal rOWNUM_) {
		ROWNUM_ = rOWNUM_;
	}

	public String getAsunto() {
		return asunto;
	}

	public void setAsunto(String asunto) {
		this.asunto = asunto;
	}

	public String getProceso() {
		return proceso;
	}

	public void setProceso(String proceso) {
		this.proceso = proceso;
	}

	public String getReferencia() {
		return referencia;
	}

	public void setReferencia(String referencia) {
		this.referencia = referencia;
	}

	public Integer getTransaccion() {
		return transaccion;
	}

	public void setTransaccion(Integer transaccion) {
		this.transaccion = transaccion;
	}

	public String getReferenciaPadre() {
		return referenciaPadre;
	}

	public void setReferenciaPadre(String referenciaPadre) {
		this.referenciaPadre = referenciaPadre;
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
