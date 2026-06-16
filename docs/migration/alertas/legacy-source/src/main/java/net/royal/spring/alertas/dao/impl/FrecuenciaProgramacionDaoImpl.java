package net.royal.spring.alertas.dao.impl;

import java.util.Date;

import jakarta.annotation.Resource;

import org.hibernate.SessionFactory;
import org.springframework.stereotype.Repository;

import net.royal.spring.alertas.dominio.FrecuenciaProgramacion;
import net.royal.spring.alertas.dominio.FrecuenciaProgramacionPk;
import net.royal.spring.framework.constante.ConstanteEstadoGenerico;
import net.royal.spring.framework.modelo.seguridad.SeguridadUsuarioActual;
import net.royal.spring.framework.util.UString;
import net.royal.spring.framework.web.dao.impl.GenericoDaoImpl;

@Repository
public class FrecuenciaProgramacionDaoImpl extends GenericoDaoImpl<FrecuenciaProgramacion, FrecuenciaProgramacionPk> {

	private static final long serialVersionUID = 1L;

	public FrecuenciaProgramacionDaoImpl() {
		super("frecuenciaprogramacion");
	}

	//@Resource(name = "sessionFactory")
	public void asignarSessionFactory(SessionFactory factory) {
		super.setSessionFactory(factory);
	}

	public FrecuenciaProgramacion obtenerPorId(String pidFrecuenciaProgramacion) {
		return obtenerPorId(new FrecuenciaProgramacionPk( pidFrecuenciaProgramacion));
	}

	public FrecuenciaProgramacion coreInsertar(FrecuenciaProgramacion bean) {
		// TODO FrecuenciaProgramacion.Insertar Datos
		
		this.registrar(bean);
		return bean;
	}

	public FrecuenciaProgramacion coreInsertar(SeguridadUsuarioActual usuarioActual, FrecuenciaProgramacion bean, String estado) {
		if (UString.estaVacio(estado))
			estado = ConstanteEstadoGenerico.ACTIVO;
		bean.setEstado(estado);
		bean.setCreacionTerminal(usuarioActual.getDireccionIp());
		bean.setCreacionFecha(new Date());
		bean.setCreacionUsuario(usuarioActual.getUsuario());
		this.registrar(bean);
		return bean;
	}

	public FrecuenciaProgramacion coreActualizar(SeguridadUsuarioActual usuarioActual, FrecuenciaProgramacion bean, String estado) {
		if (UString.estaVacio(estado))
			estado = ConstanteEstadoGenerico.ACTIVO;
		bean.setEstado(estado);
		bean.setModificacionTerminal(usuarioActual.getDireccionIp());
		bean.setModificacionFecha(new Date());
		bean.setModificacionUsuario(usuarioActual.getUsuario());
		this.actualizar(bean);
		return bean;
	}

	public FrecuenciaProgramacion coreActualizar(FrecuenciaProgramacion bean) {
		this.actualizar(bean);
		return bean;
	}

}
