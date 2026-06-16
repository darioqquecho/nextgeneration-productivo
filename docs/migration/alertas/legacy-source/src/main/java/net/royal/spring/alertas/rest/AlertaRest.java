package net.royal.spring.alertas.rest;

import java.util.Date;
import java.util.List;
import java.util.UUID;

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

import net.royal.spring.alertas.boot.SpringAlertasConstanteBoot;
import net.royal.spring.alertas.dao.impl.AlertaDaoImpl;
import net.royal.spring.alertas.dominio.Alerta;
import net.royal.spring.alertas.dominio.AlertaPk;
import net.royal.spring.alertas.dominio.filtros.FiltroPaginacionAlerta;
import net.royal.spring.alertas.servicio.impl.AlertaServicioImpl;
import net.royal.spring.alertas.servicio.validar.AlertaServicioValidar;
import net.royal.spring.framework.modelo.generico.DominioMensajeUsuario;
import net.royal.spring.framework.modelo.generico.DominioPaginacion;
import net.royal.spring.framework.modelo.seguridad.SeguridadUsuarioActual;
import net.royal.spring.framework.web.rest.GenericoRest;

@RestController
@RequestMapping("/spring/alertas/alerta")
@CrossOrigin(origins = "*")
public class AlertaRest extends GenericoRest {

	private static Logger logger = LogManager.getLogger(AlertaRest.class);

	@Autowired
	private AlertaServicioImpl servicio;

	@Autowired
	private AlertaServicioValidar validar;

	@Autowired
	private AlertaDaoImpl consulta;

	@Transactional
	@PutMapping(value = "/validar/{accion}", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<DominioMensajeUsuario>> validar(@Validated @PathVariable String accion,
			@RequestBody Alerta bean) throws Exception {
		logger.debug("AlertaRest.validar");
		logger.debug(accion);
		logger.debug(bean);
		List<DominioMensajeUsuario> lst = validar.core(this.getUsuarioActual(), accion, bean);
		logger.debug(lst.size());
		if (lst.isEmpty())
			return new ResponseEntity<List<DominioMensajeUsuario>>(HttpStatus.OK);
		return new ResponseEntity<List<DominioMensajeUsuario>>(lst, HttpStatus.OK);
	}

	@Transactional
	@GetMapping(value = "/obtenerporid", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Alerta> obtenerPorId(@RequestBody AlertaPk pk) throws Exception {
		logger.debug("AlertaRest.obtenerPorId");
		Alerta bean = consulta.obtenerPorId(pk);
		if (bean == null)
			return new ResponseEntity<Alerta>(HttpStatus.NOT_FOUND);
		return new ResponseEntity<Alerta>(bean, HttpStatus.OK);
	}

	@Transactional
	@PostMapping(value = "/registrar", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Alerta> registrar(@RequestBody Alerta bean) throws Exception {
		logger.debug("AlertaRest.registrar");
		bean = servicio.coreInsertar(this.getUsuarioActual(), bean);
		return new ResponseEntity<Alerta>(bean, HttpStatus.CREATED);
	}

	@Transactional
	@PutMapping(value = "/actualizar", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Alerta> actualizar(@RequestBody Alerta bean) throws Exception {
		logger.debug("AlertaRest.actualizar");
		bean = servicio.coreActualizar(this.getUsuarioActual(), bean);
		return new ResponseEntity<Alerta>(bean, HttpStatus.OK);
	}

	@Transactional
	@PutMapping(value = "/anular", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Alerta> anular(@RequestBody Alerta bean) throws Exception {
		logger.debug("AlertaRest.anular");
		if (bean == null)
			return new ResponseEntity<Alerta>(HttpStatus.NOT_FOUND);
		bean = servicio.coreAnular(this.getUsuarioActual(), bean);
		return new ResponseEntity<Alerta>(bean, HttpStatus.OK);
	}

	@Transactional
	@DeleteMapping(value = "/eliminar", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Void> eliminar(@RequestBody Alerta bean) throws Exception {
		logger.debug("AlertaRest.eliminar");
		if (bean == null) {
			return new ResponseEntity<Void>(HttpStatus.NOT_FOUND);
		} else {
			servicio.coreEliminar(this.getUsuarioActual(), bean);
			return new ResponseEntity<Void>(HttpStatus.OK);
		}
	}

	@Transactional
	@PostMapping(value = "/evaluarReglaNegocioHilo", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Void> evaluarReglaNegocioHilo() throws Exception {
		logger.debug("AlertaRest.evaluarReglaNegocioHilo");
			servicio.cambiarEstadoEjecucion(SpringAlertasConstanteBoot.PROCESO_EVALUAR_REGLANEGOCIO, "S");
			logger.debug("AlertaRest.valida existencia usuario");
			SeguridadUsuarioActual usu = this.getUsuarioActual();
			if (usu == null) {
				logger.debug("El usuario esta nulo");
				usu = new SeguridadUsuarioActual();
				usu.setUsuario("SINUSUARIO");
			}
			
			String tag = UUID.randomUUID().toString().substring(1, 8);
			servicio.evaluarReglaNegocioHilo(usu,tag);
			return new ResponseEntity<Void>(HttpStatus.OK);
	}
	
	
	@Transactional
	@PostMapping(value = "/extraerDataHilo", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Void> extraerDataHilo() throws Exception {
		logger.debug("AlertaRest.ExtraerDataHilo");
			servicio.cambiarEstadoEjecucion(SpringAlertasConstanteBoot.PROCESO_EXTRAER_DATA, "S");
			logger.debug("AlertaRest.valida existencia usuario");
			SeguridadUsuarioActual usu = this.getUsuarioActual();
			if (usu == null) {
				logger.debug("El usuario esta nulo");
				usu = new SeguridadUsuarioActual();
				usu.setUsuario("SINUSUARIO");
			}
			String tag = UUID.randomUUID().toString().substring(1, 8);
			servicio.extraerDataHilo(usu,tag);
			return new ResponseEntity<Void>(HttpStatus.OK);
	}
	
	@Transactional
	@PostMapping(value = "/crearAlertaHilo", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Void> crearAlertaHilo() throws Exception {
		logger.debug("AlertaRest.crearAlertaHilo");
			servicio.cambiarEstadoEjecucion(SpringAlertasConstanteBoot.PROCESO_CREAR_ALERTA, "S");
			logger.debug("AlertaRest.valida existencia usuario");
			SeguridadUsuarioActual usu = this.getUsuarioActual();
			if (usu == null) {
				logger.debug("El usuario esta nulo");
				usu = new SeguridadUsuarioActual();
				usu.setUsuario("SINUSUARIO");
			}
			String tag = UUID.randomUUID().toString().substring(1, 8);
			servicio.crearAlertaHilo(usu,tag);
			return new ResponseEntity<Void>(HttpStatus.OK);
	}
	
	
	@Transactional
	@PostMapping(value = "/crearCorreoHilo", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Void> crearCorreoHilo() throws Exception {
		logger.debug("AlertaRest.crearCorreoHilo");
			servicio.cambiarEstadoEjecucion(SpringAlertasConstanteBoot.PROCESO_CREAR_CORREO, "S");
			logger.debug("AlertaRest.valida existencia usuario");
			SeguridadUsuarioActual usu = this.getUsuarioActual();
			if (usu == null) {
				logger.debug("El usuario esta nulo");
				usu = new SeguridadUsuarioActual();
				usu.setUsuario("SINUSUARIO");
			}
			String tag = UUID.randomUUID().toString().substring(1, 8);
			servicio.crearCorreoHilo(usu,tag);
			return new ResponseEntity<Void>(HttpStatus.OK);
	}
	
	
	
	@Transactional
	@PostMapping(value = "/envioCorreoMasivoHilo", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Void> envioCorreoMasivoHilo() throws Exception {
		logger.debug("AlertaRest.envioCorreoMasivoHilo");
			servicio.cambiarEstadoEjecucion(SpringAlertasConstanteBoot.PROCESO_ENVIO_CORREO, "S");
			logger.debug("AlertaRest.valida existencia usuario");
			SeguridadUsuarioActual usu = this.getUsuarioActual();
			if (usu == null) {
				logger.debug("El usuario esta nulo");
				usu = new SeguridadUsuarioActual();
				usu.setUsuario("SINUSUARIO");
			}
			String tag = UUID.randomUUID().toString().substring(1, 8);
			servicio.envioCorreoMasivoHilo(usu,tag);
			return new ResponseEntity<Void>(HttpStatus.OK);
	}
	
	
	@Transactional
	@PutMapping(value = "/listarAlerta",  produces = MediaType.APPLICATION_JSON_VALUE)
	public DominioPaginacion listarAlerta(@RequestBody FiltroPaginacionAlerta filtro) throws Exception {
		return servicio.listarAlerta(filtro);
	} 
	
	@Transactional
	@PutMapping(value = "/listarAlertaDetalle",  produces = MediaType.APPLICATION_JSON_VALUE)
	public DominioPaginacion listarAlertaDetalle(@RequestBody FiltroPaginacionAlerta filtro) throws Exception {
		return servicio.listarAlertaDetalle(filtro);
	} 
	
	@Transactional
	@PutMapping(value = "/listarAlertaAdicional",  produces = MediaType.APPLICATION_JSON_VALUE)
	public DominioPaginacion listarAlertaAdicional(@RequestBody FiltroPaginacionAlerta filtro) throws Exception {
		return servicio.listarAlertaAdicional(filtro);
	}
	
	@Transactional
	@PutMapping(value = "/listarAlertaDestino",  produces = MediaType.APPLICATION_JSON_VALUE)
	public DominioPaginacion listarAlertaDestino(@RequestBody FiltroPaginacionAlerta filtro) throws Exception {
		return servicio.listarAlertaDestino(filtro);
	} 
	
	@Transactional
	@PutMapping(value = "/listarAlertaPlan",  produces = MediaType.APPLICATION_JSON_VALUE)
	public DominioPaginacion listarAlertaPlan(@RequestBody FiltroPaginacionAlerta filtro) throws Exception {
		return servicio.listarAlertaPlan(filtro);
	} 
	
	@GetMapping(value="/estavivo",produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<String> estavivo() {
		logger.debug("UsuarioRest (Privado).estavivo");
		Date now = new Date();		
		try {
			//List<Alerta> lst = consulta.listarTodos();
			//logger.debug(lst.size());
			
			SeguridadUsuarioActual usu = this.getUsuarioActual();
			logger.debug(usu);
			//logger.debug(usu.getUsuario());	
		} catch (Exception e) {
			logger.debug("usuario actual no encontrado");
			e.printStackTrace();
		}		
		
		return new ResponseEntity<String>("SI - 3.3.2 " + now.toString(), HttpStatus.OK);
	}
	
}
