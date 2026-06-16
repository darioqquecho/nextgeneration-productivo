package net.royal.erp.modules.rrhh.application.parametro.usecase;

import net.royal.erp.framework.versioning.FunctionalVersionResolver;
import net.royal.erp.modules.rrhh.application.parametro.dto.*;

/**
 * Implementa: - MOD-012 CU-001 ListarParametros. - ARCH-010 Versionamiento Funcional.
 */
public class ListarParametrosVersionedUseCase
		extends VersionedParametroUseCase<ListarParametrosQuery, ListarParametrosResult>
		implements ListarParametrosUseCase {
	public ListarParametrosVersionedUseCase(FunctionalVersionResolver resolver, ListarParametrosUseCase v1,
			ListarParametrosUseCase v2) {
		super(resolver, v1, v2);
	}
}
