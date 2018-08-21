package com.anjiplus.product.controller;

import com.anjiplus.product.dataobject.ProductCategory;
import com.anjiplus.product.dataobject.ProductInfo;
import com.anjiplus.product.service.CategoryService;
import com.anjiplus.product.service.ProductService;
import com.anjiplus.product.utils.ResultVOUtil;
import com.anjiplus.product.vo.ProductInfoVO;
import com.anjiplus.product.vo.ProductVO;
import com.anjiplus.product.vo.ResultVO;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @Auther: Kean
 * @Date: 2018/8/21 下午11:25
 * @Description:
 */

@RestController
@RequestMapping("/product")
public class ProductController {

    @Autowired
    private ProductService productService;

    @Autowired
    private CategoryService categoryService;

    /**
     * 1.查询在架商品
     * 2.获取类目type列表
     * 3.查询类目
     * 4.构造函数
     */
    @GetMapping("/list")
    public ResultVO list(){
        //1.先查询所有上架商品
        List<ProductInfo> productInfoList = productService.findUpAll();

        //2.查询类目（一次性查询
        List<Integer> categoryTypeList = productInfoList.stream()
                .map(ProductInfo::getCategoryType)
                .collect(Collectors.toList());

        //3.查询类目
        List<ProductCategory> productCategoryList = categoryService.findByCategoryTypeIn(categoryTypeList);

        //4.构造函数
        List<ProductVO> productVOList = new ArrayList<>();
        //遍历类目
        for (ProductCategory productCategory: productCategoryList){
            ProductVO productVO = new ProductVO();
            productVO.setCategoryType(productCategory.getCategoryType());
            productVO.setCategoryName(productCategory.getCategoryName());

            List<ProductInfoVO> productInfoVOList = new ArrayList<>();
            for (ProductInfo productInfo: productInfoList){
                if (productInfo.getCategoryType().equals(productCategory.getCategoryType())){
                    ProductInfoVO productInfoVO = new ProductInfoVO();
//                    将 p roductInfo copy到productInfoVO中
                    BeanUtils.copyProperties(productInfo, productInfoVO);
                    productInfoVOList.add(productInfoVO);
                }
            }
//            将productInfoVOList 添加到对应对象中
            productVO.setProductInfoVOList(productInfoVOList);

//            将productVO添加到数组中
            productVOList.add(productVO);
        }
        return ResultVOUtil.success(productVOList);




    }
}
