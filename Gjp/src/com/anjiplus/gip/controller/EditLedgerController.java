package com.anjiplus.gip.controller;

import java.util.List;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JDialog;
import javax.swing.JOptionPane;

import com.anjiplus.gip.domain.Ledger;
import com.anjiplus.gip.services.LedgerService;
import com.anjiplus.gip.services.SortService;
import com.anjiplus.gip.view.AbstractOperationLedgerDialog;

public class EditLedgerController extends AbstractOperationLedgerDialog {

	/**
	 * 
	 */
	private static final long serialVersionUID = 2792204556425725395L;
	private SortService sortService = new SortService();
	private LedgerService ledgerService = new LedgerService();
	Ledger ledger;
	public EditLedgerController(JDialog dialog, Ledger ledger) {
		super(dialog);
		titleLabel.setText("编辑账务");
		super.setTitle("编辑账务");
		
		/**
		 * String parent = parentBox.getSelectedItem().toString();
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
		 */
		this.parentBox.setSelectedItem(ledger.getParent());
		this.sortBox.setSelectedItem(ledger.getSname());
		accountTxt.setText(ledger.getAccount());
		createtimeTxt.setText(ledger.getCreatetime());
		moneyTxt.setText(""+ledger.getMoney());
		ldescTxt.setText(ledger.getLdesc());
		this.ledger = ledger;
	}

	@Override
	public void changeParent() {
		// TODO Auto-generated method stub
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
		if (money <= 0) {
			JOptionPane.showMessageDialog(this, "金额必须大于0", "错误提示", JOptionPane.ERROR_MESSAGE);
			return;
		}

		// 获取ID
		int sid = ledgerService.getSidBySname(sname);
		// sid 从表里查到的
		Ledger ledger = new Ledger(this.ledger.getLid(), parent, money, sid, account, createtime, ldesc, sname);
		ledgerService.editLedger(ledger);
		this.dispose();
		JOptionPane.showMessageDialog(this, "编辑账务成功");
	}

}
