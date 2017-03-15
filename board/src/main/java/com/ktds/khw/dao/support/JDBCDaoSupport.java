package com.ktds.khw.dao.support;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public abstract class JDBCDaoSupport {

	private void loadDriver() {
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
		} catch (ClassNotFoundException e) {
			throw new RuntimeException(e.getMessage(), e);
		}
	}
	
	public List selectList(QueryHandler queryHandler) {

		loadDriver();

		Connection conn = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;


		try {
			conn = getConnection();

			String query = queryHandler.preparedQuery();
			stmt = conn.prepareStatement(query);

			queryHandler.mappingParameters(stmt);
			rs = stmt.executeQuery();

			List result = new ArrayList();
			while (rs.next()) {
				result.add(queryHandler.getData(rs));
			}

			return result;

		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		} finally {
			close(conn, stmt, rs);
		}
	}


	public Connection getConnection () throws SQLException {
		Connection conn;
		String oracleUrl = "jdbc:oracle:thin:@172.20.10.9:1521:XE";
		conn = DriverManager.getConnection(oracleUrl, "BOARD", "board");
		return conn;
	}

	private void close (Connection conn, PreparedStatement stmt, ResultSet rs) {

		try {
			if (rs != null) {
				rs.close();
			}
		} catch (SQLException e) {

		}
		try {
			if (stmt != null) {
				stmt.close();
			}
		} catch (SQLException e) {

		}
		try {
			if (conn != null) {
				conn.close();
			}
		} catch (SQLException e) {

		}
	}



	public  int update(QueryHandler queryHandler) {

		loadDriver();

		Connection conn = null;
		PreparedStatement stmt = null;


		try {
			conn = getConnection();

			String query = queryHandler.preparedQuery();
			stmt = conn.prepareStatement(query);

			queryHandler.mappingParameters(stmt);
			return stmt.executeUpdate();


		} catch (SQLException e) {
			throw new RuntimeException(e.getMessage(), e);
		} finally {
			close( conn, stmt, null);
		}

	}

	public Object selectOne (QueryHandler queryHandler ) {

		loadDriver();

		Connection conn = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;

		try {
			conn = getConnection();
			stmt = conn.prepareStatement(queryHandler.preparedQuery());

			queryHandler.mappingParameters(stmt);

			rs = stmt.executeQuery();


			Object result = null;

			if ( rs.next() ){
				result = queryHandler.getData(rs);
			}

			return result;

		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		} finally {
			close(conn, stmt, rs);
		}

	}
	
}
