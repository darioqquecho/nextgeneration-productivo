package net.royal.spring.alertas.dominio.dto;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import net.royal.spring.framework.core.UValidador;

public class FrecuencyEvaluator {

	private static Log LOGGER = LogFactory.getLog(FrecuencyEvaluator.class);

	private String sysprintln;
	/**
	 * Constante para identificar la frecuencia de ejecucion unica de una
	 * programacion
	 */
	public static final String FRECUENCIA_UNICA = "U";
	/**
	 * Constante para identificar la frecuencia de ejecucion dentro de un rango para
	 * una programacion
	 */
	public static final String FRECUENCIA_RANGO = "R";

	/**
	 * Constante para identificar la iteracion de la ejecucion de la programacion en
	 * minutos (cada N minutos, donde N es un valor numerico entero)
	 * 
	 * se cambio el original. el original es de valor (1) se cambio a (HOR)
	 */
	public static final String TIPO_ITERACION_MINUTOS = "MIN";
	/**
	 * Constante para identificar la iteracion de la ejecucion de la programacion en
	 * horas (cada N horas, donde N es un valor numerico entero)
	 * 
	 * se cambio el original. el original es de valor (1) se cambio a (HOR)
	 */
	public static final String TIPO_ITERACION_HORAS = "HOR";

	/**
	 * La holgura representa la cantidad de minutos que se agrega a cada iteracion.
	 * Por ejemplo: si la iteracion se realiza cada 10 minutos, entonces se
	 * agregaran 2 minutos de holgura. Esto responde al hecho de que el hilo no
	 * siempre se ejecuta en el minuto esperado.
	 */
	private static final int HOLGURA_MINUTOS = 1;

	/** Programacion tiene fecha de fin */
	private static final String TIENE_FIN = "T";

	/** Programacion indefinida */
	private static final String NO_TIENE_FIN = "N";

	/**
	 * Clase para formatear objetos del tipo fecha con el patron <b>dd/MM/yyyy</b>
	 */
	private SimpleDateFormat dFormatoFecha = new SimpleDateFormat("dd/MM/yyyy");

	/**
	 * Clase para formatear objetos del tipo fecha con el patron <b>dd/MM/yyyy
	 * HH:mm</b>
	 */
	private SimpleDateFormat dFormatoFechaHora = new SimpleDateFormat("dd/MM/yyyy HH:mm");

	/**
	 * Clase para formatear objetos del tipo fecha con el patron <b>HH:mm</b>
	 */
	private SimpleDateFormat dFormatoHora = new SimpleDateFormat("HH:mm");

	/**
	 * Tipo de frecuencia (Unica o Rango)
	 */
	private String tipoFrecuencia;
	/**
	 * Fecha de inicio permitida para la ejecucion de la programacion
	 */
	private Date fechaInicio;

	/**
	 * Fecha maxima permitida para la ejecucion de la programacion
	 */
	private Date fechaFin;

	/**
	 * Fecha en la cual fue ejecutada por ultima vez la programacion
	 */
	private Date fechaUltimaEjecucion;

	/**
	 * Hora de inicio permitida para realizar la ejecucion. En caso que la
	 * frecuencia sea UNICA, este atributo almacena la hora unica de ejecucion.
	 */
	private Date horaInicio;

	/**
	 * Hora maxima permitida para realizar la ejecucion. En caso que la frecuencia
	 * sea UNICA, este atributo estara vacio.
	 */
	private Date horaFin;
	/**
	 * Programacion que sera evaluada (puede ser diaria, semanal o mensual)
	 */
	private BProgramacionBase programacion;

	/**
	 * Hora actual (hora en la que el hilo invoca a esta clase)
	 */
	private Date fechaActual;

	/**
	 * El constructor inicializa:<br/>
	 * - El map mapDiasSemanaConfigurados
	 */
	public FrecuencyEvaluator() {
	}

	/**
	 * Llena los siguientes atributos de la clase:<br/>
	 * - Programacion a evaluar (que puede ser diaria, semanal o mensual)
	 * 
	 * @param tipoProgramacion
	 *            , tipo de programacion (D-Diario, S-Semanal o M-Mensual), tipo
	 *            String
	 * @param programacion
	 *            programacion a la cual se evaluara segun su frecuencia programada,
	 *            tipo BProgramacionBase
	 * @param fechaUltimaEjecucion
	 *            fecha de ultima ejecucion de la programacion, tipo Date
	 */
	public void llenarParametros(String tipoProgramacion, BProgramacionBase programacion, Date fechaUltimaEjecucion) {

		this.programacion = programacion;

		this.fechaInicio = this.programacion.getFechaInicio();
		this.fechaFin = this.programacion.getFechaFin();
		this.fechaUltimaEjecucion = fechaUltimaEjecucion;

		this.tipoFrecuencia = this.programacion.getTipoFrecuenciaEjecucion();

		if (!UValidador.estaVacio(this.tipoFrecuencia)) {

			if (this.tipoFrecuencia.equals(FRECUENCIA_UNICA)) {
				//LOGGER.debug("FRECUENCIA UNICA =HORAINICIO: FRECUENCIA_HORAEJECUCION");
				this.horaInicio = this.programacion.getFrecuenciaUnica_HoraEjecucion();

			} else if (this.tipoFrecuencia.equals(FRECUENCIA_RANGO)) {
				//LOGGER.debug("FRECUENCIA RANGO =HORAINICIO: FRECUENCIA_RANGOHORAINICIO");
				//LOGGER.debug("FRECUENCIA RANGO =HORAFIN: FRECUENCIA_RANGOHORAFIN");
				this.horaInicio = this.programacion.getFrecuenciaRango_HoraInicio();
				this.horaFin = this.programacion.getFrecuenciaRango_HoraFin();
			}
		}

	}

	/**
	 * Evalua si es que la fecha actual se encuentra dentro de la parametrizacion de
	 * horas y fechas de la programacion. Si es que retorna TRUE, quiere decir que
	 * se puede realizar la ejecucion de la programacion. Si es que retorna FALSE,
	 * quiere decir que la fecha actual esta fuera de los rangos permitidos por la
	 * programacion.
	 * 
	 * @param fechaActual
	 *            fecha actual que sera evaluada, tipo Date
	 * @return TRUE o FALSE segun cumpla condiciones, tipo boolean
	 * @throws Exception
	 *             excepcion general en caso de error, tipo Exception
	 */
	public boolean esFechaValidaDentroDeProgramacion(Date fechaActual) throws Exception {

		this.fechaActual = fechaActual;

		if (!validar())
			return false;

		return estaDentroDeFrecuencia(fechaActual);
	}

	/**
	 * Valida que los siguientes atributos sean validos:<br/>
	 * <br/>
	 * - Fecha actual no debe ser nula.<br/>
	 * - Fecha de inicio no debe ser nula.<br/>
	 * - Fecha de fin no debe ser nula.<br/>
	 * - Fecha de inicio no debe ser nula.<br/>
	 * - El tipo de frecuencia no debe ser nulo y debe tener valores validos
	 * (U-Unica o R-Rango).<br/>
	 * <br/>
	 * Si se cumplen estas condiciones, se retorna TRUE. Caso contrario, se retorna
	 * FALSE.
	 * 
	 * @return TRUE o FALSE segun cumpla condiciones, tipo boolean
	 * @throws Exception
	 *             excepcion general en caso de error, tipo Exception
	 */
	@SuppressWarnings("deprecation")
	public boolean validar() throws Exception {

		if (this.programacion == null)
			throw new Exception("Frecuency Evaluator: La programacion es nula");

		if (this.fechaActual == null)
			throw new Exception("Frecuency Evaluator: La fecha actual es nula");

		if (this.fechaInicio == null)
			throw new Exception("Frecuency Evaluator: La fecha de inicio es nula");

		if (this.fechaFin == null) {
			this.fechaFin = new Date();
			this.fechaFin.setYear(2099);
		}
		/*
		 * if(this.fechaFin == null) throw new
		 * Exception("Frecuency Evaluator: La fecha de fin " + "es nula");
		 */

		// La fecha de ultima ejecucion puede ser nula, ya que se trataria de
		// la primera ejecucion de la programacion.

		/*
		 * if(this.fechaUltimaEjecucion == null) throw new
		 * Exception("Frecuency Evaluator: La fecha de ultima " + "ejecucion es nula");
		 */

		if (this.horaInicio == null)
			throw new Exception("Frecuency Evaluator:(FrecuenciaUnica_HoraEjecucion) La hora de inicio (o unica) es nula....");

		if (UValidador.estaVacio(this.tipoFrecuencia))
			throw new Exception("Frecuency Evaluator: El tipo de frecuencia no se ha definido");
		else {
			if (!this.tipoFrecuencia.equals(FRECUENCIA_RANGO) && !this.tipoFrecuencia.equals(FRECUENCIA_UNICA))
				throw new Exception("Frecuency Evaluator: El tipo de frecuencia no tiene un valor valido ("
						+ this.tipoFrecuencia + ")");
			else {
				if (this.tipoFrecuencia.equals(FRECUENCIA_RANGO)) {

					if (this.horaFin == null) {
						throw new Exception("Frecuency Evaluator: La hora fin es nula");
					}
				}

			}

		}

		return true;
	}

	public boolean estaDentroDeRecurrencia(Date fechaActual) {

		return true;
	}

	/**
	 * Valida que la fecha actual se encuentre dentro de las fechas y horas
	 * permitidas (definidas en la programacion). Para ello, se evaluan criterios
	 * como:<br/>
	 * <br/>
	 * 
	 * - Si la frecuencia es UNICA o RANGO<br/>
	 * - Si la fecha actual esta dentro del rango de fechas de inicio y fin
	 * definidos en la programacion<br/>
	 * - Si la hora actual es igual o se encuentra dentro del rango de horas
	 * permitido<br/>
	 * <br/>
	 * 
	 * Si se cumplen estas condiciones, se retorna TRUE. Caso contrario, se retorna
	 * FALSE.
	 * 
	 * @param fechaActual
	 *            fecha actual a la cual se sometera a evaluacion, tipo Date
	 * @return TRUE o FALSE segun cumpla condiciones, tipo boolean
	 * @throws Exception
	 *             exception general en caso de error, tipo Exception
	 */
	@SuppressWarnings("deprecation")
	public boolean estaDentroDeFrecuencia(Date fechaActual) throws Exception {

		// Primero se verifica que la fecha actual se encuentre dentro del
		// rango de fechas de inicio y fin definidos para la programacion.
		// Antes de eso, se parsean todas las fechas para obtener solamente
		// las fechas (se dejan de lado las horas) con la finalidad de no
		// generar errores en la comparacion.

		Date soloFechaInicio = obtenerSoloFecha(this.fechaInicio);
		Date soloFechaFin = obtenerSoloFecha(this.fechaFin);
		Date soloFechaActual = obtenerSoloFecha(fechaActual);

		boolean flgPasoValidacion = false;

		// Este flag indica que la hora actual es igual a la hora de inicio
		// programada. Solo es usada para la FRECUENCIA_RANGO.
		boolean flgEsHoraInicio = false;

		// La fecha actual debe ser MAYOR O IGUAL A LA FECHA DE INICIO Y MENOR
		// O IGUAL A LA FECHA DE FIN

		if (this.programacion.getFlagTieneFin().equals(TIENE_FIN)) {

			// logger.debug("> La programacion tiene fin");

			if (estaDentroDeRango(soloFechaActual, soloFechaInicio, soloFechaFin)) {

				flgPasoValidacion = true;
			}

		} else {

			// logger.debug("> La programacion no tiene fin");

			// Si no tiene fin, solo se valida que la fecha actual sea
			// MAYOR O IGUAL A LA FECHA DE INICIO
			if (soloFechaActual.equals(soloFechaInicio) || soloFechaActual.after(soloFechaInicio)) {
				sysprintln = ">> La fecha actual " + this.dFormatoFecha.format(soloFechaActual)
						+ " es mayor o igual a la fecha de inicio " + this.dFormatoFecha.format(soloFechaInicio);
				// logger.debug(sysprintln);
				flgPasoValidacion = true;
			} else {
				sysprintln = ">> La fecha actual " + this.dFormatoFecha.format(soloFechaActual)
						+ " no es mayor o igual a la fecha de inicio " + this.dFormatoFecha.format(soloFechaInicio);
				// logger.error(sysprintln);
			}

		}

		if (!flgPasoValidacion) {
			sysprintln = "> La fecha actual " + this.dFormatoFecha.format(soloFechaActual)
					+ " no es valida porque no esta en el rango de " + " fecha inicio "
					+ this.dFormatoFecha.format(soloFechaInicio) + " y fecha fin "
					+ this.dFormatoFecha.format(soloFechaFin);
			// logger.error(sysprintln);

			// Si no paso la validacion, quiere decir que la fecha esta fuera
			// del rango programado.
			return false;
		}

		// En caso que la fecha se encuentre dentro del rango de fechas
		// permitido, se pregunta si se encuentra dentro del rango de horas
		// permitidas. Esta validacion se hara de dos formas (dependiendo
		// del tipo de frecuencia).

		Date nHoraActual = obtenerSoloHora(fechaActual);
		Date nHoraInicio = obtenerSoloHora(this.horaInicio);

		Calendar gcHolgura = null;

		Date nHoraHolgura = null;

		if (this.tipoFrecuencia.equals(FRECUENCIA_UNICA)) {

			gcHolgura = new GregorianCalendar();
			gcHolgura.setTimeInMillis(nHoraInicio.getTime());
			gcHolgura.add(Calendar.MINUTE, HOLGURA_MINUTOS);

			nHoraHolgura = gcHolgura.getTime();

			// logger.debug("> El tipo de frecuencia es UNICA");

			// Se pregunta si la hora actual esta dentro de la hora inicio y la
			// hora de holgura.

			if (estaDentroDeRango(nHoraActual, nHoraInicio, nHoraHolgura)) {
				sysprintln = ">> La hora actual " + this.dFormatoHora.format(nHoraActual)
						+ " esta dentro del rango de la hora inicio " + this.dFormatoHora.format(nHoraInicio)
						+ " y la hora de holgura " + this.dFormatoHora.format(nHoraHolgura);
				// logger.debug(sysprintln);

				flgPasoValidacion = true;
			} else {
				sysprintln = ">> La hora actual " + this.dFormatoHora.format(nHoraActual)
						+ " no esta dentro del rango de la hora inicio " + this.dFormatoHora.format(nHoraInicio)
						+ " y la hora de holgura " + this.dFormatoHora.format(nHoraHolgura);
				// logger.debug(sysprintln);

				return false;
			}

			// Si es FRECUENCIA UNICA, se valida que la hora actual sea igual
			// a la hora de inicio de la programacion y que no exista ejecucion
			// anterior con la misma fecha.

			if (this.fechaUltimaEjecucion == null) {

				sysprintln = ">> La fecha de ultima ejecucion " + "esta vacia";
				// logger.debug(sysprintln);

				// Si la fecha de ultima ejecucion es nula, quiere decir que se
				// trata de la primera ejecucion de la programacion.

				if (estaDentroDeRango(nHoraActual, nHoraInicio, nHoraHolgura)) {
					// if(nHoraActual.equals(nHoraInicio) ||
					//
					// (nHoraActual.equals(nHoraHolgura) ||
					// nHoraActual.before(nHoraHolgura) )
					// ){

					sysprintln = ">> La hora actual " + dFormatoHora.format(nHoraActual)
							+ " esta dentro del rango de la hora unica " + dFormatoHora.format(nHoraInicio)
							+ " y la hora de holgura " + dFormatoHora.format(nHoraHolgura);
					// logger.debug(sysprintln);

					return true;
				} else {
					sysprintln = ">> La hora actual " + dFormatoHora.format(nHoraActual)
							+ " esta fuera del rango de la hora unica " + dFormatoHora.format(nHoraInicio)
							+ " y la hora de holgura " + dFormatoHora.format(nHoraHolgura);
					// logger.debug(sysprintln);
					// logger.debug("Se corta proceso");

					return false;
				}

			} else {
				LOGGER.debug("La fecha de ultima ejecucion no esta vacia");
				sysprintln = ">> La fecha de ultima ejecucion no " + "esta vacia";
				// logger.debug(sysprintln);

				// Si existe una fecha de ejecucion, quiere decir que la
				// programacion fue ejecutada anteriormente. En este caso si
				// esta fecha se encuentra en el rango de la hora unica y
				// hora de holgura, quiere decir que la programacion ya fue
				// ejecutada y no se debe realizar otra ejecucion para la fecha
				// actual. Caso contrario, debe ejecutarse.

				// Primero, se pregunta si la fecha unica coincide con la fecha
				// de ultima ejecucion.

				Date soloFechaUltEjec = obtenerSoloFecha(this.fechaUltimaEjecucion);

				if (soloFechaActual.equals(soloFechaUltEjec)) {

					// Si es que la fechas conciden, se pregunta si la hora de
					// ultima ejecucion se encuentra en el rango de hora de
					// inicio y hora de holgura.

					Date nHoraUltimaEjecucion = obtenerSoloHora(this.fechaUltimaEjecucion);
					if (estaDentroDeRango(nHoraActual, nHoraUltimaEjecucion, nHoraHolgura)) {

						// Ejemplo:
						// Hora de inicio: 10:00
						// Hora de maxima holgura: 10:01
						// Hora de ultima ejecucion: 10:01

						// HUE == HA > false
						// HUE > HA > true

						// HUE == HH > true
						// HUE < HH = false

						sysprintln = ">> UNICA: La hora actual " + this.dFormatoHora.format(nHoraActual) + " no es "
								+ "valida porque se encuentra en el rango de " + "la ultima hora de ejecucion "
								+ this.dFormatoHora.format(nHoraUltimaEjecucion) + " y la hora de holgura "
								+ this.dFormatoHora.format(nHoraHolgura);
						// logger.error(sysprintln);

						return false;
					} else {

						// Ejemplo:
						// Hora de inicio: 10:00
						// Hora de maxima holgura: 10:01
						// Hora de ultima ejecucion: 10:02

						// HUE == HA > false
						// HUE > HA > true

						// HUE == HH > true
						// HUE < HH = false

						sysprintln = ">> UNICA: La hora actual " + dFormatoHora.format(nHoraActual) + " es "
								+ "valida porque se encuentra fuera del rango de " + "la ultima hora de ejecucion "
								+ this.dFormatoHora.format(nHoraUltimaEjecucion) + " y la hora de holgura "
								+ this.dFormatoHora.format(nHoraHolgura);
						// logger.error(sysprintln);
						flgPasoValidacion = true;
					}

				} else {

					// Si las fechas actual y de ultima ejecucion son
					// diferentes, entonces se trata de iteraciones que toman
					// dias, semanas o meses. En este caso, se valida que la
					// fecha actual sea mayor a la ultima fecha de ejecucion.

					if (soloFechaActual.after(soloFechaUltEjec)) {
						sysprintln = ">> La fecha actual " + this.dFormatoFecha.format(soloFechaActual)
								+ " es valida porque es posterior a la fecha de" + " ultima ejecucion "
								+ this.dFormatoFecha.format(soloFechaUltEjec);
						// logger.debug(sysprintln);

						flgPasoValidacion = true;

					} else if (soloFechaActual.before(soloFechaUltEjec)) {
						sysprintln = ">> La fecha actual " + this.dFormatoFecha.format(soloFechaActual)
								+ " no es valida porque es anterior a la fecha de" + " ultima ejecucion "
								+ this.dFormatoFecha.format(soloFechaUltEjec);
						// logger.debug(sysprintln);

						flgPasoValidacion = false;
					}
				}

			}

		} else if (this.tipoFrecuencia.equals(FRECUENCIA_RANGO)) {

			// logger.debug("> El tipo de frecuencia es RANGO");

			Integer iteracionFrecuencia = this.programacion.getFrecuenciaRango_CadaN();

			String tipoIteracion = this.programacion.getFrecuenciaRango_Frecuencia();

			if (iteracionFrecuencia == null || iteracionFrecuencia.intValue() <= 0) {
				throw new Exception(
						"El campo CADA_DIA_NDIAS NDIAS de la programacion es " + "nulo o tiene un valor menor o igual a cero.");

			}

			if (UValidador.estaVacio(tipoIteracion))
				throw new Exception("El tipo iteracion (Horas o Minutos) " + "esta vacio");

			// Si es FRECUENCIA RANGO, se valida que la hora actual se
			// encuentre dentro del rango de fechas permitido y que tenga
			// + N minutos u horas con respecto a la fecha de inicio.
			// Por ejemplo:
			// - Tipo de iteracion: Cada 2 minutos
			// - Fecha de inicio: 18/02/2012 10:40
			// - Fecha actual: 18/02/2012 10:42
			// Entonces >>> Fecha actual - fecha inicio = 2 minutos
			// Si se cumple esta condicion, se permitira la ejecucion de la
			// programacion.

			// ======================== PRIMERA VALIDACION =====================

			// Se valida que la hora este dentro del rango.

			gcHolgura = new GregorianCalendar();
			gcHolgura.setTimeInMillis(nHoraInicio.getTime());
			gcHolgura.add(Calendar.MINUTE, HOLGURA_MINUTOS);

			nHoraHolgura = gcHolgura.getTime();

			String sHoraFinal = this.dFormatoHora.format(this.horaFin);

			Date nHoraFin = this.dFormatoHora.parse(sHoraFinal);

			// Se llena el flag que define que la hora actual es igual a la
			// hora de inicio programada.

			if (nHoraActual.equals(nHoraInicio) ||

					(nHoraActual.equals(nHoraHolgura) || nHoraActual.before(nHoraHolgura)))
				flgEsHoraInicio = true;

			// Se evalua si la hora actual esta dentro del rango de inicio y
			// fin programado.

			if (estaDentroDeRango(nHoraActual, nHoraInicio, nHoraFin))
				flgPasoValidacion = true;
			else
				flgPasoValidacion = false;

			// En caso que la hora actual este fuera del rango se corta el
			// proceso.
			if (!flgPasoValidacion) {
				sysprintln = ">> La hora actual " + this.dFormatoHora.format(nHoraActual) + " es < a la hora inicio "
						+ this.dFormatoHora.format(nHoraInicio) + " o > a la hora fin "
						+ this.dFormatoHora.format(nHoraFin);
				// logger.error(sysprintln);

				// logger.error("Se corta proceso");
				return false;
			} else {
				sysprintln = ">> La hora actual " + this.dFormatoHora.format(nHoraActual) + " es >= a la hora inicio "
						+ this.dFormatoHora.format(nHoraInicio) + " y <= a la hora fin "
						+ this.dFormatoHora.format(nHoraFin);
				// logger.debug(sysprintln);
			}

			// ======================== SEGUNDA VALIDACION =====================

			// Se valida que la hora actual se encuentre en el minuto
			// o en la hora de la iteracion correspondiente.

			// 1 seg = 1000 milisegundos
			// 1 minuto = 60 000 milisegundos
			// 1 hora = 3 600 000 milisegundos

			// Si la hora actual es igual a la hora de inicio, entonces no se
			// realiza la comparacion entre la hora actual y la ultima hora
			// de ejecucion.

			if (flgEsHoraInicio) {

				// logger.debug(">> La hora actual es igual a la hora de inicio");

				// Si se trata de la hora de inicio, la hora de inicio
				// puede ser:
				// - La hora exacta de inicio
				// - La holgura con respecto a la hora exacta de inicio
				// De presentarse el segundo caso, se evalua la ultima hora de
				// ejecucion para saber esta en el rango de la hora exacta de
				// inicio y la hora de holgura

				if (this.fechaUltimaEjecucion != null) {

					Date nHoraUltimaEjecucion = obtenerSoloHora(this.fechaUltimaEjecucion);
					if (estaDentroDeRango(nHoraActual, nHoraUltimaEjecucion, nHoraHolgura)) {
						sysprintln = ">> RANGO: La hora actual " + this.dFormatoHora.format(nHoraActual) + " no es "
								+ "valida porque se encuentra en el rango de " + "la ultima hora de ejecucion "
								+ this.dFormatoHora.format(nHoraUltimaEjecucion) + " y la hora de holgura "
								+ this.dFormatoHora.format(nHoraHolgura);
						// logger.error(sysprintln);

						flgPasoValidacion = false;
					} else {
						sysprintln = ">> RANGO: La hora actual " + dFormatoHora.format(nHoraActual) + " es "
								+ "valida porque se encuentra fuera del rango de " + "la ultima hora de ejecucion "
								+ this.dFormatoHora.format(nHoraUltimaEjecucion) + " y la hora de holgura "
								+ this.dFormatoHora.format(nHoraHolgura);
						// logger.error(sysprintln);
						flgPasoValidacion = true;
					}
				} else
					flgPasoValidacion = true;

			} else {

				if (tipoIteracion.equals(TIPO_ITERACION_MINUTOS)) {

					// logger.debug("> El tipo de iteracion es MINUTOS");

					// Si la iteracion esta en minutos, entonces se busca que
					// la hora actual sea parte de la frecuencia de iteracion
					// configurado para la programacion.

					/*
					 * if(nHoraActual.getTime() - nHoraUltimaEjecucion.getTime() ==
					 * (iteracionFrecuencia.intValue() * 60 * 1000) ){
					 */

					if (seEncuentraEnTiempoIteracion(nHoraActual, nHoraInicio, iteracionFrecuencia.intValue())) {
						sysprintln = ">> La iteracion de tiempo " + iteracionFrecuencia + " es valida para la ("
								+ "HoraActual<" + this.dFormatoHora.format(nHoraActual) + "> y HoraInicio<"
								+ this.dFormatoHora.format(nHoraInicio) + ">)";
						// logger.debug(sysprintln);
						flgPasoValidacion = true;
					} else {
						sysprintln = ">> La iteracion de tiempo " + iteracionFrecuencia + " no es igual a ("
								+ "HoraActual<" + this.dFormatoHora.format(nHoraActual) + "> y HoraInicio<"
								+ this.dFormatoHora.format(nHoraInicio) + ">)";
						// logger.error(sysprintln);

						flgPasoValidacion = false;
					}

				} else if (tipoIteracion.equals(TIPO_ITERACION_HORAS)) {

					// logger.debug("> El tipo de iteracion es HORAS");

					// Se convierten las horas a minutos.
					long nTotalMinutos = iteracionFrecuencia.intValue() * 60;

					// Si la iteracion esta en horas, primero estas se pasan a
					// minutos y luego se busca verifica que la hora actual sea
					// parte de frecuencia de iteracion configurado para la
					// programacion.

					/*
					 * if(nHoraActual.getTime() - nHoraUltimaEjecucion.getTime() == nTotalMinutos){
					 */
					if (seEncuentraEnTiempoIteracion(nHoraActual, nHoraInicio, new Long(nTotalMinutos).intValue())) {
						sysprintln = ">> La hora actual es valida " + "porque se encuentra en una iteracion de cada "
								+ iteracionFrecuencia + " horas, HoraActual<" + dFormatoFechaHora.format(nHoraActual)
								+ ">, HoraInicio<" + dFormatoFechaHora.format(nHoraInicio) + ">";
						// logger.debug(sysprintln);

						flgPasoValidacion = true;

					} else {
						sysprintln = ">> La hora actual no es valida "
								+ "porque no se encuentra en una iteracion de cada " + iteracionFrecuencia
								+ " horas, HoraActual<" + dFormatoFechaHora.format(nHoraActual) + ">, HoraInicio<"
								+ dFormatoFechaHora.format(nHoraActual) + ">";
						// logger.error(sysprintln);

						flgPasoValidacion = false;

					}

				}
			}

		}

		return flgPasoValidacion;
	}

	/**
	 * Retorna TRUE cuando la fecha actual se encuentra dentro de una hora de
	 * iteracion valida. Por ejemplo: si la iteracion es cada 10 minutos, la hora de
	 * inicio es 10:00 y la hora actual es 10:30, entonces cumple la condicion
	 * porque la iteracion comprende las horas 10:10, 10:20 y 10:30. De no cumplirse
	 * este criterio, se retorna FALSE. Se debe tener en cuenta que a la iteracion
	 * se le agrega N minutos (donde <i>N</i> es el valor de la constante
	 * {@link #HOLGURA_MINUTOS}).
	 * 
	 * @param fechaActual
	 *            fecha actual cuya hora sera evaluada, tipo Date
	 * @param fechaInicio
	 *            fecha de inicio de la programacion, tipo Date
	 * @param minutos
	 *            cantidad de minutos de iteracion, tipo Integer
	 * @return TRUE o FALSE segun cumpla condiciones, tipo boolean
	 */
	@SuppressWarnings("deprecation")
	public boolean seEncuentraEnTiempoIteracion(Date fechaActual, Date fechaInicio, int minutos) throws Exception {

		boolean flgPertenece = false;

		// A la fecha actual se le suma la holgura para que el hilo pueda
		// entrar en el tiempo valido de ejecucion de la programacion
		Calendar gcFechaActual = new GregorianCalendar();
		gcFechaActual.setTimeInMillis(fechaActual.getTime());
		// gcFechaActual.add(Calendar.MINUTE, HOLGURA_MINUTOS);

		fechaActual = gcFechaActual.getTime();

		long resta1 = fechaActual.getTime() - fechaInicio.getTime();

		double transcurrido = resta1 / 1000 / 60;

		// Cantidad de minutos que se van a incrementar a la fecha actual
		int minutosxIncrementar = 0;

		// ========== PRIMERA VALIDACION ==========
		if (transcurrido % minutos <= 1) {

			flgPertenece = true;

			if (transcurrido % minutos == 1)
				minutosxIncrementar = 0;
		} else
			flgPertenece = false;

		if (flgPertenece) {
			sysprintln = "La fecha actual " + dFormatoFechaHora.format(fechaActual)
					+ " esta dentro de la iteracion (cada " + minutos + " minutos) y dentro de la holgura";
			// logger.debug(sysprintln);

			if (this.fechaUltimaEjecucion == null) {

				// Si la fecha de ultima ejecucion es nula, quiere decir que se
				// trata de la primera ejecucion de la programacion. Por ende,
				// la fecha es valida.
				sysprintln = "> La fecha de ultima ejecucion. " + "Entonces, se trata de la primera ejecucion";
				// logger.debug(sysprintln);
				// logger.error("> Se corta proceso.");

				return true;
			}

			// logger.debug("> La fecha de ultima ejecucion no esta vacia.");

			// ========== SEGUNDA VALIDACION ==========

			// Se pregunta si es que la ultima fecha de ejecucion coincide
			// la fecha actual. De ser asi, quiere decir que esta programacion
			// ya ha sido ejecutada y, por ende, no se realizara accion alguna.

			Date fechaActualOrig = new Date(this.fechaActual.getTime());
			fechaActualOrig.setSeconds(0);

			Date fechaUltimaEjecucion_ = null;

			fechaUltimaEjecucion_ = new Date(this.fechaUltimaEjecucion.getTime());
			fechaUltimaEjecucion_.setSeconds(0);

			Date fechaMaximaHolgura = new Date(this.fechaActual.getTime());

			Calendar gcFechaMaximaHolgura = new GregorianCalendar();
			gcFechaMaximaHolgura.setTimeInMillis(fechaMaximaHolgura.getTime());

			gcFechaMaximaHolgura.add(Calendar.MINUTE, minutosxIncrementar);
			gcFechaMaximaHolgura.set(Calendar.SECOND, 0);
			gcFechaMaximaHolgura.set(Calendar.MILLISECOND, 0);

			// Despues de haber agregado los minutos de holgura se formatea
			// para dejar la fecha solo con hora.

			fechaMaximaHolgura = gcFechaMaximaHolgura.getTime();

			// Cuando se tienen las tres fechas solo con horas se valida que:
			// FECHA_ACTUAL != FECHA_ULT_EJEC && FECHA_HOLGURA != FECHA_ACTUAL

			Calendar gcFechaInicio = new GregorianCalendar();
			gcFechaInicio.setTimeInMillis(fechaMaximaHolgura.getTime());

			// Si es que minutosxIncrementar es igual a cero, quiere decir que
			// la fecha de holgura es igual a la fecha de iteracion mas los
			// minutos de holgura. Por eso se restan los minutos de holgura para
			// obtener la fecha de inicio.
			if (minutosxIncrementar == 0) {

				gcFechaInicio.add(Calendar.MINUTE, -HOLGURA_MINUTOS);
			}
			Date fechaInicio_ = gcFechaInicio.getTime();

			if (estaDentroDeRango(fechaUltimaEjecucion_, fechaInicio_, fechaMaximaHolgura)) {
				sysprintln = ">> La fecha actual " + dFormatoFechaHora.format(fechaActualOrig)
						+ " es invalida porque la programacion ya fue " + "ejecutada en la fecha "
						+ dFormatoFechaHora.format(fechaUltimaEjecucion_) + " y se encuentra dentro de la holgura "
						+ dFormatoFechaHora.format(fechaMaximaHolgura);
				// logger.error(sysprintln);
				flgPertenece = false;

			} else {
				sysprintln = ">> La fecha actual " + dFormatoFechaHora.format(fechaActualOrig)
						+ " es valida porque no coincide con  " + "la fecha de ultima ejecucion "
						+ dFormatoFechaHora.format(fechaUltimaEjecucion_) + " y se encuentra dentro de la holgura "
						+ dFormatoFechaHora.format(fechaMaximaHolgura);
				// logger.error(sysprintln);
				flgPertenece = true;

			}
		} else {
			sysprintln = "La hora actual " + dFormatoHora.format(fechaActual) + " no esta dentro de la iteracion (cada "
					+ minutos + " minutos).";
			// logger.debug(sysprintln);
		}

		return flgPertenece;
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

		String sFecha = this.dFormatoFecha.format(fecha);

		return this.dFormatoFecha.parse(sFecha);

	}

	/**
	 * Retorna la objeto date solo parseado con hora.
	 * 
	 * @param fecha
	 *            fecha que se parseara, tipo Date
	 * @return objeto date solo con hora, tipo Date
	 * @throws Exception
	 *             excepcion general en caso de error, tipo Exception
	 */
	private Date obtenerSoloHora(Date fecha) throws Exception {

		String sFecha = this.dFormatoHora.format(fecha);

		return this.dFormatoHora.parse(sFecha);

	}

	/**
	 * Retorna TRUE cuando la fecha a evaluar se encuentra en el rango de fechas
	 * definido por los 2 ultimos parametros. La formula es <b> FECHA_RANGO_INICIO
	 * <= FECHA_EVALUAR <= FECHA_RANGO_FIN</b>. De cumplirse esta condicion, se
	 * retorna TRUE. Caso contrario, se retorna FALSE.
	 * 
	 * @param fechaAEvaluar
	 *            fecha que se evalua, tipo Date
	 * @param fechaRangoInicio
	 *            fecha de inicio del rango, tipo Date
	 * @param fechaRangoFin
	 *            fecha final del rango, tipo Date
	 * @return TRUE o FALSE segun cumpla condiciones, tipo boolean
	 */
	private boolean estaDentroDeRango(Date fechaAEvaluar, Date fechaRangoInicio, Date fechaRangoFin) {
		//System.out.println("fechaAEvaluar" + fechaAEvaluar);
		//System.out.println("fechaRangoInicio" + fechaRangoInicio);
		//System.out.println("fechaRangoFin" + fechaRangoFin);

		if ((fechaAEvaluar.equals(fechaRangoInicio) || fechaAEvaluar.after(fechaRangoInicio))
				&& (fechaAEvaluar.equals(fechaRangoFin) || fechaAEvaluar.before(fechaRangoFin)))
			return true;

		return false;
	}

	public String getSysprintln() {
		return sysprintln;
	}

	public void setSysprintln(String sysprintln) {
		this.sysprintln = sysprintln;
	}

	public static String getNoTieneFin() {
		return NO_TIENE_FIN;
	}

}
