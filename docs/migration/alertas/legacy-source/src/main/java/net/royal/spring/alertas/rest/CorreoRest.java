package net.royal.spring.alertas.rest;

import java.util.ArrayList;
import java.util.Base64;
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

import net.royal.spring.alertas.dao.impl.CorreoDaoImpl;
import net.royal.spring.alertas.dominio.Correo;
import net.royal.spring.alertas.dominio.CorreoAdjunto;
import net.royal.spring.alertas.dominio.CorreoCuerpo;
import net.royal.spring.alertas.dominio.CorreoDestino;
import net.royal.spring.alertas.dominio.CorreoPk;
import net.royal.spring.alertas.dominio.filtros.FiltroPaginacionCorreo;
import net.royal.spring.alertas.servicio.impl.CorreoServicioImpl;
import net.royal.spring.alertas.servicio.validar.CorreoServicioValidar;
import net.royal.spring.framework.core.UException;
import net.royal.spring.framework.modelo.correo.EmailDestino;
import net.royal.spring.framework.modelo.correo.EmailTransaccion;
import net.royal.spring.framework.modelo.correo.EmailDestino.tipo_destino;
import net.royal.spring.framework.modelo.generico.DominioAdjunto;
import net.royal.spring.framework.modelo.generico.DominioMensajeUsuario;
import net.royal.spring.framework.modelo.generico.DominioPaginacion;
import net.royal.spring.framework.modelo.generico.DominioParametroPersistencia;
import net.royal.spring.framework.modelo.generico.DominioMensajeUsuario.tipo_mensaje;
import net.royal.spring.framework.modelo.generico.dto.DtoTabla;
import net.royal.spring.framework.modelo.seguridad.SeguridadUsuarioActual;
import net.royal.spring.framework.util.UByte;
import net.royal.spring.framework.util.UString;
import net.royal.spring.framework.web.rest.GenericoRest;

@RestController
@RequestMapping("/spring/alertas/correo")
@CrossOrigin(origins = "*")
public class CorreoRest extends GenericoRest {
	
	private static Logger logger = LogManager.getLogger(CorreoRest.class);
	
	@Autowired
	private CorreoServicioImpl servicio;

	@Autowired
	private CorreoServicioValidar validar;

	@Autowired
	private CorreoDaoImpl consulta;

	@Transactional
	@PutMapping(value = "/validar/{accion}", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<DominioMensajeUsuario>> validar(@Validated @PathVariable String accion,
			@RequestBody Correo bean) throws Exception {
		List<DominioMensajeUsuario> lst = validar.core(this.getUsuarioActual(), accion, bean);
		if (lst.isEmpty())
			return new ResponseEntity<List<DominioMensajeUsuario>>(HttpStatus.OK);
		return new ResponseEntity<List<DominioMensajeUsuario>>(lst, HttpStatus.OK);
	}

	@Transactional
	@GetMapping(value = "/obtenerporid", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Correo> obtenerPorId(@RequestBody CorreoPk pk) throws Exception {
		Correo bean = consulta.obtenerPorId(pk);
		if (bean == null)
			return new ResponseEntity<Correo>(HttpStatus.NOT_FOUND);
		return new ResponseEntity<Correo>(bean, HttpStatus.OK);
	}

	@Transactional
	@PostMapping(value = "/registrar", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Correo> registrar(@RequestBody Correo bean) throws Exception {
		bean = servicio.coreInsertar(this.getUsuarioActual(), bean);
		return new ResponseEntity<Correo>(bean, HttpStatus.CREATED);
	}

	@Transactional
	@PutMapping(value = "/actualizar", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Correo> actualizar(@RequestBody Correo bean) throws Exception {
		bean = servicio.coreActualizar(this.getUsuarioActual(), bean);
		return new ResponseEntity<Correo>(bean, HttpStatus.OK);
	}

	@Transactional
	@PutMapping(value = "/anular", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Correo> anular(@RequestBody Correo bean) throws Exception {
		if (bean == null)
			return new ResponseEntity<Correo>(HttpStatus.NOT_FOUND);
		bean = servicio.coreAnular(this.getUsuarioActual(), bean);
		return new ResponseEntity<Correo>(bean, HttpStatus.OK);
	}

	@Transactional
	@DeleteMapping(value = "/eliminar", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Void> eliminar(@RequestBody Correo bean) throws Exception {
		if (bean == null) {
			return new ResponseEntity<Void>(HttpStatus.NOT_FOUND);
		} else {
			servicio.coreEliminar(this.getUsuarioActual(), bean);
			return new ResponseEntity<Void>(HttpStatus.OK);
		}
	}

	@Transactional
	@PutMapping(value = "/listarCorrreo", produces = MediaType.APPLICATION_JSON_VALUE)
	public DominioPaginacion listarCorrreo(@RequestBody FiltroPaginacionCorreo filtro) throws Exception {
		logger.debug("listarCorrreo");
		
		SeguridadUsuarioActual usu = this.getUsuarioActual();
		logger.debug(usu.getAplicacionCodigo());
		String isAdmin = UString.obtenerValorCadenaSinNulo(usu.getEsAdministradorAplicacion());
		logger.debug(isAdmin);
		 if (isAdmin.equals("S")) {
			 return servicio.listarCorrreoAdministrador(filtro);
		 }
		 else
		 {
			return servicio.listarCorrreo(filtro);
		 }		
	}

	@Transactional
	@PutMapping(value = "/actualizarEstadosSeleccionados", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Correo> actualizarEstadosSeleccionados(@RequestBody Correo bean) throws Exception {
		bean = servicio.actualizarEstadosSeleccionadosServ(bean);
		return new ResponseEntity<Correo>(bean, HttpStatus.OK);
	}

	@Transactional
	@PostMapping(value = "/enviarCorreoPrueba", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<String> enviarCorreoPrueba(@RequestBody Correo bean) throws Exception {
		servicio.ConfigurarCorreo(bean);
		return new ResponseEntity<String>(HttpStatus.CREATED);
	}

	@Transactional
	@PostMapping(value = "/enviarCorreoRegular", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<String> enviarCorreoRegular(@RequestBody Correo bean) throws Exception {
		servicio.ConfigurarCorreoRegular(bean);
		return new ResponseEntity<String>(HttpStatus.CREATED);
	}

	@SuppressWarnings({ "unchecked", "rawtypes" })
	@Transactional
	@GetMapping(value = "/comboreglanegocio", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<DtoTabla>> comboreglanegocio() throws Exception {
		List<DominioParametroPersistencia> parametros = new ArrayList<DominioParametroPersistencia>();
		List datos = consulta.listarPorQuery(DtoTabla.class, "correo.comboreglanegocio", parametros);
		return new ResponseEntity<List<DtoTabla>>(datos, HttpStatus.OK);
	}
	
	@Transactional
	@PostMapping(value = "/registrarexterno", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<EmailTransaccion> registrarExterno(@RequestBody EmailTransaccion bean) throws Exception {
		try {
			logger.debug("registrarexterno");
			SeguridadUsuarioActual usu = null;
			try {
				usu = this.getUsuarioActual();				
			} catch (Exception e) {
				e.printStackTrace();
			}
			if (usu==null) {
				usu=new SeguridadUsuarioActual();
				usu.setUsuario("EXTERNO");
				usu.setDireccionIp("IP EXTERNO");
			}
			
			Correo correo = new Correo();
			
			// tabla correo
			correo.setAsunto(bean.getAsunto());
			//bean.getTraceReferencia()
			//bean.getRemitenteCorreo()
			//bean.getRemitenteNombre()
			correo.setProcesoId(bean.getProcesoId());
			correo.setReferenciaId(bean.getReferenciaId());
			correo.setReferenciaPadreId(bean.getReferenciaPadreId());
			correo.setTransaccionId(bean.getTransaccionId());
			correo.setSeguimientoId(bean.getSeguimientoId());
			correo.setAccionId(bean.getAccionId());
			correo.setSubAccionId(bean.getSubAccionId());
			correo.setAuxTipoTransaccion(bean.getTipoTransaccion());
			correo.setPerfilCorreoId(bean.getPerfilCorreoId());
			correo.setReferenciaPrincipalId(bean.getReferenciaPrincipalId());
			
			// tabla correo correo
			byte[] addcuerpo = UByte.obtenerArchivoDesde(bean.getCuerpoCorreoBase64(), bean.getCuerpoCorreoBytes());
			if (addcuerpo!=null) {
				correo.setCorreoCuerpo(new CorreoCuerpo(addcuerpo));
			}
						
			// cargar los destinatarios
			for (EmailDestino destino : bean.getListaCorreoDestino()) {
				CorreoDestino des = new CorreoDestino();
				des.getPk().setCorreoDestino(destino.getCorreoDestino());
				if(destino.getDestino()==null) {
					destino.setDestino(tipo_destino.TO);
				}
				des.setIdTipoDestino(destino.getDestino().toString());
				correo.getListaCorreoDestino().add(des);
			}
			logger.debug("bean.getListaCorreoDestino().size():"+bean.getListaCorreoDestino().size());
			logger.debug("correo.getListaCorreoDestino().size():"+correo.getListaCorreoDestino().size());
			
			//cargar adjuntos
			correo.setListaCorreoAdjuntos(new ArrayList<CorreoAdjunto>());
			for (DominioAdjunto adjunto : bean.getListaCorreoAdjunto()) {
				byte[] adjFile = UByte.obtenerArchivoDesde(adjunto.getArchivoAdjuntoBase64(), adjunto.getArchivoAdjuntoBytes());
				CorreoAdjunto adj = new CorreoAdjunto();
				adj.setCuerpoAdjunto(adjFile);
				adj.setNombreArchivo(adjunto.getNombreArchivo());
				correo.getListaCorreoAdjuntos().add(adj);
			}
			logger.debug("bean.getListaCorreoAdjunto().size():"+bean.getListaCorreoAdjunto().size());
			logger.debug("correo.getListaCorreoAdjuntos().size():"+correo.getListaCorreoAdjuntos().size());
			
			correo = servicio.coreInsertar(usu, correo);
			logger.debug("correo.getPk().getIdCorreo():"+correo.getPk().getIdCorreo());
			
			bean.setTransaccionEstado(EmailTransaccion.OK);			
		} catch (Exception e) {
			bean.setTransaccionEstado(EmailTransaccion.ERROR);
			bean.getTransaccionListaMensajes().add(new DominioMensajeUsuario(tipo_mensaje.ERROR, e.getMessage(),UException.getStackTrace(e)));
			e.printStackTrace();
		}
		return new ResponseEntity<EmailTransaccion>(bean, HttpStatus.CREATED);
	}
	
	@Transactional
	@PutMapping(value = "/reenvioporreferencia", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<EmailTransaccion> reenvioporreferencia(@RequestBody EmailTransaccion bean) throws Exception {
		logger.debug("registrarexterno");
		return new ResponseEntity<EmailTransaccion>(bean, HttpStatus.CREATED);
	}
	
	@Transactional
	@PutMapping(value = "/estadoporreferencia", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<EmailTransaccion> estadoporreferencia(@RequestBody EmailTransaccion bean) throws Exception {
		logger.debug("estadoporreferencia");
		return new ResponseEntity<EmailTransaccion>(bean, HttpStatus.CREATED);
	}
	
}
