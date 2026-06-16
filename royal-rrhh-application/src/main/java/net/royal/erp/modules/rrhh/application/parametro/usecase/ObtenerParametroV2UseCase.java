package net.royal.erp.modules.rrhh.application.parametro.usecase;

import net.royal.erp.modules.rrhh.application.common.UseCaseGuards;
import net.royal.erp.modules.rrhh.domain.parametro.ParametroRepository;

/**
 * Implementa: - MOD-012 CU-001 ObtenerParametro V2. - ARCH-010 Versionamiento Funcional.
 */
public class ObtenerParametroV2UseCase extends ObtenerParametroV1UseCase {
	public ObtenerParametroV2UseCase(ParametroRepository repository, UseCaseGuards guards) {
		super(repository, guards);
	}
}
