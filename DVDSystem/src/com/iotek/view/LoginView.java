package com.iotek.view;

import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

import com.iotek.biz.UserBiz;
import com.iotek.biz.impl.UserBizImpl;
import com.iotek.entity.Users;

public class LoginView extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private JPanel panel_main = null;//主面板
	private JPanel panel_left = null;//左面板
	private JPanel panel_right = null;//右面板
	
	private JLabel lb_uname = null;//用户标签
	private JLabel lb_upass = null;//密码标签
	private JLabel lb_type = null;//登录密码标签

	private JTextField tf_uname = null;
	private JPasswordField pf_pass = null;
	
	private JLabel lb_image = null;//用来显示图片的标签
	
	private JButton btn_login = null; //登录按钮
	private JButton btn_register = null;//注册按钮
	
	private JComboBox<String> cb_type= null;//登录类型下拉列表框
	
	private UserBiz userBiz = null;
	
	
	public LoginView() {
		
		userBiz = new UserBizImpl();
		init();
		registerListener();
	}
	private void init(){
		this.setSize(320, 220);//设置窗体大小显示
		this.setResizable(false);//是否可以拖动窗体大小
		this.setLocationRelativeTo(null);//居中
		this.setTitle("登录窗口");
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);//关闭窗体功能
		initUI();

		this.setVisible(true);
	}
	
	private void initUI(){
		//初始化面板
		panel_main = new JPanel(new GridLayout(1, 2));
		panel_left = new JPanel();
		panel_right = new JPanel(new GridLayout(4, 2, 0, 10));
		
		//初始化控件
		tf_uname = new JTextField(8);//长度
		pf_pass = new JPasswordField(8);
		cb_type = new JComboBox<String>(new String[]{"普通用户", "管理员"});
		btn_login = new JButton("登录");
		btn_register = new JButton("注册");
		lb_uname = new JLabel("用户：", JLabel.CENTER);
		lb_upass = new JLabel("密码：", JLabel.CENTER);
		lb_type = new JLabel("类型：", JLabel.CENTER);

		lb_image = new JLabel(new ImageIcon(ClassLoader.getSystemResource("img/tabBar_mine_highlited.png")));
		//把相应的控件放到面板中去
		panel_left.add(lb_image);
		panel_right.add(lb_uname);
		panel_right.add(tf_uname);
		panel_right.add(lb_upass);
		panel_right.add(pf_pass);
		panel_right.add(lb_type);
		panel_right.add(cb_type);
		panel_right.add(btn_login);
		panel_right.add(btn_register);

		panel_main.add(panel_left);
		panel_main.add(panel_right);
		
		this.getContentPane().add(panel_main);
		
		this.pack();
	}

	private void registerListener(){
		btn_login.addActionListener(new ActionListener() { //登录
			
			@Override
			public void actionPerformed(ActionEvent e) {
				String uname = tf_uname.getText().trim();
				String upass = new String(pf_pass.getPassword());
				
				int type = cb_type.getSelectedIndex() + 1;//1 用户  2 管理员

				if (uname.equals("")) {
					JOptionPane.showMessageDialog(LoginView.this, "用户名不能为空");
					return;
				} else if (upass.equals("")) {
					JOptionPane.showMessageDialog(LoginView.this, "密码不能为空");
					return;
				}
				Users user = new Users(uname, upass, type);
				user = userBiz.login(user);
				if (user != null) {
					if (user.getType() == 1) { //普通用户
						new UserMainView(user);
					} else { //管理员
						new AdminMainView(user);
					}
					LoginView.this.dispose();
				} else {
					JOptionPane.showMessageDialog(LoginView.this, "用户名或密码不正确");
					return;
				}
				
			}
		});
		
		btn_register.addActionListener(new ActionListener() {//注册
			
			@Override
			public void actionPerformed(ActionEvent e) {
				new UserRegisterView();
				LoginView.this.dispose();
			}
		});
		
		
	}
	
}
