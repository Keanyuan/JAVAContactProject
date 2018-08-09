package com.anjiplus.springboot.controller;

import com.anjiplus.springboot.utils.AjJSONResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("redis")
public class RedisController {

    @Autowired
    private StringRedisTemplate stringRedisTemplate;

    @RequestMapping("/test")
    public AjJSONResult test(){
        stringRedisTemplate.opsForValue().set("cache", "hello redis");
        return AjJSONResult.ok(stringRedisTemplate.opsForValue().get("cache"));
    }


}
