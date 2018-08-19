package com.anjiplus.sell;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;

//MapperScan 扫码mapper文件
@SpringBootApplication
@MapperScan(basePackages = "com.anjiplus.sell.dataobject.mapper")
//Redis 缓存
@EnableCaching
public class SellApplication {

    public static void main(String[] args) {
        SpringApplication.run(SellApplication.class, args);
    }
}
