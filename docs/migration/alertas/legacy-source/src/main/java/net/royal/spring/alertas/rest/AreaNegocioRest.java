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

import net.royal.spring.alertas.dao.impl.AreaNegocioDaoImpl;
import net.royal.spring.alertas.dominio.AreaNegocio;
import net.royal.spring.alertas.dominio.AreaNegocioPk;
import net.royal.spring.alertas.dominio.filtros.AreaNegocioFiltro;
import net.royal.spring.alertas.servicio.impl.AreaNegocioServicioImpl;
import net.royal.spring.alertas.servicio.validar.AreaNegocioServicioValidar;
import net.royal.spring.framework.modelo.generico.DominioMensajeUsuario;
import net.royal.spring.framework.modelo.generico.DominioPaginacion;
import net.royal.spring.framework.web.rest.GenericoRest;

@RestController
@RequestMapping("/spring/alertas/areanegocio")
@CrossOrigin(origins = "*")
public class AreaNegocioRest extends GenericoRest {

	private static Logger logger = LogManager.getLogger(AreaNegocioRest.class);

	@Autowired
	private AreaNegocioServicioImpl servicio;

	@Autowired
	private AreaNegocioServicioValidar validar;

	@Autowired
	private AreaNegocioDaoImpl consulta;

	@Transactional
	@PutMapping(value = "/validar/{accion}", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<DominioMensajeUsuario>> validar(@Validated @PathVariable String accion, @RequestBody AreaNegocio bean) throws Exception {
		logger.debug("AreaNegocioRest.validar");
		logger.debug(accion);
		logger.debug(bean);
		List<DominioMensajeUsuario> lst = validar.core(this.getUsuarioActual(), accion, bean);
		logger.debug(lst.size());
		if (lst.isEmpty())
		    return new ResponseEntity<List<DominioMensajeUsuario>>(HttpStatus.OK);
		return new ResponseEntity<List<DominioMensajeUsuario>>(lst, HttpStatus.OK);
	}

	@Transactional
	@PostMapping(value="/obtenerporid", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<AreaNegocio> obtenerPorId(@RequestBody AreaNegocioPk pk) throws Exception {
		logger.debug("AreaNegocioRest.obtenerPorId");
		AreaNegocio bean = consulta.obtenerSinConfirmarPorId(pk);
		if (bean==null)
		    return new ResponseEntity<AreaNegocio>(HttpStatus.NOT_FOUND);
		return new ResponseEntity<AreaNegocio>(bean,HttpStatus.OK);
	}

	@Transactional
	@PostMapping(value = "/registrar",  produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<AreaNegocio> registrar(@RequestBody AreaNegocio bean) throws Exception {
		logger.debug("AreaNegocioRest.registrar");
		bean =  servicio.coreInsertar(this.getUsuarioActual(),bean);
		return new ResponseEntity<AreaNegocio>(bean, HttpStatus.CREATED);
	}
	
	
	@Transactional
	@PostMapping(value = "/listar",  produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<AreaNegocio> listar(@RequestBody AreaNegocio bean) throws Exception {
		logger.debug("AreaNegocioRest.registrar");
		bean =  servicio.coreInsertar(this.getUsuarioActual(),bean);
		return new ResponseEntity<AreaNegocio>(bean, HttpStatus.CREATED);
	}
	

	@Transactional
	@PutMapping(value = "/actualizar",  produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<AreaNegocio> actualizar(@RequestBody AreaNegocio bean) throws Exception {
		logger.debug("AreaNegocioRest.actualizar");
		bean = servicio.coreActualizar(this.getUsuarioActual(),bean);
		return new ResponseEntity<AreaNegocio>(bean, HttpStatus.OK);
	}

	@Transactional
	@PutMapping(value = "/anular",  produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<AreaNegocio> anular(@RequestBody AreaNegocio bean) throws Exception {
		logger.debug("AreaNegocioRest.anular");
		if (bean==null)
		    return new ResponseEntity<AreaNegocio>(HttpStatus.NOT_FOUND);
		bean = servicio.coreAnular(this.getUsuarioActual(), bean);
		return new ResponseEntity<AreaNegocio>(bean, HttpStatus.OK);
	}

	@Transactional
	@DeleteMapping(value = "/eliminar", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Void> eliminar(@RequestBody AreaNegocio bean) throws Exception {
		logger.debug("AreaNegocioRest.eliminar");
		if (bean==null){
		    return new ResponseEntity<Void>(HttpStatus.NOT_FOUND);
		}else{
		    servicio.coreEliminar(this.getUsuarioActual(),bean);
		    return new ResponseEntity<Void>(HttpStatus.OK);
		}
	}

	
	@PostMapping(value = "/listarAreasNegocio", produces = MediaType.APPLICATION_JSON_VALUE)
	public DominioPaginacion listarActividades(@RequestBody AreaNegocioFiltro filtro) throws Exception {
		return servicio.listarAreasNegocio(filtro);
	}
	
	@Transactional
	@PutMapping(value = "/actualizarAreasNegocio",  produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<AreaNegocio> actualizarAreasNegocio(@RequestBody AreaNegocio bean) throws Exception {
		logger.debug("AreaNegocioRest.actualizarAreasNegocio");
		bean = servicio.ActualizarAreasNegocio(bean);
		return new ResponseEntity<AreaNegocio>(bean, HttpStatus.OK);
	}

	
	@GetMapping(value = "/IdGeneradoSecuencia", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<AreaNegocio> CodigoGenCabecerea() throws Exception {
		AreaNegocio id = new AreaNegocio();
		id.getPk().setIdAreaNegocio(consulta.generarSecuencia());
  		return new ResponseEntity<AreaNegocio>(id, HttpStatus.OK);
	}
	
	
	@Transactional
	@PostMapping(value = "/RegistrarAreasNegocio",  produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<AreaNegocio> RegistrarAreasNegocio(@RequestBody AreaNegocio bean) throws Exception {
		logger.debug("AreaNegocioRest.actualizarAreasNegocio");
		bean = servicio.RegistrarAreasNegocio(bean);
		return new ResponseEntity<AreaNegocio>(bean, HttpStatus.OK);
	}
	

	@Transactional
	@PutMapping(value = "/actualizarInactivar",  produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<AreaNegocio> actualizarInactivar(@RequestBody AreaNegocio bean) throws Exception {
		logger.debug("AreaNegocioRest.actualizarAreasNegocio");
		bean = servicio.ActualizarAreasNegocioEstado(bean);
		return new ResponseEntity<AreaNegocio>(bean, HttpStatus.OK);
	}
	
	
 
	
	
}
