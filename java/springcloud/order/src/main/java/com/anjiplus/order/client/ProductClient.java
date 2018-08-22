package com.anjiplus.order.client;

import com.anjiplus.order.dataobject.ProductInfo;
import com.anjiplus.order.dto.CartDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

/**
 * @Auther: kean_qi
 * @Date: 2018/8/22 15:47
 * @Description:
 */
@FeignClient(name = "product")
public interface ProductClient {

    @GetMapping("/server/msg")
    String productMsg();

    //根据订单ID list 查询
    @PostMapping("/product/listForOrder")
    List<ProductInfo> findList(List<String> productIdList);

    @PostMapping("/product/decreaseStock")
    public void decreaseStock(@RequestBody List<CartDTO> cartDTOList);
}
