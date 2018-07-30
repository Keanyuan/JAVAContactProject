package com.anjiPlus.beanannotation.service;

import com.anjiPlus.beanannotation.dao.InjectionDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class InjectionServiceImpl implements InjectionService {

    //成员变量注入
//    @Autowired
    private InjectionDao injectionDao;
//set注入
//    @Autowired
    public void setInjectionDao(InjectionDao injectionDao) {
        this.injectionDao = injectionDao;
    }
//构造注入
    @Autowired
    public InjectionServiceImpl(InjectionDao injectionDao) {
        this.injectionDao = injectionDao;
    }

    @Override
    public void save(String arg) {
        System.out.println("Service接收的参数 " + arg);
        arg = arg + ": " + this.hashCode();
        injectionDao.save(arg);
    }
}
