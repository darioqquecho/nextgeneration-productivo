package net.royal.spring.alertas.dao.impl;

import java.util.Date;

import jakarta.annotation.Resource;

import org.hibernate.SessionFactory;
import org.springframework.stereotype.Repository;

import net.royal.spring.alertas.dominio.FrecuenciaMensual;
import net.royal.spring.alertas.dominio.FrecuenciaMensualPk;
import net.royal.spring.framework.constante.ConstanteEstadoGenerico;
import net.royal.spring.framework.modelo.seguridad.SeguridadUsuarioActual;
import net.royal.spring.framework.util.UString;
import net.royal.spring.framework.web.dao.impl.GenericoDaoImpl;

@Repository
public class FrecuenciaMensualDaoImpl extends GenericoDaoImpl<FrecuenciaMensual, FrecuenciaMensualPk> {

	private static final long serialVersionUID = 1L;

	public FrecuenciaMensualDaoImpl() {
		super("frecuenciamensual");
	}

	//@Resource(name = "sessionFactory")
	public void asignarSessionFactory(SessionFactory factory) {
		super.setSessionFactory(factory);
	}

	public FrecuenciaMensual obtenerPorId(String pidFrecuenciaMensual) {
		return obtenerPorId(new FrecuenciaMensualPk( pidFrecuenciaMensual));
	}

	public FrecuenciaMensual coreInsertar(FrecuenciaMensual bean) {
		// TODO FrecuenciaMensual.Insertar Datos
		
		this.registrar(bean);
		return bean;
	}

	public FrecuenciaMensual coreInsertar(SeguridadUsuarioActual usuarioActual, FrecuenciaMensual bean, String estado) {
		if (UString.estaVacio(estado))
			estado = ConstanteEstadoGenerico.ACTIVO;
		bean.setEstado(estado);
		bean.setCreacionTerminal(usuarioActual.getDireccionIp());
		bean.setCreacionFecha(new Date());
		bean.setCreacionUsuario(usuarioActual.getUsuario());
		this.registrar(bean);
		return bean;
	}

	public FrecuenciaMensual coreActualizar(SeguridadUsuarioActual usuarioActual, FrecuenciaMensual bean, String estado) {
		if (UString.estaVacio(estado))
			estado = ConstanteEstadoGenerico.ACTIVO;
		bean.setEstado(estado);
		bean.setModificacionTerminal(usuarioActual.getDireccionIp());
		bean.setModificacionFecha(new Date());
		bean.setModificacionUsuario(usuarioActual.getUsuario());
		this.actualizar(bean);
		return bean;
	}

	public FrecuenciaMensual coreActualizar(FrecuenciaMensual bean) {
		this.actualizar(bean);
		return bean;
	}

}
