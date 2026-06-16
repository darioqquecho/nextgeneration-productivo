package net.royal.spring.alertas.dominio;

import jakarta.persistence.Column;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

/**
 * Maestro de colores
 * 
 * @tabla SGCORESYS.COLORMAST
*/
public class ColormastPk implements java.io.Serializable{


	private static final long serialVersionUID = 1L;
	@Size(min = 0, max = 3)
	@NotNull
	@NotEmpty
	@Column(name = "COLOR", length = 3, nullable = false)
	private String color;


	public ColormastPk() {
	}

	public ColormastPk(String pcolor) {
this.color = pcolor;
	}

	/**
	 * Color
	 * 
	 * @campo COLOR
	*/
	public String getColor() {
		return color;
	}

	/**
	 * Color
	 * 
	 * @campo COLOR
	*/
	public void setColor(String color) {
		this.color = color;
	}

}
