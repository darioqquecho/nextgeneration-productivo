package net.royal.erp.modules.alertas.domain.metadata;

/**
 * Implementa: - ARCH-012 Estructura Lógica. - MOD-012 Migración funcional de
 * royal-alertas-api.
 *
 * Corrige / Evoluciona: - ASIS-013 Identificación basada en tablas.
 *
 * Propósito: Metadato de campo legacy migrado desde entidades JPA del módulo
 * Alertas.
 */
public record AlertasFieldMetadata(String field, String column, String javaType, boolean primaryKey) {
}
