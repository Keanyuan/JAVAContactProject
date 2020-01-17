package com.anjiplus.mybatis.controller;

import com.anjiplus.mybatis.pojo.Account;
import com.anjiplus.mybatis.service.AccountService;
import com.anjiplus.mybatis.utils.ResultVOUtil;
import com.anjiplus.mybatis.vo.AccountVo;
import com.anjiplus.mybatis.vo.ResultVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Auther: kean_qi
 * @Date: 2020/1/17 13:58
 * @Description:
 */
@RestController
@RequestMapping("people")
public class AccountCountrller {
    @Autowired
    private AccountService accountService;

    @RequestMapping("/transfer")
    public ResultVO transfer(@RequestBody AccountVo accountVo){


        if(!AccountVo.checkVo(accountVo)){
            return ResultVOUtil.error(3, "数据缺失");
        }


        Account accIn = new Account();
        accIn.setAccNo(accountVo.getAccInNo());
        accIn.setName(accountVo.getAccInName());
        Account accOut = new Account();

        accOut.setAccNo(accountVo.getAccOutNo());
        accOut.setPassword(accountVo.getAccOutPassword());
        accOut.setBanance(accountVo.getAccOutbanance());

        int transfer = accountService.transfer(accIn, accOut);

        if(transfer == AccountService.ACCOUNT_SUCCESS){
            return ResultVOUtil.success();
        }

        return ResultVOUtil.error(transfer, "请求失败");




    }
}
