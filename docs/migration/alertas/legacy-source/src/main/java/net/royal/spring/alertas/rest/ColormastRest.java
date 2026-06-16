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

import net.royal.spring.alertas.dao.impl.ColormastDaoImpl;
import net.royal.spring.alertas.dominio.Colormast;
import net.royal.spring.alertas.dominio.ColormastPk;
import net.royal.spring.alertas.dominio.filtros.FiltroPaginacionColor;
import net.royal.spring.alertas.servicio.impl.ColormastServicioImpl;
import net.royal.spring.alertas.servicio.validar.ColormastServicioValidar;
import net.royal.spring.framework.modelo.generico.DominioMensajeUsuario;
import net.royal.spring.framework.modelo.generico.DominioPaginacion;
import net.royal.spring.framework.web.rest.GenericoRest;

@RestController
@RequestMapping("/spring/alertas/colormast")
@CrossOrigin(origins = "*")
public class ColormastRest extends GenericoRest {

	private static Logger logger = LogManager.getLogger(ColormastRest.class);

	@Autowired
	private ColormastServicioImpl servicio;

	@Autowired
	private ColormastServicioValidar validar;

	@Autowired
	private ColormastDaoImpl consulta;

	@Transactional
	@PutMapping(value = "/validar/{accion}", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<DominioMensajeUsuario>> validar(@Validated @PathVariable String accion, @RequestBody Colormast bean) throws Exception {
		logger.debug("ColormastRest.validar");
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
	public ResponseEntity<Colormast> obtenerPorId(@RequestBody ColormastPk pk) throws Exception {
		logger.debug("ColormastRest.obtenerPorId");
		Colormast bean = consulta.obtenerSinConfirmarPorId(pk);
		if (bean==null)
		    return new ResponseEntity<Colormast>(HttpStatus.NOT_FOUND);
		return new ResponseEntity<Colormast>(bean,HttpStatus.OK);
	}

	@Transactional
	@PostMapping(value = "/registrar",  produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Colormast> registrar(@RequestBody Colormast bean) throws Exception {
		logger.debug("ColormastRest.registrar");
		bean =  servicio.coreInsertar(this.getUsuarioActual(),bean);
		return new ResponseEntity<Colormast>(bean, HttpStatus.CREATED);
	}

	@Transactional
	@PutMapping(value = "/actualizar",  produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Colormast> actualizar(@RequestBody Colormast bean) throws Exception {
		logger.debug("ColormastRest.actualizar");
		bean = servicio.coreActualizar(this.getUsuarioActual(),bean);
		return new ResponseEntity<Colormast>(bean, HttpStatus.OK);
	}

	@Transactional
	@PutMapping(value = "/anular",  produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Colormast> anular(@RequestBody Colormast bean) throws Exception {
		logger.debug("ColormastRest.anular");
		if (bean==null)
		    return new ResponseEntity<Colormast>(HttpStatus.NOT_FOUND);
		bean = servicio.coreAnular(this.getUsuarioActual(), bean);
		return new ResponseEntity<Colormast>(bean, HttpStatus.OK);
	}

	@Transactional
	@DeleteMapping(value = "/eliminar", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Void> eliminar(@RequestBody Colormast bean) throws Exception {
		logger.debug("ColormastRest.eliminar");
		if (bean==null){
		    return new ResponseEntity<Void>(HttpStatus.NOT_FOUND);
		}else{
		    servicio.coreEliminar(this.getUsuarioActual(),bean);
		    return new ResponseEntity<Void>(HttpStatus.OK);
		}
	}
	
	@Transactional
	@PutMapping(value = "/actualizarColorMast",  produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Colormast> actualizarColorMast(@RequestBody Colormast bean) throws Exception {
		logger.debug("ColormastRest.actualizar");
		bean = servicio.ActualizarColorMast(bean);
		return new ResponseEntity<Colormast>(bean, HttpStatus.OK);
	}
	
	@Transactional
	@PutMapping(value = "/registrarColorMast",  produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Colormast> registrarColorMast(@RequestBody Colormast bean) throws Exception {
		logger.debug("ColormastRest.registrar");
		bean = servicio.RegistrarColorMast(bean);
		return new ResponseEntity<Colormast>(bean, HttpStatus.OK);
	}

	@Transactional
	@PutMapping(value = "/actualizarEstado",  produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Colormast> actualizarEstado(@RequestBody Colormast bean) throws Exception {
		logger.debug("ColormastRest.actualizar");
		bean = servicio.ActualizarColorMastEstado(bean);
		return new ResponseEntity<Colormast>(bean, HttpStatus.OK);
	}
	
	//Servicio Temproal
	@Transactional
	@PostMapping(value = "/listarColores", produces = MediaType.APPLICATION_JSON_VALUE)
	public DominioPaginacion listarColores(@RequestBody FiltroPaginacionColor filtro) throws Exception {
		logger.debug("ReglaNegocioRest.listar");
		return consulta.listarColores(filtro);
	}
}
