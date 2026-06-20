package net.royal.erp.modules.hr.api.v1.maestros.tiposeguro.dto;

import java.util.List;

public record ListarTiposSeguroResponseV1(List<TipoSeguroResponseV1> tiposSeguro, String traceId) {
}
