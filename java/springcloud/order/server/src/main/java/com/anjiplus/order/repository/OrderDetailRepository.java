package com.anjiplus.order.repository;

import com.anjiplus.order.dataobject.OrderDetail;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @Auther: kean_qi
 * @Date: 2018/8/14 11:00
 * @Description: dao层  订单详情
 */
public interface OrderDetailRepository extends JpaRepository<OrderDetail, String> {
}
