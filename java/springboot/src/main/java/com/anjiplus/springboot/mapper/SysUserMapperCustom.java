package com.anjiplus.springboot.mapper;

import com.anjiplus.springboot.pojo.SysUser;
import com.anjiplus.springboot.utils.MyMapper;

import java.util.List;

public interface SysUserMapperCustom {
    List<SysUser> queryUserSimplyInfoById(String id);
}