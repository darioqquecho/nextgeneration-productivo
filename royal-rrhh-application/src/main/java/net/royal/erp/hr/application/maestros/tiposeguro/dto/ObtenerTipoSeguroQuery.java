package net.royal.erp.hr.application.maestros.tiposeguro.dto;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;

public record ObtenerTipoSeguroQuery(@NotNull @Min(1) Integer tipoSeguro) {
}
