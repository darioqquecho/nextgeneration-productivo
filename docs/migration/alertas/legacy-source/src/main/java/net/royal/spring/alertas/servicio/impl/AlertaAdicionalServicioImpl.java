package net.royal.spring.alertas.servicio.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import net.royal.spring.alertas.dao.impl.AlertaAdicionalDaoImpl;
import net.royal.spring.alertas.dominio.AlertaAdicional;
import net.royal.spring.alertas.dominio.AlertaAdicionalPk;
import net.royal.spring.alertas.servicio.validar.AlertaAdicionalServicioValidar;
import net.royal.spring.framework.core.UException;
import net.royal.spring.framework.modelo.generico.DominioMensajeUsuario;
import net.royal.spring.framework.modelo.seguridad.SeguridadUsuarioActual;
import net.royal.spring.framework.web.servicio.impl.GenericoServicioImpl;

@Service (value = "BeanServicioAlertaAdicional")
public class AlertaAdicionalServicioImpl extends GenericoServicioImpl {
	public static String SPRING_NOMBRE = "BeanServicioAlertaAdicional";

	@Autowired
	private AlertaAdicionalDaoImpl alertaAdicionalDao;

	@Autowired
	private AlertaAdicionalServicioValidar validar;

	@Transactional
	public AlertaAdicional coreInsertar(SeguridadUsuarioActual usuarioActual,AlertaAdicional alertaAdicional) throws UException {
		// valores por defecto - preparando objeto
		alertaAdicional = validar.prepararInsertar(usuarioActual, alertaAdicional);
		
		// validaciones de negocio
		List<DominioMensajeUsuario> lst = validar.coreInsertar(usuarioActual, alertaAdicional);
		if (!lst.isEmpty())
			throw new UException(lst);
		
		// transaccion
		return alertaAdicionalDao.coreInsertar(alertaAdicional);
	}

	@Transactional
	public AlertaAdicional coreActualizar(SeguridadUsuarioActual usuarioActual, AlertaAdicional alertaAdicional) throws UException {
		// valores por defecto - preparando objeto
		alertaAdicional = validar.prepararActualizar(usuarioActual, alertaAdicional);
		
		// validaciones de negocio
		List<DominioMensajeUsuario> lst = validar.coreActualizar(usuarioActual, alertaAdicional);
		if (!lst.isEmpty())
			throw new UException(lst);
		
		// transaccion
	    alertaAdicional = alertaAdicionalDao.coreActualizar(alertaAdicional);
		return alertaAdicional;
	}

	@Transactional
	public AlertaAdicional coreAnular(SeguridadUsuarioActual usuarioActual, AlertaAdicional alertaAdicional) throws UException {
		// valores por defecto - preparando objeto
		alertaAdicional = validar.prepararAnular(usuarioActual, alertaAdicional);
		
		// validaciones de negocio
		List<DominioMensajeUsuario> lst = validar.coreAnular(usuarioActual, alertaAdicional);
		if (!lst.isEmpty())
			throw new UException(lst);
		
		// transaccion
		return alertaAdicionalDao.coreActualizar(alertaAdicional);
	}

	public AlertaAdicional coreAnular(SeguridadUsuarioActual usuarioActual, AlertaAdicionalPk pk) throws UException {
		AlertaAdicional bean = alertaAdicionalDao.obtenerPorId(pk);
		return coreAnular(usuarioActual,bean);
	}

	public AlertaAdicional coreAnular(SeguridadUsuarioActual usuarioActual, Integer pidAlerta,Integer pidAdicional) throws UException {
		return coreAnular(usuarioActual,new AlertaAdicionalPk( pidAlerta, pidAdicional));
	}

	@Transactional
	public void coreEliminar(SeguridadUsuarioActual usuarioActual, AlertaAdicional alertaAdicional) throws UException {
		// valores por defecto - preparando objeto
		alertaAdicional = validar.prepararEliminar(usuarioActual, alertaAdicional);
		
		// validaciones de negocio
		List<DominioMensajeUsuario> lst = validar.coreEliminar(usuarioActual, alertaAdicional);
		if (!lst.isEmpty())
			throw new UException(lst);
		
		// transaccion
		alertaAdicionalDao.eliminar(alertaAdicional);
	}

	public void coreEliminar(SeguridadUsuarioActual usuarioActual, AlertaAdicionalPk pk) throws UException {
		AlertaAdicional alertaAdicional = alertaAdicionalDao.obtenerPorId(pk);
		coreEliminar(usuarioActual,alertaAdicional);
	}

	public void coreEliminar(SeguridadUsuarioActual usuarioActual, Integer pidAlerta,Integer pidAdicional) throws UException {
		coreEliminar(usuarioActual,new AlertaAdicionalPk( pidAlerta, pidAdicional));
	}

}
