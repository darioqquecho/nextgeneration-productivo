package net.royal.erp.framework.application;

import java.lang.reflect.Method;
import java.lang.reflect.InvocationTargetException;
import java.util.EnumMap;
import java.util.Map;

import org.aopalliance.intercept.MethodInterceptor;
import org.springframework.aop.framework.ProxyFactory;

import net.royal.erp.framework.kernel.BusinessException;
import net.royal.erp.framework.kernel.FrameworkBusinessErrorCodes;
import net.royal.erp.framework.kernel.FunctionalContext;
import net.royal.erp.framework.versioning.FunctionalVersion;
import net.royal.erp.framework.versioning.FunctionalVersionResolver;

/**
 * Fabrica global para exponer un unico bean que enruta a implementaciones V1/V2.
 */
public final class RoyalFunctionalVersionProxyFactory {
	private RoyalFunctionalVersionProxyFactory() {
	}

	public static <T> Builder<T> builder(Class<T> type, FunctionalVersionResolver resolver) {
		return new Builder<>(type, resolver);
	}

	public static final class Builder<T> {
		private final Class<T> type;
		private final FunctionalVersionResolver resolver;
		private final Map<FunctionalVersion, T> versions = new EnumMap<>(FunctionalVersion.class);

		private Builder(Class<T> type, FunctionalVersionResolver resolver) {
			this.type = type;
			this.resolver = resolver;
		}

		public Builder<T> register(FunctionalVersion version, T useCase) {
			versions.put(version, useCase);
			return this;
		}

		public T build() {
			if (versions.isEmpty()) {
				throw new IllegalStateException("Debe registrarse al menos una version para " + type.getName());
			}
			ProxyFactory factory = new ProxyFactory();
			factory.setTargetClass(type);
			factory.setProxyTargetClass(true);
			factory.addAdvice((MethodInterceptor) invocation -> {
				Object target = targetFor(invocation.getArguments());
				Method method = invocation.getMethod();
				try {
					return method.invoke(target, invocation.getArguments());
				} catch (InvocationTargetException ex) {
					Throwable cause = ex.getCause();
					if (cause instanceof RuntimeException runtimeException) {
						throw runtimeException;
					}
					if (cause instanceof Error error) {
						throw error;
					}
					throw new IllegalStateException(cause);
				}
			});
			return type.cast(factory.getProxy());
		}

		private T targetFor(Object[] args) {
			FunctionalContext context = contextFrom(args);
			FunctionalVersion version = context == null ? FunctionalVersion.V1 : resolver.resolve(context);
			T useCase = versions.get(version);
			if (useCase == null) {
				throw new BusinessException(FrameworkBusinessErrorCodes.VERSION_NOT_SUPPORTED, version.name());
			}
			return useCase;
		}

		private FunctionalContext contextFrom(Object[] args) {
			if (args == null) {
				return null;
			}
			for (Object arg : args) {
				if (arg instanceof FunctionalContext context) {
					return context;
				}
			}
			return null;
		}
	}
}
