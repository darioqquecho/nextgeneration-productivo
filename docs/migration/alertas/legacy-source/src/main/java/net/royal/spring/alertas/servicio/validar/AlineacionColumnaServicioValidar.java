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

import net.royal.spring.alertas.dao.impl.AlineacionColumnaDaoImpl;
import net.royal.spring.alertas.dominio.AlineacionColumna;
import net.royal.spring.alertas.dominio.AlineacionColumnaPk;
import net.royal.spring.framework.constante.ConstantePantallaAccion;
import net.royal.spring.framework.modelo.generico.DominioMensajeUsuario;
import net.royal.spring.framework.modelo.seguridad.SeguridadUsuarioActual;
import net.royal.spring.framework.web.servicio.impl.GenericoServicioValidar;
@SuppressWarnings("rawtypes")
@Service (value = "BeanValidarAlineacionColumna")
public class AlineacionColumnaServicioValidar extends GenericoServicioValidar {
	public static String SPRING_NOMBRE = "BeanValidarAlineacionColumna";

	@Autowired
	private AlineacionColumnaDaoImpl alineacionColumnaDao;

	private AlineacionColumna prepararBasico(SeguridadUsuarioActual usuarioActual,AlineacionColumna alineacionColumna, Boolean flgInsertar) {
		if (flgInsertar) {
			alineacionColumna.setCreacionTerminal(usuarioActual.getDireccionIp());
			alineacionColumna.setCreacionFecha(new Date());
			alineacionColumna.setCreacionUsuario(usuarioActual.getUsuario());
		} else {
			alineacionColumna.setModificacionTerminal(usuarioActual.getDireccionIp());
			alineacionColumna.setModificacionFecha(new Date());
			alineacionColumna.setModificacionUsuario(usuarioActual.getUsuario());
		}
		
		// TODO AlineacionColumna : valores por defecto comunes Insert/Actualizar/Anular/Eliminar
		
		return alineacionColumna;
	}

	public AlineacionColumna prepararInsertar(SeguridadUsuarioActual usuarioActual,AlineacionColumna alineacionColumna) {
		if (alineacionColumna == null)
			return alineacionColumna;
		if (alineacionColumna.getAuxFlgPreparado())
			return alineacionColumna;
		alineacionColumna = prepararBasico(usuarioActual,alineacionColumna, true);
		alineacionColumna.setAuxFlgPreparado(Boolean.TRUE);
		
		// TODO AlineacionColumna.Insertar : valores por defecto
		
		return alineacionColumna;
	}

	public AlineacionColumna prepararActualizar(SeguridadUsuarioActual usuarioActual,AlineacionColumna alineacionColumna) {
		if (alineacionColumna == null)
			return alineacionColumna;
		if (alineacionColumna.getAuxFlgPreparado())
			return alineacionColumna;
		alineacionColumna = prepararBasico(usuarioActual,alineacionColumna, false);
		alineacionColumna.setAuxFlgPreparado(Boolean.TRUE);
		
		// TODO AlineacionColumna.Actualizar : valores por defecto
		
		return alineacionColumna;
	}

	public AlineacionColumna prepararAnular(SeguridadUsuarioActual usuarioActual,AlineacionColumna alineacionColumna) {
		if (alineacionColumna == null)
			return alineacionColumna;
		if (alineacionColumna.getAuxFlgPreparado())
			return alineacionColumna;
		alineacionColumna = prepararBasico(usuarioActual, alineacionColumna, false);
		alineacionColumna.setAuxFlgPreparado(Boolean.TRUE);
		
		// TODO AlineacionColumna.Anular : valores por defecto
		
		return alineacionColumna;
	}

	public AlineacionColumna prepararEliminar(SeguridadUsuarioActual usuarioActual,AlineacionColumna alineacionColumna) {
		if (alineacionColumna == null)
			return alineacionColumna;
		if (alineacionColumna.getAuxFlgPreparado())
			return alineacionColumna;
		alineacionColumna.setAuxFlgPreparado(Boolean.TRUE);
		
		// TODO AlineacionColumna.Eliminar : valores por defecto
		
		return alineacionColumna;
	}

	private List<DominioMensajeUsuario> coreBasico(SeguridadUsuarioActual usuarioActual, AlineacionColumna alineacionColumna) {
		List<DominioMensajeUsuario> lst = new ArrayList<DominioMensajeUsuario>();
		if (usuarioActual == null)
			lst.add(this.getMsjUsuarioError(SeguridadUsuarioActual.CONSTRAINTS_NOTNULL));
		if (alineacionColumna == null)
			lst.add(this.getMsjUsuarioError("alineacioncolumna.constraints.notnull"));
		if (!lst.isEmpty())
			return lst;
		Validator validator = Validation.buildDefaultValidatorFactory().getValidator();
		if (alineacionColumna.getPk() != null) {
			Set<ConstraintViolation<AlineacionColumnaPk>> reglasPk = validator.validate(alineacionColumna.getPk());
			for (ConstraintViolation constraint : reglasPk) {
				lst.add(this.getMsjUsuarioError(constraint));
			}
		}
		Set<ConstraintViolation<AlineacionColumna>> reglas = validator.validate(alineacionColumna);
		for (ConstraintViolation constraint : reglas) {
			lst.add(this.getMsjUsuarioError(constraint));
		}
		
		// TODO AlineacionColumna : validaciones comunes Insert/Actualizar
		
		return lst;
	}

	public List<DominioMensajeUsuario> coreInsertar(SeguridadUsuarioActual usuarioActual,AlineacionColumna alineacionColumna) {
		alineacionColumna = prepararInsertar(usuarioActual, alineacionColumna);
		List<DominioMensajeUsuario> lst = coreBasico(usuarioActual, alineacionColumna);
		if (!lst.isEmpty())
			return lst;
		
		// TODO AlineacionColumna.Insertar : validaciones
		
		return lst;
	}

	public List<DominioMensajeUsuario> coreActualizar(SeguridadUsuarioActual usuarioActual, AlineacionColumna alineacionColumna) {
		alineacionColumna = prepararActualizar(usuarioActual, alineacionColumna);
		List<DominioMensajeUsuario> lst = coreBasico(usuarioActual, alineacionColumna);
		if (!lst.isEmpty())
			return lst;
		
		// TODO AlineacionColumna.Actualizar : validaciones
		
		return lst;
	}

	public List<DominioMensajeUsuario> coreEliminar(SeguridadUsuarioActual usuarioActual,AlineacionColumna alineacionColumna) {
		alineacionColumna = prepararEliminar(usuarioActual, alineacionColumna);
		List<DominioMensajeUsuario> lst = new ArrayList<DominioMensajeUsuario>();
		
		// TODO AlineacionColumna.Eliminar : validaciones
		
		return lst;
	}

	public List<DominioMensajeUsuario> coreEliminar(SeguridadUsuarioActual usuarioActual,AlineacionColumnaPk pk) {
		AlineacionColumna bean = alineacionColumnaDao.obtenerPorId(pk);
		return coreEliminar(usuarioActual,bean);
	}

	public List<DominioMensajeUsuario> coreEliminar(SeguridadUsuarioActual usuarioActual,String pidAlineacionColumna) {
		return coreEliminar(usuarioActual,new AlineacionColumnaPk( pidAlineacionColumna));
	}

	public List<DominioMensajeUsuario> coreAnular(SeguridadUsuarioActual usuarioActual, AlineacionColumna alineacionColumna) {
		alineacionColumna = prepararAnular(usuarioActual, alineacionColumna);
		List<DominioMensajeUsuario> lst = new ArrayList<DominioMensajeUsuario>();
		
		// TODO AlineacionColumna.Anular : validaciones
		
		return lst;
	}

	public List<DominioMensajeUsuario> coreAnular(SeguridadUsuarioActual usuarioActual, AlineacionColumnaPk pk) {
		AlineacionColumna bean = alineacionColumnaDao.obtenerPorId(pk);
		return coreAnular(usuarioActual,bean);
	}

	public List<DominioMensajeUsuario> coreAnular(SeguridadUsuarioActual usuarioActual, String pidAlineacionColumna) {
		return coreAnular(usuarioActual,new AlineacionColumnaPk( pidAlineacionColumna));
	}

	public List<DominioMensajeUsuario> core(SeguridadUsuarioActual usuarioActual,String accion,AlineacionColumna alineacionColumna) {
		List<DominioMensajeUsuario> lst = new ArrayList<DominioMensajeUsuario>();
		if (accion.equals(ConstantePantallaAccion.INSERTAR))
			return coreInsertar(usuarioActual, alineacionColumna);
		if (accion.equals(ConstantePantallaAccion.ACTUALIZAR))
			return coreActualizar(usuarioActual, alineacionColumna);
		if (accion.equals(ConstantePantallaAccion.ANULAR))
			return coreAnular(usuarioActual, alineacionColumna);
		if (accion.equals(ConstantePantallaAccion.ELIMINAR))
			return coreEliminar(usuarioActual, alineacionColumna);
		return lst;
	}

}
