package com.anjiPlus.beanannotation.javabased;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.ImportResource;

//使用ImportResource 和 Value注解进行资源文件读取
@Configuration
@ImportResource("config.xml")
public class StoreConfig {

    @Value("${jdbc.username}")
    private String username;

    @Value("${jdbc.url}")
    private String url;

    @Value("${jdbc.password}")
    private String password;

    @Bean
    public MyDirverManager myDirverManager() {
        return new MyDirverManager(url, username, password);
    }


//    根据方法名作为ID    name默认是stringStore
//    @Bean(name = "stringStore", initMethod = "init", destroyMethod = "destory")
//    public Store stringStore() {
//        return  new StringStore();
//    }
}
