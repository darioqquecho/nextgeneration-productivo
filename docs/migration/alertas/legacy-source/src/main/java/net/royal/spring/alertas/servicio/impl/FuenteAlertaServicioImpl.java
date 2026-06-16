package net.royal.spring.alertas.servicio.impl;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.exolab.castor.types.DateTime;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import net.royal.spring.alertas.dao.impl.FuenteAlertaDaoImpl;
import net.royal.spring.alertas.dominio.FuenteAlerta;
import net.royal.spring.alertas.dominio.FuenteAlertaPk;
import net.royal.spring.alertas.dominio.dto.FuenteAlertaDto;
import net.royal.spring.alertas.dominio.dto.ListaReglaNegocio;
import net.royal.spring.alertas.dominio.filtros.FiltroPaginacionFuenteAlerta;
import net.royal.spring.alertas.rest.FuenteAlertaRest;
import net.royal.spring.alertas.servicio.validar.FuenteAlertaServicioValidar;
import net.royal.spring.framework.core.UException;
import net.royal.spring.framework.modelo.generico.DominioMensajeUsuario;
import net.royal.spring.framework.modelo.generico.DominioPaginacion;
import net.royal.spring.framework.modelo.generico.DominioParametroPersistencia;
import net.royal.spring.framework.modelo.seguridad.SeguridadUsuarioActual;
import net.royal.spring.framework.util.UFechaHora;
import net.royal.spring.framework.web.servicio.impl.GenericoServicioImpl;

@Service (value = "BeanServicioFuenteAlerta")
public class FuenteAlertaServicioImpl extends GenericoServicioImpl {
	public static String SPRING_NOMBRE = "BeanServicioFuenteAlerta";
	private static Logger logger = LogManager.getLogger(FuenteAlertaServicioImpl.class);
	
	@Autowired
	private FuenteAlertaDaoImpl fuenteAlertaDao;

	@Autowired
	private FuenteAlertaServicioValidar validar;

	@Transactional
	public FuenteAlerta coreInsertar(SeguridadUsuarioActual usuarioActual,FuenteAlerta fuenteAlerta) throws UException {
		// valores por defecto - preparando objeto
		fuenteAlerta = validar.prepararInsertar(usuarioActual, fuenteAlerta);
		
		// validaciones de negocio
		List<DominioMensajeUsuario> lst = validar.coreInsertar(usuarioActual, fuenteAlerta);
		if (!lst.isEmpty())
			throw new UException(lst);
		
		// transaccion
		return fuenteAlertaDao.coreInsertar(fuenteAlerta);
	}

	@Transactional
	public FuenteAlerta coreActualizar(SeguridadUsuarioActual usuarioActual, FuenteAlerta fuenteAlerta) throws UException {
		// valores por defecto - preparando objeto
		fuenteAlerta = validar.prepararActualizar(usuarioActual, fuenteAlerta);
		
		// validaciones de negocio
		List<DominioMensajeUsuario> lst = validar.coreActualizar(usuarioActual, fuenteAlerta);
		if (!lst.isEmpty())
			throw new UException(lst);
		
		// transaccion
	    fuenteAlerta = fuenteAlertaDao.coreActualizar(fuenteAlerta);
		return fuenteAlerta;
	}

	@Transactional
	public FuenteAlerta coreAnular(SeguridadUsuarioActual usuarioActual, FuenteAlerta fuenteAlerta) throws UException {
		// valores por defecto - preparando objeto
		fuenteAlerta = validar.prepararAnular(usuarioActual, fuenteAlerta);
		
		// validaciones de negocio
		List<DominioMensajeUsuario> lst = validar.coreAnular(usuarioActual, fuenteAlerta);
		if (!lst.isEmpty())
			throw new UException(lst);
		
		// transaccion
		return fuenteAlertaDao.coreActualizar(fuenteAlerta);
	}

	public FuenteAlerta coreAnular(SeguridadUsuarioActual usuarioActual, FuenteAlertaPk pk) throws UException {
		FuenteAlerta bean = fuenteAlertaDao.obtenerPorId(pk);
		return coreAnular(usuarioActual,bean);
	}

	public FuenteAlerta coreAnular(SeguridadUsuarioActual usuarioActual, Integer pidFuenteAlerta) throws UException {
		return coreAnular(usuarioActual,new FuenteAlertaPk( pidFuenteAlerta));
	}

	@Transactional
	public void coreEliminar(SeguridadUsuarioActual usuarioActual, FuenteAlerta fuenteAlerta) throws UException {
		// valores por defecto - preparando objeto
		fuenteAlerta = validar.prepararEliminar(usuarioActual, fuenteAlerta);
		
		// validaciones de negocio
		List<DominioMensajeUsuario> lst = validar.coreEliminar(usuarioActual, fuenteAlerta);
		if (!lst.isEmpty())
			throw new UException(lst);
		
		// transaccion
		fuenteAlertaDao.eliminar(fuenteAlerta);
	}

	public void coreEliminar(SeguridadUsuarioActual usuarioActual, FuenteAlertaPk pk) throws UException {
		FuenteAlerta fuenteAlerta = fuenteAlertaDao.obtenerPorId(pk);
		coreEliminar(usuarioActual,fuenteAlerta);
	}

	public void coreEliminar(SeguridadUsuarioActual usuarioActual, Integer pidFuenteAlerta) throws UException {
		coreEliminar(usuarioActual,new FuenteAlertaPk( pidFuenteAlerta));
	}

	public DominioPaginacion listarFuenteAlerta(FiltroPaginacionFuenteAlerta filtro) throws Exception {
		List<DominioParametroPersistencia> parametros = new ArrayList<DominioParametroPersistencia>();
		filtro.getPaginacion().setPaginacionRegistrosPorPagina(50);
		Date dd=null;
		if(filtro.getIdFuenteAlerta() == null) {
			filtro.setIdFuenteAlerta(null);
		}
		if (filtro.getFechaPreparacionInicio()!=null) {
			dd = UFechaHora.obtenerFechaHoraInicioDia(filtro.getFechaPreparacionInicio());
			filtro.setFechaPreparacionInicio(dd);
		}
		if (filtro.getFechaPreparacionFin()!=null) {
			dd = UFechaHora.obtenerFechaHoraFinDia(filtro.getFechaPreparacionFin());
			filtro.setFechaPreparacionFin(dd);
		}
		
		logger.debug("listarFuenteAlerta");
		logger.debug(filtro.getFechaPreparacionInicio());
		logger.debug(filtro.getFechaPreparacionFin());
		
		parametros.add(new DominioParametroPersistencia("idreglanegocio", BigDecimal.class, filtro.getIdFuenteAlerta()));
		parametros.add(new DominioParametroPersistencia("fechaPreparacionInicio", Date.class, filtro.getFechaPreparacionInicio()));
		parametros.add(new DominioParametroPersistencia("fechaPreparacionFin", Date.class, filtro.getFechaPreparacionFin()));
	 

		Integer registros = fuenteAlertaDao.contar("fuentealerta.contar", parametros);
		List<?> datos = fuenteAlertaDao.listarConPaginacion(filtro.getPaginacion(), parametros, "fuentealerta.listar",
				FuenteAlertaDto.class);
		filtro.getPaginacion().setPaginacionRegistrosPorPagina(50);
		filtro.getPaginacion().setPaginacionRegistrosEncontrados(registros.intValue());
		filtro.getPaginacion().setPaginacionListaResultado(datos);
		
		
		
		return filtro.getPaginacion();
	}

	public List<?> listarReglaNegocio() {
		List<?> datos = fuenteAlertaDao.listarPorQuery(ListaReglaNegocio.class, "fuentealerta.listarReglaNegocio");
		return datos;
	}
	
	
	@Transactional
	public void eliminarFuentes(SeguridadUsuarioActual usuarioActual, FuenteAlerta fuenteAlerta) throws UException {
		List<DominioParametroPersistencia> parametros = new ArrayList<DominioParametroPersistencia>();
		parametros.add(new DominioParametroPersistencia("id_fuente_alerta", Integer.class, fuenteAlerta.getPk().getIdFuenteAlerta()));
		fuenteAlertaDao.ejecutarPorQuery("fuentealerta.eliminarAdicional", parametros);
		fuenteAlertaDao.ejecutarPorQuery("fuentealerta.eliminarDetalle", parametros);
		fuenteAlertaDao.ejecutarPorQuery("fuentealerta.eliminar", parametros);
	}
	

}
