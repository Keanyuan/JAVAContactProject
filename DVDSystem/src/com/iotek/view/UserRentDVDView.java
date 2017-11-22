package com.iotek.view;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.List;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.event.TableModelListener;
import javax.swing.table.TableModel;

import com.iotek.biz.DVDBiz;
import com.iotek.biz.impl.DVDBizImpl;
import com.iotek.entity.DVD;
import com.iotek.entity.Users;

public class UserRentDVDView extends JInternalFrame {

	private static final long serialVersionUID = -4559017804311949973L;
	
	private JPanel paneltabel = null;//用来保存Jtable的面板
	private JTable table = null;//声明jtable
	private JPanel panelButton = null;//按钮面板
	private JButton btn_search = null;
	private JButton btn_rent = null;
	private JButton btn_exit = null;
	private JComboBox<String> cb_type = null;
	private JLabel lb_type = null;
	private DVDBiz dvdbiz=null;
	private List<DVD> dvdList = null;
	private DVDInfoTableModel infoTableModel = null;
	private Users user = null;
	private int ddid =  0;
	
	
	

	public UserRentDVDView(Users user) {
		this.user = user;
		dvdbiz = new DVDBizImpl();
		init();
		registerListener();
	}
	
	private void init(){
		this.setTitle("DVD信息查询");
		this.setSize(800,500);
		this.setIconifiable(true);//窗体可最小化
		this.setClosable(true);//窗体可被关闭
		this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		dvdList = new ArrayList<DVD>();

		table = new JTable();
		paneltabel = new JPanel(new BorderLayout());//创建面板
		refreshTabel(dvdList);
		//给面板设置边框
		paneltabel.setBorder(BorderFactory.createTitledBorder(BorderFactory.createEtchedBorder(null, null), "查询信息"));
		paneltabel.add(new JScrollPane(table), BorderLayout.CENTER);
		this.add(paneltabel, BorderLayout.CENTER);
		
		panelButton = new JPanel(new GridLayout(7, 1, 10, 30));
		panelButton.setBorder(BorderFactory.createTitledBorder(BorderFactory.createEtchedBorder(null, null), "查询条件"));
		this.add(panelButton, BorderLayout.EAST);
		lb_type = new JLabel("查询类型");
		panelButton.add(lb_type);
		cb_type = new JComboBox<String>(new String[]{"全部DVD","可借DVD","已借DVD","热门DVD"});
		btn_search = new JButton("查询");
		panelButton.add(btn_search);
		btn_rent = new JButton("租DVD");
		btn_rent.setEnabled(false);
		btn_exit = new JButton("退出");
		
		panelButton.add(cb_type);
		panelButton.add(btn_search);
		panelButton.add(btn_rent);
		panelButton.add(new JLabel());
		panelButton.add(new JLabel());
		panelButton.add(btn_exit);

		this.setVisible(true);		
	}
	
	private void registerListener(){
		/**
		 * 查询数据
		 */
		btn_search.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				//获取选择下拉列表框的index
				int index = cb_type.getSelectedIndex();
				//先清除数据 防止数据累加
				if (dvdList != null) {
					dvdList.clear();
				}
				if (index == 0) {
					dvdList =  dvdbiz.queryAllDVDs();
				} else if (index == 1) {
					dvdList = dvdbiz.canLendDVD();
				} else if (index == 2) {
					dvdList = dvdbiz.hasLendedDVD();
				} else if (index == 3) {
					dvdList = dvdbiz.ranking_top_hot();
				} 
				refreshTabel(dvdList);
				btn_rent.setEnabled(false);
				if (dvdList.size() == 0) {
					JOptionPane.showMessageDialog(UserRentDVDView.this, "没有要查询的内容");
					return;
				}
				
			}
		});
		
		btn_rent.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				
				if (ddid != 0 && user.getId() != 0) {
					int flag = JOptionPane.showInternalConfirmDialog(UserRentDVDView.this, "是否租此DVD?","确认信息",JOptionPane.YES_NO_OPTION);
					if (flag == JOptionPane.YES_OPTION) {
						int flags =  dvdbiz.lendDVD(ddid, user.getId());
						if (flags == 0) {
							JOptionPane.showMessageDialog(UserRentDVDView.this, "没有找到DVD");
							return;
						} else if (flags == 1) {
							JOptionPane.showMessageDialog(UserRentDVDView.this, "已借出，不可借");
							return;
						} else if (flags == 2) {
							JOptionPane.showMessageDialog(UserRentDVDView.this, "借出成功");
							return;
						} else {
							JOptionPane.showMessageDialog(UserRentDVDView.this, "借出失败");
							return;
						} 
					}
					
				} else {
					JOptionPane.showMessageDialog(UserRentDVDView.this, "借出失败");
					return;
				}
			}
		});
		
		/**
		 * table列表点击事件
		 */
		table.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				// TODO Auto-generated method stub
				//假设选中一行更新删除可用
				if (table.getSelectedRow() != -1) {
					int row = table.getSelectedRow();
					String status = table.getValueAt(row, 3).toString();
					int d_id = (int) table.getValueAt(row, 0);
					System.out.println(status);
					if (status.equals("可借")) { //可借
						ddid = d_id;
						btn_rent.setEnabled(true);
					} else {
						btn_rent.setEnabled(false);
					}
				} 
			}
		});
		
		/**
		 * 关闭窗口
		 */
		btn_exit.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				int flag = JOptionPane.showInternalConfirmDialog(UserRentDVDView.this, "是否确定退出窗口?","确认信息",JOptionPane.YES_NO_OPTION);
				if (flag == JOptionPane.YES_OPTION) {
					UserRentDVDView.this.dispose();
				}
			}
		});
	}
	
	private class DVDInfoTableModel implements TableModel {

		private List<DVD> dvdList = null;

		public DVDInfoTableModel(List<DVD> dvdList) {
			this.dvdList = dvdList;
		}
		/**
		 * jtabel 显示的行数
		 */
		@Override
		public int getRowCount() {
			// TODO Auto-generated method stub
			return dvdList.size();
		}

		//总共有多少列
		@Override
		public int getColumnCount() {
			// TODO Auto-generated method stub
			return 4;
		}

		//设置jtable显示的列名
		@Override
		public String getColumnName(int columnIndex) {
			// TODO Auto-generated method stub
			if (columnIndex == 0) {
				return "影碟ID";
			} else if (columnIndex == 1) {
				return "影碟名字";
			} else if (columnIndex == 2) {
				return "影碟借出次数";
			} else if (columnIndex == 3) {
				return "影碟状态";
			}
			return "出错";
		}

		//得到jtable列的数据类型
		@Override
		public Class<?> getColumnClass(int columnIndex) {
			// TODO Auto-generated method stub
			return String.class;
		}

		//设置单元格是否可编辑
		@Override
		public boolean isCellEditable(int rowIndex, int columnIndex) {
			return false;
		}

		//获得jtable指定行列的值
		@Override
		public Object getValueAt(int rowIndex, int columnIndex) {
			// TODO Auto-generated method stub
			DVD dvd = dvdList.get(rowIndex);
			if (columnIndex == 0) {
				return dvd.getId();
			} else if (columnIndex == 1) {
				return dvd.getDname();
			} else if (columnIndex == 2) {
				return dvd.getDcount();
			} else if (columnIndex == 3) {
				return "" + (dvd.getStatus() == 1 ? "可借" : "已借");
			}
			return "出错";
		}

		//单元格内容变化的操作
		@Override
		public void setValueAt(Object aValue, int rowIndex, int columnIndex) {
			// TODO Auto-generated method stub
			
		}
		
		//给单元格添加事件触发
		@Override
		public void addTableModelListener(TableModelListener l) {
			// TODO Auto-generated method stub
			
		}

		//给单元格移除事件触发
		@Override
		public void removeTableModelListener(TableModelListener l) {
			// TODO Auto-generated method stub
			
		}
		
	}
	
	//刷新jtable 并显示数据
	private void refreshTabel(List<DVD> dvdList){
		infoTableModel = new DVDInfoTableModel(dvdList);
		table.setModel(infoTableModel);
	}
}
