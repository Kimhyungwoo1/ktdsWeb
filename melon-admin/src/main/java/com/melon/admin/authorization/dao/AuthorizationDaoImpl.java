package com.melon.admin.authorization.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.melon.admin.authorization.vo.AuthorizationSearchVO;
import com.melon.admin.authorization.vo.AuthorizationVO;
import com.melon.admin.common.dao.JdbcDaoSupport;

public class AuthorizationDaoImpl implements AuthorizationDao {

	private String oracleUrl = "jdbc:oracle:thin:@172.20.10.9:1521:XE";

	@Override
	public String generateNewAuthorizationId() {

		JdbcDaoSupport dao = new JdbcDaoSupport() {			//추상클레스 객체화 시키기
			
			@Override
			public String query() {
				StringBuffer query = new StringBuffer();
				query.append(" SELECT	'AT-' || TO_CHAR(SYSDATE, 'YYYYMMDDHH24') || '-' || LPAD (ATHRZTN_ID_SEQ.NEXTVAL, 6, '0') SEQ ");
				query.append(" FROM		DUAL ");
				return query.toString();
			}
			
			@Override
			public void mappingParams(PreparedStatement stmt)  throws SQLException {
			}
			
			@Override
			public Object bindData(ResultSet rs)  throws SQLException {
				return rs.getString("SEQ");
			}
		};
		return (String) dao.selectOne();
	}

	@Override
	public int insertNewAuthorization(AuthorizationVO authorizationVO) {

		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
		} catch (ClassNotFoundException e) {
			throw new RuntimeException(e.getMessage(), e);
		}
		Connection conn = null;
		PreparedStatement stmt = null;

		try {
			conn = DriverManager.getConnection(oracleUrl, "MELON", "melon");
			StringBuffer query = new StringBuffer();
			query.append(" INSERT		INTO	ATHRZTN ( ");
			query.append(" 							ATHRZTN_ID ");
			query.append(" 							, ATHRZTN_NM ");
			query.append(" 							, PRNT_ATHRZTN_ID ");
			query.append(" 							) ");
			query.append(" VALUES					( ");
			query.append("							? ");
			query.append(" 							, ? ");
			query.append(" 							, ? ");
			query.append(" 							) ");

			stmt = conn.prepareStatement(query.toString());
			stmt.setString(1, authorizationVO.getAuthorizationId());
			stmt.setString(2, authorizationVO.getAuthorizationName());
			stmt.setString(3, authorizationVO.getParentAuthorizationId());

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
	public int selectAllAuthorizationCount(AuthorizationSearchVO authorizationSearchVO) {

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

			StringBuffer query = new StringBuffer();
			query.append(" SELECT		COUNT(1) CNT ");
			query.append(" FROM			ATHRZTN ");

			stmt = conn.prepareStatement(query.toString());

			rs = stmt.executeQuery();

			if (rs.next()) {
				return rs.getInt("CNT");
			}
			return 0;
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

	@Override
	public List<AuthorizationVO> selectAllAuthorization(AuthorizationSearchVO authorizationSearchVO) {

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

			StringBuffer query = new StringBuffer();
			query.append(" SELECT		* ");
			query.append(" FROM			( ");
			query.append(" 				SELECT ROWNUM AS RNUM ");
			query.append(" 				, A.* ");
			query.append(" 				FROM	( ");
			query.append(" 						SELECT	ATHRZTN_ID ");
			query.append(" 								, ATHRZTN_NM ");
			query.append(" 								, PRNT_ATHRZTN_ID ");
			query.append(" 						FROM	ATHRZTN ");
			query.append(" 						ORDER	BY ATHRZTN_ID DESC ");
			query.append(" 						) A ");
			query.append(" 				WHERE	ROWNUM <= ? ");
			query.append(" 				) ");
			query.append(" WHERE	RNUM >= ? ");

			stmt = conn.prepareStatement(query.toString());
			stmt.setInt(1, authorizationSearchVO.getPager().getEndArticleNumber());
			stmt.setInt(2, authorizationSearchVO.getPager().getStartArticleNumber());

			rs = stmt.executeQuery();

			List<AuthorizationVO> authorizationList = new ArrayList<AuthorizationVO>();
			AuthorizationVO authorizationVO = null;

			while (rs.next()) {
				authorizationVO = new AuthorizationVO();
				authorizationVO.setAuthorizationId(rs.getString("ATHRZTN_ID"));
				authorizationVO.setAuthorizationName(rs.getString("ATHRZTN_NM"));
				authorizationVO.setParentAuthorizationId(rs.getString("PRNT_ATHRZTN_ID"));

				authorizationList.add(authorizationVO);
			}
			return authorizationList;

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

	@Override
	public int updateAuthorizationInfo(AuthorizationVO authorizationVO) {

		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
		} catch (ClassNotFoundException e) {
			throw new RuntimeException(e.getMessage(), e);
		}
		Connection conn = null;
		PreparedStatement stmt = null;

		try {
			conn = DriverManager.getConnection(oracleUrl, "MELON", "melon");

			StringBuffer query = new StringBuffer();
			query.append(" UPDATE		ATHRZTN ");
			query.append(" SET			ATHRZTN_ID = ? ");
			query.append(" 				, ATHRZTN_NM = ? ");
			query.append(" 				, PRNT_ATHRZTN_ID = ? ");
			query.append(" WHERE		ATHRZTN_ID = ? ");

			stmt = conn.prepareStatement(query.toString());
			stmt.setString(1, authorizationVO.getAuthorizationId());
			stmt.setString(2, authorizationVO.getAuthorizationName());
			stmt.setString(3, authorizationVO.getParentAuthorizationId());
			stmt.setString(4, authorizationVO.getAuthorizationId());

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
	public int deleteOneAuthorization(String authorizationId) {

		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
		} catch (ClassNotFoundException e) {
			throw new RuntimeException(e.getMessage(), e);
		}
		Connection conn = null;
		PreparedStatement stmt = null;

		try {
			conn = DriverManager.getConnection(oracleUrl, "MELON", "melon");

			StringBuffer query = new StringBuffer();
			query.append(" DELETE		");
			query.append(" FROM		ATHRZTN ");
			query.append(" WHERE		ATHRZTN_ID = ? ");

			stmt = conn.prepareStatement(query.toString());
			stmt.setString(1, authorizationId);

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

}
