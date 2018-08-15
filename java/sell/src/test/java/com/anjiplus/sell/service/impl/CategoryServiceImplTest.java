package com.anjiplus.sell.service.impl;

import com.anjiplus.sell.dataobject.ProductCategory;
import lombok.extern.slf4j.Slf4j;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@SpringBootTest
@Slf4j
public class CategoryServiceImplTest {

    @Autowired
    private CategoryServiceImpl categoryService;

    @Test
    public void findOne() {
        ProductCategory productCategory = categoryService.findOne(3);
        log.info(productCategory.toString());
        Assert.assertEquals(new Integer(3), productCategory.getCategoryType());
    }

    @Test
    public void findAll() {
        List<ProductCategory> productCategoryList = categoryService.findAll();
        log.info(""+productCategoryList.toString());
        Assert.assertNotEquals(0, productCategoryList.size());
    }

    @Test
    public void findByCategoryTypeIn() {
        List<ProductCategory> productCategoryList = categoryService.findByCategoryTypeIn(Arrays.asList(3,4,6));
        log.info(""+productCategoryList.toString());
        Assert.assertNotEquals(0, productCategoryList.size());

    }

    @Test
    public void save() throws Exception{
        ProductCategory productCategory =  new ProductCategory("特色粥品", 7);
        ProductCategory result = categoryService.save(productCategory);
        Assert.assertNotNull(result);
    }
}