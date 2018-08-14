package com.anjiplus.sell.repository;

import com.anjiplus.sell.dataobject.OrderDetail;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * @Auther: kean_qi
 * @Date: 2018/8/14 11:00
 * @Description: dao层  订单详情
 */
public interface OrderDetailRepository extends JpaRepository<OrderDetail, String> {

    /* 根据订单ID 查询订单详情*/
    List<OrderDetail> findByOrderId(String orderId);
}
