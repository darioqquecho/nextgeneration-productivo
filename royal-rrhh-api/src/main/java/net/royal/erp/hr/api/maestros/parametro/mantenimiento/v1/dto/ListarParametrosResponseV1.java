package net.royal.erp.hr.api.maestros.parametro.mantenimiento.v1.dto;

import java.util.List;

public record ListarParametrosResponseV1(List<ParametroResponseV1> parametros, String traceId) {
}
