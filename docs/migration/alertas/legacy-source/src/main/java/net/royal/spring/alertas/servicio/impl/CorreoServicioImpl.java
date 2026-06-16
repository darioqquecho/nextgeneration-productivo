package net.royal.spring.alertas.servicio.impl;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.math.BigDecimal;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Properties;

import jakarta.mail.Message;
import jakarta.mail.PasswordAuthentication;
import jakarta.mail.Session;
import jakarta.mail.Transport;
import jakarta.mail.internet.InternetAddress;
import jakarta.mail.internet.MimeMessage;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.exolab.castor.types.DateTime;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import net.royal.spring.alertas.dao.impl.ConfiguracionServicioDaoImpl;
import net.royal.spring.alertas.dao.impl.CorreoAdjuntoDaoImpl;
import net.royal.spring.alertas.dao.impl.CorreoCuerpoDaoImpl;
import net.royal.spring.alertas.dao.impl.CorreoDaoImpl;
import net.royal.spring.alertas.dao.impl.CorreoDestinoDaoImpl;
import net.royal.spring.alertas.dao.impl.ReglaNegocioDaoImpl;
import net.royal.spring.alertas.dominio.AlertaDestino;
import net.royal.spring.alertas.dominio.ConfiguracionServicio;
import net.royal.spring.alertas.dominio.Correo;
import net.royal.spring.alertas.dominio.CorreoAdjunto;
import net.royal.spring.alertas.dominio.CorreoCuerpo;
import net.royal.spring.alertas.dominio.CorreoCuerpoPk;
import net.royal.spring.alertas.dominio.CorreoDestino;
import net.royal.spring.alertas.dominio.CorreoPk;
import net.royal.spring.alertas.dominio.dto.CorreoCore;
import net.royal.spring.alertas.dominio.dto.CorreoDestinoCore;
import net.royal.spring.alertas.dominio.dto.CorreoDto;
import net.royal.spring.alertas.dominio.dto.ReglaNegocioTaskDto;
import net.royal.spring.alertas.dominio.filtros.FiltroPaginacionCorreo;
import net.royal.spring.alertas.servicio.validar.CorreoAdjuntoServicioValidar;
import net.royal.spring.alertas.servicio.validar.CorreoDestinoServicioValidar;
import net.royal.spring.alertas.servicio.validar.CorreoServicioValidar;
import net.royal.spring.framework.core.UException;
import net.royal.spring.framework.core.UValidador;
import net.royal.spring.framework.modelo.correo.EmailConfiguracion;
import net.royal.spring.framework.modelo.correo.EmailTransaccion;
import net.royal.spring.framework.modelo.generico.DominioMensajeUsuario;
import net.royal.spring.framework.modelo.generico.DominioPaginacion;
import net.royal.spring.framework.modelo.generico.DominioParametroPersistencia;
import net.royal.spring.framework.modelo.generico.dto.DtoTabla;
import net.royal.spring.framework.modelo.seguridad.SeguridadUsuarioActual;
import net.royal.spring.framework.util.UFechaHora;
import net.royal.spring.framework.util.UString;
import net.royal.spring.framework.web.servicio.impl.GenericoServicioImpl;

@Service(value = "BeanServicioCorreo")
public class CorreoServicioImpl extends GenericoServicioImpl {
	public static String SPRING_NOMBRE = "BeanServicioCorreo";
	private static Logger logger = LogManager.getLogger(CorreoServicioImpl.class);

	@Autowired
	private CorreoDaoImpl correoDao;

	@Autowired
	private CorreoDestinoDaoImpl correDestinooDao;

	@Autowired
	private ConfiguracionServicioDaoImpl configuracionServicioDao;

	@Autowired
	CorreoDestinoServicioImpl correoDestinoService;

	@Autowired
	private CorreoServicioValidar validar;
	
	private Session sessionCorreo;

	@Autowired
	private AlertaServicioImpl alertaServicio;

	@Autowired
	private CorreoCuerpoDaoImpl correoCuerpoDao;
	
	@Autowired
	private ReglaNegocioDaoImpl reglaNegocioDao;
	
	@Autowired
	private CorreoDestinoDaoImpl correoDestinoDao;

	@Autowired
	private CorreoAdjuntoDaoImpl correoAdjuntoDao;
	
	@Autowired
	private CorreoDestinoServicioValidar validarDestino;
	
	@Autowired
	private CorreoAdjuntoServicioValidar validarAdjunto;
	
	@Transactional
	public Correo coreInsertar(SeguridadUsuarioActual usuarioActual, Correo correo) throws UException {
		// valores por defecto - preparando objeto
		correo = validar.prepararInsertar(usuarioActual, correo);
		List<CorreoDestino> lsDes = validarDestino.prepararInsertar(usuarioActual, correo.getListaCorreoDestino());
		correo.setListaCorreoDestino(lsDes);
		List<CorreoAdjunto> lsAdj = validarAdjunto.prepararInsertar(usuarioActual, correo.getListaCorreoAdjuntos());
		correo.setListaCorreoAdjuntos(lsAdj);
		
		// validaciones de negocio 		
		List<DominioMensajeUsuario> lst = new ArrayList<DominioMensajeUsuario>();
		lst = validar.coreInsertar(usuarioActual, correo);
		List<DominioMensajeUsuario> lstDestino = validarDestino.coreInsertar(usuarioActual, correo.getListaCorreoDestino());
		List<DominioMensajeUsuario> lstAdjunto = validarAdjunto.coreInsertar(usuarioActual, correo.getListaCorreoAdjuntos());
		lst.addAll(lstDestino);
		lst.addAll(lstAdjunto);
		if (!lst.isEmpty())
			throw new UException(lst);

		// transaccion - correo
		if (correo.getAuxTipoTransaccion().equals(EmailTransaccion.TIPO_TRANSACCION_LOGGER))
			correo.setEstado("LOG");
		
		// por problema en primari key
		Integer idCorreo = correoDao.contar("correo.generarsecuencia");
		logger.debug("id correo generado por secuencia:"+idCorreo);
		
		correo.getPk().setIdCorreo(idCorreo);
		correo = correoDao.coreInsertar(correo);
		
		// transaccion - correo cuerpo
		correoCuerpoDao.eliminar(new CorreoCuerpo(new CorreoCuerpoPk(correo.getPk().getIdCorreo())));
		correo.getCorreoCuerpo().getPk().setIdCorreo(idCorreo);
		correoCuerpoDao.registrar(correo.getCorreoCuerpo());
		
		// transaccion - correo destino
		List<CorreoDestino> lstDestinos = new ArrayList<CorreoDestino>();
		for (CorreoDestino destino : correo.getListaCorreoDestino() ) {
			boolean existe=false;
			for (CorreoDestino ccc : lstDestinos ) {
				if (destino.getPk().getCorreoDestino().equals(ccc.getPk().getCorreoDestino())) {
					existe=true;
				}
			}
			if (!existe) {
				lstDestinos.add(destino);
			}
		}		
		for (CorreoDestino destino : lstDestinos ) {
			destino.getPk().setIdCorreo(idCorreo);
			if (correo.getAuxTipoTransaccion().equals(EmailTransaccion.TIPO_TRANSACCION_LOGGER))
				destino.setEstado("L");
			else
				destino.setEstado("A");
			correoDestinoDao.registrar(destino);
		}
		
		// transaccion - correo adjunto
		Integer idAdjunto=1;
		for (CorreoAdjunto destino : correo.getListaCorreoAdjuntos() ) {
			destino.getPk().setIdCorreo(idCorreo);
			destino.getPk().setIdAdjunto(idAdjunto);
			correoAdjuntoDao.registrar(destino);
			idAdjunto++;
		}
		
		return correo;		
	}

	@Transactional
	public Correo coreActualizar(SeguridadUsuarioActual usuarioActual, Correo correo) throws UException {
		// valores por defecto - preparando objeto
		correo = validar.prepararActualizar(usuarioActual, correo);

		// validaciones de negocio
		List<DominioMensajeUsuario> lst = validar.coreActualizar(usuarioActual, correo);
		if (!lst.isEmpty())
			throw new UException(lst);

		// transaccion
		correo = correoDao.coreActualizar(correo);
		return correo;
	}

	@Transactional
	public Correo coreAnular(SeguridadUsuarioActual usuarioActual, Correo correo) throws UException {
		// valores por defecto - preparando objeto
		correo = validar.prepararAnular(usuarioActual, correo);

		// validaciones de negocio
		List<DominioMensajeUsuario> lst = validar.coreAnular(usuarioActual, correo);
		if (!lst.isEmpty())
			throw new UException(lst);

		// transaccion
		return correoDao.coreActualizar(correo);
	}

	public Correo coreAnular(SeguridadUsuarioActual usuarioActual, CorreoPk pk) throws UException {
		Correo bean = correoDao.obtenerPorId(pk);
		return coreAnular(usuarioActual, bean);
	}

	public Correo coreAnular(SeguridadUsuarioActual usuarioActual, Integer pidCorreo) throws UException {
		return coreAnular(usuarioActual, new CorreoPk(pidCorreo));
	}

	@Transactional
	public void coreEliminar(SeguridadUsuarioActual usuarioActual, Correo correo) throws UException {
		// valores por defecto - preparando objeto
		correo = validar.prepararEliminar(usuarioActual, correo);

		// validaciones de negocio
		List<DominioMensajeUsuario> lst = validar.coreEliminar(usuarioActual, correo);
		if (!lst.isEmpty())
			throw new UException(lst);

		// transaccion
		correoDao.eliminar(correo);
	}

	public void coreEliminar(SeguridadUsuarioActual usuarioActual, CorreoPk pk) throws UException {
		Correo correo = correoDao.obtenerPorId(pk);
		coreEliminar(usuarioActual, correo);
	}

	public void coreEliminar(SeguridadUsuarioActual usuarioActual, Integer pidCorreo) throws UException {
		coreEliminar(usuarioActual, new CorreoPk(pidCorreo));
	}

	public DominioPaginacion listarCorrreo(FiltroPaginacionCorreo filtro) throws Exception {
		logger.debug("listarCorrreo");
		List<DominioParametroPersistencia> parametros = new ArrayList<DominioParametroPersistencia>();
		Date dd=null;
		if (filtro.getFechaPreparacionInicio()!=null) {
			dd = UFechaHora.obtenerFechaHoraInicioDia(filtro.getFechaPreparacionInicio());
			filtro.setFechaPreparacionInicio(dd);
		}
		if (filtro.getFechaPreparacionFin()!=null) {
			dd = UFechaHora.obtenerFechaHoraInicioDia(filtro.getFechaPreparacionFin());
			filtro.setFechaPreparacionFin(dd);
		}
		
		if(UString.estaVacio(filtro.getProceso())) {
			filtro.setProceso(null);			
		}
		if(UString.estaVacio(filtro.getReferencia())) {
			filtro.setReferencia(null);		
		}
		if(UString.estaVacio(filtro.getReferenciaPadre())) {
			filtro.setReferenciaPadre(null);		
		}
		if(UString.estaVacio(filtro.getTransaccion())) {
			filtro.setTransaccion(null);
		}

		logger.debug(filtro.getIdreglanegocio());
		logger.debug(filtro.getIdcorreo());
		logger.debug(filtro.getFechaPreparacionInicio());
		logger.debug(filtro.getFechaPreparacionFin());
		logger.debug(filtro.getP_estado());
		logger.debug(filtro.getP_resumenlogalerta());
		logger.debug(filtro.getCorreodestino());		
		logger.debug(filtro.getReferenciaPadre());
		logger.debug(filtro.getReferenciaPrincipalId());
		
		filtro.getPaginacion().setPaginacionRegistrosPorPagina(50);
		parametros.add(new DominioParametroPersistencia("p_idreglanegocio", BigDecimal.class, filtro.getIdreglanegocio()));
		parametros.add(new DominioParametroPersistencia("idcorreo", BigDecimal.class, filtro.getIdcorreo()));
		parametros.add(new DominioParametroPersistencia("fechaPreparacionInicio", Date.class,filtro.getFechaPreparacionInicio()));
		parametros.add(new DominioParametroPersistencia("fechaPreparacionFin", Date.class, filtro.getFechaPreparacionFin()));
		parametros.add(new DominioParametroPersistencia("p_estado", String.class, filtro.getP_estado()));
		parametros.add(new DominioParametroPersistencia("p_resumenlogalerta", String.class, filtro.getP_resumenlogalerta()));
		parametros.add(new DominioParametroPersistencia("p_correodestino", String.class, filtro.getCorreodestino()));
		
		parametros.add(new DominioParametroPersistencia("p_proceso", String.class, filtro.getProceso()));
		parametros.add(new DominioParametroPersistencia("p_referencia", String.class, filtro.getReferencia()));
		parametros.add(new DominioParametroPersistencia("p_transaccion", String.class, filtro.getTransaccion()));
		parametros.add(new DominioParametroPersistencia("p_padrereferencia", String.class, filtro.getReferenciaPadre()));
		parametros.add(new DominioParametroPersistencia("p_principalreferencia", String.class, filtro.getReferenciaPrincipalId()));
		
		Integer registros = correoDao.contar("correo.contarPaginacion", parametros);
		List<?> datos = correoDao.listarConPaginacion(filtro.getPaginacion(), parametros, "correo.listarPaginacion",CorreoDto.class);
		filtro.getPaginacion().setPaginacionRegistrosPorPagina(50);
		filtro.getPaginacion().setPaginacionRegistrosEncontrados(registros.intValue());
		filtro.getPaginacion().setPaginacionListaResultado(datos);
		return filtro.getPaginacion();
	}
	
	public DominioPaginacion listarCorrreoAdministrador(FiltroPaginacionCorreo filtro) throws Exception {
		logger.debug("listarCorrreoAdministrador");
		List<DominioParametroPersistencia> parametros = new ArrayList<DominioParametroPersistencia>();
		Date dd=null;
		if (filtro.getFechaPreparacionInicio()!=null) {
			dd = UFechaHora.obtenerFechaHoraInicioDia(filtro.getFechaPreparacionInicio());
			filtro.setFechaPreparacionInicio(dd);
		}
		if (filtro.getFechaPreparacionFin()!=null) {
			dd = UFechaHora.obtenerFechaHoraInicioDia(filtro.getFechaPreparacionFin());
			filtro.setFechaPreparacionFin(dd);
		}
		
		if(UString.estaVacio(filtro.getProceso())) {
			filtro.setProceso(null);			
		}
		if(UString.estaVacio(filtro.getReferencia())) {
			filtro.setReferencia(null);		
		}
		if(UString.estaVacio(filtro.getReferenciaPadre())) {
			filtro.setReferenciaPadre(null);		
		}
		if(UString.estaVacio(filtro.getTransaccion())) {
			filtro.setTransaccion(null);
		}

		logger.debug(filtro.getIdreglanegocio());
		logger.debug(filtro.getIdcorreo());
		logger.debug(filtro.getFechaPreparacionInicio());
		logger.debug(filtro.getFechaPreparacionFin());
		logger.debug(filtro.getP_estado());
		logger.debug(filtro.getP_resumenlogalerta());
		logger.debug(filtro.getCorreodestino());		
		logger.debug(filtro.getReferenciaPadre());
		logger.debug(filtro.getReferenciaPrincipalId());
		
		filtro.getPaginacion().setPaginacionRegistrosPorPagina(50);
		parametros.add(new DominioParametroPersistencia("p_idreglanegocio", BigDecimal.class, filtro.getIdreglanegocio()));
		parametros.add(new DominioParametroPersistencia("idcorreo", BigDecimal.class, filtro.getIdcorreo()));
		parametros.add(new DominioParametroPersistencia("fechaPreparacionInicio", Date.class,filtro.getFechaPreparacionInicio()));
		parametros.add(new DominioParametroPersistencia("fechaPreparacionFin", Date.class, filtro.getFechaPreparacionFin()));
		parametros.add(new DominioParametroPersistencia("p_estado", String.class, filtro.getP_estado()));
		parametros.add(new DominioParametroPersistencia("p_resumenlogalerta", String.class, filtro.getP_resumenlogalerta()));
		parametros.add(new DominioParametroPersistencia("p_correodestino", String.class, filtro.getCorreodestino()));
		parametros.add(new DominioParametroPersistencia("p_proceso", String.class, filtro.getProceso()));
		parametros.add(new DominioParametroPersistencia("p_referencia", String.class, filtro.getReferencia()));
		parametros.add(new DominioParametroPersistencia("p_transaccion", String.class, filtro.getTransaccion()));
		parametros.add(new DominioParametroPersistencia("p_padrereferencia", String.class, filtro.getReferenciaPadre()));
		parametros.add(new DominioParametroPersistencia("p_principalreferencia", String.class, filtro.getReferenciaPrincipalId()));
		
		Integer registros = correoDao.contar("correo.contarPaginacionAdministrador", parametros);
		List<?> datos = correoDao.listarConPaginacion(filtro.getPaginacion(), parametros, "correo.listarPaginacionAdministrador",CorreoDto.class);
		filtro.getPaginacion().setPaginacionRegistrosPorPagina(50);
		filtro.getPaginacion().setPaginacionRegistrosEncontrados(registros.intValue());
		filtro.getPaginacion().setPaginacionListaResultado(datos);
		return filtro.getPaginacion();
	}

	@Transactional
	public Correo actualizarEstadosSeleccionadosServ(Correo correo) throws UException {
		int id = correo.getPk().getIdCorreo();
		Correo correoBean = new Correo();
		correoBean.getPk().setIdCorreo(id);
		correoBean = correoDao.obtenerPorId(correoBean.getPk());
		correoBean.setEstado(correo.getEstado());
		correo = correoDao.coreActualizar(correoBean);

		logger.debug(correo.getEstado());
		if (correo.getEstado().equals("PEN")) {
			List<CorreoDestino> lst = correDestinooDao.listarPorIdCorreoTodos(id);
			for (CorreoDestino correoDestino : lst) {
				correoDestino.setEstado("A");
				correDestinooDao.actualizar(correoDestino);
			}	
		}		
		
		return correo;
	}

	@SuppressWarnings({ "rawtypes", "unchecked" })
	public Boolean ConfigurarCorreoRegular(Correo beanCoreo) {
		List<DominioParametroPersistencia> parametros = new ArrayList<DominioParametroPersistencia>();
		parametros.add(new DominioParametroPersistencia("id_correo", BigDecimal.class,
				new BigDecimal(beanCoreo.getPk().getIdCorreo().intValue())));

		List datos = correDestinooDao.listarPorQuery(CorreoDestinoCore.class, "correodestino.enviarlistarPaginacion",
				parametros);

		CorreoCore dataCorrero = new CorreoCore();
		dataCorrero.setListaCorreoDestino(datos);

		// Obtener El cuerpo
		CorreoCuerpo beanCorrreoCuerpo = new CorreoCuerpo();
		beanCorrreoCuerpo.getPk().setIdCorreo(beanCoreo.getPk().getIdCorreo());
		beanCorrreoCuerpo = correoCuerpoDao.obtenerSinConfirmarPorId(beanCorrreoCuerpo.getPk());

		byte[] bynary = beanCorrreoCuerpo.getCuerpoCorreo();

		// Obtner La configuracion del Correo.
		ConfiguracionServicio bean = new ConfiguracionServicio();
		Integer id = 1;
		bean.getPk().setIdConfiguracionServicio(id);
		bean = configuracionServicioDao.obtenerSinConfirmarPorId(bean.getPk());

		Properties properties = new Properties();
		String perfilCadena;
		final String correo;
		final String clave;
		correo = bean.getEmailCuenta();
		clave = bean.getEmailClave();
		perfilCadena = bean.getEmailPerfil();
		bean.setEmailClave(clave);
		bean.setEmailCuenta(correo);

		InputStream finDoc = new ByteArrayInputStream(perfilCadena.getBytes());
		try {
			properties.load(finDoc);
		} catch (IOException e1) {
			e1.printStackTrace();
		}

		sessionCorreo = Session.getInstance(properties, new jakarta.mail.Authenticator() {
			protected PasswordAuthentication getPasswordAuthentication() {
				return new PasswordAuthentication(correo, clave);
			}
		});

		try {

			for (CorreoDestinoCore b : dataCorrero.getListaCorreoDestino()) {

				// LOGGER.debug("enviando el correo");

				Message message = new MimeMessage(sessionCorreo);

				message.setFrom(new InternetAddress(correo, "SEDALIB"));

				logger.debug("Correo Remitente:" + correo);

				// message.setSentDate(new Date());
				// LOGGER.debug("verificando que tipo de envio se realizara");
				logger.debug("CORREO DESTINO -PRUEBA:" + bean.getCorreoPrueba());
				message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(b.getCorreoDestino()));

				// LOGGER.debug("preparando el contenido del correo");
				String cuerpoCorreo = new String(bynary, StandardCharsets.UTF_8);
				message.setSubject("Correo Pruebas Regular");
				message.setContent(cuerpoCorreo, "text/html;charset=ISO-8859-1");
				// LOGGER.debug("cuerpoCorreo " + cuerpoCorreo);

				Transport.send(message);
				logger.debug("transporte enviando el correo");
			}
			return true;

		} catch (Exception e) {
			e.printStackTrace();
			return true;
		}

	}

	public EmailTransaccion ConfigurarCorreo(Correo pcorreo) throws Exception {
		// Obtener El cuerpo
		logger.debug("ConfigurarCorreo");
		
		Correo beanCoreo = correoDao.obtenerPorId(pcorreo.getPk().getIdCorreo());
		
		logger.debug(beanCoreo.getPk().getIdCorreo());
		logger.debug(beanCoreo.getIdReglaNegocio());
		
		SeguridadUsuarioActual usu = this.getUsuarioActual();
		ReglaNegocioTaskDto rn=reglaNegocioDao.listarPorReglaParaTask(beanCoreo.getIdReglaNegocio());
		EmailConfiguracion config = correoDao.obtenerConfiguracionBd(null,this.serverNombre,CorreoDaoImpl.PERFIL_POR_DEFECTO);
		EmailTransaccion et = alertaServicio.EnvioCorreo(config,usu,beanCoreo.getPk().getIdCorreo(),rn);
		return et;
	}
	
	public List<CorreoDto> validarEnviosCorreos(List<DtoTabla> lista) throws Exception {
		logger.debug("validarEnviosCorreos");
		
		List<CorreoDto> resultado = new ArrayList<CorreoDto>();
		
		for (DtoTabla d : lista) {
			List<DominioParametroPersistencia> parametros = new ArrayList<DominioParametroPersistencia>();
			parametros.add(new DominioParametroPersistencia("p_referencia", String.class, d.getCodigo() ));		
			
			List<?> datos = correoDao.listarPorQuery(CorreoDto.class, "correo.validarEnviosCorreos", parametros);
			
			List<CorreoDto> resp = (List<CorreoDto>) datos;
			
			resultado.addAll(resp);
		}
		
		return resultado;
	}

}
