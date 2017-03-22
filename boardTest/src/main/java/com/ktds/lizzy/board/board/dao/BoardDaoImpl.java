package com.ktds.lizzy.board.board.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.ktds.lizzy.board.board.vo.BoardSearchVO;
import com.ktds.lizzy.board.board.vo.BoardVO;
import com.ktds.lizzy.dao.support.JDBCDaoSupport;

public class BoardDaoImpl extends JDBCDaoSupport implements BoardDao {
	
	private String oracleUrl = "jdbc:oracle:thin:@172.20.10.9:1521:XE";
	
	public int insertArticle(BoardVO boardVO) {
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
		} catch (ClassNotFoundException e) {
			throw new RuntimeException(e.getMessage(), e);
		}

		Connection conn = null;
		PreparedStatement stmt = null;

		try {
			conn = DriverManager.getConnection(oracleUrl, "BOARD", "board");
			
			StringBuffer query = new StringBuffer();
			query.append("   INSERT INTO    BOARD   ( ");
			query.append("                              BOARD_ID, ");
			query.append("                              SUBJECT, ");
			query.append("                              CONT, ");
			query.append("                              WRTR, ");
			query.append("                              LIKE_CNT, ");
			query.append("                              WRT_DATE, ");
			query.append("                              IP, ");
			query.append("                              PST ");
			query.append("                          ) ");
			query.append("                  VALUES  (");
			query.append("                              BOARD_ID_SEQ.NEXTVAL, ");
			query.append("                              ?, ");
			query.append("                              ?, ");
			query.append("                              ?, ");
			query.append("                              0, ");
			query.append("                              SYSDATE, ");
			query.append("                              ?, ");
			query.append("                              ? ");
			query.append("                          ) ");
			
			stmt = conn.prepareStatement(query.toString());
			stmt.setString(1, boardVO.getSubject());
			stmt.setString(2, boardVO.getContents());
			stmt.setString(3, boardVO.getWriter());
			stmt.setString(4, boardVO.getIp());
			stmt.setString(5, boardVO.getPoster());
			
			return stmt.executeUpdate();
			
		} catch (SQLException e) {
			throw new RuntimeException(e.getMessage(), e);
		} finally {
			try {
				if (stmt != null)
					stmt.close();
			} catch (SQLException e) {
			}

			try {
				if (conn != null)
					conn.close();
			} catch (SQLException e) {
			}
		}
	}

	public int selectAllArticlesCount(BoardSearchVO searchVO) {
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
		} catch (ClassNotFoundException e) {
			throw new RuntimeException(e.getMessage(), e);
		}

		Connection conn = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;

		try {
			conn = DriverManager.getConnection(oracleUrl, "BOARD", "board");
			StringBuffer query = new StringBuffer();
			query.append(" SELECT	COUNT(B.BOARD_ID) CNT ");
			query.append(" FROM		BOARD B               ");
			query.append(" 			, USERS U              ");
			query.append(" WHERE	B.WRTR = U.USR_ID     ");

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
				if (rs != null)
					rs.close();
			} catch (SQLException e) {
			}

			try {
				if (stmt != null)
					stmt.close();
			} catch (SQLException e) {
			}

			try {
				if (conn != null)
					conn.close();
			} catch (SQLException e) {
			}
		}
	}
	
	public List<BoardVO> selectAllArticles(BoardSearchVO searchVO) {
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
		} catch (ClassNotFoundException e) {
			throw new RuntimeException(e.getMessage(), e);
		}

		Connection conn = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;

		try {
			conn = DriverManager.getConnection(oracleUrl, "BOARD", "board");
			
			StringBuffer query = new StringBuffer();
			query.append(" SELECT	* ");
			query.append(" FROM		( ");
			query.append(" 				SELECT	ROWNUM RNUM ");
			query.append(" 						, RST.* ");
			query.append(" 				FROM	( ");
			
			query.append("   SELECT    B.BOARD_ID ");
			query.append("             , B.SUBJECT ");
			query.append("             , B.WRTR ");
			query.append("             , B.LIKE_CNT ");
			query.append("             , B.WRT_DATE ");
			query.append("             , B.IP ");
			query.append("             , U.USR_ID ");
			query.append("             , U.USR_NM ");
			query.append("             , U.JOIN_DT ");
			query.append("   FROM      BOARD B ");
			query.append("             , USERS U ");
			query.append("   WHERE	   B.WRTR = U.USR_ID ");
			query.append("   ORDER	   BY	BOARD_ID DESC ");
			
			query.append(" 						) RST ");
			query.append(" 				WHERE	ROWNUM <= ? ");
			query.append(" 			) ");
			query.append(" WHERE	RNUM >= ? ");

			stmt = conn.prepareStatement(query.toString());
			stmt.setInt(1, searchVO.getPager().getEndArticleNumber());
			stmt.setInt(2, searchVO.getPager().getStartArticleNumber());
			
			rs = stmt.executeQuery();
			
			List<BoardVO> boardList = new ArrayList<BoardVO>();
			BoardVO boardVO = null;
		
			while (rs.next()) {
				boardVO = new BoardVO();
				
				boardVO.setBoardId(rs.getInt("BOARD_ID"));
				boardVO.setSubject(rs.getString("SUBJECT"));
				boardVO.setWriter(rs.getString("WRTR"));
				boardVO.setLikeCount(rs.getInt("LIKE_CNT"));
				boardVO.setWriteDate(rs.getString("WRT_DATE"));
				boardVO.setIp(rs.getString("IP"));
				boardVO.getUserVO().setUserId(rs.getString("USR_ID"));
				boardVO.getUserVO().setUserName(rs.getString("USR_NM"));
				boardVO.getUserVO().setJoinDate(rs.getString("JOIN_DT"));
				
				boardList.add(boardVO);
			}
			
			return boardList;
		} catch (SQLException e) {
			throw new RuntimeException(e.getMessage(), e);
		} finally {
			try {
				if (rs != null)
					rs.close();
			} catch (SQLException e) {
			}

			try {
				if (stmt != null)
					stmt.close();
			} catch (SQLException e) {
			}

			try {
				if (conn != null)
					conn.close();
			} catch (SQLException e) {
			}
		}
	}
	
	public BoardVO selectOneArticle(int boardId) {
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
		} catch (ClassNotFoundException e) {
			throw new RuntimeException(e.getMessage(), e);
		}

		Connection conn = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;

		try {
			conn = DriverManager.getConnection(oracleUrl, "BOARD", "board");
			
			StringBuffer query = new StringBuffer();
			query.append("   SELECT    B.BOARD_ID ");
			query.append("             , B.SUBJECT ");
			query.append("             , B.CONT ");
			query.append("             , B.WRTR ");
			query.append("             , B.LIKE_CNT ");
			query.append("             , B.WRT_DATE ");
			query.append("             , B.IP ");
			query.append("             , B.PST ");
			query.append("             , U.USR_ID ");
			query.append("             , U.USR_NM ");
			query.append("             , U.JOIN_DT ");
			query.append("   FROM      BOARD B ");
			query.append("             , USERS U ");
			query.append("   WHERE	   B.WRTR = U.USR_ID ");
			query.append("   AND       BOARD_ID = ?");

			stmt = conn.prepareStatement(query.toString());
			stmt.setInt(1, boardId);
			
			rs = stmt.executeQuery();
			
			BoardVO boardVO = null;
			if (rs.next()) {
				boardVO = new BoardVO();
				
				boardVO.setBoardId(rs.getInt("BOARD_ID"));
				boardVO.setSubject(rs.getString("SUBJECT"));
				boardVO.setContents(rs.getString("CONT"));
				boardVO.setWriter(rs.getString("WRTR"));
				boardVO.setLikeCount(rs.getInt("LIKE_CNT"));
				boardVO.setWriteDate(rs.getString("WRT_DATE"));
				boardVO.setIp(rs.getString("IP"));
				boardVO.setPoster(rs.getString("PST"));
				
				boardVO.getUserVO().setUserId(rs.getString("USR_ID"));
				boardVO.getUserVO().setUserName(rs.getString("USR_NM"));
				boardVO.getUserVO().setJoinDate(rs.getString("JOIN_DT"));
			}
			
			return boardVO;
		} catch (SQLException e) {
			throw new RuntimeException(e.getMessage(), e);
		} finally {
			try {
				if (rs != null)
					rs.close();
			} catch (SQLException e) {
			}

			try {
				if (stmt != null)
					stmt.close();
			} catch (SQLException e) {
			}

			try {
				if (conn != null)
					conn.close();
			} catch (SQLException e) {
			}
		}
	}

	public int removeArticle(int boardId) {
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
		} catch (ClassNotFoundException e) {
			throw new RuntimeException(e.getMessage(), e);
		}

		Connection conn = null;
		PreparedStatement stmt = null;

		try {
			conn = DriverManager.getConnection(oracleUrl, "BOARD", "board");
			
			StringBuffer query = new StringBuffer();
			query.append("   DELETE ");
			query.append("   FROM   BOARD ");
			query.append("   WHERE  BOARD_ID = ? ");
			
			stmt = conn.prepareStatement(query.toString());
			stmt.setInt(1, boardId);
			
			return stmt.executeUpdate();
			
		} catch (SQLException e) {
			throw new RuntimeException(e.getMessage(), e);
		} finally {
			try {
				if (stmt != null)
					stmt.close();
			} catch (SQLException e) {
			}

			try {
				if (conn != null)
					conn.close();
			} catch (SQLException e) {
			}
		}
	}

	public int updateArticle(BoardVO boardVO) {
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
		} catch (ClassNotFoundException e) {
			throw new RuntimeException(e.getMessage(), e);
		}

		Connection conn = null;
		PreparedStatement stmt = null;

		try {
			conn = DriverManager.getConnection(oracleUrl, "BOARD", "board");
			
			StringBuffer query = new StringBuffer();
			query.append(" UPDATE  BOARD ");
			query.append(" SET     WRTR = ? ");
			query.append("         ,SUBJECT = ? ");
			query.append("         ,CONT = ? ");
			query.append(" WHERE   BOARD_ID = ? ");
			
			stmt = conn.prepareStatement(query.toString());
			stmt.setString(1, boardVO.getWriter());
			stmt.setString(2, boardVO.getSubject());
			stmt.setString(3, boardVO.getContents());
			stmt.setInt(4, boardVO.getBoardId());
			
			return stmt.executeUpdate();
			
		} catch (SQLException e) {
			throw new RuntimeException(e.getMessage(), e);
		} finally {
			try {
				if (stmt != null)
					stmt.close();
			} catch (SQLException e) {
			}

			try {
				if (conn != null)
					conn.close();
			} catch (SQLException e) {
			}
		}
	}

}
