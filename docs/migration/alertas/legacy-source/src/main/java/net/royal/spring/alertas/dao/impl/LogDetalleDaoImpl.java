package net.royal.spring.alertas.dao.impl;

import java.util.Date;

import jakarta.annotation.Resource;

import org.hibernate.SessionFactory;
import org.springframework.stereotype.Repository;

import net.royal.spring.alertas.dominio.LogDetalle;
import net.royal.spring.alertas.dominio.LogDetallePk;
import net.royal.spring.framework.constante.ConstanteEstadoGenerico;
import net.royal.spring.framework.modelo.seguridad.SeguridadUsuarioActual;
import net.royal.spring.framework.util.UString;
import net.royal.spring.framework.web.dao.impl.GenericoDaoImpl;

@Repository
public class LogDetalleDaoImpl extends GenericoDaoImpl<LogDetalle, LogDetallePk> {

	private static final long serialVersionUID = 1L;

	public LogDetalleDaoImpl() {
		super("logdetalle");
	}

	//@Resource(name = "sessionFactory")
	public void asignarSessionFactory(SessionFactory factory) {
		super.setSessionFactory(factory);
	}

	public LogDetalle obtenerPorId(Integer pidLogAlerta,Integer pnroRegistro,String pnombreCampo) {
		return obtenerPorId(new LogDetallePk( pidLogAlerta, pnroRegistro, pnombreCampo));
	}

	public LogDetalle coreInsertar(LogDetalle bean) {
		// TODO LogDetalle.Insertar Datos
		
		this.registrar(bean);
		return bean;
	}

	public LogDetalle coreInsertar(SeguridadUsuarioActual usuarioActual, LogDetalle bean, String estado) {
		if (UString.estaVacio(estado))
			estado = ConstanteEstadoGenerico.ACTIVO;
		bean.setEstado(estado);
		bean.setCreacionTerminal(usuarioActual.getDireccionIp());
		bean.setCreacionFecha(new Date());
		bean.setCreacionUsuario(usuarioActual.getUsuario());
		this.registrar(bean);
		return bean;
	}

	public LogDetalle coreActualizar(SeguridadUsuarioActual usuarioActual, LogDetalle bean, String estado) {
		if (UString.estaVacio(estado))
			estado = ConstanteEstadoGenerico.ACTIVO;
		bean.setEstado(estado);
		bean.setModificacionTerminal(usuarioActual.getDireccionIp());
		bean.setModificacionFecha(new Date());
		bean.setModificacionUsuario(usuarioActual.getUsuario());
		this.actualizar(bean);
		return bean;
	}

	public LogDetalle coreActualizar(LogDetalle bean) {
		this.actualizar(bean);
		return bean;
	}

}
