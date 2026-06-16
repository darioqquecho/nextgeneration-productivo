package net.royal.erp.modules.alertas.application.dto;

import java.util.Map;

/**
 * Implementa: - ARCH-009 Bases de Datos. - ASIS-024 SQL HBM XML.
 *
 * Propósito: Command para ejecutar queries migradas desde SQL HBM XML.
 */
public record NamedQueryCommand(String queryName, Map<String, Object> parameters) {
}
