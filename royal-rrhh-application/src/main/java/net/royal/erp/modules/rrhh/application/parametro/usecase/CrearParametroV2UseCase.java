package net.royal.erp.modules.rrhh.application.parametro.usecase;

import net.royal.erp.framework.audit.AuditPort;
import net.royal.erp.modules.rrhh.application.common.UseCaseGuards;
import net.royal.erp.modules.rrhh.domain.parametro.ParametroRepository;

/**
 * Implementa: - MOD-012 CU-001 CrearParametro V2. - ARCH-010 Versionamiento Funcional.
 */
public class CrearParametroV2UseCase extends CrearParametroV1UseCase {
	public CrearParametroV2UseCase(ParametroRepository repository, UseCaseGuards guards, AuditPort auditPort) {
		super(repository, guards, auditPort);
	}

	@Override
	protected String functionalVersion() {
		return "V2";
	}
}
