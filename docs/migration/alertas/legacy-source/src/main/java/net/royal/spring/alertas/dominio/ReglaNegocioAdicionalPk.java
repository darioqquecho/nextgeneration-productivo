package net.royal.spring.alertas.dominio;

import jakarta.persistence.Column;
import jakarta.validation.constraints.NotNull;

/**
 * 
 * 
 * @tabla SGALERTASSYS.REGLA_NEGOCIO_ADICIONAL
*/
public class ReglaNegocioAdicionalPk implements java.io.Serializable{


	private static final long serialVersionUID = 1L;
	@NotNull
	@Column(name = "ID_REGLA_NEGOCIO", nullable = false)
	private Integer idReglaNegocio;

	@NotNull
	@Column(name = "ID_ADICIONAL", nullable = false)
	private Integer idAdicional;


	public ReglaNegocioAdicionalPk() {
	}

	public ReglaNegocioAdicionalPk(Integer pidReglaNegocio,Integer pidAdicional) {
this.idReglaNegocio = pidReglaNegocio;
		this.idAdicional = pidAdicional;
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
	/**
	 *  
	 * 
	 * @campo ID_ADICIONAL
	*/
	public Integer getIdAdicional() {
		return idAdicional;
	}

	/**
	 *  
	 * 
	 * @campo ID_ADICIONAL
	*/
	public void setIdAdicional(Integer idAdicional) {
		this.idAdicional = idAdicional;
	}

}
