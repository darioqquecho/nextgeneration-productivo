package net.royal.spring.alertas.servicio.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import net.royal.spring.alertas.dao.impl.AlertaDestinoDaoImpl;
import net.royal.spring.alertas.dominio.AlertaDestino;
import net.royal.spring.alertas.dominio.AlertaDestinoPk;
import net.royal.spring.alertas.servicio.validar.AlertaDestinoServicioValidar;
import net.royal.spring.framework.core.UException;
import net.royal.spring.framework.modelo.generico.DominioMensajeUsuario;
import net.royal.spring.framework.modelo.seguridad.SeguridadUsuarioActual;
import net.royal.spring.framework.web.servicio.impl.GenericoServicioImpl;

@Service (value = "BeanServicioAlertaDestino")
public class AlertaDestinoServicioImpl extends GenericoServicioImpl {
	public static String SPRING_NOMBRE = "BeanServicioAlertaDestino";

	@Autowired
	private AlertaDestinoDaoImpl alertaDestinoDao;

	@Autowired
	private AlertaDestinoServicioValidar validar;

	@Transactional
	public AlertaDestino coreInsertar(SeguridadUsuarioActual usuarioActual,AlertaDestino alertaDestino) throws UException {
		// valores por defecto - preparando objeto
		alertaDestino = validar.prepararInsertar(usuarioActual, alertaDestino);
		
		// validaciones de negocio
		List<DominioMensajeUsuario> lst = validar.coreInsertar(usuarioActual, alertaDestino);
		if (!lst.isEmpty())
			throw new UException(lst);
		
		// transaccion
		return alertaDestinoDao.coreInsertar(alertaDestino);
	}

	@Transactional
	public AlertaDestino coreActualizar(SeguridadUsuarioActual usuarioActual, AlertaDestino alertaDestino) throws UException {
		// valores por defecto - preparando objeto
		alertaDestino = validar.prepararActualizar(usuarioActual, alertaDestino);
		
		// validaciones de negocio
		List<DominioMensajeUsuario> lst = validar.coreActualizar(usuarioActual, alertaDestino);
		if (!lst.isEmpty())
			throw new UException(lst);
		
		// transaccion
	    alertaDestino = alertaDestinoDao.coreActualizar(alertaDestino);
		return alertaDestino;
	}

	@Transactional
	public AlertaDestino coreAnular(SeguridadUsuarioActual usuarioActual, AlertaDestino alertaDestino) throws UException {
		// valores por defecto - preparando objeto
		alertaDestino = validar.prepararAnular(usuarioActual, alertaDestino);
		
		// validaciones de negocio
		List<DominioMensajeUsuario> lst = validar.coreAnular(usuarioActual, alertaDestino);
		if (!lst.isEmpty())
			throw new UException(lst);
		
		// transaccion
		return alertaDestinoDao.coreActualizar(alertaDestino);
	}

	public AlertaDestino coreAnular(SeguridadUsuarioActual usuarioActual, AlertaDestinoPk pk) throws UException {
		AlertaDestino bean = alertaDestinoDao.obtenerPorId(pk);
		return coreAnular(usuarioActual,bean);
	}

	public AlertaDestino coreAnular(SeguridadUsuarioActual usuarioActual, Integer pidAlerta,Integer pidDestino) throws UException {
		return coreAnular(usuarioActual,new AlertaDestinoPk( pidAlerta, pidDestino));
	}

	@Transactional
	public void coreEliminar(SeguridadUsuarioActual usuarioActual, AlertaDestino alertaDestino) throws UException {
		// valores por defecto - preparando objeto
		alertaDestino = validar.prepararEliminar(usuarioActual, alertaDestino);
		
		// validaciones de negocio
		List<DominioMensajeUsuario> lst = validar.coreEliminar(usuarioActual, alertaDestino);
		if (!lst.isEmpty())
			throw new UException(lst);
		
		// transaccion
		alertaDestinoDao.eliminar(alertaDestino);
	}

	public void coreEliminar(SeguridadUsuarioActual usuarioActual, AlertaDestinoPk pk) throws UException {
		AlertaDestino alertaDestino = alertaDestinoDao.obtenerPorId(pk);
		coreEliminar(usuarioActual,alertaDestino);
	}

	public void coreEliminar(SeguridadUsuarioActual usuarioActual, Integer pidAlerta,Integer pidDestino) throws UException {
		coreEliminar(usuarioActual,new AlertaDestinoPk( pidAlerta, pidDestino));
	}

}
