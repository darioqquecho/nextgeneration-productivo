package net.royal.spring.alertas.dominio;

import jakarta.persistence.Column;
import jakarta.validation.constraints.NotNull;

/**
 * 
 * 
 * @tabla SGALERTASSYS.ALERTA_DETALLE
*/
public class AlertaDetallePk implements java.io.Serializable{


	private static final long serialVersionUID = 1L;
	@NotNull
	@Column(name = "ID_ALERTA", nullable = false)
	private Integer idAlerta;

	@NotNull
	@Column(name = "ID_DETALLE", nullable = false)
	private Integer idDetalle;


	public AlertaDetallePk() {
	}

	public AlertaDetallePk(Integer pidAlerta,Integer pidDetalle) {
this.idAlerta = pidAlerta;
		this.idDetalle = pidDetalle;
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
