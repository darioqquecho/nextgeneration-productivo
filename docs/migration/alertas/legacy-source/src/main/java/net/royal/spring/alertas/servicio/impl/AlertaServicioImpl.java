package net.royal.spring.alertas.servicio.impl;

import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.math.BigDecimal;
import java.nio.CharBuffer;
import java.nio.charset.CharacterCodingException;
import java.nio.charset.Charset;
import java.nio.charset.CharsetEncoder;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.Base64;
import java.util.Date;
import java.util.Hashtable;
import java.util.List;
import java.util.Map;
import java.util.Properties;
import java.util.StringTokenizer;
import java.util.concurrent.CompletableFuture;

import jakarta.activation.DataHandler;
import jakarta.activation.DataSource;
import jakarta.activation.FileDataSource;
import jakarta.mail.Address;
import jakarta.mail.Message;
import jakarta.mail.PasswordAuthentication;
import jakarta.mail.Session;
import jakarta.mail.Transport;
import jakarta.mail.internet.InternetAddress;
import jakarta.mail.internet.MimeBodyPart;
import jakarta.mail.internet.MimeMessage;
import jakarta.mail.internet.MimeMultipart;
import jakarta.mail.internet.MimeUtility;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Root;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.exolab.castor.types.DateTime;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.google.common.base.Strings;

import net.royal.spring.alertas.boot.SpringAlertasConstanteBoot;
import net.royal.spring.alertas.dao.impl.AlertaAdicionalDaoImpl;
import net.royal.spring.alertas.dao.impl.AlertaDaoImpl;
import net.royal.spring.alertas.dao.impl.AlertaDestinoDaoImpl;
import net.royal.spring.alertas.dao.impl.AlertaDetalleDaoImpl;
import net.royal.spring.alertas.dao.impl.AlertaPlanDaoImpl;
import net.royal.spring.alertas.dao.impl.ColormastDaoImpl;
import net.royal.spring.alertas.dao.impl.ConfiguracionServicioDaoImpl;
import net.royal.spring.alertas.dao.impl.CorreoAdjuntoDaoImpl;
import net.royal.spring.alertas.dao.impl.CorreoCuerpoDaoImpl;
import net.royal.spring.alertas.dao.impl.CorreoDaoImpl;
import net.royal.spring.alertas.dao.impl.CorreoDestinoDaoImpl;
import net.royal.spring.alertas.dao.impl.FuenteAlertaDaoImpl;
import net.royal.spring.alertas.dao.impl.LogAlertaDaoImpl;
import net.royal.spring.alertas.dao.impl.ReglaNegocioDaoImpl;
import net.royal.spring.alertas.dao.impl.ReglaNegocioDetalleDaoImpl;
import net.royal.spring.alertas.dao.impl.ReglaNegocioProgramacionDaoImpl;
import net.royal.spring.alertas.dominio.Alerta;
import net.royal.spring.alertas.dominio.AlertaAdicional;
import net.royal.spring.alertas.dominio.AlertaDestino;
import net.royal.spring.alertas.dominio.AlertaDetalle;
import net.royal.spring.alertas.dominio.AlertaPk;
import net.royal.spring.alertas.dominio.AlertaPlan;
import net.royal.spring.alertas.dominio.Colormast;
import net.royal.spring.alertas.dominio.ConfiguracionServicio;
import net.royal.spring.alertas.dominio.Correo;
import net.royal.spring.alertas.dominio.CorreoAdjunto;
import net.royal.spring.alertas.dominio.CorreoCuerpo;
import net.royal.spring.alertas.dominio.CorreoCuerpoPk;
import net.royal.spring.alertas.dominio.CorreoDestino;
import net.royal.spring.alertas.dominio.CorreoPk;
import net.royal.spring.alertas.dominio.ReglaNegocio;
import net.royal.spring.alertas.dominio.ReglaNegocioDetalle;
import net.royal.spring.alertas.dominio.ReglaNegocioPk;
import net.royal.spring.alertas.dominio.ReglaNegocioProgramacion;
import net.royal.spring.alertas.dominio.ReglaNegocioProgramacionPk;
import net.royal.spring.alertas.dominio.dto.AlertaDestinodto;
import net.royal.spring.alertas.dominio.dto.AlertaDetalledto;
import net.royal.spring.alertas.dominio.dto.AlertaPlandto;
import net.royal.spring.alertas.dominio.dto.Alertadto;
import net.royal.spring.alertas.dominio.dto.ConfiguracionCorreo;
import net.royal.spring.alertas.dominio.dto.CorreoAdjuntoCore;
import net.royal.spring.alertas.dominio.dto.CorreoCore;
import net.royal.spring.alertas.dominio.dto.CorreoDestinoCore;
import net.royal.spring.alertas.dominio.dto.DtoAlertaLogPendiente;
import net.royal.spring.alertas.dominio.dto.ProgramacionRuler;
import net.royal.spring.alertas.dominio.dto.ReglaNegocioTaskDto;
import net.royal.spring.alertas.dominio.filtros.FiltroPaginacionAlerta;
import net.royal.spring.alertas.servicio.validar.AlertaServicioValidar;
import net.royal.spring.framework.core.UConstante;
import net.royal.spring.framework.core.UException;
import net.royal.spring.framework.core.UFile;
import net.royal.spring.framework.core.UValidador;
import net.royal.spring.framework.modelo.EjecucionApiTransaccion;
import net.royal.spring.framework.modelo.ErrorTransaccion;
import net.royal.spring.framework.modelo.correo.EmailConfiguracion;
import net.royal.spring.framework.modelo.correo.EmailConstante;
import net.royal.spring.framework.modelo.correo.EmailDestino;
import net.royal.spring.framework.modelo.correo.EmailTransaccion;
import net.royal.spring.framework.modelo.correo.EmailDestino.tipo_destino;
import net.royal.spring.framework.modelo.generico.DominioAdjunto;
import net.royal.spring.framework.modelo.generico.DominioMensajeUsuario;
import net.royal.spring.framework.modelo.generico.DominioPaginacion;
import net.royal.spring.framework.modelo.generico.DominioParametroPersistencia;
import net.royal.spring.framework.modelo.generico.DominioTransaccion;
import net.royal.spring.framework.modelo.generico.DominioMensajeUsuario.tipo_mensaje;
import net.royal.spring.framework.modelo.generico.dto.DtoTabla;
import net.royal.spring.framework.modelo.seguridad.SeguridadUsuarioActual;
import net.royal.spring.framework.util.UBigDecimal;
import net.royal.spring.framework.util.UBoolean;
import net.royal.spring.framework.util.UFechaHora;
import net.royal.spring.framework.util.UInteger;
import net.royal.spring.framework.util.ULista;
import net.royal.spring.framework.util.UString;
import net.royal.spring.framework.web.servicio.impl.GenericoServicioImpl;

@Service(value = "BeanServicioAlerta")
public class AlertaServicioImpl extends GenericoServicioImpl {
	public static String SPRING_NOMBRE = "BeanServicioAlerta";
	private static Logger logger = LogManager.getLogger(AlertaServicioImpl.class);

	@Autowired
	private ApplicationContext applicationContext;

	@Autowired
	private AlertaDaoImpl alertaDao;

	@Autowired
	private ReglaNegocioDaoImpl daoReglaNegocio;

	@Autowired
	private ColormastDaoImpl daocolor;

	@Autowired
	private ReglaNegocioProgramacionDaoImpl daoReglaNegocioProgramacion;

	@Autowired
	private AlertaServicioValidar validar;

	@Autowired
	private LogAlertaDaoImpl daoLogAlerta;

	@Autowired
	private CorreoDaoImpl correoDao;

	@Autowired
	private CorreoCuerpoDaoImpl correoCuerpoDao;

	@Autowired
	private CorreoDestinoDaoImpl correoDestinoDao;

	@Autowired
	private CorreoAdjuntoDaoImpl correoAdjuntoDao;

	@Autowired
	private AlertaAdicionalDaoImpl alertaAdicionalDao;

	@Autowired
	private AlertaDestinoDaoImpl alertaDestinoDao;

	@Autowired
	private AlertaDetalleDaoImpl alertaDetalleDao;

	@Autowired
	private AlertaPlanDaoImpl alertaPlanDao;

	@Autowired
	private ReglaNegocioDetalleDaoImpl reglaNegocioDetalleDao;

	@Autowired
	private ConfiguracionServicioDaoImpl configuracionServicioDao;
	
	@Autowired
	private FuenteAlertaDaoImpl fuenteAlertaDao;
	
	private ProgramacionRuler ruler;

	private ConfiguracionServicio cfg;

	@Transactional
	public Alerta coreInsertar(SeguridadUsuarioActual usuarioActual, Alerta alerta) throws UException {
		// valores por defecto - preparando objeto
		alerta = validar.prepararInsertar(usuarioActual, alerta);

		// validaciones de negocio
		List<DominioMensajeUsuario> lst = validar.coreInsertar(usuarioActual, alerta);
		if (!lst.isEmpty())
			throw new UException(lst);

		// transaccion
		return alertaDao.coreInsertar(alerta);
	}

	@Transactional
	public Alerta coreActualizar(SeguridadUsuarioActual usuarioActual, Alerta alerta) throws UException {
		// valores por defecto - preparando objeto
		alerta = validar.prepararActualizar(usuarioActual, alerta);

		// validaciones de negocio
		List<DominioMensajeUsuario> lst = validar.coreActualizar(usuarioActual, alerta);
		if (!lst.isEmpty())
			throw new UException(lst);

		// transaccion
		alerta = alertaDao.coreActualizar(alerta);
		return alerta;
	}

	@Transactional
	public Alerta coreAnular(SeguridadUsuarioActual usuarioActual, Alerta alerta) throws UException {
		// valores por defecto - preparando objeto
		alerta = validar.prepararAnular(usuarioActual, alerta);

		// validaciones de negocio
		List<DominioMensajeUsuario> lst = validar.coreAnular(usuarioActual, alerta);
		if (!lst.isEmpty())
			throw new UException(lst);

		// transaccion
		return alertaDao.coreActualizar(alerta);
	}

	public Alerta coreAnular(SeguridadUsuarioActual usuarioActual, AlertaPk pk) throws UException {
		Alerta bean = alertaDao.obtenerPorId(pk);
		return coreAnular(usuarioActual, bean);
	}

	public Alerta coreAnular(SeguridadUsuarioActual usuarioActual, Integer pidAlerta) throws UException {
		return coreAnular(usuarioActual, new AlertaPk(pidAlerta));
	}

	@Transactional
	public void coreEliminar(SeguridadUsuarioActual usuarioActual, Alerta alerta) throws UException {
		// valores por defecto - preparando objeto
		alerta = validar.prepararEliminar(usuarioActual, alerta);

		// validaciones de negocio
		List<DominioMensajeUsuario> lst = validar.coreEliminar(usuarioActual, alerta);
		if (!lst.isEmpty())
			throw new UException(lst);

		// transaccion
		alertaDao.eliminar(alerta);
	}

	public void coreEliminar(SeguridadUsuarioActual usuarioActual, AlertaPk pk) throws UException {
		Alerta alerta = alertaDao.obtenerPorId(pk);
		coreEliminar(usuarioActual, alerta);
	}

	public void coreEliminar(SeguridadUsuarioActual usuarioActual, Integer pidAlerta) throws UException {
		coreEliminar(usuarioActual, new AlertaPk(pidAlerta));
	}

	@Transactional
	public void cambiarEstadoEjecucion(String proceso, String p_estado) {
		try {
			if (proceso.equals(SpringAlertasConstanteBoot.PROCESO_EVALUAR_REGLANEGOCIO)) {
				alertaDao.cambiarEjecutandoEvaluarRegla(p_estado);
			}
			if (proceso.equals(SpringAlertasConstanteBoot.PROCESO_EXTRAER_DATA)) {
				alertaDao.cambiarEjecutandoExtraerData(p_estado);
			}
			if (proceso.equals(SpringAlertasConstanteBoot.PROCESO_CREAR_ALERTA)) {
				alertaDao.cambiarEjecutandoCrearAlerta(p_estado);
			}
			if (proceso.equals(SpringAlertasConstanteBoot.PROCESO_CREAR_CORREO)) {
				alertaDao.cambiarEjecutandoCrearCorreo(p_estado);
			}

			if (proceso.equals(SpringAlertasConstanteBoot.PROCESO_ENVIO_CORREO)) {
				alertaDao.cambiarEjecutandoEnvioCorreo(p_estado);
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private void logger_regla(Integer reglaNegocioId,String msg, String classname) {
		logger_debug(msg, classname, null,reglaNegocioId);
	}
	private void logger_regla(BigDecimal reglaNegocioId,String msg, String classname) {
		logger_debug(msg, classname, null, UBigDecimal.obtenerValorSinNulo(reglaNegocioId).intValue());
	}
	
	private void logger_debug(String msg) {
		logger_debug(msg, null);
	}

	private void logger_debug(String msg, String classname) {
		logger_debug(msg, classname, null);
	}

	private void logger_debug(String msg, String classname, Exception ex) {
		logger_core(false, msg, classname, ex,new Integer(0));
	}
	private void logger_debug(String msg, String classname, Exception ex,Integer idReglaNegocio) {
		logger_core(false, msg, classname, ex,idReglaNegocio);
	}
	
	private void logger_error(String msg, String classname, Exception ex) {
		logger_core(true, msg, classname, ex,new Integer(0));
	}
	private void logger_error(String msg, String classname, Exception ex,Integer idReglaNegocio) {
		logger_core(true, msg, classname, ex,idReglaNegocio);
	}

	private void logger_core(boolean error, String msg, String classname, Exception ex,Integer idReglaNegocio) {
		if (error) {
			if (ex != null) {
				//logger.error(msg + " | " + ex.getMessage());
				//logger.error(UException.getStackTrace(ex));
			} else {
				//logger.error(msg);
			}
		} else {
			//logger.debug(msg);
		}

		if (cfg.getAuxFlgLogBaseDatos().equals("S")) {
			ErrorTransaccion d = new ErrorTransaccion("ALERTAS", msg, classname,
					"Linea300|AlertaServicioImpl.logger_core");
			d.setIdReglaNegocio(idReglaNegocio);
			d.setDominioMensajeUsuario(msg);
			d.setAplicacionId("MA");
			d.setEstado("INF");
			if (ex != null) {
				d.setEstado("ACT");
				d.setDescripcionError(UException.getStackTrace(ex));
				d.setDominioMensajeUsuario(ex.getMessage());
			}
			daoReglaNegocio.registrarError(d);
		}
	}

	@SuppressWarnings("deprecation")
	@Transactional
	public void evaluarReglaNegocioHilo(SeguridadUsuarioActual usuarioActual,String tag) throws Exception {
		int idReglaNegocioTemporal = 0;
		try {
			logger_debug(tag+" 001 : RN = evaluarReglaNegocioHilo - inicio", "EvaluarReglaNegocioTask");

			Date fechaHoraActualServidor = null;
			Boolean flgGenerarAlerta;
			List<ReglaNegocio> lstReglaNegocio = null;
			List<ReglaNegocioProgramacion> lstReglaNegocioProgramacion = null;

			if (this.ruler == null)
				this.ruler = new ProgramacionRuler();

			fechaHoraActualServidor = daoReglaNegocio.obtenerFechaHoraServidor();
			logger.debug(tag+" 001 : RN = Hora del Servidor = " + fechaHoraActualServidor);
			logger_debug(tag+" 001 : RN = Hora del Servidor = " + fechaHoraActualServidor, "EvaluarReglaNegocioTask");
			
			/*Criteria criReglaNegocio = daoReglaNegocio.getCriteria();
			criReglaNegocio.add(Restrictions.eq("estado", "A"));
			criReglaNegocio.add(Restrictions.eq("idTipoEjecucion", "P")); // Proactivo, Reactivo
			criReglaNegocio.addOrder(Order.asc("pk.idReglaNegocio"));
			lstReglaNegocio = daoReglaNegocio.listarPorCriterios(criReglaNegocio);*/
			CriteriaBuilder criReglaNegocioCb = daoReglaNegocio.getCriteriaBuilder();
			CriteriaQuery criReglaNegocioCr = criReglaNegocioCb.createQuery(ReglaNegocio.class);		
			Root criReglaNegocioRoot = criReglaNegocioCr.from(ReglaNegocio.class);
			criReglaNegocioCr.select(criReglaNegocioRoot).where(  
					criReglaNegocioCb.equal(criReglaNegocioRoot.get("estado"), "A" ),
					criReglaNegocioCb.equal(criReglaNegocioRoot.get("idTipoEjecucion"), "P" )
			);
			criReglaNegocioCr.orderBy(
					criReglaNegocioCb.asc(criReglaNegocioRoot.get("pk").get("idReglaNegocio"))
				);
			lstReglaNegocio = daoReglaNegocio.listarPorCriteriaQuery(criReglaNegocioCr,SpringAlertasConstanteBoot.BDLock);
			
			
			logger.debug(tag+" 001 : RN = lstReglaNegocio.size()=" + lstReglaNegocio.size());
			logger_debug(tag+" 001 : RN = lstReglaNegocio.size()=" + lstReglaNegocio.size(), "EvaluarReglaNegocioTask");
			
			for (ReglaNegocio reglaNegocio : lstReglaNegocio) {
				logger.debug("");
				logger.debug(tag+" 001 : RN = " + reglaNegocio.getPk().getIdReglaNegocio() + " -> " + UString.obtenerValorCadenaSinNulo(reglaNegocio.getNombre()));
				logger_regla(reglaNegocio.getPk().getIdReglaNegocio(),tag+" 001 : RN = lstReglaNegocio.size()=" + lstReglaNegocio.size(), "EvaluarReglaNegocioTask");
				idReglaNegocioTemporal = reglaNegocio.getPk().getIdReglaNegocio();
				/*Criteria criReglaNegocioEjecucion = daoReglaNegocioProgramacion.getCriteria();
				criReglaNegocioEjecucion.add(Restrictions.eq("pk.idReglaNegocio", reglaNegocio.getPk().getIdReglaNegocio()));
				criReglaNegocioEjecucion.add(Restrictions.eq("estado", "A"));
				criReglaNegocioEjecucion.add(Restrictions.eq("idTipoProgramacion", "EJE")); // EJE,ENV,COR
				lstReglaNegocioProgramacion = daoReglaNegocioProgramacion.listarPorCriterios(criReglaNegocioEjecucion);*/
				
				CriteriaBuilder criReglaNegocioEjecucionCb = daoReglaNegocioProgramacion.getCriteriaBuilder();
				CriteriaQuery criReglaNegocioEjecucionCr = criReglaNegocioEjecucionCb.createQuery(ReglaNegocioProgramacion.class);		
				Root criReglaNegocioEjecucionRoot = criReglaNegocioEjecucionCr.from(ReglaNegocioProgramacion.class);
				criReglaNegocioEjecucionCr.select(criReglaNegocioEjecucionRoot).where(  
						criReglaNegocioEjecucionCb.equal(criReglaNegocioEjecucionRoot.get("pk").get("idReglaNegocio"), reglaNegocio.getPk().getIdReglaNegocio()),
						criReglaNegocioEjecucionCb.equal(criReglaNegocioEjecucionRoot.get("estado"), "A"),
						criReglaNegocioEjecucionCb.equal(criReglaNegocioEjecucionRoot.get("idTipoProgramacion"), "EJE")
				);				
				lstReglaNegocioProgramacion = daoReglaNegocioProgramacion.listarPorCriteriaQuery(criReglaNegocioEjecucionCr,SpringAlertasConstanteBoot.BDLock);
				

				for (ReglaNegocioProgramacion reglaNegocioProgramacion : lstReglaNegocioProgramacion) {
					Boolean flgEjecutandoActualmente = UValidador
							.obtenerValorLogico(reglaNegocioProgramacion.getFlgEjecutandoActualmente());

					if (!flgEjecutandoActualmente) {
						flgGenerarAlerta = false;
						flgGenerarAlerta = this.ruler.evaluarProgramacion(reglaNegocioProgramacion,
								fechaHoraActualServidor);

						String logInterno1 = reglaNegocioProgramacion.getPk().getIdReglaNegocio().toString() + "-"
								+ reglaNegocioProgramacion.getPk().getIdProgramacion().toString();
						// logInterno = "" +
						// reglaNegocioProgramacion.getPk().getIdReglaNegocio().toString() + "-" +
						// reglaNegocioProgramacion.getPk().getIdProgramacion().toString();
						// logInterno = logInterno + " flgGenerarAlerta:" +
						// flgGenerarAlerta.toString().toUpperCase();
						logInterno1 = logInterno1 + " Actual:"
								+ UFechaHora.convertirFechaCadena(fechaHoraActualServidor, "dd hh:mm:ss");

						if (reglaNegocioProgramacion.getDiaFrecUnicaHoraejecucion() != null)
							logInterno1 = logInterno1 + " => Prog Unica:" + UFechaHora.convertirFechaCadena(
									reglaNegocioProgramacion.getDiaFrecUnicaHoraejecucion(), "dd hh:mm:ss");
						if (reglaNegocioProgramacion.getFechaUltimaEjecucion() != null)
							logInterno1 = logInterno1 + " => Ult Ejecucion:" + UFechaHora.convertirFechaCadena(
									reglaNegocioProgramacion.getFechaUltimaEjecucion(), "dd hh:mm:ss");

						logger_regla(reglaNegocio.getPk().getIdReglaNegocio(),tag+" 001 : RN = " + logInterno1 + " = flgGenerarAlerta:" + flgGenerarAlerta.toString().toUpperCase(), "EvaluarReglaNegocioTask");

						if (flgGenerarAlerta) {
							logger.debug(tag+" 001 : OK !!!!!");
							logger_regla(reglaNegocio.getPk().getIdReglaNegocio(),tag+" 001 : OK !!!!!", "EvaluarReglaNegocioTask");
							String tipoObject = reglaNegocio.getIdTipoObject();
							logger.debug(tag+" 001 tipoObject :==>" + tipoObject);
							logger_regla(reglaNegocio.getPk().getIdReglaNegocio(),tag+" 001 tipoObject :==>" + tipoObject, "EvaluarReglaNegocioTask");

							if ("BDSP".equals(UString.trimSinNulo(tipoObject))) {
								logger.debug(tag+" 001 reglaNegocio.getObjectBd() :==>" + reglaNegocio.getObjectBd());
								logger_regla(reglaNegocio.getPk().getIdReglaNegocio(),tag+" 001 reglaNegocio.getObjectBd() :==>" + reglaNegocio.getObjectBd(), "EvaluarReglaNegocioTask");
								
								this.EjecutarReglaNegocioHilo(usuarioActual,
										reglaNegocioProgramacion.getPk().getIdReglaNegocio(),
										reglaNegocioProgramacion.getPk().getIdProgramacion(),"EvaluarReglaNegocioTask",tag);

								this.registrarEjecucionLog("EvaluarReglaNegocioTask",
										reglaNegocio.getObjectBd(), null, reglaNegocio.getPk().getIdReglaNegocio());

							} else if ("APIR".equals(UString.trimSinNulo(tipoObject))) {
								logger.debug(tag+" 001 reglaNegocio.getObjectBd() :==>" + reglaNegocio.getObjectBd());
								logger_regla(reglaNegocio.getPk().getIdReglaNegocio(),tag+" 001 reglaNegocio.getObjectBd() :==>" + reglaNegocio.getObjectBd(), "EvaluarReglaNegocioTask");
								
								EjecucionApiTransaccion apiEjecutar = new EjecucionApiTransaccion();
								apiEjecutar.setUrlApi(reglaNegocio.getObjectBd());
								apiEjecutar = this.ejecutarApi(apiEjecutar);
								
								logger.debug(tag+" 001 apiEjecutar.getRegistrosExito() :==>" + apiEjecutar.getRegistrosExito());
								logger_regla(reglaNegocio.getPk().getIdReglaNegocio(),tag+" 001 apiEjecutar.getRegistrosExito() :==>" + apiEjecutar.getRegistrosExito(), "EvaluarReglaNegocioTask");
								
								logger.debug(tag+" 001 apiEjecutar.getRegistrosError() :==>" + apiEjecutar.getRegistrosError());
								logger_regla(reglaNegocio.getPk().getIdReglaNegocio(),tag+" 001 apiEjecutar.getRegistrosError() :==>" + apiEjecutar.getRegistrosError(), "EvaluarReglaNegocioTask");								
								
								this.registrarEjecucionLog("AlertaServicioImpl.evaluarReglaNegocioHilo",
										reglaNegocio.getObjectBd(), apiEjecutar.getTransaccionMensajesCadena(),
										reglaNegocio.getPk().getIdReglaNegocio(), apiEjecutar.getRegistrosExito(),
										apiEjecutar.getRegistrosError());

								
								if (("OK").equals(apiEjecutar.getTransaccionEstado())){
									fechaHoraActualServidor = daoReglaNegocio.obtenerFechaHoraServidor();
									reglaNegocioProgramacion.setModificacionTerminal(usuarioActual.getDireccionIp());
									reglaNegocioProgramacion.setModificacionUsuario(usuarioActual.getUsuario());
									reglaNegocioProgramacion.setModificacionFecha(fechaHoraActualServidor);
									reglaNegocioProgramacion.setFechaUltimaEjecucion(fechaHoraActualServidor);
									reglaNegocioProgramacion.setFlgEjecutandoActualmente("N");
									reglaNegocioProgramacion.setFechaEjecutandoActualmente(null);
									daoReglaNegocioProgramacion.actualizar(reglaNegocioProgramacion);

									reglaNegocio.setModificacionTerminal(usuarioActual.getDireccionIp());
									reglaNegocio.setModificacionUsuario(usuarioActual.getUsuario());
									reglaNegocio.setModificacionFecha(fechaHoraActualServidor);
									reglaNegocio.setFechaUltimaEjecucion(fechaHoraActualServidor);
									//reglaNegocio.setEjecucionCantidad(apiEjecutar.getRegistrosExito());
									//reglaNegocio.setEjecucionMensaje("Exito (" + UInteger.obtenerValorEnteroSinNulo(apiEjecutar.getRegistrosExito()).toString() +  ")  -  Error (" + UInteger.obtenerValorEnteroSinNulo(apiEjecutar.getRegistrosError()).toString() + ")");
									daoReglaNegocio.actualizar(reglaNegocio);
								}else {
									fechaHoraActualServidor = daoReglaNegocio.obtenerFechaHoraServidor();
									reglaNegocioProgramacion.setModificacionTerminal(usuarioActual.getDireccionIp());
									reglaNegocioProgramacion.setModificacionUsuario(usuarioActual.getUsuario());
									reglaNegocioProgramacion.setModificacionFecha(fechaHoraActualServidor);
									reglaNegocioProgramacion.setFechaUltimaEjecucion(fechaHoraActualServidor);
									reglaNegocioProgramacion.setFlgEjecutandoActualmente("N");
									reglaNegocioProgramacion.setFechaEjecutandoActualmente(null);
									daoReglaNegocioProgramacion.actualizar(reglaNegocioProgramacion);

									reglaNegocio.setModificacionTerminal(usuarioActual.getDireccionIp());
									reglaNegocio.setModificacionUsuario(usuarioActual.getUsuario());
									reglaNegocio.setModificacionFecha(fechaHoraActualServidor);
									reglaNegocio.setFechaUltimaEjecucion(fechaHoraActualServidor);
									//reglaNegocio.setEstado("E");
//									reglaNegocio.setEjecucionCantidad(-1);
//									reglaNegocio.setEjecucionMensaje(UString.extraer(apiEjecutar.getTransaccionComentarios(),0,3997));
									daoReglaNegocio.actualizar(reglaNegocio);
									
									if (apiEjecutar.getError()!=null) {
										apiEjecutar.getError().setIdReglaNegocio(reglaNegocio.getPk().getIdReglaNegocio());
										this.registrarError(apiEjecutar.getError());	
									}									
									
								}
							}
						}
					} else {
						String s1 = " 001 : EvaluarReglaNegocio() : SE ENCUENTRA BLOQUEADO = RN:"
								+ reglaNegocio.getPk().getIdReglaNegocio() + " PRG:"
								+ reglaNegocioProgramacion.getPk().getIdProgramacion();
						logger.debug(tag+s1);
						logger_regla(reglaNegocio.getPk().getIdReglaNegocio(),tag+s1, "EvaluarReglaNegocioTask");
					}
				}
			}
			
			
		} catch (Exception ex) {
			ex.printStackTrace();
			ErrorTransaccion d = new ErrorTransaccion(ex);
			d.setAplicacionId("MA");
			d.setIdReglaNegocio(idReglaNegocioTemporal);
			d.setEstado("ACT");			
			d.setProceso("EXCEPTION");
			this.registrarError(d);
			logger_error(tag+" 001 : evaluarReglaNegocioHilo() : ERROR", "EvaluarReglaNegocioTask", ex);
		} finally {
			configuracionServicioDao.cambiarEjecutandoEvaluarRegla("N");
		}
		logger_debug(tag+" 001 : RN = evaluarReglaNegocioHilo - fin", "EvaluarReglaNegocioTask");
	}

	@Transactional
	public void extraerDataHilo(SeguridadUsuarioActual usuarioActual,String tag) throws Exception {
		try {
			logger.debug("Extraer Data");
			this.ExtraerData(usuarioActual,tag);
		} catch (Exception ex) {
			ex.printStackTrace();
			ErrorTransaccion d = new ErrorTransaccion(ex);
			d.setAplicacionId("MA");
			d.setEstado("ACT");			
			d.setProceso("EXCEPTION");
			this.registrarError(d);			
			logger.error("ExtraerDataHilo : error al ejecutar el hilo");
			logger.error(ex.getMessage());
			logger_error("ExtraerDataHilo : error al ejecutar el hilo", "extraerDataHilo", ex);
		}
	}

	//YA
	@Transactional
	public void crearAlertaHilo(SeguridadUsuarioActual usuarioActual,String tag) throws Exception {
		logger.debug("");
		logger.debug(tag+" 004 : CrearAlertas() : inicio");
		logger_debug(tag+" 004 : CrearAlertas() : inicio","crearAlertaTask");
		Integer idReglaNegocio = null;
		try {
			Date fechaHoraActualServidor = null;

			// LOGGER.debug("public void CrearAlertas() - 1");

			// obtener la fecha real del servidor
			fechaHoraActualServidor = daoReglaNegocio.obtenerFechaHoraServidor();

			// LOGGER.debug("instanciar programacion ruler");
			if (this.ruler == null)
				this.ruler = new ProgramacionRuler();

			// listar las reglas pendientes de generacion
			// y si tienen alguna programacion de envio de correo
			List<DtoAlertaLogPendiente> lstReglaPendiente;

			// LOGGER.debug("listar reglas pendientes");
			lstReglaPendiente = daoLogAlerta.listarReglasPendientes();
			logger.debug(tag+" 004 : CrearAlertas() : " + lstReglaPendiente.size() + " registros");
			logger_debug(tag+" 004 : CrearAlertas() : " + lstReglaPendiente.size() + " registros","crearAlertaTask");
			for (DtoAlertaLogPendiente dtoAlertaLogPendiente : lstReglaPendiente) {
				logger.debug(tag+" 004 : REGLA NEGOCIO:" + dtoAlertaLogPendiente.getIdReglaNegocio());
				logger.debug(tag+" 004 : REGLA NEGOCIO FlgLogGenerarAlerta :" + dtoAlertaLogPendiente.getFlgLogGenerarAlerta());
				logger.debug(tag+" 004 : REGLA NEGOCIO FlgGenerarAlerta    :" + dtoAlertaLogPendiente.getFlgGenerarAlerta());
				
				logger_regla(dtoAlertaLogPendiente.getIdReglaNegocio(),tag+" 004 : REGLA NEGOCIO:" + dtoAlertaLogPendiente.getIdReglaNegocio(),"crearAlertaTask");
				logger_regla(dtoAlertaLogPendiente.getIdReglaNegocio(),tag+" 004 : REGLA NEGOCIO FlgLogGenerarAlerta :" + dtoAlertaLogPendiente.getFlgLogGenerarAlerta(),"crearAlertaTask");
				logger_regla(dtoAlertaLogPendiente.getIdReglaNegocio(),tag+" 004 : REGLA NEGOCIO FlgGenerarAlerta    :" + dtoAlertaLogPendiente.getFlgGenerarAlerta(),"crearAlertaTask");
				
				
				idReglaNegocio = dtoAlertaLogPendiente.getIdReglaNegocio().intValue();
				Boolean flgGenerarAlerta = true;
				if (dtoAlertaLogPendiente.getCantidadProgramacionCorreo().intValue() == 1) {
					logger.debug(tag+" 004 : entro a esto medio");
					logger_regla(dtoAlertaLogPendiente.getIdReglaNegocio(),tag+" 004 : entro a esto medio","crearAlertaTask");
					ReglaNegocioProgramacion programacion;
					Integer idReglaNegocioProgramacion = 0;
					flgGenerarAlerta = false;

					programacion = daoReglaNegocioProgramacion.obtenerPorId(new ReglaNegocioProgramacionPk(
							dtoAlertaLogPendiente.getIdReglaNegocio().intValue(), idReglaNegocioProgramacion));
					flgGenerarAlerta = false;
					flgGenerarAlerta = this.ruler.evaluarProgramacion(programacion, fechaHoraActualServidor);
				}
				dtoAlertaLogPendiente.setFlgGenerarAlerta(flgGenerarAlerta);
				logger.debug(tag+" 004 : flgGenerarAlerta=" + flgGenerarAlerta);
				logger_regla(dtoAlertaLogPendiente.getIdReglaNegocio(),tag+" 004 : flgGenerarAlerta=" + flgGenerarAlerta,"crearAlertaTask");
				if (flgGenerarAlerta) {
					// evaluar la programacion de envio
					// genear un procedimiento almacenado
					// pasar de ALERTA_LOG ===> ALERTA

					Boolean flgLogGenerarAlerta = UValidador
							.validarFlag(dtoAlertaLogPendiente.getFlgLogGenerarAlerta());
					logger.debug(tag+" 004 : flgLogGenerarAlerta=" + flgLogGenerarAlerta);
					logger_regla(dtoAlertaLogPendiente.getIdReglaNegocio(),tag+" 004 : flgLogGenerarAlerta=" + flgLogGenerarAlerta,"crearAlertaTask");
					/**
					 * dario qquecho 2021-08-09 1528 con acosta se quito la adminiracion porque no
					 * pasaba a crear las alertas algo muy raro
					 * dario qquecho, esto debe estar en N, caso contrario quiere decir que se esta ejecutando aun la extraccion de informacion
					 * deberia ser un proceso temporal
					 */
					// if (!flgLogGenerarAlerta) {
					if (!flgLogGenerarAlerta) {
						
						this.UpdateReglaNegocio("ANTES",idReglaNegocio, "A", "S", "");
																		
						//	EXEC SGALERTASSYS.SP_LOG_GENERAR_ALERTA :p_id_regla_negocio,:p_usuario,:p_terminal
						this.LogGenerarAlerta(tag,usuarioActual, dtoAlertaLogPendiente.getIdReglaNegocio().intValue());
						
						logger.debug(tag+" 004 : Alerta Creada ok!!!!");
						logger_regla(dtoAlertaLogPendiente.getIdReglaNegocio(),tag+" 004 : Alerta Creada ok!!!!","crearAlertaTask");
					} else {
						logger.debug(tag+" 004 : Alerta Creada NOT ok!!!!");
						logger_regla(dtoAlertaLogPendiente.getIdReglaNegocio(),tag+" 004 : Alerta Creada NOT ok!!!!","crearAlertaTask");
					}
				}
			}

			
			
		} catch (Exception ex) {
			ex.printStackTrace();
			ErrorTransaccion d = new ErrorTransaccion(ex);
			d.setAplicacionId("MA");
			d.setIdReglaNegocio(idReglaNegocio);
			d.setEstado("ACT");			
			d.setProceso("EXCEPTION");
			this.registrarError(d);			
			logger_error(tag+" 004 : CrearAlertas() : ERROR", "crearAlertaTask", ex);			
		} finally {
			configuracionServicioDao.cambiarEjecutandoCrearAlerta("N");
		}
		logger.debug(tag+" 004 : CrearAlertas() : fin");
		logger_debug(tag+" 004 : CrearAlertas() : fin","crearAlertaTask");

	}

	/*
	 * public void llamarhiloerror(SeguridadUsuarioActual usuarioActual, Error
	 * error) { RegistroErroresHilo hilo = new RegistroErroresHilo();
	 * applicationContext.getAutowireCapableBeanFactory().autowireBean(hilo);
	 * hilo.valordelparametroBean(error); hilo.start(); }
	 */

	//YA
	@Transactional
	public void crearCorreoHilo(SeguridadUsuarioActual usuarioActual,String tag) throws Exception {
		Integer idReglaNegocio = null;
		Integer contadorIdCorreo;
		logger.debug("");
		logger.debug(tag+" 005 : CrearAlertaCorreo() : inicio");
		logger_debug(tag+" 005 : CrearAlertaCorreo() : inicio","CrearCorreoTask");
		try {
//			Alerta alertaLocal = null;
			usuarioActual = daoReglaNegocio.obtenerUsuarioActual();

			List<DtoTabla> lstAlerta = null;
//			Date fechaHoraActualServidor = null;

			// obtener la fecha real del servidor
//			fechaHoraActualServidor = daoReglaNegocio.obtenerFechaHoraServidor();

			/*Criteria criAlerta = alertaDao.getCriteria();
			criAlerta.add(Restrictions.eq("estado", "A"));
			criAlerta.add(Restrictions.eq("flgCorreoGenerado", "N"));
			criAlerta.setMaxResults(1000);
			lstAlerta = alertaDao.listarPorCriterios(criAlerta);*/
			
//			CriteriaBuilder criAlertaCb = alertaDao.getCriteriaBuilder();
//			CriteriaQuery criAlertaCr = criAlertaCb.createQuery(Alerta.class);		
//			Root criAlertaRoot = criAlertaCr.from(Alerta.class);
//			criAlertaCr.select(criAlertaRoot).where(  
//					criAlertaCb.equal(criAlertaRoot.get("estado"), "A"),
//					criAlertaCb.equal(criAlertaRoot.get("flgCorreoGenerado"), "N")
//			);
//			lstAlerta = alertaDao.listarPorCriteriaQuery(criAlertaCr, 20);
			
			lstAlerta = correoDao.listarAlertasPendientesGenerarCorreo();

			contadorIdCorreo = correoDao.contar("correo.generarsecuencia"); // DA ERROR DE DUPLICADOS
			// contadorIdCorreo = correoDao.contar();

			// generar el correo y el xml
			for (DtoTabla dtoAlerta : lstAlerta) {
				
				Integer idAlerta = dtoAlerta.getId();
				idReglaNegocio = dtoAlerta.getId2();
				
				logger.debug("005 : CrearAlertaCorreo() : Procesando alerta -->> " + idAlerta);
				try {
					// logger.debug("");
					// logger.debug("005 : idAlerta:" + alerta.getPk().getIdAlerta() + " INICIO
					// ("+contadorIdCorreo.toString()+")");
					//idReglaNegocio = alerta.getIdReglaNegocio();
					Correo correo = null;
					ReglaNegocio rn = this.getReglaNegocio(idReglaNegocio);
					Alerta alerta = traerAlertaCompleta(idAlerta, false);
					correo = correoDao.registrar(alerta.getIdReglaNegocio(), alerta.getAsunto(),
							alerta.getResumenLogAlerta(), alerta.getPk().getIdAlerta(), contadorIdCorreo,
							usuarioActual);

					correoCuerpoDao.eliminar(new CorreoCuerpo(new CorreoCuerpoPk(correo.getPk().getIdCorreo())));
					correoDestinoDao.eliminarPorIdCorreoSql(contadorIdCorreo);

					correoCuerpoDao.registrar(correo, crearCorreoHtml(alerta, rn));
					for (AlertaDestino alertaDestino : alerta.getListaDestino()) {
						// logger.debug("
						// alertaDestino.getCorreoDestino():"+alertaDestino.getCorreoDestino());
						correoDestinoDao.registrar(correo, alertaDestino);
					}

					alerta.setModificacionFecha(new Date());
					alerta.setModificacionTerminal(usuarioActual.getUsuario());
					alerta.setModificacionUsuario(usuarioActual.getDireccionIp());
					alerta.setFlgCorreoGenerado("S");
					alertaDao.actualizar(alerta);

					//correoDao.flush();

					// logger.debug("005 : idAlerta:" + alerta.getPk().getIdAlerta() + " FIN");
				} catch (Exception e) {
					e.printStackTrace();
					ErrorTransaccion d = new ErrorTransaccion(e);
					d.setAplicacionId("MA");
					d.setIdReglaNegocio(idReglaNegocio);
					d.setEstado("ACT");			
					d.setProceso("EXCEPTION");
					this.registrarError(d);
					logger.debug(tag + " 005 : idAlerta:" + idAlerta + " (ERROR) " + e.getMessage());
					logger_error(tag + " 005 : idAlerta:" + idAlerta + " (ERROR) " + e.getMessage(), "CrearCorreoTask", e,idReglaNegocio);
				}
				contadorIdCorreo++;
			}
			
			
			
		} catch (Exception ex) {
			ex.printStackTrace();
			ErrorTransaccion d = new ErrorTransaccion(ex);
			d.setAplicacionId("MA");
			d.setIdReglaNegocio(idReglaNegocio);
			d.setEstado("ACT");			
			d.setProceso("EXCEPTION");
			this.registrarError(d);
			logger_error(tag + " 005 : CrearAlertaCorreo() : ERROR", "CrearCorreoTask", ex);
		} finally {
			configuracionServicioDao.cambiarEjecutandoCrearCorreo("N");
		}
		logger.debug(tag + " 005 : CrearAlertaCorreo() : fin");
		logger_debug(tag + " 005 : CrearAlertaCorreo() : fin","CrearCorreoTask");
	}

	/**
	 * 0001
	 * 
	 * @param usuarioActual
	 * @throws Exception
	 */
	//YA
	@Transactional
	public void envioCorreoMasivoHilo(SeguridadUsuarioActual usuarioActual,String tag) throws Exception {
		logger.debug("");
		logger.debug(tag+" 006 : EnvioCorreoMasivo() : inicio");
		logger_debug(tag+" 006 : EnvioCorreoMasivo() : inicio","EnvioCorreoTask");
		Boolean flgCorreoConfigurado = false;
		Integer idReglaNegocio = null;
		if (configuracionServicioDao == null)
			configuracionServicioDao = new ConfiguracionServicioDaoImpl();
		if (daoReglaNegocio == null)
			daoReglaNegocio = new ReglaNegocioDaoImpl();
		if (correoDao == null)
			correoDao = new CorreoDaoImpl();

		// EmailConfiguracion config =
		// correoDao.obtenerConfiguracionBd(null,this.serverNombre,null);
		Map mapPerfiles = correoDao.perfilesCorreoArmarMap(null, this.serverNombre);

		try {
			// List<ReglaNegocio> listaReglaNegocio;
			List<ReglaNegocioTaskDto> listaReglaNegocio = new ArrayList<ReglaNegocioTaskDto>();
			List<DtoTabla> listaCorreo;
			Integer contadorErrores = 0;

			// listaReglaNegocio = daoReglaNegocio.listarActivos();
			listaReglaNegocio = daoReglaNegocio.listarParaTask();
			for (ReglaNegocioTaskDto rn : listaReglaNegocio) {
				logger.debug("");
				logger.debug(tag+" 006 : IDREGLANECIO() TEST: " + rn.getIdReglaNegocio());
				logger_regla(rn.getIdReglaNegocio(),tag+" 006 : IDREGLANECIO() TEST: " + rn.getIdReglaNegocio(),"EnvioCorreoTask");
				idReglaNegocio = rn.getIdReglaNegocio().intValue();
				listaCorreo = correoDao.listarCorreoPendienteEnvio(rn.getIdReglaNegocio().intValue(),
						rn.getCantidadCorreosEnviar().intValue());

				// contadorErrores =
				// errorDao.contarErroresPorReglaNegocio(rn.getIdReglaNegocio());
				contadorErrores = rn.getErroresCantidad().intValue();
				int cantidaderroresenvio = 0;
				if (rn.getCantidadErroresEnvio() != null) {
					cantidaderroresenvio = rn.getCantidadErroresEnvio().intValue();
				}
				String ss = " 006 : listaCorreo: " + listaCorreo.size() + "contadorErrores: " + contadorErrores
						+ " : cantidaderroresenvio: " + cantidaderroresenvio + " : flgCorreoConfigurado: "
						+ flgCorreoConfigurado;
				logger.debug(tag+ss);
				logger_regla(rn.getIdReglaNegocio(),tag+ss,"EnvioCorreoTask");
				if (contadorErrores <= cantidaderroresenvio) {
					for (DtoTabla dtoCorreo : listaCorreo) {
						/*
						 * if (!flgCorreoConfigurado) { this.prepararMotor(); flgCorreoConfigurado =
						 * true; }
						 */

						Integer idCorreo = dtoCorreo.getId();
						String perfilCorreoId = dtoCorreo.getCodigo();
						
						logger.debug(tag+" 006 : LOS QUE ENTRARON POR CANTIDAD DE ERRORES: " + rn.getIdReglaNegocio());
						logger_regla(rn.getIdReglaNegocio(),tag+" 006 : LOS QUE ENTRARON POR CANTIDAD DE ERRORES: " + rn.getIdReglaNegocio(),"EnvioCorreoTask");
						EmailConfiguracion config = correoDao.perfilesCorreoObtener(mapPerfiles,
								perfilCorreoId);
						this.EnvioCorreo(config, usuarioActual, idCorreo, rn);
					}
				} else {
					logger.error(tag+" 006 : EnvioCorreoMasivo() : 30 ERRORES ACTIVOS  RN:" + rn.getIdReglaNegocio().toString());
					logger_regla(rn.getIdReglaNegocio(),tag+" 006 : EnvioCorreoMasivo() : 30 ERRORES ACTIVOS  RN:" + rn.getIdReglaNegocio().toString(),"EnvioCorreoTask");
				}
			}
			
			

		} catch (Exception ex) {
			ex.printStackTrace();
			ErrorTransaccion d = new ErrorTransaccion(ex);
			d.setAplicacionId("MA");
			d.setIdReglaNegocio(idReglaNegocio);
			d.setEstado("ACT");			
			d.setProceso("EXCEPTION");
			this.registrarError(d);
			
			logger_error(tag+" 006 : EnvioCorreoMasivo() : ERROR", "EnvioCorreoTask", ex);			
		} finally {
			configuracionServicioDao.cambiarEjecutandoEnvioCorreo("N");
		}
		logger.debug(tag+" 006 : EnvioCorreoMasivo() : fin");
		logger_debug(tag+" 006 : EnvioCorreoMasivo() : fin","EnvioCorreoTask");
	}

	/**
	 * 002
	 * 
	 * @param usuarioActual
	 * @throws Exception
	 */
	//YA
	@Transactional
	public void envioCorreoMasivoHiloGenerico(SeguridadUsuarioActual usuarioActual,String tag) throws Exception {
		logger.debug("");
		logger.debug(tag + " 007 : EnvioCorreoMasivoGenrico() : inicio");
		logger_debug(tag + " 007 : EnvioCorreoMasivoGenrico() : inicio","EnvioCorreoGenericoTask");
		Boolean flgCorreoConfigurado = false;
		Integer idReglaNegocio = null;
		if (configuracionServicioDao == null)
			configuracionServicioDao = new ConfiguracionServicioDaoImpl();
		if (daoReglaNegocio == null)
			daoReglaNegocio = new ReglaNegocioDaoImpl();
		if (correoDao == null)
			correoDao = new CorreoDaoImpl();

		// EmailConfiguracion config =
		// correoDao.obtenerConfiguracionBd(null,this.serverNombre,null);
		Map mapPerfiles = correoDao.perfilesCorreoArmarMap(null, this.serverNombre);

		try {
			// List<ReglaNegocio> listaReglaNegocio;
			List<ReglaNegocioTaskDto> listaReglaNegocio = new ArrayList<ReglaNegocioTaskDto>();
			List<DtoTabla> listaCorreo;
			// Integer contadorErrores = 0;

			ReglaNegocioTaskDto e = new ReglaNegocioTaskDto();

			e.setIdReglaNegocio(new BigDecimal(0));
			e.setCantidadCorreosEnviar(new BigDecimal(50));
			e.setErroresCantidad(0);

			listaReglaNegocio.add(e);
			for (ReglaNegocioTaskDto rn : listaReglaNegocio) {
				logger.debug("");
				logger.debug(tag+" 007 : IDREGLANECIO() TEST: " + rn.getIdReglaNegocio());
				logger_regla(rn.getIdReglaNegocio(),tag+" 007 : IDREGLANECIO() TEST: " + rn.getIdReglaNegocio(),"EnvioCorreoGenericoTask");
				idReglaNegocio = rn.getIdReglaNegocio().intValue();
				listaCorreo = correoDao.listarCorreoPendienteEnvioGenerico(rn.getCantidadCorreosEnviar().intValue());
				logger.debug(tag+" 007 : IDREGLANECIO() TEST: Pendientes a enviar " + listaCorreo.size());
				logger_regla(rn.getIdReglaNegocio(),tag+" 007 : IDREGLANECIO() TEST: Pendientes a enviar " + listaCorreo.size(),"EnvioCorreoGenericoTask");
				int cantidaderroresenvio = 0;
				if (rn.getCantidadErroresEnvio() != null) {
					cantidaderroresenvio = rn.getCantidadErroresEnvio().intValue();
				}
				String ss = " 007 : listaCorreo:" + listaCorreo.size() + " | contadorErrores:" + rn.getErroresCantidad()
				+ " | cantidaderroresenvio:" + cantidaderroresenvio + " |  flgCorreoConfigurado:"
				+ flgCorreoConfigurado;
				logger.debug(tag+ss);
				logger_regla(rn.getIdReglaNegocio(),tag+ss,"EnvioCorreoGenericoTask");
				if (rn.getErroresCantidad() <= cantidaderroresenvio) {
					for (DtoTabla correo : listaCorreo) {						
						logger.debug(tag + " 007 : LOS QUE ENTRARON POR CANTIDAD DE ERRORES: " + rn.getIdReglaNegocio());
						logger_regla(rn.getIdReglaNegocio(),tag + " 007 : LOS QUE ENTRARON POR CANTIDAD DE ERRORES: " + rn.getIdReglaNegocio(),"EnvioCorreoGenericoTask");
						EmailConfiguracion config = correoDao.perfilesCorreoObtener(mapPerfiles,
								correo.getCodigo());
						this.EnvioCorreo(config, usuarioActual, correo.getId(), rn);
					}
				} else {
					logger.error(tag + " 007 : EnvioCorreoMasivo() : 30 ERRORES ACTIVOS  RN:" + rn.getIdReglaNegocio().toString());
					logger_regla(rn.getIdReglaNegocio(),tag + " 007 : EnvioCorreoMasivo() : 30 ERRORES ACTIVOS  RN:" + rn.getIdReglaNegocio().toString(),"EnvioCorreoGenericoTask");
				}
			}

			

		} catch (Exception ex) {
			ex.printStackTrace();
			ErrorTransaccion d = new ErrorTransaccion(ex);
			d.setAplicacionId("MA");
			d.setIdReglaNegocio(idReglaNegocio);
			d.setEstado("ACT");			
			d.setProceso("EXCEPTION");
			this.registrarError(d);
			
			logger_error(tag + " 007 : EnvioCorreoMasivoGenrico() : ERROR", "EnvioCorreoGenericoTask", ex);			
		} finally {
			configuracionServicioDao.cambiarEjecutandoEnvioCorreo("N");
		}
		logger.debug(tag + " 007 : EnvioCorreoMasivoGenrico() : fin");
		logger_debug(tag + " 007 : EnvioCorreoMasivoGenrico() : fin","EnvioCorreoGenericoTask");
	}

	//YA
	public EmailTransaccion EnvioCorreo(EmailConfiguracion config, SeguridadUsuarioActual usuarioActual,
			Integer idCorreo, ReglaNegocioTaskDto reglaNegocio) throws Exception {
		Correo correo = null;
		String asuntoTemporal = null;
		EmailTransaccion et = new EmailTransaccion();

		if (idCorreo == null)
			idCorreo = 0;
		logger.debug("006.1 : EnvioCorreo(idCorreo) : inicio | idCorreo=" + idCorreo.toString());
		try {
			correo = this.traerCorreoCompleta(new Correo(new CorreoPk(idCorreo)), true);
			asuntoTemporal = correo.getAsunto();

			if (config!=null) {
				if (UValidador.obtenerValorLogico(config.getFlgCorreoPrueba())) {
					asuntoTemporal = UValidador.obtenerValorCadenaSinNulo(correo.getAsunto());
					asuntoTemporal = asuntoTemporal + " | LOG:"
							+ UValidador.obtenerValorCadenaSinNulo(correo.getResumenLogAlerta());
					asuntoTemporal = asuntoTemporal + " | A:" + correo.getIdAlerta().toString();
					asuntoTemporal = asuntoTemporal + " | C:" + correo.getPk().getIdCorreo().toString();
				}
			}
			// agregado por dario
			Boolean flgCorreoEnvioPrueba = Boolean.FALSE;
			if (reglaNegocio != null)
				flgCorreoEnvioPrueba = UValidador.obtenerValorLogico(reglaNegocio.getFlgCorreoPrueba());

			et = this.getCorreoFramework(correo, asuntoTemporal, flgCorreoEnvioPrueba);
			et = this.enviarCorreoJava(config, et);

			logger.debug("006.1 : EnvioCorreo(idCorreo) : flgEnviado=" + et.getTransaccionEstado());
			if (et.getTransaccionListaMensajes().size() > 0) {
				logger.debug("006.1 : EnvioCorreo(idCorreo) : flgEnviado="
						+ et.getTransaccionListaMensajes().get(0).getTitulo());
				logger.debug("006.1 : EnvioCorreo(idCorreo) : flgEnviado="
						+ et.getTransaccionListaMensajes().get(0).getMensaje());
			}

			if (et.getTransaccionEstado().equals("OK")) {
				correo.setFechaHoraEnvio(new Date());
				correo.setModificacionFecha(new Date());
				correo.setModificacionTerminal(usuarioActual.getDireccionIp());
				correo.setModificacionUsuario(usuarioActual.getUsuario());
				correo.setEstado(SpringAlertasConstanteBoot.CORREO_ESTADO_ENVIADO);
				correoDao.actualizar(correo);

				// LEONARDO CODE: INICIO -> ACTUALIZAMOS LA FECHA HORA ENVIO EN REGLA NEGOCIO
				logger.debug("ACTUALIZADO REGLANEG ID: " + reglaNegocio.getIdReglaNegocio());

				ReglaNegocio R1 = daoReglaNegocio
						.obtenerPorId(UBigDecimal.obtenerValorSinNulo(reglaNegocio.getIdReglaNegocio()).intValue());
				if (R1 != null) {
					R1.setFechaUltimoEnvio(correo.getFechaHoraEnvio());
					daoReglaNegocio.actualizar(R1);
				}
				// FIN
			} else {
				logger.debug("006.1 : EnvioCorreo(idCorreo) : flgEnviado=" + et.getTransaccionEstado());
				try {
					// errorDao.coreInsertarCorreoApi(usuarioActual, null, correo);
					ErrorTransaccion error = new ErrorTransaccion();
					error.setDominioMensajeUsuario(et.getTransaccionListaMensajes().get(0).getTitulo());// titulo
					error.setDescripcionError(et.getTransaccionListaMensajes().get(0).getMensaje());// blob
					error.setIdCorreo(idCorreo);
					logger.debug("006.1.001 : EnvioCorreo(idCorreo) : antes de registrar el error");
					this.ErrorRegistrar(error);
					logger.debug("006.1.001 : EnvioCorreo(idCorreo) : despues de registrar el error");
				} catch (Exception e2) {
					e2.printStackTrace();
					ErrorTransaccion d = new ErrorTransaccion(e2);
					d.setAplicacionId("MA");
					d.setIdReglaNegocio(reglaNegocio.getIdReglaNegocio().intValue());
					d.setEstado("ACT");			
					d.setProceso("EXCEPTION");
					this.registrarError(d);
					
					logger.debug("006.1.001 : EnvioCorreo(idCorreo) : error al registrar el error");
					logger.debug(e2.getMessage());
					logger.debug(UException.getStackTrace(e2));
					logger_error("006.1.001 : EnvioCorreo(idCorreo) : error al registrar el error", "EnvioCorreo", e2);					
				}
				try {
					logger.debug("006.1.002 : EnvioCorreo(idCorreo) : antes de actualizar");
					correo.setModificacionFecha(new Date());
					correo.setModificacionTerminal(usuarioActual.getDireccionIp());
					correo.setModificacionUsuario(usuarioActual.getUsuario());
					correo.setEstado(SpringAlertasConstanteBoot.CORREO_ESTADO_ERRORENVIO);
					correoDao.actualizar(correo);
					logger.debug("006.1.002 : EnvioCorreo(idCorreo) : despues de actualizar");
				} catch (Exception e3) {
					e3.printStackTrace();
					ErrorTransaccion d = new ErrorTransaccion(e3);
					d.setAplicacionId("MA");
					d.setIdReglaNegocio(reglaNegocio.getIdReglaNegocio().intValue());
					d.setEstado("ACT");			
					d.setProceso("EXCEPTION");
					this.registrarError(d);
					logger.debug("006.1.002 : EnvioCorreo(idCorreo) : error al actualizar");
					logger_error("006.1.002 : EnvioCorreo(idCorreo) : error al actualizar", "EnvioCorreo", e3);
				}
			}

			
			
			return et;
		} catch (Exception ex) {
			ex.printStackTrace();
			ErrorTransaccion d = new ErrorTransaccion(ex);
			d.setAplicacionId("MA");
			d.setIdCorreo(idCorreo);
			d.setEstado("ACT");			
			d.setProceso("EXCEPTION");
			this.registrarError(d);
			
			et.setTransaccionEstado(DominioTransaccion.ERROR);
			et.getTransaccionListaMensajes().add(new DominioMensajeUsuario(ex));
			logger_error("006.1 : EnvioCorreo(idCorreo) : ERROR | idCorreo=" + idCorreo.toString(), "EnvioCorreo", ex);
			logger.error("006.1 : EnvioCorreo(idCorreo) : ERROR | idCorreo=" + idCorreo.toString());
			logger.error(ex.getMessage());
			logger.error(UException.getStackTrace(ex));
		}
		logger.debug("006.1 : EnvioCorreo(idCorreo) : fin | idCorreo=" + idCorreo.toString());
		
		
		return et;
	}

	public EmailTransaccion getCorreoFramework(Correo correo, String asuntoExterno, Boolean flgCorreoPrueba)
			throws UnsupportedEncodingException {
		EmailTransaccion email = new EmailTransaccion();

		if (correo == null)
			return email;

		email.setAsunto(asuntoExterno);

		if (correo.getCorreoCuerpo().getCuerpoCorreo() != null) {
			email.setCuerpoCorreoBytes(correo.getCorreoCuerpo().getCuerpoCorreo());
		}
		List<EmailDestino> destino = new ArrayList<EmailDestino>();
		if (correo.getListaCorreoDestino() != null) {
			for (CorreoDestino correoDestino : correo.getListaCorreoDestino()) {
				destino.add(new EmailDestino(correoDestino.getPk().getCorreoDestino()));
			}
		}
		email.setListaCorreoDestino(destino);

		// email.setRemitente(remitente);
		List<DominioAdjunto> listaCorreoAdjunto = new ArrayList<DominioAdjunto>();
		for (CorreoAdjunto correoAdjunto : correo.getListaCorreoAdjuntos()) {
			DominioAdjunto e = new DominioAdjunto();
			e.setArchivoAdjuntoBytes(correoAdjunto.getCuerpoAdjunto());
			e.setNombreArchivo(correoAdjunto.getNombreArchivo());
			e.setRutaCompletaArchivo(correoAdjunto.getRutaCompleta());
			listaCorreoAdjunto.add(e);
		}
		email.setListaCorreoAdjunto(listaCorreoAdjunto);

		return email;
	}
	private boolean esCorreoValido(String correo) {
	    try {
	        if (correo == null || correo.trim().isEmpty()) {
	            return false;
	        }
	        InternetAddress emailAddr = new InternetAddress(correo.trim());
	        emailAddr.validate();
	        return true;

	    } catch (Exception e) {
	        return false;
	    }
	}
	public EmailTransaccion enviarCorreoJava(EmailConfiguracion cfg, EmailTransaccion correo) throws Exception {
		EmailTransaccion res = new EmailTransaccion();
		if (cfg == null) {
			res.setTransaccionEstado(EmailTransaccion.ERROR);
			res.getTransaccionListaMensajes().add(new DominioMensajeUsuario(tipo_mensaje.ERROR,
					this.getMessage("mensaje.error.emailresultado.null")));
			return res;
		}

		try {
			Message message = new MimeMessage(cfg.getSessionCorreo());

			if (message == null) {
				res.setTransaccionEstado(EmailTransaccion.ERROR);
				res.getTransaccionListaMensajes().add(new DominioMensajeUsuario(tipo_mensaje.ERROR,
						this.getMessage("mensaje.error.sessionmensaje.null")));
				return res;
			}

			if (!UString.esNuloVacio(correo.getCuerpoCorreoBase64())) {
				String sinSalto = correo.getCuerpoCorreoBase64();
				byte[] addcuerpo = Base64.getDecoder().decode(sinSalto); // ALEJANDRO, no cambiar
				correo.setCuerpoCorreoBytes(addcuerpo);
			} else {
			}

			if (UString.estaVacio(correo.getRemitenteCorreo())) {
				if (UString.estaVacio(cfg.getEmailRemitente())) {
					if (UString.esNuloVacio(correo.getRemitenteNombre()))
						message.setFrom(new InternetAddress(cfg.getEmailCuenta(), cfg.getEmailCuenta()));
					else
						message.setFrom(new InternetAddress(cfg.getEmailCuenta(), correo.getRemitenteNombre()));
				} else {
					if (UString.esNuloVacio(correo.getRemitenteNombre()))
						message.setFrom(new InternetAddress(cfg.getEmailRemitente(), cfg.getEmailRemitente()));
					else
						message.setFrom(new InternetAddress(cfg.getEmailRemitente(), correo.getRemitenteNombre()));
				}
			} else {
				if (UString.esNuloVacio(correo.getRemitenteNombre())) {
					message.setFrom(new InternetAddress(correo.getRemitenteCorreo(), correo.getRemitenteCorreo()));
				} else {
					message.setFrom(new InternetAddress(correo.getRemitenteCorreo(), correo.getRemitenteCorreo()));
				}

			}

			message.setSentDate(new Date());
			//message.setHeader("X-SES-CONFIGURATION-SET", "ConfigSet");

			if (UBoolean.validarFlag(cfg.getFlgCorreoPrueba())) {
				message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(cfg.getCorreoPrueba()));
			} else {
				int contadorInternoTO = 0;
				int contadorInternoCC = 0;
				int contadorInternoBCC = 0;

				correo.prepararListaCorreos();

				List<String> correosInvalidos = new ArrayList<>();
				
				for (EmailDestino correoDestino : correo.getListaCorreoDestino()) {
					if (!esCorreoValido(correoDestino.getCorreoDestino())) {
						 correosInvalidos.add(correoDestino.getCorreoDestino());
						 continue;
					 }
					if (correoDestino.getDestino() == null)
						correoDestino.setDestino(tipo_destino.TO);

					if (correoDestino.getDestino().equals(tipo_destino.TO))
						contadorInternoTO++;
					if (correoDestino.getDestino().equals(tipo_destino.CC))
						contadorInternoCC++;
					if (correoDestino.getDestino().equals(tipo_destino.BCC))
						contadorInternoBCC++;
				}
				InternetAddress[] addressTo = new InternetAddress[contadorInternoTO];
				InternetAddress[] addressCC = new InternetAddress[contadorInternoCC];
				InternetAddress[] addressBCC = new InternetAddress[contadorInternoBCC];

				contadorInternoTO = 0;
				contadorInternoCC = 0;
				contadorInternoBCC = 0;
				for (EmailDestino correoDestino : correo.getListaCorreoDestino()) {
					
					if (!esCorreoValido(correoDestino.getCorreoDestino())) {
						 continue;
					 }
					
					if (correoDestino.getDestino() == null)
						correoDestino.setDestino(tipo_destino.TO);

					if (correoDestino.getDestino().equals(tipo_destino.TO)) {
						addressTo[contadorInternoTO] = new InternetAddress(correoDestino.getCorreoDestino().trim());
						contadorInternoTO++;
					}
					if (correoDestino.getDestino().equals(tipo_destino.CC)) {
						addressCC[contadorInternoCC] = new InternetAddress(correoDestino.getCorreoDestino().trim());
						contadorInternoCC++;
					}
					if (correoDestino.getDestino().equals(tipo_destino.BCC)) {
						addressBCC[contadorInternoBCC] = new InternetAddress(correoDestino.getCorreoDestino().trim());
						contadorInternoBCC++;
					}
				}
				message.setRecipients(Message.RecipientType.TO, addressTo);
				message.setRecipients(Message.RecipientType.CC, addressCC);
				message.setRecipients(Message.RecipientType.BCC, addressBCC);
			}

			Address[] destinatarios = message.getAllRecipients();
			if (destinatarios == null || destinatarios.length == 0) {
			    res.setTransaccionEstado(EmailTransaccion.ERROR);
			    res.getTransaccionListaMensajes().add(
			        new DominioMensajeUsuario(
			            tipo_mensaje.ERROR,
			            "No existen destinatarios válidos para enviar el correo."
			        )
			    );
			    return res;
			}
			
			if (correo.getCuerpoCorreoBytes() != null) {
				// PARA ACENTOS/enes ANSI UTF-8
				// String cuerpoCorreo = new String(correo.getCuerpoCorreoBytes());
				// String cuerpoCorreo = new String(correo.getCuerpoCorreoBytes(),"UTF-8");
				// String cuerpoCorreo = new
				// String(correo.getCuerpoCorreoBytes(),StandardCharsets.US_ASCII);
				String cuerpoCorreo = new String(correo.getCuerpoCorreoBytes(), "ISO-8859-1");

				// PARA ACENTOS/enes ANSI UTF-8
				// logger.debug("========>");
				// logger.debug(cuerpoCorreo);
				// message.setContent(cuerpoCorreo, "text/html");
				message.setContent(cuerpoCorreo, "text/html; charset=utf-8");
			}

			String asuntoTemp = correo.getAsunto();
			if (cfg.getTraceFlg().equals("S")) {
				String as = "|" + cfg.getTraceServidor() + "|"
						+ UString.obtenerValorCadenaSinNulo(correo.getTraceReferencia());
				if (!UString.estaVacio(correo.getAsunto())) {
					asuntoTemp = asuntoTemp + as;
				} else {
					asuntoTemp = as;
				}
			}
			message.setSubject(MimeUtility.encodeText(asuntoTemp, "utf-8", "B"));

			String nombreCarpetaSession = UFile.archivoUnico();
			if (!ULista.esListaVacia(correo.getListaCorreoAdjunto())) {
				message = adjuntarArchivosJava(cfg, correo, message, nombreCarpetaSession);
			}

			Transport transport = cfg.getSessionCorreo().getTransport("smtp");
			transport.connect(cfg.getEmailServidor(), cfg.getEmailCuenta(), cfg.getEmailClave());
			transport.sendMessage(message, message.getAllRecipients());
			transport.close();

			res.setTransaccionEstado(EmailTransaccion.OK);
			String strMsg = this.getMessage("correo.envioNoExito");
			res.getTransaccionListaMensajes()
					.add(new DominioMensajeUsuario(tipo_mensaje.ERROR, this.getMessage("correo.envioExito")));
			try {
				UFile.eliminarCarpeta(new File(cfg.getRutaRaizAdjuntos() + nombreCarpetaSession));
			} catch (Exception e) {
				e.printStackTrace();
				logger_error("Eliminando carpeta", "AlertaServicioImpl.enviarCorreoJava", e);
			}
			return res;
		} catch (Exception ex) {
			ex.printStackTrace();
			ErrorTransaccion d = new ErrorTransaccion(ex);
			d.setAplicacionId("MA");
			d.setEstado("ACT");			
			d.setProceso("EXCEPTION");
			this.registrarError(d);
			
			res.setTransaccionEstado(EmailTransaccion.ERROR);
			res.getTransaccionListaMensajes().add(new DominioMensajeUsuario(tipo_mensaje.ERROR, ex.getMessage(), UException.getStackTrace(ex)));
			logger_error("Eliminando carpeta", "AlertaServicioImpl.enviarCorreoJava", ex);			
		}
		return res;
	}

	private static Message adjuntarArchivosJava(EmailConfiguracion config, EmailTransaccion correo, Message message,
			String nombreCarpetaSession) throws Exception {

		Integer contador = 0;
		MimeMultipart multipart = new MimeMultipart();
		Boolean flgCarpetaCreada = false;

		String rTemporal = UString.obtenerValorCadenaSinNulo(config.getRutaRaizAdjuntos());
		if (UString.esNuloVacio(rTemporal))
			rTemporal = UString.obtenerValorCadenaSinNulo(config.getRutaRaizTemporal());

		logger.debug("config.getRutaRaizAdjuntos():" + config.getRutaRaizAdjuntos());
		logger.debug("config.getRutaRaizTemporal():" + config.getRutaRaizTemporal());
		logger.debug("rTemporal:" + rTemporal);

		if (correo.getListaCorreoAdjunto() == null)
			correo.setListaCorreoAdjunto(new ArrayList<DominioAdjunto>());

		MimeBodyPart messageBodyPart = new MimeBodyPart();
		messageBodyPart.setContent(message.getContent(), "text/html");
		multipart.addBodyPart(messageBodyPart);

		for (DominioAdjunto adjunto : correo.getListaCorreoAdjunto()) {
			String rutaArchivoTmp = null;
			String nombreArchivoTmp = null;

			if (!flgCarpetaCreada) {
				flgCarpetaCreada = true;
				File fadj = new File(rTemporal + File.separator + nombreCarpetaSession);
				boolean bCreado = fadj.mkdir();
				if (!bCreado) {
					logger.debug("carpeta de session no creada");
				}
				logger.debug(fadj.getAbsolutePath());
			}

			if (!UString.estaVacio(adjunto.getRutaCompletaArchivo())) {
				logger.debug(">>RutaCompletaArchivo");
				rutaArchivoTmp = adjunto.getRutaCompletaArchivo();
				if (UString.estaVacio(adjunto.getNombreArchivo()))
					nombreArchivoTmp = UFile.archivoUnico();
				else
					nombreArchivoTmp = adjunto.getNombreArchivo();
				logger.debug(rutaArchivoTmp);
			} else if (adjunto.getArchivoAdjuntoBytes() != null) {
				logger.debug(">>Archivo Adjunto byte[]");
				rutaArchivoTmp = rTemporal + File.separator + nombreCarpetaSession + File.separator;
				if (UString.estaVacio(adjunto.getNombreArchivo()))
					nombreArchivoTmp = UFile.archivoUnico();
				else
					nombreArchivoTmp = adjunto.getNombreArchivo();

				rutaArchivoTmp = rutaArchivoTmp + nombreArchivoTmp;

				FileOutputStream fos = new FileOutputStream(rutaArchivoTmp);
				fos.write(adjunto.getArchivoAdjuntoBytes());
				fos.close();
				logger.debug(rutaArchivoTmp);
			} else if (!UString.estaVacio(adjunto.getArchivoAdjuntoBase64())) {
				logger.debug(">>Archivo Adjunto Bae 64");
				byte[] addjjun = Base64.getDecoder().decode(adjunto.getArchivoAdjuntoBase64());

				rutaArchivoTmp = rTemporal + File.separator + nombreCarpetaSession + File.separator;
				if (UString.estaVacio(adjunto.getNombreArchivo()))
					nombreArchivoTmp = UFile.archivoUnico();
				else
					nombreArchivoTmp = adjunto.getNombreArchivo();

				rutaArchivoTmp = rutaArchivoTmp + nombreArchivoTmp;

				FileOutputStream fos = new FileOutputStream(rutaArchivoTmp);
				fos.write(addjjun);
				fos.close();
				logger.debug(rutaArchivoTmp);
			} else {
				logger.debug(">> NO ENVIADO NADA DE ADJUNTOS");
			}

			File f = new File(rutaArchivoTmp);

			if (f.exists()) {
				contador++;

				MimeBodyPart messageBodyPart2 = new MimeBodyPart();
				DataSource source = new FileDataSource(rutaArchivoTmp);
				messageBodyPart2.setDataHandler(new DataHandler(source));
				messageBodyPart2.setFileName(nombreArchivoTmp);
				multipart.addBodyPart(messageBodyPart2);
			} else {
				logger.error("archivo no existe :{}", rutaArchivoTmp);
			}
		}

		if (contador > 0)
			message.setContent(multipart);
		
		
		return message;
	}

	public static Boolean esCorreoPrueba(ConfiguracionCorreo configInterno, CorreoCore correoInterno) {
		Boolean flgEnvioCorreoPrueba = false;

		flgEnvioCorreoPrueba = UValidador.validarFlag(configInterno.getFlgCorreoPrueba());
		if (!flgEnvioCorreoPrueba) {
			if (correoInterno.getFlgCorreoPrueba()) {
				flgEnvioCorreoPrueba = Boolean.TRUE;
			}
		}

		return flgEnvioCorreoPrueba;
	}

	public Correo traerCorreoCompleta(Correo correoObtener, Boolean flgObtenerPrincipal) {

		Correo correo = null;

		if (flgObtenerPrincipal)
			correo = correoDao.obtenerPorId(new CorreoPk(correoObtener.getPk().getIdCorreo()));
		else
			correo = correoObtener;

		correo.setCorreoCuerpo(correoCuerpoDao.obtenerPorId(correo.getPk().getIdCorreo()));
		correo.setListaCorreoDestino(correoDestinoDao.listarPorIdCorreo(correo.getPk().getIdCorreo()));
		correo.setListaCorreoAdjuntos(correoAdjuntoDao.listarPorIdCorreo(correo.getPk().getIdCorreo()));

		return correo;
	}

	private Alerta traerAlertaCompleta(Integer idAlerta, Boolean flgObtenerPrincipal) {
		Alerta alerta = null;

		alerta = alertaDao.obtenerPorId(new AlertaPk(idAlerta));
		alerta.setListaAdicional(alertaAdicionalDao.listarPorIdAlerta(idAlerta));
		// alerta.setListaDestino(alertaDestinoDao.listarPorIdAlerta(idAlerta));
		alerta.setListaDestino(alertaDestinoDao.listarPorIdAlertaSentencia(idAlerta));
		alerta.setListaDetalle(alertaDetalleDao.listarPorIdAlerta(idAlerta));
		alerta.setListaPlan(alertaPlanDao.listarPorIdAlerta(idAlerta));
		alerta.setListaReglaNegocioDetalle(reglaNegocioDetalleDao.listarPorIdReglaNegocio(alerta.getIdReglaNegocio()));
		// logger.debug("traerAlertaCompleta");
		return alerta;
	}

	private ReglaNegocio getReglaNegocio(Integer idReglaNegocio) {
		ReglaNegocio rn = daoReglaNegocio.obtenerSinConfirmarPorId(new ReglaNegocioPk(idReglaNegocio));
		if (!UValidador.estaVacio(rn.getIdColor())) {
			// ColorMast color;
			// color = daoColorMast.obtenerPorId(new ColorMastPk(rn.getIdColor()), false);
			// rn.setColorMast(color);
		}
		return rn;
	}

	//YA
	@Transactional
	public void LogGenerarAlerta(String tag,SeguridadUsuarioActual usuarioActual, Integer idReglaNegocio) throws Exception {
		if (idReglaNegocio == null)
			idReglaNegocio = 0;
		logger.debug(tag+"004.1 : LogGenerarAlerta(idReglaNegocio) : inicio | RN:" + idReglaNegocio.toString());
		
		try {
			
			logger.debug(tag+"004.1 : LogGenerarAlerta(idReglaNegocio) : P1 de 4 | RN:" + idReglaNegocio.toString());
			/*ReglaNegocio rn = null;
			rn = daoReglaNegocio.obtenerPorId(idReglaNegocio);
			rn.setFlgLogGenerarAlerta("N");
			rn.setModificacionFecha(new Date());
			daoReglaNegocio.actualizar(rn);*/
			
			logger.debug(tag+"004.1 : LogGenerarAlerta(idReglaNegocio) : P2 de 4 | RN:" + idReglaNegocio.toString());
			
			EjecucionApiTransaccion res = new EjecucionApiTransaccion();
			this.leerPropiedades();			
			String host = env.getProperty("proxy.erp.alertas");			
			String url = host + "spring/alertas/reglanegocio/loggeneraralerta";
			logger.debug(tag+"004.1 : LogGenerarAlerta(idReglaNegocio) : P3 de 4 | RN:" + idReglaNegocio.toString());
			res.setUrlApi(url);			
			res.setEntero1(idReglaNegocio);			
			this.ejecutarApi(res);
			
			/*CompletableFuture.runAsync(() -> {
	            try {
	                this.ejecutarApi(res);
	            } catch (Exception e) {
	                logger.error("{loggeneraralertahilo} - Error en ejecutarApi async", tag, e);
	            }
	        });*/
			
			logger.debug(tag+"004.1 : LogGenerarAlerta(idReglaNegocio) : P4 de 4 | RN:" + idReglaNegocio.toString());
						
		} catch (Exception ex) {
			ex.printStackTrace();
			ErrorTransaccion d = new ErrorTransaccion(ex);
			d.setAplicacionId("MA");
			d.setIdReglaNegocio(idReglaNegocio);
			d.setEstado("ACT");			
			d.setProceso("EXCEPTION");
			this.registrarError(d);
			logger_error("004.1 : LogGenerarAlerta(idReglaNegocio) : ERROR | RN:" + idReglaNegocio.toString(),
					"LogGenerarAlerta", ex,idReglaNegocio);			
		}
		logger.debug(tag+"004.1 : LogGenerarAlerta(idReglaNegocio) : fin | RN:" + idReglaNegocio.toString());
	}

	//YA
	@Transactional
	public void EjecutarReglaNegocioHilo(SeguridadUsuarioActual usuarioActual, Integer idReglaNegocio,
			Integer idProgramacion,String className,String tag) throws Exception {

		if (idReglaNegocio == null)
			idReglaNegocio = 0;
		if (idProgramacion == null)
			idProgramacion = 0;
		logger.debug("");
		logger.debug(tag+" 002 : EjecutarReglaNegocio(idReglaNegocio,idProgramacion) : inicio | RN:" + idReglaNegocio.toString() + " PRG:" + idProgramacion.toString());

		if (daoReglaNegocio == null)
			daoReglaNegocio = new ReglaNegocioDaoImpl();
		if (daoReglaNegocioProgramacion == null)
			daoReglaNegocioProgramacion = new ReglaNegocioProgramacionDaoImpl();

		try {
			ReglaNegocio reglaNegocio;
			ReglaNegocioProgramacion reglaNegocioProgramacion;
			reglaNegocio = daoReglaNegocio.obtenerSinConfirmarPorId(new ReglaNegocioPk(idReglaNegocio));
			reglaNegocioProgramacion = daoReglaNegocioProgramacion.obtenerPorId(new ReglaNegocioProgramacionPk(idReglaNegocio, idProgramacion));

			reglaNegocioProgramacion.setFlgEjecutandoActualmente("S");
			reglaNegocioProgramacion.setFechaEjecutandoActualmente(new Date());
			
			this.ActualizarReglaNegocioProgramacionHilo(reglaNegocioProgramacion, usuarioActual);
			
			//this.EjecutarReglaNegocio(reglaNegocio, reglaNegocioProgramacion, usuarioActual,className,tag);
			
			Date fechaHoraActualServidor = daoReglaNegocio.obtenerFechaHoraServidor();
			
			// ejecutar procedimiento almacenado
			DominioTransaccion res = ejecutarStoreProcedure(usuarioActual, reglaNegocio.getPk().getIdReglaNegocio(),
					reglaNegocioProgramacion.getPk().getIdProgramacion(), reglaNegocio.getObjectBd(),className,tag);
			
			logger.debug(tag+"     002 : EJECUTANDO EL SP | RN:" + res.getTransaccionEstado());
			if (("OK").equals(res.getTransaccionEstado())){
				this.actualizarOk(usuarioActual, reglaNegocio.getPk().getIdReglaNegocio(), reglaNegocioProgramacion.getPk().getIdProgramacion(),fechaHoraActualServidor,tag);	
			} else {
				this.actualizarError(usuarioActual, reglaNegocio.getPk().getIdReglaNegocio(), reglaNegocioProgramacion.getPk().getIdProgramacion(), fechaHoraActualServidor, res, tag);				
			}
			
		} catch (Exception ex) {
			ex.printStackTrace();
			ErrorTransaccion d = new ErrorTransaccion(ex);
			d.setAplicacionId("MA");
			d.setIdReglaNegocio(idReglaNegocio);
			d.setEstado("ACT");			
			d.setProceso("EXCEPTION");
			this.registrarError(d);
			logger.debug(tag+" 002 : EjecutarReglaNegocio(idReglaNegocio,idProgramacion) : ERROR | RN:" + idReglaNegocio.toString() + " PRG:" + idProgramacion.toString());
			logger.debug(ex.getMessage());
			logger_error(tag+" 002 : EjecutarReglaNegocio(idReglaNegocio,idProgramacion) : ERROR | RN:" + idReglaNegocio.toString() + " PRG:" + idProgramacion.toString(), className, ex,idReglaNegocio);
		}
		logger.debug(tag+" 002 : EjecutarReglaNegocio(idReglaNegocio,idProgramacion) : fin | RN:" + idReglaNegocio.toString() + " PRG:" + idProgramacion.toString());
	}

	public void ActualizarReglaNegocioProgramacionHilo(ReglaNegocioProgramacion reglaNegocioProgramacion,
			SeguridadUsuarioActual usuarioActual) throws Exception {
		try {
			ReglaNegocioProgramacion reglaNegocio = new ReglaNegocioProgramacion();
			reglaNegocio = reglaNegocioProgramacion;
			reglaNegocio.setModificacionFecha(new Date());
			reglaNegocio.setModificacionTerminal(usuarioActual.getDireccionIp());
			reglaNegocio.setModificacionUsuario(usuarioActual.getUsuario());
			daoReglaNegocioProgramacion.actualizar(reglaNegocio);
		} catch (Exception ex) {
			ex.printStackTrace();
			ErrorTransaccion d = new ErrorTransaccion(ex);
			d.setAplicacionId("MA");
			//d.setIdReglaNegocio(idReglaNegocio);
			d.setEstado("ACT");			
			d.setProceso("EXCEPTION");
			this.registrarError(d);
			logger.error("ErrorHilo : error al ejecutar el hilo");
			logger.error(ex.getMessage());
			logger_error("ErrorHilo : error al ejecutar el hilo", "AlertaServicioImpl.ActualizarReglaNegocioProgramacionHilo", ex);
		}
	}

	@Transactional(propagation = Propagation.REQUIRES_NEW)
	private EjecucionApiTransaccion ejecutarStoreProcedure(SeguridadUsuarioActual usuarioActual, Integer idReglaNegocio,
			Integer idProgramacion, String objetoBd,String className,String tag) throws Exception {
		//DominioTransaccion res=new DominioTransaccion();
		EjecucionApiTransaccion res = new EjecucionApiTransaccion();
		//StringBuilder sentenciaSQL = new StringBuilder();
		try {
			/*logger.debug(tag+"   002 : " + objetoBd);
			sentenciaSQL.append(objetoBd);
			daoReglaNegocio.ejecutarPorSentenciaSQL(sentenciaSQL);
			res.setTransaccionEstado(DominioTransaccion.OK);
			return res;*/
			this.leerPropiedades();
			logger.debug(tag+"   002 : " + objetoBd);
			String host = env.getProperty("proxy.erp.alertas");			
			String url = host + "spring/alertas/reglanegocio/ejecutarstore";
			logger.debug(tag+"   002 : " + url);
			res.setUrlApi(url);
			res.setTexto1(objetoBd);
			res.setEntero1(idReglaNegocio);
			res = this.ejecutarApi(res);
			
		} catch (Exception ex) {
			ErrorTransaccion d = new ErrorTransaccion(ex);
			d.setAplicacionId("MA");
			d.setIdReglaNegocio(idReglaNegocio);
			d.setEstado("ACT");			
			d.setProceso("EXCEPTION");
			this.registrarError(d);	
			
			logger.error(tag+"   002 : " + objetoBd + idReglaNegocio.toString() + " PRG:" + idProgramacion.toString()) ;
			logger.error(ex.getMessage()) ;
			ex.printStackTrace();
			logger_error(tag+"   002 : EjecutarReglaNegocio(idReglaNegocio,idProgramacion) : ERROR EJECUTANDO EL SP | RN:"
							+ idReglaNegocio.toString() + " PRG:" + idProgramacion.toString(),
							className, ex,idReglaNegocio);					
			res.setTransaccionEstado(DominioTransaccion.ERROR);
			res.setTransaccionComentarios(ex.getMessage());
		}
		return res;
	}

	//YA
	@Transactional
	public void ExtraerData(SeguridadUsuarioActual usuarioActual,String tag) throws Exception {
		logger.debug("");
		logger.debug(tag+" 003 : ExtraerData() : inicio");
		logger_debug(tag+" 003 : ExtraerData() : inicio","ExtraerDataTask");
		List<DominioParametroPersistencia> parametros = new ArrayList<DominioParametroPersistencia>();
		try {
			logger.debug(tag+" 003 : Setear parametros SP");
			logger_debug(tag+" 003 : Setear parametros SP","ExtraerDataTask");
			parametros.add(new DominioParametroPersistencia("p_usuario", String.class, usuarioActual.getUsuario()));
			parametros.add(new DominioParametroPersistencia("p_terminal", String.class, usuarioActual.getDireccionIp()));
			daoReglaNegocio.ejecutarPorQuery("reglanegocio.extraerDataFuente", parametros);
			logger.debug(tag+" 003 : SP_EXTRAER_DATA_FUENTE EJECUTADO OK!");
			logger_debug(tag+" 003 : SP_EXTRAER_DATA_FUENTE EJECUTADO OK!","ExtraerDataTask");
		} catch (Exception ex) {
			ex.printStackTrace();
			ErrorTransaccion d = new ErrorTransaccion(ex);
			d.setAplicacionId("MA");
			d.setEstado("ACT");			
			d.setProceso("EXCEPTION");
			this.registrarError(d);
			logger_error(tag+" 003 : ExtraerData() : ERROR", "ExtraerDataTask", ex);
		} finally {
			configuracionServicioDao.cambiarEjecutandoExtraerData("N");
		}	
		logger.debug(tag+" 003 : ExtraerData() : fin");
		logger_debug(tag+" 003 : ExtraerData() : fin","ExtraerDataTask");
	}

	//YA
	private byte[] crearCorreoHtml(Alerta alerta, ReglaNegocio reglaNegocio) throws Exception {
		Boolean flgMostrarFechaHoraEnvio = Boolean.FALSE;
		Boolean flgMostrarFechaHoraSeguimiento = Boolean.FALSE;

		byte[] bytes = null;
		StringBuffer cuerpoCorreo = new StringBuffer();
		char comillas = '"';

		String obtenerColorRgb = "#4BACC6";
		String obtenerAsunto = "";
		String obtenerPie = "";
		String obtenerImportancia = "";
		Date obtenerFechaEnvioAlerta = new Date();

		if (reglaNegocio != null) {
			obtenerAsunto = obtenerTextHtml(UValidador.obtenerValorCadenaSinNulo(reglaNegocio.getCorreoContenido()));
			obtenerPie = obtenerTextHtml(UValidador.obtenerValorCadenaSinNulo(reglaNegocio.getCorreoPie()));
			if (reglaNegocio.getIdColor() != null) {

				Colormast colorcabecera = daocolor.obtenerPorId(reglaNegocio.getIdColor());

				obtenerColorRgb = UValidador.obtenerValorCadenaSinNulo(colorcabecera.getColorrgb());
			}
		}

		cuerpoCorreo.append("<!doctype html>");
		cuerpoCorreo.append("<html lang=" + comillas + "es" + comillas + ">");
		cuerpoCorreo.append("<head>");
		cuerpoCorreo.append("<meta charset=" + comillas + "UTF-8" + comillas + "/>");
		// logger.debug("COLOR -REGLA " + reglaNegocio.getPk().getIdReglaNegocio() +
		// "COLOR==>" + obtenerColorRgb);
		if (UValidador.estaVacio(reglaNegocio.getCorreoHojaEstilo())) {
			// logger.debug("entro a estilo sin hojadeestilo");

			cuerpoCorreo.append("<style type='text/css'>");
			if (!UString.estaVacio(obtenerColorRgb)) {
				cuerpoCorreo.append("div#alerta {background-color:" + obtenerColorRgb + ";}");
			} else {
				cuerpoCorreo.append("div#alerta {background-color: #808080;}");
			}

			cuerpoCorreo.append("table");
			cuerpoCorreo.append("{	border: 1px solid #000000;");
			cuerpoCorreo.append("	background-color:  #000000;");
			cuerpoCorreo.append("	font-size: small;");
			cuerpoCorreo.append("	color: #FFFFFF;  }");
			cuerpoCorreo.append("th {");
			cuerpoCorreo.append("	border: 1px solid #000000;");

			if (!UString.estaVacio(obtenerColorRgb)) {
				cuerpoCorreo.append("	background-color:" + obtenerColorRgb + "; }");
			} else {
				cuerpoCorreo.append("	background-color: #808080; }");
			}
			cuerpoCorreo.append("td {");
			cuerpoCorreo.append("	border: 1px solid #000000;");
			cuerpoCorreo.append("	background-color: #FFFFFF;");
			cuerpoCorreo.append("	color: #000000;	}");
			cuerpoCorreo.append("</style>");
		} else {
			// logger.debug("entro a hoja de estilo configurado+==>>" +
			// reglaNegocio.getCorreoHojaEstilo());
			cuerpoCorreo.append(reglaNegocio.getCorreoHojaEstilo());
		}

		cuerpoCorreo.append("</head>");
		cuerpoCorreo.append("<body>");

		if (!UValidador.estaVacio(obtenerAsunto)) {
			cuerpoCorreo.append("<h2>");
			cuerpoCorreo.append(obtenerTextHtml(obtenerAsunto));
			cuerpoCorreo.append("</h2>");
		}

		if (flgMostrarFechaHoraEnvio) {
			cuerpoCorreo.append("<table>");
			if (!UValidador.estaVacio(obtenerImportancia)) {
				cuerpoCorreo.append("<tr><th><strong>");
				cuerpoCorreo.append("Importancia");
				cuerpoCorreo.append("</strong></th><td>");
				cuerpoCorreo.append(obtenerImportancia);
				cuerpoCorreo.append("</td></tr>");
			}
			cuerpoCorreo.append("<tr><th><strong>");
			cuerpoCorreo.append("Fecha y Hora de Env&iacute;o de Correo:");
			cuerpoCorreo.append("</strong></th><td>");
			cuerpoCorreo
					.append(UFechaHora.convertirFechaCadena(obtenerFechaEnvioAlerta, UConstante.FORMATO_FECHA_HORA24));
			cuerpoCorreo.append("</td></tr></table><br />");
		}

		/**
		 * ADICIONAL - INICIO
		 */
		if (alerta.getListaAdicional().size() > 0) {
			cuerpoCorreo.append("<h4>");
			cuerpoCorreo.append("Adicionales");
			cuerpoCorreo.append("</h4><table>");
		}
		for (AlertaAdicional adicional : alerta.getListaAdicional()) {
			cuerpoCorreo.append("<tr><th><strong>");
			cuerpoCorreo.append(obtenerTextHtml(adicional.getDescripcionCampo()));
			cuerpoCorreo.append("</strong></th><td>");
			cuerpoCorreo.append(obtenerTextHtml(adicional.getValor()));
			cuerpoCorreo.append("</td></tr>");
		}
		if (alerta.getListaAdicional().size() > 0) {
			cuerpoCorreo.append("</table>");
			cuerpoCorreo.append("<br/>");
		}
		/**
		 * ADICIONAL - FIN
		 */

		/**
		 * PLAN - INICIO
		 */
		if (alerta.getListaPlan().size() > 0) {
			cuerpoCorreo.append("<h4>");
			cuerpoCorreo.append("Plan de Acci&oacute;n");
			cuerpoCorreo.append("</h4>");
		}
		for (AlertaPlan plan : alerta.getListaPlan()) {
			cuerpoCorreo.append(plan.getOrdenColumna().toString() + ".- " + obtenerTextHtml(plan.getPlan()));
			cuerpoCorreo.append("<br />");
		}
		if (alerta.getListaPlan().size() > 0) {
			cuerpoCorreo.append("<br />");
		}
		/**
		 * PLAN - FIN
		 */

		/**
		 * DETALLE - INICIO
		 */
		Integer _tamanioDetalle = 0;
		Integer _tamanioDetalleReal = 0;
		Hashtable<String, String> hsExcluidos = new Hashtable<String, String>();

		for (ReglaNegocioDetalle reglaNegocioDetalle : alerta.getListaReglaNegocioDetalle()) {
			_tamanioDetalleReal++;
			if (UValidador.validarFlag(reglaNegocioDetalle.getFlgMostrarEnCorreo())) {
				_tamanioDetalle++;
			} else {

				hsExcluidos.put(reglaNegocioDetalle.getNombreCampo(), reglaNegocioDetalle.getNombreCampo());
			}
		}

		Integer contadorPosicion = null;
		String obtenerFechaHoraGeneradoAlerta = null;
		String obtenerFechaHoraTransferidoAlerta = null;

		if (alerta.getFechaLogAlerta() != null) {
			obtenerFechaHoraGeneradoAlerta = UFechaHora.convertirFechaCadena(alerta.getFechaLogAlerta(),
					UConstante.FORMATO_FECHA_HORA24);
		} else {
			obtenerFechaHoraGeneradoAlerta = UFechaHora.convertirFechaCadena(new Date(),
					UConstante.FORMATO_FECHA_HORA24);
		}

		if (alerta.getCreacionFecha() != null) {
			obtenerFechaHoraTransferidoAlerta = UFechaHora.convertirFechaCadena(alerta.getCreacionFecha(),
					UConstante.FORMATO_FECHA_HORA24);
		} else {
			obtenerFechaHoraTransferidoAlerta = UFechaHora.convertirFechaCadena(new Date(),
					UConstante.FORMATO_FECHA_HORA24);
		}

		cuerpoCorreo.append("<table>");

		if (flgMostrarFechaHoraSeguimiento) {
			cuerpoCorreo.append("<tr><th  colspan=" + comillas + _tamanioDetalle.toString() + comillas + "><strong>");
			cuerpoCorreo.append("Alerta Nro: " + alerta.getPk().getIdAlerta().toString() + " - " + "Generado: "
					+ obtenerFechaHoraGeneradoAlerta + " - " + "Transferido: " + obtenerFechaHoraTransferidoAlerta);
			cuerpoCorreo.append("</strong></th></tr>");
		}

		cuerpoCorreo.append("<tr>");
		for (ReglaNegocioDetalle reglaNegocioDetalle : alerta.getListaReglaNegocioDetalle()) {
			if (UValidador.validarFlag(reglaNegocioDetalle.getFlgMostrarEnCorreo())) {
				cuerpoCorreo.append("<th><strong>");
				cuerpoCorreo.append(obtenerTextHtml(reglaNegocioDetalle.getDescripcionCampo()));
				cuerpoCorreo.append("<strong></th>");
			}
		}
		cuerpoCorreo.append("</tr>");

		cuerpoCorreo.append("<tr>");
		contadorPosicion = 0;
		int cantidadRegistros = 0;
		for (AlertaDetalle detalle : alerta.getListaDetalle()) {
			cantidadRegistros++;
			if (contadorPosicion < _tamanioDetalleReal) {

				if (detalle.getIdAlineacionColumna() == null)
					detalle.setIdAlineacionColumna("CEN");

				Boolean flgMostrar = !hsExcluidos.containsKey(detalle.getNombreCampo());

				if (flgMostrar) {
					if (detalle.getIdAlineacionColumna().equalsIgnoreCase("IZQ")) {
						cuerpoCorreo.append("<td align=" + comillas + "left" + comillas + ">");
					} else if (detalle.getIdAlineacionColumna().equalsIgnoreCase("CEN")) {
						cuerpoCorreo.append("<td align=" + comillas + "center" + comillas + ">");
					} else if (detalle.getIdAlineacionColumna().equalsIgnoreCase("DER")) {
						cuerpoCorreo.append("<td align=" + comillas + "right" + comillas + ">");
					}
					cuerpoCorreo.append(obtenerTextHtml(detalle.getValor()) + "</td>");
				}
			}
			contadorPosicion++;
			if (_tamanioDetalleReal == contadorPosicion) {
				cuerpoCorreo.append("</tr>");
				if (alerta.getListaDetalle().size() != cantidadRegistros)
					cuerpoCorreo.append("<tr>");
			}

			if (alerta.getListaReglaNegocioDetalle().size() == contadorPosicion) {
				contadorPosicion = 0;
			}
		}
		cuerpoCorreo.append("</table>");

		if (!UValidador.estaVacio(obtenerPie)) {
			cuerpoCorreo.append(obtenerPie);
		}

		cuerpoCorreo.append("</body>");
		cuerpoCorreo.append("</html>");

		/**
		 * DETALLE - FIN
		 */

		Charset charset = StandardCharsets.UTF_8;
		CharsetEncoder encoder = charset.newEncoder();

		// LEONARDO CODE: CONVERTIR HTML EN LINEAL CSS
		// StringBuffer stringtest = new StringBuffer();
		// stringtest.append(convertirestilosenlinea(cuerpoCorreo + ""));
		// CharBuffer buffer = CharBuffer.wrap(stringtest);
		// FIN

		try {
			// bytes = encoder.encode(buffer).array();
			bytes = cuerpoCorreo.toString().getBytes();
		} catch (Exception e) {
			e.printStackTrace();
			ErrorTransaccion d = new ErrorTransaccion(e);
			d.setAplicacionId("MA");
			//d.setIdReglaNegocio(idReglaNegocio);
			d.setEstado("ACT");			
			d.setProceso("EXCEPTION");
			this.registrarError(d);
			logger_error("crearCorreoHtml", "AlertaServicioImpl.crearCorreoHtml", e);
		}
		
		
		return bytes;
	}

	// LEONARDO CODE INICIO
	public String convertirestilosenlinea(String html) throws IOException {
		Document x = inlineStyles(html);
		return x + "";
	}

	public static Document inlineStyles(String html) throws IOException {
		// Document doc = Jsoup.connect("http://mypage.com/inlineme.php").get();
		Document doc = Jsoup.parse(html);
		String style = "style";
		Elements els = doc.select(style);// to get all the style elements
		for (Element e : els) {
			String styleRules = e.getAllElements().get(0).data().replaceAll("\n", "").trim(), delims = "{}";
			StringTokenizer st = new StringTokenizer(styleRules, delims);
			while (st.countTokens() > 1) {
				String selector = st.nextToken(), properties = st.nextToken();
				// Process selectors such as "a:hover"
				if (selector.indexOf(":") > 0) {
					selector = selector.substring(0, selector.indexOf(":"));
				}
				if (Strings.isNullOrEmpty(selector)) {
					continue;
				}
				Elements selectedElements = doc.select(selector);
				for (Element selElem : selectedElements) {
					String oldProperties = selElem.attr(style);
					selElem.attr(style,
							oldProperties.length() > 0 ? concatenateProperties(oldProperties, properties) : properties);
				}
			}
			e.remove();
		}
		return doc;
	}

	private static String concatenateProperties(String oldProp, String newProp) {
		oldProp = oldProp.trim();
		if (!newProp.endsWith(";")) {
			newProp += ";";
		}
		return newProp + oldProp; // The existing (old) properties should take precedence.
	}
//LEONARDO CODE FIN

	public String obtenerTextHtml(String cadenaReemplazar) {
		return UString.cambiarCaracteresEspecialesHTML(cadenaReemplazar);
	}

	public DominioPaginacion listarAlerta(FiltroPaginacionAlerta filtro) throws Exception {
		List<DominioParametroPersistencia> parametros = new ArrayList<DominioParametroPersistencia>();
		filtro.getPaginacion().setPaginacionRegistrosPorPagina(50);

		Date dd = null;
		if (filtro.getFechaPreparacionInicio() != null) {
			dd = UFechaHora.obtenerFechaHoraInicioDia(filtro.getFechaPreparacionInicio());
			filtro.setFechaPreparacionInicio(dd);
		}
		if (filtro.getFechaPreparacionFin() != null) {
			dd = UFechaHora.obtenerFechaHoraInicioDia(filtro.getFechaPreparacionFin());
			filtro.setFechaPreparacionFin(dd);
		}
		parametros.add(
				new DominioParametroPersistencia("idreglanegocio", BigDecimal.class, filtro.getId_regla_negocio()));
		parametros.add(new DominioParametroPersistencia("fechaPreparacionInicio", Date.class,
				filtro.getFechaPreparacionInicio()));
		parametros.add(new DominioParametroPersistencia("fechaPreparacionFin", Date.class,
				filtro.getFechaPreparacionFin()));
		parametros.add(
				new DominioParametroPersistencia("p_resumenlogalerta", String.class, filtro.getP_resumenlogalerta()));

		Integer registros = alertaDao.contar("alerta.listarFiltrocontar", parametros);
		List<?> datos = alertaDao.listarConPaginacion(filtro.getPaginacion(), parametros, "alerta.listarFiltro",
				Alertadto.class);
		filtro.getPaginacion().setPaginacionRegistrosPorPagina(50);
		filtro.getPaginacion().setPaginacionRegistrosEncontrados(registros.intValue());
		filtro.getPaginacion().setPaginacionListaResultado(datos);
		return filtro.getPaginacion();
	}

	public DominioPaginacion listarAlertaDetalle(FiltroPaginacionAlerta filtro) {
		List<DominioParametroPersistencia> parametros = new ArrayList<DominioParametroPersistencia>();

		parametros.add(new DominioParametroPersistencia("id_alerta", BigDecimal.class, filtro.getId_alerta()));

		Integer registros = alertaDao.contar("alerta.listarDetallecontar", parametros);
		List<?> datos = alertaDao.listarConPaginacion(filtro.getPaginacion(), parametros, "alerta.listarDetalle",
				AlertaDetalledto.class);
		filtro.getPaginacion().setPaginacionRegistrosPorPagina(10);
		filtro.getPaginacion().setPaginacionRegistrosEncontrados(registros.intValue());
		filtro.getPaginacion().setPaginacionListaResultado(datos);
		return filtro.getPaginacion();
	}

	public DominioPaginacion listarAlertaAdicional(FiltroPaginacionAlerta filtro) {
		List<DominioParametroPersistencia> parametros = new ArrayList<DominioParametroPersistencia>();

		parametros.add(new DominioParametroPersistencia("id_alerta", BigDecimal.class, filtro.getId_alerta()));

		Integer registros = alertaDao.contar("alerta.listarAdicionalcontar", parametros);
		List<?> datos = alertaDao.listarConPaginacion(filtro.getPaginacion(), parametros, "alerta.listarAdicional",
				AlertaDetalledto.class);
		filtro.getPaginacion().setPaginacionRegistrosPorPagina(10);
		filtro.getPaginacion().setPaginacionRegistrosEncontrados(registros.intValue());
		filtro.getPaginacion().setPaginacionListaResultado(datos);
		return filtro.getPaginacion();
	}

	public DominioPaginacion listarAlertaDestino(FiltroPaginacionAlerta filtro) {
		List<DominioParametroPersistencia> parametros = new ArrayList<DominioParametroPersistencia>();

		parametros.add(new DominioParametroPersistencia("id_alerta", BigDecimal.class, filtro.getId_alerta()));

		Integer registros = alertaDao.contar("alerta.contarpaginacionDestino", parametros);
		List<?> datos = alertaDao.listarConPaginacion(filtro.getPaginacion(), parametros,
				"alerta.listarpaginacionDestino", AlertaDestinodto.class);
		filtro.getPaginacion().setPaginacionRegistrosPorPagina(10);
		filtro.getPaginacion().setPaginacionRegistrosEncontrados(registros.intValue());
		filtro.getPaginacion().setPaginacionListaResultado(datos);
		return filtro.getPaginacion();
	}

	public DominioPaginacion listarAlertaPlan(FiltroPaginacionAlerta filtro) {
		List<DominioParametroPersistencia> parametros = new ArrayList<DominioParametroPersistencia>();

		parametros.add(new DominioParametroPersistencia("id_alerta", BigDecimal.class, filtro.getId_alerta()));

		Integer registros = alertaDao.contar("alerta.listarpaginacionPlancontar", parametros);
		List<?> datos = alertaDao.listarConPaginacion(filtro.getPaginacion(), parametros, "alerta.listarpaginacionPlan",
				AlertaPlandto.class);
		filtro.getPaginacion().setPaginacionRegistrosPorPagina(10);
		filtro.getPaginacion().setPaginacionRegistrosEncontrados(registros.intValue());
		filtro.getPaginacion().setPaginacionListaResultado(datos);
		return filtro.getPaginacion();
	}

	public ConfiguracionServicio getCfg() {
		return cfg;
	}

	public void setCfg(ConfiguracionServicio cfg) {
		this.cfg = cfg;
	}

	public void registrarEjecucionLog(String javaLog, String spLog, String resumenLog) {
		logger_debug(resumenLog,javaLog,null);
		daoReglaNegocio.registrarEjecucionLog(javaLog, spLog, resumenLog, null, null, null);
		
	}

	public void registrarEjecucionLog(String javaLog, String spLog, String resumenLog, Integer reglaNegocioId) {
		daoReglaNegocio.registrarEjecucionLog(javaLog, spLog, resumenLog, reglaNegocioId, null, null);
	}

	public void registrarEjecucionLog(String javaLog, String spLog, String resumenLog, Integer reglaNegocioId,
			Integer registrosExito, Integer registrosError) {
		daoReglaNegocio.registrarEjecucionLog(javaLog, spLog, resumenLog, reglaNegocioId, registrosExito,
				registrosError);
	}
	
	@Transactional(propagation = Propagation.REQUIRES_NEW)
	private void actualizarOk(SeguridadUsuarioActual usuarioActual, Integer idReglaNegocio,
			Integer idProgramacion,Date fechaHoraActualServidor, String tag) throws Exception {	
		logger.debug(tag+"     002 : EJECUTANDO EL SP | RN: OK-01");
		ReglaNegocio reglaNegocio = daoReglaNegocio.obtenerPorId(idReglaNegocio);
		ReglaNegocioProgramacion reglaNegocioProgramacion = daoReglaNegocioProgramacion.obtenerPorId(idReglaNegocio, idProgramacion);
		Integer cantreg = 0;
		
		logger.debug(tag+"     002 : EJECUTANDO EL SP | RN: OK-02");
		List<DominioParametroPersistencia> parametros = new ArrayList<DominioParametroPersistencia>();
		parametros.add(new DominioParametroPersistencia("p_id_regla_negocio", Integer.class, idReglaNegocio));
		cantreg = fuenteAlertaDao.contar("fuentealerta.contarPorReglaNegocio", parametros);
		
		logger.debug(tag+"     002 : EJECUTANDO EL SP | RN: OK-03");			
		reglaNegocioProgramacion.setModificacionTerminal(usuarioActual.getDireccionIp());
		reglaNegocioProgramacion.setModificacionUsuario(usuarioActual.getUsuario());
		reglaNegocioProgramacion.setModificacionFecha(fechaHoraActualServidor);
		//reglaNegocioProgramacion.setCreacionTerminal( UInteger.obtenerValorEnteroSinNulo(cantreg).toString());
		reglaNegocioProgramacion.setFechaUltimaEjecucion(fechaHoraActualServidor);
		reglaNegocioProgramacion.setFlgEjecutandoActualmente("N");
		reglaNegocioProgramacion.setFechaEjecutandoActualmente(null);
		daoReglaNegocioProgramacion.actualizar(reglaNegocioProgramacion);
	
		reglaNegocio.setModificacionTerminal(usuarioActual.getDireccionIp());
		reglaNegocio.setModificacionUsuario(usuarioActual.getUsuario());
		reglaNegocio.setModificacionFecha(fechaHoraActualServidor);
		reglaNegocio.setFechaUltimaEjecucion(fechaHoraActualServidor);
		//reglaNegocio.setCreacionTerminal( UInteger.obtenerValorEnteroSinNulo(cantreg).toString());
//		reglaNegocio.setEjecucionCantidad(cantreg);
//		reglaNegocio.setEjecucionMensaje("");		
		daoReglaNegocio.actualizar(reglaNegocio);
		
		//logger.debug(tag+"     002 : EJECUTANDO EL SP | RN: OK-04  RN:" + UString.IntegerToStringSinNulo(idReglaNegocio) + " - Cant Reg:" +  UString.IntegerToStringSinNulo(cantreg) );
		//logger.debug(tag+"     002 : EJECUTANDO EL SP | RN: OK-04  RN:" + UString.IntegerToStringSinNulo(reglaNegocio.getPk().getIdReglaNegocio()) );
		logger.debug(tag+"     002 : EJECUTANDO EL SP | RN: OK-04");
		
	}
	
	@Transactional(propagation = Propagation.REQUIRES_NEW)
	private void actualizarError(SeguridadUsuarioActual usuarioActual, Integer idReglaNegocio,
			Integer idProgramacion,Date fechaHoraActualServidor,DominioTransaccion res, String tag) throws Exception {				
		ReglaNegocio reglaNegocio = daoReglaNegocio.obtenerPorId(idReglaNegocio);
		ReglaNegocioProgramacion reglaNegocioProgramacion = daoReglaNegocioProgramacion.obtenerPorId(idReglaNegocio, idProgramacion);
		
		logger.debug(tag+"     002 : EJECUTANDO EL SP | RN: ER-01");
		reglaNegocioProgramacion.setModificacionTerminal(usuarioActual.getDireccionIp());
		reglaNegocioProgramacion.setModificacionUsuario(usuarioActual.getUsuario());
		reglaNegocioProgramacion.setModificacionFecha(fechaHoraActualServidor);
		reglaNegocioProgramacion.setFechaUltimaEjecucion(fechaHoraActualServidor);
		reglaNegocioProgramacion.setFlgEjecutandoActualmente("N");
		reglaNegocioProgramacion.setFechaEjecutandoActualmente(null);
		daoReglaNegocioProgramacion.actualizar(reglaNegocioProgramacion);
		
		logger.debug(tag+"     002 : EJECUTANDO EL SP | RN: ER-02");
		logger.debug(res.getTransaccionComentarios());
		reglaNegocio.setModificacionTerminal(usuarioActual.getDireccionIp());
		reglaNegocio.setModificacionUsuario(usuarioActual.getUsuario());
		reglaNegocio.setModificacionFecha(fechaHoraActualServidor);
		reglaNegocio.setFechaUltimaEjecucion(fechaHoraActualServidor);
		//reglaNegocio.setEstado("E");
		//reglaNegocio.setCreacionTerminal(UString.extraer(res.getTransaccionComentarios(),0,49)) ;
//		reglaNegocio.setEjecucionCantidad(0);
//		reglaNegocio.setEjecucionMensaje(UString.extraer(res.getTransaccionComentarios(),0,3997));
		daoReglaNegocio.actualizar(reglaNegocio);
		
	}
		
	@Transactional
	public void UpdateReglaNegocio(String proceso,Integer idReglaNegocio,String estado,String flgLogGenerarAlerta, String mensaje) throws Exception {
		Integer idReglaNegocioTemporal = idReglaNegocio;
		if (idReglaNegocio == null)
			idReglaNegocio = 0;
		try {
			
			EjecucionApiTransaccion res = new EjecucionApiTransaccion();
			res.setEntero1(idReglaNegocio);
			
			res.setTexto1(proceso);
			res.setTexto2(estado);
			res.setTexto3(flgLogGenerarAlerta);
			res.setTexto4(mensaje);
			
			this.leerPropiedades();			
			String host = this.env.getProperty("proxy.erp.alertas");			
			String url = host + "spring/alertas/reglanegocio/actualizarregla";
			
			res.setUrlApi(url);			
				
			CompletableFuture.runAsync(() -> {
	            try {
	                this.ejecutarApi(res);
	            } catch (Exception e) {
	            	ErrorTransaccion d = new ErrorTransaccion(e);
	    			d.setAplicacionId("MA");
	    			d.setIdReglaNegocio(idReglaNegocioTemporal);
	    			d.setEstado("ACT");			
	    			d.setProceso("EXCEPTION");
	    			try {
						this.registrarError(d);
					} catch (Exception e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
	                logger.error("{UpdateReglaNegocio} - Error en ejecutarApi async");
	            }
	        });
						
		} catch (Exception ex) {			
			ex.printStackTrace();
			ErrorTransaccion d = new ErrorTransaccion(ex);
			d.setAplicacionId("MA");
			d.setIdReglaNegocio(idReglaNegocioTemporal);
			d.setEstado("ACT");			
			d.setProceso("EXCEPTION");
			this.registrarError(d);
		}		
	}
	
}
