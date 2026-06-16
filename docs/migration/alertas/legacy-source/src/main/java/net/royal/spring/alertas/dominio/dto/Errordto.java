package net.royal.spring.alertas.dominio.dto;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

public class Errordto {

	private BigDecimal idError;
	private String nombre;
	private BigDecimal  idReglaNegocio;
	private BigDecimal idCorreo;
	private BigDecimal idLogAlerta;
	private BigDecimal idAlerta;
	private String proceso;
	private String descripcionError;
	private String estado;
	private Date creacionFecha;
	private String className;
	private String objetoBasedatos;
	private String DominioMensajeUsuario;
	
	private List<Errordto> lstParaModificar;
	
	
 

	public List<Errordto> getLstParaModificar() {
		return lstParaModificar;
	}

	public void setLstParaModificar(List<Errordto> lstParaModificar) {
		this.lstParaModificar = lstParaModificar;
	}

	private BigDecimal	ROWNUM_ ;
	
	private Boolean flgInactivar = false;

	public Boolean getFlgInactivar() {
		return flgInactivar;
	}

	public void setFlgInactivar(Boolean flgInactivar) {
		this.flgInactivar = flgInactivar;
	}

	public BigDecimal getIdError() {
		return idError;
	}

	public void setIdError(BigDecimal idError) {
		this.idError = idError;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public BigDecimal getIdReglaNegocio() {
		return idReglaNegocio;
	}

	public void setIdReglaNegocio(BigDecimal idReglaNegocio) {
		this.idReglaNegocio = idReglaNegocio;
	}

	public BigDecimal getIdCorreo() {
		return idCorreo;
	}

	public void setIdCorreo(BigDecimal idCorreo) {
		this.idCorreo = idCorreo;
	}

	public BigDecimal getIdLogAlerta() {
		return idLogAlerta;
	}

	public void setIdLogAlerta(BigDecimal idLogAlerta) {
		this.idLogAlerta = idLogAlerta;
	}

	public BigDecimal getIdAlerta() {
		return idAlerta;
	}

	public void setIdAlerta(BigDecimal idAlerta) {
		this.idAlerta = idAlerta;
	}

	public String getProceso() {
		return proceso;
	}

	public void setProceso(String proceso) {
		this.proceso = proceso;
	}

	public String getDescripcionError() {
		return descripcionError;
	}

	public void setDescripcionError(String descripcionError) {
		this.descripcionError = descripcionError;
	}

	public String getEstado() {
		return estado;
	}

	public void setEstado(String estado) {
		this.estado = estado;
	}

	public Date getCreacionFecha() {
		return creacionFecha;
	}

	public void setCreacionFecha(Date creacionFecha) {
		this.creacionFecha = creacionFecha;
	}

	public String getClassName() {
		return className;
	}

	public void setClassName(String className) {
		this.className = className;
	}

	public String getObjetoBasedatos() {
		return objetoBasedatos;
	}

	public void setObjetoBasedatos(String objetoBasedatos) {
		this.objetoBasedatos = objetoBasedatos;
	}

	public String getDominioMensajeUsuario() {
		return DominioMensajeUsuario;
	}

	public void setDominioMensajeUsuario(String DominioMensajeUsuario) {
		this.DominioMensajeUsuario = DominioMensajeUsuario;
	}

	public BigDecimal getROWNUM_() {
		return ROWNUM_;
	}

	public void setROWNUM_(BigDecimal rOWNUM_) {
		ROWNUM_ = rOWNUM_;
	}

}
