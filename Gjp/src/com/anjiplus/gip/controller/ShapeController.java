package com.anjiplus.gip.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.swing.JDialog;

import com.anjiplus.gip.services.LedgerService;
import com.anjiplus.gip.tools.DateUtils;
import com.anjiplus.gip.tools.JFreeChartUtils;
import com.anjiplus.gip.view.AbstractShapeDialog;

public class ShapeController extends AbstractShapeDialog {

	/**
	 * 
	 */
	private static final long serialVersionUID = -4828055348184058376L;
	private  LedgerService ledgerService = new LedgerService(); 
	
	public ShapeController(JDialog dialog) {
		super(dialog);
		initDialog();
	}

	/**
	 * 获取生成的图片路径，把路径存储到list集合中
	 * 问题：由谁生成的图片 由JFreeChartUtils静态方法生成
	 */
	@Override
	public List<String> getImagePaths() {
		List<String> listPath = new ArrayList<String>();
		listPath.add(getImagePathByType("收入", "moneyIn.jpg"));
		listPath.add(getImagePathByType("支出", "moneyPay.jpg"));
		return listPath;
	}
	
	private String getImagePathByType(String stype, String imagePath){
		//获取相应所有收支总数金额
		Double money = ledgerService.getTotalMoney(stype);
		Map<String, Double> map = ledgerService.querySumMoneyBySort(stype);
		// 调用工具类JFreeChartUtils方法生成图片
		JFreeChartUtils.pie(stype+"占比图(" + money + ")(" + DateUtils.getYear() + "年)", map, money, imagePath);
		return imagePath;
	}

}
