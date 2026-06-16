package net.royal.erp.modules.rrhh.application.parametro.usecase;

import net.royal.erp.framework.audit.AuditPort;
import net.royal.erp.modules.rrhh.application.common.UseCaseGuards;
import net.royal.erp.modules.rrhh.domain.parametro.ParametroRepository;

/**
 * Implementa: - MOD-012 CU-001 CambiarEstadoParametro V2. - ARCH-010 Versionamiento Funcional.
 */
public class CambiarEstadoParametroV2UseCase extends CambiarEstadoParametroV1UseCase {
	public CambiarEstadoParametroV2UseCase(ParametroRepository repository, UseCaseGuards guards, AuditPort auditPort) {
		super(repository, guards, auditPort);
	}

	@Override
	protected String functionalVersion() {
		return "V2";
	}
}
