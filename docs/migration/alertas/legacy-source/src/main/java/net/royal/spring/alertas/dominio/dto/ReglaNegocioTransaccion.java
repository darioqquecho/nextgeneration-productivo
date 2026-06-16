package net.royal.spring.alertas.dominio.dto;

import net.royal.spring.framework.modelo.generico.DominioTransaccion;

public class ReglaNegocioTransaccion extends DominioTransaccion{

	private Integer idReglaNegocio;
	private String flgLogGenerarAlerta;
	private String modificacionUsuario;
	private String modificacionTerminal;
	
	public Integer getIdReglaNegocio() {
		return idReglaNegocio;
	}

	public void setIdReglaNegocio(Integer idReglaNegocio) {
		this.idReglaNegocio = idReglaNegocio;
	}

	public String getFlgLogGenerarAlerta() {
		return flgLogGenerarAlerta;
	}

	public void setFlgLogGenerarAlerta(String flgLogGenerarAlerta) {
		this.flgLogGenerarAlerta = flgLogGenerarAlerta;
	}

	public String getModificacionUsuario() {
		return modificacionUsuario;
	}

	public void setModificacionUsuario(String modificacionUsuario) {
		this.modificacionUsuario = modificacionUsuario;
	}

	public String getModificacionTerminal() {
		return modificacionTerminal;
	}

	public void setModificacionTerminal(String modificacionTerminal) {
		this.modificacionTerminal = modificacionTerminal;
	}
	
	
	
}
