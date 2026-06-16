package net.royal.spring.alertas.servicio.impl;

import java.io.UnsupportedEncodingException;
import java.nio.ByteBuffer;
import java.nio.CharBuffer;
import java.nio.charset.CharacterCodingException;
import java.nio.charset.Charset;
import java.nio.charset.CharsetDecoder;
import java.nio.charset.CodingErrorAction;
import java.nio.charset.StandardCharsets;
import java.util.List;

import jakarta.mail.internet.MimeUtility;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import net.royal.spring.alertas.dao.impl.CorreoCuerpoDaoImpl;
import net.royal.spring.alertas.dominio.CorreoCuerpo;
import net.royal.spring.alertas.dominio.CorreoCuerpoPk;
import net.royal.spring.alertas.dominio.filtros.FiltroPaginacionCorreo;
import net.royal.spring.alertas.rest.CorreoRest;
import net.royal.spring.alertas.servicio.validar.CorreoCuerpoServicioValidar;
import net.royal.spring.framework.core.UException;
import net.royal.spring.framework.modelo.generico.DominioMensajeUsuario;
import net.royal.spring.framework.modelo.seguridad.SeguridadUsuarioActual;
import net.royal.spring.framework.web.servicio.impl.GenericoServicioImpl;

@Service (value = "BeanServicioCorreoCuerpo")
public class CorreoCuerpoServicioImpl extends GenericoServicioImpl {
	private static Logger logger = LogManager.getLogger(CorreoCuerpoServicioImpl.class);
	public static String SPRING_NOMBRE = "BeanServicioCorreoCuerpo";

	@Autowired
	private CorreoCuerpoDaoImpl correoCuerpoDao;

	@Autowired
	private CorreoCuerpoServicioValidar validar;

	@Transactional
	public CorreoCuerpo coreInsertar(SeguridadUsuarioActual usuarioActual,CorreoCuerpo correoCuerpo) throws UException {
		// valores por defecto - preparando objeto
		correoCuerpo = validar.prepararInsertar(usuarioActual, correoCuerpo);
		
		// validaciones de negocio
		List<DominioMensajeUsuario> lst = validar.coreInsertar(usuarioActual, correoCuerpo);
		if (!lst.isEmpty())
			throw new UException(lst);
		
		// transaccion
		return correoCuerpoDao.coreInsertar(correoCuerpo);
	}

	@Transactional
	public CorreoCuerpo coreActualizar(SeguridadUsuarioActual usuarioActual, CorreoCuerpo correoCuerpo) throws UException {
		// valores por defecto - preparando objeto
		correoCuerpo = validar.prepararActualizar(usuarioActual, correoCuerpo);
		
		// validaciones de negocio
		List<DominioMensajeUsuario> lst = validar.coreActualizar(usuarioActual, correoCuerpo);
		if (!lst.isEmpty())
			throw new UException(lst);
		
		// transaccion
	    correoCuerpo = correoCuerpoDao.coreActualizar(correoCuerpo);
		return correoCuerpo;
	}

	@Transactional
	public CorreoCuerpo coreAnular(SeguridadUsuarioActual usuarioActual, CorreoCuerpo correoCuerpo) throws UException {
		// valores por defecto - preparando objeto
		correoCuerpo = validar.prepararAnular(usuarioActual, correoCuerpo);
		
		// validaciones de negocio
		List<DominioMensajeUsuario> lst = validar.coreAnular(usuarioActual, correoCuerpo);
		if (!lst.isEmpty())
			throw new UException(lst);
		
		// transaccion
		return correoCuerpoDao.coreActualizar(correoCuerpo);
	}

	public CorreoCuerpo coreAnular(SeguridadUsuarioActual usuarioActual, CorreoCuerpoPk pk) throws UException {
		CorreoCuerpo bean = correoCuerpoDao.obtenerPorId(pk);
		return coreAnular(usuarioActual,bean);
	}

	public CorreoCuerpo coreAnular(SeguridadUsuarioActual usuarioActual, Integer pidCorreo) throws UException {
		return coreAnular(usuarioActual,new CorreoCuerpoPk( pidCorreo));
	}

	@Transactional
	public void coreEliminar(SeguridadUsuarioActual usuarioActual, CorreoCuerpo correoCuerpo) throws UException {
		// valores por defecto - preparando objeto
		correoCuerpo = validar.prepararEliminar(usuarioActual, correoCuerpo);
		
		// validaciones de negocio
		List<DominioMensajeUsuario> lst = validar.coreEliminar(usuarioActual, correoCuerpo);
		if (!lst.isEmpty())
			throw new UException(lst);
		
		// transaccion
		correoCuerpoDao.eliminar(correoCuerpo);
	}

	public void coreEliminar(SeguridadUsuarioActual usuarioActual, CorreoCuerpoPk pk) throws UException {
		CorreoCuerpo correoCuerpo = correoCuerpoDao.obtenerPorId(pk);
		coreEliminar(usuarioActual,correoCuerpo);
	}

	public void coreEliminar(SeguridadUsuarioActual usuarioActual, Integer pidCorreo) throws UException {
		coreEliminar(usuarioActual,new CorreoCuerpoPk( pidCorreo));
	}

	
	public String CuerpoCampo(FiltroPaginacionCorreo filtro) throws UnsupportedEncodingException{
		Integer id = filtro.getIdcorreo().intValue();		

		CorreoCuerpo bean = new CorreoCuerpo();
		bean.getPk().setIdCorreo(id);
		
		bean = correoCuerpoDao.obtenerPorId(bean.getPk());
				
		byte [] bynary = bean.getCuerpoCorreo();
		String reporte = new String(bean.getCuerpoCorreo(), StandardCharsets.UTF_8);
		return reporte;
	}

	 
 
}
