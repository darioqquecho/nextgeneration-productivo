package net.royal.spring.alertas.dominio.dto;

public class BProgramacionMensual extends BProgramacionBase {

	private String tipoFrecuenciaEjecucionMensual;
	private Integer frecuenciaDia_Dia;
	private Integer frecuenciaDia_CadaN;
	private String frecuenciaNombreDia_OrdenDiaSemana;
	private String frecuenciaNombreDia_DiaSemana;
	private Integer frecuenciaNombreDia_CadaN;

	/**
	 * Indica cual es la frecuencia de ejecucion en el mes: <br/>
	 * <br/>
	 * F = (DE)dia especifico, cada N Meses<br/>
	 * O = (ND) dia semana, cada N Meses<br/>
	 * <br/>
	 * Tabla: <b>SGALERTASSYS.REGL_NEGO_EJEC</b><br/>
	 * Campo: <b>TI_MES</b> </br><b>**</b> se cambio la regla para que soporte
	 * los valores DIA,DSE en lugar de F,O
	 */
	public String getTipoFrecuenciaEjecucionMensual() {
		return tipoFrecuenciaEjecucionMensual;
	}

	/**
	 * Indica cual es la frecuencia de ejecucion en el mes: <br/>
	 * <br/>
	 * F = (DE)dia especifico, cada N Meses<br/>
	 * O = (ND) dia semana, cada N Meses<br/>
	 * <br/>
	 * Tabla: <b>SGALERTASSYS.REGL_NEGO_EJEC</b><br/>
	 * Campo: <b>TI_MES</b></br><b>**</b> se cambio la regla para que soporte
	 * los valores DIA,DSE en lugar de F,O
	 */
	public void setTipoFrecuenciaEjecucionMensual(
			String tipoFrecuenciaEjecucionMensual) {
		this.tipoFrecuenciaEjecucionMensual = tipoFrecuenciaEjecucionMensual;
	}

	/**
	 * si el tipo de frecuencia es Diario, se almacena el dia que se debe
	 * ejecutar.<br>
	 * <b>tabla</b>SGALERTASSYS.REGL_NEGO_EJEC <b>campo</b>NU_DIA_FREC_MES
	 */
	public Integer getFrecuenciaDia_Dia() {
		return frecuenciaDia_Dia;
	}

	/**
	 * si el tipo de frecuencia es Diario, se almacena el dia que se debe
	 * ejecutar.<br>
	 * <b>tabla</b>SGALERTASSYS.REGL_NEGO_EJEC <b>campo</b>NU_DIA_FREC_MES
	 */
	public void setFrecuenciaDia_Dia(Integer frecuenciaDiaDia) {
		this.frecuenciaDia_Dia = frecuenciaDiaDia;
	}

	/**
	 * si el tipo de frecuencia es Diario, se almacena cada cuantos meses se
	 * ejecuta.</br> <b>tabla</b>SGALERTASSYS.REGL_NEGO_EJEC
	 * <b>campo</b>NU_FREC_MES
	 */
	public Integer getFrecuenciaDia_CadaN() {
		return frecuenciaDia_CadaN;
	}

	/**
	 * si el tipo de frecuencia es Diario, se almacena cada cuantos meses se
	 * ejecuta.</br> <b>tabla</b>SGALERTASSYS.REGL_NEGO_EJEC
	 * <b>campo</b>NU_FREC_MES
	 */
	public void setFrecuenciaDia_CadaN(Integer frecuenciaDiaCadaN) {
		this.frecuenciaDia_CadaN = frecuenciaDiaCadaN;
	}

	/**
	 * si el tipo de frecuencia es Nombre de dia, se almacena cada cuantos meses
	 * se ejecuta.</br> 1 = Primer</br> 2 = Segundo</br> 3 = Tercer</br> 4 =
	 * Cuarto</br> 5 = Ultimo</br> <b>tabla</b>SGALERTASSYS.REGL_NEGO_EJEC
	 * <b>campo</b>TI_ORDI
	 */
	public String getFrecuenciaNombreDia_OrdenDiaSemana() {
		return frecuenciaNombreDia_OrdenDiaSemana;
	}

	/**
	 * si el tipo de frecuencia es Nombre de dia, se almacena cada cuantos meses
	 * se ejecuta.</br> 1 = Primer</br> 2 = Segundo</br> 3 = Tercer</br> 4 =
	 * Cuarto</br> 5 = Ultimo</br> <b>tabla</b>SGALERTASSYS.REGL_NEGO_EJEC
	 * <b>campo</b>TI_ORDI
	 */
	public void setFrecuenciaNombreDia_OrdenDiaSemana(
			String frecuenciaNombreDiaOrdenDiaSemana) {
		this.frecuenciaNombreDia_OrdenDiaSemana = frecuenciaNombreDiaOrdenDiaSemana;
	}

	/**
	 * si el tipo de frecuencia es Nombre de dia, se almacena el nombre del dia
	 * de semana.</br> 1 = Lunes</br> 2 = Martes</br> 3 = Miercoles</br> 4 =
	 * Jueves</br> 5 = Viernes</br> 6 = Sabado</br> 7 = Domingo</br> 8 = Dia,
	 * indica que el primero que se encuentre</br> 9 = Dia de la semana</br>
	 * <b>tabla</b>SGALERTASSYS.REGL_NEGO_EJEC <b>campo</b>TI_DIA_ORDI
	 */
	public String getFrecuenciaNombreDia_DiaSemana() {
		return frecuenciaNombreDia_DiaSemana;
	}

	/**
	 * si el tipo de frecuencia es Nombre de dia, se almacena el nombre del dia
	 * de semana.</br> 1 = Lunes</br> 2 = Martes</br> 3 = Miercoles</br> 4 =
	 * Jueves</br> 5 = Viernes</br> 6 = Sabado</br> 7 = Domingo</br> 8 = Dia,
	 * indica que el primero que se encuentre</br> 9 = Dia de la semana</br>
	 * <b>tabla</b>SGALERTASSYS.REGL_NEGO_EJEC <b>campo</b>TI_DIA_ORDI
	 */
	public void setFrecuenciaNombreDia_DiaSemana(
			String frecuenciaNombreDiaDiaSemana) {
		this.frecuenciaNombreDia_DiaSemana = frecuenciaNombreDiaDiaSemana;
	}

	/**
	 * si el tipo de frecuencia es Nombre de dia, se almacena cada cuantos meses
	 * se ejecuta.</br> <b>tabla</b>SGALERTASSYS.REGL_NEGO_EJEC
	 * <b>campo</b>NU_ORDI_MES
	 */
	public Integer getFrecuenciaNombreDia_CadaN() {
		return frecuenciaNombreDia_CadaN;
	}

	/**
	 * si el tipo de frecuencia es Nombre de dia, se almacena cada cuantos meses
	 * se ejecuta.</br> <b>tabla</b>SGALERTASSYS.REGL_NEGO_EJEC
	 * <b>campo</b>NU_ORDI_MES
	 */
	public void setFrecuenciaNombreDia_CadaN(Integer frecuenciaNombreDiaCadaN) {
		this.frecuenciaNombreDia_CadaN = frecuenciaNombreDiaCadaN;
	}
}
