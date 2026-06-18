package net.royal.erp.modules.rrhh.api.v1.parametro.dto;

import java.util.List;

public record ListarParametrosResponseV1(List<ParametroResponseV1> parametros, String traceId) {
}
