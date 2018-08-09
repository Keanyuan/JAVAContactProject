package com.anjiplus.springboot.controller;

import com.anjiplus.springboot.pojo.SysUser;
import com.anjiplus.springboot.service.UserService;
import com.anjiplus.springboot.utils.AjJSONResult;
import org.n3r.idworker.Sid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;
import java.util.List;

@RestController
@RequestMapping("mybatis")
public class MyBatisCRUDController {

    @Autowired
    private UserService userService;

    @Autowired
    private Sid sid;

    @RequestMapping("/saveUser")
    public AjJSONResult saveUser() throws Exception {
        String userId = sid.nextShort();
        SysUser user = new SysUser();
        user.setId(userId);
        user.setUsername("imooc" + new Date());
        user.setNickname("imooc" + new Date());
        user.setPassword("123456");
        user.setIsDelete(0);
        user.setRegistTime(new Date());

        userService.saveUser(user);

        return AjJSONResult.ok("保存成功");
    }

    @RequestMapping("/updateUser")
    public AjJSONResult updateUser(){
        SysUser user = new SysUser();
        user.setId("1808096ZH496YTMW");
        user.setUsername("10011001-updated" + new Date());
        user.setNickname("10011001-updated" + new Date());
        user.setPassword("10011001-updated");
        user.setIsDelete(0);
        user.setRegistTime(new Date());
        userService.updateUser(user);
        return  AjJSONResult.ok("更新成功");
    }

    @RequestMapping("/deleteUser")
    public AjJSONResult deleteUser(String userID){
        userService.deleteUser(userID);
        return AjJSONResult.ok("删除成功");
    }

    @RequestMapping("/queryUserById")
    public AjJSONResult queryUserById(String usetrID){
        return AjJSONResult.ok(userService.queryUserById(usetrID));
    }

    @RequestMapping("/queryUserList")
    public AjJSONResult queryUserList(){
        SysUser user = new SysUser();
        user.setUsername("imooc");
        user.setNickname("lee");
        List<SysUser> userList = userService.queryUserList(user);
        return AjJSONResult.ok();
    }

    @RequestMapping("/queryUserListPaged")
    public AjJSONResult queryUserListPaged(Integer page){
        if (page == null){
            page = 1;
        }
        int pageSize = 10;

        SysUser user = new SysUser();
        List<SysUser> userList = userService.queryUserListPaged(user, page, pageSize);
        return AjJSONResult.ok(userList);
    }

    @RequestMapping("/queryUserByIdCustom")
    public AjJSONResult queryUserByIdCustom(String userID){
        return  AjJSONResult.ok(userService.queryUserByIdCustom(userID));
    }

    @RequestMapping("/saveUserTransactional")
    public AjJSONResult saveUserTransactional(){
        String userId = sid.nextShort();

        SysUser user = new SysUser();
        user.setId(userId);
        user.setUsername("lee" + new Date());
        user.setNickname("lee" + new Date());
        user.setPassword("abc123");
        user.setIsDelete(0);
        user.setRegistTime(new Date());

        userService.saveUserTransactional(user);
        return AjJSONResult.ok("保存成功");
    }



}
