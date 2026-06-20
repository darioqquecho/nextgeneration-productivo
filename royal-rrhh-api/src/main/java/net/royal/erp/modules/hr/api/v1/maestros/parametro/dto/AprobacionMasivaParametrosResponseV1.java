package net.royal.erp.modules.hr.api.v1.maestros.parametro.dto;

import java.util.List;

public record AprobacionMasivaParametrosResponseV1(int solicitados, int aprobados, int omitidos,
		List<AprobacionMasivaParametroItemResponseV1> parametros, String traceId) {
}
