package com.anjiPlus.api;

import org.springframework.aop.ThrowsAdvice;

import java.lang.reflect.Method;

public class MoocThrowsAdvice implements ThrowsAdvice {
    public void afterThrowing(Exception ex) throws Throwable{
        System.out.println("MoocThrowsAdvice afterThrowing 1");
    }

    public void afterThrowing(Method method, Object[] args,Object target, Exception ex) throws Throwable{
        System.out.println("MoocThrowsAdvice afterThrowing 2: " + method.getName() + "  " + target.getClass().getName());
    }
}
