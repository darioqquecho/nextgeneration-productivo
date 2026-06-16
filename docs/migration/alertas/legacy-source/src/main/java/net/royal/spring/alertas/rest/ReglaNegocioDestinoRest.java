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

import net.royal.spring.alertas.dao.impl.ReglaNegocioDestinoDaoImpl;
import net.royal.spring.alertas.dominio.ReglaNegocioDestino;
import net.royal.spring.alertas.dominio.ReglaNegocioDestinoPk;
import net.royal.spring.alertas.dominio.dto.ReglaNegocioDestinoDto;
import net.royal.spring.alertas.dominio.filtros.FiltroGenericoSelector;
import net.royal.spring.alertas.dominio.filtros.FiltroPaginacionReglaNegocioDestino;
import net.royal.spring.alertas.servicio.impl.ReglaNegocioDestinoServicioImpl;
import net.royal.spring.alertas.servicio.validar.ReglaNegocioDestinoServicioValidar;
import net.royal.spring.framework.modelo.generico.DominioMensajeUsuario;
import net.royal.spring.framework.modelo.generico.DominioPaginacion;
import net.royal.spring.framework.web.rest.GenericoRest;

@RestController
@RequestMapping("/spring/alertas/reglanegociodestino")
@CrossOrigin(origins = "*")
public class ReglaNegocioDestinoRest extends GenericoRest {

	private static Logger logger = LogManager.getLogger(ReglaNegocioDestinoRest.class);

	@Autowired
	private ReglaNegocioDestinoServicioImpl servicio;

	@Autowired
	private ReglaNegocioDestinoServicioValidar validar;

	@Autowired
	private ReglaNegocioDestinoDaoImpl consulta;

	@Transactional
	@PutMapping(value = "/validar/{accion}", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<DominioMensajeUsuario>> validar(@Validated @PathVariable String accion, @RequestBody ReglaNegocioDestino bean) throws Exception {
		logger.debug("ReglaNegocioDestinoRest.validar");
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
	public ResponseEntity<ReglaNegocioDestino> obtenerPorId(@RequestBody ReglaNegocioDestinoPk pk) throws Exception {
		logger.debug("ReglaNegocioDestinoRest.obtenerPorId");
		ReglaNegocioDestino bean = consulta.obtenerSinConfirmarPorId(pk);
		if (bean==null)
		    return new ResponseEntity<ReglaNegocioDestino>(HttpStatus.NOT_FOUND);
		return new ResponseEntity<ReglaNegocioDestino>(bean,HttpStatus.OK);
	}

	@Transactional
	@PostMapping(value = "/registrar",  produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<ReglaNegocioDestino> registrar(@RequestBody ReglaNegocioDestino bean) throws Exception {
		logger.debug("ReglaNegocioDestinoRest.registrar");
		bean =  servicio.coreInsertar(this.getUsuarioActual(),bean);
		return new ResponseEntity<ReglaNegocioDestino>(bean, HttpStatus.CREATED);
	}

	@Transactional
	@PutMapping(value = "/actualizar",  produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<ReglaNegocioDestino> actualizar(@RequestBody ReglaNegocioDestino bean) throws Exception {
		logger.debug("ReglaNegocioDestinoRest.actualizar");
		bean = servicio.coreActualizar(this.getUsuarioActual(),bean);
		return new ResponseEntity<ReglaNegocioDestino>(bean, HttpStatus.OK);
	}

	@Transactional
	@PutMapping(value = "/anular",  produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<ReglaNegocioDestino> anular(@RequestBody ReglaNegocioDestino bean) throws Exception {
		logger.debug("ReglaNegocioDestinoRest.anular");
		if (bean==null)
		    return new ResponseEntity<ReglaNegocioDestino>(HttpStatus.NOT_FOUND);
		bean = servicio.coreAnular(this.getUsuarioActual(), bean);
		return new ResponseEntity<ReglaNegocioDestino>(bean, HttpStatus.OK);
	}

	@Transactional
	@DeleteMapping(value = "/eliminar", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Void> eliminar(@RequestBody ReglaNegocioDestino bean) throws Exception {
		logger.debug("ReglaNegocioDestinoRest.eliminar");
		if (bean==null){
		    return new ResponseEntity<Void>(HttpStatus.NOT_FOUND);
		}else{
		    servicio.coreEliminar(this.getUsuarioActual(),bean);
		    return new ResponseEntity<Void>(HttpStatus.OK);
		}
	}
	
	@Transactional
	@PostMapping(value = "/listar", produces = MediaType.APPLICATION_JSON_VALUE)
	public DominioPaginacion listartodo(@RequestBody FiltroPaginacionReglaNegocioDestino filtro) throws Exception {
		logger.debug("ReglaNegocioDestinoRest.listar");
		return consulta.listar(filtro);
	}

	@Transactional
	@PostMapping(value = "/listarSelectorEmp", produces = MediaType.APPLICATION_JSON_VALUE)
	public DominioPaginacion listarSelectorEmp(@RequestBody FiltroGenericoSelector filtro) throws Exception {
		logger.debug("ReglaNegocioDestinoRest.listarSelector");
		return consulta.listarSelectorEmp(filtro);
	}
	@Transactional
	@PostMapping(value = "/validarcorreoyausado", produces = MediaType.APPLICATION_JSON_VALUE)
	public Integer validarcorreoyausado(@RequestBody ReglaNegocioDestinoDto filtro) throws Exception {
		logger.debug("ReglaNegocioDestinoRest.validarcorreoyausado");
		return consulta.validarcorreoyausado(filtro);
	}
	
	
}
