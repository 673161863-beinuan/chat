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
 * @Time 2020��5��27��
 * @Author Zhao Minglei
 *
 * @content ��װ���ݿ�������Ϣ
 */
public class ConnectionManager {

	
	/**
	 *  ��ȡ�����ݿ�����
	 * @return
	 */
	public static synchronized Connection getConnection() {
		// 1.�������Զ���
		Properties properties = new Properties();
		try {

			// 2.����������
			FileInputStream fis = new FileInputStream("config\\db.properties");
			// 3.��fis���е��������ݼ��ص����Զ�����.
			properties.load(fis);
			// 4.�ر���
			fis.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
			System.out.println("û�������ļ�");
		} catch (IOException e) {
			e.printStackTrace();
		}

		// ͨ��key��ȡvalue
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
			System.out.println("���ӳɹ�");

		} else {
			System.out.println("����ʧ��");
		}
	}

}
