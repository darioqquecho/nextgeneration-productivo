package net.royal.erp.modules.hr.application.maestros.parametro.usecase;

import net.royal.erp.framework.kernel.FunctionalContext;
import net.royal.erp.modules.hr.application.maestros.parametro.dto.*;

/**
 * Implementa: - MOD-012 CU-007 Reporte de Parametros.
 */
public interface ReporteParametrosUseCase {
	ReporteParametrosResult ejecutar(ReporteParametrosQuery query, FunctionalContext context);
}
