package com.anjiplus.mybatis.mapper;

import com.anjiplus.mybatis.pojo.Account;
import com.anjiplus.mybatis.pojo.People;
import com.anjiplus.mybatis.utils.MyMapper;

import java.util.List;
import java.util.Map;

public interface AccountMapper extends MyMapper<People> {

    /**
     * 根据账号和密码查询账户信息
     * @param account 账户信息
     * @return
     */
    List<Account> selByAccnoPwd(Account account);

    /**
     * 根据账号和姓名查询账户信息
     * @param account 账户信息
     * @return
     */
    List<Account> selByAccnoName(Account account);


    /**
     * 根据accNo修改账号余额
     * @param account 账户信息
     * @return
     */
    int updateBananceByAccno(Account account);
}