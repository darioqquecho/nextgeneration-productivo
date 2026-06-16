package net.royal.spring.alertas.dominio;

import jakarta.persistence.Column;
import jakarta.validation.constraints.NotNull;

/**
 * 
 * 
 * @tabla SGALERTASSYS.REGLA_NEGOCIO_DETALLE
*/
public class ReglaNegocioDetallePk implements java.io.Serializable{


	private static final long serialVersionUID = 1L;
	@NotNull
	@Column(name = "ID_REGLA_NEGOCIO", nullable = false)
	private Integer idReglaNegocio;

	@NotNull
	@Column(name = "ID_DETALLE", nullable = false)
	private Integer idDetalle;


	public ReglaNegocioDetallePk() {
	}

	public ReglaNegocioDetallePk(Integer pidReglaNegocio,Integer pidDetalle) {
this.idReglaNegocio = pidReglaNegocio;
		this.idDetalle = pidDetalle;
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
	 * @campo ID_DETALLE
	*/
	public Integer getIdDetalle() {
		return idDetalle;
	}

	/**
	 *  
	 * 
	 * @campo ID_DETALLE
	*/
	public void setIdDetalle(Integer idDetalle) {
		this.idDetalle = idDetalle;
	}

}
