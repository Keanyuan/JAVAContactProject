package com.anjiPlus.SimpleMove;

import org.springframework.beans.factory.annotation.Autowired;

public class SimpleMoveLister {
    private MoveFinder moveFinder ;
//@Autowired注解应用在setter方法，也可以用于构造器或成员变量
//    默认情况下如果因找不到合适的bean将导致autowiring失败抛出异常，可以通过设置required = false进行避免
//    每个类只能有一个构造器被标记为required = true
//    @Autowired的必要属性，建议使用@required注解
    @Autowired(required = false)
    public void setMoveFinder(MoveFinder moveFinder) {
        this.moveFinder = moveFinder;
    }
}
