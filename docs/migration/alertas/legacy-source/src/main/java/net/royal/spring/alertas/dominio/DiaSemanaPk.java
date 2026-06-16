package net.royal.spring.alertas.dominio;

import jakarta.persistence.Column;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

/**
 * 
 * 
 * @tabla SGALERTASSYS.DIA_SEMANA
*/
public class DiaSemanaPk implements java.io.Serializable{


	private static final long serialVersionUID = 1L;
	@Size(min = 0, max = 3)
	@NotNull
	@NotEmpty
	@Column(name = "ID_DIA_SEMANA", length = 3, nullable = false)
	private String idDiaSemana;


	public DiaSemanaPk() {
	}

	public DiaSemanaPk(String pidDiaSemana) {
this.idDiaSemana = pidDiaSemana;
	}

	/**
	 *  
	 * 
	 * @campo ID_DIA_SEMANA
	*/
	public String getIdDiaSemana() {
		return idDiaSemana;
	}

	/**
	 *  
	 * 
	 * @campo ID_DIA_SEMANA
	*/
	public void setIdDiaSemana(String idDiaSemana) {
		this.idDiaSemana = idDiaSemana;
	}

}
