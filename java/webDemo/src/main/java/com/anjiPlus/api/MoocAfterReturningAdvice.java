package com.anjiPlus.api;

import org.springframework.aop.AfterReturningAdvice;

import java.lang.reflect.Method;

public class MoocAfterReturningAdvice implements AfterReturningAdvice {
    @Override
    public void afterReturning(Object returnValue, Method method, Object[] args, Object target) throws Throwable {
        System.out.println("MoocAfterReturningAdvice: " + method.getName() + " " + target.getClass().getName() + "  " + returnValue);

    }
}
