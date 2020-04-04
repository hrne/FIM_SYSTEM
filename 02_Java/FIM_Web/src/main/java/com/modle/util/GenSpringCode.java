package com.modle.util;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class GenSpringCode {

	public static void main(String[] args) {

		Connection connection = null;
		Statement statement = null;
		ResultSet resultSet = null;
		// 註冊驅動
		JDBCUtil jdbcUtil = new JDBCUtil();
		try {
			//連線DB
			connection = jdbcUtil.getConnection();

			statement = connection.createStatement();
			// 執行查詢 ， 得到結果集
			String sql = "select * from sen_mach";
			resultSet = statement.executeQuery(sql);
			// 遍歷查詢每一條記錄
			while (resultSet.next()) {
				int id = resultSet.getInt("id");
				// String name = resultSet.getString("name");
				// int age = resultSet.getInt("age");

				// System.out.println("id=" + id + ",name=" + name + ",age=" + age);
				System.out.println(id);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			// 最後釋放資源
			jdbcUtil.close(connection, resultSet, statement);
		}
	}
	
	/**
	 * 產生該table的Ddao、DdaoImpl檔案
	 * @param daoPath 目的路徑
	 * @param packPath pachage路徑
	 * @param boName 產生的table檔名
	 */
	public void genDaoFile(String daoPath, String packPath, String boName) {

		String vv;
		
	}

}
