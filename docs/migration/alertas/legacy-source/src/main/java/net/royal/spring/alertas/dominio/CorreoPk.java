package net.royal.spring.alertas.dominio;

import jakarta.persistence.Column;
import jakarta.validation.constraints.NotNull;

/**
 * 
 * 
 * @tabla SGALERTASSYS.CORREO
*/
public class CorreoPk implements java.io.Serializable{


	private static final long serialVersionUID = 1L;
	@NotNull
	@Column(name = "ID_CORREO", nullable = false)
	private Integer idCorreo;


	public CorreoPk() {
	}

	public CorreoPk(Integer pidCorreo) {
this.idCorreo = pidCorreo;
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

}
