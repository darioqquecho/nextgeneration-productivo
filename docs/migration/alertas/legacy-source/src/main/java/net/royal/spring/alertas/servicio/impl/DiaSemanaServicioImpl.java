package net.royal.spring.alertas.servicio.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import net.royal.spring.alertas.dao.impl.DiaSemanaDaoImpl;
import net.royal.spring.alertas.dominio.DiaSemana;
import net.royal.spring.alertas.dominio.DiaSemanaPk;
import net.royal.spring.alertas.servicio.validar.DiaSemanaServicioValidar;
import net.royal.spring.framework.core.UException;
import net.royal.spring.framework.modelo.generico.DominioMensajeUsuario;
import net.royal.spring.framework.modelo.seguridad.SeguridadUsuarioActual;
import net.royal.spring.framework.web.servicio.impl.GenericoServicioImpl;

@Service(value = "BeanServicioDiaSemana")
public class DiaSemanaServicioImpl extends GenericoServicioImpl {
	public static String SPRING_NOMBRE = "BeanServicioDiaSemana";

	@Autowired
	private DiaSemanaDaoImpl diaSemanaDao;

	@Autowired
	private DiaSemanaServicioValidar validar;

	@Transactional
	public DiaSemana coreInsertar(SeguridadUsuarioActual usuarioActual, DiaSemana diaSemana) throws UException {
		// valores por defecto - preparando objeto
		diaSemana = validar.prepararInsertar(usuarioActual, diaSemana);

		// validaciones de negocio
		List<DominioMensajeUsuario> lst = validar.coreInsertar(usuarioActual, diaSemana);
		if (!lst.isEmpty())
			throw new UException(lst);

		// transaccion
		return diaSemanaDao.coreInsertar(diaSemana);
	}

	@Transactional
	public DiaSemana coreActualizar(SeguridadUsuarioActual usuarioActual, DiaSemana diaSemana) throws UException {
		// valores por defecto - preparando objeto
		diaSemana = validar.prepararActualizar(usuarioActual, diaSemana);

		// validaciones de negocio
		List<DominioMensajeUsuario> lst = validar.coreActualizar(usuarioActual, diaSemana);
		if (!lst.isEmpty())
			throw new UException(lst);

		// transaccion
		diaSemana = diaSemanaDao.coreActualizar(diaSemana);
		return diaSemana;
	}

	@Transactional
	public DiaSemana coreAnular(SeguridadUsuarioActual usuarioActual, DiaSemana diaSemana) throws UException {
		// valores por defecto - preparando objeto
		diaSemana = validar.prepararAnular(usuarioActual, diaSemana);

		// validaciones de negocio
		List<DominioMensajeUsuario> lst = validar.coreAnular(usuarioActual, diaSemana);
		if (!lst.isEmpty())
			throw new UException(lst);

		// transaccion
		return diaSemanaDao.coreActualizar(diaSemana);
	}

	public DiaSemana coreAnular(SeguridadUsuarioActual usuarioActual, DiaSemanaPk pk) throws UException {
		DiaSemana bean = diaSemanaDao.obtenerPorId(pk);
		return coreAnular(usuarioActual, bean);
	}

	public DiaSemana coreAnular(SeguridadUsuarioActual usuarioActual, String pidDiaSemana) throws UException {
		return coreAnular(usuarioActual, new DiaSemanaPk(pidDiaSemana));
	}

	@Transactional
	public void coreEliminar(SeguridadUsuarioActual usuarioActual, DiaSemana diaSemana) throws UException {
		// valores por defecto - preparando objeto
		diaSemana = validar.prepararEliminar(usuarioActual, diaSemana);

		// validaciones de negocio
		List<DominioMensajeUsuario> lst = validar.coreEliminar(usuarioActual, diaSemana);
		if (!lst.isEmpty())
			throw new UException(lst);

		// transaccion
		diaSemanaDao.eliminar(diaSemana);
	}

	public void coreEliminar(SeguridadUsuarioActual usuarioActual, DiaSemanaPk pk) throws UException {
		DiaSemana diaSemana = diaSemanaDao.obtenerPorId(pk);
		coreEliminar(usuarioActual, diaSemana);
	}

	public void coreEliminar(SeguridadUsuarioActual usuarioActual, String pidDiaSemana) throws UException {
		coreEliminar(usuarioActual, new DiaSemanaPk(pidDiaSemana));
	}

}
