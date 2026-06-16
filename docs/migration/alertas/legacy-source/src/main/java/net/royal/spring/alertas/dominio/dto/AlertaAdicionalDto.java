package net.royal.spring.alertas.dominio.dto;

import jakarta.persistence.Column;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import net.royal.spring.alertas.dominio.AlertaAdicional;
import net.royal.spring.alertas.dominio.AlertaPlan;
import net.royal.spring.framework.util.UBigDecimal;

public class AlertaAdicionalDto {
	private Integer idAlerta;

	private Integer idAdicional;

	private Integer idLogAlerta;

	private Integer ordenColumna;

	private String nombreCampo;

	private String descripcionCampo;

	private String valor;

	private String estado;
	
	public Integer getIdAlerta() {
		return idAlerta;
	}



	public void setIdAlerta(Integer idAlerta) {
		this.idAlerta = idAlerta;
	}



	public Integer getIdAdicional() {
		return idAdicional;
	}



	public void setIdAdicional(Integer idAdicional) {
		this.idAdicional = idAdicional;
	}



	public Integer getIdLogAlerta() {
		return idLogAlerta;
	}



	public void setIdLogAlerta(Integer idLogAlerta) {
		this.idLogAlerta = idLogAlerta;
	}



	public Integer getOrdenColumna() {
		return ordenColumna;
	}



	public void setOrdenColumna(Integer ordenColumna) {
		this.ordenColumna = ordenColumna;
	}



	public String getNombreCampo() {
		return nombreCampo;
	}



	public void setNombreCampo(String nombreCampo) {
		this.nombreCampo = nombreCampo;
	}



	public String getDescripcionCampo() {
		return descripcionCampo;
	}



	public void setDescripcionCampo(String descripcionCampo) {
		this.descripcionCampo = descripcionCampo;
	}



	public String getValor() {
		return valor;
	}



	public void setValor(String valor) {
		this.valor = valor;
	}



	public String getEstado() {
		return estado;
	}



	public void setEstado(String estado) {
		this.estado = estado;
	}



	public AlertaAdicional getBean(){
		AlertaAdicional bean = new AlertaAdicional();
		bean.getPk().setIdAlerta(idAlerta); 
		bean.getPk().setIdAdicional(idAdicional);		
		bean.setOrdenColumna(ordenColumna);
		bean.setNombreCampo(nombreCampo);
		bean.setDescripcionCampo(descripcionCampo);
		bean.setValor(valor);
		bean.setEstado(estado);		
		return bean;
	}
}
