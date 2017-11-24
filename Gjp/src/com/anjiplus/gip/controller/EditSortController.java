package com.anjiplus.gip.controller;

import javax.swing.JDialog;
import javax.swing.JOptionPane;

import com.anjiplus.gip.domain.Sort;
import com.anjiplus.gip.services.SortService;
import com.anjiplus.gip.view.AbstractOperationSortDialog;

public class EditSortController extends AbstractOperationSortDialog {

	/**
	 * 
	 */
	private static final long serialVersionUID = 4926601570159554409L;
	private Sort sort;
	public EditSortController(JDialog dialog,Sort sort) {
		super(dialog);
		titleLabel.setText("编辑分类");
		super.setTitle("编辑分类");
		//获取数据到对话框中
		//将sort对象中封装的分类填充到下拉菜单中
		//setSelectedItem将菜单中已有的项目作为默认项出现
		this.sort = sort;
		parentBox.setSelectedItem(sort.getParent());
		snameTxt.setText(sort.getSname());
		sdescArea.setText(sort.getSdesc());
		
	}


	@Override
	public void confirm() {
		// TODO Auto-generated method stub
		//验证
		//获取分类下拉菜单用户选择值
		String parent = parentBox.getSelectedItem().toString();
		//获取分类名称
		String sname = snameTxt.getText().trim();
		//获取分类描述
		String sdesc = sdescArea.getText();
		//建议常量写前边
		if ("=请选择=".equals(parent)) {
			JOptionPane.showMessageDialog(this, "请选择分类", "错误提示", JOptionPane.ERROR_MESSAGE);
			return;
		}
		if (sname == null || "".equals(sname)) {
			JOptionPane.showMessageDialog(this, "请填写分类名称", "错误提示", JOptionPane.ERROR_MESSAGE);
			return;
		}
		sort.setParent(parent);
		sort.setSdesc(sdesc);
		sort.setSname(sname);
		//调取service层的修改
		SortService sortService = new SortService();
		sortService.editSort(sort);
		//new SortService().addSort(sort);
		JOptionPane.showMessageDialog(this, "编辑分类成功", "提示", JOptionPane.PLAIN_MESSAGE);
		this.dispose();
				
	}

}
