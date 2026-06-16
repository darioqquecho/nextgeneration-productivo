package net.royal.spring.alertas.servicio.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import net.royal.spring.alertas.dao.impl.ReglaNegocioAdicionalDaoImpl;
import net.royal.spring.alertas.dominio.ReglaNegocioAdicional;
import net.royal.spring.alertas.dominio.ReglaNegocioAdicionalPk;
import net.royal.spring.alertas.servicio.validar.ReglaNegocioAdicionalServicioValidar;
import net.royal.spring.framework.core.UException;
import net.royal.spring.framework.modelo.generico.DominioMensajeUsuario;
import net.royal.spring.framework.modelo.seguridad.SeguridadUsuarioActual;
import net.royal.spring.framework.web.servicio.impl.GenericoServicioImpl;

@Service (value = "BeanServicioReglaNegocioAdicional")
public class ReglaNegocioAdicionalServicioImpl extends GenericoServicioImpl {
	public static String SPRING_NOMBRE = "BeanServicioReglaNegocioAdicional";

	@Autowired
	private ReglaNegocioAdicionalDaoImpl reglaNegocioAdicionalDao;

	@Autowired
	private ReglaNegocioAdicionalServicioValidar validar;

	@Transactional
	public ReglaNegocioAdicional coreInsertar(SeguridadUsuarioActual usuarioActual,ReglaNegocioAdicional reglaNegocioAdicional) throws UException {
		// valores por defecto - preparando objeto
		reglaNegocioAdicional = validar.prepararInsertar(usuarioActual, reglaNegocioAdicional);
		
		// validaciones de negocio
		List<DominioMensajeUsuario> lst = validar.coreInsertar(usuarioActual, reglaNegocioAdicional);
		if (!lst.isEmpty())
			throw new UException(lst);
		
		// transaccion
		return reglaNegocioAdicionalDao.coreInsertar(reglaNegocioAdicional);
	}

	@Transactional
	public ReglaNegocioAdicional coreActualizar(SeguridadUsuarioActual usuarioActual, ReglaNegocioAdicional reglaNegocioAdicional) throws UException {
		// valores por defecto - preparando objeto
		reglaNegocioAdicional = validar.prepararActualizar(usuarioActual, reglaNegocioAdicional);
		
		// validaciones de negocio
		List<DominioMensajeUsuario> lst = validar.coreActualizar(usuarioActual, reglaNegocioAdicional);
		if (!lst.isEmpty())
			throw new UException(lst);
		
		// transaccion
	    reglaNegocioAdicional = reglaNegocioAdicionalDao.coreActualizar(reglaNegocioAdicional);
		return reglaNegocioAdicional;
	}

	@Transactional
	public ReglaNegocioAdicional coreAnular(SeguridadUsuarioActual usuarioActual, ReglaNegocioAdicional reglaNegocioAdicional) throws UException {
		// valores por defecto - preparando objeto
		reglaNegocioAdicional = validar.prepararAnular(usuarioActual, reglaNegocioAdicional);
		
		// validaciones de negocio
		List<DominioMensajeUsuario> lst = validar.coreAnular(usuarioActual, reglaNegocioAdicional);
		if (!lst.isEmpty())
			throw new UException(lst);
		
		// transaccion
		return reglaNegocioAdicionalDao.coreActualizar(reglaNegocioAdicional);
	}

	public ReglaNegocioAdicional coreAnular(SeguridadUsuarioActual usuarioActual, ReglaNegocioAdicionalPk pk) throws UException {
		ReglaNegocioAdicional bean = reglaNegocioAdicionalDao.obtenerPorId(pk);
		return coreAnular(usuarioActual,bean);
	}

	public ReglaNegocioAdicional coreAnular(SeguridadUsuarioActual usuarioActual, Integer pidReglaNegocio,Integer pidAdicional) throws UException {
		return coreAnular(usuarioActual,new ReglaNegocioAdicionalPk( pidReglaNegocio, pidAdicional));
	}

	@Transactional
	public void coreEliminar(SeguridadUsuarioActual usuarioActual, ReglaNegocioAdicional reglaNegocioAdicional) throws UException {
		// valores por defecto - preparando objeto
		reglaNegocioAdicional = validar.prepararEliminar(usuarioActual, reglaNegocioAdicional);
		
		// validaciones de negocio
		List<DominioMensajeUsuario> lst = validar.coreEliminar(usuarioActual, reglaNegocioAdicional);
		if (!lst.isEmpty())
			throw new UException(lst);
		
		// transaccion
		reglaNegocioAdicionalDao.eliminar(reglaNegocioAdicional);
	}

	public void coreEliminar(SeguridadUsuarioActual usuarioActual, ReglaNegocioAdicionalPk pk) throws UException {
		ReglaNegocioAdicional reglaNegocioAdicional = reglaNegocioAdicionalDao.obtenerPorId(pk);
		coreEliminar(usuarioActual,reglaNegocioAdicional);
	}

	public void coreEliminar(SeguridadUsuarioActual usuarioActual, Integer pidReglaNegocio,Integer pidAdicional) throws UException {
		coreEliminar(usuarioActual,new ReglaNegocioAdicionalPk( pidReglaNegocio, pidAdicional));
	}

}
