package net.royal.spring.alertas.servicio.impl;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;
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
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import net.royal.spring.alertas.dao.impl.ConfiguracionServicioDaoImpl;
import net.royal.spring.alertas.dominio.ConfiguracionServicio;
import net.royal.spring.alertas.dominio.ConfiguracionServicioPk;
import net.royal.spring.alertas.dominio.dto.ConfiguracionServicioDto;
import net.royal.spring.alertas.servicio.validar.ConfiguracionServicioServicioValidar;
import net.royal.spring.framework.core.UException;
import net.royal.spring.framework.modelo.correo.EmailDestino;
import net.royal.spring.framework.modelo.correo.EmailTransaccion;
import net.royal.spring.framework.modelo.generico.DominioMensajeUsuario;
import net.royal.spring.framework.modelo.generico.DominioTransaccion;
import net.royal.spring.framework.modelo.seguridad.SeguridadUsuarioActual;
import net.royal.spring.framework.web.servicio.impl.GenericoServicioImpl;

@Service(value = "BeanServicioConfiguracionServicio")
public class ConfiguracionServicioServicioImpl extends GenericoServicioImpl {
	public static String SPRING_NOMBRE = "BeanServicioConfiguracionServicio";
	private static Logger logger = LogManager.getLogger(ConfiguracionServicioServicioImpl.class);

	@Autowired
	private ConfiguracionServicioDaoImpl configuracionServicioDao;

	@Autowired
	private ConfiguracionServicioServicioValidar validar;
	private Session sessionCorreo;

	@Transactional
	public ConfiguracionServicio coreInsertar(SeguridadUsuarioActual usuarioActual,
			ConfiguracionServicio configuracionServicio) throws UException {
		// valores por defecto - preparando objeto
		configuracionServicio = validar.prepararInsertar(usuarioActual, configuracionServicio);

		// validaciones de negocio
		List<DominioMensajeUsuario> lst = validar.coreInsertar(usuarioActual, configuracionServicio);
		if (!lst.isEmpty())
			throw new UException(lst);

		// transaccion
		return configuracionServicioDao.coreInsertar(configuracionServicio);
	}

	@Transactional
	public ConfiguracionServicio coreActualizar(SeguridadUsuarioActual usuarioActual,
			ConfiguracionServicio configuracionServicio) throws UException {
		// valores por defecto - preparando objeto
		configuracionServicio = validar.prepararActualizar(usuarioActual, configuracionServicio);

		// validaciones de negocio
		List<DominioMensajeUsuario> lst = validar.coreActualizar(usuarioActual, configuracionServicio);
		if (!lst.isEmpty())
			throw new UException(lst);

		// transaccion
		configuracionServicio = configuracionServicioDao.coreActualizar(configuracionServicio);
		return configuracionServicio;
	}

	@Transactional
	public ConfiguracionServicio coreAnular(SeguridadUsuarioActual usuarioActual,
			ConfiguracionServicio configuracionServicio) throws UException {
		// valores por defecto - preparando objeto
		configuracionServicio = validar.prepararAnular(usuarioActual, configuracionServicio);

		// validaciones de negocio
		List<DominioMensajeUsuario> lst = validar.coreAnular(usuarioActual, configuracionServicio);
		if (!lst.isEmpty())
			throw new UException(lst);

		// transaccion
		return configuracionServicioDao.coreActualizar(configuracionServicio);
	}

	public ConfiguracionServicio coreAnular(SeguridadUsuarioActual usuarioActual, ConfiguracionServicioPk pk)
			throws UException {
		ConfiguracionServicio bean = configuracionServicioDao.obtenerPorId(pk);
		return coreAnular(usuarioActual, bean);
	}

	public ConfiguracionServicio coreAnular(SeguridadUsuarioActual usuarioActual, Integer pidConfiguracionServicio)
			throws UException {
		return coreAnular(usuarioActual, new ConfiguracionServicioPk(pidConfiguracionServicio));
	}

	@Transactional
	public void coreEliminar(SeguridadUsuarioActual usuarioActual, ConfiguracionServicio configuracionServicio)
			throws UException {
		// valores por defecto - preparando objeto
		configuracionServicio = validar.prepararEliminar(usuarioActual, configuracionServicio);

		// validaciones de negocio
		List<DominioMensajeUsuario> lst = validar.coreEliminar(usuarioActual, configuracionServicio);
		if (!lst.isEmpty())
			throw new UException(lst);

		// transaccion
		configuracionServicioDao.eliminar(configuracionServicio);
	}

	public void coreEliminar(SeguridadUsuarioActual usuarioActual, ConfiguracionServicioPk pk) throws UException {
		ConfiguracionServicio configuracionServicio = configuracionServicioDao.obtenerPorId(pk);
		coreEliminar(usuarioActual, configuracionServicio);
	}

	public void coreEliminar(SeguridadUsuarioActual usuarioActual, Integer pidConfiguracionServicio) throws UException {
		coreEliminar(usuarioActual, new ConfiguracionServicioPk(pidConfiguracionServicio));
	}

	// Metodos adicionales

	@Transactional
	public ConfiguracionServicio guardarConfiguracion(ConfiguracionServicio configuracionServicio) throws UException {
		configuracionServicioDao.registrar(configuracionServicio);
		return configuracionServicio;
	}

	@Transactional
	public ConfiguracionServicio actualizarConfiguracion(ConfiguracionServicio configuracionServicio)
			throws UException {
		configuracionServicioDao.actualizar(configuracionServicio);
		return configuracionServicio;
	}

	@SuppressWarnings("rawtypes")
	@Transactional
	public List listarConfiguracion() {
		List<?> datos = configuracionServicioDao.listarPorQuery(ConfiguracionServicioDto.class,
				"configuracionservicio.lista", null);
		return datos;
	}

	@Transactional
	public EmailTransaccion enviarCorreo(ConfiguracionServicio conf) throws Exception {

		// generar el formato html nuevo
		/*ReporteParametro parametro = new ReporteParametro();
		parametro.setAplicacionCodigo("SL");
		parametro.setReporteCodigo("246");
		parametro.getParametros().put("p_numero", "Pruebas");
		ReporteResultado repRes = this.reporteEjecutar(parametro);
		if (repRes.getEstado().equals(ReporteResultado.ERROR)) {*/
			// throw new UException((List<DominioMensajeUsuario>)
			// this.getMsjUsuarioError("usuario.nuevaclave.errorreporte"));
//			 			userresponse.setStatus(1);
//			 			userresponse.setMensajeuser("Error al generar la clave");
//			 			return new ResponseEntity<UsuarioBean>(userresponse,HttpStatus.OK);
		//}

		// enviar por correo
		EmailTransaccion correo = new EmailTransaccion();
		//correo.setAsunto(repRes.getAsunto());
		//correo.setCuerpoCorreo(repRes.getReporteCuerpo());
		//correo.getListaCorreoDestino().add(new EmailDestino("veraraul495@gmail.com"));

		correo = this.correoEnviar(correo);
		if (correo.getTransaccionEstado().equals(DominioTransaccion.ERROR)) {
			// throw new UException((List<DominioMensajeUsuario>)
			// this.getMsjUsuarioError("usuario.nuevaclave.errorcorreo"));

//		 			userresponse.setStatus(2);
//		 			userresponse.setMensajeuser("Error al enviar la clave al correo electr\u00f3nico");
//		 			return new ResponseEntity<UsuarioBean>(userresponse,HttpStatus.OK);
		}
		return correo;
	}

	public byte[] cuerpoCorreo() {
		String cuerpo = "<!DOCTYPE html>\r\n" + "<html lang=\"es\">\r\n" + "   <head>\r\n"
				+ "      <meta charset=\"UTF-8\"/>\r\n"
				+ "      <style type='text/css'>div#alerta {background-color: rgb(COLORRGB);}table{border-color: #000000;background-color:  #000000;font-size: small; }th{border-color: #000000;background-color: #4BACC6; }td{border-color: #000000;background-color: #FFFFFF;}</style>\r\n"
				+ "   </head>\r\n" + "   <body>" + " <h2>\r\n" + "         Correo de Pruebas\r\n" + "      </h2>"
				+ "</body</html>";

		return cuerpo.getBytes();

	}

	public Boolean ConfigurarCorreo() throws IOException {

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
		properties.load(finDoc);

		sessionCorreo = Session.getInstance(properties, new jakarta.mail.Authenticator() {
			protected PasswordAuthentication getPasswordAuthentication() {
				return new PasswordAuthentication(correo, clave);
			}
		});

		try {
			// LOGGER.debug("enviando el correo");

			Message message = new MimeMessage(sessionCorreo);

			message.setFrom(new InternetAddress(correo, "SEDALIB"));

			logger.debug("Correo Remitente:" + correo);

			// message.setSentDate(new Date());

			// LOGGER.debug("verificando que tipo de envio se realizara");
			//LEONARDO CODE:
			
			String[] strarray = bean.getCorreoPrueba().split(",");
			for (String string : strarray) {
				
			
				logger.debug("CORREO DESTINO -PRUEBA:" + string);
				message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(string));
	
				byte[] cuerpoCorreoFuncion = this.cuerpoCorreo();
	
				// LOGGER.debug("preparando el contenido del correo");
				String cuerpoCorreo = new String(cuerpoCorreoFuncion, StandardCharsets.UTF_8);
				message.setSubject("Correo Pruebas");
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

}
