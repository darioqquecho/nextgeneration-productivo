package net.royal.spring.alertas.dominio.dto;

import java.math.BigDecimal;

public class AlertaPlandto {

	private BigDecimal idPlan;
	private BigDecimal ordenColumna;
	private  String plan;
	private String estado;
	
	private BigDecimal	ROWNUM_ ;

	
	public BigDecimal getROWNUM_() {
		return ROWNUM_;
	}
	public void setROWNUM_(BigDecimal rOWNUM_) {
		ROWNUM_ = rOWNUM_;
	}
	public BigDecimal getIdPlan() {
		return idPlan;
	}
	public void setIdPlan(BigDecimal idPlan) {
		this.idPlan = idPlan;
	}
	public BigDecimal getOrdenColumna() {
		return ordenColumna;
	}
	public void setOrdenColumna(BigDecimal ordenColumna) {
		this.ordenColumna = ordenColumna;
	}
	public String getPlan() {
		return plan;
	}
	public void setPlan(String plan) {
		this.plan = plan;
	}
	public String getEstado() {
		return estado;
	}
	public void setEstado(String estado) {
		this.estado = estado;
	}
	
	
	
}
