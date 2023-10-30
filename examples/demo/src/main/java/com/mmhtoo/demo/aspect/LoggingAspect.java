package com.mmhtoo.demo.aspect;

import lombok.AllArgsConstructor;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.Arrays;

@Aspect
@Component
@AllArgsConstructor
public class LoggingAspect {
    private final HttpServletRequest request;
    private final static Logger logger =  LoggerFactory.getLogger(LoggingAspect.class);

    @Before(
            value = "execution(* com.mmhtoo.demo.controller.*.*(..))"
    )
    public void useLogBeforeRun(JoinPoint joinPoint) throws IOException {
        logger.info("Target Class "+joinPoint.getTarget().getClass());
        logger.info("Before running Target Method"+joinPoint.getSignature().getName());
        logger.info("HTTP Method "+request.getMethod());
        logger.info("Request URL "+request.getRequestURL());
    }

    @AfterReturning(
            value = "execution(* com.mmhtoo.demo.controller.*.*(..))",
            returning = "returnVal"
    )
    public void useLogAfterRun(JoinPoint joinPoint,Object returnVal) throws IOException {
        logger.info("Target Class "+joinPoint.getTarget().getClass());
        logger.info("Before running Target Method"+joinPoint.getSignature().getName());
        logger.info("HTTP Method "+request.getMethod());
    }

    @Around(
            value = "execution(* com.mmhtoo.demo.controller.*.*(..))"
    )
    public Object useLogAround(ProceedingJoinPoint joinPoint) throws Throwable {
        logger.info("Target Class : "+joinPoint.getTarget().getClass());
        logger.info("Before running Target Method :  "+joinPoint.getSignature().getName());
        logger.info("HTTP Method : "+request.getMethod());
        logger.info("Request URL : "+request.getRequestURL());

        Object [] args = joinPoint.getArgs();
        Arrays.stream(args).forEach((arg) ->{
            if(arg != null) logger.info("Parameter "+arg.toString());
        });
        Object returnVal = joinPoint.proceed(args);

        logger.info("After running Target Method : "+joinPoint.getSignature().getName());
        logger.info("Result : "+returnVal.toString());

        return returnVal;
    }
}
