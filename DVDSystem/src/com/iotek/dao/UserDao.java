package com.iotek.dao;
import com.iotek.entity.Users;
//用户接口
public interface UserDao {
	public boolean saveUser(Users user);//添加用户
	public boolean delUser(int id);//删除用户
	public boolean updateUser(Users user);//更新用户
	public Users queryUser(Users user);//查询用户
}
