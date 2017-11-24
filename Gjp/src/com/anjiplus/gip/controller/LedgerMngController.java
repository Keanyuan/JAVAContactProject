package com.anjiplus.gip.controller;

import javax.swing.JFrame;

import com.anjiplus.gip.view.AbstractLedgerMngDialog;

public class LedgerMngController extends AbstractLedgerMngDialog {

	/**
	 * 
	 */
	private static final long serialVersionUID = -832585828493774960L;

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
		new EditLedgerController(this).setVisible(true);


	}

	@Override
	public void deleteLedger() {
		// TODO Auto-generated method stub

	}

	@Override
	public void queryLedger() {
		// TODO Auto-generated method stub

	}

	@Override
	public void parentChange() {
		// TODO Auto-generated method stub

	}

	@Override
	public void pie() {
		// TODO Auto-generated method stub
		new ShapeController(this).setVisible(true);

	}

}
