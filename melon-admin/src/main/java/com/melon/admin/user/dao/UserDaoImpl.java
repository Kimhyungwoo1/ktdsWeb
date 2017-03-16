package com.melon.admin.user.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.melon.admin.user.vo.UserSearchVO;
import com.melon.admin.user.vo.UserVO;

public class UserDaoImpl implements UserDao {

	private String oracleUrl = "jdbc:oracle:thin:@172.20.10.9:1521:XE";

	@Override
	public int insertNewUser(UserVO userVO) {

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
			query.append(" INSERT		INTO	USR ( ");
			query.append(" 						USR_ID ");
			query.append(" 						, USR_NM ");
			query.append(" 						, USR_PWD ");
			query.append(" 						, USR_PNT ");
			query.append(" 						, ATHRZTN_ID ");
			query.append(" 						) ");
			query.append(" VALUES					( ");
			query.append(" 						? ");
			query.append(" 						, ? ");
			query.append(" 						, ? ");
			query.append(" 						, ? ");
			query.append(" 						, ? ");
			query.append(" 						) ");

			stmt = conn.prepareStatement(query.toString());
			stmt.setString(1, userVO.getUserId());
			stmt.setString(2, userVO.getUserName());
			stmt.setString(3, userVO.getUserPassword());
			stmt.setInt(4, userVO.getUserPoint());
			stmt.setString(5, userVO.getAuthorizationId());

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
	public List<UserVO> selectAllUser(UserSearchVO userSearchVO) {
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
			query.append(" SELECT	* ");
			query.append(" FROM	( ");
			query.append(" 			SELECT	ROWNUM AS RNUM ");
			query.append(" 			, A.* ");
			query.append(" 			FROM	( ");
			query.append(" 						SELECT	U.USR_ID ");
			query.append(" 								, U.USR_NM ");
			query.append(" 								, U.USR_PWD ");
			query.append(" 								, U.USR_PNT ");
			query.append(" 								, U.ATHRZTN_ID U_ATHRZTN_ID ");
			query.append(" 								, A.ATHRZTN_ID ");
			query.append(" 								, A.ATHRZTN_NM ");
			query.append(" 								, A.PRNT_ATHRZTN_ID ");
			query.append(" 						FROM	USR U ");
			query.append(" 								, ATHRZTN A ");
			query.append(" 						WHERE	U.ATHRZTN_ID = A.ATHRZTN_ID (+) ");
			query.append(" 					) A ");
			query.append(" 			WHERE	ROWNUM <= ? ");
			query.append(" 		) ");
			query.append(" WHERE	RNUM >= ? ");
			
			stmt = conn.prepareStatement(query.toString());
			stmt.setInt(1, userSearchVO.getPager().getEndArticleNumber());
			stmt.setInt(2, userSearchVO.getPager().getStartArticleNumber());
			
			rs = stmt.executeQuery();
			
			List<UserVO> userList = new ArrayList<UserVO>();
			UserVO userVO = null;
			while (rs.next()) {
				userVO = new UserVO();
				userVO.setIndex(rs.getInt("RNUM"));
				userVO.setUserId(rs.getString("USR_ID"));
				userVO.setUserName(rs.getString("USR_NM"));
				userVO.setUserPassword(rs.getString("USR_PWD"));
				userVO.setUserPoint(rs.getInt("USR_PNT"));
				userVO.setAuthorizationId(rs.getString("U_ATHRZTN_ID"));
				
				userVO.getAuthorizationVO().setAuthorizationId(rs.getString("ATHRZTN_ID"));
				userVO.getAuthorizationVO().setAuthorizationName(rs.getString("ATHRZTN_NM"));
				userVO.getAuthorizationVO().setParentAuthorizationId(rs.getString("PRNT_ATHRZTN_ID"));
				
				userList.add(userVO);
			}
			return userList;
			
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
	public UserVO selectOneUser(String userId) {
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
			query.append(" SELECT	U.USR_ID ");
			query.append(" 			, U.USR_NM ");
			query.append(" 			, U.USR_PWD ");
			query.append(" 			, U.USR_PNT ");
			query.append(" 			, U.ATHRZTN_ID U_ATHRZTN_ID ");
			query.append(" 			, A.ATHRZTN_ID  ");
			query.append(" 			, A.ATHRZTN_NM ");
			query.append(" 			, A.PRNT_ATHRZTN_ID ");
			query.append(" FROM		USR U ");
			query.append(" 			, ATHRZTN A ");
			query.append(" WHERE		U.ATHRZTN_ID = A.ATHRZTN_ID (+) ");
			query.append(" AND		U.USR_ID = ?  ");
			
			stmt = conn.prepareStatement(query.toString());
			stmt.setString(1, userId);
			
			rs = stmt.executeQuery();
			UserVO userVO = null;
			if ( rs.next() ) {
				userVO = new UserVO();
				userVO.setUserId(rs.getString("USR_ID"));
				userVO.setUserName(rs.getString("USR_NM"));
				userVO.setUserPassword(rs.getString("USR_PWD"));
				userVO.setUserPoint(rs.getInt("USR_PNT"));
				userVO.setAuthorizationId(rs.getString("U_ATHRZTN_ID"));
				
				userVO.getAuthorizationVO().setAuthorizationId(rs.getString("ATHRZTN_ID"));
				userVO.getAuthorizationVO().setAuthorizationName(rs.getString("ATHRZTN_NM"));
				userVO.getAuthorizationVO().setParentAuthorizationId(rs.getString("PRNT_ATHRZTN_ID"));
				
			}
			return userVO;
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
	public UserVO selectOneUser(UserVO userVO) {
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
			query.append(" SELECT	U.USR_ID ");
			query.append(" 			, U.USR_PWD ");
			query.append(" 			, U.USR_NM ");
			query.append("			, U.USR_PNT ");
			query.append("			, U.ATHRZTN_ID U_ATHRZTN_ID ");
			query.append("			, A.ATHRZTN_ID ");
			query.append("			, A.ATHRZTN_NM  ");
			query.append("			, A.PRNT_ATHRZTN_ID ");
			query.append(" FROM		USR U ");
			query.append(" 			, ATHRZTN A ");
			query.append(" WHERE		U.ATHRZTN_ID = A.ATHRZTN_ID (+) ");
			query.append(" AND		USR_ID = ? ");
			query.append(" AND		USR_PWD = ? ");

			stmt = conn.prepareStatement(query.toString());
			stmt.setString(1, userVO.getUserId());
			stmt.setString(2, userVO.getUserPassword());

			rs = stmt.executeQuery();

			if (rs.next()) {
				userVO = new UserVO();
				userVO.setUserId(rs.getString("USR_ID"));
				userVO.setUserPassword(rs.getString("USR_PWD"));
				userVO.setUserName(rs.getString("USR_NM"));
				userVO.setUserPoint(rs.getInt("USR_PNT"));
				userVO.setAuthorizationId(rs.getString("ATHRZTN_ID"));
				
				userVO.getAuthorizationVO().setAuthorizationId(rs.getString("ATHRZTN_ID"));
				userVO.getAuthorizationVO().setAuthorizationName(rs.getString("ATHRZTN_NM"));
				userVO.getAuthorizationVO().setParentAuthorizationId(rs.getString("PRNT_ATHRZTN_ID"));
			}
			return userVO;
			
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
	public int updateUserInfo(UserVO userVO) {
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
			query.append(" UPDATE	USR ");
			query.append(" SET		USR_ID = ? ");
			query.append(" 			, USR_NM = ? ");
			query.append(" 			, USR_PWD = ? ");
			query.append(" 			, USR_PNT = ? ");
			query.append(" 			, ATHRZTN_ID = ? ");
			query.append(" WHERE 	USR_ID = ? ");
			
			stmt = conn.prepareStatement(query.toString());
			stmt.setString(1, userVO.getUserId());
			stmt.setString(2, userVO.getUserName());
			stmt.setString(3, userVO.getUserPassword());
			stmt.setInt(4, userVO.getUserPoint());
			stmt.setString(5, userVO.getAuthorizationId());
			stmt.setString(6, userVO.getUserId());
			
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
	public int deleteOneUser(String userId) {
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
			query.append(" DELETE ");
			query.append(" FROM		USR ");
			query.append(" WHERE		USR_ID = ? ");
			
			stmt = conn.prepareStatement(query.toString());
			stmt.setString(1, userId);
			
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
	public int selectCountUserId(UserSearchVO userSearchVO) {
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
			query.append(" SELECT	COUNT(1) CNT ");
			query.append(" FROM		USR ");
			
			stmt = conn.prepareStatement(query.toString());
			
			rs = stmt.executeQuery();
			
			if(rs.next()) {
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
