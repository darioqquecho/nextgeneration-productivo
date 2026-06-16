package net.royal.spring.alertas.dominio;

import jakarta.persistence.Column;
import jakarta.validation.constraints.NotNull;

/**
 * 
 * 
 * @tabla SGALERTASSYS.FUENTE_HISTORIA
*/
public class FuenteHistoriaPk implements java.io.Serializable{


	private static final long serialVersionUID = 1L;
	@NotNull
	@Column(name = "ID_REGLA_NEGOCIO", nullable = false)
	private Integer idReglaNegocio;

	@NotNull
	@Column(name = "ID_FUENTE_ALERTA", nullable = false)
	private Integer idFuenteAlerta;

	@NotNull
	@Column(name = "NRO_REGISTRO", nullable = false)
	private Integer nroRegistro;


	public FuenteHistoriaPk() {
	}

	public FuenteHistoriaPk(Integer pidReglaNegocio,Integer pidFuenteAlerta,Integer pnroRegistro) {
this.idReglaNegocio = pidReglaNegocio;
		this.idFuenteAlerta = pidFuenteAlerta;
		this.nroRegistro = pnroRegistro;
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
	 * @campo ID_FUENTE_ALERTA
	*/
	public Integer getIdFuenteAlerta() {
		return idFuenteAlerta;
	}

	/**
	 *  
	 * 
	 * @campo ID_FUENTE_ALERTA
	*/
	public void setIdFuenteAlerta(Integer idFuenteAlerta) {
		this.idFuenteAlerta = idFuenteAlerta;
	}
	/**
	 *  
	 * 
	 * @campo NRO_REGISTRO
	*/
	public Integer getNroRegistro() {
		return nroRegistro;
	}

	/**
	 *  
	 * 
	 * @campo NRO_REGISTRO
	*/
	public void setNroRegistro(Integer nroRegistro) {
		this.nroRegistro = nroRegistro;
	}

}
