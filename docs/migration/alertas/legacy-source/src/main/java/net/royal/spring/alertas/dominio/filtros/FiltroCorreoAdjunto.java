package net.royal.spring.alertas.dominio.filtros;

import java.math.BigDecimal;

import net.royal.spring.framework.modelo.generico.DominioPaginacion;

public class FiltroCorreoAdjunto {
	
	private BigDecimal idCorreo;
	
	
	private BigDecimal	ROWNUM_ ;
	private DominioPaginacion paginacion;
	
	
	public BigDecimal getIdCorreo() {
		return idCorreo;
	}
	public void setIdCorreo(BigDecimal idCorreo) {
		this.idCorreo = idCorreo;
	}
	public BigDecimal getROWNUM_() {
		return ROWNUM_;
	}
	public void setROWNUM_(BigDecimal rOWNUM_) {
		ROWNUM_ = rOWNUM_;
	}
	public DominioPaginacion getPaginacion() {
		return paginacion;
	}
	public void setPaginacion(DominioPaginacion paginacion) {
		this.paginacion = paginacion;
	}
	
	
}
