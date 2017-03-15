package com.ktds.khw.bookstore.bookstore.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.ktds.khw.bookstore.bookstore.vo.BookStoreVO;

public class BookStoreDaoImpl implements BookStoreDao {

	@Override
	public List<BookStoreVO> bookList() {

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
			conn = DriverManager.getConnection(oracleUrl, "BOOKSTORE", "bookstore");

			StringBuffer query = new StringBuffer();
			query.append(" SELECT	BOOK_ID ");
			query.append("			, BOOK_NAME	 ");
			query.append("			, BOOK_AUTHOR ");
			query.append("			, BOOK_PRICE ");
			query.append("			, PUBLISHER ");
			query.append(" FROM		BOOKSTORE.BOOK	");

			stmt = conn.prepareStatement(query.toString());
			rs = stmt.executeQuery();

			List<BookStoreVO> bookList = new ArrayList<BookStoreVO>();
			BookStoreVO bookStoreVO = null;
			while (rs.next()) {
				bookStoreVO = new BookStoreVO();
				bookStoreVO.setBookId(rs.getInt("BOOK_ID"));
				bookStoreVO.setBookName(rs.getString("BOOK_NAME"));
				bookStoreVO.setBookAuthor(rs.getString("BOOK_AUTHOR"));
				bookStoreVO.setBookPrice(rs.getInt("BOOK_PRICE"));
				bookStoreVO.setPublisher(rs.getString("PUBLISHER"));

				bookList.add(bookStoreVO);
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
	public int addBook(BookStoreVO bookStoreVO) {

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
			conn = DriverManager.getConnection(oracleUrl, "BOOKSTORE", "bookstore");

			StringBuffer query = new StringBuffer();
			query.append(" INSERT	INTO BOOK  ( ");
			query.append("						BOOK_ID ");
			query.append("						, BOOK_NAME ");
			query.append("						, BOOK_AUTHOR ");
			query.append("						, BOOK_PRICE ");
			query.append("						, PUBLISHER ");
			query.append("						) ");
			query.append(" VALUES 				( ");
			query.append("						BOOK_ID_SEQ.NEXTVAL ");
			query.append("						, ? ");
			query.append("						, ? ");
			query.append("						, ? ");
			query.append("						, ? ");
			query.append("						) ");

			stmt = conn.prepareStatement(query.toString());
			stmt.setString(1, bookStoreVO.getBookName());
			stmt.setString(2, bookStoreVO.getBookAuthor());
			stmt.setInt(3, bookStoreVO.getBookPrice());
			stmt.setString(4, bookStoreVO.getPublisher());

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
	public int deleteBook(String bookName) {

		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
			System.out.println("오라클 드라이버 로딩 실패, 시스템을 종료합니다.");
			return 0;
		}

		String oracleUrl = "jdbc:oracle:thin:@172.20.10.9:1521:XE";

		Connection conn = null;
		PreparedStatement stmt = null;

		try {
			conn = DriverManager.getConnection(oracleUrl, "BOOKSTORE", "bookstore");

			StringBuffer query = new StringBuffer();
			query.append("  DELETE  ");
			query.append("  FROM 		BOOK  ");
			query.append("  WHERE		BOOK_NAME = ? ");

			stmt = conn.prepareStatement(query.toString());
			stmt.setString(1, bookName);

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
	public int updateBook(BookStoreVO bookStoreVO) {

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
			conn = DriverManager.getConnection(oracleUrl, "BOOKSTORE", "bookstore");

			StringBuffer query = new StringBuffer();
			query.append(" UPDATE	BOOK ");
			query.append(" SET		BOOK_NAME = ? ");
			query.append("			, BOOK_AUTHOR =? ");
			query.append("			, BOOK_PRICE = ? ");
			query.append("			, PUBLISHER = ? ");
			query.append(" WHERE		BOARD_ID = ? ");

			stmt = conn.prepareStatement(query.toString());
			stmt.setString(1, bookStoreVO.getBookName());
			stmt.setString(2, bookStoreVO.getBookAuthor());
			stmt.setInt(3, bookStoreVO.getBookPrice());
			stmt.setString(4, bookStoreVO.getPublisher());
			stmt.setInt(5, bookStoreVO.getBookId());

			return stmt.executeUpdate();

		} catch (SQLException e) {
			e.printStackTrace();
			System.out.println("오라클 인스턴스 로딩 실패 시스템을 종료합니다.");
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
	public BookStoreVO oneBook(int bookId) {

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
			conn = DriverManager.getConnection(oracleUrl, "BOOKSTORE", "bookstore");

			StringBuffer query = new StringBuffer();
			query.append(" SELECT   BOOK_ID ");
			query.append("          , BOOK_NAME ");
			query.append("          , BOOK_AUTHOR ");
			query.append("          , BOOK_PRICE ");
			query.append("          , PUBLISHER ");
			query.append(" FROM     BOOK ");
			query.append(" WHERE    BOOK_ID = ? ");

			stmt = conn.prepareStatement(query.toString());
			stmt.setInt(1, bookId);

			rs = stmt.executeQuery();

			BookStoreVO bookStoreVO = null;

			if (rs.next()) {
				bookStoreVO = new BookStoreVO();
				bookStoreVO.setBookId(rs.getInt("BOOK_ID"));
				bookStoreVO.setBookName(rs.getString("BOOK_NAME"));
				bookStoreVO.setBookAuthor(rs.getString("BOOK_AUTHOR"));
				bookStoreVO.setBookPrice(rs.getInt("BOOK_PRICE"));
				bookStoreVO.setPublisher(rs.getString("PUBLISHER"));
			}

			return bookVO;

		} catch (SQLException e) {
			e.printStackTrace();
			System.out.println("오라클 인스턴스 로딩 실패, 시스템을 종료합니다.");
			return null;
		} finally {
			try {
				if (rs != null) {
					rs.close();
				}
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
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
