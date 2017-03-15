package com.ktds.khw.board.user.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.ktds.khw.board.user.vo.UserVO;

public class UserDaoImpl implements UserDao{

	@Override
	public int addUser(UserVO userVO) {
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
			System.out.println("오라클 드라이버 로딩 실패, 시스템을 종료합니다.");
			return 0;
		}

		Connection conn = null;
		PreparedStatement stmt = null;

		String oracleUrl = "jdbc:oracle:thin:@172.20.10.9:1521:XE";

		try {
			conn = DriverManager.getConnection(oracleUrl, "BOARD", "board");

			StringBuffer query = new StringBuffer();
			query.append(" INSERT	INTO USRS	( ");
			query.append("					USR_ID");
			query.append("					, USR_NM");
			query.append("					, USR_PWD");
			query.append("					, JOIN_DT");
			query.append("						 ) ");
			query.append(" VALUES				( ");
			query.append("					 ? ");
			query.append("					, ? ");
			query.append("					, ? ");
			query.append("					, SYSDATE ");
			query.append("						) ");

			stmt = conn.prepareStatement(query.toString());
			stmt.setString(1, userVO.getUserId());
			stmt.setString(2, userVO.getUserName());
			stmt.setString(3, userVO.getUserPassword());
			
			return stmt.executeUpdate();

		} catch (SQLException e) {
			e.printStackTrace();
			System.out.println("오라클 인스턴스 로딩 실패, 시스템을 종료합니다.");
			return 0;
		} finally {
			try {
				if (stmt != null) {
					stmt.close();
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
			try {
				if (conn != null) {
					conn.close();
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}

	}

	@Override
	public UserVO signIn(UserVO userVO) {
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
			System.out.println("오라클 드라이버 로딩 실패, 시스템을 종료합니다.");
			return null;
		}

		Connection conn = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;

		String oracleUrl = "jdbc:oracle:thin:@172.20.10.9:1521:XE";

		try {
			conn = DriverManager.getConnection(oracleUrl, "BOARD", "board");

			StringBuffer query = new StringBuffer();
			query.append(" SELECT		USR_ID ");
			query.append("				, USR_NM ");
			query.append("				, USR_PWD ");
			query.append("				, JOIN_DT ");
			query.append(" FROM			USRS");
			query.append(" WHERE		USR_ID = ? ");
			query.append(" AND			USR_PWD = ? ");

			stmt = conn.prepareStatement(query.toString());
			stmt.setString(1, userVO.getUserId());
			stmt.setString(2, userVO.getUserPassword());
			rs = stmt.executeQuery();
			
			//userVO = null;
			
			if ( rs.next() ) {
				userVO.setUserId(rs.getString("USR_ID"));
				userVO.setUserName(rs.getString("USR_NM"));
				userVO.setUserPassword(rs.getString("USR_PWD"));
				userVO.setJoinDate(rs.getString("JOIN_DT"));
			}
			return userVO;

		} catch (SQLException e) {
			e.printStackTrace();
			System.out.println("오라클 인스턴스 로딩 실패, 시스템을 종료합니다.");
			return null;
		} finally {
			try {
				if ( rs != null ) {
					rs.close();
				}
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
			try {
				if (stmt != null) {
					stmt.close();
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
			try {
				if (conn != null) {
					conn.close();
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		
	}	
	

}
