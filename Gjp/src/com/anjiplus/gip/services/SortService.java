package com.anjiplus.gip.services;

import java.util.List;

import com.anjiplus.gip.dao.SortDao;
import com.anjiplus.gip.domain.Sort;

/**
 * 分类功能的业务层
 * @author KeanQ
 *分类的控制层controller调用service功能
 *service调用dao层的功能
 */
public class SortService {

	//创建Dao层对象
	private SortDao sortDao = new SortDao();
	/**
	 * 调用dao层SortDao#querySortAll获取数据
	 */
	public List<Sort> querySortAll(){
		return sortDao.querySortAll();
	}
	
	/**
	 * 定义方法，添加分类数据
	 * 调用dao层SortDao#addSort(Sort sort)获取数据
	 */
	public void addSort(Sort sort){
		sortDao.addSort(sort);
	}
	
	/**
	 * 定义方法，编辑分类数据
	 * 用dao层SortDao#editSort(Sort sort)获取数据
	 * 2017年11月24日 下午5:28:02 KeanQ
	 * @param sort
	 */
	public void editSort(Sort sort){
		sortDao.editSort(sort);
	}

	/**
	 * 定义方法，删除分类数据
	 * 用dao层SortDao#deleteSort(Sort sort)获取数据
	 * 2017年11月24日 下午5:28:02 KeanQ
	 * @param sort
	 */
	public void deleteSort(Sort sort){
		sortDao.deleteSort(sort);
	}
}
