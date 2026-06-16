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

import net.royal.spring.alertas.dao.impl.ReglaNegocioAdicionalDaoImpl;
import net.royal.spring.alertas.dominio.ReglaNegocioAdicional;
import net.royal.spring.alertas.dominio.ReglaNegocioAdicionalPk;
import net.royal.spring.alertas.dominio.filtros.FiltroPaginacionReglaNegocioAdicional;
import net.royal.spring.alertas.servicio.impl.ReglaNegocioAdicionalServicioImpl;
import net.royal.spring.alertas.servicio.validar.ReglaNegocioAdicionalServicioValidar;
import net.royal.spring.framework.modelo.generico.DominioMensajeUsuario;
import net.royal.spring.framework.modelo.generico.DominioPaginacion;
import net.royal.spring.framework.web.rest.GenericoRest;

@RestController
@RequestMapping("/spring/alertas/reglanegocioadicional")
@CrossOrigin(origins = "*")
public class ReglaNegocioAdicionalRest extends GenericoRest {

	private static Logger logger = LogManager.getLogger(ReglaNegocioAdicionalRest.class);

	@Autowired
	private ReglaNegocioAdicionalServicioImpl servicio;

	@Autowired
	private ReglaNegocioAdicionalServicioValidar validar;

	@Autowired
	private ReglaNegocioAdicionalDaoImpl consulta;

	@Transactional
	@PutMapping(value = "/validar/{accion}", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<DominioMensajeUsuario>> validar(@Validated @PathVariable String accion, @RequestBody ReglaNegocioAdicional bean) throws Exception {
		logger.debug("ReglaNegocioAdicionalRest.validar");
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
	public ResponseEntity<ReglaNegocioAdicional> obtenerPorId(@RequestBody ReglaNegocioAdicionalPk pk) throws Exception {
		logger.debug("ReglaNegocioAdicionalRest.obtenerPorId");
		ReglaNegocioAdicional bean = consulta.obtenerSinConfirmarPorId(pk);
		if (bean==null)
		    return new ResponseEntity<ReglaNegocioAdicional>(HttpStatus.NOT_FOUND);
		return new ResponseEntity<ReglaNegocioAdicional>(bean,HttpStatus.OK);
	}

	@Transactional
	@PostMapping(value = "/registrar",  produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<ReglaNegocioAdicional> registrar(@RequestBody ReglaNegocioAdicional bean) throws Exception {
		logger.debug("ReglaNegocioAdicionalRest.registrar");
		
		//bean =  servicio.coreInsertar(this.getUsuarioActual(),bean);
		bean = consulta.coreInsertar(bean);
		return new ResponseEntity<ReglaNegocioAdicional>(bean, HttpStatus.CREATED);
	}
	
	@Transactional
	@GetMapping(value="/generarsecuencia", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Integer> generarsecuencia() throws Exception {
		logger.debug("ReglaNegocioAdicionalRest.generarsecuencia");
		 Integer secuencia = consulta.generarSecuencia(); 
		 logger.debug("secuencia: "+secuencia);
		if (secuencia==null)
		    return new ResponseEntity<Integer>(HttpStatus.NOT_FOUND);
		return new ResponseEntity<Integer>(secuencia,HttpStatus.OK);
	}

	@Transactional
	@PutMapping(value = "/actualizar",  produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<ReglaNegocioAdicional> actualizar(@RequestBody ReglaNegocioAdicional bean) throws Exception {
		logger.debug("ReglaNegocioAdicionalRest.actualizar");
		bean = servicio.coreActualizar(this.getUsuarioActual(),bean);
		return new ResponseEntity<ReglaNegocioAdicional>(bean, HttpStatus.OK);
	}

	@Transactional
	@PutMapping(value = "/anular",  produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<ReglaNegocioAdicional> anular(@RequestBody ReglaNegocioAdicional bean) throws Exception {
		logger.debug("ReglaNegocioAdicionalRest.anular");
		if (bean==null)
		    return new ResponseEntity<ReglaNegocioAdicional>(HttpStatus.NOT_FOUND);
		bean = servicio.coreAnular(this.getUsuarioActual(), bean);
		return new ResponseEntity<ReglaNegocioAdicional>(bean, HttpStatus.OK);
	}

	@Transactional
	@DeleteMapping(value = "/eliminar", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Void> eliminar(@RequestBody ReglaNegocioAdicional bean) throws Exception {
		logger.debug("ReglaNegocioAdicionalRest.eliminar");
		if (bean==null){
		    return new ResponseEntity<Void>(HttpStatus.NOT_FOUND);
		}else{
		    servicio.coreEliminar(this.getUsuarioActual(),bean);
		    return new ResponseEntity<Void>(HttpStatus.OK);
		}
	}

	@Transactional
	@PostMapping(value = "/listar", produces = MediaType.APPLICATION_JSON_VALUE)
	public DominioPaginacion listarAdicional(@RequestBody FiltroPaginacionReglaNegocioAdicional filtro)
			throws Exception {
		logger.debug("ReglaNegocioAdicionalRest.listar");
		return consulta.listar(filtro);
	}
}
