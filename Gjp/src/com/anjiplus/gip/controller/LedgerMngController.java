package com.anjiplus.gip.controller;

import java.util.List;
import java.util.Map;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JFrame;
import javax.swing.JOptionPane;

import com.anjiplus.gip.dao.QueryForm;
import com.anjiplus.gip.domain.Ledger;
import com.anjiplus.gip.services.LedgerService;
import com.anjiplus.gip.services.SortService;
import com.anjiplus.gip.view.AbstractLedgerMngDialog;

public class LedgerMngController extends AbstractLedgerMngDialog {

	/**
	 * 
	 */
	private static final long serialVersionUID = -832585828493774960L;

	private SortService sortService = new SortService();
	private LedgerService ledgerService = new LedgerService();

	public LedgerMngController(JFrame frame) {
		super(frame);
		// TODO Auto-generated constructor stub
	}

	/**
	 * 添加账务按钮
	 */
	@Override
	public void addLedger() {
		// TODO Auto-generated method stub
		new AddLedgerController(this).setVisible(true);

	}
	/**
	 * 编辑账务按钮
	 */
	@Override
	public void editLedger() {
		// TODO Auto-generated method stub
		int row = ledgerDataTable.getSelectedRow();
		if (row < 0) {
			JOptionPane.showMessageDialog(this, "请选择对应行");
			return;
		}
		
		Ledger ledger = getLedgerByTableRow(row);
		if (ledger==null) {
			JOptionPane.showMessageDialog(this, "选择的是空行");
			return;
		}
		
		new EditLedgerController(this, ledger).setVisible(true);
		queryLedger();

	}

	@Override
	public void deleteLedger() {
		// TODO Auto-generated method stub
		// 提示用户是否真的删除
		int row = ledgerDataTable.getSelectedRow();
		if (row < 0) {
			JOptionPane.showMessageDialog(this, "请选择对应行");
			return;
		}
		
		Ledger ledger = getLedgerByTableRow(row);
		if (ledger==null) {
			JOptionPane.showMessageDialog(this, "选择的是空行");
			return;
		}
		int flag = JOptionPane.showConfirmDialog(this, "是否真的要删除", "提示", JOptionPane.OK_CANCEL_OPTION);
		if (flag == JOptionPane.OK_OPTION) {
			int flagRow = ledgerService.deleteLedger(ledger);
			if (flagRow>0) {
				JOptionPane.showConfirmDialog(this, "删除成功", "提示", JOptionPane.CLOSED_OPTION);
			} else {
				JOptionPane.showConfirmDialog(this, "删除失败", "提示", JOptionPane.CLOSED_OPTION);
			}
		}	
		queryLedger();
	}

	/**
	 * 点击查询按钮，实现查询功能
	 * 获取的是services层的查询结果
	 * 结果做成map集合
	 * 	key：键名 value： 查询数据的list集合
	 * 	key：键名 value： 所有收入的总和
	 * 	key：键名 value： 所有支出的总和
	 *  map.put("",查询数据的list集合)
	 *  map.put("",inMoney)
	 *  map.put("",payMoney)
	 */
	@Override
	public void queryLedger() {
		// TODO Auto-generated method stub
		String begin = beginDateTxt.getText();
		String end = endDateTxt.getText();
		String parent = parentBox.getSelectedItem().toString();
		String sname = sortBox.getSelectedItem().toString();
		QueryForm form = new QueryForm(begin, end, parent, sname); 
		Map<String, Object> data =ledgerService.queryLegerByQueryForm(form);
		List<Ledger> list = (List<Ledger>) data.get("ledger");
		double in = (double)data.get("in");
		double pay = (double)data.get("pay");
		this.setTableModel(list);
		this.inMoneyTotalLabel.setText("总收入："+in+"元");
		this.payMoneyTotalLabel.setText("总支出："+pay+"元");


		
		

	}

	/**
	 * 菜单联动调用方法
	 * 收支菜单选择后会跟随显示
	 * 情况一：
	 * 	收支： 请选择
	 * 	分类： 请选择
	 * 情况二
	 * 	收支： 收入/支出
	 * 	分类：所有收入和支出
	 * 情况三
	 * 	收支： 收入或者支出
	 * 	分类： 对应收入或者支出
	 */
	@Override
	public void parentChange() {

		//获取收支的选项
		String parent = parentBox.getSelectedItem().toString();
		if (parent.equals("-请选择-")) {
			sortBox.setModel(new DefaultComboBoxModel<Object>(new Object[]{"-请选择-"}));
		} 
		
		if (parent.equals("收入/支出")) {
			//调用services查询所有分类名称
			//获取list.toArray集合并填充到下拉菜单中
			List<Object> list = sortService.querySortNameAll();
			list.add(0, "-请选择-");
			sortBox.setModel(new DefaultComboBoxModel(list.toArray()));
		}
		
		
		if (parent.equals("收入")||parent.equals("支出")) {
			//调用services查询所有分类名称
			//获取list.toArray集合并填充到下拉菜单中
			List<Object> list = sortService.querySortNamByParent(parent);
			list.add(0, "-请选择-");
			sortBox.setModel(new DefaultComboBoxModel(list.toArray()));
		}
	}
	
	@Override
	public void pie() {
		// TODO Auto-generated method stub
		new ShapeController(this).setVisible(true);

	}

}
