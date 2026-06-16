package net.royal.spring.alertas.rest;

import java.util.Date;
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

import net.royal.spring.alertas.dao.impl.ConfiguracionServicioDaoImpl;
import net.royal.spring.alertas.dominio.ConfiguracionServicio;
import net.royal.spring.alertas.dominio.ConfiguracionServicioPk;
import net.royal.spring.alertas.dominio.dto.ConfiguracionServicioDto;
import net.royal.spring.alertas.servicio.impl.ConfiguracionServicioServicioImpl;
import net.royal.spring.alertas.servicio.validar.ConfiguracionServicioServicioValidar;
import net.royal.spring.framework.modelo.generico.DominioMensajeUsuario;
import net.royal.spring.framework.modelo.generico.dto.DtoTabla;
import net.royal.spring.framework.web.rest.GenericoRest;

@RestController
@RequestMapping("/spring/alertas/configuracionservicio")
@CrossOrigin(origins = "*")
public class ConfiguracionServicioRest extends GenericoRest {

	private static Logger logger = LogManager.getLogger(ConfiguracionServicioRest.class);

	@Autowired
	private ConfiguracionServicioServicioImpl servicio;

	@Autowired
	private ConfiguracionServicioServicioValidar validar;

	@Autowired
	private ConfiguracionServicioDaoImpl consulta;

	@Transactional
	@PutMapping(value = "/validar/{accion}", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<DominioMensajeUsuario>> validar(@Validated @PathVariable String accion, @RequestBody ConfiguracionServicio bean) throws Exception {
		logger.debug("ConfiguracionServicioRest.validar");
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
	public ResponseEntity<ConfiguracionServicio> obtenerPorId(@RequestBody ConfiguracionServicioPk pk) throws Exception {
		logger.debug("ConfiguracionServicioRest.obtenerPorId");
		ConfiguracionServicio bean = consulta.obtenerSinConfirmarPorId(pk);
		if (bean==null)
		    return new ResponseEntity<ConfiguracionServicio>(HttpStatus.NOT_FOUND);
		return new ResponseEntity<ConfiguracionServicio>(bean,HttpStatus.OK);
	}

	@Transactional
	@PostMapping(value = "/registrar",  produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<ConfiguracionServicio> registrar(@RequestBody ConfiguracionServicio bean) throws Exception {
		logger.debug("ConfiguracionServicioRest.registrar");
		bean =  servicio.coreInsertar(this.getUsuarioActual(),bean);
		return new ResponseEntity<ConfiguracionServicio>(bean, HttpStatus.CREATED);
	}

	@Transactional
	@PutMapping(value = "/actualizar",  produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<ConfiguracionServicio> actualizar(@RequestBody ConfiguracionServicio bean) throws Exception {
		logger.debug("ConfiguracionServicioRest.actualizar");
		bean = servicio.coreActualizar(this.getUsuarioActual(),bean);
		return new ResponseEntity<ConfiguracionServicio>(bean, HttpStatus.OK);
	}

	@Transactional
	@PutMapping(value = "/anular",  produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<ConfiguracionServicio> anular(@RequestBody ConfiguracionServicio bean) throws Exception {
		logger.debug("ConfiguracionServicioRest.anular");
		if (bean==null)
		    return new ResponseEntity<ConfiguracionServicio>(HttpStatus.NOT_FOUND);
		bean = servicio.coreAnular(this.getUsuarioActual(), bean);
		return new ResponseEntity<ConfiguracionServicio>(bean, HttpStatus.OK);
	}

	@Transactional
	@DeleteMapping(value = "/eliminar", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Void> eliminar(@RequestBody ConfiguracionServicio bean) throws Exception {
		logger.debug("ConfiguracionServicioRest.eliminar");
		if (bean==null){
		    return new ResponseEntity<Void>(HttpStatus.NOT_FOUND);
		}else{
		    servicio.coreEliminar(this.getUsuarioActual(),bean);
		    return new ResponseEntity<Void>(HttpStatus.OK);
		}
	}

	
	@Transactional
	@PostMapping(value = "/registrarConfiguracion",  produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<ConfiguracionServicio> registrarConfiguracion(@RequestBody ConfiguracionServicio bean) throws Exception {
		logger.debug("ConfiguracionServicioRest.registrar");
		bean =  servicio.guardarConfiguracion(bean);
		return new ResponseEntity<ConfiguracionServicio>(bean, HttpStatus.CREATED);
	}
	
	
	@Transactional
	@PostMapping(value = "/actualziarConfiguracion",  produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<ConfiguracionServicio> actualziarConfiguracion(@RequestBody ConfiguracionServicio bean) throws Exception {
		logger.debug("ConfiguracionServicioRest.registrar");
		logger.debug(bean.getFlgEnviarCorreoGenerico());
		bean =  servicio.actualizarConfiguracion(bean);
		return new ResponseEntity<ConfiguracionServicio>(bean, HttpStatus.CREATED);
	}
	
	
	
	@GetMapping(value = "/IdGeneradoSecuencia", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<ConfiguracionServicio> CodigoGenCabecerea() throws Exception {
		ConfiguracionServicio id = new ConfiguracionServicio();
		id.getPk().setIdConfiguracionServicio(consulta.generarSecuencia());
  		return new ResponseEntity<ConfiguracionServicio>(id, HttpStatus.OK);
	}
	
	
	@SuppressWarnings("rawtypes")
	@GetMapping(value = "/listarConfiguracion", produces = MediaType.APPLICATION_JSON_VALUE)
	public List listarConfiguracion() throws Exception {
		return servicio.listarConfiguracion();
	}
	
	
	@Transactional
	@PostMapping(value = "/enviarCorreoPrueba",  produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<String> enviarCorreoPrueba(@RequestBody ConfiguracionServicio bean) throws Exception {
		servicio.ConfigurarCorreo();
		return new ResponseEntity<String>(HttpStatus.CREATED);
	}
	
	@Transactional
	@PostMapping(value = "/guardarcorreo",  produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<ConfiguracionServicioDto> guardarcorreo(@RequestBody List<ConfiguracionServicioDto> lstdto) throws Exception {
		logger.debug("ConfiguracionServicioRest.guardarcorreo");
		ConfiguracionServicio x = new ConfiguracionServicio();
		x.setCorreoPrueba(lstdto.get(0).getCorreo_prueba());
		for (ConfiguracionServicioDto obj : lstdto) {
			ConfiguracionServicio nuevoobj = new ConfiguracionServicio();
			nuevoobj.setUltimaFechaInicioServicios(new Date());
			nuevoobj.setModificacionUsuario(getUsuarioActual().getUsuario());
			nuevoobj.setModificacionFecha(new Date());
			if(obj.getFlgactualizar().equals("NUEVO")) {
				nuevoobj.setCreacionUsuario(getUsuarioActual().getUsuario());
				nuevoobj.getPk().setIdConfiguracionServicio(consulta.generarSecuencia());
				nuevoobj.setCorreoPrueba(obj.getCorreo_prueba());
				servicio.coreInsertar(getUsuarioActual(), nuevoobj);
			} else {
				ConfiguracionServicio y = consulta.obtenerPorId(obj.getId_configuracion_servicio().intValue());
				 y.setCorreoPrueba(obj.getCorreo_prueba());
				servicio.coreActualizar(getUsuarioActual(), y);
			}
			//servicio.guardarConfiguracion(nuevoobj);
		}
		return new ResponseEntity<ConfiguracionServicioDto>(lstdto.get(0), HttpStatus.CREATED);
	}
	
	/**
	 * llamados por los hilos
	 * 
	 * @param bean
	 * @return
	 * @throws Exception
	 */
	@Transactional
	@PostMapping(value = "/crearcorreo", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<DtoTabla> crearcorreo(@RequestBody DtoTabla bean) throws Exception {
		consulta.cambiarEjecutandoCrearCorreo(bean.getEstado());
		return new ResponseEntity<DtoTabla>(bean, HttpStatus.CREATED);
	}
}
