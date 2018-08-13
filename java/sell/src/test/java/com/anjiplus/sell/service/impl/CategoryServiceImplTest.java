package com.anjiplus.sell.service.impl;

import com.anjiplus.sell.dataobject.ProductCategory;
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
public class CategoryServiceImplTest {

    @Autowired
    private CategoryServiceImpl categoryService;

    @Test
    public void findOne() {
        ProductCategory productCategory = categoryService.findOne(3);
        Assert.assertEquals(new Integer(4), productCategory.getCategoryType());
    }

    @Test
    public void findAll() {
        List<ProductCategory> productCategoryList = categoryService.findAll();
        Assert.assertNotEquals(0, productCategoryList.size());
    }

    @Test
    public void findByCategoryTypeIn() {
        List<ProductCategory> productCategoryList = categoryService.findByCategoryTypeIn(Arrays.asList(3,4,6));
        Assert.assertNotEquals(0, productCategoryList.size());

    }

    @Test
    public void save() {
        ProductCategory productCategory =  new ProductCategory("今日最佳", 10);
        ProductCategory result = categoryService.save(productCategory);
        Assert.assertNotNull(result);
    }
}