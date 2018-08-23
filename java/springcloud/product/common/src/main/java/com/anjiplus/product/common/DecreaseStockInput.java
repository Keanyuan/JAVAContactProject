package com.anjiplus.product.common;

import lombok.Data;

/**
 * @Auther: kean_qi
 * @Date: 2018/8/23 10:54
 * @Description:
 */
@Data
public class DecreaseStockInput {
    /** 商品Id. */
    private String productId;
    /** 数量. */
    private Integer productQuantity;

    public DecreaseStockInput() {
    }

    public DecreaseStockInput(String productId, Integer productQuantity) {
        this.productId = productId;
        this.productQuantity = productQuantity;
    }
}
