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

import net.royal.spring.alertas.dao.impl.FuenteAlertaAdicionalDaoImpl;
import net.royal.spring.alertas.dominio.FuenteAlertaAdicional;
import net.royal.spring.alertas.dominio.FuenteAlertaAdicionalPk;
import net.royal.spring.framework.constante.ConstantePantallaAccion;
import net.royal.spring.framework.modelo.generico.DominioMensajeUsuario;
import net.royal.spring.framework.modelo.seguridad.SeguridadUsuarioActual;
import net.royal.spring.framework.web.servicio.impl.GenericoServicioValidar;
@SuppressWarnings("rawtypes")
@Service (value = "BeanValidarFuenteAlertaAdicional")
public class FuenteAlertaAdicionalServicioValidar extends GenericoServicioValidar {
	public static String SPRING_NOMBRE = "BeanValidarFuenteAlertaAdicional";

	@Autowired
	private FuenteAlertaAdicionalDaoImpl fuenteAlertaAdicionalDao;

	private FuenteAlertaAdicional prepararBasico(SeguridadUsuarioActual usuarioActual,FuenteAlertaAdicional fuenteAlertaAdicional, Boolean flgInsertar) {
		if (flgInsertar) {
			fuenteAlertaAdicional.setCreacionTerminal(usuarioActual.getDireccionIp());
			fuenteAlertaAdicional.setCreacionFecha(new Date());
			fuenteAlertaAdicional.setCreacionUsuario(usuarioActual.getUsuario());
		} else {
			fuenteAlertaAdicional.setModificacionTerminal(usuarioActual.getDireccionIp());
			fuenteAlertaAdicional.setModificacionFecha(new Date());
			fuenteAlertaAdicional.setModificacionUsuario(usuarioActual.getUsuario());
		}
		
		// TODO FuenteAlertaAdicional : valores por defecto comunes Insert/Actualizar/Anular/Eliminar
		
		return fuenteAlertaAdicional;
	}

	public FuenteAlertaAdicional prepararInsertar(SeguridadUsuarioActual usuarioActual,FuenteAlertaAdicional fuenteAlertaAdicional) {
		if (fuenteAlertaAdicional == null)
			return fuenteAlertaAdicional;
		if (fuenteAlertaAdicional.getAuxFlgPreparado())
			return fuenteAlertaAdicional;
		fuenteAlertaAdicional = prepararBasico(usuarioActual,fuenteAlertaAdicional, true);
		fuenteAlertaAdicional.setAuxFlgPreparado(Boolean.TRUE);
		
		// TODO FuenteAlertaAdicional.Insertar : valores por defecto
		
		return fuenteAlertaAdicional;
	}

	public FuenteAlertaAdicional prepararActualizar(SeguridadUsuarioActual usuarioActual,FuenteAlertaAdicional fuenteAlertaAdicional) {
		if (fuenteAlertaAdicional == null)
			return fuenteAlertaAdicional;
		if (fuenteAlertaAdicional.getAuxFlgPreparado())
			return fuenteAlertaAdicional;
		fuenteAlertaAdicional = prepararBasico(usuarioActual,fuenteAlertaAdicional, false);
		fuenteAlertaAdicional.setAuxFlgPreparado(Boolean.TRUE);
		
		// TODO FuenteAlertaAdicional.Actualizar : valores por defecto
		
		return fuenteAlertaAdicional;
	}

	public FuenteAlertaAdicional prepararAnular(SeguridadUsuarioActual usuarioActual,FuenteAlertaAdicional fuenteAlertaAdicional) {
		if (fuenteAlertaAdicional == null)
			return fuenteAlertaAdicional;
		if (fuenteAlertaAdicional.getAuxFlgPreparado())
			return fuenteAlertaAdicional;
		fuenteAlertaAdicional = prepararBasico(usuarioActual, fuenteAlertaAdicional, false);
		fuenteAlertaAdicional.setAuxFlgPreparado(Boolean.TRUE);
		
		// TODO FuenteAlertaAdicional.Anular : valores por defecto
		
		return fuenteAlertaAdicional;
	}

	public FuenteAlertaAdicional prepararEliminar(SeguridadUsuarioActual usuarioActual,FuenteAlertaAdicional fuenteAlertaAdicional) {
		if (fuenteAlertaAdicional == null)
			return fuenteAlertaAdicional;
		if (fuenteAlertaAdicional.getAuxFlgPreparado())
			return fuenteAlertaAdicional;
		fuenteAlertaAdicional.setAuxFlgPreparado(Boolean.TRUE);
		
		// TODO FuenteAlertaAdicional.Eliminar : valores por defecto
		
		return fuenteAlertaAdicional;
	}

	private List<DominioMensajeUsuario> coreBasico(SeguridadUsuarioActual usuarioActual, FuenteAlertaAdicional fuenteAlertaAdicional) {
		List<DominioMensajeUsuario> lst = new ArrayList<DominioMensajeUsuario>();
		if (usuarioActual == null)
			lst.add(this.getMsjUsuarioError(SeguridadUsuarioActual.CONSTRAINTS_NOTNULL));
		if (fuenteAlertaAdicional == null)
			lst.add(this.getMsjUsuarioError("fuentealertaadicional.constraints.notnull"));
		if (!lst.isEmpty())
			return lst;
		Validator validator = Validation.buildDefaultValidatorFactory().getValidator();
		if (fuenteAlertaAdicional.getPk() != null) {
			Set<ConstraintViolation<FuenteAlertaAdicionalPk>> reglasPk = validator.validate(fuenteAlertaAdicional.getPk());
			for (ConstraintViolation constraint : reglasPk) {
				lst.add(this.getMsjUsuarioError(constraint));
			}
		}
		Set<ConstraintViolation<FuenteAlertaAdicional>> reglas = validator.validate(fuenteAlertaAdicional);
		for (ConstraintViolation constraint : reglas) {
			lst.add(this.getMsjUsuarioError(constraint));
		}
		
		// TODO FuenteAlertaAdicional : validaciones comunes Insert/Actualizar
		
		return lst;
	}

	public List<DominioMensajeUsuario> coreInsertar(SeguridadUsuarioActual usuarioActual,FuenteAlertaAdicional fuenteAlertaAdicional) {
		fuenteAlertaAdicional = prepararInsertar(usuarioActual, fuenteAlertaAdicional);
		List<DominioMensajeUsuario> lst = coreBasico(usuarioActual, fuenteAlertaAdicional);
		if (!lst.isEmpty())
			return lst;
		
		// TODO FuenteAlertaAdicional.Insertar : validaciones
		
		return lst;
	}

	public List<DominioMensajeUsuario> coreActualizar(SeguridadUsuarioActual usuarioActual, FuenteAlertaAdicional fuenteAlertaAdicional) {
		fuenteAlertaAdicional = prepararActualizar(usuarioActual, fuenteAlertaAdicional);
		List<DominioMensajeUsuario> lst = coreBasico(usuarioActual, fuenteAlertaAdicional);
		if (!lst.isEmpty())
			return lst;
		
		// TODO FuenteAlertaAdicional.Actualizar : validaciones
		
		return lst;
	}

	public List<DominioMensajeUsuario> coreEliminar(SeguridadUsuarioActual usuarioActual,FuenteAlertaAdicional fuenteAlertaAdicional) {
		fuenteAlertaAdicional = prepararEliminar(usuarioActual, fuenteAlertaAdicional);
		List<DominioMensajeUsuario> lst = new ArrayList<DominioMensajeUsuario>();
		
		// TODO FuenteAlertaAdicional.Eliminar : validaciones
		
		return lst;
	}

	public List<DominioMensajeUsuario> coreEliminar(SeguridadUsuarioActual usuarioActual,FuenteAlertaAdicionalPk pk) {
		FuenteAlertaAdicional bean = fuenteAlertaAdicionalDao.obtenerPorId(pk);
		return coreEliminar(usuarioActual,bean);
	}

	public List<DominioMensajeUsuario> coreEliminar(SeguridadUsuarioActual usuarioActual,Integer pidFuenteAlerta,Integer pnroRegistro,String pcampoNombre) {
		return coreEliminar(usuarioActual,new FuenteAlertaAdicionalPk( pidFuenteAlerta, pnroRegistro, pcampoNombre));
	}

	public List<DominioMensajeUsuario> coreAnular(SeguridadUsuarioActual usuarioActual, FuenteAlertaAdicional fuenteAlertaAdicional) {
		fuenteAlertaAdicional = prepararAnular(usuarioActual, fuenteAlertaAdicional);
		List<DominioMensajeUsuario> lst = new ArrayList<DominioMensajeUsuario>();
		
		// TODO FuenteAlertaAdicional.Anular : validaciones
		
		return lst;
	}

	public List<DominioMensajeUsuario> coreAnular(SeguridadUsuarioActual usuarioActual, FuenteAlertaAdicionalPk pk) {
		FuenteAlertaAdicional bean = fuenteAlertaAdicionalDao.obtenerPorId(pk);
		return coreAnular(usuarioActual,bean);
	}

	public List<DominioMensajeUsuario> coreAnular(SeguridadUsuarioActual usuarioActual, Integer pidFuenteAlerta,Integer pnroRegistro,String pcampoNombre) {
		return coreAnular(usuarioActual,new FuenteAlertaAdicionalPk( pidFuenteAlerta, pnroRegistro, pcampoNombre));
	}

	public List<DominioMensajeUsuario> core(SeguridadUsuarioActual usuarioActual,String accion,FuenteAlertaAdicional fuenteAlertaAdicional) {
		List<DominioMensajeUsuario> lst = new ArrayList<DominioMensajeUsuario>();
		if (accion.equals(ConstantePantallaAccion.INSERTAR))
			return coreInsertar(usuarioActual, fuenteAlertaAdicional);
		if (accion.equals(ConstantePantallaAccion.ACTUALIZAR))
			return coreActualizar(usuarioActual, fuenteAlertaAdicional);
		if (accion.equals(ConstantePantallaAccion.ANULAR))
			return coreAnular(usuarioActual, fuenteAlertaAdicional);
		if (accion.equals(ConstantePantallaAccion.ELIMINAR))
			return coreEliminar(usuarioActual, fuenteAlertaAdicional);
		return lst;
	}

}
