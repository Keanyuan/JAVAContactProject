package com.anjiplus.gip.controller;

import javax.swing.JDialog;

import com.anjiplus.gip.view.AbstractOperationLedgerDialog;

public class EditLedgerController extends AbstractOperationLedgerDialog {

	/**
	 * 
	 */
	private static final long serialVersionUID = 2792204556425725395L;

	public EditLedgerController(JDialog dialog) {
		super(dialog);
		titleLabel.setText("编辑账务");
		super.setTitle("编辑账务");
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
