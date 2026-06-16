package net.royal.spring.alertas.servicio.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import net.royal.spring.alertas.dao.impl.ReglaNegocioDetalleDaoImpl;
import net.royal.spring.alertas.dominio.ReglaNegocioDetalle;
import net.royal.spring.alertas.dominio.ReglaNegocioDetallePk;
import net.royal.spring.alertas.servicio.validar.ReglaNegocioDetalleServicioValidar;
import net.royal.spring.framework.core.UException;
import net.royal.spring.framework.modelo.generico.DominioMensajeUsuario;
import net.royal.spring.framework.modelo.seguridad.SeguridadUsuarioActual;
import net.royal.spring.framework.web.servicio.impl.GenericoServicioImpl;

@Service (value = "BeanServicioReglaNegocioDetalle")
public class ReglaNegocioDetalleServicioImpl extends GenericoServicioImpl {
	public static String SPRING_NOMBRE = "BeanServicioReglaNegocioDetalle";

	@Autowired
	private ReglaNegocioDetalleDaoImpl reglaNegocioDetalleDao;

	@Autowired
	private ReglaNegocioDetalleServicioValidar validar;

	@Transactional
	public ReglaNegocioDetalle coreInsertar(SeguridadUsuarioActual usuarioActual,ReglaNegocioDetalle reglaNegocioDetalle) throws UException {
		// valores por defecto - preparando objeto
		reglaNegocioDetalle = validar.prepararInsertar(usuarioActual, reglaNegocioDetalle);
		
		// validaciones de negocio
		List<DominioMensajeUsuario> lst = validar.coreInsertar(usuarioActual, reglaNegocioDetalle);
		if (!lst.isEmpty())
			throw new UException(lst);
		
		// transaccion
		return reglaNegocioDetalleDao.coreInsertar(reglaNegocioDetalle);
	}

	@Transactional
	public ReglaNegocioDetalle coreActualizar(SeguridadUsuarioActual usuarioActual, ReglaNegocioDetalle reglaNegocioDetalle) throws UException {
		// valores por defecto - preparando objeto
		reglaNegocioDetalle = validar.prepararActualizar(usuarioActual, reglaNegocioDetalle);
		
		// validaciones de negocio
		List<DominioMensajeUsuario> lst = validar.coreActualizar(usuarioActual, reglaNegocioDetalle);
		if (!lst.isEmpty())
			throw new UException(lst);
		
		// transaccion
	    reglaNegocioDetalle = reglaNegocioDetalleDao.coreActualizar(reglaNegocioDetalle);
		return reglaNegocioDetalle;
	}

	@Transactional
	public ReglaNegocioDetalle coreAnular(SeguridadUsuarioActual usuarioActual, ReglaNegocioDetalle reglaNegocioDetalle) throws UException {
		// valores por defecto - preparando objeto
		reglaNegocioDetalle = validar.prepararAnular(usuarioActual, reglaNegocioDetalle);
		
		// validaciones de negocio
		List<DominioMensajeUsuario> lst = validar.coreAnular(usuarioActual, reglaNegocioDetalle);
		if (!lst.isEmpty())
			throw new UException(lst);
		
		// transaccion
		return reglaNegocioDetalleDao.coreActualizar(reglaNegocioDetalle);
	}

	public ReglaNegocioDetalle coreAnular(SeguridadUsuarioActual usuarioActual, ReglaNegocioDetallePk pk) throws UException {
		ReglaNegocioDetalle bean = reglaNegocioDetalleDao.obtenerPorId(pk);
		return coreAnular(usuarioActual,bean);
	}

	public ReglaNegocioDetalle coreAnular(SeguridadUsuarioActual usuarioActual, Integer pidReglaNegocio,Integer pidDetalle) throws UException {
		return coreAnular(usuarioActual,new ReglaNegocioDetallePk( pidReglaNegocio, pidDetalle));
	}

	@Transactional
	public void coreEliminar(SeguridadUsuarioActual usuarioActual, ReglaNegocioDetalle reglaNegocioDetalle) throws UException {
		// valores por defecto - preparando objeto
		reglaNegocioDetalle = validar.prepararEliminar(usuarioActual, reglaNegocioDetalle);
		
		// validaciones de negocio
		List<DominioMensajeUsuario> lst = validar.coreEliminar(usuarioActual, reglaNegocioDetalle);
		if (!lst.isEmpty())
			throw new UException(lst);
		
		// transaccion
		reglaNegocioDetalleDao.eliminar(reglaNegocioDetalle);
	}

	public void coreEliminar(SeguridadUsuarioActual usuarioActual, ReglaNegocioDetallePk pk) throws UException {
		ReglaNegocioDetalle reglaNegocioDetalle = reglaNegocioDetalleDao.obtenerPorId(pk);
		coreEliminar(usuarioActual,reglaNegocioDetalle);
	}

	public void coreEliminar(SeguridadUsuarioActual usuarioActual, Integer pidReglaNegocio,Integer pidDetalle) throws UException {
		coreEliminar(usuarioActual,new ReglaNegocioDetallePk( pidReglaNegocio, pidDetalle));
	}

}
