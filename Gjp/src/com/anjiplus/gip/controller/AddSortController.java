package com.anjiplus.gip.controller;

import javax.swing.JDialog;
import javax.swing.JOptionPane;

import com.anjiplus.gip.domain.Sort;
import com.anjiplus.gip.services.SortService;
import com.anjiplus.gip.view.AbstractOperationSortDialog;
/**
 * 添加分类对话框的窗体
 * @author KeanQ
 *
 */
public class AddSortController extends AbstractOperationSortDialog {

	/**
	 * 
	 */
	private static final long serialVersionUID = -4159901788516531963L;

	public AddSortController(JDialog dialog) {
		super(dialog);
		// TODO Auto-generated constructor stub
		titleLabel.setText("添加分类");
		super.setTitle("添加分类");
	}
	
	/**
	 * 添加确认按钮事件
	 * 实现步骤：
	 * 	1.数据验证
	 * 		父分类
	 * 		验证分类名称
	 * 		数据不符合要求提示信息
	 * 	2.将获取到的数据，封装成sort对象（lid成员不需要设置值的）
	 * 	3.将sort对象传递给service处理
	 * 	4.service获取到sort对象后传递给dao层
	 * 	5.将sort对象中的数据写入到数据库
	 * 	6.提示用户添加成功
	 * 	7.成功之后重新刷新
	 */
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
			JOptionPane.showMessageDialog(this, "请选择分类", "提示", JOptionPane.ERROR_MESSAGE);
			return;
		}
		if (sname == null || "".equals(sname)) {
			JOptionPane.showMessageDialog(this, "请填写分类名称", "提示", JOptionPane.ERROR_MESSAGE);
			return;
		}
		
//		Sort sort = new Sort();
//		sort.setParent(parent);
//		sort.setSname(sname);
//		sort.setSdesc(sdesc);
		Sort sort = new Sort(sname, parent, sdesc);
		SortService sortService = new SortService();
		sortService.addSort(sort);
		//new SortService().addSort(sort);
		JOptionPane.showMessageDialog(this, "添加分类成功", "提示", JOptionPane.PLAIN_MESSAGE);
		this.dispose();
	}

	

}
