package net.royal.spring.alertas.dominio.dto;

import java.math.BigDecimal;

public class AlertaDetalledto {

	private BigDecimal nroRegistro;
	private String nombreCampo;
	private String valor;
	private BigDecimal ordenColumna;
	
	private String estado;
	private BigDecimal idLogAlerta;
	private BigDecimal	ROWNUM_ ;
	
	private String idAlineacionColumna;
	public BigDecimal getNroRegistro() {
		return nroRegistro;
	}
	public void setNroRegistro(BigDecimal nroRegistro) {
		this.nroRegistro = nroRegistro;
	}
	public String getNombreCampo() {
		return nombreCampo;
	}
	public void setNombreCampo(String nombreCampo) {
		this.nombreCampo = nombreCampo;
	}
	public String getValor() {
		return valor;
	}
	public void setValor(String valor) {
		this.valor = valor;
	}
	public BigDecimal getOrdenColumna() {
		return ordenColumna;
	}
	public void setOrdenColumna(BigDecimal ordenColumna) {
		this.ordenColumna = ordenColumna;
	}
	public String getEstado() {
		return estado;
	}
	public void setEstado(String estado) {
		this.estado = estado;
	}
	public BigDecimal getIdLogAlerta() {
		return idLogAlerta;
	}
	public void setIdLogAlerta(BigDecimal idLogAlerta) {
		this.idLogAlerta = idLogAlerta;
	}
	public String getIdAlineacionColumna() {
		return idAlineacionColumna;
	}
	public void setIdAlineacionColumna(String idAlineacionColumna) {
		this.idAlineacionColumna = idAlineacionColumna;
	}
	public BigDecimal getROWNUM_() {
		return ROWNUM_;
	}
	public void setROWNUM_(BigDecimal rOWNUM_) {
		ROWNUM_ = rOWNUM_;
	}

	
}
