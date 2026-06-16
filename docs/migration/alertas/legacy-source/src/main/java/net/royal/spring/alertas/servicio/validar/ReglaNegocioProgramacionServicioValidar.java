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

import net.royal.spring.alertas.dao.impl.ReglaNegocioProgramacionDaoImpl;
import net.royal.spring.alertas.dominio.ReglaNegocioProgramacion;
import net.royal.spring.alertas.dominio.ReglaNegocioProgramacionPk;
import net.royal.spring.framework.constante.ConstantePantallaAccion;
import net.royal.spring.framework.modelo.generico.DominioMensajeUsuario;
import net.royal.spring.framework.modelo.seguridad.SeguridadUsuarioActual;
import net.royal.spring.framework.web.servicio.impl.GenericoServicioValidar;
@SuppressWarnings("rawtypes")
@Service (value = "BeanValidarReglaNegocioProgramacion")
public class ReglaNegocioProgramacionServicioValidar extends GenericoServicioValidar {
	public static String SPRING_NOMBRE = "BeanValidarReglaNegocioProgramacion";

	@Autowired
	private ReglaNegocioProgramacionDaoImpl reglaNegocioProgramacionDao;

	private ReglaNegocioProgramacion prepararBasico(SeguridadUsuarioActual usuarioActual,ReglaNegocioProgramacion reglaNegocioProgramacion, Boolean flgInsertar) {
		if (flgInsertar) {
			reglaNegocioProgramacion.setCreacionTerminal(usuarioActual.getDireccionIp());
			reglaNegocioProgramacion.setCreacionFecha(new Date());
			reglaNegocioProgramacion.setCreacionUsuario(usuarioActual.getUsuario());
		} else {
			reglaNegocioProgramacion.setModificacionTerminal(usuarioActual.getDireccionIp());
			reglaNegocioProgramacion.setModificacionFecha(new Date());
			reglaNegocioProgramacion.setModificacionUsuario(usuarioActual.getUsuario());
		}
		
		// TODO ReglaNegocioProgramacion : valores por defecto comunes Insert/Actualizar/Anular/Eliminar
		
		return reglaNegocioProgramacion;
	}

	public ReglaNegocioProgramacion prepararInsertar(SeguridadUsuarioActual usuarioActual,ReglaNegocioProgramacion reglaNegocioProgramacion) {
		if (reglaNegocioProgramacion == null)
			return reglaNegocioProgramacion;
		if (reglaNegocioProgramacion.getAuxFlgPreparado())
			return reglaNegocioProgramacion;
		reglaNegocioProgramacion = prepararBasico(usuarioActual,reglaNegocioProgramacion, true);
		reglaNegocioProgramacion.setAuxFlgPreparado(Boolean.TRUE);
		
		// TODO ReglaNegocioProgramacion.Insertar : valores por defecto
		
		return reglaNegocioProgramacion;
	}

	public ReglaNegocioProgramacion prepararActualizar(SeguridadUsuarioActual usuarioActual,ReglaNegocioProgramacion reglaNegocioProgramacion) {
		if (reglaNegocioProgramacion == null)
			return reglaNegocioProgramacion;
		if (reglaNegocioProgramacion.getAuxFlgPreparado())
			return reglaNegocioProgramacion;
		reglaNegocioProgramacion = prepararBasico(usuarioActual,reglaNegocioProgramacion, false);
		reglaNegocioProgramacion.setAuxFlgPreparado(Boolean.TRUE);
		
		// TODO ReglaNegocioProgramacion.Actualizar : valores por defecto
		
		return reglaNegocioProgramacion;
	}

	public ReglaNegocioProgramacion prepararAnular(SeguridadUsuarioActual usuarioActual,ReglaNegocioProgramacion reglaNegocioProgramacion) {
		if (reglaNegocioProgramacion == null)
			return reglaNegocioProgramacion;
		if (reglaNegocioProgramacion.getAuxFlgPreparado())
			return reglaNegocioProgramacion;
		reglaNegocioProgramacion = prepararBasico(usuarioActual, reglaNegocioProgramacion, false);
		reglaNegocioProgramacion.setAuxFlgPreparado(Boolean.TRUE);
		
		// TODO ReglaNegocioProgramacion.Anular : valores por defecto
		
		return reglaNegocioProgramacion;
	}

	public ReglaNegocioProgramacion prepararEliminar(SeguridadUsuarioActual usuarioActual,ReglaNegocioProgramacion reglaNegocioProgramacion) {
		if (reglaNegocioProgramacion == null)
			return reglaNegocioProgramacion;
		if (reglaNegocioProgramacion.getAuxFlgPreparado())
			return reglaNegocioProgramacion;
		reglaNegocioProgramacion.setAuxFlgPreparado(Boolean.TRUE);
		
		// TODO ReglaNegocioProgramacion.Eliminar : valores por defecto
		
		return reglaNegocioProgramacion;
	}

	private List<DominioMensajeUsuario> coreBasico(SeguridadUsuarioActual usuarioActual, ReglaNegocioProgramacion reglaNegocioProgramacion) {
		List<DominioMensajeUsuario> lst = new ArrayList<DominioMensajeUsuario>();
		if (usuarioActual == null)
			lst.add(this.getMsjUsuarioError(SeguridadUsuarioActual.CONSTRAINTS_NOTNULL));
		if (reglaNegocioProgramacion == null)
			lst.add(this.getMsjUsuarioError("reglanegocioprogramacion.constraints.notnull"));
		if (!lst.isEmpty())
			return lst;
		Validator validator = Validation.buildDefaultValidatorFactory().getValidator();
		if (reglaNegocioProgramacion.getPk() != null) {
			Set<ConstraintViolation<ReglaNegocioProgramacionPk>> reglasPk = validator.validate(reglaNegocioProgramacion.getPk());
			for (ConstraintViolation constraint : reglasPk) {
				lst.add(this.getMsjUsuarioError(constraint));
			}
		}
		Set<ConstraintViolation<ReglaNegocioProgramacion>> reglas = validator.validate(reglaNegocioProgramacion);
		for (ConstraintViolation constraint : reglas) {
			lst.add(this.getMsjUsuarioError(constraint));
		}
		
		// TODO ReglaNegocioProgramacion : validaciones comunes Insert/Actualizar
		
		return lst;
	}

	public List<DominioMensajeUsuario> coreInsertar(SeguridadUsuarioActual usuarioActual,ReglaNegocioProgramacion reglaNegocioProgramacion) {
		reglaNegocioProgramacion = prepararInsertar(usuarioActual, reglaNegocioProgramacion);
		List<DominioMensajeUsuario> lst = coreBasico(usuarioActual, reglaNegocioProgramacion);
		if (!lst.isEmpty())
			return lst;
		
		// TODO ReglaNegocioProgramacion.Insertar : validaciones
		
		return lst;
	}

	public List<DominioMensajeUsuario> coreActualizar(SeguridadUsuarioActual usuarioActual, ReglaNegocioProgramacion reglaNegocioProgramacion) {
		reglaNegocioProgramacion = prepararActualizar(usuarioActual, reglaNegocioProgramacion);
		List<DominioMensajeUsuario> lst = coreBasico(usuarioActual, reglaNegocioProgramacion);
		if (!lst.isEmpty())
			return lst;
		
		// TODO ReglaNegocioProgramacion.Actualizar : validaciones
		
		return lst;
	}

	public List<DominioMensajeUsuario> coreEliminar(SeguridadUsuarioActual usuarioActual,ReglaNegocioProgramacion reglaNegocioProgramacion) {
		reglaNegocioProgramacion = prepararEliminar(usuarioActual, reglaNegocioProgramacion);
		List<DominioMensajeUsuario> lst = new ArrayList<DominioMensajeUsuario>();
		
		// TODO ReglaNegocioProgramacion.Eliminar : validaciones
		
		return lst;
	}

	public List<DominioMensajeUsuario> coreEliminar(SeguridadUsuarioActual usuarioActual,ReglaNegocioProgramacionPk pk) {
		ReglaNegocioProgramacion bean = reglaNegocioProgramacionDao.obtenerPorId(pk);
		return coreEliminar(usuarioActual,bean);
	}

	public List<DominioMensajeUsuario> coreEliminar(SeguridadUsuarioActual usuarioActual,Integer pidReglaNegocio,Integer pidProgramacion) {
		return coreEliminar(usuarioActual,new ReglaNegocioProgramacionPk( pidReglaNegocio, pidProgramacion));
	}

	public List<DominioMensajeUsuario> coreAnular(SeguridadUsuarioActual usuarioActual, ReglaNegocioProgramacion reglaNegocioProgramacion) {
		reglaNegocioProgramacion = prepararAnular(usuarioActual, reglaNegocioProgramacion);
		List<DominioMensajeUsuario> lst = new ArrayList<DominioMensajeUsuario>();
		
		// TODO ReglaNegocioProgramacion.Anular : validaciones
		
		return lst;
	}

	public List<DominioMensajeUsuario> coreAnular(SeguridadUsuarioActual usuarioActual, ReglaNegocioProgramacionPk pk) {
		ReglaNegocioProgramacion bean = reglaNegocioProgramacionDao.obtenerPorId(pk);
		return coreAnular(usuarioActual,bean);
	}

	public List<DominioMensajeUsuario> coreAnular(SeguridadUsuarioActual usuarioActual, Integer pidReglaNegocio,Integer pidProgramacion) {
		return coreAnular(usuarioActual,new ReglaNegocioProgramacionPk( pidReglaNegocio, pidProgramacion));
	}

	public List<DominioMensajeUsuario> core(SeguridadUsuarioActual usuarioActual,String accion,ReglaNegocioProgramacion reglaNegocioProgramacion) {
		List<DominioMensajeUsuario> lst = new ArrayList<DominioMensajeUsuario>();
		if (accion.equals(ConstantePantallaAccion.INSERTAR))
			return coreInsertar(usuarioActual, reglaNegocioProgramacion);
		if (accion.equals(ConstantePantallaAccion.ACTUALIZAR))
			return coreActualizar(usuarioActual, reglaNegocioProgramacion);
		if (accion.equals(ConstantePantallaAccion.ANULAR))
			return coreAnular(usuarioActual, reglaNegocioProgramacion);
		if (accion.equals(ConstantePantallaAccion.ELIMINAR))
			return coreEliminar(usuarioActual, reglaNegocioProgramacion);
		return lst;
	}

}
