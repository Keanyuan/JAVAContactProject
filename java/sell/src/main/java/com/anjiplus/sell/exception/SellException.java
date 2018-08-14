package com.anjiplus.sell.exception;

import com.anjiplus.sell.enums.ResultEnum;
import lombok.Data;

/**
 * @Auther: kean_qi
 * @Date: 2018/8/14 13:35
 * @Description: 异常处理
 */
@Data
public class SellException extends RuntimeException {
    private Integer code;

    public SellException(ResultEnum resultEnum) {
        super(resultEnum.getMessage());
        this.code = resultEnum.getCode();
    }

    public SellException(Integer code, String message) {
        super(message);
        this.code = code;
    }
}
