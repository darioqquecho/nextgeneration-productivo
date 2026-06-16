package net.royal.spring.alertas.dao.impl;

import java.util.Date;

import jakarta.annotation.Resource;

import org.hibernate.SessionFactory;
import org.springframework.stereotype.Repository;

import net.royal.spring.alertas.dominio.ConexionBd;
import net.royal.spring.alertas.dominio.ConexionBdPk;
import net.royal.spring.framework.constante.ConstanteEstadoGenerico;
import net.royal.spring.framework.modelo.seguridad.SeguridadUsuarioActual;
import net.royal.spring.framework.util.UString;
import net.royal.spring.framework.web.dao.impl.GenericoDaoImpl;

@Repository
public class ConexionBdDaoImpl extends GenericoDaoImpl<ConexionBd, ConexionBdPk> {

	private static final long serialVersionUID = 1L;

	public ConexionBdDaoImpl() {
		super("conexionbd");
	}

	//@Resource(name = "sessionFactory")
	public void asignarSessionFactory(SessionFactory factory) {
		super.setSessionFactory(factory);
	}

	public ConexionBd obtenerPorId(Integer pidConexionBd) {
		return obtenerPorId(new ConexionBdPk( pidConexionBd));
	}

	public ConexionBd coreInsertar(ConexionBd bean) {
		// TODO ConexionBd.Insertar Datos
		
		this.registrar(bean);
		return bean;
	}

	public ConexionBd coreInsertar(SeguridadUsuarioActual usuarioActual, ConexionBd bean, String estado) {
		if (UString.estaVacio(estado))
			estado = ConstanteEstadoGenerico.ACTIVO;
		bean.setEstado(estado);
		bean.setCreacionTerminal(usuarioActual.getDireccionIp());
		bean.setCreacionFecha(new Date());
		bean.setCreacionUsuario(usuarioActual.getUsuario());
		this.registrar(bean);
		return bean;
	}

	public ConexionBd coreActualizar(SeguridadUsuarioActual usuarioActual, ConexionBd bean, String estado) {
		if (UString.estaVacio(estado))
			estado = ConstanteEstadoGenerico.ACTIVO;
		bean.setEstado(estado);
		bean.setModificacionTerminal(usuarioActual.getDireccionIp());
		bean.setModificacionFecha(new Date());
		bean.setModificacionUsuario(usuarioActual.getUsuario());
		this.actualizar(bean);
		return bean;
	}

	public ConexionBd coreActualizar(ConexionBd bean) {
		this.actualizar(bean);
		return bean;
	}

}
