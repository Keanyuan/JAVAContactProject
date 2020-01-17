package com.anjiplus.mybatis.vo;

import com.anjiplus.mybatis.utils.ValidateUtil;

/**
 * @Auther: kean_qi
 * @Date: 2020/1/17 14:26
 * @Description:
 */
public class AccountVo {
    /*
    * 转账账户
    * */
    private String accOutNo;
    /*
    * 密码
    * */
    private String accOutPassword;
    /*
    * 转账金额
    * */
    private double accOutbanance;
    /*
    * 收款账户
    * */
    private String accInNo;
    /*
    * 收款姓名
    * */
    private String accInName;

    public String getAccOutNo() {
        return accOutNo;
    }

    public void setAccOutNo(String accOutNo) {
        this.accOutNo = accOutNo;
    }

    public String getAccOutPassword() {
        return accOutPassword;
    }

    public void setAccOutPassword(String accOutPassword) {
        this.accOutPassword = accOutPassword;
    }

    public double getAccOutbanance() {
        return accOutbanance;
    }

    public void setAccOutbanance(double accOutbanance) {
        this.accOutbanance = accOutbanance;
    }

    public String getAccInNo() {
        return accInNo;
    }

    public void setAccInNo(String accInNo) {
        this.accInNo = accInNo;
    }

    public String getAccInName() {
        return accInName;
    }

    public void setAccInName(String accInName) {
        this.accInName = accInName;
    }


    /**
     * 校验AccountVo是否为空
     * @param accountVo
     * @return
     */
    public static boolean checkVo(AccountVo accountVo){

        if(ValidateUtil.isEmpty(accountVo.getAccInName())){
            return  false;
        }

        if(ValidateUtil.isEmpty(accountVo.getAccInNo())){
            return  false;
        }

        if(ValidateUtil.isEmpty(accountVo.getAccOutbanance())){
            return  false;
        }

        if(ValidateUtil.isEmpty(accountVo.getAccOutNo())){
            return  false;
        }

        if(ValidateUtil.isEmpty(accountVo.getAccOutPassword())){
            return  false;
        }
        return true;
    }


}
