package com.anjiplus.sell.service;

/**
 * @Auther: Kean
 * @Date: 2018/8/18 下午6:00
 * @Description:
 */
public interface SecKillService {

    public String querySecKillProductInfo(String productId);

    public void orderProductMockDiffUser(String productId);
}
