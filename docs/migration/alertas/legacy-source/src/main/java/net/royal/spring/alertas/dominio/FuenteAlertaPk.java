package net.royal.spring.alertas.dominio;

import jakarta.persistence.Column;
import jakarta.validation.constraints.NotNull;

/**
 * 
 * 
 * @tabla SGALERTASSYS.FUENTE_ALERTA
*/
public class FuenteAlertaPk implements java.io.Serializable{


	private static final long serialVersionUID = 1L;
	@NotNull
	@Column(name = "ID_FUENTE_ALERTA", nullable = false)
	private Integer idFuenteAlerta;


	public FuenteAlertaPk() {
	}

	public FuenteAlertaPk(Integer pidFuenteAlerta) {
this.idFuenteAlerta = pidFuenteAlerta;
	}

	/**
	 *  
	 * 
	 * @campo ID_FUENTE_ALERTA
	*/
	public Integer getIdFuenteAlerta() {
		return idFuenteAlerta;
	}

	/**
	 *  
	 * 
	 * @campo ID_FUENTE_ALERTA
	*/
	public void setIdFuenteAlerta(Integer idFuenteAlerta) {
		this.idFuenteAlerta = idFuenteAlerta;
	}

}
