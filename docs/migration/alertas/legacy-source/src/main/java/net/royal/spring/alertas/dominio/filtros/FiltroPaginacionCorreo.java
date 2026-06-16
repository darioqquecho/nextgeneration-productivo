package net.royal.spring.alertas.dominio.filtros;

import java.math.BigDecimal;
import java.util.Date;

import net.royal.spring.framework.modelo.generico.DominioPaginacion;

public class FiltroPaginacionCorreo {
	private DominioPaginacion paginacion;
	
	private BigDecimal idreglanegocio;
	private BigDecimal idcorreo;
	private Date fechaPreparacionInicio;
	private Date fechaPreparacionFin;
	private String p_estado;
	private String p_resumenlogalerta; 
	private String correodestino;
	
	private String proceso;
	private String referencia;
	private String transaccion;
	private String referenciaPadre;
	private String referenciaPrincipalId;
	
	public String getCorreodestino() {
		return correodestino;
	}

	public void setCorreodestino(String correodestino) {
		this.correodestino = correodestino;
	}

	public BigDecimal getIdreglanegocio() {
		return idreglanegocio;
	}

	public void setIdreglanegocio(BigDecimal idreglanegocio) {
		this.idreglanegocio = idreglanegocio;
	}

	public BigDecimal getIdcorreo() {
		return idcorreo;
	}

	public void setIdcorreo(BigDecimal idcorreo) {
		this.idcorreo = idcorreo;
	}

	public Date getFechaPreparacionInicio() {
		return fechaPreparacionInicio;
	}

	public void setFechaPreparacionInicio(Date fechaPreparacionInicio) {
		this.fechaPreparacionInicio = fechaPreparacionInicio;
	}

	public Date getFechaPreparacionFin() {
		return fechaPreparacionFin;
	}

	public void setFechaPreparacionFin(Date fechaPreparacionFin) {
		this.fechaPreparacionFin = fechaPreparacionFin;
	}

	public String getP_estado() {
		return p_estado;
	}

	public void setP_estado(String p_estado) {
		this.p_estado = p_estado;
	}

	public String getP_resumenlogalerta() {
		return p_resumenlogalerta;
	}

	public void setP_resumenlogalerta(String p_resumenlogalerta) {
		this.p_resumenlogalerta = p_resumenlogalerta;
	}

	public DominioPaginacion getPaginacion() {
		return paginacion;
	}

	public void setPaginacion(DominioPaginacion paginacion) {
		this.paginacion = paginacion;
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

	public String getTransaccion() {
		return transaccion;
	}

	public void setTransaccion(String transaccion) {
		this.transaccion = transaccion;
	}

	public String getReferenciaPadre() {
		return referenciaPadre;
	}

	public void setReferenciaPadre(String referenciaPadre) {
		this.referenciaPadre = referenciaPadre;
	}

	public String getReferenciaPrincipalId() {
		return referenciaPrincipalId;
	}

	public void setReferenciaPrincipalId(String referenciaPrincipalId) {
		this.referenciaPrincipalId = referenciaPrincipalId;
	}
	
}
