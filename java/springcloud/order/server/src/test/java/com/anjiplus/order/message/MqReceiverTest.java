package com.anjiplus.order.message;

import com.anjiplus.order.OrderApplicationTests;
import org.junit.Test;
import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Date;

import static org.junit.Assert.*;

/**
 * @Auther: Kean
 * @Date: 2018/8/23 下午8:29
 * @Description:
 */
@Component
public class MqReceiverTest extends OrderApplicationTests {

    @Autowired
    private AmqpTemplate amqpTemplate;

    @Test
    public void prosses() {
        amqpTemplate.convertAndSend("myQueue", "now " + new Date());
    }




    @Test
    public void orderProsses() {
        amqpTemplate.convertAndSend("myOrder","fruit", "now " + new Date());
    }
}