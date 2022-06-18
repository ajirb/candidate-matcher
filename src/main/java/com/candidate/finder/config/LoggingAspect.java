package com.candidate.finder.config;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.CodeSignature;
import org.aspectj.lang.reflect.MethodSignature;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestMapping;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

@Aspect
@Component
public class LoggingAspect {
	private static final Logger logger = LoggerFactory.getLogger(LoggingAspect.class);

	@Autowired
	private ObjectMapper mapper;

	@Pointcut("within(com.candidate.finder.controller..*) "
			+ "&& @annotation(org.springframework.web.bind.annotation.RequestMapping)")
	public void pointcut() {
	}

	@Before("pointcut()")
	public void logMethod(JoinPoint joinPoint) {
		MethodSignature signature = (MethodSignature) joinPoint.getSignature();
		RequestMapping mapping = signature.getMethod().getAnnotation(RequestMapping.class);

		Map<String, Object> parameters = getParameters(joinPoint);

		try {
			logger.info("==> path(s): {}, method(s): {}, arguments: {} ", mapping.path(), mapping.method(),
					mapper.writeValueAsString(parameters));
		} catch (JsonProcessingException e) {
			logger.error("Error while converting", e);
		}
	}

	@AfterReturning(pointcut = "pointcut()", returning = "entity")
	public void logMethodAfter(JoinPoint joinPoint, ResponseEntity<?> entity) {
		MethodSignature signature = (MethodSignature) joinPoint.getSignature();
		RequestMapping mapping = signature.getMethod().getAnnotation(RequestMapping.class);

		try {
			logger.info("<== path(s): {}, method(s): {}, retuning: {}", mapping.path(), mapping.method(),
					mapper.writeValueAsString(entity));
		} catch (JsonProcessingException e) {
			logger.error("Error while converting", e);
		}
	}

	@Pointcut("within(@org.springframework.stereotype.Repository *)"
			+ " || within(@org.springframework.stereotype.Service *)"
			+ " || within(@org.springframework.web.bind.annotation.RestController *)")
	public void springBeanPointcut() {
		throw new UnsupportedOperationException();
	}

	@Pointcut("within(com.candidate.finder.repository..*)" + " || within(com.candidate.finder.service..*)")
	public void applicationPackagePointcut() {
		throw new UnsupportedOperationException();
	}

	@AfterThrowing(pointcut = "applicationPackagePointcut() && springBeanPointcut()", throwing = "e")
	public void logAfterThrowing(JoinPoint joinPoint, Throwable e) {
		logger.error("Exception in {}.{}() with cause = {}", joinPoint.getSignature().getDeclaringTypeName(),
				joinPoint.getSignature().getName(), e.getCause() != null ? e.getMessage() : "NULL");
	}

	@Around("applicationPackagePointcut() && springBeanPointcut()")
	public Object invoke(ProceedingJoinPoint joinPoint) throws Throwable {
		final String joinPoints = Arrays.toString(joinPoint.getArgs());
		if (joinPoints != null) {
			logger.info("Enter: {}.{}() with argument[s] = {}", joinPoint.getSignature().getDeclaringTypeName(),
					joinPoint.getSignature().getName(), joinPoints);
		}
		final Object result = joinPoint.proceed();
		logger.info("Exit: {}.{}() with result = {}", joinPoint.getSignature().getDeclaringTypeName(),
				joinPoint.getSignature().getName(), result);
		return result;
	}

	private Map<String, Object> getParameters(JoinPoint joinPoint) {
		CodeSignature signature = (CodeSignature) joinPoint.getSignature();

		Map<String, Object> map = new HashMap<>();

		String[] parameterNames = signature.getParameterNames();

		for (int i = 0; i < parameterNames.length; i++) {
			map.put(parameterNames[i], joinPoint.getArgs()[i]);
		}

		return map;
	}

}
