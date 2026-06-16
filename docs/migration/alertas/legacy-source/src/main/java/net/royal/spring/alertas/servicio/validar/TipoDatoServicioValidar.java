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

import net.royal.spring.alertas.dao.impl.TipoDatoDaoImpl;
import net.royal.spring.alertas.dominio.TipoDato;
import net.royal.spring.alertas.dominio.TipoDatoPk;
import net.royal.spring.framework.constante.ConstantePantallaAccion;
import net.royal.spring.framework.modelo.generico.DominioMensajeUsuario;
import net.royal.spring.framework.modelo.seguridad.SeguridadUsuarioActual;
import net.royal.spring.framework.web.servicio.impl.GenericoServicioValidar;
@SuppressWarnings("rawtypes")
@Service (value = "BeanValidarTipoDato")
public class TipoDatoServicioValidar extends GenericoServicioValidar {
	public static String SPRING_NOMBRE = "BeanValidarTipoDato";

	@Autowired
	private TipoDatoDaoImpl tipoDatoDao;

	private TipoDato prepararBasico(SeguridadUsuarioActual usuarioActual,TipoDato tipoDato, Boolean flgInsertar) {
		if (flgInsertar) {
			tipoDato.setCreacionTerminal(usuarioActual.getDireccionIp());
			tipoDato.setCreacionFecha(new Date());
			tipoDato.setCreacionUsuario(usuarioActual.getUsuario());
		} else {
			tipoDato.setModificacionTerminal(usuarioActual.getDireccionIp());
			tipoDato.setModificacionFecha(new Date());
			tipoDato.setModificacionUsuario(usuarioActual.getUsuario());
		}
		
		// TODO TipoDato : valores por defecto comunes Insert/Actualizar/Anular/Eliminar
		
		return tipoDato;
	}

	public TipoDato prepararInsertar(SeguridadUsuarioActual usuarioActual,TipoDato tipoDato) {
		if (tipoDato == null)
			return tipoDato;
		if (tipoDato.getAuxFlgPreparado())
			return tipoDato;
		tipoDato = prepararBasico(usuarioActual,tipoDato, true);
		tipoDato.setAuxFlgPreparado(Boolean.TRUE);
		
		// TODO TipoDato.Insertar : valores por defecto
		
		return tipoDato;
	}

	public TipoDato prepararActualizar(SeguridadUsuarioActual usuarioActual,TipoDato tipoDato) {
		if (tipoDato == null)
			return tipoDato;
		if (tipoDato.getAuxFlgPreparado())
			return tipoDato;
		tipoDato = prepararBasico(usuarioActual,tipoDato, false);
		tipoDato.setAuxFlgPreparado(Boolean.TRUE);
		
		// TODO TipoDato.Actualizar : valores por defecto
		
		return tipoDato;
	}

	public TipoDato prepararAnular(SeguridadUsuarioActual usuarioActual,TipoDato tipoDato) {
		if (tipoDato == null)
			return tipoDato;
		if (tipoDato.getAuxFlgPreparado())
			return tipoDato;
		tipoDato = prepararBasico(usuarioActual, tipoDato, false);
		tipoDato.setAuxFlgPreparado(Boolean.TRUE);
		
		// TODO TipoDato.Anular : valores por defecto
		
		return tipoDato;
	}

	public TipoDato prepararEliminar(SeguridadUsuarioActual usuarioActual,TipoDato tipoDato) {
		if (tipoDato == null)
			return tipoDato;
		if (tipoDato.getAuxFlgPreparado())
			return tipoDato;
		tipoDato.setAuxFlgPreparado(Boolean.TRUE);
		
		// TODO TipoDato.Eliminar : valores por defecto
		
		return tipoDato;
	}

	private List<DominioMensajeUsuario> coreBasico(SeguridadUsuarioActual usuarioActual, TipoDato tipoDato) {
		List<DominioMensajeUsuario> lst = new ArrayList<DominioMensajeUsuario>();
		if (usuarioActual == null)
			lst.add(this.getMsjUsuarioError(SeguridadUsuarioActual.CONSTRAINTS_NOTNULL));
		if (tipoDato == null)
			lst.add(this.getMsjUsuarioError("tipodato.constraints.notnull"));
		if (!lst.isEmpty())
			return lst;
		Validator validator = Validation.buildDefaultValidatorFactory().getValidator();
		if (tipoDato.getPk() != null) {
			Set<ConstraintViolation<TipoDatoPk>> reglasPk = validator.validate(tipoDato.getPk());
			for (ConstraintViolation constraint : reglasPk) {
				lst.add(this.getMsjUsuarioError(constraint));
			}
		}
		Set<ConstraintViolation<TipoDato>> reglas = validator.validate(tipoDato);
		for (ConstraintViolation constraint : reglas) {
			lst.add(this.getMsjUsuarioError(constraint));
		}
		
		// TODO TipoDato : validaciones comunes Insert/Actualizar
		
		return lst;
	}

	public List<DominioMensajeUsuario> coreInsertar(SeguridadUsuarioActual usuarioActual,TipoDato tipoDato) {
		tipoDato = prepararInsertar(usuarioActual, tipoDato);
		List<DominioMensajeUsuario> lst = coreBasico(usuarioActual, tipoDato);
		if (!lst.isEmpty())
			return lst;
		
		// TODO TipoDato.Insertar : validaciones
		
		return lst;
	}

	public List<DominioMensajeUsuario> coreActualizar(SeguridadUsuarioActual usuarioActual, TipoDato tipoDato) {
		tipoDato = prepararActualizar(usuarioActual, tipoDato);
		List<DominioMensajeUsuario> lst = coreBasico(usuarioActual, tipoDato);
		if (!lst.isEmpty())
			return lst;
		
		// TODO TipoDato.Actualizar : validaciones
		
		return lst;
	}

	public List<DominioMensajeUsuario> coreEliminar(SeguridadUsuarioActual usuarioActual,TipoDato tipoDato) {
		tipoDato = prepararEliminar(usuarioActual, tipoDato);
		List<DominioMensajeUsuario> lst = new ArrayList<DominioMensajeUsuario>();
		
		// TODO TipoDato.Eliminar : validaciones
		
		return lst;
	}

	public List<DominioMensajeUsuario> coreEliminar(SeguridadUsuarioActual usuarioActual,TipoDatoPk pk) {
		TipoDato bean = tipoDatoDao.obtenerPorId(pk);
		return coreEliminar(usuarioActual,bean);
	}

	public List<DominioMensajeUsuario> coreEliminar(SeguridadUsuarioActual usuarioActual,String pidTipoDato) {
		return coreEliminar(usuarioActual,new TipoDatoPk( pidTipoDato));
	}

	public List<DominioMensajeUsuario> coreAnular(SeguridadUsuarioActual usuarioActual, TipoDato tipoDato) {
		tipoDato = prepararAnular(usuarioActual, tipoDato);
		List<DominioMensajeUsuario> lst = new ArrayList<DominioMensajeUsuario>();
		
		// TODO TipoDato.Anular : validaciones
		
		return lst;
	}

	public List<DominioMensajeUsuario> coreAnular(SeguridadUsuarioActual usuarioActual, TipoDatoPk pk) {
		TipoDato bean = tipoDatoDao.obtenerPorId(pk);
		return coreAnular(usuarioActual,bean);
	}

	public List<DominioMensajeUsuario> coreAnular(SeguridadUsuarioActual usuarioActual, String pidTipoDato) {
		return coreAnular(usuarioActual,new TipoDatoPk( pidTipoDato));
	}

	public List<DominioMensajeUsuario> core(SeguridadUsuarioActual usuarioActual,String accion,TipoDato tipoDato) {
		List<DominioMensajeUsuario> lst = new ArrayList<DominioMensajeUsuario>();
		if (accion.equals(ConstantePantallaAccion.INSERTAR))
			return coreInsertar(usuarioActual, tipoDato);
		if (accion.equals(ConstantePantallaAccion.ACTUALIZAR))
			return coreActualizar(usuarioActual, tipoDato);
		if (accion.equals(ConstantePantallaAccion.ANULAR))
			return coreAnular(usuarioActual, tipoDato);
		if (accion.equals(ConstantePantallaAccion.ELIMINAR))
			return coreEliminar(usuarioActual, tipoDato);
		return lst;
	}

}
