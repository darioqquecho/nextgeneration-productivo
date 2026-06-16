package net.royal.spring.alertas.dominio.dto;

import java.util.Date;

public class BProgramacion {

	private Integer idReglaNegocio;
	private Integer idReglaNegocioProgramacionEjecucion;
	private Date fechaUltimaEjecucion;
	
	private String tipoProgramacion;

	// depende del tipo de programacion
	private BProgramacionDiaria diaria;

	// depende del tipo de programacion
	private BProgramacionSemanal semanal;

	// depende del tipo de programacion
	private BProgramacionMensual mensual;

	public BProgramacion() {
		diaria = new BProgramacionDiaria();
		semanal = new BProgramacionSemanal();
		mensual = new BProgramacionMensual();
	}

	public Integer getIdReglaNegocio() {
		return idReglaNegocio;
	}

	public void setIdReglaNegocio(Integer idReglaNegocio) {
		this.idReglaNegocio = idReglaNegocio;
	}

	public Integer getIdReglaNegocioProgramacionEjecucion() {
		return idReglaNegocioProgramacionEjecucion;
	}

	public void setIdReglaNegocioProgramacionEjecucion(
			Integer idReglaNegocioProgramacionEjecucion) {
		this.idReglaNegocioProgramacionEjecucion = idReglaNegocioProgramacionEjecucion;
	}

	public Date getFechaUltimaEjecucion() {
		return fechaUltimaEjecucion;
	}

	public void setFechaUltimaEjecucion(Date fechaUltimaEjecucion) {
		this.fechaUltimaEjecucion = fechaUltimaEjecucion;
	}

	/**
	 * solo puede tener 3 tipos.</br> <b>S</b> = Semanal</br> <b>D</b> =
	 * Diaria</br> <b>M</b> = mensual</br> <b>tabla</b>
	 * SGALERTASSYS.REGL_NEGO_EJEC <b>campo</b> TI_PROG_EJEC
	 */
	public String getTipoProgramacion() {
		return tipoProgramacion;
	}

	/**
	 * solo puede tener 3 tipos.</br> <b>S</b> = Semanal</br> <b>D</b> =
	 * Diaria</br> <b>M</b> = mensual</br> <b>tabla</b>
	 * SGALERTASSYS.REGL_NEGO_EJEC <b>campo</b> TI_PROG_EJEC
	 */
	public void setTipoProgramacion(String tipoProgramacion) {
		this.tipoProgramacion = tipoProgramacion;
	}

	public BProgramacionDiaria getDiaria() {
		return diaria;
	}

	public void setDiaria(BProgramacionDiaria diaria) {
		this.diaria = diaria;
	}

	public BProgramacionSemanal getSemanal() {
		return semanal;
	}

	public void setSemanal(BProgramacionSemanal semanal) {
		this.semanal = semanal;
	}

	public BProgramacionMensual getMensual() {
		return mensual;
	}

	public void setMensual(BProgramacionMensual mensual) {
		this.mensual = mensual;
	}

}
