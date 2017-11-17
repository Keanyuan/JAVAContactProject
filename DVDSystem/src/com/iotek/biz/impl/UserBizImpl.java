package com.iotek.biz.impl;

import com.iotek.biz.UserBiz;
import com.iotek.dao.UserDao;
import com.iotek.dao.impl.UserDaoImpl;
import com.iotek.entity.Users;

public class UserBizImpl implements UserBiz {

	private UserDao userDao = null;
	public UserBizImpl() {
		userDao = new UserDaoImpl();
	}
	
	public Users login(Users user) {
		// TODO Auto-generated method stub
		return userDao.queryUser(user);
	}

	public int registerUser(Users user) {
		// TODO Auto-generated method stub
		if (userDao.queryUser(user) != null) {
			return 1; //用户名已存在
		} else {
			boolean res = userDao.saveUser(user);
			if (res) {
				return 2;//注册成功
			} else {
				return 3; //注册失败
			}
		}
	}

}
