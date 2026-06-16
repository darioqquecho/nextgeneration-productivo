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

import net.royal.spring.alertas.dao.impl.ConfiguracionServicioDaoImpl;
import net.royal.spring.alertas.dominio.ConfiguracionServicio;
import net.royal.spring.alertas.dominio.ConfiguracionServicioPk;
import net.royal.spring.framework.constante.ConstantePantallaAccion;
import net.royal.spring.framework.modelo.generico.DominioMensajeUsuario;
import net.royal.spring.framework.modelo.seguridad.SeguridadUsuarioActual;
import net.royal.spring.framework.web.servicio.impl.GenericoServicioValidar;
@SuppressWarnings("rawtypes")
@Service (value = "BeanValidarConfiguracionServicio")
public class ConfiguracionServicioServicioValidar extends GenericoServicioValidar {
	public static String SPRING_NOMBRE = "BeanValidarConfiguracionServicio";

	@Autowired
	private ConfiguracionServicioDaoImpl configuracionServicioDao;

	private ConfiguracionServicio prepararBasico(SeguridadUsuarioActual usuarioActual,ConfiguracionServicio configuracionServicio, Boolean flgInsertar) {
		if (flgInsertar) {
			configuracionServicio.setCreacionTerminal(usuarioActual.getDireccionIp());
			configuracionServicio.setCreacionFecha(new Date());
			configuracionServicio.setCreacionUsuario(usuarioActual.getUsuario());
		} else {
			configuracionServicio.setModificacionTerminal(usuarioActual.getDireccionIp());
			configuracionServicio.setModificacionFecha(new Date());
			configuracionServicio.setModificacionUsuario(usuarioActual.getUsuario());
		}
		
		// TODO ConfiguracionServicio : valores por defecto comunes Insert/Actualizar/Anular/Eliminar
		
		return configuracionServicio;
	}

	public ConfiguracionServicio prepararInsertar(SeguridadUsuarioActual usuarioActual,ConfiguracionServicio configuracionServicio) {
		if (configuracionServicio == null)
			return configuracionServicio;
		if (configuracionServicio.getAuxFlgPreparado())
			return configuracionServicio;
		configuracionServicio = prepararBasico(usuarioActual,configuracionServicio, true);
		configuracionServicio.setAuxFlgPreparado(Boolean.TRUE);
		
		// TODO ConfiguracionServicio.Insertar : valores por defecto
		
		return configuracionServicio;
	}

	public ConfiguracionServicio prepararActualizar(SeguridadUsuarioActual usuarioActual,ConfiguracionServicio configuracionServicio) {
		if (configuracionServicio == null)
			return configuracionServicio;
		if (configuracionServicio.getAuxFlgPreparado())
			return configuracionServicio;
		configuracionServicio = prepararBasico(usuarioActual,configuracionServicio, false);
		configuracionServicio.setAuxFlgPreparado(Boolean.TRUE);
		
		// TODO ConfiguracionServicio.Actualizar : valores por defecto
		
		return configuracionServicio;
	}

	public ConfiguracionServicio prepararAnular(SeguridadUsuarioActual usuarioActual,ConfiguracionServicio configuracionServicio) {
		if (configuracionServicio == null)
			return configuracionServicio;
		if (configuracionServicio.getAuxFlgPreparado())
			return configuracionServicio;
		configuracionServicio = prepararBasico(usuarioActual, configuracionServicio, false);
		configuracionServicio.setAuxFlgPreparado(Boolean.TRUE);
		
		// TODO ConfiguracionServicio.Anular : valores por defecto
		
		return configuracionServicio;
	}

	public ConfiguracionServicio prepararEliminar(SeguridadUsuarioActual usuarioActual,ConfiguracionServicio configuracionServicio) {
		if (configuracionServicio == null)
			return configuracionServicio;
		if (configuracionServicio.getAuxFlgPreparado())
			return configuracionServicio;
		configuracionServicio.setAuxFlgPreparado(Boolean.TRUE);
		
		// TODO ConfiguracionServicio.Eliminar : valores por defecto
		
		return configuracionServicio;
	}

	private List<DominioMensajeUsuario> coreBasico(SeguridadUsuarioActual usuarioActual, ConfiguracionServicio configuracionServicio) {
		List<DominioMensajeUsuario> lst = new ArrayList<DominioMensajeUsuario>();
		if (usuarioActual == null)
			lst.add(this.getMsjUsuarioError(SeguridadUsuarioActual.CONSTRAINTS_NOTNULL));
		if (configuracionServicio == null)
			lst.add(this.getMsjUsuarioError("configuracionservicio.constraints.notnull"));
		if (!lst.isEmpty())
			return lst;
		Validator validator = Validation.buildDefaultValidatorFactory().getValidator();
		if (configuracionServicio.getPk() != null) {
			Set<ConstraintViolation<ConfiguracionServicioPk>> reglasPk = validator.validate(configuracionServicio.getPk());
			for (ConstraintViolation constraint : reglasPk) {
				lst.add(this.getMsjUsuarioError(constraint));
			}
		}
		Set<ConstraintViolation<ConfiguracionServicio>> reglas = validator.validate(configuracionServicio);
		for (ConstraintViolation constraint : reglas) {
			lst.add(this.getMsjUsuarioError(constraint));
		}
		
		// TODO ConfiguracionServicio : validaciones comunes Insert/Actualizar
		
		return lst;
	}

	public List<DominioMensajeUsuario> coreInsertar(SeguridadUsuarioActual usuarioActual,ConfiguracionServicio configuracionServicio) {
		configuracionServicio = prepararInsertar(usuarioActual, configuracionServicio);
		List<DominioMensajeUsuario> lst = coreBasico(usuarioActual, configuracionServicio);
		if (!lst.isEmpty())
			return lst;
		
		// TODO ConfiguracionServicio.Insertar : validaciones
		
		return lst;
	}

	public List<DominioMensajeUsuario> coreActualizar(SeguridadUsuarioActual usuarioActual, ConfiguracionServicio configuracionServicio) {
		configuracionServicio = prepararActualizar(usuarioActual, configuracionServicio);
		List<DominioMensajeUsuario> lst = coreBasico(usuarioActual, configuracionServicio);
		if (!lst.isEmpty())
			return lst;
		
		// TODO ConfiguracionServicio.Actualizar : validaciones
		
		return lst;
	}

	public List<DominioMensajeUsuario> coreEliminar(SeguridadUsuarioActual usuarioActual,ConfiguracionServicio configuracionServicio) {
		configuracionServicio = prepararEliminar(usuarioActual, configuracionServicio);
		List<DominioMensajeUsuario> lst = new ArrayList<DominioMensajeUsuario>();
		
		// TODO ConfiguracionServicio.Eliminar : validaciones
		
		return lst;
	}

	public List<DominioMensajeUsuario> coreEliminar(SeguridadUsuarioActual usuarioActual,ConfiguracionServicioPk pk) {
		ConfiguracionServicio bean = configuracionServicioDao.obtenerPorId(pk);
		return coreEliminar(usuarioActual,bean);
	}

	public List<DominioMensajeUsuario> coreEliminar(SeguridadUsuarioActual usuarioActual,Integer pidConfiguracionServicio) {
		return coreEliminar(usuarioActual,new ConfiguracionServicioPk( pidConfiguracionServicio));
	}

	public List<DominioMensajeUsuario> coreAnular(SeguridadUsuarioActual usuarioActual, ConfiguracionServicio configuracionServicio) {
		configuracionServicio = prepararAnular(usuarioActual, configuracionServicio);
		List<DominioMensajeUsuario> lst = new ArrayList<DominioMensajeUsuario>();
		
		// TODO ConfiguracionServicio.Anular : validaciones
		
		return lst;
	}

	public List<DominioMensajeUsuario> coreAnular(SeguridadUsuarioActual usuarioActual, ConfiguracionServicioPk pk) {
		ConfiguracionServicio bean = configuracionServicioDao.obtenerPorId(pk);
		return coreAnular(usuarioActual,bean);
	}

	public List<DominioMensajeUsuario> coreAnular(SeguridadUsuarioActual usuarioActual, Integer pidConfiguracionServicio) {
		return coreAnular(usuarioActual,new ConfiguracionServicioPk( pidConfiguracionServicio));
	}

	public List<DominioMensajeUsuario> core(SeguridadUsuarioActual usuarioActual,String accion,ConfiguracionServicio configuracionServicio) {
		List<DominioMensajeUsuario> lst = new ArrayList<DominioMensajeUsuario>();
		if (accion.equals(ConstantePantallaAccion.INSERTAR))
			return coreInsertar(usuarioActual, configuracionServicio);
		if (accion.equals(ConstantePantallaAccion.ACTUALIZAR))
			return coreActualizar(usuarioActual, configuracionServicio);
		if (accion.equals(ConstantePantallaAccion.ANULAR))
			return coreAnular(usuarioActual, configuracionServicio);
		if (accion.equals(ConstantePantallaAccion.ELIMINAR))
			return coreEliminar(usuarioActual, configuracionServicio);
		return lst;
	}

}
