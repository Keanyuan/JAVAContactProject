package com.anjiplus.sell.service.impl;

import com.anjiplus.sell.dataobject.ProductInfo;
import com.anjiplus.sell.enums.ProductStatusEnum;
import lombok.extern.slf4j.Slf4j;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.test.context.junit4.SpringRunner;

import java.math.BigDecimal;
import java.util.List;

import static org.junit.Assert.*;


@RunWith(SpringRunner.class)
@SpringBootTest
@Slf4j
public class ProductServiceImplTest {

    @Autowired
    private  ProductServiceImpl productService;

    @Test
    public void findOne() throws Exception{
        ProductInfo productInfo = productService.findOne("123456");
        Assert.assertEquals("123456", productInfo.getProductId());
    }

    @Test
    public void findUpAll() throws Exception{
        List<ProductInfo> productInfoList = productService.findUpAll();
        log.info("productInfoList.size :{}",productInfoList.size());
        Assert.assertNotEquals(0, productInfoList.size());
    }

    @Test
    public void findAll() throws Exception{
        PageRequest request = PageRequest.of(1, 2);
        Page<ProductInfo> productInfoPage = productService.findAll(request);
        log.info("productInfoPage " + productInfoPage.getContent().size());
        Assert.assertNotEquals(0, productInfoPage.getTotalElements());


    }

    @Test
    public void save() throws Exception{
        ProductInfo productInfo = new ProductInfo();
        productInfo.setProductId("1000003");
        productInfo.setProductName("豆浆");
        productInfo.setProductPrice(new BigDecimal(1.0));
        productInfo.setProductStock(100);
        productInfo.setProductDescription("很好吃的虾");
        productInfo.setProductIcon("http://xxxxx.jpg");
        productInfo.setProductStatus(ProductStatusEnum.UP.getCode());
        productInfo.setCategoryType(1);

        ProductInfo result = productService.save(productInfo);
        Assert.assertNotNull(result);
    }
}