package net.royal.erp.hr.application.process;

/**
 * Catalogo de casos de uso funcionales de RRHH.
 */
public final class RrhhUseCaseCatalog {
	public static final UseCase PARAMETRO_MANTENIMIENTO = useCase(RrhhProcessCatalog.MAESTROS,
			UseCaseCode.PARAMETRO_MANTENIMIENTO, "Mantenimiento de Parametro");
	public static final UseCase PARAMETRO_REPORTE = useCase(RrhhProcessCatalog.MAESTROS,
			UseCaseCode.PARAMETRO_REPORTE, "Reporte de Parametro");
	public static final UseCase PARAMETRO_APROBACIONMASIVA = useCase(RrhhProcessCatalog.MAESTROS,
			UseCaseCode.PARAMETRO_APROBACIONMASIVA, "Aprobacion masiva de Parametros");
	
	public static final UseCase TIPOSEGURO_MANTENIMIENTO = useCase(RrhhProcessCatalog.MAESTROS,
			UseCaseCode.TIPOSEGURO_MANTENIMIENTO, "Mantenimiento de Tipo Seguro");
	
	public static final UseCase CAPACITACION_REGISTRAR = useCase(RrhhProcessCatalog.CAPACITACION,
			UseCaseCode.CAPACITACION_REGISTRAR, "Registrar Capacitacion");
	
	public static final UseCase REQUERIMIENTO_APROBAR = useCase(RrhhProcessCatalog.REQUERIMIENTO,
			UseCaseCode.REQUERIMIENTO_APROBAR, "Aprobar Requerimiento Personal");

	private RrhhUseCaseCatalog() {
	}

	private static UseCase useCase(RrhhProcessCatalog.Process process, String code, String displayName) {
		return new UseCase(process.code(), process.displayName(), code, displayName);
	}

	public record UseCase(String processCode, String processName, String code, String displayName) {
	}

	public static final class UseCaseCode {
		public static final String PARAMETRO_MANTENIMIENTO = RrhhProcessCatalog.ProcessCode.MAESTROS
				+ ".PARAMETRO_MANTENIMIENTO";
		public static final String PARAMETRO_APROBACIONMASIVA = RrhhProcessCatalog.ProcessCode.MAESTROS
				+ ".PARAMETRO_APROBACIONMASIVA";
		public static final String PARAMETRO_REPORTE = RrhhProcessCatalog.ProcessCode.MAESTROS + ".PARAMETRO_REPORTE";
		public static final String TIPOSEGURO_MANTENIMIENTO = RrhhProcessCatalog.ProcessCode.MAESTROS
				+ ".TIPOSEGURO_MANTENIMIENTO";
		public static final String CAPACITACION_REGISTRAR = RrhhProcessCatalog.ProcessCode.CAPACITACION
				+ ".REGISTRAR";
		public static final String REQUERIMIENTO_APROBAR = RrhhProcessCatalog.ProcessCode.REQUERIMIENTO + ".APROBAR";

		private UseCaseCode() {
		}
	}
}
