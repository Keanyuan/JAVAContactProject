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
import com.iotek.biz.RecordBiz;
import com.iotek.biz.impl.DVDBizImpl;
import com.iotek.biz.impl.RecordBizImpl;
import com.iotek.entity.Record2;
import com.iotek.entity.Users;

public class UserQueryDVDView extends JInternalFrame {
	static final long serialVersionUID = 6423168312633652865L;
	private JPanel paneltabel = null;//用来保存Jtable的面板
	private JTable table = null;//声明jtable
	private JPanel panelButton = null;//按钮面板
	private JButton btn_search = null;
	private JButton btn_rent = null;
	private JButton btn_exit = null;
	private JComboBox<String> cb_type = null;
	private JLabel lb_type = null;
	private Users user = null;
	private RecordBiz recordBiz = null;
	private DVDBiz dvdbiz=null;

	private List<Record2> recordList = null;
	private RecordInfoTableModel recordInfoTableModel = null;
	private int rid = 0;

	public UserQueryDVDView(Users user) {
		this.user = user;
		recordBiz = new RecordBizImpl();
		dvdbiz = new DVDBizImpl();

		init();
		registerListener();
	}
	
	private void init(){
		this.setTitle("DVD租赁记录查询");
		this.setSize(800,500);
		this.setIconifiable(true);//窗体可最小化
		this.setClosable(true);//窗体可被关闭
		this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		recordList = new ArrayList<Record2>();
		table = new JTable();
		refreshTabel(recordList);
		paneltabel = new JPanel(new BorderLayout());//创建面板
		//给面板设置边框
		paneltabel.setBorder(BorderFactory.createTitledBorder(BorderFactory.createEtchedBorder(null, null), "本人租赁记录查询"));
		paneltabel.add(new JScrollPane(table), BorderLayout.CENTER);
		this.add(paneltabel, BorderLayout.CENTER);
		
		panelButton = new JPanel(new GridLayout(7, 1, 10, 30));
		panelButton.setBorder(BorderFactory.createTitledBorder(BorderFactory.createEtchedBorder(null, null), "查询条件"));
		this.add(panelButton, BorderLayout.EAST);
		lb_type = new JLabel("查询类型");
		panelButton.add(lb_type);
		cb_type = new JComboBox<String>(new String[]{"全部租赁记录","未归还租赁记录","已归还租赁记录"});
		btn_search = new JButton("查询");
		panelButton.add(btn_search);
		btn_rent = new JButton("还DVD");
		btn_rent.setEnabled(false);
		btn_exit = new JButton("退出窗口");
		
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
				if (recordList != null) {
					recordList.clear();
				}
				System.out.println(user.getUname());
				if (index == 0) {//指定用户全部租赁记录
					recordList =  recordBiz.queryUserRecords(user.getUname());
				} else if (index == 1) { //未归还租赁
					recordList =  recordBiz.queryNotHasReturnRecords(user.getUname());
				}else if (index == 2) { //已归还租赁
					recordList =  recordBiz.queryHasReturnRecords(user.getUname());
				}
				btn_rent.setEnabled(false);
				refreshTabel(recordList);
				if (recordList.size() == 0) {
					JOptionPane.showMessageDialog(UserQueryDVDView.this, "没有要查询的内容");
					return;
				}
			}
		});
		
		btn_rent.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				if (rid != 0) {
					int flag = JOptionPane.showInternalConfirmDialog(UserQueryDVDView.this, "是否租此DVD?","确认信息",JOptionPane.YES_NO_OPTION);
					if (flag == JOptionPane.YES_OPTION) {
						System.out.println(rid);
						int flags =  dvdbiz.returnDVD(rid);
						System.out.println(flags);
						 if (flags == 2) {
							JOptionPane.showMessageDialog(UserQueryDVDView.this, "已归还，请勿重复");
							return;
						} else if (flags == 3) {
							JOptionPane.showMessageDialog(UserQueryDVDView.this, "归还成功");
							return;
						} else {
							JOptionPane.showMessageDialog(UserQueryDVDView.this, "归还失败");
							return;
						} 
					}
					
				} else {
					JOptionPane.showMessageDialog(UserQueryDVDView.this, "归还失败");
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
					System.out.println("getSelectedRow");
					btn_rent.setEnabled(true);
				} 
				System.out.println("getSelectedRow000");

				int row = table.getSelectedRow();
				int recordID = (int)table.getValueAt(row, 0);
				rid = recordID;
				System.out.println(recordID);
				
			
			}
		});
		
		/**
		 * 关闭窗口
		 */
		btn_exit.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				int flag = JOptionPane.showInternalConfirmDialog(UserQueryDVDView.this, "是否确定退出窗口?","确认信息",JOptionPane.YES_NO_OPTION);
				if (flag == JOptionPane.YES_OPTION) {
					UserQueryDVDView.this.dispose();
				}
			}
		});
	}
	
	
	private class RecordInfoTableModel implements TableModel {
		private List<Record2> recordList = null;

		public RecordInfoTableModel(List<Record2> recordList) {
			// TODO Auto-generated constructor stub
			this.recordList = recordList;
		}
		@Override
		public int getRowCount() {
			// TODO Auto-generated method stub
			return recordList.size();
		}

		@Override
		public int getColumnCount() {
			// TODO Auto-generated method stub
			return 6;
		}

		@Override
		public String getColumnName(int columnIndex) {
			if (columnIndex == 0) {
				return "记录ID号";
			} else if (columnIndex == 1) {
				return "影碟ID号";
			} else if (columnIndex == 2) {
				return "用户名";
			} else if (columnIndex == 3) {
				return "影碟名字";
			}else if (columnIndex == 4) {
				return "租赁时间";
			}else if (columnIndex == 5) {
				return "影碟归还时间";
			}
			return "出错";
		}

		@Override
		public Class<?> getColumnClass(int columnIndex) {
			// TODO Auto-generated method stub
			return String.class;
		}

		@Override
		public boolean isCellEditable(int rowIndex, int columnIndex) {
			// TODO Auto-generated method stub
			return false;
		}

		@Override
		public Object getValueAt(int rowIndex, int columnIndex) {
			// TODO Auto-generated method stub
//			rec dvd = dvdList.get(rowIndex);
			Record2 record = recordList.get(rowIndex);
			if (columnIndex == 0) {
				return record.getId();
			} else if (columnIndex == 1) {
				return record.getDid();
			} else if (columnIndex == 2) {
				return record.getUname();
			} else if (columnIndex == 3) {
				return record.getDname();
			} else if (columnIndex == 4) {
				return ""+(record.getLendTime() != null ? record.getLendTime() : "未借出");
			} else if (columnIndex == 5) {
				return "" + (record.getReturnTime() != null ? record.getReturnTime() : "未归还");
			}
			return "出错";		
			}

		@Override
		public void setValueAt(Object aValue, int rowIndex, int columnIndex) {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void addTableModelListener(TableModelListener l) {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void removeTableModelListener(TableModelListener l) {
			// TODO Auto-generated method stub
			
		}
		
	}
	

	//刷新jtable 并显示数据
	private void refreshTabel(List<Record2> recordList){
		recordInfoTableModel = new RecordInfoTableModel(recordList);
		table.setModel(recordInfoTableModel);
		}
}
