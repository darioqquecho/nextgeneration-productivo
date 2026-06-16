package net.royal.spring.alertas.rest;

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

import net.royal.spring.alertas.dao.impl.TipoProgramacionDaoImpl;
import net.royal.spring.alertas.dominio.TipoProgramacion;
import net.royal.spring.alertas.dominio.TipoProgramacionPk;
import net.royal.spring.alertas.servicio.impl.TipoProgramacionServicioImpl;
import net.royal.spring.alertas.servicio.validar.TipoProgramacionServicioValidar;
import net.royal.spring.framework.modelo.generico.DominioMensajeUsuario;
import net.royal.spring.framework.web.rest.GenericoRest;

@RestController
@RequestMapping("/spring/alertas/tipoprogramacion")
@CrossOrigin(origins = "*")
public class TipoProgramacionRest extends GenericoRest {

	private static Logger logger = LogManager.getLogger(TipoProgramacionRest.class);

	@Autowired
	private TipoProgramacionServicioImpl servicio;

	@Autowired
	private TipoProgramacionServicioValidar validar;

	@Autowired
	private TipoProgramacionDaoImpl consulta;

	@Transactional
	@PutMapping(value = "/validar/{accion}", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<DominioMensajeUsuario>> validar(@Validated @PathVariable String accion, @RequestBody TipoProgramacion bean) throws Exception {
		logger.debug("TipoProgramacionRest.validar");
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
	public ResponseEntity<TipoProgramacion> obtenerPorId(@RequestBody TipoProgramacionPk pk) throws Exception {
		logger.debug("TipoProgramacionRest.obtenerPorId");
		TipoProgramacion bean = consulta.obtenerSinConfirmarPorId(pk);
		if (bean==null)
		    return new ResponseEntity<TipoProgramacion>(HttpStatus.NOT_FOUND);
		return new ResponseEntity<TipoProgramacion>(bean,HttpStatus.OK);
	}

	@Transactional
	@PostMapping(value = "/registrar",  produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<TipoProgramacion> registrar(@RequestBody TipoProgramacion bean) throws Exception {
		logger.debug("TipoProgramacionRest.registrar");
		bean =  servicio.coreInsertar(this.getUsuarioActual(),bean);
		return new ResponseEntity<TipoProgramacion>(bean, HttpStatus.CREATED);
	}

	@Transactional
	@PutMapping(value = "/actualizar",  produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<TipoProgramacion> actualizar(@RequestBody TipoProgramacion bean) throws Exception {
		logger.debug("TipoProgramacionRest.actualizar");
		bean = servicio.coreActualizar(this.getUsuarioActual(),bean);
		return new ResponseEntity<TipoProgramacion>(bean, HttpStatus.OK);
	}

	@Transactional
	@PutMapping(value = "/anular",  produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<TipoProgramacion> anular(@RequestBody TipoProgramacion bean) throws Exception {
		logger.debug("TipoProgramacionRest.anular");
		if (bean==null)
		    return new ResponseEntity<TipoProgramacion>(HttpStatus.NOT_FOUND);
		bean = servicio.coreAnular(this.getUsuarioActual(), bean);
		return new ResponseEntity<TipoProgramacion>(bean, HttpStatus.OK);
	}

	@Transactional
	@DeleteMapping(value = "/eliminar", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Void> eliminar(@RequestBody TipoProgramacion bean) throws Exception {
		logger.debug("TipoProgramacionRest.eliminar");
		if (bean==null){
		    return new ResponseEntity<Void>(HttpStatus.NOT_FOUND);
		}else{
		    servicio.coreEliminar(this.getUsuarioActual(),bean);
		    return new ResponseEntity<Void>(HttpStatus.OK);
		}
	}

}
