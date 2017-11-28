package com.anjiplus.gip.services;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.anjiplus.gip.dao.LedgerDao;
import com.anjiplus.gip.dao.QueryForm;
import com.anjiplus.gip.dao.SortDao;
import com.anjiplus.gip.domain.Ledger;
import com.anjiplus.gip.domain.Sort;

public class LedgerService {
	/**
	 * 定义方法返回map几个
	 * 作用：根据用户的条件，查询数据库（list集合）
	 * 存储map集合
	 */
	private LedgerDao ledgerDao = new LedgerDao();
	//创建Dao层对象
		private SortDao sortDao = new SortDao();
	/**
	 * 调用dao层方法，查询结果的list集合
	 */
	public Map<String, Object>queryLegerByQueryForm(QueryForm form){
		List<Ledger> list = ledgerDao.queryLedgerByQueryForm(form);
		double in = 0;
		double pay = 0;
		for (Ledger ledger : list) {
			ledger.setSname(sortDao.getSnameBySid(ledger.getSid()));
			if (ledger.getParent().equals("收入")) {
				in += ledger.getMoney();
			} else {
				pay += ledger.getMoney();
			}			
		}
		//创建map集合，将list in pay 存储到map集合中
		Map<String, Object> data = new HashMap<String, Object>();
		data.put("ledger", list);
		data.put("in", in);
		data.put("pay", pay);
		return data;
	}
	
	/**
	 * 定义一个方法 getSidBySname
	 */
	public int getSidBySname(String sname){
		return sortDao.getSidBySname(sname);
	}
	
	public String getSnameBySid(int sid) {
		return sortDao.getSnameBySid(sid);
	}
	
	//添加账务
	public void addLedger(Ledger ledger) {
		// TODO Auto-generated method stub
		ledgerDao.addLedger(ledger);
	}
	
	/**
	 * 定义方法，编辑账务数据
	 * 2017年11月28日 下午2:45:18 KeanQ
	 * @param ledger
	 */
	public void editLedger(Ledger ledger){
		ledgerDao.editLedger(ledger);
	}
	/**
	 * 删除对应账务
	 * 2017年11月28日 下午2:53:26 KeanQ
	 * @param ledger
	 */
	public int deleteLedger(Ledger ledger){
		return ledgerDao.deleteLedger(ledger);
	}
	/**
	 * 调dao层querySumMoneyBySort，传递父分类，获取这个分类下的求和数据
	 * 2017年11月28日 下午3:53:13 KeanQ
	 * @return
	 */
	public Map<String, Double> querySumMoneyBySort(String parent){
		List<Object[]> list = ledgerDao.querySumMoneyBySort(parent);
		//创建map集合
		Map<String, Double> map = new HashMap<String, Double>();
		//遍历集合list获取集合object数组
		for (Object[] objects : list) {
			Double money = (Double)objects[0];
			int sid = (int)objects[1];
			//调用sortDao方法传递sid获取sname
			String sname = sortDao.getSnameBySid(sid);
			//求和值和分类名称存储到map集合里
			map.put(sname, money);
		}
		return map;
	}
	
	/**
	 * 通过分类查询所有收支总和
	 * 2017年11月28日 下午4:00:27 KeanQ
	 * @param parent
	 * @return
	 */
	public Double getTotalMoney(String parent){
		return ledgerDao.getTotalMoney(parent);
	}
	
}
