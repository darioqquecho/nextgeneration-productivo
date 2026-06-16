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

import net.royal.spring.alertas.dao.impl.AlineacionColumnaDaoImpl;
import net.royal.spring.alertas.dominio.AlineacionColumna;
import net.royal.spring.alertas.dominio.AlineacionColumnaPk;
import net.royal.spring.alertas.servicio.impl.AlineacionColumnaServicioImpl;
import net.royal.spring.alertas.servicio.validar.AlineacionColumnaServicioValidar;
import net.royal.spring.framework.modelo.generico.DominioMensajeUsuario;
import net.royal.spring.framework.web.rest.GenericoRest;

@RestController
@RequestMapping("/spring/alertas/alineacioncolumna")
@CrossOrigin(origins = "*")
public class AlineacionColumnaRest extends GenericoRest {

	private static Logger logger = LogManager.getLogger(AlineacionColumnaRest.class);

	@Autowired
	private AlineacionColumnaServicioImpl servicio;

	@Autowired
	private AlineacionColumnaServicioValidar validar;

	@Autowired
	private AlineacionColumnaDaoImpl consulta;

	@Transactional
	@PutMapping(value = "/validar/{accion}", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<DominioMensajeUsuario>> validar(@Validated @PathVariable String accion, @RequestBody AlineacionColumna bean) throws Exception {
		logger.debug("AlineacionColumnaRest.validar");
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
	public ResponseEntity<AlineacionColumna> obtenerPorId(@RequestBody AlineacionColumnaPk pk) throws Exception {
		logger.debug("AlineacionColumnaRest.obtenerPorId");
		AlineacionColumna bean = consulta.obtenerSinConfirmarPorId(pk);
		if (bean==null)
		    return new ResponseEntity<AlineacionColumna>(HttpStatus.NOT_FOUND);
		return new ResponseEntity<AlineacionColumna>(bean,HttpStatus.OK);
	}

	@Transactional
	@PostMapping(value = "/registrar",  produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<AlineacionColumna> registrar(@RequestBody AlineacionColumna bean) throws Exception {
		logger.debug("AlineacionColumnaRest.registrar");
		bean =  servicio.coreInsertar(this.getUsuarioActual(),bean);
		return new ResponseEntity<AlineacionColumna>(bean, HttpStatus.CREATED);
	}

	@Transactional
	@PutMapping(value = "/actualizar",  produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<AlineacionColumna> actualizar(@RequestBody AlineacionColumna bean) throws Exception {
		logger.debug("AlineacionColumnaRest.actualizar");
		bean = servicio.coreActualizar(this.getUsuarioActual(),bean);
		return new ResponseEntity<AlineacionColumna>(bean, HttpStatus.OK);
	}

	@Transactional
	@PutMapping(value = "/anular",  produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<AlineacionColumna> anular(@RequestBody AlineacionColumna bean) throws Exception {
		logger.debug("AlineacionColumnaRest.anular");
		if (bean==null)
		    return new ResponseEntity<AlineacionColumna>(HttpStatus.NOT_FOUND);
		bean = servicio.coreAnular(this.getUsuarioActual(), bean);
		return new ResponseEntity<AlineacionColumna>(bean, HttpStatus.OK);
	}

	@Transactional
	@DeleteMapping(value = "/eliminar", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Void> eliminar(@RequestBody AlineacionColumna bean) throws Exception {
		logger.debug("AlineacionColumnaRest.eliminar");
		if (bean==null){
		    return new ResponseEntity<Void>(HttpStatus.NOT_FOUND);
		}else{
		    servicio.coreEliminar(this.getUsuarioActual(),bean);
		    return new ResponseEntity<Void>(HttpStatus.OK);
		}
	}

}
