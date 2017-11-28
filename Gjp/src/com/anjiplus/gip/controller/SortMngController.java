package com.anjiplus.gip.controller;

import java.util.List;

import javax.swing.JFrame;
import javax.swing.JOptionPane;

import com.anjiplus.gip.domain.Sort;
import com.anjiplus.gip.services.SortService;
import com.anjiplus.gip.view.AbstractSortMngDialog;

/**
 * 分类管理对话框子类 实现分类对话框功能
 * 
 * @author KeanQ
 *
 */
public class SortMngController extends AbstractSortMngDialog {

	/**
	 * 
	 */
	private static final long serialVersionUID = -1666575166473961564L;
	private SortService sortService = new SortService();

	public SortMngController(JFrame frame) {
		super(frame);
		this.refreshData();
	}

	/**
	 * 添加分类按钮点击方法
	 */
	@Override
	public void addSort() {
		// TODO Auto-generated method stub
		new AddSortController(this).setVisible(true);
		this.refreshData();
	}

	@Override
	public void editSort() {
		// TODO Auto-generated method stub
		int row = sortDataTable.getSelectedRow();

		if (row < 0) {
			JOptionPane.showMessageDialog(this, "请选择列表");
			return;
		}

		Sort sort = getSortByTableRow(row);

		if (sort == null) {
			JOptionPane.showMessageDialog(this, "选择的列表为空");
			return;
		}

		new EditSortController(this, sort).setVisible(true);
		this.refreshData();
	}

	@Override
	public void deleteSort() {
		// TODO Auto-generated method stub
		int row = sortDataTable.getSelectedRow();

		if (row < 0) {
			JOptionPane.showMessageDialog(this, "请选择列表");
			return;
		}

		Sort sort = getSortByTableRow(row);

		if (sort == null) {
			JOptionPane.showMessageDialog(this, "选择的列表为空");
			return;
		}

		// 提示用户是否真的删除
		int flag = JOptionPane.showConfirmDialog(this, "是否真的要删除", "提示", JOptionPane.OK_CANCEL_OPTION);
		if (flag == JOptionPane.OK_OPTION) {
			sortService.deleteSort(sort);
			this.refreshData();
		}

	}

	private void refreshData() {
		// 向表格中填充视图，在构造方法中实现
		// 调用setTableModel填充数据，传递List<sort>集合
		List<Sort> sortList = sortService.querySortAll();
		setTableModel(sortList);
	}
}
