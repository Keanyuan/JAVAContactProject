package com.anjiplus.product.repository;

import com.anjiplus.product.ProductApplicationTests;
import com.anjiplus.product.dataobject.ProductInfo;
import lombok.extern.slf4j.Slf4j;
import org.junit.Assert;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

import static org.junit.Assert.*;

/**
 * @Auther: Kean
 * @Date: 2018/8/21 下午11:41
 * @Description:
 */
@Component
@Slf4j
public class ProductInfoRepositoryTest extends ProductApplicationTests {

    @Autowired
    private ProductInfoRepository productInfoRepository;

    @Test
    public void findByProductStatus() throws Exception{
        List<ProductInfo>  list = productInfoRepository.findByProductStatus(0);
        Assert.assertTrue(list.size() > 0);

    }
}