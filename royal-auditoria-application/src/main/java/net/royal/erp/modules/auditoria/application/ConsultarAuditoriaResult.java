package net.royal.erp.modules.auditoria.application;

import java.util.List;

/**
 * Implementa: - ARCH-013 CQRS.
 */
public record ConsultarAuditoriaResult(List<String> registros) {
}
