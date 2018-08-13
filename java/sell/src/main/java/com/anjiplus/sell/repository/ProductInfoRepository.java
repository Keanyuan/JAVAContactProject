package com.anjiplus.sell.repository;

import com.anjiplus.sell.dataobject.ProductInfo;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * 详情接口
 */
public interface ProductInfoRepository extends JpaRepository<ProductInfo, String> {

//    通过商品状态查询商品信息
    List<ProductInfo> findByProductStatus(Integer productStatus);
}
