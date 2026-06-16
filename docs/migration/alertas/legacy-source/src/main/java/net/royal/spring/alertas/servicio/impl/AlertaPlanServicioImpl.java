package net.royal.spring.alertas.servicio.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import net.royal.spring.alertas.dao.impl.AlertaPlanDaoImpl;
import net.royal.spring.alertas.dominio.AlertaPlan;
import net.royal.spring.alertas.dominio.AlertaPlanPk;
import net.royal.spring.alertas.servicio.validar.AlertaPlanServicioValidar;
import net.royal.spring.framework.core.UException;
import net.royal.spring.framework.modelo.generico.DominioMensajeUsuario;
import net.royal.spring.framework.modelo.seguridad.SeguridadUsuarioActual;
import net.royal.spring.framework.web.servicio.impl.GenericoServicioImpl;

@Service (value = "BeanServicioAlertaPlan")
public class AlertaPlanServicioImpl extends GenericoServicioImpl {
	public static String SPRING_NOMBRE = "BeanServicioAlertaPlan";

	@Autowired
	private AlertaPlanDaoImpl alertaPlanDao;

	@Autowired
	private AlertaPlanServicioValidar validar;

	@Transactional
	public AlertaPlan coreInsertar(SeguridadUsuarioActual usuarioActual,AlertaPlan alertaPlan) throws UException {
		// valores por defecto - preparando objeto
		alertaPlan = validar.prepararInsertar(usuarioActual, alertaPlan);
		
		// validaciones de negocio
		List<DominioMensajeUsuario> lst = validar.coreInsertar(usuarioActual, alertaPlan);
		if (!lst.isEmpty())
			throw new UException(lst);
		
		// transaccion
		return alertaPlanDao.coreInsertar(alertaPlan);
	}

	@Transactional
	public AlertaPlan coreActualizar(SeguridadUsuarioActual usuarioActual, AlertaPlan alertaPlan) throws UException {
		// valores por defecto - preparando objeto
		alertaPlan = validar.prepararActualizar(usuarioActual, alertaPlan);
		
		// validaciones de negocio
		List<DominioMensajeUsuario> lst = validar.coreActualizar(usuarioActual, alertaPlan);
		if (!lst.isEmpty())
			throw new UException(lst);
		
		// transaccion
	    alertaPlan = alertaPlanDao.coreActualizar(alertaPlan);
		return alertaPlan;
	}

	@Transactional
	public AlertaPlan coreAnular(SeguridadUsuarioActual usuarioActual, AlertaPlan alertaPlan) throws UException {
		// valores por defecto - preparando objeto
		alertaPlan = validar.prepararAnular(usuarioActual, alertaPlan);
		
		// validaciones de negocio
		List<DominioMensajeUsuario> lst = validar.coreAnular(usuarioActual, alertaPlan);
		if (!lst.isEmpty())
			throw new UException(lst);
		
		// transaccion
		return alertaPlanDao.coreActualizar(alertaPlan);
	}

	public AlertaPlan coreAnular(SeguridadUsuarioActual usuarioActual, AlertaPlanPk pk) throws UException {
		AlertaPlan bean = alertaPlanDao.obtenerPorId(pk);
		return coreAnular(usuarioActual,bean);
	}

	public AlertaPlan coreAnular(SeguridadUsuarioActual usuarioActual, Integer pidAlerta,Integer pidPlan) throws UException {
		return coreAnular(usuarioActual,new AlertaPlanPk( pidAlerta, pidPlan));
	}

	@Transactional
	public void coreEliminar(SeguridadUsuarioActual usuarioActual, AlertaPlan alertaPlan) throws UException {
		// valores por defecto - preparando objeto
		alertaPlan = validar.prepararEliminar(usuarioActual, alertaPlan);
		
		// validaciones de negocio
		List<DominioMensajeUsuario> lst = validar.coreEliminar(usuarioActual, alertaPlan);
		if (!lst.isEmpty())
			throw new UException(lst);
		
		// transaccion
		alertaPlanDao.eliminar(alertaPlan);
	}

	public void coreEliminar(SeguridadUsuarioActual usuarioActual, AlertaPlanPk pk) throws UException {
		AlertaPlan alertaPlan = alertaPlanDao.obtenerPorId(pk);
		coreEliminar(usuarioActual,alertaPlan);
	}

	public void coreEliminar(SeguridadUsuarioActual usuarioActual, Integer pidAlerta,Integer pidPlan) throws UException {
		coreEliminar(usuarioActual,new AlertaPlanPk( pidAlerta, pidPlan));
	}

}
