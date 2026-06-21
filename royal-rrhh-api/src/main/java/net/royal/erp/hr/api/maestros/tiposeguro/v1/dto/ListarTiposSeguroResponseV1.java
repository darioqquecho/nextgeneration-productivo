package net.royal.erp.hr.api.maestros.tiposeguro.v1.dto;

import java.util.List;

public record ListarTiposSeguroResponseV1(List<TipoSeguroResponseV1> tiposSeguro, String traceId) {
}
