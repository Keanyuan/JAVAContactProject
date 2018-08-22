package com.anjiplus.order.service;

import com.anjiplus.order.dto.OrderDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

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

}
