package com.iotek.dao;

import java.util.List;

import com.iotek.entity.Record;
import com.iotek.entity.Record2;

//操作Record数据的接口
public interface RecordDao {
	public Record queryRecordById(int rid);//根据指定id查询DVD借还记录
	public boolean saveRecord(Record record);//保存记录
	public boolean updateRecord(Record record);//更新所借DVD记录
	public List<Record2> queryAllRecords();//查询所有DVD记录
	public List<Record2> queryRecordByUname(String uname);//查看指定用户的DVD借还记录
	public List<Record2> queryRecordByDname(String dname);//查看指定DVD的借还记录
	public List<Record2> queryRecordByReturnTime(Boolean flag, String uname);//查看用户归还记录（已归还、未归还）
}
