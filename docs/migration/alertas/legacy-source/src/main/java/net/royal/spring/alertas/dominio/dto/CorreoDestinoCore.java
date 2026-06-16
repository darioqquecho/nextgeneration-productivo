package net.royal.spring.alertas.dominio.dto;

import net.royal.spring.framework.modelo.correo.EmailDestino.tipo_destino;

//import net.royal.spring.framework.correo.constante.ConstanteEmail.tipo_destino;

public class CorreoDestinoCore {
	
	private tipo_destino destino;
	
	private String correoDestino;

	public CorreoDestinoCore() {
	}
	public CorreoDestinoCore(String correoDestino) {
		this.correoDestino = correoDestino;
	}

	public String getCorreoDestino() {
		return correoDestino;
	}

	public void setCorreoDestino(String correoDestino) {
		this.correoDestino = correoDestino;
	}
	public tipo_destino getDestino() {
		return destino;
	}
	public void setDestino(tipo_destino destino) {
		this.destino = destino;
	}
}
