package com.anjiplus.sell.repository;

import com.anjiplus.sell.dataobject.SellerInfo;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface SellerInfoRepository extends JpaRepository<SellerInfo, String> {
    SellerInfo findByOpenid(String openid);
    List<SellerInfo> findByUsernameAndPassword(String userName, String passWord);
}
