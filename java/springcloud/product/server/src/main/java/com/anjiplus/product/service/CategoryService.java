package com.anjiplus.product.service;

import com.anjiplus.product.dataobject.ProductCategory;
import java.util.List;

/*
 *
 * 类目接口
 * */
public interface CategoryService {


//查询多条
    List<ProductCategory> findByCategoryTypeIn(List<Integer> categoryTypeList);


}
