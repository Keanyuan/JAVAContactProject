package com.anjiplus.springboot.service.impl;

import com.anjiplus.springboot.mapper.SysUserMapper;
import com.anjiplus.springboot.pojo.SysUser;
import com.anjiplus.springboot.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private SysUserMapper userMapper;

//    @Autowired
//    private SysUserMapperCustom userMapperCustom;

    @Override
    public void saveUser(SysUser user) throws Exception {
        userMapper.insert(user);
    }

    @Override
    public void updateUser(SysUser user) {

    }

    @Override
    public void deleteUser(String userId) {

    }

    @Override
    public SysUser queryUserById(String userId) {
        return null;
    }

    @Override
    public List<SysUser> queryUserList(SysUser user) {
        return null;
    }

    @Override
    public List<SysUser> queryUserListPaged(SysUser user, Integer page, Integer pageSize) {
        return null;
    }

    @Override
    public SysUser queryUserByIdCustom(String userId) {
        return null;
    }

    @Override
    public void saveUserTransactional(SysUser user) {

    }
}
