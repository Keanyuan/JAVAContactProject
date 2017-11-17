package com.iotek.biz;

import java.util.List;

import com.iotek.entity.Record2;

public interface RecordBiz {
	//查看指定用户的租赁记录
	public List<Record2> queryUserRecords(String uname);
	//查看指定DVD的租赁记录
	public List<Record2> queryDVDRecords(String dname);
	
	//查看本人已归的还租赁记录
	public List<Record2> queryHasReturnRecords(String uname);
	//查看本人未归的还租赁记录
	public List<Record2> queryNotHasReturnRecords(String uname);
	//查看本人所有租赁记录
	public List<Record2> queryAllRecords();


}
