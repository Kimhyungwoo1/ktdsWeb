package com.ktds.khw.bookstore.bookstore.biz;

import java.util.List;

import com.ktds.khw.bookstore.bookstore.vo.BookStoreVO;

public interface BookStoreBiz {
	
	public List<BookStoreVO> printAllList();
	
	public boolean insertBook(BookStoreVO bookStoreVO);
	
	public boolean deleteBook(String bookName);
	
	public boolean updateBook(BookStoreVO bookStoreVO);
	
	public BookStoreVO printOneBook(int bookId);

}
