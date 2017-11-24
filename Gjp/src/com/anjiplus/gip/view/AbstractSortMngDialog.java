package com.anjiplus.gip.view;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.ListSelectionModel;
import javax.swing.table.DefaultTableModel;

import com.anjiplus.gip.domain.Sort;
import com.anjiplus.gip.tools.GUITools;
import com.anjiplus.gip.tools.ListTableModel;


/**
 * 
 * @author apple
 * 弹出分类管理对话框
 */
public abstract class AbstractSortMngDialog extends JDialog {
	/**
	 * 
	 */
	private static final long serialVersionUID = 455197641882704632L;
	//在对话框，显示数据的表格，权限设置是为子类使用
	protected JTable sortDataTable = new JTable();//账户数据列表
	private JButton closeBtn = new JButton("关闭");

	private JButton addBtn = new JButton("添加");
	private JButton editBtn = new JButton("编辑");
	private JButton delBtn = new JButton("删除");
	
	public AbstractSortMngDialog(JFrame frame) {
		super(frame, true);
		this.initDialog();
	}
	
	protected void initDialog() {
		this.init();
		this.addComponent();
		this.addListener();
	}
	
	private void init() {
		this.setResizable(false);// 设置窗体大小不可变
		this.setTitle("分类管理：");// 设置标题
		this.setSize(680, 400);// 设置大小
		GUITools.center(this);//设置居中
		this.setLayout(null);
		this.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);// 设置关闭按钮
	}
	
	private void addComponent() {
		// 设置标签标题
		JLabel titleLable = new JLabel();
		titleLable.setFont(new Font("宋体", Font.ITALIC, 18));
		titleLable.setText("分类管理");
		titleLable.setBounds(280, 20, 165, 20);
		this.add(titleLable);

		// 滚动面板,带有滚动条的容器
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(30, 100, 620, 160);
		
		sortDataTable.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);//单选
		sortDataTable.getTableHeader().setReorderingAllowed(false);//列不能移动

		//在带有滚动条的容器上，设置显示表格
		scrollPane.setViewportView(sortDataTable);
		this.add(scrollPane);
		
		// 按钮
		addBtn.setBounds(30, 290, 140, 28);
		this.add(addBtn);
		editBtn.setBounds(270, 290, 140, 28);
		this.add(editBtn);
		delBtn.setBounds(510, 290, 140, 28);
		this.add(delBtn);

		// 关闭按钮
		closeBtn.setBounds(570, 330, 80, 28);
		this.add(closeBtn);
	}

	/**
	 * 显示分类表格
	 */
	protected void setTableModel(List<Sort> sortList) {
		String[] colNames = new String[] {"ID", "分类名称", "父分类", "说明"};
		String[] propNames = new String[] {"sid", "sname", "parent", "sdesc"};
		if(sortList == null || sortList.size() == 0) {
			sortDataTable.setModel(new DefaultTableModel(new Object[][] {
					{null, null, null, null}, {null, null, null, null},
					{null, null, null, null}, {null, null, null, null},
					{null, null, null, null}, {null, null, null, null},
					{null, null, null, null}, {null, null, null, null}
				},colNames));
			sortDataTable.setEnabled(false);
			return;
		}
		try {
			sortDataTable.setModel(new ListTableModel<Sort>(sortList, Sort.class, colNames, propNames));
			sortDataTable.setEnabled(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	@SuppressWarnings("all")
	/*
	 * 编辑，删除分类
	 * 传递选择的第几行
	 * 将选择的那一行，所有数据封装成一个Sort对象
	 */
	protected Sort getSortByTableRow(int row) {
		return ((ListTableModel<Sort>)sortDataTable.getModel()).getInstance(row);
	}

	/**
	 * 
	 * 给组件添加监听器
	 */
	private void addListener() {
		closeBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent evt) {
				AbstractSortMngDialog.this.dispose();
			}
		});
		/*
		 * 添加分类按钮的事件监听器
		 */
		addBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent evt) {
				addSort();
			}
		});
		/*
		 * 编辑分类按钮的事件监听器
		 */
		editBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent evt) {
				editSort();
			}
		});
		/*
		 * 删除分类按钮的事件监听器
		 */
		delBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent evt) {
				deleteSort();
			}
		});
		/*
		 * 显示数据的表格，鼠标的双击事件
		 * 调用编辑分类方法
		 */
		sortDataTable.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				if(e.getButton() == 1) {
					if(e.getClickCount() >= 2) {
						editSort();		
					}
				}
			}
		});
	}
	/*
	 * 点击添加分类后调用
	 * 子类重写，运行的是子类重写
	 */
	public abstract void addSort();
	/*
	 * 点击编辑分类后调用
	 */
	public abstract void editSort();
	/*
	 * 点击删除分类后调用
	 */
	public abstract void deleteSort();
}
