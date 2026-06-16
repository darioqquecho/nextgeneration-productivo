package net.royal.spring.alertas.dominio;

import jakarta.persistence.Column;
import jakarta.validation.constraints.NotNull;

/**
 * 
 * 
 * @tabla SGALERTASSYS.ALERTA_DESTINO
*/
public class AlertaDestinoPk implements java.io.Serializable{


	private static final long serialVersionUID = 1L;
	@NotNull
	@Column(name = "ID_ALERTA", nullable = false)
	private Integer idAlerta;

	@NotNull
	@Column(name = "ID_DESTINO", nullable = false)
	private Integer idDestino;


	public AlertaDestinoPk() {
	}

	public AlertaDestinoPk(Integer pidAlerta,Integer pidDestino) {
this.idAlerta = pidAlerta;
		this.idDestino = pidDestino;
	}

	/**
	 *  
	 * 
	 * @campo ID_ALERTA
	*/
	public Integer getIdAlerta() {
		return idAlerta;
	}

	/**
	 *  
	 * 
	 * @campo ID_ALERTA
	*/
	public void setIdAlerta(Integer idAlerta) {
		this.idAlerta = idAlerta;
	}
	/**
	 *  
	 * 
	 * @campo ID_DESTINO
	*/
	public Integer getIdDestino() {
		return idDestino;
	}

	/**
	 *  
	 * 
	 * @campo ID_DESTINO
	*/
	public void setIdDestino(Integer idDestino) {
		this.idDestino = idDestino;
	}

}
