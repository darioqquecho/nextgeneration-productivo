package net.royal.spring.alertas.dominio.dto;

import java.math.BigDecimal;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;

import net.royal.spring.framework.modelo.generico.DominioTransaccion;

public class DtoBdEjecutar extends DominioTransaccion{
	private String sentencia;
	private List lstResultado;
	
	public String getSentencia() {
		return sentencia;
	}

	public void setSentencia(String sentencia) {
		this.sentencia = sentencia;
	}

	public List getLstResultado() {
		return lstResultado;
	}

	public void setLstResultado(List lstResultado) {
		this.lstResultado = lstResultado;
	}
	
}
