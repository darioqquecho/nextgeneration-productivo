package net.royal.spring.alertas.servicio.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import net.royal.spring.alertas.dao.impl.FrecuenciaMensualDaoImpl;
import net.royal.spring.alertas.dominio.FrecuenciaMensual;
import net.royal.spring.alertas.dominio.FrecuenciaMensualPk;
import net.royal.spring.alertas.servicio.validar.FrecuenciaMensualServicioValidar;
import net.royal.spring.framework.core.UException;
import net.royal.spring.framework.modelo.generico.DominioMensajeUsuario;
import net.royal.spring.framework.modelo.seguridad.SeguridadUsuarioActual;
import net.royal.spring.framework.web.servicio.impl.GenericoServicioImpl;

@Service(value = "BeanServicioFrecuenciaMensual")
public class FrecuenciaMensualServicioImpl extends GenericoServicioImpl {
	public static String SPRING_NOMBRE = "BeanServicioFrecuenciaMensual";

	@Autowired
	private FrecuenciaMensualDaoImpl frecuenciaMensualDao;

	@Autowired
	private FrecuenciaMensualServicioValidar validar;

	@Transactional
	public FrecuenciaMensual coreInsertar(SeguridadUsuarioActual usuarioActual, FrecuenciaMensual frecuenciaMensual)
			throws UException {
		// valores por defecto - preparando objeto
		frecuenciaMensual = validar.prepararInsertar(usuarioActual, frecuenciaMensual);

		// validaciones de negocio
		List<DominioMensajeUsuario> lst = validar.coreInsertar(usuarioActual, frecuenciaMensual);
		if (!lst.isEmpty())
			throw new UException(lst);

		// transaccion
		return frecuenciaMensualDao.coreInsertar(frecuenciaMensual);
	}

	@Transactional
	public FrecuenciaMensual coreActualizar(SeguridadUsuarioActual usuarioActual, FrecuenciaMensual frecuenciaMensual)
			throws UException {
		// valores por defecto - preparando objeto
		frecuenciaMensual = validar.prepararActualizar(usuarioActual, frecuenciaMensual);

		// validaciones de negocio
		List<DominioMensajeUsuario> lst = validar.coreActualizar(usuarioActual, frecuenciaMensual);
		if (!lst.isEmpty())
			throw new UException(lst);

		// transaccion
		frecuenciaMensual = frecuenciaMensualDao.coreActualizar(frecuenciaMensual);
		return frecuenciaMensual;
	}

	@Transactional
	public FrecuenciaMensual coreAnular(SeguridadUsuarioActual usuarioActual, FrecuenciaMensual frecuenciaMensual)
			throws UException {
		// valores por defecto - preparando objeto
		frecuenciaMensual = validar.prepararAnular(usuarioActual, frecuenciaMensual);

		// validaciones de negocio
		List<DominioMensajeUsuario> lst = validar.coreAnular(usuarioActual, frecuenciaMensual);
		if (!lst.isEmpty())
			throw new UException(lst);

		// transaccion
		return frecuenciaMensualDao.coreActualizar(frecuenciaMensual);
	}

	public FrecuenciaMensual coreAnular(SeguridadUsuarioActual usuarioActual, FrecuenciaMensualPk pk)
			throws UException {
		FrecuenciaMensual bean = frecuenciaMensualDao.obtenerPorId(pk);
		return coreAnular(usuarioActual, bean);
	}

	public FrecuenciaMensual coreAnular(SeguridadUsuarioActual usuarioActual, String pidFrecuenciaMensual)
			throws UException {
		return coreAnular(usuarioActual, new FrecuenciaMensualPk(pidFrecuenciaMensual));
	}

	@Transactional
	public void coreEliminar(SeguridadUsuarioActual usuarioActual, FrecuenciaMensual frecuenciaMensual)
			throws UException {
		// valores por defecto - preparando objeto
		frecuenciaMensual = validar.prepararEliminar(usuarioActual, frecuenciaMensual);

		// validaciones de negocio
		List<DominioMensajeUsuario> lst = validar.coreEliminar(usuarioActual, frecuenciaMensual);
		if (!lst.isEmpty())
			throw new UException(lst);

		// transaccion
		frecuenciaMensualDao.eliminar(frecuenciaMensual);
	}

	public void coreEliminar(SeguridadUsuarioActual usuarioActual, FrecuenciaMensualPk pk) throws UException {
		FrecuenciaMensual frecuenciaMensual = frecuenciaMensualDao.obtenerPorId(pk);
		coreEliminar(usuarioActual, frecuenciaMensual);
	}

	public void coreEliminar(SeguridadUsuarioActual usuarioActual, String pidFrecuenciaMensual) throws UException {
		coreEliminar(usuarioActual, new FrecuenciaMensualPk(pidFrecuenciaMensual));
	}

}
