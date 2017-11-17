package com.iotek.dao.impl;

import java.util.ArrayList;
import java.util.List;

import com.iotek.dao.UserDao;
import com.iotek.entity.Users;

public class UserDaoImpl extends BaseDao implements UserDao {

	@Override
	public boolean saveUser(Users user) {
		// TODO Auto-generated method stub
		String sql = "insert into users(uname,upass,type)values(?,?,?)";
		List<Object> params = new ArrayList<Object>();
		params.add(user.getUname());
		params.add(user.getUpass());
		params.add(user.getType());
		return this.operUpdate(sql, params);
	}

	@Override
	public boolean delUser(int id) {
		// TODO Auto-generated method stub
		String sql = "delete from users where id=?";
		List<Object> params = new ArrayList<Object>();
		params.add(id);
		return this.operUpdate(sql, params);
	}

	@Override
	public boolean updateUser(Users user) {
		// TODO Auto-generated method stub
		String sql = "update users set uname=?,upass=?,type=? where id=?";
		List<Object> params = new ArrayList<Object>();
		params.add(user.getUname());
		params.add(user.getUpass());
		params.add(user.getType());
		params.add(user.getId());
		return this.operUpdate(sql, params);
	}

	@Override
	public Users queryUser(Users user) {
		List<Users> uList = null;
		String sql = "select id,uname,upass,type from users where uname=? and upass=? and type=?";
		List<Object> params = new ArrayList<Object>();
		params.add(user.getUname());
		params.add(user.getUpass());
		params.add(user.getType());
		try {
			uList = this.operQuery(sql, params, Users.class);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		if (uList.size() > 0) {
			return uList.get(0);
		}
		
		return null;
	}

}
