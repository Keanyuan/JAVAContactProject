package com.anjiplus.mybatis.controller;

import com.anjiplus.mybatis.pojo.PageInfo;
import com.anjiplus.mybatis.pojo.People;
import com.anjiplus.mybatis.service.PeopleService;
import com.anjiplus.mybatis.utils.ResultVOUtil;
import com.anjiplus.mybatis.utils.ValidateUtil;
import com.anjiplus.mybatis.vo.ResultVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.thymeleaf.util.StringUtils;

import java.util.List;

/**
 * @Auther: kean_qi
 * @Date: 2020/1/14 15:41
 * @Description:
 */
@RestController
@RequestMapping("people")
public class PeopleController {
    @Autowired
    private PeopleService peopleService;

    @RequestMapping("/selectAll")
    public ResultVO selectAll(){
        List<People> userList = peopleService.selectPeopleAll();
        return ResultVOUtil.success(userList);
    }

    @RequestMapping("/queryPeopleInfoById")
    public ResultVO queryPeopleInfoById(@RequestParam("id") Integer id){
        List<People> userList = peopleService.queryPeopleInfoById(id);
        return ResultVOUtil.success(userList);
    }


    @RequestMapping("/insertPeopleInfo")
    public ResultVO insertPeopleInfo(@RequestBody People people){


        if(ValidateUtil.isEmpty(people.getAge())){
            return ResultVOUtil.error(0003, "年龄输入有误");
        }

        if(StringUtils.isEmpty(people.getName())){
            return ResultVOUtil.error(0003, "名字不能为空");
        }


        int intid = peopleService.insertPeopleInfo(people);
        if(intid>0){
            return ResultVOUtil.success();
        }
        return ResultVOUtil.error(0003, "请求失败");
    }


    @RequestMapping("/selectPeopleList")
    public ResultVO selectPeopleList(@RequestBody PageInfo pageInfo){
        PageInfo pageInfo1 = peopleService.showPage(pageInfo.getPageSize(), pageInfo.getPageNumber());
        return ResultVOUtil.success(pageInfo1);

    }
}
