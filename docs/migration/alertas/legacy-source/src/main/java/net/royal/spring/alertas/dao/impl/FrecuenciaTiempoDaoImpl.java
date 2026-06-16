package net.royal.spring.alertas.dao.impl;

import java.util.Date;

import jakarta.annotation.Resource;

import org.hibernate.SessionFactory;
import org.springframework.stereotype.Repository;

import net.royal.spring.alertas.dominio.FrecuenciaTiempo;
import net.royal.spring.alertas.dominio.FrecuenciaTiempoPk;
import net.royal.spring.framework.constante.ConstanteEstadoGenerico;
import net.royal.spring.framework.modelo.seguridad.SeguridadUsuarioActual;
import net.royal.spring.framework.util.UString;
import net.royal.spring.framework.web.dao.impl.GenericoDaoImpl;

@Repository
public class FrecuenciaTiempoDaoImpl extends GenericoDaoImpl<FrecuenciaTiempo, FrecuenciaTiempoPk> {

	private static final long serialVersionUID = 1L;

	public FrecuenciaTiempoDaoImpl() {
		super("frecuenciatiempo");
	}

	//@Resource(name = "sessionFactory")
	public void asignarSessionFactory(SessionFactory factory) {
		super.setSessionFactory(factory);
	}

	public FrecuenciaTiempo obtenerPorId(String pidFrecuenciaTiempo) {
		return obtenerPorId(new FrecuenciaTiempoPk( pidFrecuenciaTiempo));
	}

	public FrecuenciaTiempo coreInsertar(FrecuenciaTiempo bean) {
		// TODO FrecuenciaTiempo.Insertar Datos
		
		this.registrar(bean);
		return bean;
	}

	public FrecuenciaTiempo coreInsertar(SeguridadUsuarioActual usuarioActual, FrecuenciaTiempo bean, String estado) {
		if (UString.estaVacio(estado))
			estado = ConstanteEstadoGenerico.ACTIVO;
		bean.setEstado(estado);
		bean.setCreacionTerminal(usuarioActual.getDireccionIp());
		bean.setCreacionFecha(new Date());
		bean.setCreacionUsuario(usuarioActual.getUsuario());
		this.registrar(bean);
		return bean;
	}

	public FrecuenciaTiempo coreActualizar(SeguridadUsuarioActual usuarioActual, FrecuenciaTiempo bean, String estado) {
		if (UString.estaVacio(estado))
			estado = ConstanteEstadoGenerico.ACTIVO;
		bean.setEstado(estado);
		bean.setModificacionTerminal(usuarioActual.getDireccionIp());
		bean.setModificacionFecha(new Date());
		bean.setModificacionUsuario(usuarioActual.getUsuario());
		this.actualizar(bean);
		return bean;
	}

	public FrecuenciaTiempo coreActualizar(FrecuenciaTiempo bean) {
		this.actualizar(bean);
		return bean;
	}

}
