package net.royal.spring.alertas.dominio;

import jakarta.persistence.Column;
import jakarta.validation.constraints.NotNull;

/**
 * 
 * 
 * @tabla SGALERTASSYS.REGLA_NEGOCIO_PLAN
*/
public class ReglaNegocioPlanPk implements java.io.Serializable{


	private static final long serialVersionUID = 1L;
	@NotNull
	@Column(name = "ID_REGLA_NEGOCIO", nullable = false)
	private Integer idReglaNegocio;

	@NotNull
	@Column(name = "ID_PLAN", nullable = false)
	private Integer idPlan;


	public ReglaNegocioPlanPk() {
	}

	public ReglaNegocioPlanPk(Integer pidReglaNegocio,Integer pidPlan) {
this.idReglaNegocio = pidReglaNegocio;
		this.idPlan = pidPlan;
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
	 * @campo ID_PLAN
	*/
	public Integer getIdPlan() {
		return idPlan;
	}

	/**
	 *  
	 * 
	 * @campo ID_PLAN
	*/
	public void setIdPlan(Integer idPlan) {
		this.idPlan = idPlan;
	}

}
