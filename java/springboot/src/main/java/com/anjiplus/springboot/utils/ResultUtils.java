package com.anjiplus.springboot.utils;

import com.anjiplus.springboot.dao.Result;

public class ResultUtils {

    //成功
    public static Result success(Object object) {
        Result result = new Result();
        result.setCode(0);
        result.setMsg("成功");
        result.setData(object);
        return result;
    }


    public static  Result success(){
        return success(null);
    }

    //失败
    public static Result error(Integer code, String msg) {
        Result result = new Result();
        result.setCode(code);
        result.setMsg(msg);
        return result;
    }
}
