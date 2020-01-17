package com.anjiplus.mybatis.service;

import com.anjiplus.mybatis.pojo.PageInfo;
import com.anjiplus.mybatis.pojo.People;

import java.util.List;

public interface PeopleService {
    /*
    * 查询所有信息
    * */
    List<People> selectPeopleAll();

    /*
    * 根据id 查询
    * */
    List<People> queryPeopleInfoById(Integer id);

    /*
    * 添加用户信息
    * */
    int insertPeopleInfo(People people);

    /*
    * 分页显示
    * */
    PageInfo showPage(int pageSize, int pageNumber);


}
