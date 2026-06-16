package net.royal.spring.alertas.dominio;

import jakarta.persistence.Column;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

/**
 * 
 * 
 * @tabla SGALERTASSYS.TIPO_DATO
*/
public class TipoDatoPk implements java.io.Serializable{


	private static final long serialVersionUID = 1L;
	@Size(min = 0, max = 3)
	@NotNull
	@NotEmpty
	@Column(name = "ID_TIPO_DATO", length = 3, nullable = false)
	private String idTipoDato;


	public TipoDatoPk() {
	}

	public TipoDatoPk(String pidTipoDato) {
this.idTipoDato = pidTipoDato;
	}

	/**
	 *  
	 * 
	 * @campo ID_TIPO_DATO
	*/
	public String getIdTipoDato() {
		return idTipoDato;
	}

	/**
	 *  
	 * 
	 * @campo ID_TIPO_DATO
	*/
	public void setIdTipoDato(String idTipoDato) {
		this.idTipoDato = idTipoDato;
	}

}
