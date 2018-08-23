package com.anjiplus.product.dto;

import lombok.Data;

/**
 * @Auther: kean_qi
 * @Date: 2018/8/14 13:57
 * @Description: 购物车
 */
@Data
public class CartDTO {
    /** 商品Id. */
    private String productId;
    /** 数量. */
    private Integer productQuantity;

    public CartDTO(String productId, Integer productQuantity) {
        this.productId = productId;
        this.productQuantity = productQuantity;
    }
}
