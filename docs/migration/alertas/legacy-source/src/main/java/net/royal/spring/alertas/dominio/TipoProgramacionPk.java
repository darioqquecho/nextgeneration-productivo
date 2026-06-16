package net.royal.spring.alertas.dominio;

import jakarta.persistence.Column;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

/**
 * 
 * 
 * @tabla SGALERTASSYS.TIPO_PROGRAMACION
*/
public class TipoProgramacionPk implements java.io.Serializable{

	private static final long serialVersionUID = 1L;

	@Size(min = 0, max = 3)
	@NotNull
	@NotEmpty
	@Column(name = "ID_TIPO_PROGRAMACION", length = 3, nullable = false)
	private String idTipoProgramacion;


	public TipoProgramacionPk() {
	}

	public TipoProgramacionPk(String pidTipoProgramacion) {
this.idTipoProgramacion = pidTipoProgramacion;
	}

	/**
	 *  
	 * 
	 * @campo ID_TIPO_PROGRAMACION
	*/
	public String getIdTipoProgramacion() {
		return idTipoProgramacion;
	}

	/**
	 *  
	 * 
	 * @campo ID_TIPO_PROGRAMACION
	*/
	public void setIdTipoProgramacion(String idTipoProgramacion) {
		this.idTipoProgramacion = idTipoProgramacion;
	}

}
