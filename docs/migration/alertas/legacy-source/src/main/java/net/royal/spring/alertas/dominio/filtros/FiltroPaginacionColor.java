package net.royal.spring.alertas.dominio.filtros;

import net.royal.spring.framework.modelo.generico.DominioPaginacion;

public class FiltroPaginacionColor {
	private DominioPaginacion paginacion;
	private String nombre;
	private String codigo;
	private String estado;
	public DominioPaginacion getPaginacion() {
		return paginacion;
	}
	public void setPaginacion(DominioPaginacion paginacion) {
		this.paginacion = paginacion;
	}
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	public String getCodigo() {
		return codigo;
	}
	public void setCodigo(String codigo) {
		this.codigo = codigo;
	}
	public String getEstado() {
		return estado;
	}
	public void setEstado(String estado) {
		this.estado = estado;
	}
	
	
}
