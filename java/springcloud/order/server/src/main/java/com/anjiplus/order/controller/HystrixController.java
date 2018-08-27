package com.anjiplus.order.controller;

import com.netflix.hystrix.contrib.javanica.annotation.DefaultProperties;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixProperty;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;

/**
 * @Auther: Kean
 * @Date: 2018/8/26 上午9:59
 * @Description: Hystrix 服务降级
 */
@RestController
@DefaultProperties(defaultFallback = "defaultFallback")
public class HystrixController {

    //commandProperties设置超时时间 3秒
//    @HystrixCommand(fallbackMethod = "fallback")
//    @HystrixCommand(commandProperties = {
//            @HystrixProperty(name = "execution.isolation.thread.timeoutInMilliseconds", value = "3000")
//    })
//    @HystrixCommand(commandProperties = {
//            //服务熔断 circuitBreaker 短路开关，断路器
//            //sleepWindowInMilliseconds 断路器休眠时间窗10秒
//            //requestVolumeThreshold 断路器最小请求数 10次
//            //errorThresholdPercentage 断路器打开错误百分百条件 60%  即10次 超过7次进行断路
//
//            @HystrixProperty(name = "circuitBreaker.enabled", value = "true"),
//            @HystrixProperty(name = "circuitBreaker.requestVolumeThreshold", value = "10"),
//            @HystrixProperty(name = "circuitBreaker.sleepWindowInMilliseconds", value = "10000"),
//            @HystrixProperty(name = "circuitBreaker.errorThresholdPercentage", value = "60")
//
//
//    })
    @HystrixCommand
    @GetMapping("/getHystrixProductList")
    public String getProductList(){
        //抛异常也可以降级
        RestTemplate restTemplate = new RestTemplate();
        return restTemplate.postForObject("http://127.0.0.1:8081/product/listForOrder",
                Arrays.asList("1000003"),String.class);

    }

    private String fallback(){
        return "太拥挤了，请稍后再试";
    }

    private String defaultFallback(){
        return "默认提示：太拥挤了，请稍后再试";
    }


}
