package net.royal.spring.alertas.dominio;

import jakarta.persistence.Column;
import jakarta.validation.constraints.NotNull;

/**
 * 
 * 
 * @tabla SGALERTASSYS.ALERTA_PLAN
*/
public class AlertaPlanPk implements java.io.Serializable{


	private static final long serialVersionUID = 1L;
	@NotNull
	@Column(name = "ID_ALERTA", nullable = false)
	private Integer idAlerta;

	@NotNull
	@Column(name = "ID_PLAN", nullable = false)
	private Integer idPlan;


	public AlertaPlanPk() {
	}

	public AlertaPlanPk(Integer pidAlerta,Integer pidPlan) {
this.idAlerta = pidAlerta;
		this.idPlan = pidPlan;
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
