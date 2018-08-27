package com.anjiplus.user.enums;

import lombok.Getter;

/**
 * @Auther: Kean
 * @Date: 2018/8/25 下午6:09
 * @Description:
 */
@Getter
public enum  RoleEnum {

    BUYER(1, "买家"),
    SELLER(2, "买家"),
    ;
    private Integer code;

    private String message;

    RoleEnum(Integer code, String message) {
        this.code = code;
        this.message = message;
    }
}
