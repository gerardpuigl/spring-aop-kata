package com.krgcorporate.kata.aop.aspect.logging;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;

@Slf4j
@Aspect
public class LoggingAspect {

    @Pointcut("bean(*Service)")
    public void pointCut() {
    }

    @After("pointCut()")
    public void logAfter(JoinPoint jp) {
        log.info("After calling {}", jp.getSignature());
    }

    @AfterThrowing(
            pointcut="pointCut()",
            throwing="throwable")
    public void logAfterThrowing(Throwable throwable) {
        log.error("Unhandled exception", throwable);
    }
}
