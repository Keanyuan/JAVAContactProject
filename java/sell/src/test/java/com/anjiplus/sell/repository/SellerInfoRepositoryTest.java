package com.anjiplus.sell.repository;

import com.anjiplus.sell.dataobject.SellerInfo;
import com.anjiplus.sell.utils.KeyUtil;
import lombok.extern.slf4j.Slf4j;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@SpringBootTest
@Slf4j
public class SellerInfoRepositoryTest {

    @Autowired
    private SellerInfoRepository sellerInfoRepository;

    @Test
    public void save(){
        SellerInfo sellerInfo = new SellerInfo();
        sellerInfo.setSellerId(KeyUtil.genUniqueKey());
        sellerInfo.setUsername("admin");
        sellerInfo.setPassword("admin");
        sellerInfo.setOpenid("o87bg0tzNHuCaHL3lnR-kgcuUsAM");
        SellerInfo res = sellerInfoRepository.save(sellerInfo);
        Assert.assertNotNull(res);
    }

    @Test
    public void findByOpenid() {
        SellerInfo sellerInfo = sellerInfoRepository.findByOpenid("abc");
        log.info(sellerInfo.toString());
        Assert.assertEquals("abc", sellerInfo.getOpenid());

    }

    @Test
    public void  findByUserNameAndPassword(){

        List<SellerInfo> infoList = sellerInfoRepository.findByUsernameAndPassword("admin", "admin");
        if (infoList == null) {
            log.info("查询失败");
        }

        log.info(infoList.toString());
        Assert.assertNotNull(infoList);

    }
}