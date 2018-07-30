package com.anjiPlus.beanannotation.dao;

import org.springframework.stereotype.Repository;

@Repository
public class InjectionDaoImpl implements InjectionDao {

    @Override
    public void save(String arg) {
        System.out.println("保存数据： " + arg);
    }
}
