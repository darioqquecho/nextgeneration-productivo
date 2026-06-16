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

import net.royal.spring.alertas.dominio.dto.ColorMast;
import net.royal.spring.alertas.dominio.dto.ReglaNegocioAdicionalDto;
import net.royal.spring.alertas.dominio.dto.ReglaNegocioDestinoDto;
import net.royal.spring.alertas.dominio.dto.ReglaNegocioDetalleDto;
import net.royal.spring.alertas.dominio.dto.ReglaNegocioPlanDto;
import net.royal.spring.alertas.dominio.dto.ReglaNegocioProgramacionDto;
import net.royal.spring.framework.web.rest.UDeserializers;
import net.royal.spring.framework.web.rest.USerializers;

/**
 * 
 * 
 * @tabla SGALERTASSYS.REGLA_NEGOCIO
*/
@Entity

@Table(name = "REGLA_NEGOCIO",schema="SGALERTASSYS")
public class ReglaNegocio implements java.io.Serializable{

	private static final long serialVersionUID = 1L;
	@EmbeddedId
	private ReglaNegocioPk pk;

	@Size(min = 0, max = 200)
	@Column(name = "NOMBRE", length = 200, nullable = true)
	private String nombre;

	@Size(min = 0, max = 500)
	@Column(name = "DESCRIPCION", length = 500, nullable = true)
	private String descripcion;

	@Size(min = 0, max = 4)
	@Column(name = "ID_TIPO_OBJECT", length = 4, nullable = true)
	private String idTipoObject;
	
	@Size(min = 0, max = 500)
	@Column(name = "OBJECT_BD", length = 500, nullable = true)
	private String objectBd;

	@Size(min = 0, max = 1)
	@Column(name = "ID_TIPO_EJECUCION", length = 1, nullable = true)
	private String idTipoEjecucion;

	@Size(min = 0, max = 1)
	@Column(name = "ID_TIPO_ENVIO", length = 1, nullable = true)
	private String idTipoEnvio;

	@Size(min = 0, max = 8)
	@Column(name = "ID_COMPANIA", length = 8, nullable = true)
	private String idCompania;

	@Column(name = "ID_AREA_NEGOCIO", nullable = true)
	private Integer idAreaNegocio;

	@Size(min = 0, max = 3)
	@Column(name = "ID_COLOR", length = 3, nullable = true)
	private String idColor;
	
	@Column(name = "ID_CONEXION_BD", nullable = true)
	private Integer idConexionBd;

	@JsonSerialize(using = USerializers.DateSerializer.class)
	@JsonDeserialize(using = UDeserializers.DateDeserializer.class)
	@Column(name = "FECHA_ULTIMA_EJECUCION", nullable = true)
	private java.util.Date fechaUltimaEjecucion;

	@JsonSerialize(using = USerializers.DateSerializer.class)
	@JsonDeserialize(using = UDeserializers.DateDeserializer.class)
	@Column(name = "FECHA_ULTIMO_ENVIO", nullable = true)
	private java.util.Date fechaUltimoEnvio;

	@Size(min = 0, max = 1)
	@Column(name = "FLG_OBTENER_RESPONSABLE", length = 1, nullable = true)
	private String flgObtenerResponsable;

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

	@Size(min = 0, max = 4000)
	@Column(name = "CORREO_CONTENIDO", length = 4000, nullable = true)
	private String correoContenido;

	@Size(min = 0, max = 4000)
	@Column(name = "CORREO_PIE", length = 4000, nullable = true)
	private String correoPie;

	@Size(min = 0, max = 1)
	@Column(name = "FLG_CORREO_PRUEBA", length = 1, nullable = true)
	private String flgCorreoPrueba;

	@Size(min = 0, max = 1)
	@Column(name = "FLG_MOSTRAR_FECHAHORA_ENVIO", length = 1, nullable = true)
	private String flgMostrarFechahoraEnvio;

	@Size(min = 0, max = 1)
	@Column(name = "FLG_MOSTRAR_FECHAHORA_SEGUI", length = 1, nullable = true)
	private String flgMostrarFechahoraSegui;

	@Size(min = 0, max = 4000)
	@Column(name = "CORREO_HOJA_ESTILO", length = 4000, nullable = true)
	private String correoHojaEstilo;

	@Column(name = "CANTIDAD_CORREOS_ENVIAR", nullable = true)
	private Integer cantidadCorreosEnviar;

	@Column(name = "CANTIDAD_ERRORES_ENVIO", nullable = true)
	private Integer cantidadErroresEnvio;

	/**
	 * Indica si en estos momentos se esta ejecutando la creacion de la Alerta, siemrpe deberia estar en N,
	 * si es S, quiere decir que es una alerta con muchos datos.
	 */
	@Size(min = 0, max = 1)
	@Column(name = "FLG_LOG_GENERAR_ALERTA", length = 1, nullable = true)
	private String flgLogGenerarAlerta;

	@Size(min = 0, max = 100)
	@Column(name = "CORREO_TIPO_CONFIGURACION", length = 100, nullable = true)
	private String correoTipoConfiguracion;
	
	@Transient
	private Boolean auxFlgPreparado=Boolean.FALSE;
	
	@Transient
	private ColorMast colorMast;
	
	@Transient
	private String nombrecolor;
	
	@Transient
	private List<ReglaNegocioAdicionalDto> listaReglaNegocioAdicional;
	
	@Transient
	private List<ReglaNegocioDetalleDto> listaReglaNegocioDetalle;
	
	@Transient
	private List<ReglaNegocioPlanDto> listaReglaNegocioPlan;

	@Transient
	private List<ReglaNegocioDestinoDto> listaReglaNegocioDestino;
	
	@Transient
	private List<ReglaNegocioProgramacionDto> listaReglaNegocioProgramacion;
	
	@Transient
	private List<ReglaNegocioProgramacionDto> listaReglaNegocioProgramacionEnvio;
	
	@Transient
	private String flgalterado;
	
	 
	
	public String getFlgalterado() {
		return flgalterado;
	}

	public void setFlgalterado(String flgalterado) {
		this.flgalterado = flgalterado;
	}

	public String getNombrecolor() {
		return nombrecolor;
	}

	public void setNombrecolor(String nombrecolor) {
		this.nombrecolor = nombrecolor;
	}
	public List<ReglaNegocioProgramacionDto> getListaReglaNegocioProgramacionEnvio() {
		return listaReglaNegocioProgramacionEnvio;
	}

	public void setListaReglaNegocioProgramacionEnvio(
			List<ReglaNegocioProgramacionDto> listaReglaNegocioProgramacionEnvio) {
		this.listaReglaNegocioProgramacionEnvio = listaReglaNegocioProgramacionEnvio;
	}

	public List<ReglaNegocioProgramacionDto> getListaReglaNegocioProgramacion() {
		return listaReglaNegocioProgramacion;
	}

	public void setListaReglaNegocioProgramacion(List<ReglaNegocioProgramacionDto> listaReglaNegocioProgramacion) {
		this.listaReglaNegocioProgramacion = listaReglaNegocioProgramacion;
	}

	public List<ReglaNegocioDestinoDto> getListaReglaNegocioDestino() {
		return listaReglaNegocioDestino;
	}

	public void setListaReglaNegocioDestino(List<ReglaNegocioDestinoDto> listaReglaNegocioDestino) {
		this.listaReglaNegocioDestino = listaReglaNegocioDestino;
	}

	public List<ReglaNegocioDetalleDto> getListaReglaNegocioDetalle() {
		return listaReglaNegocioDetalle;
	}

	public void setListaReglaNegocioDetalle(List<ReglaNegocioDetalleDto> listaReglaNegocioDetalle) {
		this.listaReglaNegocioDetalle = listaReglaNegocioDetalle;
	}

	public List<ReglaNegocioPlanDto> getListaReglaNegocioPlan() {
		return listaReglaNegocioPlan;
	}

	public void setListaReglaNegocioPlan(List<ReglaNegocioPlanDto> listaReglaNegocioPlan) {
		this.listaReglaNegocioPlan = listaReglaNegocioPlan;
	}


	public List<ReglaNegocioAdicionalDto> getListaReglaNegocioAdicional() {
		return listaReglaNegocioAdicional;
	}

	public void setListaReglaNegocioAdicional(List<ReglaNegocioAdicionalDto> listaReglaNegocioAdicional) {
		this.listaReglaNegocioAdicional = listaReglaNegocioAdicional;
	}


	public ColorMast getColorMast() {
		return colorMast;
	}


	public void setColorMast(ColorMast colorMast) {
		this.colorMast = colorMast;
	}


	public ReglaNegocio() {
		flgalterado = "NO";
		pk = new ReglaNegocioPk();
	}


	public ReglaNegocio(ReglaNegocioPk pk) {
		this.pk = pk;
	}

	public ReglaNegocioPk getPk() {
		return pk;
	}

	public void setPk(ReglaNegocioPk pk) {
		this.pk = pk;
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
	 * @campo OBJECT_BD
	*/
	public String getObjectBd() {
		return objectBd;
	}

	/**
	 *  
	 * 
	 * @campo OBJECT_BD
	*/
	public void setObjectBd(String objectBd) {
		this.objectBd = objectBd;
	}
	/**
	 *  
	 * 
	 * @campo ID_TIPO_EJECUCION
	*/
	public String getIdTipoEjecucion() {
		return idTipoEjecucion;
	}

	/**
	 *  
	 * 
	 * @campo ID_TIPO_EJECUCION
	*/
	public void setIdTipoEjecucion(String idTipoEjecucion) {
		this.idTipoEjecucion = idTipoEjecucion;
	}
	/**
	 *  
	 * 
	 * @campo ID_TIPO_ENVIO
	*/
	public String getIdTipoEnvio() {
		return idTipoEnvio;
	}

	/**
	 *  
	 * 
	 * @campo ID_TIPO_ENVIO
	*/
	public void setIdTipoEnvio(String idTipoEnvio) {
		this.idTipoEnvio = idTipoEnvio;
	}
	/**
	 *  
	 * 
	 * @campo ID_COMPANIA
	*/
	public String getIdCompania() {
		return idCompania;
	}

	/**
	 *  
	 * 
	 * @campo ID_COMPANIA
	*/
	public void setIdCompania(String idCompania) {
		this.idCompania = idCompania;
	}
	/**
	 *  
	 * 
	 * @campo ID_AREA_NEGOCIO
	*/
	public Integer getIdAreaNegocio() {
		return idAreaNegocio;
	}

	/**
	 *  
	 * 
	 * @campo ID_AREA_NEGOCIO
	*/
	public void setIdAreaNegocio(Integer idAreaNegocio) {
		this.idAreaNegocio = idAreaNegocio;
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
	 * @campo ID_CONEXION_BD
	*/
	public Integer getIdConexionBd() {
		return idConexionBd;
	}

	/**
	 *  
	 * 
	 * @campo ID_CONEXION_BD
	*/
	public void setIdConexionBd(Integer idConexionBd) {
		this.idConexionBd = idConexionBd;
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
	 * @campo FECHA_ULTIMO_ENVIO
	*/
	public java.util.Date getFechaUltimoEnvio() {
		return fechaUltimoEnvio;
	}

	/**
	 *  
	 * 
	 * @campo FECHA_ULTIMO_ENVIO
	*/
	public void setFechaUltimoEnvio(java.util.Date fechaUltimoEnvio) {
		this.fechaUltimoEnvio = fechaUltimoEnvio;
	}
	/**
	 *  
	 * 
	 * @campo FLG_OBTENER_RESPONSABLE
	*/
	public String getFlgObtenerResponsable() {
		return flgObtenerResponsable;
	}

	/**
	 *  
	 * 
	 * @campo FLG_OBTENER_RESPONSABLE
	*/
	public void setFlgObtenerResponsable(String flgObtenerResponsable) {
		this.flgObtenerResponsable = flgObtenerResponsable;
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
	 * @campo CORREO_CONTENIDO
	*/
	public String getCorreoContenido() {
		return correoContenido;
	}

	/**
	 *  
	 * 
	 * @campo CORREO_CONTENIDO
	*/
	public void setCorreoContenido(String correoContenido) {
		this.correoContenido = correoContenido;
	}
	/**
	 *  
	 * 
	 * @campo CORREO_PIE
	*/
	public String getCorreoPie() {
		return correoPie;
	}

	/**
	 *  
	 * 
	 * @campo CORREO_PIE
	*/
	public void setCorreoPie(String correoPie) {
		this.correoPie = correoPie;
	}
	/**
	 *  
	 * 
	 * @campo FLG_CORREO_PRUEBA
	*/
	public String getFlgCorreoPrueba() {
		return flgCorreoPrueba;
	}

	/**
	 *  
	 * 
	 * @campo FLG_CORREO_PRUEBA
	*/
	public void setFlgCorreoPrueba(String flgCorreoPrueba) {
		this.flgCorreoPrueba = flgCorreoPrueba;
	}
	/**
	 *  
	 * 
	 * @campo FLG_MOSTRAR_FECHAHORA_ENVIO
	*/
	public String getFlgMostrarFechahoraEnvio() {
		return flgMostrarFechahoraEnvio;
	}

	/**
	 *  
	 * 
	 * @campo FLG_MOSTRAR_FECHAHORA_ENVIO
	*/
	public void setFlgMostrarFechahoraEnvio(String flgMostrarFechahoraEnvio) {
		this.flgMostrarFechahoraEnvio = flgMostrarFechahoraEnvio;
	}
	/**
	 *  
	 * 
	 * @campo FLG_MOSTRAR_FECHAHORA_SEGUI
	*/
	public String getFlgMostrarFechahoraSegui() {
		return flgMostrarFechahoraSegui;
	}

	/**
	 *  
	 * 
	 * @campo FLG_MOSTRAR_FECHAHORA_SEGUI
	*/
	public void setFlgMostrarFechahoraSegui(String flgMostrarFechahoraSegui) {
		this.flgMostrarFechahoraSegui = flgMostrarFechahoraSegui;
	}
	/**
	 *  
	 * 
	 * @campo CORREO_HOJA_ESTILO
	*/
	public String getCorreoHojaEstilo() {
		return correoHojaEstilo;
	}

	/**
	 *  
	 * 
	 * @campo CORREO_HOJA_ESTILO
	*/
	public void setCorreoHojaEstilo(String correoHojaEstilo) {
		this.correoHojaEstilo = correoHojaEstilo;
	}
	/**
	 *  
	 * 
	 * @campo CANTIDAD_CORREOS_ENVIAR
	*/
	public Integer getCantidadCorreosEnviar() {
		return cantidadCorreosEnviar;
	}

	/**
	 *  
	 * 
	 * @campo CANTIDAD_CORREOS_ENVIAR
	*/
	public void setCantidadCorreosEnviar(Integer cantidadCorreosEnviar) {
		this.cantidadCorreosEnviar = cantidadCorreosEnviar;
	}
	/**
	 *  
	 * 
	 * @campo CANTIDAD_ERRORES_ENVIO
	*/
	public Integer getCantidadErroresEnvio() {
		return cantidadErroresEnvio;
	}

	/**
	 *  
	 * 
	 * @campo CANTIDAD_ERRORES_ENVIO
	*/
	public void setCantidadErroresEnvio(Integer cantidadErroresEnvio) {
		this.cantidadErroresEnvio = cantidadErroresEnvio;
	}
	/**
	 *  
	 * Indica si en estos momentos se esta ejecutando la creacion de la Alerta, siemrpe deberia estar en N, si es S, quiere decir que es una alerta con muchos datos.
	 * @campo FLG_LOG_GENERAR_ALERTA
	*/
	public String getFlgLogGenerarAlerta() {
		return flgLogGenerarAlerta;
	}

	/**
	 *  Indica si en estos momentos se esta ejecutando la creacion de la Alerta, siemrpe deberia estar en N, si es S, quiere decir que es una alerta con muchos datos.
	 * 
	 * @campo FLG_LOG_GENERAR_ALERTA
	*/
	public void setFlgLogGenerarAlerta(String flgLogGenerarAlerta) {
		this.flgLogGenerarAlerta = flgLogGenerarAlerta;
	}
	/**
	 *  
	 * 
	 * @campo CORREO_TIPO_CONFIGURACION
	*/
	public String getCorreoTipoConfiguracion() {
		return correoTipoConfiguracion;
	}

	/**
	 *  
	 * 
	 * @campo CORREO_TIPO_CONFIGURACION
	*/
	public void setCorreoTipoConfiguracion(String correoTipoConfiguracion) {
		this.correoTipoConfiguracion = correoTipoConfiguracion;
	}
	public Boolean getAuxFlgPreparado() {
		if (auxFlgPreparado==null)
			return Boolean.FALSE;
		return auxFlgPreparado;
	}

	public void setAuxFlgPreparado(Boolean auxFlgPreparado) {
		this.auxFlgPreparado = auxFlgPreparado;
	}

	public String getIdTipoObject() {
		return idTipoObject;
	}

	public void setIdTipoObject(String idTipoObject) {
		this.idTipoObject = idTipoObject;
	}
}
