package net.royal.erp.modules.hr.api.v1.maestros.parametro.dto;

import jakarta.validation.constraints.*;

public record CambiarEstadoParametroRequestV1(@NotBlank @Size(max = 10) @Pattern(regexp = "^[A-Za-z]+$") String estado) {
}
