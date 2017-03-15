package com.ktds.khw.book.book.dao;

import java.util.List;

import com.ktds.khw.book.book.vo.BookSearchVO;
import com.ktds.khw.book.book.vo.BookVO;

public interface BookDao {
	
	public int getBookListCount(BookSearchVO bookSearchVO);
	
	public List<BookVO> allBookList(BookSearchVO bookSearchVO);
	
	public BookVO selectBook(int bookId);
	
	public int insertBook(BookVO bookVO);
	
	public int deleteBook(int bookId);
	
	public int update (BookVO bookVO);

}
