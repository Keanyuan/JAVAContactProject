package com.anjiplus.gip.dao;

import java.sql.SQLException;
import java.util.List;

import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanListHandler;

import com.anjiplus.gip.domain.Sort;
import com.anjiplus.gip.tools.JDBCUtils;

/**
 * 访问数据库的类
 * 服装分类功能
 * @author KeanQ
 *
 */
public class SortDao {
	//类的成员位置，顶塔queryRunner对象,所有方法都可以直接使用
	private QueryRunner qr = new QueryRunner(JDBCUtils.getDataSource());
	/**
	 * 定义方法查询所有分类数据
	 * 返回list集合返回sort类
	 */
	public List<Sort> querySortAll(){
		try {
			//拼写数据库SQL语句
			String sql = "SELECT * FROM gjp_sort";
			List<Sort> list = qr.query(sql, new BeanListHandler<Sort>(Sort.class));
			return list;
		} catch (SQLException e) {
			//手动抛出运行时异常
			throw new RuntimeException(e);
		}
	}
	
	public void addSort(Sort sort){
		try {
			String sql = "INSERT INTO gjp_sort(sname,parent,sdesc)VALUES(?,?,?)";
			Object[] params = {sort.getSname(),sort.getParent(),sort.getSdesc()};
			int row = qr.update(sql, params);
			if (row >0 ) {
				//成功
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			//手动抛出运行时异常
			throw new RuntimeException(e);
		}

	}
	
	
	public void editSort(Sort sort){
		try {
			String sql = "UPDATE gjp_sort SET sname=?,parent=?,sdesc=? where sid=?";
			Object[] params = {sort.getSname(),sort.getParent(),sort.getSdesc(),sort.getSid()};
			int row = qr.update(sql, params);
			if (row >0 ) {
				//成功
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			//手动抛出运行时异常
			throw new RuntimeException(e);
		}
	}
	
	
	public void deleteSort(Sort sort){
		try {
			String sql = "DELETE FROM gjp_sort where sid=?";
			Object[] params = {sort.getSid()};
			int row = qr.update(sql, params);
			if (row >0 ) {
				//成功
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			//手动抛出运行时异常
			throw new RuntimeException(e);
		}
	}
	

}
