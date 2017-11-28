package com.anjiplus.gip.controller;

import java.util.List;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JDialog;
import javax.swing.JOptionPane;

import com.anjiplus.gip.domain.Ledger;
import com.anjiplus.gip.services.LedgerService;
import com.anjiplus.gip.services.SortService;
import com.anjiplus.gip.view.AbstractOperationLedgerDialog;

public class AddLedgerController extends AbstractOperationLedgerDialog {

	/**
	 * 
	 */
	private static final long serialVersionUID = -5291728040065239435L;
	private SortService sortService = new SortService();
	private LedgerService ledgerService = new LedgerService();

	public AddLedgerController(JDialog dialog) {
		super(dialog);
		// TODO Auto-generated constructor stub
		titleLabel.setText("添加账务");
		super.setTitle("添加账务");
	}

	@Override
	public void changeParent() {
		// 获取收支的选项
		String parent = parentBox.getSelectedItem().toString();
		if (parent.equals("-请选择-")) {
			sortBox.setModel(new DefaultComboBoxModel<Object>(new Object[] { "-请选择-" }));
		}

		if (parent.equals("收入") || parent.equals("支出")) {
			// 调用services查询所有分类名称
			// 获取list.toArray集合并填充到下拉菜单中
			List<Object> list = sortService.querySortNamByParent(parent);
			list.add(0, "-请选择-");
			sortBox.setModel(new DefaultComboBoxModel(list.toArray()));
		}
	}

	/**
	 * 点击添加按钮实现功能
	 */
	@Override
	public void confirm() {
		// 获取收支的选项
		String parent = parentBox.getSelectedItem().toString();
		// 收支副分类
		String sname = sortBox.getSelectedItem().toString();
		// 账户
		String account = accountTxt.getText();
		// 时间
		String createtime = createtimeTxt.getText();
		// 金额
		String sMoney = moneyTxt.getText();
		// 说明
		String ldesc = ldescTxt.getText();
		if (parent.equals("-请选择-")) {
			JOptionPane.showMessageDialog(this, "请选择收/支", "错误提示", JOptionPane.ERROR_MESSAGE);
			return;
		}

		if (sname.equals("-请选择-")) {
			JOptionPane.showMessageDialog(this, "请选择分类名称", "错误提示", JOptionPane.ERROR_MESSAGE);
			return;
		}
		double money = 0;
		try {
			money = Double.parseDouble(sMoney);
		} catch (NumberFormatException e) {
			// TODO Auto-generated catch block
			JOptionPane.showMessageDialog(this, "必须填写数字", "错误提示", JOptionPane.ERROR_MESSAGE);
			return;
		}
		if (money<=0) {
			JOptionPane.showMessageDialog(this, "金额必须大于0", "错误提示", JOptionPane.ERROR_MESSAGE);
			return;
		}
		
		//获取ID
		int sid = ledgerService.getSidBySname(sname);
		//sid 从表里查到的
		Ledger ledger = new Ledger(0, parent, money, sid, account, createtime, ldesc, sname);
		ledgerService.addLedger(ledger);
		this.dispose();
		JOptionPane.showMessageDialog(this, "添加账务成功");
	}

}
