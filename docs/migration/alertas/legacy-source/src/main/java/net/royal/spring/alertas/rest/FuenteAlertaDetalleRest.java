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

import net.royal.spring.alertas.dao.impl.FuenteAlertaDetalleDaoImpl;
import net.royal.spring.alertas.dominio.FuenteAlertaDetalle;
import net.royal.spring.alertas.dominio.FuenteAlertaDetallePk;
import net.royal.spring.alertas.servicio.impl.FuenteAlertaDetalleServicioImpl;
import net.royal.spring.alertas.servicio.validar.FuenteAlertaDetalleServicioValidar;
import net.royal.spring.framework.modelo.generico.DominioMensajeUsuario;
import net.royal.spring.framework.web.rest.GenericoRest;

@RestController
@RequestMapping("/spring/alertas/fuentealertadetalle")
@CrossOrigin(origins = "*")
public class FuenteAlertaDetalleRest extends GenericoRest {

	private static Logger logger = LogManager.getLogger(FuenteAlertaDetalleRest.class);

	@Autowired
	private FuenteAlertaDetalleServicioImpl servicio;

	@Autowired
	private FuenteAlertaDetalleServicioValidar validar;

	@Autowired
	private FuenteAlertaDetalleDaoImpl consulta;

	@Transactional
	@PutMapping(value = "/validar/{accion}", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<DominioMensajeUsuario>> validar(@Validated @PathVariable String accion, @RequestBody FuenteAlertaDetalle bean) throws Exception {
		logger.debug("FuenteAlertaDetalleRest.validar");
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
	public ResponseEntity<FuenteAlertaDetalle> obtenerPorId(@RequestBody FuenteAlertaDetallePk pk) throws Exception {
		logger.debug("FuenteAlertaDetalleRest.obtenerPorId");
		FuenteAlertaDetalle bean = consulta.obtenerSinConfirmarPorId(pk);
		if (bean==null)
		    return new ResponseEntity<FuenteAlertaDetalle>(HttpStatus.NOT_FOUND);
		return new ResponseEntity<FuenteAlertaDetalle>(bean,HttpStatus.OK);
	}

	@Transactional
	@PostMapping(value = "/registrar",  produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<FuenteAlertaDetalle> registrar(@RequestBody FuenteAlertaDetalle bean) throws Exception {
		logger.debug("FuenteAlertaDetalleRest.registrar");
		bean =  servicio.coreInsertar(this.getUsuarioActual(),bean);
		return new ResponseEntity<FuenteAlertaDetalle>(bean, HttpStatus.CREATED);
	}

	@Transactional
	@PutMapping(value = "/actualizar",  produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<FuenteAlertaDetalle> actualizar(@RequestBody FuenteAlertaDetalle bean) throws Exception {
		logger.debug("FuenteAlertaDetalleRest.actualizar");
		bean = servicio.coreActualizar(this.getUsuarioActual(),bean);
		return new ResponseEntity<FuenteAlertaDetalle>(bean, HttpStatus.OK);
	}

	@Transactional
	@PutMapping(value = "/anular",  produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<FuenteAlertaDetalle> anular(@RequestBody FuenteAlertaDetalle bean) throws Exception {
		logger.debug("FuenteAlertaDetalleRest.anular");
		if (bean==null)
		    return new ResponseEntity<FuenteAlertaDetalle>(HttpStatus.NOT_FOUND);
		bean = servicio.coreAnular(this.getUsuarioActual(), bean);
		return new ResponseEntity<FuenteAlertaDetalle>(bean, HttpStatus.OK);
	}

	@Transactional
	@DeleteMapping(value = "/eliminar", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Void> eliminar(@RequestBody FuenteAlertaDetalle bean) throws Exception {
		logger.debug("FuenteAlertaDetalleRest.eliminar");
		if (bean==null){
		    return new ResponseEntity<Void>(HttpStatus.NOT_FOUND);
		}else{
		    servicio.coreEliminar(this.getUsuarioActual(),bean);
		    return new ResponseEntity<Void>(HttpStatus.OK);
		}
	}

}
