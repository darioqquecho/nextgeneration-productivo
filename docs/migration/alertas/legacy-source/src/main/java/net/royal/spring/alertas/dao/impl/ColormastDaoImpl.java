package net.royal.spring.alertas.dao.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import jakarta.annotation.Resource;

import org.hibernate.SessionFactory;
import org.springframework.stereotype.Repository;

import net.royal.spring.alertas.dominio.Colormast;
import net.royal.spring.alertas.dominio.ColormastPk;
import net.royal.spring.alertas.dominio.dto.Colordto;
import net.royal.spring.alertas.dominio.filtros.FiltroPaginacionColor;
import net.royal.spring.framework.constante.ConstanteEstadoGenerico;
import net.royal.spring.framework.modelo.generico.DominioPaginacion;
import net.royal.spring.framework.modelo.generico.DominioParametroPersistencia;
import net.royal.spring.framework.modelo.seguridad.SeguridadUsuarioActual;
import net.royal.spring.framework.util.UString;
import net.royal.spring.framework.web.dao.impl.GenericoDaoImpl;

@Repository
public class ColormastDaoImpl extends GenericoDaoImpl<Colormast, ColormastPk> {

	private static final long serialVersionUID = 1L;

	public ColormastDaoImpl() {
		super("colormast");
	}

	//@Resource(name = "sessionFactory")
	public void asignarSessionFactory(SessionFactory factory) {
		super.setSessionFactory(factory);
	}

	public Colormast obtenerPorId(String pcolor) {
		return obtenerPorId(new ColormastPk( pcolor));
	}

	public Colormast coreInsertar(Colormast bean) {
		// TODO Colormast.Insertar Datos
		
		this.registrar(bean);
		return bean;
	}

	public Colormast coreInsertar(SeguridadUsuarioActual usuarioActual, Colormast bean, String estado) {
		if (UString.estaVacio(estado))
			estado = ConstanteEstadoGenerico.ACTIVO;
		bean.setEstado(estado);
		bean.setUltimafechamodif(new Date());
		bean.setUltimousuario(usuarioActual.getUsuario());
		this.registrar(bean);
		return bean;
	}

	public Colormast coreActualizar(SeguridadUsuarioActual usuarioActual, Colormast bean, String estado) {
		if (UString.estaVacio(estado))
			estado = ConstanteEstadoGenerico.ACTIVO;
		bean.setEstado(estado);
		bean.setUltimafechamodif(new Date());
		bean.setUltimousuario(usuarioActual.getUsuario());
		this.actualizar(bean);
		return bean;
	}

	public Colormast coreActualizar(Colormast bean) {
		this.actualizar(bean);
		return bean;
	}

	public DominioPaginacion listarColores(FiltroPaginacionColor filtro) {
		List<DominioParametroPersistencia> parametros = new ArrayList<DominioParametroPersistencia>();
 
		parametros.add(new DominioParametroPersistencia("p_codigo", String.class, filtro.getCodigo()));
		parametros.add(new DominioParametroPersistencia("p_nombre", String.class, filtro.getNombre()));
		parametros.add(new DominioParametroPersistencia("p_estado", String.class, filtro.getEstado()));
 
		Integer registros = this.contar("colormast.ListadoPaginacion.contar", parametros);
		List<?> datos = this.listarConPaginacion(filtro.getPaginacion(), parametros, "colormast.ListadoPaginacion",
				Colordto.class);
		filtro.getPaginacion().setPaginacionRegistrosPorPagina(10);
		filtro.getPaginacion().setPaginacionRegistrosEncontrados(registros.intValue());
		filtro.getPaginacion().setPaginacionListaResultado(datos);
		return filtro.getPaginacion();
	}
}
