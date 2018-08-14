package com.anjiplus.sell.repository;

import com.anjiplus.sell.dataobject.ProductCategory;
import lombok.extern.slf4j.Slf4j;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@SpringBootTest
@Slf4j
public class ProductCategoryRepositoryTest {

    @Autowired
    private ProductCategoryRepository repository;

    @Test
    public void findOne() {
        ProductCategory productCategory =  repository.findById(1).get();
        System.out.println(productCategory.toString());
    }

//    测试回滚
//    @Transactional(rollbackFor = Exception.class)
    @Test
    public void saveTest(){
//        ProductCategory productCategory =  repository.findById(2).get();
//        productCategory.setCategoryName("一本书");
//        repository.save(productCategory);
        ProductCategory productCategory =  new ProductCategory("午餐类", 5);
        ProductCategory result =  repository.save(productCategory);
        Assert.assertNotNull(result);
    }

    @Test
    public void findByCategoryTypeInTest(){
        List<Integer> list = Arrays.asList(3,5);
        List<ProductCategory> result = repository.findByCategoryTypeIn(list);
        System.out.println(result.size());
        Assert.assertNotEquals(0, result.size());
    }

}