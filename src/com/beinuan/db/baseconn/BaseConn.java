package com.beinuan.db.baseconn;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.beinuan.db.conn.ConnectionManager;

public class BaseConn {
	static Connection conn = ConnectionManager.getConnection();
	static PreparedStatement pstmt = null;
	static ResultSet rs = null;

	// 查询功能
	public static ResultSet getResultSet(String sql) {
		ResultSet rs = null;
		try {
			pstmt = conn.prepareStatement(sql);
			rs = pstmt.executeQuery();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return rs;

	}

	// 增删改
	public static void myUpdate(String sql) {
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	// 关闭连接
	public static void close() {

		try {
			if (rs != null) {
				rs.close();
			}
			if (pstmt != null) {
				pstmt.close();
			}
			if (conn != null) {
				conn.close();
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}

	}
}
