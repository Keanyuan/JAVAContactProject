package com.anjiplus.sell.service;

import com.anjiplus.sell.dataobject.SellerInfo;

public interface SellerService {
    /**
     * 通过openid查询卖家端信息
     * @param openid
     * @return
     */
    SellerInfo findSellerInfoByOpenid(String openid);


    SellerInfo findSellerInfoByUserNameAndPassword(String userName, String passWord);
}
