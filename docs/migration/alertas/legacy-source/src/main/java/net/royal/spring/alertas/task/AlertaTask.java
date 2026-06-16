package net.royal.spring.alertas.task;

import java.util.UUID;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import net.royal.spring.alertas.dao.impl.ConfiguracionServicioDaoImpl;
import net.royal.spring.alertas.dominio.ConfiguracionServicio;
import net.royal.spring.alertas.dominio.ConfiguracionServicioPk;
import net.royal.spring.alertas.servicio.impl.AlertaServicioImpl;
import net.royal.spring.alertas.servicio.impl.CorreoServicioImpl;
import net.royal.spring.framework.modelo.seguridad.SeguridadUsuarioActual;

@Component
public class AlertaTask {
	private static Logger logger = LogManager.getLogger(AlertaTask.class);
	
	private static ConfiguracionServicio cfg = null;

	@Autowired
	private AlertaServicioImpl alertaServicioImpl;

	@Autowired
	private ConfiguracionServicioDaoImpl configuracionServicioDao;

	/**
	 * / LOGGER DE REGLAS 2
	 * @throws Exception 
	 */
	@Transactional
	@Scheduled(fixedRate = 60000, initialDelay = 60000)
	public void EvaluarReglaNegocioTask() throws Exception {
		/**
		 * INSERTAR AQUI 001/002 
		 * SELECT * FROM SGALERTASSYS.FUENTE_ALERTA 
		 * SELECT * FROM SGALERTASSYS.FUENTE_ALERTA_ADICIONAL 
		 * SELECT * FROM SGALERTASSYS.FUENTE_ALERTA_DETALLE 
		 * SELECT * FROM SGALERTASSYS.FUENTE_HISTORIA
		 */
		
		cfg = this.getConfiguracionServicio();
		if (cfg != null) {
			if (cfg.getAuxFlgIniciarServicios().equals("S") && cfg.getFlgEvaluarRegla().equals("S")) {
				String tag = UUID.randomUUID().toString().substring(1, 8)+" | ";
				alertaServicioImpl.setCfg(cfg);
				alertaServicioImpl.registrarEjecucionLog("EvaluarReglaNegocioTask", null, tag+" EvaluarReglaNegocioTask()");				
				alertaServicioImpl.evaluarReglaNegocioHilo(getUsuarioActual(),tag);
			}
		}
	}

	/**
	 * / LOGGER DE REGLAS 2
	 * @throws Exception 
	 */
	@Transactional
	@Scheduled(fixedRate = 65000, initialDelay = 90000)
	public void ExtraerDataTask() throws Exception {
		/**
		 * INSERTAR AQUI (003) 
		 * 
		 * SGALERTASSYS.SP_EXTRAER_DATA_FUENTE
		 * 			SGALERTASSYS.SP_LOG_ALERTA_REGISTRAR
		 * 				INSERT INTO SGALERTASSYS.LOG_ALERTA
		 * 				INSERT INTO SGALERTASSYS.LOG_DETALLE
		 * 				INSERT INTO SGALERTASSYS.LOG_ADICIONAL
		 * 		DELETE FROM SGALERTASSYS.FUENTE_ALERTA_ADICIONAL WHERE ID_FUENTE_ALERTA = @V_ID_FUENTE_ALERTA
		 * 		DELETE FROM SGALERTASSYS.FUENTE_ALERTA_DETALLE WHERE ID_FUENTE_ALERTA = @V_ID_FUENTE_ALERTA
		 * 		DELETE FROM SGALERTASSYS.FUENTE_ALERTA WHERE ID_FUENTE_ALERTA = @V_ID_FUENTE_ALERTA
		 */
		if (cfg != null) {
			if (cfg.getAuxFlgIniciarServicios().equals("S") && cfg.getFlgExtraerData().equals("S")) {
				String tag = UUID.randomUUID().toString().substring(1, 8)+" | ";
				alertaServicioImpl.setCfg(cfg);
				alertaServicioImpl.registrarEjecucionLog("ExtraerDataTask", null, tag+" ExtraerDataTask()");				
				alertaServicioImpl.ExtraerData(getUsuarioActual(),tag);
			}
		}
	}

	/*
	 * Despues de 70seg cada 90seg enviar el correo / LOGGER DE REGLAS 2
	 */
	@Transactional
	@Scheduled(fixedRate = 70000, initialDelay = 90000)
	public void crearAlertaTask() throws Exception {
		/**
		 * 004 
		 * SELECT * FROM SGALERTASSYS.ALERTA; 
		 * SELECT * FROM SGALERTASSYS.ALERTA_ADICIONAL; 
		 * SELECT * FROM SGALERTASSYS.ALERTA_DESTINO;
		 * SELECT * FROM SGALERTASSYS.ALERTA_DETALLE; 
		 * SELECT * FROM SGALERTASSYS.ALERTA_PLAN; 
		 * 
		 * CALL SGALERTASSYS.SP_LOG_GENERAR_ALERTA
		 * 					 EXEC SGALERTASSYS.SP_ALERTA_REGISTRAR
		 * 					 EXEC xxx.dbo.SP_ERROR_REGISTRAR
		 * 					 EXEC SGALERTASSYS.SP_LOG_PREPARAR_ALERTA 
		 */
		/**
		 * public void crearAlertaHilo(SeguridadUsuarioActual usuarioActual,String tag,String ddd) throws UException {
		 * 		if (flgLogGenerarAlerta){
		 * 			REGLA_NEGOCIO.FLG_LOG_GENERAR_ALERTA='S'
		 * 
		 * 			public void LogGenerarAlerta(SeguridadUsuarioActual usuarioActual, Integer idReglaNegocio) throws UException {
		 * 				spring/alertas/reglanegocio/loggeneraralerta
		 * 						EXEC SGALERTASSYS.SP_LOG_GENERAR_ALERTA :p_id_regla_negocio,:p_usuario,:p_terminal
		 * 						REGLA_NEGOCIO.FLG_LOG_GENERAR_ALERTA='N'
		 * 		}
		 */
		if (cfg != null) {
			if (cfg.getAuxFlgIniciarServicios().equals("S") && cfg.getFlgCrearAlerta().equals("S")) {
				String tag = UUID.randomUUID().toString().substring(1, 8)+" | ";
				alertaServicioImpl.setCfg(cfg);
				alertaServicioImpl.registrarEjecucionLog("crearAlertaTask", null, tag+" crearAlertaTask()");				
				alertaServicioImpl.crearAlertaHilo(getUsuarioActual(),tag);
			}
		}
	}

	/*	
	 * Despues de 75seg cada 90seg enviar el correo	/ LOGGER DE REGLAS 2
	 */
	@Transactional
	@Scheduled(fixedRate = 75000, initialDelay = 90000)
	public void CrearCorreoTask() throws Exception {
		/**
		 * 005 
		 * SELECT * FROM SGALERTASSYS.CORREO 
		 * SELECT * FROM SGALERTASSYS.CORREO_ADJUNTO 
		 * SELECT * FROM SGALERTASSYS.CORREO_CUERPO 
		 * SELECT * FROM SGALERTASSYS.CORREO_DESTINO
		 */
		if (cfg != null) {
			if (cfg.getAuxFlgIniciarServicios().equals("S") && cfg.getFlgCrearCorreo().equals("S")) {
				logger.debug("");
				String tag = UUID.randomUUID().toString().substring(1, 8)+" | ";
				alertaServicioImpl.setCfg(cfg);
				alertaServicioImpl.registrarEjecucionLog("CrearCorreoTask", null, tag + " CrearCorreoTask()");				
				alertaServicioImpl.crearCorreoHilo(getUsuarioActual(),tag);
			}
		}
	}

	/*
	 * cada 75seg(75000) enviar correo, iniciar despues de 90seg(90000) 	/ LOGGER DE REGLAS 2
	 */
	@Transactional
	@Scheduled(fixedRate = 75000, initialDelay = 90000)
	public void EnvioCorreoTask() throws Exception {
		/**
		 * 006 ENVIADO EL CORREO
		 */
		if (cfg != null) {
			if (cfg.getAuxFlgIniciarServicios().equals("S") && cfg.getFlgEnviarCorreo().equals("S")) {
				String tag = UUID.randomUUID().toString().substring(1, 8)+" | ";
				alertaServicioImpl.setCfg(cfg);
				alertaServicioImpl.registrarEjecucionLog("EnvioCorreoTask", null, tag + " EnvioCorreoTask()");				
				alertaServicioImpl.envioCorreoMasivoHilo(getUsuarioActual(),tag);
			}
		}
	}
	
	/*
	 * Despues de 30seg cada 25seg enviar el correo	/ LOGGER DE REGLAS 2
	 */	
	@Transactional
	@Scheduled(initialDelay = 60000, fixedRate = 25000)
	public void EnvioCorreoGenericoTask() throws Exception {
		/**
		 * 007 ENVIADO EL CORREO GENERICO
		 */
		if (cfg != null) {
			if (cfg.getAuxFlgIniciarServicios().equals("S") && cfg.getFlgEnviarCorreoGenerico().equals("S")) {
				String tag = UUID.randomUUID().toString().substring(1, 8)+" | ";
				alertaServicioImpl.setCfg(cfg);
				alertaServicioImpl.registrarEjecucionLog("EnvioCorreoGenericoTask", null, tag+" EnvioCorreoGenericoTask()");
				alertaServicioImpl.envioCorreoMasivoHiloGenerico(getUsuarioActual(),tag);
			}
		}
	}

	public SeguridadUsuarioActual getUsuarioActual() {
		SeguridadUsuarioActual usu = new SeguridadUsuarioActual();
		usu.setDireccionIp("MOTOR-TASK-TERM");
		usu.setUsuario("MOTOR-TASK-USU");		
		return usu;
	}

	public ConfiguracionServicio getConfiguracionServicio() {
		cfg = configuracionServicioDao.obtenerSinConfirmarPorId(new ConfiguracionServicioPk(1));
		cfg.setAuxFlgIniciarServicios(cfg.getFlgIniciarServicios());
		cfg.setAuxFlgLogBaseDatos(cfg.getFlgLogBaseDatos());
		/*************************/
		/*cfg.setAuxFlgIniciarServicios("S");
		cfg.setAuxFlgLogBaseDatos("S");
		cfg.setFlgEvaluarRegla("S");
		cfg.setFlgExtraerData("S");
		cfg.setFlgCrearAlerta("S");
		cfg.setFlgCrearCorreo("S");
		cfg.setFlgEnviarCorreo("S");
		cfg.setFlgEnviarCorreoGenerico("S");*/
		return cfg;
	}
}
