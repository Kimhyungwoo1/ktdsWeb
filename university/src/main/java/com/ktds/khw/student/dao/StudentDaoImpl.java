package com.ktds.khw.student.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.ktds.khw.student.vo.StudentVO;

public class StudentDaoImpl implements StudentDao{
	
	private String oracleUrl = "jdbc:oracle:thin:@172.20.10.9:1521:XE";
	@Override
	public List<StudentVO> studentList() {
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
			query.append(" SELECT	STDNT_ID ");
			query.append("			, STDNT_NM ");
			query.append("			, STDNT_CLSS ");
			query.append("			, STDNT_SX ");
			query.append("			, DPT_ID ");
			query.append(" FROM		STDNT ");
			query.append(" ORDER	BY STDNT_ID DESC ");

			stmt = conn.prepareStatement(query.toString());
			rs = stmt.executeQuery();

			List<StudentVO> studentList = new ArrayList<StudentVO>();

			StudentVO studentVO = null;
			while (rs.next()) {
				studentVO = new StudentVO();
				studentVO.setStudentId(rs.getInt("STDNT_ID"));
				studentVO.setStudentName(rs.getString("STDNT_NM"));
				studentVO.setStudentClass(rs.getString("STDNT_CLSS"));
				studentVO.setStudentSx(rs.getString("STDNT_SX"));
				studentVO.setDepartmentId(rs.getInt("DPT_ID"));
				
				studentList.add(studentVO);
			}
			return studentList;

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
	public int insertStudentVO(StudentVO studentVO) {
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
			query.append(" INSERT	INTO STDNT ( ");
			query.append("						STDNT_ID ");
			query.append("						, STDNT_NM ");
			query.append("						, STDNT_CLSS ");
			query.append("						, STDNT_SX ");
			query.append("						, DPT_ID ");
			query.append("						) ");
			query.append(" VALUES				( ");
			query.append("						STDNT_ID_SEQ.NEXTVAL ");
			query.append("						, ? ");
			query.append("						, ? ");
			query.append("						, ? ");
			query.append("						, ? ");
			query.append("						) ");
			
			stmt = conn.prepareStatement(query.toString());
			
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
	public int deleteStudent(int studentId) {
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
			query.append(" FROM		STDNT ");
			query.append(" WHERE	STDNT_ID = ? ");
			
			stmt = conn.prepareStatement(query.toString());
			stmt.setInt(1, studentId);
			
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
