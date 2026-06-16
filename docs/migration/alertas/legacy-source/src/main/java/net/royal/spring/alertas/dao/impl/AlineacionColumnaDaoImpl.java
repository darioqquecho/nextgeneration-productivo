package net.royal.spring.alertas.dao.impl;

import java.util.Date;

import jakarta.annotation.Resource;

import org.hibernate.SessionFactory;
import org.springframework.stereotype.Repository;

import net.royal.spring.alertas.dominio.AlineacionColumna;
import net.royal.spring.alertas.dominio.AlineacionColumnaPk;
import net.royal.spring.framework.constante.ConstanteEstadoGenerico;
import net.royal.spring.framework.modelo.seguridad.SeguridadUsuarioActual;
import net.royal.spring.framework.util.UString;
import net.royal.spring.framework.web.dao.impl.GenericoDaoImpl;

@Repository
public class AlineacionColumnaDaoImpl extends GenericoDaoImpl<AlineacionColumna, AlineacionColumnaPk> {

	private static final long serialVersionUID = 1L;

	public AlineacionColumnaDaoImpl() {
		super("alineacioncolumna");
	}

	//@Resource(name = "sessionFactory")
	public void asignarSessionFactory(SessionFactory factory) {
		super.setSessionFactory(factory);
	}

	public AlineacionColumna obtenerPorId(String pidAlineacionColumna) {
		return obtenerPorId(new AlineacionColumnaPk( pidAlineacionColumna));
	}

	public AlineacionColumna coreInsertar(AlineacionColumna bean) {
		// TODO AlineacionColumna.Insertar Datos
		
		this.registrar(bean);
		return bean;
	}

	public AlineacionColumna coreInsertar(SeguridadUsuarioActual usuarioActual, AlineacionColumna bean, String estado) {
		if (UString.estaVacio(estado))
			estado = ConstanteEstadoGenerico.ACTIVO;
		bean.setEstado(estado);
		bean.setCreacionTerminal(usuarioActual.getDireccionIp());
		bean.setCreacionFecha(new Date());
		bean.setCreacionUsuario(usuarioActual.getUsuario());
		this.registrar(bean);
		return bean;
	}

	public AlineacionColumna coreActualizar(SeguridadUsuarioActual usuarioActual, AlineacionColumna bean, String estado) {
		if (UString.estaVacio(estado))
			estado = ConstanteEstadoGenerico.ACTIVO;
		bean.setEstado(estado);
		bean.setModificacionTerminal(usuarioActual.getDireccionIp());
		bean.setModificacionFecha(new Date());
		bean.setModificacionUsuario(usuarioActual.getUsuario());
		this.actualizar(bean);
		return bean;
	}

	public AlineacionColumna coreActualizar(AlineacionColumna bean) {
		this.actualizar(bean);
		return bean;
	}

}
