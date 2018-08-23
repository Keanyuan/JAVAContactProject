package com.anjiplus.order.enums;

import lombok.Getter;

/**
 * @Auther: kean_qi
 * @Date: 2018/8/14 10:36
 * @Description: 订单状态
 */
@Getter
public enum OrderStatusEnum implements CodeEnum{

    NEW(0, "新订单"),
    FINISHED(1, "完结"),
    CANCEL(2, "已取消"),
    ;

    private Integer code;

    private String message;

    OrderStatusEnum(Integer code, String message) {
        this.code = code;
        this.message = message;
    }
}
