package net.royal.spring.alertas.servicio.validar;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Set;

import jakarta.validation.ConstraintViolation;
import jakarta.validation.Validation;
import jakarta.validation.Validator;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import net.royal.spring.alertas.dao.impl.AlertaDaoImpl;
import net.royal.spring.alertas.dominio.Alerta;
import net.royal.spring.alertas.dominio.AlertaPk;
import net.royal.spring.framework.constante.ConstantePantallaAccion;
import net.royal.spring.framework.modelo.generico.DominioMensajeUsuario;
import net.royal.spring.framework.modelo.seguridad.SeguridadUsuarioActual;
import net.royal.spring.framework.web.servicio.impl.GenericoServicioValidar;

@SuppressWarnings("rawtypes")
@Service(value = "BeanValidarAlerta")
public class AlertaServicioValidar extends GenericoServicioValidar {
	public static String SPRING_NOMBRE = "BeanValidarAlerta";

	@Autowired
	private AlertaDaoImpl alertaDao;

	private Alerta prepararBasico(SeguridadUsuarioActual usuarioActual, Alerta alerta, Boolean flgInsertar) {
		if (flgInsertar) {
			alerta.setCreacionTerminal(usuarioActual.getDireccionIp());
			alerta.setCreacionFecha(new Date());
			alerta.setCreacionUsuario(usuarioActual.getUsuario());
		} else {
			alerta.setModificacionTerminal(usuarioActual.getDireccionIp());
			alerta.setModificacionFecha(new Date());
			alerta.setModificacionUsuario(usuarioActual.getUsuario());
		}

		// TODO Alerta : valores por defecto comunes Insert/Actualizar/Anular/Eliminar

		return alerta;
	}

	public Alerta prepararInsertar(SeguridadUsuarioActual usuarioActual, Alerta alerta) {
		if (alerta == null)
			return alerta;
		if (alerta.getAuxFlgPreparado())
			return alerta;
		alerta = prepararBasico(usuarioActual, alerta, true);
		alerta.setAuxFlgPreparado(Boolean.TRUE);

		// TODO Alerta.Insertar : valores por defecto

		return alerta;
	}

	public Alerta prepararActualizar(SeguridadUsuarioActual usuarioActual, Alerta alerta) {
		if (alerta == null)
			return alerta;
		if (alerta.getAuxFlgPreparado())
			return alerta;
		alerta = prepararBasico(usuarioActual, alerta, false);
		alerta.setAuxFlgPreparado(Boolean.TRUE);

		// TODO Alerta.Actualizar : valores por defecto

		return alerta;
	}

	public Alerta prepararAnular(SeguridadUsuarioActual usuarioActual, Alerta alerta) {
		if (alerta == null)
			return alerta;
		if (alerta.getAuxFlgPreparado())
			return alerta;
		alerta = prepararBasico(usuarioActual, alerta, false);
		alerta.setAuxFlgPreparado(Boolean.TRUE);

		// TODO Alerta.Anular : valores por defecto

		return alerta;
	}

	public Alerta prepararEliminar(SeguridadUsuarioActual usuarioActual, Alerta alerta) {
		if (alerta == null)
			return alerta;
		if (alerta.getAuxFlgPreparado())
			return alerta;
		alerta.setAuxFlgPreparado(Boolean.TRUE);

		// TODO Alerta.Eliminar : valores por defecto

		return alerta;
	}

	private List<DominioMensajeUsuario> coreBasico(SeguridadUsuarioActual usuarioActual, Alerta alerta) {
		List<DominioMensajeUsuario> lst = new ArrayList<DominioMensajeUsuario>();
		if (usuarioActual == null)
			lst.add(this.getMsjUsuarioError(SeguridadUsuarioActual.CONSTRAINTS_NOTNULL));
		if (alerta == null)
			lst.add(this.getMsjUsuarioError("alerta.constraints.notnull"));
		if (!lst.isEmpty())
			return lst;
		Validator validator = Validation.buildDefaultValidatorFactory().getValidator();
		if (alerta.getPk() != null) {
			Set<ConstraintViolation<AlertaPk>> reglasPk = validator.validate(alerta.getPk());
			for (ConstraintViolation constraint : reglasPk) {
				lst.add(this.getMsjUsuarioError(constraint));
			}
		}
		Set<ConstraintViolation<Alerta>> reglas = validator.validate(alerta);
		for (ConstraintViolation constraint : reglas) {
			lst.add(this.getMsjUsuarioError(constraint));
		}

		// TODO Alerta : validaciones comunes Insert/Actualizar

		return lst;
	}

	public List<DominioMensajeUsuario> coreInsertar(SeguridadUsuarioActual usuarioActual, Alerta alerta) {
		alerta = prepararInsertar(usuarioActual, alerta);
		List<DominioMensajeUsuario> lst = coreBasico(usuarioActual, alerta);
		if (!lst.isEmpty())
			return lst;

		// TODO Alerta.Insertar : validaciones

		return lst;
	}

	public List<DominioMensajeUsuario> coreActualizar(SeguridadUsuarioActual usuarioActual, Alerta alerta) {
		alerta = prepararActualizar(usuarioActual, alerta);
		List<DominioMensajeUsuario> lst = coreBasico(usuarioActual, alerta);
		if (!lst.isEmpty())
			return lst;

		// TODO Alerta.Actualizar : validaciones

		return lst;
	}

	public List<DominioMensajeUsuario> coreEliminar(SeguridadUsuarioActual usuarioActual, Alerta alerta) {
		alerta = prepararEliminar(usuarioActual, alerta);
		List<DominioMensajeUsuario> lst = new ArrayList<DominioMensajeUsuario>();

		// TODO Alerta.Eliminar : validaciones

		return lst;
	}

	public List<DominioMensajeUsuario> coreEliminar(SeguridadUsuarioActual usuarioActual, AlertaPk pk) {
		Alerta bean = alertaDao.obtenerPorId(pk);
		return coreEliminar(usuarioActual, bean);
	}

	public List<DominioMensajeUsuario> coreEliminar(SeguridadUsuarioActual usuarioActual, Integer pidAlerta) {
		return coreEliminar(usuarioActual, new AlertaPk(pidAlerta));
	}

	public List<DominioMensajeUsuario> coreAnular(SeguridadUsuarioActual usuarioActual, Alerta alerta) {
		alerta = prepararAnular(usuarioActual, alerta);
		List<DominioMensajeUsuario> lst = new ArrayList<DominioMensajeUsuario>();

		// TODO Alerta.Anular : validaciones

		return lst;
	}

	public List<DominioMensajeUsuario> coreAnular(SeguridadUsuarioActual usuarioActual, AlertaPk pk) {
		Alerta bean = alertaDao.obtenerPorId(pk);
		return coreAnular(usuarioActual, bean);
	}

	public List<DominioMensajeUsuario> coreAnular(SeguridadUsuarioActual usuarioActual, Integer pidAlerta) {
		return coreAnular(usuarioActual, new AlertaPk(pidAlerta));
	}

	public List<DominioMensajeUsuario> core(SeguridadUsuarioActual usuarioActual, String accion, Alerta alerta) {
		List<DominioMensajeUsuario> lst = new ArrayList<DominioMensajeUsuario>();
		if (accion.equals(ConstantePantallaAccion.INSERTAR))
			return coreInsertar(usuarioActual, alerta);
		if (accion.equals(ConstantePantallaAccion.ACTUALIZAR))
			return coreActualizar(usuarioActual, alerta);
		if (accion.equals(ConstantePantallaAccion.ANULAR))
			return coreAnular(usuarioActual, alerta);
		if (accion.equals(ConstantePantallaAccion.ELIMINAR))
			return coreEliminar(usuarioActual, alerta);
		return lst;
	}

}
