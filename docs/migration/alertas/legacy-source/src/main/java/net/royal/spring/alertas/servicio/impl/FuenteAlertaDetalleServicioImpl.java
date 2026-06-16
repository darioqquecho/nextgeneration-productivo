package net.royal.spring.alertas.servicio.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import net.royal.spring.alertas.dao.impl.FuenteAlertaDetalleDaoImpl;
import net.royal.spring.alertas.dominio.FuenteAlertaDetalle;
import net.royal.spring.alertas.dominio.FuenteAlertaDetallePk;
import net.royal.spring.alertas.servicio.validar.FuenteAlertaDetalleServicioValidar;
import net.royal.spring.framework.core.UException;
import net.royal.spring.framework.modelo.generico.DominioMensajeUsuario;
import net.royal.spring.framework.modelo.seguridad.SeguridadUsuarioActual;
import net.royal.spring.framework.web.servicio.impl.GenericoServicioImpl;

@Service (value = "BeanServicioFuenteAlertaDetalle")
public class FuenteAlertaDetalleServicioImpl extends GenericoServicioImpl {
	public static String SPRING_NOMBRE = "BeanServicioFuenteAlertaDetalle";

	@Autowired
	private FuenteAlertaDetalleDaoImpl fuenteAlertaDetalleDao;

	@Autowired
	private FuenteAlertaDetalleServicioValidar validar;

	@Transactional
	public FuenteAlertaDetalle coreInsertar(SeguridadUsuarioActual usuarioActual,FuenteAlertaDetalle fuenteAlertaDetalle) throws UException {
		// valores por defecto - preparando objeto
		fuenteAlertaDetalle = validar.prepararInsertar(usuarioActual, fuenteAlertaDetalle);
		
		// validaciones de negocio
		List<DominioMensajeUsuario> lst = validar.coreInsertar(usuarioActual, fuenteAlertaDetalle);
		if (!lst.isEmpty())
			throw new UException(lst);
		
		// transaccion
		return fuenteAlertaDetalleDao.coreInsertar(fuenteAlertaDetalle);
	}

	@Transactional
	public FuenteAlertaDetalle coreActualizar(SeguridadUsuarioActual usuarioActual, FuenteAlertaDetalle fuenteAlertaDetalle) throws UException {
		// valores por defecto - preparando objeto
		fuenteAlertaDetalle = validar.prepararActualizar(usuarioActual, fuenteAlertaDetalle);
		
		// validaciones de negocio
		List<DominioMensajeUsuario> lst = validar.coreActualizar(usuarioActual, fuenteAlertaDetalle);
		if (!lst.isEmpty())
			throw new UException(lst);
		
		// transaccion
	    fuenteAlertaDetalle = fuenteAlertaDetalleDao.coreActualizar(fuenteAlertaDetalle);
		return fuenteAlertaDetalle;
	}

	@Transactional
	public FuenteAlertaDetalle coreAnular(SeguridadUsuarioActual usuarioActual, FuenteAlertaDetalle fuenteAlertaDetalle) throws UException {
		// valores por defecto - preparando objeto
		fuenteAlertaDetalle = validar.prepararAnular(usuarioActual, fuenteAlertaDetalle);
		
		// validaciones de negocio
		List<DominioMensajeUsuario> lst = validar.coreAnular(usuarioActual, fuenteAlertaDetalle);
		if (!lst.isEmpty())
			throw new UException(lst);
		
		// transaccion
		return fuenteAlertaDetalleDao.coreActualizar(fuenteAlertaDetalle);
	}

	public FuenteAlertaDetalle coreAnular(SeguridadUsuarioActual usuarioActual, FuenteAlertaDetallePk pk) throws UException {
		FuenteAlertaDetalle bean = fuenteAlertaDetalleDao.obtenerPorId(pk);
		return coreAnular(usuarioActual,bean);
	}

	public FuenteAlertaDetalle coreAnular(SeguridadUsuarioActual usuarioActual, Integer pidFuenteAlerta,Integer pnroRegistro,String pcampoNombre) throws UException {
		return coreAnular(usuarioActual,new FuenteAlertaDetallePk( pidFuenteAlerta, pnroRegistro, pcampoNombre));
	}

	@Transactional
	public void coreEliminar(SeguridadUsuarioActual usuarioActual, FuenteAlertaDetalle fuenteAlertaDetalle) throws UException {
		// valores por defecto - preparando objeto
		fuenteAlertaDetalle = validar.prepararEliminar(usuarioActual, fuenteAlertaDetalle);
		
		// validaciones de negocio
		List<DominioMensajeUsuario> lst = validar.coreEliminar(usuarioActual, fuenteAlertaDetalle);
		if (!lst.isEmpty())
			throw new UException(lst);
		
		// transaccion
		fuenteAlertaDetalleDao.eliminar(fuenteAlertaDetalle);
	}

	public void coreEliminar(SeguridadUsuarioActual usuarioActual, FuenteAlertaDetallePk pk) throws UException {
		FuenteAlertaDetalle fuenteAlertaDetalle = fuenteAlertaDetalleDao.obtenerPorId(pk);
		coreEliminar(usuarioActual,fuenteAlertaDetalle);
	}

	public void coreEliminar(SeguridadUsuarioActual usuarioActual, Integer pidFuenteAlerta,Integer pnroRegistro,String pcampoNombre) throws UException {
		coreEliminar(usuarioActual,new FuenteAlertaDetallePk( pidFuenteAlerta, pnroRegistro, pcampoNombre));
	}

}
