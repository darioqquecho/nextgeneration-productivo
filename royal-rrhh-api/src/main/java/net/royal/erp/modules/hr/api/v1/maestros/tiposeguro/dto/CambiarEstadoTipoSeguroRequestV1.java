package net.royal.erp.modules.hr.api.v1.maestros.tiposeguro.dto;

import jakarta.validation.constraints.*;

public record CambiarEstadoTipoSeguroRequestV1(@NotBlank @Size(max = 1) @Pattern(regexp = "^[A-Za-z]$") String estado) {
}
