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

import net.royal.spring.alertas.dao.impl.FuenteHistoriaDaoImpl;
import net.royal.spring.alertas.dominio.FuenteHistoria;
import net.royal.spring.alertas.dominio.FuenteHistoriaPk;
import net.royal.spring.alertas.dominio.dto.FuenteHistoriaDto;
import net.royal.spring.alertas.dominio.filtros.FiltroPaginacionFuenteHistoria;
import net.royal.spring.alertas.servicio.impl.FuenteHistoriaServicioImpl;
import net.royal.spring.alertas.servicio.validar.FuenteHistoriaServicioValidar;
import net.royal.spring.framework.modelo.generico.DominioMensajeUsuario;
import net.royal.spring.framework.modelo.generico.DominioPaginacion;
import net.royal.spring.framework.web.rest.GenericoRest;

@RestController
@RequestMapping("/spring/alertas/fuentehistoria")
@CrossOrigin(origins = "*")
public class FuenteHistoriaRest extends GenericoRest {

	private static Logger logger = LogManager.getLogger(FuenteHistoriaRest.class);

	@Autowired
	private FuenteHistoriaServicioImpl servicio;

	@Autowired
	private FuenteHistoriaServicioValidar validar;

	@Autowired
	private FuenteHistoriaDaoImpl consulta;

	@Transactional
	@PutMapping(value = "/validar/{accion}", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<DominioMensajeUsuario>> validar(@Validated @PathVariable String accion, @RequestBody FuenteHistoria bean) throws Exception {
		logger.debug("FuenteHistoriaRest.validar");
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
	public ResponseEntity<FuenteHistoria> obtenerPorId(@RequestBody FuenteHistoriaPk pk) throws Exception {
		logger.debug("FuenteHistoriaRest.obtenerPorId");
		FuenteHistoria bean = consulta.obtenerSinConfirmarPorId(pk);
		if (bean==null)
		    return new ResponseEntity<FuenteHistoria>(HttpStatus.NOT_FOUND);
		return new ResponseEntity<FuenteHistoria>(bean,HttpStatus.OK);
	}

	@Transactional
	@PostMapping(value = "/registrar",  produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<FuenteHistoria> registrar(@RequestBody FuenteHistoria bean) throws Exception {
		logger.debug("FuenteHistoriaRest.registrar");
		bean =  servicio.coreInsertar(this.getUsuarioActual(),bean);
		return new ResponseEntity<FuenteHistoria>(bean, HttpStatus.CREATED);
	}

	@Transactional
	@PutMapping(value = "/actualizar",  produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<FuenteHistoria> actualizar(@RequestBody FuenteHistoria bean) throws Exception {
		logger.debug("FuenteHistoriaRest.actualizar");
		bean = servicio.coreActualizar(this.getUsuarioActual(),bean);
		return new ResponseEntity<FuenteHistoria>(bean, HttpStatus.OK);
	}

	@Transactional
	@PutMapping(value = "/anular",  produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<FuenteHistoria> anular(@RequestBody FuenteHistoria bean) throws Exception {
		logger.debug("FuenteHistoriaRest.anular");
		if (bean==null)
		    return new ResponseEntity<FuenteHistoria>(HttpStatus.NOT_FOUND);
		bean = servicio.coreAnular(this.getUsuarioActual(), bean);
		return new ResponseEntity<FuenteHistoria>(bean, HttpStatus.OK);
	}

	@Transactional
	@DeleteMapping(value = "/eliminar", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Void> eliminar(@RequestBody FuenteHistoria bean) throws Exception {
		logger.debug("FuenteHistoriaRest.eliminar");
		if (bean==null){
		    return new ResponseEntity<Void>(HttpStatus.NOT_FOUND);
		}else{
		    servicio.coreEliminar(this.getUsuarioActual(),bean);
		    return new ResponseEntity<Void>(HttpStatus.OK);
		}
	}

	@Transactional
	@PostMapping(value = "/listar", produces = MediaType.APPLICATION_JSON_VALUE)
	public DominioPaginacion listartodo(@RequestBody FiltroPaginacionFuenteHistoria filtro)
			throws Exception {
		logger.debug("FuenteHistoriaRest.listar");
		return consulta.listar(filtro);
	}
	
	@Transactional
	@PostMapping(value = "/inactivar", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<FuenteHistoriaDto>> inactivar(@RequestBody List<FuenteHistoriaDto> lst)
			throws Exception {
		consulta.inactivar(this.getUsuarioActual(), lst);
		return new ResponseEntity<List<FuenteHistoriaDto>>(lst, HttpStatus.OK);
	}
}
