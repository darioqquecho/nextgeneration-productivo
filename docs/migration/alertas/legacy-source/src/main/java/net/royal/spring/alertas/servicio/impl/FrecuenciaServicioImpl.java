package net.royal.spring.alertas.servicio.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import net.royal.spring.alertas.dao.impl.FrecuenciaDaoImpl;
import net.royal.spring.alertas.dominio.Frecuencia;
import net.royal.spring.alertas.dominio.FrecuenciaPk;
import net.royal.spring.alertas.servicio.validar.FrecuenciaServicioValidar;
import net.royal.spring.framework.core.UException;
import net.royal.spring.framework.modelo.generico.DominioMensajeUsuario;
import net.royal.spring.framework.modelo.seguridad.SeguridadUsuarioActual;
import net.royal.spring.framework.web.servicio.impl.GenericoServicioImpl;

@Service(value = "BeanServicioFrecuencia")
public class FrecuenciaServicioImpl extends GenericoServicioImpl {
	public static String SPRING_NOMBRE = "BeanServicioFrecuencia";

	@Autowired
	private FrecuenciaDaoImpl frecuenciaDao;

	@Autowired
	private FrecuenciaServicioValidar validar;

	@Transactional
	public Frecuencia coreInsertar(SeguridadUsuarioActual usuarioActual, Frecuencia frecuencia) throws UException {
		// valores por defecto - preparando objeto
		frecuencia = validar.prepararInsertar(usuarioActual, frecuencia);

		// validaciones de negocio
		List<DominioMensajeUsuario> lst = validar.coreInsertar(usuarioActual, frecuencia);
		if (!lst.isEmpty())
			throw new UException(lst);

		// transaccion
		return frecuenciaDao.coreInsertar(frecuencia);
	}

	@Transactional
	public Frecuencia coreActualizar(SeguridadUsuarioActual usuarioActual, Frecuencia frecuencia) throws UException {
		// valores por defecto - preparando objeto
		frecuencia = validar.prepararActualizar(usuarioActual, frecuencia);

		// validaciones de negocio
		List<DominioMensajeUsuario> lst = validar.coreActualizar(usuarioActual, frecuencia);
		if (!lst.isEmpty())
			throw new UException(lst);

		// transaccion
		frecuencia = frecuenciaDao.coreActualizar(frecuencia);
		return frecuencia;
	}

	@Transactional
	public Frecuencia coreAnular(SeguridadUsuarioActual usuarioActual, Frecuencia frecuencia) throws UException {
		// valores por defecto - preparando objeto
		frecuencia = validar.prepararAnular(usuarioActual, frecuencia);

		// validaciones de negocio
		List<DominioMensajeUsuario> lst = validar.coreAnular(usuarioActual, frecuencia);
		if (!lst.isEmpty())
			throw new UException(lst);

		// transaccion
		return frecuenciaDao.coreActualizar(frecuencia);
	}

	public Frecuencia coreAnular(SeguridadUsuarioActual usuarioActual, FrecuenciaPk pk) throws UException {
		Frecuencia bean = frecuenciaDao.obtenerPorId(pk);
		return coreAnular(usuarioActual, bean);
	}

	public Frecuencia coreAnular(SeguridadUsuarioActual usuarioActual, String pidFrecuencia) throws UException {
		return coreAnular(usuarioActual, new FrecuenciaPk(pidFrecuencia));
	}

	@Transactional
	public void coreEliminar(SeguridadUsuarioActual usuarioActual, Frecuencia frecuencia) throws UException {
		// valores por defecto - preparando objeto
		frecuencia = validar.prepararEliminar(usuarioActual, frecuencia);

		// validaciones de negocio
		List<DominioMensajeUsuario> lst = validar.coreEliminar(usuarioActual, frecuencia);
		if (!lst.isEmpty())
			throw new UException(lst);

		// transaccion
		frecuenciaDao.eliminar(frecuencia);
	}

	public void coreEliminar(SeguridadUsuarioActual usuarioActual, FrecuenciaPk pk) throws UException {
		Frecuencia frecuencia = frecuenciaDao.obtenerPorId(pk);
		coreEliminar(usuarioActual, frecuencia);
	}

	public void coreEliminar(SeguridadUsuarioActual usuarioActual, String pidFrecuencia) throws UException {
		coreEliminar(usuarioActual, new FrecuenciaPk(pidFrecuencia));
	}

}
