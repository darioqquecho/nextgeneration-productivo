package net.royal.erp.framework.application;

/**
 * Clase maestra para DTOs de application/API.
 */
public abstract class RoyalBaseDto {
	private String traceId;

	protected RoyalBaseDto() {
	}

	protected RoyalBaseDto(String traceId) {
		this.traceId = traceId;
	}

	public String traceId() {
		return traceId;
	}

	public void traceId(String traceId) {
		this.traceId = traceId;
	}
}
