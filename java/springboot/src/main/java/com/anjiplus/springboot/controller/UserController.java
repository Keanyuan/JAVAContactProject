package com.anjiplus.springboot.controller;

import com.anjiplus.springboot.dao.User;
import com.anjiplus.springboot.utils.AjJSONResult;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;

@RestController //@RestController = @Controller + @ResponseBody
@RequestMapping("/user")
public class UserController {

    @RequestMapping("/getUser")
    public AjJSONResult getUser(){
        User user = new User();
        user.setAge(18);
        user.setName("imooc");
        user.setBirthday(new Date());
        user.setPassword("123456");
        user.setDesc("hello spring boot");
        return AjJSONResult.ok(user);
    }
}
