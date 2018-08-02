package com.anjiPlus.aopAspectj;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.stereotype.Component;

import java.util.Collection;

@Component
@Aspect
public class SampleImpl implements Sample<String> {
    @Override
    @Before("execution(* com.anjiPlus.aopAspectj.Sample.sampleGenericMethod(*)) && args(param)")
    public void sampleGenericMethod(String param) {
    }

    @Override
    @Before("execution(* com.anjiPlus.aopAspectj.Sample.sampleGenericCollectMethod(*)) && args(param)")
    public void sampleGenericCollectMethod(Collection<String> param) {

    }

//    通知和切入点注解有一个额外的"argNames"属性，可以用来指定所注解的注解的参数名  ProceedingJoinPoint，JoinPoint.StaticPart，JoinPoint可以忽略
    @Before(value = "com.anjiPlus.aopAspectj.MoocAspect.pointcut() && target(bean) && @annotation(moocAspect)", argNames = "bean, moocAspect")
    public void aduit(ProceedingJoinPoint jpj, Object bean, MoocAspect moocAspect) {

    }

    @Before(value = "com.anjiPlus.aopAspectj.MoocAspect.pointcut() && target(bean) && @annotation(moocAspect)", argNames = "bean, moocAspect")
    public void aduit(JoinPoint.StaticPart js, Object bean, MoocAspect moocAspect) {

    }


    @Before(value = "com.anjiPlus.aopAspectj.MoocAspect.pointcut() && target(bean) && @annotation(moocAspect)", argNames = "bean, moocAspect")
    public void aduit(JoinPoint joinPoint, Object bean, MoocAspect moocAspect) {

    }
}
