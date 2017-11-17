package com.iotek.biz;

import com.iotek.entity.Users;

//用户接口
public interface UserBiz {
	//用户登录
	public Users login(Users user);
	//用户注册
	public int registerUser(Users user);
	
}
