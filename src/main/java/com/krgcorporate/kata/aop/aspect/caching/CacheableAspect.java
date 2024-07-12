package com.krgcorporate.kata.aop.aspect.caching;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;

@Slf4j
@Aspect
public class CacheableAspect {

    @AfterReturning(value = "execution(public * *..*Repository.findBy*(..))", returning = "result")
    public void cacheAfterReturning(Object result) {
        log.info("Simulating cache insertion {}", result);
    }

    @AfterReturning(value = "execution(public * *..*Repository.save(..))", returning = "result")
    public void evictAfterReturning(Object result) {
        log.info("Simulating cache eviction {}", result);
    }
}
