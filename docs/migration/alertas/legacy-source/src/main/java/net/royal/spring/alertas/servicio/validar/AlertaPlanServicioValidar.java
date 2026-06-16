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

import net.royal.spring.alertas.dao.impl.AlertaPlanDaoImpl;
import net.royal.spring.alertas.dominio.AlertaPlan;
import net.royal.spring.alertas.dominio.AlertaPlanPk;
import net.royal.spring.framework.constante.ConstantePantallaAccion;
import net.royal.spring.framework.modelo.generico.DominioMensajeUsuario;
import net.royal.spring.framework.modelo.seguridad.SeguridadUsuarioActual;
import net.royal.spring.framework.web.servicio.impl.GenericoServicioValidar;

@SuppressWarnings("rawtypes")
@Service(value = "BeanValidarAlertaPlan")
public class AlertaPlanServicioValidar extends GenericoServicioValidar {
	public static String SPRING_NOMBRE = "BeanValidarAlertaPlan";

	@Autowired
	private AlertaPlanDaoImpl alertaPlanDao;

	private AlertaPlan prepararBasico(SeguridadUsuarioActual usuarioActual, AlertaPlan alertaPlan,
			Boolean flgInsertar) {
		if (flgInsertar) {
			alertaPlan.setCreacionTerminal(usuarioActual.getDireccionIp());
			alertaPlan.setCreacionFecha(new Date());
			alertaPlan.setCreacionUsuario(usuarioActual.getUsuario());
		} else {
			alertaPlan.setModificacionTerminal(usuarioActual.getDireccionIp());
			alertaPlan.setModificacionFecha(new Date());
			alertaPlan.setModificacionUsuario(usuarioActual.getUsuario());
		}

		// TODO AlertaPlan : valores por defecto comunes
		// Insert/Actualizar/Anular/Eliminar

		return alertaPlan;
	}

	public AlertaPlan prepararInsertar(SeguridadUsuarioActual usuarioActual, AlertaPlan alertaPlan) {
		if (alertaPlan == null)
			return alertaPlan;
		if (alertaPlan.getAuxFlgPreparado())
			return alertaPlan;
		alertaPlan = prepararBasico(usuarioActual, alertaPlan, true);
		alertaPlan.setAuxFlgPreparado(Boolean.TRUE);

		// TODO AlertaPlan.Insertar : valores por defecto

		return alertaPlan;
	}

	public AlertaPlan prepararActualizar(SeguridadUsuarioActual usuarioActual, AlertaPlan alertaPlan) {
		if (alertaPlan == null)
			return alertaPlan;
		if (alertaPlan.getAuxFlgPreparado())
			return alertaPlan;
		alertaPlan = prepararBasico(usuarioActual, alertaPlan, false);
		alertaPlan.setAuxFlgPreparado(Boolean.TRUE);

		// TODO AlertaPlan.Actualizar : valores por defecto

		return alertaPlan;
	}

	public AlertaPlan prepararAnular(SeguridadUsuarioActual usuarioActual, AlertaPlan alertaPlan) {
		if (alertaPlan == null)
			return alertaPlan;
		if (alertaPlan.getAuxFlgPreparado())
			return alertaPlan;
		alertaPlan = prepararBasico(usuarioActual, alertaPlan, false);
		alertaPlan.setAuxFlgPreparado(Boolean.TRUE);

		// TODO AlertaPlan.Anular : valores por defecto

		return alertaPlan;
	}

	public AlertaPlan prepararEliminar(SeguridadUsuarioActual usuarioActual, AlertaPlan alertaPlan) {
		if (alertaPlan == null)
			return alertaPlan;
		if (alertaPlan.getAuxFlgPreparado())
			return alertaPlan;
		alertaPlan.setAuxFlgPreparado(Boolean.TRUE);

		// TODO AlertaPlan.Eliminar : valores por defecto

		return alertaPlan;
	}

	private List<DominioMensajeUsuario> coreBasico(SeguridadUsuarioActual usuarioActual, AlertaPlan alertaPlan) {
		List<DominioMensajeUsuario> lst = new ArrayList<DominioMensajeUsuario>();
		if (usuarioActual == null)
			lst.add(this.getMsjUsuarioError(SeguridadUsuarioActual.CONSTRAINTS_NOTNULL));
		if (alertaPlan == null)
			lst.add(this.getMsjUsuarioError("alertaplan.constraints.notnull"));
		if (!lst.isEmpty())
			return lst;
		Validator validator = Validation.buildDefaultValidatorFactory().getValidator();
		if (alertaPlan.getPk() != null) {
			Set<ConstraintViolation<AlertaPlanPk>> reglasPk = validator.validate(alertaPlan.getPk());
			for (ConstraintViolation constraint : reglasPk) {
				lst.add(this.getMsjUsuarioError(constraint));
			}
		}
		Set<ConstraintViolation<AlertaPlan>> reglas = validator.validate(alertaPlan);
		for (ConstraintViolation constraint : reglas) {
			lst.add(this.getMsjUsuarioError(constraint));
		}

		// TODO AlertaPlan : validaciones comunes Insert/Actualizar

		return lst;
	}

	public List<DominioMensajeUsuario> coreInsertar(SeguridadUsuarioActual usuarioActual, AlertaPlan alertaPlan) {
		alertaPlan = prepararInsertar(usuarioActual, alertaPlan);
		List<DominioMensajeUsuario> lst = coreBasico(usuarioActual, alertaPlan);
		if (!lst.isEmpty())
			return lst;

		// TODO AlertaPlan.Insertar : validaciones

		return lst;
	}

	public List<DominioMensajeUsuario> coreActualizar(SeguridadUsuarioActual usuarioActual, AlertaPlan alertaPlan) {
		alertaPlan = prepararActualizar(usuarioActual, alertaPlan);
		List<DominioMensajeUsuario> lst = coreBasico(usuarioActual, alertaPlan);
		if (!lst.isEmpty())
			return lst;

		// TODO AlertaPlan.Actualizar : validaciones

		return lst;
	}

	public List<DominioMensajeUsuario> coreEliminar(SeguridadUsuarioActual usuarioActual, AlertaPlan alertaPlan) {
		alertaPlan = prepararEliminar(usuarioActual, alertaPlan);
		List<DominioMensajeUsuario> lst = new ArrayList<DominioMensajeUsuario>();

		// TODO AlertaPlan.Eliminar : validaciones

		return lst;
	}

	public List<DominioMensajeUsuario> coreEliminar(SeguridadUsuarioActual usuarioActual, AlertaPlanPk pk) {
		AlertaPlan bean = alertaPlanDao.obtenerPorId(pk);
		return coreEliminar(usuarioActual, bean);
	}

	public List<DominioMensajeUsuario> coreEliminar(SeguridadUsuarioActual usuarioActual, Integer pidAlerta,
			Integer pidPlan) {
		return coreEliminar(usuarioActual, new AlertaPlanPk(pidAlerta, pidPlan));
	}

	public List<DominioMensajeUsuario> coreAnular(SeguridadUsuarioActual usuarioActual, AlertaPlan alertaPlan) {
		alertaPlan = prepararAnular(usuarioActual, alertaPlan);
		List<DominioMensajeUsuario> lst = new ArrayList<DominioMensajeUsuario>();

		// TODO AlertaPlan.Anular : validaciones

		return lst;
	}

	public List<DominioMensajeUsuario> coreAnular(SeguridadUsuarioActual usuarioActual, AlertaPlanPk pk) {
		AlertaPlan bean = alertaPlanDao.obtenerPorId(pk);
		return coreAnular(usuarioActual, bean);
	}

	public List<DominioMensajeUsuario> coreAnular(SeguridadUsuarioActual usuarioActual, Integer pidAlerta,
			Integer pidPlan) {
		return coreAnular(usuarioActual, new AlertaPlanPk(pidAlerta, pidPlan));
	}

	public List<DominioMensajeUsuario> core(SeguridadUsuarioActual usuarioActual, String accion,
			AlertaPlan alertaPlan) {
		List<DominioMensajeUsuario> lst = new ArrayList<DominioMensajeUsuario>();
		if (accion.equals(ConstantePantallaAccion.INSERTAR))
			return coreInsertar(usuarioActual, alertaPlan);
		if (accion.equals(ConstantePantallaAccion.ACTUALIZAR))
			return coreActualizar(usuarioActual, alertaPlan);
		if (accion.equals(ConstantePantallaAccion.ANULAR))
			return coreAnular(usuarioActual, alertaPlan);
		if (accion.equals(ConstantePantallaAccion.ELIMINAR))
			return coreEliminar(usuarioActual, alertaPlan);
		return lst;
	}

}
