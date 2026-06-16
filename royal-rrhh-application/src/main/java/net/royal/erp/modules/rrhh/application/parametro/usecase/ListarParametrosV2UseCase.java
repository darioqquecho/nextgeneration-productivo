package net.royal.erp.modules.rrhh.application.parametro.usecase;

import net.royal.erp.modules.rrhh.application.common.UseCaseGuards;
import net.royal.erp.modules.rrhh.domain.parametro.ParametroRepository;

/**
 * Implementa: - MOD-012 CU-001 ListarParametros V2. - ARCH-010 Versionamiento Funcional.
 */
public class ListarParametrosV2UseCase extends ListarParametrosV1UseCase {
	public ListarParametrosV2UseCase(ParametroRepository repository, UseCaseGuards guards) {
		super(repository, guards);
	}
}
