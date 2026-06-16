package net.royal.spring.alertas.dao.impl;

import java.util.Date;

import jakarta.annotation.Resource;

import org.hibernate.SessionFactory;
import org.springframework.stereotype.Repository;

import net.royal.spring.alertas.dominio.FuenteAlerta;
import net.royal.spring.alertas.dominio.FuenteAlertaPk;
import net.royal.spring.framework.constante.ConstanteEstadoGenerico;
import net.royal.spring.framework.modelo.seguridad.SeguridadUsuarioActual;
import net.royal.spring.framework.util.UString;
import net.royal.spring.framework.web.dao.impl.GenericoDaoImpl;

@Repository
public class FuenteAlertaDaoImpl extends GenericoDaoImpl<FuenteAlerta, FuenteAlertaPk> {

	private static final long serialVersionUID = 1L;

	public FuenteAlertaDaoImpl() {
		super("fuentealerta");
	}

	//@Resource(name = "sessionFactory")
	public void asignarSessionFactory(SessionFactory factory) {
		super.setSessionFactory(factory);
	}

	public FuenteAlerta obtenerPorId(Integer pidFuenteAlerta) {
		return obtenerPorId(new FuenteAlertaPk( pidFuenteAlerta));
	}

	public FuenteAlerta coreInsertar(FuenteAlerta bean) {
		// TODO FuenteAlerta.Insertar Datos
		
		this.registrar(bean);
		return bean;
	}

	public FuenteAlerta coreInsertar(SeguridadUsuarioActual usuarioActual, FuenteAlerta bean, String estado) {
		if (UString.estaVacio(estado))
			estado = ConstanteEstadoGenerico.ACTIVO;
		bean.setEstado(estado);
		bean.setCreacionTerminal(usuarioActual.getDireccionIp());
		bean.setCreacionFecha(new Date());
		bean.setCreacionUsuario(usuarioActual.getUsuario());
		this.registrar(bean);
		return bean;
	}

	public FuenteAlerta coreActualizar(SeguridadUsuarioActual usuarioActual, FuenteAlerta bean, String estado) {
		if (UString.estaVacio(estado))
			estado = ConstanteEstadoGenerico.ACTIVO;
		bean.setEstado(estado);
		bean.setModificacionTerminal(usuarioActual.getDireccionIp());
		bean.setModificacionFecha(new Date());
		bean.setModificacionUsuario(usuarioActual.getUsuario());
		this.actualizar(bean);
		return bean;
	}

	public FuenteAlerta coreActualizar(FuenteAlerta bean) {
		this.actualizar(bean);
		return bean;
	}

}
