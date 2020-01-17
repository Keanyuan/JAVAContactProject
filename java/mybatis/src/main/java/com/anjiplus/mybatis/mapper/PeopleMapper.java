package com.anjiplus.mybatis.mapper;

import com.anjiplus.mybatis.pojo.People;
import com.anjiplus.mybatis.utils.MyMapper;

import java.util.List;
import java.util.Map;

public interface PeopleMapper extends MyMapper<People> {
    List<People> selectPeopleAll();

    //    通过Id查询用户信息
    List<People> queryPeopleInfoById(Integer id);

    int insertPeopleInfo(People people);

    List<People> selectByPage(Map map);

    /*数据个数*/
    int selCount();

}