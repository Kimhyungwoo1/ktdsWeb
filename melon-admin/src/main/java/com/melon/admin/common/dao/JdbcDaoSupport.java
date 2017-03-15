package com.melon.admin.common.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.melon.admin.authorization.vo.AuthorizationVO;

public abstract class JdbcDaoSupport {

	private String oracleUrl = "jdbc:oracle:thin:@172.20.10.9:1521:XE";

	
	public Object selectOne() {
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
		} catch (ClassNotFoundException e) {
			throw new RuntimeException(e.getMessage(), e);
		}

		Connection conn = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;

		try {
			conn = DriverManager.getConnection(oracleUrl, "MELON", "melon");

//			StringBuffer query = new StringBuffer();
//			query.append(" SELECT	'AT-' || TO_CHAR(SYSDATE, 'TTTTMMDDHH24') || '-' || LPAD (ATHRZTIN_ID_SEQ.NEXTVAL, 6, '0') SEQ ");
//			query.append(" FROM		DUAL ");
			String query = query();
			
			stmt = conn.prepareStatement(query);
			// 파라미터 
			mappingParams(stmt);
			
			
			rs = stmt.executeQuery();
			
			if ( rs.next() ) {
				//VO 만들기
				return bindData(rs);
			}
			return null;

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
	
	public Object allList() {
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
		} catch (ClassNotFoundException e) {
			throw new RuntimeException(e.getMessage(), e);
		}
		Connection conn = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;

		try {
			conn = DriverManager.getConnection(oracleUrl, "MELON", "melon");

			String query = query();

			stmt = conn.prepareStatement(query.toString());
			mappingParams(stmt);

			rs = stmt.executeQuery();

//			List<AuthorizationVO> authorizationList = new ArrayList<AuthorizationVO>();
//			AuthorizationVO authorizationVO = null;

			while (rs.next()) {
				return bindData(rs);
			}
			return null;

		} catch (SQLException e) {
			throw new RuntimeException(e.getMessage(), e);
		} finally {
			try {
				if (rs != null) {
					rs.close();
				}
			} catch (SQLException e1) {
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
	
	//다른부분을 여기에 추가
	
	public abstract String query();
	
	public abstract void mappingParams(PreparedStatement stmt)  throws SQLException; 
	
	public abstract Object bindData(ResultSet rs) throws SQLException;
	
}
