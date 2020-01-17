package com.anjiplus.mybatis.controller;

import com.anjiplus.mybatis.pojo.SysUser;
import com.anjiplus.mybatis.service.UserService;
import com.anjiplus.mybatis.utils.KeyUtil;
import com.anjiplus.mybatis.utils.ResultVOUtil;
import com.anjiplus.mybatis.vo.ResultVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;


import java.util.Date;
import java.util.List;

/**
 * @Auther: kean_qi
 * @Date: 2018/8/29 14:19
 * @Description:
 */
@RestController
@RequestMapping("mybatis")
public class MyBatisCRUDController {
    @Autowired
    private UserService userService;

    @RequestMapping("/saveUser")
    public ResultVO saveUser(){
        String userId = KeyUtil.genUniqueKey();
        SysUser user = new SysUser();
        user.setId(userId);
        user.setUsername("mybatis " + userId);
        user.setNickname("mybatis" + userId);
        user.setPassword("123456");
        user.setIsDelete(0);
        user.setRegistTime(new Date());
        try {
            userService.saveUser(user);
        } catch (Exception e) {
            return ResultVOUtil.error(5, e.getMessage());
        }
        return ResultVOUtil.success(user);

    }

    @RequestMapping("/updateUser")
    public ResultVO updateUser(){
        SysUser user = new SysUser();
        user.setId("10021");
        user.setUsername("10011001-updated" + new Date());
        user.setNickname("10011001-updated" + new Date());
        user.setPassword("10011001-updated");
        user.setIsDelete(0);
        user.setRegistTime(new Date());
        try {
            userService.updateUser(user);
        } catch (Exception e) {
            return ResultVOUtil.error(5, e.getMessage());
        }
        return  ResultVOUtil.success("更新成功");
    }

    @RequestMapping("/deleteUser")
    public ResultVO deleteUser(@RequestParam("userId") String userId){
        try {
            userService.deleteUser(userId);
        } catch (Exception e) {
            return ResultVOUtil.error(5, e.getMessage());
        }
        return ResultVOUtil.success("删除成功");
    }


    //    根据条件查询用户列表
    @RequestMapping("/queryUserList")
    public ResultVO queryUserList(){
        SysUser user = new SysUser();
        user.setUsername("mybatis 1535525874074239834");
        user.setNickname("mybatis1535525874074239834");
        List<SysUser> userList = userService.queryUserList(user);
        return ResultVOUtil.success(userList);
    }


    @RequestMapping("/queryUserListPaged")
    public ResultVO queryUserListPaged(@RequestParam("page") Integer page){
        if (page == null){
            page = 1;
        }
        int pageSize = 10;

        SysUser user = new SysUser();
        List<SysUser> userList = userService.queryUserListPaged(user, page, pageSize);
        return ResultVOUtil.success(userList);
    }

    @RequestMapping("/queryUserByIdCustom")
    public ResultVO queryUserByIdCustom(@RequestParam("userID") String userID){
        return  ResultVOUtil.success(userService.queryUserByIdCustom(userID));
    }

    @RequestMapping("/saveUserTransactional")
    public ResultVO saveUserTransactional(){
        String userId = KeyUtil.genUniqueKey();
        SysUser user = new SysUser();
        user.setId(userId);
        user.setUsername("saveUserTransactional1001---" + userId);
        user.setNickname("saveUserTransactional1001---" + userId);
        user.setPassword("123456");
        user.setIsDelete(0);
        user.setRegistTime(new Date());
        userService.saveUserTransactional(user);
        return ResultVOUtil.success("保存成功");

    }
}
