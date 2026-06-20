package net.royal.erp.modules.hr.application.maestros.parametro.dto;

import java.util.List;

/**
 * Implementa: - MOD-012 CU-008 Aprobacion masiva de Parametros.
 */
public record AprobarMasivamenteParametrosCommand(List<AprobarParametroItem> parametros) {
}
