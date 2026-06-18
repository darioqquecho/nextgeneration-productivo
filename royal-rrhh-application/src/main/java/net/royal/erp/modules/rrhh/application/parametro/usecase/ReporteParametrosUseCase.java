package net.royal.erp.modules.rrhh.application.parametro.usecase;

import net.royal.erp.framework.kernel.FunctionalContext;
import net.royal.erp.modules.rrhh.application.parametro.dto.*;

/**
 * Implementa: - MOD-012 CU-007 Reporte de Parametros.
 */
public interface ReporteParametrosUseCase {
	ReporteParametrosResult ejecutar(ReporteParametrosQuery query, FunctionalContext context);
}
