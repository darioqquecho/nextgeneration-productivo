package net.royal.spring.alertas.dominio;

import jakarta.persistence.Column;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

/**
 * 
 * 
 * @tabla SGALERTASSYS.TIPO_DESTINO
*/
public class TipoDestinoPk implements java.io.Serializable{


	private static final long serialVersionUID = 1L;
	@Size(min = 0, max = 3)
	@NotNull
	@NotEmpty
	@Column(name = "ID_TIPO_DESTINO", length = 3, nullable = false)
	private String idTipoDestino;


	public TipoDestinoPk() {
	}

	public TipoDestinoPk(String pidTipoDestino) {
this.idTipoDestino = pidTipoDestino;
	}

	/**
	 *  
	 * 
	 * @campo ID_TIPO_DESTINO
	*/
	public String getIdTipoDestino() {
		return idTipoDestino;
	}

	/**
	 *  
	 * 
	 * @campo ID_TIPO_DESTINO
	*/
	public void setIdTipoDestino(String idTipoDestino) {
		this.idTipoDestino = idTipoDestino;
	}

}
