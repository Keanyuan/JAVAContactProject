package com.anjiplus.mybatis.service.impl;

import com.anjiplus.mybatis.mapper.PeopleMapper;
import com.anjiplus.mybatis.pojo.PageInfo;
import com.anjiplus.mybatis.pojo.People;
import com.anjiplus.mybatis.service.PeopleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @Auther: kean_qi
 * @Date: 2020/1/14 15:36
 * @Description:
 */
@Service
public class PeopleServiceImpl implements PeopleService {
    @Autowired
    private PeopleMapper peopleMapper;
    @Override
    public List<People> selectPeopleAll() {
        return peopleMapper.selectPeopleAll();
    }

    @Override
    public List<People> queryPeopleInfoById(Integer id) {
        return peopleMapper.queryPeopleInfoById(id);
    }

    @Override
    public int insertPeopleInfo(People people) {
        return peopleMapper.insertPeopleInfo(people);
    }

    @Override
    public PageInfo showPage(int pageSize, int pageNumber) {
        PageInfo pageInfo = new PageInfo();
        pageInfo.setPageNumber(pageNumber);
        pageInfo.setPageSize(pageSize);
        Map<String, Object> map = new HashMap<>();
        map.put("pageStart", pageSize * (pageNumber - 1));
        map.put("pageSize", pageSize);
        pageInfo.setList(peopleMapper.selectByPage(map));
        int count = peopleMapper.selCount();
        pageInfo.setTotal(count % pageSize == 0 ? count/pageSize : count/(pageSize +1));
        return pageInfo;
    }
}
