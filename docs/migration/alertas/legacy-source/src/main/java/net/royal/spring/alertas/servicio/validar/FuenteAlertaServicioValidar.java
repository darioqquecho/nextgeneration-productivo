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

import net.royal.spring.alertas.dao.impl.FuenteAlertaDaoImpl;
import net.royal.spring.alertas.dominio.FuenteAlerta;
import net.royal.spring.alertas.dominio.FuenteAlertaPk;
import net.royal.spring.framework.constante.ConstantePantallaAccion;
import net.royal.spring.framework.modelo.generico.DominioMensajeUsuario;
import net.royal.spring.framework.modelo.seguridad.SeguridadUsuarioActual;
import net.royal.spring.framework.web.servicio.impl.GenericoServicioValidar;
@SuppressWarnings("rawtypes")
@Service (value = "BeanValidarFuenteAlerta")
public class FuenteAlertaServicioValidar extends GenericoServicioValidar {
	public static String SPRING_NOMBRE = "BeanValidarFuenteAlerta";

	@Autowired
	private FuenteAlertaDaoImpl fuenteAlertaDao;

	private FuenteAlerta prepararBasico(SeguridadUsuarioActual usuarioActual,FuenteAlerta fuenteAlerta, Boolean flgInsertar) {
		if (flgInsertar) {
			fuenteAlerta.setCreacionTerminal(usuarioActual.getDireccionIp());
			fuenteAlerta.setCreacionFecha(new Date());
			fuenteAlerta.setCreacionUsuario(usuarioActual.getUsuario());
		} else {
			fuenteAlerta.setModificacionTerminal(usuarioActual.getDireccionIp());
			fuenteAlerta.setModificacionFecha(new Date());
			fuenteAlerta.setModificacionUsuario(usuarioActual.getUsuario());
		}
		
		// TODO FuenteAlerta : valores por defecto comunes Insert/Actualizar/Anular/Eliminar
		
		return fuenteAlerta;
	}

	public FuenteAlerta prepararInsertar(SeguridadUsuarioActual usuarioActual,FuenteAlerta fuenteAlerta) {
		if (fuenteAlerta == null)
			return fuenteAlerta;
		if (fuenteAlerta.getAuxFlgPreparado())
			return fuenteAlerta;
		fuenteAlerta = prepararBasico(usuarioActual,fuenteAlerta, true);
		fuenteAlerta.setAuxFlgPreparado(Boolean.TRUE);
		
		// TODO FuenteAlerta.Insertar : valores por defecto
		
		return fuenteAlerta;
	}

	public FuenteAlerta prepararActualizar(SeguridadUsuarioActual usuarioActual,FuenteAlerta fuenteAlerta) {
		if (fuenteAlerta == null)
			return fuenteAlerta;
		if (fuenteAlerta.getAuxFlgPreparado())
			return fuenteAlerta;
		fuenteAlerta = prepararBasico(usuarioActual,fuenteAlerta, false);
		fuenteAlerta.setAuxFlgPreparado(Boolean.TRUE);
		
		// TODO FuenteAlerta.Actualizar : valores por defecto
		
		return fuenteAlerta;
	}

	public FuenteAlerta prepararAnular(SeguridadUsuarioActual usuarioActual,FuenteAlerta fuenteAlerta) {
		if (fuenteAlerta == null)
			return fuenteAlerta;
		if (fuenteAlerta.getAuxFlgPreparado())
			return fuenteAlerta;
		fuenteAlerta = prepararBasico(usuarioActual, fuenteAlerta, false);
		fuenteAlerta.setAuxFlgPreparado(Boolean.TRUE);
		
		// TODO FuenteAlerta.Anular : valores por defecto
		
		return fuenteAlerta;
	}

	public FuenteAlerta prepararEliminar(SeguridadUsuarioActual usuarioActual,FuenteAlerta fuenteAlerta) {
		if (fuenteAlerta == null)
			return fuenteAlerta;
		if (fuenteAlerta.getAuxFlgPreparado())
			return fuenteAlerta;
		fuenteAlerta.setAuxFlgPreparado(Boolean.TRUE);
		
		// TODO FuenteAlerta.Eliminar : valores por defecto
		
		return fuenteAlerta;
	}

	private List<DominioMensajeUsuario> coreBasico(SeguridadUsuarioActual usuarioActual, FuenteAlerta fuenteAlerta) {
		List<DominioMensajeUsuario> lst = new ArrayList<DominioMensajeUsuario>();
		if (usuarioActual == null)
			lst.add(this.getMsjUsuarioError(SeguridadUsuarioActual.CONSTRAINTS_NOTNULL));
		if (fuenteAlerta == null)
			lst.add(this.getMsjUsuarioError("fuentealerta.constraints.notnull"));
		if (!lst.isEmpty())
			return lst;
		Validator validator = Validation.buildDefaultValidatorFactory().getValidator();
		if (fuenteAlerta.getPk() != null) {
			Set<ConstraintViolation<FuenteAlertaPk>> reglasPk = validator.validate(fuenteAlerta.getPk());
			for (ConstraintViolation constraint : reglasPk) {
				lst.add(this.getMsjUsuarioError(constraint));
			}
		}
		Set<ConstraintViolation<FuenteAlerta>> reglas = validator.validate(fuenteAlerta);
		for (ConstraintViolation constraint : reglas) {
			lst.add(this.getMsjUsuarioError(constraint));
		}
		
		// TODO FuenteAlerta : validaciones comunes Insert/Actualizar
		
		return lst;
	}

	public List<DominioMensajeUsuario> coreInsertar(SeguridadUsuarioActual usuarioActual,FuenteAlerta fuenteAlerta) {
		fuenteAlerta = prepararInsertar(usuarioActual, fuenteAlerta);
		List<DominioMensajeUsuario> lst = coreBasico(usuarioActual, fuenteAlerta);
		if (!lst.isEmpty())
			return lst;
		
		// TODO FuenteAlerta.Insertar : validaciones
		
		return lst;
	}

	public List<DominioMensajeUsuario> coreActualizar(SeguridadUsuarioActual usuarioActual, FuenteAlerta fuenteAlerta) {
		fuenteAlerta = prepararActualizar(usuarioActual, fuenteAlerta);
		List<DominioMensajeUsuario> lst = coreBasico(usuarioActual, fuenteAlerta);
		if (!lst.isEmpty())
			return lst;
		
		// TODO FuenteAlerta.Actualizar : validaciones
		
		return lst;
	}

	public List<DominioMensajeUsuario> coreEliminar(SeguridadUsuarioActual usuarioActual,FuenteAlerta fuenteAlerta) {
		fuenteAlerta = prepararEliminar(usuarioActual, fuenteAlerta);
		List<DominioMensajeUsuario> lst = new ArrayList<DominioMensajeUsuario>();
		
		// TODO FuenteAlerta.Eliminar : validaciones
		
		return lst;
	}

	public List<DominioMensajeUsuario> coreEliminar(SeguridadUsuarioActual usuarioActual,FuenteAlertaPk pk) {
		FuenteAlerta bean = fuenteAlertaDao.obtenerPorId(pk);
		return coreEliminar(usuarioActual,bean);
	}

	public List<DominioMensajeUsuario> coreEliminar(SeguridadUsuarioActual usuarioActual,Integer pidFuenteAlerta) {
		return coreEliminar(usuarioActual,new FuenteAlertaPk( pidFuenteAlerta));
	}

	public List<DominioMensajeUsuario> coreAnular(SeguridadUsuarioActual usuarioActual, FuenteAlerta fuenteAlerta) {
		fuenteAlerta = prepararAnular(usuarioActual, fuenteAlerta);
		List<DominioMensajeUsuario> lst = new ArrayList<DominioMensajeUsuario>();
		
		// TODO FuenteAlerta.Anular : validaciones
		
		return lst;
	}

	public List<DominioMensajeUsuario> coreAnular(SeguridadUsuarioActual usuarioActual, FuenteAlertaPk pk) {
		FuenteAlerta bean = fuenteAlertaDao.obtenerPorId(pk);
		return coreAnular(usuarioActual,bean);
	}

	public List<DominioMensajeUsuario> coreAnular(SeguridadUsuarioActual usuarioActual, Integer pidFuenteAlerta) {
		return coreAnular(usuarioActual,new FuenteAlertaPk( pidFuenteAlerta));
	}

	public List<DominioMensajeUsuario> core(SeguridadUsuarioActual usuarioActual,String accion,FuenteAlerta fuenteAlerta) {
		List<DominioMensajeUsuario> lst = new ArrayList<DominioMensajeUsuario>();
		if (accion.equals(ConstantePantallaAccion.INSERTAR))
			return coreInsertar(usuarioActual, fuenteAlerta);
		if (accion.equals(ConstantePantallaAccion.ACTUALIZAR))
			return coreActualizar(usuarioActual, fuenteAlerta);
		if (accion.equals(ConstantePantallaAccion.ANULAR))
			return coreAnular(usuarioActual, fuenteAlerta);
		if (accion.equals(ConstantePantallaAccion.ELIMINAR))
			return coreEliminar(usuarioActual, fuenteAlerta);
		return lst;
	}

}
