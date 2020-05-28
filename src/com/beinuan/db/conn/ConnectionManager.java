package com.beinuan.db.conn;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.ObjectInputStream.GetField;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

/**
 * @Time 2020年5月27日
 * @Author Zhao Minglei
 *
 * @content 封装数据库连接信息
 */
public class ConnectionManager {

	
	/**
	 *  获取到数据库连接
	 * @return
	 */
	public static synchronized Connection getConnection() {
		// 1.创建属性对象
		Properties properties = new Properties();
		try {

			// 2.创建输入流
			FileInputStream fis = new FileInputStream("config\\db.properties");
			// 3.将fis流中的所有数据加载到属性对象中.
			properties.load(fis);
			// 4.关闭流
			fis.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
			System.out.println("没有配置文件");
		} catch (IOException e) {
			e.printStackTrace();
		}

		// 通过key获取value
		String driverName = properties.getProperty("db.driver");
		String url = properties.getProperty("db.url");
		String user = properties.getProperty("db.username");
		String password = properties.getProperty("db.password");
		Connection conn = null;
		try {
			Class.forName(driverName);
			conn = DriverManager.getConnection(url, user, password);
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return conn;

	}

	public static void main(String[] args) {
		Connection connection = ConnectionManager.getConnection();
		if (connection != null) {
			System.out.println("连接成功");

		} else {
			System.out.println("连接失败");
		}
	}

}
