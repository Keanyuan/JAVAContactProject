package com.anjiplus.order.controller;

import com.anjiplus.product.client.ProductClient;
import com.anjiplus.product.common.DecreaseStockInput;
import com.anjiplus.product.common.ProductInfoOutput;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

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



//        String response = productClient.productMsg();
//        log.info("response {}", response);
        return null;
    }


    @GetMapping("/getProductList")
    public String getProductList(){
        List<ProductInfoOutput> productInfoList =  productClient.listForOrder(Arrays.asList("123457", "123455"));
        log.info(productInfoList + "");
        return "ok";
    }

    @GetMapping("/decreaseStock")
    public String decreaseStock(){
        productClient.decreaseStock(Arrays.asList(new DecreaseStockInput("123456", 10), new DecreaseStockInput("123457", 8)));
        return "ok";
    }



}
