package com.anjiplus.product.service.impl;

import com.anjiplus.product.dataobject.ProductInfo;
import com.anjiplus.product.enums.ProductStatusEnum;
import com.anjiplus.product.repository.ProductInfoRepository;
import com.anjiplus.product.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @Auther: Kean
 * @Date: 2018/8/21 下午11:57
 * @Description:
 */
@Service
public class ProductServiceImpl implements ProductService {
    @Autowired
    private ProductInfoRepository repository;
    @Override
    public List<ProductInfo> findUpAll() {
        return repository.findByProductStatus(ProductStatusEnum.UP.getCode());
    }
}
