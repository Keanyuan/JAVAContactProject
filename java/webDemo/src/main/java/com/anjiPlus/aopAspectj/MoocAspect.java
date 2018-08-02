package com.anjiPlus.aopAspectj;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
import org.springframework.stereotype.Component;

@Component
@Aspect
public class MoocAspect {
//    执行.biz 包下的所有以Biz结尾的类的任何类型方法
    @Pointcut("execution(* com.anjiPlus.aopAspectj.biz.*Biz.*(..))")
    public void pointcut() {
        System.out.println("MoocAspect pointcut");
    }

    @Pointcut("within(com.anjiPlus.aopAspectj.biz.*)*")
    public void bizPointcut() {}

//    在执行该包下以Biz结尾的类的所有方法时匹配Advice
//    @Before("execution(* com.anjiPlus.aopAspectj.biz.*Biz.*(..))")
//    引用一个已经定义好的advice
    @Before("pointcut()")
    public void before() {
        System.out.println("MoocAspect before");
    }

//    advice传递参数
    @Before("pointcut() && args(arg)")
    public void beforeWithParam(String arg) {
        System.out.println("MoocAspect beforeWithParam: " + arg);
    }
//自定义注解advice传递参数
    @Before("pointcut() && @annotation(moocMethod)")
    public void beforeWithAnnotaion(MoocMethod moocMethod) {
        System.out.println("MoocAspect beforeWithAnnotaion: " + moocMethod.value());
    }
    
    @AfterReturning(pointcut = "bizPointcut()", returning = "returnValue")
    public void afterReturning(Object returnValue) {
        System.out.println("MoocAspect afterReturning" + returnValue);
    }

    @AfterThrowing(pointcut = "pointcut()", throwing = "e")
    public void afterThrowing(RuntimeException e) {
        System.out.println("MoocAspect afterThrowing: " + e.getMessage());
    }

    @After("pointcut()")
    public void after() {
        System.out.println("MoocAspect after");
    }

//    环绕通知
    @Around("pointcut()")
    public Object around(ProceedingJoinPoint pjp) throws Throwable{
        System.out.println("around 1");
        Object obj = pjp.proceed();
        System.out.println("around 2");
        System.out.println("around: " + obj);
        return obj;
    }

}
