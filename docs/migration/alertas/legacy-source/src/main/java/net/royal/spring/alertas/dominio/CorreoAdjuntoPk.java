package net.royal.spring.alertas.dominio;

import jakarta.persistence.Column;
import jakarta.validation.constraints.NotNull;

/**
 * 
 * 
 * @tabla SGALERTASSYS.CORREO_ADJUNTO
*/
public class CorreoAdjuntoPk implements java.io.Serializable{


	private static final long serialVersionUID = 1L;
	@NotNull
	@Column(name = "ID_CORREO", nullable = false)
	private Integer idCorreo;

	@NotNull
	@Column(name = "ID_ADJUNTO", nullable = false)
	private Integer idAdjunto;


	public CorreoAdjuntoPk() {
	}

	public CorreoAdjuntoPk(Integer pidCorreo,Integer pidAdjunto) {
this.idCorreo = pidCorreo;
		this.idAdjunto = pidAdjunto;
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
	 * @campo ID_ADJUNTO
	*/
	public Integer getIdAdjunto() {
		return idAdjunto;
	}

	/**
	 *  
	 * 
	 * @campo ID_ADJUNTO
	*/
	public void setIdAdjunto(Integer idAdjunto) {
		this.idAdjunto = idAdjunto;
	}

}
