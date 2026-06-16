package net.royal.spring.alertas.servicio.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import net.royal.spring.alertas.dao.impl.LogAdicionalDaoImpl;
import net.royal.spring.alertas.dominio.LogAdicional;
import net.royal.spring.alertas.dominio.LogAdicionalPk;
import net.royal.spring.alertas.servicio.validar.LogAdicionalServicioValidar;
import net.royal.spring.framework.core.UException;
import net.royal.spring.framework.modelo.generico.DominioMensajeUsuario;
import net.royal.spring.framework.modelo.seguridad.SeguridadUsuarioActual;
import net.royal.spring.framework.web.servicio.impl.GenericoServicioImpl;

@Service (value = "BeanServicioLogAdicional")
public class LogAdicionalServicioImpl extends GenericoServicioImpl {
	public static String SPRING_NOMBRE = "BeanServicioLogAdicional";

	@Autowired
	private LogAdicionalDaoImpl logAdicionalDao;

	@Autowired
	private LogAdicionalServicioValidar validar;

	@Transactional
	public LogAdicional coreInsertar(SeguridadUsuarioActual usuarioActual,LogAdicional logAdicional) throws UException {
		// valores por defecto - preparando objeto
		logAdicional = validar.prepararInsertar(usuarioActual, logAdicional);
		
		// validaciones de negocio
		List<DominioMensajeUsuario> lst = validar.coreInsertar(usuarioActual, logAdicional);
		if (!lst.isEmpty())
			throw new UException(lst);
		
		// transaccion
		return logAdicionalDao.coreInsertar(logAdicional);
	}

	@Transactional
	public LogAdicional coreActualizar(SeguridadUsuarioActual usuarioActual, LogAdicional logAdicional) throws UException {
		// valores por defecto - preparando objeto
		logAdicional = validar.prepararActualizar(usuarioActual, logAdicional);
		
		// validaciones de negocio
		List<DominioMensajeUsuario> lst = validar.coreActualizar(usuarioActual, logAdicional);
		if (!lst.isEmpty())
			throw new UException(lst);
		
		// transaccion
	    logAdicional = logAdicionalDao.coreActualizar(logAdicional);
		return logAdicional;
	}

	@Transactional
	public LogAdicional coreAnular(SeguridadUsuarioActual usuarioActual, LogAdicional logAdicional) throws UException {
		// valores por defecto - preparando objeto
		logAdicional = validar.prepararAnular(usuarioActual, logAdicional);
		
		// validaciones de negocio
		List<DominioMensajeUsuario> lst = validar.coreAnular(usuarioActual, logAdicional);
		if (!lst.isEmpty())
			throw new UException(lst);
		
		// transaccion
		return logAdicionalDao.coreActualizar(logAdicional);
	}

	public LogAdicional coreAnular(SeguridadUsuarioActual usuarioActual, LogAdicionalPk pk) throws UException {
		LogAdicional bean = logAdicionalDao.obtenerPorId(pk);
		return coreAnular(usuarioActual,bean);
	}

	public LogAdicional coreAnular(SeguridadUsuarioActual usuarioActual, Integer pidLogAlerta,Integer pnroRegistro,String pnombreCampo) throws UException {
		return coreAnular(usuarioActual,new LogAdicionalPk( pidLogAlerta, pnroRegistro, pnombreCampo));
	}

	@Transactional
	public void coreEliminar(SeguridadUsuarioActual usuarioActual, LogAdicional logAdicional) throws UException {
		// valores por defecto - preparando objeto
		logAdicional = validar.prepararEliminar(usuarioActual, logAdicional);
		
		// validaciones de negocio
		List<DominioMensajeUsuario> lst = validar.coreEliminar(usuarioActual, logAdicional);
		if (!lst.isEmpty())
			throw new UException(lst);
		
		// transaccion
		logAdicionalDao.eliminar(logAdicional);
	}

	public void coreEliminar(SeguridadUsuarioActual usuarioActual, LogAdicionalPk pk) throws UException {
		LogAdicional logAdicional = logAdicionalDao.obtenerPorId(pk);
		coreEliminar(usuarioActual,logAdicional);
	}

	public void coreEliminar(SeguridadUsuarioActual usuarioActual, Integer pidLogAlerta,Integer pnroRegistro,String pnombreCampo) throws UException {
		coreEliminar(usuarioActual,new LogAdicionalPk( pidLogAlerta, pnroRegistro, pnombreCampo));
	}

}
