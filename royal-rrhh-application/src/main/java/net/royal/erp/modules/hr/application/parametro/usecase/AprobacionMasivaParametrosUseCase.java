package net.royal.erp.modules.hr.application.parametro.usecase;

import net.royal.erp.framework.kernel.FunctionalContext;
import net.royal.erp.modules.hr.application.parametro.dto.*;

/**
 * Implementa: - MOD-012 CU-008 Aprobacion masiva de Parametros.
 */
public interface AprobacionMasivaParametrosUseCase {
	AprobarMasivamenteParametrosResult aprobar(AprobarMasivamenteParametrosCommand command, FunctionalContext context);
}
