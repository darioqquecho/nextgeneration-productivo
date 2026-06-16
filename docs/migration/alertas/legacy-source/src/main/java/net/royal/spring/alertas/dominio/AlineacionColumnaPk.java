package net.royal.spring.alertas.dominio;

import jakarta.persistence.Column;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

/**
 * 
 * 
 * @tabla SGALERTASSYS.ALINEACION_COLUMNA
*/
public class AlineacionColumnaPk implements java.io.Serializable{


	private static final long serialVersionUID = 1L;
	@Size(min = 0, max = 3)
	@NotNull
	@NotEmpty
	@Column(name = "ID_ALINEACION_COLUMNA", length = 3, nullable = false)
	private String idAlineacionColumna;


	public AlineacionColumnaPk() {
	}

	public AlineacionColumnaPk(String pidAlineacionColumna) {
this.idAlineacionColumna = pidAlineacionColumna;
	}

	/**
	 *  
	 * 
	 * @campo ID_ALINEACION_COLUMNA
	*/
	public String getIdAlineacionColumna() {
		return idAlineacionColumna;
	}

	/**
	 *  
	 * 
	 * @campo ID_ALINEACION_COLUMNA
	*/
	public void setIdAlineacionColumna(String idAlineacionColumna) {
		this.idAlineacionColumna = idAlineacionColumna;
	}

}
