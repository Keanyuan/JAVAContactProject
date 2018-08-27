package com.anjiplus.order.service;

import com.anjiplus.order.dto.OrderDTO;

/**
 * @Auther: kean_qi
 * @Date: 2018/8/14 13:16
 * @Description: 订单serverice
 */
public interface OrderService {

    /**
     * 创建订单.
     */
    OrderDTO create(OrderDTO orderDTO);

    /**
     * 完结订单（只能卖家操作）
     * @param orderId
     * @return
     */
    OrderDTO finish(String orderId);


}
