package net.royal.erp.modules.hr.api.v1.maestros.parametro.dto;

import java.util.List;

import jakarta.validation.Valid;
import jakarta.validation.constraints.*;

public record AprobacionMasivaParametrosRequestV1(@NotEmpty @Size(max = 500) List<@Valid AprobacionMasivaParametroItemV1> parametros) {
}
