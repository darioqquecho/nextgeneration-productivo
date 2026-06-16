package net.royal.spring.alertas.servicio.hilo;

import java.net.URI;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import net.royal.spring.alertas.dominio.dto.DtoEjecucionlog;
import net.royal.spring.framework.core.UException;
import net.royal.spring.framework.modelo.generico.dto.DtoTabla;

public class ConfiguracionServicioCrearCorreoHilo extends Thread {
	private static Logger logger = LogManager.getLogger(ConfiguracionServicioCrearCorreoHilo.class);
	private String apiAlertas;
	private HttpHeaders httpHeaders;
	private String vestado;
	
	public ConfiguracionServicioCrearCorreoHilo(String apiAlertas,HttpHeaders httpHeaders,String estado) {
		this.apiAlertas = apiAlertas;
		this.httpHeaders = httpHeaders;
		vestado = estado;
	}
	
	@Override
	public void run() {
		URI uri = null;
		String ss = "spring/alertas/configuracionservicio/crearcorreo";
		try {
			DtoTabla bean = new DtoTabla();
			bean.setEstado(vestado);
			
			RestTemplate restTemplate = new RestTemplate();
			uri = new URI(apiAlertas + ss);
			//logger.debug(uri);
			HttpEntity<DtoTabla> request = new HttpEntity<DtoTabla>(bean, httpHeaders);
			//logger.debug(uri);
			ResponseEntity<DtoTabla> result = restTemplate.exchange(uri, HttpMethod.POST, request,DtoTabla.class);
			//logger.debug(uri + " despues");
			//logger.debug(result.getBody().getTransaccionEstado());
			//logger.debug(result.getBody().getTransaccionMensajesCadena());
		} catch (Exception e) {
			logger.error("ConfiguracionServicioCrearCorreoHilo run():"+e.getMessage());
			logger.error(uri);
			logger.error(UException.getStackTrace(e));
		}
	}
	
}
