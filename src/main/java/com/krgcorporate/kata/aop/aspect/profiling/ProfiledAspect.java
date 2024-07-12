package com.krgcorporate.kata.aop.aspect.profiling;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;

@Slf4j
@AllArgsConstructor
@Aspect
public class ProfiledAspect {

    private final Profiler profiler;

    @Around("@annotation(Profiled)")
    public Object aroundProfiledMethods(ProceedingJoinPoint pjp) throws Throwable {
        return profiler.profile(pjp.getSignature().getName(), pjp::proceed);
    }
}
