package com.anjiplus.order.message;

import com.anjiplus.order.utils.JsonUtil;
import com.anjiplus.product.common.ProductInfoOutput;
import com.fasterxml.jackson.core.type.TypeReference;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.annotation.Queue;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @Auther: kean_qi
 * @Date: 2018/8/24 10:46
 * @Description:
 */
@Component
@Slf4j
@Transactional
public class ProductInfoReceiver {

    private static final String PRODUCT_STOCK_TEMPLATE =  "product_stock_%s";
    @Autowired
    private StringRedisTemplate stringRedisTemplate;


    @RabbitListener(queuesToDeclare = @Queue("productInfo"))
    public void process(String message){
        List<ProductInfoOutput> productInfoOutputList = (List<ProductInfoOutput>)JsonUtil.fromJson(message,
                new TypeReference<List<ProductInfoOutput>>() {});
        log.info("从队列{}接收到消息： {}", "productInfo", productInfoOutputList);

        //储存到Redis
        for (ProductInfoOutput productInfoOutput : productInfoOutputList) {
            stringRedisTemplate.opsForValue().set(String.format(PRODUCT_STOCK_TEMPLATE, productInfoOutput.getProductId()),
                    String.valueOf(productInfoOutput.getProductStock()));
        }

    }

}
