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
 * @tabla SGALERTASSYS.REGLA_NEGOCIO_PROGRAMACION
*/
@Entity
@Table(name = "REGLA_NEGOCIO_PROGRAMACION",schema="SGALERTASSYS")
public class ReglaNegocioProgramacion implements java.io.Serializable{
	private static final long serialVersionUID = 1L;

	@EmbeddedId
	private ReglaNegocioProgramacionPk pk;

	@Size(min = 0, max = 3)
	@Column(name = "ID_TIPO_PROGRAMACION", length = 3, nullable = true)
	private String idTipoProgramacion;

	@Size(min = 0, max = 200)
	@Column(name = "NOMBRE", length = 200, nullable = true)
	private String nombre;

	@Size(min = 0, max = 500)
	@Column(name = "DESCRIPCION", length = 500, nullable = true)
	private String descripcion;

	@Size(min = 0, max = 3)
	@Column(name = "ID_FRECUENCIA_PROGRAMACION", length = 3, nullable = true)
	private String idFrecuenciaProgramacion;

	@JsonSerialize(using = USerializers.DateSerializer.class)
	@JsonDeserialize(using = UDeserializers.DateDeserializer.class)
	@Column(name = "FECHA_ULTIMA_EJECUCION", nullable = true)
	private java.util.Date fechaUltimaEjecucion;

	@Size(min = 0, max = 1)
	@Column(name = "FLG_EJECUTANDO_ACTUALMENTE", length = 1, nullable = true)
	private String flgEjecutandoActualmente;

	@Column(name = "DIA_CADA_N_DIAS", nullable = true)
	private Integer diaCadaNDias;

	@Size(min = 0, max = 3)
	@Column(name = "DIA_ID_FRECUENCIA", length = 3, nullable = true)
	private String diaIdFrecuencia;

	@JsonSerialize(using = USerializers.DateSerializer.class)
	@JsonDeserialize(using = UDeserializers.DateDeserializer.class)
	@Column(name = "DIA_FREC_UNICA_HORAEJECUCION", nullable = true)
	private java.util.Date diaFrecUnicaHoraejecucion;

	@Column(name = "DIA_FREC_RANGO_CADAN", nullable = true)
	private Integer diaFrecRangoCadan;

	@Size(min = 0, max = 3)
	@Column(name = "DIA_FREC_RANGO_ID_FRECU_TIEMPO", length = 3, nullable = true)
	private String diaFrecRangoIdFrecuTiempo;

	@JsonSerialize(using = USerializers.DateSerializer.class)
	@JsonDeserialize(using = UDeserializers.DateDeserializer.class)
	@Column(name = "DIA_FREC_RANGO_HORAINICIO", nullable = true)
	private java.util.Date diaFrecRangoHorainicio;

	@JsonSerialize(using = USerializers.DateSerializer.class)
	@JsonDeserialize(using = UDeserializers.DateDeserializer.class)
	@Column(name = "DIA_FREC_RANGO_HORAFIN", nullable = true)
	private java.util.Date diaFrecRangoHorafin;

	@JsonSerialize(using = USerializers.DateSerializer.class)
	@JsonDeserialize(using = UDeserializers.DateDeserializer.class)
	@Column(name = "DIA_FECHA_INICIO", nullable = true)
	private java.util.Date diaFechaInicio;

	@Size(min = 0, max = 1)
	@Column(name = "DIA_FLG_TIENE_FIN", length = 1, nullable = true)
	private String diaFlgTieneFin;

	@JsonSerialize(using = USerializers.DateSerializer.class)
	@JsonDeserialize(using = UDeserializers.DateDeserializer.class)
	@Column(name = "DIA_FECHA_FIN", nullable = true)
	private java.util.Date diaFechaFin;

	@Column(name = "SEM_CADA_N_SEMANAS", nullable = true)
	private Integer semCadaNSemanas;

	@Size(min = 0, max = 1)
	@Column(name = "SEM_FLG_EJECUTAR_LUNES", length = 1, nullable = true)
	private String semFlgEjecutarLunes;

	@Size(min = 0, max = 1)
	@Column(name = "SEM_FLG_EJECUTAR_MARTES", length = 1, nullable = true)
	private String semFlgEjecutarMartes;

	@Size(min = 0, max = 1)
	@Column(name = "SEM_FLG_EJECUTAR_MIERCOLES", length = 1, nullable = true)
	private String semFlgEjecutarMiercoles;

	@Size(min = 0, max = 1)
	@Column(name = "SEM_FLG_EJECUTAR_JUEVES", length = 1, nullable = true)
	private String semFlgEjecutarJueves;

	@Size(min = 0, max = 1)
	@Column(name = "SEM_FLG_EJECUTAR_VIERNES", length = 1, nullable = true)
	private String semFlgEjecutarViernes;

	@Size(min = 0, max = 1)
	@Column(name = "SEM_FLG_EJECUTAR_SABADO", length = 1, nullable = true)
	private String semFlgEjecutarSabado;

	@Size(min = 0, max = 1)
	@Column(name = "SEM_FLG_EJECUTAR_DOMINGO", length = 1, nullable = true)
	private String semFlgEjecutarDomingo;

	@Size(min = 0, max = 3)
	@Column(name = "SEM_ID_FRECUENCIA", length = 3, nullable = true)
	private String semIdFrecuencia;

	@JsonSerialize(using = USerializers.DateSerializer.class)
	@JsonDeserialize(using = UDeserializers.DateDeserializer.class)
	@Column(name = "SEM_FREC_UNICA_HORAEJECUCION", nullable = true)
	private java.util.Date semFrecUnicaHoraejecucion;

	@Column(name = "SEM_FREC_RANGO_CADAN", nullable = true)
	private Integer semFrecRangoCadan;

	@Size(min = 0, max = 3)
	@Column(name = "SEM_FREC_RANGO_ID_FRECU_TIEMPO", length = 3, nullable = true)
	private String semFrecRangoIdFrecuTiempo;

	@JsonSerialize(using = USerializers.DateSerializer.class)
	@JsonDeserialize(using = UDeserializers.DateDeserializer.class)
	@Column(name = "SEM_FREC_RANGO_HORAINICIO", nullable = true)
	private java.util.Date semFrecRangoHorainicio;

	@JsonSerialize(using = USerializers.DateSerializer.class)
	@JsonDeserialize(using = UDeserializers.DateDeserializer.class)
	@Column(name = "SEM_FREC_RANGO_HORAFIN", nullable = true)
	private java.util.Date semFrecRangoHorafin;

	@JsonSerialize(using = USerializers.DateSerializer.class)
	@JsonDeserialize(using = UDeserializers.DateDeserializer.class)
	@Column(name = "SEM_FECHA_INICIO", nullable = true)
	private java.util.Date semFechaInicio;

	@Size(min = 0, max = 1)
	@Column(name = "SEM_FLG_TIENE_FIN", length = 1, nullable = true)
	private String semFlgTieneFin;

	@JsonSerialize(using = USerializers.DateSerializer.class)
	@JsonDeserialize(using = UDeserializers.DateDeserializer.class)
	@Column(name = "SEM_FECHA_FIN", nullable = true)
	private java.util.Date semFechaFin;

	@Size(min = 0, max = 3)
	@Column(name = "MES_ID_FRECUENCIA_MENSUAL", length = 3, nullable = true)
	private String mesIdFrecuenciaMensual;

	@Column(name = "MES_FREC_DIA_DIA", nullable = true)
	private Integer mesFrecDiaDia;

	@Column(name = "MES_FREC_DIA_CADAN", nullable = true)
	private Integer mesFrecDiaCadan;

	@Size(min = 0, max = 3)
	@Column(name = "MES_FREC_NOM_DIA_ID_ORDEN_DIA", length = 3, nullable = true)
	private String mesFrecNomDiaIdOrdenDia;

	@Size(min = 0, max = 3)
	@Column(name = "MES_FREC_NOM_DIA_ID_DIA_SEMANA", length = 3, nullable = true)
	private String mesFrecNomDiaIdDiaSemana;

	@Column(name = "MES_FREC_NOM_DIA_CADAN", nullable = true)
	private Integer mesFrecNomDiaCadan;

	@Size(min = 0, max = 3)
	@Column(name = "MES_ID_FRECUENCIA", length = 3, nullable = true)
	private String mesIdFrecuencia;

	@JsonSerialize(using = USerializers.DateSerializer.class)
	@JsonDeserialize(using = UDeserializers.DateDeserializer.class)
	@Column(name = "MES_FREC_UNICA_HORAEJECUCION", nullable = true)
	private java.util.Date mesFrecUnicaHoraejecucion;

	@Column(name = "MES_FREC_RANGO_CADAN", nullable = true)
	private Integer mesFrecRangoCadan;

	@Size(min = 0, max = 3)
	@Column(name = "MES_FREC_RANGO_ID_FRECU_TIEMPO", length = 3, nullable = true)
	private String mesFrecRangoIdFrecuTiempo;

	@JsonSerialize(using = USerializers.DateSerializer.class)
	@JsonDeserialize(using = UDeserializers.DateDeserializer.class)
	@Column(name = "MES_FREC_RANGO_HORAINICIO", nullable = true)
	private java.util.Date mesFrecRangoHorainicio;

	@JsonSerialize(using = USerializers.DateSerializer.class)
	@JsonDeserialize(using = UDeserializers.DateDeserializer.class)
	@Column(name = "MES_FREC_RANGO_HORAFIN", nullable = true)
	private java.util.Date mesFrecRangoHorafin;

	@JsonSerialize(using = USerializers.DateSerializer.class)
	@JsonDeserialize(using = UDeserializers.DateDeserializer.class)
	@Column(name = "MES_FECHA_INICIO", nullable = true)
	private java.util.Date mesFechaInicio;

	@Size(min = 0, max = 1)
	@Column(name = "MES_FLG_TIENE_FIN", length = 1, nullable = true)
	private String mesFlgTieneFin;

	@JsonSerialize(using = USerializers.DateSerializer.class)
	@JsonDeserialize(using = UDeserializers.DateDeserializer.class)
	@Column(name = "MES_FECHA_FIN", nullable = true)
	private java.util.Date mesFechaFin;

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

	@JsonSerialize(using = USerializers.DateSerializer.class)
	@JsonDeserialize(using = UDeserializers.DateDeserializer.class)
	@Column(name = "FECHA_EJECUTANDO_ACTUALMENTE", nullable = true)
	private java.util.Date fechaEjecutandoActualmente;

	@Transient
	private Boolean auxFlgPreparado=Boolean.FALSE;


	public ReglaNegocioProgramacion() {
		pk = new ReglaNegocioProgramacionPk();
	}


	public ReglaNegocioProgramacion(ReglaNegocioProgramacionPk pk) {
		this.pk = pk;
	}

	public ReglaNegocioProgramacionPk getPk() {
		return pk;
	}

	public void setPk(ReglaNegocioProgramacionPk pk) {
		this.pk = pk;
	}
	/**
	 *  
	 * 
	 * @campo ID_TIPO_PROGRAMACION
	*/
	public String getIdTipoProgramacion() {
		return idTipoProgramacion;
	}

	/**
	 *  
	 * 
	 * @campo ID_TIPO_PROGRAMACION
	*/
	public void setIdTipoProgramacion(String idTipoProgramacion) {
		this.idTipoProgramacion = idTipoProgramacion;
	}
	/**
	 *  
	 * 
	 * @campo NOMBRE
	*/
	public String getNombre() {
		return nombre;
	}

	/**
	 *  
	 * 
	 * @campo NOMBRE
	*/
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	/**
	 *  
	 * 
	 * @campo DESCRIPCION
	*/
	public String getDescripcion() {
		return descripcion;
	}

	/**
	 *  
	 * 
	 * @campo DESCRIPCION
	*/
	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}
	/**
	 *  
	 * 
	 * @campo ID_FRECUENCIA_PROGRAMACION
	*/
	public String getIdFrecuenciaProgramacion() {
		return idFrecuenciaProgramacion;
	}

	/**
	 *  
	 * 
	 * @campo ID_FRECUENCIA_PROGRAMACION
	*/
	public void setIdFrecuenciaProgramacion(String idFrecuenciaProgramacion) {
		this.idFrecuenciaProgramacion = idFrecuenciaProgramacion;
	}
	/**
	 *  
	 * 
	 * @campo FECHA_ULTIMA_EJECUCION
	*/
	public java.util.Date getFechaUltimaEjecucion() {
		return fechaUltimaEjecucion;
	}

	/**
	 *  
	 * 
	 * @campo FECHA_ULTIMA_EJECUCION
	*/
	public void setFechaUltimaEjecucion(java.util.Date fechaUltimaEjecucion) {
		this.fechaUltimaEjecucion = fechaUltimaEjecucion;
	}
	/**
	 *  
	 * 
	 * @campo FLG_EJECUTANDO_ACTUALMENTE
	*/
	public String getFlgEjecutandoActualmente() {
		return flgEjecutandoActualmente;
	}

	/**
	 *  
	 * 
	 * @campo FLG_EJECUTANDO_ACTUALMENTE
	*/
	public void setFlgEjecutandoActualmente(String flgEjecutandoActualmente) {
		this.flgEjecutandoActualmente = flgEjecutandoActualmente;
	}
	/**
	 *  
	 * 
	 * @campo DIA_CADA_N_DIAS
	*/
	public Integer getDiaCadaNDias() {
		return diaCadaNDias;
	}

	/**
	 *  
	 * 
	 * @campo DIA_CADA_N_DIAS
	*/
	public void setDiaCadaNDias(Integer diaCadaNDias) {
		this.diaCadaNDias = diaCadaNDias;
	}
	/**
	 *  
	 * 
	 * @campo DIA_ID_FRECUENCIA
	*/
	public String getDiaIdFrecuencia() {
		return diaIdFrecuencia;
	}

	/**
	 *  
	 * 
	 * @campo DIA_ID_FRECUENCIA
	*/
	public void setDiaIdFrecuencia(String diaIdFrecuencia) {
		this.diaIdFrecuencia = diaIdFrecuencia;
	}
	/**
	 *  
	 * 
	 * @campo DIA_FREC_UNICA_HORAEJECUCION
	*/
	public java.util.Date getDiaFrecUnicaHoraejecucion() {
		return diaFrecUnicaHoraejecucion;
	}

	/**
	 *  
	 * 
	 * @campo DIA_FREC_UNICA_HORAEJECUCION
	*/
	public void setDiaFrecUnicaHoraejecucion(java.util.Date diaFrecUnicaHoraejecucion) {
		this.diaFrecUnicaHoraejecucion = diaFrecUnicaHoraejecucion;
	}
	/**
	 *  
	 * 
	 * @campo DIA_FREC_RANGO_CADAN
	*/
	public Integer getDiaFrecRangoCadan() {
		return diaFrecRangoCadan;
	}

	/**
	 *  
	 * 
	 * @campo DIA_FREC_RANGO_CADAN
	*/
	public void setDiaFrecRangoCadan(Integer diaFrecRangoCadan) {
		this.diaFrecRangoCadan = diaFrecRangoCadan;
	}
	/**
	 *  
	 * 
	 * @campo DIA_FREC_RANGO_ID_FRECU_TIEMPO
	*/
	public String getDiaFrecRangoIdFrecuTiempo() {
		return diaFrecRangoIdFrecuTiempo;
	}

	/**
	 *  
	 * 
	 * @campo DIA_FREC_RANGO_ID_FRECU_TIEMPO
	*/
	public void setDiaFrecRangoIdFrecuTiempo(String diaFrecRangoIdFrecuTiempo) {
		this.diaFrecRangoIdFrecuTiempo = diaFrecRangoIdFrecuTiempo;
	}
	/**
	 *  
	 * 
	 * @campo DIA_FREC_RANGO_HORAINICIO
	*/
	public java.util.Date getDiaFrecRangoHorainicio() {
		return diaFrecRangoHorainicio;
	}

	/**
	 *  
	 * 
	 * @campo DIA_FREC_RANGO_HORAINICIO
	*/
	public void setDiaFrecRangoHorainicio(java.util.Date diaFrecRangoHorainicio) {
		this.diaFrecRangoHorainicio = diaFrecRangoHorainicio;
	}
	/**
	 *  
	 * 
	 * @campo DIA_FREC_RANGO_HORAFIN
	*/
	public java.util.Date getDiaFrecRangoHorafin() {
		return diaFrecRangoHorafin;
	}

	/**
	 *  
	 * 
	 * @campo DIA_FREC_RANGO_HORAFIN
	*/
	public void setDiaFrecRangoHorafin(java.util.Date diaFrecRangoHorafin) {
		this.diaFrecRangoHorafin = diaFrecRangoHorafin;
	}
	/**
	 *  
	 * 
	 * @campo DIA_FECHA_INICIO
	*/
	public java.util.Date getDiaFechaInicio() {
		return diaFechaInicio;
	}

	/**
	 *  
	 * 
	 * @campo DIA_FECHA_INICIO
	*/
	public void setDiaFechaInicio(java.util.Date diaFechaInicio) {
		this.diaFechaInicio = diaFechaInicio;
	}
	/**
	 *  
	 * 
	 * @campo DIA_FLG_TIENE_FIN
	*/
	public String getDiaFlgTieneFin() {
		return diaFlgTieneFin;
	}

	/**
	 *  
	 * 
	 * @campo DIA_FLG_TIENE_FIN
	*/
	public void setDiaFlgTieneFin(String diaFlgTieneFin) {
		this.diaFlgTieneFin = diaFlgTieneFin;
	}
	/**
	 *  
	 * 
	 * @campo DIA_FECHA_FIN
	*/
	public java.util.Date getDiaFechaFin() {
		return diaFechaFin;
	}

	/**
	 *  
	 * 
	 * @campo DIA_FECHA_FIN
	*/
	public void setDiaFechaFin(java.util.Date diaFechaFin) {
		this.diaFechaFin = diaFechaFin;
	}
	/**
	 *  
	 * 
	 * @campo SEM_CADA_N_SEMANAS
	*/
	public Integer getSemCadaNSemanas() {
		return semCadaNSemanas;
	}

	/**
	 *  
	 * 
	 * @campo SEM_CADA_N_SEMANAS
	*/
	public void setSemCadaNSemanas(Integer semCadaNSemanas) {
		this.semCadaNSemanas = semCadaNSemanas;
	}
	/**
	 *  
	 * 
	 * @campo SEM_FLG_EJECUTAR_LUNES
	*/
	public String getSemFlgEjecutarLunes() {
		return semFlgEjecutarLunes;
	}

	/**
	 *  
	 * 
	 * @campo SEM_FLG_EJECUTAR_LUNES
	*/
	public void setSemFlgEjecutarLunes(String semFlgEjecutarLunes) {
		this.semFlgEjecutarLunes = semFlgEjecutarLunes;
	}
	/**
	 *  
	 * 
	 * @campo SEM_FLG_EJECUTAR_MARTES
	*/
	public String getSemFlgEjecutarMartes() {
		return semFlgEjecutarMartes;
	}

	/**
	 *  
	 * 
	 * @campo SEM_FLG_EJECUTAR_MARTES
	*/
	public void setSemFlgEjecutarMartes(String semFlgEjecutarMartes) {
		this.semFlgEjecutarMartes = semFlgEjecutarMartes;
	}
	/**
	 *  
	 * 
	 * @campo SEM_FLG_EJECUTAR_MIERCOLES
	*/
	public String getSemFlgEjecutarMiercoles() {
		return semFlgEjecutarMiercoles;
	}

	/**
	 *  
	 * 
	 * @campo SEM_FLG_EJECUTAR_MIERCOLES
	*/
	public void setSemFlgEjecutarMiercoles(String semFlgEjecutarMiercoles) {
		this.semFlgEjecutarMiercoles = semFlgEjecutarMiercoles;
	}
	/**
	 *  
	 * 
	 * @campo SEM_FLG_EJECUTAR_JUEVES
	*/
	public String getSemFlgEjecutarJueves() {
		return semFlgEjecutarJueves;
	}

	/**
	 *  
	 * 
	 * @campo SEM_FLG_EJECUTAR_JUEVES
	*/
	public void setSemFlgEjecutarJueves(String semFlgEjecutarJueves) {
		this.semFlgEjecutarJueves = semFlgEjecutarJueves;
	}
	/**
	 *  
	 * 
	 * @campo SEM_FLG_EJECUTAR_VIERNES
	*/
	public String getSemFlgEjecutarViernes() {
		return semFlgEjecutarViernes;
	}

	/**
	 *  
	 * 
	 * @campo SEM_FLG_EJECUTAR_VIERNES
	*/
	public void setSemFlgEjecutarViernes(String semFlgEjecutarViernes) {
		this.semFlgEjecutarViernes = semFlgEjecutarViernes;
	}
	/**
	 *  
	 * 
	 * @campo SEM_FLG_EJECUTAR_SABADO
	*/
	public String getSemFlgEjecutarSabado() {
		return semFlgEjecutarSabado;
	}

	/**
	 *  
	 * 
	 * @campo SEM_FLG_EJECUTAR_SABADO
	*/
	public void setSemFlgEjecutarSabado(String semFlgEjecutarSabado) {
		this.semFlgEjecutarSabado = semFlgEjecutarSabado;
	}
	/**
	 *  
	 * 
	 * @campo SEM_FLG_EJECUTAR_DOMINGO
	*/
	public String getSemFlgEjecutarDomingo() {
		return semFlgEjecutarDomingo;
	}

	/**
	 *  
	 * 
	 * @campo SEM_FLG_EJECUTAR_DOMINGO
	*/
	public void setSemFlgEjecutarDomingo(String semFlgEjecutarDomingo) {
		this.semFlgEjecutarDomingo = semFlgEjecutarDomingo;
	}
	/**
	 *  
	 * 
	 * @campo SEM_ID_FRECUENCIA
	*/
	public String getSemIdFrecuencia() {
		return semIdFrecuencia;
	}

	/**
	 *  
	 * 
	 * @campo SEM_ID_FRECUENCIA
	*/
	public void setSemIdFrecuencia(String semIdFrecuencia) {
		this.semIdFrecuencia = semIdFrecuencia;
	}
	/**
	 *  
	 * 
	 * @campo SEM_FREC_UNICA_HORAEJECUCION
	*/
	public java.util.Date getSemFrecUnicaHoraejecucion() {
		return semFrecUnicaHoraejecucion;
	}

	/**
	 *  
	 * 
	 * @campo SEM_FREC_UNICA_HORAEJECUCION
	*/
	public void setSemFrecUnicaHoraejecucion(java.util.Date semFrecUnicaHoraejecucion) {
		this.semFrecUnicaHoraejecucion = semFrecUnicaHoraejecucion;
	}
	/**
	 *  
	 * 
	 * @campo SEM_FREC_RANGO_CADAN
	*/
	public Integer getSemFrecRangoCadan() {
		return semFrecRangoCadan;
	}

	/**
	 *  
	 * 
	 * @campo SEM_FREC_RANGO_CADAN
	*/
	public void setSemFrecRangoCadan(Integer semFrecRangoCadan) {
		this.semFrecRangoCadan = semFrecRangoCadan;
	}
	/**
	 *  
	 * 
	 * @campo SEM_FREC_RANGO_ID_FRECU_TIEMPO
	*/
	public String getSemFrecRangoIdFrecuTiempo() {
		return semFrecRangoIdFrecuTiempo;
	}

	/**
	 *  
	 * 
	 * @campo SEM_FREC_RANGO_ID_FRECU_TIEMPO
	*/
	public void setSemFrecRangoIdFrecuTiempo(String semFrecRangoIdFrecuTiempo) {
		this.semFrecRangoIdFrecuTiempo = semFrecRangoIdFrecuTiempo;
	}
	/**
	 *  
	 * 
	 * @campo SEM_FREC_RANGO_HORAINICIO
	*/
	public java.util.Date getSemFrecRangoHorainicio() {
		return semFrecRangoHorainicio;
	}

	/**
	 *  
	 * 
	 * @campo SEM_FREC_RANGO_HORAINICIO
	*/
	public void setSemFrecRangoHorainicio(java.util.Date semFrecRangoHorainicio) {
		this.semFrecRangoHorainicio = semFrecRangoHorainicio;
	}
	/**
	 *  
	 * 
	 * @campo SEM_FREC_RANGO_HORAFIN
	*/
	public java.util.Date getSemFrecRangoHorafin() {
		return semFrecRangoHorafin;
	}

	/**
	 *  
	 * 
	 * @campo SEM_FREC_RANGO_HORAFIN
	*/
	public void setSemFrecRangoHorafin(java.util.Date semFrecRangoHorafin) {
		this.semFrecRangoHorafin = semFrecRangoHorafin;
	}
	/**
	 *  
	 * 
	 * @campo SEM_FECHA_INICIO
	*/
	public java.util.Date getSemFechaInicio() {
		return semFechaInicio;
	}

	/**
	 *  
	 * 
	 * @campo SEM_FECHA_INICIO
	*/
	public void setSemFechaInicio(java.util.Date semFechaInicio) {
		this.semFechaInicio = semFechaInicio;
	}
	/**
	 *  
	 * 
	 * @campo SEM_FLG_TIENE_FIN
	*/
	public String getSemFlgTieneFin() {
		return semFlgTieneFin;
	}

	/**
	 *  
	 * 
	 * @campo SEM_FLG_TIENE_FIN
	*/
	public void setSemFlgTieneFin(String semFlgTieneFin) {
		this.semFlgTieneFin = semFlgTieneFin;
	}
	/**
	 *  
	 * 
	 * @campo SEM_FECHA_FIN
	*/
	public java.util.Date getSemFechaFin() {
		return semFechaFin;
	}

	/**
	 *  
	 * 
	 * @campo SEM_FECHA_FIN
	*/
	public void setSemFechaFin(java.util.Date semFechaFin) {
		this.semFechaFin = semFechaFin;
	}
	/**
	 *  
	 * 
	 * @campo MES_ID_FRECUENCIA_MENSUAL
	*/
	public String getMesIdFrecuenciaMensual() {
		return mesIdFrecuenciaMensual;
	}

	/**
	 *  
	 * 
	 * @campo MES_ID_FRECUENCIA_MENSUAL
	*/
	public void setMesIdFrecuenciaMensual(String mesIdFrecuenciaMensual) {
		this.mesIdFrecuenciaMensual = mesIdFrecuenciaMensual;
	}
	/**
	 *  
	 * 
	 * @campo MES_FREC_DIA_DIA
	*/
	public Integer getMesFrecDiaDia() {
		return mesFrecDiaDia;
	}

	/**
	 *  
	 * 
	 * @campo MES_FREC_DIA_DIA
	*/
	public void setMesFrecDiaDia(Integer mesFrecDiaDia) {
		this.mesFrecDiaDia = mesFrecDiaDia;
	}
	/**
	 *  
	 * 
	 * @campo MES_FREC_DIA_CADAN
	*/
	public Integer getMesFrecDiaCadan() {
		return mesFrecDiaCadan;
	}

	/**
	 *  
	 * 
	 * @campo MES_FREC_DIA_CADAN
	*/
	public void setMesFrecDiaCadan(Integer mesFrecDiaCadan) {
		this.mesFrecDiaCadan = mesFrecDiaCadan;
	}
	/**
	 *  
	 * 
	 * @campo MES_FREC_NOM_DIA_ID_ORDEN_DIA
	*/
	public String getMesFrecNomDiaIdOrdenDia() {
		return mesFrecNomDiaIdOrdenDia;
	}

	/**
	 *  
	 * 
	 * @campo MES_FREC_NOM_DIA_ID_ORDEN_DIA
	*/
	public void setMesFrecNomDiaIdOrdenDia(String mesFrecNomDiaIdOrdenDia) {
		this.mesFrecNomDiaIdOrdenDia = mesFrecNomDiaIdOrdenDia;
	}
	/**
	 *  
	 * 
	 * @campo MES_FREC_NOM_DIA_ID_DIA_SEMANA
	*/
	public String getMesFrecNomDiaIdDiaSemana() {
		return mesFrecNomDiaIdDiaSemana;
	}

	/**
	 *  
	 * 
	 * @campo MES_FREC_NOM_DIA_ID_DIA_SEMANA
	*/
	public void setMesFrecNomDiaIdDiaSemana(String mesFrecNomDiaIdDiaSemana) {
		this.mesFrecNomDiaIdDiaSemana = mesFrecNomDiaIdDiaSemana;
	}
	/**
	 *  
	 * 
	 * @campo MES_FREC_NOM_DIA_CADAN
	*/
	public Integer getMesFrecNomDiaCadan() {
		return mesFrecNomDiaCadan;
	}

	/**
	 *  
	 * 
	 * @campo MES_FREC_NOM_DIA_CADAN
	*/
	public void setMesFrecNomDiaCadan(Integer mesFrecNomDiaCadan) {
		this.mesFrecNomDiaCadan = mesFrecNomDiaCadan;
	}
	/**
	 *  
	 * 
	 * @campo MES_ID_FRECUENCIA
	*/
	public String getMesIdFrecuencia() {
		return mesIdFrecuencia;
	}

	/**
	 *  
	 * 
	 * @campo MES_ID_FRECUENCIA
	*/
	public void setMesIdFrecuencia(String mesIdFrecuencia) {
		this.mesIdFrecuencia = mesIdFrecuencia;
	}
	/**
	 *  
	 * 
	 * @campo MES_FREC_UNICA_HORAEJECUCION
	*/
	public java.util.Date getMesFrecUnicaHoraejecucion() {
		return mesFrecUnicaHoraejecucion;
	}

	/**
	 *  
	 * 
	 * @campo MES_FREC_UNICA_HORAEJECUCION
	*/
	public void setMesFrecUnicaHoraejecucion(java.util.Date mesFrecUnicaHoraejecucion) {
		this.mesFrecUnicaHoraejecucion = mesFrecUnicaHoraejecucion;
	}
	/**
	 *  
	 * 
	 * @campo MES_FREC_RANGO_CADAN
	*/
	public Integer getMesFrecRangoCadan() {
		return mesFrecRangoCadan;
	}

	/**
	 *  
	 * 
	 * @campo MES_FREC_RANGO_CADAN
	*/
	public void setMesFrecRangoCadan(Integer mesFrecRangoCadan) {
		this.mesFrecRangoCadan = mesFrecRangoCadan;
	}
	/**
	 *  
	 * 
	 * @campo MES_FREC_RANGO_ID_FRECU_TIEMPO
	*/
	public String getMesFrecRangoIdFrecuTiempo() {
		return mesFrecRangoIdFrecuTiempo;
	}

	/**
	 *  
	 * 
	 * @campo MES_FREC_RANGO_ID_FRECU_TIEMPO
	*/
	public void setMesFrecRangoIdFrecuTiempo(String mesFrecRangoIdFrecuTiempo) {
		this.mesFrecRangoIdFrecuTiempo = mesFrecRangoIdFrecuTiempo;
	}
	/**
	 *  
	 * 
	 * @campo MES_FREC_RANGO_HORAINICIO
	*/
	public java.util.Date getMesFrecRangoHorainicio() {
		return mesFrecRangoHorainicio;
	}

	/**
	 *  
	 * 
	 * @campo MES_FREC_RANGO_HORAINICIO
	*/
	public void setMesFrecRangoHorainicio(java.util.Date mesFrecRangoHorainicio) {
		this.mesFrecRangoHorainicio = mesFrecRangoHorainicio;
	}
	/**
	 *  
	 * 
	 * @campo MES_FREC_RANGO_HORAFIN
	*/
	public java.util.Date getMesFrecRangoHorafin() {
		return mesFrecRangoHorafin;
	}

	/**
	 *  
	 * 
	 * @campo MES_FREC_RANGO_HORAFIN
	*/
	public void setMesFrecRangoHorafin(java.util.Date mesFrecRangoHorafin) {
		this.mesFrecRangoHorafin = mesFrecRangoHorafin;
	}
	/**
	 *  
	 * 
	 * @campo MES_FECHA_INICIO
	*/
	public java.util.Date getMesFechaInicio() {
		return mesFechaInicio;
	}

	/**
	 *  
	 * 
	 * @campo MES_FECHA_INICIO
	*/
	public void setMesFechaInicio(java.util.Date mesFechaInicio) {
		this.mesFechaInicio = mesFechaInicio;
	}
	/**
	 *  
	 * 
	 * @campo MES_FLG_TIENE_FIN
	*/
	public String getMesFlgTieneFin() {
		return mesFlgTieneFin;
	}

	/**
	 *  
	 * 
	 * @campo MES_FLG_TIENE_FIN
	*/
	public void setMesFlgTieneFin(String mesFlgTieneFin) {
		this.mesFlgTieneFin = mesFlgTieneFin;
	}
	/**
	 *  
	 * 
	 * @campo MES_FECHA_FIN
	*/
	public java.util.Date getMesFechaFin() {
		return mesFechaFin;
	}

	/**
	 *  
	 * 
	 * @campo MES_FECHA_FIN
	*/
	public void setMesFechaFin(java.util.Date mesFechaFin) {
		this.mesFechaFin = mesFechaFin;
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
	 * @campo FECHA_EJECUTANDO_ACTUALMENTE
	*/
	public java.util.Date getFechaEjecutandoActualmente() {
		return fechaEjecutandoActualmente;
	}

	/**
	 *  
	 * 
	 * @campo FECHA_EJECUTANDO_ACTUALMENTE
	*/
	public void setFechaEjecutandoActualmente(java.util.Date fechaEjecutandoActualmente) {
		this.fechaEjecutandoActualmente = fechaEjecutandoActualmente;
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
