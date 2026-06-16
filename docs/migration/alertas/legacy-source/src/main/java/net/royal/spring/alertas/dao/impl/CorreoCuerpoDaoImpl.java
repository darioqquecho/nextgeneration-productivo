package net.royal.spring.alertas.dao.impl;

import java.util.Date;

import jakarta.annotation.Resource;

import org.hibernate.SessionFactory;
import org.springframework.stereotype.Repository;

import net.royal.spring.alertas.dominio.Correo;
import net.royal.spring.alertas.dominio.CorreoCuerpo;
import net.royal.spring.alertas.dominio.CorreoCuerpoPk;
import net.royal.spring.framework.constante.ConstanteEstadoGenerico;
import net.royal.spring.framework.modelo.seguridad.SeguridadUsuarioActual;
import net.royal.spring.framework.util.UString;
import net.royal.spring.framework.web.dao.impl.GenericoDaoImpl;

@Repository
public class CorreoCuerpoDaoImpl extends GenericoDaoImpl<CorreoCuerpo, CorreoCuerpoPk> {

	private static final long serialVersionUID = 1L;

	public CorreoCuerpoDaoImpl() {
		super("correocuerpo");
	}

		//@Resource(name = "sessionFactory")
	public void asignarSessionFactory(SessionFactory factory) {
		super.setSessionFactory(factory);
	}

	public CorreoCuerpo obtenerPorId(Integer pidCorreo) {
		return obtenerPorId(new CorreoCuerpoPk( pidCorreo));
	}

	public CorreoCuerpo coreInsertar(CorreoCuerpo bean) {
		// TODO CorreoCuerpo.Insertar Datos
		
		this.registrar(bean);
		return bean;
	}

	public CorreoCuerpo coreInsertar(SeguridadUsuarioActual usuarioActual, CorreoCuerpo bean, String estado) {
		if (UString.estaVacio(estado))
			estado = ConstanteEstadoGenerico.ACTIVO;
		bean.setEstado(estado);
		bean.setCreacionTerminal(usuarioActual.getDireccionIp());
		bean.setCreacionFecha(new Date());
		bean.setCreacionUsuario(usuarioActual.getUsuario());
		this.registrar(bean);
		return bean;
	}

	public CorreoCuerpo coreActualizar(SeguridadUsuarioActual usuarioActual, CorreoCuerpo bean, String estado) {
		if (UString.estaVacio(estado))
			estado = ConstanteEstadoGenerico.ACTIVO;
		bean.setEstado(estado);
		bean.setModificacionTerminal(usuarioActual.getDireccionIp());
		bean.setModificacionFecha(new Date());
		bean.setModificacionUsuario(usuarioActual.getUsuario());
		this.actualizar(bean);
		return bean;
	}

	public CorreoCuerpo coreActualizar(CorreoCuerpo bean) {
		this.actualizar(bean);
		return bean;
	}
	
	public CorreoCuerpo registrar(Correo correo, byte[] cuerpoCorreo) {
		//LOGGER.debug("correo.getPk().getIdCorreo()");
		//LOGGER.debug(correo.getPk().getIdCorreo());
		CorreoCuerpo correoCuerpo=new CorreoCuerpo();
		
		correoCuerpo.getPk().setIdCorreo(correo.getPk().getIdCorreo());
		correoCuerpo.setCuerpoCorreo(cuerpoCorreo);
		correoCuerpo.setEstado("A");
		correoCuerpo.setCreacionUsuario(correo.getCreacionUsuario());
		correoCuerpo.setCreacionTerminal(correo.getCreacionTerminal());
		correoCuerpo.setCreacionFecha(correo.getCreacionFecha());
				
		this.registrar(correoCuerpo);
		
		return correoCuerpo;
	}
	
 
	}
