package net.royal.spring.alertas.dominio;

import jakarta.persistence.Column;
import jakarta.validation.constraints.NotNull;

/**
 * 
 * 
 * @tabla SGALERTASSYS.REGLA_NEGOCIO
*/
public class ReglaNegocioPk implements java.io.Serializable{


	private static final long serialVersionUID = 1L;
	@NotNull
	@Column(name = "ID_REGLA_NEGOCIO", nullable = false)
	private Integer idReglaNegocio;


	public ReglaNegocioPk() {
	}

	public ReglaNegocioPk(Integer pidReglaNegocio) {
this.idReglaNegocio = pidReglaNegocio;
	}

	/**
	 *  
	 * 
	 * @campo ID_REGLA_NEGOCIO
	*/
	public Integer getIdReglaNegocio() {
		return idReglaNegocio;
	}

	/**
	 *  
	 * 
	 * @campo ID_REGLA_NEGOCIO
	*/
	public void setIdReglaNegocio(Integer idReglaNegocio) {
		this.idReglaNegocio = idReglaNegocio;
	}

}
