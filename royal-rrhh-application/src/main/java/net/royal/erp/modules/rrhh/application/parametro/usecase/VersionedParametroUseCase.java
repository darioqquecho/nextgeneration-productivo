package net.royal.erp.modules.rrhh.application.parametro.usecase;

import net.royal.erp.framework.kernel.*;
import net.royal.erp.framework.versioning.*;

abstract class VersionedParametroUseCase<C, R> implements UseCase<C, R> {
	private final FunctionalVersionResolver resolver;
	private final UseCase<C, R> v1;
	private final UseCase<C, R> v2;

	VersionedParametroUseCase(FunctionalVersionResolver resolver, UseCase<C, R> v1, UseCase<C, R> v2) {
		this.resolver = resolver;
		this.v1 = v1;
		this.v2 = v2;
	}

	public R execute(C command, FunctionalContext context) {
		return resolver.resolve(context) == FunctionalVersion.V2 ? v2.execute(command, context)
				: v1.execute(command, context);
	}
}
