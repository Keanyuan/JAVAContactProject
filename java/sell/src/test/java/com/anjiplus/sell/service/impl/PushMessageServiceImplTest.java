package com.anjiplus.sell.service.impl;

import com.anjiplus.sell.dto.OrderDTO;
import com.anjiplus.sell.service.OrderService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@SpringBootTest
public class PushMessageServiceImplTest {

    @Autowired
    private PushMessageServiceImpl pushMessageService;

    @Autowired
    private OrderService orderService;
    @Test
    public void orderStatus() throws Exception{
        OrderDTO orderDTO = orderService.findOne("1534494148399209714");
        pushMessageService.orderStatus(orderDTO);
    }
}