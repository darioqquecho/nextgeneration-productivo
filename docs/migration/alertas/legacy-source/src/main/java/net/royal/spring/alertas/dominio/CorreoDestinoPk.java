package net.royal.spring.alertas.dominio;

import jakarta.persistence.Column;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

/**
 * 
 * 
 * @tabla SGALERTASSYS.CORREO_DESTINO
*/
public class CorreoDestinoPk implements java.io.Serializable{


	private static final long serialVersionUID = 1L;
	@NotNull
	@Column(name = "ID_CORREO", nullable = false)
	private Integer idCorreo;

	@Size(min = 0, max = 200)
	@NotNull
	@NotEmpty
	@Column(name = "CORREO_DESTINO", length = 200, nullable = false)
	private String correoDestino;


	public CorreoDestinoPk() {
	}

	public CorreoDestinoPk(Integer pidCorreo,String pcorreoDestino) {
this.idCorreo = pidCorreo;
		this.correoDestino = pcorreoDestino;
	}

	/**
	 *  
	 * 
	 * @campo ID_CORREO
	*/
	public Integer getIdCorreo() {
		return idCorreo;
	}

	/**
	 *  
	 * 
	 * @campo ID_CORREO
	*/
	public void setIdCorreo(Integer idCorreo) {
		this.idCorreo = idCorreo;
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
