package net.royal.spring.alertas.dominio;

import jakarta.persistence.Column;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

/**
 * 
 * 
 * @tabla SGALERTASSYS.FUENTE_ALERTA_ADICIONAL
*/
public class FuenteAlertaAdicionalPk implements java.io.Serializable{


	private static final long serialVersionUID = 1L;
	@NotNull
	@Column(name = "ID_FUENTE_ALERTA", nullable = false)
	private Integer idFuenteAlerta;

	@NotNull
	@Column(name = "NRO_REGISTRO", nullable = false)
	private Integer nroRegistro;

	@Size(min = 0, max = 100)
	@NotNull
	@NotEmpty
	@Column(name = "CAMPO_NOMBRE", length = 100, nullable = false)
	private String campoNombre;


	public FuenteAlertaAdicionalPk() {
	}

	public FuenteAlertaAdicionalPk(Integer pidFuenteAlerta,Integer pnroRegistro,String pcampoNombre) {
this.idFuenteAlerta = pidFuenteAlerta;
		this.nroRegistro = pnroRegistro;
		this.campoNombre = pcampoNombre;
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
	/**
	 *  
	 * 
	 * @campo CAMPO_NOMBRE
	*/
	public String getCampoNombre() {
		return campoNombre;
	}

	/**
	 *  
	 * 
	 * @campo CAMPO_NOMBRE
	*/
	public void setCampoNombre(String campoNombre) {
		this.campoNombre = campoNombre;
	}

}
