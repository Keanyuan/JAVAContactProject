package com.anjiplus.springboot.exception;

import com.anjiplus.springboot.enums.ResultEnum;

public class GirlException extends RuntimeException {
    private Integer code;

    public GirlException(Integer code, String message) {
        super(message);
        this.code = code;
    }



    public GirlException(ResultEnum resultEnum) {
        super(resultEnum.getMsg());
        this.code = resultEnum.getCode();
    }

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }
}

