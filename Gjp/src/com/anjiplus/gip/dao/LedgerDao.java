package com.anjiplus.gip.dao;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.ArrayListHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;
import org.apache.commons.dbutils.handlers.ScalarHandler;

import com.anjiplus.gip.domain.Ledger;
import com.anjiplus.gip.domain.Sort;
import com.anjiplus.gip.tools.DateUtils;
import com.anjiplus.gip.tools.JDBCUtils;

public class LedgerDao {
	SortDao sortDao = new SortDao();
	//类的成员位置，顶塔queryRunner对象,所有方法都可以直接使用
		private QueryRunner qr = new QueryRunner(JDBCUtils.getDataSource());
	/**
	 * 定义方法实现组合查询功能
	 * 返回List<Ledger>集合
	 * 根据QueryForm封装的数据，进行SQL语句填写
	 */
	public List<Ledger> queryLedgerByQueryForm(QueryForm form){
		// 查询语句中的？占位符是一个不确定因素，参数选择容器进行存储
		List<String> params = new ArrayList<String>();
		StringBuilder builder = new StringBuilder();
		//开始日期和结束日期可以忽略（必传）
		builder.append("SELECT * FROM gjp_ledger WHERE createtime BETWEEN ? AND ?");
		params.add(form.getBegin());
		params.add(form.getEnd());
		
		//对查询条件 收入或者支出的选择，组合查询语句
		if (form.getParent().equals("收入") || form.getParent().equals("支出")) {
			builder.append(" And parent=?");
			params.add(form.getParent());
		}
		
		//对查询条件分类名称的选择
		if (!form.getSname().equals("-请选择-")) {
			//获取sname的值，获取数据中sid
			int sid = sortDao.getSidBySname(form.getSname());
			builder.append(" And sid=?");
			params.add(sid+"");
		}
		
		
		try {
			List<Ledger> lsit = qr.query(builder.toString(), new BeanListHandler<Ledger>(Ledger.class),params.toArray());
			return lsit;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			throw new RuntimeException(e);
		}
	}
	/**
	 * 添加账务
	 * 2017年11月28日 下午2:04:49 KeanQ
	 * @param ledger
	 */
	public void addLedger(Ledger ledger) {
		String sql = "INSERT INTO gjp_ledger (parent,money,sid,account,createtime,ldesc)"+
						"values(?,?,?,?,?,?)";
		Object[] params = {ledger.getParent(),ledger.getMoney(),ledger.getSid(),
				ledger.getAccount(),ledger.getCreatetime(),ledger.getLdesc()};
		try {
			qr.update(sql, params);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			throw new RuntimeException(e);
		}
		
	}
	
	public void editLedger(Ledger ledger){
		try {
			String sql = "UPDATE gjp_ledger SET parent=?,money=?,sid=?,account=?,createtime=?,ldesc=? where lid=?";
			Object[] params = {ledger.getParent(),ledger.getMoney(),ledger.getSid(),
					ledger.getAccount(),ledger.getCreatetime(),ledger.getLdesc(),ledger.getLid()};
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
	
	public int deleteLedger(Ledger ledger){
		try {
			String sql = "DELETE FROM gjp_ledger where lid=?";
			Object[] params = {ledger.getLid()};
			int row = qr.update(sql, params);
			return row;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			//手动抛出运行时异常
			throw new RuntimeException(e);
		}
	}
	
	/**
	 * querySumMoneyBySort
	 * 通过分类名称查询所有分类数据的总格，传递参数输入还是支出
	 */
	public List<Object[]> querySumMoneyBySort(String parent){
		try {
			//所属parent的分组查询
			String sql = "SELECT SUM(money),sid  FROM gjp_ledger WHERE"+
			" parent=? AND createtime LIKE ? GROUP BY sid";
			Object[] params = {parent, DateUtils.getYear()+"%"};
			return  qr.query(sql,new ArrayListHandler(),params);
		} catch (SQLException e) {
			//手动抛出运行时异常
			throw new RuntimeException(e);		
			}
	}
	
	/**
	 * getTotalMoney
	 * 通过分类查询所有收支总和
	 * 2017年11月28日 下午3:46:54 KeanQ
	 * @return
	 */
	public Double getTotalMoney(String parent){
		try {
			//所属parent的分组查询
			String sql = "SELECT SUM(money)  FROM gjp_ledger WHERE"+
			" parent=? AND createtime LIKE ?";
			Object[] params = {parent, DateUtils.getYear()+"%"};
			return  (Double) qr.query(sql,new ScalarHandler<>(),params);
		} catch (SQLException e) {
			//手动抛出运行时异常
			throw new RuntimeException(e);		
			}
	}
	
}
