package net.royal.spring.alertas.servicio.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import net.royal.spring.alertas.dao.impl.LogDetalleDaoImpl;
import net.royal.spring.alertas.dominio.LogDetalle;
import net.royal.spring.alertas.dominio.LogDetallePk;
import net.royal.spring.alertas.servicio.validar.LogDetalleServicioValidar;
import net.royal.spring.framework.core.UException;
import net.royal.spring.framework.modelo.generico.DominioMensajeUsuario;
import net.royal.spring.framework.modelo.seguridad.SeguridadUsuarioActual;
import net.royal.spring.framework.web.servicio.impl.GenericoServicioImpl;

@Service (value = "BeanServicioLogDetalle")
public class LogDetalleServicioImpl extends GenericoServicioImpl {
	public static String SPRING_NOMBRE = "BeanServicioLogDetalle";

	@Autowired
	private LogDetalleDaoImpl logDetalleDao;

	@Autowired
	private LogDetalleServicioValidar validar;

	@Transactional
	public LogDetalle coreInsertar(SeguridadUsuarioActual usuarioActual,LogDetalle logDetalle) throws UException {
		// valores por defecto - preparando objeto
		logDetalle = validar.prepararInsertar(usuarioActual, logDetalle);
		
		// validaciones de negocio
		List<DominioMensajeUsuario> lst = validar.coreInsertar(usuarioActual, logDetalle);
		if (!lst.isEmpty())
			throw new UException(lst);
		
		// transaccion
		return logDetalleDao.coreInsertar(logDetalle);
	}

	@Transactional
	public LogDetalle coreActualizar(SeguridadUsuarioActual usuarioActual, LogDetalle logDetalle) throws UException {
		// valores por defecto - preparando objeto
		logDetalle = validar.prepararActualizar(usuarioActual, logDetalle);
		
		// validaciones de negocio
		List<DominioMensajeUsuario> lst = validar.coreActualizar(usuarioActual, logDetalle);
		if (!lst.isEmpty())
			throw new UException(lst);
		
		// transaccion
	    logDetalle = logDetalleDao.coreActualizar(logDetalle);
		return logDetalle;
	}

	@Transactional
	public LogDetalle coreAnular(SeguridadUsuarioActual usuarioActual, LogDetalle logDetalle) throws UException {
		// valores por defecto - preparando objeto
		logDetalle = validar.prepararAnular(usuarioActual, logDetalle);
		
		// validaciones de negocio
		List<DominioMensajeUsuario> lst = validar.coreAnular(usuarioActual, logDetalle);
		if (!lst.isEmpty())
			throw new UException(lst);
		
		// transaccion
		return logDetalleDao.coreActualizar(logDetalle);
	}

	public LogDetalle coreAnular(SeguridadUsuarioActual usuarioActual, LogDetallePk pk) throws UException {
		LogDetalle bean = logDetalleDao.obtenerPorId(pk);
		return coreAnular(usuarioActual,bean);
	}

	public LogDetalle coreAnular(SeguridadUsuarioActual usuarioActual, Integer pidLogAlerta,Integer pnroRegistro,String pnombreCampo) throws UException {
		return coreAnular(usuarioActual,new LogDetallePk( pidLogAlerta, pnroRegistro, pnombreCampo));
	}

	@Transactional
	public void coreEliminar(SeguridadUsuarioActual usuarioActual, LogDetalle logDetalle) throws UException {
		// valores por defecto - preparando objeto
		logDetalle = validar.prepararEliminar(usuarioActual, logDetalle);
		
		// validaciones de negocio
		List<DominioMensajeUsuario> lst = validar.coreEliminar(usuarioActual, logDetalle);
		if (!lst.isEmpty())
			throw new UException(lst);
		
		// transaccion
		logDetalleDao.eliminar(logDetalle);
	}

	public void coreEliminar(SeguridadUsuarioActual usuarioActual, LogDetallePk pk) throws UException {
		LogDetalle logDetalle = logDetalleDao.obtenerPorId(pk);
		coreEliminar(usuarioActual,logDetalle);
	}

	public void coreEliminar(SeguridadUsuarioActual usuarioActual, Integer pidLogAlerta,Integer pnroRegistro,String pnombreCampo) throws UException {
		coreEliminar(usuarioActual,new LogDetallePk( pidLogAlerta, pnroRegistro, pnombreCampo));
	}

}
