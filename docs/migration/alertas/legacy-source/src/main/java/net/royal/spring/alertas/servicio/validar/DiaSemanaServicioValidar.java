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

import net.royal.spring.alertas.dao.impl.DiaSemanaDaoImpl;
import net.royal.spring.alertas.dominio.DiaSemana;
import net.royal.spring.alertas.dominio.DiaSemanaPk;
import net.royal.spring.framework.constante.ConstantePantallaAccion;
import net.royal.spring.framework.modelo.generico.DominioMensajeUsuario;
import net.royal.spring.framework.modelo.seguridad.SeguridadUsuarioActual;
import net.royal.spring.framework.web.servicio.impl.GenericoServicioValidar;
@SuppressWarnings("rawtypes")
@Service (value = "BeanValidarDiaSemana")
public class DiaSemanaServicioValidar extends GenericoServicioValidar {
	public static String SPRING_NOMBRE = "BeanValidarDiaSemana";

	@Autowired
	private DiaSemanaDaoImpl diaSemanaDao;

	private DiaSemana prepararBasico(SeguridadUsuarioActual usuarioActual,DiaSemana diaSemana, Boolean flgInsertar) {
		if (flgInsertar) {
			diaSemana.setCreacionTerminal(usuarioActual.getDireccionIp());
			diaSemana.setCreacionFecha(new Date());
			diaSemana.setCreacionUsuario(usuarioActual.getUsuario());
		} else {
			diaSemana.setModificacionTerminal(usuarioActual.getDireccionIp());
			diaSemana.setModificacionFecha(new Date());
			diaSemana.setModificacionUsuario(usuarioActual.getUsuario());
		}
		
		// TODO DiaSemana : valores por defecto comunes Insert/Actualizar/Anular/Eliminar
		
		return diaSemana;
	}

	public DiaSemana prepararInsertar(SeguridadUsuarioActual usuarioActual,DiaSemana diaSemana) {
		if (diaSemana == null)
			return diaSemana;
		if (diaSemana.getAuxFlgPreparado())
			return diaSemana;
		diaSemana = prepararBasico(usuarioActual,diaSemana, true);
		diaSemana.setAuxFlgPreparado(Boolean.TRUE);
		
		// TODO DiaSemana.Insertar : valores por defecto
		
		return diaSemana;
	}

	public DiaSemana prepararActualizar(SeguridadUsuarioActual usuarioActual,DiaSemana diaSemana) {
		if (diaSemana == null)
			return diaSemana;
		if (diaSemana.getAuxFlgPreparado())
			return diaSemana;
		diaSemana = prepararBasico(usuarioActual,diaSemana, false);
		diaSemana.setAuxFlgPreparado(Boolean.TRUE);
		
		// TODO DiaSemana.Actualizar : valores por defecto
		
		return diaSemana;
	}

	public DiaSemana prepararAnular(SeguridadUsuarioActual usuarioActual,DiaSemana diaSemana) {
		if (diaSemana == null)
			return diaSemana;
		if (diaSemana.getAuxFlgPreparado())
			return diaSemana;
		diaSemana = prepararBasico(usuarioActual, diaSemana, false);
		diaSemana.setAuxFlgPreparado(Boolean.TRUE);
		
		// TODO DiaSemana.Anular : valores por defecto
		
		return diaSemana;
	}

	public DiaSemana prepararEliminar(SeguridadUsuarioActual usuarioActual,DiaSemana diaSemana) {
		if (diaSemana == null)
			return diaSemana;
		if (diaSemana.getAuxFlgPreparado())
			return diaSemana;
		diaSemana.setAuxFlgPreparado(Boolean.TRUE);
		
		// TODO DiaSemana.Eliminar : valores por defecto
		
		return diaSemana;
	}

	private List<DominioMensajeUsuario> coreBasico(SeguridadUsuarioActual usuarioActual, DiaSemana diaSemana) {
		List<DominioMensajeUsuario> lst = new ArrayList<DominioMensajeUsuario>();
		if (usuarioActual == null)
			lst.add(this.getMsjUsuarioError(SeguridadUsuarioActual.CONSTRAINTS_NOTNULL));
		if (diaSemana == null)
			lst.add(this.getMsjUsuarioError("diasemana.constraints.notnull"));
		if (!lst.isEmpty())
			return lst;
		Validator validator = Validation.buildDefaultValidatorFactory().getValidator();
		if (diaSemana.getPk() != null) {
			Set<ConstraintViolation<DiaSemanaPk>> reglasPk = validator.validate(diaSemana.getPk());
			for (ConstraintViolation constraint : reglasPk) {
				lst.add(this.getMsjUsuarioError(constraint));
			}
		}
		Set<ConstraintViolation<DiaSemana>> reglas = validator.validate(diaSemana);
		for (ConstraintViolation constraint : reglas) {
			lst.add(this.getMsjUsuarioError(constraint));
		}
		
		// TODO DiaSemana : validaciones comunes Insert/Actualizar
		
		return lst;
	}

	public List<DominioMensajeUsuario> coreInsertar(SeguridadUsuarioActual usuarioActual,DiaSemana diaSemana) {
		diaSemana = prepararInsertar(usuarioActual, diaSemana);
		List<DominioMensajeUsuario> lst = coreBasico(usuarioActual, diaSemana);
		if (!lst.isEmpty())
			return lst;
		
		// TODO DiaSemana.Insertar : validaciones
		
		return lst;
	}

	public List<DominioMensajeUsuario> coreActualizar(SeguridadUsuarioActual usuarioActual, DiaSemana diaSemana) {
		diaSemana = prepararActualizar(usuarioActual, diaSemana);
		List<DominioMensajeUsuario> lst = coreBasico(usuarioActual, diaSemana);
		if (!lst.isEmpty())
			return lst;
		
		// TODO DiaSemana.Actualizar : validaciones
		
		return lst;
	}

	public List<DominioMensajeUsuario> coreEliminar(SeguridadUsuarioActual usuarioActual,DiaSemana diaSemana) {
		diaSemana = prepararEliminar(usuarioActual, diaSemana);
		List<DominioMensajeUsuario> lst = new ArrayList<DominioMensajeUsuario>();
		
		// TODO DiaSemana.Eliminar : validaciones
		
		return lst;
	}

	public List<DominioMensajeUsuario> coreEliminar(SeguridadUsuarioActual usuarioActual,DiaSemanaPk pk) {
		DiaSemana bean = diaSemanaDao.obtenerPorId(pk);
		return coreEliminar(usuarioActual,bean);
	}

	public List<DominioMensajeUsuario> coreEliminar(SeguridadUsuarioActual usuarioActual,String pidDiaSemana) {
		return coreEliminar(usuarioActual,new DiaSemanaPk( pidDiaSemana));
	}

	public List<DominioMensajeUsuario> coreAnular(SeguridadUsuarioActual usuarioActual, DiaSemana diaSemana) {
		diaSemana = prepararAnular(usuarioActual, diaSemana);
		List<DominioMensajeUsuario> lst = new ArrayList<DominioMensajeUsuario>();
		
		// TODO DiaSemana.Anular : validaciones
		
		return lst;
	}

	public List<DominioMensajeUsuario> coreAnular(SeguridadUsuarioActual usuarioActual, DiaSemanaPk pk) {
		DiaSemana bean = diaSemanaDao.obtenerPorId(pk);
		return coreAnular(usuarioActual,bean);
	}

	public List<DominioMensajeUsuario> coreAnular(SeguridadUsuarioActual usuarioActual, String pidDiaSemana) {
		return coreAnular(usuarioActual,new DiaSemanaPk( pidDiaSemana));
	}

	public List<DominioMensajeUsuario> core(SeguridadUsuarioActual usuarioActual,String accion,DiaSemana diaSemana) {
		List<DominioMensajeUsuario> lst = new ArrayList<DominioMensajeUsuario>();
		if (accion.equals(ConstantePantallaAccion.INSERTAR))
			return coreInsertar(usuarioActual, diaSemana);
		if (accion.equals(ConstantePantallaAccion.ACTUALIZAR))
			return coreActualizar(usuarioActual, diaSemana);
		if (accion.equals(ConstantePantallaAccion.ANULAR))
			return coreAnular(usuarioActual, diaSemana);
		if (accion.equals(ConstantePantallaAccion.ELIMINAR))
			return coreEliminar(usuarioActual, diaSemana);
		return lst;
	}

}
