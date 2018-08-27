package com.anjiplus.order.repository;

import com.anjiplus.order.dataobject.OrderDetail;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * @Auther: kean_qi
 * @Date: 2018/8/14 11:00
 * @Description: dao层  订单详情
 */
public interface OrderDetailRepository extends JpaRepository<OrderDetail, String> {

    /**
     * 通过orderId 查询订单详情
     * @param orderId
     * @return
     */
    List<OrderDetail> findByOrderId(String orderId);
}
