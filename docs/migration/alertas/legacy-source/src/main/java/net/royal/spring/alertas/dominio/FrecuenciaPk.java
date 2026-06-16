package net.royal.spring.alertas.dominio;

import jakarta.persistence.Column;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

/**
 * 
 * 
 * @tabla SGALERTASSYS.FRECUENCIA
*/
public class FrecuenciaPk implements java.io.Serializable{


	private static final long serialVersionUID = 1L;
	@Size(min = 0, max = 3)
	@NotNull
	@NotEmpty
	@Column(name = "ID_FRECUENCIA", length = 3, nullable = false)
	private String idFrecuencia;


	public FrecuenciaPk() {
	}

	public FrecuenciaPk(String pidFrecuencia) {
this.idFrecuencia = pidFrecuencia;
	}

	/**
	 *  
	 * 
	 * @campo ID_FRECUENCIA
	*/
	public String getIdFrecuencia() {
		return idFrecuencia;
	}

	/**
	 *  
	 * 
	 * @campo ID_FRECUENCIA
	*/
	public void setIdFrecuencia(String idFrecuencia) {
		this.idFrecuencia = idFrecuencia;
	}

}
