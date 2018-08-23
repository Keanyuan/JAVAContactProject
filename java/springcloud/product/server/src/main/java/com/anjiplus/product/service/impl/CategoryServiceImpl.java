package com.anjiplus.product.service.impl;

import com.anjiplus.product.dataobject.ProductCategory;
import com.anjiplus.product.repository.ProductCategoryRepository;
import com.anjiplus.product.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @Auther: Kean
 * @Date: 2018/8/21 下午11:59
 * @Description:
 */
@Service
public class CategoryServiceImpl implements CategoryService {

    @Autowired
    private ProductCategoryRepository repository;
    @Override
    public List<ProductCategory> findByCategoryTypeIn(List<Integer> categoryTypeList) {
        return repository.findByCategoryTypeIn(categoryTypeList);
    }
}
