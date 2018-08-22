package com.anjiplus.order.controller;

import com.anjiplus.order.client.ProductClient;
import com.anjiplus.order.dataobject.ProductInfo;
import com.anjiplus.order.dto.CartDTO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.loadbalancer.LoadBalancerClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;
import java.util.List;

/**
 * @Auther: kean_qi
 * @Date: 2018/8/22 15:24
 * @Description:
 */
@RestController
@Slf4j
public class ClientController {


//    @Autowired
//    private LoadBalancerClient loadBalancerClient;

//    @Autowired
//    private RestTemplate restTemplate;

    @Autowired
    public ProductClient productClient;

    @GetMapping("/getProductMsg")
    public String getProduct(){
        //方法一（直接使用RestTemplate，URL写死）
//        RestTemplate restTemplate = new RestTemplate();
//        String response = restTemplate.getForObject("http://localhost:8081/server/msg", String.class);
//        log.info("response {}", response);

        //方法二（利用LoadBalancerClient通过应用获取URL，在使用RestTemplate）
//        RestTemplate restTemplate = new RestTemplate();
//        ServiceInstance serviceInstance = loadBalancerClient.choose("PRODUCT");
//        String url = String.format("http://%s:%s/server/msg", serviceInstance.getHost(), serviceInstance.getPort());
//        String response = restTemplate.getForObject(url, String.class);
//        log.info("response {}", response);

        //方法三(使用LoadBalanced注解可在restTemplate中使用应用名字)
//        String response = restTemplate.getForObject("http://PRODUCT/server/msg", String.class);
//        log.info("response {}", response);



        String response = productClient.productMsg();
        log.info("response {}", response);
        return response;
    }


    @GetMapping("/getProductList")
    public String getProductList(){
        List<ProductInfo> productInfoList =  productClient.findList(Arrays.asList("123457", "123455"));
        log.info(productInfoList + "");
        return "ok";
    }

    @GetMapping("/decreaseStock")
    public String decreaseStock(){
        productClient.decreaseStock(Arrays.asList(new CartDTO("123456", 10), new CartDTO("123457", 8)));
        return "ok";
    }



}
