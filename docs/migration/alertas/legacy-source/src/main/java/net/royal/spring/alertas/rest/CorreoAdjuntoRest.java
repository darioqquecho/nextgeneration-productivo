package net.royal.spring.alertas.rest;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.URLConnection;
import java.util.Base64;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import jakarta.servlet.http.HttpServletResponse;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.FileCopyUtils;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import net.royal.spring.alertas.dao.impl.CorreoAdjuntoDaoImpl;
import net.royal.spring.alertas.dominio.CorreoAdjunto;
import net.royal.spring.alertas.dominio.CorreoAdjuntoPk;
import net.royal.spring.alertas.dominio.dto.DtoCorreoAdjunto;
import net.royal.spring.alertas.dominio.filtros.FiltroCorreoAdjunto;
import net.royal.spring.alertas.dominio.filtros.FiltroPaginacionCorreo;
import net.royal.spring.alertas.servicio.impl.CorreoAdjuntoServicioImpl;
import net.royal.spring.alertas.servicio.validar.CorreoAdjuntoServicioValidar;
import net.royal.spring.framework.core.UFile;
import net.royal.spring.framework.modelo.generico.DominioMensajeUsuario;
import net.royal.spring.framework.modelo.generico.DominioPaginacion;
import net.royal.spring.framework.web.constante.ConstanteBoot;
import net.royal.spring.framework.web.rest.GenericoRest;

@RestController
@RequestMapping("/spring/alertas/correoadjunto")
@CrossOrigin(origins = "*")
public class CorreoAdjuntoRest extends GenericoRest {

	private static Logger logger = LogManager.getLogger(CorreoAdjuntoRest.class);

	@Autowired
	private CorreoAdjuntoServicioImpl servicio;

	@Autowired
	private CorreoAdjuntoServicioValidar validar;

	@Autowired
	private CorreoAdjuntoDaoImpl consulta;

	@Transactional
	@PutMapping(value = "/validar/{accion}", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<DominioMensajeUsuario>> validar(@Validated @PathVariable String accion, @RequestBody CorreoAdjunto bean) throws Exception {
		logger.debug("CorreoAdjuntoRest.validar");
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
	public ResponseEntity<CorreoAdjunto> obtenerPorId(@RequestBody CorreoAdjuntoPk pk) throws Exception {
		logger.debug("CorreoAdjuntoRest.obtenerPorId");
		CorreoAdjunto bean = consulta.obtenerSinConfirmarPorId(pk);
		if (bean==null)
		    return new ResponseEntity<CorreoAdjunto>(HttpStatus.NOT_FOUND);
		return new ResponseEntity<CorreoAdjunto>(bean,HttpStatus.OK);
	}

	@Transactional
	@PostMapping(value = "/registrar",  produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<CorreoAdjunto> registrar(@RequestBody CorreoAdjunto bean) throws Exception {
		logger.debug("CorreoAdjuntoRest.registrar");
		bean =  servicio.coreInsertar(this.getUsuarioActual(),bean);
		return new ResponseEntity<CorreoAdjunto>(bean, HttpStatus.CREATED);
	}

	@Transactional
	@PutMapping(value = "/actualizar",  produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<CorreoAdjunto> actualizar(@RequestBody CorreoAdjunto bean) throws Exception {
		logger.debug("CorreoAdjuntoRest.actualizar");
		bean = servicio.coreActualizar(this.getUsuarioActual(),bean);
		return new ResponseEntity<CorreoAdjunto>(bean, HttpStatus.OK);
	}
	
	@Transactional
	@PutMapping(value = "/listarCorreoAdjuntos", produces = MediaType.APPLICATION_JSON_VALUE)
	public DominioPaginacion listarCorreoAdjuntos(@RequestBody FiltroCorreoAdjunto filtro) throws Exception {
		
		return servicio.listarCorreoAdjuntos(filtro);
	}

	@Transactional
	@DeleteMapping(value = "/eliminar", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Void> eliminar(@RequestBody CorreoAdjunto bean) throws Exception {
		logger.debug("CorreoAdjuntoRest.eliminar");
		if (bean==null){
		    return new ResponseEntity<Void>(HttpStatus.NOT_FOUND);
		}else{
		    servicio.coreEliminar(this.getUsuarioActual(),bean);
		    return new ResponseEntity<Void>(HttpStatus.OK);
		}
	}
//@ResponseBody byte[]
	@Transactional
	@RequestMapping(method = RequestMethod.POST, value = "/imprimir")
	public void imprimir(HttpServletResponse response, @RequestBody DtoCorreoAdjunto dto)
			throws Exception {
//		byte[] buffer = new byte[8192];
//		String rutaReporte = "";
//		String carpeta = "temporal";
//		String archivoDestino = UFile.rutaFisicaWebApp() + File.separator + ConstanteBoot.RECURSOS_GLOBAL + File.separator
//				+ carpeta+ File.separator;
		
		logger.debug("CORREO -> OBTENER ARCHIVO");
		logger.debug(dto.getRutaCompleta());
		logger.debug(dto.getNombreArchivo());	
		logger.debug( dto.getCuerpoAdjunto()  );
		
		int index = dto.getNombreArchivo().lastIndexOf('.');
		String extension = dto.getNombreArchivo().substring(index + 1).toLowerCase();			
		
		/*String rutaArchivo = UFile.rutaFisicaWebApp() + File.separator +  ConstanteBoot.RECURSOS_GLOBAL + File.separator + "temporal"
				+ File.separator + UFile.archivoUnico() + "."+ extension;*/
		String rutaArchivo = env.getProperty("ruta.fisica.temporal") 
				+ File.separator + UFile.archivoUnico() + "."+ extension;
		
		logger.debug(rutaArchivo);
		
		if (UFile.existeArchivo(rutaArchivo)) {
			UFile.eliminarArchivo(rutaArchivo);
		}

		OutputStream out = new FileOutputStream(rutaArchivo);
	     out.write(dto.getCuerpoAdjunto());
	     out.close();
		File file = new File(rutaArchivo);

		String mimeType = URLConnection.guessContentTypeFromName(file.getName());		
		if (mimeType == null) {			
			mimeType = "application/octet-stream;charset=UTF-8";
		}

		String nombreArchivo=UFile.obtenerNombreWebDescargar(file.getName());
		response.setContentType(mimeType);
		response.setHeader("Content-Disposition", String.format("attachment; filename=\"" + nombreArchivo + "\""));
		response.setContentLength((int) file.length());
		InputStream inputStream = new BufferedInputStream(new FileInputStream(file));
		FileCopyUtils.copy(inputStream, response.getOutputStream());
	}
}
