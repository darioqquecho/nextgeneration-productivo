package net.royal.spring.alertas.dominio;

import jakarta.persistence.Column;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

/**
 * 
 * 
 * @tabla SGALERTASSYS.FRECUENCIA_PROGRAMACION
*/
public class FrecuenciaProgramacionPk implements java.io.Serializable{


	private static final long serialVersionUID = 1L;
	@Size(min = 0, max = 3)
	@NotNull
	@NotEmpty
	@Column(name = "ID_FRECUENCIA_PROGRAMACION", length = 3, nullable = false)
	private String idFrecuenciaProgramacion;


	public FrecuenciaProgramacionPk() {
	}

	public FrecuenciaProgramacionPk(String pidFrecuenciaProgramacion) {
this.idFrecuenciaProgramacion = pidFrecuenciaProgramacion;
	}

	/**
	 *  
	 * 
	 * @campo ID_FRECUENCIA_PROGRAMACION
	*/
	public String getIdFrecuenciaProgramacion() {
		return idFrecuenciaProgramacion;
	}

	/**
	 *  
	 * 
	 * @campo ID_FRECUENCIA_PROGRAMACION
	*/
	public void setIdFrecuenciaProgramacion(String idFrecuenciaProgramacion) {
		this.idFrecuenciaProgramacion = idFrecuenciaProgramacion;
	}

}
