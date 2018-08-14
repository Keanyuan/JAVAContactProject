package com.anjiplus.sell.utils;

import java.util.Random;

/**
 * @Auther: kean_qi
 * @Date: 2018/8/14 13:46
 * @Description: 生成随机数
 */
public class KeyUtil {

    /**
     * 生成唯一主键
     * 格式：时间+随机数
     * */
    public static synchronized String genUniqueKey(){
        Random random = new Random();
        Integer number = random.nextInt(900000) + 100000;
        return System.currentTimeMillis() + String.valueOf(number);
    }

}
