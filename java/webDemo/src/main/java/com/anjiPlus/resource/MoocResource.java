package com.anjiPlus.resource;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.core.io.Resource;

import java.io.IOException;

public class MoocResource implements ApplicationContextAware {

    private ApplicationContext applicationContext;
    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        this.applicationContext = applicationContext;
    }

    public void resource()throws IOException {
        Resource resource = applicationContext.getResource("config.txt");
//        "classpath:config.txt"
//        "file:/Users/Kean/Desktop/JAVA/webDemo/src/main/resources/config.txt"
        System.out.println(resource.getFilename());
        System.out.println(resource.contentLength());
    }
}
