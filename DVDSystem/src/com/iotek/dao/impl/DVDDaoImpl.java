package com.iotek.dao.impl;

import java.util.ArrayList;
import java.util.List;

import com.iotek.dao.DVDDao;
import com.iotek.entity.DVD;

public class DVDDaoImpl extends BaseDao implements DVDDao {

	@Override
	public boolean saveDVD(DVD dvd) {
		String sql = "insert into dvds(dname,dcount,status)values(?,?,?)";
		List<Object> params = new ArrayList<Object>();
		params.add(dvd.getDname());
		params.add(dvd.getDcount());
		params.add(dvd.getStatus());
		return this.operUpdate(sql, params);
	}

	@Override
	public boolean updateDVD(DVD dvd) {
		String sql = "update dvds set dname=?,dcount=?,status=? where id=?";
		List<Object> params = new ArrayList<Object>();
		params.add(dvd.getDname());
		params.add(dvd.getDcount());
		params.add(dvd.getStatus());
		params.add(dvd.getId());
		return this.operUpdate(sql, params);
	}

	@Override
	public boolean delDVD(int did) {
		String sql = "delete from dvds where id=?";
		List<Object> params = new ArrayList<Object>();
		params.add(did);
		return this.operUpdate(sql, params);
	}

	@Override
	public List<DVD> queryDVDs() {
		String sql = "select id,dname,dcount,status from dvds";
		List<DVD> dList = null;
		try {
			dList = this.operQuery(sql, null, DVD.class);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return dList;
	}

	@Override
	public List<DVD> queryDVDByName(String name) {
		String sql = "select id,dname,dcount,status from dvds where dname=?";
		List<DVD> dList = null;
		List<Object> params = new ArrayList<Object>();
		params.add(name);
		try {
			dList = this.operQuery(sql, params, DVD.class);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return dList;
	}

	/**
	 * 查询热门DVD，即借出次数前五位的DVD
	 */
	@Override
	public List<DVD> querySortDVDByLimit(int index, int number) {
		String sql = "select id,dname,dcount,status from dvds order by dcount desc limit " + index + "," + number;
		List<DVD> dList = null;
		try {
			dList = this.operQuery(sql, null, DVD.class);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return dList;
	}

	@Override
	public DVD queryDVDById(int did) {
		String sql = "select id,dname,dcount,status from dvds where id=?";
		List<DVD> dList = null;
		List<Object> params = new ArrayList<Object>();
		params.add(did);
		try {
			dList = this.operQuery(sql, params, DVD.class);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		if (dList.size() > 0) {
			return dList.get(0);
		}
		return null;
	}

	@Override
	public List<DVD> queryDVDByStatus(int status) {
		String sql = "select id,dname,dcount,status from dvds where status=?";
		List<DVD> dList = null;
		List<Object> params = new ArrayList<Object>();
		params.add(status);
		try {
			dList = this.operQuery(sql, params, DVD.class);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return dList;
	}

}
