package net.royal.erp.modules.hr.application.parametro.usecase;

import net.royal.erp.framework.audit.AuditPort;
import net.royal.erp.framework.security.UseCaseGuards;
import net.royal.erp.modules.hr.application.parametro.port.MantenimientoTablaParametrosRepository;

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
