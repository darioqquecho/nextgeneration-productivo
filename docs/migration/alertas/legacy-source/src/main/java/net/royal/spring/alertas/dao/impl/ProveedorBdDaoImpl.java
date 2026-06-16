package net.royal.spring.alertas.dao.impl;

import java.util.Date;

import jakarta.annotation.Resource;

import org.hibernate.SessionFactory;
import org.springframework.stereotype.Repository;

import net.royal.spring.alertas.dominio.ProveedorBd;
import net.royal.spring.alertas.dominio.ProveedorBdPk;
import net.royal.spring.framework.constante.ConstanteEstadoGenerico;
import net.royal.spring.framework.modelo.seguridad.SeguridadUsuarioActual;
import net.royal.spring.framework.util.UString;
import net.royal.spring.framework.web.dao.impl.GenericoDaoImpl;

@Repository
public class ProveedorBdDaoImpl extends GenericoDaoImpl<ProveedorBd, ProveedorBdPk> {

	private static final long serialVersionUID = 1L;

	public ProveedorBdDaoImpl() {
		super("proveedorbd");
	}

	//@Resource(name = "sessionFactory")
	public void asignarSessionFactory(SessionFactory factory) {
		super.setSessionFactory(factory);
	}

	public ProveedorBd obtenerPorId(String pidProveedorBd) {
		return obtenerPorId(new ProveedorBdPk( pidProveedorBd));
	}

	public ProveedorBd coreInsertar(ProveedorBd bean) {
		// TODO ProveedorBd.Insertar Datos
		
		this.registrar(bean);
		return bean;
	}

	public ProveedorBd coreInsertar(SeguridadUsuarioActual usuarioActual, ProveedorBd bean, String estado) {
		if (UString.estaVacio(estado))
			estado = ConstanteEstadoGenerico.ACTIVO;
		bean.setEstado(estado);
		bean.setCreacionTerminal(usuarioActual.getDireccionIp());
		bean.setCreacionFecha(new Date());
		bean.setCreacionUsuario(usuarioActual.getUsuario());
		this.registrar(bean);
		return bean;
	}

	public ProveedorBd coreActualizar(SeguridadUsuarioActual usuarioActual, ProveedorBd bean, String estado) {
		if (UString.estaVacio(estado))
			estado = ConstanteEstadoGenerico.ACTIVO;
		bean.setEstado(estado);
		bean.setModificacionTerminal(usuarioActual.getDireccionIp());
		bean.setModificacionFecha(new Date());
		bean.setModificacionUsuario(usuarioActual.getUsuario());
		this.actualizar(bean);
		return bean;
	}

	public ProveedorBd coreActualizar(ProveedorBd bean) {
		this.actualizar(bean);
		return bean;
	}

}
