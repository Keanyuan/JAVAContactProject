package com.iotek.biz;

import java.util.List;

import com.iotek.entity.DVD;

public interface DVDBiz {
	public boolean addDVD(DVD dvd);//添加DVD
	public boolean delDVD(int did);//删除DVD
	public boolean modifyDVD(DVD dvd);//修改DVD
	public List<DVD> queryAllDVDs();//查询所有DVD
	public List<DVD> ranking_top_hot();//查看前5张最受欢迎的DVD（热门DVD）
	public List<DVD> queryDVDByName(String dname);//根据DVD名字查询DVD
	public DVD queryDVDById(int uid);//根据ID查询DVD
	public int lendDVD(int did,int uid);//按DVD编号和用户编号租DVD
	public int returnDVD(int rid);//还DVD
	public List<DVD> canLendDVD();//可借的DVD
	public List<DVD> hasLendedDVD();//不可借DVD，即已借的DVD

}
