package net.royal.spring.alertas.rest;

import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import net.royal.spring.alertas.dao.impl.CorreoDaoImpl;
import net.royal.spring.alertas.dominio.dto.CorreoDto;
import net.royal.spring.alertas.dominio.filtros.FiltroCorreoAdjunto;
import net.royal.spring.alertas.dominio.filtros.FiltroPaginacionCorreo;
import net.royal.spring.alertas.servicio.impl.CorreoAdjuntoServicioImpl;
import net.royal.spring.alertas.servicio.impl.CorreoCuerpoServicioImpl;
import net.royal.spring.alertas.servicio.impl.CorreoDestinoServicioImpl;
import net.royal.spring.alertas.servicio.impl.CorreoServicioImpl;
import net.royal.spring.framework.modelo.ReporteTransaccion;
import net.royal.spring.framework.modelo.correo.EmailTransaccion;
import net.royal.spring.framework.modelo.generico.DominioMensajeUsuario;
import net.royal.spring.framework.modelo.generico.DominioPaginacion;
import net.royal.spring.framework.modelo.generico.DominioTransaccion;
import net.royal.spring.framework.modelo.generico.dto.DtoTabla;
import net.royal.spring.framework.modelo.generico.DominioMensajeUsuario.tipo_mensaje;
import net.royal.spring.framework.web.rest.GenericoRest;

@RestController
@RequestMapping("/spring/publico/correosvalidar")
@CrossOrigin(origins = "*")
public class PublicoCorreoValidacionRest extends GenericoRest{
	
	private static Logger logger = LogManager.getLogger(PublicoCorreoValidacionRest.class);

	@Autowired
	private CorreoServicioImpl servicio;
	
	@Autowired
	private CorreoDestinoServicioImpl servicioDestino;
	
	@Autowired
	private CorreoAdjuntoServicioImpl servicioAdjuntos;
	
	@Autowired
	private CorreoCuerpoServicioImpl servicioCuerpo;
	
	@Transactional
	@PutMapping(value = "/validarEnviosCorreos", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<CorreoDto>> validarEnviosCorreos(@RequestBody List<DtoTabla> lista) throws Exception {
		logger.debug("ReporteMotorRest.ejecutar");
		ReporteTransaccion res = new ReporteTransaccion();
		
		List<CorreoDto> lst = servicio.validarEnviosCorreos(lista);
		
		return new ResponseEntity<List<CorreoDto>>(lst, HttpStatus.OK);
	}
	
	@Transactional
	@PutMapping(value = "/listarCorrreoDestino",  produces = MediaType.APPLICATION_JSON_VALUE)
	public DominioPaginacion listarCorrreo(@RequestBody FiltroPaginacionCorreo filtro) throws Exception {
		return servicioDestino.listarCorrreodestino(filtro);
	}
	
	@Transactional
	@PutMapping(value = "/listarCorreoAdjuntos", produces = MediaType.APPLICATION_JSON_VALUE)
	public DominioPaginacion listarCorreoAdjuntos(@RequestBody FiltroCorreoAdjunto filtro) throws Exception {
		
		return servicioAdjuntos.listarCorreoAdjuntos(filtro);
	}
	
	@Transactional
	@PutMapping(value = "/cuerpoCorreo",  produces = MediaType.APPLICATION_JSON_VALUE)
	public String  cuerpoCorreo(@RequestBody FiltroPaginacionCorreo filtro) throws Exception {
		return servicioCuerpo.CuerpoCampo(filtro);
	}
	
	
	
}
