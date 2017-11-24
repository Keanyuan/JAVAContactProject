package com.anjiplus.gip.view;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.Date;
import java.util.List;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;
import javax.swing.WindowConstants;
import javax.swing.table.DefaultTableModel;

import com.anjiplus.gip.domain.Ledger;
import com.anjiplus.gip.tools.DateChooser;
import com.anjiplus.gip.tools.DateUtils;
import com.anjiplus.gip.tools.GUITools;
import com.anjiplus.gip.tools.ListTableModel;



/**
 * @author apple
 * 管理账务的对话框，从主窗体中，点击账务管理按钮显示
 */
public abstract class AbstractLedgerMngDialog extends JDialog {
	protected JTextField beginDateTxt = new JTextField(6);//开始查询时间
	protected JTextField endDateTxt = new JTextField(6);//结束查询时间
	protected JComboBox  parentBox = new JComboBox ();//父分类下拉列表
	protected JComboBox sortBox = new JComboBox ();//分类下拉列表
	protected JTable ledgerDataTable = new JTable();//账务数据列表
	protected JLabel inMoneyTotalLabel = new JLabel("总收入：0.00元");
	protected JLabel payMoneyTotalLabel = new JLabel("总支出：0.00元");
	
	private JButton queryBtn = new JButton("查　询");//查询按钮
	private JButton pieBtn = new JButton("收/支比重统计");
	private JButton closeBtn = new JButton("关闭");
	
	private JButton addBtn = new JButton("添加");
	private JButton editBtn = new JButton("编辑");
	private JButton delBtn = new JButton("删除");
	
	public AbstractLedgerMngDialog(JFrame frame) {
		super(frame, false);
		initDialog();
	}
	
	protected void initDialog() {
		this.init();
		this.addComponent();
		this.addListener();
	}
	
	private void init() {
		this.setResizable(false);// 设置窗体大小不可变
		this.setTitle("账务管理：");// 设置标题
		this.setSize(680, 400);// 设置大小
		GUITools.center(this);//设置居中
		this.setLayout(null);
		this.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);// 设置关闭按钮
	}
	
	private void addComponent() {
		// 设置标签标题
		JLabel titleLabel = new JLabel();
		titleLabel.setFont(new Font("宋体", Font.ITALIC, 18));
		titleLabel.setText("账务管理");
		titleLabel.setBounds(280, 20, 165, 20);
		this.add(titleLabel);

		// 起始日期
		JLabel beginDateLabel = new JLabel("起始：");
		beginDateLabel.setBounds(30, 70, 60, 28);
		this.beginDateTxt.setBounds(70, 70, 80, 28);
		this.beginDateTxt.setEditable(false);
		beginDateTxt.setText(String.format("%tF", DateUtils.getFirstDayOfMethod()));
		DateChooser.getInstance().register(this.beginDateTxt);

		this.add(beginDateLabel);
		this.add(this.beginDateTxt);

		// 结束日期
		JLabel endDateLabel = new JLabel("至：");
		endDateLabel.setBounds(160, 70, 30, 28);
		this.endDateTxt.setBounds(190, 70, 80, 28);
		this.endDateTxt.setEditable(false);
		endDateTxt.setText(String.format("%tF", new Date()));
		DateChooser.getInstance().register(this.endDateTxt);

		this.add(endDateLabel);
		this.add(this.endDateTxt);

		// 收支选择
		JLabel inAndPayLabel = new JLabel("收/支：");
		inAndPayLabel.setBounds(280, 70, 50, 28);
		parentBox.setModel(new DefaultComboBoxModel<Object>(new Object[] {"-请选择-", "收入/支出", "收入", "支出"}));
		this.parentBox.setBounds(320, 70, 90, 28);
		this.add(inAndPayLabel);
		this.add(this.parentBox);

		// 收支项目
		JLabel sortLabel = new JLabel("分类：");
		sortLabel.setBounds(420, 70, 50, 28);
		sortBox.setModel(new DefaultComboBoxModel<Object>(new Object[]{"-请选择-"}));
		this.sortBox.setBounds(460, 70, 110, 28);
		this.add(sortLabel);
		this.add(this.sortBox);

		// 查询按钮
		queryBtn.setBounds(580, 70, 70, 28);
		this.add(queryBtn);

		// 滚动面板
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(30, 100, 620, 160);
		setTableModel(null);
		
		ledgerDataTable.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);//单选
		ledgerDataTable.getTableHeader().setReorderingAllowed(false);//列不能移动

		scrollPane.setViewportView(ledgerDataTable);
		this.add(scrollPane);

		// 总收入标签
		this.inMoneyTotalLabel.setBounds(400, 260, 120, 28);
		this.add(inMoneyTotalLabel);
		inMoneyTotalLabel.setForeground(new java.awt.Color(0, 102, 0));

		// 总支出标签
		this.payMoneyTotalLabel.setBounds(530, 260, 120, 28);
		this.add(this.payMoneyTotalLabel);
		payMoneyTotalLabel.setForeground(new java.awt.Color(255, 0, 0));
		
		// 按钮
		addBtn.setBounds(30, 290, 140, 28);
		this.add(addBtn);
		editBtn.setBounds(270, 290, 140, 28);
		this.add(editBtn);
		delBtn.setBounds(510, 290, 140, 28);
		this.add(delBtn);

		// 收支统计报表按钮
		pieBtn.setBounds(30, 330, 140, 28);
		this.add(pieBtn);

		// 关闭按钮
		closeBtn.setBounds(570, 330, 80, 28);
		this.add(closeBtn);
	}

	/**
	 * 通过表格行号获取账务对象
	 */
	@SuppressWarnings("unchecked")
	protected Ledger getLedgerByTableRow(int row) {
		return ((ListTableModel<Ledger>)ledgerDataTable.getModel()).getInstance(row);
	}

	/**
	 * 显示账务表格
	 */
	protected void setTableModel(List<Ledger> ledgerList) {
		String[] colNames = new String[] {"ID", "收/支", "分类", "金额", "账户", "创建时间", "说明"};
		String[] propNames = new String[] {"lid", "parent", "sname", "money", "account", "createtime", "ldesc"};
		if(ledgerList == null || ledgerList.size() == 0) {
			ledgerDataTable.setModel(new DefaultTableModel(new Object[][] {
					{ null, null, null, null, null, null, null }, { null, null, null, null, null, null, null },
					{ null, null, null, null, null, null, null }, { null, null, null, null, null, null, null },
					{ null, null, null, null, null, null, null }, { null, null, null, null, null, null, null },
					{ null, null, null, null, null, null, null }, { null, null, null, null, null, null, null }
				},colNames));
			ledgerDataTable.setEnabled(false);
			return;
		}
		try {
			ledgerDataTable.setModel(new ListTableModel<Ledger>(ledgerList, Ledger.class, colNames, propNames));
			ledgerDataTable.setEnabled(true);
		}catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * 给组件添加监听器
	 */
	private void addListener() {
		/*
		 * 查询按钮事件监听器
		 */
		queryBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent evt) {
				queryLedger();
			}
		});
		/*
		 * 收支统计图按钮事件监听器
		 */
		pieBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent evt) {
				pie();
			}
		});
		closeBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent evt) {
				AbstractLedgerMngDialog.this.dispose();
			}
		});
		/*
		 * 添加账务按钮事件监听器
		 */
		addBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent evt) {
				addLedger();
			}
		});
		/*
		 * 编辑账务按钮事件监听器
		 */
		editBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent evt) {
				editLedger();
			}
		});
		/*
		 * 删除账务按钮事件监听器
		 */
		delBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent evt) {
				deleteLedger();
			}
		});
		/*
		 * 选择分类，父分类下拉菜单事件
		 * 实现联动
		 */
		parentBox.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent evt) {
				parentChange();
			}
		});
		
		ledgerDataTable.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				if(e.getButton() == 1) {
					if(e.getClickCount() >= 2) {
						editLedger();		
					}
				}
			}
		});
	}

	/**
	 * 添加账务
	 */
	public abstract void addLedger();
	
	/**
	 * 添加账务
	 */
	public abstract void editLedger();
	
	/**
	 * 删除账务
	 */
	public abstract void deleteLedger();
	
	/**
	 * 查询账务
	 * @param form
	 * @return
	 */
	public abstract void queryLedger();
	
	/**
	 * 父分类变化
	 */
	public abstract void parentChange();
	

	/**
	 * 生成饼形图
	 */
	public abstract void pie();
}


