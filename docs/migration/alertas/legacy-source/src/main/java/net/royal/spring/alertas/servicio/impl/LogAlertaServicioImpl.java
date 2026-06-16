package net.royal.spring.alertas.servicio.impl;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.exolab.castor.types.DateTime;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import net.royal.spring.alertas.dao.impl.LogAlertaDaoImpl;
import net.royal.spring.alertas.dominio.LogAlerta;
import net.royal.spring.alertas.dominio.LogAlertaPk;
import net.royal.spring.alertas.dominio.dto.LogAlertaDto;
import net.royal.spring.alertas.dominio.filtros.FiltroPaginacionLogAlerta;
import net.royal.spring.alertas.servicio.validar.LogAlertaServicioValidar;
import net.royal.spring.framework.core.UException;
import net.royal.spring.framework.modelo.generico.DominioMensajeUsuario;
import net.royal.spring.framework.modelo.generico.DominioPaginacion;
import net.royal.spring.framework.modelo.generico.DominioParametroPersistencia;
import net.royal.spring.framework.modelo.seguridad.SeguridadUsuarioActual;
import net.royal.spring.framework.util.UFechaHora;
import net.royal.spring.framework.web.servicio.impl.GenericoServicioImpl;

@Service (value = "BeanServicioLogAlerta")
public class LogAlertaServicioImpl extends GenericoServicioImpl {
	public static String SPRING_NOMBRE = "BeanServicioLogAlerta";

	@Autowired
	private LogAlertaDaoImpl logAlertaDao;

	@Autowired
	private LogAlertaServicioValidar validar;

	@Transactional
	public LogAlerta coreInsertar(SeguridadUsuarioActual usuarioActual,LogAlerta logAlerta) throws UException {
		// valores por defecto - preparando objeto
		logAlerta = validar.prepararInsertar(usuarioActual, logAlerta);
		
		// validaciones de negocio
		List<DominioMensajeUsuario> lst = validar.coreInsertar(usuarioActual, logAlerta);
		if (!lst.isEmpty())
			throw new UException(lst);
		
		// transaccion
		return logAlertaDao.coreInsertar(logAlerta);
	}

	@Transactional
	public LogAlerta coreActualizar(SeguridadUsuarioActual usuarioActual, LogAlerta logAlerta) throws UException {
		// valores por defecto - preparando objeto
		logAlerta = validar.prepararActualizar(usuarioActual, logAlerta);
		
		// validaciones de negocio
		List<DominioMensajeUsuario> lst = validar.coreActualizar(usuarioActual, logAlerta);
		if (!lst.isEmpty())
			throw new UException(lst);
		
		// transaccion
	    logAlerta = logAlertaDao.coreActualizar(logAlerta);
		return logAlerta;
	}

	@Transactional
	public LogAlerta coreAnular(SeguridadUsuarioActual usuarioActual, LogAlerta logAlerta) throws UException {
		// valores por defecto - preparando objeto
		logAlerta = validar.prepararAnular(usuarioActual, logAlerta);
		
		// validaciones de negocio
		List<DominioMensajeUsuario> lst = validar.coreAnular(usuarioActual, logAlerta);
		if (!lst.isEmpty())
			throw new UException(lst);
		
		// transaccion
		return logAlertaDao.coreActualizar(logAlerta);
	}

	public LogAlerta coreAnular(SeguridadUsuarioActual usuarioActual, LogAlertaPk pk) throws UException {
		LogAlerta bean = logAlertaDao.obtenerPorId(pk);
		return coreAnular(usuarioActual,bean);
	}

	public LogAlerta coreAnular(SeguridadUsuarioActual usuarioActual, Integer pidLogAlerta) throws UException {
		return coreAnular(usuarioActual,new LogAlertaPk( pidLogAlerta));
	}

	@Transactional
	public void coreEliminar(SeguridadUsuarioActual usuarioActual, LogAlerta logAlerta) throws UException {
		// valores por defecto - preparando objeto
		logAlerta = validar.prepararEliminar(usuarioActual, logAlerta);
		
		// validaciones de negocio
		List<DominioMensajeUsuario> lst = validar.coreEliminar(usuarioActual, logAlerta);
		if (!lst.isEmpty())
			throw new UException(lst);
		
		// transaccion
		logAlertaDao.eliminar(logAlerta);
	}

	public void coreEliminar(SeguridadUsuarioActual usuarioActual, LogAlertaPk pk) throws UException {
		LogAlerta logAlerta = logAlertaDao.obtenerPorId(pk);
		coreEliminar(usuarioActual,logAlerta);
	}

	public void coreEliminar(SeguridadUsuarioActual usuarioActual, Integer pidLogAlerta) throws UException {
		coreEliminar(usuarioActual,new LogAlertaPk( pidLogAlerta));
	}

	public DominioPaginacion listarLogAlerta(FiltroPaginacionLogAlerta filtro) throws Exception {
		List<DominioParametroPersistencia> parametros = new ArrayList<DominioParametroPersistencia>();
		filtro.getPaginacion().setPaginacionRegistrosPorPagina(50);
		Date dd=null;
		if (filtro.getFechaInicio()!=null) {
			dd = UFechaHora.obtenerFechaHoraInicioDia(filtro.getFechaInicio());
			filtro.setFechaInicio(dd);
		}
		if (filtro.getFechaFin()!=null) {
			dd = UFechaHora.obtenerFechaHoraInicioDia(filtro.getFechaFin());
			filtro.setFechaFin(dd);
		}
		parametros.add(new DominioParametroPersistencia("idreglanegocio", BigDecimal.class, filtro.getIdreglanegocio()));
		parametros.add(new DominioParametroPersistencia("fechaPreparacionInicio", Date.class, filtro.getFechaInicio()));
		parametros.add(new DominioParametroPersistencia("fechaPreparacionFin", Date.class, filtro.getFechaFin()));	 

		Integer registros = logAlertaDao.contar("logalerta.listarcontar", parametros);
		List<?> datos = logAlertaDao.listarConPaginacion(filtro.getPaginacion(), parametros, "logalerta.listar",
				LogAlertaDto.class);
		filtro.getPaginacion().setPaginacionRegistrosPorPagina(50);
		filtro.getPaginacion().setPaginacionRegistrosEncontrados(registros.intValue());
		filtro.getPaginacion().setPaginacionListaResultado(datos);
		
		
		
		return filtro.getPaginacion();
	}

	public DominioPaginacion listarLogAlertaDetalle(FiltroPaginacionLogAlerta filtro) {
		List<DominioParametroPersistencia> parametros = new ArrayList<DominioParametroPersistencia>();
		filtro.getPaginacion().setPaginacionRegistrosPorPagina(50);


		parametros.add(new DominioParametroPersistencia("id_log_alerta", BigDecimal.class, filtro.getId_log_alerta()));
 

		Integer registros = logAlertaDao.contar("logalerta.listarDetallecontar", parametros);
		List<?> datos = logAlertaDao.listarConPaginacion(filtro.getPaginacion(), parametros, "logalerta.listarDetalle",
				LogAlertaDto.class);
		filtro.getPaginacion().setPaginacionRegistrosPorPagina(50);
		filtro.getPaginacion().setPaginacionRegistrosEncontrados(registros.intValue());
		filtro.getPaginacion().setPaginacionListaResultado(datos);
		
		
		
		return filtro.getPaginacion();
	}

	public LogAlerta ActualizarAreasNegocioEstado(LogAlerta bean) {
		logAlertaDao.actualizar(bean);
		return bean;
	}

	public DominioPaginacion listarLogAlertaAdicional(FiltroPaginacionLogAlerta filtro) {
		List<DominioParametroPersistencia> parametros = new ArrayList<DominioParametroPersistencia>();
		filtro.getPaginacion().setPaginacionRegistrosPorPagina(50);


		parametros.add(new DominioParametroPersistencia("id_log_alerta", BigDecimal.class, filtro.getId_log_alerta()));
 

		Integer registros = logAlertaDao.contar("logalerta.listarAdicionalcontar", parametros);
		List<?> datos = logAlertaDao.listarConPaginacion(filtro.getPaginacion(), parametros, "logalerta.listarAdicional",
				LogAlertaDto.class);
		filtro.getPaginacion().setPaginacionRegistrosPorPagina(50);
		filtro.getPaginacion().setPaginacionRegistrosEncontrados(registros.intValue());
		filtro.getPaginacion().setPaginacionListaResultado(datos);
		
		
		
		return filtro.getPaginacion();
	}

}
