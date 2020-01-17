package com.anjiplus.mybatis.service.impl;

import com.anjiplus.mybatis.mapper.SysUserMapper;
import com.anjiplus.mybatis.mapper.SysUserMapperCustom;
import com.anjiplus.mybatis.pojo.SysUser;
import com.anjiplus.mybatis.service.UserService;
import com.github.pagehelper.PageHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.thymeleaf.util.StringUtils;
import tk.mybatis.mapper.entity.Example;

import java.util.List;

/**
 * @Auther: kean_qi
 * @Date: 2018/8/29 14:12
 * @Description:
 */
@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private SysUserMapper userMapper;

    //自定义mapper
    @Autowired
    private SysUserMapperCustom userMapperCustom;

    @Override
    @Transactional(propagation = Propagation.REQUIRED)
    public void saveUser(SysUser user) throws Exception {
        userMapper.insert(user);
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED)
    public void updateUser(SysUser user) throws Exception{
        userMapper.updateByPrimaryKeySelective(user);
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED)
    public void deleteUser(String userId) throws Exception{
        userMapper.deleteByPrimaryKey(userId);
    }

    @Override
    @Transactional(propagation = Propagation.SUPPORTS)
    public SysUser queryUserById(String userId) {
        return userMapper.selectByPrimaryKey(userId);
    }

    @Override
    @Transactional(propagation = Propagation.SUPPORTS)
    public List<SysUser> queryUserList(SysUser user) {
        Example example = new Example(SysUser.class);
        Example.Criteria criteria = example.createCriteria();
        if (!StringUtils.isEmptyOrWhitespace(user.getUsername())) {
            criteria.andLike("username", "%" + user.getUsername() + "%");
        }
        if (!StringUtils.isEmptyOrWhitespace(user.getNickname())){
            criteria.andLike("nickname", "%"+ user.getNickname() +"%");
        }
        List<SysUser> userList = userMapper.selectByExample(example);
        return userList;    }

    @Override
    @Transactional(propagation = Propagation.SUPPORTS)
    public List<SysUser> queryUserListPaged(SysUser user, Integer page, Integer pageSize) {
        // 开始分页
        PageHelper.startPage(page, pageSize);

        Example example = new Example(SysUser.class);
        Example.Criteria criteria = example.createCriteria();
        if (!StringUtils.isEmptyOrWhitespace(user.getNickname())) {
            criteria.andLike("nickname", "%" + user.getNickname() + "%");
        }
        //根据时间进行降序查询
//        example.orderBy("registTime").desc();
        //根据id进行升序查询
        example.orderBy("id").asc();
        List<SysUser> userList = userMapper.selectByExample(example);
        return userList;
    }

    @Override
    @Transactional(propagation = Propagation.SUPPORTS)
    public SysUser queryUserByIdCustom(String userId) {
        List<SysUser> userList = userMapperCustom.queryUserSimplyInfoById(userId);
        if (userList != null && !userList.isEmpty()) {
            return (SysUser)userList.get(0);
        }
        return null;
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void saveUserTransactional(SysUser user) {
        userMapper.insert(user);
        user.setIsDelete(1);
        userMapper.updateByPrimaryKeySelective(user);
    }
}
