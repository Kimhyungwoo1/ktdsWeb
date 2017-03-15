package com.ktds.khw.book.users.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.ktds.khw.book.users.vo.UsersVO;

public class UsersDaoImpl implements UsersDao {

	private String oracleUrl = "jdbc:oracle:thin:@172.20.10.9:1521:XE";

	@Override
	public int addUser(UsersVO usersVO) {

		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
		} catch (ClassNotFoundException e) {
			throw new RuntimeException(e.getMessage(), e);
		}

		Connection conn = null;
		PreparedStatement stmt = null;

		try {
			conn = DriverManager.getConnection(oracleUrl, "BOOK", "book");

			StringBuffer query = new StringBuffer();
			query.append(" INSERT	INTO USRS	( ");
			query.append("						USR_ID");
			query.append("						, USR_NM");
			query.append("						, USR_PWD");
			query.append("						) ");
			query.append(" VALUES				( ");
			query.append("						? ");
			query.append("						, ? ");
			query.append("						, ? ");
			query.append("						) ");

			stmt = conn.prepareStatement(query.toString());
			stmt.setString(1, usersVO.getUserId());
			stmt.setString(2, usersVO.getUserName());
			stmt.setString(3, usersVO.getPassword());

			return stmt.executeUpdate();

		} catch (SQLException e) {
			throw new RuntimeException(e.getMessage(), e);
		} finally {
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

	}

	@Override
	public UsersVO oneUser(UsersVO usersVO) {

		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
		} catch (ClassNotFoundException e) {
			throw new RuntimeException(e.getMessage(), e);
		}

		Connection conn = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;

		try {
			conn = DriverManager.getConnection(oracleUrl, "BOOK", "book");

			StringBuffer query = new StringBuffer();
			query.append(" SELECT	USR_ID ");
			query.append(" 			, USR_NM ");
			query.append("			, USR_PWD ");
			query.append(" FROM		USRS ");
			query.append(" WHERE		USR_ID = ? ");
			query.append(" AND		USR_PWD = ? ");

			stmt = conn.prepareStatement(query.toString());
			stmt.setString(1, usersVO.getUserId());
			stmt.setString(2, usersVO.getPassword());

			rs = stmt.executeQuery();

			UsersVO user = null;
			if (rs.next()) {
				user = new UsersVO();
				user.setUserId(rs.getString("USR_ID"));
				user.setUserName(rs.getString("USR_NM"));
				user.setPassword(rs.getString("USR_PWD"));
			}
			return user;

		} catch (SQLException e) {
			throw new RuntimeException(e.getMessage(), e);
		} finally {
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

	}

}
