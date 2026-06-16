package net.royal.spring.alertas.rest;

import java.math.BigDecimal;
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
import net.royal.spring.alertas.dominio.dto.DtoTabla;
import net.royal.spring.alertas.dominio.dto.ReglaNegocioAdicionalDto;
import net.royal.spring.alertas.dominio.dto.ReglaNegocioDestinoDto;
import net.royal.spring.alertas.dominio.dto.ReglaNegocioDetalleDto;
import net.royal.spring.alertas.dominio.dto.ReglaNegocioPlanDto;
import net.royal.spring.alertas.dominio.dto.ReglaNegocioProgramacionDto;
import net.royal.spring.alertas.dominio.filtros.FiltroPaginacionColor;
import net.royal.spring.alertas.dominio.filtros.FiltroPaginacionReglaNegocio;
import net.royal.spring.alertas.dominio.filtros.FiltroPaginacionReglaNegocioAdicional;
import net.royal.spring.alertas.dominio.filtros.FiltroPaginacionReglaNegocioProgramacion;
import net.royal.spring.alertas.servicio.impl.ReglaNegocioServicioImpl;
import net.royal.spring.alertas.servicio.validar.ReglaNegocioServicioValidar;
import net.royal.spring.framework.modelo.generico.DominioMensajeUsuario;
import net.royal.spring.framework.modelo.generico.DominioPaginacion;
import net.royal.spring.framework.util.UString;
import net.royal.spring.framework.web.rest.GenericoRest;

@RestController
@RequestMapping("/spring/alertas/principal")
@CrossOrigin(origins = "*")
public class PrincipalRest extends GenericoRest {

	private static Logger logger = LogManager.getLogger(PrincipalRest.class);

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

	/*
	 * Se comenta porque no se encontro el Servicio Angular que lo usaba
	 */
	//@Transactional
	//XGetMapping(value = "/obtenerporidpost/{pk}", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<ReglaNegocio> obtenerPorIdPost(@PathVariable Integer pk) throws Exception {
		FiltroPaginacionReglaNegocioAdicional frnA = new FiltroPaginacionReglaNegocioAdicional();
		frnA.setIdReglaNegocio(new BigDecimal(pk)); 
		ReglaNegocio bean = consulta.obtenerPorId(pk);

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

}
