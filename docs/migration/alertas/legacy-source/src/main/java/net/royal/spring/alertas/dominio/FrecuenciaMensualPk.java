package net.royal.spring.alertas.dominio;

import jakarta.persistence.Column;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

/**
 * 
 * 
 * @tabla SGALERTASSYS.FRECUENCIA_MENSUAL
*/
public class FrecuenciaMensualPk implements java.io.Serializable{


	private static final long serialVersionUID = 1L;
	@Size(min = 0, max = 3)
	@NotNull
	@NotEmpty
	@Column(name = "ID_FRECUENCIA_MENSUAL", length = 3, nullable = false)
	private String idFrecuenciaMensual;


	public FrecuenciaMensualPk() {
	}

	public FrecuenciaMensualPk(String pidFrecuenciaMensual) {
this.idFrecuenciaMensual = pidFrecuenciaMensual;
	}

	/**
	 *  
	 * 
	 * @campo ID_FRECUENCIA_MENSUAL
	*/
	public String getIdFrecuenciaMensual() {
		return idFrecuenciaMensual;
	}

	/**
	 *  
	 * 
	 * @campo ID_FRECUENCIA_MENSUAL
	*/
	public void setIdFrecuenciaMensual(String idFrecuenciaMensual) {
		this.idFrecuenciaMensual = idFrecuenciaMensual;
	}

}
