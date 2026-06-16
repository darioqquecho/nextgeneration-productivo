package net.royal.spring.alertas.dominio;

import jakarta.persistence.Column;
import jakarta.validation.constraints.NotNull;

/**
 * 
 * 
 * @tabla SGALERTASSYS.PERFIL_CORREO
*/
public class PerfilCorreoPk implements java.io.Serializable{


	private static final long serialVersionUID = 1L;
	@NotNull
	@Column(name = "ID_PERFIL_CORREO", nullable = false)
	private Integer idPerfilCorreo;


	public PerfilCorreoPk() {
	}

	public PerfilCorreoPk(Integer pidPerfilCorreo) {
this.idPerfilCorreo = pidPerfilCorreo;
	}

	/**
	 *  
	 * 
	 * @campo ID_PERFIL_CORREO
	*/
	public Integer getIdPerfilCorreo() {
		return idPerfilCorreo;
	}

	/**
	 *  
	 * 
	 * @campo ID_PERFIL_CORREO
	*/
	public void setIdPerfilCorreo(Integer idPerfilCorreo) {
		this.idPerfilCorreo = idPerfilCorreo;
	}

}
