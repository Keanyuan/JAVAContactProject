package com.anjiplus.sell.service.impl;

import com.anjiplus.sell.dataobject.SellerInfo;
import com.anjiplus.sell.repository.SellerInfoRepository;
import com.anjiplus.sell.service.SellerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @Auther: kean_qi
 * @Date: 2018/8/17 10:58
 * @Description: 买家端用户
 */
@Service
public class SellerServiceImpl implements SellerService {

    @Autowired
    private SellerInfoRepository infoRepository;

    @Override
    public SellerInfo findSellerInfoByOpenid(String openid) {
        SellerInfo sellerInfo = infoRepository.findByOpenid(openid);
        return sellerInfo;
    }

    @Override
    public SellerInfo findSellerInfoByUserNameAndPassword(String userName, String passWord) {


        List<SellerInfo> infoList = infoRepository.findByUsernameAndPassword(userName, passWord);
        if (infoList.size() == 0) {
            return null;
        }
        return infoList.get(0);
    }
}

