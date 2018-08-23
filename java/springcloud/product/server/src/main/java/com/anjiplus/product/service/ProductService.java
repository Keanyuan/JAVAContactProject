package com.anjiplus.product.service;

import com.anjiplus.product.dataobject.ProductInfo;
import com.anjiplus.product.dto.CartDTO;

import java.util.List;

public interface ProductService {
    //    查询在架的商品列表
    List<ProductInfo> findUpAll();
    //根据订单ID list 查询
    List<ProductInfo> findList(List<String> productIdList);

    //减库存
    void decreaseStock(List<CartDTO> cartDTOList);

}
