package net.royal.erp.hr.api.maestros.parametro.reporte.v1.dto;

import jakarta.validation.constraints.*;

public record ReporteParametrosRequestV1(@Size(max = 10) @Pattern(regexp = "^[A-Za-z0-9_-]*$") String compania,
		@Size(max = 20) @Pattern(regexp = "^[A-Za-z0-9_.-]*$") String codigo,
		@Size(max = 10) @Pattern(regexp = "^[A-Za-z]*$") String estado) {
}
