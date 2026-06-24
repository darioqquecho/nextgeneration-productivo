package net.royal.erp.hr.domain.capacitacion;

import net.royal.erp.framework.kernel.BusinessException;
import net.royal.erp.hr.domain.RrhhBusinessErrorCodes;
import java.time.LocalDate;

/**
 * Implementa: - ARCH-003 DDD. - MOD-012 CU-002 RegistrarCapacitacion.
 */
public class Capacitacion {
	private final String codigo;
	private final String nombre;
	private final LocalDate fechaInicio;
	private final LocalDate fechaFin;
	private final String instructor;

	private Capacitacion(String codigo, String nombre, LocalDate fechaInicio, LocalDate fechaFin, String instructor) {
		this.codigo = codigo;
		this.nombre = nombre;
		this.fechaInicio = fechaInicio;
		this.fechaFin = fechaFin;
		this.instructor = instructor;
	}

	/**
	 * Crea capacitación validando reglas de dominio.
	 *
	 * Implementa: - ARCH-003 DDD.
	 */
	public static Capacitacion registrar(String codigo, String nombre, LocalDate fechaInicio, LocalDate fechaFin,
			String instructor) {
		if (codigo == null || codigo.isBlank())
			throw new BusinessException(RrhhBusinessErrorCodes.CAPACITACION_CODIGO_REQUERIDO);
		if (nombre == null || nombre.isBlank())
			throw new BusinessException(RrhhBusinessErrorCodes.CAPACITACION_NOMBRE_REQUERIDO);
		if (fechaInicio == null || fechaFin == null || fechaInicio.isAfter(fechaFin))
			throw new BusinessException(RrhhBusinessErrorCodes.CAPACITACION_FECHAS_INVALIDAS);
		return new Capacitacion(codigo, nombre, fechaInicio, fechaFin, instructor);
	}

	public String codigo() {
		return codigo;
	}

	public String nombre() {
		return nombre;
	}

	public LocalDate fechaInicio() {
		return fechaInicio;
	}

	public LocalDate fechaFin() {
		return fechaFin;
	}

	public String instructor() {
		return instructor;
	}
}
