package net.royal.spring.alertas.servicio.impl;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import net.royal.spring.alertas.dao.impl.ReglaNegocioAdicionalDaoImpl;
import net.royal.spring.alertas.dao.impl.ReglaNegocioDaoImpl;
import net.royal.spring.alertas.dao.impl.ReglaNegocioDestinoDaoImpl;
import net.royal.spring.alertas.dao.impl.ReglaNegocioDetalleDaoImpl;
import net.royal.spring.alertas.dao.impl.ReglaNegocioPlanDaoImpl;
import net.royal.spring.alertas.dao.impl.ReglaNegocioProgramacionDaoImpl;
import net.royal.spring.alertas.dominio.ReglaNegocio;
import net.royal.spring.alertas.dominio.ReglaNegocioAdicional;
import net.royal.spring.alertas.dominio.ReglaNegocioDestino;
import net.royal.spring.alertas.dominio.ReglaNegocioDetalle;
import net.royal.spring.alertas.dominio.ReglaNegocioPk;
import net.royal.spring.alertas.dominio.ReglaNegocioPlan;
import net.royal.spring.alertas.dominio.ReglaNegocioProgramacion;
import net.royal.spring.alertas.dominio.dto.ReglaNegocioAdicionalDto;
import net.royal.spring.alertas.dominio.dto.ReglaNegocioDestinoDto;
import net.royal.spring.alertas.dominio.dto.ReglaNegocioDetalleDto;
import net.royal.spring.alertas.dominio.dto.ReglaNegocioPlanDto;
import net.royal.spring.alertas.dominio.dto.ReglaNegocioProgramacionDto;
import net.royal.spring.alertas.servicio.validar.ReglaNegocioServicioValidar;
import net.royal.spring.framework.core.UException;
import net.royal.spring.framework.core.UValidador;
import net.royal.spring.framework.modelo.generico.DominioMensajeUsuario;
import net.royal.spring.framework.modelo.seguridad.SeguridadUsuarioActual;
import net.royal.spring.framework.util.UBigDecimal;
import net.royal.spring.framework.util.UString;
import net.royal.spring.framework.web.servicio.impl.GenericoServicioImpl;

@Service(value = "BeanServicioReglaNegocio")
public class ReglaNegocioServicioImpl extends GenericoServicioImpl {
	public static String SPRING_NOMBRE = "BeanServicioReglaNegocio";

	@Autowired
	private ReglaNegocioDaoImpl reglaNegocioDao;

	@Autowired
	private ReglaNegocioServicioValidar validar;

	@Autowired
	private ReglaNegocioAdicionalDaoImpl consultaAdicional;

	@Autowired
	private ReglaNegocioDetalleDaoImpl consultaDetalle;

	@Autowired
	private ReglaNegocioPlanDaoImpl consultaPlan;

	@Autowired
	private ReglaNegocioDestinoDaoImpl consultaDestino;

	@Autowired
	private ReglaNegocioProgramacionDaoImpl consultaProgramacion;

	@Transactional
	public ReglaNegocio coreInsertar(SeguridadUsuarioActual usuarioActual, ReglaNegocio reglaNegocio) throws UException {
		// valores por defecto - preparando objeto
		reglaNegocio = validar.prepararInsertar(usuarioActual, reglaNegocio);

		// validaciones de negocio
		List<DominioMensajeUsuario> lst = validar.coreInsertar(usuarioActual, reglaNegocio);
		if (!lst.isEmpty())
			throw new UException(lst);

		// transaccion
		return reglaNegocioDao.coreInsertar(reglaNegocio);
	}

	@Transactional
	public ReglaNegocio insertarReglaNegocio(ReglaNegocio bean) throws UException {
		bean.getPk().setIdReglaNegocio(reglaNegocioDao.generarSecuencia());
		bean.setCreacionFecha(new Date());
		bean.setModificacionFecha(new Date());
		bean.setCreacionUsuario("SIA");
		bean.setModificacionUsuario("SIA");

		bean = reglaNegocioDao.coreInsertar(bean);

		if (!UValidador.esListaVacia(bean.getListaReglaNegocioAdicional())) {
			Integer secuencia = consultaAdicional.generarSecuencia(bean.getPk().getIdReglaNegocio());

			for (ReglaNegocioAdicionalDto x : bean.getListaReglaNegocioAdicional()) {
				ReglaNegocioAdicional detalle = new ReglaNegocioAdicional();

				detalle.getPk().setIdReglaNegocio(bean.getPk().getIdReglaNegocio());
				detalle.getPk().setIdAdicional(secuencia);
				//detalle.setLongitudCampo(x.getLongitudCampo().intValue());
				detalle.setOrdenColumna(x.getOrdenColumna().intValue());
				detalle.setNombreCampo(x.getNombreCampo());
				detalle.setDescripcionCampo(x.getDescripcionCampo());
				detalle.setEstado(x.getEstado());

				detalle.setCreacionFecha(new Date());
				detalle.setCreacionUsuario("SIA");
				detalle.setModificacionFecha(new Date());
				detalle.setModificacionUsuario("SIA");
				consultaAdicional.coreInsertar(detalle);
				secuencia++;
			}
		}

		if (!UValidador.esListaVacia(bean.getListaReglaNegocioDetalle())) {
			Integer secuencia = consultaDetalle.generarSecuencia(bean.getPk().getIdReglaNegocio());

			for (ReglaNegocioDetalleDto x : bean.getListaReglaNegocioDetalle()) {
				ReglaNegocioDetalle detalle = new ReglaNegocioDetalle();

				detalle.getPk().setIdReglaNegocio(bean.getPk().getIdReglaNegocio());
				detalle.getPk().setIdDetalle(secuencia);
				detalle.setOrdenColumna(x.getOrdenColumna().intValue());
				detalle.setIdAlineacionColumna(x.getIdAlineacionColumna());
				detalle.setIdTipoDato(x.getIdTipoDato());
				detalle.setNombreCampo(x.getNombreCampo());
				detalle.setDescripcionCampo(x.getDescripcionCampo());
				detalle.setFlgMostrarEnCorreo(x.getFlgMostrarEnCorreo());
				detalle.setLongitudCampo(x.getLongitudCampo().intValue());
				detalle.setEstado(x.getEstado());

				detalle.setCreacionFecha(new Date());
				detalle.setCreacionUsuario("SIA");
				detalle.setModificacionFecha(new Date());
				detalle.setModificacionUsuario("SIA");
				consultaDetalle.coreInsertar(detalle);
				secuencia++;
			}
		}

		if (!UValidador.esListaVacia(bean.getListaReglaNegocioPlan())) {
			Integer secuencia = consultaPlan.generarSecuencia(bean.getPk().getIdReglaNegocio());

			for (ReglaNegocioPlanDto x : bean.getListaReglaNegocioPlan()) {
				ReglaNegocioPlan detalle = new ReglaNegocioPlan();

				detalle.getPk().setIdReglaNegocio(bean.getPk().getIdReglaNegocio());
				detalle.getPk().setIdPlan(secuencia);
				detalle.setOrdenColumna(x.getOrdenColumna().intValue());
				detalle.setPlan(x.getPlan());
				detalle.setEstado(x.getEstado());

				detalle.setCreacionFecha(new Date());
				detalle.setCreacionUsuario("SIA");
				detalle.setModificacionFecha(new Date());
				detalle.setModificacionUsuario("SIA");
				consultaPlan.coreInsertar(detalle);
				secuencia++;
			}
		}

		if (!UValidador.esListaVacia(bean.getListaReglaNegocioDestino())) {

			for (ReglaNegocioDestinoDto x : bean.getListaReglaNegocioDestino()) {
				ReglaNegocioDestino detalle = new ReglaNegocioDestino();

				detalle.getPk().setIdReglaNegocio(bean.getPk().getIdReglaNegocio());
				detalle.getPk().setCorreoDestino(x.getCorreoDestino());
				if (!UString.esNuloVacio(x.getGrupoCorreo())) {
					detalle.setGrupoCorreo(x.getGrupoCorreo());
				}

				detalle.setIdTipoDestino(x.getIdTipoDestino());
				detalle.setEstado(x.getEstado());

				detalle.setCreacionFecha(new Date());
				detalle.setCreacionUsuario("SIA");
				detalle.setModificacionFecha(new Date());
				detalle.setModificacionUsuario("SIA");
				consultaDestino.coreInsertar(detalle);

			}
		}

		if (!UValidador.esListaVacia(bean.getListaReglaNegocioProgramacion())) {
			Integer secuencia = consultaProgramacion.generarSecuencia(bean.getPk().getIdReglaNegocio());
			for (ReglaNegocioProgramacionDto x : bean.getListaReglaNegocioProgramacion()) {
				ReglaNegocioProgramacion detalle = new ReglaNegocioProgramacion();

				detalle.getPk().setIdReglaNegocio(bean.getPk().getIdReglaNegocio());
				detalle.getPk().setIdProgramacion(x.getIdProgramacion().intValue());
				detalle.setEstado(x.getEstado());
				if (!UString.esNuloVacio(x.getIdTipoProgramacion())) {
					detalle.setIdTipoProgramacion(x.getIdTipoProgramacion());
				}

				detalle.setIdFrecuenciaProgramacion(x.getIdFrecuenciaProgramacion());
				///// DIA //////
				if (!UBigDecimal.esCeroOrNulo(x.getDiaCadaNDias())) {
					detalle.setDiaCadaNDias(x.getDiaCadaNDias().intValue());
				}

				if (!UValidador.estaVacio(x.getDiaFrecUnicaHoraejecucion())) {
					detalle.setDiaFrecUnicaHoraejecucion(x.getDiaFrecUnicaHoraejecucion());
				}

				// frecuencia dia
				if (!UString.esNuloVacio(x.getDiaIdFrecuencia())) {
					detalle.setDiaIdFrecuencia(x.getDiaIdFrecuencia());
				}

				if (!UBigDecimal.esCeroOrNulo(x.getDiaFrecRangoCadan())) {
					detalle.setDiaFrecRangoCadan(x.getDiaFrecRangoCadan().intValue());
				}

				if (!UString.esNuloVacio(x.getDiaFrecRangoIdFrecuTiempo())) {
					detalle.setDiaFrecRangoIdFrecuTiempo(x.getDiaFrecRangoIdFrecuTiempo());
				}

				if (!UValidador.estaVacio(x.getDiaFrecRangoHorainicio())) {
					detalle.setDiaFrecRangoHorainicio(x.getDiaFrecRangoHorainicio());
				}

				if (!UValidador.estaVacio(x.getDiaFrecRangoHorafin())) {
					detalle.setDiaFrecRangoHorafin(x.getDiaFrecRangoHorafin());
				}
				// duracion dia
				if (!UValidador.estaVacio(x.getDiaFechaInicio())) {
					detalle.setDiaFechaInicio(x.getDiaFechaInicio());
				}

				if (!UString.esNuloVacio(x.getDiaFlgTieneFin())) {
					detalle.setDiaFlgTieneFin(x.getDiaFlgTieneFin());
				}

				if (!UValidador.estaVacio(x.getDiaFechaFin())) {
					detalle.setDiaFechaFin(x.getDiaFechaFin());
				}

				if (!UString.esNuloVacio(x.getDiaFlgTieneFin())) {
					detalle.setDiaFlgTieneFin(x.getDiaFlgTieneFin());
				}

				//////// SEMANA //////
				if (!UBigDecimal.esCeroOrNulo(x.getSemCadaNSemanas())) {
					detalle.setSemCadaNSemanas(x.getSemCadaNSemanas().intValue());
				}
				/* todos los checkbox comienzan en N */
				detalle.setSemFlgEjecutarLunes(x.getSemFlgEjecutarLunes());
				detalle.setSemFlgEjecutarMartes(x.getSemFlgEjecutarMartes());
				detalle.setSemFlgEjecutarMiercoles(x.getSemFlgEjecutarMiercoles());
				detalle.setSemFlgEjecutarJueves(x.getSemFlgEjecutarJueves());
				detalle.setSemFlgEjecutarViernes(x.getSemFlgEjecutarViernes());
				detalle.setSemFlgEjecutarSabado(x.getSemFlgEjecutarSabado());
				detalle.setSemFlgEjecutarDomingo(x.getSemFlgEjecutarDomingo());
				/**/

				// frecuencia semana
				if (!UString.esNuloVacio(x.getSemIdFrecuencia())) {
					detalle.setSemIdFrecuencia(x.getSemIdFrecuencia());
				}

				if (!UBigDecimal.esCeroOrNulo(x.getSemFrecRangoCadan())) {
					detalle.setSemFrecRangoCadan(x.getSemFrecRangoCadan().intValue());
				}

				if (!UString.esNuloVacio(x.getSemFrecRangoIdFrecuTiempo())) {
					detalle.setSemFrecRangoIdFrecuTiempo(x.getSemFrecRangoIdFrecuTiempo());
				}

				if (!UValidador.estaVacio(x.getSemFrecRangoHorainicio())) {
					detalle.setSemFrecRangoHorainicio(x.getSemFrecRangoHorainicio());
				}

				if (!UValidador.estaVacio(x.getSemFrecRangoHorafin())) {
					detalle.setSemFrecRangoHorafin(x.getSemFrecRangoHorafin());
				}
				// duracion semana
				if (!UValidador.estaVacio(x.getSemFechaInicio())) {
					detalle.setSemFechaInicio(x.getSemFechaInicio());
				}

				if (!UString.esNuloVacio(x.getSemFlgTieneFin())) {
					detalle.setSemFlgTieneFin(x.getSemFlgTieneFin());
				}

				if (!UValidador.estaVacio(x.getSemFechaFin())) {
					detalle.setSemFechaFin(x.getSemFechaFin());
				}

				if (!UString.esNuloVacio(x.getSemFlgTieneFin())) {
					detalle.setSemFlgTieneFin(x.getSemFlgTieneFin());
				}

				///// MES /////
				if (!UString.esNuloVacio(x.getMesIdFrecuenciaMensual())) {
					detalle.setMesIdFrecuenciaMensual(x.getMesIdFrecuenciaMensual());
				}
				if (!UBigDecimal.esCeroOrNulo(x.getMesFrecDiaDia())) {
					detalle.setMesFrecDiaDia(x.getMesFrecDiaDia().intValue());
				}

				if (!UBigDecimal.esCeroOrNulo(x.getMesFrecDiaCadan())) {
					detalle.setMesFrecDiaCadan(x.getMesFrecDiaCadan().intValue());
				}

				if (!UString.esNuloVacio(x.getMesIdFrecuenciaMensual())) {
					detalle.setMesIdFrecuenciaMensual(x.getMesIdFrecuenciaMensual());
				}

				if (!UString.esNuloVacio(x.getMesFrecNomDiaIdDiaSemana())) {
					detalle.setMesFrecNomDiaIdDiaSemana(x.getMesFrecNomDiaIdDiaSemana());
				}

				if (!UBigDecimal.esCeroOrNulo(x.getMesFrecNomDiaCadan())) {
					detalle.setMesFrecNomDiaCadan(x.getMesFrecNomDiaCadan().intValue());
				}

				// frecuencia Mes
				if (!UValidador.estaVacio(x.getMesFrecUnicaHoraejecucion())) {
					detalle.setMesFrecUnicaHoraejecucion(x.getMesFrecUnicaHoraejecucion());
				}
				
				
				if (!UString.esNuloVacio(x.getMesIdFrecuencia())) {
					detalle.setMesIdFrecuencia(x.getMesIdFrecuencia());
				}

				if (!UBigDecimal.esCeroOrNulo(x.getMesFrecRangoCadan())) {
					detalle.setMesFrecRangoCadan(x.getMesFrecRangoCadan().intValue());
				}

				if (!UString.esNuloVacio(x.getMesFrecRangoIdFrecuTiempo())) {
					detalle.setMesFrecRangoIdFrecuTiempo(x.getMesFrecRangoIdFrecuTiempo());
				}

				if (!UValidador.estaVacio(x.getMesFrecRangoHorainicio())) {
					detalle.setMesFrecRangoHorainicio(x.getMesFrecRangoHorainicio());
				}

				if (!UValidador.estaVacio(x.getMesFrecRangoHorafin())) {
					detalle.setMesFrecRangoHorafin(x.getMesFrecRangoHorafin());
				}
				// duracion mes
				if (!UValidador.estaVacio(x.getMesFechaInicio())) {
					detalle.setMesFechaInicio(x.getMesFechaInicio());
				}

				if (!UString.esNuloVacio(x.getMesFlgTieneFin())) {
					detalle.setMesFlgTieneFin(x.getMesFlgTieneFin());
				}

				if (!UValidador.estaVacio(x.getMesFechaFin())) {
					detalle.setMesFechaFin(x.getMesFechaFin());
				}

				if (!UString.esNuloVacio(x.getMesFlgTieneFin())) {
					detalle.setMesFlgTieneFin(x.getMesFlgTieneFin());
				}

				/* extra parte baja */
				if (!UValidador.estaVacio(x.getFechaUltimaEjecucion())) {
					detalle.setFechaUltimaEjecucion(x.getFechaUltimaEjecucion());
				}
				if (!UString.esNuloVacio(x.getFlgEjecutandoActualmente())) {
					detalle.setFlgEjecutandoActualmente(x.getFlgEjecutandoActualmente());
				}
				if (!UValidador.estaVacio(x.getFechaEjecutandoActualmente())) {
					detalle.setFechaEjecutandoActualmente(x.getFechaEjecutandoActualmente());
				}

				detalle.setCreacionFecha(new Date());
				detalle.setCreacionUsuario("SIA");
				detalle.setModificacionFecha(new Date());
				detalle.setModificacionUsuario("SIA");
				consultaProgramacion.coreInsertar(detalle);
				secuencia++;
			}
		}
		
		
		
		return reglaNegocioDao.coreInsertar(bean);
	}

	@Transactional
	public ReglaNegocio actualizarReglaNegocio(ReglaNegocio bean) throws UException {

		bean.setModificacionFecha(new Date());
		bean.setModificacionUsuario("SIA");
		
		if(bean.getFlgalterado().equals("SI")) {
			bean.setFechaUltimaEjecucion(null);
			bean.setFechaUltimoEnvio(null);
			//bean.setEjecucionCantidad(null);
			//bean.setEjecucionMensaje(null);;
		}
		
		bean = reglaNegocioDao.coreActualizar(bean);
	
		if (!UValidador.esListaVacia(bean.getListaReglaNegocioAdicional())) {
			Integer secuencia;

			for (ReglaNegocioAdicionalDto x : bean.getListaReglaNegocioAdicional()) {
				ReglaNegocioAdicional detalle = new ReglaNegocioAdicional();

				detalle.getPk().setIdReglaNegocio(bean.getPk().getIdReglaNegocio());
				if (x.getFlgActualizar().equals("EDITAR")) {
					detalle.getPk().setIdAdicional(x.getIdAdicional().intValue());
				} else if (x.getFlgActualizar().equals("NUEVO")) {
					secuencia = consultaAdicional.generarSecuencia(bean.getPk().getIdReglaNegocio());
					detalle.getPk().setIdAdicional(secuencia);
				}

				//detalle.setLongitudCampo(x.getLongitudCampo().intValue());
				detalle.setOrdenColumna(x.getOrdenColumna().intValue());
				detalle.setNombreCampo(x.getNombreCampo());
				detalle.setDescripcionCampo(x.getDescripcionCampo());
				detalle.setEstado(x.getEstado());

				if (!UValidador.esNulo(x.getCreacionFecha())) {
					detalle.setCreacionFecha(x.getCreacionFecha());
				}
				if (!UString.esNuloVacio(x.getCreacionUsuario())) {
					detalle.setCreacionUsuario(x.getCreacionUsuario());
				}

				detalle.setModificacionFecha(new Date());
				detalle.setModificacionUsuario("SIA");

				if (x.getFlgActualizar().equals("EDITAR")) {
					consultaAdicional.coreActualizar(detalle);
				} else if (x.getFlgActualizar().equals("NUEVO")) {
					detalle.setCreacionFecha(new Date());
					detalle.setCreacionUsuario("SIA");
					consultaAdicional.coreInsertar(detalle);
				}

			}
		}

		if (!UValidador.esListaVacia(bean.getListaReglaNegocioDetalle())) {
			Integer secuencia;

			for (ReglaNegocioDetalleDto x : bean.getListaReglaNegocioDetalle()) {
				ReglaNegocioDetalle detalle = new ReglaNegocioDetalle();

				detalle.getPk().setIdReglaNegocio(bean.getPk().getIdReglaNegocio());
				if (x.getFlgActualizar().equals("EDITAR")) {
					detalle.getPk().setIdDetalle(x.getIdDetalle().intValue());
				} else if (x.getFlgActualizar().equals("NUEVO")) {
					secuencia = consultaDetalle.generarSecuencia(bean.getPk().getIdReglaNegocio());
					detalle.getPk().setIdDetalle(secuencia);
				}

				detalle.setOrdenColumna(x.getOrdenColumna().intValue());
				detalle.setIdAlineacionColumna(x.getIdAlineacionColumna());
				detalle.setIdTipoDato(x.getIdTipoDato());
				detalle.setNombreCampo(x.getNombreCampo());
				detalle.setDescripcionCampo(x.getDescripcionCampo());
				detalle.setFlgMostrarEnCorreo(x.getFlgMostrarEnCorreo());
				if (!UBigDecimal.esCeroOrNulo(x.getLongitudCampo())) {
					detalle.setLongitudCampo(x.getLongitudCampo().intValue());
				}

				detalle.setEstado(x.getEstado());
				if (!UValidador.esNulo(x.getCreacionFecha())) {
					detalle.setCreacionFecha(x.getCreacionFecha());
				}
				if (!UString.esNuloVacio(x.getCreacionUsuario())) {
					detalle.setCreacionUsuario(x.getCreacionUsuario());
				}
				detalle.setModificacionFecha(new Date());
				detalle.setModificacionUsuario("SIA");

				if (x.getFlgActualizar().equals("EDITAR")) {
					consultaDetalle.coreActualizar(detalle);
				} else if (x.getFlgActualizar().equals("NUEVO")) {
					detalle.setCreacionFecha(new Date());
					detalle.setCreacionUsuario("SIA");

					consultaDetalle.coreInsertar(detalle);
				}
			}
		}

		if (!UValidador.esListaVacia(bean.getListaReglaNegocioPlan())) {

			Integer secuencia;
			for (ReglaNegocioPlanDto x : bean.getListaReglaNegocioPlan()) {
				ReglaNegocioPlan detalle = new ReglaNegocioPlan();

				detalle.getPk().setIdReglaNegocio(bean.getPk().getIdReglaNegocio());
				if (x.getFlgActualizar().equals("EDITAR")) {
					detalle.getPk().setIdPlan(x.getIdPlan().intValue());
				} else if (x.getFlgActualizar().equals("NUEVO")) {
					secuencia = consultaPlan.generarSecuencia(bean.getPk().getIdReglaNegocio());
					detalle.getPk().setIdPlan(secuencia);
				}

				detalle.setOrdenColumna(x.getOrdenColumna().intValue());
				detalle.setPlan(x.getPlan());
				detalle.setEstado(x.getEstado());
				if (!UValidador.esNulo(x.getCreacionFecha())) {
					detalle.setCreacionFecha(x.getCreacionFecha());
				}
				if (!UString.esNuloVacio(x.getCreacionUsuario())) {
					detalle.setCreacionUsuario(x.getCreacionUsuario());
				}
				detalle.setModificacionFecha(new Date());
				detalle.setModificacionUsuario("SIA");
				if (x.getFlgActualizar().equals("EDITAR")) {
					consultaPlan.coreActualizar(detalle);

				} else if (x.getFlgActualizar().equals("NUEVO")) {
					detalle.setCreacionFecha(new Date());
					detalle.setCreacionUsuario("SIA");
					consultaPlan.coreInsertar(detalle);
				}

			}
		}

		if (!UValidador.esListaVacia(bean.getListaReglaNegocioDestino())) {

			for (ReglaNegocioDestinoDto x : bean.getListaReglaNegocioDestino()) {
				ReglaNegocioDestino detalle = new ReglaNegocioDestino();

				detalle.getPk().setIdReglaNegocio(bean.getPk().getIdReglaNegocio());
				detalle.getPk().setCorreoDestino(x.getCorreoDestino());
				if (!UString.esNuloVacio(x.getNombrePersona())) {
					detalle.setNombrePersona(x.getNombrePersona());
				}
				detalle.setIdTipoDestino(x.getIdTipoDestino());
				detalle.setEstado(x.getEstado());

				if (!UString.esNuloVacio(x.getGrupoCorreo())) {
					detalle.setGrupoCorreo(x.getGrupoCorreo());
				}

				if (!UValidador.esNulo(x.getCreacionFecha())) {
					detalle.setCreacionFecha(x.getCreacionFecha());
				}
				if (!UString.esNuloVacio(x.getCreacionUsuario())) {
					detalle.setCreacionUsuario(x.getCreacionUsuario());
				}

				detalle.setModificacionFecha(new Date());
				detalle.setModificacionUsuario("SIA");

				if (x.getFlgActualizar().equals("EDITAR")) {
					consultaDestino.coreActualizar(detalle);
				} else if (x.getFlgActualizar().equals("NUEVO")) {
					detalle.setCreacionFecha(new Date());
					detalle.setCreacionUsuario("SIA");
					consultaDestino.coreInsertar(detalle);
				}

			}
		}

		// programacion :3

		if (!UValidador.esListaVacia(bean.getListaReglaNegocioProgramacion())) {

			Integer secuencia;
			for (ReglaNegocioProgramacionDto x : bean.getListaReglaNegocioProgramacion()) {
				ReglaNegocioProgramacion detalle = new ReglaNegocioProgramacion();

				detalle.getPk().setIdReglaNegocio(bean.getPk().getIdReglaNegocio());
				if (x.getFlgActualizar().equals("EDITAR")) {
					detalle.getPk().setIdProgramacion(x.getIdProgramacion().intValue());
				} else if (x.getFlgActualizar().equals("NUEVO")) {
					secuencia = consultaProgramacion.generarSecuencia(bean.getPk().getIdReglaNegocio());
					detalle.getPk().setIdProgramacion(secuencia);
				}

				/**/

				detalle.setIdTipoProgramacion(x.getIdTipoProgramacion());
				detalle.setIdFrecuenciaProgramacion(x.getIdFrecuenciaProgramacion());
				detalle.setEstado(x.getEstado());
 
				///// DIA //////
				if (!UBigDecimal.esCeroOrNulo(x.getDiaCadaNDias())) {
					detalle.setDiaCadaNDias(x.getDiaCadaNDias().intValue());
				}

				// frecuencia dia
				if (!UString.esNuloVacio(x.getDiaIdFrecuencia())) {
					detalle.setDiaIdFrecuencia(x.getDiaIdFrecuencia());
				}

				if (!UValidador.estaVacio(x.getDiaFrecUnicaHoraejecucion())) {
					detalle.setDiaFrecUnicaHoraejecucion(x.getDiaFrecUnicaHoraejecucion());
				}

				if (!UBigDecimal.esCeroOrNulo(x.getDiaFrecRangoCadan())) {
					detalle.setDiaFrecRangoCadan(x.getDiaFrecRangoCadan().intValue());
				}

				if (!UString.esNuloVacio(x.getDiaFrecRangoIdFrecuTiempo())) {
					detalle.setDiaFrecRangoIdFrecuTiempo(x.getDiaFrecRangoIdFrecuTiempo());
				}

				if (!UValidador.estaVacio(x.getDiaFrecRangoHorainicio())) {
					detalle.setDiaFrecRangoHorainicio(x.getDiaFrecRangoHorainicio());
				}

				if (!UValidador.estaVacio(x.getDiaFrecRangoHorafin())) {
					detalle.setDiaFrecRangoHorafin(x.getDiaFrecRangoHorafin());
				}
				// duracion dia
				if (!UValidador.estaVacio(x.getDiaFechaInicio())) {
					detalle.setDiaFechaInicio(x.getDiaFechaInicio());
				}

				if (!UString.esNuloVacio(x.getDiaFlgTieneFin())) {
					detalle.setDiaFlgTieneFin(x.getDiaFlgTieneFin());
				}

				if (!UValidador.estaVacio(x.getDiaFechaFin())) {
					detalle.setDiaFechaFin(x.getDiaFechaFin());
				}

				if (!UString.esNuloVacio(x.getDiaFlgTieneFin())) {
					detalle.setDiaFlgTieneFin(x.getDiaFlgTieneFin());
				}

				//////// SEMANA //////
				if (!UBigDecimal.esCeroOrNulo(x.getSemCadaNSemanas())) {
					detalle.setSemCadaNSemanas(x.getSemCadaNSemanas().intValue());
				}
				/* todos los checkbox comienzan en N */
				detalle.setSemFlgEjecutarLunes(x.getSemFlgEjecutarLunes());
				detalle.setSemFlgEjecutarMartes(x.getSemFlgEjecutarMartes());
				detalle.setSemFlgEjecutarMiercoles(x.getSemFlgEjecutarMiercoles());
				detalle.setSemFlgEjecutarJueves(x.getSemFlgEjecutarJueves());
				detalle.setSemFlgEjecutarViernes(x.getSemFlgEjecutarViernes());
				detalle.setSemFlgEjecutarSabado(x.getSemFlgEjecutarSabado());
				detalle.setSemFlgEjecutarDomingo(x.getSemFlgEjecutarDomingo());
				/**/

				// frecuencia semana
				if (!UString.esNuloVacio(x.getSemIdFrecuencia())) {
					detalle.setSemIdFrecuencia(x.getSemIdFrecuencia());
				}

				if (!UBigDecimal.esCeroOrNulo(x.getSemFrecRangoCadan())) {
					detalle.setSemFrecRangoCadan(x.getSemFrecRangoCadan().intValue());
				}

				if (!UString.esNuloVacio(x.getSemFrecRangoIdFrecuTiempo())) {
					detalle.setSemFrecRangoIdFrecuTiempo(x.getSemFrecRangoIdFrecuTiempo());
				}

				if (!UValidador.estaVacio(x.getSemFrecRangoHorainicio())) {
					detalle.setSemFrecRangoHorainicio(x.getSemFrecRangoHorainicio());
				}

				if (!UValidador.estaVacio(x.getSemFrecRangoHorafin())) {
					detalle.setSemFrecRangoHorafin(x.getSemFrecRangoHorafin());
				}

				if (!UValidador.estaVacio(x.getSemFrecUnicaHoraejecucion())) {
					detalle.setSemFrecUnicaHoraejecucion(x.getSemFrecUnicaHoraejecucion());
				}

				// duracion semana
				if (!UValidador.estaVacio(x.getSemFechaInicio())) {
					detalle.setSemFechaInicio(x.getSemFechaInicio());
				}

				if (!UString.esNuloVacio(x.getSemFlgTieneFin())) {
					detalle.setSemFlgTieneFin(x.getSemFlgTieneFin());
				}

				if (!UValidador.estaVacio(x.getSemFechaFin())) {
					detalle.setSemFechaFin(x.getSemFechaFin());
				}
				/*
				 * repetido if (!UString.esNuloVacio(x.getSemFlgTieneFin())) {
				 * detalle.setSemFlgTieneFin(x.getSemFlgTieneFin()); }
				 */
				///// MES /////
				if (!UString.esNuloVacio(x.getMesIdFrecuenciaMensual())) {
					detalle.setMesIdFrecuenciaMensual(x.getMesIdFrecuenciaMensual());
				}
				if (!UBigDecimal.esCeroOrNulo(x.getMesFrecDiaDia())) {
					detalle.setMesFrecDiaDia(x.getMesFrecDiaDia().intValue());
				}

				if (!UBigDecimal.esCeroOrNulo(x.getMesFrecDiaCadan())) {
					detalle.setMesFrecDiaCadan(x.getMesFrecDiaCadan().intValue());
				}

				if (!UString.esNuloVacio(x.getMesIdFrecuenciaMensual())) {
					detalle.setMesIdFrecuenciaMensual(x.getMesIdFrecuenciaMensual());
				}

				if (!UString.esNuloVacio(x.getMesFrecNomDiaIdDiaSemana())) {
					detalle.setMesFrecNomDiaIdDiaSemana(x.getMesFrecNomDiaIdDiaSemana());
				}

				if (!UBigDecimal.esCeroOrNulo(x.getMesFrecNomDiaCadan())) {
					detalle.setMesFrecNomDiaCadan(x.getMesFrecNomDiaCadan().intValue());
				}

				// frecuencia Mes
				
				if (!UValidador.estaVacio(x.getMesFrecUnicaHoraejecucion())) {
					detalle.setMesFrecUnicaHoraejecucion(x.getMesFrecUnicaHoraejecucion());
				}
				
				if (!UString.esNuloVacio(x.getMesIdFrecuencia())) {
					detalle.setMesIdFrecuencia(x.getMesIdFrecuencia());
				}

				if (!UBigDecimal.esCeroOrNulo(x.getMesFrecRangoCadan())) {
					detalle.setMesFrecRangoCadan(x.getMesFrecRangoCadan().intValue());
				}

				if (!UString.esNuloVacio(x.getMesFrecRangoIdFrecuTiempo())) {
					detalle.setMesFrecRangoIdFrecuTiempo(x.getMesFrecRangoIdFrecuTiempo());
				}

				if (!UValidador.estaVacio(x.getMesFrecRangoHorainicio())) {
					detalle.setMesFrecRangoHorainicio(x.getMesFrecRangoHorainicio());
				}

				if (!UValidador.estaVacio(x.getMesFrecRangoHorafin())) {
					detalle.setMesFrecRangoHorafin(x.getMesFrecRangoHorafin());
				}
				// duracion mes
				if (!UValidador.estaVacio(x.getMesFechaInicio())) {
					detalle.setMesFechaInicio(x.getMesFechaInicio());
				}

				if (!UString.esNuloVacio(x.getMesFlgTieneFin())) {
					detalle.setMesFlgTieneFin(x.getMesFlgTieneFin());
				}

				if (!UValidador.estaVacio(x.getMesFechaFin())) {
					detalle.setMesFechaFin(x.getMesFechaFin());
				}
				/*
				 * repetido if (!UString.esNuloVacio(x.getMesFlgTieneFin())) {
				 * detalle.setMesFlgTieneFin(x.getMesFlgTieneFin()); }
				 */
				/* extra parte baja */
				if (!UValidador.estaVacio(x.getFechaUltimaEjecucion())) {
					detalle.setFechaUltimaEjecucion(x.getFechaUltimaEjecucion());
				}
				if (!UString.esNuloVacio(x.getFlgEjecutandoActualmente())) {
					detalle.setFlgEjecutandoActualmente(x.getFlgEjecutandoActualmente());
				}
				if (!UValidador.estaVacio(x.getFechaEjecutandoActualmente())) {
					detalle.setFechaEjecutandoActualmente(x.getFechaEjecutandoActualmente());
				}

				/**/

				if (!UValidador.esNulo(x.getCreacionFecha())) {
					detalle.setCreacionFecha(x.getCreacionFecha());
				}
				if (!UString.esNuloVacio(x.getCreacionUsuario())) {
					detalle.setCreacionUsuario(x.getCreacionUsuario());
				}
				detalle.setModificacionFecha(new Date());
				detalle.setModificacionUsuario("SIA");
				if (x.getFlgActualizar().equals("EDITAR")) {
					consultaProgramacion.coreActualizar(detalle);

				} else if (x.getFlgActualizar().equals("NUEVO")) {
					detalle.setCreacionFecha(new Date());
					detalle.setCreacionUsuario("SIA");
					consultaProgramacion.coreInsertar(detalle);
				}

			}
		}

		
		
		return reglaNegocioDao.coreActualizar(bean);
	}

	@Transactional
	public ReglaNegocio coreActualizar(SeguridadUsuarioActual usuarioActual, ReglaNegocio reglaNegocio) throws UException {
		// valores por defecto - preparando objeto
		reglaNegocio = validar.prepararActualizar(usuarioActual, reglaNegocio);

		// validaciones de negocio
		List<DominioMensajeUsuario> lst = validar.coreActualizar(usuarioActual, reglaNegocio);
		if (!lst.isEmpty())
			throw new UException(lst);

		// transaccion
		reglaNegocio = reglaNegocioDao.coreActualizar(reglaNegocio);
		return reglaNegocio;
	}

	@Transactional
	public ReglaNegocio coreAnular(SeguridadUsuarioActual usuarioActual, ReglaNegocio reglaNegocio) throws UException {
		// valores por defecto - preparando objeto
		reglaNegocio = validar.prepararAnular(usuarioActual, reglaNegocio);

		// validaciones de negocio
		List<DominioMensajeUsuario> lst = validar.coreAnular(usuarioActual, reglaNegocio);
		if (!lst.isEmpty())
			throw new UException(lst);

		// transaccion
		return reglaNegocioDao.coreActualizar(reglaNegocio);
	}

	public ReglaNegocio coreAnular(SeguridadUsuarioActual usuarioActual, ReglaNegocioPk pk) throws UException {
		ReglaNegocio bean = reglaNegocioDao.obtenerPorId(pk);
		return coreAnular(usuarioActual, bean);
	}

	public ReglaNegocio coreAnular(SeguridadUsuarioActual usuarioActual, Integer pidReglaNegocio) throws UException {
		return coreAnular(usuarioActual, new ReglaNegocioPk(pidReglaNegocio));
	}

	@Transactional
	public void coreEliminar(SeguridadUsuarioActual usuarioActual, ReglaNegocio reglaNegocio) throws UException {
		// valores por defecto - preparando objeto
		reglaNegocio = validar.prepararEliminar(usuarioActual, reglaNegocio);

		// validaciones de negocio
		List<DominioMensajeUsuario> lst = validar.coreEliminar(usuarioActual, reglaNegocio);
		if (!lst.isEmpty())
			throw new UException(lst);

		// transaccion
		reglaNegocioDao.eliminar(reglaNegocio);
	}

	public void coreEliminar(SeguridadUsuarioActual usuarioActual, ReglaNegocioPk pk) throws UException {
		ReglaNegocio reglaNegocio = reglaNegocioDao.obtenerPorId(pk);
		coreEliminar(usuarioActual, reglaNegocio);
	}

	public void coreEliminar(SeguridadUsuarioActual usuarioActual, Integer pidReglaNegocio) throws UException {
		coreEliminar(usuarioActual, new ReglaNegocioPk(pidReglaNegocio));
	}

}
