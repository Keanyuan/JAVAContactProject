package com.anjiplus.gip.tools;

import javax.sql.DataSource;
import org.apache.commons.dbcp2.BasicDataSource;


public class JDBCUtils {
	public static final String DRIVER_CLASS_NAME = "com.mysql.jdbc.Driver";
	public static final String URL = "jdbc:mysql://localhost:3306/gjp?characterEncoding=utf8&useSSL=true";
	public static final String USERNAME = "root";
	public static final String PASSWORD = "root";
	
	private static final int MAX_IDLE = 3;
	private static final long MAX_WAIT = 5000;
	private static final int MAX_ACTIVE = 5;
	private static final int INITIAL_SIZE = 3;
	
	private static BasicDataSource dataSource = new BasicDataSource();
	static {
		dataSource.setDriverClassName(DRIVER_CLASS_NAME);//驱动类名称
		dataSource.setUrl(URL);//设置数据连接
		dataSource.setUsername(USERNAME);//设置登录用户名
		dataSource.setPassword(PASSWORD);//设置登录密码
	
		dataSource.setMaxIdle(MAX_IDLE);//连接池最大空闲连接个数
		dataSource.setMaxWaitMillis(MAX_WAIT);//连接池最大等待时间
		dataSource.setMaxTotal(MAX_ACTIVE);//连接池最大连接个数
		dataSource.setInitialSize(INITIAL_SIZE);//连接池初始化连接个数
	}
	
	public static DataSource getDataSource() {
		return dataSource;
	}
}

