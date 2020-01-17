package com.anjiplus.mybatis.service.impl;

import com.anjiplus.mybatis.mapper.AccountMapper;
import com.anjiplus.mybatis.pojo.Account;
import com.anjiplus.mybatis.service.AccountService;
import com.anjiplus.mybatis.utils.ValidateUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @Auther: kean_qi
 * @Date: 2020/1/17 11:17
 * @Description:
 */
@Service
public class AccountServiceImpl implements AccountService {
    @Autowired
    private AccountMapper accountMapper;

    /**
     * 转账
     * @param accIn 收款账号
     * @param accOut 转账
     * @return
     */
    @Override
    public int transfer(Account accIn, Account accOut) {

        List<Account> accOutAccounts = accountMapper.selByAccnoPwd(accOut);

        if(ValidateUtil.isEmpty(accOutAccounts)){
            return ACCOUNT_PASSWORD_NOT_MATCH;
        }

        Account accOutaccount = accOutAccounts.get(0);
//        如果查询的信息 小于 转账输入的钱  月不足
        if(accOutaccount.getBanance() < accOut.getBanance()){
            return  ACCOUNT_BALANCE_NOT_ENOUGH;
        }
        List<Account> accInAccounts = accountMapper.selByAccnoName(accIn);
        if(ValidateUtil.isEmpty(accInAccounts)){
            return ACCOUNT_NAME_NOT_MATCH;
        }

        accOutaccount.setBanance(-accOut.getBanance());

        //转账人金额减少
        int index = accountMapper.updateBananceByAccno(accOutaccount);

        accIn.setBanance(accOut.getBanance());
        //手收账人金额增加
        index += accountMapper.updateBananceByAccno(accIn);

        //转账成功
        if(index == 2){
            return ACCOUNT_SUCCESS;
        }

        return ACCOUNT_ERROR;
    }
}
