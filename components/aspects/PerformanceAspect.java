package com.project.shopapp.components.aspects;

import com.project.shopapp.Controllers.UserController;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import java.util.concurrent.TimeUnit;
import java.util.logging.Logger;

@Component
@Aspect
@Slf4j
public class PerformanceAspect {
    private java.util.logging.Logger logger = Logger.getLogger(getClass().getName());

    @Pointcut("within(com.project.shopapp.controllers.*)")
    public void controllerMethods(){}

    @Before("controllerMethods()")
    public void beforeMethodExecution(JoinPoint joinPoint) {
        String methodName = joinPoint.getSignature().getName();
        logger.info("Starting execution"+ methodName);
    }

    @After("controllerMethods()")
    public void afterMethodExecution(JoinPoint joinPoint) {
        String methodName = joinPoint.getSignature().getName();
        logger.info("Starting execution"+ methodName);
    }

    @Around("controllerMethods()")
    public Object measureMethodExecution(ProceedingJoinPoint proceedingJoinPoint) throws Throwable {
        Long start = System.nanoTime();
        Object returnValue = proceedingJoinPoint.proceed();
        long end = System.nanoTime();
        String methodName = proceedingJoinPoint.getSignature().getName();
        logger.info("Execution of "+ methodName + "took" +
                TimeUnit.NANOSECONDS.toMillis(end - start) + "ms");
        return returnValue;

    }



}
