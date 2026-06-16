package net.royal.spring.alertas.servicio.hilo;

import java.net.URI;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import net.royal.spring.alertas.dominio.dto.ReglaNegocioTransaccion;
import net.royal.spring.framework.core.UException;
import net.royal.spring.framework.modelo.ErrorTransaccion;

public class ReglaNegocioLogGenerarAlertaHilo extends Thread{
	private static Logger logger = LogManager.getLogger(ReglaNegocioLogGenerarAlertaHilo.class);

	private String apiAlertas;
	private ReglaNegocioTransaccion bean;
	private HttpHeaders httpHeaders;

	public ReglaNegocioLogGenerarAlertaHilo(String apiAlertas, ReglaNegocioTransaccion bean1, HttpHeaders httpHeaders) {
		this.apiAlertas = apiAlertas;
		this.bean = bean1;
		this.httpHeaders = httpHeaders; 
	}

	@Override
	public void run() {
		URI uri = null;
		String ss = "spring/alertas/reglanegocio/actualizarflggeneraralerta";
		try {
			RestTemplate restTemplate = new RestTemplate();
			uri = new URI(apiAlertas + ss);
			//logger.debug(uri);
			HttpEntity<ReglaNegocioTransaccion> request = new HttpEntity<ReglaNegocioTransaccion>(bean, httpHeaders);
			ResponseEntity<ReglaNegocioTransaccion> result = restTemplate.exchange(uri, HttpMethod.POST, request,ReglaNegocioTransaccion.class);
			//logger.debug(result.getBody().getTransaccionEstado());
			//logger.debug(result.getBody().getTransaccionMensajesCadena());
		} catch (Exception e) {
			logger.error("ReglaNegocioHilo run():"+e.getMessage());
			logger.error(uri);
			logger.error(UException.getStackTrace(e));
		}
	}
}
