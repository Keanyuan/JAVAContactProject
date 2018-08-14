package com.anjiplus.sell.service;

import com.anjiplus.sell.dataobject.ProductInfo;
import com.anjiplus.sell.dto.CartDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface ProductService {

//    查询单个商品
    ProductInfo findOne(String productId);

    //    查询在架的商品列表
    List<ProductInfo> findUpAll();

//    查询所有商品 分页 pageable
    Page<ProductInfo> findAll(Pageable pageable);

//    添加
    ProductInfo save(ProductInfo productInfo);

    //加库存
    void increaseStock(List<CartDTO> carDTOList);

    //减库存
    void decreaseStock(List<CartDTO> cartDTOList);


    //上架


    //下架

}
