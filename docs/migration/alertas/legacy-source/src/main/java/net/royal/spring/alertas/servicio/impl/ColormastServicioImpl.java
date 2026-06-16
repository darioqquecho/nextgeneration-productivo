package net.royal.spring.alertas.servicio.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import net.royal.spring.alertas.dao.impl.ColormastDaoImpl;
import net.royal.spring.alertas.dominio.Colormast;
import net.royal.spring.alertas.dominio.ColormastPk;
import net.royal.spring.alertas.servicio.validar.ColormastServicioValidar;
import net.royal.spring.framework.core.UException;
import net.royal.spring.framework.modelo.generico.DominioMensajeUsuario;
import net.royal.spring.framework.modelo.seguridad.SeguridadUsuarioActual;
import net.royal.spring.framework.web.servicio.impl.GenericoServicioImpl;
 

@Service (value = "BeanServicioColormast")
public class ColormastServicioImpl extends GenericoServicioImpl {
	public static String SPRING_NOMBRE = "BeanServicioColormast";

	@Autowired
	private ColormastDaoImpl colormastDao;

	@Autowired
	private ColormastServicioValidar validar;

	@Transactional
	public Colormast coreInsertar(SeguridadUsuarioActual usuarioActual,Colormast colormast) throws UException {
		// valores por defecto - preparando objeto
		colormast = validar.prepararInsertar(usuarioActual, colormast);
		
		// validaciones de negocio
		List<DominioMensajeUsuario> lst = validar.coreInsertar(usuarioActual, colormast);
		if (!lst.isEmpty())
			throw new UException(lst);
		
		// transaccion
		return colormastDao.coreInsertar(colormast);
	}

	@Transactional
	public Colormast coreActualizar(SeguridadUsuarioActual usuarioActual, Colormast colormast) throws UException {
		// valores por defecto - preparando objeto
		colormast = validar.prepararActualizar(usuarioActual, colormast);
		
		// validaciones de negocio
		List<DominioMensajeUsuario> lst = validar.coreActualizar(usuarioActual, colormast);
		if (!lst.isEmpty())
			throw new UException(lst);
		
		// transaccion
	    colormast = colormastDao.coreActualizar(colormast);
		return colormast;
	}

	@Transactional
	public Colormast coreAnular(SeguridadUsuarioActual usuarioActual, Colormast colormast) throws UException {
		// valores por defecto - preparando objeto
		colormast = validar.prepararAnular(usuarioActual, colormast);
		
		// validaciones de negocio
		List<DominioMensajeUsuario> lst = validar.coreAnular(usuarioActual, colormast);
		if (!lst.isEmpty())
			throw new UException(lst);
		
		// transaccion
		return colormastDao.coreActualizar(colormast);
	}

	public Colormast coreAnular(SeguridadUsuarioActual usuarioActual, ColormastPk pk) throws UException {
		Colormast bean = colormastDao.obtenerPorId(pk);
		return coreAnular(usuarioActual,bean);
	}

	public Colormast coreAnular(SeguridadUsuarioActual usuarioActual, String pcolor) throws UException {
		return coreAnular(usuarioActual,new ColormastPk( pcolor));
	}

	@Transactional
	public void coreEliminar(SeguridadUsuarioActual usuarioActual, Colormast colormast) throws UException {
		// valores por defecto - preparando objeto
		colormast = validar.prepararEliminar(usuarioActual, colormast);
		
		// validaciones de negocio
		List<DominioMensajeUsuario> lst = validar.coreEliminar(usuarioActual, colormast);
		if (!lst.isEmpty())
			throw new UException(lst);
		
		// transaccion
		colormastDao.eliminar(colormast);
	}

	public void coreEliminar(SeguridadUsuarioActual usuarioActual, ColormastPk pk) throws UException {
		Colormast colormast = colormastDao.obtenerPorId(pk);
		coreEliminar(usuarioActual,colormast);
	}

	public void coreEliminar(SeguridadUsuarioActual usuarioActual, String pcolor) throws UException {
		coreEliminar(usuarioActual,new ColormastPk( pcolor));
	}

	
	@Transactional
	public Colormast  ActualizarColorMast( Colormast colormast) throws UException {
	    colormast = colormastDao.coreActualizar(colormast);
		return colormast;
	}

	
	@Transactional
	public Colormast  RegistrarColorMast( Colormast colormast) throws UException {
	    colormast = colormastDao.coreInsertar(colormast);
		return colormast;
	}


	@Transactional
	public Colormast  ActualizarColorMastEstado( Colormast colormast) throws UException {
		String color = colormast.getPk().getColor();
		Colormast beanColor = new Colormast();
		beanColor.getPk().setColor(color);
		beanColor = colormastDao.obtenerPorId(beanColor.getPk().getColor());
		beanColor.setEstado(colormast.getEstado());
		
	    colormastDao.coreActualizar(beanColor);
		return colormast;
	}
}
