package com.anjiplus.sell.service;

import com.anjiplus.sell.dataobject.ProductCategory;

import java.util.List;

/*
 *
 * 类目接口
 * */
public interface CategoryService {
//    单个查询
    ProductCategory findOne(Integer categoryId);
//    查询列表
    List<ProductCategory> findAll();

//查询多条
    List<ProductCategory> findByCategoryTypeIn(List<Integer> categoryTypeList);

//    新增和更新
    ProductCategory save(ProductCategory productCategory);


}
