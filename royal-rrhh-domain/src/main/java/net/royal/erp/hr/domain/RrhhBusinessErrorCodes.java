package net.royal.erp.hr.domain;

/**
 * Catalogo central de codigos funcionales expuestos por la API de RRHH.
 */
public final class RrhhBusinessErrorCodes {
	public static final String CAPACITACION_CODIGO_REQUERIDO = "CAP-001";
	public static final String CAPACITACION_NOMBRE_REQUERIDO = "CAP-002";
	public static final String CAPACITACION_FECHAS_INVALIDAS = "CAP-003";
	public static final String CAPACITACION_DUPLICADA = "CAP-004";
	public static final String CAPACITACION_V2_INSTRUCTOR_REQUERIDO = "CAP-V2-001";

	public static final String PARAMETRO_NOMBRE_MUY_LARGO = "HR-PAR-002";
	public static final String PARAMETRO_ESTADO_REQUERIDO = "HR-PAR-003";
	public static final String PARAMETRO_DUPLICADO = "HR-PAR-004";
	public static final String PARAMETRO_USUARIO_MUY_LARGO = "HR-PAR-005";
	public static final String PARAMETRO_NO_ENCONTRADO = "HR-PAR-404";
	public static final String PARAMETRO_APROBACION_MASIVA_VACIA = "HR-PAR-APR-001";
	public static final String PARAMETRO_REPORTE_PLANTILLA_NO_ENCONTRADA = "HR-PAR-REP-001";
	public static final String PARAMETRO_REPORTE_ERROR_JASPER = "HR-PAR-REP-002";
	public static final String PARAMETRO_REPORTE_ERROR_IO = "HR-PAR-REP-003";

	public static final String TIPO_SEGURO_DESCRIPCION_MUY_LARGA = "HR-TSG-002";
	public static final String TIPO_SEGURO_ESTADO_MUY_LARGO = "HR-TSG-003";
	public static final String TIPO_SEGURO_DUPLICADO = "HR-TSG-004";
	public static final String TIPO_SEGURO_USUARIO_MUY_LARGO = "HR-TSG-005";
	public static final String TIPO_SEGURO_NO_ENCONTRADO = "HR-TSG-404";

	public static final String SEGURIDAD_PERMISOS_SERVICE = "SECURITY-PERMISSION-SERVICE";

	private RrhhBusinessErrorCodes() {
	}
}
