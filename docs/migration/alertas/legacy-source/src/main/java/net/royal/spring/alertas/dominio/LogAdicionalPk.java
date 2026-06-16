package net.royal.spring.alertas.dominio;

import jakarta.persistence.Column;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

/**
 * 
 * 
 * @tabla SGALERTASSYS.LOG_ADICIONAL
*/
public class LogAdicionalPk implements java.io.Serializable{


	private static final long serialVersionUID = 1L;
	@NotNull
	@Column(name = "ID_LOG_ALERTA", nullable = false)
	private Integer idLogAlerta;

	@NotNull
	@Column(name = "NRO_REGISTRO", nullable = false)
	private Integer nroRegistro;

	@Size(min = 0, max = 100)
	@NotNull
	@NotEmpty
	@Column(name = "NOMBRE_CAMPO", length = 100, nullable = false)
	private String nombreCampo;


	public LogAdicionalPk() {
	}

	public LogAdicionalPk(Integer pidLogAlerta,Integer pnroRegistro,String pnombreCampo) {
this.idLogAlerta = pidLogAlerta;
		this.nroRegistro = pnroRegistro;
		this.nombreCampo = pnombreCampo;
	}

	/**
	 *  
	 * 
	 * @campo ID_LOG_ALERTA
	*/
	public Integer getIdLogAlerta() {
		return idLogAlerta;
	}

	/**
	 *  
	 * 
	 * @campo ID_LOG_ALERTA
	*/
	public void setIdLogAlerta(Integer idLogAlerta) {
		this.idLogAlerta = idLogAlerta;
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
	 * @campo NOMBRE_CAMPO
	*/
	public String getNombreCampo() {
		return nombreCampo;
	}

	/**
	 *  
	 * 
	 * @campo NOMBRE_CAMPO
	*/
	public void setNombreCampo(String nombreCampo) {
		this.nombreCampo = nombreCampo;
	}

}
