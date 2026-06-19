package net.royal.erp.modules.hr.application.tiposeguro.dto;

import java.util.List;

public record ListarTiposSeguroResult(List<TipoSeguroResult> tiposSeguro, String traceId) {
}
