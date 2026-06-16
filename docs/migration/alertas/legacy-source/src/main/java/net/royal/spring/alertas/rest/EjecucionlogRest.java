package net.royal.spring.alertas.rest;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.exolab.castor.types.DateTime;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import net.royal.spring.alertas.dao.impl.AlertaDaoImpl;
import net.royal.spring.alertas.dao.impl.ReglaNegocioDaoImpl;
import net.royal.spring.alertas.dao.impl.TipoDatoDaoImpl;
import net.royal.spring.alertas.dominio.Alerta;
import net.royal.spring.alertas.dominio.AlertaPk;
import net.royal.spring.alertas.dominio.dto.DtoEjecucionlog;
import net.royal.spring.alertas.dominio.filtros.FiltroEjecucionlog;
import net.royal.spring.alertas.dominio.filtros.FiltroPaginacionAlerta;
import net.royal.spring.alertas.servicio.impl.AlertaServicioImpl;
import net.royal.spring.alertas.servicio.validar.AlertaServicioValidar;
import net.royal.spring.framework.core.UException;
import net.royal.spring.framework.modelo.generico.DominioMensajeUsuario;
import net.royal.spring.framework.modelo.generico.DominioPaginacion;
import net.royal.spring.framework.modelo.generico.DominioParametroPersistencia;
import net.royal.spring.framework.modelo.seguridad.SeguridadUsuarioActual;
import net.royal.spring.framework.util.UFechaHora;
import net.royal.spring.framework.util.UInteger;
import net.royal.spring.framework.util.UString;
import net.royal.spring.framework.web.rest.GenericoRest;

@RestController
@RequestMapping("/spring/alertas/ejecucionlog")
@CrossOrigin(origins = "*")
public class EjecucionlogRest extends GenericoRest {

	private static Logger logger = LogManager.getLogger(EjecucionlogRest.class);

	@Autowired
	private TipoDatoDaoImpl consulta;
	
	@Autowired
	private ReglaNegocioDaoImpl reglaNegocioDao;

	@Transactional
	@PostMapping(value = "/listar", produces = MediaType.APPLICATION_JSON_VALUE)
	public DominioPaginacion listar(@RequestBody FiltroEjecucionlog filtro) throws Exception {
		logger.debug("EjecucionlogRest.listar");
		List<DominioParametroPersistencia> parametros = new ArrayList<DominioParametroPersistencia>();
		Date dd=null;
		if (filtro.getFechaPreparacionInicio()!=null) {
			dd = UFechaHora.obtenerFechaHoraInicioDia(filtro.getFechaPreparacionInicio());
			filtro.setFechaPreparacionInicio(dd);
		}
		if (filtro.getFechaPreparacionFin()!=null) {
			dd = UFechaHora.obtenerFechaHoraInicioDia(filtro.getFechaPreparacionFin());
			filtro.setFechaPreparacionFin(dd);
		}		
		parametros.add(new DominioParametroPersistencia("idreglanegocio", BigDecimal.class, filtro.getIdreglanegocio()));
		parametros.add(new DominioParametroPersistencia("fechaPreparacionInicio", Date.class,filtro.getFechaPreparacionInicio()));
		parametros.add(new DominioParametroPersistencia("fechaPreparacionFin", Date.class, filtro.getFechaPreparacionFin()));
		
		Integer registros = consulta.contar("tipodato.ejecucionlogContarlistarPaginacion", parametros);
		List datos = consulta.listarConPaginacion(filtro.getPaginacion(), parametros, "tipodato.ejecucionlogListarPaginacion", DtoEjecucionlog.class);
		filtro.getPaginacion().setPaginacionRegistrosPorPagina(10);
		filtro.getPaginacion().setPaginacionRegistrosEncontrados(registros.intValue());
		filtro.getPaginacion().setPaginacionListaResultado(datos);
		logger.debug("EjecucionlogRest.listarErrores-Fin");
		return filtro.getPaginacion();
	}
	
	@Transactional
	@PostMapping(value = "/limpiarDatod", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Void> limpiarInformativos(@RequestBody DtoEjecucionlog bean) throws Exception {
		logger.debug("EjecucionlogRest.limpiarDatod");
        consulta.limpiarInformativos();
        return new ResponseEntity<>(HttpStatus.OK);
	}
	
	@Transactional
	@PostMapping(value = "/registrar", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<DtoEjecucionlog> registrar(@RequestBody DtoEjecucionlog dto) throws Exception {	
		//logger.debug("EjecucionlogRest.registrar RN:" + UInteger.obtenerValorEnteroSinNulo(dto.getReglaNegocioId()).toString() );
		try {
			//dto = reglaNegocioDao.registrarEjecucionLogCore(dto);			
			//logger.debug(dto.getTransaccionEstado() +" RN:" + UInteger.obtenerValorEnteroSinNulo(dto.getReglaNegocioId()).toString()  );
			//logger.debug(dto.getJavalog());
			//logger.debug(dto.getSplog());
			//logger.debug(dto.getReglaNegocioId());
			
			if ( UString.esNuloVacioTrim(dto.getJavalog()) )
				dto.setJavalog("-");
			if ( UString.esNuloVacioTrim(dto.getSplog()) )
				dto.setSplog("-");
			if ( UString.esNuloVacioTrim(dto.getResumenlog()) )
				dto.setResumenlog("-");
			
			if ( UInteger.esCeroOrNulo(dto.getReglaNegocioId()) )
				dto.setReglaNegocioId(-1);
			if ( UInteger.esCeroOrNulo(dto.getRegistrosExito()) )
				dto.setRegistrosExito(-1);
			if ( UInteger.esCeroOrNulo(dto.getRegistrosError()) )
				dto.setRegistrosError(-1);
			
			List<DominioParametroPersistencia> parametros = new ArrayList<DominioParametroPersistencia>();
			parametros.add(new DominioParametroPersistencia("p_javalog", String.class, dto.getJavalog()));
			parametros.add(new DominioParametroPersistencia("p_splog", String.class, dto.getSplog()));
			parametros.add(new DominioParametroPersistencia("p_resumenlog", String.class, dto.getResumenlog()));
			parametros.add(new DominioParametroPersistencia("p_rn_id", Integer.class, dto.getReglaNegocioId()));				
			parametros.add(new DominioParametroPersistencia("p_reg_exito", Integer.class, dto.getRegistrosExito()));
			parametros.add(new DominioParametroPersistencia("p_reg_error", Integer.class, dto.getRegistrosError()));				
			reglaNegocioDao.ejecutarPorQuery("reglanegocio.registrarLog",parametros);
		} catch (Exception e) {
			logger.error("EjecucionlogRest.registrar RN:" + UInteger.obtenerValorEnteroSinNulo(dto.getReglaNegocioId()).toString());
			logger.error(dto.getJavalog());
			logger.error(dto.getSplog());
			logger.error(dto.getReglaNegocioId());
			logger.error(UException.getStackTrace(e));
			e.printStackTrace();
		}		
		return new ResponseEntity<DtoEjecucionlog>(dto,HttpStatus.OK);
	}
	
}
