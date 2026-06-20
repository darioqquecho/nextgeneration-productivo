package net.royal.erp.modules.hr.api.v1.maestros.parametro.dto;

import java.util.List;

public record ListarParametrosResponseV1(List<ParametroResponseV1> parametros, String traceId) {
}
