package net.royal.spring.alertas.dominio;

import jakarta.persistence.Column;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

/**
 * 
 * 
 * @tabla SGALERTASSYS.REGLA_NEGOCIO_DESTINO
*/
public class ReglaNegocioDestinoPk implements java.io.Serializable{


	private static final long serialVersionUID = 1L;
	@NotNull
	@Column(name = "ID_REGLA_NEGOCIO", nullable = false)
	private Integer idReglaNegocio;

	@Size(min = 0, max = 200)
	@NotNull
	@NotEmpty
	@Column(name = "CORREO_DESTINO", length = 200, nullable = false)
	private String correoDestino;


	public ReglaNegocioDestinoPk() {
	}

	public ReglaNegocioDestinoPk(Integer pidReglaNegocio,String pcorreoDestino) {
this.idReglaNegocio = pidReglaNegocio;
		this.correoDestino = pcorreoDestino;
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
	 * @campo CORREO_DESTINO
	*/
	public String getCorreoDestino() {
		return correoDestino;
	}

	/**
	 *  
	 * 
	 * @campo CORREO_DESTINO
	*/
	public void setCorreoDestino(String correoDestino) {
		this.correoDestino = correoDestino;
	}

}
