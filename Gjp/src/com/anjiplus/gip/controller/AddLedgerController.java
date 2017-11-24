package com.anjiplus.gip.controller;

import javax.swing.JDialog;

import com.anjiplus.gip.view.AbstractOperationLedgerDialog;

public class AddLedgerController extends AbstractOperationLedgerDialog {

	/**
	 * 
	 */
	private static final long serialVersionUID = -5291728040065239435L;

	public AddLedgerController(JDialog dialog) {
		super(dialog);
		// TODO Auto-generated constructor stub
		titleLabel.setText("添加账务");
		super.setTitle("添加账务");
	}

	@Override
	public void changeParent() {
		// TODO Auto-generated method stub

	}

	@Override
	public void confirm() {
		// TODO Auto-generated method stub

	}

}
