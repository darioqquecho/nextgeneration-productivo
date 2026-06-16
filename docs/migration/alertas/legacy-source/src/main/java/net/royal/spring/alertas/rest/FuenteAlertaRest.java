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

import net.royal.spring.alertas.dao.impl.FuenteAlertaDaoImpl;
import net.royal.spring.alertas.dominio.FuenteAlerta;
import net.royal.spring.alertas.dominio.FuenteAlertaPk;
import net.royal.spring.alertas.dominio.filtros.FiltroPaginacionFuenteAlerta;
import net.royal.spring.alertas.servicio.impl.FuenteAlertaServicioImpl;
import net.royal.spring.alertas.servicio.validar.FuenteAlertaServicioValidar;
import net.royal.spring.framework.modelo.generico.DominioMensajeUsuario;
import net.royal.spring.framework.modelo.generico.DominioPaginacion;
import net.royal.spring.framework.web.rest.GenericoRest;

@RestController
@RequestMapping("/spring/alertas/fuentealerta")
@CrossOrigin(origins = "*")
public class FuenteAlertaRest extends GenericoRest {

	private static Logger logger = LogManager.getLogger(FuenteAlertaRest.class);

	@Autowired
	private FuenteAlertaServicioImpl servicio;

	@Autowired
	private FuenteAlertaServicioValidar validar;

	@Autowired
	private FuenteAlertaDaoImpl consulta;

	@Transactional
	@PutMapping(value = "/validar/{accion}", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<DominioMensajeUsuario>> validar(@Validated @PathVariable String accion, @RequestBody FuenteAlerta bean) throws Exception {
		logger.debug("FuenteAlertaRest.validar");
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
	public ResponseEntity<FuenteAlerta> obtenerPorId(@RequestBody FuenteAlertaPk pk) throws Exception {
		logger.debug("FuenteAlertaRest.obtenerPorId");
		FuenteAlerta bean = consulta.obtenerSinConfirmarPorId(pk);
		if (bean==null)
		    return new ResponseEntity<FuenteAlerta>(HttpStatus.NOT_FOUND);
		return new ResponseEntity<FuenteAlerta>(bean,HttpStatus.OK);
	}

	@Transactional
	@PostMapping(value = "/registrar",  produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<FuenteAlerta> registrar(@RequestBody FuenteAlerta bean) throws Exception {
		logger.debug("FuenteAlertaRest.registrar");
		bean =  servicio.coreInsertar(this.getUsuarioActual(),bean);
		return new ResponseEntity<FuenteAlerta>(bean, HttpStatus.CREATED);
	}

	@Transactional
	@PutMapping(value = "/actualizar",  produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<FuenteAlerta> actualizar(@RequestBody FuenteAlerta bean) throws Exception {
		logger.debug("FuenteAlertaRest.actualizar");
		bean = servicio.coreActualizar(this.getUsuarioActual(),bean);
		return new ResponseEntity<FuenteAlerta>(bean, HttpStatus.OK);
	}

	@Transactional
	@PutMapping(value = "/anular",  produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<FuenteAlerta> anular(@RequestBody FuenteAlerta bean) throws Exception {
		logger.debug("FuenteAlertaRest.anular");
		if (bean==null)
		    return new ResponseEntity<FuenteAlerta>(HttpStatus.NOT_FOUND);
		bean = servicio.coreAnular(this.getUsuarioActual(), bean);
		return new ResponseEntity<FuenteAlerta>(bean, HttpStatus.OK);
	}

	@Transactional
	@DeleteMapping(value = "/eliminar", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Void> eliminar(@RequestBody FuenteAlerta bean) throws Exception {
		logger.debug("FuenteAlertaRest.eliminar");
		if (bean==null){
		    return new ResponseEntity<Void>(HttpStatus.NOT_FOUND);
		}else{
		    servicio.coreEliminar(this.getUsuarioActual(),bean);
		    return new ResponseEntity<Void>(HttpStatus.OK);
		}
	}
	
	@PostMapping(value = "/listarFuenteAlerta", produces = MediaType.APPLICATION_JSON_VALUE)
	public DominioPaginacion listarFuenteAlerta(@RequestBody FiltroPaginacionFuenteAlerta filtro) throws Exception {
		return servicio.listarFuenteAlerta(filtro);
	}
	
	
	@GetMapping(value = "/listarReglaNegocio", produces = MediaType.APPLICATION_JSON_VALUE)
	public List<?> listarReglaNegocio() throws Exception {
		return servicio.listarReglaNegocio();
	}
	
	
	
	@Transactional
	@PostMapping(value = "/eliminarFuenteAlerta", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Void> eliminarFuenteAlerta(@RequestBody FuenteAlerta bean) throws Exception {
		logger.debug("FuenteAlertaRest.eliminar");
		if (bean==null){
		    return new ResponseEntity<Void>(HttpStatus.NOT_FOUND);
		}else{
		    servicio.coreEliminar(this.getUsuarioActual(),bean);
		    return new ResponseEntity<Void>(HttpStatus.OK);
		}
	}
	
}
