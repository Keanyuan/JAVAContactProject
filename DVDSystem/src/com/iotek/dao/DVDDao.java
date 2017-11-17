package com.iotek.dao;

import java.util.List;

import com.iotek.entity.DVD;

//DVD接口
public interface DVDDao {
	public boolean saveDVD(DVD dvd);//添加DVD
	public boolean updateDVD(DVD dvd);//更新DVD
	public boolean delDVD(int did);//删除指定DVD
	public List<DVD> queryDVDs();//查找所有DVD
	public List<DVD> queryDVDByName(String name);//查找指定名字的DVD
	public List<DVD> querySortDVDByLimit(int index,int number);//查找指定起始位置指定个数的DVD
	public DVD queryDVDById(int did);//更加DVD编号查找DVD
	public List<DVD> queryDVDByStatus(int status);//根据状态查找DVD
}
