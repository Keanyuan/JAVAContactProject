package com.anjiplus.sell.contorller;

import com.anjiplus.sell.dataobject.ProductCategory;
import com.anjiplus.sell.dataobject.ProductInfo;
import com.anjiplus.sell.service.CategoryService;
import com.anjiplus.sell.service.ProductService;
import com.anjiplus.sell.utils.ResultVOUtil;
import com.anjiplus.sell.vo.ProductInfoVO;
import com.anjiplus.sell.vo.ProductVO;
import com.anjiplus.sell.vo.ResultVO;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/buyer/product")
public class BuyerProductController {

    @Autowired
    private ProductService productService;

    @Autowired
    private CategoryService categoryService;

    @GetMapping("/list")
    public ResultVO list(){
        //1.先查询所有上架商品
        List<ProductInfo> productInfoList = productService.findUpAll();
        //2.查询类目（一次性查询）
//        List<Integer> categoryTypeList = new ArrayList<>();
        //传统方法
//        for (ProductInfo productInfo: productInfoList){
//            categoryTypeList.add(productInfo.getCategoryType());
//        }
        //简化方法(java8 lambda表达式)
        List<Integer> categoryTypeList = productInfoList.stream()
                .map(e -> e.getCategoryType())
                .collect(Collectors.toList());
        List<ProductCategory> productCategoryList = categoryService.findByCategoryTypeIn(categoryTypeList);
        //3.数据拼装
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
