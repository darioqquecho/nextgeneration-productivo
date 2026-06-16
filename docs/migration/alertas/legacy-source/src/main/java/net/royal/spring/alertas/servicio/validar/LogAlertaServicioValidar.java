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

import net.royal.spring.alertas.dao.impl.LogAlertaDaoImpl;
import net.royal.spring.alertas.dominio.LogAlerta;
import net.royal.spring.alertas.dominio.LogAlertaPk;
import net.royal.spring.framework.constante.ConstantePantallaAccion;
import net.royal.spring.framework.modelo.generico.DominioMensajeUsuario;
import net.royal.spring.framework.modelo.seguridad.SeguridadUsuarioActual;
import net.royal.spring.framework.web.servicio.impl.GenericoServicioValidar;
@SuppressWarnings("rawtypes")
@Service (value = "BeanValidarLogAlerta")
public class LogAlertaServicioValidar extends GenericoServicioValidar {
	public static String SPRING_NOMBRE = "BeanValidarLogAlerta";

	@Autowired
	private LogAlertaDaoImpl logAlertaDao;

	private LogAlerta prepararBasico(SeguridadUsuarioActual usuarioActual,LogAlerta logAlerta, Boolean flgInsertar) {
		if (flgInsertar) {
			logAlerta.setCreacionTerminal(usuarioActual.getDireccionIp());
			logAlerta.setCreacionFecha(new Date());
			logAlerta.setCreacionUsuario(usuarioActual.getUsuario());
		} else {
			logAlerta.setModificacionTerminal(usuarioActual.getDireccionIp());
			logAlerta.setModificacionFecha(new Date());
			logAlerta.setModificacionUsuario(usuarioActual.getUsuario());
		}
		
		// TODO LogAlerta : valores por defecto comunes Insert/Actualizar/Anular/Eliminar
		
		return logAlerta;
	}

	public LogAlerta prepararInsertar(SeguridadUsuarioActual usuarioActual,LogAlerta logAlerta) {
		if (logAlerta == null)
			return logAlerta;
		if (logAlerta.getAuxFlgPreparado())
			return logAlerta;
		logAlerta = prepararBasico(usuarioActual,logAlerta, true);
		logAlerta.setAuxFlgPreparado(Boolean.TRUE);
		
		// TODO LogAlerta.Insertar : valores por defecto
		
		return logAlerta;
	}

	public LogAlerta prepararActualizar(SeguridadUsuarioActual usuarioActual,LogAlerta logAlerta) {
		if (logAlerta == null)
			return logAlerta;
		if (logAlerta.getAuxFlgPreparado())
			return logAlerta;
		logAlerta = prepararBasico(usuarioActual,logAlerta, false);
		logAlerta.setAuxFlgPreparado(Boolean.TRUE);
		
		// TODO LogAlerta.Actualizar : valores por defecto
		
		return logAlerta;
	}

	public LogAlerta prepararAnular(SeguridadUsuarioActual usuarioActual,LogAlerta logAlerta) {
		if (logAlerta == null)
			return logAlerta;
		if (logAlerta.getAuxFlgPreparado())
			return logAlerta;
		logAlerta = prepararBasico(usuarioActual, logAlerta, false);
		logAlerta.setAuxFlgPreparado(Boolean.TRUE);
		
		// TODO LogAlerta.Anular : valores por defecto
		
		return logAlerta;
	}

	public LogAlerta prepararEliminar(SeguridadUsuarioActual usuarioActual,LogAlerta logAlerta) {
		if (logAlerta == null)
			return logAlerta;
		if (logAlerta.getAuxFlgPreparado())
			return logAlerta;
		logAlerta.setAuxFlgPreparado(Boolean.TRUE);
		
		// TODO LogAlerta.Eliminar : valores por defecto
		
		return logAlerta;
	}

	private List<DominioMensajeUsuario> coreBasico(SeguridadUsuarioActual usuarioActual, LogAlerta logAlerta) {
		List<DominioMensajeUsuario> lst = new ArrayList<DominioMensajeUsuario>();
		if (usuarioActual == null)
			lst.add(this.getMsjUsuarioError(SeguridadUsuarioActual.CONSTRAINTS_NOTNULL));
		if (logAlerta == null)
			lst.add(this.getMsjUsuarioError("logalerta.constraints.notnull"));
		if (!lst.isEmpty())
			return lst;
		Validator validator = Validation.buildDefaultValidatorFactory().getValidator();
		if (logAlerta.getPk() != null) {
			Set<ConstraintViolation<LogAlertaPk>> reglasPk = validator.validate(logAlerta.getPk());
			for (ConstraintViolation constraint : reglasPk) {
				lst.add(this.getMsjUsuarioError(constraint));
			}
		}
		Set<ConstraintViolation<LogAlerta>> reglas = validator.validate(logAlerta);
		for (ConstraintViolation constraint : reglas) {
			lst.add(this.getMsjUsuarioError(constraint));
		}
		
		// TODO LogAlerta : validaciones comunes Insert/Actualizar
		
		return lst;
	}

	public List<DominioMensajeUsuario> coreInsertar(SeguridadUsuarioActual usuarioActual,LogAlerta logAlerta) {
		logAlerta = prepararInsertar(usuarioActual, logAlerta);
		List<DominioMensajeUsuario> lst = coreBasico(usuarioActual, logAlerta);
		if (!lst.isEmpty())
			return lst;
		
		// TODO LogAlerta.Insertar : validaciones
		
		return lst;
	}

	public List<DominioMensajeUsuario> coreActualizar(SeguridadUsuarioActual usuarioActual, LogAlerta logAlerta) {
		logAlerta = prepararActualizar(usuarioActual, logAlerta);
		List<DominioMensajeUsuario> lst = coreBasico(usuarioActual, logAlerta);
		if (!lst.isEmpty())
			return lst;
		
		// TODO LogAlerta.Actualizar : validaciones
		
		return lst;
	}

	public List<DominioMensajeUsuario> coreEliminar(SeguridadUsuarioActual usuarioActual,LogAlerta logAlerta) {
		logAlerta = prepararEliminar(usuarioActual, logAlerta);
		List<DominioMensajeUsuario> lst = new ArrayList<DominioMensajeUsuario>();
		
		// TODO LogAlerta.Eliminar : validaciones
		
		return lst;
	}

	public List<DominioMensajeUsuario> coreEliminar(SeguridadUsuarioActual usuarioActual,LogAlertaPk pk) {
		LogAlerta bean = logAlertaDao.obtenerPorId(pk);
		return coreEliminar(usuarioActual,bean);
	}

	public List<DominioMensajeUsuario> coreEliminar(SeguridadUsuarioActual usuarioActual,Integer pidLogAlerta) {
		return coreEliminar(usuarioActual,new LogAlertaPk( pidLogAlerta));
	}

	public List<DominioMensajeUsuario> coreAnular(SeguridadUsuarioActual usuarioActual, LogAlerta logAlerta) {
		logAlerta = prepararAnular(usuarioActual, logAlerta);
		List<DominioMensajeUsuario> lst = new ArrayList<DominioMensajeUsuario>();
		
		// TODO LogAlerta.Anular : validaciones
		
		return lst;
	}

	public List<DominioMensajeUsuario> coreAnular(SeguridadUsuarioActual usuarioActual, LogAlertaPk pk) {
		LogAlerta bean = logAlertaDao.obtenerPorId(pk);
		return coreAnular(usuarioActual,bean);
	}

	public List<DominioMensajeUsuario> coreAnular(SeguridadUsuarioActual usuarioActual, Integer pidLogAlerta) {
		return coreAnular(usuarioActual,new LogAlertaPk( pidLogAlerta));
	}

	public List<DominioMensajeUsuario> core(SeguridadUsuarioActual usuarioActual,String accion,LogAlerta logAlerta) {
		List<DominioMensajeUsuario> lst = new ArrayList<DominioMensajeUsuario>();
		if (accion.equals(ConstantePantallaAccion.INSERTAR))
			return coreInsertar(usuarioActual, logAlerta);
		if (accion.equals(ConstantePantallaAccion.ACTUALIZAR))
			return coreActualizar(usuarioActual, logAlerta);
		if (accion.equals(ConstantePantallaAccion.ANULAR))
			return coreAnular(usuarioActual, logAlerta);
		if (accion.equals(ConstantePantallaAccion.ELIMINAR))
			return coreEliminar(usuarioActual, logAlerta);
		return lst;
	}

}
