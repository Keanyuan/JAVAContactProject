package com.anjiPlus.beanannotation.javabased;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.*;

//使用ImportResource 和 Value注解进行资源文件读取
@Configuration
@ImportResource("config.xml")
public class StoreConfig {

//    @Value("${jdbc.username}")
//    private String username;
//
//    @Value("${jdbc.url}")
//    private String url;
//
//    @Value("${jdbc.password}")
//    private String password;
//
//    @Bean
//    public MyDirverManager myDirverManager() {
//        return new MyDirverManager(url, username, password);
//    }




//    根据方法名作为ID    name默认是stringStore
//    @Bean(name = "stringStore", initMethod = "init", destroyMethod = "destory")
//    public Store stringStore() {
//        return  new StringStore();
//    }


//        根据方法名作为ID    name默认是stringStore
    @Bean(name = "stringStore")
    @Scope(value = "prototype", proxyMode = ScopedProxyMode.DEFAULT)
    public Store stringStore() {
        return  new StringStore();
    }

//    @Autowired
//    private Store<String> s1;
//
//    @Autowired
//    private Store<Integer> s2;
//
//    @Bean
//    public StringStore stringStore() {
//        return  new StringStore();
//    }
//
//    @Bean
//    public IntegerStore integerStore() {
//        return  new IntegerStore();
//    }
//
//    @Bean(name = "stringStoreTest")
//    public Store stringStoreTest() {
//        System.out.println("s1: " + s1.getClass().getName());
//        System.out.println("s2: " + s2.getClass().getName());
//        return  new StringStore();
//    }




}
