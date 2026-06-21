package net.royal.erp.hr.api.maestros.tiposeguro.v1.dto;

import jakarta.validation.constraints.*;

public record CambiarEstadoTipoSeguroRequestV1(@NotBlank @Size(max = 1) @Pattern(regexp = "^[A-Za-z]$") String estado) {
}
