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
import net.royal.spring.alertas.dominio.dto.ReglaNegocioTransaccion;
import net.royal.spring.framework.core.UException;
import net.royal.spring.framework.modelo.ErrorTransaccion;

public class RegistrarEjecucionLogHilo extends Thread{
	private static Logger logger = LogManager.getLogger(RegistrarEjecucionLogHilo.class);

	private String apiAlertas;
	private HttpHeaders httpHeaders;
	
	private String vjavaLog = null; 
	private String vspLog = null;
	private String vresumenLog = null;
	private Integer vreglaNegocioId = null;
	private Integer vregistrosExito = null;
	private Integer vregistrosError = null;
	
	public RegistrarEjecucionLogHilo(String apiAlertas,HttpHeaders httpHeaders, String javaLog, String spLog,String resumenLog,Integer reglaNegocioId,Integer registrosExito,Integer registrosError) {
		this.apiAlertas = apiAlertas;
		this.httpHeaders = httpHeaders; 
		
		vjavaLog = javaLog;
		vspLog = spLog;
		vresumenLog = resumenLog;
		vreglaNegocioId = reglaNegocioId;
		vregistrosExito = registrosExito;		
		vregistrosError = registrosError;
	}
	
	public RegistrarEjecucionLogHilo(String apiAlertas,HttpHeaders httpHeaders, String javaLog, String spLog,String resumenLog,Integer reglaNegocioId) {
		this.apiAlertas = apiAlertas;
		this.httpHeaders = httpHeaders; 		
		vjavaLog = javaLog;
		vspLog = spLog;
		vresumenLog = resumenLog;
		vreglaNegocioId = reglaNegocioId;		
	}

	public RegistrarEjecucionLogHilo(String apiAlertas,HttpHeaders httpHeaders, String javaLog, String spLog,String resumenLog) {
		this.apiAlertas = apiAlertas;
		this.httpHeaders = httpHeaders; 
		vjavaLog = javaLog;
		vspLog = spLog;
		vresumenLog = resumenLog;
	}
	
	/*public RegistrarEjecucionLogHilo(String apiAlertas,HttpHeaders httpHeaders, DtoEjecucionlog bean1) {
		this.apiAlertas = apiAlertas;
		this.bean = bean1;
		this.httpHeaders = httpHeaders; 
	}*/

	@Override
	public void run() {
		URI uri = null;
		String ss = "spring/alertas/ejecucionlog/registrar";
		try {
			DtoEjecucionlog bean = new DtoEjecucionlog();
			bean.setJavalog(vjavaLog);
			bean.setSplog(vspLog);
			bean.setResumenlog(vresumenLog);
			bean.setReglaNegocioId(vreglaNegocioId);
			bean.setRegistrosExito(vregistrosExito);
			bean.setRegistrosError(vregistrosError);
			
			RestTemplate restTemplate = new RestTemplate();
			uri = new URI(apiAlertas + ss);
			//logger.debug(uri);
			HttpEntity<DtoEjecucionlog> request = new HttpEntity<DtoEjecucionlog>(bean, httpHeaders);
			//logger.debug(uri);
			ResponseEntity<DtoEjecucionlog> result = restTemplate.exchange(uri, HttpMethod.POST, request,DtoEjecucionlog.class);
			//logger.debug(uri + " despues");
			//logger.debug(result.getBody().getTransaccionEstado());
			//logger.debug(result.getBody().getTransaccionMensajesCadena());
		} catch (Exception e) {
			logger.error("RegistrarEjecucionLogHilo run():"+e.getMessage());
			logger.error(uri);
			logger.error(UException.getStackTrace(e));
		}
	}
}
