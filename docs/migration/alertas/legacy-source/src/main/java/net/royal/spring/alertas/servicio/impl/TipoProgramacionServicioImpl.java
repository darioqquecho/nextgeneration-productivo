package net.royal.spring.alertas.servicio.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import net.royal.spring.alertas.dao.impl.TipoProgramacionDaoImpl;
import net.royal.spring.alertas.dominio.TipoProgramacion;
import net.royal.spring.alertas.dominio.TipoProgramacionPk;
import net.royal.spring.alertas.servicio.validar.TipoProgramacionServicioValidar;
import net.royal.spring.framework.core.UException;
import net.royal.spring.framework.modelo.generico.DominioMensajeUsuario;
import net.royal.spring.framework.modelo.seguridad.SeguridadUsuarioActual;
import net.royal.spring.framework.web.servicio.impl.GenericoServicioImpl;

@Service (value = "BeanServicioTipoProgramacion")
public class TipoProgramacionServicioImpl extends GenericoServicioImpl {
	public static String SPRING_NOMBRE = "BeanServicioTipoProgramacion";

	@Autowired
	private TipoProgramacionDaoImpl tipoProgramacionDao;

	@Autowired
	private TipoProgramacionServicioValidar validar;

	@Transactional
	public TipoProgramacion coreInsertar(SeguridadUsuarioActual usuarioActual,TipoProgramacion tipoProgramacion) throws UException {
		// valores por defecto - preparando objeto
		tipoProgramacion = validar.prepararInsertar(usuarioActual, tipoProgramacion);
		
		// validaciones de negocio
		List<DominioMensajeUsuario> lst = validar.coreInsertar(usuarioActual, tipoProgramacion);
		if (!lst.isEmpty())
			throw new UException(lst);
		
		// transaccion
		return tipoProgramacionDao.coreInsertar(tipoProgramacion);
	}

	@Transactional
	public TipoProgramacion coreActualizar(SeguridadUsuarioActual usuarioActual, TipoProgramacion tipoProgramacion) throws UException {
		// valores por defecto - preparando objeto
		tipoProgramacion = validar.prepararActualizar(usuarioActual, tipoProgramacion);
		
		// validaciones de negocio
		List<DominioMensajeUsuario> lst = validar.coreActualizar(usuarioActual, tipoProgramacion);
		if (!lst.isEmpty())
			throw new UException(lst);
		
		// transaccion
	    tipoProgramacion = tipoProgramacionDao.coreActualizar(tipoProgramacion);
		return tipoProgramacion;
	}

	@Transactional
	public TipoProgramacion coreAnular(SeguridadUsuarioActual usuarioActual, TipoProgramacion tipoProgramacion) throws UException {
		// valores por defecto - preparando objeto
		tipoProgramacion = validar.prepararAnular(usuarioActual, tipoProgramacion);
		
		// validaciones de negocio
		List<DominioMensajeUsuario> lst = validar.coreAnular(usuarioActual, tipoProgramacion);
		if (!lst.isEmpty())
			throw new UException(lst);
		
		// transaccion
		return tipoProgramacionDao.coreActualizar(tipoProgramacion);
	}

	public TipoProgramacion coreAnular(SeguridadUsuarioActual usuarioActual, TipoProgramacionPk pk) throws UException {
		TipoProgramacion bean = tipoProgramacionDao.obtenerPorId(pk);
		return coreAnular(usuarioActual,bean);
	}

	public TipoProgramacion coreAnular(SeguridadUsuarioActual usuarioActual, String pidTipoProgramacion) throws UException {
		return coreAnular(usuarioActual,new TipoProgramacionPk( pidTipoProgramacion));
	}

	@Transactional
	public void coreEliminar(SeguridadUsuarioActual usuarioActual, TipoProgramacion tipoProgramacion) throws UException {
		// valores por defecto - preparando objeto
		tipoProgramacion = validar.prepararEliminar(usuarioActual, tipoProgramacion);
		
		// validaciones de negocio
		List<DominioMensajeUsuario> lst = validar.coreEliminar(usuarioActual, tipoProgramacion);
		if (!lst.isEmpty())
			throw new UException(lst);
		
		// transaccion
		tipoProgramacionDao.eliminar(tipoProgramacion);
	}

	public void coreEliminar(SeguridadUsuarioActual usuarioActual, TipoProgramacionPk pk) throws UException {
		TipoProgramacion tipoProgramacion = tipoProgramacionDao.obtenerPorId(pk);
		coreEliminar(usuarioActual,tipoProgramacion);
	}

	public void coreEliminar(SeguridadUsuarioActual usuarioActual, String pidTipoProgramacion) throws UException {
		coreEliminar(usuarioActual,new TipoProgramacionPk( pidTipoProgramacion));
	}

}
