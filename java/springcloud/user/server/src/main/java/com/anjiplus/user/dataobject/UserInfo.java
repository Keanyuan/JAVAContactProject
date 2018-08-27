package com.anjiplus.user.dataobject;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Id;

/**
 * @Auther: Kean
 * @Date: 2018/8/25 下午5:37
 * @Description:
 */
@Entity
@Data
public class UserInfo {

    @Id
    private String id;

    private String username;

    private String password;

    /**
     * 用户标识
     */
    private String openid;

    /**
     * 1.买家端 2.卖家端
     */
    private Integer role;

}
