package net.royal.spring.alertas.dao.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import jakarta.annotation.Resource;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Root;

import org.hibernate.SessionFactory;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import net.royal.spring.alertas.dominio.ConfiguracionServicio;
import net.royal.spring.alertas.dominio.ConfiguracionServicioPk;
import net.royal.spring.framework.constante.ConstanteEstadoGenerico;
import net.royal.spring.framework.modelo.generico.DominioParametroPersistencia;
import net.royal.spring.framework.modelo.seguridad.SeguridadUsuarioActual;
import net.royal.spring.framework.util.UString;
import net.royal.spring.framework.web.dao.impl.GenericoDaoImpl;

@Repository
public class ConfiguracionServicioDaoImpl extends GenericoDaoImpl<ConfiguracionServicio, ConfiguracionServicioPk> {

	private static final long serialVersionUID = 1L;

	public ConfiguracionServicioDaoImpl() {
		super("configuracionservicio");
	}

	//@Resource(name = "sessionFactory")
	public void asignarSessionFactory(SessionFactory factory) {
		super.setSessionFactory(factory);
	}

	public ConfiguracionServicio obtenerPorId(Integer pidConfiguracionServicio) {
		return obtenerPorId(new ConfiguracionServicioPk( pidConfiguracionServicio));
	}

	public ConfiguracionServicio coreInsertar(ConfiguracionServicio bean) {
		// TODO ConfiguracionServicio.Insertar Datos
		
		this.registrar(bean);
		return bean;
	}

	public ConfiguracionServicio coreInsertar(SeguridadUsuarioActual usuarioActual, ConfiguracionServicio bean, String estado) {
		if (UString.estaVacio(estado))
			estado = ConstanteEstadoGenerico.ACTIVO;
		bean.setEstado(estado);
		bean.setCreacionTerminal(usuarioActual.getDireccionIp());
		bean.setCreacionFecha(new Date());
		bean.setCreacionUsuario(usuarioActual.getUsuario());
		this.registrar(bean);
		return bean;
	}

	public ConfiguracionServicio coreActualizar(SeguridadUsuarioActual usuarioActual, ConfiguracionServicio bean, String estado) {
		if (UString.estaVacio(estado))
			estado = ConstanteEstadoGenerico.ACTIVO;
		bean.setEstado(estado);
		bean.setModificacionTerminal(usuarioActual.getDireccionIp());
		bean.setModificacionFecha(new Date());
		bean.setModificacionUsuario(usuarioActual.getUsuario());
		this.actualizar(bean);
		return bean;
	}

	public ConfiguracionServicio coreActualizar(ConfiguracionServicio bean) {
		this.actualizar(bean);
		return bean;
	}
	
	
	@Transactional
    public Integer generarSecuencia() {
		/*JDK17 OK*/
        /*Criteria c = this.getCriteria()
                .setProjection(Projections.projectionList().add(Projections.max("pk.idConfiguracionServicio")));
        return this.incrementarInteger(c);*/
		
		CriteriaBuilder cb = this.getCriteriaBuilder();
		CriteriaQuery cq = cb.createQuery(Integer.class);		
		Root root = cq.from(ConfiguracionServicio.class);
		cq.select(cb.max(root.get("pk").get("idConfiguracionServicio")));
		
		return this.incrementarInteger(cq);
    }
	
	public void cambiarEjecutandoEnvioCorreo(String p_estado) {
		List<DominioParametroPersistencia> parametros = new ArrayList<DominioParametroPersistencia>();
		parametros.add(new DominioParametroPersistencia("p_estado",String.class, p_estado));		
		this.ejecutarPorQuery("configuracionservicio.cambiarEjecutandoEnvioCorreo", parametros);
	}

	
	
	public void cambiarEjecutandoEvaluarRegla(String p_estado) {
		List<DominioParametroPersistencia> parametros = new ArrayList<DominioParametroPersistencia>();
		parametros.add(new DominioParametroPersistencia("p_estado",String.class, p_estado));		
		this.ejecutarPorQuery("configuracionservicio.cambiarEjecutandoEvaluarRegla", parametros);
	}

	public void cambiarEjecutandoExtraerData(String p_estado) {
		List<DominioParametroPersistencia> parametros = new ArrayList<DominioParametroPersistencia>();
		parametros.add(new DominioParametroPersistencia("p_estado",String.class, p_estado));		
		this.ejecutarPorQuery("configuracionservicio.cambiarEjecutandoExtraerData", parametros);
	}

	public void cambiarEjecutandoCrearAlerta(String p_estado) {
		List<DominioParametroPersistencia> parametros = new ArrayList<DominioParametroPersistencia>();
		parametros.add(new DominioParametroPersistencia("p_estado",String.class, p_estado));		
		this.ejecutarPorQuery("configuracionservicio.cambiarEjecutandoCrearAlerta", parametros);
	}

	public void cambiarEjecutandoCrearCorreo(String p_estado) {
		List<DominioParametroPersistencia> parametros = new ArrayList<DominioParametroPersistencia>();
		parametros.add(new DominioParametroPersistencia("p_estado",String.class, p_estado));		
		this.ejecutarPorQuery("configuracionservicio.cambiarEjecutandoCrearCorreo", parametros);
	}
	
	public ConfiguracionServicio obtenerConfiguracionActual() {
		return this.obtenerPorId(new ConfiguracionServicioPk(1),false);
	}
}
