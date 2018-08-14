package com.anjiplus.sell.contorller;

import com.anjiplus.sell.converter.OrderFormToOrderDTOConverter;
import com.anjiplus.sell.dto.OrderDTO;
import com.anjiplus.sell.enums.ResultEnum;
import com.anjiplus.sell.exception.SellException;
import com.anjiplus.sell.form.OrderForm;
import com.anjiplus.sell.service.BuyerService;
import com.anjiplus.sell.service.OrderService;
import com.anjiplus.sell.utils.ResultVOUtil;
import com.anjiplus.sell.vo.ResultVO;
import lombok.extern.slf4j.Slf4j;
import org.hibernate.criterion.Order;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @Auther: kean_qi
 * @Date: 2018/8/14 15:50
 * @Description: 订单控制器
 */
@RestController
@RequestMapping("/buyer/order")
@Slf4j
public class BuyerOrderController {

    @Autowired
    private OrderService orderService;

    @Autowired
    private BuyerService buyerService;

    //创建订单
    @PostMapping("/create")
    public ResultVO<Map<String, String>> create(@Valid OrderForm orderForm, BindingResult bindingResult){

        if (bindingResult.hasErrors()){
            log.error("【创建订单】参数不正确, orderForm={}", orderForm);
            throw new SellException(ResultEnum.PARAM_ERROR.getCode(), bindingResult.getFieldError().getDefaultMessage());
        }

        OrderDTO orderDTO = OrderFormToOrderDTOConverter.convert(orderForm);

        if (CollectionUtils.isEmpty(orderDTO.getOrderDetailList())) {
            log.error("【创建订单】购物车不能为空");
            throw new SellException(ResultEnum.CART_EMPTY);
        }


        OrderDTO createResult = orderService.create(orderDTO);

        Map<String, String> map = new HashMap<>();

        map.put("orderId", createResult.getOrderId());

        return ResultVOUtil.success(map);
    }

    //订单列表
    @GetMapping("/list")
    public ResultVO<List<OrderDTO>> list(@RequestParam("openid") String openid,
                                         @RequestParam(value = "page", defaultValue = "0") Integer page,
                                         @RequestParam(value = "size", defaultValue = "10") Integer size){
        if (StringUtils.isEmpty(openid)){
            log.error("【查询订单列表】openid为空");
            throw new  SellException(ResultEnum.PARAM_ERROR);
        }


        PageRequest request = PageRequest.of(page, size);
        Page<OrderDTO> orderDTOPage = orderService.findList(openid, request);
        Map<String, Object> orderListMap = new HashMap<>();
        orderListMap.put("totals", orderDTOPage.getTotalElements());
        orderListMap.put("currentPage", page);
        orderListMap.put("totalPages", orderDTOPage.getTotalPages());
        orderListMap.put("rows", orderDTOPage.getContent());
        return ResultVOUtil.success(orderListMap);
    }

    //订单详情
    @GetMapping("/detail")
    public ResultVO<OrderDTO> detail(@RequestParam("openid") String openid,
                                     @RequestParam("orderId") String orderId){
//        OrderDTO orderDTO = orderService.findOne(orderId);
        OrderDTO orderDTO = new OrderDTO();

        try {
            orderDTO = buyerService.findOrderOne(openid, orderId);
        } catch (SellException e) {
            return ResultVOUtil.error(e.getCode(), e.getLocalizedMessage());
        } catch (Exception e){
            return ResultVOUtil.error();
        }
        return ResultVOUtil.success(orderDTO);

    }

    //取消订单
    @PostMapping("/cancel")
    public ResultVO cancel(@RequestParam("openid") String openid,
                           @RequestParam("orderId") String orderId) {
        try {
            buyerService.cancelOrder(openid, orderId);
        } catch (SellException e) {
            return ResultVOUtil.error(e.getCode(), e.getLocalizedMessage());
        } catch (Exception e){
            return ResultVOUtil.error();
        }
        return ResultVOUtil.success();
    }
}
