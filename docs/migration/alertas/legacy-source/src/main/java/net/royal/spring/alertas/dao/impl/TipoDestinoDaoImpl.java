package net.royal.spring.alertas.dao.impl;

import java.util.Date;

import jakarta.annotation.Resource;

import org.hibernate.SessionFactory;
import org.springframework.stereotype.Repository;

import net.royal.spring.alertas.dominio.TipoDestino;
import net.royal.spring.alertas.dominio.TipoDestinoPk;
import net.royal.spring.framework.constante.ConstanteEstadoGenerico;
import net.royal.spring.framework.modelo.seguridad.SeguridadUsuarioActual;
import net.royal.spring.framework.util.UString;
import net.royal.spring.framework.web.dao.impl.GenericoDaoImpl;

@Repository
public class TipoDestinoDaoImpl extends GenericoDaoImpl<TipoDestino, TipoDestinoPk> {

	private static final long serialVersionUID = 1L;

	public TipoDestinoDaoImpl() {
		super("tipodestino");
	}

	//@Resource(name = "sessionFactory")
	public void asignarSessionFactory(SessionFactory factory) {
		super.setSessionFactory(factory);
	}

	public TipoDestino obtenerPorId(String pidTipoDestino) {
		return obtenerPorId(new TipoDestinoPk( pidTipoDestino));
	}

	public TipoDestino coreInsertar(TipoDestino bean) {
		// TODO TipoDestino.Insertar Datos
		
		this.registrar(bean);
		return bean;
	}

	public TipoDestino coreInsertar(SeguridadUsuarioActual usuarioActual, TipoDestino bean, String estado) {
		if (UString.estaVacio(estado))
			estado = ConstanteEstadoGenerico.ACTIVO;
		bean.setEstado(estado);
		bean.setCreacionTerminal(usuarioActual.getDireccionIp());
		bean.setCreacionFecha(new Date());
		bean.setCreacionUsuario(usuarioActual.getUsuario());
		this.registrar(bean);
		return bean;
	}

	public TipoDestino coreActualizar(SeguridadUsuarioActual usuarioActual, TipoDestino bean, String estado) {
		if (UString.estaVacio(estado))
			estado = ConstanteEstadoGenerico.ACTIVO;
		bean.setEstado(estado);
		bean.setModificacionTerminal(usuarioActual.getDireccionIp());
		bean.setModificacionFecha(new Date());
		bean.setModificacionUsuario(usuarioActual.getUsuario());
		this.actualizar(bean);
		return bean;
	}

	public TipoDestino coreActualizar(TipoDestino bean) {
		this.actualizar(bean);
		return bean;
	}

}
