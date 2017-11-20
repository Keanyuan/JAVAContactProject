package com.iotek.view;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;
import java.util.List;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.event.TableModelListener;
import javax.swing.table.TableModel;

import com.iotek.biz.DVDBiz;
import com.iotek.biz.impl.DVDBizImpl;
import com.iotek.entity.DVD;
import com.iotek.util.DVDUtil;

public class AdminDVDOperatorView extends JInternalFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 7392664926696825770L;

	private JPanel paneltabel = null;//用来保存Jtable的面板
	private JTable table = null;//声明jtable
	private JPanel panelButton = null;//按钮面板	
	private JButton btn_search = null;
	private JButton btn_insert = null;
	private JButton btn_update = null;
	private JButton btn_del = null;
	private JButton btn_exit = null;
	private JComboBox<String> cb_type = null;
	private JLabel lb_type = null;
	private JTextField tf_selectf = null;//查询输入框

	
	private JPanel panelBottom = null;//底部输入面板
	private JLabel lb_dvdName = null;//dvd名字
	private JLabel lb_sendCount = null;	//借出次数
	private JLabel lb_dvdStatus = null;//借出状态
	private JTextField tf_dvdName = null;//DVD名字
	private JTextField tf_sendCount = null;//借出次数
	private JComboBox<String> cb_dvdStatus = null;//DVD状态
	private DVDBiz dvdbiz=null;
	private List<DVD> dvdList = null;

	private DVDInfoTableModel infoTableModel = null;

	
	
	public AdminDVDOperatorView() {
		dvdbiz = new DVDBizImpl();
		init();
		registerListener();
	}
	
	private void init(){
		this.setTitle("管理员DVD操作");
		this.setSize(800,500);
		this.setIconifiable(true);//窗体可最小化
		this.setClosable(true);//窗体可被关闭
		this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		this.setLayout(new BorderLayout());
		dvdList = new ArrayList<DVD>();
		table = new JTable();
		//让jtable绑定数据模型
		refreshTabel(dvdList);
		paneltabel = new JPanel(new BorderLayout());//创建面板
		//给面板设置边框
		paneltabel.setBorder(BorderFactory.createTitledBorder(BorderFactory.createEtchedBorder(null, null), "查询信息"));
		paneltabel.add(table);
		this.add(paneltabel, BorderLayout.CENTER);
		
		panelButton = new JPanel(new GridLayout(9, 1, 10, 10));
		panelButton.setBorder(BorderFactory.createTitledBorder(BorderFactory.createEtchedBorder(null, null), "查询条件"));
		this.add(panelButton, BorderLayout.EAST);
		lb_type = new JLabel("查询类型");
		panelButton.add(lb_type);
		cb_type = new JComboBox<String>(new String[]{"所有DVD","DVD编号","DVD名字"});
		tf_selectf = new JTextField(8);
		tf_selectf.setEditable(false);
		btn_search = new JButton("查询");
		panelButton.add(btn_search);
		btn_insert = new JButton("添加DVD");
		btn_update = new JButton("更新DVD");
		btn_del = new JButton("删除DVD");
		btn_update.setEnabled(false);
		btn_del.setEnabled(false);
		btn_exit = new JButton("退出窗口");

		panelButton.add(cb_type);
		panelButton.add(tf_selectf);
		panelButton.add(btn_search);
		panelButton.add(btn_insert);
		panelButton.add(btn_update);
		panelButton.add(btn_del);
		panelButton.add(new JLabel());
		panelButton.add(btn_exit);

		panelBottom = new JPanel(new GridLayout(1, 9, 10, 10));
		this.add(panelBottom, BorderLayout.SOUTH);
		
		lb_dvdName = new JLabel("DVD名字");
		lb_sendCount = new JLabel("借出次数");
		lb_dvdStatus = new JLabel("借出状态");
		tf_dvdName = new JTextField(8);
		tf_sendCount = new JTextField(8);
		cb_dvdStatus = new JComboBox<String>(new String[]{"已借","可借"});
		panelBottom.add(lb_dvdName);
		panelBottom.add(tf_dvdName);
		panelBottom.add(lb_sendCount);
		panelBottom.add(tf_sendCount);
		panelBottom.add(lb_dvdStatus);
		panelBottom.add(cb_dvdStatus);
		panelBottom.add(new JLabel());
		panelBottom.add(new JLabel());
		this.setVisible(true);
				
	}
	
	private void registerListener(){
		
		btn_search.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				//获取选择下拉列表框的index
				int index = cb_type.getSelectedIndex();
				//获取文本框内容
				String content = tf_selectf.getText().trim();
				if (index != 0 && content.equals("")) {
					JOptionPane.showMessageDialog(AdminDVDOperatorView.this, "查询内容不能为空");
					return;
				}
				//先清除数据 防止数据累加
				if (dvdList != null) {
					dvdList.clear();
				}
				
				if (index == 0) {
					dvdList =  dvdbiz.queryAllDVDs();//查询所有数据
				} else if (index == 1) {
					if (DVDUtil.isNumber(content)) {
						DVD dvd = dvdbiz.queryDVDById(Integer.parseInt(content));
						if (dvd != null) {
							dvdList.add(dvd);
						} 
//						else {
//							JOptionPane.showMessageDialog(AdminDVDOperatorView.this, "查询为空");
//							return;
//						}
					} else {
						JOptionPane.showMessageDialog(AdminDVDOperatorView.this, "输入的编号只能是数字");
						return;
					}
				} else {
					dvdList =  dvdbiz.queryDVDByName(content);
				}
				
				refreshTabel(dvdList);
				btn_update.setEnabled(false);
				btn_del.setEnabled(false);
				if (dvdList.size() == 0) {
					JOptionPane.showMessageDialog(AdminDVDOperatorView.this, "没有要查询的内容");
					return;
				}
				
			}
		});
		
		
		
		btn_insert.addActionListener(new ActionListener() {
			
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				String dname = tf_dvdName.getText().trim();
				String dCount = tf_sendCount.getText().trim();
				
				int status = cb_dvdStatus.getSelectedIndex();//0 已借 1 可借
				if (dname.equals("")) {
					JOptionPane.showMessageDialog(AdminDVDOperatorView.this, "DVD名字不能为空");
					return;
				} 
				if (dCount.equals("")){
					JOptionPane.showMessageDialog(AdminDVDOperatorView.this, "借出次数不能为空");
					return;
				}
				
				if (!DVDUtil.isNumber(dCount)) {
					JOptionPane.showMessageDialog(AdminDVDOperatorView.this, "借出次数只能为数字");
					return;
				}
				
				int flag = JOptionPane.showInternalConfirmDialog(AdminDVDOperatorView.this, "是否确定添加DVD?","确认信息",JOptionPane.YES_NO_OPTION);
				if (flag == JOptionPane.YES_OPTION) {
					List<DVD> dvdList = dvdbiz.queryDVDByName(dname);
					if (dvdList.isEmpty()) {
						boolean res = dvdbiz.addDVD(new DVD(dname, new Integer(dCount), status));
						if (res) {
							JOptionPane.showMessageDialog(AdminDVDOperatorView.this, "添加成功！");
						} else {
							JOptionPane.showMessageDialog(AdminDVDOperatorView.this, "添加失败，请联系管理员！");
						}
					} else {
						JOptionPane.showMessageDialog(AdminDVDOperatorView.this, "添加失败,DVD已存在");
					}
					
					
				}
			}
		});
		
		cb_type.addItemListener(new ItemListener() {
			
			@Override
			public void itemStateChanged(ItemEvent e) {
				// TODO Auto-generated method stub
				String item =  e.getItem().toString();
				//先清空
				tf_selectf.setText("");
				if (item.equals("所有DVD")) {
					tf_selectf.setEditable(false);
				} else {
					tf_selectf.setEditable(true);
				}
			}
		});
		
		
		table.addMouseListener(new MouseListener() {
			
			@Override
			public void mouseReleased(MouseEvent e) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void mousePressed(MouseEvent e) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void mouseExited(MouseEvent e) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void mouseEntered(MouseEvent e) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void mouseClicked(MouseEvent e) {
				// TODO Auto-generated method stub
				//假设选中一行更新删除可用
				if (table.getSelectedRow() != -1) {
					btn_update.setEnabled(true);
					btn_del.setEnabled(true);
				} 
				int row = table.getSelectedRow();
				String dname = table.getValueAt(row, 1).toString();
				String dcount = table.getValueAt(row, 2).toString();
				String status = table.getValueAt(row, 3).toString();
				tf_dvdName.setText(dname);
				tf_sendCount.setText(dcount);
				cb_dvdStatus.setSelectedItem(status);
				
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
			// TODO Auto-generated method stub
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
