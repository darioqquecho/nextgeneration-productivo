package net.royal.erp.hr.api.maestros.parametro.aprobacionmasiva.v1.dto;

import java.util.List;

import jakarta.validation.Valid;
import jakarta.validation.constraints.*;

public record AprobacionMasivaParametrosRequestV1(@NotEmpty @Size(max = 500) List<@Valid AprobacionMasivaParametroItemV1> parametros) {
}
