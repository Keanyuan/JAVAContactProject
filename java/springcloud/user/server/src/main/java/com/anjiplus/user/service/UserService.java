package com.anjiplus.user.service;

import com.anjiplus.user.dataobject.UserInfo;

/**
 * @Auther: Kean
 * @Date: 2018/8/25 下午5:46
 * @Description:
 */
public interface UserService {

    /**
     * 通过openid查询
     * @param openid
     * @return
     */
    UserInfo findByOpenid(String openid);
}
