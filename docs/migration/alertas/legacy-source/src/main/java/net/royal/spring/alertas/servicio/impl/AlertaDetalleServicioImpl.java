package net.royal.spring.alertas.servicio.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import net.royal.spring.alertas.dao.impl.AlertaDetalleDaoImpl;
import net.royal.spring.alertas.dominio.AlertaDetalle;
import net.royal.spring.alertas.dominio.AlertaDetallePk;
import net.royal.spring.alertas.servicio.validar.AlertaDetalleServicioValidar;
import net.royal.spring.framework.core.UException;
import net.royal.spring.framework.modelo.generico.DominioMensajeUsuario;
import net.royal.spring.framework.modelo.seguridad.SeguridadUsuarioActual;
import net.royal.spring.framework.web.servicio.impl.GenericoServicioImpl;

@Service (value = "BeanServicioAlertaDetalle")
public class AlertaDetalleServicioImpl extends GenericoServicioImpl {
	public static String SPRING_NOMBRE = "BeanServicioAlertaDetalle";

	@Autowired
	private AlertaDetalleDaoImpl alertaDetalleDao;

	@Autowired
	private AlertaDetalleServicioValidar validar;

	@Transactional
	public AlertaDetalle coreInsertar(SeguridadUsuarioActual usuarioActual,AlertaDetalle alertaDetalle) throws UException {
		// valores por defecto - preparando objeto
		alertaDetalle = validar.prepararInsertar(usuarioActual, alertaDetalle);
		
		// validaciones de negocio
		List<DominioMensajeUsuario> lst = validar.coreInsertar(usuarioActual, alertaDetalle);
		if (!lst.isEmpty())
			throw new UException(lst);
		
		// transaccion
		return alertaDetalleDao.coreInsertar(alertaDetalle);
	}

	@Transactional
	public AlertaDetalle coreActualizar(SeguridadUsuarioActual usuarioActual, AlertaDetalle alertaDetalle) throws UException {
		// valores por defecto - preparando objeto
		alertaDetalle = validar.prepararActualizar(usuarioActual, alertaDetalle);
		
		// validaciones de negocio
		List<DominioMensajeUsuario> lst = validar.coreActualizar(usuarioActual, alertaDetalle);
		if (!lst.isEmpty())
			throw new UException(lst);
		
		// transaccion
	    alertaDetalle = alertaDetalleDao.coreActualizar(alertaDetalle);
		return alertaDetalle;
	}

	@Transactional
	public AlertaDetalle coreAnular(SeguridadUsuarioActual usuarioActual, AlertaDetalle alertaDetalle) throws UException {
		// valores por defecto - preparando objeto
		alertaDetalle = validar.prepararAnular(usuarioActual, alertaDetalle);
		
		// validaciones de negocio
		List<DominioMensajeUsuario> lst = validar.coreAnular(usuarioActual, alertaDetalle);
		if (!lst.isEmpty())
			throw new UException(lst);
		
		// transaccion
		return alertaDetalleDao.coreActualizar(alertaDetalle);
	}

	public AlertaDetalle coreAnular(SeguridadUsuarioActual usuarioActual, AlertaDetallePk pk) throws UException {
		AlertaDetalle bean = alertaDetalleDao.obtenerPorId(pk);
		return coreAnular(usuarioActual,bean);
	}

	public AlertaDetalle coreAnular(SeguridadUsuarioActual usuarioActual, Integer pidAlerta,Integer pidDetalle) throws UException {
		return coreAnular(usuarioActual,new AlertaDetallePk( pidAlerta, pidDetalle));
	}

	@Transactional
	public void coreEliminar(SeguridadUsuarioActual usuarioActual, AlertaDetalle alertaDetalle) throws UException {
		// valores por defecto - preparando objeto
		alertaDetalle = validar.prepararEliminar(usuarioActual, alertaDetalle);
		
		// validaciones de negocio
		List<DominioMensajeUsuario> lst = validar.coreEliminar(usuarioActual, alertaDetalle);
		if (!lst.isEmpty())
			throw new UException(lst);
		
		// transaccion
		alertaDetalleDao.eliminar(alertaDetalle);
	}

	public void coreEliminar(SeguridadUsuarioActual usuarioActual, AlertaDetallePk pk) throws UException {
		AlertaDetalle alertaDetalle = alertaDetalleDao.obtenerPorId(pk);
		coreEliminar(usuarioActual,alertaDetalle);
	}

	public void coreEliminar(SeguridadUsuarioActual usuarioActual, Integer pidAlerta,Integer pidDetalle) throws UException {
		coreEliminar(usuarioActual,new AlertaDetallePk( pidAlerta, pidDetalle));
	}

}
