package net.royal.spring.alertas.rest;

import java.util.Date;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
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

import net.royal.spring.alertas.dao.impl.LogAlertaDaoImpl;
import net.royal.spring.alertas.dominio.LogAlerta;
import net.royal.spring.alertas.dominio.LogAlertaPk;
import net.royal.spring.alertas.dominio.filtros.FiltroPaginacionLogAlerta;
import net.royal.spring.alertas.servicio.impl.LogAlertaServicioImpl;
import net.royal.spring.alertas.servicio.validar.LogAlertaServicioValidar;
import net.royal.spring.framework.modelo.generico.DominioMensajeUsuario;
import net.royal.spring.framework.modelo.generico.DominioPaginacion;
import net.royal.spring.framework.web.rest.GenericoRest;

@RestController
@RequestMapping("/spring/alertas/logalerta")
@CrossOrigin(origins = "*")
public class LogAlertaRest extends GenericoRest {

	private static Logger logger = LogManager.getLogger(LogAlertaRest.class);

	@Autowired
	private LogAlertaServicioImpl servicio;

	@Autowired
	private LogAlertaServicioValidar validar;

	@Autowired
	private LogAlertaDaoImpl consulta;

	@Transactional
	@PutMapping(value = "/validar/{accion}", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<DominioMensajeUsuario>> validar(@Validated @PathVariable String accion, @RequestBody LogAlerta bean) throws Exception {
		logger.debug("LogAlertaRest.validar");
		logger.debug(accion);
		logger.debug(bean);
		List<DominioMensajeUsuario> lst = validar.core(this.getUsuarioActual(), accion, bean);
		logger.debug(lst.size());
		if (lst.isEmpty())
		    return new ResponseEntity<List<DominioMensajeUsuario>>(HttpStatus.OK);
		return new ResponseEntity<List<DominioMensajeUsuario>>(lst, HttpStatus.OK);
	}

	@Transactional
	@GetMapping(value="/obtenerporid", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<LogAlerta> obtenerPorId(@RequestBody LogAlertaPk pk) throws Exception {
		logger.debug("LogAlertaRest.obtenerPorId");
		LogAlerta bean = consulta.obtenerSinConfirmarPorId(pk);
		if (bean==null)
		    return new ResponseEntity<LogAlerta>(HttpStatus.NOT_FOUND);
		return new ResponseEntity<LogAlerta>(bean,HttpStatus.OK);
	}

	@Transactional
	@PostMapping(value = "/registrar",  produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<LogAlerta> registrar(@RequestBody LogAlerta bean) throws Exception {
		logger.debug("LogAlertaRest.registrar");
		bean =  servicio.coreInsertar(this.getUsuarioActual(),bean);
		return new ResponseEntity<LogAlerta>(bean, HttpStatus.CREATED);
	}

	@Transactional
	@PutMapping(value = "/actualizar",  produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<LogAlerta> actualizar(@RequestBody LogAlerta bean) throws Exception {
		logger.debug("LogAlertaRest.actualizar");
		bean = servicio.coreActualizar(this.getUsuarioActual(),bean);
		return new ResponseEntity<LogAlerta>(bean, HttpStatus.OK);
	}

	@Transactional
	@PutMapping(value = "/anular",  produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<LogAlerta> anular(@RequestBody LogAlerta bean) throws Exception {
		logger.debug("LogAlertaRest.anular");
		if (bean==null)
		    return new ResponseEntity<LogAlerta>(HttpStatus.NOT_FOUND);
		bean = servicio.coreAnular(this.getUsuarioActual(), bean);
		return new ResponseEntity<LogAlerta>(bean, HttpStatus.OK);
	}

	@Transactional
	@DeleteMapping(value = "/eliminar", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Void> eliminar(@RequestBody LogAlerta bean) throws Exception {
		logger.debug("LogAlertaRest.eliminar");
		if (bean==null){
		    return new ResponseEntity<Void>(HttpStatus.NOT_FOUND);
		}else{
		    servicio.coreEliminar(this.getUsuarioActual(),bean);
		    return new ResponseEntity<Void>(HttpStatus.OK);
		}
	}
	
	
	

	@Transactional
	@PutMapping(value = "/listarLogAlerta",  produces = MediaType.APPLICATION_JSON_VALUE)
	public DominioPaginacion listarLogAlerta(@RequestBody FiltroPaginacionLogAlerta filtro) throws Exception {
		return servicio.listarLogAlerta(filtro);
	}
	
	@Transactional
	@PutMapping(value = "/listarLogAlertaDetalle",  produces = MediaType.APPLICATION_JSON_VALUE)
	public DominioPaginacion listarLogAlertaDetalle(@RequestBody FiltroPaginacionLogAlerta filtro) throws Exception {
		return servicio.listarLogAlertaDetalle(filtro);
	}
	
	
	@Transactional
	@PutMapping(value = "/actualizarInactivar",  produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<LogAlerta> actualizarInactivar(@RequestBody LogAlerta bean) throws Exception {
		logger.debug("AreaNegocioRest.actualizarAreasNegocio");
		String estado = bean.getEstado();
		bean = consulta.obtenerPorId(bean.getPk().getIdLogAlerta());
		bean.setModificacionUsuario(this.getUsuarioActual().getUsuario());
		bean.setModificacionFecha(new Date());
		bean.setEstado(estado);
		bean = servicio.ActualizarAreasNegocioEstado(bean);
		return new ResponseEntity<LogAlerta>(bean, HttpStatus.OK);
	}
	
	
	
	@Transactional
	@PutMapping(value = "/listarLogAlertaAdicional",  produces = MediaType.APPLICATION_JSON_VALUE)
	public DominioPaginacion listarLogAlertaAdicional(@RequestBody FiltroPaginacionLogAlerta filtro) throws Exception {
		return servicio.listarLogAlertaAdicional(filtro);
	}
	
	
	

}
