package net.royal.erp.modules.hr.domain.tiposeguro;

public record TipoSeguroId(Integer value) {
	public TipoSeguroId {
		if (value == null) {
			throw new IllegalArgumentException("TipoSeguro es requerido");
		}
	}
}
