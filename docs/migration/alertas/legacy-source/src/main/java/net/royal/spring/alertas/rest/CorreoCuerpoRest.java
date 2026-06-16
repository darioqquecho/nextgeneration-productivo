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

import net.royal.spring.alertas.dao.impl.CorreoCuerpoDaoImpl;
import net.royal.spring.alertas.dominio.CorreoCuerpo;
import net.royal.spring.alertas.dominio.CorreoCuerpoPk;
import net.royal.spring.alertas.dominio.filtros.FiltroPaginacionCorreo;
import net.royal.spring.alertas.servicio.impl.CorreoCuerpoServicioImpl;
import net.royal.spring.alertas.servicio.validar.CorreoCuerpoServicioValidar;
import net.royal.spring.framework.modelo.generico.DominioMensajeUsuario;
import net.royal.spring.framework.web.rest.GenericoRest;

@RestController
@RequestMapping("/spring/alertas/correocuerpo")
@CrossOrigin(origins = "*")
public class CorreoCuerpoRest extends GenericoRest {

	private static Logger logger = LogManager.getLogger(CorreoCuerpoRest.class);

	@Autowired
	private CorreoCuerpoServicioImpl servicio;

	@Autowired
	private CorreoCuerpoServicioValidar validar;

	@Autowired
	private CorreoCuerpoDaoImpl consulta;

	@Transactional
	@PutMapping(value = "/validar/{accion}", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<DominioMensajeUsuario>> validar(@Validated @PathVariable String accion, @RequestBody CorreoCuerpo bean) throws Exception {
		logger.debug("CorreoCuerpoRest.validar");
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
	public ResponseEntity<CorreoCuerpo> obtenerPorId(@RequestBody CorreoCuerpoPk pk) throws Exception {
		logger.debug("CorreoCuerpoRest.obtenerPorId");
		CorreoCuerpo bean = consulta.obtenerSinConfirmarPorId(pk);
		if (bean==null)
		    return new ResponseEntity<CorreoCuerpo>(HttpStatus.NOT_FOUND);
		return new ResponseEntity<CorreoCuerpo>(bean,HttpStatus.OK);
	}

	@Transactional
	@PostMapping(value = "/registrar",  produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<CorreoCuerpo> registrar(@RequestBody CorreoCuerpo bean) throws Exception {
		logger.debug("CorreoCuerpoRest.registrar");
		bean =  servicio.coreInsertar(this.getUsuarioActual(),bean);
		return new ResponseEntity<CorreoCuerpo>(bean, HttpStatus.CREATED);
	}

	@Transactional
	@PutMapping(value = "/actualizar",  produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<CorreoCuerpo> actualizar(@RequestBody CorreoCuerpo bean) throws Exception {
		logger.debug("CorreoCuerpoRest.actualizar");
		bean = servicio.coreActualizar(this.getUsuarioActual(),bean);
		return new ResponseEntity<CorreoCuerpo>(bean, HttpStatus.OK);
	}

	@Transactional
	@PutMapping(value = "/anular",  produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<CorreoCuerpo> anular(@RequestBody CorreoCuerpo bean) throws Exception {
		logger.debug("CorreoCuerpoRest.anular");
		if (bean==null)
		    return new ResponseEntity<CorreoCuerpo>(HttpStatus.NOT_FOUND);
		bean = servicio.coreAnular(this.getUsuarioActual(), bean);
		return new ResponseEntity<CorreoCuerpo>(bean, HttpStatus.OK);
	}

	@Transactional
	@DeleteMapping(value = "/eliminar", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Void> eliminar(@RequestBody CorreoCuerpo bean) throws Exception {
		logger.debug("CorreoCuerpoRest.eliminar");
		if (bean==null){
		    return new ResponseEntity<Void>(HttpStatus.NOT_FOUND);
		}else{
		    servicio.coreEliminar(this.getUsuarioActual(),bean);
		    return new ResponseEntity<Void>(HttpStatus.OK);
		}
	}
	
	@Transactional
	@PutMapping(value = "/cuerpoCorreo",  produces = MediaType.APPLICATION_JSON_VALUE)
	public String  cuerpoCorreo(@RequestBody FiltroPaginacionCorreo filtro) throws Exception {
		return servicio.CuerpoCampo(filtro);
	}
	
	
	

}
