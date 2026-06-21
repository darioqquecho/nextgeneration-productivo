package net.royal.erp.framework.application;

import java.lang.reflect.Method;

import org.aopalliance.intercept.MethodInterceptor;
import org.springframework.aop.framework.ProxyFactory;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanPostProcessor;
import org.springframework.core.Ordered;
import org.springframework.core.PriorityOrdered;
import org.springframework.stereotype.Component;

import net.royal.erp.framework.kernel.FunctionalContext;
import net.royal.erp.framework.observability.ExecutionTimer;
import net.royal.erp.framework.observability.ObservabilityPort;

/**
 * Observabilidad global para casos de uso funcionales.
 */
@Component
public class RoyalUseCaseObservabilityPostProcessor implements BeanPostProcessor, PriorityOrdered {
	private final ObservabilityPort observability;

	public RoyalUseCaseObservabilityPostProcessor(ObservabilityPort observability) {
		this.observability = observability;
	}

	@Override
	public Object postProcessAfterInitialization(Object bean, String beanName) throws BeansException {
		if (!(bean instanceof RoyalBaseUseCase) || bean instanceof RoyalUseCaseObservabilityProxy) {
			return bean;
		}
		ProxyFactory factory = new ProxyFactory(bean);
		factory.setProxyTargetClass(true);
		factory.addInterface(RoyalUseCaseObservabilityProxy.class);
		factory.addAdvice((MethodInterceptor) invocation -> {
			FunctionalContext context = contextFrom(invocation.getArguments());
			if (context == null || !isFunctionalMethod(invocation.getMethod())) {
				return invocation.proceed();
			}
			ExecutionTimer timer = ExecutionTimer.start();
			try {
				Object result = invocation.proceed();
				observability.succeeded(context, timer.stopMillis());
				return result;
			} catch (RuntimeException e) {
				observability.failed(context, timer.stopMillis(), e);
				throw e;
			}
		});
		return factory.getProxy();
	}

	@Override
	public int getOrder() {
		return Ordered.LOWEST_PRECEDENCE;
	}

	private boolean isFunctionalMethod(Method method) {
		return method.getDeclaringClass() != Object.class;
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

	private interface RoyalUseCaseObservabilityProxy {
	}
}
