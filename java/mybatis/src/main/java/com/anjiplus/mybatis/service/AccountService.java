package com.anjiplus.mybatis.service;

import com.anjiplus.mybatis.pojo.Account;

public interface AccountService {

    /**
     * 账号和密码不匹配
     */
    int ACCOUNT_PASSWORD_NOT_MATCH = 1;


    /**
     * 余额不足
     */
    int ACCOUNT_BALANCE_NOT_ENOUGH = 2;

    /**
     * 账号和姓名不匹配
     */
    int ACCOUNT_NAME_NOT_MATCH = 3;


    /**
     * 转账失败
     */
    int ACCOUNT_ERROR = 4;


    /**
     * 转账成功
     */
    int ACCOUNT_SUCCESS = 5;
    /**
     * 转账
     * @param accIn 收款账号
     * @param accOut 转账
     * @return
     */
    int transfer(Account accIn, Account accOut);
}
