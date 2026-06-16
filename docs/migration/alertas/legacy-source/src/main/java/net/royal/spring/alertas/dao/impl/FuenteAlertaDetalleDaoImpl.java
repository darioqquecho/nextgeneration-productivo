package net.royal.spring.alertas.dao.impl;

import java.util.Date;

import jakarta.annotation.Resource;

import org.hibernate.SessionFactory;
import org.springframework.stereotype.Repository;

import net.royal.spring.alertas.dominio.FuenteAlertaDetalle;
import net.royal.spring.alertas.dominio.FuenteAlertaDetallePk;
import net.royal.spring.framework.constante.ConstanteEstadoGenerico;
import net.royal.spring.framework.modelo.seguridad.SeguridadUsuarioActual;
import net.royal.spring.framework.util.UString;
import net.royal.spring.framework.web.dao.impl.GenericoDaoImpl;

@Repository
public class FuenteAlertaDetalleDaoImpl extends GenericoDaoImpl<FuenteAlertaDetalle, FuenteAlertaDetallePk> {

	private static final long serialVersionUID = 1L;

	public FuenteAlertaDetalleDaoImpl() {
		super("fuentealertadetalle");
	}

	//@Resource(name = "sessionFactory")
	public void asignarSessionFactory(SessionFactory factory) {
		super.setSessionFactory(factory);
	}

	public FuenteAlertaDetalle obtenerPorId(Integer pidFuenteAlerta,Integer pnroRegistro,String pcampoNombre) {
		return obtenerPorId(new FuenteAlertaDetallePk( pidFuenteAlerta, pnroRegistro, pcampoNombre));
	}

	public FuenteAlertaDetalle coreInsertar(FuenteAlertaDetalle bean) {
		// TODO FuenteAlertaDetalle.Insertar Datos
		
		this.registrar(bean);
		return bean;
	}

	public FuenteAlertaDetalle coreInsertar(SeguridadUsuarioActual usuarioActual, FuenteAlertaDetalle bean, String estado) {
		if (UString.estaVacio(estado))
			estado = ConstanteEstadoGenerico.ACTIVO;
		bean.setEstado(estado);
		bean.setCreacionTerminal(usuarioActual.getDireccionIp());
		bean.setCreacionFecha(new Date());
		bean.setCreacionUsuario(usuarioActual.getUsuario());
		this.registrar(bean);
		return bean;
	}

	public FuenteAlertaDetalle coreActualizar(SeguridadUsuarioActual usuarioActual, FuenteAlertaDetalle bean, String estado) {
		if (UString.estaVacio(estado))
			estado = ConstanteEstadoGenerico.ACTIVO;
		bean.setEstado(estado);
		bean.setModificacionTerminal(usuarioActual.getDireccionIp());
		bean.setModificacionFecha(new Date());
		bean.setModificacionUsuario(usuarioActual.getUsuario());
		this.actualizar(bean);
		return bean;
	}

	public FuenteAlertaDetalle coreActualizar(FuenteAlertaDetalle bean) {
		this.actualizar(bean);
		return bean;
	}

}
