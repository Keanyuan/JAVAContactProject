package com.iotek.view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JDesktopPane;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

import com.iotek.entity.Users;

public class UserMainView extends JFrame {

	private static final long serialVersionUID = 8424986472791518996L;
	private JPanel main_panel = null;//主面板
	private JPanel wel_panel = null;//欢迎面板
	private JPanel btn_panel = null;//按钮面板
	private JDesktopPane funcDesktop = null;//桌面面板
	
	private JButton btn_query_rent_dvd = null;//查询租赁按钮
	private JButton btn_dvd_record = null;//查看租赁记录按钮
	private JButton btn_exit = null;//退出按钮
	private JLabel lb_welcome = null;//欢迎label
	private JLabel deskLabel = null;//存放图片label
	
	private Users user = null;
	public UserMainView(Users user){
		this.user = user;
		init();
		registerListener();
	}
//	public UserMainView() {
//		// TODO Auto-generated method stub
//		init();
//		registerListener();
//
//	}
	private void init(){
		main_panel = new JPanel(new BorderLayout());
		btn_panel = new JPanel(new GridLayout(7, 1, 0, 35));
		btn_query_rent_dvd = new JButton("DVD查询租赁操作");
		btn_dvd_record = new JButton("DVD租赁记录查询");
		btn_exit = new JButton("退出窗口");
		btn_panel.add(new JLabel());
		btn_panel.add(new JLabel());
		btn_panel.add(btn_query_rent_dvd);
		btn_panel.add(btn_dvd_record);
		btn_panel.add(btn_exit);
		btn_panel.add(new JLabel());
		btn_panel.add(new JLabel());
		//设置面板边框外观
		btn_panel.setBorder(BorderFactory.createTitledBorder(BorderFactory.createRaisedBevelBorder(), "快捷功能区"));
		
		wel_panel = new JPanel();
		lb_welcome = new JLabel("欢	迎	"+ user.getUname() +"	使	用	影	碟	租	赁	系	统");
		lb_welcome.setFont(new  Font("宋体", Font.BOLD, 23));
		lb_welcome.setForeground(Color.BLUE);
		wel_panel.add(lb_welcome);
		
		//初始化桌面面板
		funcDesktop = new JDesktopPane();
		ImageIcon imageI = new ImageIcon("src/img/bg.png");
		deskLabel = new JLabel(imageI);
		deskLabel.setBounds(0, 0, imageI.getIconWidth(), imageI.getIconHeight());
		funcDesktop.add(deskLabel, new Integer(Integer.MIN_VALUE));
		
		main_panel.add(btn_panel, BorderLayout.EAST);
		main_panel.add(wel_panel, BorderLayout.NORTH);
		main_panel.add(funcDesktop, BorderLayout.CENTER);
		
		//开启线程
		EventQueue.invokeLater(new Runnable() {
			
			@Override
			public void run() {
				new Thread(new DynaminThread()).start();;
				
			}
		});
		
		this.setTitle("影碟租赁管理系统");
		this.getContentPane().add(main_panel);
		this.setSize(1000, 650);
		this.setVisible(true);
		this.setResizable(false);
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		this.setLocationRelativeTo(null);
	}
	/**
	 * 线程雷，处理欢迎label标签移动
	 * @author KeanQ
	 *
	 */
	private class DynaminThread implements Runnable {
		@Override
		public void run() {
			while (true) {
				for (int i = 1000; i > -750; i--) {
					try {
						Thread.sleep(10);
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					lb_welcome.setLocation(i, 5);
					
				}
			}
		}
	}
	
	
	private void registerListener(){
		btn_query_rent_dvd.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				UserRentDVDView qdv = new UserRentDVDView();
				//把指定的视图添加到桌面
				funcDesktop.add(qdv);
				qdv.toFront();
			}
		});
		
		btn_dvd_record.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				UserQueryDVDView userQuery = new UserQueryDVDView();
				//把指定的视图添加到桌面
				funcDesktop.add(userQuery);
				userQuery.toFront();
			}
		});
	}
	

}
