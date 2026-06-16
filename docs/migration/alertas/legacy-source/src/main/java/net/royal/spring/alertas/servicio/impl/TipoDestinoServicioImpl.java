package net.royal.spring.alertas.servicio.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import net.royal.spring.alertas.dao.impl.TipoDestinoDaoImpl;
import net.royal.spring.alertas.dominio.TipoDestino;
import net.royal.spring.alertas.dominio.TipoDestinoPk;
import net.royal.spring.alertas.servicio.validar.TipoDestinoServicioValidar;
import net.royal.spring.framework.core.UException;
import net.royal.spring.framework.modelo.generico.DominioMensajeUsuario;
import net.royal.spring.framework.modelo.seguridad.SeguridadUsuarioActual;
import net.royal.spring.framework.web.servicio.impl.GenericoServicioImpl;

@Service (value = "BeanServicioTipoDestino")
public class TipoDestinoServicioImpl extends GenericoServicioImpl {
	public static String SPRING_NOMBRE = "BeanServicioTipoDestino";

	@Autowired
	private TipoDestinoDaoImpl tipoDestinoDao;

	@Autowired
	private TipoDestinoServicioValidar validar;

	@Transactional
	public TipoDestino coreInsertar(SeguridadUsuarioActual usuarioActual,TipoDestino tipoDestino) throws UException {
		// valores por defecto - preparando objeto
		tipoDestino = validar.prepararInsertar(usuarioActual, tipoDestino);
		
		// validaciones de negocio
		List<DominioMensajeUsuario> lst = validar.coreInsertar(usuarioActual, tipoDestino);
		if (!lst.isEmpty())
			throw new UException(lst);
		
		// transaccion
		return tipoDestinoDao.coreInsertar(tipoDestino);
	}

	@Transactional
	public TipoDestino coreActualizar(SeguridadUsuarioActual usuarioActual, TipoDestino tipoDestino) throws UException {
		// valores por defecto - preparando objeto
		tipoDestino = validar.prepararActualizar(usuarioActual, tipoDestino);
		
		// validaciones de negocio
		List<DominioMensajeUsuario> lst = validar.coreActualizar(usuarioActual, tipoDestino);
		if (!lst.isEmpty())
			throw new UException(lst);
		
		// transaccion
	    tipoDestino = tipoDestinoDao.coreActualizar(tipoDestino);
		return tipoDestino;
	}

	@Transactional
	public TipoDestino coreAnular(SeguridadUsuarioActual usuarioActual, TipoDestino tipoDestino) throws UException {
		// valores por defecto - preparando objeto
		tipoDestino = validar.prepararAnular(usuarioActual, tipoDestino);
		
		// validaciones de negocio
		List<DominioMensajeUsuario> lst = validar.coreAnular(usuarioActual, tipoDestino);
		if (!lst.isEmpty())
			throw new UException(lst);
		
		// transaccion
		return tipoDestinoDao.coreActualizar(tipoDestino);
	}

	public TipoDestino coreAnular(SeguridadUsuarioActual usuarioActual, TipoDestinoPk pk) throws UException {
		TipoDestino bean = tipoDestinoDao.obtenerPorId(pk);
		return coreAnular(usuarioActual,bean);
	}

	public TipoDestino coreAnular(SeguridadUsuarioActual usuarioActual, String pidTipoDestino) throws UException {
		return coreAnular(usuarioActual,new TipoDestinoPk( pidTipoDestino));
	}

	@Transactional
	public void coreEliminar(SeguridadUsuarioActual usuarioActual, TipoDestino tipoDestino) throws UException {
		// valores por defecto - preparando objeto
		tipoDestino = validar.prepararEliminar(usuarioActual, tipoDestino);
		
		// validaciones de negocio
		List<DominioMensajeUsuario> lst = validar.coreEliminar(usuarioActual, tipoDestino);
		if (!lst.isEmpty())
			throw new UException(lst);
		
		// transaccion
		tipoDestinoDao.eliminar(tipoDestino);
	}

	public void coreEliminar(SeguridadUsuarioActual usuarioActual, TipoDestinoPk pk) throws UException {
		TipoDestino tipoDestino = tipoDestinoDao.obtenerPorId(pk);
		coreEliminar(usuarioActual,tipoDestino);
	}

	public void coreEliminar(SeguridadUsuarioActual usuarioActual, String pidTipoDestino) throws UException {
		coreEliminar(usuarioActual,new TipoDestinoPk( pidTipoDestino));
	}

}
