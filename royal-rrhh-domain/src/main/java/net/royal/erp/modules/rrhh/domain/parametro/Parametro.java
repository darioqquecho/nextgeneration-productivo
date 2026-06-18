package net.royal.erp.modules.rrhh.domain.parametro;

import java.time.Instant;

import net.royal.erp.framework.kernel.BusinessException;

/**
 * Implementa: - ARCH-003 DDD. - MOD-012 CU-001 CRUD HR_Parametros.
 */
public class Parametro {
	private final ParametroId id;
	private String nombre;
	private ParametroEstado estado;
	private String ultimoUsuario;
	private Instant ultimaFechaModif;

	private Parametro(ParametroId id, String nombre, ParametroEstado estado, String ultimoUsuario,
			Instant ultimaFechaModif) {
		this.id = id;
		this.nombre = nombre;
		this.estado = estado;
		this.ultimoUsuario = ultimoUsuario;
		this.ultimaFechaModif = ultimaFechaModif;
	}

	public static Parametro crear(String compania, String codigo, String nombre, String usuario, Instant fechaModif) {
		validarNombre(nombre);
		return new Parametro(new ParametroId(compania, codigo), nombre, ParametroEstado.ACTIVO,
				validarUsuario(usuario), fechaModif);
	}

	public static Parametro cargar(String compania, String codigo, String nombre, String estado, String ultimoUsuario,
			Instant ultimaFechaModif) {
		validarNombre(nombre);
		ParametroEstado parametroEstado = estado == null || estado.isBlank() ? null
				: ParametroEstado.valueOf(estado.trim().toUpperCase());
		return new Parametro(new ParametroId(compania, codigo), nombre, parametroEstado, validarUsuario(ultimoUsuario),
				ultimaFechaModif);
	}

	public void actualizarNombre(String nombre, String usuario, Instant fechaModif) {
		validarNombre(nombre);
		this.nombre = nombre;
		marcarModificacion(usuario, fechaModif);
	}

	public void cambiarEstado(ParametroEstado estado, String usuario, Instant fechaModif) {
		if (estado == null) {
			throw new BusinessException("HR-PAR-003");
		}
		this.estado = estado;
		marcarModificacion(usuario, fechaModif);
	}

	private void marcarModificacion(String usuario, Instant fechaModif) {
		this.ultimoUsuario = validarUsuario(usuario);
		this.ultimaFechaModif = fechaModif;
	}

	private static void validarNombre(String nombre) {
		if (nombre != null && nombre.length() > 100) {
			throw new BusinessException("HR-PAR-002");
		}
	}

	private static String validarUsuario(String usuario) {
		if (usuario != null && usuario.length() > 20) {
			throw new BusinessException("HR-PAR-005");
		}
		return usuario;
	}

	public ParametroId id() {
		return id;
	}

	public String nombre() {
		return nombre;
	}

	public ParametroEstado estado() {
		return estado;
	}

	public String ultimoUsuario() {
		return ultimoUsuario;
	}

	public Instant ultimaFechaModif() {
		return ultimaFechaModif;
	}
}
