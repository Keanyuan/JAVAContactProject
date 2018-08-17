package com.anjiplus.sell.handler;

import com.anjiplus.sell.config.ProjectUrlConfig;
import com.anjiplus.sell.enums.ResultEnum;
import com.anjiplus.sell.exception.SellException;
import com.anjiplus.sell.exception.SellerAuthorizeException;
import com.anjiplus.sell.utils.ResultVOUtil;
import com.anjiplus.sell.vo.ResultVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.servlet.ModelAndView;

import java.util.HashMap;
import java.util.Map;
import java.util.NoSuchElementException;

/**
 * @Auther: kean_qi
 * @Date: 2018/8/17 15:19
 * @Description:
 */
@ControllerAdvice
public class SellExceptionHandler {

    @Autowired
    private ProjectUrlConfig projectUrlConfig;

    //拦截登录异常
    //http://sell.natapp4.cc/sell/wechat/qrAuthorize?returnUrl=http://sell.natapp4.cc/sell/seller/login
    @ExceptionHandler(value = SellerAuthorizeException.class)
    public ModelAndView handlerAuthorizeException() {
//        return new ModelAndView("redirect:"
//                .concat(projectUrlConfig.getWechatOpenAuthorize())
//                .concat("/sell/wechat/qrAuthorize")
//                .concat("?returnUrl=")
//                .concat(projectUrlConfig.getSell())
//                .concat("/sell/seller/login"));
        Map<String, Object> map = new HashMap<>();
        map.put("openId","");
        return  new ModelAndView("login/login",map);
    }

    @ExceptionHandler(value = SellException.class)
    @ResponseBody
    public ResultVO handlerSellerException(SellException e) {
        return ResultVOUtil.error(e.getCode(), e.getMessage());
    }


    @ExceptionHandler(value = NoSuchElementException.class)
    @ResponseBody
    public ResultVO handlerNoSuchException(NoSuchElementException e) {
        return ResultVOUtil.error(ResultEnum.PRODUCT_NOT_EXIST.getCode(), ResultEnum.PRODUCT_NOT_EXIST.getMessage());
    }


    //    @ResponseStatus(HttpStatus.FORBIDDEN)  返回网络状态
//    @ExceptionHandler(value = NoSuchElementException.class)
//    @ResponseStatus(HttpStatus.FORBIDDEN)
//    public ResultVO handlderNoSuchException(NoSuchElementException e) {
//        return ResultVOUtil.error(ResultEnum.PRODUCT_NOT_EXIST.getCode(), ResultEnum.PRODUCT_NOT_EXIST.getMessage());
//    }


}
