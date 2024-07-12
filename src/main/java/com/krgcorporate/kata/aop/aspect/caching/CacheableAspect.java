package com.krgcorporate.kata.aop.aspect.caching;

import com.krgcorporate.kata.aop.contract.Contract;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;

import java.util.Optional;

@Slf4j
@Aspect
public class CacheableAspect {

    @Around("execution(public * *..*Repository.findBy*(..)) && args(reference)")
    public Object checkCacheBeforeCalling(ProceedingJoinPoint pjp, String reference) throws Throwable {
        log.info("Simulating cache reading {}", reference);
        if(reference.equals("CACHED_REFERENCE")){
            log.info("Simulating returning cached object {}", reference);
            return Optional.of(new Contract("CACHED_REFERENCE"));
        }

        log.info("Simulating search database {}", reference);
        Object retVal = pjp.proceed();
        log.info("Simulating cache insertion {}", reference);
        return retVal;
    }

    @AfterReturning(value = "execution(public * *..*Repository.save(..))", returning = "result")
    public void evictAfterReturning(Object result) {
        log.info("Simulating cache eviction {}", result);
    }
}
