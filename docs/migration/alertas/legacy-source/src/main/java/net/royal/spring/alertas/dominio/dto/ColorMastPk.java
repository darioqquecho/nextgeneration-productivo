package net.royal.spring.alertas.dominio.dto;

import java.io.Serializable;

public class ColorMastPk implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String color;

	public ColorMastPk(){}
	public ColorMastPk(String color){
		this.color=color;
	}
	
	public String getColor() {
		return color;
	}

	public void setColor(String color) {
		this.color = color;
	}

}
