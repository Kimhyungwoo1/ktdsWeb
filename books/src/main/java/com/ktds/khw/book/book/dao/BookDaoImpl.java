package com.ktds.khw.book.book.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.ktds.khw.book.book.vo.BookSearchVO;
import com.ktds.khw.book.book.vo.BookVO;

public class BookDaoImpl implements BookDao {

	private String oracleUrl = "jdbc:oracle:thin:@172.20.10.9:1521:XE";

	@Override
	public int getBookListCount(BookSearchVO bookSearchVO) {

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
			query.append(" SELECT	COUNT(B.BOOK_ID) CNT ");
			query.append(" FROM 	BOOK B ");
			query.append("			, USRS U ");
			query.append(" WHERE		B.BOOK_ID = U.USR_ID ");
			
			stmt = conn.prepareStatement(query.toString());
			rs = stmt.executeQuery();
			
			if( rs.next() ){
				return rs.getInt("CNT");
			}
			
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

		return 0;
	}

	@Override
	public List<BookVO> allBookList(BookSearchVO bookSearchVO) {

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

		try {
			conn = DriverManager.getConnection(oracleUrl, "BOOK", "book");

			StringBuffer query = new StringBuffer();
			
			query.append(" SELECT 	*");
			query.append(" FROM		(");
			query.append(" 				SELECT ROWNUM RNUM ");
			query.append(" 						, RST.*");
			query.append(" 				FROM	(");
			query.append(" SELECT		BOOK_ID  ");
			query.append("				, BOOK_NM ");
			query.append("				, BOOK_SUB_NM ");
			query.append("				, IDX ");
			query.append(" FROM			BOOK.BOOK ");
			query.append(" ORDER	BY BOOK_ID DESC ");
			query.append(" 						) RST ");
			query.append(" 				WHERE ROWNUM <= ? ");
			query.append(" 				)");
			query.append(" WHERE	RNUM >= ?");

			stmt = conn.prepareStatement(query.toString());
			stmt.setInt(1, bookSearchVO.getPager().getEndArticleNumber());
			stmt.setInt(2, bookSearchVO.getPager().getStartArticleNumber());
			rs = stmt.executeQuery();

			List<BookVO> bookList = new ArrayList<BookVO>();
			BookVO bookVO = null;

			while (rs.next()) {
				bookVO = new BookVO();
				bookVO.setBookId(rs.getInt("BOOK_ID"));
				bookVO.setBookName(rs.getString("BOOK_NM"));
				bookVO.setBookSubName(rs.getString("BOOK_SUB_NM"));
				bookVO.setIndex(rs.getString("IDX"));

				bookList.add(bookVO);
			}
			return bookList;

		} catch (SQLException e) {
			e.printStackTrace();
			System.out.println("오라클 인스턴스 로딩 실패, 시스템을 종료합니다.");
			return null;
		} finally {
			try {
				if (rs != null) {
					rs.close();
				}
			} catch (SQLException e) {
				e.printStackTrace();
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

	@Override
	public BookVO selectBook(int bookId) {
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
			System.out.println("오라클 드라이버 실패다.");
			return null;
		}

		Connection conn = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;

		try {
			conn = DriverManager.getConnection(oracleUrl, "BOOK", "book");

			StringBuffer query = new StringBuffer();
			query.append(" SELECT		BOOK_ID  ");
			query.append("				, BOOK_NM ");
			query.append("				, BOOK_SUB_NM ");
			query.append("				, IDX ");
			query.append(" FROM			BOOK ");
			query.append(" WHERE		BOOK_ID = ? ");

			stmt = conn.prepareStatement(query.toString());
			stmt.setInt(1, bookId);

			rs = stmt.executeQuery();

			BookVO bookVO = new BookVO();

			if (rs.next()) {
				bookVO.setBookId(rs.getInt("BOOK_ID"));
				bookVO.setBookName(rs.getString("BOOK_NM"));
				bookVO.setBookSubName(rs.getString("BOOK_SUB_NM"));
				bookVO.setIndex(rs.getString("IDX"));
			}
			return bookVO;

		} catch (SQLException e) {
			e.printStackTrace();
			System.out.println("오라클 인스턴스 로딩 실패 시스템을 종료합니다.");
			return null;
		} finally {
			try {
				if (rs != null) {
					rs.close();
				}
			} catch (SQLException e) {
				e.printStackTrace();
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

	@Override
	public int insertBook(BookVO bookVO) {

		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
			System.out.println("오라클 드라이버 로딩 실패, 시스템을 종료합니다.");
			return 0;
		}

		Connection conn = null;
		PreparedStatement stmt = null;

		try {
			conn = DriverManager.getConnection(oracleUrl, "BOOK", "book");

			StringBuffer query = new StringBuffer();
			query.append(" INSERT	INTO BOOK	(");
			query.append("						BOOK_ID");
			query.append("						, BOOK_NM");
			query.append("						, BOOK_SUB_NM");
			query.append("						, IDX");
			query.append("						) ");
			query.append(" VALUES				(");
			query.append("						BOOK_ID_SEQ.NEXTVAL");
			query.append("						, ?");
			query.append("						, ?");
			query.append("						, ?");
			query.append("						) ");

			stmt = conn.prepareStatement(query.toString());
			stmt.setString(1, bookVO.getBookName());
			stmt.setString(2, bookVO.getBookSubName());
			stmt.setString(3, bookVO.getIndex());

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
	public int deleteBook(int bookId) {

		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
			System.out.println("오라클 드라이버 로딩 실패, 시스템을 종료합니다.");
			return 0;
		}

		Connection conn = null;
		PreparedStatement stmt = null;

		try {
			conn = DriverManager.getConnection(oracleUrl, "BOOK", "book");

			StringBuffer query = new StringBuffer();
			query.append(" DELETE ");
			query.append(" FROM		BOOK ");
			query.append(" WHERE	BOOK_ID = ?");

			stmt = conn.prepareStatement(query.toString());
			stmt.setInt(1, bookId);

			return stmt.executeUpdate();

		} catch (SQLException e) {
			e.printStackTrace();
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
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

	}

	@Override
	public int update(BookVO bookVO) {
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
			System.out.println("오라클 드라이버 로딩 실패, 시스템을 종료합니다.");
			return 0;
		}

		Connection conn = null;
		PreparedStatement stmt = null;

		try {
			conn = DriverManager.getConnection(oracleUrl, "BOOK", "book");

			StringBuffer query = new StringBuffer();
			query.append(" UPDATE	BOOK ");
			query.append(" SET		BOOK_ID = ? ");
			query.append("			, BOOK_NM =? ");
			query.append("			, BOOK_SUB_NM = ? ");
			query.append("			, IDX = ? ");
			query.append(" WHERE		BOOK_ID = ? ");

			stmt = conn.prepareStatement(query.toString());
			stmt.setInt(1, bookVO.getBookId());
			stmt.setString(2, bookVO.getBookName());
			stmt.setString(3, bookVO.getBookSubName());
			stmt.setString(4, bookVO.getIndex());
			stmt.setInt(5, bookVO.getBookId());

			return stmt.executeUpdate();

		} catch (SQLException e) {
			e.printStackTrace();
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
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

	}

}
