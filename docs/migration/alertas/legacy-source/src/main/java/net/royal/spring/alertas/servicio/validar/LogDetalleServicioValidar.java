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

import net.royal.spring.alertas.dao.impl.LogDetalleDaoImpl;
import net.royal.spring.alertas.dominio.LogDetalle;
import net.royal.spring.alertas.dominio.LogDetallePk;
import net.royal.spring.framework.constante.ConstantePantallaAccion;
import net.royal.spring.framework.modelo.generico.DominioMensajeUsuario;
import net.royal.spring.framework.modelo.seguridad.SeguridadUsuarioActual;
import net.royal.spring.framework.web.servicio.impl.GenericoServicioValidar;
@SuppressWarnings("rawtypes")
@Service (value = "BeanValidarLogDetalle")
public class LogDetalleServicioValidar extends GenericoServicioValidar {
	public static String SPRING_NOMBRE = "BeanValidarLogDetalle";

	@Autowired
	private LogDetalleDaoImpl logDetalleDao;

	private LogDetalle prepararBasico(SeguridadUsuarioActual usuarioActual,LogDetalle logDetalle, Boolean flgInsertar) {
		if (flgInsertar) {
			logDetalle.setCreacionTerminal(usuarioActual.getDireccionIp());
			logDetalle.setCreacionFecha(new Date());
			logDetalle.setCreacionUsuario(usuarioActual.getUsuario());
		} else {
			logDetalle.setModificacionTerminal(usuarioActual.getDireccionIp());
			logDetalle.setModificacionFecha(new Date());
			logDetalle.setModificacionUsuario(usuarioActual.getUsuario());
		}
		
		// TODO LogDetalle : valores por defecto comunes Insert/Actualizar/Anular/Eliminar
		
		return logDetalle;
	}

	public LogDetalle prepararInsertar(SeguridadUsuarioActual usuarioActual,LogDetalle logDetalle) {
		if (logDetalle == null)
			return logDetalle;
		if (logDetalle.getAuxFlgPreparado())
			return logDetalle;
		logDetalle = prepararBasico(usuarioActual,logDetalle, true);
		logDetalle.setAuxFlgPreparado(Boolean.TRUE);
		
		// TODO LogDetalle.Insertar : valores por defecto
		
		return logDetalle;
	}

	public LogDetalle prepararActualizar(SeguridadUsuarioActual usuarioActual,LogDetalle logDetalle) {
		if (logDetalle == null)
			return logDetalle;
		if (logDetalle.getAuxFlgPreparado())
			return logDetalle;
		logDetalle = prepararBasico(usuarioActual,logDetalle, false);
		logDetalle.setAuxFlgPreparado(Boolean.TRUE);
		
		// TODO LogDetalle.Actualizar : valores por defecto
		
		return logDetalle;
	}

	public LogDetalle prepararAnular(SeguridadUsuarioActual usuarioActual,LogDetalle logDetalle) {
		if (logDetalle == null)
			return logDetalle;
		if (logDetalle.getAuxFlgPreparado())
			return logDetalle;
		logDetalle = prepararBasico(usuarioActual, logDetalle, false);
		logDetalle.setAuxFlgPreparado(Boolean.TRUE);
		
		// TODO LogDetalle.Anular : valores por defecto
		
		return logDetalle;
	}

	public LogDetalle prepararEliminar(SeguridadUsuarioActual usuarioActual,LogDetalle logDetalle) {
		if (logDetalle == null)
			return logDetalle;
		if (logDetalle.getAuxFlgPreparado())
			return logDetalle;
		logDetalle.setAuxFlgPreparado(Boolean.TRUE);
		
		// TODO LogDetalle.Eliminar : valores por defecto
		
		return logDetalle;
	}

	private List<DominioMensajeUsuario> coreBasico(SeguridadUsuarioActual usuarioActual, LogDetalle logDetalle) {
		List<DominioMensajeUsuario> lst = new ArrayList<DominioMensajeUsuario>();
		if (usuarioActual == null)
			lst.add(this.getMsjUsuarioError(SeguridadUsuarioActual.CONSTRAINTS_NOTNULL));
		if (logDetalle == null)
			lst.add(this.getMsjUsuarioError("logdetalle.constraints.notnull"));
		if (!lst.isEmpty())
			return lst;
		Validator validator = Validation.buildDefaultValidatorFactory().getValidator();
		if (logDetalle.getPk() != null) {
			Set<ConstraintViolation<LogDetallePk>> reglasPk = validator.validate(logDetalle.getPk());
			for (ConstraintViolation constraint : reglasPk) {
				lst.add(this.getMsjUsuarioError(constraint));
			}
		}
		Set<ConstraintViolation<LogDetalle>> reglas = validator.validate(logDetalle);
		for (ConstraintViolation constraint : reglas) {
			lst.add(this.getMsjUsuarioError(constraint));
		}
		
		// TODO LogDetalle : validaciones comunes Insert/Actualizar
		
		return lst;
	}

	public List<DominioMensajeUsuario> coreInsertar(SeguridadUsuarioActual usuarioActual,LogDetalle logDetalle) {
		logDetalle = prepararInsertar(usuarioActual, logDetalle);
		List<DominioMensajeUsuario> lst = coreBasico(usuarioActual, logDetalle);
		if (!lst.isEmpty())
			return lst;
		
		// TODO LogDetalle.Insertar : validaciones
		
		return lst;
	}

	public List<DominioMensajeUsuario> coreActualizar(SeguridadUsuarioActual usuarioActual, LogDetalle logDetalle) {
		logDetalle = prepararActualizar(usuarioActual, logDetalle);
		List<DominioMensajeUsuario> lst = coreBasico(usuarioActual, logDetalle);
		if (!lst.isEmpty())
			return lst;
		
		// TODO LogDetalle.Actualizar : validaciones
		
		return lst;
	}

	public List<DominioMensajeUsuario> coreEliminar(SeguridadUsuarioActual usuarioActual,LogDetalle logDetalle) {
		logDetalle = prepararEliminar(usuarioActual, logDetalle);
		List<DominioMensajeUsuario> lst = new ArrayList<DominioMensajeUsuario>();
		
		// TODO LogDetalle.Eliminar : validaciones
		
		return lst;
	}

	public List<DominioMensajeUsuario> coreEliminar(SeguridadUsuarioActual usuarioActual,LogDetallePk pk) {
		LogDetalle bean = logDetalleDao.obtenerPorId(pk);
		return coreEliminar(usuarioActual,bean);
	}

	public List<DominioMensajeUsuario> coreEliminar(SeguridadUsuarioActual usuarioActual,Integer pidLogAlerta,Integer pnroRegistro,String pnombreCampo) {
		return coreEliminar(usuarioActual,new LogDetallePk( pidLogAlerta, pnroRegistro, pnombreCampo));
	}

	public List<DominioMensajeUsuario> coreAnular(SeguridadUsuarioActual usuarioActual, LogDetalle logDetalle) {
		logDetalle = prepararAnular(usuarioActual, logDetalle);
		List<DominioMensajeUsuario> lst = new ArrayList<DominioMensajeUsuario>();
		
		// TODO LogDetalle.Anular : validaciones
		
		return lst;
	}

	public List<DominioMensajeUsuario> coreAnular(SeguridadUsuarioActual usuarioActual, LogDetallePk pk) {
		LogDetalle bean = logDetalleDao.obtenerPorId(pk);
		return coreAnular(usuarioActual,bean);
	}

	public List<DominioMensajeUsuario> coreAnular(SeguridadUsuarioActual usuarioActual, Integer pidLogAlerta,Integer pnroRegistro,String pnombreCampo) {
		return coreAnular(usuarioActual,new LogDetallePk( pidLogAlerta, pnroRegistro, pnombreCampo));
	}

	public List<DominioMensajeUsuario> core(SeguridadUsuarioActual usuarioActual,String accion,LogDetalle logDetalle) {
		List<DominioMensajeUsuario> lst = new ArrayList<DominioMensajeUsuario>();
		if (accion.equals(ConstantePantallaAccion.INSERTAR))
			return coreInsertar(usuarioActual, logDetalle);
		if (accion.equals(ConstantePantallaAccion.ACTUALIZAR))
			return coreActualizar(usuarioActual, logDetalle);
		if (accion.equals(ConstantePantallaAccion.ANULAR))
			return coreAnular(usuarioActual, logDetalle);
		if (accion.equals(ConstantePantallaAccion.ELIMINAR))
			return coreEliminar(usuarioActual, logDetalle);
		return lst;
	}

}
