package com.modle.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;


/**
 * jdbc資料庫連線
 * 
 * @author hrne
 *
 */
public class JDBCUtil {

	public Connection conn = null;
	public Statement stmt = null;
	public ResultSet rs = null;

	public Connection getConnection() {
		// 資料庫設定
		PropertiesUtils.loadFile("database.properties");
		String driver = PropertiesUtils.getPropertyValue("jdbc.driverClassName");
		String url = PropertiesUtils.getPropertyValue("jdbc.url");
		String username = PropertiesUtils.getPropertyValue("jdbc.username");
		String password = PropertiesUtils.getPropertyValue("jdbc.password");

		Connection conn = null;
		try {
			Class.forName(driver);
			// 開啟連線
			conn = (Connection) DriverManager.getConnection(url, username, password);
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		if (conn == null) {
			System.out.println("db connect error!!!!!");
		}
		return conn;
	}

	/**
	 * 釋放資源
	 * <p>
	 * Title: close
	 * </p>
	 * <p>
	 * Description:
	 * </p>
	 * 
	 * @param connection
	 * @param resultSet
	 * @param statement
	 */
	public void close(Connection connection, ResultSet resultSet, Statement statement) {
		closeRS(resultSet);
		closeSt(statement);
		closeConn(connection);

	}

	private void closeRS(ResultSet resultSet) {
		try {
			if (resultSet != null) {
				resultSet.close();
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			resultSet = null;
		}
	}

	private void closeSt(Statement statement) {
		try {
			if (statement != null) {
				statement.close();
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			statement = null;
		}
	}

	private void closeConn(Connection connection) {
		try {
			if (connection != null) {
				connection.close();
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			connection = null;
		}
	}

}
