package net.royal.spring.alertas.dao.impl;

import java.util.Date;

import jakarta.annotation.Resource;

import org.hibernate.SessionFactory;
import org.springframework.stereotype.Repository;

import net.royal.spring.alertas.dominio.PerfilCorreo;
import net.royal.spring.alertas.dominio.PerfilCorreoPk;
import net.royal.spring.framework.constante.ConstanteEstadoGenerico;
import net.royal.spring.framework.modelo.seguridad.SeguridadUsuarioActual;
import net.royal.spring.framework.util.UString;
import net.royal.spring.framework.web.dao.impl.GenericoDaoImpl;

@Repository
public class PerfilCorreoDaoImpl extends GenericoDaoImpl<PerfilCorreo, PerfilCorreoPk> {

	private static final long serialVersionUID = 1L;

	public PerfilCorreoDaoImpl() {
		super("perfilcorreo");
	}

	//@Resource(name = "sessionFactory")
	public void asignarSessionFactory(SessionFactory factory) {
		super.setSessionFactory(factory);
	}

	public PerfilCorreo obtenerPorId(Integer pidPerfilCorreo) {
		return obtenerPorId(new PerfilCorreoPk( pidPerfilCorreo));
	}

	public PerfilCorreo coreInsertar(PerfilCorreo bean) {
		// TODO PerfilCorreo.Insertar Datos
		
		this.registrar(bean);
		return bean;
	}

	public PerfilCorreo coreInsertar(SeguridadUsuarioActual usuarioActual, PerfilCorreo bean, String estado) {
		if (UString.estaVacio(estado))
			estado = ConstanteEstadoGenerico.ACTIVO;
		bean.setEstado(estado);
		bean.setCreacionTerminal(usuarioActual.getDireccionIp());
		bean.setCreacionFecha(new Date());
		bean.setCreacionUsuario(usuarioActual.getUsuario());
		this.registrar(bean);
		return bean;
	}

	public PerfilCorreo coreActualizar(SeguridadUsuarioActual usuarioActual, PerfilCorreo bean, String estado) {
		if (UString.estaVacio(estado))
			estado = ConstanteEstadoGenerico.ACTIVO;
		bean.setEstado(estado);
		bean.setModificacionTerminal(usuarioActual.getDireccionIp());
		bean.setModificacionFecha(new Date());
		bean.setModificacionUsuario(usuarioActual.getUsuario());
		this.actualizar(bean);
		return bean;
	}

	public PerfilCorreo coreActualizar(PerfilCorreo bean) {
		this.actualizar(bean);
		return bean;
	}

}
