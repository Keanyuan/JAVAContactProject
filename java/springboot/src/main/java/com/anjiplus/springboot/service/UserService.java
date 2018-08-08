package com.anjiplus.springboot.service;

import com.anjiplus.springboot.pojo.SysUser;

import java.util.List;

public interface UserService {
//    保存
    public void saveUser(SysUser user) throws Exception;
//    更新
    public void updateUser(SysUser user);
//    删除
    public void deleteUser(String userId);
//    根据用户ID查询用户
    public SysUser queryUserById(String userId);
//    根据条件查询用户列表
    public List<SysUser> queryUserList(SysUser user);

    public List<SysUser> queryUserListPaged(SysUser user, Integer page, Integer pageSize);

    public SysUser queryUserByIdCustom(String userId);

    public void saveUserTransactional(SysUser user);

}
