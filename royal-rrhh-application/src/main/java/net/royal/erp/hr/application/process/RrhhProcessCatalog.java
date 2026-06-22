package net.royal.erp.hr.application.process;

/**
 * Catalogo funcional del modulo RRHH. Mantiene nombres de procesos y casos de
 * uso en un solo lugar para evitar paquetes planos y literales repetidos.
 */
public final class RrhhProcessCatalog {
	public static final String MODULE = "HR";

	public static final Process MAESTROS = new Process("maestros", "Maestros");
	public static final Process CAPACITACION = new Process("capacitacion", "Capacitacion");
	public static final Process REQUERIMIENTO = new Process("requerimiento", "Requerimiento");

	public static final UseCase MANTENIMIENTO_PARAMETRO = useCase(MAESTROS, "parametro", "Mantenimiento de Parametro");
	public static final UseCase REPORTE_PARAMETRO = useCase(MAESTROS, "reporte-parametro", "Reporte de Parametro");
	public static final UseCase APROBACION_MASIVA_PARAMETROS = useCase(MAESTROS, "aprobacion-masiva-parametros",
			"Aprobacion masiva de Parametros");
	public static final UseCase MANTENIMIENTO_TIPO_SEGURO = useCase(MAESTROS, "tiposeguro",
			"Mantenimiento de Tipo Seguro");
	public static final UseCase REGISTRAR_CAPACITACION = useCase(CAPACITACION, "registrar", "Registrar Capacitacion");
	public static final UseCase APROBAR_REQUERIMIENTO_PERSONAL = useCase(REQUERIMIENTO, "aprobar",
			"Aprobar Requerimiento Personal");

	private RrhhProcessCatalog() {
	}

	private static UseCase useCase(Process process, String code, String displayName) {
		return new UseCase(process.code(), process.displayName(), code, displayName);
	}

	public record Process(String code, String displayName) {
	}

	public record UseCase(String processCode, String processName, String code, String displayName) {
	}
}
