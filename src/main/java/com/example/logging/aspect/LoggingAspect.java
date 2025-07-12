package com.example.logging.aspect;

import com.example.logging.dto.*;
import com.example.logging.service.LogFacade;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.List;

@Aspect
@Component
public class LoggingAspect {

    private final LogFacade logFacade;

    public LoggingAspect(LogFacade logFacade) {
        this.logFacade = logFacade;
    }

    @Around("@annotation(com.example.logging.annotation.Log)")
    public Object logMethodInvocation(ProceedingJoinPoint joinPoint) throws Throwable {
        String className = joinPoint.getTarget().getClass().getSimpleName();
        String methodName = joinPoint.getSignature().getName();
        List<Object> args = Arrays.asList(joinPoint.getArgs());

        logFacade.logInvocation(new InvocationLogDto(className, methodName, args));

        return joinPoint.proceed();
    }

    @AfterReturning(pointcut = "execution(* com.example.logging.service.ClockService.getNextDate(..))", returning = "result")
    public void logReturnValue(JoinPoint joinPoint, Object result) {
        String className = joinPoint.getTarget().getClass().getSimpleName();
        String methodName = joinPoint.getSignature().getName();

        logFacade.logReturnValue(new ReturnLogDto(className, methodName, result));
    }

    @AfterThrowing(pointcut = "execution(* com.example.logging.service.ClockService.getNextDate(..))", throwing = "ex")
    public void logException(JoinPoint joinPoint, Throwable ex) {
        String className = joinPoint.getTarget().getClass().getSimpleName();
        String methodName = joinPoint.getSignature().getName();

        logFacade.logThrownException(new ExceptionLogDto(className, methodName, ex));
    }

    @Before("execution(* com.codility.aop.date.DateRepository.save(..)) || " +
            "execution(* com.codility.aop.calendar.MeetingRepository.save(..))")
    public void logSavedEntities(JoinPoint joinPoint) {
        String className = joinPoint.getTarget().getClass().getSimpleName();
        Object entity = joinPoint.getArgs()[0];

        logFacade.logEntitySave(new EntitySaveLogDto(className, entity));
    }

    @Around("execution(* com.codility.aop.database.DatabaseConnectivity.save(..))")
    public Object logExecutionTime(ProceedingJoinPoint joinPoint) throws Throwable {
        String className = joinPoint.getTarget().getClass().getSimpleName();
        Object entity = joinPoint.getArgs()[0];
        long start = System.currentTimeMillis();

        try {
            Object result = joinPoint.proceed();
            long end = System.currentTimeMillis();
            logFacade.logEntitySavingTime(new EntitySaveTimeLogDto(className, entity, end - start));
            return result;
        } catch (Throwable ex) {
            throw ex; // No logging on exception
        }
    }
}
