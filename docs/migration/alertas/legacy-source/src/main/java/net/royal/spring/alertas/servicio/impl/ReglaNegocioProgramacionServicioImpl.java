package net.royal.spring.alertas.servicio.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import net.royal.spring.alertas.dao.impl.ReglaNegocioProgramacionDaoImpl;
import net.royal.spring.alertas.dominio.ReglaNegocioProgramacion;
import net.royal.spring.alertas.dominio.ReglaNegocioProgramacionPk;
import net.royal.spring.alertas.servicio.validar.ReglaNegocioProgramacionServicioValidar;
import net.royal.spring.framework.core.UException;
import net.royal.spring.framework.modelo.generico.DominioMensajeUsuario;
import net.royal.spring.framework.modelo.seguridad.SeguridadUsuarioActual;
import net.royal.spring.framework.web.servicio.impl.GenericoServicioImpl;

@Service (value = "BeanServicioReglaNegocioProgramacion")
public class ReglaNegocioProgramacionServicioImpl extends GenericoServicioImpl {
	public static String SPRING_NOMBRE = "BeanServicioReglaNegocioProgramacion";

	@Autowired
	private ReglaNegocioProgramacionDaoImpl reglaNegocioProgramacionDao;

	@Autowired
	private ReglaNegocioProgramacionServicioValidar validar;

	@Transactional
	public ReglaNegocioProgramacion coreInsertar(SeguridadUsuarioActual usuarioActual,ReglaNegocioProgramacion reglaNegocioProgramacion) throws UException {
		// valores por defecto - preparando objeto
		reglaNegocioProgramacion = validar.prepararInsertar(usuarioActual, reglaNegocioProgramacion);
		
		// validaciones de negocio
		List<DominioMensajeUsuario> lst = validar.coreInsertar(usuarioActual, reglaNegocioProgramacion);
		if (!lst.isEmpty())
			throw new UException(lst);
		
		// transaccion
		return reglaNegocioProgramacionDao.coreInsertar(reglaNegocioProgramacion);
	}

	@Transactional
	public ReglaNegocioProgramacion coreActualizar(SeguridadUsuarioActual usuarioActual, ReglaNegocioProgramacion reglaNegocioProgramacion) throws UException {
		// valores por defecto - preparando objeto
		reglaNegocioProgramacion = validar.prepararActualizar(usuarioActual, reglaNegocioProgramacion);
		
		// validaciones de negocio
		List<DominioMensajeUsuario> lst = validar.coreActualizar(usuarioActual, reglaNegocioProgramacion);
		if (!lst.isEmpty())
			throw new UException(lst);
		
		// transaccion
	    reglaNegocioProgramacion = reglaNegocioProgramacionDao.coreActualizar(reglaNegocioProgramacion);
		return reglaNegocioProgramacion;
	}

	@Transactional
	public ReglaNegocioProgramacion coreAnular(SeguridadUsuarioActual usuarioActual, ReglaNegocioProgramacion reglaNegocioProgramacion) throws UException {
		// valores por defecto - preparando objeto
		reglaNegocioProgramacion = validar.prepararAnular(usuarioActual, reglaNegocioProgramacion);
		
		// validaciones de negocio
		List<DominioMensajeUsuario> lst = validar.coreAnular(usuarioActual, reglaNegocioProgramacion);
		if (!lst.isEmpty())
			throw new UException(lst);
		
		// transaccion
		return reglaNegocioProgramacionDao.coreActualizar(reglaNegocioProgramacion);
	}

	public ReglaNegocioProgramacion coreAnular(SeguridadUsuarioActual usuarioActual, ReglaNegocioProgramacionPk pk) throws UException {
		ReglaNegocioProgramacion bean = reglaNegocioProgramacionDao.obtenerPorId(pk);
		return coreAnular(usuarioActual,bean);
	}

	public ReglaNegocioProgramacion coreAnular(SeguridadUsuarioActual usuarioActual, Integer pidReglaNegocio,Integer pidProgramacion) throws UException {
		return coreAnular(usuarioActual,new ReglaNegocioProgramacionPk( pidReglaNegocio, pidProgramacion));
	}

	@Transactional
	public void coreEliminar(SeguridadUsuarioActual usuarioActual, ReglaNegocioProgramacion reglaNegocioProgramacion) throws UException {
		// valores por defecto - preparando objeto
		reglaNegocioProgramacion = validar.prepararEliminar(usuarioActual, reglaNegocioProgramacion);
		
		// validaciones de negocio
		List<DominioMensajeUsuario> lst = validar.coreEliminar(usuarioActual, reglaNegocioProgramacion);
		if (!lst.isEmpty())
			throw new UException(lst);
		
		// transaccion
		reglaNegocioProgramacionDao.eliminar(reglaNegocioProgramacion);
	}

	public void coreEliminar(SeguridadUsuarioActual usuarioActual, ReglaNegocioProgramacionPk pk) throws UException {
		ReglaNegocioProgramacion reglaNegocioProgramacion = reglaNegocioProgramacionDao.obtenerPorId(pk);
		coreEliminar(usuarioActual,reglaNegocioProgramacion);
	}

	public void coreEliminar(SeguridadUsuarioActual usuarioActual, Integer pidReglaNegocio,Integer pidProgramacion) throws UException {
		coreEliminar(usuarioActual,new ReglaNegocioProgramacionPk( pidReglaNegocio, pidProgramacion));
	}

}
