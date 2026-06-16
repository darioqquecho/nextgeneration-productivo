package net.royal.spring.alertas.servicio.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import net.royal.spring.alertas.dao.impl.ProveedorBdDaoImpl;
import net.royal.spring.alertas.dominio.ProveedorBd;
import net.royal.spring.alertas.dominio.ProveedorBdPk;
import net.royal.spring.alertas.servicio.validar.ProveedorBdServicioValidar;
import net.royal.spring.framework.core.UException;
import net.royal.spring.framework.modelo.generico.DominioMensajeUsuario;
import net.royal.spring.framework.modelo.seguridad.SeguridadUsuarioActual;
import net.royal.spring.framework.web.servicio.impl.GenericoServicioImpl;

@Service (value = "BeanServicioProveedorBd")
public class ProveedorBdServicioImpl extends GenericoServicioImpl {
	public static String SPRING_NOMBRE = "BeanServicioProveedorBd";

	@Autowired
	private ProveedorBdDaoImpl proveedorBdDao;

	@Autowired
	private ProveedorBdServicioValidar validar;

	@Transactional
	public ProveedorBd coreInsertar(SeguridadUsuarioActual usuarioActual,ProveedorBd proveedorBd) throws UException {
		// valores por defecto - preparando objeto
		proveedorBd = validar.prepararInsertar(usuarioActual, proveedorBd);
		
		// validaciones de negocio
		List<DominioMensajeUsuario> lst = validar.coreInsertar(usuarioActual, proveedorBd);
		if (!lst.isEmpty())
			throw new UException(lst);
		
		// transaccion
		return proveedorBdDao.coreInsertar(proveedorBd);
	}

	@Transactional
	public ProveedorBd coreActualizar(SeguridadUsuarioActual usuarioActual, ProveedorBd proveedorBd) throws UException {
		// valores por defecto - preparando objeto
		proveedorBd = validar.prepararActualizar(usuarioActual, proveedorBd);
		
		// validaciones de negocio
		List<DominioMensajeUsuario> lst = validar.coreActualizar(usuarioActual, proveedorBd);
		if (!lst.isEmpty())
			throw new UException(lst);
		
		// transaccion
	    proveedorBd = proveedorBdDao.coreActualizar(proveedorBd);
		return proveedorBd;
	}

	@Transactional
	public ProveedorBd coreAnular(SeguridadUsuarioActual usuarioActual, ProveedorBd proveedorBd) throws UException {
		// valores por defecto - preparando objeto
		proveedorBd = validar.prepararAnular(usuarioActual, proveedorBd);
		
		// validaciones de negocio
		List<DominioMensajeUsuario> lst = validar.coreAnular(usuarioActual, proveedorBd);
		if (!lst.isEmpty())
			throw new UException(lst);
		
		// transaccion
		return proveedorBdDao.coreActualizar(proveedorBd);
	}

	public ProveedorBd coreAnular(SeguridadUsuarioActual usuarioActual, ProveedorBdPk pk) throws UException {
		ProveedorBd bean = proveedorBdDao.obtenerPorId(pk);
		return coreAnular(usuarioActual,bean);
	}

	public ProveedorBd coreAnular(SeguridadUsuarioActual usuarioActual, String pidProveedorBd) throws UException {
		return coreAnular(usuarioActual,new ProveedorBdPk( pidProveedorBd));
	}

	@Transactional
	public void coreEliminar(SeguridadUsuarioActual usuarioActual, ProveedorBd proveedorBd) throws UException {
		// valores por defecto - preparando objeto
		proveedorBd = validar.prepararEliminar(usuarioActual, proveedorBd);
		
		// validaciones de negocio
		List<DominioMensajeUsuario> lst = validar.coreEliminar(usuarioActual, proveedorBd);
		if (!lst.isEmpty())
			throw new UException(lst);
		
		// transaccion
		proveedorBdDao.eliminar(proveedorBd);
	}

	public void coreEliminar(SeguridadUsuarioActual usuarioActual, ProveedorBdPk pk) throws UException {
		ProveedorBd proveedorBd = proveedorBdDao.obtenerPorId(pk);
		coreEliminar(usuarioActual,proveedorBd);
	}

	public void coreEliminar(SeguridadUsuarioActual usuarioActual, String pidProveedorBd) throws UException {
		coreEliminar(usuarioActual,new ProveedorBdPk( pidProveedorBd));
	}

}
