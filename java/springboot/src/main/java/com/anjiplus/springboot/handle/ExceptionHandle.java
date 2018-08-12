package com.anjiplus.springboot.handle;

import com.anjiplus.springboot.exception.GirlException;
import com.anjiplus.springboot.utils.AjJSONResult;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;


@ControllerAdvice
public class ExceptionHandle {

    @ExceptionHandler(value = Exception.class)
    @ResponseBody
    public AjJSONResult errorHandler(Exception e){
        if (e instanceof GirlException){
            GirlException girlException = (GirlException) e;
            return AjJSONResult.error(girlException.getCode(), girlException.getMessage());
        } else {
            return AjJSONResult.errorMsg("系统异常");
        }
    }


}
