package com.iotek.view;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
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
import javax.swing.JTextField;
import javax.swing.event.TableModelListener;
import javax.swing.table.TableModel;

import com.iotek.biz.RecordBiz;
import com.iotek.biz.impl.RecordBizImpl;
import com.iotek.entity.Record2;

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

	private RecordBiz recordBiz = null;
	private List<Record2> recordList = null;

	private RecordInfoTableModel recordInfoTableModel = null;

	
	public AdminQueryDVDRecordVeiw() {
		// TODO Auto-generated constructor stub
		recordBiz = new RecordBizImpl();
		init();
		registerListener();

	}
	
	private void init(){
		this.setTitle("管理员操作租赁记录查询");
		this.setSize(800,500);
		this.setIconifiable(true);//窗体可最小化
		this.setClosable(true);//窗体可被关闭
		this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		recordList = new ArrayList<Record2>();
		table = new JTable();
		refreshTabel(recordList);

		paneltabel = new JPanel(new BorderLayout());//创建面板
		//给面板设置边框
		paneltabel.setBorder(BorderFactory.createTitledBorder(BorderFactory.createEtchedBorder(null, null), "DVD租赁记录查询"));
		paneltabel.add(new JScrollPane(table), BorderLayout.CENTER);
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
	private void registerListener(){
		
		/**
		 * 查询数据
		 */
		btn_search.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				//获取选择下拉列表框的index
				int index = cb_type.getSelectedIndex();
				//获取文本框内容
				String content = tf_searchName.getText().trim();
				if (content.equals("")) {
					JOptionPane.showMessageDialog(AdminQueryDVDRecordVeiw.this, "查询内容不能为空");
					return;
				}
				
				//先清除数据 防止数据累加
				if (recordList != null) {
					recordList.clear();
				}
				
				if (index == 0) {//指定用户
					recordList =  recordBiz.queryUserRecords(content);
				} else if (index == 1) { //指定名字
					recordList =  recordBiz.queryDVDRecords(content);
				}
				refreshTabel(recordList);
				if (recordList.size() == 0) {
					JOptionPane.showMessageDialog(AdminQueryDVDRecordVeiw.this, "没有要查询的内容");
					return;
				}
				
			}
		});
		
		/**
		 * 关闭窗口
		 */
		btn_exit.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				int flag = JOptionPane.showInternalConfirmDialog(AdminQueryDVDRecordVeiw.this, "是否确定退出窗口?","确认信息",JOptionPane.YES_NO_OPTION);
				if (flag == JOptionPane.YES_OPTION) {
					AdminQueryDVDRecordVeiw.this.dispose();
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
