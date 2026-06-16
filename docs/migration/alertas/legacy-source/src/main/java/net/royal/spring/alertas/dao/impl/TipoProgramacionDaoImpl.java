package net.royal.spring.alertas.dao.impl;

import java.util.Date;

import jakarta.annotation.Resource;

import org.hibernate.SessionFactory;
import org.springframework.stereotype.Repository;

import net.royal.spring.alertas.dominio.TipoProgramacion;
import net.royal.spring.alertas.dominio.TipoProgramacionPk;
import net.royal.spring.framework.constante.ConstanteEstadoGenerico;
import net.royal.spring.framework.modelo.seguridad.SeguridadUsuarioActual;
import net.royal.spring.framework.util.UString;
import net.royal.spring.framework.web.dao.impl.GenericoDaoImpl;

@Repository
public class TipoProgramacionDaoImpl extends GenericoDaoImpl<TipoProgramacion, TipoProgramacionPk> {

	private static final long serialVersionUID = 1L;

	public TipoProgramacionDaoImpl() {
		super("tipoprogramacion");
	}

	//@Resource(name = "sessionFactory")
	public void asignarSessionFactory(SessionFactory factory) {
		super.setSessionFactory(factory);
	}

	public TipoProgramacion obtenerPorId(String pidTipoProgramacion) {
		return obtenerPorId(new TipoProgramacionPk( pidTipoProgramacion));
	}

	public TipoProgramacion coreInsertar(TipoProgramacion bean) {
		// TODO TipoProgramacion.Insertar Datos
		
		this.registrar(bean);
		return bean;
	}

	public TipoProgramacion coreInsertar(SeguridadUsuarioActual usuarioActual, TipoProgramacion bean, String estado) {
		if (UString.estaVacio(estado))
			estado = ConstanteEstadoGenerico.ACTIVO;
		bean.setEstado(estado);
		bean.setCreacionTerminal(usuarioActual.getDireccionIp());
		bean.setCreacionFecha(new Date());
		bean.setCreacionUsuario(usuarioActual.getUsuario());
		this.registrar(bean);
		return bean;
	}

	public TipoProgramacion coreActualizar(SeguridadUsuarioActual usuarioActual, TipoProgramacion bean, String estado) {
		if (UString.estaVacio(estado))
			estado = ConstanteEstadoGenerico.ACTIVO;
		bean.setEstado(estado);
		bean.setModificacionTerminal(usuarioActual.getDireccionIp());
		bean.setModificacionFecha(new Date());
		bean.setModificacionUsuario(usuarioActual.getUsuario());
		this.actualizar(bean);
		return bean;
	}

	public TipoProgramacion coreActualizar(TipoProgramacion bean) {
		this.actualizar(bean);
		return bean;
	}

}
