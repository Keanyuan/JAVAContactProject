package com.anjiplus.mybatis.pojo;

/**
 * @Auther: kean_qi
 * @Date: 2020/1/17 10:45
 * @Description:
 */
public class Account {
    private int id;
    private String accNo;
    private String password;
    private double banance;
    private String name;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getAccNo() {
        return accNo;
    }

    public void setAccNo(String accNo) {
        this.accNo = accNo;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getBanance() {
        return banance;
    }

    public void setBanance(double banance) {
        this.banance = banance;
    }
}
