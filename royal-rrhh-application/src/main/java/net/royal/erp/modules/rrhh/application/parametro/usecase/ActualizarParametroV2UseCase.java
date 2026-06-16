package net.royal.erp.modules.rrhh.application.parametro.usecase;

import net.royal.erp.framework.audit.AuditPort;
import net.royal.erp.modules.rrhh.application.common.UseCaseGuards;
import net.royal.erp.modules.rrhh.domain.parametro.ParametroRepository;

/**
 * Implementa: - MOD-012 CU-001 ActualizarParametro V2. - ARCH-010 Versionamiento Funcional.
 */
public class ActualizarParametroV2UseCase extends ActualizarParametroV1UseCase {
	public ActualizarParametroV2UseCase(ParametroRepository repository, UseCaseGuards guards, AuditPort auditPort) {
		super(repository, guards, auditPort);
	}

	@Override
	protected String functionalVersion() {
		return "V2";
	}
}
