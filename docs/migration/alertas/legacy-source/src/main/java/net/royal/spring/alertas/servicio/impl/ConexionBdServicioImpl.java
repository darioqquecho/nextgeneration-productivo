package net.royal.spring.alertas.servicio.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import net.royal.spring.alertas.dao.impl.ConexionBdDaoImpl;
import net.royal.spring.alertas.dominio.ConexionBd;
import net.royal.spring.alertas.dominio.ConexionBdPk;
import net.royal.spring.alertas.servicio.validar.ConexionBdServicioValidar;
import net.royal.spring.framework.core.UException;
import net.royal.spring.framework.modelo.generico.DominioMensajeUsuario;
import net.royal.spring.framework.modelo.seguridad.SeguridadUsuarioActual;
import net.royal.spring.framework.web.servicio.impl.GenericoServicioImpl;

@Service (value = "BeanServicioConexionBd")
public class ConexionBdServicioImpl extends GenericoServicioImpl {
	public static String SPRING_NOMBRE = "BeanServicioConexionBd";

	@Autowired
	private ConexionBdDaoImpl conexionBdDao;

	@Autowired
	private ConexionBdServicioValidar validar;

	@Transactional
	public ConexionBd coreInsertar(SeguridadUsuarioActual usuarioActual,ConexionBd conexionBd) throws UException {
		// valores por defecto - preparando objeto
		conexionBd = validar.prepararInsertar(usuarioActual, conexionBd);
		
		// validaciones de negocio
		List<DominioMensajeUsuario> lst = validar.coreInsertar(usuarioActual, conexionBd);
		if (!lst.isEmpty())
			throw new UException(lst);
		
		// transaccion
		return conexionBdDao.coreInsertar(conexionBd);
	}

	@Transactional
	public ConexionBd coreActualizar(SeguridadUsuarioActual usuarioActual, ConexionBd conexionBd) throws UException {
		// valores por defecto - preparando objeto
		conexionBd = validar.prepararActualizar(usuarioActual, conexionBd);
		
		// validaciones de negocio
		List<DominioMensajeUsuario> lst = validar.coreActualizar(usuarioActual, conexionBd);
		if (!lst.isEmpty())
			throw new UException(lst);
		
		// transaccion
	    conexionBd = conexionBdDao.coreActualizar(conexionBd);
		return conexionBd;
	}

	@Transactional
	public ConexionBd coreAnular(SeguridadUsuarioActual usuarioActual, ConexionBd conexionBd) throws UException {
		// valores por defecto - preparando objeto
		conexionBd = validar.prepararAnular(usuarioActual, conexionBd);
		
		// validaciones de negocio
		List<DominioMensajeUsuario> lst = validar.coreAnular(usuarioActual, conexionBd);
		if (!lst.isEmpty())
			throw new UException(lst);
		
		// transaccion
		return conexionBdDao.coreActualizar(conexionBd);
	}

	public ConexionBd coreAnular(SeguridadUsuarioActual usuarioActual, ConexionBdPk pk) throws UException {
		ConexionBd bean = conexionBdDao.obtenerPorId(pk);
		return coreAnular(usuarioActual,bean);
	}

	public ConexionBd coreAnular(SeguridadUsuarioActual usuarioActual, Integer pidConexionBd) throws UException {
		return coreAnular(usuarioActual,new ConexionBdPk( pidConexionBd));
	}

	@Transactional
	public void coreEliminar(SeguridadUsuarioActual usuarioActual, ConexionBd conexionBd) throws UException {
		// valores por defecto - preparando objeto
		conexionBd = validar.prepararEliminar(usuarioActual, conexionBd);
		
		// validaciones de negocio
		List<DominioMensajeUsuario> lst = validar.coreEliminar(usuarioActual, conexionBd);
		if (!lst.isEmpty())
			throw new UException(lst);
		
		// transaccion
		conexionBdDao.eliminar(conexionBd);
	}

	public void coreEliminar(SeguridadUsuarioActual usuarioActual, ConexionBdPk pk) throws UException {
		ConexionBd conexionBd = conexionBdDao.obtenerPorId(pk);
		coreEliminar(usuarioActual,conexionBd);
	}

	public void coreEliminar(SeguridadUsuarioActual usuarioActual, Integer pidConexionBd) throws UException {
		coreEliminar(usuarioActual,new ConexionBdPk( pidConexionBd));
	}

}
