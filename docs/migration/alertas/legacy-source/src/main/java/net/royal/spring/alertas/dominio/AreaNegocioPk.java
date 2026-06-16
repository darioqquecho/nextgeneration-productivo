package net.royal.spring.alertas.dominio;

import jakarta.persistence.Column;
import jakarta.validation.constraints.NotNull;

/**
 * 
 * 
 * @tabla SGALERTASSYS.AREA_NEGOCIO
*/
public class AreaNegocioPk implements java.io.Serializable{


	private static final long serialVersionUID = 1L;
	@NotNull
	@Column(name = "ID_AREA_NEGOCIO", nullable = false)
	private Integer idAreaNegocio;


	public AreaNegocioPk() {
	}

	public AreaNegocioPk(Integer pidAreaNegocio) {
this.idAreaNegocio = pidAreaNegocio;
	}

	/**
	 *  
	 * 
	 * @campo ID_AREA_NEGOCIO  
	*/
	public Integer getIdAreaNegocio() {
		return idAreaNegocio;
	}

	/**
	 *  
	 * 
	 * @campo ID_AREA_NEGOCIO
	*/
	public void setIdAreaNegocio(Integer idAreaNegocio) {
		this.idAreaNegocio = idAreaNegocio;
	}

}
