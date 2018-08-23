package com.anjiplus.order.vo;

import lombok.Data;

import java.io.Serializable;

/*
* HTTP 返回的请求最外层对象
* */
@Data
public class ResultVO<T> implements Serializable {

    private static final long serialVersionUID = 9013246807411199661L;

    /** 错误码. */
    private Integer code;

    /** 提示信息. */
    private String msg;

    /** 具体内容. */
    private T data;
}
