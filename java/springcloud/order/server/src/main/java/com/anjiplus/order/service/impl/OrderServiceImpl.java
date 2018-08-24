package com.anjiplus.order.service.impl;

import com.anjiplus.order.dataobject.OrderDetail;
import com.anjiplus.order.dataobject.OrderMaster;
import com.anjiplus.order.dto.OrderDTO;
import com.anjiplus.order.enums.OrderStatusEnum;
import com.anjiplus.order.enums.PayStatusEnum;
import com.anjiplus.order.repository.OrderDetailRepository;
import com.anjiplus.order.repository.OrderMasterRepository;
import com.anjiplus.order.service.OrderService;
import com.anjiplus.order.utils.KeyUtil;
import com.anjiplus.product.client.ProductClient;
import com.anjiplus.product.common.DecreaseStockInput;
import com.anjiplus.product.common.ProductInfoOutput;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @Auther: kean_qi
 * @Date: 2018/8/22 15:11
 * @Description:
 */
@Service
@Slf4j
public class OrderServiceImpl implements OrderService {


    @Autowired
    private OrderDetailRepository orderDetailRepository;

    @Autowired
    private OrderMasterRepository orderMasterRepository;

    @Autowired
    private ProductClient productClient;

    @Override
    @Transactional
    public OrderDTO create(OrderDTO orderDTO) {
        BigDecimal orderAmount = new BigDecimal(BigInteger.ZERO);
        String orderId = KeyUtil.genUniqueKey();


        //查詢商品信息（调商品服务）
        List<String> productList = orderDTO.getOrderDetailList().stream()
                .map(OrderDetail::getProductId).collect(Collectors.toList());
        List<ProductInfoOutput> productInfoList = productClient.listForOrder(productList);

        //读Redis
        //减库存并将新值重新设置进Redis

        //订单入库异常，需要手动回滚Redis


        // 计算总价
        for (OrderDetail orderDetail:  orderDTO.getOrderDetailList()) {
            for (ProductInfoOutput productInfo: productInfoList){
                if (productInfo.getProductId().equals(orderDetail.getProductId())){
                    //2.计算总价 单价*数量 + 总价格
                    orderAmount = productInfo.getProductPrice().
                            multiply(new BigDecimal(orderDetail.getProductQuantity())).
                            add(orderAmount);
                    //将productInfo的数据拷贝到orderDetail
                    //将productInfo的数据拷贝到orderDetail
                    BeanUtils.copyProperties(productInfo, orderDetail);
                    //订单详情入库
                    orderDetail.setDetailId(KeyUtil.genUniqueKey());
                    orderDetail.setOrderId(orderId);

                    //订单详情保存入库
                    orderDetailRepository.save(orderDetail);
                }
            }


        }

        //扣库存（调商品服务）
        List<DecreaseStockInput> cartDTOList = orderDTO.getOrderDetailList().stream().
                map(e -> new DecreaseStockInput(e.getProductId(), e.getProductQuantity())).
                collect(Collectors.toList());
        productClient.decreaseStock(cartDTOList);


        //订单入口（OrderMaster和OrderDetail）
        OrderMaster orderMaster = new OrderMaster();
        orderDTO.setOrderId(orderId);
        BeanUtils.copyProperties(orderDTO, orderMaster);
        orderMaster.setOrderAmount(orderAmount);
        orderMaster.setOrderStatus(OrderStatusEnum.NEW.getCode());
        orderMaster.setPayStatus(PayStatusEnum.WAIT.getCode());
        orderMasterRepository.save(orderMaster);

        return orderDTO;
    }
}
