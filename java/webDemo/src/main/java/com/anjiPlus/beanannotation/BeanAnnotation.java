package com.anjiPlus.beanannotation;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

//prototype hashCode会改变
@Scope("prototype")
//指定名称 自定义bean名称
//@Component("beanAnnotation")
//自动定义
@Component
public class BeanAnnotation {

    public void say(String arg) {
        System.out.println("BeanAnnotation: " + arg);
    }

    public void myHashCode() {
        System.out.println("BeanAnnotation: " + this.hashCode());
    }
}
