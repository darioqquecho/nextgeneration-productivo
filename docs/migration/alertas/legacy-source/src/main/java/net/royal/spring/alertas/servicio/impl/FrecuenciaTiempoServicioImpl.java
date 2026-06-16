package net.royal.spring.alertas.servicio.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import net.royal.spring.alertas.dao.impl.FrecuenciaTiempoDaoImpl;
import net.royal.spring.alertas.dominio.FrecuenciaTiempo;
import net.royal.spring.alertas.dominio.FrecuenciaTiempoPk;
import net.royal.spring.alertas.servicio.validar.FrecuenciaTiempoServicioValidar;
import net.royal.spring.framework.core.UException;
import net.royal.spring.framework.modelo.generico.DominioMensajeUsuario;
import net.royal.spring.framework.modelo.seguridad.SeguridadUsuarioActual;
import net.royal.spring.framework.web.servicio.impl.GenericoServicioImpl;

@Service(value = "BeanServicioFrecuenciaTiempo")
public class FrecuenciaTiempoServicioImpl extends GenericoServicioImpl {
	public static String SPRING_NOMBRE = "BeanServicioFrecuenciaTiempo";

	@Autowired
	private FrecuenciaTiempoDaoImpl frecuenciaTiempoDao;

	@Autowired
	private FrecuenciaTiempoServicioValidar validar;

	@Transactional
	public FrecuenciaTiempo coreInsertar(SeguridadUsuarioActual usuarioActual, FrecuenciaTiempo frecuenciaTiempo)
			throws UException {
		// valores por defecto - preparando objeto
		frecuenciaTiempo = validar.prepararInsertar(usuarioActual, frecuenciaTiempo);

		// validaciones de negocio
		List<DominioMensajeUsuario> lst = validar.coreInsertar(usuarioActual, frecuenciaTiempo);
		if (!lst.isEmpty())
			throw new UException(lst);

		// transaccion
		return frecuenciaTiempoDao.coreInsertar(frecuenciaTiempo);
	}

	@Transactional
	public FrecuenciaTiempo coreActualizar(SeguridadUsuarioActual usuarioActual, FrecuenciaTiempo frecuenciaTiempo)
			throws UException {
		// valores por defecto - preparando objeto
		frecuenciaTiempo = validar.prepararActualizar(usuarioActual, frecuenciaTiempo);

		// validaciones de negocio
		List<DominioMensajeUsuario> lst = validar.coreActualizar(usuarioActual, frecuenciaTiempo);
		if (!lst.isEmpty())
			throw new UException(lst);

		// transaccion
		frecuenciaTiempo = frecuenciaTiempoDao.coreActualizar(frecuenciaTiempo);
		return frecuenciaTiempo;
	}

	@Transactional
	public FrecuenciaTiempo coreAnular(SeguridadUsuarioActual usuarioActual, FrecuenciaTiempo frecuenciaTiempo)
			throws UException {
		// valores por defecto - preparando objeto
		frecuenciaTiempo = validar.prepararAnular(usuarioActual, frecuenciaTiempo);

		// validaciones de negocio
		List<DominioMensajeUsuario> lst = validar.coreAnular(usuarioActual, frecuenciaTiempo);
		if (!lst.isEmpty())
			throw new UException(lst);

		// transaccion
		return frecuenciaTiempoDao.coreActualizar(frecuenciaTiempo);
	}

	public FrecuenciaTiempo coreAnular(SeguridadUsuarioActual usuarioActual, FrecuenciaTiempoPk pk) throws UException {
		FrecuenciaTiempo bean = frecuenciaTiempoDao.obtenerPorId(pk);
		return coreAnular(usuarioActual, bean);
	}

	public FrecuenciaTiempo coreAnular(SeguridadUsuarioActual usuarioActual, String pidFrecuenciaTiempo)
			throws UException {
		return coreAnular(usuarioActual, new FrecuenciaTiempoPk(pidFrecuenciaTiempo));
	}

	@Transactional
	public void coreEliminar(SeguridadUsuarioActual usuarioActual, FrecuenciaTiempo frecuenciaTiempo)
			throws UException {
		// valores por defecto - preparando objeto
		frecuenciaTiempo = validar.prepararEliminar(usuarioActual, frecuenciaTiempo);

		// validaciones de negocio
		List<DominioMensajeUsuario> lst = validar.coreEliminar(usuarioActual, frecuenciaTiempo);
		if (!lst.isEmpty())
			throw new UException(lst);

		// transaccion
		frecuenciaTiempoDao.eliminar(frecuenciaTiempo);
	}

	public void coreEliminar(SeguridadUsuarioActual usuarioActual, FrecuenciaTiempoPk pk) throws UException {
		FrecuenciaTiempo frecuenciaTiempo = frecuenciaTiempoDao.obtenerPorId(pk);
		coreEliminar(usuarioActual, frecuenciaTiempo);
	}

	public void coreEliminar(SeguridadUsuarioActual usuarioActual, String pidFrecuenciaTiempo) throws UException {
		coreEliminar(usuarioActual, new FrecuenciaTiempoPk(pidFrecuenciaTiempo));
	}

}
