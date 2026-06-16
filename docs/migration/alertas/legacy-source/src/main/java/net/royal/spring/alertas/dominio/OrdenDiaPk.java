package net.royal.spring.alertas.dominio;

import jakarta.persistence.Column;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

/**
 * 
 * 
 * @tabla SGALERTASSYS.ORDEN_DIA
*/
public class OrdenDiaPk implements java.io.Serializable{


	private static final long serialVersionUID = 1L;
	@Size(min = 0, max = 3)
	@NotNull
	@NotEmpty
	@Column(name = "ID_ORDEN_DIA", length = 3, nullable = false)
	private String idOrdenDia;


	public OrdenDiaPk() {
	}

	public OrdenDiaPk(String pidOrdenDia) {
this.idOrdenDia = pidOrdenDia;
	}

	/**
	 *  
	 * 
	 * @campo ID_ORDEN_DIA
	*/
	public String getIdOrdenDia() {
		return idOrdenDia;
	}

	/**
	 *  
	 * 
	 * @campo ID_ORDEN_DIA
	*/
	public void setIdOrdenDia(String idOrdenDia) {
		this.idOrdenDia = idOrdenDia;
	}

}
