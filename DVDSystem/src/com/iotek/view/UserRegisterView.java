package com.iotek.view;

import java.awt.Font;
import java.awt.GridLayout;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

public class UserRegisterView extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private JPanel panel_main = null;
	private JPanel panel1 = null;
	private JPanel panel2 = null;
	private JPanel panel3 = null;
	private JPanel panel4 = null;
	private JPanel panel5 = null;
	private JLabel lb_name = null;
	private JLabel lb_init_pass = null;
	private JLabel lb_confirm_pass = null;
	private JTextField tf_uname = null;
	private JPasswordField userPassInit = null;
	private JPasswordField userPassConfirm= null;
	private JButton btn_confirm = null;
	private JButton btn_back = null;

	
	public UserRegisterView() {
		init();
	}
	
	private void init(){
		tf_uname = new JTextField(15);
		userPassInit = new JPasswordField(15);
		userPassConfirm = new JPasswordField(15);
		
		btn_confirm = new JButton("确认提交");
		btn_back = new JButton("退出");
		
		lb_name = new JLabel("用户名：");
		lb_name.setFont(new Font("宋体", Font.BOLD, 15));//15号字体
		lb_init_pass = new JLabel("初始化密码：");
		lb_init_pass.setFont(new Font("宋体", Font.BOLD, 15));//15号字体
		lb_confirm_pass = new JLabel("确认密码：");
		lb_confirm_pass.setFont(new Font("宋体", Font.BOLD, 15));//15号字体
		
		panel_main = new JPanel(new GridLayout(5, 1));//表格布局
		panel1 = new JPanel();//默认流式布局
		panel2 = new JPanel();
		panel3 = new JPanel();
		panel4 = new JPanel();
		panel5 = new JPanel();
		
		panel2.add(lb_name);
		panel2.add(tf_uname);
		panel3.add(lb_init_pass);
		panel3.add(userPassInit);
		panel4.add(lb_confirm_pass);
		panel4.add(userPassConfirm);
		panel5.add(btn_confirm);
		panel5.add(btn_back);
		
		panel_main.add(panel1);
		panel_main.add(panel2);
		panel_main.add(panel3);
		panel_main.add(panel4);
		panel_main.add(panel5);
		
		this.getContentPane().add(panel_main);
		this.setTitle("用户注册窗口");
		this.setSize(450, 260);
		this.setLocationRelativeTo(null);
		this.setResizable(false);
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		this.getRootPane().setDefaultButton(btn_confirm);
		this.setVisible(true);
		
		
		
	}





}
