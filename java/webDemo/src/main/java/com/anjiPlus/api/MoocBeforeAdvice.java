package com.anjiPlus.api;

import org.springframework.aop.MethodBeforeAdvice;

import java.lang.reflect.Method;

public class MoocBeforeAdvice implements MethodBeforeAdvice {
    @Override
    public void before(Method method, Object[] objects, Object o) throws Throwable {
        System.out.println("MoocBeforeAdvice: " + method.getName() + "  " + o.getClass().getName());
    }
}
