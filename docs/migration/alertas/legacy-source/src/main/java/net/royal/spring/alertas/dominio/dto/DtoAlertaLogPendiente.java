package net.royal.spring.alertas.dominio.dto;

import java.math.BigDecimal;

public class DtoAlertaLogPendiente {
	private BigDecimal idReglaNegocio;
	private Integer cantidadProgramacionCorreo;
	private Boolean flgGenerarAlerta;
	private String flgLogGenerarAlerta;

	public BigDecimal getIdReglaNegocio() {
		return idReglaNegocio;
	}

	public void setIdReglaNegocio(BigDecimal idReglaNegocio) {
		this.idReglaNegocio = idReglaNegocio;
	}

	public Integer getCantidadProgramacionCorreo() {
		return cantidadProgramacionCorreo;
	}

	public void setCantidadProgramacionCorreo(Integer cantidadProgramacionCorreo) {
		this.cantidadProgramacionCorreo = cantidadProgramacionCorreo;
	}

	public Boolean getFlgGenerarAlerta() {
		return flgGenerarAlerta;
	}

	public void setFlgGenerarAlerta(Boolean flgGenerarAlerta) {
		this.flgGenerarAlerta = flgGenerarAlerta;
	}

	public String getFlgLogGenerarAlerta() {
		return flgLogGenerarAlerta;
	}

	public void setFlgLogGenerarAlerta(String flgLogGenerarAlerta) {
		this.flgLogGenerarAlerta = flgLogGenerarAlerta;
	}
	
}
