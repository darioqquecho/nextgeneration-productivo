package net.royal.spring.alertas.servicio.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import net.royal.spring.alertas.dao.impl.ReglaNegocioPlanDaoImpl;
import net.royal.spring.alertas.dominio.ReglaNegocioPlan;
import net.royal.spring.alertas.dominio.ReglaNegocioPlanPk;
import net.royal.spring.alertas.servicio.validar.ReglaNegocioPlanServicioValidar;
import net.royal.spring.framework.core.UException;
import net.royal.spring.framework.modelo.generico.DominioMensajeUsuario;
import net.royal.spring.framework.modelo.seguridad.SeguridadUsuarioActual;
import net.royal.spring.framework.web.servicio.impl.GenericoServicioImpl;

@Service (value = "BeanServicioReglaNegocioPlan")
public class ReglaNegocioPlanServicioImpl extends GenericoServicioImpl {
	public static String SPRING_NOMBRE = "BeanServicioReglaNegocioPlan";

	@Autowired
	private ReglaNegocioPlanDaoImpl reglaNegocioPlanDao;

	@Autowired
	private ReglaNegocioPlanServicioValidar validar;

	@Transactional
	public ReglaNegocioPlan coreInsertar(SeguridadUsuarioActual usuarioActual,ReglaNegocioPlan reglaNegocioPlan) throws UException {
		// valores por defecto - preparando objeto
		reglaNegocioPlan = validar.prepararInsertar(usuarioActual, reglaNegocioPlan);
		
		// validaciones de negocio
		List<DominioMensajeUsuario> lst = validar.coreInsertar(usuarioActual, reglaNegocioPlan);
		if (!lst.isEmpty())
			throw new UException(lst);
		
		// transaccion
		return reglaNegocioPlanDao.coreInsertar(reglaNegocioPlan);
	}

	@Transactional
	public ReglaNegocioPlan coreActualizar(SeguridadUsuarioActual usuarioActual, ReglaNegocioPlan reglaNegocioPlan) throws UException {
		// valores por defecto - preparando objeto
		reglaNegocioPlan = validar.prepararActualizar(usuarioActual, reglaNegocioPlan);
		
		// validaciones de negocio
		List<DominioMensajeUsuario> lst = validar.coreActualizar(usuarioActual, reglaNegocioPlan);
		if (!lst.isEmpty())
			throw new UException(lst);
		
		// transaccion
	    reglaNegocioPlan = reglaNegocioPlanDao.coreActualizar(reglaNegocioPlan);
		return reglaNegocioPlan;
	}

	@Transactional
	public ReglaNegocioPlan coreAnular(SeguridadUsuarioActual usuarioActual, ReglaNegocioPlan reglaNegocioPlan) throws UException {
		// valores por defecto - preparando objeto
		reglaNegocioPlan = validar.prepararAnular(usuarioActual, reglaNegocioPlan);
		
		// validaciones de negocio
		List<DominioMensajeUsuario> lst = validar.coreAnular(usuarioActual, reglaNegocioPlan);
		if (!lst.isEmpty())
			throw new UException(lst);
		
		// transaccion
		return reglaNegocioPlanDao.coreActualizar(reglaNegocioPlan);
	}

	public ReglaNegocioPlan coreAnular(SeguridadUsuarioActual usuarioActual, ReglaNegocioPlanPk pk) throws UException {
		ReglaNegocioPlan bean = reglaNegocioPlanDao.obtenerPorId(pk);
		return coreAnular(usuarioActual,bean);
	}

	public ReglaNegocioPlan coreAnular(SeguridadUsuarioActual usuarioActual, Integer pidReglaNegocio,Integer pidPlan) throws UException {
		return coreAnular(usuarioActual,new ReglaNegocioPlanPk( pidReglaNegocio, pidPlan));
	}

	@Transactional
	public void coreEliminar(SeguridadUsuarioActual usuarioActual, ReglaNegocioPlan reglaNegocioPlan) throws UException {
		// valores por defecto - preparando objeto
		reglaNegocioPlan = validar.prepararEliminar(usuarioActual, reglaNegocioPlan);
		
		// validaciones de negocio
		List<DominioMensajeUsuario> lst = validar.coreEliminar(usuarioActual, reglaNegocioPlan);
		if (!lst.isEmpty())
			throw new UException(lst);
		
		// transaccion
		reglaNegocioPlanDao.eliminar(reglaNegocioPlan);
	}

	public void coreEliminar(SeguridadUsuarioActual usuarioActual, ReglaNegocioPlanPk pk) throws UException {
		ReglaNegocioPlan reglaNegocioPlan = reglaNegocioPlanDao.obtenerPorId(pk);
		coreEliminar(usuarioActual,reglaNegocioPlan);
	}

	public void coreEliminar(SeguridadUsuarioActual usuarioActual, Integer pidReglaNegocio,Integer pidPlan) throws UException {
		coreEliminar(usuarioActual,new ReglaNegocioPlanPk( pidReglaNegocio, pidPlan));
	}

}
