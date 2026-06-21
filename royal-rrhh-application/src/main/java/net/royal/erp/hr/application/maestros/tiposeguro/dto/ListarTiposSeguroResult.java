package net.royal.erp.hr.application.maestros.tiposeguro.dto;

import java.util.List;

public record ListarTiposSeguroResult(List<TipoSeguroResult> tiposSeguro, String traceId) {
}
