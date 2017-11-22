package com.iotek.dao.impl;

import java.util.ArrayList;
import java.util.List;

import com.iotek.dao.RecordDao;
import com.iotek.entity.Record;
import com.iotek.entity.Record2;

public class RecordDaoImpl extends BaseDao implements RecordDao {

	@Override
	public Record queryRecordById(int rid) {
		String sql = "select id,uid,did,lendTime,returnTime from record where id=?";
		List<Record> rList = null;
		List<Object> params = new ArrayList<Object>();
		params.add(rid);
		try {
			rList = this.operQuery(sql, params, Record.class);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		if (rList.size() > 0) {
			return rList.get(0);
		}
		return null;
	}

	@Override
	public boolean saveRecord(Record record) {
		String sql = "insert into record(uid,did,lendTime,returnTime)values(?,?,?,?)";
		List<Object> params = new ArrayList<Object>();
		params.add(record.getUid());
		params.add(record.getDid());
		params.add(record.getLendTime());
		params.add(record.getReturnTime());
		return this.operUpdate(sql, params);
	}

	@Override
	public boolean updateRecord(Record record) {
		String sql = "update record set uid=?,did=?,lendTime=?,returnTime=? where id=?";
		List<Object> params = new ArrayList<Object>();
		params.add(record.getUid());
		params.add(record.getDid());
		params.add(record.getLendTime());
		params.add(record.getReturnTime());
		params.add(record.getId());
		return this.operUpdate(sql, params);
	}

	@Override
	public List<Record2> queryAllRecords() {
		// TODO Auto-generated method stub
		String sql = "select r.id,d.id as did,u.uname,d.dname,r.lendTime,r.returnTime from users u,dvds d,record r where u.id=r.uid and d.id=r.did";
		List<Record2> data = null;
		try {
			data = this.operQuery(sql, null, Record2.class);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return data;
	}

	@Override
	public List<Record2> queryRecordByUname(String uname) {
		String sql = "select r.id,d.id as did,u.uname,d.dname,r.lendTime,r.returnTime from users u,dvds d,record r where u.id=r.uid and d.id=r.did and uname=?";
		List<Record2> data = null;
		List<Object> params = new ArrayList<Object>();
		params.add(uname);
		try {
			data = this.operQuery(sql, params, Record2.class);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return data;
	}

	@Override
	public List<Record2> queryRecordByDname(String dname) {
		String sql = "select r.id,d.id as did,u.uname,d.dname,r.lendTime,r.returnTime from users u,dvds d,record r where u.id=r.uid and d.id=r.did and dname=?";
		List<Record2> data = null;
		List<Object> params = new ArrayList<Object>();
		params.add(dname);
		try {
			data = this.operQuery(sql, params, Record2.class);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return data;
	}

	/**
	 * 查看已归还记录和未归还记录
	 * 2017年11月17日 下午2:05:50 KeanQ
	 * @param flag
	 * @param uname
	 * @return
	 */
	@Override
	public List<Record2> queryRecordByReturnTime(Boolean flag, String uname) {
		
		String sql = null;
		if (flag) {
			sql = "select r.id,d.id as did,u.uname,d.dname,r.lendTime,r.returnTime from users u,dvds d,record r where u.id=r.uid and d.id=r.did and returnTime is not null and uname=?";
		} else {
			sql = "select r.id,d.id as did,u.uname,d.dname,r.lendTime,r.returnTime from users u,dvds d,record r where u.id=r.uid and d.id=r.did and returnTime is null and uname=?";
		}
		List<Object> params = new ArrayList<>();
		params.add(uname);
		List<Record2> rList = null;
		try {
			rList = this.operQuery(sql, params, Record2.class);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return rList;
	}

}
