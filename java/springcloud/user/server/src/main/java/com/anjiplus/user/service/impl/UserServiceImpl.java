package com.anjiplus.user.service.impl;

import com.anjiplus.user.dataobject.UserInfo;
import com.anjiplus.user.repository.UserInfoRepository;
import com.anjiplus.user.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @Auther: Kean
 * @Date: 2018/8/25 下午5:48
 * @Description:
 */
@Service
public class UserServiceImpl implements UserService {


    @Autowired
    private UserInfoRepository repository;

    /**
     * 通过openid查询用户信息
     * @param openid
     * @return
     */
    @Override
    public UserInfo findByOpenid(String openid) {

        return repository.findByOpenid(openid);
    }
}
