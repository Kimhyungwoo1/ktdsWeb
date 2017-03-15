package com.ktds.khw.book.book.biz;

import java.util.List;

import com.ktds.khw.book.book.vo.BookSearchVO;
import com.ktds.khw.book.book.vo.BookVO;

public interface BookBiz {
	
	public List<BookVO> bookList(BookSearchVO searchVO);
	
	public BookVO oneBook(int bookId);
	
	public boolean insertBook(BookVO bookVO);
	
	public boolean deleteBook(int bookId);
	
	public boolean updateBook(BookVO bookVO);

}
