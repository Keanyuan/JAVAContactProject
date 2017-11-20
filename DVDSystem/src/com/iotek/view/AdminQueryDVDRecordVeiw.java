package com.iotek.view;

import java.awt.BorderLayout;
import java.awt.GridLayout;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.JTextField;

public class AdminQueryDVDRecordVeiw extends JInternalFrame {
	private static final long serialVersionUID = -5893022936986176380L;
	private JPanel paneltabel = null;//用来保存Jtable的面板
	private JTable table = null;//声明jtable
	private JPanel panelButton = null;//按钮面板
	private JButton btn_search = null;
	private JButton btn_exit = null;
	private JComboBox<String> cb_type = null;
	private JLabel lb_type = null;
	private JTextField tf_searchName = null;//借出输入

	
	public AdminQueryDVDRecordVeiw() {
		// TODO Auto-generated constructor stub
		init();
	}
	
	private void init(){
		this.setTitle("管理员操作租赁记录查询");
		this.setSize(800,500);
		this.setIconifiable(true);//窗体可最小化
		this.setClosable(true);//窗体可被关闭
		this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		table = new JTable();
		paneltabel = new JPanel(new BorderLayout());//创建面板
		//给面板设置边框
		paneltabel.setBorder(BorderFactory.createTitledBorder(BorderFactory.createEtchedBorder(null, null), "DVD租赁记录查询"));
		paneltabel.add(table);
		this.add(paneltabel, BorderLayout.CENTER);
		
		panelButton = new JPanel(new GridLayout(9, 1, 10, 10));
		panelButton.setBorder(BorderFactory.createTitledBorder(BorderFactory.createEtchedBorder(null, null), "查询条件"));
		this.add(panelButton, BorderLayout.EAST);
		lb_type = new JLabel("查询类型");
		panelButton.add(lb_type);
		cb_type = new JComboBox<String>(new String[]{"指定用户租赁记录","指定DVD租赁记录"});
		tf_searchName = new JTextField(8);
		btn_search = new JButton("查询");
		panelButton.add(btn_search);

		btn_exit = new JButton("退出窗口");
		
		panelButton.add(cb_type);
		panelButton.add(tf_searchName);
		panelButton.add(btn_search);
		panelButton.add(new JLabel());
		panelButton.add(new JLabel());
		panelButton.add(new JLabel());
		panelButton.add(new JLabel());
		panelButton.add(btn_exit);

		this.setVisible(true);
		
		
		
	}
}
