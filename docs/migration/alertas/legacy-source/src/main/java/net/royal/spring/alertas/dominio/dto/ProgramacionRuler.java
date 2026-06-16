package net.royal.spring.alertas.dominio.dto;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.HashMap;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import net.royal.spring.alertas.dominio.ReglaNegocioProgramacion;
import net.royal.spring.framework.core.UValidador;
import net.royal.spring.framework.util.UInteger;

public class ProgramacionRuler {

	private static Log LOGGER = LogFactory.getLog(ProgramacionRuler.class);

	private String sysprintln;
	// =============== Tipos de programacion ===============

	/**
	 * Programacion que se ejecuta diariamente
	 */
	public static final String TIPO_PROGRAMACION_DIARIA = "D";
	/**
	 * Programacion que se ejecuta semanalmente
	 */
	public static final String TIPO_PROGRAMACION_SEMANAL = "S";
	/**
	 * Programacion que se ejecuta mensualmente
	 */
	public static final String TIPO_PROGRAMACION_MENSUAL = "M";

	// ============ Tipos de frecuencia para programacion mensual ============

	/**
	 * Esta frecuencia define un dia especifico. Por ejemplo: cada <b>20</b> de cada
	 * mes. se cambio el valor original (F) por el valor (DIA)
	 */
	public static final String FRECUENCIA_MENSUAL_DIA_ESPECIFICO = "DIA";

	/**
	 * Esta frecuencia define un dia de semana especifico. Por ejemplo: cada
	 * <b>PRIMER LUNES</b> de cada mes. * se cambio el valor original (O) por el
	 * valor (DSE)
	 */
	public static final String FRECUENCIA_MENSUAL_DIA_SEMANA = "O";

	// ======= Ordinales (solo se usan para el programa mensual) =======

	/**
	 * Ordinal que define que se trata del primer dia (lunes, martes, etc.)
	 */
	public static final String ORDINAL_PRIMERO = "1";
	/**
	 * Ordinal que define que se trata del segundo dia (lunes, martes, etc.)
	 */
	public static final String ORDINAL_SEGUNDO = "2";
	/**
	 * Ordinal que define que se trata del tercer dia (lunes, martes, etc.)
	 */
	public static final String ORDINAL_TERCERO = "3";
	/**
	 * Ordinal que define que se trata del cuarto dia (lunes, martes, etc.)
	 */
	public static final String ORDINAL_CUARTO = "4";
	/**
	 * Ordinal que define que se trata del ultimo dia (lunes, martes, etc.)
	 */
	public static final String ORDINAL_ULTIMO = "5";

	// ============ Dias de la semana (para la programacion mensual ============

	/**
	 * Representacion numerica del dia lunes definida en la tabla ESTADO
	 */
	public static final String DIA_LUNES = "1";
	/**
	 * Representacion numerica del dia martes definida en la tabla ESTADO
	 */
	public static final String DIA_MARTES = "2";
	/**
	 * Representacion numerica del dia miercoles definida en la tabla ESTADO
	 */
	public static final String DIA_MIERCOLES = "3";
	/**
	 * Representacion numerica del dia jueves definida en la tabla ESTADO
	 */
	public static final String DIA_JUEVES = "4";
	/**
	 * Representacion numerica del dia viernes definida en la tabla ESTADO
	 */
	public static final String DIA_VIERNES = "5";
	/**
	 * Representacion numerica del dia sabado definida en la tabla ESTADO
	 */
	public static final String DIA_SABADO = "6";
	/**
	 * Representacion numerica del dia domingo definida en la tabla ESTADO
	 */
	public static final String DIA_DOMINGO = "7";
	/**
	 * Representacion numerica de la fila <b>Dia</b> definida en la tabla ESTADO
	 */
	public static final String DIA_EN_CURSO = "8";
	/**
	 * Representacion numerica de la fila <b>Dia de la Semana</b> definida en la
	 * tabla ESTADO
	 */
	public static final String DIA_SEMANA = "9";

	// ============ Atributos para evaluacion de la programacion ============

	/**
	 * Clase que evalua si la fecha actual se encuentra dentro de las horas y fechas
	 * definidas en la parametrizacion de la programacion
	 */
	private FrecuencyEvaluator frecuenciaRuler;

	/**
	 * Programacion que se obtuvo desde la base de datos
	 */
	private BProgramacion programacion;

	/**
	 * Tipo de programacion (Diaria, Semanal o Mensual)
	 */
	private String tipoProgramacion;

	/**
	 * Flag que indica si se puede realizar o no la ejecucion de la programacion
	 */
	private boolean flgGenerarAlerta;

	/**
	 * Fecha actual que sera evaluada para ver si se generar ejecuta o no la
	 * programacion
	 */
	private Date fechaActual;

	/**
	 * Fecha de ultima ejecucion de la programacion
	 */
	private Date fechaUltimaEjecucion;

	/**
	 * Este map solo se usara para el caso de programacion semanal y almacenara los
	 * dias de la semana seleccionados como parte de la ejecucion
	 */
	@SuppressWarnings("rawtypes")
	private HashMap mapDiasSemanaConfigurados;

	/**
	 * La frecuencia tiempo se refiere a la cantidad de dias, semanas o meses en
	 * laque una programacion se ejecutara. Por ejemplo: si la frecuencia es
	 * <b>2</b> y el tipo de programacion es <b>SEMANAL</b>, entonces se entiende
	 * que la programacion se ejecutara <b>cada 2 semanas</b>.
	 */
	private Integer frecuenciaTiempo;

	/**
	 * Clase para formatear fechas.
	 */
	private SimpleDateFormat dfFormatoFecha = new SimpleDateFormat("dd/MM/yyyy");

	/**
	 * Nombre de los dias de la semana ordenados segun clase Calendar.
	 */
	private String[] diasSemana = new String[] { "Domingo", "Lunes", "Martes", "Miercoles", "Jueves", "Viernes",
			"Sabado" };

	@SuppressWarnings("rawtypes")
	public ProgramacionRuler() {
		this.frecuenciaRuler = new FrecuencyEvaluator();
		this.mapDiasSemanaConfigurados = new HashMap();
	}

	@SuppressWarnings("rawtypes")
	public ProgramacionRuler(BProgramacion programacion, Date fechaUltimaEjecucion) {
		this.frecuenciaRuler = new FrecuencyEvaluator();
		this.mapDiasSemanaConfigurados = new HashMap();
		this.programacion = programacion;
		this.tipoProgramacion = programacion.getTipoProgramacion();
		this.fechaUltimaEjecucion = fechaUltimaEjecucion;

	}

	@SuppressWarnings("rawtypes")
	public ProgramacionRuler(BProgramacion programacion, Date fechaUltimaEjecucion, Date fechaActual) {
		this.frecuenciaRuler = new FrecuencyEvaluator();
		this.mapDiasSemanaConfigurados = new HashMap();
		this.programacion = programacion;
		this.tipoProgramacion = programacion.getTipoProgramacion();
		this.fechaUltimaEjecucion = fechaUltimaEjecucion;
		this.fechaActual = fechaActual;

	}

	/**
	 * Llena los objetos necesarios para el procesamiento del calculo de fechas
	 * validas.
	 * 
	 * @param programacion
	 *            programacion cuya ejecucion sera evaluada, tipo BProgramacion
	 * @param fechaActual
	 *            fecha actual, tipo Date
	 * @param fechaUltimaEjecucion
	 *            fecha de ultima ejecucion de la programacion, tipo Date
	 */
	public void llenarParametros(BProgramacion programacion, Date fechaActual, Date fechaUltimaEjecucion) {
		this.programacion = programacion;
		this.tipoProgramacion = programacion.getTipoProgramacion();
		this.fechaActual = fechaActual;
		this.fechaUltimaEjecucion = fechaUltimaEjecucion;
	}

	/**
	 * Evalua si la programacion tiene una fecha actual valida (segun la
	 * parametrizacion), de tal forma que pueda se ejecutada. Retorna TRUE cuando es
	 * factible realizar la ejecucion y FALSE en caso contrario.
	 * 
	 * @return TRUE o FALSE segun cumpla condiciones, tipo boolean
	 * @throws Exception
	 *             excepcion general en caso de error, tipo Exception
	 */
	public boolean evaluarProgramacion() throws Exception {

		// En caso que la fecha actual sea nula, se tomara por defecto la fecha
		// del sistema.

		if (this.fechaActual == null)
			this.fechaActual = new Date();

		if (!validar())
			return false;

		llenarRecurrenciaxTipo();

		// Antes de comenzar con la evaluacion de los criterios de la
		// programacion, se verifican que las fechas sean consistentes.

		if (esFechaActualValida()) {

			// Se llenan los parametros que necesita frecuenciaRuler para
			// evaluar las fechas.
			llenarFrecuenciaRuler();

			// Se pregunta a frecuenciaRuler si es que la fecha actual es
			// valida para poder generar una alerta.
			// AQUI SE VALIDA EL RANGO DE LA FECHA

			this.flgGenerarAlerta = this.frecuenciaRuler.esFechaValidaDentroDeProgramacion(this.fechaActual);
		} else {
			this.flgGenerarAlerta = false;
		}

		return this.flgGenerarAlerta;
	}

	/**
	 * Llena los parametros que necesita <b>frecuenciaRuler</b> para evaluar fechas
	 * dentro de la programacion
	 * 
	 * @param tipo
	 *            de programacion, tipo String
	 */
	private void llenarFrecuenciaRuler() {

		if (this.tipoProgramacion.equals(TIPO_PROGRAMACION_DIARIA)) {

			BProgramacionDiaria programacionDiaria = this.programacion.getDiaria();

			this.frecuenciaRuler.llenarParametros(this.tipoProgramacion, programacionDiaria, this.fechaUltimaEjecucion);

		} else if (this.tipoProgramacion.equals(TIPO_PROGRAMACION_SEMANAL)) {

			BProgramacionSemanal programacionSemanal = this.programacion.getSemanal();

			this.frecuenciaRuler.llenarParametros(this.tipoProgramacion, programacionSemanal,
					this.fechaUltimaEjecucion);

		} else if (this.tipoProgramacion.equals(TIPO_PROGRAMACION_MENSUAL)) {

			BProgramacionMensual programacionMensual = this.programacion.getMensual();

			this.frecuenciaRuler.llenarParametros(this.tipoProgramacion, programacionMensual,
					this.fechaUltimaEjecucion);

		}
	}

	/**
	 * Valida que los siguientes atributos sean validos:<br/>
	 * <br/>
	 * - La programacion no debe ser nula.<br/>
	 * - La Fecha de ultima ejecucion no debe ser nula.<br/>
	 * - La Fecha actual no debe ser nula.<br/>
	 * - El tipo de programacion no debe ser nulo y debe tener valores validos
	 * (D-Diaria, S-Semanal o M-Mensual).<br/>
	 * <br/>
	 * Si se cumplen estas condiciones, se retorna TRUE. Caso contrario, se retorna
	 * FALSE.
	 * 
	 * @return TRUE o FALSE segun cumpla condiciones, tipo boolean
	 * @throws Exception
	 *             excepcion general en caso de error, tipo Exception
	 */
	private boolean validar() throws Exception {

		if (this.programacion == null)
			throw new Exception("La programacion es nula");

		// La fecha de ultima ejecucion puede ser nula, ya que se trataria de
		// la primera ejecucion de la programacion.

		/*
		 * if(this.fechaUltimaEjecucion == null) throw new
		 * Exception("La fecha de ultima ejecucion es nula");
		 */

		if (this.fechaActual == null)
			throw new Exception("La fecha actual es nula");

		if (UValidador.estaVacio(this.programacion.getTipoProgramacion()))
			throw new Exception("La programacion no tiene un tipo definido");
		else {
			if (!this.programacion.getTipoProgramacion().equals(TIPO_PROGRAMACION_DIARIA)
					&& !this.programacion.getTipoProgramacion().equals(TIPO_PROGRAMACION_SEMANAL)
					&& !this.programacion.getTipoProgramacion().equals(TIPO_PROGRAMACION_MENSUAL))
				throw new Exception(
						"El tipo de programacion no tiene un " + "valor valido (" + this.tipoProgramacion + ")");
		}

		/*
		 * if(Utilitarios.estaVacio(this.frecuenciaTiempo) ||
		 * this.frecuenciaTiempo.intValue() <=0) throw new
		 * Exception("La frecuencia tiempo es nula o tiene un " +
		 * "valor menor o igual a cero");
		 */

		return true;
	}

	/**
	 * Retorna TRUE cuando la fecha de ultima ejecucion es menor al campo fecha
	 * actual.
	 * 
	 * @return TRUE o FALSE segun cumpla condiciones, tipo boolean
	 */
	private boolean esFechaActualValida() throws Exception {

		if (this.tipoProgramacion.equals(TIPO_PROGRAMACION_DIARIA)) {
			//LOGGER.debug("001 : Se evalua fechaActual para PROGRAMACION_DIARIA");
			//sysprintln = "> Se evalua fechaActual para " + "PROGRAMACION_DIARIA";
			// logger.debug(sysprintln);

			BProgramacionDiaria diaria = this.programacion.getDiaria();
			this.frecuenciaTiempo = diaria.getCadaNDias();

			return evaluarProgramaDiaria();

		} else if (this.tipoProgramacion.equals(TIPO_PROGRAMACION_SEMANAL)) {
			LOGGER.debug("001 : Se evalua fechaActual para PROGRAMACION_DIARIA");
			setSysprintln("> Se evalua fechaActual para " + "PROGRAMACION_SEMANAL");
			// logger.debug(sysprintln);

			BProgramacionSemanal semanal = this.programacion.getSemanal();
			this.frecuenciaTiempo = semanal.getCadaNSemanas();

			// Primero, se valida que la fecha actual se encuentre dentro
			// de la semana N. Por ejemplo, si la frecuencia de semanas
			// es 2 y la fecha de inicio es 06/02/2012, entonces la proxima
			// fecha de ejecucion sera el 20/02/2012.

			return evaluarProgramaSemanal();

		} else if (this.tipoProgramacion.equals(TIPO_PROGRAMACION_MENSUAL)) {
			//LOGGER.debug("001 : EVALUAMOS LA REGLA PARA ALERTAS MENSUAL");
			setSysprintln("> Se evalua fechaActual para " + "PROGRAMACION_MENSUAL");
			// logger.debug(sysprintln);

			BProgramacionMensual mensual = this.programacion.getMensual();

			if (mensual.getTipoFrecuenciaEjecucionMensual().equals(FRECUENCIA_MENSUAL_DIA_ESPECIFICO)) {

				// frecuenciaDia_CadaN
				this.frecuenciaTiempo = mensual.getFrecuenciaDia_CadaN();

			} else if (mensual.getTipoFrecuenciaEjecucionMensual().equals(FRECUENCIA_MENSUAL_DIA_SEMANA)) {

				// frecuenciaNombreDia_CadaN
				this.frecuenciaTiempo = mensual.getFrecuenciaNombreDia_CadaN();
			}else {
				LOGGER.debug("001 : getTipoFrecuenciaEjecucionMensual NO COINCIDE CON NI CON :"+FRECUENCIA_MENSUAL_DIA_ESPECIFICO+"O"+FRECUENCIA_MENSUAL_DIA_SEMANA);
			}

			if (this.frecuenciaTiempo == null)
				throw new Exception("La frecuencia del tiempo no tiene valor");

			return evaluarProgramaMensual();
		}

		return true;
	}

	/**
	 * Evalua que la fecha actual se encuentre dentro de los dias permitidos para la
	 * ejecucion de la programacion. Para ello, se considera lo siguiente:<br/>
	 * <br/>
	 * 
	 * - La fecha actual debe estar dentro del dia que coincida con la frecuencia de
	 * tiempo. Es decir <b>NRO_DIAS (FECHA_ACTUAL - FECHA_INICIO) =
	 * FRECUENCIA_TIEMPO</b>.<br/>
	 * <br/>
	 * 
	 * Por ejemplo: <br/>
	 * - Frecuencia: Cada 2 dias<br/>
	 * - Fecha de inicio: 30/01/2012<br/>
	 * - Fecha actual: 01/02/2012<br/>
	 * Entonces, la fecha actual es valida porque <b>01/02/2012 - 30/01/2012 = 2
	 * dias</b>. <br/>
	 * <br/>
	 * 
	 * Si se cumplen estas condiciones, se retorna TRUE. Caso contrario, se retorna
	 * FALSE.
	 * 
	 * @return TRUE o FALSE segun cumpla condiciones, tipo boolean
	 * @throws Exception
	 *             excepcion general en caso de error, tipo Exception
	 */
	private boolean evaluarProgramaDiaria() throws Exception {

		boolean flgDiaValido = false;

		BProgramacionDiaria diaria = this.programacion.getDiaria();

		SimpleDateFormat df = new SimpleDateFormat("dd/MM/yyyy");

		
		if(UInteger.esCeroOrNulo(this.frecuenciaTiempo))
		{
			this.frecuenciaTiempo = 0;
		}
		
		int nCadaNDia = this.frecuenciaTiempo.intValue();

		String fechaInicio = df.format(diaria.getFechaInicio());
		String fechaActual = df.format(this.fechaActual);

		Date fFechaInicio = df.parse(fechaInicio);
		Date fFechaActual = df.parse(fechaActual);

		// El atributo CadaNDia viene por defecto con un valor numerico
		// mayor a cero.

		if (nCadaNDia > 1) {

			// En caso que el valor sea mayor a 1, se calcula si la semana
			// de la fecha actual es valida.

			if (seEncuentraEnDiaIteracion(fFechaActual, fFechaInicio, nCadaNDia)) {

				flgDiaValido = true;
				setSysprintln("La fecha actual es valida para " + "la ejecucion");
				// logger.debug(sysprintln);
			} else {
				setSysprintln(">> Aun no han pasado " + nCadaNDia + " dias. No se puede realizar la ejecucion");
				// logger.error(sysprintln);
			}
		} else {

			// En caso que el valor sea 1, la fecha actual es valida, ya que
			// cualquier dia es valido para la fecha de ejecucion de la
			// programacion.

			setSysprintln("La fecha actual es valida para " + "la ejecucion");
			// logger.debug(sysprintln);
			flgDiaValido = true;
		}

		return flgDiaValido;
	}

	/**
	 * Evalua que la fecha actual se encuentre dentro de la semana permitida para la
	 * ejecucion de la programacion. Para ello, se considera lo siguiente:<br/>
	 * <br/>
	 * 
	 * - La fecha actual debe estar dentro de la semana que coincida con la
	 * frecuencia de tiempo. Es decir <b>NRO_SEMANAS (FECHA_ACTUAL - FECHA_INICIO) =
	 * FRECUENCIA_TIEMPO</b>. - El dia de la fecha actual debe ser parte de la
	 * parametrizacion.<br/>
	 * <br/>
	 * 
	 * Si se cumplen estas condiciones, se retorna TRUE. Caso contrario, se retorna
	 * FALSE.
	 * 
	 * @return TRUE o FALSE segun cumpla condiciones, tipo boolean
	 * @throws Exception
	 *             excepcion general en caso de error, tipo Exception
	 */
	private boolean evaluarProgramaSemanal() throws Exception {

		boolean flgDiaSemanaValido = false;

		BProgramacionSemanal semanal = this.programacion.getSemanal();

		SimpleDateFormat df = new SimpleDateFormat("dd/MM/yyyy");
		
		if(UInteger.esCeroOrNulo(this.frecuenciaTiempo))
		{
			this.frecuenciaTiempo = 0;
		}

		int nCadaNSemana = this.frecuenciaTiempo.intValue();

		String inicioSemana = df.format(semanal.getFechaInicio());
		String fechaActual = df.format(this.fechaActual);

		Date fInicioSemana = df.parse(inicioSemana);
		Date fFechaActual = df.parse(fechaActual);

		// El atributo CadaNSemana viene por defecto con un valor numerico
		// mayor a cero.

		if (nCadaNSemana > 1) {

			// En caso que el valor sea mayor a 1, se calcula si la semana
			// de la fecha actual es valida.

			if (seEncuentraEnSemanaIteracion(fFechaActual, fInicioSemana, nCadaNSemana)) {

				// La fecha actual se encuentra dentro de la semana
				// permitida para la ejecucion de la programacion.
				// Sin embargo, antes se debe validar que el dia actual sea
				// parte de la parametrizacion.

				Calendar gcFechaActual = new GregorianCalendar();
				gcFechaActual.setTime(this.fechaActual);

				if (esDiaValidoProgramaSemanal(gcFechaActual.get(Calendar.DAY_OF_WEEK))) {
					setSysprintln("La fecha se encuentra dentro " + "de los dias parametrizados");
					// logger.debug(sysprintln);
					flgDiaSemanaValido = true;
				} else {
					setSysprintln("La fecha no se encuentra dentro " + "de los dias parametrizados");
					// logger.error(sysprintln);
				}
			} else {
				setSysprintln(">> Aun no han pasado " + nCadaNSemana + " semanas. No se puede realizar la ejecucion");
				// logger.error(sysprintln);
			}
		} else {

			// En caso que el valor sea 1, solo se pregunta si el dia de
			// la fecha actual se encuentra dentro de la parametrizacion.

			Calendar gcFechaActual = new GregorianCalendar();
			gcFechaActual.setTime(this.fechaActual);

			if (esDiaValidoProgramaSemanal(gcFechaActual.get(Calendar.DAY_OF_WEEK))) {
				setSysprintln("La fecha se encuentra dentro " + "de los dias parametrizados");
				// logger.debug(sysprintln);
				flgDiaSemanaValido = true;
			} else {
				setSysprintln("La fecha no se encuentra dentro " + "de los dias parametrizados");
				// logger.error(sysprintln);
			}

		}

		return flgDiaSemanaValido;
	}

	@SuppressWarnings("deprecation")
	private boolean evaluarProgramaMensual() throws Exception {

		boolean flgDiaMesValido = false;

		BProgramacionMensual mensual = this.programacion.getMensual();

		// El atributo CadaNMeses viene por defecto con un valor numerico
		// mayor a cero.
		if(UInteger.esCeroOrNulo(this.frecuenciaTiempo))
		{
			this.frecuenciaTiempo = 0;
		}
		
		int nCadaNMeses = this.frecuenciaTiempo.intValue();

		Date fInicioSemana = obtenerSoloFecha(mensual.getFechaInicio());
		Date fFechaActual = obtenerSoloFecha(this.fechaActual);

		if (seEncuentraEnMesIteracion(fFechaActual, fInicioSemana, nCadaNMeses)) {

			setSysprintln("> La fecha actual se encuentra dentro de un " + "mes valido");
			// logger.debug(sysprintln);

			if (mensual.getTipoFrecuenciaEjecucionMensual().equals(FRECUENCIA_MENSUAL_DIA_ESPECIFICO)) {

				// logger.debug("> La frecuencia mensual es DIA_N_ESPECIFICO");

				int diaEspecifico = mensual.getFrecuenciaDia_Dia().intValue();
				int diaActual = fFechaActual.getDate();

				if (diaEspecifico == diaActual) {
					setSysprintln(">> El dia actual " + diaActual + " es igual al dia especifico programado "
							+ diaEspecifico);
					// logger.debug(sysprintln);

					flgDiaMesValido = true;

				} else {
					setSysprintln(">> El dia actual " + diaActual + " no es igual al dia especifico programado "
							+ diaEspecifico);
					// logger.error(sysprintln);

					flgDiaMesValido = false;
				}

			} else if (mensual.getTipoFrecuenciaEjecucionMensual().equals(FRECUENCIA_MENSUAL_DIA_SEMANA)) {

				// logger.debug("> La frecuencia mensual es DIA_ORDINAL_MES");

				// frecuenciaNombreDia_OrdenDiaSemana
				String ordinal = mensual.getFrecuenciaNombreDia_OrdenDiaSemana();

				// frecuenciaNombreDia_DiaSemana
				String diaSemana = mensual.getFrecuenciaNombreDia_DiaSemana();

				Calendar gcFechaActual = new GregorianCalendar();
				gcFechaActual.setTimeInMillis(this.fechaActual.getTime());

				int diaFechaActual = gcFechaActual.get(Calendar.DATE);

				if (diaSemana.equals(DIA_EN_CURSO)) {

					if (ordinal.equals(ORDINAL_PRIMERO)) {

						int primerDiaMes = gcFechaActual.getActualMinimum(Calendar.DATE);

						if (diaFechaActual == primerDiaMes)
							flgDiaMesValido = true;

					} else if (ordinal.equals(ORDINAL_SEGUNDO)) {

						int segundoDiaMes = gcFechaActual.getActualMinimum(Calendar.DATE) + 1;

						if (diaFechaActual == segundoDiaMes)
							flgDiaMesValido = true;

					} else if (ordinal.equals(ORDINAL_TERCERO)) {

						int tercerDiaMes = gcFechaActual.getActualMinimum(Calendar.DATE) + 2;

						if (diaFechaActual == tercerDiaMes)
							flgDiaMesValido = true;

					} else if (ordinal.equals(ORDINAL_CUARTO)) {

						int cuartoDiaMes = gcFechaActual.getActualMinimum(Calendar.DATE) + 3;

						if (diaFechaActual == cuartoDiaMes)
							flgDiaMesValido = true;

					} else if (ordinal.equals(ORDINAL_ULTIMO)) {

						int ultimoDiaMes = gcFechaActual.getActualMaximum(Calendar.DATE);

						if (diaFechaActual == ultimoDiaMes)
							flgDiaMesValido = true;
					}

					return flgDiaMesValido;

				} // ============== DIA DE SEMANA ==============
				else if (diaSemana.equals(DIA_SEMANA)) {

					int nroDiaSemana = gcFechaActual.get(Calendar.DAY_OF_WEEK);

					if (ordinal.equals(ORDINAL_PRIMERO)) {

						// El primer dia de cada semana segun el objeto
						// Calendar es el DOMINGO.
						int primerDiaSemana = gcFechaActual.getActualMinimum(Calendar.DAY_OF_WEEK);

						// Sin embargo, para el negocio el lunes es
						// considerado como primer dia de la semana.
						primerDiaSemana++;

						if (nroDiaSemana == primerDiaSemana)
							flgDiaMesValido = true;

					} else if (ordinal.equals(ORDINAL_SEGUNDO)) {

						int segundoDiaSemana = gcFechaActual.getActualMinimum(Calendar.DAY_OF_WEEK) + 2;

						if (nroDiaSemana == segundoDiaSemana)
							flgDiaMesValido = true;

					} else if (ordinal.equals(ORDINAL_TERCERO)) {

						int tercerDiaSemana = gcFechaActual.getActualMinimum(Calendar.DAY_OF_WEEK) + 3;

						if (nroDiaSemana == tercerDiaSemana)
							flgDiaMesValido = true;

					} else if (ordinal.equals(ORDINAL_CUARTO)) {

						int cuartoDiaSemana = gcFechaActual.getActualMinimum(Calendar.DAY_OF_WEEK) + 4;

						if (nroDiaSemana == cuartoDiaSemana)
							flgDiaMesValido = true;

					} else if (ordinal.equals(ORDINAL_ULTIMO)) {

						/*
						 * int ultimoDiaSemana = gcFechaActual. getActualMaximum(Calendar.DAY_OF_WEEK);
						 */

						// El dia dia de la semana segun el objeto Calendar es
						// el sabado. Sin embargo, para el negocio es dia
						// domingo es considerado el ultimo dia de la semana.
						int ultimoDiaSemana = Calendar.SUNDAY;

						if (nroDiaSemana == ultimoDiaSemana)
							flgDiaMesValido = true;
					}

					return flgDiaMesValido;

				}

				// ============== CONDICION COMIENZA CON ORDINALES
				// ==============

				// PRIMERO
				if (ordinal.equals(ORDINAL_PRIMERO)) {

					if (esPrimerDia(fFechaActual, diaSemana))
						flgDiaMesValido = true;
				} // SEGUNDO
				else if (ordinal.equals(ORDINAL_SEGUNDO)) {

					if (esSegundoDia(fFechaActual, diaSemana))
						flgDiaMesValido = true;
				} // TERCERO
				else if (ordinal.equals(ORDINAL_TERCERO)) {

					if (esTercerDia(fFechaActual, diaSemana))
						flgDiaMesValido = true;
				} // CUARTO
				else if (ordinal.equals(ORDINAL_CUARTO)) {

					if (esCuartoDia(fFechaActual, diaSemana))
						flgDiaMesValido = true;
				} // ULTIMO
				else if (ordinal.equals(ORDINAL_ULTIMO)) {

					if (esUltimoDia(fFechaActual, diaSemana))
						flgDiaMesValido = true;
				} //

			} else
				return false;

		} else {
			setSysprintln(">> Aun no han pasado " + nCadaNMeses + " semanas. No se puede realizar la ejecucion");
			// logger.error(sysprintln);
		}

		return flgDiaMesValido;
	}

	/**
	 * Para entender la recurrencia se explica lo siguiente:<br/>
	 * 
	 * Por recurrencia se entiende cada cuantos dias, semanas o meses se realizara
	 * la ejecucion de una programacion. <br/>
	 * <br/>
	 * 
	 * - Para el caso de programacion <b>DIARIA</b>, la recurrencia sera asi:<br/>
	 * <i>Cada X dias</i>, donde <i>X</i> es un numero de dias. Por ejemplo, cada 1
	 * dia, cada 2 dias, etc.<br/>
	 * <br/>
	 * 
	 * - Para el caso de programacion <b>SEMANAL</b>, la recurrencia sera asi:<br/>
	 * <i>Cada X semanas</i>, donde <i>X</i> es un numero de semanas. Por ejemplo,
	 * cada 1 semana, cada 2 semanas, etc. A eso, se suman los dias de la semana en
	 * los cuales se realizara la ejecucion. Por ejemplo: de los 7 dias de la
	 * semana, solo se ejecutara en los dias <i>miercoles</i> y <i>viernes</i>. En
	 * concreto, la recurrencia de la programacion semanal es <b>cada x semanas y en
	 * ciertos dias</b>. <br/>
	 * <br/>
	 * 
	 * - Para el caso de programacion <b>MENSUAL</b>, la recurrencia sera de dos
	 * formas:<br/>
	 * <br/>
	 * >> La primera es <i>El dia X cada Z meses</i>, donde <i>X</i> es el numero
	 * del dia del mes y Z es el numero de meses . Por ejemplo, el dia 15 de cada 2
	 * meses, el dia 23 de cada 1 mes, etc.<br/>
	 * >> La segunda es <i>El <b>numero_ordinal dia_semana</b> cada X meses</b></i>,
	 * donde <i>numero_ordinal</i> puede ser PRIMER, SEGUNDO, TERCER, CUARTO o
	 * ULTIMO, <i>dia_semana</i> puede ser LUNES, MARTES, MIERCOLES, JUEVES,
	 * VIERNES, SABADO o DOMINGO, y X es el numero de meses. Por ejemplo: El PRIMER
	 * LUNES cada 1 mes, El TERCER SABADO cada 3 meses, etc.
	 * 
	 */
	@SuppressWarnings("unchecked")
	private void llenarRecurrenciaxTipo() {

		if (this.tipoProgramacion.equals(ProgramacionRuler.TIPO_PROGRAMACION_DIARIA)) {

		} else if (this.tipoProgramacion.equals(ProgramacionRuler.TIPO_PROGRAMACION_SEMANAL)) {

			BProgramacionSemanal semanal = (BProgramacionSemanal) this.programacion.getSemanal();

			this.frecuenciaTiempo = semanal.getCadaNSemanas();

			// Se llenan los dias seleccionados en el hashmap

			Calendar gcCalendario = new GregorianCalendar();

			gcCalendario.set(Calendar.DAY_OF_WEEK, Calendar.MONDAY);

			if (semanal.isFlagEjecutarLunes())
				this.mapDiasSemanaConfigurados.put(gcCalendario.get(Calendar.DAY_OF_WEEK) + "",
						gcCalendario.get(Calendar.DAY_OF_WEEK) + "");

			gcCalendario.set(Calendar.DAY_OF_WEEK, Calendar.TUESDAY);

			if (semanal.isFlagEjecutarMartes())
				this.mapDiasSemanaConfigurados.put(gcCalendario.get(Calendar.DAY_OF_WEEK) + "",
						gcCalendario.get(Calendar.DAY_OF_WEEK) + "");

			gcCalendario.set(Calendar.DAY_OF_WEEK, Calendar.WEDNESDAY);

			if (semanal.isFlagEjecutarMiercoles())
				this.mapDiasSemanaConfigurados.put(gcCalendario.get(Calendar.DAY_OF_WEEK) + "",
						gcCalendario.get(Calendar.DAY_OF_WEEK) + "");

			gcCalendario.set(Calendar.DAY_OF_WEEK, Calendar.THURSDAY);

			if (semanal.isFlagEjecutarJueves())
				this.mapDiasSemanaConfigurados.put(gcCalendario.get(Calendar.DAY_OF_WEEK) + "",
						gcCalendario.get(Calendar.DAY_OF_WEEK) + "");

			gcCalendario.set(Calendar.DAY_OF_WEEK, Calendar.FRIDAY);

			if (semanal.isFlagEjecutarViernes())
				this.mapDiasSemanaConfigurados.put(gcCalendario.get(Calendar.DAY_OF_WEEK) + "",
						gcCalendario.get(Calendar.DAY_OF_WEEK) + "");

			gcCalendario.set(Calendar.DAY_OF_WEEK, Calendar.SATURDAY);

			if (semanal.isFlagEjecutarSabado())
				this.mapDiasSemanaConfigurados.put(gcCalendario.get(Calendar.DAY_OF_WEEK) + "",
						gcCalendario.get(Calendar.DAY_OF_WEEK) + "");

			gcCalendario.set(Calendar.DAY_OF_WEEK, Calendar.SUNDAY);

			if (semanal.isFlagEjecutarDomingo())
				this.mapDiasSemanaConfigurados.put(gcCalendario.get(Calendar.DAY_OF_WEEK) + "",
						gcCalendario.get(Calendar.DAY_OF_WEEK) + "");

		} else if (this.tipoProgramacion.equals(ProgramacionRuler.TIPO_PROGRAMACION_MENSUAL)) {

		}
	}

	/**
	 * Busca que el dia enviado como parametro forme parte de los dias configurados
	 * para la ejecucion semanal de la programacion. Si existe, retorna TRUE. Caso
	 * contrario, retorna FALSE.
	 * 
	 * @param diaCalendario
	 *            dia calendario segun las constantes del objeto Calendar. Por
	 *            ejemplo: Calendar.SUNDAY, Calendar.SATURDAY, etc.
	 * @return TRUE o FALSE segun cumpla condiciones, tipo boolean
	 */
	private boolean esDiaValidoProgramaSemanal(int diaCalendario) {

		if (this.mapDiasSemanaConfigurados.get(diaCalendario + "") != null)
			return true;

		return false;

	}

	/**
	 * Retorna TRUE cuando la fecha actual se encuentra dentro del dia de iteracion
	 * programado. Por ejemplo, si se itera cada 2 dias y la fecha de inicio es
	 * 30/01/2012, y la fecha actual es 01/02/2012, entonces se trata de una fecha
	 * valida, ya que se encuentra 2 dias despues de la fecha de inicio. En caso de
	 * no cumplirse con este criterio, se retorna FALSE.
	 * 
	 * @param fechaActual
	 *            fecha actual para evaluacion de ejecucion de programacion, tipo
	 *            Date
	 * @param fechaInicio
	 *            fecha de inicio de ejecucion de la programacion, tipo Date
	 * @param dias
	 *            numero de dias para la iteracion, tipo Integer
	 * @return TRUE o FALSE segun cumpla condiciones, tipo boolean
	 * @throws Exception
	 *             excepcion general en caso de error, tipo Exception
	 */
	private boolean seEncuentraEnDiaIteracion(Date fechaActual, Date fechaInicio, int dias) throws Exception {

		boolean flgDiaValido = false;

		// Se captura el tiempo transcurrido en milisegundos tomando la fecha
		// de inicio como punto de partida y la fecha actual como punto final.
		long tiempoTranscurridoMiliSeg = fechaActual.getTime() - fechaInicio.getTime();

		// Se convierten los milisegundos a dias.
		long diasTranscurridos = tiempoTranscurridoMiliSeg / 1000 / 60 / 60 / 24;

		// Si la division es exacta, quiere decir que la fecha actual esta
		// ubicada en un dia valido.
		if (diasTranscurridos % dias == 0)
			flgDiaValido = true;
		else
			flgDiaValido = false;

		return flgDiaValido;

	}

	/**
	 * Retorna TRUE cuando la fecha actual se encuentra dentro de la semana de
	 * iteracion. Por ejemplo, si se itera cada 2 semanas y la fecha de inicio es
	 * 30/01/2012, y la fecha actual es 13/02/2012, entonces se trata de una fecha
	 * valida, ya que se encuentra 2 semanas despues de la fecha de inicio. En caso
	 * de no cumplirse con este criterio, se retorna FALSE.
	 * 
	 * @param fechaActual
	 *            fecha actual para evaluacion de ejecucion de programacion, tipo
	 *            Date
	 * @param fechaInicio
	 *            fecha de inicio de ejecucion de la programacion, tipo Date
	 * @param semanas
	 *            numero de semanas para la iteracion, tipo Integer
	 * @return TRUE o FALSE segun cumpla condiciones, tipo boolean
	 * @throws Exception
	 *             excepcion general en caso de error, tipo Exception
	 */
	private boolean seEncuentraEnSemanaIteracion(Date fechaActual, Date fechaInicio, int semanas) throws Exception {

		boolean flgSemanaValida = false;

		// Se captura el tiempo transcurrido en milisegundos tomando la fecha
		// de inicio como punto de partida y la fecha actual como punto final.
		long resta = fechaActual.getTime() - fechaInicio.getTime();

		// Se convierten los milisegundos a semanas.
		long semanasTranscurridas = resta / 1000 / 60 / 60 / 24 / 7;

		// Si la division es exacta, quiere decir que la fecha actual esta
		// ubicada en una semana valida.
		if (semanasTranscurridas % semanas == 0)
			flgSemanaValida = true;
		else
			flgSemanaValida = false;

		return flgSemanaValida;
	}

	/**
	 * Retorna TRUE cuando la fecha actual se encuentra dentro del mes de iteracion.
	 * Por ejemplo, si se itera cada 2 meses y la fecha de inicio es 01/02/2012, y
	 * la fecha actual es 03/03/2012, entonces se trata de una fecha valida, ya que
	 * se encuentra 2 meses despues de la fecha de inicio. En caso de no cumplirse
	 * con este criterio, se retorna FALSE.
	 * 
	 * @param fechaActual
	 *            fecha actual para evaluacion de ejecucion de programacion, tipo
	 *            Date
	 * @param fechaInicio
	 *            fecha de inicio de ejecucion de la programacion, tipo Date
	 * @param meses
	 *            numero de meses para la iteracion, tipo Integer
	 * @return TRUE o FALSE segun cumpla condiciones, tipo boolean
	 * @throws Exception
	 *             excepcion general en caso de error, tipo Exception
	 */
	private boolean seEncuentraEnMesIteracion(Date fechaActual, Date fechaInicio, int meses) throws Exception {

		boolean flgMesValido = false;

		// Si la iteracion es cada mes, se retorna true.
		if (meses == 1)
			return true;

		// Se captura el tiempo transcurrido en milisegundos tomando la fecha
		// de inicio como punto de partida y la fecha actual como punto final.
		long resta = fechaActual.getTime() - fechaInicio.getTime();

		// Se convierten los milisegundos a meses.
		long mesesTranscurridos = resta / 1000 / 60 / 60 / 24 / 30;

		// Si la division es exacta, quiere decir que la fecha actual esta
		// ubicada en un mes valido.
		if (mesesTranscurridos % meses == 0)
			flgMesValido = true;
		else
			flgMesValido = false;

		return flgMesValido;
	}

	/**
	 * Evalua si la fecha actual es el primer DIA_N del mes. Por ejemplo, si DIA_N
	 * es LUNES, se verifica que la fecha actual (enviada como parametro) sea el
	 * PRIMER LUNES del mes. De cumplirse esta condicion, se retorna TRUE. Caso
	 * contrario, se retorna FALSE.
	 * 
	 * @param fechaActual
	 *            fecha actual que sera evaluada, tipo Date
	 * @param diaProgramacion
	 *            dia de la programacion que viene desde la base de datos (puede ser
	 *            1-Lunes, 2-Martes, 3-Miercoles, 4-Jueves, 5-Viernes, 6-Sabado y
	 *            7-Domingo), tipo String
	 * @return TRUE o FALSE segun cumpla condiciones, tipo boolean
	 * @throws Exception
	 *             excepcion general en caso de error, tipo Exception
	 */
	private boolean esPrimerDia(Date fechaActual, String diaProgramacion) throws Exception {

		int diaCalendar = 0;

		if (diaProgramacion.equals(DIA_LUNES))
			diaCalendar = Calendar.MONDAY;
		else if (diaProgramacion.equals(DIA_MARTES))
			diaCalendar = Calendar.TUESDAY;
		else if (diaProgramacion.equals(DIA_MIERCOLES))
			diaCalendar = Calendar.WEDNESDAY;
		else if (diaProgramacion.equals(DIA_JUEVES))
			diaCalendar = Calendar.THURSDAY;
		else if (diaProgramacion.equals(DIA_VIERNES))
			diaCalendar = Calendar.FRIDAY;
		else if (diaProgramacion.equals(DIA_SABADO))
			diaCalendar = Calendar.SATURDAY;
		else if (diaProgramacion.equals(DIA_DOMINGO))
			diaCalendar = Calendar.SUNDAY;
		else
			throw new Exception("El dia de programacion " + diaProgramacion + " es invalido");

		boolean flgEsPrimerDiaMes = false;

		Calendar gcFechaPrimerDia = new GregorianCalendar();
		gcFechaPrimerDia.setTimeInMillis(fechaActual.getTime());

		// Sea cual sea el valor de la fecha actual, primero se obtiene el
		// primer dia del mes
		int primerDiaMes = gcFechaPrimerDia.getActualMinimum(Calendar.DATE);

		// Luego, se cambia la fecha actual por la fecha del primer dia.
		gcFechaPrimerDia.set(Calendar.DATE, primerDiaMes);

		// Despues, se pregunta si el primer dia es igual al dia enviado como
		// parametro. De ser asi, se considera la fecha como el primer DIA_N
		// del mes (donde N puede ser cualquiera de los 7 dias). Caso
		// contrario, se avanza una semana para poder coger el primer lunes.

		if (gcFechaPrimerDia.get(Calendar.DAY_OF_WEEK) == diaCalendar) {
			setSysprintln("> " + gcFechaPrimerDia.getTime() + " es el primer " + this.diasSemana[diaCalendar - 1]
					+ " del mes.");
			// logger.debug(sysprintln);

			if (gcFechaPrimerDia.getTime().equals(fechaActual))
				flgEsPrimerDiaMes = true;
		} else {
			// Se avanza una semana.
			gcFechaPrimerDia.add(Calendar.WEEK_OF_MONTH, 1);

			// Se setea el DIA_N de esta nueva semana.
			gcFechaPrimerDia.set(Calendar.DAY_OF_WEEK, diaCalendar);

			setSysprintln("> " + gcFechaPrimerDia.getTime() + " es el primer " + this.diasSemana[diaCalendar - 1]
					+ " del mes.");
			// logger.debug(sysprintln);

			if (gcFechaPrimerDia.getTime().equals(fechaActual)) {
				setSysprintln(">> La fecha actual " + dfFormatoFecha.format(fechaActual) + " es igual al primer "
						+ this.diasSemana[diaCalendar - 1] + " del mes.");
				// logger.debug(sysprintln);

				flgEsPrimerDiaMes = true;
			}

		}

		return flgEsPrimerDiaMes;
	}

	/**
	 * Evalua si la fecha actual es el segundo DIA_N del mes. Por ejemplo, si DIA_N
	 * es LUNES, se verifica que la fecha actual (enviada como parametro) sea el
	 * SEGUNDO LUNES del mes. De cumplirse esta condicion, se retorna TRUE. Caso
	 * contrario, se retorna FALSE.
	 * 
	 * @param fechaActual
	 *            fecha actual que sera evaluada, tipo Date
	 * @param diaProgramacion
	 *            dia de la programacion que viene desde la base de datos (puede ser
	 *            1-Lunes, 2-Martes, 3-Miercoles, 4-Jueves, 5-Viernes, 6-Sabado y
	 *            7-Domingo), tipo String
	 * @return TRUE o FALSE segun cumpla condiciones, tipo boolean
	 * @throws Exception
	 *             excepcion general en caso de error, tipo Exception
	 */
	public boolean esSegundoDia(Date fechaActual, String diaProgramacion) throws Exception {

		// =========== CALCULO DEL SEGUNDO DIA N DEL MES =================

		int diaCalendar = 0;

		if (diaProgramacion.equals(DIA_LUNES))
			diaCalendar = Calendar.MONDAY;
		else if (diaProgramacion.equals(DIA_MARTES))
			diaCalendar = Calendar.TUESDAY;
		else if (diaProgramacion.equals(DIA_MIERCOLES))
			diaCalendar = Calendar.WEDNESDAY;
		else if (diaProgramacion.equals(DIA_JUEVES))
			diaCalendar = Calendar.THURSDAY;
		else if (diaProgramacion.equals(DIA_VIERNES))
			diaCalendar = Calendar.FRIDAY;
		else if (diaProgramacion.equals(DIA_SABADO))
			diaCalendar = Calendar.SATURDAY;
		else if (diaProgramacion.equals(DIA_DOMINGO))
			diaCalendar = Calendar.SUNDAY;
		else
			throw new Exception("El dia de programacion " + diaProgramacion + "es invalido");

		boolean flgEsSegundoDiaMes = false;

		SimpleDateFormat df = new SimpleDateFormat("dd/MM/yyyy");

		Calendar gcFecha = new GregorianCalendar();
		gcFecha.setTime(fechaActual);

		// Se captura el mes de la fecha actual.
		int mesActual = gcFecha.get(Calendar.MONTH);

		// Se obtiene el primer dia del mes de la fecha actual.
		int primerDia = gcFecha.getActualMinimum(Calendar.DAY_OF_MONTH);

		// La fecha es modificada y se setea el ultimo dia del mes.
		gcFecha.set(Calendar.DAY_OF_MONTH, primerDia);

		// Se obtiene el dia de la ultima fecha del mes. Por ejemplo: si el
		// ultimo dia del mes es 29/02/2012, entonces se devolvera 3
		// (miercoles).
		int diaPrimeraSemana = gcFecha.get(Calendar.DAY_OF_WEEK);

		gcFecha.get(Calendar.DATE);

		// Como la fecha se encuentra en su ultima semana, se setea el dia
		// que se buscando. Por ejemplo: si el dia buscado es MIERCOLES,
		// entonces se posiciona la fecha en este dia de su ultima semana.
		// Se debe tener en cuenta que esta accion podria hacer que la
		// fecha pase al mes siguiente (esto es controlado lineas abajo).
		gcFecha.set(Calendar.DAY_OF_WEEK, diaCalendar);

		// Se evalua si el primer dia del mes es Lunes por ser el dia de inicio
		// de la semana.
		if (diaPrimeraSemana == Calendar.MONDAY) {

			// Si el primer dia del mes es lunes y es diferente al dia
			// buscado, entonces se retrocede una semana, ya que la fecha
			// actual se encuentra en un mes posterior.

			// Por ejemplo: el caso del mes de abril
			if (diaPrimeraSemana != diaCalendar) {

				gcFecha.add(Calendar.WEEK_OF_MONTH, 1);
				setSysprintln(">> Segundo " + diasSemana[diaCalendar - 1] + " mes = " + df.format(gcFecha.getTime()));
				// logger.debug(sysprintln);

			} else {

				gcFecha.add(Calendar.WEEK_OF_MONTH, 1);

				// Si el dia del mes es Lunes y coincide con el dia actual,
				// entonces se trata del ultimo dia N del mes (donde N es el dia
				// buscado).
				setSysprintln(">> Segundo " + diasSemana[diaCalendar - 1] + " mes = " + df.format(gcFecha.getTime()));
				// logger.debug(sysprintln);

			}

		} else {

			// Como la fecha inicial ha cambiado, se guarda el mes de la ultima
			// modificacion de la fecha.
			int mesCambiado = gcFecha.get(Calendar.MONTH);

			// Si la resta del mes modificado menos el mes original es 1,
			// quiere decir que la fecha actual se encuentra en un mes
			// posterior.
			if (mesActual - mesCambiado == 1 || mesCambiado - mesActual == 11) {

				// La fecha actual ha pasado al mes siguiente, se resta menos
				// uno para llegar al ultimo dia N del mes actual.

				gcFecha.add(Calendar.WEEK_OF_MONTH, 2);

				setSysprintln(">> Segundo " + diasSemana[diaCalendar - 1] + " mes = " + df.format(gcFecha.getTime()));
				// logger.debug(sysprintln);
			} else {

				gcFecha.add(Calendar.WEEK_OF_MONTH, 1);
				// Si la resta es menor a 1, entonces el mes modificado es el
				// mismo que el mes original.

				setSysprintln(">> Segundo " + diasSemana[diaCalendar - 1] + " mes = " + df.format(gcFecha.getTime()));
				// logger.debug(sysprintln);
			}
		}

		// ============= COMPARACION DE FECHAS ================
		Date segundoDiaNMes = gcFecha.getTime();
		segundoDiaNMes = obtenerSoloFecha(segundoDiaNMes);

		if (fechaActual.equals(segundoDiaNMes)) {
			setSysprintln("> La fecha actual " + this.dfFormatoFecha.format(fechaActual)
					+ " es valida porque es el segundo " + this.diasSemana[diaCalendar - 1] + " del mes.");
			// logger.debug(sysprintln);

			flgEsSegundoDiaMes = true;
		} else {
			setSysprintln("> La fecha actual " + this.dfFormatoFecha.format(fechaActual)
					+ " no es valida porque no es el segundo " + this.diasSemana[diaCalendar - 1] + " del mes.");
			// logger.debug(sysprintln);

			flgEsSegundoDiaMes = false;
		}

		return flgEsSegundoDiaMes;
	}

	/**
	 * Evalua si la fecha actual es el tercer DIA_N del mes. Por ejemplo, si DIA_N
	 * es LUNES, se verifica que la fecha actual (enviada como parametro) sea el
	 * TERCER LUNES del mes. De cumplirse esta condicion, se retorna TRUE. Caso
	 * contrario, se retorna FALSE.
	 * 
	 * @param fechaActual
	 *            fecha actual que sera evaluada, tipo Date
	 * @param diaProgramacion
	 *            dia de la programacion que viene desde la base de datos (puede ser
	 *            1-Lunes, 2-Martes, 3-Miercoles, 4-Jueves, 5-Viernes, 6-Sabado y
	 *            7-Domingo), tipo String
	 * @return TRUE o FALSE segun cumpla condiciones, tipo boolean
	 * @throws Exception
	 *             excepcion general en caso de error, tipo Exception
	 */
	public boolean esTercerDia(Date fechaActual, String diaProgramacion) throws Exception {

		// =========== CALCULO DEL SEGUNDO DIA N DEL MES =================

		int diaCalendar = 0;

		if (diaProgramacion.equals(DIA_LUNES))
			diaCalendar = Calendar.MONDAY;
		else if (diaProgramacion.equals(DIA_MARTES))
			diaCalendar = Calendar.TUESDAY;
		else if (diaProgramacion.equals(DIA_MIERCOLES))
			diaCalendar = Calendar.WEDNESDAY;
		else if (diaProgramacion.equals(DIA_JUEVES))
			diaCalendar = Calendar.THURSDAY;
		else if (diaProgramacion.equals(DIA_VIERNES))
			diaCalendar = Calendar.FRIDAY;
		else if (diaProgramacion.equals(DIA_SABADO))
			diaCalendar = Calendar.SATURDAY;
		else if (diaProgramacion.equals(DIA_DOMINGO))
			diaCalendar = Calendar.SUNDAY;
		else
			throw new Exception("El dia de programacion " + diaProgramacion + "es invalido");

		boolean flgEsTercerDiaMes = false;

		SimpleDateFormat df = new SimpleDateFormat("dd/MM/yyyy");

		Calendar gcFecha = new GregorianCalendar();
		gcFecha.setTime(fechaActual);

		// Se captura el mes de la fecha actual.
		int mesActual = gcFecha.get(Calendar.MONTH);

		// Se obtiene el primer dia del mes de la fecha actual.
		int primerDia = gcFecha.getActualMinimum(Calendar.DAY_OF_MONTH);

		// La fecha es modificada y se setea el ultimo dia del mes.
		gcFecha.set(Calendar.DAY_OF_MONTH, primerDia);

		// Se obtiene el dia de la ultima fecha del mes. Por ejemplo: si el
		// ultimo dia del mes es 29/02/2012, entonces se devolvera 3
		// (miercoles).
		int diaPrimeraSemana = gcFecha.get(Calendar.DAY_OF_WEEK);

		gcFecha.get(Calendar.DATE);

		// Como la fecha se encuentra en su primera semana, se setea el dia
		// que se buscando. Por ejemplo: si el dia buscado es MIERCOLES,
		// entonces se posiciona la fecha en este dia de su ultima semana.
		// Se debe tener en cuenta que esta accion podria hacer que la
		// fecha pase al mes siguiente (esto es controlado lineas abajo).
		gcFecha.set(Calendar.DAY_OF_WEEK, diaCalendar);

		// Se evalua si el primer dia del mes es Lunes por ser el dia de inicio
		// de la semana.
		if (diaPrimeraSemana == Calendar.MONDAY) {

			// Si el primer dia del mes es lunes y es diferente al dia
			// buscado, entonces se retrocede una semana, ya que la fecha
			// actual se encuentra en un mes posterior.

			// Por ejemplo: el caso del mes de abril
			if (diaPrimeraSemana != diaCalendar) {

				gcFecha.add(Calendar.WEEK_OF_MONTH, 2);

				setSysprintln(">> Tercer " + diasSemana[diaCalendar - 1] + " mes = " + df.format(gcFecha.getTime()));
				// logger.debug(sysprintln);

			} else {

				gcFecha.add(Calendar.WEEK_OF_MONTH, 2);

				// Si el dia del mes es Lunes y coincide con el dia actual,
				// entonces se trata del tercer dia N del mes (donde N es el dia
				// buscado).

				setSysprintln(">> Tercer " + diasSemana[diaCalendar - 1] + " mes = " + df.format(gcFecha.getTime()));
				// logger.debug(sysprintln);

			}

		} else {

			// Como la fecha inicial ha cambiado, se guarda el mes de la ultima
			// modificacion de la fecha.
			int mesCambiado = gcFecha.get(Calendar.MONTH);

			// Si la resta del mes modificado menos el mes original es 1,
			// quiere decir que la fecha actual se encuentra en un mes
			// posterior.
			if (mesActual - mesCambiado == 1 || mesCambiado - mesActual == 11) {

				// La fecha actual ha pasado al mes siguiente, se resta menos
				// uno para llegar al ultimo dia N del mes actual.

				gcFecha.add(Calendar.WEEK_OF_MONTH, 3);

				setSysprintln(">> Tercer " + diasSemana[diaCalendar - 1] + " mes = " + df.format(gcFecha.getTime()));
				// logger.debug(sysprintln);
			} else {

				gcFecha.add(Calendar.WEEK_OF_MONTH, 2);
				// Si la resta es menor a 1, entonces el mes modificado es el
				// mismo que el mes original.
				setSysprintln(">> Tercer " + diasSemana[diaCalendar - 1] + " mes = " + df.format(gcFecha.getTime()));
				// logger.debug(sysprintln);
			}
		}

		// ============= COMPARACION DE FECHAS ================
		Date tercerDiaNMes = gcFecha.getTime();
		tercerDiaNMes = obtenerSoloFecha(tercerDiaNMes);

		if (fechaActual.equals(tercerDiaNMes)) {
			setSysprintln("> La fecha actual " + this.dfFormatoFecha.format(fechaActual)
					+ " es valida porque es el tercer " + this.diasSemana[diaCalendar - 1] + " del mes.");
			// logger.debug(sysprintln);

			flgEsTercerDiaMes = true;
		} else {
			setSysprintln("> La fecha actual " + this.dfFormatoFecha.format(fechaActual)
					+ " no es valida porque no es el tercer " + this.diasSemana[diaCalendar - 1] + " del mes.");
			// logger.debug(sysprintln);

			flgEsTercerDiaMes = false;
		}

		return flgEsTercerDiaMes;
	}

	/**
	 * Evalua si la fecha actual es el cuarto DIA_N del mes. Por ejemplo, si DIA_N
	 * es LUNES, se verifica que la fecha actual (enviada como parametro) sea el
	 * CUARTO LUNES del mes. De cumplirse esta condicion, se retorna TRUE. Caso
	 * contrario, se retorna FALSE.
	 * 
	 * @param fechaActual
	 *            fecha actual que sera evaluada, tipo Date
	 * @param diaProgramacion
	 *            dia de la programacion que viene desde la base de datos (puede ser
	 *            1-Lunes, 2-Martes, 3-Miercoles, 4-Jueves, 5-Viernes, 6-Sabado y
	 *            7-Domingo), tipo String
	 * @return TRUE o FALSE segun cumpla condiciones, tipo boolean
	 * @throws Exception
	 *             excepcion general en caso de error, tipo Exception
	 */
	@SuppressWarnings("deprecation")
	public boolean esCuartoDia(Date fechaActual, String diaProgramacion) throws Exception {

		// ============== CALCULO DEL CUARTO DIA N DEL MES =================

		int diaCalendar = 0;

		if (diaProgramacion.equals(DIA_LUNES))
			diaCalendar = Calendar.MONDAY;
		else if (diaProgramacion.equals(DIA_MARTES))
			diaCalendar = Calendar.TUESDAY;
		else if (diaProgramacion.equals(DIA_MIERCOLES))
			diaCalendar = Calendar.WEDNESDAY;
		else if (diaProgramacion.equals(DIA_JUEVES))
			diaCalendar = Calendar.THURSDAY;
		else if (diaProgramacion.equals(DIA_VIERNES))
			diaCalendar = Calendar.FRIDAY;
		else if (diaProgramacion.equals(DIA_SABADO))
			diaCalendar = Calendar.SATURDAY;
		else if (diaProgramacion.equals(DIA_DOMINGO))
			diaCalendar = Calendar.SUNDAY;
		else
			throw new Exception("El dia de programacion " + diaProgramacion + "es invalido");

		boolean flgEsCuartoDiaMes = false;

		Calendar gcFecha = new GregorianCalendar();
		gcFecha.setTime(fechaActual);

		Calendar gcDiaEspecifico = null;
		int diasMes = gcFecha.getActualMaximum(Calendar.DAY_OF_MONTH);

		Date cuartoDiaN = null;

		int contadorLunes = 0;
		int nroDiaSemana = 0;

		for (int i = 1; i <= diasMes; i++) {
			//int nroDiaMes = i;

			Date diaEspecifico = new Date();
			diaEspecifico.setDate(i);
			diaEspecifico.setMonth(fechaActual.getMonth());
			diaEspecifico.setYear(fechaActual.getYear());

			gcDiaEspecifico = new GregorianCalendar();
			gcDiaEspecifico.setTime(diaEspecifico);

			nroDiaSemana = gcDiaEspecifico.get(Calendar.DAY_OF_WEEK);

			if (nroDiaSemana == diaCalendar) {
				contadorLunes++;

				if (contadorLunes == 4) {
					cuartoDiaN = new Date(gcDiaEspecifico.getTimeInMillis());
					break;
				}
			}
		}

		setSysprintln(">> Cuarto " + diasSemana[diaCalendar - 1] + " del mes " + this.dfFormatoFecha.format(cuartoDiaN));
		// logger.debug(sysprintln);

		// ============= COMPARACION DE FECHAS ================
		Date cuartoDiaNMes = obtenerSoloFecha(cuartoDiaN);

		if (fechaActual.equals(cuartoDiaNMes)) {
			setSysprintln("> La fecha actual " + this.dfFormatoFecha.format(fechaActual)
					+ " es valida porque es el cuarto " + this.diasSemana[diaCalendar - 1] + " del mes.");
			// logger.debug(sysprintln);

			flgEsCuartoDiaMes = true;
		} else {
			setSysprintln("> La fecha actual " + this.dfFormatoFecha.format(fechaActual)
					+ " no es valida porque no es el cuarto " + this.diasSemana[diaCalendar - 1] + " del mes.");
			// logger.debug(sysprintln);

			flgEsCuartoDiaMes = false;
		}

		return flgEsCuartoDiaMes;
	}

	/**
	 * Evalua si la fecha actual es el ultimo DIA_N del mes. Por ejemplo, si DIA_N
	 * es LUNES, se verifica que la fecha actual (enviada como parametro) sea el
	 * ULTIMO LUNES del mes. De cumplirse esta condicion, se retorna TRUE. Caso
	 * contrario, se retorna FALSE.
	 * 
	 * @param fechaActual
	 *            fecha actual que sera evaluada, tipo Date
	 * @param diaProgramacion
	 *            dia de la programacion que viene desde la base de datos (puede ser
	 *            1-Lunes, 2-Martes, 3-Miercoles, 4-Jueves, 5-Viernes, 6-Sabado y
	 *            7-Domingo), tipo String
	 * @return TRUE o FALSE segun cumpla condiciones, tipo boolean
	 * @throws Exception
	 *             excepcion general en caso de error, tipo Exception
	 */
	private boolean esUltimoDia(Date fechaActual, String diaProgramacion) throws Exception {

		boolean flgEsUltimoDiaMes = false;

		// ============= CALCULO DEL ULTIMO DIA DEL MES ================
		int diaCalendar = 0;

		if (diaProgramacion.equals(DIA_LUNES))
			diaCalendar = Calendar.MONDAY;
		else if (diaProgramacion.equals(DIA_MARTES))
			diaCalendar = Calendar.TUESDAY;
		else if (diaProgramacion.equals(DIA_MIERCOLES))
			diaCalendar = Calendar.WEDNESDAY;
		else if (diaProgramacion.equals(DIA_JUEVES))
			diaCalendar = Calendar.THURSDAY;
		else if (diaProgramacion.equals(DIA_VIERNES))
			diaCalendar = Calendar.FRIDAY;
		else if (diaProgramacion.equals(DIA_SABADO))
			diaCalendar = Calendar.SATURDAY;
		else if (diaProgramacion.equals(DIA_DOMINGO))
			diaCalendar = Calendar.SUNDAY;
		else
			throw new Exception("El dia de programacion " + diaProgramacion + "es invalido");

		Calendar gcFechaUltimoDiaN = new GregorianCalendar();
		gcFechaUltimoDiaN.setTime(fechaActual);

		// Se captura el mes de la fecha actual.
		int mesActual = gcFechaUltimoDiaN.get(Calendar.MONTH);

		// Se obtiene el ultimo dia del mes de la fecha actual.
		int ultimoDia = gcFechaUltimoDiaN.getActualMaximum(Calendar.DAY_OF_MONTH);

		// La fecha es modificada y se setea el ultimo dia del mes.
		gcFechaUltimoDiaN.set(Calendar.DAY_OF_MONTH, ultimoDia);

		// Se obtiene el dia de la ultima fecha del mes. Por ejemplo: si el
		// ultimo dia del mes es 29/02/2012, entonces se devolvera 3
		// (miercoles).
		int diaUltimaSemana = gcFechaUltimoDiaN.get(Calendar.DAY_OF_WEEK);

		gcFechaUltimoDiaN.get(Calendar.DATE);

		// Como la fecha se encuentra en su ultima semana, se setea el dia
		// que se buscando. Por ejemplo: si el dia buscado es MIERCOLES,
		// entonces se posiciona la fecha en este dia de su ultima semana.
		// Se debe tener en cuenta que esta accion podria hacer que la
		// fecha pase al mes siguiente (esto es controlado lineas abajo).
		gcFechaUltimoDiaN.set(Calendar.DAY_OF_WEEK, diaCalendar);

		// Se evalua si el ultimo dia del mes es Lunes por ser el dia de inicio
		// de la semana.
		if (diaUltimaSemana == Calendar.MONDAY) {

			// Si el ultimo dia del mes es lunes y es diferente al dia
			// buscado, entonces se retrocede una semana, ya que la fecha
			// actual se encuentra en un mes posterior.

			// Por ejemplo: el caso del mes de abril
			if (diaUltimaSemana != diaCalendar) {

				gcFechaUltimoDiaN.add(Calendar.WEEK_OF_MONTH, -1);
				setSysprintln(">> Ultimo " + this.diasSemana[diaCalendar - 1] + " mes = "
						+ this.dfFormatoFecha.format(gcFechaUltimoDiaN.getTime()));
				// logger.debug(sysprintln);

			} else {

				// Si el dia del mes es Lunes y coincide con el dia actual,
				// entonces se trata del ultimo dia N del mes (donde N es el dia
				// buscado).
				setSysprintln(">> Ultimo " + this.diasSemana[diaCalendar - 1] + " mes = "
						+ this.dfFormatoFecha.format(gcFechaUltimoDiaN.getTime()));
				// logger.debug(sysprintln);
			}

		} else {

			// Como la fecha inicial ha cambiado, se guarda el mes de la ultima
			// modificacion de la fecha.
			int mesCambiado = gcFechaUltimoDiaN.get(Calendar.MONTH);

			// Si la resta del mes modificado menos el mes original es 1,
			// quiere decir que la fecha actual se encuentra en un mes
			// posterior.
			if (mesCambiado - mesActual == 1) {

				// La fecha actual ha pasado al mes siguiente, se resta menos
				// uno para llegar al ultimo dia N del mes actual.

				gcFechaUltimoDiaN.add(Calendar.WEEK_OF_MONTH, -1);
				setSysprintln(">> Ultimo " + this.diasSemana[diaCalendar - 1] + " mes = "
						+ this.dfFormatoFecha.format(gcFechaUltimoDiaN.getTime()));
				// logger.debug(sysprintln);
			} else {
				// Si la resta es menor a 1, entonces el mes modificado es el
				// mismo que el mes original.
				setSysprintln(">> Ultimo " + this.diasSemana[diaCalendar - 1] + " mes = "
						+ this.dfFormatoFecha.format(gcFechaUltimoDiaN.getTime()));
				// logger.debug(sysprintln);
			}
		}

		// ============= COMPARACION DE FECHAS ================
		Date ultimoDiaNMes = gcFechaUltimoDiaN.getTime();
		ultimoDiaNMes = obtenerSoloFecha(ultimoDiaNMes);

		if (fechaActual.equals(ultimoDiaNMes)) {
			setSysprintln("> La fecha actual " + this.dfFormatoFecha.format(fechaActual)
					+ " es valida porque es el ultimo " + this.diasSemana[diaCalendar - 1] + " del mes.");
			// logger.debug(sysprintln);

			flgEsUltimoDiaMes = true;
		} else {
			setSysprintln("> La fecha actual " + this.dfFormatoFecha.format(fechaActual)
					+ " no es valida porque no es el ultimo " + this.diasSemana[diaCalendar - 1] + " del mes.");
			// logger.debug(sysprintln);

			flgEsUltimoDiaMes = false;
		}

		return flgEsUltimoDiaMes;
	}

	/**
	 * Retorna la objeto date solo parseado con fecha (no existe hora)
	 * 
	 * @param fecha
	 *            fecha que se parseara, tipo Date
	 * @return objeto date solo con fecha, tipo Date
	 * @throws Exception
	 *             excepcion general en caso de error, tipo Exception
	 */
	private Date obtenerSoloFecha(Date fecha) throws Exception {

		String sFecha = this.dfFormatoFecha.format(fecha);

		return this.dfFormatoFecha.parse(sFecha);

	}

	public boolean evaluarProgramacion(ReglaNegocioProgramacion programacion, Date fechaServidorReal) throws Exception {

		// LOGGER.debug("evaluarProgramacion.1");
		BProgramacion bProgramacion = new BProgramacion();
		bProgramacion.setIdReglaNegocio(programacion.getPk().getIdReglaNegocio());
		bProgramacion.setIdReglaNegocioProgramacionEjecucion(programacion.getPk().getIdProgramacion());
		if (programacion.getIdFrecuenciaProgramacion() != null) {
			bProgramacion.setTipoProgramacion(programacion.getIdFrecuenciaProgramacion().substring(0, 1));
		} else {
			LOGGER.debug("no tiene un tipo de proogramacion " + programacion.getPk().getIdReglaNegocio() + "-"
					+ programacion.getPk().getIdProgramacion());
			return false;
		}

		if (programacion.getFechaUltimaEjecucion() != null) {
			bProgramacion.setFechaUltimaEjecucion(programacion.getFechaUltimaEjecucion());
		}
		// LOGGER.debug("evaluarProgramacion.2");
		// configuracion diaria
		bProgramacion.getDiaria().setCadaNDias(programacion.getDiaCadaNDias());

		if (programacion.getDiaIdFrecuencia() != null) {
			bProgramacion.getDiaria().setTipoFrecuenciaEjecucion(programacion.getDiaIdFrecuencia().substring(0, 1));
		} else {
			LOGGER.debug("    001 : REGLA NEGOCIO = " + programacion.getPk().getIdReglaNegocio() + "-"
					+ programacion.getPk().getIdProgramacion() + " no tiene un idFrecuencia");
			return false;
		}

		bProgramacion.getDiaria().setFrecuenciaUnica_HoraEjecucion(programacion.getDiaFrecUnicaHoraejecucion());
		bProgramacion.getDiaria().setFrecuenciaRango_CadaN(programacion.getDiaFrecRangoCadan());
		bProgramacion.getDiaria().setFrecuenciaRango_Frecuencia(programacion.getDiaFrecRangoIdFrecuTiempo());
		bProgramacion.getDiaria().setFrecuenciaRango_HoraInicio(programacion.getDiaFrecRangoHorainicio());
		bProgramacion.getDiaria().setFrecuenciaRango_HoraFin(programacion.getDiaFrecRangoHorafin());
		bProgramacion.getDiaria().setFechaInicio(programacion.getDiaFechaInicio());
		bProgramacion.getDiaria().setFlagTieneFin(programacion.getDiaFlgTieneFin());
		bProgramacion.getDiaria().setFechaFin(programacion.getDiaFechaFin());

		// LOGGER.debug("evaluarProgramacion.3");
		// semanas

		bProgramacion.getSemanal().setCadaNSemanas(programacion.getSemCadaNSemanas());
		bProgramacion.getSemanal().setFlagEjecutarLunes(UValidador.validarFlag(programacion.getSemFlgEjecutarLunes()));
		bProgramacion.getSemanal()
				.setFlagEjecutarMartes(UValidador.validarFlag(programacion.getSemFlgEjecutarMartes()));
		bProgramacion.getSemanal()
				.setFlagEjecutarMiercoles(UValidador.validarFlag(programacion.getSemFlgEjecutarMiercoles()));
		bProgramacion.getSemanal()
				.setFlagEjecutarJueves(UValidador.validarFlag(programacion.getSemFlgEjecutarJueves()));
		bProgramacion.getSemanal()
				.setFlagEjecutarViernes(UValidador.validarFlag(programacion.getSemFlgEjecutarViernes()));
		bProgramacion.getSemanal()
				.setFlagEjecutarSabado(UValidador.validarFlag(programacion.getSemFlgEjecutarSabado()));
		bProgramacion.getSemanal()
				.setFlagEjecutarDomingo(UValidador.validarFlag(programacion.getSemFlgEjecutarDomingo()));
		if (programacion.getSemIdFrecuencia() == null) {
			bProgramacion.getSemanal().setTipoFrecuenciaEjecucion(null);
		} else {
			bProgramacion.getSemanal().setTipoFrecuenciaEjecucion(programacion.getSemIdFrecuencia().substring(0, 1));
		}

		bProgramacion.getSemanal().setFrecuenciaUnica_HoraEjecucion(programacion.getSemFrecUnicaHoraejecucion());
		bProgramacion.getSemanal().setFrecuenciaRango_CadaN(programacion.getSemFrecRangoCadan());
		bProgramacion.getSemanal().setFrecuenciaRango_Frecuencia(programacion.getSemFrecRangoIdFrecuTiempo());
		bProgramacion.getSemanal().setFrecuenciaRango_HoraInicio(programacion.getSemFrecRangoHorainicio());
		bProgramacion.getSemanal().setFrecuenciaRango_HoraFin(programacion.getSemFrecRangoHorafin());
		bProgramacion.getSemanal().setFechaInicio(programacion.getSemFechaInicio());
		bProgramacion.getSemanal().setFlagTieneFin(programacion.getSemFlgTieneFin());
		bProgramacion.getSemanal().setFechaFin(programacion.getSemFechaFin());

		// LOGGER.debug("evaluarProgramacion.4");
		// mensual
		bProgramacion.getMensual().setTipoFrecuenciaEjecucionMensual(programacion.getMesIdFrecuenciaMensual());
		bProgramacion.getMensual().setFrecuenciaDia_Dia(programacion.getMesFrecDiaDia());
		bProgramacion.getMensual().setFrecuenciaDia_CadaN(programacion.getMesFrecDiaCadan());
		bProgramacion.getMensual().setFrecuenciaNombreDia_OrdenDiaSemana(programacion.getMesFrecNomDiaIdOrdenDia());
		bProgramacion.getMensual().setFrecuenciaNombreDia_DiaSemana(programacion.getMesFrecNomDiaIdDiaSemana());
		bProgramacion.getMensual().setFrecuenciaNombreDia_CadaN(programacion.getMesFrecNomDiaCadan());
		if (programacion.getMesIdFrecuencia() == null) {
			bProgramacion.getMensual().setTipoFrecuenciaEjecucion(null);
		} else {
			bProgramacion.getMensual().setTipoFrecuenciaEjecucion(programacion.getMesIdFrecuencia().substring(0, 1));
		}

		bProgramacion.getMensual().setFrecuenciaUnica_HoraEjecucion(programacion.getMesFrecUnicaHoraejecucion());
		bProgramacion.getMensual().setFrecuenciaRango_CadaN(programacion.getMesFrecRangoCadan());
		bProgramacion.getMensual().setFrecuenciaRango_Frecuencia(programacion.getMesFrecRangoIdFrecuTiempo());
		bProgramacion.getMensual().setFrecuenciaRango_HoraInicio(programacion.getMesFrecRangoHorainicio());
		bProgramacion.getMensual().setFrecuenciaRango_HoraFin(programacion.getMesFrecRangoHorafin());
		bProgramacion.getMensual().setFechaInicio(programacion.getMesFechaInicio());
		bProgramacion.getMensual().setFlagTieneFin(programacion.getMesFlgTieneFin());
		bProgramacion.getMensual().setFechaFin(programacion.getMesFechaFin());

		// LOGGER.debug("evaluarProgramacion.5");
		// La clase ruler requiere de:
		// - Programacion
		// - Fecha actual (obtenida con new Date o desde la BD)
		// - Fecha de ultima ejecucion
		this.llenarParametros(bProgramacion, fechaServidorReal, bProgramacion.getFechaUltimaEjecucion());

		// LOGGER.debug("evaluarProgramacion.6");
		// Se evalua si se debe realizar la ejecucion.
		return this.evaluarProgramacion();
	}

	public String getSysprintln() {
		return sysprintln;
	}

	public void setSysprintln(String sysprintln) {
		this.sysprintln = sysprintln;
	}

}
