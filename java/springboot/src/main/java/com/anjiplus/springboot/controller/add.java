package com.anjiplus.springboot.controller;

import com.anjiplus.springboot.utils.redis.RedisUtils;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;

public class add {

    @Autowired
    private StringRedisTemplate stringRedisTemplate;

    @Autowired
    private RedisTemplate<String,String> redisTemplate;

    @Autowired
    private RedisUtils redisUtils;
    @Test
    public void test() {
        stringRedisTemplate.opsForValue().set("cache", "hello redis");
        System.out.println(stringRedisTemplate.opsForValue().get("cache"));

//        redisUtils.set("cook", "my cook");
//        System.out.println(redisUtils.get("cook"));



    }
}
