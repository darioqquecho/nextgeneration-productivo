package net.royal.erp.framework.application;

import java.util.EnumMap;
import java.util.Map;

import net.royal.erp.framework.kernel.BusinessException;
import net.royal.erp.framework.kernel.FunctionalContext;
import net.royal.erp.framework.versioning.FunctionalVersion;
import net.royal.erp.framework.versioning.FunctionalVersionResolver;

/**
 * Versionador funcional generico para un modulo/API.
 */
public class ModuleVersionRouter<T> {
	private final FunctionalVersionResolver resolver;
	private final Map<FunctionalVersion, T> versions = new EnumMap<>(FunctionalVersion.class);

	public ModuleVersionRouter(FunctionalVersionResolver resolver) {
		this.resolver = resolver;
	}

	public ModuleVersionRouter<T> register(FunctionalVersion version, T useCase) {
		versions.put(version, useCase);
		return this;
	}

	public T delegate(FunctionalContext context) {
		FunctionalVersion version = resolver.resolve(context);
		T useCase = versions.get(version);
		if (useCase == null) {
			throw new BusinessException("VERSION-NOT-SUPPORTED", version.name());
		}
		return useCase;
	}
}
