package com.anjiplus.sell.dataobject.mapper;

import com.anjiplus.sell.dataobject.ProductCategory;
import lombok.extern.slf4j.Slf4j;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.HashMap;
import java.util.Map;

import static org.junit.Assert.*;

/**
 * @Auther: Kean
 * @Date: 2018/8/17 下午11:14
 * @Description:
 */
@RunWith(SpringRunner.class)
@SpringBootTest
@Slf4j
public class ProductCategoryMapperTest {

    @Autowired
    private ProductCategoryMapper mapper;

    @Test
    public void insertByMap() throws Exception{
        Map<String, Object> map = new HashMap<>();
        map.put("category_name", "养生堂");
        map.put("category_type", 7);
        int result = mapper.insertByMap(map);
        Assert.assertEquals(1, result);
    }


    @Test
    public void insertByObject() throws Exception{

        ProductCategory productCategory = new ProductCategory();
        productCategory.setCategoryName("美容养颜");
        productCategory.setCategoryType(8);
        int result = mapper.insertByObject(productCategory);
        Assert.assertEquals(1, result);
    }


    @Test
    public void findByCatrgoryType() throws Exception{

        ProductCategory productCategory = mapper.findByCatrgoryType(8);

        Assert.assertNotNull(productCategory);
    }


}