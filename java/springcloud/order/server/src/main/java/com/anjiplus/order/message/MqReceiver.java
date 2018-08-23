package com.anjiplus.order.message;

import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.annotation.Exchange;
import org.springframework.amqp.rabbit.annotation.Queue;
import org.springframework.amqp.rabbit.annotation.QueueBinding;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

/**
 * @Auther: Kean
 * @Date: 2018/8/23 下午8:28
 * @Description:
 */
@Slf4j
@Component
public class MqReceiver {

    //1.手动配置
//    @RabbitListener(queues = "myQueue")
    //自动创建队列
//    @RabbitListener(queuesToDeclare = @Queue("myQueue"))
    //自动创建队列 Exchange和Queue绑定
    @RabbitListener(bindings = @QueueBinding(
            exchange = @Exchange("myExchange"),
            value = @Queue("myQueue")
    ))
    public void prosses(String message){
        log.info("MqReceiver prosses: {}",message);
    }




    @RabbitListener(bindings = @QueueBinding(
            exchange = @Exchange("myOrder"),
            key = "computer",
            value = @Queue("myComputerQueue")
    ))
    public void computerProsses(String message){
        log.info("MqReceiver Computer prosses: {}",message);
    }


    @RabbitListener(bindings = @QueueBinding(
            exchange = @Exchange("myOrder"),
            key = "fruit",
            value = @Queue("myFruitQueue")
    ))
    public void fruitProsses(String message){
        log.info("MqReceiver Fruit prosses: {}",message);
    }
}
