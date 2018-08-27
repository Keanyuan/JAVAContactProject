package com.anjiplus.user.repository;

import com.anjiplus.user.dataobject.UserInfo;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @Auther: Kean
 * @Date: 2018/8/25 下午5:44
 * @Description:
 */
public interface UserInfoRepository extends JpaRepository<UserInfo, String> {
    UserInfo findByOpenid(String openid);
}
