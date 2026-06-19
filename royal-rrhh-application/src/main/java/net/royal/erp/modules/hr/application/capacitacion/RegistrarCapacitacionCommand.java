package net.royal.erp.modules.hr.application.capacitacion;

import java.time.LocalDate;

/**
 * Implementa: - ARCH-013 CQRS.
 */
public record RegistrarCapacitacionCommand(String codigo, String nombre, LocalDate fechaInicio, LocalDate fechaFin,
		String instructor) {
}
