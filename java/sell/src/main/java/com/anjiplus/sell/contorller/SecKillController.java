package com.anjiplus.sell.contorller;

import com.anjiplus.sell.service.SecKillService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Auther: Kean
 * @Date: 2018/8/18 下午5:52
 * @Description: 秒杀活动
 */
@RestController
@RequestMapping("/skill")
@Slf4j
public class SecKillController {
    @Autowired
    private SecKillService secKillService;

    /**
     * 查询秒杀活动特价商品的信息
     * @param productId
     * @return
     * @throws Exception
     */
    @GetMapping("/query/{productId}")
    public String query(@PathVariable String productId) throws Exception{
        return secKillService.querySecKillProductInfo(productId);
    }

    /**
     * 秒杀， 没有抢到活动“哎呦喂，xxxxxx”，抢到的返回剩余的库存量
     * @return
     * @throws Exception
     */
    @GetMapping("/order/{productId}")
    public String skill(@PathVariable String productId) throws Exception{
        log.info("@skill request, productId:" + productId);
        secKillService.orderProductMockDiffUser(productId);
        return secKillService.querySecKillProductInfo(productId);
    }



}
