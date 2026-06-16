package net.royal.spring.alertas.dominio;

import jakarta.persistence.Column;
import jakarta.validation.constraints.NotNull;

/**
 * 
 * 
 * @tabla SGALERTASSYS.ALERTA_ADICIONAL
*/
public class AlertaAdicionalPk implements java.io.Serializable{


	private static final long serialVersionUID = 1L;
	@NotNull
	@Column(name = "ID_ALERTA", nullable = false)
	private Integer idAlerta;

	@NotNull
	@Column(name = "ID_ADICIONAL", nullable = false)
	private Integer idAdicional;


	public AlertaAdicionalPk() {
	}

	public AlertaAdicionalPk(Integer pidAlerta,Integer pidAdicional) {
this.idAlerta = pidAlerta;
		this.idAdicional = pidAdicional;
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
	 * @campo ID_ADICIONAL
	*/
	public Integer getIdAdicional() {
		return idAdicional;
	}

	/**
	 *  
	 * 
	 * @campo ID_ADICIONAL
	*/
	public void setIdAdicional(Integer idAdicional) {
		this.idAdicional = idAdicional;
	}

}
