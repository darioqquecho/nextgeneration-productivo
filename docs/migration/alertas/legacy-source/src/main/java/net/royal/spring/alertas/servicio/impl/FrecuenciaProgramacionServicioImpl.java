package net.royal.spring.alertas.servicio.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import net.royal.spring.alertas.dao.impl.FrecuenciaProgramacionDaoImpl;
import net.royal.spring.alertas.dominio.FrecuenciaProgramacion;
import net.royal.spring.alertas.dominio.FrecuenciaProgramacionPk;
import net.royal.spring.alertas.servicio.validar.FrecuenciaProgramacionServicioValidar;
import net.royal.spring.framework.core.UException;
import net.royal.spring.framework.modelo.generico.DominioMensajeUsuario;
import net.royal.spring.framework.modelo.seguridad.SeguridadUsuarioActual;
import net.royal.spring.framework.web.servicio.impl.GenericoServicioImpl;

@Service(value = "BeanServicioFrecuenciaProgramacion")
public class FrecuenciaProgramacionServicioImpl extends GenericoServicioImpl {
	public static String SPRING_NOMBRE = "BeanServicioFrecuenciaProgramacion";

	@Autowired
	private FrecuenciaProgramacionDaoImpl frecuenciaProgramacionDao;

	@Autowired
	private FrecuenciaProgramacionServicioValidar validar;

	@Transactional
	public FrecuenciaProgramacion coreInsertar(SeguridadUsuarioActual usuarioActual,
			FrecuenciaProgramacion frecuenciaProgramacion) throws UException {
		// valores por defecto - preparando objeto
		frecuenciaProgramacion = validar.prepararInsertar(usuarioActual, frecuenciaProgramacion);

		// validaciones de negocio
		List<DominioMensajeUsuario> lst = validar.coreInsertar(usuarioActual, frecuenciaProgramacion);
		if (!lst.isEmpty())
			throw new UException(lst);

		// transaccion
		return frecuenciaProgramacionDao.coreInsertar(frecuenciaProgramacion);
	}

	@Transactional
	public FrecuenciaProgramacion coreActualizar(SeguridadUsuarioActual usuarioActual,
			FrecuenciaProgramacion frecuenciaProgramacion) throws UException {
		// valores por defecto - preparando objeto
		frecuenciaProgramacion = validar.prepararActualizar(usuarioActual, frecuenciaProgramacion);

		// validaciones de negocio
		List<DominioMensajeUsuario> lst = validar.coreActualizar(usuarioActual, frecuenciaProgramacion);
		if (!lst.isEmpty())
			throw new UException(lst);

		// transaccion
		frecuenciaProgramacion = frecuenciaProgramacionDao.coreActualizar(frecuenciaProgramacion);
		return frecuenciaProgramacion;
	}

	@Transactional
	public FrecuenciaProgramacion coreAnular(SeguridadUsuarioActual usuarioActual,
			FrecuenciaProgramacion frecuenciaProgramacion) throws UException {
		// valores por defecto - preparando objeto
		frecuenciaProgramacion = validar.prepararAnular(usuarioActual, frecuenciaProgramacion);

		// validaciones de negocio
		List<DominioMensajeUsuario> lst = validar.coreAnular(usuarioActual, frecuenciaProgramacion);
		if (!lst.isEmpty())
			throw new UException(lst);

		// transaccion
		return frecuenciaProgramacionDao.coreActualizar(frecuenciaProgramacion);
	}

	public FrecuenciaProgramacion coreAnular(SeguridadUsuarioActual usuarioActual, FrecuenciaProgramacionPk pk)
			throws UException {
		FrecuenciaProgramacion bean = frecuenciaProgramacionDao.obtenerPorId(pk);
		return coreAnular(usuarioActual, bean);
	}

	public FrecuenciaProgramacion coreAnular(SeguridadUsuarioActual usuarioActual, String pidFrecuenciaProgramacion)
			throws UException {
		return coreAnular(usuarioActual, new FrecuenciaProgramacionPk(pidFrecuenciaProgramacion));
	}

	@Transactional
	public void coreEliminar(SeguridadUsuarioActual usuarioActual, FrecuenciaProgramacion frecuenciaProgramacion)
			throws UException {
		// valores por defecto - preparando objeto
		frecuenciaProgramacion = validar.prepararEliminar(usuarioActual, frecuenciaProgramacion);

		// validaciones de negocio
		List<DominioMensajeUsuario> lst = validar.coreEliminar(usuarioActual, frecuenciaProgramacion);
		if (!lst.isEmpty())
			throw new UException(lst);

		// transaccion
		frecuenciaProgramacionDao.eliminar(frecuenciaProgramacion);
	}

	public void coreEliminar(SeguridadUsuarioActual usuarioActual, FrecuenciaProgramacionPk pk) throws UException {
		FrecuenciaProgramacion frecuenciaProgramacion = frecuenciaProgramacionDao.obtenerPorId(pk);
		coreEliminar(usuarioActual, frecuenciaProgramacion);
	}

	public void coreEliminar(SeguridadUsuarioActual usuarioActual, String pidFrecuenciaProgramacion) throws UException {
		coreEliminar(usuarioActual, new FrecuenciaProgramacionPk(pidFrecuenciaProgramacion));
	}

}
