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

import net.royal.spring.alertas.dao.impl.ColormastDaoImpl;
import net.royal.spring.alertas.dominio.Colormast;
import net.royal.spring.alertas.dominio.ColormastPk;
import net.royal.spring.framework.constante.ConstantePantallaAccion;
import net.royal.spring.framework.modelo.generico.DominioMensajeUsuario;
import net.royal.spring.framework.modelo.seguridad.SeguridadUsuarioActual;
import net.royal.spring.framework.web.servicio.impl.GenericoServicioValidar;
@SuppressWarnings("rawtypes")
@Service (value = "BeanValidarColormast")
public class ColormastServicioValidar extends GenericoServicioValidar {
	public static String SPRING_NOMBRE = "BeanValidarColormast";

	@Autowired
	private ColormastDaoImpl colormastDao;

	private Colormast prepararBasico(SeguridadUsuarioActual usuarioActual,Colormast colormast, Boolean flgInsertar) {
     colormast.setUltimousuario(usuarioActual.getUsuario());
     colormast.setUltimafechamodif(new Date());
		
		// TODO Colormast : valores por defecto comunes Insert/Actualizar/Anular/Eliminar
		
		return colormast;
	}

	public Colormast prepararInsertar(SeguridadUsuarioActual usuarioActual,Colormast colormast) {
		if (colormast == null)
			return colormast;
		if (colormast.getAuxFlgPreparado())
			return colormast;
		colormast = prepararBasico(usuarioActual,colormast, true);
		colormast.setAuxFlgPreparado(Boolean.TRUE);
		
		// TODO Colormast.Insertar : valores por defecto
		
		return colormast;
	}

	public Colormast prepararActualizar(SeguridadUsuarioActual usuarioActual,Colormast colormast) {
		if (colormast == null)
			return colormast;
		if (colormast.getAuxFlgPreparado())
			return colormast;
		colormast = prepararBasico(usuarioActual,colormast, false);
		colormast.setAuxFlgPreparado(Boolean.TRUE);
		
		// TODO Colormast.Actualizar : valores por defecto
		
		return colormast;
	}

	public Colormast prepararAnular(SeguridadUsuarioActual usuarioActual,Colormast colormast) {
		if (colormast == null)
			return colormast;
		if (colormast.getAuxFlgPreparado())
			return colormast;
		colormast = prepararBasico(usuarioActual, colormast, false);
		colormast.setAuxFlgPreparado(Boolean.TRUE);
		
		// TODO Colormast.Anular : valores por defecto
		
		return colormast;
	}

	public Colormast prepararEliminar(SeguridadUsuarioActual usuarioActual,Colormast colormast) {
		if (colormast == null)
			return colormast;
		if (colormast.getAuxFlgPreparado())
			return colormast;
		colormast.setAuxFlgPreparado(Boolean.TRUE);
		
		// TODO Colormast.Eliminar : valores por defecto
		
		return colormast;
	}

	private List<DominioMensajeUsuario> coreBasico(SeguridadUsuarioActual usuarioActual, Colormast colormast) {
		List<DominioMensajeUsuario> lst = new ArrayList<DominioMensajeUsuario>();
		if (usuarioActual == null)
			lst.add(this.getMsjUsuarioError(SeguridadUsuarioActual.CONSTRAINTS_NOTNULL));
		if (colormast == null)
			lst.add(this.getMsjUsuarioError("colormast.constraints.notnull"));
		if (!lst.isEmpty())
			return lst;
		Validator validator = Validation.buildDefaultValidatorFactory().getValidator();
		if (colormast.getPk() != null) {
			Set<ConstraintViolation<ColormastPk>> reglasPk = validator.validate(colormast.getPk());
			for (ConstraintViolation constraint : reglasPk) {
				lst.add(this.getMsjUsuarioError(constraint));
			}
		}
		Set<ConstraintViolation<Colormast>> reglas = validator.validate(colormast);
		for (ConstraintViolation constraint : reglas) {
			lst.add(this.getMsjUsuarioError(constraint));
		}
		
		// TODO Colormast : validaciones comunes Insert/Actualizar
		
		return lst;
	}

	public List<DominioMensajeUsuario> coreInsertar(SeguridadUsuarioActual usuarioActual,Colormast colormast) {
		colormast = prepararInsertar(usuarioActual, colormast);
		List<DominioMensajeUsuario> lst = coreBasico(usuarioActual, colormast);
		if (!lst.isEmpty())
			return lst;
		
		// TODO Colormast.Insertar : validaciones
		
		return lst;
	}

	public List<DominioMensajeUsuario> coreActualizar(SeguridadUsuarioActual usuarioActual, Colormast colormast) {
		colormast = prepararActualizar(usuarioActual, colormast);
		List<DominioMensajeUsuario> lst = coreBasico(usuarioActual, colormast);
		if (!lst.isEmpty())
			return lst;
		
		// TODO Colormast.Actualizar : validaciones
		
		return lst;
	}

	public List<DominioMensajeUsuario> coreEliminar(SeguridadUsuarioActual usuarioActual,Colormast colormast) {
		colormast = prepararEliminar(usuarioActual, colormast);
		List<DominioMensajeUsuario> lst = new ArrayList<DominioMensajeUsuario>();
		
		// TODO Colormast.Eliminar : validaciones
		
		return lst;
	}

	public List<DominioMensajeUsuario> coreEliminar(SeguridadUsuarioActual usuarioActual,ColormastPk pk) {
		Colormast bean = colormastDao.obtenerPorId(pk);
		return coreEliminar(usuarioActual,bean);
	}

	public List<DominioMensajeUsuario> coreEliminar(SeguridadUsuarioActual usuarioActual,String pcolor) {
		return coreEliminar(usuarioActual,new ColormastPk( pcolor));
	}

	public List<DominioMensajeUsuario> coreAnular(SeguridadUsuarioActual usuarioActual, Colormast colormast) {
		colormast = prepararAnular(usuarioActual, colormast);
		List<DominioMensajeUsuario> lst = new ArrayList<DominioMensajeUsuario>();
		
		// TODO Colormast.Anular : validaciones
		
		return lst;
	}

	public List<DominioMensajeUsuario> coreAnular(SeguridadUsuarioActual usuarioActual, ColormastPk pk) {
		Colormast bean = colormastDao.obtenerPorId(pk);
		return coreAnular(usuarioActual,bean);
	}

	public List<DominioMensajeUsuario> coreAnular(SeguridadUsuarioActual usuarioActual, String pcolor) {
		return coreAnular(usuarioActual,new ColormastPk( pcolor));
	}

	public List<DominioMensajeUsuario> core(SeguridadUsuarioActual usuarioActual,String accion,Colormast colormast) {
		List<DominioMensajeUsuario> lst = new ArrayList<DominioMensajeUsuario>();
		if (accion.equals(ConstantePantallaAccion.INSERTAR))
			return coreInsertar(usuarioActual, colormast);
		if (accion.equals(ConstantePantallaAccion.ACTUALIZAR))
			return coreActualizar(usuarioActual, colormast);
		if (accion.equals(ConstantePantallaAccion.ANULAR))
			return coreAnular(usuarioActual, colormast);
		if (accion.equals(ConstantePantallaAccion.ELIMINAR))
			return coreEliminar(usuarioActual, colormast);
		return lst;
	}

}
