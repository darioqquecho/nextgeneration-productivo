package net.royal.spring.alertas.dominio;

import jakarta.persistence.Column;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

/**
 * 
 * 
 * @tabla SGALERTASSYS.FRECUENCIA_TIEMPO
*/
public class FrecuenciaTiempoPk implements java.io.Serializable{


	private static final long serialVersionUID = 1L;
	@Size(min = 0, max = 3)
	@NotNull
	@NotEmpty
	@Column(name = "ID_FRECUENCIA_TIEMPO", length = 3, nullable = false)
	private String idFrecuenciaTiempo;


	public FrecuenciaTiempoPk() {
	}

	public FrecuenciaTiempoPk(String pidFrecuenciaTiempo) {
this.idFrecuenciaTiempo = pidFrecuenciaTiempo;
	}

	/**
	 *  
	 * 
	 * @campo ID_FRECUENCIA_TIEMPO
	*/
	public String getIdFrecuenciaTiempo() {
		return idFrecuenciaTiempo;
	}

	/**
	 *  
	 * 
	 * @campo ID_FRECUENCIA_TIEMPO
	*/
	public void setIdFrecuenciaTiempo(String idFrecuenciaTiempo) {
		this.idFrecuenciaTiempo = idFrecuenciaTiempo;
	}

}
