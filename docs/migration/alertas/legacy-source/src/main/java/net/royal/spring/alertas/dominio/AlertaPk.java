package net.royal.spring.alertas.dominio;

import jakarta.persistence.Column;
import jakarta.validation.constraints.NotNull;

/**
 * 
 * 
 * @tabla SGALERTASSYS.ALERTA
*/
public class AlertaPk implements java.io.Serializable{


	private static final long serialVersionUID = 1L;
	@NotNull
	@Column(name = "ID_ALERTA", nullable = false)
	private Integer idAlerta;


	public AlertaPk() {
	}

	public AlertaPk(Integer pidAlerta) {
this.idAlerta = pidAlerta;
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

}
