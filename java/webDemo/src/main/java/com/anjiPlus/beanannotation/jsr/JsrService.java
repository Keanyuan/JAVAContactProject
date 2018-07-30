package com.anjiPlus.beanannotation.jsr;

import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.annotation.Resource;
import javax.inject.Inject;
import javax.inject.Named;

//@Service --> @Resource
//@Named --> @Inject
//@Service
@Named
public class JsrService {
//    通过注解在成员变量上
//    @Resource
//    @Inject
    private JsrDao jsrDao;

    //通过注解在set方法上
//    @Resource
    @Inject
    public void setJsrDao(@Named("jsrDao") JsrDao jsrDao) {
        this.jsrDao = jsrDao;
    }

    @PostConstruct
    public void init() {
        System.out.println("jsrService init");
    }

    @PreDestroy
    public void destory() {
        System.out.println("jsrService destory");

    }


    public void save() {
        System.out.println("jsrService save");
        jsrDao.save();
    }
}
