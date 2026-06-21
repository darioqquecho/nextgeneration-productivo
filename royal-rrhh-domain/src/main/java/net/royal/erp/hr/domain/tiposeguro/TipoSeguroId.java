package net.royal.erp.hr.domain.tiposeguro;

public record TipoSeguroId(Integer value) {
	public TipoSeguroId {
		if (value == null) {
			throw new IllegalArgumentException("TipoSeguro es requerido");
		}
	}
}
