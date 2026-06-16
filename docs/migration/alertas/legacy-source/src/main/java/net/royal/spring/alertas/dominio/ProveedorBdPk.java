package net.royal.spring.alertas.dominio;

import jakarta.persistence.Column;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

/**
 * 
 * 
 * @tabla SGALERTASSYS.PROVEEDOR_BD
*/
public class ProveedorBdPk implements java.io.Serializable{


	private static final long serialVersionUID = 1L;
	@Size(min = 0, max = 3)
	@NotNull
	@NotEmpty
	@Column(name = "ID_PROVEEDOR_BD", length = 3, nullable = false)
	private String idProveedorBd;


	public ProveedorBdPk() {
	}

	public ProveedorBdPk(String pidProveedorBd) {
this.idProveedorBd = pidProveedorBd;
	}

	/**
	 *  
	 * 
	 * @campo ID_PROVEEDOR_BD
	*/
	public String getIdProveedorBd() {
		return idProveedorBd;
	}

	/**
	 *  
	 * 
	 * @campo ID_PROVEEDOR_BD
	*/
	public void setIdProveedorBd(String idProveedorBd) {
		this.idProveedorBd = idProveedorBd;
	}

}
