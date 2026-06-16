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

import net.royal.spring.alertas.dao.impl.ReglaNegocioDetalleDaoImpl;
import net.royal.spring.alertas.dominio.ReglaNegocioDetalle;
import net.royal.spring.alertas.dominio.ReglaNegocioDetallePk;
import net.royal.spring.framework.constante.ConstantePantallaAccion;
import net.royal.spring.framework.modelo.generico.DominioMensajeUsuario;
import net.royal.spring.framework.modelo.seguridad.SeguridadUsuarioActual;
import net.royal.spring.framework.web.servicio.impl.GenericoServicioValidar;
@SuppressWarnings("rawtypes")
@Service (value = "BeanValidarReglaNegocioDetalle")
public class ReglaNegocioDetalleServicioValidar extends GenericoServicioValidar {
	public static String SPRING_NOMBRE = "BeanValidarReglaNegocioDetalle";

	@Autowired
	private ReglaNegocioDetalleDaoImpl reglaNegocioDetalleDao;

	private ReglaNegocioDetalle prepararBasico(SeguridadUsuarioActual usuarioActual,ReglaNegocioDetalle reglaNegocioDetalle, Boolean flgInsertar) {
		if (flgInsertar) {
			reglaNegocioDetalle.setCreacionTerminal(usuarioActual.getDireccionIp());
			reglaNegocioDetalle.setCreacionFecha(new Date());
			reglaNegocioDetalle.setCreacionUsuario(usuarioActual.getUsuario());
		} else {
			reglaNegocioDetalle.setModificacionTerminal(usuarioActual.getDireccionIp());
			reglaNegocioDetalle.setModificacionFecha(new Date());
			reglaNegocioDetalle.setModificacionUsuario(usuarioActual.getUsuario());
		}
		
		// TODO ReglaNegocioDetalle : valores por defecto comunes Insert/Actualizar/Anular/Eliminar
		
		return reglaNegocioDetalle;
	}

	public ReglaNegocioDetalle prepararInsertar(SeguridadUsuarioActual usuarioActual,ReglaNegocioDetalle reglaNegocioDetalle) {
		if (reglaNegocioDetalle == null)
			return reglaNegocioDetalle;
		if (reglaNegocioDetalle.getAuxFlgPreparado())
			return reglaNegocioDetalle;
		reglaNegocioDetalle = prepararBasico(usuarioActual,reglaNegocioDetalle, true);
		reglaNegocioDetalle.setAuxFlgPreparado(Boolean.TRUE);
		
		// TODO ReglaNegocioDetalle.Insertar : valores por defecto
		
		return reglaNegocioDetalle;
	}

	public ReglaNegocioDetalle prepararActualizar(SeguridadUsuarioActual usuarioActual,ReglaNegocioDetalle reglaNegocioDetalle) {
		if (reglaNegocioDetalle == null)
			return reglaNegocioDetalle;
		if (reglaNegocioDetalle.getAuxFlgPreparado())
			return reglaNegocioDetalle;
		reglaNegocioDetalle = prepararBasico(usuarioActual,reglaNegocioDetalle, false);
		reglaNegocioDetalle.setAuxFlgPreparado(Boolean.TRUE);
		
		// TODO ReglaNegocioDetalle.Actualizar : valores por defecto
		
		return reglaNegocioDetalle;
	}

	public ReglaNegocioDetalle prepararAnular(SeguridadUsuarioActual usuarioActual,ReglaNegocioDetalle reglaNegocioDetalle) {
		if (reglaNegocioDetalle == null)
			return reglaNegocioDetalle;
		if (reglaNegocioDetalle.getAuxFlgPreparado())
			return reglaNegocioDetalle;
		reglaNegocioDetalle = prepararBasico(usuarioActual, reglaNegocioDetalle, false);
		reglaNegocioDetalle.setAuxFlgPreparado(Boolean.TRUE);
		
		// TODO ReglaNegocioDetalle.Anular : valores por defecto
		
		return reglaNegocioDetalle;
	}

	public ReglaNegocioDetalle prepararEliminar(SeguridadUsuarioActual usuarioActual,ReglaNegocioDetalle reglaNegocioDetalle) {
		if (reglaNegocioDetalle == null)
			return reglaNegocioDetalle;
		if (reglaNegocioDetalle.getAuxFlgPreparado())
			return reglaNegocioDetalle;
		reglaNegocioDetalle.setAuxFlgPreparado(Boolean.TRUE);
		
		// TODO ReglaNegocioDetalle.Eliminar : valores por defecto
		
		return reglaNegocioDetalle;
	}

	private List<DominioMensajeUsuario> coreBasico(SeguridadUsuarioActual usuarioActual, ReglaNegocioDetalle reglaNegocioDetalle) {
		List<DominioMensajeUsuario> lst = new ArrayList<DominioMensajeUsuario>();
		if (usuarioActual == null)
			lst.add(this.getMsjUsuarioError(SeguridadUsuarioActual.CONSTRAINTS_NOTNULL));
		if (reglaNegocioDetalle == null)
			lst.add(this.getMsjUsuarioError("reglanegociodetalle.constraints.notnull"));
		if (!lst.isEmpty())
			return lst;
		Validator validator = Validation.buildDefaultValidatorFactory().getValidator();
		if (reglaNegocioDetalle.getPk() != null) {
			Set<ConstraintViolation<ReglaNegocioDetallePk>> reglasPk = validator.validate(reglaNegocioDetalle.getPk());
			for (ConstraintViolation constraint : reglasPk) {
				lst.add(this.getMsjUsuarioError(constraint));
			}
		}
		Set<ConstraintViolation<ReglaNegocioDetalle>> reglas = validator.validate(reglaNegocioDetalle);
		for (ConstraintViolation constraint : reglas) {
			lst.add(this.getMsjUsuarioError(constraint));
		}
		
		// TODO ReglaNegocioDetalle : validaciones comunes Insert/Actualizar
		
		return lst;
	}

	public List<DominioMensajeUsuario> coreInsertar(SeguridadUsuarioActual usuarioActual,ReglaNegocioDetalle reglaNegocioDetalle) {
		reglaNegocioDetalle = prepararInsertar(usuarioActual, reglaNegocioDetalle);
		List<DominioMensajeUsuario> lst = coreBasico(usuarioActual, reglaNegocioDetalle);
		if (!lst.isEmpty())
			return lst;
		
		// TODO ReglaNegocioDetalle.Insertar : validaciones
		
		return lst;
	}

	public List<DominioMensajeUsuario> coreActualizar(SeguridadUsuarioActual usuarioActual, ReglaNegocioDetalle reglaNegocioDetalle) {
		reglaNegocioDetalle = prepararActualizar(usuarioActual, reglaNegocioDetalle);
		List<DominioMensajeUsuario> lst = coreBasico(usuarioActual, reglaNegocioDetalle);
		if (!lst.isEmpty())
			return lst;
		
		// TODO ReglaNegocioDetalle.Actualizar : validaciones
		
		return lst;
	}

	public List<DominioMensajeUsuario> coreEliminar(SeguridadUsuarioActual usuarioActual,ReglaNegocioDetalle reglaNegocioDetalle) {
		reglaNegocioDetalle = prepararEliminar(usuarioActual, reglaNegocioDetalle);
		List<DominioMensajeUsuario> lst = new ArrayList<DominioMensajeUsuario>();
		
		// TODO ReglaNegocioDetalle.Eliminar : validaciones
		
		return lst;
	}

	public List<DominioMensajeUsuario> coreEliminar(SeguridadUsuarioActual usuarioActual,ReglaNegocioDetallePk pk) {
		ReglaNegocioDetalle bean = reglaNegocioDetalleDao.obtenerPorId(pk);
		return coreEliminar(usuarioActual,bean);
	}

	public List<DominioMensajeUsuario> coreEliminar(SeguridadUsuarioActual usuarioActual,Integer pidReglaNegocio,Integer pidDetalle) {
		return coreEliminar(usuarioActual,new ReglaNegocioDetallePk( pidReglaNegocio, pidDetalle));
	}

	public List<DominioMensajeUsuario> coreAnular(SeguridadUsuarioActual usuarioActual, ReglaNegocioDetalle reglaNegocioDetalle) {
		reglaNegocioDetalle = prepararAnular(usuarioActual, reglaNegocioDetalle);
		List<DominioMensajeUsuario> lst = new ArrayList<DominioMensajeUsuario>();
		
		// TODO ReglaNegocioDetalle.Anular : validaciones
		
		return lst;
	}

	public List<DominioMensajeUsuario> coreAnular(SeguridadUsuarioActual usuarioActual, ReglaNegocioDetallePk pk) {
		ReglaNegocioDetalle bean = reglaNegocioDetalleDao.obtenerPorId(pk);
		return coreAnular(usuarioActual,bean);
	}

	public List<DominioMensajeUsuario> coreAnular(SeguridadUsuarioActual usuarioActual, Integer pidReglaNegocio,Integer pidDetalle) {
		return coreAnular(usuarioActual,new ReglaNegocioDetallePk( pidReglaNegocio, pidDetalle));
	}

	public List<DominioMensajeUsuario> core(SeguridadUsuarioActual usuarioActual,String accion,ReglaNegocioDetalle reglaNegocioDetalle) {
		List<DominioMensajeUsuario> lst = new ArrayList<DominioMensajeUsuario>();
		if (accion.equals(ConstantePantallaAccion.INSERTAR))
			return coreInsertar(usuarioActual, reglaNegocioDetalle);
		if (accion.equals(ConstantePantallaAccion.ACTUALIZAR))
			return coreActualizar(usuarioActual, reglaNegocioDetalle);
		if (accion.equals(ConstantePantallaAccion.ANULAR))
			return coreAnular(usuarioActual, reglaNegocioDetalle);
		if (accion.equals(ConstantePantallaAccion.ELIMINAR))
			return coreEliminar(usuarioActual, reglaNegocioDetalle);
		return lst;
	}

}
