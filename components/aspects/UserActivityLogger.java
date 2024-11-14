package com.project.shopapp.components.aspects;

import com.project.shopapp.Controllers.UserController;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

@Component
@Aspect
@Slf4j
public class UserActivityLogger {
    // name pointcut
    private static final Logger logger = LoggerFactory.getLogger(UserActivityLogger.class);

    @Pointcut("within(@org.springframework.web.bind.annotation.RestController*)")
    public void controllerMethods() {

    }

    @Around("controllerMethods() && execution(* com.project.shopapp.Controllers.UserController.*(..))")
    public Object logUserActivity(ProceedingJoinPoint joinPoint) throws Throwable {
        String methodName = joinPoint.getSignature().getName();
        String remoteAddress = ((ServletRequestAttributes) RequestContextHolder.currentRequestAttributes())
                .getRequest().getRemoteAddr();
        logger.info("User activity started: {}, IP address: {}", methodName, remoteAddress);

        Object result = joinPoint.proceed();

        logger.info("User activity finished: {}", methodName);
        return result;
    }
}
