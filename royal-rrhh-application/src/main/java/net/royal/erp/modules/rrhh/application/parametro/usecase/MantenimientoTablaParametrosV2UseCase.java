package net.royal.erp.modules.rrhh.application.parametro.usecase;

import net.royal.erp.framework.audit.AuditPort;
import net.royal.erp.modules.rrhh.application.common.UseCaseGuards;
import net.royal.erp.modules.rrhh.application.parametro.port.MantenimientoTablaParametrosRepository;

/**
 * Implementa: - MOD-012 CU-001 Mantenimiento de Tabla Parametros V2.
 */
public class MantenimientoTablaParametrosV2UseCase extends MantenimientoTablaParametrosV1UseCase {
	public MantenimientoTablaParametrosV2UseCase(MantenimientoTablaParametrosRepository repository, UseCaseGuards guards,
			AuditPort auditPort) {
		super(repository, guards, auditPort);
	}

	@Override
	protected String functionalVersion() {
		return "V2";
	}
}
