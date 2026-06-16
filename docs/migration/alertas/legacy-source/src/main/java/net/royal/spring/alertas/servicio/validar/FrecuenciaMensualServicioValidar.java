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

import net.royal.spring.alertas.dao.impl.FrecuenciaMensualDaoImpl;
import net.royal.spring.alertas.dominio.FrecuenciaMensual;
import net.royal.spring.alertas.dominio.FrecuenciaMensualPk;
import net.royal.spring.framework.constante.ConstantePantallaAccion;
import net.royal.spring.framework.modelo.generico.DominioMensajeUsuario;
import net.royal.spring.framework.modelo.seguridad.SeguridadUsuarioActual;
import net.royal.spring.framework.web.servicio.impl.GenericoServicioValidar;
@SuppressWarnings("rawtypes")
@Service (value = "BeanValidarFrecuenciaMensual")
public class FrecuenciaMensualServicioValidar extends GenericoServicioValidar {
	public static String SPRING_NOMBRE = "BeanValidarFrecuenciaMensual";

	@Autowired
	private FrecuenciaMensualDaoImpl frecuenciaMensualDao;

	private FrecuenciaMensual prepararBasico(SeguridadUsuarioActual usuarioActual,FrecuenciaMensual frecuenciaMensual, Boolean flgInsertar) {
		if (flgInsertar) {
			frecuenciaMensual.setCreacionTerminal(usuarioActual.getDireccionIp());
			frecuenciaMensual.setCreacionFecha(new Date());
			frecuenciaMensual.setCreacionUsuario(usuarioActual.getUsuario());
		} else {
			frecuenciaMensual.setModificacionTerminal(usuarioActual.getDireccionIp());
			frecuenciaMensual.setModificacionFecha(new Date());
			frecuenciaMensual.setModificacionUsuario(usuarioActual.getUsuario());
		}
		
		// TODO FrecuenciaMensual : valores por defecto comunes Insert/Actualizar/Anular/Eliminar
		
		return frecuenciaMensual;
	}

	public FrecuenciaMensual prepararInsertar(SeguridadUsuarioActual usuarioActual,FrecuenciaMensual frecuenciaMensual) {
		if (frecuenciaMensual == null)
			return frecuenciaMensual;
		if (frecuenciaMensual.getAuxFlgPreparado())
			return frecuenciaMensual;
		frecuenciaMensual = prepararBasico(usuarioActual,frecuenciaMensual, true);
		frecuenciaMensual.setAuxFlgPreparado(Boolean.TRUE);
		
		// TODO FrecuenciaMensual.Insertar : valores por defecto
		
		return frecuenciaMensual;
	}

	public FrecuenciaMensual prepararActualizar(SeguridadUsuarioActual usuarioActual,FrecuenciaMensual frecuenciaMensual) {
		if (frecuenciaMensual == null)
			return frecuenciaMensual;
		if (frecuenciaMensual.getAuxFlgPreparado())
			return frecuenciaMensual;
		frecuenciaMensual = prepararBasico(usuarioActual,frecuenciaMensual, false);
		frecuenciaMensual.setAuxFlgPreparado(Boolean.TRUE);
		
		// TODO FrecuenciaMensual.Actualizar : valores por defecto
		
		return frecuenciaMensual;
	}

	public FrecuenciaMensual prepararAnular(SeguridadUsuarioActual usuarioActual,FrecuenciaMensual frecuenciaMensual) {
		if (frecuenciaMensual == null)
			return frecuenciaMensual;
		if (frecuenciaMensual.getAuxFlgPreparado())
			return frecuenciaMensual;
		frecuenciaMensual = prepararBasico(usuarioActual, frecuenciaMensual, false);
		frecuenciaMensual.setAuxFlgPreparado(Boolean.TRUE);
		
		// TODO FrecuenciaMensual.Anular : valores por defecto
		
		return frecuenciaMensual;
	}

	public FrecuenciaMensual prepararEliminar(SeguridadUsuarioActual usuarioActual,FrecuenciaMensual frecuenciaMensual) {
		if (frecuenciaMensual == null)
			return frecuenciaMensual;
		if (frecuenciaMensual.getAuxFlgPreparado())
			return frecuenciaMensual;
		frecuenciaMensual.setAuxFlgPreparado(Boolean.TRUE);
		
		// TODO FrecuenciaMensual.Eliminar : valores por defecto
		
		return frecuenciaMensual;
	}

	private List<DominioMensajeUsuario> coreBasico(SeguridadUsuarioActual usuarioActual, FrecuenciaMensual frecuenciaMensual) {
		List<DominioMensajeUsuario> lst = new ArrayList<DominioMensajeUsuario>();
		if (usuarioActual == null)
			lst.add(this.getMsjUsuarioError(SeguridadUsuarioActual.CONSTRAINTS_NOTNULL));
		if (frecuenciaMensual == null)
			lst.add(this.getMsjUsuarioError("frecuenciamensual.constraints.notnull"));
		if (!lst.isEmpty())
			return lst;
		Validator validator = Validation.buildDefaultValidatorFactory().getValidator();
		if (frecuenciaMensual.getPk() != null) {
			Set<ConstraintViolation<FrecuenciaMensualPk>> reglasPk = validator.validate(frecuenciaMensual.getPk());
			for (ConstraintViolation constraint : reglasPk) {
				lst.add(this.getMsjUsuarioError(constraint));
			}
		}
		Set<ConstraintViolation<FrecuenciaMensual>> reglas = validator.validate(frecuenciaMensual);
		for (ConstraintViolation constraint : reglas) {
			lst.add(this.getMsjUsuarioError(constraint));
		}
		
		// TODO FrecuenciaMensual : validaciones comunes Insert/Actualizar
		
		return lst;
	}

	public List<DominioMensajeUsuario> coreInsertar(SeguridadUsuarioActual usuarioActual,FrecuenciaMensual frecuenciaMensual) {
		frecuenciaMensual = prepararInsertar(usuarioActual, frecuenciaMensual);
		List<DominioMensajeUsuario> lst = coreBasico(usuarioActual, frecuenciaMensual);
		if (!lst.isEmpty())
			return lst;
		
		// TODO FrecuenciaMensual.Insertar : validaciones
		
		return lst;
	}

	public List<DominioMensajeUsuario> coreActualizar(SeguridadUsuarioActual usuarioActual, FrecuenciaMensual frecuenciaMensual) {
		frecuenciaMensual = prepararActualizar(usuarioActual, frecuenciaMensual);
		List<DominioMensajeUsuario> lst = coreBasico(usuarioActual, frecuenciaMensual);
		if (!lst.isEmpty())
			return lst;
		
		// TODO FrecuenciaMensual.Actualizar : validaciones
		
		return lst;
	}

	public List<DominioMensajeUsuario> coreEliminar(SeguridadUsuarioActual usuarioActual,FrecuenciaMensual frecuenciaMensual) {
		frecuenciaMensual = prepararEliminar(usuarioActual, frecuenciaMensual);
		List<DominioMensajeUsuario> lst = new ArrayList<DominioMensajeUsuario>();
		
		// TODO FrecuenciaMensual.Eliminar : validaciones
		
		return lst;
	}

	public List<DominioMensajeUsuario> coreEliminar(SeguridadUsuarioActual usuarioActual,FrecuenciaMensualPk pk) {
		FrecuenciaMensual bean = frecuenciaMensualDao.obtenerPorId(pk);
		return coreEliminar(usuarioActual,bean);
	}

	public List<DominioMensajeUsuario> coreEliminar(SeguridadUsuarioActual usuarioActual,String pidFrecuenciaMensual) {
		return coreEliminar(usuarioActual,new FrecuenciaMensualPk( pidFrecuenciaMensual));
	}

	public List<DominioMensajeUsuario> coreAnular(SeguridadUsuarioActual usuarioActual, FrecuenciaMensual frecuenciaMensual) {
		frecuenciaMensual = prepararAnular(usuarioActual, frecuenciaMensual);
		List<DominioMensajeUsuario> lst = new ArrayList<DominioMensajeUsuario>();
		
		// TODO FrecuenciaMensual.Anular : validaciones
		
		return lst;
	}

	public List<DominioMensajeUsuario> coreAnular(SeguridadUsuarioActual usuarioActual, FrecuenciaMensualPk pk) {
		FrecuenciaMensual bean = frecuenciaMensualDao.obtenerPorId(pk);
		return coreAnular(usuarioActual,bean);
	}

	public List<DominioMensajeUsuario> coreAnular(SeguridadUsuarioActual usuarioActual, String pidFrecuenciaMensual) {
		return coreAnular(usuarioActual,new FrecuenciaMensualPk( pidFrecuenciaMensual));
	}

	public List<DominioMensajeUsuario> core(SeguridadUsuarioActual usuarioActual,String accion,FrecuenciaMensual frecuenciaMensual) {
		List<DominioMensajeUsuario> lst = new ArrayList<DominioMensajeUsuario>();
		if (accion.equals(ConstantePantallaAccion.INSERTAR))
			return coreInsertar(usuarioActual, frecuenciaMensual);
		if (accion.equals(ConstantePantallaAccion.ACTUALIZAR))
			return coreActualizar(usuarioActual, frecuenciaMensual);
		if (accion.equals(ConstantePantallaAccion.ANULAR))
			return coreAnular(usuarioActual, frecuenciaMensual);
		if (accion.equals(ConstantePantallaAccion.ELIMINAR))
			return coreEliminar(usuarioActual, frecuenciaMensual);
		return lst;
	}

}
