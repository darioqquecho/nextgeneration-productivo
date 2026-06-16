package net.royal.spring.alertas.dominio;

import jakarta.persistence.Column;
import jakarta.validation.constraints.NotNull;

/**
 * 
 * 
 * @tabla SGALERTASSYS.LOG_ALERTA
*/
public class LogAlertaPk implements java.io.Serializable{


	private static final long serialVersionUID = 1L;
	@NotNull
	@Column(name = "ID_LOG_ALERTA", nullable = false)
	private Integer idLogAlerta;


	public LogAlertaPk() {
	}

	public LogAlertaPk(Integer pidLogAlerta) {
this.idLogAlerta = pidLogAlerta;
	}

	/**
	 *  
	 * 
	 * @campo ID_LOG_ALERTA
	*/
	public Integer getIdLogAlerta() {
		return idLogAlerta;
	}

	/**
	 *  
	 * 
	 * @campo ID_LOG_ALERTA
	*/
	public void setIdLogAlerta(Integer idLogAlerta) {
		this.idLogAlerta = idLogAlerta;
	}

}
