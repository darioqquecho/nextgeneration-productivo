package net.royal.spring.alertas.dao.impl;

import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
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

import net.royal.spring.alertas.dominio.ReglaNegocio;
import net.royal.spring.alertas.dominio.ReglaNegocioPk;
import net.royal.spring.alertas.dominio.dto.Colordto;
import net.royal.spring.alertas.dominio.dto.DtoBdCampo;
import net.royal.spring.alertas.dominio.dto.DtoBdComparar;
import net.royal.spring.alertas.dominio.dto.DtoBdEjecutar;
import net.royal.spring.alertas.dominio.dto.DtoBdObject;
import net.royal.spring.alertas.dominio.dto.DtoDatosServidor;
import net.royal.spring.alertas.dominio.dto.DtoTabla;
import net.royal.spring.alertas.dominio.dto.FuenteHistoriaDto;
import net.royal.spring.alertas.dominio.dto.ReglaNegocioDto;
import net.royal.spring.alertas.dominio.dto.ReglaNegocioTaskDto;
import net.royal.spring.alertas.dominio.filtros.FiltroPaginacionColor;
import net.royal.spring.alertas.dominio.filtros.FiltroPaginacionReglaNegocio;
import net.royal.spring.framework.constante.ConstanteEstadoGenerico;
import net.royal.spring.framework.constante.ConstanteDatos.SORT_ORDER;
import net.royal.spring.framework.core.UException;
import net.royal.spring.framework.core.UValidador;
import net.royal.spring.framework.modelo.generico.DominioMensajeUsuario;
import net.royal.spring.framework.modelo.generico.DominioPaginacion;
import net.royal.spring.framework.modelo.generico.DominioParametroPersistencia;
import net.royal.spring.framework.modelo.generico.DominioTransaccion;
import net.royal.spring.framework.modelo.seguridad.SeguridadUsuarioActual;
import net.royal.spring.framework.util.UString;
import net.royal.spring.framework.web.dao.impl.GenericoDaoImpl;

@Repository
public class ReglaNegocioDaoImpl extends GenericoDaoImpl<ReglaNegocio, ReglaNegocioPk> {

	private static final long serialVersionUID = 1L;

	public ReglaNegocioDaoImpl() {
		super("reglanegocio");
	}

	//@Resource(name = "sessionFactory")
	public void asignarSessionFactory(SessionFactory factory) {
		super.setSessionFactory(factory);
	}

	public ReglaNegocio obtenerPorId(Integer pidReglaNegocio) {
		return obtenerPorId(new ReglaNegocioPk(pidReglaNegocio));
	}

	public ReglaNegocio coreInsertar(ReglaNegocio bean) {
		// TODO ReglaNegocio.Insertar Datos

		this.registrar(bean);
		return bean;
	}

	@Transactional
	public Integer generarSecuencia() {
		/*JDK17 OK*/
		/*Criteria c = this.getCriteria()
				.setProjection(Projections.projectionList().add(Projections.max("pk.idReglaNegocio")));
		return this.incrementarInteger(c);*/
		
		
		CriteriaBuilder cb = this.getCriteriaBuilder();
		CriteriaQuery cq = cb.createQuery(Integer.class);		
		Root root = cq.from(ReglaNegocio.class);
		cq.select(cb.max(root.get("pk").get("idReglaNegocio")));
		
		return this.incrementarInteger(cq);
	}

	public ReglaNegocio coreInsertar(SeguridadUsuarioActual usuarioActual, ReglaNegocio bean, String estado) {
		if (UString.estaVacio(estado))
			estado = ConstanteEstadoGenerico.ACTIVO;
		bean.setEstado(estado);
		bean.setCreacionTerminal(usuarioActual.getDireccionIp());
		bean.setCreacionFecha(new Date());
		bean.setCreacionUsuario(usuarioActual.getUsuario());
		this.registrar(bean);
		return bean;
	}

	public ReglaNegocio coreActualizar(SeguridadUsuarioActual usuarioActual, ReglaNegocio bean, String estado) {
		if (UString.estaVacio(estado))
			estado = ConstanteEstadoGenerico.ACTIVO;
		bean.setEstado(estado);
		bean.setModificacionTerminal(usuarioActual.getDireccionIp());
		bean.setModificacionFecha(new Date());
		bean.setModificacionUsuario(usuarioActual.getUsuario());
		this.actualizar(bean);
		return bean;
	}

	public ReglaNegocio coreActualizar(ReglaNegocio bean) {
		this.actualizar(bean);
		return bean;
	}

	@SuppressWarnings("rawtypes")
	@Transactional
	public Date obtenerFechaHoraServidor() {
		DtoDatosServidor dtoDatosServidor;
		List lista = this.listarPorQuery(DtoDatosServidor.class, "reglanegocio.obtenerFechaHoraServidor");
		dtoDatosServidor = (DtoDatosServidor) lista.get(0);
		return dtoDatosServidor.getFechaHoraActual();
	}

	@SuppressWarnings("rawtypes")
	public SeguridadUsuarioActual obtenerUsuarioActual() {
		SeguridadUsuarioActual usuarioActual;
		List lista;
		lista = this.listarPorQuery(SeguridadUsuarioActual.class, "reglanegocio.obtenerUsuarioActual");
		if (lista == null)
			return null;
		if (lista.size() != 1)
			return null;
		usuarioActual = (SeguridadUsuarioActual) lista.get(0);

		usuarioActual.setUsuario(UValidador.obtenerValorCadenaSinNulo(usuarioActual.getUsuario()).toUpperCase());

		return usuarioActual;
	}

	public void LogGenerarAlerta(SeguridadUsuarioActual usuarioActual, Integer idReglaNegocio) {
		//logger.debug("LogGenerarAlerta-->");
		//logger.debug(idReglaNegocio);
		//logger.debug(usuarioActual.getUsuario());
		//logger.debug(usuarioActual.getDireccionIp());
		List<DominioParametroPersistencia> parametros = new ArrayList<DominioParametroPersistencia>();
		parametros.add(new DominioParametroPersistencia("p_id_regla_negocio", Integer.class, idReglaNegocio));
		parametros.add(new DominioParametroPersistencia("p_usuario", String.class, usuarioActual.getUsuario()));
		parametros.add(new DominioParametroPersistencia("p_terminal", String.class, usuarioActual.getDireccionIp()));
		this.ejecutarPorQuery("reglanegocio.LogGenerarAlerta", parametros);
	}

	@SuppressWarnings("unchecked")
	public List<ReglaNegocio> listarActivos() {
		/*JDK17 OK*/
		/*Criteria criteria = this.getCriteria();
		criteria.add(Restrictions.eq("estado", "A"));
		return criteria.list();*/
		
		CriteriaBuilder cb = this.getCriteriaBuilder();
		CriteriaQuery cq = cb.createQuery(Integer.class);		
		Root root = cq.from(ReglaNegocio.class);
		cq.select(root).where(  
				cb.equal(root.get("estado"), "A")
		);
		return this.listarPorCriteriaQuery(cq);
	}

	@Transactional
	public DominioPaginacion listar(FiltroPaginacionReglaNegocio filtro) throws Exception {
		List<DominioParametroPersistencia> parametros = new ArrayList<DominioParametroPersistencia>();
		filtro.getPaginacion().setPaginacionRegistrosPorPagina(120);

		parametros.add(new DominioParametroPersistencia("p_regla", BigDecimal.class, filtro.getIdReglaNegocio()));
		parametros.add(new DominioParametroPersistencia("p_idarea", BigDecimal.class, filtro.getIdAreaNegocio()));
		parametros.add(new DominioParametroPersistencia("p_compania", String.class, filtro.getIdCompania()));
		parametros.add(new DominioParametroPersistencia("p_estado", String.class, filtro.getEstado()));

		Integer registros = this.contar("reglanegocio.contar", parametros);
		List<?> datos = this.listarConPaginacion(filtro.getPaginacion(), parametros, "reglanegocio.listar",
				ReglaNegocioDto.class);
		filtro.getPaginacion().setPaginacionRegistrosPorPagina(50);
		filtro.getPaginacion().setPaginacionRegistrosEncontrados(registros.intValue());
		filtro.getPaginacion().setPaginacionListaResultado(datos);
		return filtro.getPaginacion();
	}

	@Transactional
	public List<FuenteHistoriaDto> inactivar(SeguridadUsuarioActual usuarioActual, List<FuenteHistoriaDto> dto)
			throws UException {
		List<DominioParametroPersistencia> parametros = new ArrayList<DominioParametroPersistencia>();

		for (FuenteHistoriaDto x : dto) {
			if (x.getFlgInactivar() == true) {
				/*
				 * FuenteHistoria obid =
				 * fuenteHistoriaDao.obtenerPorId(x.getIdReglaNegocio().intValue(),
				 * x.getIdFuenteAlerta().intValue(),x.getNroRegistro().intValue());
				 * obid.setEstado("I"); fuenteHistoriaDao.actualizar(obid);
				 */
				parametros.add(
						new DominioParametroPersistencia("p_reglanegocio", BigDecimal.class, x.getIdReglaNegocio()));
				parametros.add(
						new DominioParametroPersistencia("p_fuentealerta", BigDecimal.class, x.getIdFuenteAlerta()));
				parametros.add(new DominioParametroPersistencia("p_nroregistro", BigDecimal.class, x.getNroRegistro()));

				this.ejecutarPorQuery("fuentehistoria.inactivar", parametros);
			}
		}
		return dto;
	}

	@SuppressWarnings({ "rawtypes", "unchecked" })
	@Transactional
	public List<DtoTabla> comboareanegocio() {
		List datos = this.listarPorQuery(DtoTabla.class, "reglanegocio.comboareanegocio");
		return datos;
	}

	@SuppressWarnings({ "rawtypes", "unchecked" })
	@Transactional
	public List<DtoTabla> combocompania() {
		List datos = this.listarPorQuery(DtoTabla.class, "reglanegocio.combocompania");
		return datos;
	}

	public DominioPaginacion listarColores(FiltroPaginacionColor filtro) {
		List<DominioParametroPersistencia> parametros = new ArrayList<DominioParametroPersistencia>();

		parametros.add(new DominioParametroPersistencia("p_codigo", String.class, filtro.getCodigo()));
		parametros.add(new DominioParametroPersistencia("p_nombre", String.class, filtro.getNombre()));
		parametros.add(new DominioParametroPersistencia("p_estado", String.class, filtro.getEstado()));

		Integer registros = this.contar("reglanegocio.colorListadoPaginacion.contar", parametros);
		List<?> datos = this.listarConPaginacion(filtro.getPaginacion(), parametros,
				"reglanegocio.colorListadoPaginacion", Colordto.class);
		filtro.getPaginacion().setPaginacionRegistrosPorPagina(10);
		filtro.getPaginacion().setPaginacionRegistrosEncontrados(registros.intValue());
		filtro.getPaginacion().setPaginacionListaResultado(datos);
		return filtro.getPaginacion();
	}

	@SuppressWarnings({ "rawtypes", "unchecked" })
	@Transactional
	public List<ReglaNegocioTaskDto> listarParaTask() {
		List datos = this.listarPorQuery(ReglaNegocioTaskDto.class, "reglanegocio.listarParaTask");
		return datos;
	}
	
	@SuppressWarnings({ "rawtypes", "unchecked" })
	@Transactional
	public ReglaNegocioTaskDto listarPorReglaParaTask(Integer idReglaNegocio) {
		List<DominioParametroPersistencia> parametros = new ArrayList<DominioParametroPersistencia>();
		parametros.add(new DominioParametroPersistencia("p_idrn", Integer.class, idReglaNegocio));
		List datos = this.listarPorQuery(ReglaNegocioTaskDto.class, "reglanegocio.listarPorReglaParaTask",parametros);
		if (datos.size()==1)
			return (ReglaNegocioTaskDto)datos.get(0);
		return new ReglaNegocioTaskDto();
	}
	
	
	//===========================================================================================
	
		public DtoBdEjecutar ejecutar(DtoBdEjecutar dto) {
			logger.debug("ejecutar-inicio");
			try {
				StringBuilder sql = new StringBuilder(dto.getSentencia());
				List lst = this.listarPorSentenciaSQLGenerica(sql);
				dto.setLstResultado(lst);	
				dto.setTransaccionEstado(DominioTransaccion.OK);
				//dto.setTransaccionFechaHora(new Date());
			} catch (Exception e) {
				e.printStackTrace();
				//dto.setTransaccionFechaHora(new Date());
				dto.setTransaccionEstado(DominioTransaccion.ERROR);
				dto.getTransaccionListaMensajes().add(new DominioMensajeUsuario(e));
				return dto;
			}
			logger.debug("ejecutar-fin");
			return dto;
		}
		
		
		public DtoBdComparar comparar(DtoBdComparar dto) {
			logger.debug("APROBAR-INICIO");
			try {
				List<DtoBdObject> lstResultado = null;
				logger.debug("APROBAR-origenObjetos");
				List<DtoBdObject> origenObjetos = listarCnnObjetos(dto.getOrigenCnnUrl(),dto.getOrigenCnnUsuario(),dto.getOrigenCnnClave(),dto.getOrigenSqlCabecera());
				logger.debug("APROBAR-origenColumnas");
				List<DtoBdCampo> origenColumnas = listarCnnColumnas(dto.getOrigenCnnUrl(),dto.getOrigenCnnUsuario(),dto.getOrigenCnnClave(),dto.getOrigenSqlColumnas());
				logger.debug("APROBAR-destinoObjetos");
				List<DtoBdObject> destinoObjetos = listarSqlObjetos(dto.getDestinoSqlCabecera());
				logger.debug("APROBAR-destinoColumnas");
				List<DtoBdCampo> destinoColumnas = listarSqlColumnas(dto.getDestinoSqlColumnas());
				logger.debug("origenObjetos:"+origenObjetos.size());
				logger.debug("origenColumnas:"+origenColumnas.size());
				logger.debug("destinoObjetos:"+destinoObjetos.size());
				logger.debug("destinoColumnas:"+destinoColumnas.size());			
				lstResultado = DtoBdObject.comparar(origenObjetos, origenColumnas, destinoObjetos, destinoColumnas,dto.getComparacion());
				logger.debug("lstResultado.size():"+lstResultado.size());	
				dto.setListaResultado(lstResultado);
				dto.setTransaccionEstado(DominioTransaccion.OK);
				dto.setTransaccionFechaHora(new Date());
				logger.debug("APROBAR-FIN");
			} catch (Exception e) {
				logger.debug("APROBAR-ERROR");
				e.printStackTrace();
				dto.setTransaccionFechaHora(new Date());
				dto.setTransaccionEstado(DominioTransaccion.ERROR);
				dto.getTransaccionListaMensajes().add(new DominioMensajeUsuario(e));
				return dto;
			}
			return dto;
		}

		
		public List<DtoBdObject> listarSqlObjetos(String sql) throws Exception {
			StringBuilder sentenciaSQL = new StringBuilder(sql);
			List<DominioParametroPersistencia> parametros = new ArrayList<DominioParametroPersistencia>();
			List lista = this.listarPorSentenciaSQL(sentenciaSQL, parametros, DtoBdObject.class);	
			return lista;
		}	
		public List<DtoBdCampo> listarSqlColumnas(String sql) throws Exception {
			StringBuilder sentenciaSQL = new StringBuilder(sql);
			List<DominioParametroPersistencia> parametros = new ArrayList<DominioParametroPersistencia>();
			List lista = this.listarPorSentenciaSQL(sentenciaSQL, parametros, DtoBdCampo.class);
			return lista;
		}
		public List<DtoBdObject> listarCnnObjetos(String url,String user,String clave,String sql) throws Exception {
			List<DtoBdObject> origenObjetos = new ArrayList<DtoBdObject>();
			Connection connection = null;
	        Statement selectStmt = null;
			try {
				Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
				connection = DriverManager.getConnection(url, user, clave);
		        selectStmt = connection.createStatement();
		        ResultSet rs = selectStmt.executeQuery(sql);
		        while(rs.next())
		        {
		        	DtoBdObject e=new DtoBdObject();
		        	e.setEsquema(rs.getString(1));
		        	e.setObjeto(rs.getString(2));
		            origenObjetos.add(e);
		        }
			} catch (Exception e) {
				try {
	                selectStmt.close();
	                connection.close();
	            } catch (Exception e2) {
	                e2.printStackTrace();
	            }
			}finally {
	            try {
	                selectStmt.close();
	                connection.close();
	            } catch (Exception e) {
	                e.printStackTrace();
	            }
	        }
			return origenObjetos;
		}
		public List<DtoBdCampo> listarCnnColumnas(String url,String user,String clave,String sql) throws Exception {
			List<DtoBdCampo> origenObjetos = new ArrayList<DtoBdCampo>();
			Connection connection = null;
	        Statement selectStmt = null;
			try {
				Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
				connection = DriverManager.getConnection(url, user, clave);
		        selectStmt = connection.createStatement();
		        ResultSet rs = selectStmt.executeQuery(sql);
		        while(rs.next())
		        {
		        	DtoBdCampo e=new DtoBdCampo();
		        	e.setEsquema(rs.getString(1));
		        	e.setObjeto(rs.getString(2));
		        	
		        	e.setColumna(rs.getString(3));
		        	e.setTipoDato(rs.getString(4));
		        	e.setLongitud(rs.getBigDecimal(5));
		        	e.setPrecision(rs.getBigDecimal(6));
		        	e.setEscala(rs.getBigDecimal(7));
		        	e.setColumnaId(rs.getBigDecimal(8));
		        	e.setNulo(rs.getString(9));
		        	
		            origenObjetos.add(e);
		        }
			} catch (Exception e) {
				try {
	                selectStmt.close();
	                connection.close();
	            } catch (Exception e2) {
	                e2.printStackTrace();
	            }
			}finally {
	            try {
	                selectStmt.close();
	                connection.close();
	            } catch (Exception e) {
	                e.printStackTrace();
	            }
	        }
			return origenObjetos;
		}
		
		@SuppressWarnings({ "rawtypes", "unchecked" })
		@Transactional
		public void registrarEjecucionLog(String javaLog, String spLog,String resumenLog,Integer reglaNegocioId,Integer registrosExito,Integer registrosError) {
			try {
				List<DominioParametroPersistencia> parametros = new ArrayList<DominioParametroPersistencia>();
				parametros.add(new DominioParametroPersistencia("p_javalog", String.class, javaLog));
				parametros.add(new DominioParametroPersistencia("p_splog", String.class, spLog));
				parametros.add(new DominioParametroPersistencia("p_resumenlog", String.class, resumenLog));
				parametros.add(new DominioParametroPersistencia("p_rn_id", Integer.class, reglaNegocioId));
				
				parametros.add(new DominioParametroPersistencia("p_reg_exito", Integer.class, registrosExito));
				parametros.add(new DominioParametroPersistencia("p_reg_error", Integer.class, registrosError));
				
				this.ejecutarPorQuery("reglanegocio.registrarLog",parametros);
			} catch (Exception e) {
				e.printStackTrace();
			}			
		}
		
		
		@Transactional
		public DominioPaginacion listarlogs(FiltroPaginacionReglaNegocio filtro) throws Exception {
			List<DominioParametroPersistencia> parametros = new ArrayList<DominioParametroPersistencia>();
			filtro.getPaginacion().setPaginacionRegistrosPorPagina(50);

			parametros.add(new DominioParametroPersistencia("p_regla", BigDecimal.class, filtro.getIdReglaNegocio()));
			
			filtro.getPaginacion().setPaginacionOrdenDireccion(SORT_ORDER.DESC);
			filtro.getPaginacion().setPaginacionOrdenAtributo("SGALERTASSYS.EJECUCION_LOG.CREACION_FECHA");
			
			Integer registros = this.contar("reglanegocio.contarlogs", parametros);
			List<?> datos = this.listarConPaginacion(filtro.getPaginacion(), parametros, "reglanegocio.listarlogs", ReglaNegocioDto.class);
			filtro.getPaginacion().setPaginacionRegistrosPorPagina(50);
			filtro.getPaginacion().setPaginacionRegistrosEncontrados(registros.intValue());
			filtro.getPaginacion().setPaginacionListaResultado(datos);
			return filtro.getPaginacion();
		}
		
		
		public ReglaNegocioDto obtenerDtoPorId(Integer id) {
			ReglaNegocioDto dto = null;
			List<DominioParametroPersistencia> parametros = new ArrayList<DominioParametroPersistencia>();
			parametros.add(new DominioParametroPersistencia("p_reglanegocioid", Integer.class, id));
			List lst = listarPorQuery(ReglaNegocioDto.class, "reglanegocio.obtenerDtoPorId", parametros);
			if (lst.size() == 1)
				dto = (ReglaNegocioDto) lst.get(0);
			return dto;
		}
		
}
