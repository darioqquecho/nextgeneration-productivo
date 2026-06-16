package net.royal.spring.alertas.dominio;

import jakarta.persistence.Column;
import jakarta.validation.constraints.NotNull;

/**
 * 
 * 
 * @tabla SGALERTASSYS.CONEXION_BD
*/
public class ConexionBdPk implements java.io.Serializable{


	private static final long serialVersionUID = 1L;
	@NotNull
	@Column(name = "ID_CONEXION_BD", nullable = false)
	private Integer idConexionBd;


	public ConexionBdPk() {
	}

	public ConexionBdPk(Integer pidConexionBd) {
this.idConexionBd = pidConexionBd;
	}

	/**
	 *  
	 * 
	 * @campo ID_CONEXION_BD
	*/
	public Integer getIdConexionBd() {
		return idConexionBd;
	}

	/**
	 *  
	 * 
	 * @campo ID_CONEXION_BD
	*/
	public void setIdConexionBd(Integer idConexionBd) {
		this.idConexionBd = idConexionBd;
	}

}
