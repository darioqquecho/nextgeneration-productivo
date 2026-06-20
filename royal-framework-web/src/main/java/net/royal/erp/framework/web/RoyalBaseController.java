package net.royal.erp.framework.web;

import jakarta.servlet.http.HttpServletRequest;
import net.royal.erp.framework.kernel.FunctionalContext;

/**
 * Clase maestra para controllers REST.
 */
public abstract class RoyalBaseController {
	private final FunctionalContextFactory contextFactory;
	private final String module;
	private final String process;
	private final String useCase;

	protected RoyalBaseController(FunctionalContextFactory contextFactory, String module, String process,
			String useCase) {
		this.contextFactory = contextFactory;
		this.module = module;
		this.process = process;
		this.useCase = useCase;
	}

	protected FunctionalContext context(HttpServletRequest request, String functionality) {
		return contextFactory.from(request, module, process, functionality, useCase);
	}
}
