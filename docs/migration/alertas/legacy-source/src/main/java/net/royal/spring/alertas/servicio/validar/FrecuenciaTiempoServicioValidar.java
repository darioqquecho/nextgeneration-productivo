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

import net.royal.spring.alertas.dao.impl.FrecuenciaTiempoDaoImpl;
import net.royal.spring.alertas.dominio.FrecuenciaTiempo;
import net.royal.spring.alertas.dominio.FrecuenciaTiempoPk;
import net.royal.spring.framework.constante.ConstantePantallaAccion;
import net.royal.spring.framework.modelo.generico.DominioMensajeUsuario;
import net.royal.spring.framework.modelo.seguridad.SeguridadUsuarioActual;
import net.royal.spring.framework.web.servicio.impl.GenericoServicioValidar;
@SuppressWarnings("rawtypes")
@Service (value = "BeanValidarFrecuenciaTiempo")
public class FrecuenciaTiempoServicioValidar extends GenericoServicioValidar {
	public static String SPRING_NOMBRE = "BeanValidarFrecuenciaTiempo";

	@Autowired
	private FrecuenciaTiempoDaoImpl frecuenciaTiempoDao;

	private FrecuenciaTiempo prepararBasico(SeguridadUsuarioActual usuarioActual,FrecuenciaTiempo frecuenciaTiempo, Boolean flgInsertar) {
		if (flgInsertar) {
			frecuenciaTiempo.setCreacionTerminal(usuarioActual.getDireccionIp());
			frecuenciaTiempo.setCreacionFecha(new Date());
			frecuenciaTiempo.setCreacionUsuario(usuarioActual.getUsuario());
		} else {
			frecuenciaTiempo.setModificacionTerminal(usuarioActual.getDireccionIp());
			frecuenciaTiempo.setModificacionFecha(new Date());
			frecuenciaTiempo.setModificacionUsuario(usuarioActual.getUsuario());
		}
		
		// TODO FrecuenciaTiempo : valores por defecto comunes Insert/Actualizar/Anular/Eliminar
		
		return frecuenciaTiempo;
	}

	public FrecuenciaTiempo prepararInsertar(SeguridadUsuarioActual usuarioActual,FrecuenciaTiempo frecuenciaTiempo) {
		if (frecuenciaTiempo == null)
			return frecuenciaTiempo;
		if (frecuenciaTiempo.getAuxFlgPreparado())
			return frecuenciaTiempo;
		frecuenciaTiempo = prepararBasico(usuarioActual,frecuenciaTiempo, true);
		frecuenciaTiempo.setAuxFlgPreparado(Boolean.TRUE);
		
		// TODO FrecuenciaTiempo.Insertar : valores por defecto
		
		return frecuenciaTiempo;
	}

	public FrecuenciaTiempo prepararActualizar(SeguridadUsuarioActual usuarioActual,FrecuenciaTiempo frecuenciaTiempo) {
		if (frecuenciaTiempo == null)
			return frecuenciaTiempo;
		if (frecuenciaTiempo.getAuxFlgPreparado())
			return frecuenciaTiempo;
		frecuenciaTiempo = prepararBasico(usuarioActual,frecuenciaTiempo, false);
		frecuenciaTiempo.setAuxFlgPreparado(Boolean.TRUE);
		
		// TODO FrecuenciaTiempo.Actualizar : valores por defecto
		
		return frecuenciaTiempo;
	}

	public FrecuenciaTiempo prepararAnular(SeguridadUsuarioActual usuarioActual,FrecuenciaTiempo frecuenciaTiempo) {
		if (frecuenciaTiempo == null)
			return frecuenciaTiempo;
		if (frecuenciaTiempo.getAuxFlgPreparado())
			return frecuenciaTiempo;
		frecuenciaTiempo = prepararBasico(usuarioActual, frecuenciaTiempo, false);
		frecuenciaTiempo.setAuxFlgPreparado(Boolean.TRUE);
		
		// TODO FrecuenciaTiempo.Anular : valores por defecto
		
		return frecuenciaTiempo;
	}

	public FrecuenciaTiempo prepararEliminar(SeguridadUsuarioActual usuarioActual,FrecuenciaTiempo frecuenciaTiempo) {
		if (frecuenciaTiempo == null)
			return frecuenciaTiempo;
		if (frecuenciaTiempo.getAuxFlgPreparado())
			return frecuenciaTiempo;
		frecuenciaTiempo.setAuxFlgPreparado(Boolean.TRUE);
		
		// TODO FrecuenciaTiempo.Eliminar : valores por defecto
		
		return frecuenciaTiempo;
	}

	private List<DominioMensajeUsuario> coreBasico(SeguridadUsuarioActual usuarioActual, FrecuenciaTiempo frecuenciaTiempo) {
		List<DominioMensajeUsuario> lst = new ArrayList<DominioMensajeUsuario>();
		if (usuarioActual == null)
			lst.add(this.getMsjUsuarioError(SeguridadUsuarioActual.CONSTRAINTS_NOTNULL));
		if (frecuenciaTiempo == null)
			lst.add(this.getMsjUsuarioError("frecuenciatiempo.constraints.notnull"));
		if (!lst.isEmpty())
			return lst;
		Validator validator = Validation.buildDefaultValidatorFactory().getValidator();
		if (frecuenciaTiempo.getPk() != null) {
			Set<ConstraintViolation<FrecuenciaTiempoPk>> reglasPk = validator.validate(frecuenciaTiempo.getPk());
			for (ConstraintViolation constraint : reglasPk) {
				lst.add(this.getMsjUsuarioError(constraint));
			}
		}
		Set<ConstraintViolation<FrecuenciaTiempo>> reglas = validator.validate(frecuenciaTiempo);
		for (ConstraintViolation constraint : reglas) {
			lst.add(this.getMsjUsuarioError(constraint));
		}
		
		// TODO FrecuenciaTiempo : validaciones comunes Insert/Actualizar
		
		return lst;
	}

	public List<DominioMensajeUsuario> coreInsertar(SeguridadUsuarioActual usuarioActual,FrecuenciaTiempo frecuenciaTiempo) {
		frecuenciaTiempo = prepararInsertar(usuarioActual, frecuenciaTiempo);
		List<DominioMensajeUsuario> lst = coreBasico(usuarioActual, frecuenciaTiempo);
		if (!lst.isEmpty())
			return lst;
		
		// TODO FrecuenciaTiempo.Insertar : validaciones
		
		return lst;
	}

	public List<DominioMensajeUsuario> coreActualizar(SeguridadUsuarioActual usuarioActual, FrecuenciaTiempo frecuenciaTiempo) {
		frecuenciaTiempo = prepararActualizar(usuarioActual, frecuenciaTiempo);
		List<DominioMensajeUsuario> lst = coreBasico(usuarioActual, frecuenciaTiempo);
		if (!lst.isEmpty())
			return lst;
		
		// TODO FrecuenciaTiempo.Actualizar : validaciones
		
		return lst;
	}

	public List<DominioMensajeUsuario> coreEliminar(SeguridadUsuarioActual usuarioActual,FrecuenciaTiempo frecuenciaTiempo) {
		frecuenciaTiempo = prepararEliminar(usuarioActual, frecuenciaTiempo);
		List<DominioMensajeUsuario> lst = new ArrayList<DominioMensajeUsuario>();
		
		// TODO FrecuenciaTiempo.Eliminar : validaciones
		
		return lst;
	}

	public List<DominioMensajeUsuario> coreEliminar(SeguridadUsuarioActual usuarioActual,FrecuenciaTiempoPk pk) {
		FrecuenciaTiempo bean = frecuenciaTiempoDao.obtenerPorId(pk);
		return coreEliminar(usuarioActual,bean);
	}

	public List<DominioMensajeUsuario> coreEliminar(SeguridadUsuarioActual usuarioActual,String pidFrecuenciaTiempo) {
		return coreEliminar(usuarioActual,new FrecuenciaTiempoPk( pidFrecuenciaTiempo));
	}

	public List<DominioMensajeUsuario> coreAnular(SeguridadUsuarioActual usuarioActual, FrecuenciaTiempo frecuenciaTiempo) {
		frecuenciaTiempo = prepararAnular(usuarioActual, frecuenciaTiempo);
		List<DominioMensajeUsuario> lst = new ArrayList<DominioMensajeUsuario>();
		
		// TODO FrecuenciaTiempo.Anular : validaciones
		
		return lst;
	}

	public List<DominioMensajeUsuario> coreAnular(SeguridadUsuarioActual usuarioActual, FrecuenciaTiempoPk pk) {
		FrecuenciaTiempo bean = frecuenciaTiempoDao.obtenerPorId(pk);
		return coreAnular(usuarioActual,bean);
	}

	public List<DominioMensajeUsuario> coreAnular(SeguridadUsuarioActual usuarioActual, String pidFrecuenciaTiempo) {
		return coreAnular(usuarioActual,new FrecuenciaTiempoPk( pidFrecuenciaTiempo));
	}

	public List<DominioMensajeUsuario> core(SeguridadUsuarioActual usuarioActual,String accion,FrecuenciaTiempo frecuenciaTiempo) {
		List<DominioMensajeUsuario> lst = new ArrayList<DominioMensajeUsuario>();
		if (accion.equals(ConstantePantallaAccion.INSERTAR))
			return coreInsertar(usuarioActual, frecuenciaTiempo);
		if (accion.equals(ConstantePantallaAccion.ACTUALIZAR))
			return coreActualizar(usuarioActual, frecuenciaTiempo);
		if (accion.equals(ConstantePantallaAccion.ANULAR))
			return coreAnular(usuarioActual, frecuenciaTiempo);
		if (accion.equals(ConstantePantallaAccion.ELIMINAR))
			return coreEliminar(usuarioActual, frecuenciaTiempo);
		return lst;
	}

}
