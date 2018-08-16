package com.anjiplus.sell.repository;

import com.anjiplus.sell.dataobject.OrderMaster;
import lombok.extern.slf4j.Slf4j;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.swing.*;
import java.awt.print.Pageable;
import java.math.BigDecimal;

import static org.junit.Assert.*;

/**
 * @Auther: kean_qi
 * @Date: 2018/8/14 11:04
 * @Description: 订单列表测试
 */
@RunWith(SpringRunner.class)
@SpringBootTest
@Slf4j
public class OrderMasterRepositoryTest {

    @Autowired
    private OrderMasterRepository repository;

    private final String OPENID = "13222656656";

    @Test
    public void saveTest(){
        OrderMaster orderMaster = new OrderMaster();
        orderMaster.setOrderId("1000001");
        orderMaster.setBuyerName("张希");
        orderMaster.setBuyerPhone("18366776666");
        orderMaster.setBuyerAddress("上海市青浦区");
        orderMaster.setBuyerOpenid(OPENID);
        orderMaster.setOrderAmount(new BigDecimal(4.5));

        OrderMaster result = repository.save(orderMaster);
        Assert.assertNotNull(result);
    }

    @Test
    public void findByBuyerOpenid() {
        PageRequest request = PageRequest.of(0,2);
        Page<OrderMaster> result = repository.findByBuyerOpenid(OPENID, request);
        log.info(result.getContent().toString());
        Assert.assertNotEquals(0, result.getContent().size());



    }

}