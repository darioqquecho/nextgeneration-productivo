package net.royal.erp.hr.api.maestros.parametro.mantenimiento.v1.dto;

import jakarta.validation.constraints.*;

public record CambiarEstadoParametroRequestV1(@NotBlank @Size(max = 10) @Pattern(regexp = "^[A-Za-z]+$") String estado) {
}
