package net.royal.spring.alertas.servicio.impl;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.exolab.castor.types.DateTime;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import net.royal.spring.alertas.dao.impl.CorreoAdjuntoDaoImpl;
import net.royal.spring.alertas.dominio.CorreoAdjunto;
import net.royal.spring.alertas.dominio.CorreoAdjuntoPk;
import net.royal.spring.alertas.dominio.dto.CorreoDto;
import net.royal.spring.alertas.dominio.dto.DtoCorreoAdjunto;
import net.royal.spring.alertas.dominio.filtros.FiltroCorreoAdjunto;
import net.royal.spring.alertas.dominio.filtros.FiltroPaginacionCorreo;
import net.royal.spring.alertas.servicio.validar.CorreoAdjuntoServicioValidar;
import net.royal.spring.framework.core.UException;
import net.royal.spring.framework.modelo.generico.DominioMensajeUsuario;
import net.royal.spring.framework.modelo.generico.DominioPaginacion;
import net.royal.spring.framework.modelo.generico.DominioParametroPersistencia;
import net.royal.spring.framework.modelo.seguridad.SeguridadUsuarioActual;
import net.royal.spring.framework.util.UFechaHora;
import net.royal.spring.framework.web.servicio.impl.GenericoServicioImpl;

@Service (value = "BeanServicioCorreoAdjunto")
public class CorreoAdjuntoServicioImpl extends GenericoServicioImpl {
	public static String SPRING_NOMBRE = "BeanServicioCorreoAdjunto";

	@Autowired
	private CorreoAdjuntoDaoImpl correoAdjuntoDao;

	@Autowired
	private CorreoAdjuntoServicioValidar validar;

	@Transactional
	public CorreoAdjunto coreInsertar(SeguridadUsuarioActual usuarioActual,CorreoAdjunto correoAdjunto) throws UException {
		// valores por defecto - preparando objeto
		correoAdjunto = validar.prepararInsertar(usuarioActual, correoAdjunto);
		
		// validaciones de negocio
		List<DominioMensajeUsuario> lst = validar.coreInsertar(usuarioActual, correoAdjunto);
		if (!lst.isEmpty())
			throw new UException(lst);
		
		// transaccion
		return correoAdjuntoDao.coreInsertar(correoAdjunto);
	}

	@Transactional
	public CorreoAdjunto coreActualizar(SeguridadUsuarioActual usuarioActual, CorreoAdjunto correoAdjunto) throws UException {
		// valores por defecto - preparando objeto
		correoAdjunto = validar.prepararActualizar(usuarioActual, correoAdjunto);
		
		// validaciones de negocio
		List<DominioMensajeUsuario> lst = validar.coreActualizar(usuarioActual, correoAdjunto);
		if (!lst.isEmpty())
			throw new UException(lst);
		
		// transaccion
	    correoAdjunto = correoAdjuntoDao.coreActualizar(correoAdjunto);
		return correoAdjunto;
	}

	@Transactional
	public void coreEliminar(SeguridadUsuarioActual usuarioActual, CorreoAdjunto correoAdjunto) throws UException {
		// valores por defecto - preparando objeto
		correoAdjunto = validar.prepararEliminar(usuarioActual, correoAdjunto);
		
		// validaciones de negocio
		List<DominioMensajeUsuario> lst = validar.coreEliminar(usuarioActual, correoAdjunto);
		if (!lst.isEmpty())
			throw new UException(lst);
		
		// transaccion
		correoAdjuntoDao.eliminar(correoAdjunto);
	}

	public void coreEliminar(SeguridadUsuarioActual usuarioActual, CorreoAdjuntoPk pk) throws UException {
		CorreoAdjunto correoAdjunto = correoAdjuntoDao.obtenerPorId(pk);
		coreEliminar(usuarioActual,correoAdjunto);
	}

	public void coreEliminar(SeguridadUsuarioActual usuarioActual, Integer pidCorreo,Integer pidAdjunto) throws UException {
		coreEliminar(usuarioActual,new CorreoAdjuntoPk( pidCorreo, pidAdjunto));
	}

	public DominioPaginacion listarCorreoAdjuntos(FiltroCorreoAdjunto filtro) throws Exception {
		List<DominioParametroPersistencia> parametros = new ArrayList<DominioParametroPersistencia>();

		filtro.getPaginacion().setPaginacionRegistrosPorPagina(50);
		parametros.add(new DominioParametroPersistencia("p_idcorreo", BigDecimal.class, filtro.getIdCorreo()));
 

		Integer registros = correoAdjuntoDao.contar("correoadjunto.contarPaginacion", parametros);
		List<?> datos = correoAdjuntoDao.listarConPaginacion(filtro.getPaginacion(), parametros, "correoadjunto.listarPaginacion",
				DtoCorreoAdjunto.class);
		filtro.getPaginacion().setPaginacionRegistrosPorPagina(50);
		filtro.getPaginacion().setPaginacionRegistrosEncontrados(registros.intValue());
		filtro.getPaginacion().setPaginacionListaResultado(datos);
		
		
		return filtro.getPaginacion();
	}
}
