package net.royal.erp.hr.application.maestros.parametro.aprobacionmasiva.dto;

import java.util.List;

import jakarta.validation.Valid;
import jakarta.validation.constraints.*;

/**
 * Implementa: - MOD-012 CU-008 Aprobacion masiva de Parametros.
 */
public record AprobarMasivamenteParametrosCommand(
		@NotEmpty @Size(max = 500) List<@Valid AprobarParametroItem> parametros) {
}
