package com.iotek.biz.impl;

import java.util.List;

import com.iotek.biz.RecordBiz;
import com.iotek.dao.RecordDao;
import com.iotek.dao.impl.RecordDaoImpl;
import com.iotek.entity.Record2;

public class RecordBizImpl implements RecordBiz {
	private RecordDao recordDao = null;
	
	public RecordBizImpl() {
		recordDao = new RecordDaoImpl();
	}
	
	
	@Override
	public List<Record2> queryUserRecords(String uname) {
		// TODO Auto-generated method stub
		return recordDao.queryRecordByUname(uname);
	}

	@Override
	public List<Record2> queryDVDRecords(String dname) {
		// TODO Auto-generated method stub
		return recordDao.queryRecordByDname(dname);
	}

	@Override
	public List<Record2> queryHasReturnRecords(String uname) {
		// TODO Auto-generated method stub
		return recordDao.queryRecordByReturnTime(true, uname);
	}

	@Override
	public List<Record2> queryNotHasReturnRecords(String uname) {
		// TODO Auto-generated method stub
		return recordDao.queryRecordByReturnTime(false, uname);
	}

	@Override
	public List<Record2> queryAllRecords() {
		// TODO Auto-generated method stub
		return recordDao.queryAllRecords();
	}

}
