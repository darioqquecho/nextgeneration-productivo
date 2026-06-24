package net.royal.erp.hr.domain.tiposeguro;

import java.time.Instant;

import net.royal.erp.framework.kernel.BusinessException;
import net.royal.erp.hr.domain.RrhhBusinessErrorCodes;

/**
 * Dominio del maestro HR_TipoSeguro.
 */
public class TipoSeguro {
	private final TipoSeguroId id;
	private String descripcion;
	private String estado;
	private String ultimoUsuario;
	private Instant ultimaFechaModif;

	private TipoSeguro(TipoSeguroId id, String descripcion, String estado, String ultimoUsuario,
			Instant ultimaFechaModif) {
		this.id = id;
		this.descripcion = normalizarDescripcion(descripcion);
		this.estado = validarEstado(estado);
		this.ultimoUsuario = validarUsuario(ultimoUsuario);
		this.ultimaFechaModif = ultimaFechaModif;
	}

	public static TipoSeguro crear(Integer tipoSeguro, String descripcion, String estado, String usuario,
			Instant fechaModif) {
		return new TipoSeguro(new TipoSeguroId(tipoSeguro), descripcion, estado, usuario, fechaModif);
	}

	public static TipoSeguro cargar(Integer tipoSeguro, String descripcion, String estado, String ultimoUsuario,
			Instant ultimaFechaModif) {
		return new TipoSeguro(new TipoSeguroId(tipoSeguro), descripcion, estado, ultimoUsuario, ultimaFechaModif);
	}

	public void actualizar(String descripcion, String estado, String usuario, Instant fechaModif) {
		this.descripcion = normalizarDescripcion(descripcion);
		this.estado = validarEstado(estado);
		marcarModificacion(usuario, fechaModif);
	}

	public void cambiarEstado(String estado, String usuario, Instant fechaModif) {
		this.estado = validarEstado(estado);
		marcarModificacion(usuario, fechaModif);
	}

	private void marcarModificacion(String usuario, Instant fechaModif) {
		this.ultimoUsuario = validarUsuario(usuario);
		this.ultimaFechaModif = fechaModif;
	}

	private static String normalizarDescripcion(String descripcion) {
		if (descripcion != null && descripcion.length() > 40) {
			throw new BusinessException(RrhhBusinessErrorCodes.TIPO_SEGURO_DESCRIPCION_MUY_LARGA);
		}
		return descripcion == null ? null : descripcion.stripTrailing();
	}

	private static String validarEstado(String estado) {
		if (estado != null && estado.length() > 1) {
			throw new BusinessException(RrhhBusinessErrorCodes.TIPO_SEGURO_ESTADO_MUY_LARGO);
		}
		return estado;
	}

	private static String validarUsuario(String usuario) {
		if (usuario != null && usuario.length() > 20) {
			throw new BusinessException(RrhhBusinessErrorCodes.TIPO_SEGURO_USUARIO_MUY_LARGO);
		}
		return usuario;
	}

	public TipoSeguroId id() {
		return id;
	}

	public String descripcion() {
		return descripcion;
	}

	public String estado() {
		return estado;
	}

	public String ultimoUsuario() {
		return ultimoUsuario;
	}

	public Instant ultimaFechaModif() {
		return ultimaFechaModif;
	}
}
