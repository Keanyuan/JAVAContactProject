package com.anjiPlus.aopAdvice;

import org.aspectj.lang.ProceedingJoinPoint;

//切面类
public class MoocAspect {
    public void before(){
        System.out.println("MoocAspect before.");
    }
    
    public void afterReturning() {
        System.out.println("MoocAspect afterReturning");
        
    }
    
    public void afterThrowing() {
        System.out.println("MoocAspect afterThrowing");
    }
    
    public void after() {
        System.out.println("MoocAspect after");
    }

//    根据业务类型判断是否处理异常 throws Throwable
    public Object around(ProceedingJoinPoint pjp) throws Throwable {
        Object obj= null;
        try {
            System.out.println("MoocAspect around 1");
            obj = pjp.proceed();
            System.out.println("MoocAspect around 2");
        } catch (Throwable throwable) {
            throwable.printStackTrace();
        }
//        Object obj = pjp.proceed();
        return obj;
    }

    public Object aroundInit(ProceedingJoinPoint pjp, String bizName, int times) {
        System.out.println("aroundInit " + bizName + " " + times);
        Object obj= null;
        try {
            System.out.println("MoocAspect aroundInit 1");
            obj = pjp.proceed();
            System.out.println("MoocAspect aroundInit 2");
        } catch (Throwable throwable) {
            throwable.printStackTrace();
        }
//        Object obj = pjp.proceed();
        return obj;
    }
}
