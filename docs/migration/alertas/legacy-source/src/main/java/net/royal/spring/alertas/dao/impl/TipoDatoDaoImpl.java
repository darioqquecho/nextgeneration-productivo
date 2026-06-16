package net.royal.spring.alertas.dao.impl;

import java.util.ArrayList;
import java.util.Date;

import jakarta.annotation.Resource;
import jakarta.transaction.Transactional;

import org.hibernate.SessionFactory;
import org.springframework.stereotype.Repository;

import net.royal.spring.alertas.dominio.TipoDato;
import net.royal.spring.alertas.dominio.TipoDatoPk;
import net.royal.spring.framework.constante.ConstanteEstadoGenerico;
import net.royal.spring.framework.modelo.generico.DominioParametroPersistencia;
import net.royal.spring.framework.modelo.seguridad.SeguridadUsuarioActual;
import net.royal.spring.framework.util.UString;
import net.royal.spring.framework.web.dao.impl.GenericoDaoImpl;
import java.util.List;

@Repository
public class TipoDatoDaoImpl extends GenericoDaoImpl<TipoDato, TipoDatoPk> {

	private static final long serialVersionUID = 1L;

	public TipoDatoDaoImpl() {
		super("tipodato");
	}

	//@Resource(name = "sessionFactory")
	public void asignarSessionFactory(SessionFactory factory) {
		super.setSessionFactory(factory);
	}

	public TipoDato obtenerPorId(String pidTipoDato) {
		return obtenerPorId(new TipoDatoPk( pidTipoDato));
	}

	public TipoDato coreInsertar(TipoDato bean) {
		// TODO TipoDato.Insertar Datos
		
		this.registrar(bean);
		return bean;
	}

	public TipoDato coreInsertar(SeguridadUsuarioActual usuarioActual, TipoDato bean, String estado) {
		if (UString.estaVacio(estado))
			estado = ConstanteEstadoGenerico.ACTIVO;
		bean.setEstado(estado);
		bean.setCreacionTerminal(usuarioActual.getDireccionIp());
		bean.setCreacionFecha(new Date());
		bean.setCreacionUsuario(usuarioActual.getUsuario());
		this.registrar(bean);
		return bean;
	}

	public TipoDato coreActualizar(SeguridadUsuarioActual usuarioActual, TipoDato bean, String estado) {
		if (UString.estaVacio(estado))
			estado = ConstanteEstadoGenerico.ACTIVO;
		bean.setEstado(estado);
		bean.setModificacionTerminal(usuarioActual.getDireccionIp());
		bean.setModificacionFecha(new Date());
		bean.setModificacionUsuario(usuarioActual.getUsuario());
		this.actualizar(bean);
		return bean;
	}

	public TipoDato coreActualizar(TipoDato bean) {
		this.actualizar(bean);
		return bean;
	}
	
    @Transactional
    public void limpiarInformativos() {
        List<DominioParametroPersistencia> parametros = new ArrayList<>();
        ejecutarPorQuery("tipodato.limpiarDatodejecucionlog", parametros);
    }

}
