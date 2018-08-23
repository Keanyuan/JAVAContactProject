package com.anjiplus.order.converter;

import com.anjiplus.order.dataobject.OrderMaster;
import com.anjiplus.order.dto.OrderDTO;
import org.springframework.beans.BeanUtils;

import java.util.List;
import java.util.stream.Collectors;

/**
 * @Auther: kean_qi
 * @Date: 2018/8/14 14:53
 * @Description: OrderMaster 转成  OrderDTO
 */
public class OrderMasterToOrderDTOConverter {

    public static OrderDTO convert(OrderMaster orderMaster){
        OrderDTO orderDTO = new OrderDTO();
        BeanUtils.copyProperties(orderMaster, orderDTO);
        return orderDTO;
    }

    public static List<OrderDTO> convert(List<OrderMaster> orderMasterList){
        return orderMasterList.stream().
                map(e -> convert(e)).collect(Collectors.toList());
    }

}
