package net.royal.spring.alertas.boot;

import net.royal.spring.framework.web.constante.ConstanteBoot;

public class SpringAlertasConstanteBoot {
	
	public static final String APLICACION_CODIGO = "SY";
	public static final String NOMBRE = "erp.ma";

	public static final Integer TOKEN_MINUTES = 600;

	public static final String TIPO_REPORTE_POWERBUILDER = "POWER";
	public static final String TIPO_REPORTE_JASPER = "JASPE";
	public static final String TIPO_FORMATO_HTML = "HTML";

	public static final String PARAMETRO_RUTA_REPORTES_WEB = "RUTAREPWEB";

	public static final String PROCESO_EVALUAR_REGLANEGOCIO = "PROCESO_EVALUAR_REGLANEGOCIO";
	public static final String PROCESO_EXTRAER_DATA = "PROCESO_EXTRAER_DATA";
	public static final String PROCESO_CREAR_ALERTA = "PROCESO_CREAR_ALERTA";
	public static final String PROCESO_CREAR_CORREO = "PROCESO_CREAR_CORREO";
	public static final String PROCESO_ENVIO_CORREO = "PROCESO_ENVIO_CORREO";

	public static final String CORREO_ESTADO_ENVIADO = "ENV";
	public static final String CORREO_ESTADO_PENDIENTE = "PEN";
	public static final String CORREO_ESTADO_ANULADO = "ANU";
	public static final String CORREO_ESTADO_ERRORENVIO = "EER";

	public static final String ERROR_ESTADO_ACTIVO = "ACT";
	public static final String ERROR_ESTADO_ELIMINADO = "ELI";

	public static final String CORREO_TIPO_CONFIGURACION_CLASE = "CLASE";
	public static final String CORREO_TIPO_CONFIGURACION = "CORREO.TIPO_CONFIGURACION";
	public static final String CORREO_TIPO_CONFIGURACION_ARCHIVO = "ARCHIVO";
	public static final String CORREO_TIPO_CONFIGURACION_BDPARAMETROS = "BDPARAMETROS";
	public static final String CORREO_TIPO_CONFIGURACION_BDFUNCION = "BDFUNCION";

	public enum MODO_INICIO {
		REST_WEB, SPRING_TASK_REST, NINGUNO
	}

	public enum CONSTANTE_PROCESO {
		REST_WEB, MODO_FACTORY, NINGUNO
	}

	public enum TIPO_CONTROL_MOTOR {
		SPRING, BASEDATOS
	}

	public static final String MOTOR_REGLAS_TERMINAL = "SRV-WEBLOGIC-HILO";
	public static final String MOTOR_REGLAS_USUARIO = "USUARIO-HILO";
	public static final String VERSION = "1.4";

	public static final boolean BDLock = false;
	
}
