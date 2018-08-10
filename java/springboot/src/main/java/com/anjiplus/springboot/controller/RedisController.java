package com.anjiplus.springboot.controller;

import com.anjiplus.springboot.pojo.SysUser;
import com.anjiplus.springboot.utils.AjJSONResult;
import com.anjiplus.springboot.utils.JsonUtils;
import com.anjiplus.springboot.utils.redis.RedisOperator;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@RestController
@RequestMapping("redis")
public class RedisController {

    @Autowired
    private StringRedisTemplate strRedis;

    @Autowired
    private RedisOperator redis;

    @RequestMapping("/test")
    public AjJSONResult cacheData(){
//        strRedis.opsForValue().set("cache", "hello redis");


        SysUser user = new SysUser();
        user.setId("101100");
        user.setUsername("redis");
        user.setNickname("recc");
        user.setIsDelete(0);
        user.setRegistTime(new Date());

        SysUser user1 = new SysUser();
        user1.setId("101100");
        user1.setUsername("redis");
        user1.setNickname("recc");
        user1.setIsDelete(0);
        user1.setRegistTime(new Date());

        List<SysUser> userList = new ArrayList<>();
        userList.add(user);
        userList.add(user1);

//        redis封装
        redis.set("json:info:userlist", JsonUtils.objectToJson(userList), 2000);
        String userListJson = redis.get("json:info:userlist");
        List<SysUser> userList1 = JsonUtils.jsonToList(userListJson, SysUser.class);
        System.out.println(userList1);


        strRedis.opsForValue().set("json:user", JsonUtils.objectToJson(user));
        SysUser sysUser = JsonUtils.jsonToPojo(strRedis.opsForValue().get("json:user"), SysUser.class);
        return AjJSONResult.ok(sysUser);
    }

}
