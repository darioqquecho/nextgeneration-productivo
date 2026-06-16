package net.royal.spring.alertas.dominio.dto;

import java.math.BigDecimal;

public class ReglaNegocioProgramacionDto {

	
	private BigDecimal idReglaNegocio;
	private BigDecimal idProgramacion;
	private String idTipoProgramacion;
	private String nombre;
	private String descripcion;
	private String idFrecuenciaProgramacion;
	private java.util.Date fechaUltimaEjecucion;
	private String flgEjecutandoActualmente;
	private BigDecimal diaCadaNDias;
	private String diaIdFrecuencia;
	private java.util.Date diaFrecUnicaHoraejecucion;
	private BigDecimal diaFrecRangoCadan;
	private String diaFrecRangoIdFrecuTiempo;
	private java.util.Date diaFrecRangoHorainicio;
	private java.util.Date diaFrecRangoHorafin;
	private java.util.Date diaFechaInicio;
	private String diaFlgTieneFin;
	private java.util.Date diaFechaFin;
	private BigDecimal semCadaNSemanas;
	private String semFlgEjecutarLunes;
	private String semFlgEjecutarMartes;
	private String semFlgEjecutarMiercoles;
	private String semFlgEjecutarJueves;
	private String semFlgEjecutarViernes;
	private String semFlgEjecutarSabado;
	private String semFlgEjecutarDomingo;
	private String semIdFrecuencia;
	private java.util.Date semFrecUnicaHoraejecucion;
	
	private BigDecimal semFrecRangoCadan;
	private String semFrecRangoIdFrecuTiempo;
	private java.util.Date semFrecRangoHorainicio;
	private java.util.Date semFrecRangoHorafin;
	
	
	private java.util.Date semFechaInicio;
	private String semFlgTieneFin;
	private java.util.Date semFechaFin;
	private String mesIdFrecuenciaMensual;
	private BigDecimal mesFrecDiaDia;
	private BigDecimal mesFrecDiaCadan;
	
	private String mesFrecNomDiaIdOrdenDia;
	private String mesFrecNomDiaIdDiaSemana;
	private BigDecimal mesFrecNomDiaCadan;
	
	private String mesIdFrecuencia;
	private java.util.Date mesFrecUnicaHoraejecucion;
	private BigDecimal mesFrecRangoCadan;
	private String mesFrecRangoIdFrecuTiempo;
	private java.util.Date mesFrecRangoHorainicio;
	private java.util.Date mesFrecRangoHorafin;
	
	private java.util.Date mesFechaInicio;
	private String mesFlgTieneFin;
	private java.util.Date mesFechaFin;
	private String estado;
	private String creacionUsuario;
	private java.util.Date creacionFecha;
	private String creacionTerminal;
	private String modificacionUsuario;
	private java.util.Date modificacionFecha;
	private String modificacionTerminal;
	private String estadoaux;
	private java.util.Date fechaEjecutandoActualmente;
	private Boolean auxFlgPreparado=Boolean.FALSE;
	private BigDecimal ROWNUM_;
	private String flgActualizar;
	
	private String flgalterado;
	
	
	
	public String getFlgalterado() {
		return flgalterado;
	}
	public void setFlgalterado(String flgalterado) {
		this.flgalterado = flgalterado;
	}
	public String getFlgActualizar() {
		return flgActualizar;
	}
	public void setFlgActualizar(String flgActualizar) {
		this.flgActualizar = flgActualizar;
	}
	public BigDecimal getROWNUM_() {
		return ROWNUM_;
	}
	public void setROWNUM_(BigDecimal rOWNUM_) {
		ROWNUM_ = rOWNUM_;
	}
	public BigDecimal getIdReglaNegocio() {
		return idReglaNegocio;
	}
	public String getEstadoaux() {
		return estadoaux;
	}
	public void setEstadoaux(String estadoaux) {
		this.estadoaux = estadoaux;
	}
	
	public void setIdReglaNegocio(BigDecimal idReglaNegocio) {
		this.idReglaNegocio = idReglaNegocio;
	}
	public BigDecimal getIdProgramacion() {
		return idProgramacion;
	}
	public void setIdProgramacion(BigDecimal idProgramacion) {
		this.idProgramacion = idProgramacion;
	}
	public String getIdTipoProgramacion() {
		return idTipoProgramacion;
	}
	public void setIdTipoProgramacion(String idTipoProgramacion) {
		this.idTipoProgramacion = idTipoProgramacion;
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
	public String getIdFrecuenciaProgramacion() {
		return idFrecuenciaProgramacion;
	}
	public void setIdFrecuenciaProgramacion(String idFrecuenciaProgramacion) {
		this.idFrecuenciaProgramacion = idFrecuenciaProgramacion;
	}
	public java.util.Date getFechaUltimaEjecucion() {
		return fechaUltimaEjecucion;
	}
	public void setFechaUltimaEjecucion(java.util.Date fechaUltimaEjecucion) {
		this.fechaUltimaEjecucion = fechaUltimaEjecucion;
	}
	public String getFlgEjecutandoActualmente() {
		return flgEjecutandoActualmente;
	}
	public void setFlgEjecutandoActualmente(String flgEjecutandoActualmente) {
		this.flgEjecutandoActualmente = flgEjecutandoActualmente;
	}
	public BigDecimal getDiaCadaNDias() {
		return diaCadaNDias;
	}
	public void setDiaCadaNDias(BigDecimal diaCadaNDias) {
		this.diaCadaNDias = diaCadaNDias;
	}
	public String getDiaIdFrecuencia() {
		return diaIdFrecuencia;
	}
	public void setDiaIdFrecuencia(String diaIdFrecuencia) {
		this.diaIdFrecuencia = diaIdFrecuencia;
	}
	public java.util.Date getDiaFrecUnicaHoraejecucion() {
		return diaFrecUnicaHoraejecucion;
	}
	public void setDiaFrecUnicaHoraejecucion(java.util.Date diaFrecUnicaHoraejecucion) {
		this.diaFrecUnicaHoraejecucion = diaFrecUnicaHoraejecucion;
	}
	public BigDecimal getDiaFrecRangoCadan() {
		return diaFrecRangoCadan;
	}
	public void setDiaFrecRangoCadan(BigDecimal diaFrecRangoCadan) {
		this.diaFrecRangoCadan = diaFrecRangoCadan;
	}
	public String getDiaFrecRangoIdFrecuTiempo() {
		return diaFrecRangoIdFrecuTiempo;
	}
	public void setDiaFrecRangoIdFrecuTiempo(String diaFrecRangoIdFrecuTiempo) {
		this.diaFrecRangoIdFrecuTiempo = diaFrecRangoIdFrecuTiempo;
	}
	public java.util.Date getDiaFrecRangoHorainicio() {
		return diaFrecRangoHorainicio;
	}
	public void setDiaFrecRangoHorainicio(java.util.Date diaFrecRangoHorainicio) {
		this.diaFrecRangoHorainicio = diaFrecRangoHorainicio;
	}
	public java.util.Date getDiaFrecRangoHorafin() {
		return diaFrecRangoHorafin;
	}
	public void setDiaFrecRangoHorafin(java.util.Date diaFrecRangoHorafin) {
		this.diaFrecRangoHorafin = diaFrecRangoHorafin;
	}
	public java.util.Date getDiaFechaInicio() {
		return diaFechaInicio;
	}
	public void setDiaFechaInicio(java.util.Date diaFechaInicio) {
		this.diaFechaInicio = diaFechaInicio;
	}
	public String getDiaFlgTieneFin() {
		return diaFlgTieneFin;
	}
	public void setDiaFlgTieneFin(String diaFlgTieneFin) {
		this.diaFlgTieneFin = diaFlgTieneFin;
	}
	public java.util.Date getDiaFechaFin() {
		return diaFechaFin;
	}
	public void setDiaFechaFin(java.util.Date diaFechaFin) {
		this.diaFechaFin = diaFechaFin;
	}
	public BigDecimal getSemCadaNSemanas() {
		return semCadaNSemanas;
	}
	public void setSemCadaNSemanas(BigDecimal semCadaNSemanas) {
		this.semCadaNSemanas = semCadaNSemanas;
	}
	public String getSemFlgEjecutarLunes() {
		return semFlgEjecutarLunes;
	}
	public void setSemFlgEjecutarLunes(String semFlgEjecutarLunes) {
		this.semFlgEjecutarLunes = semFlgEjecutarLunes;
	}
	public String getSemFlgEjecutarMartes() {
		return semFlgEjecutarMartes;
	}
	public void setSemFlgEjecutarMartes(String semFlgEjecutarMartes) {
		this.semFlgEjecutarMartes = semFlgEjecutarMartes;
	}
	public String getSemFlgEjecutarMiercoles() {
		return semFlgEjecutarMiercoles;
	}
	public void setSemFlgEjecutarMiercoles(String semFlgEjecutarMiercoles) {
		this.semFlgEjecutarMiercoles = semFlgEjecutarMiercoles;
	}
	public String getSemFlgEjecutarJueves() {
		return semFlgEjecutarJueves;
	}
	public void setSemFlgEjecutarJueves(String semFlgEjecutarJueves) {
		this.semFlgEjecutarJueves = semFlgEjecutarJueves;
	}
	public String getSemFlgEjecutarViernes() {
		return semFlgEjecutarViernes;
	}
	public void setSemFlgEjecutarViernes(String semFlgEjecutarViernes) {
		this.semFlgEjecutarViernes = semFlgEjecutarViernes;
	}
	public String getSemFlgEjecutarSabado() {
		return semFlgEjecutarSabado;
	}
	public void setSemFlgEjecutarSabado(String semFlgEjecutarSabado) {
		this.semFlgEjecutarSabado = semFlgEjecutarSabado;
	}
	public String getSemFlgEjecutarDomingo() {
		return semFlgEjecutarDomingo;
	}
	public void setSemFlgEjecutarDomingo(String semFlgEjecutarDomingo) {
		this.semFlgEjecutarDomingo = semFlgEjecutarDomingo;
	}
	public String getSemIdFrecuencia() {
		return semIdFrecuencia;
	}
	public void setSemIdFrecuencia(String semIdFrecuencia) {
		this.semIdFrecuencia = semIdFrecuencia;
	}
	public java.util.Date getSemFrecUnicaHoraejecucion() {
		return semFrecUnicaHoraejecucion;
	}
	public void setSemFrecUnicaHoraejecucion(java.util.Date semFrecUnicaHoraejecucion) {
		this.semFrecUnicaHoraejecucion = semFrecUnicaHoraejecucion;
	}
	public BigDecimal getSemFrecRangoCadan() {
		return semFrecRangoCadan;
	}
	public void setSemFrecRangoCadan(BigDecimal semFrecRangoCadan) {
		this.semFrecRangoCadan = semFrecRangoCadan;
	}
	public String getSemFrecRangoIdFrecuTiempo() {
		return semFrecRangoIdFrecuTiempo;
	}
	public void setSemFrecRangoIdFrecuTiempo(String semFrecRangoIdFrecuTiempo) {
		this.semFrecRangoIdFrecuTiempo = semFrecRangoIdFrecuTiempo;
	}
	public java.util.Date getSemFrecRangoHorainicio() {
		return semFrecRangoHorainicio;
	}
	public void setSemFrecRangoHorainicio(java.util.Date semFrecRangoHorainicio) {
		this.semFrecRangoHorainicio = semFrecRangoHorainicio;
	}
	public java.util.Date getSemFrecRangoHorafin() {
		return semFrecRangoHorafin;
	}
	public void setSemFrecRangoHorafin(java.util.Date semFrecRangoHorafin) {
		this.semFrecRangoHorafin = semFrecRangoHorafin;
	}
	public java.util.Date getSemFechaInicio() {
		return semFechaInicio;
	}
	public void setSemFechaInicio(java.util.Date semFechaInicio) {
		this.semFechaInicio = semFechaInicio;
	}
	public String getSemFlgTieneFin() {
		return semFlgTieneFin;
	}
	public void setSemFlgTieneFin(String semFlgTieneFin) {
		this.semFlgTieneFin = semFlgTieneFin;
	}
	public java.util.Date getSemFechaFin() {
		return semFechaFin;
	}
	public void setSemFechaFin(java.util.Date semFechaFin) {
		this.semFechaFin = semFechaFin;
	}
	public String getMesIdFrecuenciaMensual() {
		return mesIdFrecuenciaMensual;
	}
	public void setMesIdFrecuenciaMensual(String mesIdFrecuenciaMensual) {
		this.mesIdFrecuenciaMensual = mesIdFrecuenciaMensual;
	}
	public BigDecimal getMesFrecDiaDia() {
		return mesFrecDiaDia;
	}
	public void setMesFrecDiaDia(BigDecimal mesFrecDiaDia) {
		this.mesFrecDiaDia = mesFrecDiaDia;
	}
	public BigDecimal getMesFrecDiaCadan() {
		return mesFrecDiaCadan;
	}
	public void setMesFrecDiaCadan(BigDecimal mesFrecDiaCadan) {
		this.mesFrecDiaCadan = mesFrecDiaCadan;
	}
	public String getMesFrecNomDiaIdOrdenDia() {
		return mesFrecNomDiaIdOrdenDia;
	}
	public void setMesFrecNomDiaIdOrdenDia(String mesFrecNomDiaIdOrdenDia) {
		this.mesFrecNomDiaIdOrdenDia = mesFrecNomDiaIdOrdenDia;
	}
	public String getMesFrecNomDiaIdDiaSemana() {
		return mesFrecNomDiaIdDiaSemana;
	}
	public void setMesFrecNomDiaIdDiaSemana(String mesFrecNomDiaIdDiaSemana) {
		this.mesFrecNomDiaIdDiaSemana = mesFrecNomDiaIdDiaSemana;
	}
	public BigDecimal getMesFrecNomDiaCadan() {
		return mesFrecNomDiaCadan;
	}
	public void setMesFrecNomDiaCadan(BigDecimal mesFrecNomDiaCadan) {
		this.mesFrecNomDiaCadan = mesFrecNomDiaCadan;
	}
	public String getMesIdFrecuencia() {
		return mesIdFrecuencia;
	}
	public void setMesIdFrecuencia(String mesIdFrecuencia) {
		this.mesIdFrecuencia = mesIdFrecuencia;
	}
	public java.util.Date getMesFrecUnicaHoraejecucion() {
		return mesFrecUnicaHoraejecucion;
	}
	public void setMesFrecUnicaHoraejecucion(java.util.Date mesFrecUnicaHoraejecucion) {
		this.mesFrecUnicaHoraejecucion = mesFrecUnicaHoraejecucion;
	}
	public BigDecimal getMesFrecRangoCadan() {
		return mesFrecRangoCadan;
	}
	public void setMesFrecRangoCadan(BigDecimal mesFrecRangoCadan) {
		this.mesFrecRangoCadan = mesFrecRangoCadan;
	}
	public String getMesFrecRangoIdFrecuTiempo() {
		return mesFrecRangoIdFrecuTiempo;
	}
	public void setMesFrecRangoIdFrecuTiempo(String mesFrecRangoIdFrecuTiempo) {
		this.mesFrecRangoIdFrecuTiempo = mesFrecRangoIdFrecuTiempo;
	}
	public java.util.Date getMesFrecRangoHorainicio() {
		return mesFrecRangoHorainicio;
	}
	public void setMesFrecRangoHorainicio(java.util.Date mesFrecRangoHorainicio) {
		this.mesFrecRangoHorainicio = mesFrecRangoHorainicio;
	}
	public java.util.Date getMesFrecRangoHorafin() {
		return mesFrecRangoHorafin;
	}
	public void setMesFrecRangoHorafin(java.util.Date mesFrecRangoHorafin) {
		this.mesFrecRangoHorafin = mesFrecRangoHorafin;
	}
	public java.util.Date getMesFechaInicio() {
		return mesFechaInicio;
	}
	public void setMesFechaInicio(java.util.Date mesFechaInicio) {
		this.mesFechaInicio = mesFechaInicio;
	}
	public String getMesFlgTieneFin() {
		return mesFlgTieneFin;
	}
	public void setMesFlgTieneFin(String mesFlgTieneFin) {
		this.mesFlgTieneFin = mesFlgTieneFin;
	}
	public java.util.Date getMesFechaFin() {
		return mesFechaFin;
	}
	public void setMesFechaFin(java.util.Date mesFechaFin) {
		this.mesFechaFin = mesFechaFin;
	}
	public String getEstado() {
		return estado;
	}
	public void setEstado(String estado) {
		this.estado = estado;
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
	public java.util.Date getFechaEjecutandoActualmente() {
		return fechaEjecutandoActualmente;
	}
	public void setFechaEjecutandoActualmente(java.util.Date fechaEjecutandoActualmente) {
		this.fechaEjecutandoActualmente = fechaEjecutandoActualmente;
	}
	public Boolean getAuxFlgPreparado() {
		return auxFlgPreparado;
	}
	public void setAuxFlgPreparado(Boolean auxFlgPreparado) {
		this.auxFlgPreparado = auxFlgPreparado;
	}
	
	
}
