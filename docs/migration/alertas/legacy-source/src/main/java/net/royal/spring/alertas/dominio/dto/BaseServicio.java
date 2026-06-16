package net.royal.spring.alertas.dominio.dto;

public class BaseServicio {

	private volatile Boolean flgEjecutandose = false;
	private volatile Integer contadorVeces = 0;

	public Integer getContadorVeces() {
		if (contadorVeces == null)
			contadorVeces = 0;
		return contadorVeces;
	}

	public void setContadorVeces(Integer contadorVeces) {
		this.contadorVeces = contadorVeces;
	}

	public Boolean getFlgEjecutandose() {
		if (flgEjecutandose == null)
			return false;
		return flgEjecutandose;
	}

	public void setFlgEjecutandose(Boolean flgEjecutandose) {
		this.flgEjecutandose = flgEjecutandose;
	}

	/*
	 * public void liberar(Session sessionInterna) { if (sessionInterna != null) {
	 * sessionInterna.clear(); sessionInterna.disconnect(); sessionInterna.close();
	 * } }
	 * 
	 * public void transaccionFin(Session sessionExterna) { Session sessionInterna =
	 * null; if (sessionExterna != null) sessionInterna = sessionExterna;
	 * 
	 * if (sessionInterna == null) { LOGGER.debug("session NULL"); return; } if
	 * (sessionInterna.getTransaction() == null) {
	 * LOGGER.debug("session.getTransaction() NULL"); return; }
	 * sessionInterna.getTransaction().commit(); }
	 */
}
