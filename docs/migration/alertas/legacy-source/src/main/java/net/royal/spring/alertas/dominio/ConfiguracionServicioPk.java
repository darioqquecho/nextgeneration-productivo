package net.royal.spring.alertas.dominio;

import jakarta.persistence.Column;
import jakarta.validation.constraints.NotNull;

/**
 * 
 * 
 * @tabla SGALERTASSYS.CONFIGURACION_SERVICIO
*/
public class ConfiguracionServicioPk implements java.io.Serializable{


	private static final long serialVersionUID = 1L;
	@NotNull
	@Column(name = "ID_CONFIGURACION_SERVICIO", nullable = false)
	private Integer idConfiguracionServicio;


	public ConfiguracionServicioPk() {
	}

	public ConfiguracionServicioPk(Integer pidConfiguracionServicio) {
this.idConfiguracionServicio = pidConfiguracionServicio;
	}

	/**
	 *  
	 * 
	 * @campo ID_CONFIGURACION_SERVICIO
	*/
	public Integer getIdConfiguracionServicio() {
		return idConfiguracionServicio;
	}

	/**
	 *  
	 * 
	 * @campo ID_CONFIGURACION_SERVICIO
	*/
	public void setIdConfiguracionServicio(Integer idConfiguracionServicio) {
		this.idConfiguracionServicio = idConfiguracionServicio;
	}

}
