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

import net.royal.spring.alertas.dao.impl.ReglaNegocioDetalleDaoImpl;
import net.royal.spring.alertas.dominio.ReglaNegocioDetalle;
import net.royal.spring.alertas.dominio.ReglaNegocioDetallePk;
import net.royal.spring.alertas.dominio.filtros.FiltroPaginacionReglaNegocioDetalle;
import net.royal.spring.alertas.servicio.impl.ReglaNegocioDetalleServicioImpl;
import net.royal.spring.alertas.servicio.validar.ReglaNegocioDetalleServicioValidar;
import net.royal.spring.framework.modelo.generico.DominioMensajeUsuario;
import net.royal.spring.framework.modelo.generico.DominioPaginacion;
import net.royal.spring.framework.web.rest.GenericoRest;

@RestController
@RequestMapping("/spring/alertas/reglanegociodetalle")
@CrossOrigin(origins = "*")
public class ReglaNegocioDetalleRest extends GenericoRest {

	private static Logger logger = LogManager.getLogger(ReglaNegocioDetalleRest.class);

	@Autowired
	private ReglaNegocioDetalleServicioImpl servicio;

	@Autowired
	private ReglaNegocioDetalleServicioValidar validar;

	@Autowired
	private ReglaNegocioDetalleDaoImpl consulta;

	@Transactional
	@PutMapping(value = "/validar/{accion}", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<DominioMensajeUsuario>> validar(@Validated @PathVariable String accion, @RequestBody ReglaNegocioDetalle bean) throws Exception {
		logger.debug("ReglaNegocioDetalleRest.validar");
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
	public ResponseEntity<ReglaNegocioDetalle> obtenerPorId(@RequestBody ReglaNegocioDetallePk pk) throws Exception {
		logger.debug("ReglaNegocioDetalleRest.obtenerPorId");
		ReglaNegocioDetalle bean = consulta.obtenerSinConfirmarPorId(pk);
		if (bean==null)
		    return new ResponseEntity<ReglaNegocioDetalle>(HttpStatus.NOT_FOUND);
		return new ResponseEntity<ReglaNegocioDetalle>(bean,HttpStatus.OK);
	}

	@Transactional
	@PostMapping(value = "/registrar",  produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<ReglaNegocioDetalle> registrar(@RequestBody ReglaNegocioDetalle bean) throws Exception {
		logger.debug("ReglaNegocioDetalleRest.registrar");
		bean =  servicio.coreInsertar(this.getUsuarioActual(),bean);
		return new ResponseEntity<ReglaNegocioDetalle>(bean, HttpStatus.CREATED);
	}

	@Transactional
	@PutMapping(value = "/actualizar",  produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<ReglaNegocioDetalle> actualizar(@RequestBody ReglaNegocioDetalle bean) throws Exception {
		logger.debug("ReglaNegocioDetalleRest.actualizar");
		bean = servicio.coreActualizar(this.getUsuarioActual(),bean);
		return new ResponseEntity<ReglaNegocioDetalle>(bean, HttpStatus.OK);
	}

	@Transactional
	@PutMapping(value = "/anular",  produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<ReglaNegocioDetalle> anular(@RequestBody ReglaNegocioDetalle bean) throws Exception {
		logger.debug("ReglaNegocioDetalleRest.anular");
		if (bean==null)
		    return new ResponseEntity<ReglaNegocioDetalle>(HttpStatus.NOT_FOUND);
		bean = servicio.coreAnular(this.getUsuarioActual(), bean);
		return new ResponseEntity<ReglaNegocioDetalle>(bean, HttpStatus.OK);
	}
	
	

	@Transactional
	@DeleteMapping(value = "/eliminar", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Void> eliminar(@RequestBody ReglaNegocioDetalle bean) throws Exception {
		logger.debug("ReglaNegocioDetalleRest.eliminar");
		if (bean==null){
		    return new ResponseEntity<Void>(HttpStatus.NOT_FOUND);
		}else{
		    servicio.coreEliminar(this.getUsuarioActual(),bean);
		    return new ResponseEntity<Void>(HttpStatus.OK);
		}
	}
	
	@Transactional
	@PostMapping(value = "/listar", produces = MediaType.APPLICATION_JSON_VALUE)
	public DominioPaginacion listartodo(@RequestBody FiltroPaginacionReglaNegocioDetalle filtro) throws Exception {
		logger.debug("ReglaNegocioDetalleRest.listar");
		return consulta.listar(filtro);
	}


}
