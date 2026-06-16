package net.royal.spring.alertas.servicio.impl;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import net.royal.spring.alertas.dao.impl.CorreoDestinoDaoImpl;
import net.royal.spring.alertas.dominio.CorreoDestino;
import net.royal.spring.alertas.dominio.CorreoDestinoPk;
import net.royal.spring.alertas.dominio.dto.CorreoDestinodto;
import net.royal.spring.alertas.dominio.filtros.FiltroPaginacionCorreo;
import net.royal.spring.alertas.servicio.validar.CorreoDestinoServicioValidar;
import net.royal.spring.framework.core.UException;
import net.royal.spring.framework.modelo.generico.DominioMensajeUsuario;
import net.royal.spring.framework.modelo.generico.DominioPaginacion;
import net.royal.spring.framework.modelo.generico.DominioParametroPersistencia;
import net.royal.spring.framework.modelo.seguridad.SeguridadUsuarioActual;
import net.royal.spring.framework.web.servicio.impl.GenericoServicioImpl;

@Service (value = "BeanServicioCorreoDestino")
public class CorreoDestinoServicioImpl extends GenericoServicioImpl {
	public static String SPRING_NOMBRE = "BeanServicioCorreoDestino";

	@Autowired
	private CorreoDestinoDaoImpl correoDestinoDao;

	@Autowired
	private CorreoDestinoServicioValidar validar;

	@Transactional
	public CorreoDestino coreInsertar(SeguridadUsuarioActual usuarioActual,CorreoDestino correoDestino) throws UException {
		// valores por defecto - preparando objeto
		correoDestino = validar.prepararInsertar(usuarioActual, correoDestino);
		
		// validaciones de negocio
		List<DominioMensajeUsuario> lst = validar.coreInsertar(usuarioActual, correoDestino);
		if (!lst.isEmpty())
			throw new UException(lst);
		
		// transaccion
		return correoDestinoDao.coreInsertar(correoDestino);
	}

	@Transactional
	public CorreoDestino coreActualizar(SeguridadUsuarioActual usuarioActual, CorreoDestino correoDestino) throws UException {
		// valores por defecto - preparando objeto
		correoDestino = validar.prepararActualizar(usuarioActual, correoDestino);
		
		// validaciones de negocio
		List<DominioMensajeUsuario> lst = validar.coreActualizar(usuarioActual, correoDestino);
		if (!lst.isEmpty())
			throw new UException(lst);
		
		// transaccion
	    correoDestino = correoDestinoDao.coreActualizar(correoDestino);
		return correoDestino;
	}

	@Transactional
	public CorreoDestino coreAnular(SeguridadUsuarioActual usuarioActual, CorreoDestino correoDestino) throws UException {
		// valores por defecto - preparando objeto
		correoDestino = validar.prepararAnular(usuarioActual, correoDestino);
		
		// validaciones de negocio
		List<DominioMensajeUsuario> lst = validar.coreAnular(usuarioActual, correoDestino);
		if (!lst.isEmpty())
			throw new UException(lst);
		
		// transaccion
		return correoDestinoDao.coreActualizar(correoDestino);
	}

	public CorreoDestino coreAnular(SeguridadUsuarioActual usuarioActual, CorreoDestinoPk pk) throws UException {
		CorreoDestino bean = correoDestinoDao.obtenerPorId(pk);
		return coreAnular(usuarioActual,bean);
	}

	public CorreoDestino coreAnular(SeguridadUsuarioActual usuarioActual, Integer pidCorreo,String pcorreoDestino) throws UException {
		return coreAnular(usuarioActual,new CorreoDestinoPk( pidCorreo, pcorreoDestino));
	}

	@Transactional
	public void coreEliminar(SeguridadUsuarioActual usuarioActual, CorreoDestino correoDestino) throws UException {
		// valores por defecto - preparando objeto
		correoDestino = validar.prepararEliminar(usuarioActual, correoDestino);
		
		// validaciones de negocio
		List<DominioMensajeUsuario> lst = validar.coreEliminar(usuarioActual, correoDestino);
		if (!lst.isEmpty())
			throw new UException(lst);
		
		// transaccion
		correoDestinoDao.eliminar(correoDestino);
	}

	public void coreEliminar(SeguridadUsuarioActual usuarioActual, CorreoDestinoPk pk) throws UException {
		CorreoDestino correoDestino = correoDestinoDao.obtenerPorId(pk);
		coreEliminar(usuarioActual,correoDestino);
	}

	public void coreEliminar(SeguridadUsuarioActual usuarioActual, Integer pidCorreo,String pcorreoDestino) throws UException {
		coreEliminar(usuarioActual,new CorreoDestinoPk( pidCorreo, pcorreoDestino));
	}

	public DominioPaginacion listarCorrreodestino(FiltroPaginacionCorreo filtro) {
		List<DominioParametroPersistencia> parametros = new ArrayList<DominioParametroPersistencia>();
		filtro.getPaginacion().setPaginacionRegistrosPorPagina(50);
	
		parametros.add(new DominioParametroPersistencia("id_correo", BigDecimal.class, filtro.getIdcorreo()));
	 	
		Integer registros = correoDestinoDao.contar("correodestino.contarPaginacion", parametros);
		List<?> datos = correoDestinoDao.listarConPaginacion(filtro.getPaginacion(), parametros, "correodestino.listarPaginacion",
				CorreoDestinodto.class);
		filtro.getPaginacion().setPaginacionRegistrosPorPagina(50);
		filtro.getPaginacion().setPaginacionRegistrosEncontrados(registros.intValue());
		filtro.getPaginacion().setPaginacionListaResultado(datos);
		
		
		return filtro.getPaginacion();
	}

}
