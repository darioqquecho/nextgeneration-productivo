package net.royal.spring.alertas.dominio.dto;

import java.util.Date;

public class BProgramacionBase {

	private Date fechaInicio;
	private Date fechaFin;
	private String flagTieneFin;
	private String tipoFrecuenciaEjecucion;
	private Date frecuenciaUnica_HoraEjecucion;
	private Integer frecuenciaRango_CadaN;
	private String frecuenciaRango_Frecuencia;
	private Date frecuenciaRango_HoraInicio;
	private Date frecuenciaRango_HoraFin;

	/**
	 * indica a partir de que fecha y hora se inicia la programacion. </br>
	 * <b>tabla</b> SGALERTASSYS.REGL_NEGO_EJEC <b>campo</b> FE_DIA_INIC
	 */
	public Date getFechaInicio() {
		return fechaInicio;
	}

	/**
	 * indica a partir de que fecha y hora se inicia la programacion. </br>
	 * <b>tabla</b> SGALERTASSYS.REGL_NEGO_EJEC <b>campo</b> FE_DIA_INIC
	 */
	public void setFechaInicio(Date fechaInicio) {
		this.fechaInicio = fechaInicio;
	}

	/**
	 * indica en que fecha y hora se da fin la programacion. </br> <b>tabla</b>
	 * SGALERTASSYS.REGL_NEGO_EJEC <b>campo</b> FE_DIA_TERM
	 * */
	public Date getFechaFin() {
		return fechaFin;
	}

	/**
	 * indica en que fecha y hora se da fin la programacion. </br> <b>tabla</b>
	 * SGALERTASSYS.REGL_NEGO_EJEC <b>campo</b> FE_DIA_TERM
	 * */
	public void setFechaFin(Date fechaFin) {
		this.fechaFin = fechaFin;
	}

	/**
	 * indica si la programacion tiene fin(T), o si es infinita(F). </br>
	 * <b>tabla</b> SGALERTASSYS.REGL_NEGO_EJEC <b>campo</b> TI_DURA - N=False
	 * T=True
	 * */
	public String getFlagTieneFin() {
		return flagTieneFin;
	}

	/**
	 * indica si la programacion tiene fin(T), o si es infinita(F). </br>
	 * <b>tabla</b> SGALERTASSYS.REGL_NEGO_EJEC <b>campo</b> TI_DURA - N=False
	 * T=True
	 * */
	public void setFlagTieneFin(String flagTieneFin) {
		this.flagTieneFin = flagTieneFin;
	}

	/**
	 * indica cual es la frecuencia de ejecucion. </br> <b>U</b> = se debe
	 * ejecutar una sola vez</br> <b>F</b> = se especifica un rango de
	 * ejecucion.</br> <b>tabla</b> SGALERTASSYS.REGL_NEGO_EJEC <b>campo</b>
	 * TI_HORA
	 * */
	public String getTipoFrecuenciaEjecucion() {
		return tipoFrecuenciaEjecucion;
	}

	/**
	 * indica cual es la frecuencia de ejecucion. </br> <b>U</b> = se debe
	 * ejecutar una sola vez</br> <b>F</b> = se especifica un rango de
	 * ejecucion.</br> <b>tabla</b> SGALERTASSYS.REGL_NEGO_EJEC <b>campo</b>
	 * TI_HORA
	 * */
	public void setTipoFrecuenciaEjecucion(String tipoFrecuenciaEjecucion) {
		this.tipoFrecuenciaEjecucion = tipoFrecuenciaEjecucion;
	}

	/**
	 * si el tipo de frecuencia de ejecucion es U, aqui se almacena la hora en
	 * la que se debe ejecutar. </br> <b>tabla</b> SGALERTASSYS.REGL_NEGO_EJEC
	 * <b>campo</b> FE_HORA_UNIC
	 * */
	public Date getFrecuenciaUnica_HoraEjecucion() {
		return frecuenciaUnica_HoraEjecucion;
	}

	/**
	 * si el tipo de frecuencia de ejecucion es U, aqui se almacena la hora en
	 * la que se debe ejecutar. </br> <b>tabla</b> SGALERTASSYS.REGL_NEGO_EJEC
	 * <b>campo</b> FE_HORA_UNIC
	 * */
	public void setFrecuenciaUnica_HoraEjecucion(
			Date frecuenciaUnica_HoraEjecucion) {
		this.frecuenciaUnica_HoraEjecucion = frecuenciaUnica_HoraEjecucion;
	}

	/**
	 * si el tipo de frecuencia de ejecucion es R, se almacena cada cuanto se
	 * ejecutara. </br> <b>tabla</b> SGALERTASSYS.REGL_NEGO_EJEC <b>campo</b>
	 * NU_HORA
	 * */
	public Integer getFrecuenciaRango_CadaN() {
		return frecuenciaRango_CadaN;
	}

	/**
	 * si el tipo de frecuencia de ejecucion es R, se almacena cada cuanto se
	 * ejecutara. </br> <b>tabla</b> SGALERTASSYS.REGL_NEGO_EJEC <b>campo</b>
	 * NU_HORA
	 * */
	public void setFrecuenciaRango_CadaN(Integer frecuenciaRango_CadaN) {
		this.frecuenciaRango_CadaN = frecuenciaRango_CadaN;
	}

	/**
	 * si el tipo de frecuencia de ejecucion es R, se almacena el tipo de
	 * frecuencia va con frecuenciaRango_CadaN. </br> <b>(1)</b> - (H)oras</br>
	 * <b>(2)</b> - (M)inutos</br> <b>tabla</b> SGALERTASSYS.REGL_NEGO_EJEC
	 * <b>campo</b> TI_HORA_FREC</br><b>**</b>se cambio para que soporte los valores de
	 * HOR,MIN
	 * */
	public String getFrecuenciaRango_Frecuencia() {
		return frecuenciaRango_Frecuencia;
	}

	/**
	 * si el tipo de frecuencia de ejecucion es R, se almacena el tipo de
	 * frecuencia va con frecuenciaRango_CadaN. </br> <b>(1)</b> - (H)oras</br>
	 * <b>(2)</b> - (M)inutos</br> <b>tabla</b> SGALERTASSYS.REGL_NEGO_EJEC
	 * <b>campo</b> TI_HORA_FREC</br><b>**</b>se cambio para que soporte los valores de
	 * HOR,MIN
	 * */
	public void setFrecuenciaRango_Frecuencia(String frecuenciaRango_Frecuencia) {
		this.frecuenciaRango_Frecuencia = frecuenciaRango_Frecuencia;
	}

	/**
	 * si el tipo de frecuencia de ejecucion es R, se almacena la hora de
	 * inicio. </br> <b>tabla</b> SGALERTASSYS.REGL_NEGO_EJEC <b>campo</b>
	 * FE_HORA_INIC
	 * */
	public Date getFrecuenciaRango_HoraInicio() {
		return frecuenciaRango_HoraInicio;
	}

	/**
	 * si el tipo de frecuencia de ejecucion es R, se almacena la hora de
	 * inicio. </br> <b>tabla</b> SGALERTASSYS.REGL_NEGO_EJEC <b>campo</b>
	 * FE_HORA_INIC
	 * */
	public void setFrecuenciaRango_HoraInicio(Date frecuenciaRango_HoraInicio) {
		this.frecuenciaRango_HoraInicio = frecuenciaRango_HoraInicio;
	}

	/**
	 * si el tipo de frecuencia de ejecucion es R, se almacena la hora fin.
	 * </br> <b>tabla</b> SGALERTASSYS.REGL_NEGO_EJEC <b>campo</b> FE_HORA_TERM
	 * */
	public Date getFrecuenciaRango_HoraFin() {
		return frecuenciaRango_HoraFin;
	}

	/**
	 * si el tipo de frecuencia de ejecucion es R, se almacena la hora fin.
	 * </br> <b>tabla</b> SGALERTASSYS.REGL_NEGO_EJEC <b>campo</b> FE_HORA_TERM
	 * */
	public void setFrecuenciaRango_HoraFin(Date frecuenciaRango_HoraFin) {
		this.frecuenciaRango_HoraFin = frecuenciaRango_HoraFin;
	}

}
