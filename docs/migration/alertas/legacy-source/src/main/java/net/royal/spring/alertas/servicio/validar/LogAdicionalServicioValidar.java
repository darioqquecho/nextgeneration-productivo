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

import net.royal.spring.alertas.dao.impl.LogAdicionalDaoImpl;
import net.royal.spring.alertas.dominio.LogAdicional;
import net.royal.spring.alertas.dominio.LogAdicionalPk;
import net.royal.spring.framework.constante.ConstantePantallaAccion;
import net.royal.spring.framework.modelo.generico.DominioMensajeUsuario;
import net.royal.spring.framework.modelo.seguridad.SeguridadUsuarioActual;
import net.royal.spring.framework.web.servicio.impl.GenericoServicioValidar;
@SuppressWarnings("rawtypes")
@Service (value = "BeanValidarLogAdicional")
public class LogAdicionalServicioValidar extends GenericoServicioValidar {
	public static String SPRING_NOMBRE = "BeanValidarLogAdicional";

	@Autowired
	private LogAdicionalDaoImpl logAdicionalDao;

	private LogAdicional prepararBasico(SeguridadUsuarioActual usuarioActual,LogAdicional logAdicional, Boolean flgInsertar) {
		if (flgInsertar) {
			logAdicional.setCreacionTerminal(usuarioActual.getDireccionIp());
			logAdicional.setCreacionFecha(new Date());
			logAdicional.setCreacionUsuario(usuarioActual.getUsuario());
		} else {
			logAdicional.setModificacionTerminal(usuarioActual.getDireccionIp());
			logAdicional.setModificacionFecha(new Date());
			logAdicional.setModificacionUsuario(usuarioActual.getUsuario());
		}
		
		// TODO LogAdicional : valores por defecto comunes Insert/Actualizar/Anular/Eliminar
		
		return logAdicional;
	}

	public LogAdicional prepararInsertar(SeguridadUsuarioActual usuarioActual,LogAdicional logAdicional) {
		if (logAdicional == null)
			return logAdicional;
		if (logAdicional.getAuxFlgPreparado())
			return logAdicional;
		logAdicional = prepararBasico(usuarioActual,logAdicional, true);
		logAdicional.setAuxFlgPreparado(Boolean.TRUE);
		
		// TODO LogAdicional.Insertar : valores por defecto
		
		return logAdicional;
	}

	public LogAdicional prepararActualizar(SeguridadUsuarioActual usuarioActual,LogAdicional logAdicional) {
		if (logAdicional == null)
			return logAdicional;
		if (logAdicional.getAuxFlgPreparado())
			return logAdicional;
		logAdicional = prepararBasico(usuarioActual,logAdicional, false);
		logAdicional.setAuxFlgPreparado(Boolean.TRUE);
		
		// TODO LogAdicional.Actualizar : valores por defecto
		
		return logAdicional;
	}

	public LogAdicional prepararAnular(SeguridadUsuarioActual usuarioActual,LogAdicional logAdicional) {
		if (logAdicional == null)
			return logAdicional;
		if (logAdicional.getAuxFlgPreparado())
			return logAdicional;
		logAdicional = prepararBasico(usuarioActual, logAdicional, false);
		logAdicional.setAuxFlgPreparado(Boolean.TRUE);
		
		// TODO LogAdicional.Anular : valores por defecto
		
		return logAdicional;
	}

	public LogAdicional prepararEliminar(SeguridadUsuarioActual usuarioActual,LogAdicional logAdicional) {
		if (logAdicional == null)
			return logAdicional;
		if (logAdicional.getAuxFlgPreparado())
			return logAdicional;
		logAdicional.setAuxFlgPreparado(Boolean.TRUE);
		
		// TODO LogAdicional.Eliminar : valores por defecto
		
		return logAdicional;
	}

	private List<DominioMensajeUsuario> coreBasico(SeguridadUsuarioActual usuarioActual, LogAdicional logAdicional) {
		List<DominioMensajeUsuario> lst = new ArrayList<DominioMensajeUsuario>();
		if (usuarioActual == null)
			lst.add(this.getMsjUsuarioError(SeguridadUsuarioActual.CONSTRAINTS_NOTNULL));
		if (logAdicional == null)
			lst.add(this.getMsjUsuarioError("logadicional.constraints.notnull"));
		if (!lst.isEmpty())
			return lst;
		Validator validator = Validation.buildDefaultValidatorFactory().getValidator();
		if (logAdicional.getPk() != null) {
			Set<ConstraintViolation<LogAdicionalPk>> reglasPk = validator.validate(logAdicional.getPk());
			for (ConstraintViolation constraint : reglasPk) {
				lst.add(this.getMsjUsuarioError(constraint));
			}
		}
		Set<ConstraintViolation<LogAdicional>> reglas = validator.validate(logAdicional);
		for (ConstraintViolation constraint : reglas) {
			lst.add(this.getMsjUsuarioError(constraint));
		}
		
		// TODO LogAdicional : validaciones comunes Insert/Actualizar
		
		return lst;
	}

	public List<DominioMensajeUsuario> coreInsertar(SeguridadUsuarioActual usuarioActual,LogAdicional logAdicional) {
		logAdicional = prepararInsertar(usuarioActual, logAdicional);
		List<DominioMensajeUsuario> lst = coreBasico(usuarioActual, logAdicional);
		if (!lst.isEmpty())
			return lst;
		
		// TODO LogAdicional.Insertar : validaciones
		
		return lst;
	}

	public List<DominioMensajeUsuario> coreActualizar(SeguridadUsuarioActual usuarioActual, LogAdicional logAdicional) {
		logAdicional = prepararActualizar(usuarioActual, logAdicional);
		List<DominioMensajeUsuario> lst = coreBasico(usuarioActual, logAdicional);
		if (!lst.isEmpty())
			return lst;
		
		// TODO LogAdicional.Actualizar : validaciones
		
		return lst;
	}

	public List<DominioMensajeUsuario> coreEliminar(SeguridadUsuarioActual usuarioActual,LogAdicional logAdicional) {
		logAdicional = prepararEliminar(usuarioActual, logAdicional);
		List<DominioMensajeUsuario> lst = new ArrayList<DominioMensajeUsuario>();
		
		// TODO LogAdicional.Eliminar : validaciones
		
		return lst;
	}

	public List<DominioMensajeUsuario> coreEliminar(SeguridadUsuarioActual usuarioActual,LogAdicionalPk pk) {
		LogAdicional bean = logAdicionalDao.obtenerPorId(pk);
		return coreEliminar(usuarioActual,bean);
	}

	public List<DominioMensajeUsuario> coreEliminar(SeguridadUsuarioActual usuarioActual,Integer pidLogAlerta,Integer pnroRegistro,String pnombreCampo) {
		return coreEliminar(usuarioActual,new LogAdicionalPk( pidLogAlerta, pnroRegistro, pnombreCampo));
	}

	public List<DominioMensajeUsuario> coreAnular(SeguridadUsuarioActual usuarioActual, LogAdicional logAdicional) {
		logAdicional = prepararAnular(usuarioActual, logAdicional);
		List<DominioMensajeUsuario> lst = new ArrayList<DominioMensajeUsuario>();
		
		// TODO LogAdicional.Anular : validaciones
		
		return lst;
	}

	public List<DominioMensajeUsuario> coreAnular(SeguridadUsuarioActual usuarioActual, LogAdicionalPk pk) {
		LogAdicional bean = logAdicionalDao.obtenerPorId(pk);
		return coreAnular(usuarioActual,bean);
	}

	public List<DominioMensajeUsuario> coreAnular(SeguridadUsuarioActual usuarioActual, Integer pidLogAlerta,Integer pnroRegistro,String pnombreCampo) {
		return coreAnular(usuarioActual,new LogAdicionalPk( pidLogAlerta, pnroRegistro, pnombreCampo));
	}

	public List<DominioMensajeUsuario> core(SeguridadUsuarioActual usuarioActual,String accion,LogAdicional logAdicional) {
		List<DominioMensajeUsuario> lst = new ArrayList<DominioMensajeUsuario>();
		if (accion.equals(ConstantePantallaAccion.INSERTAR))
			return coreInsertar(usuarioActual, logAdicional);
		if (accion.equals(ConstantePantallaAccion.ACTUALIZAR))
			return coreActualizar(usuarioActual, logAdicional);
		if (accion.equals(ConstantePantallaAccion.ANULAR))
			return coreAnular(usuarioActual, logAdicional);
		if (accion.equals(ConstantePantallaAccion.ELIMINAR))
			return coreEliminar(usuarioActual, logAdicional);
		return lst;
	}

}
