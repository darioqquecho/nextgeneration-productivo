package net.royal.spring.alertas.servicio.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import net.royal.spring.alertas.dao.impl.PerfilCorreoDaoImpl;
import net.royal.spring.alertas.dominio.PerfilCorreo;
import net.royal.spring.alertas.dominio.PerfilCorreoPk;
import net.royal.spring.alertas.servicio.validar.PerfilCorreoServicioValidar;
import net.royal.spring.framework.core.UException;
import net.royal.spring.framework.modelo.generico.DominioMensajeUsuario;
import net.royal.spring.framework.modelo.seguridad.SeguridadUsuarioActual;
import net.royal.spring.framework.web.servicio.impl.GenericoServicioImpl;

@Service (value = "BeanServicioPerfilCorreo")
public class PerfilCorreoServicioImpl extends GenericoServicioImpl {
	public static String SPRING_NOMBRE = "BeanServicioPerfilCorreo";

	@Autowired
	private PerfilCorreoDaoImpl perfilCorreoDao;

	@Autowired
	private PerfilCorreoServicioValidar validar;

	@Transactional
	public PerfilCorreo coreInsertar(SeguridadUsuarioActual usuarioActual,PerfilCorreo perfilCorreo) throws UException {
		// valores por defecto - preparando objeto
		perfilCorreo = validar.prepararInsertar(usuarioActual, perfilCorreo);
		
		// validaciones de negocio
		List<DominioMensajeUsuario> lst = validar.coreInsertar(usuarioActual, perfilCorreo);
		if (!lst.isEmpty())
			throw new UException(lst);
		
		// transaccion
		return perfilCorreoDao.coreInsertar(perfilCorreo);
	}

	@Transactional
	public PerfilCorreo coreActualizar(SeguridadUsuarioActual usuarioActual, PerfilCorreo perfilCorreo) throws UException {
		// valores por defecto - preparando objeto
		perfilCorreo = validar.prepararActualizar(usuarioActual, perfilCorreo);
		
		// validaciones de negocio
		List<DominioMensajeUsuario> lst = validar.coreActualizar(usuarioActual, perfilCorreo);
		if (!lst.isEmpty())
			throw new UException(lst);
		
		// transaccion
	    perfilCorreo = perfilCorreoDao.coreActualizar(perfilCorreo);
		return perfilCorreo;
	}

	@Transactional
	public PerfilCorreo coreAnular(SeguridadUsuarioActual usuarioActual, PerfilCorreo perfilCorreo) throws UException {
		// valores por defecto - preparando objeto
		perfilCorreo = validar.prepararAnular(usuarioActual, perfilCorreo);
		
		// validaciones de negocio
		List<DominioMensajeUsuario> lst = validar.coreAnular(usuarioActual, perfilCorreo);
		if (!lst.isEmpty())
			throw new UException(lst);
		
		// transaccion
		return perfilCorreoDao.coreActualizar(perfilCorreo);
	}

	public PerfilCorreo coreAnular(SeguridadUsuarioActual usuarioActual, PerfilCorreoPk pk) throws UException {
		PerfilCorreo bean = perfilCorreoDao.obtenerPorId(pk);
		return coreAnular(usuarioActual,bean);
	}

	public PerfilCorreo coreAnular(SeguridadUsuarioActual usuarioActual, Integer pidPerfilCorreo) throws UException {
		return coreAnular(usuarioActual,new PerfilCorreoPk( pidPerfilCorreo));
	}

	@Transactional
	public void coreEliminar(SeguridadUsuarioActual usuarioActual, PerfilCorreo perfilCorreo) throws UException {
		// valores por defecto - preparando objeto
		perfilCorreo = validar.prepararEliminar(usuarioActual, perfilCorreo);
		
		// validaciones de negocio
		List<DominioMensajeUsuario> lst = validar.coreEliminar(usuarioActual, perfilCorreo);
		if (!lst.isEmpty())
			throw new UException(lst);
		
		// transaccion
		perfilCorreoDao.eliminar(perfilCorreo);
	}

	public void coreEliminar(SeguridadUsuarioActual usuarioActual, PerfilCorreoPk pk) throws UException {
		PerfilCorreo perfilCorreo = perfilCorreoDao.obtenerPorId(pk);
		coreEliminar(usuarioActual,perfilCorreo);
	}

	public void coreEliminar(SeguridadUsuarioActual usuarioActual, Integer pidPerfilCorreo) throws UException {
		coreEliminar(usuarioActual,new PerfilCorreoPk( pidPerfilCorreo));
	}

}
