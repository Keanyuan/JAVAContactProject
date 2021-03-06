package com.anjiplus.product.repository;

import com.anjiplus.product.dataobject.ProductInfo;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * 详情接口
 */
public interface ProductInfoRepository extends JpaRepository<ProductInfo, String> {

//    通过商品状态查询商品信息
    List<ProductInfo> findByProductStatus(Integer productStatus);

    //根据订单ID list 查询
    List<ProductInfo> findByProductIdIn(List<String> productIdList);
}
