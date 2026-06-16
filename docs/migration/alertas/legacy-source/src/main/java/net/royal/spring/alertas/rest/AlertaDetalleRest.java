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

import net.royal.spring.alertas.dao.impl.AlertaDetalleDaoImpl;
import net.royal.spring.alertas.dominio.AlertaDetalle;
import net.royal.spring.alertas.dominio.AlertaDetallePk;
import net.royal.spring.alertas.servicio.impl.AlertaDetalleServicioImpl;
import net.royal.spring.alertas.servicio.validar.AlertaDetalleServicioValidar;
import net.royal.spring.framework.modelo.generico.DominioMensajeUsuario;
import net.royal.spring.framework.web.rest.GenericoRest;

@RestController
@RequestMapping("/spring/alertas/alertadetalle")
@CrossOrigin(origins = "*")
public class AlertaDetalleRest extends GenericoRest {

	private static Logger logger = LogManager.getLogger(AlertaDetalleRest.class);

	@Autowired
	private AlertaDetalleServicioImpl servicio;

	@Autowired
	private AlertaDetalleServicioValidar validar;

	@Autowired
	private AlertaDetalleDaoImpl consulta;

	@Transactional
	@PutMapping(value = "/validar/{accion}", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<DominioMensajeUsuario>> validar(@Validated @PathVariable String accion, @RequestBody AlertaDetalle bean) throws Exception {
		logger.debug("AlertaDetalleRest.validar");
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
	public ResponseEntity<AlertaDetalle> obtenerPorId(@RequestBody AlertaDetallePk pk) throws Exception {
		logger.debug("AlertaDetalleRest.obtenerPorId");
		AlertaDetalle bean = consulta.obtenerSinConfirmarPorId(pk);
		if (bean==null)
		    return new ResponseEntity<AlertaDetalle>(HttpStatus.NOT_FOUND);
		return new ResponseEntity<AlertaDetalle>(bean,HttpStatus.OK);
	}

	@Transactional
	@PostMapping(value = "/registrar",  produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<AlertaDetalle> registrar(@RequestBody AlertaDetalle bean) throws Exception {
		logger.debug("AlertaDetalleRest.registrar");
		bean =  servicio.coreInsertar(this.getUsuarioActual(),bean);
		return new ResponseEntity<AlertaDetalle>(bean, HttpStatus.CREATED);
	}

	@Transactional
	@PutMapping(value = "/actualizar",  produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<AlertaDetalle> actualizar(@RequestBody AlertaDetalle bean) throws Exception {
		logger.debug("AlertaDetalleRest.actualizar");
		bean = servicio.coreActualizar(this.getUsuarioActual(),bean);
		return new ResponseEntity<AlertaDetalle>(bean, HttpStatus.OK);
	}

	@Transactional
	@PutMapping(value = "/anular",  produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<AlertaDetalle> anular(@RequestBody AlertaDetalle bean) throws Exception {
		logger.debug("AlertaDetalleRest.anular");
		if (bean==null)
		    return new ResponseEntity<AlertaDetalle>(HttpStatus.NOT_FOUND);
		bean = servicio.coreAnular(this.getUsuarioActual(), bean);
		return new ResponseEntity<AlertaDetalle>(bean, HttpStatus.OK);
	}

	@Transactional
	@DeleteMapping(value = "/eliminar", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Void> eliminar(@RequestBody AlertaDetalle bean) throws Exception {
		logger.debug("AlertaDetalleRest.eliminar");
		if (bean==null){
		    return new ResponseEntity<Void>(HttpStatus.NOT_FOUND);
		}else{
		    servicio.coreEliminar(this.getUsuarioActual(),bean);
		    return new ResponseEntity<Void>(HttpStatus.OK);
		}
	}

}
