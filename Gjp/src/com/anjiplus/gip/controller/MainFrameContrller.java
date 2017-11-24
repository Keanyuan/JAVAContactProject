package com.anjiplus.gip.controller;

import com.anjiplus.gip.view.AbstractMainFrame;

/**
 * 主窗体继承方法实现控制器
 * @author KeanQ
 *
 */
public class MainFrameContrller extends AbstractMainFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 7110275205256297464L;

	/**
	 * 注解表示重写主窗体类的抽象方法，作用打开账务管理对话框
	 */
	@Override
	public void ledgerMng() {
		new LedgerMngController(this).setVisible(true);;
	}

	/**
	 * 打开管理分类窗口
	 */
	@Override
	public void sortMng() {
		// TODO Auto-generated method stub
		//分类对话框界面
		new SortMngController(this).setVisible(true);;

	}

}
