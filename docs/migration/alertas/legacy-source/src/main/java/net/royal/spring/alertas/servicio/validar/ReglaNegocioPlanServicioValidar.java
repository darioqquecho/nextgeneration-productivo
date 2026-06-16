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

import net.royal.spring.alertas.dao.impl.ReglaNegocioPlanDaoImpl;
import net.royal.spring.alertas.dominio.ReglaNegocioPlan;
import net.royal.spring.alertas.dominio.ReglaNegocioPlanPk;
import net.royal.spring.framework.constante.ConstantePantallaAccion;
import net.royal.spring.framework.modelo.generico.DominioMensajeUsuario;
import net.royal.spring.framework.modelo.seguridad.SeguridadUsuarioActual;
import net.royal.spring.framework.web.servicio.impl.GenericoServicioValidar;
@SuppressWarnings("rawtypes")
@Service (value = "BeanValidarReglaNegocioPlan")
public class ReglaNegocioPlanServicioValidar extends GenericoServicioValidar {
	public static String SPRING_NOMBRE = "BeanValidarReglaNegocioPlan";

	@Autowired
	private ReglaNegocioPlanDaoImpl reglaNegocioPlanDao;

	private ReglaNegocioPlan prepararBasico(SeguridadUsuarioActual usuarioActual,ReglaNegocioPlan reglaNegocioPlan, Boolean flgInsertar) {
		if (flgInsertar) {
			reglaNegocioPlan.setCreacionTerminal(usuarioActual.getDireccionIp());
			reglaNegocioPlan.setCreacionFecha(new Date());
			reglaNegocioPlan.setCreacionUsuario(usuarioActual.getUsuario());
		} else {
			reglaNegocioPlan.setModificacionTerminal(usuarioActual.getDireccionIp());
			reglaNegocioPlan.setModificacionFecha(new Date());
			reglaNegocioPlan.setModificacionUsuario(usuarioActual.getUsuario());
		}
		
		// TODO ReglaNegocioPlan : valores por defecto comunes Insert/Actualizar/Anular/Eliminar
		
		return reglaNegocioPlan;
	}

	public ReglaNegocioPlan prepararInsertar(SeguridadUsuarioActual usuarioActual,ReglaNegocioPlan reglaNegocioPlan) {
		if (reglaNegocioPlan == null)
			return reglaNegocioPlan;
		if (reglaNegocioPlan.getAuxFlgPreparado())
			return reglaNegocioPlan;
		reglaNegocioPlan = prepararBasico(usuarioActual,reglaNegocioPlan, true);
		reglaNegocioPlan.setAuxFlgPreparado(Boolean.TRUE);
		
		// TODO ReglaNegocioPlan.Insertar : valores por defecto
		
		return reglaNegocioPlan;
	}

	public ReglaNegocioPlan prepararActualizar(SeguridadUsuarioActual usuarioActual,ReglaNegocioPlan reglaNegocioPlan) {
		if (reglaNegocioPlan == null)
			return reglaNegocioPlan;
		if (reglaNegocioPlan.getAuxFlgPreparado())
			return reglaNegocioPlan;
		reglaNegocioPlan = prepararBasico(usuarioActual,reglaNegocioPlan, false);
		reglaNegocioPlan.setAuxFlgPreparado(Boolean.TRUE);
		
		// TODO ReglaNegocioPlan.Actualizar : valores por defecto
		
		return reglaNegocioPlan;
	}

	public ReglaNegocioPlan prepararAnular(SeguridadUsuarioActual usuarioActual,ReglaNegocioPlan reglaNegocioPlan) {
		if (reglaNegocioPlan == null)
			return reglaNegocioPlan;
		if (reglaNegocioPlan.getAuxFlgPreparado())
			return reglaNegocioPlan;
		reglaNegocioPlan = prepararBasico(usuarioActual, reglaNegocioPlan, false);
		reglaNegocioPlan.setAuxFlgPreparado(Boolean.TRUE);
		
		// TODO ReglaNegocioPlan.Anular : valores por defecto
		
		return reglaNegocioPlan;
	}

	public ReglaNegocioPlan prepararEliminar(SeguridadUsuarioActual usuarioActual,ReglaNegocioPlan reglaNegocioPlan) {
		if (reglaNegocioPlan == null)
			return reglaNegocioPlan;
		if (reglaNegocioPlan.getAuxFlgPreparado())
			return reglaNegocioPlan;
		reglaNegocioPlan.setAuxFlgPreparado(Boolean.TRUE);
		
		// TODO ReglaNegocioPlan.Eliminar : valores por defecto
		
		return reglaNegocioPlan;
	}

	private List<DominioMensajeUsuario> coreBasico(SeguridadUsuarioActual usuarioActual, ReglaNegocioPlan reglaNegocioPlan) {
		List<DominioMensajeUsuario> lst = new ArrayList<DominioMensajeUsuario>();
		if (usuarioActual == null)
			lst.add(this.getMsjUsuarioError(SeguridadUsuarioActual.CONSTRAINTS_NOTNULL));
		if (reglaNegocioPlan == null)
			lst.add(this.getMsjUsuarioError("reglanegocioplan.constraints.notnull"));
		if (!lst.isEmpty())
			return lst;
		Validator validator = Validation.buildDefaultValidatorFactory().getValidator();
		if (reglaNegocioPlan.getPk() != null) {
			Set<ConstraintViolation<ReglaNegocioPlanPk>> reglasPk = validator.validate(reglaNegocioPlan.getPk());
			for (ConstraintViolation constraint : reglasPk) {
				lst.add(this.getMsjUsuarioError(constraint));
			}
		}
		Set<ConstraintViolation<ReglaNegocioPlan>> reglas = validator.validate(reglaNegocioPlan);
		for (ConstraintViolation constraint : reglas) {
			lst.add(this.getMsjUsuarioError(constraint));
		}
		
		// TODO ReglaNegocioPlan : validaciones comunes Insert/Actualizar
		
		return lst;
	}

	public List<DominioMensajeUsuario> coreInsertar(SeguridadUsuarioActual usuarioActual,ReglaNegocioPlan reglaNegocioPlan) {
		reglaNegocioPlan = prepararInsertar(usuarioActual, reglaNegocioPlan);
		List<DominioMensajeUsuario> lst = coreBasico(usuarioActual, reglaNegocioPlan);
		if (!lst.isEmpty())
			return lst;
		
		// TODO ReglaNegocioPlan.Insertar : validaciones
		
		return lst;
	}

	public List<DominioMensajeUsuario> coreActualizar(SeguridadUsuarioActual usuarioActual, ReglaNegocioPlan reglaNegocioPlan) {
		reglaNegocioPlan = prepararActualizar(usuarioActual, reglaNegocioPlan);
		List<DominioMensajeUsuario> lst = coreBasico(usuarioActual, reglaNegocioPlan);
		if (!lst.isEmpty())
			return lst;
		
		// TODO ReglaNegocioPlan.Actualizar : validaciones
		
		return lst;
	}

	public List<DominioMensajeUsuario> coreEliminar(SeguridadUsuarioActual usuarioActual,ReglaNegocioPlan reglaNegocioPlan) {
		reglaNegocioPlan = prepararEliminar(usuarioActual, reglaNegocioPlan);
		List<DominioMensajeUsuario> lst = new ArrayList<DominioMensajeUsuario>();
		
		// TODO ReglaNegocioPlan.Eliminar : validaciones
		
		return lst;
	}

	public List<DominioMensajeUsuario> coreEliminar(SeguridadUsuarioActual usuarioActual,ReglaNegocioPlanPk pk) {
		ReglaNegocioPlan bean = reglaNegocioPlanDao.obtenerPorId(pk);
		return coreEliminar(usuarioActual,bean);
	}

	public List<DominioMensajeUsuario> coreEliminar(SeguridadUsuarioActual usuarioActual,Integer pidReglaNegocio,Integer pidPlan) {
		return coreEliminar(usuarioActual,new ReglaNegocioPlanPk( pidReglaNegocio, pidPlan));
	}

	public List<DominioMensajeUsuario> coreAnular(SeguridadUsuarioActual usuarioActual, ReglaNegocioPlan reglaNegocioPlan) {
		reglaNegocioPlan = prepararAnular(usuarioActual, reglaNegocioPlan);
		List<DominioMensajeUsuario> lst = new ArrayList<DominioMensajeUsuario>();
		
		// TODO ReglaNegocioPlan.Anular : validaciones
		
		return lst;
	}

	public List<DominioMensajeUsuario> coreAnular(SeguridadUsuarioActual usuarioActual, ReglaNegocioPlanPk pk) {
		ReglaNegocioPlan bean = reglaNegocioPlanDao.obtenerPorId(pk);
		return coreAnular(usuarioActual,bean);
	}

	public List<DominioMensajeUsuario> coreAnular(SeguridadUsuarioActual usuarioActual, Integer pidReglaNegocio,Integer pidPlan) {
		return coreAnular(usuarioActual,new ReglaNegocioPlanPk( pidReglaNegocio, pidPlan));
	}

	public List<DominioMensajeUsuario> core(SeguridadUsuarioActual usuarioActual,String accion,ReglaNegocioPlan reglaNegocioPlan) {
		List<DominioMensajeUsuario> lst = new ArrayList<DominioMensajeUsuario>();
		if (accion.equals(ConstantePantallaAccion.INSERTAR))
			return coreInsertar(usuarioActual, reglaNegocioPlan);
		if (accion.equals(ConstantePantallaAccion.ACTUALIZAR))
			return coreActualizar(usuarioActual, reglaNegocioPlan);
		if (accion.equals(ConstantePantallaAccion.ANULAR))
			return coreAnular(usuarioActual, reglaNegocioPlan);
		if (accion.equals(ConstantePantallaAccion.ELIMINAR))
			return coreEliminar(usuarioActual, reglaNegocioPlan);
		return lst;
	}

}
