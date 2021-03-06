package com.github.bmariesan.playground.logging;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.Signature;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.stereotype.Component;

import java.lang.reflect.Method;
import java.util.Arrays;

@Aspect
@Component
public class LoggingAspect {


    private final LoggerService logger;

    public LoggingAspect(LoggerService loggerService) {
        this.logger = loggerService;
    }

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

        logger.debug(() -> logMethodSignature(joinPoint, signature));

        // proceed with the call
        Object result = joinPoint.proceed();

        long durationTime = System.currentTimeMillis() - startTime;

        // Suppliers need effectively final objects, thus a StringBuilder is preferred to using a String
        StringBuilder logResult = new StringBuilder();
        if (!method.getReturnType().equals(Void.TYPE)) {
            logResult.append(result.toString());
        }

        logger.debug(() -> logMethodResult(joinPoint, signature, durationTime, logResult.toString()));

        return result;
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
