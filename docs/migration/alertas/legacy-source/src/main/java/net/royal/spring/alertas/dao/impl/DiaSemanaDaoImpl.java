package net.royal.spring.alertas.dao.impl;

import java.util.Date;

import jakarta.annotation.Resource;

import org.hibernate.SessionFactory;
import org.springframework.stereotype.Repository;

import net.royal.spring.alertas.dominio.DiaSemana;
import net.royal.spring.alertas.dominio.DiaSemanaPk;
import net.royal.spring.framework.constante.ConstanteEstadoGenerico;
import net.royal.spring.framework.modelo.seguridad.SeguridadUsuarioActual;
import net.royal.spring.framework.util.UString;
import net.royal.spring.framework.web.dao.impl.GenericoDaoImpl;

@Repository
public class DiaSemanaDaoImpl extends GenericoDaoImpl<DiaSemana, DiaSemanaPk> {

	private static final long serialVersionUID = 1L;

	public DiaSemanaDaoImpl() {
		super("diasemana");
	}

	//@Resource(name = "sessionFactory")
	public void asignarSessionFactory(SessionFactory factory) {
		super.setSessionFactory(factory);
	}

	public DiaSemana obtenerPorId(String pidDiaSemana) {
		return obtenerPorId(new DiaSemanaPk( pidDiaSemana));
	}

	public DiaSemana coreInsertar(DiaSemana bean) {
		// TODO DiaSemana.Insertar Datos
		
		this.registrar(bean);
		return bean;
	}

	public DiaSemana coreInsertar(SeguridadUsuarioActual usuarioActual, DiaSemana bean, String estado) {
		if (UString.estaVacio(estado))
			estado = ConstanteEstadoGenerico.ACTIVO;
		bean.setEstado(estado);
		bean.setCreacionTerminal(usuarioActual.getDireccionIp());
		bean.setCreacionFecha(new Date());
		bean.setCreacionUsuario(usuarioActual.getUsuario());
		this.registrar(bean);
		return bean;
	}

	public DiaSemana coreActualizar(SeguridadUsuarioActual usuarioActual, DiaSemana bean, String estado) {
		if (UString.estaVacio(estado))
			estado = ConstanteEstadoGenerico.ACTIVO;
		bean.setEstado(estado);
		bean.setModificacionTerminal(usuarioActual.getDireccionIp());
		bean.setModificacionFecha(new Date());
		bean.setModificacionUsuario(usuarioActual.getUsuario());
		this.actualizar(bean);
		return bean;
	}

	public DiaSemana coreActualizar(DiaSemana bean) {
		this.actualizar(bean);
		return bean;
	}

}
