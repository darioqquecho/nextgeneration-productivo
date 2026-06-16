package net.royal.spring.alertas.dominio.dto;

public class BProgramacionDiaria extends BProgramacionBase {
	private Integer cadaNDias;

	/**
	 * variable que almacena cada cuantos dias debe de ejecutarse la regla. </br>
	 * <b>tabla</b> SGALERTASSYS.REGL_NEGO_EJEC <b>campo</b> NU_DIAS
	 */
	public Integer getCadaNDias() {
		return cadaNDias;
	}

	/**
	 * variable que almacena cada cuantos dias debe de ejecutarse la regla. </br>
	 * <b>tabla</b> SGALERTASSYS.REGL_NEGO_EJEC <b>campo</b> NU_DIAS
	 */
	public void setCadaNDias(Integer cadaNDias) {
		this.cadaNDias = cadaNDias;
	}

}
