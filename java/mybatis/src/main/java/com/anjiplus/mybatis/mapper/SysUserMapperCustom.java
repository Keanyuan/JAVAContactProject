package com.anjiplus.mybatis.mapper;


import com.anjiplus.mybatis.pojo.SysUser;

import java.util.List;

public interface SysUserMapperCustom {
//    通过Id查询用户信息
    List<SysUser> queryUserSimplyInfoById(String id);

}