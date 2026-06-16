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

import net.royal.spring.alertas.dao.impl.TipoDestinoDaoImpl;
import net.royal.spring.alertas.dominio.TipoDestino;
import net.royal.spring.alertas.dominio.TipoDestinoPk;
import net.royal.spring.framework.constante.ConstantePantallaAccion;
import net.royal.spring.framework.modelo.generico.DominioMensajeUsuario;
import net.royal.spring.framework.modelo.seguridad.SeguridadUsuarioActual;
import net.royal.spring.framework.web.servicio.impl.GenericoServicioValidar;
@SuppressWarnings("rawtypes")
@Service (value = "BeanValidarTipoDestino")
public class TipoDestinoServicioValidar extends GenericoServicioValidar {
	public static String SPRING_NOMBRE = "BeanValidarTipoDestino";

	@Autowired
	private TipoDestinoDaoImpl tipoDestinoDao;

	private TipoDestino prepararBasico(SeguridadUsuarioActual usuarioActual,TipoDestino tipoDestino, Boolean flgInsertar) {
		if (flgInsertar) {
			tipoDestino.setCreacionTerminal(usuarioActual.getDireccionIp());
			tipoDestino.setCreacionFecha(new Date());
			tipoDestino.setCreacionUsuario(usuarioActual.getUsuario());
		} else {
			tipoDestino.setModificacionTerminal(usuarioActual.getDireccionIp());
			tipoDestino.setModificacionFecha(new Date());
			tipoDestino.setModificacionUsuario(usuarioActual.getUsuario());
		}
		
		// TODO TipoDestino : valores por defecto comunes Insert/Actualizar/Anular/Eliminar
		
		return tipoDestino;
	}

	public TipoDestino prepararInsertar(SeguridadUsuarioActual usuarioActual,TipoDestino tipoDestino) {
		if (tipoDestino == null)
			return tipoDestino;
		if (tipoDestino.getAuxFlgPreparado())
			return tipoDestino;
		tipoDestino = prepararBasico(usuarioActual,tipoDestino, true);
		tipoDestino.setAuxFlgPreparado(Boolean.TRUE);
		
		// TODO TipoDestino.Insertar : valores por defecto
		
		return tipoDestino;
	}

	public TipoDestino prepararActualizar(SeguridadUsuarioActual usuarioActual,TipoDestino tipoDestino) {
		if (tipoDestino == null)
			return tipoDestino;
		if (tipoDestino.getAuxFlgPreparado())
			return tipoDestino;
		tipoDestino = prepararBasico(usuarioActual,tipoDestino, false);
		tipoDestino.setAuxFlgPreparado(Boolean.TRUE);
		
		// TODO TipoDestino.Actualizar : valores por defecto
		
		return tipoDestino;
	}

	public TipoDestino prepararAnular(SeguridadUsuarioActual usuarioActual,TipoDestino tipoDestino) {
		if (tipoDestino == null)
			return tipoDestino;
		if (tipoDestino.getAuxFlgPreparado())
			return tipoDestino;
		tipoDestino = prepararBasico(usuarioActual, tipoDestino, false);
		tipoDestino.setAuxFlgPreparado(Boolean.TRUE);
		
		// TODO TipoDestino.Anular : valores por defecto
		
		return tipoDestino;
	}

	public TipoDestino prepararEliminar(SeguridadUsuarioActual usuarioActual,TipoDestino tipoDestino) {
		if (tipoDestino == null)
			return tipoDestino;
		if (tipoDestino.getAuxFlgPreparado())
			return tipoDestino;
		tipoDestino.setAuxFlgPreparado(Boolean.TRUE);
		
		// TODO TipoDestino.Eliminar : valores por defecto
		
		return tipoDestino;
	}

	private List<DominioMensajeUsuario> coreBasico(SeguridadUsuarioActual usuarioActual, TipoDestino tipoDestino) {
		List<DominioMensajeUsuario> lst = new ArrayList<DominioMensajeUsuario>();
		if (usuarioActual == null)
			lst.add(this.getMsjUsuarioError(SeguridadUsuarioActual.CONSTRAINTS_NOTNULL));
		if (tipoDestino == null)
			lst.add(this.getMsjUsuarioError("tipodestino.constraints.notnull"));
		if (!lst.isEmpty())
			return lst;
		Validator validator = Validation.buildDefaultValidatorFactory().getValidator();
		if (tipoDestino.getPk() != null) {
			Set<ConstraintViolation<TipoDestinoPk>> reglasPk = validator.validate(tipoDestino.getPk());
			for (ConstraintViolation constraint : reglasPk) {
				lst.add(this.getMsjUsuarioError(constraint));
			}
		}
		Set<ConstraintViolation<TipoDestino>> reglas = validator.validate(tipoDestino);
		for (ConstraintViolation constraint : reglas) {
			lst.add(this.getMsjUsuarioError(constraint));
		}
		
		// TODO TipoDestino : validaciones comunes Insert/Actualizar
		
		return lst;
	}

	public List<DominioMensajeUsuario> coreInsertar(SeguridadUsuarioActual usuarioActual,TipoDestino tipoDestino) {
		tipoDestino = prepararInsertar(usuarioActual, tipoDestino);
		List<DominioMensajeUsuario> lst = coreBasico(usuarioActual, tipoDestino);
		if (!lst.isEmpty())
			return lst;
		
		// TODO TipoDestino.Insertar : validaciones
		
		return lst;
	}

	public List<DominioMensajeUsuario> coreActualizar(SeguridadUsuarioActual usuarioActual, TipoDestino tipoDestino) {
		tipoDestino = prepararActualizar(usuarioActual, tipoDestino);
		List<DominioMensajeUsuario> lst = coreBasico(usuarioActual, tipoDestino);
		if (!lst.isEmpty())
			return lst;
		
		// TODO TipoDestino.Actualizar : validaciones
		
		return lst;
	}

	public List<DominioMensajeUsuario> coreEliminar(SeguridadUsuarioActual usuarioActual,TipoDestino tipoDestino) {
		tipoDestino = prepararEliminar(usuarioActual, tipoDestino);
		List<DominioMensajeUsuario> lst = new ArrayList<DominioMensajeUsuario>();
		
		// TODO TipoDestino.Eliminar : validaciones
		
		return lst;
	}

	public List<DominioMensajeUsuario> coreEliminar(SeguridadUsuarioActual usuarioActual,TipoDestinoPk pk) {
		TipoDestino bean = tipoDestinoDao.obtenerPorId(pk);
		return coreEliminar(usuarioActual,bean);
	}

	public List<DominioMensajeUsuario> coreEliminar(SeguridadUsuarioActual usuarioActual,String pidTipoDestino) {
		return coreEliminar(usuarioActual,new TipoDestinoPk( pidTipoDestino));
	}

	public List<DominioMensajeUsuario> coreAnular(SeguridadUsuarioActual usuarioActual, TipoDestino tipoDestino) {
		tipoDestino = prepararAnular(usuarioActual, tipoDestino);
		List<DominioMensajeUsuario> lst = new ArrayList<DominioMensajeUsuario>();
		
		// TODO TipoDestino.Anular : validaciones
		
		return lst;
	}

	public List<DominioMensajeUsuario> coreAnular(SeguridadUsuarioActual usuarioActual, TipoDestinoPk pk) {
		TipoDestino bean = tipoDestinoDao.obtenerPorId(pk);
		return coreAnular(usuarioActual,bean);
	}

	public List<DominioMensajeUsuario> coreAnular(SeguridadUsuarioActual usuarioActual, String pidTipoDestino) {
		return coreAnular(usuarioActual,new TipoDestinoPk( pidTipoDestino));
	}

	public List<DominioMensajeUsuario> core(SeguridadUsuarioActual usuarioActual,String accion,TipoDestino tipoDestino) {
		List<DominioMensajeUsuario> lst = new ArrayList<DominioMensajeUsuario>();
		if (accion.equals(ConstantePantallaAccion.INSERTAR))
			return coreInsertar(usuarioActual, tipoDestino);
		if (accion.equals(ConstantePantallaAccion.ACTUALIZAR))
			return coreActualizar(usuarioActual, tipoDestino);
		if (accion.equals(ConstantePantallaAccion.ANULAR))
			return coreAnular(usuarioActual, tipoDestino);
		if (accion.equals(ConstantePantallaAccion.ELIMINAR))
			return coreEliminar(usuarioActual, tipoDestino);
		return lst;
	}

}
