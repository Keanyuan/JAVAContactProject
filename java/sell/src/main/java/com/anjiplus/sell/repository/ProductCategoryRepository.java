package com.anjiplus.sell.repository;

import com.anjiplus.sell.dataobject.ProductCategory;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ProductCategoryRepository extends JpaRepository <ProductCategory, Integer>{
//    通过ID的list查询
    List<ProductCategory> findByCategoryTypeIn(List<Integer> categoryTypeList);
}
