package com.anjiplus.user.controller;

import com.anjiplus.user.constant.CookieConstant;
import com.anjiplus.user.constant.RedisConstant;
import com.anjiplus.user.dataobject.UserInfo;
import com.anjiplus.user.enums.ResultEnum;
import com.anjiplus.user.enums.RoleEnum;
import com.anjiplus.user.service.UserService;
import com.anjiplus.user.utils.CookieUtil;
import com.anjiplus.user.utils.ResultVOUtil;
import com.anjiplus.user.vo.ResultVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.UUID;
import java.util.concurrent.TimeUnit;

/**
 * @Auther: Kean
 * @Date: 2018/8/25 下午5:49
 * @Description:
 */
@RestController
@RequestMapping("/login")
public class LoginController {

    @Autowired
    private UserService userService;

    @Autowired
    private StringRedisTemplate redisTemplate;

    /**
     * 买家登录
     * @param openid
     * @param response
     * @return
     */
    @GetMapping("/buyer")
    public ResultVO buyer(@RequestParam("openid") String openid,
                          HttpServletResponse response){

        //1.openid 和 数据库里的数据是否匹配
        UserInfo userInfo = userService.findByOpenid(openid);
        if (userInfo == null){
            return ResultVOUtil.error(ResultEnum.LOGIN_FAIL.getCode(),ResultEnum.LOGIN_FAIL.getMessage());
        }

        //2、判断角色
        if (RoleEnum.BUYER.getCode() != userInfo.getRole()){

            return ResultVOUtil.error(ResultEnum.LOGIN_ERROR.getCode(),ResultEnum.LOGIN_ERROR.getMessage());
        }


        //3、cookie里设置openid=abc
        CookieUtil.set(response, CookieConstant.OPENID, openid, CookieConstant.expire);

        return ResultVOUtil.success();
    }


    /**
     * 卖家登录
     * @param openid
     * @param response
     * @return
     */
    @GetMapping("/seller")
    public ResultVO seller(@RequestParam("openid") String openid,
                          HttpServletRequest request,
                          HttpServletResponse response){

        //判断是否登录
        //获取cook 判断cook的值和 Redis中是否有值
        Cookie cookie = CookieUtil.get(request, CookieConstant.TOKEN);
        if (cookie != null &&
                !StringUtils.isEmpty(redisTemplate.opsForValue().get(String.format(RedisConstant.TOKEN_TEMPLATE,cookie.getValue())))) {
            return ResultVOUtil.success();
        }


        //1.openid 和 数据库里的数据是否匹配
        UserInfo userInfo = userService.findByOpenid(openid);
        if (userInfo == null){
            return ResultVOUtil.error(ResultEnum.LOGIN_FAIL.getCode(),ResultEnum.LOGIN_FAIL.getMessage());
        }

        //2、判断角色
        if (RoleEnum.SELLER.getCode() != userInfo.getRole()){

            return ResultVOUtil.error(ResultEnum.LOGIN_ERROR.getCode(),ResultEnum.LOGIN_ERROR.getMessage());
        }


        //3.redis设置key=UUID, value=xyz
        String token = UUID.randomUUID().toString();
        Integer expire = CookieConstant.expire;
        redisTemplate.opsForValue().set(String.format(RedisConstant.TOKEN_TEMPLATE,token),
                openid,
                expire,
                TimeUnit.SECONDS);

        //4、cookie里设置openid=xyz
        CookieUtil.set(response, CookieConstant.TOKEN, token, expire);



        return ResultVOUtil.success();
    }
}
