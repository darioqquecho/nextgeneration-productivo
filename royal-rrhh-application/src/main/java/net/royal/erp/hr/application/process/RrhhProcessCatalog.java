package net.royal.erp.hr.application.process;

/**
 * Catalogo de modulo y procesos funcionales de RRHH.
 */
public final class RrhhProcessCatalog {
	public static final String MODULE = Module.CODE;

	public static final Process MAESTROS = new Process(MODULE, ProcessCode.MAESTROS, "Maestros");
	public static final Process CAPACITACION = new Process(MODULE, ProcessCode.CAPACITACION, "Capacitacion");
	public static final Process REQUERIMIENTO = new Process(MODULE, ProcessCode.REQUERIMIENTO, "Requerimiento");

	private RrhhProcessCatalog() {
	}

	public record Process(String module, String code, String displayName) {
	}

	public static final class Module {
		public static final String CODE = "HR";
		public static final String NAME = "Recursos Humanos";
		public static final String DESCRIPTION = "Modulo de gestion de recursos humanos";

		private Module() {
		}
	}

	public static final class ProcessCode {
		public static final String MAESTROS = Module.CODE + ".MAESTROS";
		public static final String CAPACITACION = Module.CODE + ".CAPACITACION";
		public static final String REQUERIMIENTO = Module.CODE + ".REQUERIMIENTO";

		private ProcessCode() {
		}
	}
}
