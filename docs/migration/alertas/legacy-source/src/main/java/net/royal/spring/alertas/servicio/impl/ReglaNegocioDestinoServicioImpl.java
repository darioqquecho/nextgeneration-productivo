package net.royal.spring.alertas.servicio.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import net.royal.spring.alertas.dao.impl.ReglaNegocioDestinoDaoImpl;
import net.royal.spring.alertas.dominio.ReglaNegocioDestino;
import net.royal.spring.alertas.dominio.ReglaNegocioDestinoPk;
import net.royal.spring.alertas.servicio.validar.ReglaNegocioDestinoServicioValidar;
import net.royal.spring.framework.core.UException;
import net.royal.spring.framework.modelo.generico.DominioMensajeUsuario;
import net.royal.spring.framework.modelo.seguridad.SeguridadUsuarioActual;
import net.royal.spring.framework.web.servicio.impl.GenericoServicioImpl;

@Service (value = "BeanServicioReglaNegocioDestino")
public class ReglaNegocioDestinoServicioImpl extends GenericoServicioImpl {
	public static String SPRING_NOMBRE = "BeanServicioReglaNegocioDestino";

	@Autowired
	private ReglaNegocioDestinoDaoImpl reglaNegocioDestinoDao;

	@Autowired
	private ReglaNegocioDestinoServicioValidar validar;

	@Transactional
	public ReglaNegocioDestino coreInsertar(SeguridadUsuarioActual usuarioActual,ReglaNegocioDestino reglaNegocioDestino) throws UException {
		// valores por defecto - preparando objeto
		reglaNegocioDestino = validar.prepararInsertar(usuarioActual, reglaNegocioDestino);
		
		// validaciones de negocio
		List<DominioMensajeUsuario> lst = validar.coreInsertar(usuarioActual, reglaNegocioDestino);
		if (!lst.isEmpty())
			throw new UException(lst);
		
		// transaccion
		return reglaNegocioDestinoDao.coreInsertar(reglaNegocioDestino);
	}

	@Transactional
	public ReglaNegocioDestino coreActualizar(SeguridadUsuarioActual usuarioActual, ReglaNegocioDestino reglaNegocioDestino) throws UException {
		// valores por defecto - preparando objeto
		reglaNegocioDestino = validar.prepararActualizar(usuarioActual, reglaNegocioDestino);
		
		// validaciones de negocio
		List<DominioMensajeUsuario> lst = validar.coreActualizar(usuarioActual, reglaNegocioDestino);
		if (!lst.isEmpty())
			throw new UException(lst);
		
		// transaccion
	    reglaNegocioDestino = reglaNegocioDestinoDao.coreActualizar(reglaNegocioDestino);
		return reglaNegocioDestino;
	}

	@Transactional
	public ReglaNegocioDestino coreAnular(SeguridadUsuarioActual usuarioActual, ReglaNegocioDestino reglaNegocioDestino) throws UException {
		// valores por defecto - preparando objeto
		reglaNegocioDestino = validar.prepararAnular(usuarioActual, reglaNegocioDestino);
		
		// validaciones de negocio
		List<DominioMensajeUsuario> lst = validar.coreAnular(usuarioActual, reglaNegocioDestino);
		if (!lst.isEmpty())
			throw new UException(lst);
		
		// transaccion
		return reglaNegocioDestinoDao.coreActualizar(reglaNegocioDestino);
	}

	public ReglaNegocioDestino coreAnular(SeguridadUsuarioActual usuarioActual, ReglaNegocioDestinoPk pk) throws UException {
		ReglaNegocioDestino bean = reglaNegocioDestinoDao.obtenerPorId(pk);
		return coreAnular(usuarioActual,bean);
	}

	public ReglaNegocioDestino coreAnular(SeguridadUsuarioActual usuarioActual, Integer pidReglaNegocio,String pcorreoDestino) throws UException {
		return coreAnular(usuarioActual,new ReglaNegocioDestinoPk( pidReglaNegocio, pcorreoDestino));
	}

	@Transactional
	public void coreEliminar(SeguridadUsuarioActual usuarioActual, ReglaNegocioDestino reglaNegocioDestino) throws UException {
		// valores por defecto - preparando objeto
		reglaNegocioDestino = validar.prepararEliminar(usuarioActual, reglaNegocioDestino);
		
		// validaciones de negocio
		List<DominioMensajeUsuario> lst = validar.coreEliminar(usuarioActual, reglaNegocioDestino);
		if (!lst.isEmpty())
			throw new UException(lst);
		
		// transaccion
		reglaNegocioDestinoDao.eliminar(reglaNegocioDestino);
	}

	public void coreEliminar(SeguridadUsuarioActual usuarioActual, ReglaNegocioDestinoPk pk) throws UException {
		ReglaNegocioDestino reglaNegocioDestino = reglaNegocioDestinoDao.obtenerPorId(pk);
		coreEliminar(usuarioActual,reglaNegocioDestino);
	}

	public void coreEliminar(SeguridadUsuarioActual usuarioActual, Integer pidReglaNegocio,String pcorreoDestino) throws UException {
		coreEliminar(usuarioActual,new ReglaNegocioDestinoPk( pidReglaNegocio, pcorreoDestino));
	}

}
