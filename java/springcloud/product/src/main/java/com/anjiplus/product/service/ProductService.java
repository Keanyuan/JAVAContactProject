package com.anjiplus.product.service;

import com.anjiplus.product.dataobject.ProductInfo;

import java.util.List;

public interface ProductService {
    //    查询在架的商品列表
    List<ProductInfo> findUpAll();

}
