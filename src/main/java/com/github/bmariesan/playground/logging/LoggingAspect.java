package com.github.bmariesan.playground.logging;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.Signature;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.function.Supplier;

@Aspect
@Component
public class LoggingAspect {

    private static final Logger logger = LoggerFactory.getLogger(LoggingAspect.class);

    @Pointcut("within(com.github.bmariesan.playground.resource..*) || " +
            "within(com.github.bmariesan.playground.service..*)")
    public void loggingPointcut() {
        // method created to define the pointcut
    }

    @Around("loggingPointcut()")
    public Object logAround(ProceedingJoinPoint joinPoint) throws Throwable {
        long startTime = System.currentTimeMillis();
        Signature signature = joinPoint.getSignature();
        Method method = ((MethodSignature) signature).getMethod();

        logDebugMessageLazy(() -> logMethodSignature(joinPoint, signature));

        // proceed with the call
        Object result = joinPoint.proceed();

        long durationTime = System.currentTimeMillis() - startTime;

        // Suppliers need effectively final objects, thus a StringBuilder is preferred to using a String
        StringBuilder logResult = new StringBuilder();
        if (!method.getReturnType().equals(Void.TYPE)) {
            logResult.append(result.toString());
        }

        logDebugMessageLazy(() -> logMethodResult(joinPoint, signature, durationTime, logResult.toString()));

        return result;
    }

    private void logDebugMessageLazy(Supplier<String> message) {
        if (logger.isDebugEnabled()) {
            logger.debug(message.get());
        }
    }

    private String logMethodResult(ProceedingJoinPoint joinPoint, Signature signature, long durationTime, String logResult) {
        return String.format("Exit: %s.%s() took [%s] ms for target class %s %s",
                signature.getDeclaringType().getSimpleName(),
                signature.getName(),
                durationTime,
                joinPoint.getTarget().getClass().getSimpleName(),
                logResult);
    }

    private String logMethodSignature(ProceedingJoinPoint joinPoint, Signature signature) {
        return String.format("Enter: %s.%s() for target class %s with argument[s] = %s",
                signature.getDeclaringType().getSimpleName(),
                signature.getName(),
                joinPoint.getTarget().getClass().getSimpleName(),
                Arrays.toString(joinPoint.getArgs()));
    }

}
