package net.royal.spring.alertas.dominio.dto;

public class BProgramacionSemanal extends BProgramacionBase {
	private Integer cadaNSemanas;
	private boolean flagEjecutarLunes;
	private boolean flagEjecutarMartes;
	private boolean flagEjecutarMiercoles;
	private boolean flagEjecutarJueves;
	private boolean flagEjecutarViernes;
	private boolean flagEjecutarSabado;
	private boolean flagEjecutarDomingo;

	/**
	 * ejecutar regla cada N Semanas, se guarda la N.</br>
	 * <b>tabla</b>SGALERTASSYS.REGL_NEGO_EJEC <b>campo</b>NU_SEMA
	 */
	public Integer getCadaNSemanas() {
		return cadaNSemanas;
	}

	/**
	 * ejecutar regla cada N Semanas, se guarda la N.</br>
	 * <b>tabla</b>SGALERTASSYS.REGL_NEGO_EJEC <b>campo</b>NU_SEMA
	 */
	public void setCadaNSemanas(Integer cadaNSemanas) {
		this.cadaNSemanas = cadaNSemanas;
	}

	/**
	 * flag si el dia se debe ejecutar la regla.</br>
	 * <b>tabla</b>SGALERTASSYS.REGL_NEGO_EJEC <b>campo</b>TI_FLAG_LUNE - S=True
	 * N=False
	 */
	public boolean isFlagEjecutarLunes() {
		return flagEjecutarLunes;
	}

	/**
	 * flag si el dia se debe ejecutar la regla.</br>
	 * <b>tabla</b>SGALERTASSYS.REGL_NEGO_EJEC <b>campo</b>TI_FLAG_LUNE - S=True
	 * N=False
	 */
	public void setFlagEjecutarLunes(boolean flagEjecutarLunes) {
		this.flagEjecutarLunes = flagEjecutarLunes;
	}

	/**
	 * flag si el dia se debe ejecutar la regla.</br>
	 * <b>tabla</b>SGALERTASSYS.REGL_NEGO_EJEC <b>campo</b>TI_FLAG_MART - S=True
	 * N=False
	 */
	public boolean isFlagEjecutarMartes() {
		return flagEjecutarMartes;
	}

	/**
	 * flag si el dia se debe ejecutar la regla.</br>
	 * <b>tabla</b>SGALERTASSYS.REGL_NEGO_EJEC <b>campo</b>TI_FLAG_MART - S=True
	 * N=False
	 */
	public void setFlagEjecutarMartes(boolean flagEjecutarMartes) {
		this.flagEjecutarMartes = flagEjecutarMartes;
	}

	/**
	 * flag si el dia se debe ejecutar la regla.</br>
	 * <b>tabla</b>SGALERTASSYS.REGL_NEGO_EJEC <b>campo</b>TI_FLAG_MIER - S=True
	 * N=False
	 */
	public boolean isFlagEjecutarMiercoles() {
		return flagEjecutarMiercoles;
	}

	/**
	 * flag si el dia se debe ejecutar la regla.</br>
	 * <b>tabla</b>SGALERTASSYS.REGL_NEGO_EJEC <b>campo</b>TI_FLAG_MIER - S=True
	 * N=False
	 */
	public void setFlagEjecutarMiercoles(boolean flagEjecutarMiercoles) {
		this.flagEjecutarMiercoles = flagEjecutarMiercoles;
	}

	/**
	 * flag si el dia se debe ejecutar la regla.</br>
	 * <b>tabla</b>SGALERTASSYS.REGL_NEGO_EJEC <b>campo</b>TI_FLAG_JUEV - S=True
	 * N=False
	 */
	public boolean isFlagEjecutarJueves() {
		return flagEjecutarJueves;
	}

	/**
	 * flag si el dia se debe ejecutar la regla.</br>
	 * <b>tabla</b>SGALERTASSYS.REGL_NEGO_EJEC <b>campo</b>TI_FLAG_JUEV - S=True
	 * N=False
	 */
	public void setFlagEjecutarJueves(boolean flagEjecutarJueves) {
		this.flagEjecutarJueves = flagEjecutarJueves;
	}

	/**
	 * flag si el dia se debe ejecutar la regla.</br>
	 * <b>tabla</b>SGALERTASSYS.REGL_NEGO_EJEC <b>campo</b>TI_FLAG_VIER - S=True
	 * N=False
	 */
	public boolean isFlagEjecutarViernes() {
		return flagEjecutarViernes;
	}

	/**
	 * flag si el dia se debe ejecutar la regla.</br>
	 * <b>tabla</b>SGALERTASSYS.REGL_NEGO_EJEC <b>campo</b>TI_FLAG_VIER - S=True
	 * N=False
	 */
	public void setFlagEjecutarViernes(boolean flagEjecutarViernes) {
		this.flagEjecutarViernes = flagEjecutarViernes;
	}

	/**
	 * flag si el dia se debe ejecutar la regla.</br>
	 * <b>tabla</b>SGALERTASSYS.REGL_NEGO_EJEC <b>campo</b>TI_FLAG_SABA - S=True
	 * N=False
	 */
	public boolean isFlagEjecutarSabado() {
		return flagEjecutarSabado;
	}

	/**
	 * flag si el dia se debe ejecutar la regla.</br>
	 * <b>tabla</b>SGALERTASSYS.REGL_NEGO_EJEC <b>campo</b>TI_FLAG_SABA - S=True
	 * N=False
	 */
	public void setFlagEjecutarSabado(boolean flagEjecutarSabado) {
		this.flagEjecutarSabado = flagEjecutarSabado;
	}

	/**
	 * flag si el dia se debe ejecutar la regla.</br>
	 * <b>tabla</b>SGALERTASSYS.REGL_NEGO_EJEC <b>campo</b>TI_FLAG_DOMI - S=True
	 * N=False
	 */
	public boolean isFlagEjecutarDomingo() {
		return flagEjecutarDomingo;
	}

	/**
	 * flag si el dia se debe ejecutar la regla.</br>
	 * <b>tabla</b>SGALERTASSYS.REGL_NEGO_EJEC <b>campo</b>TI_FLAG_DOMI - S=True
	 * N=False
	 */
	public void setFlagEjecutarDomingo(boolean flagEjecutarDomingo) {
		this.flagEjecutarDomingo = flagEjecutarDomingo;
	}

}
