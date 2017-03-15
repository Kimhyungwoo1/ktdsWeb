package com.ktds.khw.department.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.ktds.khw.department.vo.DepartmentVO;

public class DepartmentDaoImpl implements DepartmentDao {

	private String oracleUrl = "jdbc:oracle:thin:@172.20.10.9:1521:XE";
	
	@Override
	public List<DepartmentVO> departmentList() {
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
		} catch (ClassNotFoundException e) {
			throw new RuntimeException(e.getMessage(), e);
		}

		Connection conn = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;

		try {
			conn = DriverManager.getConnection(oracleUrl, "UNIVERSITY", "university");

			StringBuffer query = new StringBuffer();
			query.append(" SELECT	DPT_ID ");
			query.append("			, DPT_NM ");
			query.append("			, DPT_KND ");
			query.append(" FROM		DPTMNT ");
			query.append(" ORDER	BY DPT_ID DESC ");

			stmt = conn.prepareStatement(query.toString());
			rs = stmt.executeQuery();

			List<DepartmentVO> departmentList = new ArrayList<DepartmentVO>();

			DepartmentVO departmentVO = null;
			while (rs.next()) {
				departmentVO = new DepartmentVO();
				departmentVO.setDepartmentId(rs.getInt("DPT_ID"));
				departmentVO.setDepartmentName(rs.getString("DPT_NM"));
				departmentVO.setDepartmentKind(rs.getString("DPT_KND"));

				departmentList.add(departmentVO);
			}
			return departmentList;

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
	
	
	

	@Override
	public int insertDepartment(DepartmentVO departmentVO) {
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
		} catch (ClassNotFoundException e) {
			throw new RuntimeException(e.getMessage(), e);
		}

		Connection conn = null;
		PreparedStatement stmt = null;
		
		try {
			conn = DriverManager.getConnection(oracleUrl, "UNIVERSITY", "university");
			
			StringBuffer query = new StringBuffer();
			query.append(" INSERT	INTO DPTMNT ( ");
			query.append("						DPT_ID ");
			query.append("						, DPT_NM ");
			query.append("						, DPT_KND ");
			query.append("						) ");
			query.append(" VALUES				( ");
			query.append("						DPT_ID_SEQ.NEXTVAL ");
			query.append("						, ? ");
			query.append("						, ? ");
			query.append("						) ");
			
			stmt = conn.prepareStatement(query.toString());
			stmt.setString(1, departmentVO.getDepartmentName());
			stmt.setString(2, departmentVO.getDepartmentKind());
			
			return stmt.executeUpdate();
			
		} catch (SQLException e) {
			throw new RuntimeException(e.getMessage(), e);
		} finally {
			try {
				if (stmt != null) {
					stmt.close();
				}
			} catch (SQLException e) {}
			try {
				if (conn != null) {
					conn.close();
				}
			} catch (SQLException e) {}
		}
	}

	

	@Override
	public int deleteDepartment(int departmentId) {
		
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
		} catch (ClassNotFoundException e) {
			throw new RuntimeException(e.getMessage(), e);
		}
		
		Connection conn = null;
		PreparedStatement stmt = null;
		
		try {
			conn = DriverManager.getConnection(oracleUrl, "UNIVERSITY", "university");
			
			StringBuffer query = new StringBuffer();
			query.append(" DELETE ");
			query.append(" FROM		DPTMNT ");
			query.append(" WHERE	DPT_ID = ? ");
			
			stmt = conn.prepareStatement(query.toString());
			stmt.setInt(1, departmentId);
			
			return stmt.executeUpdate();
			
		} catch (SQLException e) {
			throw new RuntimeException(e.getMessage(), e);
		} finally {
			try {
				if (stmt != null) {
					stmt.close();
				}
			} catch (SQLException e) {}
			try {
				if (conn != null) {
					conn.close();
				}
			} catch (SQLException e) {}
		}
	}

	

	

}
