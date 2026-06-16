package net.royal.spring.alertas.servicio.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import net.royal.spring.alertas.dao.impl.FuenteHistoriaDaoImpl;
import net.royal.spring.alertas.dominio.FuenteHistoria;
import net.royal.spring.alertas.dominio.FuenteHistoriaPk;
import net.royal.spring.alertas.servicio.validar.FuenteHistoriaServicioValidar;
import net.royal.spring.framework.core.UException;
import net.royal.spring.framework.modelo.generico.DominioMensajeUsuario;
import net.royal.spring.framework.modelo.seguridad.SeguridadUsuarioActual;
import net.royal.spring.framework.web.servicio.impl.GenericoServicioImpl;

@Service (value = "BeanServicioFuenteHistoria")
public class FuenteHistoriaServicioImpl extends GenericoServicioImpl {
	public static String SPRING_NOMBRE = "BeanServicioFuenteHistoria";

	@Autowired
	private FuenteHistoriaDaoImpl fuenteHistoriaDao;

	@Autowired
	private FuenteHistoriaServicioValidar validar;

	@Transactional
	public FuenteHistoria coreInsertar(SeguridadUsuarioActual usuarioActual,FuenteHistoria fuenteHistoria) throws UException {
		// valores por defecto - preparando objeto
		fuenteHistoria = validar.prepararInsertar(usuarioActual, fuenteHistoria);
		
		// validaciones de negocio
		List<DominioMensajeUsuario> lst = validar.coreInsertar(usuarioActual, fuenteHistoria);
		if (!lst.isEmpty())
			throw new UException(lst);
		
		// transaccion
		return fuenteHistoriaDao.coreInsertar(fuenteHistoria);
	}

	@Transactional
	public FuenteHistoria coreActualizar(SeguridadUsuarioActual usuarioActual, FuenteHistoria fuenteHistoria) throws UException {
		// valores por defecto - preparando objeto
		fuenteHistoria = validar.prepararActualizar(usuarioActual, fuenteHistoria);
		
		// validaciones de negocio
		List<DominioMensajeUsuario> lst = validar.coreActualizar(usuarioActual, fuenteHistoria);
		if (!lst.isEmpty())
			throw new UException(lst);
		
		// transaccion
	    fuenteHistoria = fuenteHistoriaDao.coreActualizar(fuenteHistoria);
		return fuenteHistoria;
	}

	@Transactional
	public FuenteHistoria coreAnular(SeguridadUsuarioActual usuarioActual, FuenteHistoria fuenteHistoria) throws UException {
		// valores por defecto - preparando objeto
		fuenteHistoria = validar.prepararAnular(usuarioActual, fuenteHistoria);
		
		// validaciones de negocio
		List<DominioMensajeUsuario> lst = validar.coreAnular(usuarioActual, fuenteHistoria);
		if (!lst.isEmpty())
			throw new UException(lst);
		
		// transaccion
		return fuenteHistoriaDao.coreActualizar(fuenteHistoria);
	}

	public FuenteHistoria coreAnular(SeguridadUsuarioActual usuarioActual, FuenteHistoriaPk pk) throws UException {
		FuenteHistoria bean = fuenteHistoriaDao.obtenerPorId(pk);
		return coreAnular(usuarioActual,bean);
	}

	public FuenteHistoria coreAnular(SeguridadUsuarioActual usuarioActual, Integer pidReglaNegocio,Integer pidFuenteAlerta,Integer pnroRegistro) throws UException {
		return coreAnular(usuarioActual,new FuenteHistoriaPk( pidReglaNegocio, pidFuenteAlerta, pnroRegistro));
	}

	@Transactional
	public void coreEliminar(SeguridadUsuarioActual usuarioActual, FuenteHistoria fuenteHistoria) throws UException {
		// valores por defecto - preparando objeto
		fuenteHistoria = validar.prepararEliminar(usuarioActual, fuenteHistoria);
		
		// validaciones de negocio
		List<DominioMensajeUsuario> lst = validar.coreEliminar(usuarioActual, fuenteHistoria);
		if (!lst.isEmpty())
			throw new UException(lst);
		
		// transaccion
		fuenteHistoriaDao.eliminar(fuenteHistoria);
	}

	public void coreEliminar(SeguridadUsuarioActual usuarioActual, FuenteHistoriaPk pk) throws UException {
		FuenteHistoria fuenteHistoria = fuenteHistoriaDao.obtenerPorId(pk);
		coreEliminar(usuarioActual,fuenteHistoria);
	}

	public void coreEliminar(SeguridadUsuarioActual usuarioActual, Integer pidReglaNegocio,Integer pidFuenteAlerta,Integer pnroRegistro) throws UException {
		coreEliminar(usuarioActual,new FuenteHistoriaPk( pidReglaNegocio, pidFuenteAlerta, pnroRegistro));
	}
	
	
}
