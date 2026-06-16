package net.royal.spring.alertas.dominio;

import jakarta.persistence.Column;
import jakarta.validation.constraints.NotNull;

/**
 * 
 * 
 * @tabla SGALERTASSYS.REGLA_NEGOCIO_PROGRAMACION
*/
public class ReglaNegocioProgramacionPk implements java.io.Serializable{


	private static final long serialVersionUID = 1L;
	@NotNull
	@Column(name = "ID_REGLA_NEGOCIO", nullable = false)
	private Integer idReglaNegocio;

	@NotNull
	@Column(name = "ID_PROGRAMACION", nullable = false)
	private Integer idProgramacion;


	public ReglaNegocioProgramacionPk() {
	}

	public ReglaNegocioProgramacionPk(Integer pidReglaNegocio,Integer pidProgramacion) {
this.idReglaNegocio = pidReglaNegocio;
		this.idProgramacion = pidProgramacion;
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
	 * @campo ID_PROGRAMACION
	*/
	public Integer getIdProgramacion() {
		return idProgramacion;
	}

	/**
	 *  
	 * 
	 * @campo ID_PROGRAMACION
	*/
	public void setIdProgramacion(Integer idProgramacion) {
		this.idProgramacion = idProgramacion;
	}

}
