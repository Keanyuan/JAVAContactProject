package com.anjiplus.springboot.controller;

import com.anjiplus.springboot.pojo.SysUser;
import com.anjiplus.springboot.service.UserService;
import com.anjiplus.springboot.utils.AjJSONResult;
import org.n3r.idworker.Sid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;

@RestController
@RequestMapping("mybatis")
public class MyBatisCRUDController {

    @Autowired
    private UserService userService;

    @Autowired
    private Sid sid;

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

}
