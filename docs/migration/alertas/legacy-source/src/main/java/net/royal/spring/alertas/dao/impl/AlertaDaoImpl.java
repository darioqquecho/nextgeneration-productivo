package net.royal.spring.alertas.dao.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import jakarta.annotation.Resource;

import org.hibernate.SessionFactory;
import org.springframework.stereotype.Repository;

import net.royal.spring.alertas.dominio.Alerta;
import net.royal.spring.alertas.dominio.AlertaPk;
import net.royal.spring.framework.constante.ConstanteEstadoGenerico;
import net.royal.spring.framework.modelo.generico.DominioParametroPersistencia;
import net.royal.spring.framework.modelo.seguridad.SeguridadUsuarioActual;
import net.royal.spring.framework.util.UString;
import net.royal.spring.framework.web.dao.impl.GenericoDaoImpl;

@Repository
public class AlertaDaoImpl extends GenericoDaoImpl<Alerta, AlertaPk> {

	private static final long serialVersionUID = 1L;

	public AlertaDaoImpl() {
		super("alerta");
	}

	//@Resource(name = "sessionFactory")
	public void asignarSessionFactory(SessionFactory factory) {
		super.setSessionFactory(factory);
	}

	public Alerta obtenerPorId(Integer pidAlerta) {
		return obtenerPorId(new AlertaPk( pidAlerta));
	}

	public Alerta coreInsertar(Alerta bean) {
		// TODO Alerta.Insertar Datos
		
		this.registrar(bean);
		return bean;
	}

	public Alerta coreInsertar(SeguridadUsuarioActual usuarioActual, Alerta bean, String estado) {
		if (UString.estaVacio(estado))
			estado = ConstanteEstadoGenerico.ACTIVO;
		bean.setEstado(estado);
		bean.setCreacionTerminal(usuarioActual.getDireccionIp());
		bean.setCreacionFecha(new Date());
		bean.setCreacionUsuario(usuarioActual.getUsuario());
		this.registrar(bean);
		return bean;
	}

	public Alerta coreActualizar(SeguridadUsuarioActual usuarioActual, Alerta bean, String estado) {
		if (UString.estaVacio(estado))
			estado = ConstanteEstadoGenerico.ACTIVO;
		bean.setEstado(estado);
		bean.setModificacionTerminal(usuarioActual.getDireccionIp());
		bean.setModificacionFecha(new Date());
		bean.setModificacionUsuario(usuarioActual.getUsuario());
		this.actualizar(bean);
		return bean;
	}

	public Alerta coreActualizar(Alerta bean) {
		this.actualizar(bean);
		return bean;
	}
	
	
	
	public void cambiarEjecutandoEvaluarRegla(String p_estado) {
		List<DominioParametroPersistencia> parametros = new ArrayList<DominioParametroPersistencia>();
		parametros.add(new DominioParametroPersistencia("p_estado",String.class, p_estado));		
		this.ejecutarPorQuery("alerta.cambiarEjecutandoEvaluarRegla", parametros);
	}
	
	
	
	public void cambiarEjecutandoExtraerData(String p_estado) {
		List<DominioParametroPersistencia> parametros = new ArrayList<DominioParametroPersistencia>();
		parametros.add(new DominioParametroPersistencia("p_estado",String.class, p_estado));		
		this.ejecutarPorQuery("alerta.cambiarEjecutandoExtraerData", parametros);
	}

	
	public void cambiarEjecutandoCrearAlerta(String p_estado) {
		List<DominioParametroPersistencia> parametros = new ArrayList<DominioParametroPersistencia>();
		parametros.add(new DominioParametroPersistencia("p_estado",String.class, p_estado));		
		this.ejecutarPorQuery("alerta.cambiarEjecutandoCrearAlerta", parametros);
	}

	
	public void cambiarEjecutandoCrearCorreo(String p_estado) {
		List<DominioParametroPersistencia> parametros = new ArrayList<DominioParametroPersistencia>();
		parametros.add(new DominioParametroPersistencia("p_estado",String.class, p_estado));		
		this.ejecutarPorQuery("alerta.cambiarEjecutandoCrearCorreo", parametros);
	}
	
	public void cambiarEjecutandoEnvioCorreo(String p_estado) {
		List<DominioParametroPersistencia> parametros = new ArrayList<DominioParametroPersistencia>();
		parametros.add(new DominioParametroPersistencia("p_estado",String.class, p_estado));		
		this.ejecutarPorQuery("alerta.cambiarEjecutandoEnvioCorreo", parametros);
	}

	
	
}
