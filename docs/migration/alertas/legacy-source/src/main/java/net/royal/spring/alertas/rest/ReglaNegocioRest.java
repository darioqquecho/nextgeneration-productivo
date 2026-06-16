package net.royal.spring.alertas.rest;

import java.math.BigDecimal;
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

import net.royal.spring.alertas.dao.impl.ColormastDaoImpl;
import net.royal.spring.alertas.dao.impl.ReglaNegocioAdicionalDaoImpl;
import net.royal.spring.alertas.dao.impl.ReglaNegocioDaoImpl;
import net.royal.spring.alertas.dao.impl.ReglaNegocioDestinoDaoImpl;
import net.royal.spring.alertas.dao.impl.ReglaNegocioDetalleDaoImpl;
import net.royal.spring.alertas.dao.impl.ReglaNegocioPlanDaoImpl;
import net.royal.spring.alertas.dao.impl.ReglaNegocioProgramacionDaoImpl;
import net.royal.spring.alertas.dominio.Colormast;
import net.royal.spring.alertas.dominio.ReglaNegocio;
import net.royal.spring.alertas.dominio.ReglaNegocioAdicional;
import net.royal.spring.alertas.dominio.ReglaNegocioDestino;
import net.royal.spring.alertas.dominio.ReglaNegocioDetalle;
import net.royal.spring.alertas.dominio.ReglaNegocioPk;
import net.royal.spring.alertas.dominio.ReglaNegocioPlan;
import net.royal.spring.alertas.dominio.ReglaNegocioProgramacion;
import net.royal.spring.alertas.dominio.dto.DtoBdComparar;
import net.royal.spring.alertas.dominio.dto.DtoBdEjecutar;
import net.royal.spring.alertas.dominio.dto.DtoServidorHora;
import net.royal.spring.alertas.dominio.dto.DtoTabla;
import net.royal.spring.alertas.dominio.dto.ReglaNegocioAdicionalDto;
import net.royal.spring.alertas.dominio.dto.ReglaNegocioDestinoDto;
import net.royal.spring.alertas.dominio.dto.ReglaNegocioDetalleDto;
import net.royal.spring.alertas.dominio.dto.ReglaNegocioDto;
import net.royal.spring.alertas.dominio.dto.ReglaNegocioPlanDto;
import net.royal.spring.alertas.dominio.dto.ReglaNegocioProgramacionDto;
import net.royal.spring.alertas.dominio.dto.ReglaNegocioTransaccion;
import net.royal.spring.alertas.dominio.filtros.FiltroPaginacionColor;
import net.royal.spring.alertas.dominio.filtros.FiltroPaginacionReglaNegocio;
import net.royal.spring.alertas.dominio.filtros.FiltroPaginacionReglaNegocioAdicional;
import net.royal.spring.alertas.dominio.filtros.FiltroPaginacionReglaNegocioProgramacion;
import net.royal.spring.alertas.servicio.impl.AlertaServicioImpl;
import net.royal.spring.alertas.servicio.impl.ReglaNegocioServicioImpl;
import net.royal.spring.alertas.servicio.validar.ReglaNegocioServicioValidar;
import net.royal.spring.framework.core.UException;
import net.royal.spring.framework.modelo.EjecucionApiTransaccion;
import net.royal.spring.framework.modelo.ErrorTransaccion;
import net.royal.spring.framework.modelo.generico.DominioMensajeUsuario;
import net.royal.spring.framework.modelo.generico.DominioMensajeUsuario.tipo_mensaje;
import net.royal.spring.framework.modelo.generico.DominioPaginacion;
import net.royal.spring.framework.modelo.generico.DominioTransaccion;
import net.royal.spring.framework.modelo.seguridad.SeguridadUsuarioActual;
import net.royal.spring.framework.util.UBigDecimal;
import net.royal.spring.framework.util.UString;
import net.royal.spring.framework.web.rest.GenericoRest;

@RestController
@RequestMapping("/spring/alertas/reglanegocio")
@CrossOrigin(origins = "*")
public class ReglaNegocioRest extends GenericoRest {

	private static Logger logger = LogManager.getLogger(ReglaNegocioRest.class);

	@Autowired
	private ReglaNegocioServicioImpl servicio;

	@Autowired
	private ReglaNegocioServicioValidar validar;

	@Autowired
	private ReglaNegocioDaoImpl consulta;

	@Autowired
	private ReglaNegocioAdicionalDaoImpl consultaAdicional;

	@Autowired
	private ReglaNegocioDetalleDaoImpl consultaDetalle;

	@Autowired
	private ReglaNegocioPlanDaoImpl consultaPlan;

	@Autowired
	private ReglaNegocioDestinoDaoImpl consultaDestino;

	@Autowired
	private ReglaNegocioProgramacionDaoImpl consultaProgramacion;

	@Autowired
	private ColormastDaoImpl consultaColor;
	
	@Autowired
	private AlertaServicioImpl alertaServicio;
	

	@Transactional
	@PutMapping(value = "/validar/{accion}", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<DominioMensajeUsuario>> validar(@Validated @PathVariable String accion,
			@RequestBody ReglaNegocio bean) throws Exception {
		logger.debug("ReglaNegocioRest.validar");
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
	public ResponseEntity<ReglaNegocio> obtenerPorId(@RequestBody ReglaNegocioPk pk) throws Exception {
		logger.debug("ReglaNegocioRest.obtenerPorId");
		ReglaNegocio bean = consulta.obtenerSinConfirmarPorId(pk);
		if (bean == null)
			return new ResponseEntity<ReglaNegocio>(HttpStatus.NOT_FOUND);
		return new ResponseEntity<ReglaNegocio>(bean, HttpStatus.OK);
	}

	@Transactional
	@PutMapping(value = "/obtenerporidpost", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<ReglaNegocio> obtenerPorIdPost(@RequestBody ReglaNegocioPk pk) throws Exception {
		logger.debug("ReglaNegocioRest.obtenerPorId");
		FiltroPaginacionReglaNegocioAdicional frnA = new FiltroPaginacionReglaNegocioAdicional();
		frnA.setIdReglaNegocio(new BigDecimal(pk.getIdReglaNegocio())); 
		ReglaNegocio bean = consulta.obtenerSinConfirmarPorId(pk);
		if (bean == null)
			return new ResponseEntity<ReglaNegocio>(HttpStatus.NOT_FOUND);
		Colormast colorbean = new Colormast();
		if (!UString.esNuloVacio(bean.getIdColor())) {
			colorbean = consultaColor.obtenerPorId(bean.getIdColor());
			bean.setNombrecolor(colorbean.getDescripcioncorta());
		}

		return new ResponseEntity<ReglaNegocio>(bean, HttpStatus.OK);
	}

	@Transactional
	@GetMapping(value = "/generarsecuencia", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Integer> generarsecuencia() throws Exception {
		logger.debug("ReglaNegocioRest.generarsecuencia");
		Integer id = consulta.generarSecuencia();
		if (id == null)
			return new ResponseEntity<Integer>(HttpStatus.NOT_FOUND);
		return new ResponseEntity<Integer>(id, HttpStatus.OK);
	}

	@Transactional
	@PostMapping(value = "/registrar", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<ReglaNegocio> registrar(@RequestBody ReglaNegocio bean) throws Exception {
		logger.debug("ReglaNegocioRest.registrar");
		bean = servicio.insertarReglaNegocio(bean);
		return new ResponseEntity<ReglaNegocio>(bean, HttpStatus.CREATED);
	}

	@Transactional
	@PutMapping(value = "/actualizar", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<ReglaNegocio> actualizar(@RequestBody ReglaNegocio bean) throws Exception {
		logger.debug("ReglaNegocioRest.actualizar");
		bean = servicio.actualizarReglaNegocio(bean);
		return new ResponseEntity<ReglaNegocio>(bean, HttpStatus.OK);
	}

	@Transactional
	@PutMapping(value = "/anular", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<ReglaNegocio> anular(@RequestBody ReglaNegocio bean) throws Exception {
		logger.debug("ReglaNegocioRest.anular");
		if (bean == null)
			return new ResponseEntity<ReglaNegocio>(HttpStatus.NOT_FOUND);
		bean = servicio.coreAnular(this.getUsuarioActual(), bean);
		return new ResponseEntity<ReglaNegocio>(bean, HttpStatus.OK);
	}

	@Transactional
	@DeleteMapping(value = "/eliminar", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Void> eliminar(@RequestBody ReglaNegocio bean) throws Exception {
		logger.debug("ReglaNegocioRest.eliminar");
		if (bean == null) {
			return new ResponseEntity<Void>(HttpStatus.NOT_FOUND);
		} else {
			servicio.coreEliminar(this.getUsuarioActual(), bean);
			return new ResponseEntity<Void>(HttpStatus.OK);
		}
	}

	/* eliminar Adicional */
	@Transactional
	@PostMapping(value = "/eliminarAdicional", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<ReglaNegocioAdicionalDto> eliminarAdicional(@RequestBody ReglaNegocioAdicionalDto bean)
			throws Exception {
		logger.debug("ReglaNegocioRest.inactivarAdicional");
		// consulta.inactivar(this.getUsuarioActual(), lst);
		ReglaNegocioAdicional b = new ReglaNegocioAdicional();
		b.getPk().setIdAdicional(bean.getIdAdicional().intValue());
		b.getPk().setIdReglaNegocio(bean.getIdReglaNegocio().intValue());
		consultaAdicional.eliminar(b);
		return new ResponseEntity<ReglaNegocioAdicionalDto>(bean, HttpStatus.OK);
	}

	@Transactional
	@PutMapping(value = "/anularAdicional", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<ReglaNegocioAdicionalDto> anularAdicional(@RequestBody ReglaNegocioAdicionalDto dto)
			throws Exception {
		logger.debug("ReglaNegocioDetalleRest.anularAdicional");
		ReglaNegocioAdicional x = new ReglaNegocioAdicional();
		x.getPk().setIdAdicional(dto.getIdAdicional().intValue());
		x.getPk().setIdReglaNegocio(dto.getIdReglaNegocio().intValue());

		x = consultaAdicional.obtenerPorId(x.getPk());
		if (x == null)
			return new ResponseEntity<ReglaNegocioAdicionalDto>(HttpStatus.NOT_FOUND);
		x = consultaAdicional.coreActualizar(this.getUsuarioActual(), x, dto.getEstado());
		return new ResponseEntity<ReglaNegocioAdicionalDto>(dto, HttpStatus.OK);
	}

	/* eliminar Detalle */
	@Transactional
	@PostMapping(value = "/eliminarDetalle", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<ReglaNegocioDetalleDto> eliminarDetalle(@RequestBody ReglaNegocioDetalleDto bean)
			throws Exception {
		logger.debug("ReglaNegocioRest.inactivarAdicional");
		// consulta.inactivar(this.getUsuarioActual(), lst);
		ReglaNegocioDetalle b = new ReglaNegocioDetalle();
		b.getPk().setIdDetalle(bean.getIdDetalle().intValue());
		b.getPk().setIdReglaNegocio(bean.getIdReglaNegocio().intValue());
		consultaDetalle.eliminar(b);
		return new ResponseEntity<ReglaNegocioDetalleDto>(bean, HttpStatus.OK);
	}

	@Transactional
	@PutMapping(value = "/anularDetalle", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<ReglaNegocioDetalleDto> anularDetalle(@RequestBody ReglaNegocioDetalleDto dto)
			throws Exception {
		logger.debug("ReglaNegocioDetalleRest.anularDetalle");
		ReglaNegocioDetalle x = new ReglaNegocioDetalle();
		x.getPk().setIdDetalle(dto.getIdDetalle().intValue());
		x.getPk().setIdReglaNegocio(dto.getIdReglaNegocio().intValue());

		x = consultaDetalle.obtenerPorId(x.getPk());
		if (x == null)
			return new ResponseEntity<ReglaNegocioDetalleDto>(HttpStatus.NOT_FOUND);
		x = consultaDetalle.coreActualizar(this.getUsuarioActual(), x, dto.getEstado());
		return new ResponseEntity<ReglaNegocioDetalleDto>(dto, HttpStatus.OK);
	}

	/* eliminar Plan Accion */
	@Transactional
	@PostMapping(value = "/eliminarPlanAccion", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<ReglaNegocioPlanDto> eliminarPlanAccion(@RequestBody ReglaNegocioPlanDto bean)
			throws Exception {
		logger.debug("ReglaNegocioRest.inactivarAdicional");
		// consulta.inactivar(this.getUsuarioActual(), lst);
		ReglaNegocioPlan b = new ReglaNegocioPlan();
		b.getPk().setIdPlan(bean.getIdPlan().intValue());
		b.getPk().setIdReglaNegocio(bean.getIdReglaNegocio().intValue());
		consultaPlan.eliminar(b);
		return new ResponseEntity<ReglaNegocioPlanDto>(bean, HttpStatus.OK);
	}

	@Transactional
	@PutMapping(value = "/anularPlanAccion", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<ReglaNegocioPlanDto> anularPlanAccion(@RequestBody ReglaNegocioPlanDto dto) throws Exception {
		logger.debug("ReglaNegocioDetalleRest.anularPlanAccion");
		ReglaNegocioPlan x = new ReglaNegocioPlan();
		x.getPk().setIdPlan(dto.getIdPlan().intValue());
		x.getPk().setIdReglaNegocio(dto.getIdReglaNegocio().intValue());

		x = consultaPlan.obtenerPorId(x.getPk());
		if (x == null)
			return new ResponseEntity<ReglaNegocioPlanDto>(HttpStatus.NOT_FOUND);
		x = consultaPlan.coreActualizar(this.getUsuarioActual(), x, dto.getEstado());
		return new ResponseEntity<ReglaNegocioPlanDto>(dto, HttpStatus.OK);
	}

	/* eliminar Responsable */
	@Transactional
	@PostMapping(value = "/eliminarResponsable", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<ReglaNegocioDestinoDto> eliminarResponsable(@RequestBody ReglaNegocioDestinoDto bean)
			throws Exception {
		logger.debug("ReglaNegocioRest.inactivarAdicional");
		// consulta.inactivar(this.getUsuarioActual(), lst);
		ReglaNegocioDestino b = new ReglaNegocioDestino();
		b.getPk().setCorreoDestino(bean.getCorreoDestino());
		b.getPk().setIdReglaNegocio(bean.getIdReglaNegocio().intValue());
		consultaDestino.eliminar(b);
		return new ResponseEntity<ReglaNegocioDestinoDto>(bean, HttpStatus.OK);
	}

	@Transactional
	@PutMapping(value = "/anularResponsable", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<ReglaNegocioDestinoDto> anularResponsable(@RequestBody ReglaNegocioDestinoDto dto)
			throws Exception {
		logger.debug("ReglaNegocioDetalleRest.anularResponsable");
		ReglaNegocioDestino x = new ReglaNegocioDestino();
		x.getPk().setCorreoDestino(dto.getCorreoDestino());
		x.getPk().setIdReglaNegocio(dto.getIdReglaNegocio().intValue());

		x = consultaDestino.obtenerPorId(x.getPk());
		if (x == null)
			return new ResponseEntity<ReglaNegocioDestinoDto>(HttpStatus.NOT_FOUND);
		x = consultaDestino.coreActualizar(this.getUsuarioActual(), x, dto.getEstado());
		return new ResponseEntity<ReglaNegocioDestinoDto>(dto, HttpStatus.OK);
	}

	// Eliminar Programacion/
	@Transactional
	@PostMapping(value = "/eliminarProgramacion", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<ReglaNegocioProgramacionDto> eliminarProgramacion(
			@RequestBody ReglaNegocioProgramacionDto bean) throws Exception {
		logger.debug("ReglaNegocioRest.eliminarProgramacion");
		// consulta.inactivar(this.getUsuarioActual(), lst);
		ReglaNegocioProgramacion b = new ReglaNegocioProgramacion();
		b.getPk().setIdProgramacion(bean.getIdProgramacion().intValue());
		b.getPk().setIdReglaNegocio(bean.getIdReglaNegocio().intValue());
		consultaProgramacion.eliminar(b);
		return new ResponseEntity<ReglaNegocioProgramacionDto>(bean, HttpStatus.OK);
	}

	@Transactional
	@PutMapping(value = "/anularProgramacion", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<ReglaNegocioProgramacionDto> anularProgramacion(@RequestBody ReglaNegocioProgramacionDto dto)
			throws Exception {
		logger.debug("ReglaNegocioDetalleRest.anularProgramacion");
		ReglaNegocioProgramacion x = new ReglaNegocioProgramacion();
		x.getPk().setIdProgramacion(dto.getIdProgramacion().intValue());
		x.getPk().setIdReglaNegocio(dto.getIdReglaNegocio().intValue());

		x = consultaProgramacion.obtenerPorId(x.getPk());
		if (x == null)
			return new ResponseEntity<ReglaNegocioProgramacionDto>(HttpStatus.NOT_FOUND);
		x = consultaProgramacion.coreActualizar(this.getUsuarioActual(), x, dto.getEstado());
		return new ResponseEntity<ReglaNegocioProgramacionDto>(dto, HttpStatus.OK);
	}

	@Transactional
	@PostMapping(value = "/listar", produces = MediaType.APPLICATION_JSON_VALUE)
	public DominioPaginacion listartodo(@RequestBody FiltroPaginacionReglaNegocio filtro) throws Exception {
		logger.debug("ReglaNegocioRest.listar");
		return consulta.listar(filtro);
	}

	@Transactional
	@PostMapping(value = "/listarProgramacion", produces = MediaType.APPLICATION_JSON_VALUE)
	public DominioPaginacion listarProgramacion(@RequestBody FiltroPaginacionReglaNegocioProgramacion filtro)
			throws Exception {
		logger.debug("ReglaNegocioRest.listarProgramacion");
		return consultaProgramacion.listarProgramacion(filtro);
	}

	@Transactional
	@PostMapping(value = "/inactivar", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<ReglaNegocio> inactivar(@RequestBody ReglaNegocio bean) throws Exception {
		logger.debug("ReglaNegocioRest.inactivar");
		ReglaNegocio x = new ReglaNegocio();
		x = consulta.obtenerPorId(bean.getPk().getIdReglaNegocio());
		x.setEstado("I");
		consulta.actualizar(x);
		return new ResponseEntity<ReglaNegocio>(bean, HttpStatus.OK);
	}

	@Transactional
	@PostMapping(value = "/actualizarInactivar", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<ReglaNegocio> actualizarInactivar(@RequestBody ReglaNegocio bean) throws Exception {
		logger.debug("ReglaNegocioRest.actualizarInactivar");
		ReglaNegocio x = new ReglaNegocio();
		x = consulta.obtenerPorId(bean.getPk().getIdReglaNegocio());
		x.setEstado(bean.getEstado());
		consulta.actualizar(x);
		return new ResponseEntity<ReglaNegocio>(bean, HttpStatus.OK);
	}

	@Transactional
	@GetMapping(value = "/comboareanegocio", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<DtoTabla>> comboareanegocio() throws Exception {
		logger.debug("ReglaNegocioRest.comboareanegocio");
		return new ResponseEntity<List<DtoTabla>>(consulta.comboareanegocio(), HttpStatus.OK);
	}

	@Transactional
	@GetMapping(value = "/combocompania", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<DtoTabla>> combocompania() throws Exception {
		logger.debug("ReglaNegocioRest.combocompania");
		return new ResponseEntity<List<DtoTabla>>(consulta.combocompania(), HttpStatus.OK);
	}

	// Servicio Temproal
	@Transactional
	@PostMapping(value = "/listarColores", produces = MediaType.APPLICATION_JSON_VALUE)
	public DominioPaginacion listarColores(@RequestBody FiltroPaginacionColor filtro) throws Exception {
		logger.debug("ReglaNegocioRest.listar");
		return consulta.listarColores(filtro);
	}

	
	@Transactional
	@PutMapping(value = "/comparar", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<DtoBdComparar> comparar(@RequestBody DtoBdComparar dto) throws Exception {
		logger.debug("AplicacionesmastRest.comparar");
		dto = consulta.comparar(dto);
		
		return new ResponseEntity<DtoBdComparar>(dto, HttpStatus.OK); 
	}
	
	@Transactional
	@PutMapping(value = "/ejecutar", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<DtoBdEjecutar> ejecutar(@RequestBody DtoBdEjecutar dto) throws Exception {
		logger.debug("AplicacionesmastRest.ejecutar");
		dto = consulta.ejecutar(dto);
		return new ResponseEntity<DtoBdEjecutar>(dto, HttpStatus.OK);
	}
	
	@Transactional
	@PostMapping(value = "/listarlogs", produces = MediaType.APPLICATION_JSON_VALUE)
	public DominioPaginacion listarlogs(@RequestBody FiltroPaginacionReglaNegocio filtro) throws Exception {
		logger.debug("ReglaNegocioRest.listarlogs");
		return consulta.listarlogs(filtro);
	}
	
//	@Transactional
//	@GetMapping(value="/obtenerHoraServidor", produces = MediaType.APPLICATION_JSON_VALUE)
//	public ResponseEntity<DtoServidorHora> obtenerHoraServidor() throws Exception {
//		DtoServidorHora dto = new DtoServidorHora();
//		dto.setHora(new Date());
//		return new ResponseEntity<DtoServidorHora>(dto,HttpStatus.OK);
//	}
	
	@GetMapping(value="/obtenerHoraServidor", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<DtoServidorHora> obtenerHoraServidor() {
	    DtoServidorHora dto = new DtoServidorHora();
	    dto.setHora(new Date()); 
	    
	    return new ResponseEntity<>(dto, HttpStatus.OK);
	}
	
	@Transactional
	@PutMapping(value = "/ejecutarstore", produces = MediaType.APPLICATION_JSON_VALUE)
	public EjecucionApiTransaccion ejecutarstore(@RequestBody EjecucionApiTransaccion res) throws Exception {
		logger.debug("ReglaNegocioRest.ejecutarstore");		
		try {
			logger.debug("ReglaNegocioRest.ejecutarstore:"+res.getTexto1());
			StringBuilder sentenciaSQL = new StringBuilder();
			sentenciaSQL.append(res.getTexto1());
			consulta.ejecutarPorSentenciaSQL(sentenciaSQL);
			res.setTransaccionEstado(DominioTransaccion.OK);
		} catch (Exception ex) {
			ErrorTransaccion d = new ErrorTransaccion(ex);
			d.setIdReglaNegocio(res.getEntero1());
			d.setEstado("ACT");			
			d.setProceso("EXCEPTION");
			this.registrarError(d);				
			res.setTransaccionEstado(DominioTransaccion.ERROR);
			res.setTransaccionComentarios(ex.getMessage());
		}		
		return res;		
	}
	
	/**
	 * 
	 * @param res
	 * @return
	 * @throws Exception
	 */
	@Transactional
	@PutMapping(value = "/loggeneraralerta", produces = MediaType.APPLICATION_JSON_VALUE)
	public EjecucionApiTransaccion loggeneraralerta(@RequestBody EjecucionApiTransaccion res) throws Exception {
		Integer idReglaNegocio = res.getEntero1();
		if (idReglaNegocio == null)
			idReglaNegocio = 0;
		logger.debug("004.1-HILO : LogGenerarAlerta(idReglaNegocio) : inicio | RN:" + idReglaNegocio.toString());
		ReglaNegocio rn = null;
		try {			
			logger.debug("004.1-HILO : LogGenerarAlerta(idReglaNegocio) : P1 de 3 | RN:" + idReglaNegocio.toString());
			consulta.LogGenerarAlerta(this.getUsuarioActualx(), idReglaNegocio);
			
			logger.debug("004.1-HILO : LogGenerarAlerta(idReglaNegocio) : P2 de 3 | RN:" + idReglaNegocio.toString());			
			
			alertaServicio.UpdateReglaNegocio("DESPUES",idReglaNegocio, "A", "N", "");
			
			logger.debug("004.1-HILO : LogGenerarAlerta(idReglaNegocio) : P3 de 3 | RN:" + idReglaNegocio.toString());
		} catch (Exception ex) {
			logger.error("004.1-HILO : LogGenerarAlerta(idReglaNegocio) : P3 de 3 | RN:" + ex.getMessage());
			
			alertaServicio.UpdateReglaNegocio("ERROR",idReglaNegocio, "E","N", ex.getMessage());
			
			ex.printStackTrace();			
			ErrorTransaccion tt = new ErrorTransaccion(ex);
			tt.setIdReglaNegocio(idReglaNegocio);
			this.registrarError(tt);
		}		
		logger.debug("004.1-HILO : LogGenerarAlerta(idReglaNegocio) : fin | RN:" + idReglaNegocio.toString());				
		return res;		
	}
	
	@Transactional
	@PutMapping(value = "/actualizarregla", produces = MediaType.APPLICATION_JSON_VALUE)
	public EjecucionApiTransaccion actualizarregla(@RequestBody EjecucionApiTransaccion res) throws Exception {
		logger.debug("AlertaTask.actualizarregla");
		logger.debug("Proceso:"+res.getTexto1());
		logger.debug("Estado :"+res.getTexto2());
		logger.debug("Flag:  "+res.getTexto3());
		logger.debug("Mensaje:  "+res.getTexto4());
		logger.debug(res.getEntero1());
		logger.debug(res.getEntero2());
		
		Integer idReglaNegocio = res.getEntero1();
		String estado = res.getTexto2();
		String flgLogGenerarAlerta = res.getTexto3();
		String mensaje = res.getTexto4();
		
		ReglaNegocio rn = null;
		if (idReglaNegocio == null)
			idReglaNegocio = 0;
		rn = consulta.obtenerPorId(idReglaNegocio);
		rn.setEstado(estado);
		rn.setFlgLogGenerarAlerta(flgLogGenerarAlerta);
		rn.setModificacionFecha(new Date());
		//rn.setEjecucionMensaje(mensaje);
		consulta.actualizar(rn);
		return res;
	}
	
	@Transactional
	@PostMapping(value = "/ejecucionmanual", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<ReglaNegocioDto> ejecucionmanual(@RequestBody ReglaNegocioDto dto) throws Exception {
		logger.debug("ReglaNegocioRest.ejecucionmanual" + UBigDecimal.obtenerValorSinNulo(dto.getIdReglaNegocio()).intValue() );
		try {
			alertaServicio.registrarEjecucionLog("ReglaNegocioRest.ejecucionmanual", "", "inicio 1",dto.getIdReglaNegocio().intValue());

			//ReglaNegocioPk pk = new ReglaNegocioPk(dto.getIdReglaNegocio().intValue());
			//ReglaNegocio reglaNegocio = consulta.obtenerPorId(pk);
			
			ReglaNegocioDto reglaNegocio = consulta.obtenerDtoPorId(dto.getIdReglaNegocio().intValue());
			
			alertaServicio.registrarEjecucionLog("ReglaNegocioRest.ejecucionmanual", reglaNegocio.getObjectBd(), "inicio 2",reglaNegocio.getIdReglaNegocio().intValue());
				
			EjecucionApiTransaccion apiEjecutar=new EjecucionApiTransaccion();
			apiEjecutar.setUrlApi(reglaNegocio.getObjectBd());
			logger.debug("ReglaNegocioRest.ejecucionmanual --> antes del llamado : " + UString.obtenerValorCadenaSinNulo(reglaNegocio.getObjectBd()));
			apiEjecutar = alertaServicio.ejecutarApi(apiEjecutar);
			logger.debug(apiEjecutar.getTransaccionEstado());
			//logger.debug(apiEjecutar.getTransaccionMensajesCadena());
			dto.setTransaccionEstado(apiEjecutar.getTransaccionEstado());
			dto.setTransaccionListaMensajes(apiEjecutar.getTransaccionListaMensajes());
			
			alertaServicio.registrarEjecucionLog("ReglaNegocioRest.ejecucionmanual", reglaNegocio.getObjectBd(), "fin",reglaNegocio.getIdReglaNegocio().intValue());
			
		} catch (Exception e) {
			dto.setTransaccionEstado(DominioTransaccion.ERROR);
			dto.setTransaccionMensajesCadena(UException.getStackTrace(e));
		}		
		return new ResponseEntity<ReglaNegocioDto>(dto, HttpStatus.OK);
	}
	
	public SeguridadUsuarioActual getUsuarioActualx() {
		SeguridadUsuarioActual usu = new SeguridadUsuarioActual();
		usu.setDireccionIp("MOTOR-TASK-TERM");
		usu.setUsuario("MOTOR-TASK-USU");		
		return usu;
	}
	
	
	@Transactional
	@PostMapping(value = "/actualizarflggeneraralerta", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<ReglaNegocioTransaccion> actualizarflggeneraralerta(@RequestBody ReglaNegocioTransaccion bean)
			throws Exception {
		logger.debug("ReglaNegocioRest.actualizarflggeneraralerta");
		ReglaNegocio x = new ReglaNegocio();
		x = consulta.obtenerPorId(bean.getIdReglaNegocio());
		if (x != null) {
			if (UString.esNuloVacio(bean.getModificacionUsuario()))
				bean.setModificacionUsuario("SIN USUARIO");
			if (UString.esNuloVacio(bean.getModificacionTerminal()))
				bean.setModificacionTerminal("SIN TERMINAL");
			x.setFlgLogGenerarAlerta(bean.getFlgLogGenerarAlerta());
			x.setModificacionFecha(new Date());
			x.setModificacionUsuario(bean.getModificacionUsuario());
			x.setModificacionTerminal(bean.getModificacionTerminal());
			consulta.actualizar(x);
			bean.setTransaccionEstado(DominioTransaccion.OK);
		} else {
			bean.setTransaccionEstado(DominioTransaccion.ERROR);
			bean.getTransaccionListaMensajes().add(new DominioMensajeUsuario(tipo_mensaje.ERROR, "Regla No enontrada"));
			logger.error("Regla No enontrada");
			logger.error(bean.getIdReglaNegocio());
		}
		return new ResponseEntity<ReglaNegocioTransaccion>(bean, HttpStatus.OK);
	}
	
}
