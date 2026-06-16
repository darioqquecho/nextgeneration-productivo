package net.royal.spring.alertas.servicio.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import net.royal.spring.alertas.dao.impl.AlineacionColumnaDaoImpl;
import net.royal.spring.alertas.dominio.AlineacionColumna;
import net.royal.spring.alertas.dominio.AlineacionColumnaPk;
import net.royal.spring.alertas.servicio.validar.AlineacionColumnaServicioValidar;
import net.royal.spring.framework.core.UException;
import net.royal.spring.framework.modelo.generico.DominioMensajeUsuario;
import net.royal.spring.framework.modelo.seguridad.SeguridadUsuarioActual;
import net.royal.spring.framework.web.servicio.impl.GenericoServicioImpl;

@Service (value = "BeanServicioAlineacionColumna")
public class AlineacionColumnaServicioImpl extends GenericoServicioImpl {
	public static String SPRING_NOMBRE = "BeanServicioAlineacionColumna";

	@Autowired
	private AlineacionColumnaDaoImpl alineacionColumnaDao;

	@Autowired
	private AlineacionColumnaServicioValidar validar;

	@Transactional
	public AlineacionColumna coreInsertar(SeguridadUsuarioActual usuarioActual,AlineacionColumna alineacionColumna) throws UException {
		// valores por defecto - preparando objeto
		alineacionColumna = validar.prepararInsertar(usuarioActual, alineacionColumna);
		
		// validaciones de negocio
		List<DominioMensajeUsuario> lst = validar.coreInsertar(usuarioActual, alineacionColumna);
		if (!lst.isEmpty())
			throw new UException(lst);
		
		// transaccion
		return alineacionColumnaDao.coreInsertar(alineacionColumna);
	}

	@Transactional
	public AlineacionColumna coreActualizar(SeguridadUsuarioActual usuarioActual, AlineacionColumna alineacionColumna) throws UException {
		// valores por defecto - preparando objeto
		alineacionColumna = validar.prepararActualizar(usuarioActual, alineacionColumna);
		
		// validaciones de negocio
		List<DominioMensajeUsuario> lst = validar.coreActualizar(usuarioActual, alineacionColumna);
		if (!lst.isEmpty())
			throw new UException(lst);
		
		// transaccion
	    alineacionColumna = alineacionColumnaDao.coreActualizar(alineacionColumna);
		return alineacionColumna;
	}

	@Transactional
	public AlineacionColumna coreAnular(SeguridadUsuarioActual usuarioActual, AlineacionColumna alineacionColumna) throws UException {
		// valores por defecto - preparando objeto
		alineacionColumna = validar.prepararAnular(usuarioActual, alineacionColumna);
		
		// validaciones de negocio
		List<DominioMensajeUsuario> lst = validar.coreAnular(usuarioActual, alineacionColumna);
		if (!lst.isEmpty())
			throw new UException(lst);
		
		// transaccion
		return alineacionColumnaDao.coreActualizar(alineacionColumna);
	}

	public AlineacionColumna coreAnular(SeguridadUsuarioActual usuarioActual, AlineacionColumnaPk pk) throws UException {
		AlineacionColumna bean = alineacionColumnaDao.obtenerPorId(pk);
		return coreAnular(usuarioActual,bean);
	}

	public AlineacionColumna coreAnular(SeguridadUsuarioActual usuarioActual, String pidAlineacionColumna) throws UException {
		return coreAnular(usuarioActual,new AlineacionColumnaPk( pidAlineacionColumna));
	}

	@Transactional
	public void coreEliminar(SeguridadUsuarioActual usuarioActual, AlineacionColumna alineacionColumna) throws UException {
		// valores por defecto - preparando objeto
		alineacionColumna = validar.prepararEliminar(usuarioActual, alineacionColumna);
		
		// validaciones de negocio
		List<DominioMensajeUsuario> lst = validar.coreEliminar(usuarioActual, alineacionColumna);
		if (!lst.isEmpty())
			throw new UException(lst);
		
		// transaccion
		alineacionColumnaDao.eliminar(alineacionColumna);
	}

	public void coreEliminar(SeguridadUsuarioActual usuarioActual, AlineacionColumnaPk pk) throws UException {
		AlineacionColumna alineacionColumna = alineacionColumnaDao.obtenerPorId(pk);
		coreEliminar(usuarioActual,alineacionColumna);
	}

	public void coreEliminar(SeguridadUsuarioActual usuarioActual, String pidAlineacionColumna) throws UException {
		coreEliminar(usuarioActual,new AlineacionColumnaPk( pidAlineacionColumna));
	}

}
