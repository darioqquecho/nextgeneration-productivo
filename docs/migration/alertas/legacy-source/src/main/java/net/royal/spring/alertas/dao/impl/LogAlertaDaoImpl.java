package net.royal.spring.alertas.dao.impl;

import java.util.Date;
import java.util.List;

import jakarta.annotation.Resource;

import org.hibernate.SessionFactory;
import org.springframework.stereotype.Repository;

import net.royal.spring.alertas.dominio.LogAlerta;
import net.royal.spring.alertas.dominio.LogAlertaPk;
import net.royal.spring.alertas.dominio.dto.DtoAlertaLogPendiente;
import net.royal.spring.framework.constante.ConstanteEstadoGenerico;
import net.royal.spring.framework.modelo.seguridad.SeguridadUsuarioActual;
import net.royal.spring.framework.util.UString;
import net.royal.spring.framework.web.dao.impl.GenericoDaoImpl;

@Repository
public class LogAlertaDaoImpl extends GenericoDaoImpl<LogAlerta, LogAlertaPk> {

	private static final long serialVersionUID = 1L;

	public LogAlertaDaoImpl() {
		super("logalerta");
	}

	//@Resource(name = "sessionFactory")
	public void asignarSessionFactory(SessionFactory factory) {
		super.setSessionFactory(factory);
	}

	public LogAlerta obtenerPorId(Integer pidLogAlerta) {
		return obtenerPorId(new LogAlertaPk(pidLogAlerta));
	}

	public LogAlerta coreInsertar(LogAlerta bean) {
		// TODO LogAlerta.Insertar Datos

		this.registrar(bean);
		return bean;
	}

	public LogAlerta coreInsertar(SeguridadUsuarioActual usuarioActual, LogAlerta bean, String estado) {
		if (UString.estaVacio(estado))
			estado = ConstanteEstadoGenerico.ACTIVO;
		bean.setEstado(estado);
		bean.setCreacionTerminal(usuarioActual.getDireccionIp());
		bean.setCreacionFecha(new Date());
		bean.setCreacionUsuario(usuarioActual.getUsuario());
		this.registrar(bean);
		return bean;
	}

	public LogAlerta coreActualizar(SeguridadUsuarioActual usuarioActual, LogAlerta bean, String estado) {
		if (UString.estaVacio(estado))
			estado = ConstanteEstadoGenerico.ACTIVO;
		bean.setEstado(estado);
		bean.setModificacionTerminal(usuarioActual.getDireccionIp());
		bean.setModificacionFecha(new Date());
		bean.setModificacionUsuario(usuarioActual.getUsuario());
		this.actualizar(bean);
		return bean;
	}

	public LogAlerta coreActualizar(LogAlerta bean) {
		this.actualizar(bean);
		return bean;
	}

	@SuppressWarnings({ "unchecked", "rawtypes" })
	public List<DtoAlertaLogPendiente> listarReglasPendientes() {
		List lista = this.listarPorQuery(DtoAlertaLogPendiente.class, "logalerta.listarReglasPendientes");
		return (List<DtoAlertaLogPendiente>) lista;
	}

}
