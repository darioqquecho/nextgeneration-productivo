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

import net.royal.spring.alertas.dao.impl.FrecuenciaProgramacionDaoImpl;
import net.royal.spring.alertas.dominio.FrecuenciaProgramacion;
import net.royal.spring.alertas.dominio.FrecuenciaProgramacionPk;
import net.royal.spring.framework.constante.ConstantePantallaAccion;
import net.royal.spring.framework.modelo.generico.DominioMensajeUsuario;
import net.royal.spring.framework.modelo.seguridad.SeguridadUsuarioActual;
import net.royal.spring.framework.web.servicio.impl.GenericoServicioValidar;
@SuppressWarnings("rawtypes")
@Service (value = "BeanValidarFrecuenciaProgramacion")
public class FrecuenciaProgramacionServicioValidar extends GenericoServicioValidar {
	public static String SPRING_NOMBRE = "BeanValidarFrecuenciaProgramacion";

	@Autowired
	private FrecuenciaProgramacionDaoImpl frecuenciaProgramacionDao;

	private FrecuenciaProgramacion prepararBasico(SeguridadUsuarioActual usuarioActual,FrecuenciaProgramacion frecuenciaProgramacion, Boolean flgInsertar) {
		if (flgInsertar) {
			frecuenciaProgramacion.setCreacionTerminal(usuarioActual.getDireccionIp());
			frecuenciaProgramacion.setCreacionFecha(new Date());
			frecuenciaProgramacion.setCreacionUsuario(usuarioActual.getUsuario());
		} else {
			frecuenciaProgramacion.setModificacionTerminal(usuarioActual.getDireccionIp());
			frecuenciaProgramacion.setModificacionFecha(new Date());
			frecuenciaProgramacion.setModificacionUsuario(usuarioActual.getUsuario());
		}
		
		// TODO FrecuenciaProgramacion : valores por defecto comunes Insert/Actualizar/Anular/Eliminar
		
		return frecuenciaProgramacion;
	}

	public FrecuenciaProgramacion prepararInsertar(SeguridadUsuarioActual usuarioActual,FrecuenciaProgramacion frecuenciaProgramacion) {
		if (frecuenciaProgramacion == null)
			return frecuenciaProgramacion;
		if (frecuenciaProgramacion.getAuxFlgPreparado())
			return frecuenciaProgramacion;
		frecuenciaProgramacion = prepararBasico(usuarioActual,frecuenciaProgramacion, true);
		frecuenciaProgramacion.setAuxFlgPreparado(Boolean.TRUE);
		
		// TODO FrecuenciaProgramacion.Insertar : valores por defecto
		
		return frecuenciaProgramacion;
	}

	public FrecuenciaProgramacion prepararActualizar(SeguridadUsuarioActual usuarioActual,FrecuenciaProgramacion frecuenciaProgramacion) {
		if (frecuenciaProgramacion == null)
			return frecuenciaProgramacion;
		if (frecuenciaProgramacion.getAuxFlgPreparado())
			return frecuenciaProgramacion;
		frecuenciaProgramacion = prepararBasico(usuarioActual,frecuenciaProgramacion, false);
		frecuenciaProgramacion.setAuxFlgPreparado(Boolean.TRUE);
		
		// TODO FrecuenciaProgramacion.Actualizar : valores por defecto
		
		return frecuenciaProgramacion;
	}

	public FrecuenciaProgramacion prepararAnular(SeguridadUsuarioActual usuarioActual,FrecuenciaProgramacion frecuenciaProgramacion) {
		if (frecuenciaProgramacion == null)
			return frecuenciaProgramacion;
		if (frecuenciaProgramacion.getAuxFlgPreparado())
			return frecuenciaProgramacion;
		frecuenciaProgramacion = prepararBasico(usuarioActual, frecuenciaProgramacion, false);
		frecuenciaProgramacion.setAuxFlgPreparado(Boolean.TRUE);
		
		// TODO FrecuenciaProgramacion.Anular : valores por defecto
		
		return frecuenciaProgramacion;
	}

	public FrecuenciaProgramacion prepararEliminar(SeguridadUsuarioActual usuarioActual,FrecuenciaProgramacion frecuenciaProgramacion) {
		if (frecuenciaProgramacion == null)
			return frecuenciaProgramacion;
		if (frecuenciaProgramacion.getAuxFlgPreparado())
			return frecuenciaProgramacion;
		frecuenciaProgramacion.setAuxFlgPreparado(Boolean.TRUE);
		
		// TODO FrecuenciaProgramacion.Eliminar : valores por defecto
		
		return frecuenciaProgramacion;
	}

	private List<DominioMensajeUsuario> coreBasico(SeguridadUsuarioActual usuarioActual, FrecuenciaProgramacion frecuenciaProgramacion) {
		List<DominioMensajeUsuario> lst = new ArrayList<DominioMensajeUsuario>();
		if (usuarioActual == null)
			lst.add(this.getMsjUsuarioError(SeguridadUsuarioActual.CONSTRAINTS_NOTNULL));
		if (frecuenciaProgramacion == null)
			lst.add(this.getMsjUsuarioError("frecuenciaprogramacion.constraints.notnull"));
		if (!lst.isEmpty())
			return lst;
		Validator validator = Validation.buildDefaultValidatorFactory().getValidator();
		if (frecuenciaProgramacion.getPk() != null) {
			Set<ConstraintViolation<FrecuenciaProgramacionPk>> reglasPk = validator.validate(frecuenciaProgramacion.getPk());
			for (ConstraintViolation constraint : reglasPk) {
				lst.add(this.getMsjUsuarioError(constraint));
			}
		}
		Set<ConstraintViolation<FrecuenciaProgramacion>> reglas = validator.validate(frecuenciaProgramacion);
		for (ConstraintViolation constraint : reglas) {
			lst.add(this.getMsjUsuarioError(constraint));
		}
		
		// TODO FrecuenciaProgramacion : validaciones comunes Insert/Actualizar
		
		return lst;
	}

	public List<DominioMensajeUsuario> coreInsertar(SeguridadUsuarioActual usuarioActual,FrecuenciaProgramacion frecuenciaProgramacion) {
		frecuenciaProgramacion = prepararInsertar(usuarioActual, frecuenciaProgramacion);
		List<DominioMensajeUsuario> lst = coreBasico(usuarioActual, frecuenciaProgramacion);
		if (!lst.isEmpty())
			return lst;
		
		// TODO FrecuenciaProgramacion.Insertar : validaciones
		
		return lst;
	}

	public List<DominioMensajeUsuario> coreActualizar(SeguridadUsuarioActual usuarioActual, FrecuenciaProgramacion frecuenciaProgramacion) {
		frecuenciaProgramacion = prepararActualizar(usuarioActual, frecuenciaProgramacion);
		List<DominioMensajeUsuario> lst = coreBasico(usuarioActual, frecuenciaProgramacion);
		if (!lst.isEmpty())
			return lst;
		
		// TODO FrecuenciaProgramacion.Actualizar : validaciones
		
		return lst;
	}

	public List<DominioMensajeUsuario> coreEliminar(SeguridadUsuarioActual usuarioActual,FrecuenciaProgramacion frecuenciaProgramacion) {
		frecuenciaProgramacion = prepararEliminar(usuarioActual, frecuenciaProgramacion);
		List<DominioMensajeUsuario> lst = new ArrayList<DominioMensajeUsuario>();
		
		// TODO FrecuenciaProgramacion.Eliminar : validaciones
		
		return lst;
	}

	public List<DominioMensajeUsuario> coreEliminar(SeguridadUsuarioActual usuarioActual,FrecuenciaProgramacionPk pk) {
		FrecuenciaProgramacion bean = frecuenciaProgramacionDao.obtenerPorId(pk);
		return coreEliminar(usuarioActual,bean);
	}

	public List<DominioMensajeUsuario> coreEliminar(SeguridadUsuarioActual usuarioActual,String pidFrecuenciaProgramacion) {
		return coreEliminar(usuarioActual,new FrecuenciaProgramacionPk( pidFrecuenciaProgramacion));
	}

	public List<DominioMensajeUsuario> coreAnular(SeguridadUsuarioActual usuarioActual, FrecuenciaProgramacion frecuenciaProgramacion) {
		frecuenciaProgramacion = prepararAnular(usuarioActual, frecuenciaProgramacion);
		List<DominioMensajeUsuario> lst = new ArrayList<DominioMensajeUsuario>();
		
		// TODO FrecuenciaProgramacion.Anular : validaciones
		
		return lst;
	}

	public List<DominioMensajeUsuario> coreAnular(SeguridadUsuarioActual usuarioActual, FrecuenciaProgramacionPk pk) {
		FrecuenciaProgramacion bean = frecuenciaProgramacionDao.obtenerPorId(pk);
		return coreAnular(usuarioActual,bean);
	}

	public List<DominioMensajeUsuario> coreAnular(SeguridadUsuarioActual usuarioActual, String pidFrecuenciaProgramacion) {
		return coreAnular(usuarioActual,new FrecuenciaProgramacionPk( pidFrecuenciaProgramacion));
	}

	public List<DominioMensajeUsuario> core(SeguridadUsuarioActual usuarioActual,String accion,FrecuenciaProgramacion frecuenciaProgramacion) {
		List<DominioMensajeUsuario> lst = new ArrayList<DominioMensajeUsuario>();
		if (accion.equals(ConstantePantallaAccion.INSERTAR))
			return coreInsertar(usuarioActual, frecuenciaProgramacion);
		if (accion.equals(ConstantePantallaAccion.ACTUALIZAR))
			return coreActualizar(usuarioActual, frecuenciaProgramacion);
		if (accion.equals(ConstantePantallaAccion.ANULAR))
			return coreAnular(usuarioActual, frecuenciaProgramacion);
		if (accion.equals(ConstantePantallaAccion.ELIMINAR))
			return coreEliminar(usuarioActual, frecuenciaProgramacion);
		return lst;
	}

}
