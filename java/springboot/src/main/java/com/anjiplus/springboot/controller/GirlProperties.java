package com.anjiplus.springboot;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

//获取前缀是girl的配置
@Component
@ConfigurationProperties(prefix = "girl")
public class GirlProperties {
    private String cupSize;
    private int age;

    public String getCupSize() {
        return cupSize;
    }

    public void setCupSize(String cupSize) {
        this.cupSize = cupSize;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }
}
