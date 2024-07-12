package com.krgcorporate.kata.aop.aspect.activation;

import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;

@Aspect
public class DisabledAspect {

    @Before("@annotation(disabled)")
    public void beforeDisabledMethod(Disabled disabled) throws DisabledException {
        throw new DisabledException();
    }
}
