package net.royal.spring.alertas.dao.impl;

import java.util.Date;

import jakarta.annotation.Resource;

import org.hibernate.SessionFactory;
import org.springframework.stereotype.Repository;

import net.royal.spring.alertas.dominio.FuenteAlertaAdicional;
import net.royal.spring.alertas.dominio.FuenteAlertaAdicionalPk;
import net.royal.spring.framework.constante.ConstanteEstadoGenerico;
import net.royal.spring.framework.modelo.seguridad.SeguridadUsuarioActual;
import net.royal.spring.framework.util.UString;
import net.royal.spring.framework.web.dao.impl.GenericoDaoImpl;

@Repository
public class FuenteAlertaAdicionalDaoImpl extends GenericoDaoImpl<FuenteAlertaAdicional, FuenteAlertaAdicionalPk> {

	private static final long serialVersionUID = 1L;

	public FuenteAlertaAdicionalDaoImpl() {
		super("fuentealertaadicional");
	}

	//@Resource(name = "sessionFactory")
	public void asignarSessionFactory(SessionFactory factory) {
		super.setSessionFactory(factory);
	}

	public FuenteAlertaAdicional obtenerPorId(Integer pidFuenteAlerta,Integer pnroRegistro,String pcampoNombre) {
		return obtenerPorId(new FuenteAlertaAdicionalPk( pidFuenteAlerta, pnroRegistro, pcampoNombre));
	}

	public FuenteAlertaAdicional coreInsertar(FuenteAlertaAdicional bean) {
		// TODO FuenteAlertaAdicional.Insertar Datos
		
		this.registrar(bean);
		return bean;
	}

	public FuenteAlertaAdicional coreInsertar(SeguridadUsuarioActual usuarioActual, FuenteAlertaAdicional bean, String estado) {
		if (UString.estaVacio(estado))
			estado = ConstanteEstadoGenerico.ACTIVO;
		bean.setEstado(estado);
		bean.setCreacionTerminal(usuarioActual.getDireccionIp());
		bean.setCreacionFecha(new Date());
		bean.setCreacionUsuario(usuarioActual.getUsuario());
		this.registrar(bean);
		return bean;
	}

	public FuenteAlertaAdicional coreActualizar(SeguridadUsuarioActual usuarioActual, FuenteAlertaAdicional bean, String estado) {
		if (UString.estaVacio(estado))
			estado = ConstanteEstadoGenerico.ACTIVO;
		bean.setEstado(estado);
		bean.setModificacionTerminal(usuarioActual.getDireccionIp());
		bean.setModificacionFecha(new Date());
		bean.setModificacionUsuario(usuarioActual.getUsuario());
		this.actualizar(bean);
		return bean;
	}

	public FuenteAlertaAdicional coreActualizar(FuenteAlertaAdicional bean) {
		this.actualizar(bean);
		return bean;
	}

}
