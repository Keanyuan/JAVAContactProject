package com.anjiplus.product.common;

import lombok.Data;

import java.math.BigDecimal;

/**
 * @Auther: kean_qi
 * @Date: 2018/8/23 10:57
 * @Description:
 */
@Data
public class ProductInfoOutput {

    private String productId;

    /** 名字. */
    private String productName;

    /** 单价. */
    private BigDecimal productPrice;

    /** 库存. */
    private Integer productStock;

    /** 描述. */
    private String productDescription;

    /** 小图. */
    private String productIcon;

    /** 状态, 0正常1下架. */
    private Integer productStatus;

    /** 类目编号. */
    private Integer categoryType;
}
