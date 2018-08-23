package com.anjiplus.product.vo;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.io.Serializable;
import java.util.List;

/*
* 商品（包含类目）
* */
@Data
public class ProductVO implements Serializable {

    private static final long serialVersionUID = -7573094259378565323L;
    @JsonProperty("name")
    private String categoryName;

    @JsonProperty("type")
    private Integer categoryType;

    @JsonProperty("foods")
    private List<ProductInfoVO> productInfoVOList;
}
