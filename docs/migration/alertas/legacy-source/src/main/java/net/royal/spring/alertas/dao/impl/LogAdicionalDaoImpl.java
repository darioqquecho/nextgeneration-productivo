package net.royal.spring.alertas.dao.impl;

import java.util.Date;

import jakarta.annotation.Resource;

import org.hibernate.SessionFactory;
import org.springframework.stereotype.Repository;

import net.royal.spring.alertas.dominio.LogAdicional;
import net.royal.spring.alertas.dominio.LogAdicionalPk;
import net.royal.spring.framework.constante.ConstanteEstadoGenerico;
import net.royal.spring.framework.modelo.seguridad.SeguridadUsuarioActual;
import net.royal.spring.framework.util.UString;
import net.royal.spring.framework.web.dao.impl.GenericoDaoImpl;

@Repository
public class LogAdicionalDaoImpl extends GenericoDaoImpl<LogAdicional, LogAdicionalPk> {

	private static final long serialVersionUID = 1L;

	public LogAdicionalDaoImpl() {
		super("logadicional");
	}

	//@Resource(name = "sessionFactory")
	public void asignarSessionFactory(SessionFactory factory) {
		super.setSessionFactory(factory);
	}

	public LogAdicional obtenerPorId(Integer pidLogAlerta,Integer pnroRegistro,String pnombreCampo) {
		return obtenerPorId(new LogAdicionalPk( pidLogAlerta, pnroRegistro, pnombreCampo));
	}

	public LogAdicional coreInsertar(LogAdicional bean) {
		// TODO LogAdicional.Insertar Datos
		
		this.registrar(bean);
		return bean;
	}

	public LogAdicional coreInsertar(SeguridadUsuarioActual usuarioActual, LogAdicional bean, String estado) {
		if (UString.estaVacio(estado))
			estado = ConstanteEstadoGenerico.ACTIVO;
		bean.setEstado(estado);
		bean.setCreacionTerminal(usuarioActual.getDireccionIp());
		bean.setCreacionFecha(new Date());
		bean.setCreacionUsuario(usuarioActual.getUsuario());
		this.registrar(bean);
		return bean;
	}

	public LogAdicional coreActualizar(SeguridadUsuarioActual usuarioActual, LogAdicional bean, String estado) {
		if (UString.estaVacio(estado))
			estado = ConstanteEstadoGenerico.ACTIVO;
		bean.setEstado(estado);
		bean.setModificacionTerminal(usuarioActual.getDireccionIp());
		bean.setModificacionFecha(new Date());
		bean.setModificacionUsuario(usuarioActual.getUsuario());
		this.actualizar(bean);
		return bean;
	}

	public LogAdicional coreActualizar(LogAdicional bean) {
		this.actualizar(bean);
		return bean;
	}

}
