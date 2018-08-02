package com.anjiPlus.aopAspectj;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

//自定义注解@interface
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.METHOD)
public @interface MoocMethod {
    String value();
}
