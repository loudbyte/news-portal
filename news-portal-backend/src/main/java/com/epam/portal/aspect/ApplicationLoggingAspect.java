package com.epam.portal.aspect;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class ApplicationLoggingAspect {

    private static final Logger LOGGER = LoggerFactory.getLogger(ApplicationLoggingAspect.class);

    @Pointcut("execution(* com.epam.portal.config.*.*(..))")
    private void forConfigPackage() {}

    @Pointcut("execution(* com.epam.portal.controller.*.*(..))")
    private void forControllerPackage() {}

    @Pointcut("execution(* com.epam.portal.dto.*.*(..))")
    private void forDTOPackage() {}

    @Pointcut("execution(* com.epam.portal.entity.*.*(..))")
    private void forEntityPackage() {}

    @Pointcut("execution(* com.epam.portal.repository.*.*(..))")
    private void forRepositoryPackage() {}

    @Pointcut("execution(* com.epam.portal.service.*.*(..))")
    private void forServicePackage() {}

    @Pointcut("forConfigPackage() || forControllerPackage() || forDTOPackage() " +
                    "|| forEntityPackage() || forRepositoryPackage() || forServicePackage()")
    private void forAppFlow() {}


    @Before("forAppFlow()")
    private void before(JoinPoint joinPoint) {
        String methodSignature = joinPoint.getSignature().toShortString();
        String className = joinPoint.getTarget().getClass().getName();

        StringBuilder stringArgList = new StringBuilder();
        Object[] args = joinPoint.getArgs();
        for (Object tempArg: args) {
            stringArgList.append(" ").append(tempArg);
        }

        LOGGER.info("Method invoked: " + className + " : " + methodSignature + " arguments : " + stringArgList);
    }

    @After("forAppFlow()")
    private void after(JoinPoint joinPoint) {
        String methodSignature = joinPoint.getSignature().toShortString();
        LOGGER.info("Method executed: " + methodSignature);
    }

    @AfterReturning(pointcut = "forAppFlow()", returning = "result")
    private void afterReturning(JoinPoint joinPoint, Object result) {
        String methodSignature = joinPoint.getSignature().toShortString();
        String shortResult = result == null ? "null" : result.getClass().getName();
        LOGGER.info("   Returned: " + shortResult + " from method: " + methodSignature);
    }

    @AfterThrowing(pointcut = "forAppFlow()", throwing = "error")
    private void afterThrowing(JoinPoint joinPoint, Throwable error) {
        String methodSignature = joinPoint.getSignature().toShortString();
        LOGGER.error("Method " + methodSignature + " threw exception: " + error);
    }
}
