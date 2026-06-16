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

import net.royal.spring.alertas.dao.impl.CorreoDestinoDaoImpl;
import net.royal.spring.alertas.dominio.CorreoDestino;
import net.royal.spring.alertas.dominio.CorreoDestinoPk;
import net.royal.spring.alertas.dominio.filtros.FiltroPaginacionCorreo;
import net.royal.spring.alertas.servicio.impl.CorreoDestinoServicioImpl;
import net.royal.spring.alertas.servicio.validar.CorreoDestinoServicioValidar;
import net.royal.spring.framework.modelo.generico.DominioMensajeUsuario;
import net.royal.spring.framework.modelo.generico.DominioPaginacion;
import net.royal.spring.framework.web.rest.GenericoRest;

@RestController
@RequestMapping("/spring/alertas/correodestino")
@CrossOrigin(origins = "*")
public class CorreoDestinoRest extends GenericoRest {

	private static Logger logger = LogManager.getLogger(CorreoDestinoRest.class);

	@Autowired
	private CorreoDestinoServicioImpl servicio;

	@Autowired
	private CorreoDestinoServicioValidar validar;

	@Autowired
	private CorreoDestinoDaoImpl consulta;

	@Transactional
	@PutMapping(value = "/validar/{accion}", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<DominioMensajeUsuario>> validar(@Validated @PathVariable String accion, @RequestBody CorreoDestino bean) throws Exception {
		logger.debug("CorreoDestinoRest.validar");
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
	public ResponseEntity<CorreoDestino> obtenerPorId(@RequestBody CorreoDestinoPk pk) throws Exception {
		logger.debug("CorreoDestinoRest.obtenerPorId");
		CorreoDestino bean = consulta.obtenerSinConfirmarPorId(pk);
		if (bean==null)
		    return new ResponseEntity<CorreoDestino>(HttpStatus.NOT_FOUND);
		return new ResponseEntity<CorreoDestino>(bean,HttpStatus.OK);
	}

	@Transactional
	@PostMapping(value = "/registrar",  produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<CorreoDestino> registrar(@RequestBody CorreoDestino bean) throws Exception {
		logger.debug("CorreoDestinoRest.registrar");
		bean =  servicio.coreInsertar(this.getUsuarioActual(),bean);
		return new ResponseEntity<CorreoDestino>(bean, HttpStatus.CREATED);
	}

	@Transactional
	@PutMapping(value = "/actualizar",  produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<CorreoDestino> actualizar(@RequestBody CorreoDestino bean) throws Exception {
		logger.debug("CorreoDestinoRest.actualizar");
		bean = servicio.coreActualizar(this.getUsuarioActual(),bean);
		return new ResponseEntity<CorreoDestino>(bean, HttpStatus.OK);
	}

	@Transactional
	@PutMapping(value = "/anular",  produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<CorreoDestino> anular(@RequestBody CorreoDestino bean) throws Exception {
		logger.debug("CorreoDestinoRest.anular");
		if (bean==null)
		    return new ResponseEntity<CorreoDestino>(HttpStatus.NOT_FOUND);
		bean = servicio.coreAnular(this.getUsuarioActual(), bean);
		return new ResponseEntity<CorreoDestino>(bean, HttpStatus.OK);
	}

	@Transactional
	@DeleteMapping(value = "/eliminar", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Void> eliminar(@RequestBody CorreoDestino bean) throws Exception {
		logger.debug("CorreoDestinoRest.eliminar");
		if (bean==null){
		    return new ResponseEntity<Void>(HttpStatus.NOT_FOUND);
		}else{
		    servicio.coreEliminar(this.getUsuarioActual(),bean);
		    return new ResponseEntity<Void>(HttpStatus.OK);
		}
	}
	
	
	
	
	@Transactional
	@PutMapping(value = "/listarCorrreoDestino",  produces = MediaType.APPLICATION_JSON_VALUE)
	public DominioPaginacion listarCorrreo(@RequestBody FiltroPaginacionCorreo filtro) throws Exception {
		return servicio.listarCorrreodestino(filtro);
	}

}
