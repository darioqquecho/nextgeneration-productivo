package net.royal.spring.alertas.servicio.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import net.royal.spring.alertas.dao.impl.FuenteAlertaAdicionalDaoImpl;
import net.royal.spring.alertas.dominio.FuenteAlertaAdicional;
import net.royal.spring.alertas.dominio.FuenteAlertaAdicionalPk;
import net.royal.spring.alertas.servicio.validar.FuenteAlertaAdicionalServicioValidar;
import net.royal.spring.framework.core.UException;
import net.royal.spring.framework.modelo.generico.DominioMensajeUsuario;
import net.royal.spring.framework.modelo.seguridad.SeguridadUsuarioActual;
import net.royal.spring.framework.web.servicio.impl.GenericoServicioImpl;

@Service (value = "BeanServicioFuenteAlertaAdicional")
public class FuenteAlertaAdicionalServicioImpl extends GenericoServicioImpl {
	public static String SPRING_NOMBRE = "BeanServicioFuenteAlertaAdicional";

	@Autowired
	private FuenteAlertaAdicionalDaoImpl fuenteAlertaAdicionalDao;

	@Autowired
	private FuenteAlertaAdicionalServicioValidar validar;

	@Transactional
	public FuenteAlertaAdicional coreInsertar(SeguridadUsuarioActual usuarioActual,FuenteAlertaAdicional fuenteAlertaAdicional) throws UException {
		// valores por defecto - preparando objeto
		fuenteAlertaAdicional = validar.prepararInsertar(usuarioActual, fuenteAlertaAdicional);
		
		// validaciones de negocio
		List<DominioMensajeUsuario> lst = validar.coreInsertar(usuarioActual, fuenteAlertaAdicional);
		if (!lst.isEmpty())
			throw new UException(lst);
		
		// transaccion
		return fuenteAlertaAdicionalDao.coreInsertar(fuenteAlertaAdicional);
	}

	@Transactional
	public FuenteAlertaAdicional coreActualizar(SeguridadUsuarioActual usuarioActual, FuenteAlertaAdicional fuenteAlertaAdicional) throws UException {
		// valores por defecto - preparando objeto
		fuenteAlertaAdicional = validar.prepararActualizar(usuarioActual, fuenteAlertaAdicional);
		
		// validaciones de negocio
		List<DominioMensajeUsuario> lst = validar.coreActualizar(usuarioActual, fuenteAlertaAdicional);
		if (!lst.isEmpty())
			throw new UException(lst);
		
		// transaccion
	    fuenteAlertaAdicional = fuenteAlertaAdicionalDao.coreActualizar(fuenteAlertaAdicional);
		return fuenteAlertaAdicional;
	}

	@Transactional
	public FuenteAlertaAdicional coreAnular(SeguridadUsuarioActual usuarioActual, FuenteAlertaAdicional fuenteAlertaAdicional) throws UException {
		// valores por defecto - preparando objeto
		fuenteAlertaAdicional = validar.prepararAnular(usuarioActual, fuenteAlertaAdicional);
		
		// validaciones de negocio
		List<DominioMensajeUsuario> lst = validar.coreAnular(usuarioActual, fuenteAlertaAdicional);
		if (!lst.isEmpty())
			throw new UException(lst);
		
		// transaccion
		return fuenteAlertaAdicionalDao.coreActualizar(fuenteAlertaAdicional);
	}

	public FuenteAlertaAdicional coreAnular(SeguridadUsuarioActual usuarioActual, FuenteAlertaAdicionalPk pk) throws UException {
		FuenteAlertaAdicional bean = fuenteAlertaAdicionalDao.obtenerPorId(pk);
		return coreAnular(usuarioActual,bean);
	}

	public FuenteAlertaAdicional coreAnular(SeguridadUsuarioActual usuarioActual, Integer pidFuenteAlerta,Integer pnroRegistro,String pcampoNombre) throws UException {
		return coreAnular(usuarioActual,new FuenteAlertaAdicionalPk( pidFuenteAlerta, pnroRegistro, pcampoNombre));
	}

	@Transactional
	public void coreEliminar(SeguridadUsuarioActual usuarioActual, FuenteAlertaAdicional fuenteAlertaAdicional) throws UException {
		// valores por defecto - preparando objeto
		fuenteAlertaAdicional = validar.prepararEliminar(usuarioActual, fuenteAlertaAdicional);
		
		// validaciones de negocio
		List<DominioMensajeUsuario> lst = validar.coreEliminar(usuarioActual, fuenteAlertaAdicional);
		if (!lst.isEmpty())
			throw new UException(lst);
		
		// transaccion
		fuenteAlertaAdicionalDao.eliminar(fuenteAlertaAdicional);
	}

	public void coreEliminar(SeguridadUsuarioActual usuarioActual, FuenteAlertaAdicionalPk pk) throws UException {
		FuenteAlertaAdicional fuenteAlertaAdicional = fuenteAlertaAdicionalDao.obtenerPorId(pk);
		coreEliminar(usuarioActual,fuenteAlertaAdicional);
	}

	public void coreEliminar(SeguridadUsuarioActual usuarioActual, Integer pidFuenteAlerta,Integer pnroRegistro,String pcampoNombre) throws UException {
		coreEliminar(usuarioActual,new FuenteAlertaAdicionalPk( pidFuenteAlerta, pnroRegistro, pcampoNombre));
	}

}
